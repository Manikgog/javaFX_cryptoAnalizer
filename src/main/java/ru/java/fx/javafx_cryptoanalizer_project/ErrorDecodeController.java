package ru.java.fx.javafx_cryptoanalizer_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class ErrorDecodeController {

    @FXML
    private Button encodeButton;
    @FXML
    void initialize() {
        encodeButton.setOnAction(event -> {
            encodeButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("first-view.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }


            Stage stage = new Stage();
            Parent root;
            try {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("first-view.fxml")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.setTitle("Приложение для кодирования и декодирования текстовых файлов");
            stage.setScene(new Scene(root, 700, 400));
            stage.show();

        });
    }

}

