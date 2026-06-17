package elderia.com.elderia;

import java.io.Serializable;

public class Convenio implements Serializable {
    private static final long serialVersionUID = 1L;

    private int idConvenio;
    private String nome;
    private String telefone;
    private String email;

    public Convenio(int idConvenio, String nome, String telefone, String email) {
        this.idConvenio = idConvenio;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public int getIdConvenio() {
        return idConvenio;
    }

    public void setIdConvenio(int idConvenio) {
        this.idConvenio = idConvenio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}