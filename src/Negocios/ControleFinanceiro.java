package Negocios;

import Dados.RepositorioCategoria;
import Dados.RepositorioConta;
import Dados.RepositorioDespesaRecorrente;
import Dados.RepositorioTransacao;
import Negocios.exceptions.NegocioException;
import Negocios.exceptions.SaldoInsuficienteException;

import java.util.Date;
import java.util.List;

public class ControleFinanceiro {

    private RepositorioConta repositorioConta;
    private RepositorioCategoria repositorioCategoria;
    private RepositorioTransacao repositorioTransacao;
    private RepositorioDespesaRecorrente repositorioDespesaRecorrente;

    // Construtor
    public ControleFinanceiro() {
        this.repositorioConta = new RepositorioConta();
        this.repositorioCategoria = new RepositorioCategoria();
        this.repositorioTransacao = new RepositorioTransacao();
        this.repositorioDespesaRecorrente = new RepositorioDespesaRecorrente();
    }

    // Gerenciamento de Contas
    public void criarConta(String nome) throws NegocioException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new NegocioException("O nome da conta não pode ser vazio.");
        }
        Conta novaConta = new Conta(nome);
        this.repositorioConta.adicionar(novaConta);
    }

    public void editarNomeConta(int id, String novoNome) throws NegocioException {
        if (novoNome == null || novoNome.trim().isEmpty()) {
            throw new NegocioException("O novo nome da conta não pode ser vazio.");
        }
        Conta conta = buscarContaPorId(id);
        conta.setNome(novoNome);
    }

    public void removerConta(int id) throws NegocioException {
        Conta conta = buscarContaPorId(id);
        boolean contaTemTransacao = this.repositorioTransacao.listarTodas().stream()
                .anyMatch(t -> t.getConta().equals(conta));
        if (contaTemTransacao) {
            throw new NegocioException("Não é possível remover uma conta que possui transações.");
        }
        this.repositorioConta.remover(id);
    }

    public Conta buscarContaPorId(int id) throws NegocioException {
        Conta conta = this.repositorioConta.buscarPorId(id);
        if (conta == null) {
            throw new NegocioException("Conta com ID " + id + " não encontrada.");
        }
        return conta;
    }

    public List<Conta> listarContas() {
        return this.repositorioConta.listarTodas();
    }

    // Gerenciamento de Categorias
    public void criarCategoria(String nome) throws NegocioException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new NegocioException("O nome da categoria não pode ser vazio.");
        }
        Categoria novaCategoria = new Categoria(nome);
        this.repositorioCategoria.adicionar(novaCategoria);
    }
    
    public void editarNomeCategoria(int id, String novoNome) throws NegocioException {
        if (novoNome == null || novoNome.trim().isEmpty()) {
            throw new NegocioException("O novo nome da categoria não pode ser vazio.");
        }
        Categoria categoria = buscarCategoriaPorId(id);
        categoria.setNome(novoNome);
    }
    
    public void removerCategoria(int id) throws NegocioException {
        Categoria categoria = buscarCategoriaPorId(id);
        boolean categoriaEmUso = this.repositorioTransacao.listarTodas().stream()
                .filter(t -> t instanceof Despesa)
                .map(t -> (Despesa) t)
                .anyMatch(d -> d.getCategoria().equals(categoria));
        if (categoriaEmUso) {
            throw new NegocioException("Não é possível remover uma categoria que está em uso por uma despesa.");
        }
        this.repositorioCategoria.remover(id);
    }

    public Categoria buscarCategoriaPorId(int id) throws NegocioException {
        Categoria categoria = this.repositorioCategoria.buscarPorId(id);
        if (categoria == null) {
            throw new NegocioException("Categoria com ID " + id + " não encontrada.");
        }
        return categoria;
    }

    public List<Categoria> listarCategorias() {
        return this.repositorioCategoria.listarTodas();
    }

    // Gerenciamento de Transações
    public void adicionarReceita(int contaId, double valor, String descricao) throws NegocioException {
        Conta conta = buscarContaPorId(contaId);
        conta.creditar(valor);
        Receita receita = new Receita(valor, new Date(), descricao, conta);
        this.repositorioTransacao.adicionar(receita);
    }

    public void adicionarDespesa(int contaId, double valor, String descricao, int categoriaId) throws NegocioException, SaldoInsuficienteException {
        Conta conta = buscarContaPorId(contaId);
        Categoria categoria = buscarCategoriaPorId(categoriaId);
        conta.debitar(valor);
        Despesa despesa = new Despesa(valor, new Date(), descricao, conta, categoria);
        this.repositorioTransacao.adicionar(despesa);
    }
    
    public void removerTransacao(int id) throws NegocioException, SaldoInsuficienteException {
        Transacao transacao = buscarTransacaoPorId(id);
        if (transacao instanceof Receita) {
            transacao.getConta().debitar(transacao.getValor());
        } else if (transacao instanceof Despesa) {
            transacao.getConta().creditar(transacao.getValor());
        }
        this.repositorioTransacao.remover(id);
    }
    
    public Transacao buscarTransacaoPorId(int id) throws NegocioException {
        Transacao transacao = this.repositorioTransacao.buscarPorId(id);
        if (transacao == null) {
            throw new NegocioException("Transação com ID " + id + " não encontrada.");
        }
        return transacao;
    }
    
    public List<Transacao> listarTransacoes() {
        return this.repositorioTransacao.listarTodas();
    }
    
    // Gerenciamento de Despesas Recorrentes
    public void adicionarDespesaRecorrente(double valor, String descricao, int contaId, int categoriaId, Periodicidade periodicidade) throws NegocioException {
        Conta conta = buscarContaPorId(contaId);
        Categoria categoria = buscarCategoriaPorId(categoriaId);
        DespesaRecorrente dr = new DespesaRecorrente(valor, new Date(), descricao, conta, categoria, periodicidade);
        this.repositorioDespesaRecorrente.adicionar(dr);
    }
    
    public void processarDespesasRecorrentes() throws NegocioException, SaldoInsuficienteException {
        List<DespesaRecorrente> recorrentes = this.repositorioDespesaRecorrente.listarTodas();
        for (DespesaRecorrente dr : recorrentes) {
            if (dr.isVencida()) {
                // Adiciona a despesa real no registro de transações
                adicionarDespesa(dr.getConta().getId(), dr.getValor(), dr.getDescricao(), dr.getCategoria().getId());
                // Atualiza a despesa recorrente para a próxima data de vencimento
                dr.atualizarParaProximaOcorrencia();
            }
        }
    }
    
    public List<DespesaRecorrente> listarDespesasRecorrentes() {
        return this.repositorioDespesaRecorrente.listarTodas();
    }
}
