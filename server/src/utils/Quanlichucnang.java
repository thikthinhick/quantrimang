package utils;

import components.ClientHandler;
import components.Server;
import controller.*;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.logging.SocketHandler;

public class Quanlichucnang {
    FXMLLoader chatLoader;
    FXMLLoader speedTestLoader;
    FXMLLoader shareScreen;
    FXMLLoader sendFile;
    FXMLLoader writeMessage;
    FXMLLoader selectAccess;
    private ClientHandler client;
    public Quanlichucnang(ClientHandler client) {
        this.client = client;
    }
    public void showChat(String ClientName) {
        chatLoader = new FXMLLoader();
        chatLoader.setLocation(getClass().getResource("/fxml/Chat.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(chatLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Chat chat = chatLoader.getController();
        chat.initChat(client);
        Stage newWindow = new Stage();
        newWindow.setTitle("Server-Chat");
        newWindow.setScene(scene);
        newWindow.show();
    }
    public void showSpeedTest() {
        speedTestLoader = new FXMLLoader();
        speedTestLoader.setLocation(getClass().getResource("/fxml/SpeedTest.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(speedTestLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage newWindow = new Stage();
        newWindow.setTitle("Server-Chat");
        newWindow.setScene(scene);
        newWindow.show();
    }
    public void showSendFile() {
        sendFile = new FXMLLoader();
        sendFile.setLocation(getClass().getResource("/fxml/SendFile.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(sendFile.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        SendFile x = sendFile.getController();
        x.init(client);
        Stage newWindow = new Stage();
        newWindow.setTitle("Send-File");
        newWindow.setScene(scene);
        newWindow.show();
    }
    public void showWriteNotify() {
        writeMessage = new FXMLLoader();
        writeMessage.setLocation(getClass().getResource("/fxml/WriteNotify.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(writeMessage.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        WriteNotify writeNotify = writeMessage.getController();
        writeNotify.initWriteNotify(client);
        Stage newWindow = new Stage();
        newWindow.setScene(scene);
        newWindow.show();
    }
    public void showSelectAccess() {
        selectAccess = new FXMLLoader();
        selectAccess.setLocation(getClass().getResource("/fxml/SelectAccess.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(selectAccess.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        SelectAccess a = selectAccess.getController();
        a.init(client.getID());
        Stage newWindow = new Stage();
        newWindow.setScene(scene);
        newWindow.show();
    }
    public void showShareScreen(boolean remote) {
        shareScreen = new FXMLLoader();
        shareScreen.setLocation(getClass().getResource("/fxml/ShareScreen.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(shareScreen.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage newWindow = new Stage();
        newWindow.setTitle("ShareScreen");
        if(remote) {
            commandsClient(scene);
        }

        newWindow.setOnHidden(windowEvent -> {
            shareScreen = null;
            try {
                client.getDos().writeObject(new Message(9, ""));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        newWindow.setScene(scene);
        newWindow.show();
    }

    public void updateScreen(byte[] data) {
        if(this.shareScreen != null) {
            ShareScreen controller = shareScreen.getController();
            controller.updateScreen(data);
        }
    }
    public void updateChat(String message) {
        Chat chat = chatLoader.getController();
        chat.receiveChat(message);
    }
    public void commandsClient(Scene a) {
        a.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().toString().equals("PRIMARY")) {
                    try {
                        client.getDos().writeObject(new Message(11, new Commands(2, 0, 0, "")));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if(mouseEvent.getButton().toString().equals("SECONDARY")) {
                    try {
                        client.getDos().writeObject(new Message(11, new Commands(3, 0, 0, "")));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        a.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

            }
        });
        a.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    client.getDos().writeObject(new Message(11, new Commands(1, (double)mouseEvent.getX(), (double)mouseEvent.getY(), "")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        a.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                try {
                    client.getDos().writeObject(new Message(11, new Commands(4, 0, 0, keyEvent.getCode().toString())));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
