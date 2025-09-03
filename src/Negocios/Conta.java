package Negocios;

import Negocios.exceptions.NegocioException;
import Negocios.exceptions.SaldoInsuficienteException;
import java.io.Serializable;
import java.util.Objects;

public class Conta implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String nome;
    private double saldo;

    // Construtor
    public Conta(String nome) {
        this.nome = nome;
        this.saldo = 0.0;
    }
    
    public Conta(int id, String nome, double saldo) {
        this.id = id;
        this.nome = nome;
        this.saldo = saldo;
    }

    // Métodos de Operação da Conta
    public void creditar(double valor) throws NegocioException {
        if (valor <= 0) {
            throw new NegocioException("O valor a ser creditado deve ser maior que zero.");
        }
        this.saldo += valor;
    }

    public void debitar(double valor) throws SaldoInsuficienteException, NegocioException {
        if (valor <= 0) {
            throw new NegocioException("O valor a ser debitado deve ser maior que zero.");
        }
        if (this.saldo < valor) {
            throw new SaldoInsuficienteException("Saldo insuficiente. Saldo atual: " + this.saldo);
        }
        this.saldo -= valor;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public double getSaldo() {
        return saldo;
    }

    // Métodos Padrão
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conta conta = (Conta) o;
        return id == conta.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Conta{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", saldo=" + saldo +
                '}';
    }
}
