package elderia.com.elderia;

import java.time.LocalDate;


// TODOS TEM QUE EXTENDES DE USUARIO?
public class Proffisional {
    private int idProfissional;
    private String registroProfissional; // CRM ou COREN
    private String especialidade;
    private String localizacao;
    private String biografia;
    private LocalDate dataEmissaoCertificado;


    public Profissional(int idProfissional, String registroProfissional, String specialty,
                        String localizacao, String biografia, LocalDate dataEmissaoCertificado) {
        this.idProfissional = idProfissional;
        this.registroProfissional = registroProfissional;
        this.especialidade = specialty;
        this.localizacao = localizacao;
        this.biografia = biografia;
        this.dataEmissaoCertificado = dataEmissaoCertificado;
    }



    // MÉTODO 1: Verifica o tempo de expedição do documento (Ex: Alerta se > 10 anos)
    public boolean necessitaRecertificacao() {
        if (this.dataEmissaoCertificado == null) {
            return true;
        }
        int anosExpedicao = Period.between(this.dataEmissaoCertificado, LocalDate.now()).getYears();
        return anosExpedicao >= 10; // Exige revisão caso o documento tenha mais de uma década
    }


    // MÉTODO 2: Gera uma apresentação comercial curta para os cards do JavaFX
    public String obterCartaoApresentacao() {
        return String.format("Dr(a). %s | Registro: %s\nEspecialidade: %s\nAtendimento em: %s",
                this.especialidade,
                this.registroProfissional,
                this.especialidade,
                this.localizacao.isEmpty() ? "Não informada" : this.localizacao);
    }




    public String getRegistroProfissional() {
        return registroProfissional;
    }

    public void setRegistroProfissional(String registroProfissional) {
        this.registroProfissional = registroProfissional;
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

    public LocalDate getDataEmissaoCertificado() {
        return dataEmissaoCertificado;
    }

    public void setDataEmissaoCertificado(LocalDate dataEmissaoCertificado) {
        this.dataEmissaoCertificado = dataEmissaoCertificado;
    }
}
