package elderia.com.elderia;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AvaliacaoRepository {
    private static final String NOME_ARQUIVO = "avaliacoes.dat";

    public static List<Avaliacao> listarTodos() {
        File file = new File(NOME_ARQUIVO);

        if (!file.exists()) {
            return new ArrayList<>();
        }

        FileInputStream fileInput = null;
        ObjectInputStream objectInput = null;
        List<Avaliacao> listaRecuperada = new ArrayList<>();

        try {
            fileInput = new FileInputStream(file);
            objectInput = new ObjectInputStream(fileInput);

            listaRecuperada = (List<Avaliacao>) objectInput.readObject();
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo de avaliações não encontrado.\nErro: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erro ao ler avaliações.\nErro: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Classe 'Avaliacao' não encontrada.\nErro: " + e.getMessage());
        } finally {
            try {
                if (objectInput != null) {
                    objectInput.close();
                }
                if (fileInput != null) {
                    fileInput.close();
                }
            } catch (IOException e) {
                System.err.println("Falha ao fechar arquivo de avaliações.\nErro: " + e.getMessage());
            }
        }

        return listaRecuperada;
    }
}
