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

        Label lblTitulo = new Label("ELDERIA");
        lblTitulo.setFont(new Font("Arial", 32));
        lblTitulo.setAlignment(Pos.CENTER);

        Label lblSubtitulo = new Label("Sistema de apoio para idosos e profissionais");
        lblSubtitulo.setFont(new Font("Arial", 14));
        lblSubtitulo.setAlignment(Pos.CENTER);

        VBox box1 = criarTelaBase();
        box1.getChildren().addAll(lblTitulo, lblSubtitulo, new Separator());

        // area inicial
        Scene sceneInicial = new Scene(box1, 1280, 720);

        // ---------------- AREA IDOSO ----------------
        // botao de ir para idoso manda pra essa scene aqui
        // deixei simples pq a parte de idoso e do dudu

        Label lblIdoso = new Label("Área Idoso");
        lblIdoso.setFont(new Font("Arial", 24));
        lblIdoso.setAlignment(Pos.CENTER);

        Label txtIdoso = new Label("Área destinada às funcionalidades do idoso.");
        txtIdoso.setFont(new Font("Arial", 14));

        VBox boxIdoso = criarTelaBase();
        boxIdoso.getChildren().addAll(lblIdoso, txtIdoso, new Separator());

        // essa cena vamos usar para printar os dados do idoso
        Scene sceneIdoso = new Scene(boxIdoso, 1280, 720);

        // ---------------- AREA PROFISSIONAL ----------------
        // botao de ir para profissional manda pra essa scene aqui
        // mantive o campo de busca que ja existia, so organizei em uma linha

        Label lblProfissional = new Label("Área Profissional");
        lblProfissional.setFont(new Font("Arial", 24));
        lblProfissional.setAlignment(Pos.CENTER);

        VBox boxProfissional = criarTelaBase();
        boxProfissional.getChildren().addAll(lblProfissional, new Separator());

        // input de busca para profissional
        Label lblInputProfissional = new Label("Busque aqui seu profissional:");

        TextField txtEspecializacao = new TextField();
        txtEspecializacao.setPromptText("Ex: Geriatra"); // tipo o placeholder
        txtEspecializacao.setMaxWidth(250); // tamanho do input

        // atualizacao: antes o label e o input eram adicionados direto no boxCadastroProfissional
        // agora fica em hbox so pra alinhar melhor
        HBox linhaBuscaProfissional = criarLinha();
        linhaBuscaProfissional.getChildren().addAll(lblInputProfissional, txtEspecializacao);
        boxProfissional.getChildren().add(linhaBuscaProfissional);

        // ---------------- CADASTRO ----------------
        // area de cadastro
        // atualizacao: deixei o cadastro organizado com gridpane
        // nao muda a logica de salvar usuario, so muda a organizacao visual

        Label lbl4 = new Label("Cadastro");
        lbl4.setFont(new Font("Arial", 28));
        lbl4.setAlignment(Pos.CENTER);

        Label lblCadastroInfo = new Label("Preencha os dados abaixo:");
        lblCadastroInfo.setFont(new Font("Arial", 14));

        VBox boxFormCadastro = criarTelaBase();
        boxFormCadastro.getChildren().addAll(lbl4, lblCadastroInfo, new Separator());

        // parte de cadastro
        Scene scene3 = new Scene(boxFormCadastro, 1280, 720);

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

        boxFormCadastro.getChildren().add(formularioCadastro);

        // ---------------- MOSTRAR DADOS ----------------
        // essa tela mostra os usuarios salvos no arquivo .dat
        // obs: isso existia na branch que a gente tinha usado antes, entao mantive

        Label lbl5 = new Label("Dados dos Usuários");
        lbl5.setFont(new Font("Arial", 26));
        lbl5.setAlignment(Pos.CENTER);

        VBox boxDadosUsuarios = criarTelaBase();
        boxDadosUsuarios.getChildren().addAll(lbl5, new Separator());

        // parte de mostrar os dados cadastrados
        Scene scene4 = new Scene(boxDadosUsuarios, 1280, 720);

        // ---------------- Cadastro do profissional-----------

        //titulo da pagina/scene
        Label lblCadastroProfissional = new Label("Cadastro do Profissional");
        lblCadastroProfissional.setFont(new Font("Arial", 28));
        lblCadastroProfissional.setAlignment(Pos.CENTER);

        //subtítulo
        Label lblCadastroProfissionalInfo = new Label("Preencha seus dados de profissional abaixo:");
        lblCadastroProfissionalInfo.setFont(new Font("Arial", 14));

        VBox boxCadastroProfissional = criarTelaBase();
        boxCadastroProfissional.getChildren().addAll(lblCadastroProfissional, lblCadastroProfissionalInfo, new Separator());


        // essa cena vamos usar pro cadastro do profissional
        Scene scene2 = new Scene(boxCadastroProfissional, 1280, 720);

        // formulário
        GridPane formularioProfissional = new GridPane();
        formularioProfissional.setHgap(10);
        formularioProfissional.setVgap(12);
        formularioProfissional.setAlignment(Pos.CENTER);

        // Nome
        Label lblNomeProfissional = new Label("Nome:");

        TextField txtNomeProfissional = new TextField();
        txtNomeProfissional.setPromptText("Ex: Dr. Nefario de Carvalhão");
        txtNomeProfissional.setPrefWidth(250);

        // CRM/COREN/registro do profissional
        Label lblRegistroProfissional = new Label("CRM/COREN:");

        TextField txtRegistroProfissional = new TextField();
        txtRegistroProfissional.setPromptText("Ex: 12345");
        txtRegistroProfissional.setPrefWidth(250);

        // Especialidade do profissional
        Label lblEspecialidadeProfissional = new Label("Especialidade:");

        TextField txtEspecialidadeProfissional = new TextField();
        txtEspecialidadeProfissional.setPromptText("Ex: Urologista");
        txtEspecialidadeProfissional.setPrefWidth(250);

        // Localização do profissional
        Label lblLocalizacaoProfissional = new Label("Localização:");

        TextField txtLocalizacaoProfissional = new TextField();
        txtLocalizacaoProfissional.setPromptText("Ex: Where Judas lost his boots - PQP");
        txtLocalizacaoProfissional.setPrefWidth(250);

        // Biografia do profissional
        Label lblBiografiaProfissional = new Label("Biografia:");

        TextArea txtBiografiaProfissional = new TextArea();
        txtBiografiaProfissional.setPromptText("Descreva sua experiência profissional...");
        txtBiografiaProfissional.setPrefWidth(250);
        txtBiografiaProfissional.setPrefRowCount(4); //define quantas linhas vai aparecer antes de aparecer o scroll

        // Bota no GridPane que eu criei la em cima
        // coluna 0 = label
        // coluna 1 = campo (Importante lembrar pra nao confundir igual o bobo aqui)
        formularioProfissional.add(lblNomeProfissional, 0, 0);
        formularioProfissional.add(txtNomeProfissional, 1, 0);

        formularioProfissional.add(lblRegistroProfissional, 0, 1);
        formularioProfissional.add(txtRegistroProfissional, 1, 1);

        formularioProfissional.add(lblEspecialidadeProfissional, 0, 2);
        formularioProfissional.add(txtEspecialidadeProfissional, 1, 2);

        formularioProfissional.add(lblLocalizacaoProfissional, 0, 3);
        formularioProfissional.add(txtLocalizacaoProfissional, 1, 3);

        formularioProfissional.add(lblBiografiaProfissional, 0, 4);
        formularioProfissional.add(txtBiografiaProfissional, 1, 4);

        boxCadastroProfissional.getChildren().add(formularioProfissional);

        // ---------------- ÁREA DE AVALIAÇÃO ----------------
        // feito pelo Pierre
        Label lblAvaliacao = new Label("Avaliação");
        lblAvaliacao.setFont(new Font("Arial", 28));
        lblAvaliacao.setAlignment(Pos.CENTER);

        Label textoAvaliacao = new Label("Área destinada às funcionalidades de avaliação.");
        textoAvaliacao.setFont(new Font("Arial", 14));

        VBox boxAvaliacao = criarTelaBase();
        boxAvaliacao.getChildren().addAll(lblAvaliacao, textoAvaliacao, new Separator());

        // parte de avaliação
        Scene sceneAvaliacao = new Scene(boxAvaliacao, 1280, 720);

        // mostra o nome dos profissionais cadastrados - Pierre
        Label lblSelecionarProfissional = new Label("Selecione o Profissional:");
        ComboBox<Profissional> cbProfissional = new ComboBox<>();
        cbProfissional.getItems().addAll(ProfissionalRepository.listarTodos());
        cbProfissional.setPromptText("Escolha um profissional...");
        cbProfissional.setPrefWidth(250);

        // isso aqui faz o ComboBox mostrar o nome - Pierre
        cbProfissional.setCellFactory(lv -> new ListCell<Profissional>() {
            @Override
            protected void updateItem(Profissional p, boolean empty) {
                super.updateItem(p, empty);
                setText(empty || p == null ? null : p.getNomeProfissional());
            }
        });
        cbProfissional.setButtonCell(new ListCell<Profissional>() {
            @Override
            protected void updateItem(Profissional p, boolean empty) {
                super.updateItem(p, empty);
                setText(empty || p == null ? null : p.getNomeProfissional());
            }
        });

        cbProfissional.setPromptText("Escolha um Profissional:");
        cbProfissional.setPrefWidth(250);

        // nota de 1 a 5 - Pierre
        Label lblNota = new Label("Nota (1 a 5):");
        Slider sliderNota = new Slider(1, 5, 3);
        sliderNota.setMajorTickUnit(1);
        sliderNota.setMinorTickCount(0);
        sliderNota.setSnapToTicks(true);
        sliderNota.setShowTickLabels(true);
        sliderNota.setShowTickMarks(true);
        sliderNota.setPrefWidth(250);

        // aqui o usuário/idoso pode fazer um comentário avaliando o profissional - Pierre
        Label lblComentario = new Label("Comentário");
        TextArea txtComentario = new TextArea();
        txtComentario.setPromptText("Comente sobre sua experiência!");
        txtComentario.setPrefWidth(250);
        txtComentario.setPrefRowCount(4);

        // GridPane para organizar igual o form do cadastro - Pierre
        GridPane formularioAvaliacao = new GridPane();
        formularioAvaliacao.setHgap(10);
        formularioAvaliacao.setVgap(12);
        formularioAvaliacao.setAlignment(Pos.CENTER);

        formularioAvaliacao.add(lblSelecionarProfissional, 0, 0);
        formularioAvaliacao.add(cbProfissional, 1, 0);
        formularioAvaliacao.add(lblNota, 0, 1);
        formularioAvaliacao.add(sliderNota, 1, 1);
        formularioAvaliacao.add(lblComentario, 0, 2);
        formularioAvaliacao.add(txtComentario, 1, 2);

        // btn de enviar avaliação - Pierre
        Button btnEnviarAvaliacao = new Button("Enviar Avaliação");
        btnEnviarAvaliacao.setPrefWidth(180);

        btnEnviarAvaliacao.setOnAction(e -> {
            Profissional profissionalSelecionado = cbProfissional.getValue();
            int nota = (int) sliderNota.getValue();
            String comentario = txtComentario.getText().trim();

            if (profissionalSelecionado == null || comentario.isEmpty()) {
                System.err.println("Erro: Selecione um profissional cadastrado e escreva um comentário.");
                return;
            }

            // salva a avaliação do usuário/idoso
            List<Avaliacao> listaAtual = AvaliacaoRepository.listarTodos();
            int novoId = listaAtual.size() + 1;
            Avaliacao nova = new Avaliacao(novoId, profissionalSelecionado.getIdProfissional(), nota, comentario);
            listaAtual.add(nova);
            AvaliacaoRepository.salvarTodos(listaAtual);

            cbProfissional.setValue(null);
            sliderNota.setValue(3);
            txtComentario.clear();

            stage.setScene(sceneInicial);
        });

        boxAvaliacao.getChildren().add(formularioAvaliacao);

        HBox boxBotaoAvaliacao = new HBox(btnEnviarAvaliacao);
        boxBotaoAvaliacao.setAlignment(Pos.CENTER);

        boxAvaliacao.getChildren().add(boxBotaoAvaliacao);

        // --------------------- TABELA DE PESSOAS CADASTRADAS --------------
        // cria a tabela que vai listar os usuários cadastrados

        tabelaUsuarios = new TableView<>();

        // este aq e tipo eventListener
        // ele fica esperando os dados da tabela mudar para atualizar visualmente
        dadosTabela = FXCollections.observableArrayList();

        // conecta a lista na tabela
        tabelaUsuarios.setItems(dadosTabela);
        tabelaUsuarios.setPrefHeight(400);

        // criação das colunas da tabela dos usuários cadastrados

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

        // botão de delete dentro da coluna
        TableColumn<Usuario, Void> colDeletar = new TableColumn<>("Ação");
        colDeletar.setPrefWidth(100);

        // tem que add o botão dentro da coluna
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

                            System.out.println("Usuário '" + usuarioSelecionado.getNome() + "' foi excluído do sistema.");

                        } catch (Exception e) {
                            System.err.println("Erro ao excluir usuário. Código: " + e.getMessage());
                        }
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                // se a linha estiver vazia, não mostra o botão
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btnDeletar); // injeta o botão na célula
                    setAlignment(Pos.CENTER);
                }
            }
        });

        // pega tudo e add na tabela criada
        tabelaUsuarios.getColumns().addAll(colId, colNome, colCpf, colEmail, colTelefone, colTipo, colDeletar);
        boxDadosUsuarios.getChildren().add(tabelaUsuarios);

        // ObjectOutputStream = saída
        // ObjectInputStream = entrada, le os dados

        // ---------------- BOTÕES DO CADASTRO ----------------
        // aqui ficam os botões da tela de cadastro
        // atualização: antes os botões eram adicionados um por um no boxFormCadastro
        // agora eles ficam na mesma linha

        // botão de salvar os dados
        Button btnSalvarDadosIdoso = new Button("Confirmar Cadastro");
        btnSalvarDadosIdoso.setPrefWidth(180);

        btnSalvarDadosIdoso.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    String nome = txtNome.getText().trim();
                    String cpf = txtCPF.getText().trim();
                    String email = txtEmail.getText().trim();
                    String dataNasc = txtDataNascimento.getText().trim(); // pode mapear no model depois

                    // não pode deixar nada em branco
                    // obs: mantive a mesma validação que já tinha antes
                    if (nome.isEmpty() || cpf.isEmpty() || email.isEmpty()) {
                        throw new IllegalArgumentException("Erro: Falta de informações para cadastro de usuário. Por favor, tente novamente.");
                    }

                    // pega a lista atual do arquivo
                    List<Usuario> listaAtual = UsuarioRepository.listarTodos();

                    // cria um id novo baseado no tamanho da lista
                    int novoId = listaAtual.size() + 1;

                    // cria um usuário novo
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

                    // volta para a tela inicial automaticamente após salvar
                    stage.setScene(sceneInicial);

                } catch (IllegalArgumentException y) {
                    System.err.println("Erro de Validação: " + y.getMessage());
                } catch (Exception y) {
                    System.err.println("Erro inesperado do sistema: " + y.getMessage());
                }
            }
        });

        Button btnMostrarDados = new Button("Pessoas Cadastradas");
        btnMostrarDados.setPrefWidth(180);

        btnMostrarDados.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // acha o trem de bytes do arquivo e atualiza a lista da tabela
                List<Usuario> listaDoArquivo = UsuarioRepository.listarTodos();
                dadosTabela.setAll(listaDoArquivo);

                stage.setScene(scene4);
            }
        });

        Button btnVoltarCadastro = new Button("Voltar");
        btnVoltarCadastro.setPrefWidth(180);

        btnVoltarCadastro.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(sceneInicial);
            }
        });

        HBox botoesCadastro = criarLinha();
        botoesCadastro.getChildren().addAll(btnSalvarDadosIdoso, btnMostrarDados, btnVoltarCadastro);

        boxFormCadastro.getChildren().addAll(new Separator(), botoesCadastro);

        // ------ botão da tela do cadastro do profissional-----------
        Button btnSalvarDadosProfissional = new Button("Confirmar Cadastro");
        btnSalvarDadosProfissional.setPrefWidth(180);

        btnSalvarDadosProfissional.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    String nome = txtNomeProfissional.getText().trim();
                    String registro = txtRegistroProfissional.getText().trim();
                    String especialidade = txtEspecialidadeProfissional.getText().trim();
                    String localizacao = txtLocalizacaoProfissional.getText().trim();
                    String biografia = txtBiografiaProfissional.getText().trim();

                    // valida se falta algo
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

                    txtNomeProfissional.clear();
                    txtRegistroProfissional.clear();
                    txtEspecialidadeProfissional.clear();
                    txtLocalizacaoProfissional.clear();
                    txtBiografiaProfissional.clear();

                    stage.setScene(sceneInicial);

                } catch (IllegalArgumentException y) {
                    System.err.println("Deu Erro de Validação aqui: " + y.getMessage());
                } catch (Exception y) {
                    System.err.println("Erro inesperado do sistema: " + y.getMessage());
                }
            }
        });

        HBox boxBotaoProfissional = new HBox(btnSalvarDadosProfissional);
        boxBotaoProfissional.setAlignment(Pos.CENTER);

        boxCadastroProfissional.getChildren().add(boxBotaoProfissional);

        // ---------------- BOTÕES TELA INICIAL ----------------
        // aqui ficam os botões principais do menu inicial
        // atualização: mantive os botões de cadastro, idoso, profissional e fechar
        // também mantive o botão avaliar da demo e o botão certificados da nossa parte

        // botão de cadastrar
        Button btnCadastrarUsuario = new Button("Cadastrar-se como Usuário");
        btnCadastrarUsuario.setPrefWidth(220);

        btnCadastrarUsuario.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(scene3);
            }
        });

        // botão para ir para area de profissional
        Button btnCadastrarProfissional = new Button("Cadastrar-se como Profissional");
        btnCadastrarProfissional.setPrefWidth(220);

        btnCadastrarProfissional.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(scene2);
            }
        });

        // botão de ir para a area do idoso
        Button btnAbaIdoso = new Button("Área do Idoso");
        btnAbaIdoso.setPrefWidth(220);

        btnAbaIdoso.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(sceneIdoso);
            }
        });


        // botão de avaliar
        // isso veio da branch demo, então não pode sumir
        Button btnAvaliar = new Button("Avaliar");
        btnAvaliar.setPrefWidth(220);

        btnAvaliar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(sceneAvaliacao);
            }
        });

        // botão para ir para area de certificados
        // isso e a nossa parte nova
        Button btnCertificados = new Button("Certificados");
        btnCertificados.setPrefWidth(220);

        btnCertificados.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TelaCertificados telaCertificados = new TelaCertificados();
                stage.setScene(telaCertificados.criarCena(stage, sceneInicial));
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
        menuPrincipal.getChildren().addAll(btnCadastrarUsuario, btnCadastrarProfissional, btnAbaIdoso, btnAvaliar, btnCertificados, close);

        box1.getChildren().add(menuPrincipal);

        // ---------------- BOTÕES DE VOLTAR ----------------
        // esses botões so mandam de volta para a scene anterior
        // mantive a lógica de navegação por stage.setScene()

        // botão de voltar da area do idoso
        Button btnVoltarAreaIdoso = new Button("Voltar");
        btnVoltarAreaIdoso.setPrefWidth(180);

        btnVoltarAreaIdoso.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(sceneInicial);
            }
        });

        boxIdoso.getChildren().add(btnVoltarAreaIdoso);

        // botão de voltar da area do profissional
        Button btnVoltarAreaProfissional = new Button("Voltar");
        btnVoltarAreaProfissional.setPrefWidth(180);

        btnVoltarAreaProfissional.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(sceneInicial);
            }
        });

        boxCadastroProfissional.getChildren().add(btnVoltarAreaProfissional);

        // botão de voltar da tela de dados dos usuários
        Button btnVoltarDados = new Button("Voltar");
        btnVoltarDados.setPrefWidth(180);

        btnVoltarDados.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(scene3);
            }
        });

        boxDadosUsuarios.getChildren().add(btnVoltarDados);

        // botão de voltar da tela de avaliação
        // atualização: a demo tinha tela de avaliação, mas não tinha botão de voltar
        // coloquei um voltar simples para não prender o usuário na tela
        Button voltarAvaliacao = new Button("Voltar");
        voltarAvaliacao.setPrefWidth(180);

        voltarAvaliacao.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(sceneInicial);
            }
        });

        boxAvaliacao.getChildren().add(voltarAvaliacao);

        stage.setScene(sceneInicial);
        stage.show();
    }

    // método auxiliar para criar uma tela base
    // atualização: isso evita ficar repetindo spacing, padding e alinhamento em toda vbox
    private VBox criarTelaBase() {
        VBox box = new VBox();
        box.setSpacing(18);
        box.setPadding(new Insets(30));
        box.setAlignment(Pos.TOP_CENTER);
        return box;
    }

    // método auxiliar pra criar linhas centralizadas
    // atualização: usado na busca de profissional e nos botões do cadastro
    // deixa o código menos repetido
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