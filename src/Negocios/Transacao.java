package Negocios;

import java.time.LocalDate;
import java.util.Objects;
import java.io.Serializable;

public abstract class Transacao implements Serializable {
	private String id;
	private String descricao;
	private Double valor;
	private LocalDate data;
	private Categoria categoria;
	private Conta conta;


	public Transacao(String id, String descricao, Double valor, LocalDate data, Categoria categoria, Conta conta) {
		this.id=id;
		this.descricao=descricao;
		this.valor=valor;
		this.data=data;
		this.categoria=categoria;
		this.conta=conta;
	}

	public String getId(){
		return id;
	}
	public String getDescricao(){
		return descricao;
	}
	public Double getValor (){
		return valor;
	}
	public LocalDate getData(){
		return data;
	}
	public Categoria getCategoria(){
		return categoria;
	}
	public Conta getConta(){
		return conta;
	}

	public void setDescricao (String descricao){
		this.descricao=descricao;
	}
	public void setValor ( Double valor ){
		this.valor=valor;
	}
	public void setData ( LocalDate data){
		this.data=data;
	}
	public void setCategoria ( Categoria categoria){
		this.categoria=categoria;
	}
	public void setConta (Conta conta ){
		this.conta=conta;
	}
//comparar pra ver se o id é unico

	@Override
	public boolean equals (Object o ){
		if (this == o) return true;
		if (!(o instanceof Transacao outra)) return false;
		return Objects.equals(id, outra.id);
	}

	@Override
	public int hashCode (){
		return Objects.hash (id);
	}

	@Override
	public String toString(){
		return "Transacao{" +
               "id='" + id + '\'' +
               ", descricao='" + descricao + '\'' +
               ", valor=" + valor +
               ", data=" + data +
               ", categoria=" + (categoria != null ? categoria.getNome() : "N/A") +
               ", conta=" + (conta != null ? conta.getNome() : "N/A") +
               '}';
    }
} 
