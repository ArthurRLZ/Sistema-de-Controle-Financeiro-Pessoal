package Fachada;

import Dados.*;
import Negocios.*;
import Negocios.exceptions.NegocioException;
import Negocios.exceptions.SaldoInsuficienteException;
import java.io.IOException;
import java.util.List;


public class FinanceiroFachada {

    private ControleFinanceiro controlador;

    public FinanceiroFachada() {
        // Tenta carregar o sistema a partir de um arquivo
    	  this.controlador = PersistenciaDados.carregarDadosSerializacao("dados.dat");
        // Se o arquivo não existir (ou houver um erro), cria um novo controlador
        // com repositórios vazios
        if (this.controlador == null) {
            this.controlador = new ControleFinanceiro(
                new RepositorioConta(),
                new RepositorioCategoria(),
                new RepositorioTransacao(),
                new RepositorioDespesaRecorrente()
            );
        }
    }
    

    // Gerenciamento de Contas
    public void criarConta(String nome) throws NegocioException {
        Conta novaConta = new Conta(nome);
        controlador.criarConta(novaConta.getNome());
    }

    public void editarNomeConta(int id, String novoNome) throws NegocioException {
        controlador.editarNomeConta(id, novoNome);
    }

    public void removerConta(int id) throws NegocioException {
        controlador.removerConta(id);
    }

    public Conta buscarContaPorId(int id) throws NegocioException {
        return controlador.buscarContaPorId(id);
    }

    public List<Conta> listarContas() {
        return controlador.listarContas();
    }

    // Gerenciamento de Categorias
    public void criarCategoria(String nome) throws NegocioException {
        controlador.criarCategoria(nome);
    }
    
    public void editarNomeCategoria(int id, String novoNome) throws NegocioException {
        controlador.editarNomeCategoria(id, novoNome);
    }
    
    public void removerCategoria(int id) throws NegocioException {
        controlador.removerCategoria(id);
    }

    public Categoria buscarCategoriaPorId(int id) throws NegocioException {
        return controlador.buscarCategoriaPorId(id);
    }

    public List<Categoria> listarCategorias() {
        return controlador.listarCategorias();
    }

    // Gerenciamento de Transações
    public void adicionarReceita(int contaId, double valor, String descricao, Categoria categoria) throws NegocioException {
        controlador.adicionarReceita(contaId, valor, descricao,categoria);
    }

    public void adicionarDespesa(int contaId, double valor, String descricao, int categoriaId) throws NegocioException, SaldoInsuficienteException {
        controlador.adicionarDespesa(contaId, valor, descricao, categoriaId);
    }
    
    public void removerTransacao(int id) throws NegocioException, SaldoInsuficienteException {
        controlador.removerTransacao(id);
    }
    
    public Transacao buscarTransacaoPorId(int id) throws NegocioException {
        return controlador.buscarTransacaoPorId(id);
    }
    
    public List<Transacao> listarTransacoes() {
        return controlador.listarTransacoes();
    }
    
    // Gerenciamento de Despesas Recorrentes
    public void adicionarDespesaRecorrente(double valor, String descricao, int contaId, int categoriaId, Periodicidade periodicidade, int numeroDeParcelas) throws NegocioException {
        controlador.adicionarDespesaRecorrente(valor, descricao, contaId, categoriaId, periodicidade, numeroDeParcelas);
    }
    
    public void processarDespesasRecorrentes() throws NegocioException, SaldoInsuficienteException {
        controlador.processarDespesasRecorrentes();
    }
    
    public List<DespesaRecorrente> listarDespesasRecorrentes() {
        return controlador.listarDespesasRecorrentes();
    }

    public void removerDespesaRecorrente(int id) throws NegocioException {
        controlador.removerDespesaRecorrente(id);
    }

    public DespesaRecorrente buscarDespesaRecorrentePorId(int id) throws NegocioException {
        return controlador.buscarDespesaRecorrentePorId(id);
    }

    // Gerenciamento de Persistência
     
    // salva com um nome de arquivo padrão
    public void salvarDados(String tipo) throws IOException {
        salvarDados(tipo, "dados.dat");
    }

    // salva com um nome de arquivo especificado
    public void salvarDados(String tipo, String nomeArquivo) throws IOException {
        if ("serializacao".equalsIgnoreCase(tipo)) {
            PersistenciaDados.salvarDadosSerializacao(controlador, nomeArquivo);
        } else if ("csv".equalsIgnoreCase(tipo)) {
            PersistenciaDados.salvarDadosCSV(controlador, nomeArquivo);
        }
    }
    
    // carrega com um nome de arquivo padrão
    public void carregarDados(String tipo) throws IOException {
        carregarDados(tipo, "dados.dat");
    }
    
    //carrega com um nome de arquivo especificado
    public void carregarDados(String tipo, String nomeArquivo) throws IOException {
        if ("serializacao".equalsIgnoreCase(tipo)) {
            ControleFinanceiro carregado = PersistenciaDados.carregarDadosSerializacao(nomeArquivo);
            if (carregado != null) {
                this.controlador = carregado;
            }
        } else if ("csv".equalsIgnoreCase(tipo)) {
            ControleFinanceiro carregado = PersistenciaDados.carregarDadosCSV(nomeArquivo);
            if (carregado != null) {
                this.controlador = carregado;
            }
        }
    }

    // Gerenciamento de Relatórios
    public String gerarBalanco() {
        return controlador.gerarBalanco();
    }

    public String gerarRelatorioGastoPorCategoria() {
        return controlador.gerarRelatorioGastoPorCategoria();
    }
    
    public String gerarRelatorioMensal(int ano, int mes) {
        return controlador.gerarRelatorioMensal(ano, mes);
    }

    public String gerarRelatorioTrimestral(int ano, int trimestre) {
        return controlador.gerarRelatorioTrimestral(ano, trimestre);
    }

    public String gerarRelatorioSemestral(int ano, int semestre) {
        return controlador.gerarRelatorioSemestral(ano, semestre);
    }
    
    public String exibirRelatorioAnual(int ano) {
        return controlador.exibirRelatorioAnual(ano);
    }
}
