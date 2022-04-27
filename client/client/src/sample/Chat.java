package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import utils.Message;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Chat implements Initializable {
    public Button buttonChat;
    public TextField inputChat;
    public ListView contentMessage;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    public void sendChat(ActionEvent actionEvent) {
        String message = inputChat.getText();
        try {
            Controller.socket.getOos().writeObject(new Message(5, message));
        } catch (IOException e) {
            e.printStackTrace();
        }
        contentMessage.getItems().add(new Label(message));
        inputChat.setText("");
    }

    public void updateChat(String data) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                contentMessage.getItems().add(new Label("Server>> " + data));
            }
        });
    }
}
