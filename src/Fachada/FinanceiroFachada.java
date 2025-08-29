package Fachada;

import Negocios.*;
import Negocios.exceptions.NegocioException;
import Negocios.exceptions.SaldoInsuficienteException;
import java.util.List;

public class FinanceiroFachada {

    private ControleFinanceiro controlador;

    public FinanceiroFachada() {
        this.controlador = new ControleFinanceiro();
    }

    // Gerenciamento de Contas
    public void criarConta(String nome) throws NegocioException {
        controlador.criarConta(nome);
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
    public void adicionarReceita(int contaId, double valor, String descricao) throws NegocioException {
        controlador.adicionarReceita(contaId, valor, descricao);
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
    public void salvarDados() throws Exception {
        controlador.salvarDados();
    }

    public void carregarDados() throws Exception {
        controlador.carregarDados();
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
