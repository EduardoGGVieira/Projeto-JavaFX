package elderia.com.elderia;

import java.io.Serializable;

public class Consulta implements Serializable {
    private static final long serialVersionUID = 1L;

    private int idConsulta;
    private int idUsuario;
    private int idProfissional;
    private String dataHora;
    private String status;

    public Consulta(int idConsulta, int idUsuario, int idProfissional, String dataHora, String status) {
        this.idConsulta = idConsulta;
        this.idUsuario = idUsuario;
        this.idProfissional = idProfissional;
        this.dataHora = dataHora;
        this.status = "agendada";
    }

    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdProfissional() {
        return idProfissional;
    }

    public void setIdProfissional(int idProfissional) {
        this.idProfissional = idProfissional;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}