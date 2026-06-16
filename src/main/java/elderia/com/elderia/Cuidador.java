package elderia.com.elderia;

import java.io.Serializable;

public class Cuidador implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nomeCuidador;
    private String idosoResponsavel;
    private String cpfCuidador;


    // Criar isso em todas as classe que forem ser usadas o Seriazable para n dar merda
    public Cuidador() {
    }

    public Cuidador(String nomeCuidador, String idosoResponsavel, String cpfCuidador) {
        this.nomeCuidador = nomeCuidador;
        this.idosoResponsavel = idosoResponsavel;
        this.cpfCuidador = cpfCuidador;
    }

    public String getNome() {
        return nomeCuidador;
    }

    public void setNome(String nomeCuidador) {
        this.nomeCuidador = nomeCuidador;
    }

    public String getIdosoResponsavel() {
        return idosoResponsavel;
    }

    public void setIdosoResponsavel(String idosoResponsavel) {
        this.idosoResponsavel = idosoResponsavel;
    }

    public String getCpf() {
        return cpfCuidador;
    }

    public void setCpf(String cpfCuidador) {
        this.cpfCuidador = cpfCuidador;
    }

}