package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ConverterController implements Initializable {
    @FXML
    private ChoiceBox<String> choice1;
    @FXML
    private ChoiceBox<String> choice2;
    @FXML
    private AnchorPane pane;
    @FXML
    private TextField amountField;
    @FXML
    private TextField resultField;


    private Stage stage;
    double amount;
    double result;
    String [] choices = {"usd", "cny", "egp", "eur", "gbp", "cad"};


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choice1.getItems().addAll(choices);
        choice2.getItems().addAll(choices);
    }


    public void closeButtonPressed (ActionEvent e){
        stage = (Stage)pane.getScene().getWindow();
        stage.close();
    }


    public void convertProcess (){
        try{
            amount = Double.parseDouble(amountField.getText());
            String fromCode = choice1.getValue();
            String toCode = choice2.getValue();
            Currency currency = new Currency(amount, fromCode);
            result = currency.getVal(toCode);
            String printResult = String.format("%,.2f", result);
            resultField.setText(printResult);
        }
        catch (NumberFormatException nfe){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid input!");
            alert.setContentText("You can only enter numeric values");
            alert.showAndWait();
        }
        catch (NullPointerException npe){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("You must choose currencies to convert");
            alert.showAndWait();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
