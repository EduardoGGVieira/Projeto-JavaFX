package elderia.com.elderia;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

import java.util.ArrayList;
import java.util.List;

public class TelaProntuarios {

    //
    private TableView<Prontuario> tabelaProntuarios;
    private ObservableList<Prontuario> dadosProntuarios;

    // texto do prontuario
    private TextField txtNomePaciente;
    private TextField txtDataRegistro;
    private TextField txtAlergias;
    private TextField txtMedicamentos;
    private TextArea txtObservacoes;
    private TextField txtBuscaProntuario;

    // btn de salvar os dados DO PRONTUARIO
    private Button btnSalvarProntuario;

    public Scene criarCena(Stage stage, Scene cenaAnterior) {
        Label titulo = new Label("Área de Prontuários Clínicos");
        titulo.setFont(new Font("Arial", 30));
        titulo.setAlignment(Pos.TOP_CENTER);

        GridPane formulario = new GridPane();
        formulario.setHgap(10);
        formulario.setVgap(12);
        formulario.setAlignment(Pos.CENTER);

        txtNomePaciente = new TextField();
        txtNomePaciente.setPromptText("Ex: Eduardo Guilherme");
        txtNomePaciente.setPrefWidth(250);

        txtDataRegistro = new TextField();
        txtDataRegistro.setPromptText("Ex: 16/06/2026");
        txtDataRegistro.setPrefWidth(250);

        txtAlergias = new TextField();
        txtAlergias.setPromptText("Ex: Dipirona, Corantes");
        txtAlergias.setPrefWidth(250);

        txtMedicamentos = new TextField();
        txtMedicamentos.setPromptText("Ex: Insulina, Vitaminas");
        txtMedicamentos.setPrefWidth(250);

        txtObservacoes = new TextArea();
        txtObservacoes.setPromptText("Descreva o histórico clínico...");
        txtObservacoes.setPrefWidth(250);
        txtObservacoes.setPrefRowCount(3);

        formulario.add(new Label("Nome do Paciente:"), 0, 0);
        formulario.add(txtNomePaciente, 1, 0);
        formulario.add(new Label("Data Registro:"), 0, 1);
        formulario.add(txtDataRegistro, 1, 1);
        formulario.add(new Label("Alergias:"), 0, 2);
        formulario.add(txtAlergias, 1, 2);
        formulario.add(new Label("Medicamentos:"), 0, 3);
        formulario.add(txtMedicamentos, 1, 3);
        formulario.add(new Label("Observações:"), 0, 4);
        formulario.add(txtObservacoes, 1, 4);

        // Botão de ações
        btnSalvarProntuario = new Button("Confirmar Cadastro");
        Button btnLimpar = new Button("Limpar");
        Button btnVoltar = new Button("Voltar");

        btnSalvarProntuario.setPrefWidth(180);
        btnLimpar.setPrefWidth(180);
        btnVoltar.setPrefWidth(180);

        btnSalvarProntuario.setOnAction(event -> salvarProntuario());
        btnLimpar.setOnAction(event -> limparCampos());
        btnVoltar.setOnAction(event -> {
            limparCampos();
            stage.setScene(cenaAnterior);
        });

        HBox linhaBotoes = new HBox(10);
        linhaBotoes.setAlignment(Pos.CENTER);
        linhaBotoes.getChildren().addAll(btnSalvarProntuario, btnVoltar);



        txtBuscaProntuario = new TextField();
        txtBuscaProntuario.setPromptText("Pesquisar paciente por nome");
        txtBuscaProntuario.setPrefWidth(300);

        Button btnPesquisar = new Button("Pesquisar");
        btnPesquisar.setOnAction(event -> pesquisarProntuario());

        Button btnMostrarTodos = new Button("Mostrar Todos");
        btnMostrarTodos.setOnAction(event -> {
            dadosProntuarios.setAll(ProntuarioRepository.listarTodos());
        });

        HBox linhaBusca = new HBox(10);
        linhaBusca.setAlignment(Pos.CENTER);
        linhaBusca.getChildren().addAll(txtBuscaProntuario, btnPesquisar, btnMostrarTodos);

        tabelaProntuarios = new TableView<>();
        dadosProntuarios = FXCollections.observableArrayList();
        tabelaProntuarios.setItems(dadosProntuarios);
        tabelaProntuarios.setPrefHeight(260);

        TableColumn<Prontuario, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("idProntuario"));
        colId.setPrefWidth(60);

        TableColumn<Prontuario, String> colPaciente = new TableColumn<>("Paciente");
        colPaciente.setCellValueFactory(new PropertyValueFactory<>("nomePaciente"));
        colPaciente.setPrefWidth(180);

        TableColumn<Prontuario, String> colData = new TableColumn<>("Data Registro");
        colData.setCellValueFactory(new PropertyValueFactory<>("dataRegistro"));
        colData.setPrefWidth(120);

        TableColumn<Prontuario, String> colAlergias = new TableColumn<>("Alergias");
        colAlergias.setCellValueFactory(new PropertyValueFactory<>("alergias"));
        colAlergias.setPrefWidth(140);

        TableColumn<Prontuario, String> colMedicamentos = new TableColumn<>("Medicamentos");
        colMedicamentos.setCellValueFactory(new PropertyValueFactory<>("medicamentos"));
        colMedicamentos.setPrefWidth(140);

        TableColumn<Prontuario, String> colObs = new TableColumn<>("Observações");
        colObs.setCellValueFactory(new PropertyValueFactory<>("observacoes"));
        colObs.setPrefWidth(220);

        // Coluna de Editar integrada na própria tabela com a cor verde usada no elderia.java
        TableColumn<Prontuario, Void> colEditar = new TableColumn<>("Editar");
        colEditar.setPrefWidth(90);
        colEditar.setCellFactory(param -> new TableCell<Prontuario, Void>() {
            private final Button btnEditarVisual = new Button("Editar");
            {
                btnEditarVisual.setStyle("-fx-background-color: #5cb85c; -fx-text-fill: white; -fx-font-weight: bold;");
                btnEditarVisual.setOnAction(event -> {
                    Prontuario selecionado = getTableView().getItems().get(getIndex());
                    if (selecionado != null) {
                        txtNomePaciente.setText(selecionado.getNomePaciente());
                        txtDataRegistro.setText(selecionado.getDataRegistro());
                        txtAlergias.setText(selecionado.getAlergias());
                        txtMedicamentos.setText(selecionado.getMedicamentos());
                        txtObservacoes.setText(selecionado.getObservacoes());

                        btnSalvarProntuario.setUserData(Integer.valueOf(selecionado.getIdProntuario()));
                        btnSalvarProntuario.setText("Salvar Alterações");
                    }
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btnEditarVisual);
                setAlignment(Pos.CENTER);
            }
        });

        //  Coluna de Deletar integrada na própria tabela em vermelho igual no elderia.java
        TableColumn<Prontuario, Void> colDeletar = new TableColumn<>("Deletar");
        colDeletar.setPrefWidth(90);
        colDeletar.setCellFactory(param -> new TableCell<Prontuario, Void>() {
            private final Button btnDeletarVisual = new Button("Deletar");
            {
                btnDeletarVisual.setStyle("-fx-background-color: #d9534f; -fx-text-fill: white; -fx-font-weight: bold;");
                btnDeletarVisual.setOnAction(event -> {
                    Prontuario selecionado = getTableView().getItems().get(getIndex());
                    if (selecionado != null) {
                        try {
                            dadosProntuarios.remove(selecionado);
                            List<Prontuario> listaCompleta = ProntuarioRepository.listarTodos();
                            listaCompleta.removeIf(p -> p.getIdProntuario() == selecionado.getIdProntuario());
                            ProntuarioRepository.salvarTodos(listaCompleta);
                            System.out.println("\n=== PRONTUÁRIO EXCLUÍDO COM SUCESSO ===");
                            System.out.println("Paciente: " + selecionado.getNomePaciente() + " | ID: " + selecionado.getIdProntuario());
                        } catch (Exception ex) {
                            System.err.println("Erro crítico ao excluir prontuário: " + ex.getMessage());
                        }
                    }
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btnDeletarVisual);
                setAlignment(Pos.CENTER);
            }
        });

        tabelaProntuarios.getColumns().addAll(colId, colPaciente, colData, colAlergias, colMedicamentos, colObs, colEditar, colDeletar);


        VBox layout = new VBox();
        layout.setSpacing(15);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setStyle("-fx-background-color: #f2f2f2;");

        layout.getChildren().addAll(titulo, formulario, linhaBotoes, new Separator(), linhaBusca, tabelaProntuarios);

        dadosProntuarios.setAll(ProntuarioRepository.listarTodos());

        return new Scene(layout, 1100, 750);
    }

    private void salvarProntuario() {
        try {
            String paciente = validarTexto(txtNomePaciente.getText(), "nome do paciente");
            String data = validarTexto(txtDataRegistro.getText(), "data registro");
            String alergias = txtAlergias.getText().trim();
            String medicamentos = txtMedicamentos.getText().trim();
            String obs = txtObservacoes.getText().trim();

            List<Prontuario> listaAtual = ProntuarioRepository.listarTodos();

            if (btnSalvarProntuario.getUserData() != null) {
                //pega as informações passadas anteriormente e salva as substitui
                int idEditar = (int) btnSalvarProntuario.getUserData();
                for (Prontuario p : listaAtual) {
                    if (p.getIdProntuario() == idEditar) {
                        p.setNomePaciente(paciente);
                        p.setDataRegistro(data);
                        p.setAlergias(alergias);
                        p.setMedicamentos(medicamentos);
                        p.setObservacoes(obs);
                        break;
                    }
                }
                System.out.println("\n=== PRONTUÁRIO ALTERADO COM SUCESSO VIA OPERAÇÃO HÍBRIDA ===");
                System.out.println("Paciente: " + paciente + " | ID: " + idEditar);
            } else {
                //pra novos cadastros
                int novoId = listaAtual.size() + 1;
                Prontuario novo = new Prontuario(novoId, paciente, data, alergias, medicamentos, obs);
                listaAtual.add(novo);
                System.out.println("\n=== NOVO PRONTUÁRIO GRAVADO NO ARQUIVO .DAT ===");
                System.out.println("Paciente: " + paciente + " | Novo ID Gerado: " + novoId);
            }

            ProntuarioRepository.salvarTodos(listaAtual);
            dadosProntuarios.setAll(ProntuarioRepository.listarTodos());
            limparCampos();


        } catch (IllegalArgumentException e) {
            // pega erros de validação e mostra o erro no terminal
            System.err.println("Erro de Validação de Prontuário: " + e.getMessage());
        } catch (Exception e) {
            // pega falhas inesperadas e mostra o erro no termianl
            System.err.println("Erro inesperado do sistema ao processar prontuário: " + e.getMessage());
        }
    }

    private void pesquisarProntuario() {
        try {
            String busca = txtBuscaProntuario.getText().trim().toLowerCase();
            List<Prontuario> todos = ProntuarioRepository.listarTodos();

            if (busca.isEmpty()) {
                dadosProntuarios.setAll(todos);
                System.out.println("Pesquisa vazia: mostrando todos os registros.");
            } else {
                List<Prontuario> filtrados = new ArrayList<>();
                for (Prontuario p : todos) {
                    if (p.getNomePaciente().toLowerCase().contains(busca)) {
                        filtrados.add(p);
                    }
                }
                dadosProntuarios.setAll(filtrados);
                System.out.println("Pesquisa executada: encontrados " + filtrados.size() + " resultados para '" + busca + "'.");
            }
        } catch (Exception e) {
            System.err.println("Erro crítico ao realizar pesquisa de prontuários: " + e.getMessage());
        }
    }

    private void limparCampos() {
        txtNomePaciente.clear();
        txtDataRegistro.clear();
        txtAlergias.clear();
        txtMedicamentos.clear();
        txtObservacoes.clear();
        txtBuscaProntuario.clear();

        btnSalvarProntuario.setUserData(null);
        btnSalvarProntuario.setText("Confirmar Cadastro");
        tabelaProntuarios.getSelectionModel().clearSelection();
        System.out.println("Formulário de prontuários limpo e resetado com sucesso.");
    }

    private String validarTexto(String texto, String nomeCampo) {
        if (texto == null || texto.trim().isEmpty()) {
            throw new IllegalArgumentException("Ta faltando coisa ALI amigão.");
        }
        return texto.trim();
    }
}