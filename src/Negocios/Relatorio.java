package Negocios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Relatorio {

    // gera um resumo de receitas despesas e saldo final
    public String gerarBalanco(ArrayList<Transacao> transacoes) {
        if (transacoes == null || transacoes.isEmpty()) {
            return "Nenhuma transação registrada.";
        }

        double receitas = 0;
        double despesas = 0;

        for (Transacao t : transacoes) {
            if (t instanceof Receita) {
                receitas += t.getValor();
            } else if (t instanceof Despesa) {
                despesas += Math.abs(t.getValor());
            }
        }

        double saldo = receitas - despesas;

        return String.format("Receitas: R$ %.2f\nDespesas: R$ %.2f\nSaldo Final: R$ %.2f",
                receitas, despesas, saldo);
    }

    // gera um relatório que mostra o total gasto em cada categoria
    public String gerarRelatorioGastoPorCategoria(ArrayList<Transacao> transacoes) {
        if (transacoes == null || transacoes.isEmpty()) {
            return "Nenhuma transação registrada.";
        }

        Map<String, Double> gastosPorCategoria = new HashMap<>();

        for (Transacao t : transacoes) {
            if (t instanceof Despesa) {
                String categoria = t.getCategoria().getNome();
                double valor = Math.abs(t.getValor());

                gastosPorCategoria.put(categoria,
                        gastosPorCategoria.getOrDefault(categoria, 0.0) + valor);
            }
        }

        StringBuilder sb = new StringBuilder("Relatório de Gastos por Categoria:\n");
        for (Map.Entry<String, Double> entry : gastosPorCategoria.entrySet()) {
            sb.append(entry.getKey()).append(": R$ ")
                    .append(String.format("%.2f", entry.getValue()))
                    .append("\n");
        }

        return sb.toString();
    }

    // todos os relatórios por período organiado
    private ArrayList<Transacao> filtrarTransacoesPorPeriodo(ArrayList<Transacao> transacoes, LocalDate dataInicio, LocalDate dataFim) {
        if (transacoes == null) {
            return new ArrayList<>();
        }
        
        return transacoes.stream()
                .filter(t -> !t.getData().isBefore(dataInicio) && !t.getData().isAfter(dataFim))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    // mês que a gente quiser
    public String gerarRelatorioMensal(ArrayList<Transacao> todasTransacoes, int ano, int mes) {
        LocalDate dataInicio = LocalDate.of(ano, mes, 1);
        LocalDate dataFim = dataInicio.withDayOfMonth(dataInicio.lengthOfMonth());
        
        ArrayList<Transacao> transacoesDoMes = filtrarTransacoesPorPeriodo(todasTransacoes, dataInicio, dataFim);
        
        return "relatório mensal (" + mes + "/" + ano + ") ---\n" +
               gerarBalanco(transacoesDoMes) + "\n\n" +
               gerarRelatorioGastoPorCategoria(transacoesDoMes);
    }
    
    // trimestre
    public String gerarRelatorioTrimestral(ArrayList<Transacao> todasTransacoes, int ano, int trimestre) {
        int mesInicial = (trimestre - 1) * 3 + 1;
        int mesFinal = mesInicial + 2;
        
        LocalDate dataInicio = LocalDate.of(ano, mesInicial, 1);
        LocalDate dataFim = LocalDate.of(ano, mesFinal, 1).withDayOfMonth(LocalDate.of(ano, mesFinal, 1).lengthOfMonth());

        ArrayList<Transacao> transacoesDoTrimestre = filtrarTransacoesPorPeriodo(todasTransacoes, dataInicio, dataFim);

        return "relatório trimestral (" + trimestre + "º trimestre de " + ano + ") ---\n" +
               gerarBalanco(transacoesDoTrimestre) + "\n\n" +
               gerarRelatorioGastoPorCategoria(transacoesDoTrimestre);
    }

    // gera de uma semestre
    public String gerarRelatorioSemestral(ArrayList<Transacao> todasTransacoes, int ano, int semestre) {
        int mesInicial = (semestre == 1) ? 1 : 7;
        int mesFinal = (semestre == 1) ? 6 : 12;

        LocalDate dataInicio = LocalDate.of(ano, mesInicial, 1);
        LocalDate dataFim = LocalDate.of(ano, mesFinal, 1).withDayOfMonth(LocalDate.of(ano, mesFinal, 1).lengthOfMonth());
        
        ArrayList<Transacao> transacoesDoSemestre = filtrarTransacoesPorPeriodo(todasTransacoes, dataInicio, dataFim);
        
        return "relatório semestral (" + semestre + "º semestre de " + ano + ") ---\n" +
               gerarBalanco(transacoesDoSemestre) + "\n\n" +
               gerarRelatorioGastoPorCategoria(transacoesDoSemestre);
    }

    // gera o relatório dum ano
    public String gerarRelatorioAnual(ArrayList<Transacao> todasTransacoes, int ano) {
        LocalDate dataInicio = LocalDate.of(ano, 1, 1);
        LocalDate dataFim = LocalDate.of(ano, 12, 31);
        
        ArrayList<Transacao> transacoesDoAno = filtrarTransacoesPorPeriodo(todasTransacoes, dataInicio, dataFim);
        
        return "relatório anual (" + ano + ") \n" +
               gerarBalanco(transacoesDoAno) + "\n\n" +
               gerarRelatorioGastoPorCategoria(transacoesDoAno);
    }
}
