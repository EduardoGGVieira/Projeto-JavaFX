package elderia.com.elderia;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class UsuarioRepository {
    private static final String FILE_NAME = "usuarios_elderia.dat";

    // =====================================================================
    // MÉTODO: Listar/Recuperar todos os usuários (Deserialização)
    // =====================================================================

    @SuppressWarnings("unchecked")
    public static List<Usuario> listarTodos() {
        File file = new File(FILE_NAME);

        // Se o arquivo permanente não existe, retorna uma lista vazia inicial
        if (!file.exists()) {
            return new ArrayList<>();
        }

        // Declaração dos fluxos de leitura fora do bloco try para torná-los visíveis no finally
        FileInputStream fileInput = null;
        ObjectInputStream objectInput = null;
        List<Usuario> listaRecuperada = new ArrayList<>();

        try {
            // Inicializa os streams de leitura a partir do arquivo permanente
            fileInput = new FileInputStream(file);
            objectInput = new ObjectInputStream(fileInput);

            // Reconstrói o objeto (deserialização) a partir do fluxo de bytes
            listaRecuperada = (List<Usuario>) objectInput.readObject();

        } catch (FileNotFoundException e) {
            System.err.println("Arquivo de persistência não encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erro de Entrada/Saída ao ler o fluxo de bytes: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Classe de domínio não mapeada para deserialização: " + e.getMessage());
        } finally {
            // O BLOCO FINALLY garante que o fechamento ocorra mesmo se houver uma exceção no try
            try {
                if (objectInput != null) {
                    objectInput.close(); // Fecha o ObjectInputStream
                }
                if (fileInput != null) {
                    fileInput.close(); // Fecha o FileInputStream físico
                }
                System.out.println("Fluxos de leitura encerrados com segurança no bloco finally.");
            } catch (IOException ex) {
                System.err.println("Erro crítico ao tentar fechar os fluxos de leitura: " + ex.getMessage());
            }
        }

        return listaRecuperada;
    }

    // =====================================================================
    // MÉTODO: Salvar/Sobrescrever a lista de usuários (Serialização)
    // =====================================================================
    public static void salvarTodos(List<Usuario> lista) {
        // Declaração dos fluxos de escrita fora do bloco try
        FileOutputStream fileOutput = null;
        ObjectOutputStream objectOutput = null;

        try {
            // Inicializa o stream que salva os bytes no arquivo permanente
            fileOutput = new FileOutputStream(FILE_NAME);
            // Inicializa o stream que divide o objeto na sequência (trem) de bytes
            objectOutput = new ObjectOutputStream(fileOutput);

            // Escreve os bytes do objeto encapsulado no Stream
            objectOutput.writeObject(lista);
            System.out.println("Estado do sistema persistido com sucesso além do processo atual.");

        } catch (IOException e) {
            System.err.println("Falha crítica na serialização do objeto: " + e.getMessage());
        } finally {
            // Garante a liberação dos recursos do sistema operacional
            try {
                if (objectOutput != null) {
                    objectOutput.close(); // Fecha o ObjectOutputStream e limpa o buffer
                }
                if (fileOutput != null) {
                    fileOutput.close(); // Fecha o FileOutputStream físico
                }
                System.out.println("Fluxos de escrita encerrados com segurança no bloco finally.");
            } catch (IOException ex) {
                System.err.println("Erro crítico ao tentar fechar os fluxos de escrita: " + ex.getMessage());
            }
        }
    }
}