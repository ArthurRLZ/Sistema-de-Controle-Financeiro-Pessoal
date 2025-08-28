package Negocios;

import java.io.Serializable;
import java.time.LocalDate;

public class DespesaRecorrente extends Despesa implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String frequencia;
    private int numeroDeParcelas;
    private LocalDate dataUltimaCobranca;

    public DespesaRecorrente(String id, String descricao, double valor, LocalDate data, Categoria categoria, Conta conta, String frequencia, int numeroDeParcelas) {
        super(id, descricao, valor, data, categoria, conta);
        this.frequencia = frequencia;
        this.numeroDeParcelas = numeroDeParcelas;
        this.dataUltimaCobranca = data;
    }

    public String getFrequencia() {
        return frequencia;
    }

    public int getNumeroDeParcelas() {
        return numeroDeParcelas;
    }

    public LocalDate getDataUltimaCobranca() {
        return dataUltimaCobranca;
    }

    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }

    public void setNumeroDeParcelas(int numeroDeParcelas) {
        this.numeroDeParcelas = numeroDeParcelas;
    }

    public void setDataUltimaCobranca(LocalDate dataUltimaCobranca) {
        this.dataUltimaCobranca = dataUltimaCobranca;
    }

    // calcula a data da proxima cobranca com base na frequencia
    public LocalDate calcularProximaData() {
        LocalDate proximaData = this.dataUltimaCobranca;
        switch (frequencia.toLowerCase()) {
            case "mensal":
                proximaData = proximaData.plusMonths(1);
                break;
            case "trimestral":
                proximaData = proximaData.plusMonths(3);
                break;
            case "semestral":
                proximaData = proximaData.plusMonths(6);
                break;
            case "anual":
                proximaData = proximaData.plusYears(1);
                break;
        }
        return proximaData;
    }

    @Override
    public String toString() {
        return "Despesa Recorrente{" +
               "id='" + this.getId() + '\'' +
               ", descricao='" + this.getDescricao() + '\'' +
               ", valor=" + this.getValor() +
               ", data=" + this.getData() +
               ", categoria=" + this.getCategoria().getNome() +
               ", conta=" + this.getConta().getNome() +
               ", frequencia='" + frequencia + '\'' +
               ", numeroDeParcelas=" + numeroDeParcelas +
               ", proximaGeracao=" + this.calcularProximaData() +
               '}';
    }
}
