module ru.java.fx.javafx_first_project {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.java.fx.javafx_cryptoanalizer_project to javafx.fxml;
    exports ru.java.fx.javafx_cryptoanalizer_project;
}