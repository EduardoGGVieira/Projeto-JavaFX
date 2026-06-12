package elderia.com.elderia;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class UsuarioRepository {
    private static final String NOME_ARQUIVO = "usuarios_elderia.dat";

    @SuppressWarnings("unchecked")
    public static List<Usuario> listarTodos() {
        File file = new File(NOME_ARQUIVO);

        // Se o arquivo permanente não existe, retorna uma lista vazia inicial
        if (!file.exists()) {
            return new ArrayList<>();
        }

        // Lê tudo isso aqui para joga lá no Finally
        FileInputStream fileInput = null;
        ObjectInputStream objectInput = null;
        List<Usuario> listaRecuperada = new ArrayList<>();

        try {
            // Inicializa os streams de leitura a partir do arquivo permanente
            fileInput = new FileInputStream(file);
            objectInput = new ObjectInputStream(fileInput);

            // leitor do trem de byts!!
            listaRecuperada = (List<Usuario>) objectInput.readObject();


        } catch (FileNotFoundException e) {
            System.err.println("Arquivo de persistência não encontrado: " + e.getMessage());
        } catch (IOException e) {

            System.err.println("Erro de Entrada/Saída ao ler o fluxo de bytes: " + e.getMessage());
        } catch (ClassNotFoundException e) {

            System.err.println("Classe de domínio não mapeada para deserialização: " + e.getMessage());
        } finally {

            // O BLOCO FINALLY garante que o isso aqui vai acontecer idependete de qualquer coisa
            try {
                if (objectInput != null) {
                    objectInput.close(); // Fecha o ObjectInputStream
                }
                if (fileInput != null) {
                    fileInput.close(); // Fecha o FileInputStream físico
                }
                System.out.println("Deu certo, tu leu oq tinha q ler.");
            } catch (IOException ex) {
                System.err.println("Deu bosta pae, azedo a sopa: " + ex.getMessage());
            }
        }
        // volta tudo q aconteceu para a lista
        return listaRecuperada;
    }

    public static void salvarTodos(List<Usuario> lista) {

        // ObjectOutputStream = saída
        // ObjectInputStream = entrada lê os dados

        FileOutputStream fileOutput = null;
        ObjectOutputStream objectOutput = null;

        try {
            // Inicializa o stream que salva tudo
            fileOutput = new FileOutputStream(NOME_ARQUIVO);

            // Inicializa o stream que separa um por um (TREM)
            objectOutput = new ObjectOutputStream(fileOutput);

            // Escreve tudo q tem nos byts para guardar na lista
            objectOutput.writeObject(lista);
            System.out.println("Muito bom, o eduardo conseguiu fazer funcionar!.");

            // Deu merda faz isso
        } catch (IOException e) {
            System.err.println("Aqui pode dar ALT+F4: " + e.getMessage());
        } finally {

            // oq tem aqui, vai acontecer e foda-se
            try {
                if (objectOutput != null) {
                    objectOutput.close(); // Fecha o ObjectOutputStream
                }
                if (fileOutput != null) {
                    fileOutput.close(); // Fecha o FileOutputStream
                }
                System.out.println("Deu certo esta BUFUNFA, joga muito.");
            } catch (IOException ex) {
                System.err.println("Erro crítico contato do suporte: @Abimael " + ex.getMessage());
            }
        }
    }
}