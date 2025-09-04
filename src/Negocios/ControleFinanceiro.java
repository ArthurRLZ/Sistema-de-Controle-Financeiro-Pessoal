package Negocios;
	
import Dados.RepositorioCategoria;
import Dados.RepositorioConta;
import Dados.RepositorioDespesaRecorrente;
import Dados.RepositorioTransacao;
import Negocios.exceptions.NegocioException;
import Negocios.exceptions.SaldoInsuficienteException;
import java.util.List;
import java.time.LocalDate;
import java.io.Serializable;

public class ControleFinanceiro implements Serializable {
    private static final long serialVersionUID = 1L;

    private RepositorioConta repositorioConta;
    private RepositorioCategoria repositorioCategoria;
    private RepositorioTransacao repositorioTransacao;
    private RepositorioDespesaRecorrente repositorioDespesaRecorrente;

    // Construtor
    public ControleFinanceiro(RepositorioConta repositorioConta, RepositorioCategoria repositorioCategoria, RepositorioTransacao repositorioTransacao, RepositorioDespesaRecorrente repositorioDespesaRecorrente) {
        this.repositorioConta = repositorioConta;
        this.repositorioCategoria = repositorioCategoria;
        this.repositorioTransacao = repositorioTransacao;
        this.repositorioDespesaRecorrente = repositorioDespesaRecorrente;
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
        if(conta == null){
            throw new NegocioException("Conta com ID: "+id+" não encontrada.");
        }
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
    public void adicionarReceita(int contaId, double valor, String descricao, Categoria categoria) throws NegocioException {
        Conta conta = buscarContaPorId(contaId);
        if(contaId == null){
            throw new NegocioException("Conta com ID: "+contaId+" não encontrada.");
        }
        if(valor<0){
            throw new NegocioException("O valor da receita deve ser positivo.");
        }
        if(categoria == null){
            throw new NegocioException("Categoria não pode ser nula.");
        }
        conta.creditar(valor);
        Receita receita = new Receita(valor, LocalDate.now(), descricao, conta, categoria);
        this.repositorioTransacao.adicionar(receita);
    }

    public void adicionarDespesa(int contaId, double valor, String descricao, int categoriaId) throws NegocioException, SaldoInsuficienteException {
        Conta conta = buscarContaPorId(contaId);
        if(contaId == null){
            throw new NegocioException("Conta com ID: "+contaId+" não encontrada.");
        }
        if(contaId.getSaldo() < valor){
            throw new SaldoInsuficienteException("Saldo insuficiente para essa despesa.");
        }
        Categoria categoria = buscarCategoriaPorId(categoriaId);
        if(categoria == null){
            throw new NegocioException("Categoria com ID: "+categoriaId+" não encontrada.");
        }
        if(valor < 0){
            throw new NegocioException("O valor da despesa deve ser positivo.");
        }
        conta.debitar(valor);
        Despesa despesa = new Despesa(valor, LocalDate.now(), descricao, conta, categoria);
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
    public void adicionarDespesaRecorrente(double valor, String descricao, int contaId, int categoriaId, Periodicidade periodicidade, int numeroDeParcelas) throws NegocioException {
        Conta conta = buscarContaPorId(contaId);
        Categoria categoria = buscarCategoriaPorId(categoriaId);
        DespesaRecorrente dr = new DespesaRecorrente(valor, LocalDate.now(), descricao, conta, categoria, periodicidade, numeroDeParcelas);
        this.repositorioDespesaRecorrente.adicionar(dr);
    }
    
    public void processarDespesasRecorrentes() throws NegocioException, SaldoInsuficienteException {
        List<DespesaRecorrente> recorrentes = this.repositorioDespesaRecorrente.listarTodas();
        for (DespesaRecorrente dr : recorrentes) {
            if (dr.isVencida()) {
                adicionarDespesa(dr.getConta().getId(), dr.getValor(), dr.getDescricao(), dr.getCategoria().getId());
                dr.atualizarParaProximaOcorrencia();
            }
        }
    }
    
    public List<DespesaRecorrente> listarDespesasRecorrentes() {
        return this.repositorioDespesaRecorrente.listarTodas();
    }

    public void removerDespesaRecorrente(int id) throws NegocioException {
        // Regra: Apenas remove o "modelo" da despesa recorrente.
        // As transações que ela já gerou continuam no histórico.
        DespesaRecorrente dr = buscarDespesaRecorrentePorId(id);
        this.repositorioDespesaRecorrente.remover(dr.getId());
    }

    public DespesaRecorrente buscarDespesaRecorrentePorId(int id) throws NegocioException {
        DespesaRecorrente dr = this.repositorioDespesaRecorrente.buscarPorId(id);
        if (dr == null) {
            throw new NegocioException("Despesa Recorrente com ID " + id + " não encontrada.");
        }
        return dr;
    }
    
    // Gerenciamento de Persistência
    
    
    
    // Gerenciamento de Relatórios
    public String gerarBalanco() {
        Relatorio relatorio = new Relatorio();
        return relatorio.gerarBalanco(this.repositorioTransacao.listarTodas());
    }

    public String gerarRelatorioGastoPorCategoria() {
        Relatorio relatorio = new Relatorio();
        return relatorio.gerarRelatorioGastoPorCategoria(this.repositorioTransacao.listarTodas());
    }
    
    public String gerarRelatorioMensal(int ano, int mes) {
        Relatorio relatorio = new Relatorio();
        return relatorio.gerarRelatorioMensal(this.repositorioTransacao.listarTodas(), ano, mes);
    }

    public String gerarRelatorioTrimestral(int ano, int trimestre) {
        Relatorio relatorio = new Relatorio();
        return relatorio.gerarRelatorioTrimestral(this.repositorioTransacao.listarTodas(), ano, trimestre);
    }

    public String gerarRelatorioSemestral(int ano, int semestre) {
        Relatorio relatorio = new Relatorio();
        return relatorio.gerarRelatorioSemestral(this.repositorioTransacao.listarTodas(), ano, semestre);
    }
    
    public String exibirRelatorioAnual(int ano) {
        Relatorio relatorio = new Relatorio();
        return relatorio.gerarRelatorioAnual(this.repositorioTransacao.listarTodas(), ano);
    }
}
