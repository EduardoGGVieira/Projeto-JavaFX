package elderia.com.elderia;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MedicamentoRepository {
    private static final String NOME_ARQUIVO = "medicamento_elderia.dat";

    @SuppressWarnings("unchecked")
    public static List<Medicamento> listarTodos() {
        File file = new File(NOME_ARQUIVO);

        if (!file.exists()) {
            return new ArrayList<>();
        }

        FileInputStream fileInput = null;
        ObjectInputStream objectInput = null;
        List<Medicamento> listaRecuperada = new ArrayList<>();

        try {
            fileInput = new FileInputStream(file);
            objectInput = new ObjectInputStream(fileInput);

            listaRecuperada = (List<Medicamento>) objectInput.readObject();

        } catch (FileNotFoundException e) {
            System.err.println("Arquivo de medicamento não encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erro ao ver medicamentos: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Classe Medicamento não encontrada: " + e.getMessage());
        } finally {
            try {
                if (objectInput != null) {
                    objectInput.close();
                }
                if (fileInput != null) {
                    fileInput.close();
                }
            } catch (IOException ex) {
                System.err.println("Erro ao fechar arquivo de medicamentos: " + ex.getMessage());
            }
        }

        return listaRecuperada;
    }

    public static void salvarTodos(List<Medicamento> lista) {
        FileOutputStream fileOutput = null;
        ObjectOutputStream objectOutput = null;

        try {
            fileOutput = new FileOutputStream(NOME_ARQUIVO);
            objectOutput = new ObjectOutputStream(fileOutput);

            objectOutput.writeObject(lista);

        } catch (IOException e) {
            System.err.println("Erro ao salvar medicamento: " + e.getMessage());
        } finally {
            try {
                if (objectOutput != null) {
                    objectOutput.close();
                }
                if (fileOutput != null) {
                    fileOutput.close();
                }
            } catch (IOException ex) {
                System.err.println("Erro ao fechar arquivo de medicamento: " + ex.getMessage());
            }
        }
    }

    public static int gerarProximoId() {
        List<Medicamento> Medicamento = listarTodos();

        int maiorId = 0;

        for (Medicamento medicamento : Medicamento) {
            if (medicamento.getIdMedicamento() > maiorId) {
                maiorId = Medicamento.getFirst().getIdMedicamento();
            }
        }

        return maiorId + 1;
    }

    public static void adicionar(Medicamento medicamento) {
        List<Medicamento> medicamentos = listarTodos();
        medicamentos.add(medicamento);
        salvarTodos(medicamentos);
    }

    public static void atualizar(Medicamento medicamentosatualizados) {
        List<Medicamento> medicamentos = listarTodos();

        for (int i = 0; i < medicamentos.size(); i++) {
            Medicamento atual = medicamentos.get(i);

            if (atual.getIdMedicamento() == medicamentosatualizados.getIdMedicamento()) {
                medicamentos.set(i, medicamentosatualizados);
                break;
            }
        }

        salvarTodos(medicamentos);
    }

    public static void excluirPorId(int idMenticamento) {
        List<Medicamento> medicamentos = listarTodos();

        medicamentos.removeIf(medicamento -> medicamento.getIdMedicamentos() == idMenticamento);

        salvarTodos(medicamentos);
    }

    public static Medicamento buscarPorId(int idMedicamento) {
        List<Medicamento> medicamentos = listarTodos();

        for (Medicamento medicamento : medicamentos) {
            if (medicamento.getIdMedicamentos() == idMedicamento) {
                return medicamento;
            }
        }

        return null;
    }

    public static List<Medicamento> pesquisarPorTitulo(String textoBusca) {
        List<Medicamento> medicamentos = listarTodos();
        List<Medicamento> resultado = new ArrayList<>();

        String filtro = textoBusca.toLowerCase().trim();

        for (Medicamento medicamento : medicamentos) {
            if (medicamento.getNomeMedicamento().toLowerCase().contains(filtro)) {
                resultado.add(medicamento);
            }
        }

        return resultado;
    }
}