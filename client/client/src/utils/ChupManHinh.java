package utils;

import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class ChupManHinh{
    private static final ThreadLocal<byte[]> previous;

    private static final float JPG_QUALITY;

    private final double scale;
    static {
        previous = new ThreadLocal();
        JPG_QUALITY = 1f;
    }
    public ChupManHinh(double scale) {
        this.scale = scale;
    }
    public byte[] execute(Robot robot) throws IOException {
        long time = System.currentTimeMillis();
        Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        Rectangle shotArea = new Rectangle(defaultToolkit.getScreenSize());
        BufferedImage image = robot.createScreenCapture(shotArea);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", bos );
        byte[] bytes = bos.toByteArray();
        time = System.currentTimeMillis() - time;
        byte[] prev = previous.get();
        if (prev != null && Arrays.equals(bytes, prev)) {
            return null;
        }
        previous.set(bytes);
        return bytes;
    }

}
