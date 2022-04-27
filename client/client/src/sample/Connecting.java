package sample;

import javafx.event.ActionEvent;
import javafx.scene.text.Text;

public class Connecting {
    public Controller controller;
    public Text title;

    public void init(Controller controller) {
        this.controller = controller;
        String text= "Đang kết nối vs " + controller.socket.getSocket().getInetAddress().toString();
        title.setText(text);
    }
    public void disconnect(ActionEvent actionEvent) {
        Controller.socket.Disconnect();
    }
}
