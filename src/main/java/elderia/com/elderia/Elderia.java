package elderia.com.elderia;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.awt.*;


public class Elderia extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Elderia");
        Label lbl1 = new Label("Jogo do Abimael");
        lbl1.setFont (new Font("Arial",24));
        lbl1.setAlignment(Pos.TOP_CENTER);

        VBox box1 = new VBox();
        box1.setSpacing(20);

        box1.getChildren().add(lbl1);

        Scene scene = new Scene(box1, 500,300);
        stage.setScene(scene);
        stage.show();

        Button btn1 = new Button();
        btn1.setText("PRINT DE MENSAGEM");
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("BLA BLA BLA|");
            }
        });
        box1.getChildren().add(btn1);

        Button btn2 = new Button("Close");
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }


        });
        



        box1.getChildren().add(btn2);
    }

    public static void main (String[] args){
        launch();
    }

}