package controller;

import components.ClientHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import utils.Message;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.SocketHandler;

public class WriteNotify implements Initializable {
    public TextArea textarea;
    private ClientHandler client;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void initWriteNotify(ClientHandler client) {
        this.client = client;
    }
    public void Send(ActionEvent actionEvent) {
        String message = textarea.getText();
        try {
            this.client.getDos().writeObject(new Message(14, message));
            ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
            managerFxml.showAlert("Gửi thông báo thành công!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
