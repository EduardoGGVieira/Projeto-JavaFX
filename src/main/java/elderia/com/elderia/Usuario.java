package elderia.com.elderia;

import java.io.Serializable; // Essa bomba o chat q mandou

public class Usuario implements Serializable { // Essa bomba o chat q mandou
    private static final long serialVersionUID = 1L; // Essa bomba o chat q mandou

    private int idUsuario;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private String tipoUsuario;


    public Usuario(int idUsuario, String nome, String cpf, String email, String telefone, String tipoUsuario) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.tipoUsuario = tipoUsuario;
    }

    // MÉTODO 1: Validação simples se o formato do e-mail possui '@' e '.'

    public boolean validarEmail() {
        if (this.email == null || this.email.isEmpty()) {
            return false;
        }
        return this.email.contains("@") && this.email.contains(".");
    }

    // MÉTODO 2: Retorna as iniciais do nome em letras maiúsculas (Ex: "A. F.")

    public String obterIniciaisNome() {
        if (this.nome == null || this.nome.trim().isEmpty()) {
            return "??";
        }
        String[] partes = this.nome.trim().split("\\s+");
        StringBuilder iniciais = new StringBuilder();
        for (int i = 0; i < Math.min(partes.length, 2); i++) {
            if (!partes[i].isEmpty()) {
                iniciais.append(partes[i].substring(0, 1).toUpperCase()).append(". ");
            }
        }
        return iniciais.toString().trim();
    }



    // GETTERS E SETTERS ATUALIZADOS


    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public String toString() {
        return this.nome + " (CPF: " + this.cpf + ")";
    }
}