package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import utils.ManagerFxml;

import java.net.URL;
import java.util.ResourceBundle;

public class Login{
    Controller controller;
    @FXML
    TextField inputIP;
    @FXML
    TextField inputUsername;
    @FXML
    TextField inputPassword;
    public void init(Controller controller) {
        this.controller = controller;
    }
    public void loginButton(ActionEvent actionEvent) {
        this.controller.connect(inputIP.getText(), inputUsername.getText(), inputPassword.getText());
    }

    public void Disconnect(ActionEvent actionEvent) {
    }
}
