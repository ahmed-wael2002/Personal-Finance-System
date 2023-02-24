package com.example.project;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class RegisterController {
    @FXML
    AnchorPane pane;
    @FXML
    TextField firstNameField;
    @FXML
    TextField lastNameField;
    @FXML
    TextField usernameField;
    @FXML
    TextField passwordField;


    Stage stage;


    public void closeWindow(){
        stage = (Stage) pane.getScene().getWindow();
        stage.close();
    }

    public void register () {
        try{
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String username = usernameField.getText();
            String password = passwordField.getText();
            String input = firstName + '!' + lastName + '!' + username + '!' + password + '\n';

            FileWriter fileWriter = new FileWriter("accounts.txt", true);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.write(input);
            writer.close();

            FileWriter transactionDB = new FileWriter(firstName + ' ' + lastName + ".txt");
            BufferedWriter newWriter = new BufferedWriter(transactionDB);
            newWriter.write("0!0!0\n");
            newWriter.close();

            confirmationMSG();

        } catch (NullPointerException npc){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Null Pointer Exception Error");
            alert.setContentText("Some data might be missing");
            alert.showAndWait();
        } catch (IOException io){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("IO Exception");
            alert.setContentText("Database File is not found!");
            alert.showAndWait();
        }

    }

    public void confirmationMSG (){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setContentText("Your data has been registered successfully");
        alert.showAndWait();
    }


}
