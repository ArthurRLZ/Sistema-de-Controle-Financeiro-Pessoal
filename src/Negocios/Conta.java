package Negocios;

import java.util.Objects;
import java.io.Serializable;

public class Conta implements Serializable {
	private String id;
	private String nome;

	public Conta ( String id, String nome){
		this.id=id;
		this.nome=nome;

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
	public boolean equals(Object o ) {
		if (this==0) return true;
		if ( !(o instanceof Conta conta)) return false;
		return Objects.equals(id, conta.id);
	}

	@Override
	public int hashCode(){
		return Objects.hash(id);
	}

	@Override
	public String toString (){
		return "Conta{" +
			"id=" + id + '\'' +
               ", nome='" + nome + '\'' +
               '}';
    }
}
