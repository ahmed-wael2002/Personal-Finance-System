package com.example.project;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class TransactionController implements Initializable {

    // Nodes
    @FXML
    private Label label;
    @FXML
    private Slider slider;
    @FXML
    private TextField amountField;
    @FXML
    private TextField sourceField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private AnchorPane pane;

    // Attributes
    private int transactionAmount;
    private Stage stage;
    private String amountText;
    private Date date = new Date();
    private String fileName;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        transactionAmount = (int) slider.getValue();
        amountField.setText(Integer.toString(transactionAmount));
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                transactionAmount = (int) slider.getValue();
                amountField.setText(Integer.toString(transactionAmount));
            }
        });
    }


    public void submitButtonPressed (){
            try {
                double amount;
                amountText = amountField.getText();
                amount = Double.parseDouble(amountText);
                String source = sourceField.getText();
                if (label.getText().equals("Withdraw"))
                    amount = amount * -1;
                System.out.println(label.getText().equals("Withdraw"));
                System.out.println(amount);
                LocalDate localDate = datePicker.getValue();
                String date = localDate.toString();

                FileWriter file = new FileWriter(fileName + ".txt", true);
                BufferedWriter writer = new BufferedWriter(file);
                String input = source+'!'+date+'!'+Double.toString(amount)+'\n';
                System.out.println(input);
                writer.write(input);
                writer.close();
            }
            catch (NumberFormatException nfe) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Data Input!");
                alert.setContentText("You may have entered invalid values in numeric fields");
                alert.showAndWait();
            }
            catch (NullPointerException npe) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText("Missing data!");
                alert.setContentText("Data is missing");
                alert.showAndWait();
            }
            catch (Exception e) {
                System.out.println(e);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setContentText("Something went wrong!");
                alert.showAndWait();
                stage = (Stage) pane.getScene().getWindow();
                stage.close();
            }
    }

    public void closeButtonPressed (ActionEvent e){
        stage = (Stage)pane.getScene().getWindow();
        stage.close();
    }

    public void displayName (String text){
        label.setText(text);
    }
    public void setFileName (String fileName) {this.fileName = fileName;}
}
