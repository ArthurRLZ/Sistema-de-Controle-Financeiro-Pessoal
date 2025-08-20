package Dados;

import Negocios.Transacao;

import java.util.ArrayList;

public class RepositorioTransacao {
    private ArrayList<Transacao> transacoes = new ArrayList<>();

    public void adicionar(Transacao t) { transacoes.add(t); }
    public boolean remover(String id) {
        return transacoes.removeIf(t -> t.getId().equals(id));
    }
    public ArrayList<Transacao> getTodos() { return transacoes; }
}
