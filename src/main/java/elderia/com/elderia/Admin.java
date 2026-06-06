package elderia.com.elderia;

import java.util.List;



// TODOS TEM QUE EXTENDES DE USUARIO?
public class Admin {
    private int idAdmin;
    private String nome;
    private String email;
    private int totalDenunciasTratadas;


    public Admin(int idAdmin, String nome, String email) {
        this.idAdmin = idAdmin;
        this.nome = nome;
        this.email = email;

        // isso n existe ou existe?
        this.totalDenunciasTratadas = 0;
    }


    // Ta certo sabomba? ( deletar idoso e profissa)

    // MÉTODO 1: Remove um idoso da lista do sistema baseado no ID

    public boolean deletarIdoso(int idIdoso, List<Idoso> listaIdosos) {
        if (listaIdosos == null || listaIdosos.isEmpty()) {
            return false;
        }
        // Utiliza expressão lambda para remover o idoso correspondente (ON DELETE CASCADE lógico)
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

    public int getTotalDenunciasTratadas() {
        return totalDenunciasTratadas;
    }

    public void setTotalDenunciasTratadas(int totalDenunciasTratadas) {
        this.totalDenunciasTratadas = totalDenunciasTratadas;
    }
}
