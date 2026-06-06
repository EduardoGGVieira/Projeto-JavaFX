package elderia.com.elderia;

import java.io.*;
import java.util.ArrayList;
import java.util.List;




// NEM PRECISO DIZER Q FOI CHAT Q FEZ SAMERDA AQ, É SÓ PARA GERAR O ARQUIVO COM OS CADASTRADOS, MAS ACHO Q TA ERRADO


 class UsuarioRepository {
    private static final String FILE_NAME = "usuarios_elderia.dat";

    public static List<Usuario> listarTodos() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return new ArrayList<Usuario>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Usuario>) ois.readObject();
        } catch (Exception e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            return new ArrayList<Usuario>();
        }
    }

    public static void salvarTodos(List<Usuario> lista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            System.err.println("Erro ao salvar: " + e.getMessage());
        }
    }
}