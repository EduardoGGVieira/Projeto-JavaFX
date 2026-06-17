package elderia.com.elderia;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProntuarioRepository {
    private static final String CAMINHO_ARQUIVO = "prontuarios.dat";

    @SuppressWarnings("unchecked")
    // NOVO COMENTÁRIO: CORREÇÃO: Alterado de List<Prontuarios> para List<Prontuario> (singular) para bater com o nome da sua classe Model
    public static List<Prontuario> listarTodos() {
        File arquivo = new File(CAMINHO_ARQUIVO);
        if (!arquivo.exists()) {
            return new ArrayList<>(); // Retorna lista vazia caso o arquivo físico ainda não exista em disco
        }

        // NOVO COMENTÁRIO: Utilização de try-catch para interceptar erros de I/O e desserializar o trem de bytes com segurança
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (List<Prontuario>) ois.readObject();
        } catch (Exception e) {
            System.err.println("Erro ao ler arquivo de prontuários: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void salvarTodos(List<Prontuario> lista) {
        // NOVO COMENTÁRIO: Serializa a coleção de objetos convertendo seu estado atual de volta para formato binário permanente
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CAMINHO_ARQUIVO))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            System.err.println("Erro ao salvar arquivo de prontuários: " + e.getMessage());
        }
    }
}