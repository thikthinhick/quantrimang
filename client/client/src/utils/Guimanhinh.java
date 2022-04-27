package utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import sample.Controller;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.Socket;


public class Guimanhinh extends Thread{
    private Socket socket;
    private Robot robot;
    Rectangle rectangle = null;
    public Guimanhinh(Socket socket, Robot robot, Rectangle rectangle){
        this.socket = socket;
        this.robot = robot;
        this.rectangle = rectangle;
    }
    public void run() {
        ChupManHinh chupManHinh = new ChupManHinh(1.0f);
        try {
            Controller.socket.getOos().writeObject(new Message(12, this.rectangle));
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(true){
            try {
                Controller.socket.getOos().writeObject(new Message(9, chupManHinh.execute(robot)));
                Thread.sleep(100);
            } catch (Exception ex) {
                break;
            }
        }
    }
}
