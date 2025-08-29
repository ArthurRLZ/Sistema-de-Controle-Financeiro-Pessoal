package Dados;

import Negocios.Conta;
import java.io.Serializable;
import java.util.ArrayList;

public class RepositorioConta implements Serializable {

    private ArrayList<Conta> contas;

    public RepositorioConta() {
        this.contas = new ArrayList<>();
    }

    public void adicionar (Conta c){
        if (c != null && !contas.contains(c)) {
            contas.add(c);
        }
    }

    public boolean remover(String id) {
        return contas.removeIf( c -> c.getId().equals(id));
    }

    public Conta buscarPorId(String id){
        for( Conta c : contas) {
            if (c.getId().equals(id)){
                return c;
            }
        }
        return null;
    }

    public ArrayList<Conta> getTodos() {
        return contas;
    }
}
