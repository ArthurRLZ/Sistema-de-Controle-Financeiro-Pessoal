package Negocios;

import java.time.LocalDate;	
import java.util.Objects;
import Negocios.Conta;

public abstract class Transacao {
    private String id;
    private String descricao;
    private Double valor;
    private LocalDate data;
    private Categoria categoria;
    private Conta conta;

    public Transacao(String id, String descricao, Double valor, LocalDate data, Conta conta) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.conta = conta;
    }
    public void setDescricao(String descricao) {
    	this.descricao = descricao;
    }
    
    public void setValor(double valor) {
    	this.valor = valor;
    }
    
    public void setData(LocalDate data) {
    	this.data = data;
    }
    
    public void setValor(Double valor) {
		this.valor = valor;
	}
	public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getValor() {
        return valor;
    }

    public LocalDate getData() {
        return data;
    }

    public Categoria getCategoria() { return categoria; }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Transacao outra)) return false;
        return Objects.equals(id, outra.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
