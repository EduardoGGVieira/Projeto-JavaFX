package elderia.com.elderia;

import java.io.Serializable;
import java.util.List;



// TODOS TEM QUE EXTENDES DE USUARIO?
public class Admin implements Serializable {
    private static final long serialVersionUID = 1L;

    private int idAdmin;
    private String nome;
    private String email;
    private String telefone;

    public Admin(int idAdmin, String nome, String email, String telefone) {
        this.idAdmin = idAdmin;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }


    public boolean deletarIdoso(int idIdoso, List<Idoso> listaIdosos) {
        if (listaIdosos == null || listaIdosos.isEmpty()) {
            return false;
        }
        return listaIdosos.removeIf(idoso -> idoso.getIdIdoso() == idIdoso);
    }


    // MÉTODO 2: Remove um Profissional da lista do sistema baseado no ID

    // nao funfa pq list Profissional n existe nem idProfissionall

//    public boolean deletarProfissional(int idProfissional, List<Profissional> listaProfissionais) {
//        if (listaProfissionais == null || listaProfissionais.isEmpty()) {
//            return false;
//        }
//        // Utiliza expressão lambda para remover o profissional correspondente
//        return listaProfissionais.removeIf(prof -> prof.getIdProfissional() == idProfissional);
//    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
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

}
