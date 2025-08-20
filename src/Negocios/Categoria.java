package Negocios;

import java.util.Objects;

public class Categoria {
    private String id;
    private String nome;

    public Categoria(String id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    // equals baseado no ID
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Categoria outra)) return false;
        return Objects.equals(id, outra.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
