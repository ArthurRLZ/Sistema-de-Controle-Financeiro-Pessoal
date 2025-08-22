package Negocios;

import Dados.RepositorioCategoria;
import Dados.RepositorioTransacao;

import java.util.ArrayList;
import java.time.LocalDate;

public class ControleFinanceiro {
    private RepositorioTransacao repoTransacao;
    private RepositorioCategoria repoCategoria;

    public ControleFinanceiro() {
        this.repoTransacao = new RepositorioTransacao();
        this.repoCategoria = new RepositorioCategoria();
    }
    // ===== Transações =====
    public void adicionarTransacao(Transacao t, Categoria c) {
    	t.setCategoria(c);    //associa a categoria a transação
    	repoTransacao.adicionar(t);          
    }
    
    public boolean removerTransacao(String id) {
        return repoTransacao.remover(id);
    }


    public ArrayList<Transacao> getTransacoes() {
        return repoTransacao.getTodos();
    }
    
    public boolean editarDescricaoTransacao(String id, String novaDescricao) {
        for (Transacao t : repoTransacao.getTodos()) {
            if (t.getId().equals(id)) {
                t.setDescricao(novaDescricao);
                return true;
            }
        }
        return false;
    }

    public boolean editarValorTransacao(String id, double novoValor) {
        for (Transacao t : repoTransacao.getTodos()) {
            if (t.getId().equals(id)) {
                t.setValor(novoValor);
                return true;
            }
        }
        return false;
    }
 
    public boolean editarDataTransacao(String id, LocalDate novaData) {
        for (Transacao t : repoTransacao.getTodos()) {
            if (t.getId().equals(id)) {
                t.setData(novaData);
                return true;
            }
        }
        return false;
    }
    
    public boolean editarCategoriaTransacao(String id, Categoria novaCat) {
        for (Transacao t : repoTransacao.getTodos()) {
            if (t.getId().equals(id)) {
                t.setCategoria(novaCat);
                return true;
            }
        }
        return false;
    }
    
    
    

    // ===== Categorias =====
    
    public boolean editarCategoria(String antigoNome, String novoNome) {
        for (Categoria c : repoCategoria.getTodos()) {
            if (c.getNome().equals(antigoNome)) {
                c.setNome(novoNome);
                return true;
            }
        }
        return false;
    }
   
    public void adicionarCategoria(Categoria c) {
        repoCategoria.adicionar(c);
    }

    public boolean removerCategoria(String id) {
        return repoCategoria.remover(id);
    }

    public ArrayList<Categoria> getCategorias() {
        return repoCategoria.getTodos();
    }
}
