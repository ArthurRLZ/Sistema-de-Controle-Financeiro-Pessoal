package Negocios;

import java.time.LocalDate;

public class Receita extends Transacao{
    public Receita(String id, String descricao, double valor, LocalDate data) {
        super(id, descricao, valor, data);
    }
}
