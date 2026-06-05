package elderia.com.elderia;

public class Usuario {
    private int idUsuario;
    private String nome;
    // QUAL TIPO?
    private int cpf;
    private String email;
    private String telefone;
    private String tipoUsuario; // 'idoso', 'profissional' ou 'admin'



    // CPF É INT OU STRING?
    public Usuario(int idUsuario, String nome, int cpf, String email, String telefone, String tipoUsuario) {
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


    // GETS E SET A PARTIR DO GENERATE
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
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
}
