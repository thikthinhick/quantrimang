package utils;
import java.io.Serializable;

public class Message implements Serializable {
    private int id;
    private Object data;

    public Message(int id, Object data) {
        this.id = id;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void printData() {
        String message = (String) this.data;
        System.out.println(message);
    }
}