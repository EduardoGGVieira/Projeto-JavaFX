package elderia.com.elderia;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

//copiado e colado do Repository de horario, caso de cagadinhas eu faço um novo

public class MedicamentoRepository {

    private static final String NOME_ARQUIVO = "medicamento_elderia.dat";

    @SuppressWarnings("unchecked")
    public static List<HorarioDisponivel> listarTodos() {

        File file = new File(NOME_ARQUIVO);

        if (!file.exists()) {
            return new ArrayList<>();
        }

        FileInputStream fileInput = null;
        ObjectInputStream objectInput = null;
        List<HorarioDisponivel> listaRecuperada = new ArrayList<>();

        try {

            fileInput = new FileInputStream(file);
            objectInput = new ObjectInputStream(fileInput);

            listaRecuperada =
                    (List<HorarioDisponivel>) objectInput.readObject();

        } catch (FileNotFoundException e) {

            System.err.println("Arquivo de persistência não encontrado: "
                    + e.getMessage());

        } catch (IOException e) {

            System.err.println("Erro de Entrada/Saída ao ler o fluxo de bytes: "
                    + e.getMessage());

        } catch (ClassNotFoundException e) {

            System.err.println("Falto coisa filho, deu bosta: "
                    + e.getMessage());

        } finally {

            try {

                if (objectInput != null) {
                    objectInput.close();
                }

                if (fileInput != null) {
                    fileInput.close();
                }

                System.out.println("Você conseguiu ler, meus parábens.");

            } catch (IOException ex) {

                System.err.println("Cara, acho que deu merda, recomendo jogar água benta. "
                        + ex.getMessage());
            }
        }

        return listaRecuperada;
    }

    public static void salvarTodos(List<HorarioDisponivel> lista) {

        FileOutputStream fileOutput = null;
        ObjectOutputStream objectOutput = null;

        try {

            fileOutput = new FileOutputStream(NOME_ARQUIVO);

            objectOutput = new ObjectOutputStream(fileOutput);

            objectOutput.writeObject(lista);

            System.out.println("párabens, funcionou agora pode descansar");

        } catch (IOException e) {

            System.err.println("Aqui pode dar ALT+F4: "
                    + e.getMessage());

        } finally {

            try {

                if (objectOutput != null) {
                    objectOutput.close();
                }

                if (fileOutput != null) {
                    fileOutput.close();
                }

                System.out.println("Funcionou essa brincadeira. Dito isso podemos descansar");

            } catch (IOException ex) {

                System.err.println("Erro crítico contato do suporte: @Jesus Cristo"
                        + ex.getMessage());
            }
        }
    }
}