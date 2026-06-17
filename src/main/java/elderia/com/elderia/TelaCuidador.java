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

public class TelaCuidador {

    //Cria os atributos das tabelas.
    private TableView<Cuidador> tabelaCuidador;
    private ObservableList<Cuidador> dadosCuidador;

    //cria os TXT
    TextField txtNomeCuidador;
    TextField txtIdosoResponsavel;
    TextField txtCPFCuidador;
    TextField txtBuscaCuidador;

    Button btnSalvarCuidador;

    public Scene criarCena(Stage stage, Scene cenaAnterior) {
        Label tituloCuidador = new Label("Cadastre-se como um Cuidador");
        tituloCuidador.setFont(new Font("Arial", 30));

        //subtítulo
        Label lblCuidadorCadastroInfo = new Label("Aqui você pode se cadastrar como cuidador de um idoso no site:");
        lblCuidadorCadastroInfo.setFont(new Font("Arial", 14));

        GridPane formularioCuidador = new GridPane();
        formularioCuidador.setHgap(10);
        formularioCuidador.setVgap(12);
        formularioCuidador.setAlignment(Pos.CENTER);

        txtNomeCuidador = new TextField();
        txtNomeCuidador.setPromptText("Ex: Jose da Silva Sauro");
        txtNomeCuidador.setPrefWidth(250);

        txtIdosoResponsavel = new TextField();
        txtIdosoResponsavel.setPromptText("Ex: Alberto Oliveira");
        txtIdosoResponsavel.setPrefWidth(250);

        txtCPFCuidador = new TextField();
        txtCPFCuidador.setPromptText("Ex: 167.169.420-67");
        txtCPFCuidador.setPrefWidth(250);

        formularioCuidador.add(new Label("Nome do Cuidador:"), 0, 0);
        formularioCuidador.add(txtNomeCuidador, 1, 0);
        formularioCuidador.add(new Label("Nome do Idoso que você é responsável:"), 0, 1);
        formularioCuidador.add(txtIdosoResponsavel, 1, 1);
        formularioCuidador.add(new Label("CPF:"), 0, 2);
        formularioCuidador.add(txtCPFCuidador, 1, 2);

        //butaos
        Button btnVoltar = new Button("Voltar");
        btnSalvarCuidador = new Button("Confirmar Cadastro");
        Button btnLimpar = new Button("Limpar");

        btnSalvarCuidador.setPrefWidth(180);
        btnLimpar.setPrefWidth(180);
        btnVoltar.setPrefWidth(180);

        //ta dando erro no salvar agr mas eu vou criar depois (pro artur do futuro(o de 30 min do futuro))
        btnSalvarCuidador.setOnAction(event -> salvarCuidador());
        btnLimpar.setOnAction(event -> limparCamposCuidador());
        btnVoltar.setOnAction(event -> {
            limparCamposCuidador();
            stage.setScene(cenaAnterior);
        });

        HBox linhaBotoes = new HBox(15);
        linhaBotoes.setAlignment(Pos.CENTER);
        linhaBotoes.getChildren().addAll(btnSalvarCuidador, btnVoltar);


        //nao sei se vou manter isso
        txtBuscaCuidador = new TextField();
        txtBuscaCuidador.setPromptText("Pesquisar Cuidadores pelo seu nome");
        txtBuscaCuidador.setPrefWidth(300);

        Button btnPesquisarCuidador = new Button("Pesquisar");
        btnPesquisarCuidador.setOnAction(event -> pesquisarCuidador());

        Button btnMostrarTodosCuidadores = new Button("Mostrar Todos os Cuidadores");
        btnMostrarTodosCuidadores.setOnAction(event -> {
            dadosCuidador.setAll(CuidadorRepository.listarTodos());
        });

        //define o tamanho do input de busca e puxa os buttons e o txt de busca.
        //EU BOTEI LINDADEBUSCA AAAAAAAAAAAAAAAA >:(
        HBox linhaDeBusca = new HBox(10);
        linhaDeBusca.setAlignment(Pos.CENTER);
        linhaDeBusca.getChildren().addAll(txtBuscaCuidador, btnPesquisarCuidador, btnMostrarTodosCuidadores);

        //cria a tabela e puxa os dados do arraylist do cuidador
        tabelaCuidador = new TableView<>();

        dadosCuidador = FXCollections.observableArrayList();

        tabelaCuidador.setItems(dadosCuidador);
        //ainda nao sei se eu gosto desse tamanho
        tabelaCuidador.setPrefHeight(280);

        TableColumn<Cuidador, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("idCuidador"));
        colId.setPrefWidth(70);

        TableColumn<Cuidador, String> colnomeCuidador = new TableColumn<>("Nome");
        colnomeCuidador.setCellValueFactory(new PropertyValueFactory<>("NomeCuidador"));
        colnomeCuidador.setPrefWidth(280);

        TableColumn<Cuidador, String> colidosoResponsavel = new TableColumn<>("Idoso Responsavel");
        colidosoResponsavel.setCellValueFactory(new PropertyValueFactory<>("idosoResponsavel"));
        colidosoResponsavel.setPrefWidth(280);

        TableColumn<Cuidador, String> colCPFCuidador = new TableColumn<>("CPFCuidador");
        //eu sem querer coloquei o colCPFCuidador no propertyvalue, burro do krl
        colCPFCuidador.setCellValueFactory(new PropertyValueFactory<>("CpfCuidador"));
        colCPFCuidador.setPrefWidth(170);

        //coluna pro editar com a corzinha q eu botei no profissional
        TableColumn<Cuidador, Void> colEditarCuidador = new TableColumn<>("Editar");
        colEditarCuidador.setPrefWidth(100);
        colEditarCuidador.setCellFactory(param -> new TableCell<Cuidador, Void>() {
            private final Button btnEditarVisualBonitinho = new Button("Editar");
            {
                btnEditarVisualBonitinho.setStyle(
                        "-fx-background-color: #5cb85c; -fx-text-fill: white; -fx-font-weight: bold;"
                );
                //aqui ele pega o Cuidador Selecionado e puxa as informações pra botar no
                //input pra alterar as informações.
                btnEditarVisualBonitinho.setOnAction(event -> {
                    Cuidador selecionado =
                            getTableView()
                                    .getItems()
                                    .get(getIndex());
                    txtNomeCuidador.setText(selecionado.getNomeCuidador());
                    txtIdosoResponsavel.setText(selecionado.getIdosoResponsavel());
                    txtCPFCuidador.setText(selecionado.getCpfCuidador());
                    btnSalvarCuidador.setUserData(selecionado.getIdCuidador());
                    btnSalvarCuidador.setText("Salvar Alterações");
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btnEditarVisualBonitinho);
                setAlignment(Pos.CENTER);
            }
        });

        //Coluna de deletar, tambem com a corzinha do profissional
        TableColumn<Cuidador, Void> colDeletar = new TableColumn<>("Deletar");
        colDeletar.setPrefWidth(100);
        colDeletar.setCellFactory(param -> new TableCell<Cuidador, Void>() {
            private final Button btnDeletarVisualBonitinho = new Button("Deletar");
            {
                btnDeletarVisualBonitinho.setStyle(
                        "-fx-background-color: #d9534f; -fx-text-fill: white; -fx-font-weight: bold;"
                );
                btnDeletarVisualBonitinho.setOnAction(event -> {

                    Cuidador selecionado =
                            getTableView()
                                    .getItems()
                                    .get(getIndex());
                    if (selecionado != null) {
                        try {
                            dadosCuidador.remove(selecionado);
                            List<Cuidador> lista = CuidadorRepository.listarTodos();
                            lista.removeIf(c -> c.getIdCuidador() == selecionado.getIdCuidador());
                            CuidadorRepository.salvarTodos(lista);

                            System.out.println("\n o Cuidador extermidado *emoji de joinha*");
                            System.out.println("Nome do Cuidador: " + selecionado.getNomeCuidador() +
                                    " | CPF do Elemento: " + selecionado.getCpfCuidador());
                        } catch (Exception ex) {
                            System.err.println("Erro crítico ao tentar apagar o Cuidador, favor entrar em contato com os administradores: " + ex.getMessage());
                        }
                    }
                });
            }
            //chama o metodo toda vez q pro botão reaparecer na tabela toda vez q ela precisa ser
            //desenhada ou atualizda.
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btnDeletarVisualBonitinho);
                setAlignment(Pos.CENTER);
            }
        });

        //chama TODAS as colunas q eu criei antes
        tabelaCuidador.getColumns().addAll(colId, colnomeCuidador, colidosoResponsavel, colCPFCuidador, colEditarCuidador, colDeletar);

        VBox layoutCuidador = new VBox();
        layoutCuidador.setSpacing(15);
        layoutCuidador.setPadding(new Insets(20));
        layoutCuidador.setAlignment(Pos.TOP_CENTER);
        layoutCuidador.setStyle("-fx-background-color: #f2f2f2;");

        layoutCuidador.getChildren().addAll(tituloCuidador, formularioCuidador, linhaBotoes,
                new Separator(), linhaDeBusca, tabelaCuidador
        );

        dadosCuidador.setAll(CuidadorRepository.listarTodos());

        return new Scene(layoutCuidador, 1280, 720);
    }

    private void salvarCuidador() {
        try {
            //usa do validar texto pra ver se nao ta faltando nenhuma informação do formulario
            String Nome = validarTexto(txtNomeCuidador.getText(), "nome do Cuidador");
            String idosoResponsavel = validarTexto(txtIdosoResponsavel.getText(), "Idoso Responsável");
            String CPFCuidador = validarTexto(txtCPFCuidador.getText(), "Cpf do Cuidador");

            List<Cuidador> listaAtual = CuidadorRepository.listarTodos();

            if (btnSalvarCuidador.getUserData() != null) {
                //pega as informações q acabamos de validar pra dai salvar
                int idEditar = (int) btnSalvarCuidador.getUserData();
                for (Cuidador c : listaAtual) {
                    if (c.getIdCuidador() == idEditar) {
                        c.setNomeCuidador(Nome);
                        c.setIdosoResponsavel(idosoResponsavel);
                        c.setCpfCuidador(CPFCuidador);
                        break;
                    }
                }
                System.out.println("\n Informações alteradas com sucesso do Cuidador");
                System.out.println("Cuidador mudado: " + Nome + " | ID: " + idEditar);
            } else {
                //cria um novo id e salva as informações
                int novoId = listaAtual.size() + 1;
                Cuidador novo = new Cuidador(novoId, Nome, idosoResponsavel, CPFCuidador);
                listaAtual.add(novo);

                System.out.println("\n Novo Cuidador Cadastrado no sistema");
                System.out.println("Cuidador Novo: " + Nome + " | Novo ID dele: " + novoId);
            }
            CuidadorRepository.salvarTodos(listaAtual);
            dadosCuidador.setAll(CuidadorRepository.listarTodos());
            limparCamposCuidador();

        } catch (IllegalArgumentException e) {
            // mostra erro na validação
            System.err.println("Erro na validação do Cuidador: " + e.getMessage());
        } catch (Exception e) {
            //avisa falhas inesperadas
            System.err.println("Deu um erro inesperado no sistema ao tentar processar o Cuidador: " + e.getMessage());
        }
    }

    private void pesquisarCuidador() {
        try {
            String busca = txtBuscaCuidador.getText().trim().toLowerCase();
            List<Cuidador> todos = CuidadorRepository.listarTodos();

            if (busca.isEmpty()) {
                dadosCuidador.setAll(todos);
                System.out.println("Campo de pesquisa vazio: mostrando todos os dados Cadastrados.");
            } else {
                List<Cuidador> filtrados = new ArrayList<>();
                for (Cuidador c : todos) {
                    if (c.getNomeCuidador().toLowerCase().contains(busca)) {
                        filtrados.add(c);
                    }
                }
                dadosCuidador.setAll(filtrados);
                System.out.println("Pesquisa executada: encontrados " + filtrados.size() + " resultados para '" + busca + "'.");
            }
        } catch (Exception e) {
            System.err.println("Deu ruim quando tentamos realizar a pesquisa: " + e.getMessage());
        }
    }

    private void limparCamposCuidador() {
        txtNomeCuidador.clear();
        txtIdosoResponsavel.clear();
        txtCPFCuidador.clear();

        btnSalvarCuidador.setUserData(null);
        btnSalvarCuidador.setText("Confirmar Cadastro");
        tabelaCuidador.getSelectionModel().clearSelection();
        System.out.println("Formulário do Cadastro resetado com sucesso.");
    }

    private String validarTexto(String texto, String nomeCampo) {
        if (texto == null || texto.trim().isEmpty()) {
            throw new IllegalArgumentException("Ta faltando coisa ALI amigão.");
        }
        return texto.trim();
    }
}
// isso tudo é oq eu tava fazendo no elderia e truxe pra ca pra dar ctrl+c em oq eu posso reutilizar
//// ---------------- Cadastro do cuidador -----------
//
////titulo da pagina/scene
//Label lblCuidadorCadastro = new Label("Cadastro do Cuidador");
//        lblCuidadorCadastro.setFont(new Font("Arial", 28));
//        lblCuidadorCadastro.setAlignment(Pos.CENTER);
//
////subtítulo
//Label lblCuidadorCadastroInfo = new Label("Aqui você pode se cadastrar como cuidador de um idoso no site:");
//        lblCuidadorCadastroInfo.setFont(new Font("Arial", 14));
//
//VBox boxCuidadorCadastro = criarTelaBase();
//        boxCuidadorCadastro.getChildren().addAll(lblCadastroProfissional, lblCadastroProfissionalInfo, new Separator());
//
//
//// essa cena vamos usar pro cadastro do profissional
//Scene sceneCuidadorCadastro = new Scene(boxCuidadorCadastro, 1280, 720);
//
//// formulário
//GridPane formularioCuidador = new GridPane();
//        formularioCuidador.setHgap(10);
//        formularioCuidador.setVgap(12);
//        formularioCuidador.setAlignment(Pos.CENTER);
//
//// Nome
//Label lblNomeCuidador = new Label("Nome:");
//
//TextField txtNomeCuidador = new TextField();
//        txtNomeCuidador.setPromptText("Ex: Cuidador Robson Da Silva Sauro");
//        txtNomeCuidador.setPrefWidth(250);
//
//Label lblCPFCuidador = new Label("CPF:");
//
//TextField txtCPFCuidador = new TextField();
//        txtCPFCuidador.setPromptText("Ex: 167.169.420-69");
//        txtCPFCuidador.setPrefWidth(250);
