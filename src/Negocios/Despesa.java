package Negocios;

import java.time.LocalDate;

public class Despesa extends Transacao{
    public Despesa(String id, String descricao, double valor, LocalDate data) {
        super(id, descricao, valor, data);
    }
}
