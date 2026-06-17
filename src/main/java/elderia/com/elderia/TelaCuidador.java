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

    Button btnSalvarCuidador;

    public Scene criarCena(Stage stage, Scene cenaAnterior) {
        Label titulo = new Label("Cadastre-se como um Cuidador");
        titulo.setFont(new Font("Arial", 30));
        titulo.setAlignment(Pos.TOP_CENTER);


        VBox layoutCuidador = new VBox();
        layoutCuidador.setSpacing(15);
        layoutCuidador.setPadding(new Insets(20));
        layoutCuidador.setAlignment(Pos.TOP_CENTER);
        layoutCuidador.setStyle("-fx-background-color: #f2f2f2;");

        return new Scene(layoutCuidador, 1100, 750);
    }

}
//
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
