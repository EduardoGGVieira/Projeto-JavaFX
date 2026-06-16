package elderia.com.elderia;

import java.io.Serializable;

public class Medicamento implements Serializable {
    private static final long serialVersionUID = 1L;

    private int idMedicamento;
    private String nomeMedicamento;
    private String dosagem;
    private String horario;
    private String observacao;

    public Medicamento() {
    }

    public Medicamento(int idMedicamento, String nomeMedicamento, String dosagem , String horario,
                       String observacao) {
        this.idMedicamento = idMedicamento;
        this.nomeMedicamento = nomeMedicamento;
        this.dosagem = dosagem;
        this.horario = horario;
        this.observacao = observacao;
    }


    public String getNomeMedicamento() {
        return nomeMedicamento;
    }

    public void setnomeMedicamento(String nomeMedicamento) {
        this.nomeMedicamento = nomeMedicamento;
    }

    public String getDosagem() {
        return dosagem;
    }

    public void setDosagem(String dosagem) {
        this.dosagem = dosagem;
    }

    public int getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(int idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}