package elderia.com.elderia;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultaRepository {
    private static final String NOME_ARQUIVO = "consultas.dat";

    public static List<Consulta> listarTodos() {
        File file = new File(NOME_ARQUIVO);

        if (!file.exists()) {
            return new ArrayList<>();
        }

        FileInputStream fileInput = null;
        ObjectInputStream objectInput = null;
        List<Consulta> listaRecuperada = new ArrayList<>();

        try {
            fileInput = new FileInputStream(file);
            objectInput = new ObjectInputStream(fileInput);

            listaRecuperada = (List<Consulta>) objectInput.readObject();
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo de consultas não encontrado.\nErro: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo.\nErro: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Classe 'Consulta' não encontrada.\nErro: " + e.getMessage());
        } finally {
            try {
                if (objectInput != null) objectInput.close();
                if (fileInput != null) fileInput.close();
            } catch (IOException e) {
                System.err.println("Falha ao fechar arquivo de consultas.\nErro: " + e.getMessage());
            }
        }

        return listaRecuperada;
    }

    public static void salvarTodos(List<Consulta> lista) {
        FileOutputStream fileOutput = null;
        ObjectOutputStream objectOutput = null;

        try {
            fileOutput = new FileOutputStream(NOME_ARQUIVO);
            objectOutput = new ObjectOutputStream(fileOutput);

            objectOutput.writeObject(lista);

            System.out.println("Consulta(s) salva(s) com sucesso. Total: " + lista.size());
        } catch (IOException e) {
            System.err.println("Falha ao salvar consulta(s).\nErro: " + e.getMessage());
        } finally {
            try {
                if (objectOutput != null) objectOutput.close();
                if (fileOutput != null) fileOutput.close();
            } catch (IOException e) {
                System.err.println("Falha ao fechar arquivo de consultas.\nErro: " + e.getMessage());
            }
        }
    }
}
