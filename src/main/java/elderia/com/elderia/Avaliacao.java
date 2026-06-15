package elderia.com.elderia;

import java.io.Serializable;

public class Avaliacao implements Serializable {
    private int idAvaliacao;
    private int idConsulta;
    private int nota;
    private String comentario;
    private String statusModeracao;

    public Avaliacao(int idAvaliacao, int idConsulta, int nota, String comentario) {
        this.idAvaliacao = idAvaliacao;
        this.idConsulta = idConsulta;
        this.nota = nota;
        this.comentario = comentario;
        this.statusModeracao = "pendente";
    }

    // isso aq é full cha, não entendi a parte de Avaliaçao

    // Retorna a nota convertida visualmente em caracteres de estrelas
    public String obterEstrelasVisuais() {
        StringBuilder estrelas = new StringBuilder();
        for (int i = 0; i < this.nota; i++) {
            estrelas.append("⭐");
        }
        return estrelas.toString();
    }

    // MÉTODO 2: Filtro de segurança contra palavras ofensivas (Moderação Automática)

    public boolean verificarConteudoInadequado() {
        if (this.comentario == null || this.comentario.isEmpty()) {
            return false;
        }
        String texto = this.comentario.toLowerCase();
        // Exemplo de verificação simples de strings inadequadas para o sistema
        return texto.contains("incompetente") || texto.contains("falso") || texto.contains("burro");
    }

    // Getters e Setters com validação de consistência no Setter da nota
    public void setIdAvaliacao(int idAvaliacao) { this.idAvaliacao = idAvaliacao; }
    public int getIdAvaliacao() { return idAvaliacao; }

    public void setIdConsulta(int idConsulta) { this.idConsulta = idConsulta; }
    public int getIdConsulta() { return idConsulta; }

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
