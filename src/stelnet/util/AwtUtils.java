package stelnet.util;

import java.awt.AWTException;
import java.awt.Robot;

public class AwtUtils {

    public static void send(int key) {
        try {
            Robot robot = new Robot();
            robot.keyPress(key);
            robot.keyRelease(key);
        } catch (AWTException exception) {}
    }
}
