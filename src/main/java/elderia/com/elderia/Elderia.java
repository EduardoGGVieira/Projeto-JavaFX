package elderia.com.elderia;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.List;


public class Elderia extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("ELDERIA");

        Label lbl1 = new Label("ELDERIA");
        lbl1.setFont (new Font("Arial",30));
        lbl1.setAlignment(Pos.TOP_CENTER);
        lbl1.setStyle("-fx-background-color: rgb(0, 102, 102);");

        VBox box1 = new VBox();
        box1.setSpacing(20);
        box1.setAlignment(Pos.CENTER);
        box1.getChildren().add(lbl1);

        // monitor que mostrou isso
        box1.setStyle("-fx-background-color: rgb(0, 153, 153);");

        // botão de ir para idoso
        Label lbl2 = new Label("Area idoso");
        lbl2.setFont (new Font("Arial",20));
        lbl2.setAlignment(Pos.TOP_CENTER);

        VBox box2 = new VBox();
        box2.setSpacing(20);
        box2.setAlignment(Pos.CENTER);
        box2.getChildren().add(lbl2);

        // botão de ir para profissao
        Label lbl3 = new Label("Area Profissional");
        lbl3.setFont (new Font("Arial",20));
        lbl3.setAlignment(Pos.TOP_CENTER);

        VBox box3 = new VBox();
        box3.setSpacing(20);
        box3.setAlignment(Pos.CENTER);
        box3.getChildren().add(lbl3);

        // area de cadastro
        Label lbl4 = new Label("Cadastro");
        lbl4.setFont (new Font("Arial",50));
        lbl4.setAlignment(Pos.TOP_CENTER);

        VBox box4 = new VBox();
        box4.setSpacing(20);
        box4.setAlignment(Pos.TOP_CENTER);
        box4.getChildren().add(lbl4);

        // area de avaliacao
        Label lblAvaliacao = new Label("Avaliação");
        lblAvaliacao.setFont(new Font("Arial", 50));
        lblAvaliacao.setAlignment(Pos.TOP_CENTER);

        VBox boxAvaliacao = new VBox();
        boxAvaliacao.setSpacing(20);
        boxAvaliacao.setAlignment((Pos.TOP_CENTER));
        boxAvaliacao.getChildren().add(lblAvaliacao);

        // area inicial
        Scene scene = new Scene(box1, 900,600);

        // essa cena vamos usar para printar os dados do idoso
        Scene scene1 = new Scene(box2, 500,400);

        // essa cena vamos usar pra printar os dados do profissional
        Scene scene2 = new Scene(box3, 500,400);

        // parte de cadastro
        Scene scene3 = new Scene(box4, 900,900);

        // parte de avaliacao
        Scene sceneAvaliacao = new Scene(boxAvaliacao, 900, 600);

        // input de busca para profissional
        Label lblInput = new Label("Busque aqui seu profissional:");
        box3.getChildren().add(lblInput);
        TextField txtEspecializacao = new TextField();
        txtEspecializacao.setPromptText("Ex: Geriatra"); // tipo o placeholder
        txtEspecializacao.setMaxWidth(200); // tamanho do imput
        box3.getChildren().add(txtEspecializacao);


        // inputs do cadastro - todos os inputs
        // caso queira arrumar tem que usar GridPane ao invés de HBox

        // -- nome --
        Label lblInputNome = new Label("Nome:");
        TextField txtNome = new TextField();
        txtNome.setPromptText("Ex: Eduardo Guilherme");
        txtNome.setMaxWidth(200);
        // css básico
        HBox linhaNome = new HBox();
        linhaNome.setSpacing(10); // um little espaço entre o input e o nome
        linhaNome.setAlignment(Pos.CENTER);
        linhaNome.getChildren().addAll(lblInputNome, txtNome);


        box4.getChildren().add(linhaNome);

        // -- cpf --
        Label lblInputCPF = new Label("CPF:");
        TextField txtCPF = new TextField();
        txtCPF.setPromptText("Ex: 123.269.789-35"); // tipo o placeholder
        txtCPF.setMaxWidth(200); // tamanho do input
        // css básico
        HBox linhaCPF = new HBox();
        linhaCPF.setSpacing(10); // um little espaço entre o input e o nome
        linhaCPF.setAlignment(Pos.CENTER);
        linhaCPF.getChildren().addAll(lblInputCPF, txtCPF);

        box4.getChildren().add(linhaCPF);

        // -- data de nascimento --
        Label lblInputDataNascimento = new Label("Data Nascimento:");
        TextField txtDataNascimento = new TextField();
        txtDataNascimento.setPromptText("Ex: 11/11/2001"); // tipo o placeholder
        txtDataNascimento.setMaxWidth(200); // tamanho do input
        // css básico
        HBox linhaDataNasc = new HBox();
        linhaDataNasc.setSpacing(10); // um little espaço entre o input e o nome
        linhaDataNasc.setAlignment(Pos.CENTER);
        linhaDataNasc.getChildren().addAll(lblInputDataNascimento, txtDataNascimento);

        box4.getChildren().add(linhaDataNasc);

        // -- e-mail --
        Label lblInputEmail = new Label("Email:");
        TextField txtEmail = new TextField();
        txtEmail.setPromptText("Ex: eduardoggv@gmail.com"); // tipo o placeholder
        txtEmail.setMaxWidth(200); // tamanho do input
        // css básico
        HBox linhaEmail = new HBox();
        linhaEmail.setSpacing(10); // um little espaço entre o input e o nome
        linhaEmail.setAlignment(Pos.CENTER);
        linhaEmail.getChildren().addAll(lblInputEmail, txtEmail);

        box4.getChildren().add(linhaEmail);

        // -- senha --
        Label lblInputSenha = new Label("Senha:");
        TextField txtSenha = new TextField();
        txtSenha.setPromptText("Ex: POOMelhorMateria"); // tipo o placeholder
        txtSenha.setMaxWidth(200); // tamanho do input
        // css básico
        HBox linhaSenha = new HBox();
        linhaSenha.setSpacing(10); // um little espaço entre o input e o nome
        linhaSenha.setAlignment(Pos.CENTER);
        linhaSenha.getChildren().addAll(lblInputSenha, txtSenha);

        box4.getChildren().add(linhaSenha);

        // ObjectOutputStream = saída
        // ObjectInputStream = entrada lê os dados

        // botão de salvar os dados
        // fiz metade, o resto da lógica não funcionou
        Button salvardados = new Button();
        salvardados.setText("Salvar seus dados");
        salvardados.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    String nome = txtNome.getText().trim();
                    String cpf = txtCPF.getText().trim();
                    String email = txtEmail.getText().trim();
                    String senha = txtSenha.getText().trim();
                    String dataNasc = txtDataNascimento.getText().trim(); // pode mapear no model depois

                    // não pode deixar nada em branco
                    if (nome.isEmpty() || cpf.isEmpty() || email.isEmpty()) {
                        throw new IllegalArgumentException("NANANINANÃO, faltou algo ali amigão");
                    }

                    List<Usuario> listaAtual = UsuarioRepository.listarTodos();
                    int novoId = listaAtual.size() + 1;
                    Usuario novoUsuario = new Usuario(novoId, nome, cpf, email, dataNasc, "idoso");
                    listaAtual.add(novoUsuario);
                    UsuarioRepository.salvarTodos(listaAtual);

                    // feedback visual e no console
                    System.out.println("\n=== DADOS SALVOS COM SUCESSO NO ARQUIVO .DAT ===");
                    System.out.println("Nome: " + nome + " | CPF: " + cpf + " | Total cadastrados: " + listaAtual.size());
                    
                    // depois de salvar tudo, limpa os inputs
                    txtNome.clear();
                    txtCPF.clear();
                    txtEmail.clear();
                    txtSenha.clear();
                    txtDataNascimento.clear();

                    // volta para a tela inicial automaticamente depois de salvar
                    stage.setScene(scene);

                } catch (IllegalArgumentException y) {
                    System.err.println("Erro de Validação: " + y.getMessage());
                } catch (Exception y) {
                    System.err.println("Erro inesperado do sistema: " + y.getMessage());
                }
            }
        });

        box4.getChildren().add(salvardados);

        // botões de cadastrar
        Button cadastrar = new Button();
        cadastrar.setText("Cadastre-se");
        cadastrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(scene3);
            }
        });
        box1.getChildren().add(cadastrar);

        // botão de voltar do cadastro
        Button voltarcadastro = new Button();
        voltarcadastro.setText("Voltar");
        voltarcadastro.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(scene);
            }
        });
        box4.getChildren().add(voltarcadastro);

        // Exemplo de um botão que printa alguma coisa
//        Button btn1 = new Button();
//        btn1.setText("PRINT DE MENSAGEM");
//        btn1.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("BLA BLA BLA|");
//            }
//        });
//        box1.getChildren().add(btn1);

        // botão de ir para a area do idoso
        Button btn3 = new Button();
        btn3.setText("Aba de idoso");
        btn3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(scene1);
            }

        });
        box1.getChildren().add(btn3);

        // botão de voltar da area do idoso
        Button btn4 = new Button();
        btn4.setText("Voltar");
        btn4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(scene);
            }
        });
        box2.getChildren().add(btn4);

        // botão para ir para area de profissional
        Button btn5 = new Button();
        btn5.setText("Profissional");
        btn5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(scene2);
            }

        });
        box1.getChildren().add(btn5);

        // botão de voltar da area do profissional
        Button btn6 = new Button();
        btn6.setText("Voltar");
        btn6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(scene);
            }
        });
        box3.getChildren().add(btn6);

        // botões de avaliar
        Button btnAvaliar = new Button();
        btnAvaliar.setText("Avaliar");
        btnAvaliar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(sceneAvaliacao);
            }
        });
        box1.getChildren().add(btnAvaliar);

        Button close = new Button("Fechar");
        close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });
        box1.getChildren().add(close);

        stage.setScene(scene);
        stage.show();
    }
    public static void main (String[] args){
        launch();
    }
}