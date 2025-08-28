package Negocios;

import java.util.Objects;
import java.io.Serializable;

public class Categoria implements Serializable {
    private static final long serialVersionUID = 1L;
    
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


    // adicionei esse método pra gente poder mudar o nome da categoria dps
    // tp se o cara errar ou quiser trocar o nome tlgd
    public void setNome(String nome) {
        this.nome = nome;
    }

    // esse  equals é pra garantir qua gnt não va ter 2 categorias com o mesmo ID
    // ele so compara o ID então se o ID for igual  ele considera que são a mesma categoria


	// equals baseado no ID

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Categoria outra)) return false;
        return Objects.equals(id, outra.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // mudei o 'toString' pra ficar mais izi de ver as info de uma categoria
    // mas ve se ta certo essa bomba ai e qlqr coisa mexe ai nessa bosta

    @Override
    public String toString() {
        return "Categoria{" +
               "id='" + id + '\'' +
               ", nome='" + nome + '\'' +
               '}';
    }
}
