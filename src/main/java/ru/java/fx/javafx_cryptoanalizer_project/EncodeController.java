package ru.java.fx.javafx_cryptoanalizer_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.java.fx.javafx_cryptoanalizer_project.entity.Result;

import java.io.IOException;

public class EncodeController {

    @FXML
    private Button encodeButton;

    @FXML
    private TextField encodedFileField;

    @FXML
    private TextField fileForEncodeField;

    @FXML
    private TextField keyField;

    @FXML
    void initialize() {
        encodeButton.setOnAction(event -> {

            String pathSourceToEncodeFile = fileForEncodeField.getText();
            String pathToEncodedFile = encodedFileField.getText();
            String shift = keyField.getText();
            String[] args = new String[]{"encode", pathSourceToEncodeFile, pathToEncodedFile, shift};
            Application app = new Application();
            Result result = null;
            try {
                result = app.run(args);
            }catch (RuntimeException ex){
                encodeButton.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("errorOfEncode-view.fxml"));
                try{
                    loader.load();
                }catch (IOException e){
                    System.out.println(e.getMessage());
                }

                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.showAndWait();
            }
            if(result != null){
                encodeButton.getScene().getWindow().hide();

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("resultOfEncode-view.fxml"));

                try{
                    loader.load();
                }catch (IOException e){
                    System.out.println(e.getMessage());
                }

                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.showAndWait();
            }
        });

    }

}

