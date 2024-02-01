package ru.java.fx.javafx_cryptoanalizer_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    @FXML
    private Button decodeButton;

    @FXML
    private Button decodeWithoutKeyButton;

    @FXML
    private Button encodeButton;

    @FXML
    void initialize() {
        encodeButton.setOnAction(event -> {
            encodeButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("encode-view.fxml"));

            try{
                loader.load();
            }catch (IOException e){
                System.out.println(e.getMessage());
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });

        decodeButton.setOnAction(event -> {
            decodeButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("decodeWithKey-view.fxml"));

            try{
                loader.load();
            }catch (IOException e){
                System.out.println(e.getMessage());
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });

        decodeWithoutKeyButton.setOnAction(event -> {
            decodeWithoutKeyButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("decodeWithBruteforce-view.fxml"));

            try{
                loader.load();
            }catch (IOException e){
                System.out.println(e.getMessage());
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });

    }

}
