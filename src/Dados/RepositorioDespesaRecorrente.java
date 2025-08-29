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

    public List<DespesaRecorrente> listarTodas() {
        return new ArrayList<>(this.despesasRecorrentes);
    }
}
