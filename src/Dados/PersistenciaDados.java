package Dados;

import Negocios.ControleFinanceiro;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

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
        public void salvarCategorias(ArrayList<Categoria> categorias) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("categorias.dat"))) {
            oos.writeObject(categorias);
            System.out.println("Categorias salvas com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar categorias: " + e.getMessage());
        }
}

    @SuppressWarnings("unchecked")
    public ArrayList<Categoria> carregarCategorias() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("categorias.dat"))) {
            return (ArrayList<Categoria>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Nenhuma categoria encontrada, criando lista nova.");
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar categorias: " + e.getMessage());
            return new ArrayList<>();
        }
}
    public void salvarContas(ArrayList<Conta> contas) {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("contas.dat"))) {
        oos.writeObject(contas);
        System.out.println("Contas salvas com sucesso.");
    } catch (IOException e) {
        System.err.println("Erro ao salvar contas: " + e.getMessage());
    }
}

    @SuppressWarnings("unchecked")
    public ArrayList<Conta> carregarContas() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("contas.dat"))) {
            return (ArrayList<Conta>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Nenhuma conta encontrada, criando lista nova.");
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar contas: " + e.getMessage());
            return new ArrayList<>();
        }
}
    public void salvarTransacoes(ArrayList<Transacao> transacoes) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("transacoes.dat"))) {
            oos.writeObject(transacoes);
            System.out.println("Transações salvas com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar transações: " + e.getMessage());
        }
}

    @SuppressWarnings("unchecked")
    public ArrayList<Transacao> carregarTransacoes() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("transacoes.dat"))) {
            return (ArrayList<Transacao>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Nenhuma transação encontrada, criando lista nova.");
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar transações: " + e.getMessage());
            return new ArrayList<>();
        }
}
    public void salvarDespesasRecorrentes(ArrayList<DespesaRecorrente> despesas) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("despesasRecorrentes.dat"))) {
            oos.writeObject(despesas);
            System.out.println("Despesas recorrentes salvas com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar despesas recorrentes: " + e.getMessage());
        }
}

    @SuppressWarnings("unchecked")
    public ArrayList<DespesaRecorrente> carregarDespesasRecorrentes() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("despesasRecorrentes.dat"))) {
            return (ArrayList<DespesaRecorrente>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
                System.err.println("Erro ao carregar despesas recorrentes: " + e.getMessage());
            return new ArrayList<>();
        }
}
}

