package Dados;

import Negocios.Conta;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RepositorioConta implements Serializable {
    private List<Conta> contas = new ArrayList<>();
    private int proximoId = 1;

    public void adicionar(Conta conta) {
        conta.setId(this.proximoId++);
        this.contas.add(conta);
    }

    public boolean remover(int id) {
        return this.contas.removeIf(c -> c.getId() == id);
    }

    public Conta buscarPorId(int id) {
        for (Conta c : this.contas) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public List<Conta> listarTodas() {
        return new ArrayList<>(this.contas);
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
        if (!contas.isEmpty()) {
            this.proximoId = contas.stream().mapToInt(Conta::getId).max().orElse(0) + 1;
        }
    }
}
