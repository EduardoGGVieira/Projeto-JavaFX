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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.List;

public class TelaCertificados {
    private static final DateTimeFormatter FORMATO_DATA =
            DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);

    private TableView<Certificado> tabelaCertificados;
    private ObservableList<Certificado> dadosCertificados;

    private TableView<ValidacaoCertificado> tabelaValidacoes;
    private ObservableList<ValidacaoCertificado> dadosValidacoes;

    private TextField txtIdProfissional;
    private TextField txtTitulo;
    private TextField txtDataEmissao;
    private TextField txtUrlDocumento;
    private TextField txtBuscaCertificado;

    private TextField txtIdCertificadoValidacao;
    private ComboBox<String> comboStatus;
    private TextField txtDataValidacao;
    private TextField txtResponsavel;
    private TextArea txtObservacao;
    private TextField txtBuscaValidacao;

    public Scene criarCena(Stage stage, Scene cenaAnterior) {
        Label titulo = new Label("Área de Certificados");
        titulo.setFont(new Font("Arial", 30));
        titulo.setAlignment(Pos.TOP_CENTER);

        TabPane abas = new TabPane();

        Tab abaCertificados = new Tab("Certificados");
        abaCertificados.setClosable(false);
        abaCertificados.setContent(criarAbaCertificados());

        Tab abaValidacoes = new Tab("Validações");
        abaValidacoes.setClosable(false);
        abaValidacoes.setContent(criarAbaValidacoes());

        abas.getTabs().addAll(abaCertificados, abaValidacoes);

        Button btnVoltar = new Button("Voltar");
        btnVoltar.setOnAction(event -> stage.setScene(cenaAnterior));

        VBox layout = new VBox();
        layout.setSpacing(15);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setStyle("-fx-background-color: #f2f2f2;");
        layout.getChildren().addAll(titulo, abas, btnVoltar);

        atualizarTabelas();

        return new Scene(layout, 1000, 750);
    }

    private VBox criarAbaCertificados() {
        VBox box = new VBox();
        box.setSpacing(12);
        box.setPadding(new Insets(15));
        box.setAlignment(Pos.TOP_CENTER);

        Label lblTitulo = new Label("Certificados");
        lblTitulo.setFont(new Font("Arial", 22));

        txtBuscaCertificado = new TextField();
        txtBuscaCertificado.setPromptText("Pesquisar certificado por título");
        txtBuscaCertificado.setMaxWidth(300);

        Button btnPesquisar = new Button("Pesquisar");
        btnPesquisar.setOnAction(event -> pesquisarCertificado());

        Button btnMostrarTodos = new Button("Mostrar Todos");
        btnMostrarTodos.setOnAction(event -> {
            dadosCertificados.setAll(CertificadoRepository.listarTodos());
        });

        HBox linhaBusca = new HBox(10);
        linhaBusca.setAlignment(Pos.CENTER);
        linhaBusca.getChildren().addAll(txtBuscaCertificado, btnPesquisar, btnMostrarTodos);

        tabelaCertificados = new TableView<>();
        dadosCertificados = FXCollections.observableArrayList();
        tabelaCertificados.setItems(dadosCertificados);
        tabelaCertificados.setPrefHeight(260);

        TableColumn<Certificado, String> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(dado ->
                new SimpleStringProperty(String.valueOf(dado.getValue().getIdCertificado())));
        colId.setPrefWidth(60);

        TableColumn<Certificado, String> colProfissional = new TableColumn<>("ID Profissional");
        colProfissional.setCellValueFactory(dado ->
                new SimpleStringProperty(String.valueOf(dado.getValue().getIdProfissional())));
        colProfissional.setPrefWidth(120);

        TableColumn<Certificado, String> colTitulo = new TableColumn<>("Título");
        colTitulo.setCellValueFactory(dado ->
                new SimpleStringProperty(dado.getValue().getTitulo()));
        colTitulo.setPrefWidth(220);

        TableColumn<Certificado, String> colData = new TableColumn<>("Data de Emissão");
        colData.setCellValueFactory(dado ->
                new SimpleStringProperty(formatarData(dado.getValue().getDataEmissao())));
        colData.setPrefWidth(140);

        TableColumn<Certificado, String> colUrl = new TableColumn<>("Documento");
        colUrl.setCellValueFactory(dado ->
                new SimpleStringProperty(dado.getValue().getUrlDocumento()));
        colUrl.setPrefWidth(300);

        tabelaCertificados.getColumns().addAll(colId, colProfissional, colTitulo, colData, colUrl);

        txtIdProfissional = new TextField();
        txtIdProfissional.setPromptText("ID do profissional");
        txtIdProfissional.setMaxWidth(250);

        txtTitulo = new TextField();
        txtTitulo.setPromptText("Título do certificado");
        txtTitulo.setMaxWidth(250);

        txtDataEmissao = new TextField();
        txtDataEmissao.setPromptText("Data de emissão: DD/MM/AAAA");
        txtDataEmissao.setMaxWidth(250);

        txtUrlDocumento = new TextField();
        txtUrlDocumento.setPromptText("Caminho do documento PDF");
        txtUrlDocumento.setMaxWidth(250);

        Button btnAdicionar = new Button("Adicionar");
        Button btnAtualizar = new Button("Atualizar");
        Button btnExcluir = new Button("Excluir");
        Button btnLimpar = new Button("Limpar");

        btnAdicionar.setOnAction(event -> adicionarCertificado());
        btnAtualizar.setOnAction(event -> atualizarCertificado());
        btnExcluir.setOnAction(event -> excluirCertificado());
        btnLimpar.setOnAction(event -> limparCamposCertificado());

        HBox linha1 = criarLinhaFormulario("ID Profissional:", txtIdProfissional);
        HBox linha2 = criarLinhaFormulario("Título:", txtTitulo);
        HBox linha3 = criarLinhaFormulario("Data Emissão:", txtDataEmissao);
        HBox linha4 = criarLinhaFormulario("Documento:", txtUrlDocumento);

        HBox linhaBotoes = new HBox(10);
        linhaBotoes.setAlignment(Pos.CENTER);
        linhaBotoes.getChildren().addAll(btnAdicionar, btnAtualizar, btnExcluir, btnLimpar);

        tabelaCertificados.getSelectionModel().selectedItemProperty().addListener((obs, antigo, selecionado) -> {
            if (selecionado != null) {
                txtIdProfissional.setText(String.valueOf(selecionado.getIdProfissional()));
                txtTitulo.setText(selecionado.getTitulo());
                txtDataEmissao.setText(formatarData(selecionado.getDataEmissao()));
                txtUrlDocumento.setText(selecionado.getUrlDocumento());
            }
        });

        box.getChildren().addAll(lblTitulo, linhaBusca, tabelaCertificados, linha1, linha2, linha3, linha4, linhaBotoes);

        return box;
    }

    private VBox criarAbaValidacoes() {
        VBox box = new VBox();
        box.setSpacing(12);
        box.setPadding(new Insets(15));
        box.setAlignment(Pos.TOP_CENTER);

        Label lblTitulo = new Label("Validações de Certificados");
        lblTitulo.setFont(new Font("Arial", 22));

        txtBuscaValidacao = new TextField();
        txtBuscaValidacao.setPromptText("Pesquisar validação por status");
        txtBuscaValidacao.setMaxWidth(300);

        Button btnPesquisar = new Button("Pesquisar");
        btnPesquisar.setOnAction(event -> pesquisarValidacao());

        Button btnMostrarTodos = new Button("Mostrar Todos");
        btnMostrarTodos.setOnAction(event -> {
            dadosValidacoes.setAll(ValidacaoCertificadoRepository.listarTodos());
        });

        HBox linhaBusca = new HBox(10);
        linhaBusca.setAlignment(Pos.CENTER);
        linhaBusca.getChildren().addAll(txtBuscaValidacao, btnPesquisar, btnMostrarTodos);

        tabelaValidacoes = new TableView<>();
        dadosValidacoes = FXCollections.observableArrayList();
        tabelaValidacoes.setItems(dadosValidacoes);
        tabelaValidacoes.setPrefHeight(260);

        TableColumn<ValidacaoCertificado, String> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(dado ->
                new SimpleStringProperty(String.valueOf(dado.getValue().getIdValidacao())));
        colId.setPrefWidth(60);

        TableColumn<ValidacaoCertificado, String> colCertificado = new TableColumn<>("ID Certificado");
        colCertificado.setCellValueFactory(dado ->
                new SimpleStringProperty(String.valueOf(dado.getValue().getIdCertificado())));
        colCertificado.setPrefWidth(120);

        TableColumn<ValidacaoCertificado, String> colStatus = new TableColumn<>("Status");
        colStatus.setCellValueFactory(dado ->
                new SimpleStringProperty(dado.getValue().getStatus()));
        colStatus.setPrefWidth(120);

        TableColumn<ValidacaoCertificado, String> colData = new TableColumn<>("Data Validação");
        colData.setCellValueFactory(dado ->
                new SimpleStringProperty(formatarData(dado.getValue().getDataValidacao())));
        colData.setPrefWidth(140);

        TableColumn<ValidacaoCertificado, String> colResponsavel = new TableColumn<>("Responsável");
        colResponsavel.setCellValueFactory(dado ->
                new SimpleStringProperty(dado.getValue().getResponsavel()));
        colResponsavel.setPrefWidth(180);

        TableColumn<ValidacaoCertificado, String> colObservacao = new TableColumn<>("Observação");
        colObservacao.setCellValueFactory(dado ->
                new SimpleStringProperty(dado.getValue().getObservacao()));
        colObservacao.setPrefWidth(320);

        tabelaValidacoes.getColumns().addAll(colId, colCertificado, colStatus, colData, colResponsavel, colObservacao);

        txtIdCertificadoValidacao = new TextField();
        txtIdCertificadoValidacao.setPromptText("ID do certificado");
        txtIdCertificadoValidacao.setMaxWidth(250);

        comboStatus = new ComboBox<>();
        comboStatus.getItems().addAll("Pendente", "Aprovado", "Recusado");
        comboStatus.setValue("Pendente");
        comboStatus.setMaxWidth(250);

        txtDataValidacao = new TextField();
        txtDataValidacao.setPromptText("Data da validação: DD/MM/AAAA");
        txtDataValidacao.setMaxWidth(250);

        txtResponsavel = new TextField();
        txtResponsavel.setPromptText("Nome do responsável");
        txtResponsavel.setMaxWidth(250);

        txtObservacao = new TextArea();
        txtObservacao.setPromptText("Observação da validação");
        txtObservacao.setMaxWidth(250);
        txtObservacao.setPrefRowCount(3);

        Button btnAdicionar = new Button("Adicionar");
        Button btnAtualizar = new Button("Atualizar");
        Button btnExcluir = new Button("Excluir");
        Button btnLimpar = new Button("Limpar");

        btnAdicionar.setOnAction(event -> adicionarValidacao());
        btnAtualizar.setOnAction(event -> atualizarValidacao());
        btnExcluir.setOnAction(event -> excluirValidacao());
        btnLimpar.setOnAction(event -> limparCamposValidacao());

        HBox linha1 = criarLinhaFormulario("ID Certificado:", txtIdCertificadoValidacao);
        HBox linha2 = criarLinhaFormulario("Status:", comboStatus);
        HBox linha3 = criarLinhaFormulario("Data Validação:", txtDataValidacao);
        HBox linha4 = criarLinhaFormulario("Responsável:", txtResponsavel);
        HBox linha5 = criarLinhaFormulario("Observação:", txtObservacao);

        HBox linhaBotoes = new HBox(10);
        linhaBotoes.setAlignment(Pos.CENTER);
        linhaBotoes.getChildren().addAll(btnAdicionar, btnAtualizar, btnExcluir, btnLimpar);

        tabelaValidacoes.getSelectionModel().selectedItemProperty().addListener((obs, antigo, selecionado) -> {
            if (selecionado != null) {
                txtIdCertificadoValidacao.setText(String.valueOf(selecionado.getIdCertificado()));
                comboStatus.setValue(selecionado.getStatus());
                txtDataValidacao.setText(formatarData(selecionado.getDataValidacao()));
                txtResponsavel.setText(selecionado.getResponsavel());
                txtObservacao.setText(selecionado.getObservacao());
            }
        });

        box.getChildren().addAll(lblTitulo, linhaBusca, tabelaValidacoes, linha1, linha2, linha3, linha4, linha5, linhaBotoes);

        return box;
    }

    private HBox criarLinhaFormulario(String textoLabel, Control campo) {
        Label label = new Label(textoLabel);
        label.setPrefWidth(130);

        HBox linha = new HBox(10);
        linha.setAlignment(Pos.CENTER);
        linha.getChildren().addAll(label, campo);

        return linha;
    }

    private void adicionarCertificado() {
        try {
            int idProfissional = validarInteiroPositivo(txtIdProfissional.getText(), "ID do profissional");
            String titulo = validarTexto(txtTitulo.getText(), "título");
            LocalDate dataEmissao = validarData(txtDataEmissao.getText(), "data de emissão");
            String urlDocumento = validarTexto(txtUrlDocumento.getText(), "documento");

            Certificado certificado = new Certificado(
                    CertificadoRepository.gerarProximoId(),
                    idProfissional,
                    titulo,
                    dataEmissao,
                    urlDocumento
            );

            if (!certificado.validarExtensaoPdf()) {
                throw new IllegalArgumentException("O documento precisa terminar com .pdf");
            }

            CertificadoRepository.adicionar(certificado);

            ValidacaoCertificado validacaoAutomatica = new ValidacaoCertificado(
                    ValidacaoCertificadoRepository.gerarProximoId(),
                    certificado.getIdCertificado(),
                    "Pendente",
                    LocalDate.now(),
                    "Aguardando análise",
                    "Validação criada automaticamente ao cadastrar o certificado."
            );

            ValidacaoCertificadoRepository.adicionar(validacaoAutomatica);

            atualizarTabelas();
            limparCamposCertificado();

            mostrarInformacao("Certificado cadastrado com sucesso. Uma validação pendente foi criada automaticamente.");

        } catch (IllegalArgumentException e) {
            mostrarErro(e.getMessage());
        } catch (Exception e) {
            mostrarErro("Erro inesperado ao adicionar certificado: " + e.getMessage());
        }
    }

    private void atualizarCertificado() {
        try {
            Certificado selecionado = tabelaCertificados.getSelectionModel().getSelectedItem();

            if (selecionado == null) {
                throw new IllegalArgumentException("Selecione um certificado na tabela.");
            }

            int idProfissional = validarInteiroPositivo(txtIdProfissional.getText(), "ID do profissional");
            String titulo = validarTexto(txtTitulo.getText(), "título");
            LocalDate dataEmissao = validarData(txtDataEmissao.getText(), "data de emissão");
            String urlDocumento = validarTexto(txtUrlDocumento.getText(), "documento");

            Certificado certificadoAtualizado = new Certificado(
                    selecionado.getIdCertificado(),
                    idProfissional,
                    titulo,
                    dataEmissao,
                    urlDocumento
            );

            if (!certificadoAtualizado.validarExtensaoPdf()) {
                throw new IllegalArgumentException("O documento precisa terminar com .pdf");
            }

            CertificadoRepository.atualizar(certificadoAtualizado);
            atualizarTabelas();
            limparCamposCertificado();
            mostrarInformacao("Certificado atualizado com sucesso.");

        } catch (IllegalArgumentException e) {
            mostrarErro(e.getMessage());
        } catch (Exception e) {
            mostrarErro("Erro inesperado ao atualizar certificado: " + e.getMessage());
        }
    }

    private void excluirCertificado() {
        try {
            Certificado selecionado = tabelaCertificados.getSelectionModel().getSelectedItem();

            if (selecionado == null) {
                throw new IllegalArgumentException("Selecione um certificado na tabela.");
            }

            CertificadoRepository.excluirPorId(selecionado.getIdCertificado());
            atualizarTabelas();
            limparCamposCertificado();
            mostrarInformacao("Certificado excluído com sucesso.");

        } catch (IllegalArgumentException e) {
            mostrarErro(e.getMessage());
        } catch (Exception e) {
            mostrarErro("Erro inesperado ao excluir certificado: " + e.getMessage());
        }
    }

    private void pesquisarCertificado() {
        try {
            String busca = txtBuscaCertificado.getText().trim();

            if (busca.isEmpty()) {
                dadosCertificados.setAll(CertificadoRepository.listarTodos());
            } else {
                dadosCertificados.setAll(CertificadoRepository.pesquisarPorTitulo(busca));
            }

        } catch (Exception e) {
            mostrarErro("Erro ao pesquisar certificado: " + e.getMessage());
        }
    }

    private void adicionarValidacao() {
        try {
            int idCertificado = validarInteiroPositivo(txtIdCertificadoValidacao.getText(), "ID do certificado");
            validarCertificadoExistente(idCertificado);

            String status = validarTexto(comboStatus.getValue(), "status");
            LocalDate dataValidacao = validarData(txtDataValidacao.getText(), "data de validação");
            String responsavel = validarTexto(txtResponsavel.getText(), "responsável");
            String observacao = validarTexto(txtObservacao.getText(), "observação");

            ValidacaoCertificado validacao = new ValidacaoCertificado(
                    ValidacaoCertificadoRepository.gerarProximoId(),
                    idCertificado,
                    status,
                    dataValidacao,
                    responsavel,
                    observacao
            );

            ValidacaoCertificadoRepository.adicionar(validacao);
            atualizarTabelas();
            limparCamposValidacao();
            mostrarInformacao("Validação cadastrada com sucesso.");

        } catch (IllegalArgumentException e) {
            mostrarErro(e.getMessage());
        } catch (Exception e) {
            mostrarErro("Erro inesperado ao adicionar validação: " + e.getMessage());
        }
    }

    private void atualizarValidacao() {
        try {
            ValidacaoCertificado selecionada = tabelaValidacoes.getSelectionModel().getSelectedItem();

            if (selecionada == null) {
                throw new IllegalArgumentException("Selecione uma validação na tabela.");
            }

            int idCertificado = validarInteiroPositivo(txtIdCertificadoValidacao.getText(), "ID do certificado");
            validarCertificadoExistente(idCertificado);

            String status = validarTexto(comboStatus.getValue(), "status");
            LocalDate dataValidacao = validarData(txtDataValidacao.getText(), "data de validação");
            String responsavel = validarTexto(txtResponsavel.getText(), "responsável");
            String observacao = validarTexto(txtObservacao.getText(), "observação");

            ValidacaoCertificado validacaoAtualizada = new ValidacaoCertificado(
                    selecionada.getIdValidacao(),
                    idCertificado,
                    status,
                    dataValidacao,
                    responsavel,
                    observacao
            );

            ValidacaoCertificadoRepository.atualizar(validacaoAtualizada);
            atualizarTabelas();
            limparCamposValidacao();
            mostrarInformacao("Validação atualizada com sucesso.");

        } catch (IllegalArgumentException e) {
            mostrarErro(e.getMessage());
        } catch (Exception e) {
            mostrarErro("Erro inesperado ao atualizar validação: " + e.getMessage());
        }
    }

    private void excluirValidacao() {
        try {
            ValidacaoCertificado selecionada = tabelaValidacoes.getSelectionModel().getSelectedItem();

            if (selecionada == null) {
                throw new IllegalArgumentException("Selecione uma validação na tabela.");
            }

            ValidacaoCertificadoRepository.excluirPorId(selecionada.getIdValidacao());
            atualizarTabelas();
            limparCamposValidacao();
            mostrarInformacao("Validação excluída com sucesso.");

        } catch (IllegalArgumentException e) {
            mostrarErro(e.getMessage());
        } catch (Exception e) {
            mostrarErro("Erro inesperado ao excluir validação: " + e.getMessage());
        }
    }

    private void pesquisarValidacao() {
        try {
            String busca = txtBuscaValidacao.getText().trim();

            if (busca.isEmpty()) {
                dadosValidacoes.setAll(ValidacaoCertificadoRepository.listarTodos());
            } else {
                dadosValidacoes.setAll(ValidacaoCertificadoRepository.pesquisarPorStatus(busca));
            }

        } catch (Exception e) {
            mostrarErro("Erro ao pesquisar validação: " + e.getMessage());
        }
    }

    private void atualizarTabelas() {
        if (dadosCertificados != null) {
            dadosCertificados.setAll(CertificadoRepository.listarTodos());
        }

        if (dadosValidacoes != null) {
            dadosValidacoes.setAll(ValidacaoCertificadoRepository.listarTodos());
        }
    }

    private void limparCamposCertificado() {
        txtIdProfissional.clear();
        txtTitulo.clear();
        txtDataEmissao.clear();
        txtUrlDocumento.clear();
        tabelaCertificados.getSelectionModel().clearSelection();
    }

    private void limparCamposValidacao() {
        txtIdCertificadoValidacao.clear();
        comboStatus.setValue("Pendente");
        txtDataValidacao.clear();
        txtResponsavel.clear();
        txtObservacao.clear();
        tabelaValidacoes.getSelectionModel().clearSelection();
    }

    private int validarInteiroPositivo(String texto, String nomeCampo) {
        if (texto == null || texto.trim().isEmpty()) {
            throw new IllegalArgumentException("O campo " + nomeCampo + " é obrigatório.");
        }

        try {
            int numero = Integer.parseInt(texto.trim());

            if (numero <= 0) {
                throw new IllegalArgumentException("O campo " + nomeCampo + " precisa ser maior que zero.");
            }

            return numero;

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("O campo " + nomeCampo + " precisa ser um número inteiro.");
        }
    }

    private String validarTexto(String texto, String nomeCampo) {
        if (texto == null || texto.trim().isEmpty()) {
            throw new IllegalArgumentException("O campo " + nomeCampo + " é obrigatório.");
        }

        return texto.trim();
    }

    private LocalDate validarData(String texto, String nomeCampo) {
        if (texto == null || texto.trim().isEmpty()) {
            throw new IllegalArgumentException("O campo " + nomeCampo + " é obrigatório.");
        }

        try {
            return LocalDate.parse(texto.trim(), FORMATO_DATA);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("O campo " + nomeCampo + " deve estar no formato DD/MM/AAAA.");
        }
    }

    private void validarCertificadoExistente(int idCertificado) {
        Certificado certificado = CertificadoRepository.buscarPorId(idCertificado);

        if (certificado == null) {
            throw new IllegalArgumentException("Não existe certificado cadastrado com esse ID.");
        }
    }

    private String formatarData(LocalDate data) {
        if (data == null) {
            return "";
        }

        return data.format(FORMATO_DATA);
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