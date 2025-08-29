package Dados;

import Negocios.Categoria;
import Negocios.Conta;

import java.util.ArrayList;

public class RepositorioCategoria {
    private ArrayList<Categoria> categorias = new ArrayList<>();

    public void adicionar(Categoria c) { 
    	categorias.add(c); 
    }
    public boolean remover(String id) {
        return categorias.removeIf(c -> c.getId().equals(id));
    }
    public ArrayList<Categoria> getTodos() { 
    	return categorias; 
    	}
    public Categoria buscarPorId(String id) {
        for (Categoria c : categorias) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null; 
    }
}
