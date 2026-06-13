package elderia.com.elderia;

import java.io.Serializable;
import java.time.LocalDate;

public class ValidacaoCertificado implements Serializable {
    private static final long serialVersionUID = 1L;

    private int idValidacao;
    private int idCertificado;
    private String status;
    private LocalDate dataValidacao;
    private String responsavel;
    private String observacao;

    public ValidacaoCertificado(int idValidacao, int idCertificado, String status,
                                LocalDate dataValidacao, String responsavel, String observacao) {
        this.idValidacao = idValidacao;
        this.idCertificado = idCertificado;
        this.status = status;
        this.dataValidacao = dataValidacao;
        this.responsavel = responsavel;
        this.observacao = observacao;
    }

    public int getIdValidacao() {
        return idValidacao;
    }

    public void setIdValidacao(int idValidacao) {
        this.idValidacao = idValidacao;
    }

    public int getIdCertificado() {
        return idCertificado;
    }

    public void setIdCertificado(int idCertificado) {
        this.idCertificado = idCertificado;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDataValidacao() {
        return dataValidacao;
    }

    public void setDataValidacao(LocalDate dataValidacao) {
        this.dataValidacao = dataValidacao;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}