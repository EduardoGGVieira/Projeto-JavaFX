package elderia.com.elderia;

import java.io.Serializable;

public class Avaliacao implements Serializable {
    private int idAvaliacao;
    private int idProfissional;
    private int nota;
    private String comentario;
    private String statusModeracao;

    public Avaliacao(int idAvaliacao, int idProfissional, int nota, String comentario) {
        this.idAvaliacao = idAvaliacao;
        this.idProfissional = idProfissional;
        this.nota = nota;
        this.comentario = comentario;
        this.statusModeracao = "pendente";
    }

    // Getters e Setters com validação de consistência no Setter da nota
    public void setIdAvaliacao(int idAvaliacao) { this.idAvaliacao = idAvaliacao; }
    public int getIdAvaliacao() { return idAvaliacao; }

    public void setIdProfissional(int idProfissional) { this.idProfissional = idProfissional; }
    public int getIdProfissional() { return idProfissional; }

    public void setComentario(String comentario) { this.comentario = comentario; }
    public String getComentario() { return comentario; }

    public void setStatusModeracao(String statusModeracao) { this.statusModeracao = statusModeracao; }
    public String getStatusModeracao() { return statusModeracao; }

    public void setNota(int nota) {
        if (nota < 1 || nota > 5) {
            throw new IllegalArgumentException("A nota da avaliação deve ser obrigatoriamente entre 1 e 5 estrelas.");
        }
        this.nota = nota;
    }
    public int getNota() { return nota; }
}
