package elderia.com.elderia;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.List;

public class Elderia extends Application {

    // tabela que vai aparecer na tela de pessoas cadastradas
    private TableView<Usuario> tabelaUsuarios;

    // lista que fica conectada na tabela
    // quando muda essa lista, a tabela muda junto
    private ObservableList<Usuario> dadosTabela;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("ELDERIA");

        // ---------------- TELA INICIAL ----------------
        // essa e a primeira tela do sistema
        // atualizacao: mantive a ideia da tela inicial, mas organizei com subtitulo e separador

        Label lbl1 = new Label("ELDERIA");
        lbl1.setFont(new Font("Arial", 32));
        lbl1.setAlignment(Pos.CENTER);

        Label lblSubtitulo = new Label("Sistema de apoio para idosos e profissionais");
        lblSubtitulo.setFont(new Font("Arial", 14));
        lblSubtitulo.setAlignment(Pos.CENTER);

        VBox box1 = criarTelaBase();
        box1.getChildren().addAll(lbl1, lblSubtitulo, new Separator());

        // area inicial
        Scene scene = new Scene(box1, 900, 600);

        // ---------------- AREA IDOSO ----------------
        // botao de ir para idoso manda pra essa scene aqui
        // deixei simples pq a parte de idoso e do dudu

        Label lbl2 = new Label("Área Idoso");
        lbl2.setFont(new Font("Arial", 24));
        lbl2.setAlignment(Pos.CENTER);

        Label textoIdoso = new Label("Área destinada às funcionalidades do idoso.");
        textoIdoso.setFont(new Font("Arial", 14));

        VBox box2 = criarTelaBase();
        box2.getChildren().addAll(lbl2, textoIdoso, new Separator());

        // essa cena vamos usar para printar os dados do idoso
        Scene scene1 = new Scene(box2, 900, 600);

        // ---------------- AREA PROFISSIONAL ----------------
        // botao de ir para profissional manda pra essa scene aqui
        // mantive o campo de busca que ja existia, so organizei em uma linha

        Label lbl3 = new Label("Área Profissional");
        lbl3.setFont(new Font("Arial", 24));
        lbl3.setAlignment(Pos.CENTER);

        VBox boxNENHUMA = criarTelaBase();
        boxNENHUMA.getChildren().addAll(lbl3, new Separator());

        // input de busca para profissional
        Label lblInput = new Label("Busque aqui seu profissional:");

        TextField txtEspecializacao = new TextField();
        txtEspecializacao.setPromptText("Ex: Geriatra"); // tipo o placeholder
        txtEspecializacao.setMaxWidth(250); // tamanho do input

        // atualizacao: antes o label e o input eram adicionados direto no box3
        // agora fica em hbox so pra alinhar melhor
        HBox linhaBuscaProfissional = criarLinha();
        linhaBuscaProfissional.getChildren().addAll(lblInput, txtEspecializacao);
        boxNENHUMA.getChildren().add(linhaBuscaProfissional);

        // ---------------- CADASTRO ----------------
        // area de cadastro
        // atualizacao: deixei o cadastro organizado com gridpane
        // nao muda a logica de salvar usuario, so muda a organizacao visual

        Label lbl4 = new Label("Cadastro");
        lbl4.setFont(new Font("Arial", 28));
        lbl4.setAlignment(Pos.CENTER);

        Label lblCadastroInfo = new Label("Preencha os dados abaixo:");
        lblCadastroInfo.setFont(new Font("Arial", 14));

        VBox box4 = criarTelaBase();
        box4.getChildren().addAll(lbl4, lblCadastroInfo, new Separator());

        // parte de cadastro
        Scene scene3 = new Scene(box4, 900, 700);

        // inputs do cadastro - todos os inputs
        // antes cada campo era um hbox separado
        // agora fica num gridpane, tipo uma tabelinha: label de um lado e input do outro

        GridPane formularioCadastro = new GridPane();
        formularioCadastro.setHgap(10);
        formularioCadastro.setVgap(12);
        formularioCadastro.setAlignment(Pos.CENTER);

        // -- nome --
        Label lblInputNome = new Label("Nome:");

        TextField txtNome = new TextField();
        txtNome.setPromptText("Ex: Eduardo Guilherme");
        txtNome.setPrefWidth(250);

        // -- cpf --
        Label lblInputCPF = new Label("CPF:");

        TextField txtCPF = new TextField();
        txtCPF.setPromptText("Ex: 123.269.789-35");
        txtCPF.setPrefWidth(250);

        // -- data de nascimento --
        Label lblInputDataNascimento = new Label("Data Nascimento:");

        TextField txtDataNascimento = new TextField();
        txtDataNascimento.setPromptText("Ex: 11/11/2001");
        txtDataNascimento.setPrefWidth(250);

        // -- e-mail --
        Label lblInputEmail = new Label("Email:");

        TextField txtEmail = new TextField();
        txtEmail.setPromptText("Ex: eduardoggv@gmail.com");
        txtEmail.setPrefWidth(250);

        // -- senha --
        Label lblInputSenha = new Label("Senha:");

        TextField txtSenha = new TextField();
        txtSenha.setPromptText("Ex: POOMelhorMateria");
        txtSenha.setPrefWidth(250);

        // coloca cada label e cada input em uma linha do grid
        // coluna 0 = label
        // coluna 1 = campo
        formularioCadastro.add(lblInputNome, 0, 0);
        formularioCadastro.add(txtNome, 1, 0);

        formularioCadastro.add(lblInputCPF, 0, 1);
        formularioCadastro.add(txtCPF, 1, 1);

        formularioCadastro.add(lblInputDataNascimento, 0, 2);
        formularioCadastro.add(txtDataNascimento, 1, 2);

        formularioCadastro.add(lblInputEmail, 0, 3);
        formularioCadastro.add(txtEmail, 1, 3);

        formularioCadastro.add(lblInputSenha, 0, 4);
        formularioCadastro.add(txtSenha, 1, 4);

        box4.getChildren().add(formularioCadastro);

        // ---------------- MOSTRAR DADOS ----------------
        // essa tela mostra os usuarios salvos no arquivo .dat
        // obs: isso existia na branch que a gente tinha usado antes, entao mantive

        Label lbl5 = new Label("Dados dos Usuários");
        lbl5.setFont(new Font("Arial", 26));
        lbl5.setAlignment(Pos.CENTER);

        VBox box5 = criarTelaBase();
        box5.getChildren().addAll(lbl5, new Separator());

        // parte de mostrar os dados cadastrados
        Scene scene4 = new Scene(box5, 1000, 700);

        // ---------------- Cadastro do proffisional-----------

        //titulo da pagina/scene
        Label lblCadastroProfissa = new Label("Cadastro do Profissional");
        lblCadastroProfissa.setFont(new Font("Arial", 28));
        lblCadastroProfissa.setAlignment(Pos.CENTER);

        //subtitulo
        Label lblCadastroProfissaInformacoes = new Label("Preencha seus dados de profissional abaixo:");
        lblCadastroProfissaInformacoes.setFont(new Font("Arial", 14));

        VBox box3 = criarTelaBase();
        box3.getChildren().addAll(lblCadastroProfissa, lblCadastroProfissaInformacoes, new Separator());


        // essa cena vamos usar pro cadastro do profissional
        Scene scene2 = new Scene(box3, 900, 700);

        // formulário
        GridPane formularioProfissa = new GridPane();
        formularioProfissa.setHgap(10);
        formularioProfissa.setVgap(12);
        formularioProfissa.setAlignment(Pos.CENTER);

        // Nome
        Label lblNomeProfissa = new Label("Nome:");

        TextField txtNomeProfissa = new TextField();
        txtNomeProfissa.setPromptText("Ex: Dr. Nefario de Carvalhão");
        txtNomeProfissa.setPrefWidth(250);

        // CRM/COREN/registro do proffisional
        Label lblRegistroProfissa = new Label("CRM/COREN:");

        TextField txtRegistroProfissa = new TextField();
        txtRegistroProfissa.setPromptText("Ex: 12345");
        txtRegistroProfissa.setPrefWidth(250);

        // Especialidade do proffisional
        Label lblEspecialidadeProfissa = new Label("Especialidade:");

        TextField txtEspecialidadeProfissa = new TextField();
        txtEspecialidadeProfissa.setPromptText("Ex: Urologista");
        txtEspecialidadeProfissa.setPrefWidth(250);

        // Localização do proffisional
        Label lblLocalizacaoProfissa = new Label("Localização:");

        TextField txtLocalizacaoProfissa = new TextField();
        txtLocalizacaoProfissa.setPromptText("Ex: Where Judas lost his boots - PQP");
        txtLocalizacaoProfissa.setPrefWidth(250);

        // Biografia do Proffisional
        Label lblBiografiaProfissa = new Label("Biografia:");

        TextArea txtBiografiaProfissa = new TextArea();
        txtBiografiaProfissa.setPromptText("Descreva sua experiência profissional...");
        txtBiografiaProfissa.setPrefWidth(250);
        txtBiografiaProfissa.setPrefRowCount(4); //define quantas linhas vai aparecer antes de aparecer o scrollzinho.

        // Bota no gridpane que eu criei la em cima.
        // coluna 0 = label
        // coluna 1 = campo (Importante lembrar pra nao confundir igual o bobo aqui)
        formularioProfissa.add(lblNomeProfissa, 0, 0);
        formularioProfissa.add(txtNomeProfissa, 1, 0);

        formularioProfissa.add(lblRegistroProfissa, 0, 1);
        formularioProfissa.add(txtRegistroProfissa, 1, 1);

        formularioProfissa.add(lblEspecialidadeProfissa, 0, 2);
        formularioProfissa.add(txtEspecialidadeProfissa, 1, 2);

        formularioProfissa.add(lblLocalizacaoProfissa, 0, 3);
        formularioProfissa.add(txtLocalizacaoProfissa, 1, 3);

        formularioProfissa.add(lblBiografiaProfissa, 0, 4);
        formularioProfissa.add(txtBiografiaProfissa, 1, 4);

        box3.getChildren().add(formularioProfissa);

        // ---------------- AREA DE AVALIACAO ----------------
        // atualizacao importante da branch demo:
        // a demo tinha uma tela e um botao de avaliacao, entao coloquei de volta aqui
        // deixei simples igual as outras areas, sem mexer na logica de quem for fazer avaliacao

        Label lblAvaliacao = new Label("Avaliação");
        lblAvaliacao.setFont(new Font("Arial", 28));
        lblAvaliacao.setAlignment(Pos.CENTER);

        Label textoAvaliacao = new Label("Área destinada às funcionalidades de avaliação.");
        textoAvaliacao.setFont(new Font("Arial", 14));

        VBox boxAvaliacao = criarTelaBase();
        boxAvaliacao.getChildren().addAll(lblAvaliacao, textoAvaliacao, new Separator());

        // parte de avaliacao
        Scene sceneAvaliacao = new Scene(boxAvaliacao, 900, 600);

        // --------------------- TABELA DE PESSOAS CADASTRADAS --------------
        // cria a tabela que vai listar os usuarios cadastrados

        tabelaUsuarios = new TableView<>();

        // este aq e tipo eventelistner
        // ele fica esperando os dados da tabela mudar pra atualizar visualmente
        dadosTabela = FXCollections.observableArrayList();

        // conecta a lista na tabela
        tabelaUsuarios.setItems(dadosTabela);
        tabelaUsuarios.setPrefHeight(400);

        // criacao das colunas da tabela dos usuarios cadastrados

        TableColumn<Usuario, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
        colId.setPrefWidth(60);

        TableColumn<Usuario, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colNome.setPrefWidth(200);

        TableColumn<Usuario, String> colCpf = new TableColumn<>("CPF");
        colCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colCpf.setPrefWidth(130);

        TableColumn<Usuario, String> colEmail = new TableColumn<>("E-mail");
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colEmail.setPrefWidth(200);

        TableColumn<Usuario, String> colTelefone = new TableColumn<>("Data Nasc / Tel");
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        colTelefone.setPrefWidth(140);

        TableColumn<Usuario, String> colTipo = new TableColumn<>("Perfil");
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipoUsuario"));
        colTipo.setPrefWidth(100);

        // botao de deletar dentro da coluna
        TableColumn<Usuario, Void> colDeletar = new TableColumn<>("Ação");
        colDeletar.setPrefWidth(100);

        // tem que add o botao dentro da coluna
        colDeletar.setCellFactory(param -> new TableCell<Usuario, Void>() {
            private final Button btnDeletar = new Button("Deletar");

            {
                btnDeletar.setOnAction(event -> {
                    // pega o objeto usuario correspondente a linha onde o botao foi clicado
                    Usuario usuarioSelecionado = getTableView().getItems().get(getIndex());

                    if (usuarioSelecionado != null) {
                        try {
                            // tira da tabela, mas so a parte visual
                            dadosTabela.remove(usuarioSelecionado);

                            // apaga literalmente do arquivo .dat
                            List<Usuario> listaCompleta = UsuarioRepository.listarTodos();

                            // remove da lista do arquivo o usuario que possui o mesmo id
                            listaCompleta.removeIf(u -> u.getIdUsuario() == usuarioSelecionado.getIdUsuario());

                            // grava o trem de bytes atualizado de volta no arquivo permanente
                            UsuarioRepository.salvarTodos(listaCompleta);

                            System.out.println("Usuário " + usuarioSelecionado.getNome() + " Se despende alegremente!");

                        } catch (Exception e) {
                            System.err.println("Deu bostica, não foi o delete ;-; " + e.getMessage());
                        }
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                // se a linha estiver vazia, nao mostra o botao
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btnDeletar); // injeta o botao na celula
                    setAlignment(Pos.CENTER);
                }
            }
        });

        // pega tudo e add na tabela criada
        tabelaUsuarios.getColumns().addAll(colId, colNome, colCpf, colEmail, colTelefone, colTipo, colDeletar);
        box5.getChildren().add(tabelaUsuarios);

        // ObjectOutputStream = saida
        // ObjectInputStream = entrada, le os dados

        // ---------------- BOTOES DO CADASTRO ----------------
        // aqui ficam os botoes da tela de cadastro
        // atualizacao: antes os botoes eram adicionados um por um no box4
        // agora eles ficam na mesma linha

        // botao de salvar os dados
        Button salvardados = new Button("Salvar seus dados");
        salvardados.setPrefWidth(180);

        salvardados.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    String nome = txtNome.getText().trim();
                    String cpf = txtCPF.getText().trim();
                    String email = txtEmail.getText().trim();
                    String dataNasc = txtDataNascimento.getText().trim(); // pode mapear no model depois

                    // nao pode deixar nada em branco
                    // obs: mantive a mesma validacao que ja tinha antes
                    if (nome.isEmpty() || cpf.isEmpty() || email.isEmpty()) {
                        throw new IllegalArgumentException("NANANINANÃO, faltou algo ali amigão");
                    }

                    // pega a lista atual do arquivo
                    List<Usuario> listaAtual = UsuarioRepository.listarTodos();

                    // cria um id novo baseado no tamanho da lista
                    int novoId = listaAtual.size() + 1;

                    // cria um usuario novo
                    // obs: mantive dataNasc indo no lugar de telefone pq era assim que ja tava funcionando
                    Usuario novoUsuario = new Usuario(novoId, nome, cpf, email, dataNasc, "idoso");

                    // adiciona na lista
                    listaAtual.add(novoUsuario);

                    // salva tudo de volta no .dat
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

        Button mostrarDados = new Button("Pessoas Cadastradas");
        mostrarDados.setPrefWidth(180);

        mostrarDados.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // acha o trem de bytes do arquivo e atualiza a lista da tabela
                List<Usuario> listaDoArquivo = UsuarioRepository.listarTodos();
                dadosTabela.setAll(listaDoArquivo);

                stage.setScene(scene4);
            }
        });

        Button voltarcadastro = new Button("Voltar");
        voltarcadastro.setPrefWidth(180);

        voltarcadastro.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(scene);
            }
        });

        HBox botoesCadastro = criarLinha();
        botoesCadastro.getChildren().addAll(salvardados, mostrarDados, voltarcadastro);

        box4.getChildren().addAll(new Separator(), botoesCadastro);

        // ------ butao da tela do cadastro do profissional-----------
        Button btnCadastrarProfissa = new Button("Cadastrar-se");
        btnCadastrarProfissa.setPrefWidth(180);

        btnCadastrarProfissa.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    String nome = txtNomeProfissa.getText().trim();
                    String registro = txtRegistroProfissa.getText().trim();
                    String especialidade = txtEspecialidadeProfissa.getText().trim();
                    String localizacao = txtLocalizacaoProfissa.getText().trim();
                    String biografia = txtBiografiaProfissa.getText().trim();

                    // valida se nao ta faltando coisa
                    if (nome.isEmpty() || registro.isEmpty() || especialidade.isEmpty() || localizacao.isEmpty() || biografia.isEmpty()) {
                        throw new IllegalArgumentException(
                                "Calma ai paizão, ta faltando coisa nesse formulario.");
                    }
                    List<Profissional> listaAtual = ProfissionalRepository.listarTodos();

                    // bota um id novo
                    int novoId = listaAtual.size() + 1;

                    // cria o profissional cadastrado no formulario lindo
                    Profissional novoProfissional = new Profissional(novoId, nome, registro, especialidade, localizacao, biografia);

                    listaAtual.add(novoProfissional);

                    ProfissionalRepository.salvarTodos(listaAtual);

                    System.out.println("\n= Ta salvo pai :P =");

                    System.out.println("Nome: " + nome + " | CRM/COREN: " + registro + " | Total de profissionais cadastrados: " + listaAtual.size());

                    txtNomeProfissa.clear();
                    txtRegistroProfissa.clear();
                    txtEspecialidadeProfissa.clear();
                    txtLocalizacaoProfissa.clear();
                    txtBiografiaProfissa.clear();

                    stage.setScene(scene);

                } catch (IllegalArgumentException y) {
                    System.err.println("Deu Erro de Validação aqui: " + y.getMessage());
                } catch (Exception y) {
                    System.err.println("Erro inesperado do sistema: " + y.getMessage());
                }
            }
        });

        HBox boxBotaoProfissa = new HBox(btnCadastrarProfissa);
        boxBotaoProfissa.setAlignment(Pos.CENTER);

        box3.getChildren().add(boxBotaoProfissa);









        // ---------------- BOTOES TELA INICIAL ----------------
        // aqui ficam os botoes principais do menu inicial
        // atualizacao: mantive os botoes de cadastro, idoso, profissional e fechar
        // tambem mantive o botao avaliar da demo e o botao certificados da nossa parte

        // botao de cadastrar
        Button cadastrar = new Button("Cadastrar-se como Usuário.");
        cadastrar.setPrefWidth(220);

        cadastrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(scene3);
            }
        });

        // botao para ir para area de profissional
        Button btn3 = new Button("Cadastrar-se como Profissional");
        btn3.setPrefWidth(220);

        btn3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(scene2);
            }
        });

        // botao de ir para a area do idoso
        Button btn5 = new Button("Aba de idoso");
        btn5.setPrefWidth(220);

        btn5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(scene1);
            }
        });


        // botao de avaliar
        // isso veio da branch demo, entao nao pode sumir
        Button btnAvaliar = new Button("Avaliar");
        btnAvaliar.setPrefWidth(220);

        btnAvaliar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(sceneAvaliacao);
            }
        });

        // botao para ir para area de certificados
        // isso e a nossa parte nova
        Button btnCertificados = new Button("Certificados");
        btnCertificados.setPrefWidth(220);

        btnCertificados.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TelaCertificados telaCertificados = new TelaCertificados();
                stage.setScene(telaCertificados.criarCena(stage, scene));
            }
        });

        Button close = new Button("Fechar");
        close.setPrefWidth(220);

        close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });

        VBox menuPrincipal = new VBox();
        menuPrincipal.setSpacing(10);
        menuPrincipal.setAlignment(Pos.CENTER);
        menuPrincipal.getChildren().addAll(cadastrar, btn3, btn5, btnAvaliar, btnCertificados, close);

        box1.getChildren().add(menuPrincipal);

        // ---------------- BOTOES DE VOLTAR ----------------
        // esses botoes so mandam de volta para a scene anterior
        // mantive a mesma logica de navegacao por stage.setScene()

        // botao de voltar da area do idoso
        Button btn4 = new Button("Voltar");
        btn4.setPrefWidth(180);

        btn4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(scene);
            }
        });

        box2.getChildren().add(btn4);

        // botao de voltar da area do profissional
        Button btn6 = new Button("Voltar");
        btn6.setPrefWidth(180);

        btn6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(scene);
            }
        });

        box3.getChildren().add(btn6);

        // botao de voltar da tela de dados dos usuarios
        Button voltardados = new Button("Voltar");
        voltardados.setPrefWidth(180);

        voltardados.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(scene3);
            }
        });

        box5.getChildren().add(voltardados);

        // botao de voltar da tela de avaliacao
        // atualizacao: a demo tinha tela de avaliacao, mas nao tinha botao de voltar
        // coloquei um voltar simples pra nao prender o usuario na tela
        Button voltarAvaliacao = new Button("Voltar");
        voltarAvaliacao.setPrefWidth(180);

        voltarAvaliacao.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(scene);
            }
        });

        boxAvaliacao.getChildren().add(voltarAvaliacao);

        stage.setScene(scene);
        stage.show();
    }

    // metodo auxiliar pra criar uma tela base
    // atualizacao: isso evita ficar repetindo spacing, padding e alinhamento em toda vbox
    private VBox criarTelaBase() {
        VBox box = new VBox();
        box.setSpacing(18);
        box.setPadding(new Insets(30));
        box.setAlignment(Pos.TOP_CENTER);
        return box;
    }

    // metodo auxiliar pra criar linhas centralizadas
    // atualizacao: usado na busca de profissional e nos botoes do cadastro
    // deixa o codigo menos repetido
    private HBox criarLinha() {
        HBox linha = new HBox();
        linha.setSpacing(10);
        linha.setAlignment(Pos.CENTER);
        return linha;
    }

    public static void main(String[] args) {
        launch();
    }
}