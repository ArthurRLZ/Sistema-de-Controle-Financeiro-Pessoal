package Negocios;

import java.io.Serializable;
import java.util.Date;

public class Despesa extends Transacao implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Categoria categoria;

    // Construtor
    public Despesa(double valor, Date data, String descricao, Conta conta, Categoria categoria) {
        super(valor, data, descricao, conta); // Passa as informações comuns para a classe-mãe
        this.categoria = categoria;
    }

    // Getter
    public Categoria getCategoria() {
        return categoria;
    }

    // Método Padrão
    @Override
    public String toString() {
        return "Despesa{" +
                super.toString() + // Chama o toString() da classe Transacao
                ", categoria=" + categoria.getNome() +
                '}';
    }
}
