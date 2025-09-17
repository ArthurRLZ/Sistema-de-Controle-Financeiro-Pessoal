package Dados;

import org.apache.commons.csv.CSVFormat;	
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Negocios.Categoria;
import Negocios.Conta;
import Negocios.ControleFinanceiro;
import Negocios.Despesa;
import Negocios.DespesaRecorrente;
import Negocios.Periodicidade;
import Negocios.Receita;
import Negocios.Transacao;

public class PersistenciaDados {
	// SERIALIZACAO 
	
	// Salva ControleFinanceiro completo usando serialização
    public static void salvarDadosSerializacao(ControleFinanceiro controle, String nomeArquivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(controle);
            System.out.println("Dados salvos");
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados por serialização: " + e.getMessage());
        }
    }

    // Carrega ControleFinanceiro completo a partir da serialização
    public static ControleFinanceiro carregarDadosSerializacao(String nomeArquivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            return (ControleFinanceiro) ois.readObject();
        } catch (FileNotFoundException e) {
            // caso em que o arquivo ainda não existe.
            System.out.println("Arquivo de dados não encontrado. Iniciando com sistema vazio.");
            return null;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro crítico ao carregar os dados: " + e.getMessage());
            return null;
        }
    }
    
//  CSV 
    // Salvar
    public static void salvarDadosCSV(ControleFinanceiro controle, String nomeBaseArquivo) throws IOException { //Funcao principal para salvar
        salvarContasCSV(controle.listarContas(), nomeBaseArquivo + "_contas.csv");
        salvarCategoriasCSV(controle.listarCategorias(), nomeBaseArquivo + "_categorias.csv");
        salvarTransacoesCSV(controle.listarTransacoes(), nomeBaseArquivo + "_transacoes.csv");
        salvarDespesasRecorrentesCSV(controle.listarDespesasRecorrentes(), nomeBaseArquivo + "_despesas_recorrentes.csv");
        System.out.println("Dados salvos em CSV.");
    }
    

    private static void salvarContasCSV(List<Conta> contas, String nomeArquivo) throws IOException {
        try (
            FileWriter out = new FileWriter(nomeArquivo);
            CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader("id", "nome", "saldo"));
        ) {
            for (Conta c : contas) {
                printer.printRecord(c.getId(), c.getNome(), c.getSaldo());
            }
        }
    }

    private static void salvarCategoriasCSV(List<Categoria> categorias, String nomeArquivo) throws IOException {
        try (
            FileWriter out = new FileWriter(nomeArquivo);
            CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader("id", "nome"));
        ) {
            for (Categoria c : categorias) {
                printer.printRecord(c.getId(), c.getNome());
            }
        }
    }

    private static void salvarTransacoesCSV(List<Transacao> transacoes, String nomeArquivo) throws IOException {
        try (
            FileWriter out = new FileWriter(nomeArquivo);
            CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader("id", "valor", "data", "descricao", "tipo", "id_conta", "id_categoria"));
        ) {
            for (Transacao t : transacoes) {
                String tipo = (t instanceof Receita) ? "Receita" : "Despesa";
                printer.printRecord(t.getId(), t.getValor(), t.getData(), t.getDescricao(), tipo, t.getConta().getId(), t.getCategoria().getId());
            }
        }
    }

    private static void salvarDespesasRecorrentesCSV(List<DespesaRecorrente> despesasRecorrentes, String nomeArquivo) throws IOException {
        try (
            FileWriter out = new FileWriter(nomeArquivo);
            CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader("id", "valor", "data", "descricao", "id_conta", "id_categoria", "periodicidade", "num_parcelas", "parcela_atual", "prox_ocorrencia"));
        ) {
            for (DespesaRecorrente dr : despesasRecorrentes) {
                printer.printRecord(dr.getId(), dr.getValor(), dr.getData(), dr.getDescricao(), dr.getConta().getId(), dr.getCategoria().getId(), dr.getPeriodicidade(), dr.getNumeroDeParcelas(), dr.getParcelaAtual(), dr.getProximaOcorrencia());
            }
        }
    }

    
    
  // Carregar
    public static ControleFinanceiro carregarDadosCSV(String nomeBaseArquivo) throws IOException {
        List<Conta> contas = carregarContasCSV(nomeBaseArquivo + "_contas.csv");
        List<Categoria> categorias = carregarCategoriaCSV(nomeBaseArquivo + "_categorias.csv");
        List<Transacao> transacoes = carregarTransacoesCSV(nomeBaseArquivo + "_transacoes.csv", contas, categorias);
        List<DespesaRecorrente> despesasRecorrentes = carregarDespesasRecorrentesCSV(nomeBaseArquivo + "_despesas_recorrentes.csv", contas, categorias);

        // Cria  os repositórios com as listas carregadas
        RepositorioConta repoConta = new RepositorioConta();
        repoConta.setContas(contas);
        
        RepositorioCategoria repoCategoria = new RepositorioCategoria();
        repoCategoria.setCategorias(categorias);
        
        RepositorioTransacao repoTransacao = new RepositorioTransacao();
        repoTransacao.setTransacoes(transacoes);
        
        RepositorioDespesaRecorrente repoDespesaRecorrente = new RepositorioDespesaRecorrente();
        repoDespesaRecorrente.setDespesasRecorrentes(despesasRecorrentes);

        return new ControleFinanceiro(repoConta, repoCategoria, repoTransacao, repoDespesaRecorrente);
    }
    
    public static List<Transacao> carregarTransacoesCSV(String nomeArquivo, List<Conta> contas, List<Categoria> categorias) throws IOException {
        List<Transacao> transacoes = new ArrayList<>();
        
        try (
            FileReader in = new FileReader(nomeArquivo);
            CSVParser parser = new CSVParser(in, CSVFormat.DEFAULT.withFirstRecordAsHeader());
        ) {
            for (CSVRecord record : parser) {
               
                int id = Integer.parseInt(record.get("id"));
                double valor = Double.parseDouble(record.get("valor"));
                LocalDate data = LocalDate.parse(record.get("data"));
                String descricao = record.get("descricao");
                String tipoTransacao = record.get("tipo");
                
            
                int idConta = Integer.parseInt(record.get("id_conta"));
                Conta conta = contas.stream()
                        .filter(c -> c.getId() == idConta)
                        .findFirst().orElse(null);
                
                int idCategoria = Integer.parseInt(record.get("id_categoria"));
                Categoria categoria = categorias.stream()
                        .filter(c -> c.getId() == idCategoria)
                        .findFirst().orElse(null);

                if (conta != null && categoria != null) {
                  
                    Transacao novaTransacao = null;
                    if (tipoTransacao.equalsIgnoreCase("Receita")) {
                        novaTransacao = new Receita(valor, data, descricao, conta, categoria);
                    } else if (tipoTransacao.equalsIgnoreCase("Despesa")) {
                        novaTransacao = new Despesa(valor, data, descricao, conta, categoria);
                    }
                    
                    if (novaTransacao != null) {
                        novaTransacao.setId(id);
                        transacoes.add(novaTransacao);
                    }
                }
            }
        }
        return transacoes;
    }
    

    public static List<Conta> carregarContasCSV(String nomeArquivo) throws IOException {
        List<Conta> contas = new ArrayList<>();
        try (
            FileReader in = new FileReader(nomeArquivo);
            CSVParser parser = new CSVParser(in, CSVFormat.DEFAULT.withHeader("id", "nome", "saldo"));
        ) {
            for (CSVRecord record : parser) {
                try {
                    int id = Integer.parseInt(record.get("id"));
                    String nome = record.get("nome");
                    double saldo = Double.parseDouble(record.get("saldo"));
                    contas.add(new Conta(id, nome, saldo));
                } catch (NumberFormatException e) {
                   
                }
            }
        }
        return contas;
    }
    
    public static List<Categoria> carregarCategoriaCSV(String nomeArquivo) throws IOException{
    	List<Categoria> categorias = new ArrayList<>();
    	try(
    	FileReader in = new FileReader(nomeArquivo);
        CSVParser parser = new CSVParser(in, CSVFormat.DEFAULT.withHeader("id", "nome"));
    			){
    		for (CSVRecord record : parser) {
    			try {
    				int id = Integer.parseInt(record.get("id"));
    				String nome = record.get("nome");
    				categorias.add(new Categoria(nome,id));
    			} catch (NumberFormatException e) {
                  
                }
            }
        }
        return categorias;
    }
    
    public static List<DespesaRecorrente> carregarDespesasRecorrentesCSV(String nomeArquivo, List<Conta> contas, List<Categoria> categorias) throws IOException {
        List<DespesaRecorrente> despesasRecorrentes = new ArrayList<>();
        
        try (
            FileReader in = new FileReader(nomeArquivo);
            CSVParser parser = new CSVParser(in, CSVFormat.DEFAULT.withHeader());
        ) {
            for (CSVRecord record : parser) {
                try {
                 
                    int id = Integer.parseInt(record.get("id"));
                    double valor = Double.parseDouble(record.get("valor"));
                    LocalDate data = LocalDate.parse(record.get("data"));
                    String descricao = record.get("descricao");
                    String periodicidade = record.get("periodicidade");
                    int numeroDeParcelas = Integer.parseInt(record.get("numeroDeParcelas"));
                    
                 
                    int idConta = Integer.parseInt(record.get("id_conta"));
                    Conta conta = contas.stream()
                            .filter(c -> c.getId() == idConta)
                            .findFirst().orElse(null);
                    
                    int idCategoria = Integer.parseInt(record.get("id_categoria"));
                    Categoria categoria = categorias.stream()
                            .filter(c -> c.getId() == idCategoria)
                            .findFirst().orElse(null);
                    
             
                    if (conta != null && categoria != null) {
                        DespesaRecorrente novaDespesa = new DespesaRecorrente(
                                valor, data, descricao, conta, categoria, 
                                Periodicidade.valueOf(periodicidade), numeroDeParcelas);
                        novaDespesa.setId(id);
                        despesasRecorrentes.add(novaDespesa);
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Erro ao carregar despesa recorrente: dado numérico inválido.");
                }
            }
        }
        return despesasRecorrentes;
    }
}
