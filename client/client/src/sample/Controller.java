package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import utils.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.ResourceBundle;

public class Controller{
    private ManagerFxml fxml;
    public static SocketClient socket;
    public Controller() {
        fxml = new ManagerFxml();
    }

    public void connect (String ip, String username,String password) {
        socket = new SocketClient(ip, 1234);
        if(socket.getSocket() != null) {
            try {
                String computerName = InetAddress.getLocalHost().getHostName();
                String msg = username + "/" + password + "/" + computerName;
                socket.getOos().writeObject(new Message(1, msg));
                socket.setLogin(true);
                readData();

            } catch (IOException e) {

            }
        }
    }
    public void readData(){
        final Thread[] SendDataScene = {null};
        final CommandsClient[] commandsClient = {null};
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(socket.isLogin()) {
                    try {
                        Message msg = (Message)socket.getOis().readObject();
                        int i = msg.getId();
                        switch (i) {
                            case 1: {
                                String login = msg.getData().toString();
                                if(login.equals("false")) {
                                    socket.setLogin(false);
                                    ManagerFxml.showAlert("Tài khoản hoặc mật khẩu nhập không đúng");
                                }
                                else{
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            Main.changeScene("Connecting.fxml");
                                            ManagerFxml.showAlert("Kết thành công vs server");
                                        }
                                    });
                                }
                                break;
                            }
                            case 2: {
                                if(fxml.getLoaderChat() == null) {
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            fxml.showChat();
                                        }
                                    });
                                }
                                break;
                            }
                            case 5: {
                                if(fxml.getLoaderChat() == null) {
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            fxml.showChat();
                                        }
                                    });
                                }
                                Chat controller = fxml.getLoaderChat().getController();
                                controller.updateChat(msg.getData().toString());
                                break;
                            }
                            case 3: {
                                System.out.println("show log out");
                                break;
                            }
                            case 8: {
                                GraphicsEnvironment gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
                                GraphicsDevice gDev = gEnv.getDefaultScreenDevice();
                                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                                Rectangle rectangle = new Rectangle(dim);
                                Robot robot = new Robot();
                                commandsClient[0] = new CommandsClient(robot);
                                SendDataScene[0] = new Thread(new Guimanhinh(Controller.socket.getSocket(), robot, rectangle));
                                SendDataScene[0].start();
                                break;
                            }
                            case 9: {
                                SendDataScene[0].stop();
                                commandsClient[0] = null;
                                break;
                            }
                            case 11: {
                                // nhan lenh dieu khien client;
                                commandsClient[0].RemoteClient(msg);
                                break;
                            }
                            case 10: {

                                break;
                            }
                            case 14: {
                                ManagerFxml.showAlert(msg.getData().toString());
                                break;
                            }
                            case 21: { // gửi file
                                FileSend fileSend = (FileSend) msg.getData();
                                String desktopPath = System.getProperty("user.home") + "\\";
                                System.out.println(desktopPath);
                                FileOutputStream outStream = new FileOutputStream(new File("Files\\" + fileSend.getName()));
                                outStream.write(fileSend.getData());
                                ManagerFxml.showAlert("bạn nhận được một file từ server!");
                                break;
                            }
                        }
                    } catch (IOException e) {
                        socket.Disconnect();
                        ManagerFxml.showAlert("Mất kết nối vs server");
                    } catch (ClassNotFoundException e) {
                        socket.Disconnect();
                        ManagerFxml.showAlert("Mất kết nối vs server");
                    } catch (AWTException e) {
                        socket.Disconnect();
                        ManagerFxml.showAlert("Mất kết nối vs server");
                    }
                }
            }
        }).start();
    }
}
