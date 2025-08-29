package Fachada;

import Dados.PersistenciaDados;
import Dados.RepositorioCategoria;
import Dados.RepositorioConta;
import Dados.RepositorioTransacao;
import Negocios.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class FinanceiroFachada {

    private ControleFinanceiro controle;
    private PersistenciaDados persistencia;

    // O construtor recebe os repositorios e cria o controle e a persistencia
    public FinanceiroFachada(RepositorioTransacao repoTransacao, RepositorioCategoria repoCategoria, RepositorioConta repoConta) {
        this.controle = new ControleFinanceiro(repoTransacao, repoCategoria, repoConta);
        this.persistencia = new PersistenciaDados();
    }
    
    // ===== Persistencia =====
    // Chamadas para salvar e carregar todas as listas
    public void salvar() {
        persistencia.salvarCategoriasCSV(controle.getCategorias());
        persistencia.salvarContasCSV(controle.getContas());
        persistencia.salvarTransacoesCSV(controle.getTransacoes());
        // ... (e as despesas recorrentes, se implementado)
    }

    public void carregar() {
        RepositorioCategoria repoCategoria = new RepositorioCategoria();
        repoCategoria.setTodos(persistencia.carregarCategoriasCSV());
        // ... (e as outras listas)
    }
    
    // ===== Gerenciamento de Categorias =====
    public void adicionarCategoria(String nome) {
        controle.adicionarCategoria(nome);
    }

    public boolean removerCategoria(String id) {
        return controle.removerCategoria(id);
    }

    public boolean editarCategoria(String id, String novoNome) {
        return controle.editarCategoria(id, novoNome);
    }
    
    public ArrayList<Categoria> listarCategorias() {
        return controle.getCategorias();
    }

    // ===== Gerenciamento de Contas =====
    public void adicionarConta(String nome, double saldoInicial) {
        controle.adicionarConta(nome, saldoInicial);
    }
    
    public boolean removerConta(String id) {
        return controle.removerConta(id);
    }

    public boolean editarConta(String id, String novoNome) {
        return controle.editarConta(id, novoNome);
    }
    
    public ArrayList<Conta> listarContas() {
        return controle.getContas();
    }

    // ===== Gerenciamento de Transações (Receita e Despesa) =====
    public void adicionarReceita(String descricao, double valor, String categoriaId, String contaId) {
        controle.adicionarReceita(descricao, valor, categoriaId, contaId);
    }

    public void adicionarDespesa(String descricao, double valor, String categoriaId, String contaId) {
        controle.adicionarDespesa(descricao, valor, categoriaId, contaId);
    }

    public boolean removerTransacao(String id) {
        return controle.removerTransacao(id);
    }
    
    public ArrayList<Transacao> listarTransacoes() {
        return controle.getTransacoes();
    }

    // Métodos para editar Transações
    public boolean editarDescricaoTransacao(String id, String novaDescricao) {
        return controle.editarDescricaoTransacao(id, novaDescricao);
    }
    
    public boolean editarValorTransacao(String id, double novoValor) {
        return controle.editarValorTransacao(id, novoValor);
    }
    
    public boolean editarDataTransacao(String id, LocalDate novaData) {
        return controle.editarDataTransacao(id, novaData);
    }
    
    public boolean editarCategoriaTransacao(String id, String novaCategoriaId) {
        return controle.editarCategoriaTransacao(id, novaCategoriaId);
    }
    
    public boolean editarContaTransacao(String id, String novaContaId) {
        return controle.editarContaTransacao(id, novaContaId);
    }

    // ===== Gerenciamento de Despesas Recorrentes =====
    public void adicionarDespesaRecorrente(String descricao, double valor, String categoriaId, String contaId, String frequencia, int numeroDeParcelas) {
        controle.adicionarDespesaRecorrente(descricao, valor, categoriaId, contaId, frequencia, numeroDeParcelas);
    }
    
    public void processarDespesasRecorrentes() {
        controle.processarDespesasRecorrentes();
    }

    // ===== Gerenciamento de Relatórios =====
    public String exibirBalanco() {
        Relatorio relatorio = new Relatorio();
        return relatorio.gerarBalanco(controle.getTransacoes());
    }

    public String exibirRelatorioGastos() {
        Relatorio relatorio = new Relatorio();
        return relatorio.gerarRelatorioGastoPorCategoria(controle.getTransacoes());
    }

    public String exibirRelatorioMensal(int ano, int mes) {
        Relatorio relatorio = new Relatorio();
        return relatorio.gerarRelatorioMensal(controle.getTransacoes(), ano, mes);
    }
    
    public String exibirRelatorioTrimestral(int ano, int trimestre) {
        Relatorio relatorio = new Relatorio();
        return relatorio.gerarRelatorioTrimestral(controle.getTransacoes(), ano, trimestre);
    }

    public String exibirRelatorioSemestral(int ano, int semestre) {
        Relatorio relatorio = new Relatorio();
        return relatorio.gerarRelatorioSemestral(controle.getTransacoes(), ano, semestre);
    }
    
    public String exibirRelatorioAnual(int ano) {
        Relatorio relatorio = new Relatorio();
        return relatorio.gerarRelatorioAnual(controle.getTransacoes(), ano);
    }
}
