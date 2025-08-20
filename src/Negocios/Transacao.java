package Negocios;

import java.time.LocalDate;

public abstract class Transacao {
    private String id;
    private String descricao;
    private Double valor;
    private LocalDate data;
    private Categoria categoria;

    public Transacao(String id, String descricao, Double valor, LocalDate data) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
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


}
