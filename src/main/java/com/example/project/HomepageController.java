package com.example.project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;

public class HomepageController{

    @FXML
    private AnchorPane pane;
    @FXML
    private Label balanceLabel;
    @FXML
    private Label nameLabel;


    // Scenes navigation window
    private Scene scene;
    private Stage stage;
    private Parent root;
    private TransactionController controller;

    public void deposit () throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Transaction.fxml"));
        root = loader.load();
        controller = loader.getController();
        controller.displayName("Deposit");
        controller.setFileName(nameLabel.getText());
        scene = new Scene(root);
        stage = new Stage();
        stage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    public void withdraw () throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Transaction.fxml"));
        root = loader.load();
        controller = loader.getController();
        controller.displayName("Withdraw");
        controller.setFileName(nameLabel.getText());
        scene = new Scene(root);
        stage = new Stage();
        stage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    public void openConverter () throws IOException {
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("converter.fxml"));
        root = loader.load();
        scene = new Scene (root);
        stage.setScene(scene);

        // Stage attributes
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    public void addNewWalletPressed () throws IOException {
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("NewWallet.fxml"));
        root = loader.load();
        scene = new Scene (root);
        stage.setScene(scene);
        // Stage attributes
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    public void closeWindow (){
        stage = (Stage) pane.getScene().getWindow();
        stage.close();
    }

    public void minimizeWindow (){
        stage = (Stage) pane.getScene().getWindow();
        stage.setIconified(true);
    }

    public void openProductInfo () throws IOException {
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ProductInfo.fxml"));
        root = loader.load();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Product Info");
        stage.show();
        // Default view
    }


    public void setBalanceLabelText (String text){
        balanceLabel.setText(text);
    }

    public void openHistory () throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("History.fxml"));
        root = loader.load();
        scene = new Scene(root);
        stage = new Stage();
        stage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);

        HistoryController controller = loader.getController();
        controller.setFileName(nameLabel.getText());
        controller.refresh();
        stage.show();
    }

    public void setName (String fullname){
        nameLabel.setText(fullname);
    }

    public void setBalance () throws IOException {
            FileReader fileReader = new FileReader(nameLabel.getText() + ".txt");
            BufferedReader reader = new BufferedReader(fileReader);
            double total = 0;

            String s;
            while((s=reader.readLine()) != null){
                String[] arrOfStr = s.split("!");
                total+= Double.parseDouble(arrOfStr[2]);
            }
            reader.close();
            balanceLabel.setText(Double.toString(total));


    }


}