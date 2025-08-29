package Dados;

import Negocios.DespesaRecorrente;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RepositorioDespesaRecorrente implements Serializable {

    private List<DespesaRecorrente> despesasRecorrentes = new ArrayList<>();
    private int proximoId = 1;

    public void adicionar(DespesaRecorrente despesa) {
        despesa.setId(this.proximoId++);
        this.despesasRecorrentes.add(despesa);
    }

    public boolean remover(int id) {
        return this.despesasRecorrentes.removeIf(dr -> dr.getId() == id);
    }

    public DespesaRecorrente buscarPorId(int id) {
        for (DespesaRecorrente dr : this.despesasRecorrentes) {
            if (dr.getId() == id) {
                return dr;
            }
        }
        return null;
    }

    public List<DespesaRecorrente> listarTodas() {
        return new ArrayList<>(this.despesasRecorrentes);
    }

    public void setDespesasRecorrentes(List<DespesaRecorrente> despesas) {
        this.despesasRecorrentes = despesas;
        if (!despesas.isEmpty()) {
            this.proximoId = despesas.stream().mapToInt(DespesaRecorrente::getId).max().orElse(0) + 1;
        }
    }
}
