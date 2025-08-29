package Negocios;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Date; // Usaremos o java.util.Date por compatibilidade

public abstract class Transacao implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private double valor;
    private Date data;
    private String descricao;
    private Conta conta;

    // Construtor
    public Transacao(double valor, Date data, String descricao, Conta conta) {
        this.valor = valor;
        this.data = data;
        this.descricao = descricao;
        this.conta = conta;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) { // Usado pelo repositório
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public Date getData() {
        return data;
    }

    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) { // Permitimos editar a descrição
        this.descricao = descricao;
    }

    public Conta getConta() {
        return conta;
    }

    // Métodos Padrão
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transacao transacao = (Transacao) o;
        return id == transacao.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return  "id=" + id +
                ", valor=" + valor +
                ", data=" + data +
                ", descricao='" + descricao + '\'' +
                ", conta=" + conta.getNome();
    }
}
