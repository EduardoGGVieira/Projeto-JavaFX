package elderia.com.elderia;

import java.time.LocalDateTime;

public class Consulta {
    private int idConsulta;
    private int idIdoso;
    private int idProfissional;
    private LocalDateTime dataHora;
    private String status;
    private String resumoAtendimento;



    public Consulta(int idConsulta, int idIdoso, int idProfissional, LocalDateTime dataHora, String status) {
        this.idConsulta = idConsulta;
        this.idIdoso = idIdoso;
        this.idProfissional = idProfissional;
        this.dataHora = dataHora;
        this.status = status;
        this.resumoAtendimento = "";
    }

    // MÉTODO 1: Verifica se a consulta foi marcada para uma data/hora válida no futuro

    public boolean validarDataFutura() {
        if (this.dataHora == null) return false;
        return this.dataHora.isAfter(LocalDateTime.now());
    }


    // MÉTODO 2: Conclui o atendimento inserindo a ficha de resumo clínica

    public void finalizarConsulta(String resumo) {
        if (resumo == null || resumo.trim().isEmpty()) {
            throw new IllegalArgumentException("O resumo clínico do atendimento não pode estar vazio.");
        }
        this.resumoAtendimento = resumo;
        this.status = "realizada";
    }






    

    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public int getIdIdoso() {
        return idIdoso;
    }

    public void setIdIdoso(int idIdoso) {
        this.idIdoso = idIdoso;
    }

    public int getIdProfissional() {
        return idProfissional;
    }

    public void setIdProfissional(int idProfissional) {
        this.idProfissional = idProfissional;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResumoAtendimento() {
        return resumoAtendimento;
    }

    public void setResumoAtendimento(String resumoAtendimento) {
        this.resumoAtendimento = resumoAtendimento;
    }
}
