package Fachada;

import Dados.PersistenciaDados;
import Negocios.Categoria;
import Negocios.ControleFinanceiro;
import Negocios.Relatorio;
import Negocios.Transacao;

import java.time.LocalDate;
import java.util.ArrayList;

public class FinanceiroFachada {
    private ControleFinanceiro controle;
    private PersistenciaDados persistencia;
    private Relatorio relatorio;

    public FinanceiroFachada() {
        this.controle = new ControleFinanceiro();
        this.persistencia = new PersistenciaDados();
        this.relatorio = new Relatorio();
    }

    // ===== Persistência =====
    public void carregarDados(String tipo) {
        persistencia.carregarDados(tipo, controle);
    }

    public void salvarDados(String tipo) {
        persistencia.salvarDados(tipo, controle);
    }

    // ===== Transações =====
    public void adicionarReceita(String descricao, double valor, LocalDate data, Categoria categoria) {
        controle.adicionarReceita(descricao, valor, data, categoria);
    }

    public void adicionarDespesa(String descricao, double valor, LocalDate data, Categoria categoria) {
        controle.adicionarDespesa(descricao, valor, data, categoria);
    }

    public void editarTransacao(String id, String novaDesc, double novoValor, LocalDate novaData, Categoria novaCat) {
        controle.editarTransacao(id, novaDesc, novoValor, novaData, novaCat);
    }

    public void removerTransacao(String id) {
        controle.removerTransacao(id);
    }

    public ArrayList<Transacao> listarTransacoes() {
        return controle.listarTransacoes();
    }

    // ===== Categorias =====
    public void adicionarCategoria(String nome) {
        controle.adicionarCategoria(nome);
    }

    public void editarCategoria(String antigoNome, String novoNome) {
        controle.editarCategoria(antigoNome, novoNome);
    }

    public void removerCategoria(String nome) {
        controle.removerCategoria(nome);
    }

    public ArrayList<Categoria> listarCategorias() {
        return controle.listarCategorias();
    }

    // ===== Relatórios =====
    public String exibirBalanco() {
        return relatorio.exibirBalanco(controle);
    }

    public String exibirRelatorioGastos() {
        return relatorio.exibirRelatorioGastos(controle);
    }
}

