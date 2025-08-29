package Negocios;

import java.io.Serializable;	
import java.time.LocalDate;

public class Despesa extends Transacao implements Serializable {
    private static final long serialVersionUID = 1L;
    

    // Construtor
    public Despesa(double valor, LocalDate data, String descricao, Conta conta, Categoria categoria) {
        super(valor, data, descricao, conta, categoria); // Passa as informações comuns para a classe-mãe
    }

    // Método Padrão
    @Override
    public String toString() {
        return "Despesa{" +
                super.toString() +
                '}';
    }
}