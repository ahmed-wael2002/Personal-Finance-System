package com.example.project;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.control.Alert;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class NewWalletController implements Initializable {
    @FXML
    private AnchorPane pane;
    @FXML
    private Slider slider;
    @FXML
    private TextField balanceField;
    @FXML
    private TextField nameField;

    private Stage stage;
    int amount;

    public void closeButtonPressed (){
        stage = (Stage)pane.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        amount = (int) slider.getValue();
        slider.valueProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                amount = (int) slider.getValue();
                balanceField.setText(Integer.toString(amount));
            }
        });
    }

    public void createAccount (){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Homepage.fxml"));
            HomepageController controller = loader.getController();
            controller.setBalanceLabelText(balanceField.getText());

            stage = (Stage)pane.getScene().getWindow();
            stage.close();
        }
        catch (NumberFormatException nfe) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Input!");
            alert.setContentText("You have input some data with invalid format");
            alert.showAndWait();
        }
        catch (NullPointerException npe) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Missing data!");
            alert.setContentText("Some attributes are missing input");
            alert.showAndWait();
        }
        catch (Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Some error occurred");
            alert.showAndWait();
        }
    }

}
