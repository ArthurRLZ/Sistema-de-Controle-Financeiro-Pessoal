package Dados;

import Negocios.Transacao;
import java.io.Serializable;
import java.util.ArrayList;

public class RepositoriTransacao implements Serializable {
    private ArrayList<Transacao> transacoes;

    public RepositorioTransacao() {
        this.transacoes = new ArrayList <>();
    }

    public void adicionar (Transacao t) {
        if (t != null && !transacoes.contains(t)) {
            transacoes.add(t);
        }
    }

    public boolean remover (String id) {
        return transacoes.removeIf ( t-> t.getId().equals(id));
    }

    public Transacao buscarPorId(String id) {
        for ( Transacao t : transacoes ) {
            if (t.getId().equals(id)) {
                return t;
            }
        }
        return null;
    }

    public ArratList<Transacao> getTodos () {
        return transacoes;
    }
}
