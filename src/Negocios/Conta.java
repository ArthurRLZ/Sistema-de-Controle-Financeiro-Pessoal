package Negocios;

import java.util.Objects;
import java.io.Serializable;

public class Conta implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String nome;
	private double saldo;

	public Conta ( String id, String nome, double saldo){
		this.id=id;
		this.nome=nome;
		this.saldo=saldo;

	}
	
	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public void creditar(double valor) {
	    this.saldo += valor;
	}
	public void debitar(double valor) {
	    this.saldo -= valor;
	}
	

	public String getId(){
		return id;
	}

	public String getNome(){
		return nome;
	}

	public void setNome (String nome) {
		this.nome=nome;
	}

	  @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (!(o instanceof Conta conta)) return false;
	        return Objects.equals(id, conta.id);
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(id);
	    }

	    @Override
	    public String toString() {
	        return "Conta{" +
	                "id='" + id + '\'' +
	                ", nome='" + nome + '\'' +
	                ", saldo=" + saldo +
	                '}';
	    }
	}
