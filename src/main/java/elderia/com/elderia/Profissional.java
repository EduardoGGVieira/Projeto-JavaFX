package elderia.com.elderia;

import java.io.Serializable;

public class Profissional implements Serializable {
    private static final long serialVersionUID = 1L;

    private int idProfissional;
    private String nomeProfissional;
    private String registroProfissional; // CRM ou COREN
    private String especialidade;
    private String localizacao;
    private String biografia;


    // Criar isso em todas as classe que forem ser usadas o Seriazable para n dar merda
    public Profissional() {
    }

    public Profissional(int idProfissional, String nomeProfissional, String registroProfissional, String especialidade,
                        String localizacao, String biografia) {
        this.idProfissional = idProfissional;
        this.nomeProfissional = nomeProfissional;
        this.registroProfissional = registroProfissional;
        this.especialidade = especialidade;
        this.localizacao = localizacao;
        this.biografia = biografia;
    }

    public String getRegistroProfissional() {
        return registroProfissional;
    }

    public void setRegistroProfissional(String registroProfissional) {
        this.registroProfissional = registroProfissional;
    }

    public String getNomeProfissional() {
        return nomeProfissional;
    }

    public void setNomeProfissional(String nomeProfissional) {
        this.nomeProfissional = nomeProfissional;
    }

    public int getIdProfissional() {
        return idProfissional;
    }

    public void setIdProfissional(int idProfissional) {
        this.idProfissional = idProfissional;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }
}
