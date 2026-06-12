package elderia.com.elderia;

import java.time.LocalDate;
import java.time.Period;

public class Profissional {
    private int idProfissional;
    private String nomeProfissional;
    private String registroProfissional; // CRM ou COREN
    private String especialidade;
    private String localizacao;
    private String biografia;


    // Criar isso em todas as classe que forem ser usadas o Seriazable para n dar merda
    public Profissional() {
    }

    public Profissional(int idProfissional, String nomeProfissional, String registroProfissional, String specialty,
                        String localizacao, String biografia) {
        this.idProfissional = idProfissional;
        this.nomeProfissional = nomeProfissional;
        this.registroProfissional = registroProfissional;
        this.especialidade = specialty;
        this.localizacao = localizacao;
        this.biografia = biografia;
    }


    public String obterIniciaisNomedoProfissional() {
        if (this.nomeProfissional == null || this.nomeProfissional.trim().isEmpty()) {
            return "??";
        }
        String[] partes = this.nomeProfissional.trim().split("\\s+");
        StringBuilder iniciais = new StringBuilder();
        for (int i = 0; i < Math.min(partes.length, 2); i++) {
            if (!partes[i].isEmpty()) {
                iniciais.append(partes[i].substring(0, 1).toUpperCase()).append(". ");
            }
        }
        return iniciais.toString().trim();
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
