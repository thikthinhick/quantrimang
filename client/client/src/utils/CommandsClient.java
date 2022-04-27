package utils;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import sample.SocketClient;
import java.awt.event.*;
import java.awt.*;
import java.util.Locale;

public class CommandsClient{
    private Robot robot;
    public CommandsClient(Robot robot) {
        this.robot = robot;
    }
    public void RemoteClient(Message msg) {
        Commands commands = (Commands) msg.getData();
        if(commands.getId() == 1) {
            int x = (int) (commands.getX() * 1.2);
            int y = (int) (commands.getY() * 1.2);
            robot.mouseMove(x, y);
        }
        else if(commands.getId() == 2) {
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.delay(500);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            robot.delay(500);
        }
        else if(commands.getId() == 3) {
            robot.mousePress(InputEvent.BUTTON3_MASK);
            robot.delay(500);
            robot.mouseRelease(InputEvent.BUTTON3_MASK);
            robot.delay(500);
        }
        else if(commands.getId() == 4) {
            type(robot, commands.getCharacter().toLowerCase());
        }
    }
    public void  type(Robot r, String character) {
        switch (character) {
            case "a":
                r.keyPress(KeyEvent.VK_A);
                break;
            case "b":
                r.keyPress(KeyEvent.VK_B);
                break;
            case "c":
                r.keyPress(KeyEvent.VK_C);
                break;
            case "d":
                r.keyPress(KeyEvent.VK_D);
                break;
            case "e":
                r.keyPress(KeyEvent.VK_E);
                break;
            case "f":
                r.keyPress(KeyEvent.VK_F);
                break;
            case "g":
                r.keyPress(KeyEvent.VK_G);
                break;
            case "h":
                r.keyPress(KeyEvent.VK_H);
                break;
            case "i":
                r.keyPress(KeyEvent.VK_I);
                break;
            case "j":
                r.keyPress(KeyEvent.VK_J);
                break;
            case "k":
                r.keyPress(KeyEvent.VK_K);
                break;
            case "l":
                r.keyPress(KeyEvent.VK_L);
                break;
            case "m":
                r.keyPress(KeyEvent.VK_M);
                break;
            case "n":
                r.keyPress(KeyEvent.VK_N);
                break;
            case "o":
                r.keyPress(KeyEvent.VK_O);
                break;
            case "p":
                r.keyPress(KeyEvent.VK_P);
                break;
            case "q":
                r.keyPress(KeyEvent.VK_Q);
                break;
            case "r":
                r.keyPress(KeyEvent.VK_R);
                break;
            case "s":
                r.keyPress(KeyEvent.VK_S);
                break;
            case "t":
                r.keyPress(KeyEvent.VK_T);
                break;
            case "u":
                r.keyPress(KeyEvent.VK_U);
                break;
            case "v":
                r.keyPress(KeyEvent.VK_V);
                break;
            case "w":
                r.keyPress(KeyEvent.VK_W);
                break;
            case "x":
                r.keyPress(KeyEvent.VK_X);
                break;
            case "y":
                r.keyPress(KeyEvent.VK_Y);
                break;
            case "z":
                r.keyPress(KeyEvent.VK_Z);
                break;
            //special
            case "alt":
                r.keyPress(KeyEvent.VK_ALT);
                break;
            case "tab":
                r.keyPress(KeyEvent.VK_TAB);
                break;
            case "enter":
                r.keyPress(KeyEvent.VK_ENTER);
                break;
            case "shift":
                r.keyPress(KeyEvent.VK_SHIFT);
                break;
            case "windows":
                r.keyPress(KeyEvent.VK_WINDOWS);
                break;
            case "control":
                r.keyPress(KeyEvent.VK_CONTROL);
                break;
            case "open_bracket":
                r.keyPress(KeyEvent.VK_OPEN_BRACKET);
                break;//
            case "back_space":
                r.keyPress(KeyEvent.VK_BACK_SPACE);
                break;
            case "escape":
                r.keyPress(KeyEvent.VK_ESCAPE);
                break;
            case "space":
                r.keyPress(KeyEvent.VK_SPACE);
                break;
            default:
                break;
        }
    }
}
