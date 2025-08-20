package Negocios;

import Dados.RepositorioCategoria;
import Dados.RepositorioTransacao;

import java.util.ArrayList;

public class ControleFinanceiro {
    private RepositorioTransacao repoTransacao;
    private RepositorioCategoria repoCategoria;

    public ControleFinanceiro() {
        this.repoTransacao = new RepositorioTransacao();
        this.repoCategoria = new RepositorioCategoria();
    }

    public void adicionarTransacao(Transacao t, Categoria c) {
        repoTransacao.adicionar(t);
        // associar categoria à transação
    }

    public boolean removerTransacao(String id) {
        return repoTransacao.remover(id);
    }

    public void adicionarCategoria(Categoria c) {
        repoCategoria.adicionar(c);
    }

    public boolean removerCategoria(String id) {
        return repoCategoria.remover(id);
    }

    public ArrayList<Transacao> getTransacoes() {
        return repoTransacao.getTodos();
    }

    public ArrayList<Categoria> getCategorias() {
        return repoCategoria.getTodos();
    }
}
