package sample;

import javafx.application.Platform;
import utils.ManagerFxml;

import java.io.*;
import java.net.Socket;

public class SocketClient {
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private OutputStream output;
    public boolean isLogin;

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public SocketClient(String hostName, int port) {
        try {
           this.socket = new Socket(hostName, port);
           oos = new ObjectOutputStream(this.socket.getOutputStream());
           ois = new ObjectInputStream(this.socket.getInputStream());
           output = this.socket.getOutputStream();
        } catch (IOException e) {
            ManagerFxml.showAlert("Không tồn tại server để kết nối!");
        }
    }
    public void Disconnect() {
        this.isLogin = false;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Main.changeScene("login.fxml");
            }
        });

        try {
            if(this.socket != null) {

                this.socket.close();
                oos.close();
                ois.close();
            }
        } catch (IOException e) {

        }
    }
    public ObjectOutputStream getOos() {
        return oos;
    }

    public OutputStream getOutput() {
        return output;
    }

    public void setOutput(OutputStream output) {
        this.output = output;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setDos(ObjectOutputStream dos) {
        this.oos = oos;
    }

    public ObjectInputStream getOis() {
        return ois;
    }

    public void setDis(ObjectInputStream dis) {
        this.ois = ois;
    }
}
