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

    // Verifica se a consulta foi marcada para uma data/hora válida no futuro
    public boolean validarDataFutura() {
        if (this.dataHora == null) {
            return false;
        } else {
            return this.dataHora.isAfter(LocalDateTime.now());
        }
    }

    // Conclui o atendimento inserindo a ficha de resumo clínica
    public void finalizarConsulta(String resumo) {
        if (resumo == null || resumo.trim().isEmpty()) {
            throw new IllegalArgumentException("O resumo clínico do atendimento não pode estar váio.");
        } else {
            this.resumoAtendimento = resumo;
            this.status = "realizada";
        }
    }

    // Setters e getters
    public void setIdConsulta(int idConsulta) { this.idConsulta = idConsulta; }
    public int getIdConsulta() { return idConsulta; }

    public void setIdIdoso(int idIdoso) { this.idIdoso = idIdoso; }
    public int getIdIdoso() { return idIdoso; }

    public void setIdProfissional(int idProfissional) { this.idProfissional = idProfissional; }
    public int getIdProfissional() { return idProfissional; }

    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }
    public LocalDateTime getDataHora() { return dataHora; }

    public void setStatus(String status) { this.status = status; }
    public String getStatus() { return status; }

    public void setResumoAtendimento(String resumoAtendimento) { this.resumoAtendimento = resumoAtendimento; }
    public String getResumoAtendimento() { return resumoAtendimento; }
}
