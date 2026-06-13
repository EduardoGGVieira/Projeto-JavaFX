package elderia.com.elderia;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ValidacaoCertificadoRepository {
    private static final String NOME_ARQUIVO = "validacoes_certificados_elderia.dat";

    @SuppressWarnings("unchecked")
    public static List<ValidacaoCertificado> listarTodos() {
        File file = new File(NOME_ARQUIVO);

        if (!file.exists()) {
            return new ArrayList<>();
        }

        FileInputStream fileInput = null;
        ObjectInputStream objectInput = null;
        List<ValidacaoCertificado> listaRecuperada = new ArrayList<>();

        try {
            fileInput = new FileInputStream(file);
            objectInput = new ObjectInputStream(fileInput);

            listaRecuperada = (List<ValidacaoCertificado>) objectInput.readObject();

        } catch (FileNotFoundException e) {
            System.err.println("Arquivo de validações não encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erro ao ler validações: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Classe ValidacaoCertificado não encontrada: " + e.getMessage());
        } finally {
            try {
                if (objectInput != null) {
                    objectInput.close();
                }
                if (fileInput != null) {
                    fileInput.close();
                }
            } catch (IOException ex) {
                System.err.println("Erro ao fechar arquivo de validações: " + ex.getMessage());
            }
        }

        return listaRecuperada;
    }

    public static void salvarTodos(List<ValidacaoCertificado> lista) {
        FileOutputStream fileOutput = null;
        ObjectOutputStream objectOutput = null;

        try {
            fileOutput = new FileOutputStream(NOME_ARQUIVO);
            objectOutput = new ObjectOutputStream(fileOutput);

            objectOutput.writeObject(lista);

        } catch (IOException e) {
            System.err.println("Erro ao salvar validações: " + e.getMessage());
        } finally {
            try {
                if (objectOutput != null) {
                    objectOutput.close();
                }
                if (fileOutput != null) {
                    fileOutput.close();
                }
            } catch (IOException ex) {
                System.err.println("Erro ao fechar arquivo de validações: " + ex.getMessage());
            }
        }
    }

    public static int gerarProximoId() {
        List<ValidacaoCertificado> validacoes = listarTodos();

        int maiorId = 0;

        for (ValidacaoCertificado validacao : validacoes) {
            if (validacao.getIdValidacao() > maiorId) {
                maiorId = validacao.getIdValidacao();
            }
        }

        return maiorId + 1;
    }

    public static void adicionar(ValidacaoCertificado validacao) {
        List<ValidacaoCertificado> validacoes = listarTodos();
        validacoes.add(validacao);
        salvarTodos(validacoes);
    }

    public static void atualizar(ValidacaoCertificado validacaoAtualizada) {
        List<ValidacaoCertificado> validacoes = listarTodos();

        for (int i = 0; i < validacoes.size(); i++) {
            ValidacaoCertificado atual = validacoes.get(i);

            if (atual.getIdValidacao() == validacaoAtualizada.getIdValidacao()) {
                validacoes.set(i, validacaoAtualizada);
                break;
            }
        }

        salvarTodos(validacoes);
    }

    public static void excluirPorId(int idValidacao) {
        List<ValidacaoCertificado> validacoes = listarTodos();

        validacoes.removeIf(validacao -> validacao.getIdValidacao() == idValidacao);

        salvarTodos(validacoes);
    }

    public static List<ValidacaoCertificado> pesquisarPorStatus(String textoBusca) {
        List<ValidacaoCertificado> validacoes = listarTodos();
        List<ValidacaoCertificado> resultado = new ArrayList<>();

        String filtro = textoBusca.toLowerCase().trim();

        for (ValidacaoCertificado validacao : validacoes) {
            if (validacao.getStatus().toLowerCase().contains(filtro)) {
                resultado.add(validacao);
            }
        }

        return resultado;
    }
}