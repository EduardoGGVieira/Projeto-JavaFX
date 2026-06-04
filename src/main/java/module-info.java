module elderia.com.elderia {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens elderia.com.elderia to javafx.fxml;
    exports elderia.com.elderia;
}