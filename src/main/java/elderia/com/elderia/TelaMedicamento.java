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

public class TelaMedicamento {

    private TableView<Medicamento> tabelaMedicamentos;
    private ObservableList<Medicamento> dadosMedicamentos;

    private TextField txtIdMedicamento;
    private TextField txtNomeMedicamento;
    private TextField txtDosagem;
    private TextField txtHorario;
    private TextField txtObservacao;
    private TextField txtBuscaMedicamento;

    public Scene remedio(Stage stage, Scene cenaAnterior) {

        Label titulo = new Label("Área de Medicamentos");
        titulo.setFont(new Font("Arial", 30));

        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.TOP_CENTER);

        layout.getChildren().addAll(
                titulo,
                criarAbaMedicamentos()
        );

        Button btnVoltar = new Button("Voltar");
        btnVoltar.setOnAction(e -> stage.setScene(cenaAnterior));

        layout.getChildren().add(btnVoltar);

        atualizarTabela();

        return new Scene(layout, 1000, 700);
    }

    private VBox criarAbaMedicamentos() {

        VBox box = new VBox(12);
        box.setAlignment(Pos.TOP_CENTER);
        box.setPadding(new Insets(15));

        Label lblTitulo = new Label("Medicamentos");
        lblTitulo.setFont(new Font("Arial", 22));

        txtBuscaMedicamento = new TextField();
        txtBuscaMedicamento.setPromptText("Pesquisar medicamento");
        txtBuscaMedicamento.setMaxWidth(300);

        Button btnPesquisar = new Button("Pesquisar");
        btnPesquisar.setOnAction(e -> pesquisarMedicamento());

        Button btnMostrarTodos = new Button("Mostrar Todos");
        btnMostrarTodos.setOnAction(e ->
                dadosMedicamentos.setAll(MedicamentoRepository.listarTodos()));

        HBox linhaBusca = new HBox(10);
        linhaBusca.setAlignment(Pos.CENTER);
        linhaBusca.getChildren().addAll(
                txtBuscaMedicamento,
                btnPesquisar,
                btnMostrarTodos
        );

        tabelaMedicamentos = new TableView<>();
        dadosMedicamentos = FXCollections.observableArrayList();
        tabelaMedicamentos.setItems(dadosMedicamentos);

        TableColumn<Medicamento, String> colId =
                new TableColumn<>("ID");

        colId.setCellValueFactory(dado ->
                new SimpleStringProperty(
                        String.valueOf(dado.getValue().getIdMedicamento())
                ));

        TableColumn<Medicamento, String> colNome =
                new TableColumn<>("Nome");

        colNome.setCellValueFactory(dado ->
                new SimpleStringProperty(
                        dado.getValue().getNomeMedicamento()
                ));

        TableColumn<Medicamento, String> colDosagem =
                new TableColumn<>("Dosagem");

        colDosagem.setCellValueFactory(dado ->
                new SimpleStringProperty(
                        dado.getValue().getDosagem()
                ));

        TableColumn<Medicamento, String> colHorario =
                new TableColumn<>("Horário");

        colHorario.setCellValueFactory(dado ->
                new SimpleStringProperty(
                        dado.getValue().getHorario()
                ));

        TableColumn<Medicamento, String> colObservacao =
                new TableColumn<>("Observação");

        colObservacao.setCellValueFactory(dado ->
                new SimpleStringProperty(
                        dado.getValue().getObservacao()
                ));

        tabelaMedicamentos.getColumns().addAll(
                colId,
                colNome,
                colDosagem,
                colHorario,
                colObservacao
        );

        tabelaMedicamentos.setPrefHeight(250);

        txtIdMedicamento = new TextField();
        txtIdMedicamento.setPromptText("ID");

        txtNomeMedicamento = new TextField();
        txtNomeMedicamento.setPromptText("Nome do medicamento");

        txtDosagem = new TextField();
        txtDosagem.setPromptText("Dosagem");

        txtHorario = new TextField();
        txtHorario.setPromptText("Horário");

        txtObservacao = new TextField();
        txtObservacao.setPromptText("Observação");

        HBox linha1 = criarLinhaFormulario("ID:", txtIdMedicamento);
        HBox linha2 = criarLinhaFormulario("Nome:", txtNomeMedicamento);
        HBox linha3 = criarLinhaFormulario("Dosagem:", txtDosagem);
        HBox linha4 = criarLinhaFormulario("Horário:", txtHorario);
        HBox linha5 = criarLinhaFormulario("Observação:", txtObservacao);

        Button btnAdicionar = new Button("Adicionar");
        Button btnAtualizar = new Button("Atualizar");
        Button btnExcluir = new Button("Excluir");
        Button btnLimpar = new Button("Limpar");

        btnAdicionar.setOnAction(e -> adicionarMedicamento());
        btnAtualizar.setOnAction(e -> atualizarMedicamento());
        btnExcluir.setOnAction(e -> excluirMedicamento());
        btnLimpar.setOnAction(e -> limparCampos());

        HBox linhaBotoes = new HBox(10);
        linhaBotoes.setAlignment(Pos.CENTER);
        linhaBotoes.getChildren().addAll(
                btnAdicionar,
                btnAtualizar,
                btnExcluir,
                btnLimpar
        );

        tabelaMedicamentos.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, antigo, selecionado) -> {

                    if (selecionado != null) {

                        txtIdMedicamento.setText(
                                String.valueOf(selecionado.getIdMedicamento())
                        );

                        txtNomeMedicamento.setText(
                                selecionado.getNomeMedicamento()
                        );

                        txtDosagem.setText(
                                selecionado.getDosagem()
                        );

                        txtHorario.setText(
                                selecionado.getHorario()
                        );

                        txtObservacao.setText(
                                selecionado.getObservacao()
                        );
                    }
                });

        box.getChildren().addAll(
                lblTitulo,
                linhaBusca,
                tabelaMedicamentos,
                linha1,
                linha2,
                linha3,
                linha4,
                linha5,
                linhaBotoes
        );

        return box;
    }

    private void adicionarMedicamento() {

        try {

            String nome = validarTexto(
                    txtNomeMedicamento.getText(),
                    "Nome"
            );

            String dosagem = validarTexto(
                    txtDosagem.getText(),
                    "Dosagem"
            );

            String horario = validarTexto(
                    txtHorario.getText(),
                    "Horário"
            );

            String observacao = txtObservacao.getText();

            Medicamento medicamento = new Medicamento(
                    MedicamentoRepository.gerarProximoId(),
                    nome,
                    dosagem,
                    horario,
                    observacao
            );

            MedicamentoRepository.adicionar(medicamento);

            atualizarTabela();
            limparCampos();

            mostrarInformacao(
                    "Medicamento cadastrado com sucesso."
            );

        } catch (Exception e) {

            mostrarErro(e.getMessage());
        }
    }

    private void atualizarMedicamento() {

        Medicamento selecionado =
                tabelaMedicamentos.getSelectionModel()
                        .getSelectedItem();

        if (selecionado == null) {

            mostrarErro("Selecione um medicamento.");
            return;
        }

        Medicamento atualizado = new Medicamento(
                selecionado.getIdMedicamento(),
                txtNomeMedicamento.getText(),
                txtDosagem.getText(),
                txtHorario.getText(),
                txtObservacao.getText()
        );

        MedicamentoRepository.atualizar(atualizado);

        atualizarTabela();
        limparCampos();

        mostrarInformacao(
                "Medicamento atualizado com sucesso."
        );
    }

    private void excluirMedicamento() {

        Medicamento selecionado =
                tabelaMedicamentos.getSelectionModel()
                        .getSelectedItem();

        if (selecionado == null) {

            mostrarErro("Selecione um medicamento.");
            return;
        }

        MedicamentoRepository.excluirPorId(
                selecionado.getIdMedicamento()
        );

        atualizarTabela();
        limparCampos();

        mostrarInformacao(
                "Medicamento removido com sucesso."
        );
    }

    private void pesquisarMedicamento() {

        String busca = txtBuscaMedicamento.getText();

        if (busca == null || busca.trim().isEmpty()) {

            atualizarTabela();
            return;
        }

        dadosMedicamentos.setAll(
                MedicamentoRepository.pesquisarPorTitulo(busca)
        );
    }

    private void atualizarTabela() {
        dadosMedicamentos.setAll(
                MedicamentoRepository.listarTodos()
        );
    }

    private void limparCampos() {

        txtIdMedicamento.clear();
        txtNomeMedicamento.clear();
        txtDosagem.clear();
        txtHorario.clear();
        txtObservacao.clear();

        tabelaMedicamentos.getSelectionModel()
                .clearSelection();
    }

    private HBox criarLinhaFormulario(
            String texto,
            Control campo
    ) {

        Label label = new Label(texto);
        label.setPrefWidth(120);

        HBox linha = new HBox(10);
        linha.setAlignment(Pos.CENTER);

        linha.getChildren().addAll(label, campo);

        return linha;
    }

    private String validarTexto(
            String texto,
            String campo
    ) {

        if (texto == null || texto.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "O campo " + campo + " é obrigatório."
            );
        }

        return texto.trim();
    }

    private void mostrarInformacao(String mensagem) {

        Alert alert =
                new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Elderia");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void mostrarErro(String mensagem) {

        Alert alert =
                new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}