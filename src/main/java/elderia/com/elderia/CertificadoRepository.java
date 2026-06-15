package elderia.com.elderia;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CertificadoRepository {
    private static final String NOME_ARQUIVO = "certificados_elderia.dat";

    @SuppressWarnings("unchecked")
    public static List<Certificado> listarTodos() {
        File file = new File(NOME_ARQUIVO);

        if (!file.exists()) {
            return new ArrayList<>();
        }

        FileInputStream fileInput = null;
        ObjectInputStream objectInput = null;
        List<Certificado> listaRecuperada = new ArrayList<>();

        try {
            fileInput = new FileInputStream(file);
            objectInput = new ObjectInputStream(fileInput);

            listaRecuperada = (List<Certificado>) objectInput.readObject();

        } catch (FileNotFoundException e) {
            System.err.println("Arquivo de certificados não encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erro ao ler certificados: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Classe Certificado não encontrada: " + e.getMessage());
        } finally {
            try {
                if (objectInput != null) {
                    objectInput.close();
                }
                if (fileInput != null) {
                    fileInput.close();
                }
            } catch (IOException ex) {
                System.err.println("Erro ao fechar arquivo de certificados: " + ex.getMessage());
            }
        }

        return listaRecuperada;
    }

    public static void salvarTodos(List<Certificado> lista) {
        FileOutputStream fileOutput = null;
        ObjectOutputStream objectOutput = null;

        try {
            fileOutput = new FileOutputStream(NOME_ARQUIVO);
            objectOutput = new ObjectOutputStream(fileOutput);

            objectOutput.writeObject(lista);

        } catch (IOException e) {
            System.err.println("Erro ao salvar certificados: " + e.getMessage());
        } finally {
            try {
                if (objectOutput != null) {
                    objectOutput.close();
                }
                if (fileOutput != null) {
                    fileOutput.close();
                }
            } catch (IOException ex) {
                System.err.println("Erro ao fechar arquivo de certificados: " + ex.getMessage());
            }
        }
    }

    public static int gerarProximoId() {
        List<Certificado> certificados = listarTodos();

        int maiorId = 0;

        for (Certificado certificado : certificados) {
            if (certificado.getIdCertificado() > maiorId) {
                maiorId = certificado.getIdCertificado();
            }
        }

        return maiorId + 1;
    }

    public static void adicionar(Certificado certificado) {
        List<Certificado> certificados = listarTodos();
        certificados.add(certificado);
        salvarTodos(certificados);
    }

    public static void atualizar(Certificado certificadoAtualizado) {
        List<Certificado> certificados = listarTodos();

        for (int i = 0; i < certificados.size(); i++) {
            Certificado atual = certificados.get(i);

            if (atual.getIdCertificado() == certificadoAtualizado.getIdCertificado()) {
                certificados.set(i, certificadoAtualizado);
                break;
            }
        }

        salvarTodos(certificados);
    }

    public static void excluirPorId(int idCertificado) {
        List<Certificado> certificados = listarTodos();

        certificados.removeIf(certificado -> certificado.getIdCertificado() == idCertificado);

        salvarTodos(certificados);
    }

    public static Certificado buscarPorId(int idCertificado) {
        List<Certificado> certificados = listarTodos();

        for (Certificado certificado : certificados) {
            if (certificado.getIdCertificado() == idCertificado) {
                return certificado;
            }
        }

        return null;
    }

    public static List<Certificado> pesquisarPorTitulo(String textoBusca) {
        List<Certificado> certificados = listarTodos();
        List<Certificado> resultado = new ArrayList<>();

        String filtro = textoBusca.toLowerCase().trim();

        for (Certificado certificado : certificados) {
            if (certificado.getTitulo().toLowerCase().contains(filtro)) {
                resultado.add(certificado);
            }
        }

        return resultado;
    }
}