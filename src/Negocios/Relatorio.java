package Negocios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Relatorio {

    // Gera relatório de gastos agrupados por categoria
	public String gerarRelatorioGastoPorCategoria(ArrayList<Transacao> transacoes) {
	    if (transacoes == null || transacoes.isEmpty()) {
	        return "Nenhuma transação registrada.";
	    }

	    Map<String, Double> gastosPorCategoria = new HashMap<>();

	    for (Transacao t : transacoes) {
	    										// Agora, verifica se a transação é uma Despesa
	        if (t instanceof Despesa) {
	            String categoria = t.getCategoria().getNome();
	            double valor = t.getValor();

	            gastosPorCategoria.put(categoria,
	                    gastosPorCategoria.getOrDefault(categoria, 0.0) + valor);
	        }
	    }

	    // Montando o relatório em String
	    StringBuilder sb = new StringBuilder("Relatório de Gastos por Categoria:\n");
	    for (Map.Entry<String, Double> entry : gastosPorCategoria.entrySet()) {
	        sb.append(entry.getKey()).append(": R$ ")
	                .append(String.format("%.2f", entry.getValue()))
	                .append("\n");
	    }

	    return sb.toString();
	}

	// Gera balanço geral (receitas - despesas)
	public String gerarBalanco(ArrayList<Transacao> transacoes) {
	    if (transacoes == null || transacoes.isEmpty()) {
	        return "Nenhuma transação registrada.";
	    }

	    double receitas = 0;
	    double despesas = 0;

	    for (Transacao t : transacoes) {
	        															// Usa o instanceof para distinguir entre Receita e Despesa
	        if (t instanceof Receita) {
	            receitas += t.getValor();
	        } else if (t instanceof Despesa) {
	            despesas += t.getValor();
	        }
	    }

	    double saldo = receitas - despesas;

	    return String.format("Receitas: R$ %.2f\nDespesas: R$ %.2f\nSaldo Final: R$ %.2f",
	            receitas, despesas, saldo);
	}
}