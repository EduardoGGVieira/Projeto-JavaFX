package elderia.com.elderia;

import java.io.Serializable;

public class Cuidador implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nome;
    private String idosoResponsavel;
    private String cpf;


    // Criar isso em todas as classe que forem ser usadas o Seriazable para n dar merda
    public Cuidador() {
    }

    public Cuidador(String nome, String idosoResponsavel, String cpf) {
        this.nome = nome;
        this.idosoResponsavel = idosoResponsavel;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdosoResponsavel() {
        return idosoResponsavel;
    }

    public void setIdosoResponsavel(String idosoResponsavel) {
        this.idosoResponsavel = idosoResponsavel;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}