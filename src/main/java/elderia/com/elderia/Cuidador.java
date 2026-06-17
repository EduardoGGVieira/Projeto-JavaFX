package elderia.com.elderia;

import java.io.Serializable;

public class Cuidador implements Serializable {
    private static final long serialVersionUID = 1L;

    private int idCuidador;
    private String nomeCuidador;
    private String idosoResponsavel;
    private String cpfCuidador;


    // Criar isso em todas as classe que forem ser usadas o Seriazable para n dar merda
    public Cuidador() {
    }

    public Cuidador(int idCuidador, String nomeCuidador, String idosoResponsavel, String cpfCuidador) {
        this.idCuidador = idCuidador;
        this.nomeCuidador = nomeCuidador;
        this.idosoResponsavel = idosoResponsavel;
        this.cpfCuidador = cpfCuidador;
    }

    public int getIdCuidador() {
        return idCuidador;
    }

    public void setIdCuidador(int idCuidador) {
        this.idCuidador = idCuidador;
    }

    public String getNomeCuidador() {
        return nomeCuidador;
    }

    public void setNomeCuidador(String nomeCuidador) {
        this.nomeCuidador = nomeCuidador;
    }

    public String getIdosoResponsavel() {
        return idosoResponsavel;
    }

    public void setIdosoResponsavel(String idosoResponsavel) {
        this.idosoResponsavel = idosoResponsavel;
    }

    public String getCpfCuidador() {
        return cpfCuidador;
    }

    public void setCpfCuidador(String cpfCuidador) {
        this.cpfCuidador = cpfCuidador;
    }

}