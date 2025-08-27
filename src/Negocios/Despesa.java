package Negocios;

import java.time.LocalDate;

public class Despesa extends Transacao {
    
    public Despesa(String id, String descricao, double valor, LocalDate data, Categoria categoria, Conta conta) {
        super(id, descricao, -Math.abs(valor), data, categoria, conta);
    }
}
