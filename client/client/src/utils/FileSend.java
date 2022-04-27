package utils;

import java.io.Serializable;

public class FileSend implements Serializable {
    private String name;
    private byte[] data;
    public FileSend(String name, byte[] data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public byte[] getData() {
        return data;
    }
}
