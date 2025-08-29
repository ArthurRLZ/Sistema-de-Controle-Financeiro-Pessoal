package Negocios;

import java.io.Serializable;	
import java.time.LocalDate;

public class Receita extends Transacao implements Serializable {
    private static final long serialVersionUID = 1L;

    // Construtor
    public Receita(double valor, LocalDate data, String descricao, Conta conta,Categoria categoria) {
        super(valor, data, descricao, conta, categoria); // Apenas chama o construtor da classe-mãe
        
    }	

    // Método Padrão
    @Override
    public String toString() {
        return "Receita{" +
                super.toString() +
                '}';
    }
}
