package elderia.com.elderia;

import java.time.LocalDate;
import java.time.Period;

public class Certificado {
    private int idCertificado;
    private int idProfissional;
    private String titulo;
    private LocalDate dataEmissao;

    // Caminho local do ficheiro PDF tem que usar o local completo
    private String urlDocumento;



    public Certificado(int idCertificado, int idProfissional, String titulo, LocalDate dataEmissao, String urlDocumento) {
        this.idCertificado = idCertificado;
        this.idProfissional = idProfissional;
        this.titulo = titulo;
        this.dataEmissao = dataEmissao;
        this.urlDocumento = urlDocumento;
    }


    // MÉTODO 1: Valida se o documento anexado possui extensão .pdf

    public boolean validarExtensaoPdf() {
        if (this.urlDocumento == null || this.urlDocumento.isEmpty()) {
            return false;
        }
        return this.urlDocumento.toLowerCase().endsWith(".pdf");
    }


    // MÉTODO 2: Retorna o tempo total de emissão em anos

    public int obterAnosDesdeEmissao() {
        if (this.dataEmissao == null) {
            return 0;
        }
        return Period.between(this.dataEmissao, LocalDate.now()).getYears();
    }


    


    public int getIdProfissional() {
        return idProfissional;
    }

    public void setIdProfissional(int idProfissional) {
        this.idProfissional = idProfissional;
    }

    public int getIdCertificado() {
        return idCertificado;
    }

    public void setIdCertificado(int idCertificado) {
        this.idCertificado = idCertificado;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public String getUrlDocumento() {
        return urlDocumento;
    }

    public void setUrlDocumento(String urlDocumento) {
        this.urlDocumento = urlDocumento;
    }
}
