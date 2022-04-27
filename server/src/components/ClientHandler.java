package components;

import connectDatabase.ConnectMysql;
import controller.controllerTable;
import controller.managerFxml;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import utils.Message;
import utils.Quanlichucnang;

import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;
import java.util.UUID;

public class ClientHandler extends Thread{
    private String name;
    final ObjectInputStream ois;
    final ObjectOutputStream oos;
    Socket s;
    private String ID;
    private String UserName;
    private Rectangle rectangle;
    boolean Connecting = true;
    Quanlichucnang function;

    public ClientHandler(Socket s, String name, ObjectInputStream ois, ObjectOutputStream oos, String ID, String UserName) {
        this.ID = ID;
        this.ois = ois;
        this.oos = oos;
        this.name = name;
        this.UserName = UserName;
        this.s = s;
        this.Connecting = true;
        this.function = new Quanlichucnang(this);
    }
    public void run() {
        while(true) {
            try {
                Message message = (Message) this.ois.readObject();
                switch (message.getId()) {
                    case 5: {
                        this.function.updateChat((String)message.getData());
                        break;
                    }
                    case 9: {
                        byte [] bytes = (byte[]) message.getData();
                        if(bytes != null)
                            this.function.updateScreen(bytes);
                        break;
                    }
                    case 12: {
                        this.rectangle = (Rectangle) message.getData();
                        break;
                    }
                    default: break;
                }
            } catch (IOException e) {
                removeClientHandler();
                break;
            } catch (ClassNotFoundException e) {
                break;
            }
        }
    }
    public void removeClientHandler() {
        Connection conn = ConnectMysql.getConnection();
        Statement stmt = null;
        String sql2 = "update userclient set ip = NULL where iduser = '" + this.ID + "'";
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql2);
            this.s.close();
            this.ois.close();
            this.oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
        throwables.printStackTrace();
        }
        catch (Exception ex) {
        ex.printStackTrace();
        }
        Server.ar.remove(compareTo(ID));
        managerFxml.showAlert(s.getInetAddress() + ": đã mất kết nối!");
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                controllerTable controller = managerFxml.loaderTable.getController();
                controller.update();
            }
        });
    }
    public int compareTo(String id) {
        for(int i = 0; i < Server.ar.size(); i++) {
            if(id.equals(Server.ar.get(i).getID())) {
                return i;
            }
        }
        return -1;
    }
    public void showChat(String ClientName) {
        this.function.showChat(ClientName);
    }
    public String getID() {
        return ID;
    }
    public String getUserName() {
        return this.UserName;
    }
    public String getNameClient() {
        return name;
    }
    public void sendFile() {
        this.function.showSendFile();
    }

    public Quanlichucnang getFunction() {
        return function;
    }

    public ObjectInputStream getDis() {
        return ois;
    }

    public ObjectOutputStream getDos() {
        return oos;
    }

    public Socket getS() {
        return s;
    }

}