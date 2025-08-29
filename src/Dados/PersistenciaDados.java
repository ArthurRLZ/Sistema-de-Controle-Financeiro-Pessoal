package Dados;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import Negocios.ControleFinanceiro;

public class PersistenciaDados {

	// Salva ControleFinanceiro completo usando serialização
    public static void salvarDadosSerializacao(ControleFinanceiro controle, String nomeArquivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(controle);
            System.out.println("Dados salvos por serialização em: " + nomeArquivo);
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
    
    
    //TUDO: faltando a implemtenação da lógica para csv
}
