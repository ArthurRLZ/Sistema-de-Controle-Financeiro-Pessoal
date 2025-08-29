package Negocios;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Relatorio {

    // Gera um resumo de receitas, despesas e saldo final
    private String gerarBalanco(List<Transacao> transacoes) {
        if (transacoes == null || transacoes.isEmpty()) {
            return "Nenhuma transação no período.\n";
        }

        double totalReceitas = transacoes.stream()
                .filter(t -> t instanceof Receita)
                .mapToDouble(Transacao::getValor)
                .sum();

        double totalDespesas = transacoes.stream()
                .filter(t -> t instanceof Despesa)
                .mapToDouble(Transacao::getValor)
                .sum();

        double saldoFinal = totalReceitas - totalDespesas;

        return String.format(
            "Balanço do Período:\n" +
            "  - Total de Receitas: R$ %.2f\n" +
            "  - Total de Despesas: R$ %.2f\n" +
            "  - Saldo:             R$ %.2f\n",
            totalReceitas, totalDespesas, saldoFinal
        );
    }

    // Gera um relatório que mostra o total gasto em cada categoria
    private String gerarRelatorioGastoPorCategoria(List<Transacao> transacoes) {
        Map<String, Double> gastosPorCategoria = transacoes.stream()
                .filter(t -> t instanceof Despesa)
                .map(t -> (Despesa) t)
                .collect(Collectors.groupingBy(
                        d -> d.getCategoria().getNome(),
                        Collectors.summingDouble(Despesa::getValor)
                ));

        if (gastosPorCategoria.isEmpty()) {
            return "Nenhuma despesa no período.\n";
        }

        StringBuilder sb = new StringBuilder("Gastos por Categoria:\n");
        gastosPorCategoria.forEach((categoria, total) ->
                sb.append(String.format("  - %-20s: R$ %.2f\n", categoria, total))
        );

        return sb.toString();
    }
    
    // Filtra as transações por um período de datas
    private List<Transacao> filtrarPorPeriodo(List<Transacao> transacoes, LocalDate inicio, LocalDate fim) {
        return transacoes.stream()
            .filter(t -> {
                LocalDate dataTransacao = t.getData().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                return !dataTransacao.isBefore(inicio) && !dataTransacao.isAfter(fim);
            })
            .collect(Collectors.toList());
    }

    // Gera o relatório de um ano específico
    public String gerarRelatorioAnual(List<Transacao> todasTransacoes, int ano) {
        LocalDate dataInicio = LocalDate.of(ano, 1, 1);
        LocalDate dataFim = LocalDate.of(ano, 12, 31);
        List<Transacao> transacoesDoPeriodo = filtrarPorPeriodo(todasTransacoes, dataInicio, dataFim);
        
        StringBuilder relatorio = new StringBuilder();
        relatorio.append(String.format("========= RELATÓRIO ANUAL DE %d =========\n", ano));
        relatorio.append(gerarBalanco(transacoesDoPeriodo));
        relatorio.append("----------------------------------------\n");
        relatorio.append(gerarRelatorioGastoPorCategoria(transacoesDoPeriodo));
        relatorio.append("========================================\n");
        return relatorio.toString();
    }
    
    // Gera o relatório de um mês específico
    public String gerarRelatorioMensal(List<Transacao> todasTransacoes, int ano, int mes) {
        LocalDate dataInicio = LocalDate.of(ano, mes, 1);
        LocalDate dataFim = dataInicio.withDayOfMonth(dataInicio.lengthOfMonth());
        List<Transacao> transacoesDoPeriodo = filtrarPorPeriodo(todasTransacoes, dataInicio, dataFim);

        StringBuilder relatorio = new StringBuilder();
        relatorio.append(String.format("======= RELATÓRIO MENSAL - %02d/%d =======\n", mes, ano));
        relatorio.append(gerarBalanco(transacoesDoPeriodo));
        relatorio.append("----------------------------------------\n");
        relatorio.append(gerarRelatorioGastoPorCategoria(transacoesDoPeriodo));
        relatorio.append("========================================\n");
        return relatorio.toString();
    }
    
    // Gera o relatório de um trimestre específico
    public String gerarRelatorioTrimestral(List<Transacao> todasTransacoes, int ano, int trimestre) {
        int mesInicial = (trimestre - 1) * 3 + 1;
        LocalDate dataInicio = LocalDate.of(ano, mesInicial, 1);
        LocalDate dataFim = dataInicio.plusMonths(2).withDayOfMonth(dataInicio.plusMonths(2).lengthOfMonth());
        List<Transacao> transacoesDoPeriodo = filtrarPorPeriodo(todasTransacoes, dataInicio, dataFim);

        StringBuilder relatorio = new StringBuilder();
        relatorio.append(String.format("==== RELATÓRIO TRIMESTRAL - %dº TRI/%d ====\n", trimestre, ano));
        relatorio.append(gerarBalanco(transacoesDoPeriodo));
        relatorio.append("----------------------------------------\n");
        relatorio.append(gerarRelatorioGastoPorCategoria(transacoesDoPeriodo));
        relatorio.append("========================================\n");
        return relatorio.toString();
    }
    
    // Gera o relatório de um semestre específico
    public String gerarRelatorioSemestral(List<Transacao> todasTransacoes, int ano, int semestre) {
        int mesInicial = (semestre - 1) * 6 + 1;
        LocalDate dataInicio = LocalDate.of(ano, mesInicial, 1);
        LocalDate dataFim = dataInicio.plusMonths(5).withDayOfMonth(dataInicio.plusMonths(5).lengthOfMonth());
        List<Transacao> transacoesDoPeriodo = filtrarPorPeriodo(todasTransacoes, dataInicio, dataFim);

        StringBuilder relatorio = new StringBuilder();
        relatorio.append(String.format("==== RELATÓRIO SEMESTRAL - %dº SEM/%d ====\n", semestre, ano));
        relatorio.append(gerarBalanco(transacoesDoPeriodo));
        relatorio.append("----------------------------------------\n");
        relatorio.append(gerarRelatorioGastoPorCategoria(transacoesDoPeriodo));
        relatorio.append("========================================\n");
        return relatorio.toString();
    }
}
