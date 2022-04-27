package utils;

import components.ClientHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.Date;

public class Client {
    private int id;
    private String Description;
    private int port;
    private String hostName;
    private Date date;
    private MenuButton action;
    private String userName;
    private ClientHandler client;

    public Client(int id, String description, int port, String hostName, Date date, String userName, ClientHandler client) {
        this.id = id;
        this.client = client;
        Description = description;
        this.port = port;
        this.hostName = hostName;
        this.date = date;
        this.userName = userName;
        MenuItem menuItem1 = new MenuItem("Gửi thông báo");
        menuItem1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                client.getFunction().showWriteNotify();
            }
        });
        MenuItem menuItem2 = new MenuItem("Nhắn tin");
        menuItem2.setOnAction(new EventHandler<ActionEvent>() {
           public void handle(ActionEvent actionEvent) {
               client.getFunction().showChat(userName+hostName);
               try {
                   client.getDos().writeObject(new Message(2, "true"));
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
        });
        MenuItem menuItem3 = new MenuItem("Theo dõi");
        menuItem3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    client.getDos().writeObject(new Message(8, ""));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                client.getFunction().showShareScreen(false);
            }
        });
        MenuItem menuItem4 = new MenuItem("Điều khiển");
        menuItem4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    client.getDos().writeObject(new Message(8, ""));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                client.getFunction().showShareScreen(true);
            }
        });
        MenuItem menuItem6 = new MenuItem("Gửi file");
        menuItem6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                client.getFunction().showSendFile();
            }
        });
        MenuItem menuItem7 = new MenuItem("Tùy chọn truy cập");
        menuItem7.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                client.getFunction().showSelectAccess();
            }
        });
        MenuItem menuItem8 = new MenuItem("Ngắt kết nối");
        menuItem8.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                client.removeClientHandler();
            }
        });
        this.action = new MenuButton("", null, menuItem1, menuItem2, menuItem3, menuItem4, menuItem6, menuItem7, menuItem8);
        this.action.setStyle("-fx-background-color: transparent;");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public MenuButton isAction() {
        return action;
    }

    public void setAction(MenuButton action) {
        this.action = action;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
