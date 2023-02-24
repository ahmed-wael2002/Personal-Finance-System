package com.example.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class HistoryController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private TableView<Transaction> table;
    @FXML
    private TableColumn<Transaction, String> sourceColumn;
    @FXML
    private TableColumn<Transaction, String> amountColumn;
    @FXML
    private TableColumn<Transaction, String> dateColumn;
    Stage stage;
    private String fileName;

    private final ObservableList<Transaction> transactions = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sourceColumn.setCellValueFactory(new PropertyValueFactory<>("Source"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));

        table.setItems(transactions);
    }

    public void closeButtonPressed (){
        stage = (Stage)pane.getScene().getWindow();
        stage.close();
    }

    public void refresh (){
        try {
            FileReader fileReader = new FileReader(fileName + ".txt");
            BufferedReader reader = new BufferedReader(fileReader);
            String s;
            while((s=reader.readLine()) != null){
                String[] arrOfStr = s.split("!");
                Transaction transaction = new Transaction();
                transaction.setSource(arrOfStr[0]);
                transaction.setDate(arrOfStr[2]);
                transaction.setAmount(arrOfStr[1]);
                transactions.add(transaction);
            }
            reader.close();
        } catch (FileNotFoundException fnfe){
            System.out.println("File not found!");
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}

