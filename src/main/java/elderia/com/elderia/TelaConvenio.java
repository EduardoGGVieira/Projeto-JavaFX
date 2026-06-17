package elderia.com.elderia;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TelaConvenio {

    private TableView<Convenio> tabelaConvenios;
    private ObservableList<Convenio> dadosConvenios;

    private TextField txtNome;
    private TextField txtTelefone;
    private TextField txtEmail;
    private TextField txtBusca;

    public Scene criarCena(Stage stage, Scene cenaAnterior) {
        Label titulo = new Label("Área de Convênios");
        titulo.setFont(new Font("Arial", 30));
        titulo.setAlignment(Pos.TOP_CENTER);

        txtBusca = new TextField();
        txtBusca.setPromptText("Pesquisar convênio por nome");
        txtBusca.setMaxWidth(300);

        Button btnPesquisar = new Button("Pesquisar");
        btnPesquisar.setOnAction(event -> pesquisarConvenio());

        Button btnMostrarTodos = new Button("Mostrar Todos");
        btnMostrarTodos.setOnAction(event -> dadosConvenios.setAll(ConvenioRepository.listarTodos()));

        HBox linhaBusca = new HBox(10);
        linhaBusca.setAlignment(Pos.CENTER);
        linhaBusca.getChildren().addAll(txtBusca, btnPesquisar, btnMostrarTodos);

        tabelaConvenios = new TableView<>();
        dadosConvenios = FXCollections.observableArrayList();
        tabelaConvenios.setItems(dadosConvenios);
        tabelaConvenios.setPrefHeight(300);

        TableColumn<Convenio, String> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(dado ->
                new SimpleStringProperty(String.valueOf(dado.getValue().getIdConvenio())));
        colId.setPrefWidth(60);

        TableColumn<Convenio, String> colNome = new TableColumn<>("Nome do Convênio");
        colNome.setCellValueFactory(dado ->
                new SimpleStringProperty(dado.getValue().getNome()));
        colNome.setPrefWidth(280);

        TableColumn<Convenio, String> colTelefone = new TableColumn<>("Telefone");
        colTelefone.setCellValueFactory(dado ->
                new SimpleStringProperty(dado.getValue().getTelefone()));
        colTelefone.setPrefWidth(150);

        TableColumn<Convenio, String> colEmail = new TableColumn<>("E-mail");
        colEmail.setCellValueFactory(dado ->
                new SimpleStringProperty(dado.getValue().getEmail()));
        colEmail.setPrefWidth(250);

        tabelaConvenios.getColumns().addAll(colId, colNome, colTelefone, colEmail);

        tabelaConvenios.getSelectionModel().selectedItemProperty().addListener((obs, antigo, selecionado) -> {
            if (selecionado != null) {
                txtNome.setText(selecionado.getNome());
                txtTelefone.setText(selecionado.getTelefone());
                txtEmail.setText(selecionado.getEmail());
            }
        });

        txtNome = new TextField();
        txtNome.setPromptText("Ex: Unimed Curitiba");
        txtNome.setMaxWidth(250);

        txtTelefone = new TextField();
        txtTelefone.setPromptText("Ex: (41) 3333-4444");
        txtTelefone.setMaxWidth(250);

        txtEmail = new TextField();
        txtEmail.setPromptText("Ex: contato@convenio.com.br");
        txtEmail.setMaxWidth(250);

        Button btnAdicionar = new Button("Adicionar");
        Button btnAtualizar = new Button("Atualizar");
        Button btnExcluir = new Button("Excluir");
        Button btnLimpar = new Button("Limpar");

        btnAdicionar.setOnAction(event -> adicionarConvenio());
        btnAtualizar.setOnAction(event -> atualizarConvenio());
        btnExcluir.setOnAction(event -> excluirConvenio());
        btnLimpar.setOnAction(event -> limparCampos());

        HBox linha1 = criarLinhaFormulario("Nome:", txtNome);
        HBox linha2 = criarLinhaFormulario("Telefone:", txtTelefone);
        HBox linha3 = criarLinhaFormulario("E-mail:", txtEmail);

        HBox linhaBotoes = new HBox(10);
        linhaBotoes.setAlignment(Pos.CENTER);
        linhaBotoes.getChildren().addAll(btnAdicionar, btnAtualizar, btnExcluir, btnLimpar);

        Button btnVoltar = new Button("Voltar");
        btnVoltar.setOnAction(event -> stage.setScene(cenaAnterior));

        VBox layout = new VBox();
        layout.setSpacing(15);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setStyle("-fx-background-color: #f2f2f2;");
        layout.getChildren().addAll(titulo, new Separator(), linhaBusca, tabelaConvenios,
                linha1, linha2, linha3, linhaBotoes, btnVoltar);

        dadosConvenios.setAll(ConvenioRepository.listarTodos());

        return new Scene(layout, 1000, 750);
    }

    private HBox criarLinhaFormulario(String textoLabel, Control campo) {
        Label label = new Label(textoLabel);
        label.setPrefWidth(130);

        HBox linha = new HBox(10);
        linha.setAlignment(Pos.CENTER);
        linha.getChildren().addAll(label, campo);

        return linha;
    }

    private void adicionarConvenio() {
        try {
            String nome = validarTexto(txtNome.getText(), "nome");
            String telefone = validarTexto(txtTelefone.getText(), "telefone");
            String email = validarTexto(txtEmail.getText(), "e-mail");

            Convenio convenio = new Convenio(
                    ConvenioRepository.gerarProximoId(),
                    nome,
                    telefone,
                    email
            );

            ConvenioRepository.adicionar(convenio);
            dadosConvenios.setAll(ConvenioRepository.listarTodos());
            limparCampos();
            mostrarInformacao("Convênio cadastrado com sucesso.");

        } catch (IllegalArgumentException e) {
            mostrarErro(e.getMessage());
        } catch (Exception e) {
            mostrarErro("Erro inesperado ao adicionar convênio: " + e.getMessage());
        }
    }

    private void atualizarConvenio() {
        try {
            Convenio selecionado = tabelaConvenios.getSelectionModel().getSelectedItem();

            if (selecionado == null) {
                throw new IllegalArgumentException("Selecione um convênio na tabela.");
            }

            String nome = validarTexto(txtNome.getText(), "nome");
            String telefone = validarTexto(txtTelefone.getText(), "telefone");
            String email = validarTexto(txtEmail.getText(), "e-mail");

            Convenio convenioAtualizado = new Convenio(
                    selecionado.getIdConvenio(),
                    nome,
                    telefone,
                    email
            );

            ConvenioRepository.atualizar(convenioAtualizado);
            dadosConvenios.setAll(ConvenioRepository.listarTodos());
            limparCampos();
            mostrarInformacao("Convênio atualizado com sucesso.");

        } catch (IllegalArgumentException e) {
            mostrarErro(e.getMessage());
        } catch (Exception e) {
            mostrarErro("Erro inesperado ao atualizar convênio: " + e.getMessage());
        }
    }

    private void excluirConvenio() {
        try {
            Convenio selecionado = tabelaConvenios.getSelectionModel().getSelectedItem();

            if (selecionado == null) {
                throw new IllegalArgumentException("Selecione um convênio na tabela.");
            }

            ConvenioRepository.excluirPorId(selecionado.getIdConvenio());
            dadosConvenios.setAll(ConvenioRepository.listarTodos());
            limparCampos();
            mostrarInformacao("Convênio excluído com sucesso.");

        } catch (IllegalArgumentException e) {
            mostrarErro(e.getMessage());
        } catch (Exception e) {
            mostrarErro("Erro inesperado ao excluir convênio: " + e.getMessage());
        }
    }

    private void pesquisarConvenio() {
        try {
            String busca = txtBusca.getText().trim();

            if (busca.isEmpty()) {
                dadosConvenios.setAll(ConvenioRepository.listarTodos());
            } else {
                dadosConvenios.setAll(ConvenioRepository.pesquisarPorNome(busca));
            }

        } catch (Exception e) {
            mostrarErro("Erro ao pesquisar convênio: " + e.getMessage());
        }
    }

    private void limparCampos() {
        txtNome.clear();
        txtTelefone.clear();
        txtEmail.clear();
        txtBusca.clear();
        tabelaConvenios.getSelectionModel().clearSelection();
    }

    private String validarTexto(String texto, String nomeCampo) {
        if (texto == null || texto.trim().isEmpty()) {
            throw new IllegalArgumentException("O campo " + nomeCampo + " é obrigatório.");
        }
        return texto.trim();
    }

    private void mostrarInformacao(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Elderia");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void mostrarErro(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}