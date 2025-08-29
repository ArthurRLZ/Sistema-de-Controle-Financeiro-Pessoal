package Negocios;

import java.io.Serializable;
import java.util.Date;

public class Receita extends Transacao implements Serializable {
    private static final long serialVersionUID = 1L;

    // Construtor
    public Receita(double valor, Date data, String descricao, Conta conta) {
        super(valor, data, descricao, conta); // Apenas chama o construtor da classe-mãe
    }

    // Método Padrão
    @Override
    public String toString() {
        return "Receita{" +
                super.toString() + // Chama o toString() da classe Transacao
                '}';
    }
}
