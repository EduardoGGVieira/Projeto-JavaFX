package elderia.com.elderia;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.util.ArrayList;
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

        // deixa essa jiiromba aqui para compilar certo
        // botão de salvar os dados
        Button btnSalvarDadosIdoso = new Button("Confirmar Cadastro");
        btnSalvarDadosIdoso.setPrefWidth(180);

        // ------ botão da tela do cadastro do profissional-----------
        Button btnSalvarDadosProfissional = new Button("Confirmar Cadastro");
        btnSalvarDadosProfissional.setPrefWidth(180);


        // ---------------- TELA INICIAL ----------------
        // essa e a primeira tela do sistema
        // atualizacao: mantive a ideia da tela inicial, mas organizei com subtitulo e separador

        Label lblTitulo = new Label("ELDERIA");
        lblTitulo.setFont(new Font("Arial", 32));
        lblTitulo.setAlignment(Pos.CENTER);

        Label lblSubtitulo = new Label("Sistema de apoio para idosos e profissionais");
        lblSubtitulo.setFont(new Font("Arial", 14));
        lblSubtitulo.setAlignment(Pos.CENTER);

        VBox boxInicial = criarTelaBase();
        boxInicial.getChildren().addAll(lblTitulo, lblSubtitulo, new Separator());

        // area inicial
        Scene sceneInicial = new Scene(boxInicial, 1280, 720);

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

        // ---------------- AREA HORÁRIOS DISPONIVEIS----------------
        //parte do idoso poder ver horários disponiveis
        Label lblHorarios = new Label("Horários Disponíveis");

        ListView<HorarioDisponivel> listHorariosMarcados =
                new ListView<>();

        Button btnAtualizarMarcados =
                new Button("Atualizar Horários Marcados");

        Button btnCancelarHorario =
                new Button("Cancelar Horário");

        ComboBox<HorarioDisponivel> cbHorarios =
                new ComboBox<>();

        cbHorarios.setPrefWidth(300);

        Button btnMarcarHorario =
                new Button("Marcar Horário");
        btnMarcarHorario.setOnAction(e -> {

            HorarioDisponivel selecionado =
                    cbHorarios.getValue();

            if(selecionado == null){
                return;
            }

            List<HorarioDisponivel> lista =
                    HorarioRepository.listarTodos();

            for(HorarioDisponivel h : lista){

                if(h.getData().equals(selecionado.getData())
                        && h.getHora().equals(selecionado.getHora())){

                    h.setReservado(true);
                }
            }

            HorarioRepository.salvarTodos(lista);

            cbHorarios.getItems().remove(selecionado);

            System.out.println("Parábens, conseguiu achar onde reserva horário!");
        });
        btnAtualizarMarcados.setOnAction(e -> {

            listHorariosMarcados.getItems().clear();

            List<HorarioDisponivel> lista =
                    HorarioRepository.listarTodos();

            for(HorarioDisponivel h : lista){

                if(h.isReservado()){

                    listHorariosMarcados.getItems().add(h);
                }
            }
        });
        btnCancelarHorario.setOnAction(e -> {

            HorarioDisponivel selecionado =
                    listHorariosMarcados.getSelectionModel()
                            .getSelectedItem();

            if(selecionado == null){
                return;
            }

            List<HorarioDisponivel> lista =
                    HorarioRepository.listarTodos();

            for(HorarioDisponivel h : lista){

                if(h.getProfissional().equals(
                        selecionado.getProfissional())
                        &&
                        h.getData().equals(
                                selecionado.getData())
                        &&
                        h.getHora().equals(
                                selecionado.getHora())){

                    h.setReservado(false);

                    break;
                }
            }

            HorarioRepository.salvarTodos(lista);
            cbHorarios.getItems().clear();

            for(HorarioDisponivel h : lista){

                if(!h.isReservado()){

                    cbHorarios.getItems().add(h);
                }
            }
            listHorariosMarcados.getItems().clear();

            for(HorarioDisponivel h : lista){

                if(h.isReservado()){

                    listHorariosMarcados.getItems().add(h);
                }
            }

            listHorariosMarcados.getItems().clear();

            for(HorarioDisponivel h : lista){

                if(h.isReservado()){

                    listHorariosMarcados.getItems().add(h);
                }
            }
        });

        boxIdoso.getChildren().addAll(
                lblHorarios,
                cbHorarios,
                btnMarcarHorario,
                btnAtualizarMarcados,
                listHorariosMarcados,
                btnCancelarHorario
        );

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
        Scene sceneFormCadastro = new Scene(boxFormCadastro, 1280, 720);

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

        VBox boxDadosUsuario = criarTelaBase();
        boxDadosUsuario.getChildren().addAll(lbl5, new Separator());

        // parte de mostrar os dados cadastrados
        Scene sceneDadosUsuario = new Scene(boxDadosUsuario, 1280, 720);

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
        Scene sceneCadastroProfissional = new Scene(boxCadastroProfissional, 1280, 720);

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
        txtBiografiaProfissional.setPromptText("Descreva a sua carreira até aqui :) ");
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
        // CRUD: CREATE - Pierre
        Label lblAvaliacao = new Label("Avaliação");
        lblAvaliacao.setFont(new Font("Arial", 28));
        lblAvaliacao.setAlignment(Pos.CENTER);

        Label textoAvaliacao = new Label("Área destinada às funcionalidades de avaliação.");
        textoAvaliacao.setFont(new Font("Arial", 14));

        VBox boxAvaliacao = criarTelaBase();
        boxAvaliacao.getChildren().addAll(lblAvaliacao, textoAvaliacao, new Separator());

        // cena de avaliação - Pierre
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
                System.err.println("Erro: Por favor, selecione um profissional e escreva uma avaliação.");
                return;
            }

            List<Avaliacao> listAvaliacaoAtual = AvaliacaoRepository.listarTodos();

            Integer idEdicao = (Integer) btnEnviarAvaliacao.getUserData();

            if (idEdicao != null) {
                // UPDATE - Pierre
                for (Avaliacao a : listAvaliacaoAtual) {
                    if (a.getIdAvaliacao() == idEdicao) {
                        a.setComentario(comentario);
                        a.setNota(nota);

                        a.setIdProfissional(profissionalSelecionado.getIdProfissional());
                        break;
                    }
                }

                btnEnviarAvaliacao.setUserData(null);
                btnEnviarAvaliacao.setText("Enviar Avaliação");
            } else {
                // INSERT - Pierre
                int novoId = listAvaliacaoAtual.size() + 1;

                Avaliacao novaAvaliacao = new Avaliacao(novoId, profissionalSelecionado.getIdProfissional(), nota, comentario);
                listAvaliacaoAtual.add(novaAvaliacao);
            }

            AvaliacaoRepository.salvarTodos(listAvaliacaoAtual);

            cbProfissional.setValue(null);
            sliderNota.setValue(3);
            txtComentario.clear();
        });

        stage.setScene(sceneAvaliacao);

        // CRUD: READ - Pierre
        Label lblListaAvaliacoes = new Label("Avaliações por Profissional");
        lblListaAvaliacoes.setFont(new Font("Arial", 24));

        VBox boxListaAvaliacoes = criarTelaBase();
        boxListaAvaliacoes.getChildren().addAll(lblListaAvaliacoes, new Separator());

        Scene sceneListaAvaliacoes = new Scene(boxListaAvaliacoes, 1280, 720);
        
        // ComboBox para filtrar cada profissional - Pierre
        Label lblFiltro = new Label("Profissional:");
        ComboBox<Profissional> cbFiltro = new ComboBox<>();
        cbFiltro.getItems().addAll(ProfissionalRepository.listarTodos());
        cbFiltro.setPromptText("Selecione um profissional");
        cbFiltro.setPrefWidth(250);

        cbFiltro.setCellFactory(lv -> new ListCell<Profissional>() {
            @Override
            protected void updateItem(Profissional p, boolean empty) {
                super.updateItem(p, empty);
                setText(empty || p == null ? null : p.getNomeProfissional());
            }
        });

        // configura para aparecer o nome - Pierre
        cbFiltro.setButtonCell(new ListCell<Profissional>() {
            @Override
            protected void updateItem(Profissional p, boolean empty) {
                super.updateItem(p, empty);
                setText(empty || p == null ? null : p.getNomeProfissional());
            }
        });

        // tabela de avaliações - Pierre
        TableView<Avaliacao> tabelaAvaliacoes = new TableView<>();
        tabelaAvaliacoes.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Avaliacao, Integer> colIdAv = new TableColumn<>("ID");
        colIdAv.setCellValueFactory(new PropertyValueFactory<>("idAvaliacao"));
        colIdAv.setPrefWidth(50);

        TableColumn<Avaliacao, Integer> colNota = new TableColumn<>("Nota");
        colNota.setCellValueFactory(new PropertyValueFactory<>("nota"));
        colNota.setPrefWidth(80);

        TableColumn<Avaliacao, String> colComentario = new TableColumn<>("Comentário");
        colComentario.setCellValueFactory(new PropertyValueFactory<>("comentario"));
        colComentario.setPrefWidth(400);

        // CRUD: UPDATE - Pierre
        TableColumn<Avaliacao, Void> colEditarAvaliacao = new TableColumn<>("Editar");
        colEditarAvaliacao.setPrefWidth(100);

        colEditarAvaliacao.setCellFactory(param -> new TableCell<Avaliacao, Void>() {
            private final Button btnEditarAvaliacao = new Button("Editar");

            {
                btnEditarAvaliacao.setStyle("-fx-background-color: #5cb85c; -fx-text-fill: white; -fx-font-weight: bold;");
                btnEditarAvaliacao.setOnAction(event -> {
                    Avaliacao avaliacaoSelecionada = getTableView().getItems().get(getIndex());

                    if (avaliacaoSelecionada != null) {
                        txtComentario.setText(avaliacaoSelecionada.getComentario());
                        sliderNota.setValue(avaliacaoSelecionada.getNota());
                        for (Profissional p : ProfissionalRepository.listarTodos()) {
                            if (p.getIdProfissional() == avaliacaoSelecionada.getIdProfissional()) {
                                cbProfissional.setValue(p);
                                break;
                            }
                        }

                        btnEnviarAvaliacao.setUserData(avaliacaoSelecionada.getIdAvaliacao());
                        btnEnviarAvaliacao.setText("Salvar Alterações");

                        stage.setScene(sceneAvaliacao);
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btnEditarAvaliacao);
                    setAlignment(Pos.CENTER);
                }
            }
        });

        // CRUD: DELETE - Pierre
        TableColumn<Avaliacao, Void> colDeletarAvaliacao = new TableColumn<>("Ação");
        colDeletarAvaliacao.setPrefWidth(100);

        colDeletarAvaliacao.setCellFactory(param -> new TableCell<Avaliacao, Void>() {
            private final Button btnDeletarAvaliacao = new Button("Remover");

            {
                btnDeletarAvaliacao.setStyle("-fx-background-color: #d9534f; -fx-text-fill: white; -fx-font-weight: bold;");
                btnDeletarAvaliacao.setOnAction(event -> {
                    Avaliacao avaliacaoSelecionada = getTableView().getItems().get(getIndex());

                    if (avaliacaoSelecionada != null) {
                        try {
                            tabelaAvaliacoes.getItems().remove(avaliacaoSelecionada);

                            List<Avaliacao> listaCompletaAvaliacao = AvaliacaoRepository.listarTodos();

                            listaCompletaAvaliacao.removeIf(u -> u.getIdAvaliacao() == avaliacaoSelecionada.getIdAvaliacao());

                            AvaliacaoRepository.salvarTodos(listaCompletaAvaliacao);

                            System.out.println("Avaliação '" + avaliacaoSelecionada.getIdAvaliacao() + "' foi removida do sistema.");
                        } catch (Exception e) {
                            System.err.println("Problema ao remover avaliação.\nErro: " + e.getMessage());
                        }
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btnDeletarAvaliacao);
                    setAlignment(Pos.CENTER);
                }
            }
        });

        tabelaAvaliacoes.getColumns().addAll(colIdAv, colNota, colComentario, colEditarAvaliacao, colDeletarAvaliacao);
        tabelaAvaliacoes.setPrefHeight(400);

        // filtra as avaliações ao selecionar o profissional - Pierre
        cbFiltro.setOnAction(e -> {
            Profissional profissionalSelecionado = cbFiltro.getValue();
            if (profissionalSelecionado == null) return;

            List<Avaliacao> todas = AvaliacaoRepository.listarTodos();
            List<Avaliacao> filtradas = new ArrayList<>();

            for (Avaliacao av : todas) {
                if (av.getIdProfissional() == profissionalSelecionado.getIdProfissional()) {
                    filtradas.add(av);
                }
            }

            tabelaAvaliacoes.setItems(FXCollections.observableArrayList(filtradas));
        });

        HBox linhaFiltro = criarLinha();
        linhaFiltro.getChildren().addAll(lblFiltro, cbFiltro);

        boxListaAvaliacoes.getChildren().addAll(linhaFiltro, tabelaAvaliacoes);

        // botão de voltar para a sceneAvaliacao
        Button btnVoltarLista = new Button("Voltar");
        btnVoltarLista.setPrefWidth(180);
        btnVoltarLista.setOnAction(e -> stage.setScene(sceneAvaliacao));
        boxListaAvaliacoes.getChildren().add(btnVoltarLista);

        // botão de ver avaliações - Pierre
        Button btnVerAvaliacoes = new Button("Avaliações");
        btnVerAvaliacoes.setPrefWidth(180);
        btnVerAvaliacoes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cbFiltro.getItems().setAll(ProfissionalRepository.listarTodos());
                stage.setScene(sceneListaAvaliacoes);
            }
        });

        boxAvaliacao.getChildren().add(formularioAvaliacao);

        HBox boxBotaoAvaliacao = new HBox(10);
        boxBotaoAvaliacao.setAlignment(Pos.CENTER);
        boxBotaoAvaliacao.getChildren().addAll(btnEnviarAvaliacao, btnVerAvaliacoes);

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

        // botão de editar dentro da coluna
        TableColumn<Usuario, Void> colEditar = new TableColumn<>("Editar");
        colEditar.setPrefWidth(100);

        colEditar.setCellFactory(param -> new TableCell<Usuario, Void>() {
            private final Button btnEditarVisual = new Button("Editar");

            {
                // botao verde poggers
                btnEditarVisual.setStyle("-fx-background-color: #5cb85c; -fx-text-fill: white; -fx-font-weight: bold;");
                btnEditarVisual.setOnAction(event -> {
                    Usuario usuarioSelecionado = getTableView().getItems().get(getIndex());

                    if (usuarioSelecionado != null) {
                        // bota a informação no input
                        txtNome.setText(usuarioSelecionado.getNome());
                        txtCPF.setText(usuarioSelecionado.getCpf());
                        txtEmail.setText(usuarioSelecionado.getEmail());
                        txtDataNascimento.setText(usuarioSelecionado.getTelefone());

                        // guarda o id no botao salvar usando o "integer"
                        btnSalvarDadosIdoso.setUserData(Integer.valueOf(usuarioSelecionado.getIdUsuario()));

                        // muda o textinho do botao se estiver na tela de edição
                        btnSalvarDadosIdoso.setText("Salvar Alterações");

                        // Redireciona o fluxo para a cena do formulário de cadastro (sceneFormCadastro)
                        stage.setScene(sceneFormCadastro);
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btnEditarVisual);
                    setAlignment(Pos.CENTER);
                }
            }
        });

        // botão de delete dentro da coluna
        TableColumn<Usuario, Void> colDeletar = new TableColumn<>("Ação");
        colDeletar.setPrefWidth(100);

        // tem que add o botão dentro da coluna
        colDeletar.setCellFactory(param -> new TableCell<Usuario, Void>() {
            private final Button btnDeletar = new Button("Deletar");

            {
                // botao vermeiao
                btnDeletar.setStyle("-fx-background-color: #d9534f; -fx-text-fill: white; -fx-font-weight: bold;");
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
        tabelaUsuarios.getColumns().addAll(colId, colNome, colCpf, colEmail, colTelefone, colTipo, colEditar, colDeletar);
        boxDadosUsuario.getChildren().add(tabelaUsuarios);

        // ObjectOutputStream = saída
        // ObjectInputStream = entrada, le os dados

        // ---------------- BOTÕES DO CADASTRO ----------------
        // aqui ficam os botões da tela de cadastro
        // atualização: antes os botões eram adicionados um por um no boxFormCadastro
        // agora eles ficam na mesma linha



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

                    // se tiver id no botao, considera atualização.
                    if (btnSalvarDadosIdoso.getUserData() != null) {
                        int idParaEditar = (int) btnSalvarDadosIdoso.getUserData();

                        // verifica a tabela pra ver qual usuario q tem q mudar
                        for (Usuario u : listaAtual) {
                            if (u.getIdUsuario() == idParaEditar) {
                                u.setNome(nome);
                                u.setCpf(cpf);
                                u.setEmail(email);
                                u.setTelefone(dataNasc); // mantive dataNasc indo no lugar de telefone
                                break;
                            }
                        }

                        // limpa o id e volta o botao ao normal
                        btnSalvarDadosIdoso.setUserData(null);
                        btnSalvarDadosIdoso.setText("Confirmar Cadastro");

                        System.out.println("\n=== DADOS ATUALIZADOS COM SUCESSO NO ARQUIVO .DAT ===");
                    } else {
                        // se for nulo o id, cria um cadastro padrao
                        // cria um id novo baseado no tamanho da lista
                        int novoId = listaAtual.size() + 1;

                        // cria um usuário novo
                        // obs: mantive dataNasc indo no lugar de telefone pq era assim que ja tava funcionando
                        Usuario novoUsuario = new Usuario(novoId, nome, cpf, email, dataNasc, "idoso");

                        // adiciona na lista
                        listaAtual.add(novoUsuario);

                        System.out.println("\n=== DADOS SALVOS COM SUCESSO NO ARQUIVO .DAT ===");
                    }

                    // salva tudo de volta no .dat
                    UsuarioRepository.salvarTodos(listaAtual);

                    // feedback visual e no console
                    System.out.println("Nome: " + nome + " | CPF: " + cpf + " | Total cadastrados: " + listaAtual.size());

                    // depois de salvar tudo, limpa os inputs
                    txtNome.clear();
                    txtCPF.clear();
                    txtEmail.clear();
                    txtSenha.clear();
                    txtDataNascimento.clear();

                    // atualiza a tabela dps de mudar os dados
                    dadosTabela.setAll(UsuarioRepository.listarTodos());

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

                stage.setScene(sceneDadosUsuario);
            }
        });

        Button btnVoltarCadastro = new Button("Voltar");
        btnVoltarCadastro.setPrefWidth(180);

        btnVoltarCadastro.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //volta o botao pro normal caso o usuario volte atras
                txtNome.clear();
                txtCPF.clear();
                txtEmail.clear();
                txtSenha.clear();
                txtDataNascimento.clear();

                btnSalvarDadosIdoso.setUserData(null);
                btnSalvarDadosIdoso.setText("Confirmar Cadastro");

                stage.setScene(sceneInicial);
            }
        });

        HBox botoesCadastro = criarLinha();
        botoesCadastro.getChildren().addAll(btnSalvarDadosIdoso, btnMostrarDados, btnVoltarCadastro);

        boxFormCadastro.getChildren().addAll(new Separator(), botoesCadastro);

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

                    // de carregar um id significa q é uma atualização
                    if (btnSalvarDadosProfissional.getUserData() != null) {
                        int idParaEditar = (int) btnSalvarDadosProfissional.getUserData();

                        // ve a lista pra ver qual usuario vai mudar
                        for (Profissional p : listaAtual) {
                            if (p.getIdProfissional() == idParaEditar) {
                                p.setNomeProfissional(nome);
                                p.setRegistroProfissional(registro);
                                p.setEspecialidade(especialidade);
                                p.setLocalizacao(localizacao); // mantive dataNasc indo no lugar de telefone
                                p.setBiografia(biografia);
                                break;
                            }
                        }





                        // volta o botao pro normar
                        btnSalvarDadosProfissional.setUserData(null);
                        btnSalvarDadosProfissional.setText("Confirmar Cadastro");

                        System.out.println("\n=== os dados foram atualizados no arquivo ===");
                    } else {
                        // isso ai em baixo caso for nulo
                        // cria um id novo baseado no tamanho da lista
                        int novoId = listaAtual.size() + 1;
                        // cria o profissional cadastrado no formulario lindo
                        Profissional novoProfissional = new Profissional(novoId, nome, registro, especialidade, localizacao, biografia);

                        listaAtual.add(novoProfissional);



                        System.out.println("\n= Ta salvo pai :P =");
                    }
                    ProfissionalRepository.salvarTodos(listaAtual);

                    System.out.println("Nome: " + nome + " | CRM/COREN: " + registro + " | Total de profissionais cadastrados: " + listaAtual.size());

                    txtNomeProfissional.clear();
                    txtRegistroProfissional.clear();
                    txtEspecialidadeProfissional.clear();
                    txtLocalizacaoProfissional.clear();
                    txtBiografiaProfissional.clear();

                    // atualiza a tabela dps de atualizar as mudanças
                    dadosTabela.setAll(UsuarioRepository.listarTodos());

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


        VBox boxProfissasTabela = criarTelaBase();

        //cena q vai ser usada pra tabela de top 10 profissionais
        Scene sceneTabelaProfissas = new Scene(boxProfissasTabela, 1280, 720);


        // --------------------- tabea dos profissionais cadastrados ---------------------

        //cabecalho
        Label lblTituloTabelaProfissa = new Label("Profissionais Cadastrados");
        lblTituloTabelaProfissa.setFont(new Font("Arial", 28));
        lblTituloTabelaProfissa.setAlignment(Pos.CENTER);

        Label lblTabelaProfissaInfo = new Label("Aqui voce pode ver os nossos profissionais do site.");
        lblTabelaProfissaInfo.setFont(new Font("Arial", 14));

        boxProfissasTabela.getChildren().addAll(lblTituloTabelaProfissa, lblTabelaProfissaInfo, new Separator());

        //criação da tabela
        TableView<Profissional> tabelaProfissa = new TableView<>();

        ObservableList<Profissional> dadosProfissa = FXCollections.observableArrayList();


        tabelaProfissa.setItems(dadosProfissa);
        tabelaProfissa.setPrefHeight(400);

        TableColumn<Profissional, Integer> colIdProf = new TableColumn<>("ID");

        colIdProf.setCellValueFactory(new PropertyValueFactory<>("idProfissional"));
        colIdProf.setPrefWidth(60);


        TableColumn<Profissional, String> colNomeProf = new TableColumn<>("Nome");

        colNomeProf.setCellValueFactory(new PropertyValueFactory<>("nomeProfissional"));
        colNomeProf.setPrefWidth(200);

        TableColumn<Profissional, String> colRegistro = new TableColumn<>("CRM/COREN");

        colRegistro.setCellValueFactory(new PropertyValueFactory<>("registroProfissional"));
        colRegistro.setPrefWidth(130);


        TableColumn<Profissional, String> colEspecialidade = new TableColumn<>("Especialidade");
        colEspecialidade.setCellValueFactory(new PropertyValueFactory<>("especialidade"));
        colEspecialidade.setPrefWidth(150);

        TableColumn<Profissional, String> colLocalizacao = new TableColumn<>("Localização");

        colLocalizacao.setCellValueFactory(new PropertyValueFactory<>("localizacao"));
        colLocalizacao.setPrefWidth(150);


        TableColumn<Profissional, String> colBiografia = new TableColumn<>("Biografia");

        colBiografia.setCellValueFactory(new PropertyValueFactory<>("biografia"));
        colBiografia.setPrefWidth(250);

        // botão de editar dentro da coluna
        TableColumn<Profissional, Void> colEditarProfissa = new TableColumn<>("Editar");
        colEditar.setPrefWidth(100);

        colEditarProfissa.setCellFactory(param -> new TableCell<Profissional, Void>() {
            private final Button btnEditarVisualProfissa = new Button("Editar");

            {
                // botao verde pog
                btnEditarVisualProfissa.setStyle("-fx-background-color: #5cb85c; -fx-text-fill: white; -fx-font-weight: bold;");
                btnEditarVisualProfissa.setOnAction(event -> {
                    Profissional profissionalSelecionado = getTableView().getItems().get(getIndex());

                    if (profissionalSelecionado != null) {
                        // bota os dados antigos e bota no input
                        txtNomeProfissional.setText(profissionalSelecionado.getNomeProfissional());
                        txtRegistroProfissional.setText(profissionalSelecionado.getRegistroProfissional());
                        txtEspecialidadeProfissional.setText(profissionalSelecionado.getEspecialidade());
                        txtLocalizacaoProfissional.setText(profissionalSelecionado.getLocalizacao());
                        txtBiografiaProfissional.setText(profissionalSelecionado.getBiografia());

                        // Guarda o ID do usuario no botao salvar usando o "integer"
                        btnSalvarDadosProfissional.setUserData(Integer.valueOf(profissionalSelecionado.getIdProfissional()));

                        //  muda o textinho do botao quando estiver editando
                        btnSalvarDadosProfissional.setText("Salvar Alterações");

                        // Redireciona o fluxo para a cena do formulário de cadastro do profissa (sceneCadastroProfissional)
                        stage.setScene(sceneCadastroProfissional);
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btnEditarVisualProfissa);
                    setAlignment(Pos.CENTER);
                }
            }
        });
        TableColumn<Profissional, Void> colDeletarProfissa = new TableColumn<>("Excluir");

        colDeletar.setPrefWidth(70);

        colDeletarProfissa.setCellFactory(param -> new TableCell<Profissional, Void>() {

                    private final Button btnDeletarProfissa =
                            new Button("Deletar");

                    {
                        btnDeletarProfissa.setStyle("-fx-background-color: #d9534f; -fx-text-fill: white; -fx-font-weight: bold;");
                        btnDeletarProfissa.setOnAction(event -> {

                            Profissional profissionalSelecionado =
                                    getTableView()
                                            .getItems()
                                            .get(getIndex());

                            if (profissionalSelecionado != null) {
                                try {

                                    //expulsa da tabela
                                    dadosProfissa.remove(profissionalSelecionado);

                                    //tira do arquivo .dat/ repository
                                    List<Profissional> listaCompleta = ProfissionalRepository.listarTodos();

                                    listaCompleta.removeIf(p -> p.getIdProfissional()
                                            == profissionalSelecionado.getIdProfissional());

                                    ProfissionalRepository.salvarTodos(listaCompleta);

                                    System.out.println("Total de profissionais: " + listaCompleta.size());
                                    System.out.println(ProfissionalRepository.listarTodos().size());

                                    System.out.println("O Profissional '" + profissionalSelecionado.getNomeProfissional() + "' foi pro beleléu.");

                                } catch (Exception e) {

                                    System.err.println(
                                            "Nao deu pra excluir o profissional: " + e.getMessage());
                                }
                            }
                        });
                    }

            @Override
            protected void updateItem(Void item, boolean empty) {  super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btnDeletarProfissa);
                    setAlignment(Pos.CENTER);
                }
            }
        });

        tabelaProfissa.getColumns().addAll(colIdProf, colNomeProf, colRegistro, colEspecialidade,
                colLocalizacao, colBiografia, colEditarProfissa, colDeletarProfissa
        );

        boxProfissasTabela.getChildren().add(tabelaProfissa);

        Button btnVoltarTabelaProfissa = new Button("Voltar");
        btnVoltarTabelaProfissa.setPrefWidth(180);

        btnVoltarTabelaProfissa.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                txtNomeProfissional.clear();
                txtRegistroProfissional.clear();
                txtEspecialidadeProfissional.clear(); // não txtEspecializacao
                txtLocalizacaoProfissional.clear();
                txtBiografiaProfissional.clear();

                btnSalvarDadosProfissional.setUserData(null);
                btnSalvarDadosProfissional.setText("Confirmar Cadastro");

                stage.setScene(sceneInicial);
            }
        });

        boxProfissasTabela.getChildren().add(btnVoltarTabelaProfissa);


        // Tela de gerenciamento de horários cadastrados
        // feito por Matheus
        Label lblGerenciarHorarios =
                new Label("Gerenciamento de Horários");

        lblGerenciarHorarios.setFont(
                new Font("Arial", 28)
        );

        VBox boxHorarios = criarTelaBase();
        Button btnVoltarHorarios =
                new Button("Voltar");

        btnVoltarHorarios.setOnAction(
                e -> stage.setScene(sceneCadastroProfissional)
        );

        boxHorarios.getChildren()
                .add(btnVoltarHorarios);

        boxHorarios.getChildren().addAll(
                lblGerenciarHorarios,
                new Separator()
        );

        Scene sceneHorarios =
                new Scene(boxHorarios, 1280, 720);
        ListView<HorarioDisponivel> listHorarios =
                new ListView<>();
        List<HorarioDisponivel> listaInicial =
                HorarioRepository.listarTodos();

        listHorarios.getItems().addAll(listaInicial);

        Button btnAtualizarLista =
                new Button("Atualizar Lista");

        Button btnEditarHorario =
                new Button("Editar Horário");

        Button btnExcluirHorario =
                new Button("Excluir Horário");

        GridPane formularioHorario = new GridPane();

        formularioHorario.setHgap(10);
        formularioHorario.setVgap(12);
        formularioHorario.setAlignment(Pos.CENTER);

        Label lblDataHorario = new Label("Data:");
        TextField txtDataHorario = new TextField();

        txtDataHorario.textProperty().addListener((obs, oldValue, newValue) -> {

            String numeros = newValue.replaceAll("[^0-9]", "");

            if(numeros.length() > 8){
                numeros = numeros.substring(0, 8);
            }

            StringBuilder formatado = new StringBuilder();

            for(int i = 0; i < numeros.length(); i++){

                if(i == 2 || i == 4){
                    formatado.append("/");
                }

                formatado.append(numeros.charAt(i));
            }

            txtDataHorario.setText(formatado.toString());
        });

        Label lblHoraHorario = new Label("Hora:");
        TextField txtHoraHorario = new TextField();

        txtHoraHorario.textProperty().addListener((obs, oldValue, newValue) -> {

            String numeros = newValue.replaceAll("[^0-9]", "");

            if(numeros.length() > 4){
                numeros = numeros.substring(0, 4);
            }

            StringBuilder formatado = new StringBuilder();

            for(int i = 0; i < numeros.length(); i++){

                if(i == 2){
                    formatado.append(":");
                }

                formatado.append(numeros.charAt(i));
            }

            txtHoraHorario.setText(formatado.toString());
        });

        Button btnCriarHorario =
                new Button("Criar Horário");
        btnCriarHorario.setOnAction(e -> {


            String data = txtDataHorario.getText().trim();
            String hora = txtHoraHorario.getText().trim();

            if(data.isEmpty() || hora.isEmpty()){
                return;
            }

            List<HorarioDisponivel> lista =
                    HorarioRepository.listarTodos();

            //verifica se existe outro horario com a mesma data e hora
            boolean existe = false;

            for(HorarioDisponivel h : lista){

                if(h.getData().equals(data)
                        &&
                        h.getHora().equals(hora)){

                    existe = true;
                    break;
                }
            }

            if(existe){
                System.out.println("Já tem um horário criado seu idoso, pare de insistir porfavor.");
                return;
            }

            HorarioDisponivel novo =
                    new HorarioDisponivel(
                            "Profissional",
                            data,
                            hora
                    );

            lista.add(novo);

            HorarioRepository.salvarTodos(lista);
            cbHorarios.getItems().clear();

            for(HorarioDisponivel h : lista){

                if(!h.isReservado()){

                    cbHorarios.getItems().add(h);
                }
            }
            listHorarios.getItems().clear();
            listHorarios.getItems().addAll(lista);

            txtDataHorario.clear();
            txtHoraHorario.clear();

            System.out.println("Parábens, agora só basta aguardar um idoso agendar com você (se ele conseguir :) )");
        });
        btnAtualizarLista.setOnAction(e -> {

            listHorarios.getItems().clear();

            List<HorarioDisponivel> lista =
                    HorarioRepository.listarTodos();

            listHorarios.getItems().addAll(lista);
        });
        btnExcluirHorario.setOnAction(e -> {

            HorarioDisponivel selecionado =
                    listHorarios.getSelectionModel()
                            .getSelectedItem();

            if(selecionado == null){
                return;
            }

            List<HorarioDisponivel> lista =
                    HorarioRepository.listarTodos();

            lista.removeIf(h ->
                    h.getProfissional().equals(
                            selecionado.getProfissional())
                            &&
                            h.getData().equals(
                                    selecionado.getData())
                            &&
                            h.getHora().equals(
                                    selecionado.getHora())
            );

            HorarioRepository.salvarTodos(lista);

            listHorarios.getItems().clear();
            listHorarios.getItems().addAll(lista);
        });
        listHorarios.setOnMouseClicked(e -> {

            HorarioDisponivel selecionado =
                    listHorarios.getSelectionModel()
                            .getSelectedItem();

            if(selecionado != null){

                txtDataHorario.setText(
                        selecionado.getData());

                txtHoraHorario.setText(
                        selecionado.getHora());
            }
        });
        btnEditarHorario.setOnAction(e -> {

            HorarioDisponivel selecionado =
                    listHorarios.getSelectionModel()
                            .getSelectedItem();

            if(selecionado == null){
                return;
            }

            List<HorarioDisponivel> lista =
                    HorarioRepository.listarTodos();

            for(HorarioDisponivel h : lista){

                if(h.getProfissional().equals(
                        selecionado.getProfissional())
                        &&
                        h.getData().equals(
                                selecionado.getData())
                        &&
                        h.getHora().equals(
                                selecionado.getHora())){

                    h.setData(
                            txtDataHorario.getText().trim());

                    h.setHora(
                            txtHoraHorario.getText().trim());

                    break;
                }
            }

            HorarioRepository.salvarTodos(lista);

            listHorarios.getItems().clear();
            listHorarios.getItems().addAll(lista);

            txtDataHorario.clear();
            txtHoraHorario.clear();
        });

        formularioHorario.add(lblDataHorario,0,0);
        formularioHorario.add(txtDataHorario,1,0);

        formularioHorario.add(lblHoraHorario,0,1);
        formularioHorario.add(txtHoraHorario,1,1);

        formularioHorario.add(btnCriarHorario,1,2);

        boxHorarios.getChildren().add(formularioHorario);
        boxHorarios.getChildren().addAll(
                btnAtualizarLista,
                listHorarios,
                btnEditarHorario,
                btnExcluirHorario
        );

        //botao de ir pra tabela.
        Button btnTabelaProfissa = new Button("Veja nossos profissionais cadastrados!");

        //botão para ir pra gerenciar horarios
        Button btnGerenciarHorarios =
                new Button("Gerenciar Horários");

        btnGerenciarHorarios.setPrefWidth(220);

        btnGerenciarHorarios.setOnAction(e -> {
            stage.setScene(sceneHorarios);
        });

        boxCadastroProfissional
                .getChildren()
                .add(btnGerenciarHorarios);
        //----------------------------------------//
        btnTabelaProfissa.setPrefWidth(220);
        btnTabelaProfissa.setOnAction(e -> {

            List<Profissional> lista =
                    ProfissionalRepository.listarTodos();

            dadosProfissa.clear();
            dadosProfissa.addAll(lista);

            //  DA um refresh na tabela pra nao precisar atualizar
            dadosProfissa.setAll(ProfissionalRepository.listarTodos());


            stage.setScene(sceneTabelaProfissas);
            //FUNCIONOU MEU DEUS DO CEU FINALMENTE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        });
        boxCadastroProfissional.getChildren().add(btnTabelaProfissa);


        // ---------------- CADASTRO ADMIN ----------------
        // tela de cadastro de administrador
        // segue o mesmo padrão do cadastro de usuário

        Label lblAdmin = new Label("Cadastro de Administrador");
        lblAdmin.setFont(new Font("Arial", 28));
        lblAdmin.setAlignment(Pos.CENTER);

        Label lblAdminInfo = new Label("Preencha os dados abaixo:");
        lblAdminInfo.setFont(new Font("Arial", 14));

        VBox boxAdmin = criarTelaBase();
        boxAdmin.getChildren().addAll(lblAdmin, lblAdminInfo, new Separator());

        Scene sceneAdmin = new Scene(boxAdmin, 1280, 720);

        // formulário no mesmo esquema do cadastro de usuário
        GridPane formularioAdmin = new GridPane();
        formularioAdmin.setHgap(10);
        formularioAdmin.setVgap(12);
        formularioAdmin.setAlignment(Pos.CENTER);

        // -- nome --
        Label lblInputNomeAdmin = new Label("Nome:");

        TextField txtNomeAdmin = new TextField();
        txtNomeAdmin.setPromptText("Ex: Maria Administradora");
        txtNomeAdmin.setPrefWidth(250);

        // -- e-mail --
        Label lblInputEmailAdmin = new Label("Email:");

        TextField txtEmailAdmin = new TextField();
        txtEmailAdmin.setPromptText("Ex: admin@elderia.com");
        txtEmailAdmin.setPrefWidth(250);

        // coluna 0 = label
        // coluna 1 = campo
        formularioAdmin.add(lblInputNomeAdmin, 0, 0);
        formularioAdmin.add(txtNomeAdmin, 1, 0);

        formularioAdmin.add(lblInputEmailAdmin, 0, 1);
        formularioAdmin.add(txtEmailAdmin, 1, 1);

        boxAdmin.getChildren().add(formularioAdmin);

        // --------------------- TABELA DE ADMINS CADASTRADOS ---------------------
        // cria a tabela que vai listar os administradores cadastrados

        Label lblTituloTabelaAdmin = new Label("Administradores Cadastrados");
        lblTituloTabelaAdmin.setFont(new Font("Arial", 26));
        lblTituloTabelaAdmin.setAlignment(Pos.CENTER);

        VBox boxDadosAdmin = criarTelaBase();
        boxDadosAdmin.getChildren().addAll(lblTituloTabelaAdmin, new Separator());

        Scene sceneDadosAdmin = new Scene(boxDadosAdmin, 1280, 720);

        // lista conectada na tabela
        ObservableList<Admin> dadosTabelaAdmin = FXCollections.observableArrayList();

        TableView<Admin> tabelaAdmin = new TableView<>();
        tabelaAdmin.setItems(dadosTabelaAdmin);
        tabelaAdmin.setPrefHeight(400);

        // criação das colunas da tabela de administradores
        TableColumn<Admin, Integer> colIdAdmin = new TableColumn<>("ID");
        colIdAdmin.setCellValueFactory(new PropertyValueFactory<>("idAdmin"));
        colIdAdmin.setPrefWidth(60);

        TableColumn<Admin, String> colNomeAdmin = new TableColumn<>("Nome");
        colNomeAdmin.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colNomeAdmin.setPrefWidth(200);

        TableColumn<Admin, String> colEmailAdmin = new TableColumn<>("E-mail");
        colEmailAdmin.setCellValueFactory(new PropertyValueFactory<>("email"));
        colEmailAdmin.setPrefWidth(250);

        // botão de delete dentro da coluna, igual ao da tabela de usuários
        TableColumn<Admin, Void> colDeletarAdmin = new TableColumn<>("Ação");
        colDeletarAdmin.setPrefWidth(100);

        colDeletarAdmin.setCellFactory(param -> new TableCell<Admin, Void>() {
            private final Button btnDeletarAdmin = new Button("Deletar");

            {
                btnDeletarAdmin.setOnAction(event -> {
                    // pega o objeto admin correspondente a linha onde o botao foi clicado
                    Admin adminSelecionado = getTableView().getItems().get(getIndex());

                    if (adminSelecionado != null) {
                        try {
                            // tira da tabela, mas so a parte visual
                            dadosTabelaAdmin.remove(adminSelecionado);

                            // apaga literalmente do arquivo .dat
                            List<Admin> listaCompleta = AdminRepository.listarTodos();

                            // remove da lista o admin que possui o mesmo id
                            listaCompleta.removeIf(a -> a.getIdAdmin() == adminSelecionado.getIdAdmin());

                            // grava o trem de bytes atualizado de volta no arquivo permanente
                            AdminRepository.salvarTodos(listaCompleta);

                            System.out.println("Administrador '" + adminSelecionado.getNome() + "' foi excluído do sistema.");

                        } catch (Exception e) {
                            System.err.println("Erro ao excluir administrador. Código: " + e.getMessage());
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
                    setGraphic(btnDeletarAdmin);
                    setAlignment(Pos.CENTER);
                }
            }
        });

        tabelaAdmin.getColumns().addAll(colIdAdmin, colNomeAdmin, colEmailAdmin, colDeletarAdmin);
        boxDadosAdmin.getChildren().add(tabelaAdmin);

        // botão de voltar da tela de admins cadastrados
        Button btnVoltarDadosAdmin = new Button("Voltar");
        btnVoltarDadosAdmin.setPrefWidth(180);

        btnVoltarDadosAdmin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(sceneAdmin);
            }
        });

        boxDadosAdmin.getChildren().add(btnVoltarDadosAdmin);

        // ---------------- BOTÕES DO CADASTRO DE ADMIN ----------------
        // botão de salvar os dados do admin
        Button btnSalvarDadosAdmin = new Button("Confirmar Cadastro");
        btnSalvarDadosAdmin.setPrefWidth(180);

        btnSalvarDadosAdmin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    String nome = txtNomeAdmin.getText().trim();
                    String email = txtEmailAdmin.getText().trim();

                    // não pode deixar nada em branco
                    if (nome.isEmpty() || email.isEmpty()) {
                        throw new IllegalArgumentException("Erro: Falta de informações para cadastro de administrador. Por favor, tente novamente.");
                    }

                    // pega a lista atual do arquivo
                    List<Admin> listaAtual = AdminRepository.listarTodos();

                    // cria um id novo baseado no tamanho da lista
                    int novoId = listaAtual.size() + 1;

                    // cria um admin novo
                    Admin novoAdmin = new Admin(novoId, nome, email);

                    // adiciona na lista
                    listaAtual.add(novoAdmin);

                    // salva tudo de volta no .dat
                    AdminRepository.salvarTodos(listaAtual);

                    System.out.println("\n=== ADMIN SALVO COM SUCESSO NO ARQUIVO .DAT ===");
                    System.out.println("Nome: " + nome + " | Total cadastrados: " + listaAtual.size());

                    // depois de salvar tudo, limpa os inputs
                    txtNomeAdmin.clear();
                    txtEmailAdmin.clear();

                    // volta para a tela inicial automaticamente após salvar
                    stage.setScene(sceneInicial);

                } catch (IllegalArgumentException y) {
                    System.err.println("Erro de Validação: " + y.getMessage());
                } catch (Exception y) {
                    System.err.println("Erro inesperado do sistema: " + y.getMessage());
                }
            }
        });

        // botão de ver admins cadastrados
        Button btnMostrarAdmins = new Button("Administradores Cadastrados");
        btnMostrarAdmins.setPrefWidth(180);

        btnMostrarAdmins.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // acha o trem de bytes do arquivo e atualiza a lista da tabela
                List<Admin> listaDoArquivo = AdminRepository.listarTodos();
                dadosTabelaAdmin.setAll(listaDoArquivo);

                stage.setScene(sceneDadosAdmin);
            }
        });

        Button btnVoltarAdmin = new Button("Voltar");
        btnVoltarAdmin.setPrefWidth(180);

        btnVoltarAdmin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(sceneInicial);
            }
        });

        HBox botoesAdmin = criarLinha();
        botoesAdmin.getChildren().addAll(btnSalvarDadosAdmin, btnMostrarAdmins, btnVoltarAdmin);

        boxAdmin.getChildren().addAll(new Separator(), botoesAdmin);

        // ---------------- BOTÕES TELA INICIAL ----------------
        // aqui ficam os botões principais do menu inicial
        // atualização: mantive os botões de cadastro, idoso, profissional e fechar
        // também mantive o botão avaliar da demo e o botão certificados da nossa parte

       // botão para ir para outra tela igual o gab fez
        Button btnMóduloProntuario = new Button("Prontuários");
        btnMóduloProntuario.setPrefWidth(220);

        btnMóduloProntuario.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Instancia a nova classe que criamos e altera a cena passando a atual como retorno
                TelaProntuarios telaProntuarios = new TelaProntuarios();
                stage.setScene(telaProntuarios.criarCena(stage, sceneInicial));
            }
        });

        // botão pra ir pra tela do cuidador
        Button btnCadastroCuida = new Button("Cadastre-se como Cuidador");
        btnCadastroCuida.setPrefWidth(220);

        btnCadastroCuida.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Instancia a nova classe que criamos e altera a cena passando a atual como retorno
                TelaCuidador telaCuidador = new TelaCuidador();
                stage.setScene(telaCuidador.criarCena(stage, sceneInicial));
            }
        });


        // botão de cadastrar
        Button btnCadastrarUsuario = new Button("Cadastrar-se como Usuário");
        btnCadastrarUsuario.setPrefWidth(220);

        btnCadastrarUsuario.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(sceneFormCadastro);
            }
        });

        // botão para ir para area de profissional
        Button btnCadastrarProfissional = new Button("Cadastrar-se como Profissional");
        btnCadastrarProfissional.setPrefWidth(220);

        btnCadastrarProfissional.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(sceneCadastroProfissional);
            }
        });

        // botão de ir para a area do idoso
        Button btnAbaIdoso = new Button("Área do Idoso");
        btnAbaIdoso.setPrefWidth(220);

        btnAbaIdoso.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //parte de ver horario disponivel
                cbHorarios.getItems().clear();

                List<HorarioDisponivel> lista =
                        HorarioRepository.listarTodos();

                for (HorarioDisponivel h : lista) {

                    if (!h.isReservado()) {
                        cbHorarios.getItems().add(h);
                    }
                }
                stage.setScene(sceneIdoso);
            }
        });
        
        // BOTAO NA TELA INICIAL PARA ACESSAR A TELA DE AVALIAÇÃO - Pierre
        Button btnAvaliar = new Button("Avaliar Profissional");
        btnAvaliar.setPrefWidth(220);

        btnAvaliar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cbProfissional.getItems().setAll(ProfissionalRepository.listarTodos());
                stage.setScene(sceneAvaliacao);
            }
        });

        //botão para ir para cadastrar medicamento
        Button btnMedicamento = new Button("Cadastrar Medicamento");
        btnMedicamento.setPrefWidth(220);

        btnMedicamento.setOnAction(event -> {
            TelaMedicamento telaMedicamento = new TelaMedicamento();
            stage.setScene(
                    telaMedicamento.remedio(stage, sceneInicial)
            );
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

        Button btnCadastrarAdmin = new Button("Cadastrar como Administrador");
        btnCadastrarAdmin.setPrefWidth(220);
        btnCadastrarAdmin.setOnAction(e -> stage.setScene(sceneAdmin));

        VBox menuPrincipal = new VBox();
        menuPrincipal.setSpacing(10);
        menuPrincipal.setAlignment(Pos.CENTER);

        menuPrincipal.getChildren().addAll(btnCadastrarUsuario, btnCadastroCuida ,btnCadastrarProfissional, btnCadastrarAdmin, btnMedicamento,
                btnAbaIdoso, btnAvaliar, btnCertificados, btnMóduloProntuario, close);
        boxInicial.getChildren().add(menuPrincipal);

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

        // botão de voltar da area do cadastro do profissa
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
                stage.setScene(sceneFormCadastro);
            }
        });

        boxDadosUsuario.getChildren().add(btnVoltarDados);

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
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
        stage.show();
    }

    // metodo auxiliar para criar uma tela base
    // atualização: isso evita ficar repetindo spacing, padding e alinhamento em toda vbox
    private VBox criarTelaBase() {
        VBox box = new VBox();
        box.setSpacing(18);
        box.setPadding(new Insets(30));
        box.setAlignment(Pos.TOP_CENTER);
        return box;
    }

    // metodo auxiliar pra criar linhas centralizadas
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