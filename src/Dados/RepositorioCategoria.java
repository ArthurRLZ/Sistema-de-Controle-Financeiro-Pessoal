package Dados;

import Negocios.Categoria;
import java.io.Serializable;
import java.util.ArrayList;

public class RepositorioCategoria implements Serializable {
    private ArrayList<Categoria> categorias;

    public RepositorioCategoria(){
        this.categorias = new ArrayList<>();
    }
    public void adicionar ( Categoria c) {
        if ( c != null && !categorias.contains(c)) {
            categorias.add(c);
        }
    }

    public boolean remover ( String id) {
        return categorias.removeIf(c-> c.getId(). equals(id));
    }

    public Categoria buscarPorId(String id){
        for ( Categoria c : categorias ) {
            if (c.getId().equals(id)){
                return c;
            }
        }
        return null;
    }

    public ArrayList<Categoria> getTodos(){
        return categorias;
    }
}
