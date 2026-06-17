package elderia.com.elderia;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ConvenioRepository {
    private static final String NOME_ARQUIVO = "convenios_elderia.dat";

    @SuppressWarnings("unchecked")
    public static List<Convenio> listarTodos() {
        File file = new File(NOME_ARQUIVO);

        if (!file.exists()) {
            return new ArrayList<>();
        }

        FileInputStream fileInput = null;
        ObjectInputStream objectInput = null;
        List<Convenio> listaRecuperada = new ArrayList<>();

        try {
            fileInput = new FileInputStream(file);
            objectInput = new ObjectInputStream(fileInput);

            listaRecuperada = (List<Convenio>) objectInput.readObject();

        } catch (FileNotFoundException e) {
            System.err.println("Arquivo de convênios não encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erro ao ler convênios: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Classe Convenio não encontrada: " + e.getMessage());
        } finally {
            try {
                if (objectInput != null) {
                    objectInput.close();
                }
                if (fileInput != null) {
                    fileInput.close();
                }
            } catch (IOException ex) {
                System.err.println("Erro ao fechar arquivo de convênios: " + ex.getMessage());
            }
        }

        return listaRecuperada;
    }

    public static void salvarTodos(List<Convenio> lista) {
        FileOutputStream fileOutput = null;
        ObjectOutputStream objectOutput = null;

        try {
            fileOutput = new FileOutputStream(NOME_ARQUIVO);
            objectOutput = new ObjectOutputStream(fileOutput);

            objectOutput.writeObject(lista);

        } catch (IOException e) {
            System.err.println("Erro ao salvar convênios: " + e.getMessage());
        } finally {
            try {
                if (objectOutput != null) {
                    objectOutput.close();
                }
                if (fileOutput != null) {
                    fileOutput.close();
                }
            } catch (IOException ex) {
                System.err.println("Erro ao fechar arquivo de convênios: " + ex.getMessage());
            }
        }
    }

    public static int gerarProximoId() {
        List<Convenio> convenios = listarTodos();

        int maiorId = 0;

        for (Convenio convenio : convenios) {
            if (convenio.getIdConvenio() > maiorId) {
                maiorId = convenio.getIdConvenio();
            }
        }

        return maiorId + 1;
    }

    public static void adicionar(Convenio convenio) {
        List<Convenio> convenios = listarTodos();
        convenios.add(convenio);
        salvarTodos(convenios);
    }

    public static void atualizar(Convenio convenioAtualizado) {
        List<Convenio> convenios = listarTodos();

        for (int i = 0; i < convenios.size(); i++) {
            if (convenios.get(i).getIdConvenio() == convenioAtualizado.getIdConvenio()) {
                convenios.set(i, convenioAtualizado);
                break;
            }
        }

        salvarTodos(convenios);
    }

    public static void excluirPorId(int idConvenio) {
        List<Convenio> convenios = listarTodos();
        convenios.removeIf(convenio -> convenio.getIdConvenio() == idConvenio);
        salvarTodos(convenios);
    }

    public static Convenio buscarPorId(int idConvenio) {
        for (Convenio convenio : listarTodos()) {
            if (convenio.getIdConvenio() == idConvenio) {
                return convenio;
            }
        }
        return null;
    }

    public static List<Convenio> pesquisarPorNome(String textoBusca) {
        List<Convenio> convenios = listarTodos();
        List<Convenio> resultado = new ArrayList<>();

        String filtro = textoBusca.toLowerCase().trim();

        for (Convenio convenio : convenios) {
            if (convenio.getNome().toLowerCase().contains(filtro)) {
                resultado.add(convenio);
            }
        }

        return resultado;
    }
}