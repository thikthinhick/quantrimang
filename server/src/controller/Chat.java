package controller;

import components.ClientHandler;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import utils.Message;

import java.net.URL;
import java.util.ResourceBundle;

public class Chat implements Initializable {
    public Label clientName;
    @FXML
    public ListView listChat;
    @FXML
    private TextField inputChat;
    @FXML
    private Pane contentMessage;

    private ClientHandler client;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void receiveChat(String message) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Label label = new Label("Client>> " + message);
                listChat.getItems().add(label);
            }
        });

    }

    public void initChat(ClientHandler client) {
        this.client = client;
        clientName.setText("client" +  client.getS().getInetAddress().toString());
    }
    public void sendChat(ActionEvent actionEvent) {
            try {
                Label label = new Label(inputChat.getText());
                this.client.getDos().writeObject(new Message(5, inputChat.getText()));
                listChat.getItems().add(label);
                inputChat.setText("");
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
