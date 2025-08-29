package Dados;

import Negocios.Transacao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RepositorioTransacao implements Serializable {
    private List<Transacao> transacoes = new ArrayList<>();
    private int proximoId = 1;

    public void adicionar(Transacao transacao) {
        transacao.setId(this.proximoId++);
        this.transacoes.add(transacao);
    }

    public boolean remover(int id) {
        return this.transacoes.removeIf(t -> t.getId() == id);
    }

    public Transacao buscarPorId(int id) {
        for (Transacao t : this.transacoes) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }

    public List<Transacao> listarTodas() {
        return new ArrayList<>(this.transacoes);
    }

    public void setTransacoes(List<Transacao> transacoes) {
        this.transacoes = transacoes;
        if (!transacoes.isEmpty()) {
            this.proximoId = transacoes.stream().mapToInt(Transacao::getId).max().orElse(0) + 1;
        }
    }
}
