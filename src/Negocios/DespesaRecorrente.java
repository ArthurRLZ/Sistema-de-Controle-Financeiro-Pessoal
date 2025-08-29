package Negocios;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class DespesaRecorrente extends Despesa implements Serializable {
    private static final long serialVersionUID = 1L;

    private Periodicidade periodicidade;
    private int numeroDeParcelas;
    private int parcelaAtual;
    private LocalDate proximaOcorrencia;

    // Construtor
    public DespesaRecorrente(double valor, Date data, String descricao, Conta conta, Categoria categoria, Periodicidade periodicidade, int numeroDeParcelas) {
        super(valor, data, descricao, conta, categoria);
        this.periodicidade = periodicidade;
        this.numeroDeParcelas = numeroDeParcelas;
        this.parcelaAtual = 0;
        this.proximaOcorrencia = LocalDate.now();
        atualizarParaProximaOcorrencia(); // Calcula a primeira ocorrência
    }

    // Métodos de Negócio
    public boolean isAtiva() {
        return this.parcelaAtual < this.numeroDeParcelas;
    }

    public boolean isVencida() {
        return isAtiva() && !proximaOcorrencia.isAfter(LocalDate.now());
    }

    public final void atualizarParaProximaOcorrencia() {
        if (isAtiva()) {
            this.parcelaAtual++;

            LocalDate proxima = this.proximaOcorrencia;
            switch (this.periodicidade) {
                case DIARIA:
                    this.proximaOcorrencia = proxima.plusDays(1);
                    break;
                case SEMANAL:
                    this.proximaOcorrencia = proxima.plusWeeks(1);
                    break;
                case MENSAL:
                    this.proximaOcorrencia = proxima.plusMonths(1);
                    break;
                case ANUAL:
                    this.proximaOcorrencia = proxima.plusYears(1);
                    break;
            }
        }
    }

    // Getters
    public Periodicidade getPeriodicidade() {
        return periodicidade;
    }

    public int getNumeroDeParcelas() {
        return numeroDeParcelas;
    }

    public int getParcelaAtual() {
        return parcelaAtual;
    }

    public LocalDate getProximaOcorrencia() {
        return proximaOcorrencia;
    }
    
   
    @Override
    public String toString() {
        return "DespesaRecorrente{" +
                "id=" + getId() +
                ", descricao='" + getDescricao() + '\'' +
                ", valor=" + getValor() +
                ", periodicidade=" + periodicidade +
                ", parcela=" + parcelaAtual + "/" + numeroDeParcelas +
                ", proximaOcorrencia=" + proximaOcorrencia +
                ", conta=" + getConta().getNome() +
                '}';
    }
}
