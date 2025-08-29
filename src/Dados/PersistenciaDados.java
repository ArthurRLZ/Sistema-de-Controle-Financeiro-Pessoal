package Dados;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaDados {

    // Métodos de Serialização Genérica
    public void salvarObjeto(List<?> lista, String nomeArquivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(new ArrayList<>(lista));
        }
    }

    public List<?> carregarObjeto(String nomeArquivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            return (List<?>) ois.readObject();
        } catch (FileNotFoundException e) {
            // Se o arquivo não for encontrado, retorna uma lista vazia.
            return new ArrayList<>();
        }
    }
}

