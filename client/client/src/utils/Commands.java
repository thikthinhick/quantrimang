package utils;

import java.io.Serializable;

public class Commands implements Serializable {
    private int id;
    private double x;
    private double y;
    private String character;
    public Commands(int id, double x, double y, String character) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.character = character;
    }

    public int getId() {
        return id;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
