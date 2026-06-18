package elderia.com.elderia;

import javafx.scene.control.TextField;

import java.io.Serializable;

// NOVO COMENTÁRIO: Classe de modelo Prontuario que representa o histórico clínico, implementando Serializable para persistência binária
public class Prontuario implements Serializable {
    private static final long serialVersionUID = 1L;

    private int idProntuario;
    private String nomePaciente;
    private String dataRegistro;
    private String alergias;
    private String medicamentos;
    private String observacoes;

    // AUDITORIA
    public String getTxtNovoTelefone() {
        return txtNovoTelefone;
    }
    // AUDITORIA
    public void setTxtNovoTelefone(String txtNovoTelefone) {
        this.txtNovoTelefone = txtNovoTelefone;
    }

    // AUDITORIA
    private String txtNovoTelefone; // INFO Q VAI SER ADD

    // Construtor completo                 // AUDITORIA
    public Prontuario(int idProntuario, String txtNovoTelefone ,String nomePaciente, String dataRegistro, String alergias, String medicamentos, String observacoes) {

        this.txtNovoTelefone =  txtNovoTelefone; // AUTORIA
        this.idProntuario = idProntuario;
        this.nomePaciente = nomePaciente;
        this.dataRegistro = dataRegistro;
        this.alergias = alergias;
        this.medicamentos = medicamentos;
        this.observacoes = observacoes;
    }

    // Métodos Getters e Setters para o encapsulamento seguro das propriedades
    public int getIdProntuario() { return idProntuario; }
    public void setIdProntuario(int idProntuario) { this.idProntuario = idProntuario; }

    public String getNomePaciente() { return nomePaciente; }
    public void setNomePaciente(String nomePaciente) { this.nomePaciente = nomePaciente; }

    public String getDataRegistro() { return dataRegistro; }
    public void setDataRegistro(String dataRegistro) { this.dataRegistro = dataRegistro; }

    public String getAlergias() { return alergias; }
    public void setAlergias(String alergias) { this.alergias = alergias; }

    public String getMedicamentos() { return medicamentos; }
    public void setMedicamentos(String medicamentos) { this.medicamentos = medicamentos; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
}