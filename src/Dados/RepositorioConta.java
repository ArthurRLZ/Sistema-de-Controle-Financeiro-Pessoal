package Dados;

import Negocios.Conta;
import java.util.ArrayList;

public class RepositorioConta {
    private ArrayList<Conta> contas = new ArrayList<>();

    public void adicionar(Conta c) { 
        contas.add(c); 
    }
    
    public boolean remover(String id) {
        return contas.removeIf(c -> c.getId().equals(id));
    }
    
    public Conta buscarPorId(String id) {
        for (Conta c : contas) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null; 
    }

    public ArrayList<Conta> getTodos() { 
        return contas; 
    }
}