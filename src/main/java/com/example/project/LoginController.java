package com.example.project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class LoginController {
    @FXML
    private AnchorPane pane;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;


    // Local Variables
    Stage stage;
    Scene scene;
    Parent root;
    String firstName;
    String lastName;


    public void closeWindow(){
        stage = (Stage)pane.getScene().getWindow();
        stage.close();
    }

    public boolean validateLogin () throws IOException {
            String username = usernameField.getText();
            String password = passwordField.getText();
            FileReader fileReader = new FileReader("accounts.txt");
            BufferedReader reader = new BufferedReader(fileReader);
            String s;
            while((s=reader.readLine()) != null){
                String[] arrOfStr = s.split("!");
                if (username.equals(arrOfStr[2]) && password.equals(arrOfStr[3])){
                    firstName = arrOfStr[0];
                    lastName = arrOfStr[1];
                    return true;
                }

            }
            reader.close();
        return false;
    }

    public void login () {
        try{
            if (validateLogin())
            {
                // Open Homepage
                String fullName = firstName + ' ' + lastName;
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Homepage.fxml"));
                root = loader.load();
                scene = new Scene (root);
                stage = new Stage();
                stage.setScene(scene);

                HomepageController controller = loader.getController();
                controller.setName(fullName);
                controller.setBalance();

                scene.setFill(Color.TRANSPARENT);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.show();
                stage = (Stage)pane.getScene().getWindow();
                stage.close();
            }
            else
            {
                // Display Error
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText("Invalid Login Credentials");
                alert.setContentText("Please recheck username and password");
                alert.showAndWait();
            }
        } catch (NullPointerException npe){
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Missing Data!");
            alert.setContentText("Check username and password");
        } catch (Exception e){
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(e.toString());
            alert.setContentText("Unknown error occured! Contact the developer ASAP");
        }

    }

    public void register () throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Register.fxml")));
        scene = new Scene (root);
        stage = new Stage();
        stage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }


}
