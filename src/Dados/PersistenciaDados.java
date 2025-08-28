package Dados;

import Negocios.ControleFinanceiro;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PersistenciaDados {


    public void salvarDadosSerializacao(ControleFinanceiro controle) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("controleFinanceiro.dat"))) {
            oos.writeObject(controle);
            System.out.println("Dados serializados salvos com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados serializados: " + e.getMessage());
        }
    }
    public ControleFinanceiro carregarDadosSerializacao() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("controleFinanceiro.dat"))) {
            return (ControleFinanceiro) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado. Criando novo ControleFinanceiro.");
            return new ControleFinanceiro();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar dados serializados: " + e.getMessage());
            return new ControleFinanceiro();
        }
    }
    public void salvarDadosCSV(ControleFinanceiro controle) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("controleFinanceiro.csv"))) {
            
            // Contas
            for (var conta : controle.getContas()) {
                writer.write("CONTA," + conta.getId() + "," + conta.getNome());
                writer.newLine();
            }

            // Categorias
            for (var cat : controle.getCategorias()) {
                writer.write("CATEGORIA," + cat.getId() + "," + cat.getNome());
                writer.newLine();
            }

            // Transações
            for (var t : controle.getTransacoes()) {
                writer.write("TRANSACAO," + t.getId() + "," + t.getDescricao() + "," + t.getValor() + "," +
                        t.getData() + "," + t.getCategoria().getId() + "," + t.getConta().getId());
                writer.newLine();
            }

            System.out.println("Dados CSV salvos com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados CSV: " + e.getMessage());
        }
    }

    public ControleFinanceiro carregarDadosCSV() {
        ControleFinanceiro controle = new ControleFinanceiro();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get("controleFinanceiro.csv"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(",");
                switch (partes[0]) {
                    case "CONTA":
                        controle.adicionarConta(partes[2]); // mantém UUID interno
                        break;
                    case "CATEGORIA":
                        controle.adicionarCategoria(partes[2]);
                        break;
                    case "TRANSACAO":
                        // Aqui você precisaria mapear categoria e conta pelos IDs salvos
                        // Para simplificar, vamos ignorar neste exemplo
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("Arquivo CSV não encontrado ou erro de leitura. Criando novo ControleFinanceiro.");
        }

        return controle;
    }
}

