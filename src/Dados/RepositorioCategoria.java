package Dados;

import Negocios.Categoria;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RepositorioCategoria implements Serializable {
    private List<Categoria> categorias = new ArrayList<>();
    private int proximoId = 1;

    public void adicionar(Categoria categoria) {
        categoria.setId(this.proximoId++);
        this.categorias.add(categoria);
    }

    public boolean remover(int id) {
        return this.categorias.removeIf(c -> c.getId() == id);
    }

    public Categoria buscarPorId(int id) {
        for (Categoria c : this.categorias) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public List<Categoria> listarTodas() {
        return new ArrayList<>(this.categorias);
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
        if (!categorias.isEmpty()) {
            this.proximoId = categorias.stream().mapToInt(Categoria::getId).max().orElse(0) + 1;
        }
    }
}
