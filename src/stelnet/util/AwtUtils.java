package stelnet.util;

import java.awt.AWTException;
import java.awt.Robot;
import lombok.extern.log4j.Log4j;

@Log4j
public class AwtUtils {

    public static void send(int key) {
        try {
            Robot robot = new Robot();
            robot.keyPress(key);
            robot.keyRelease(key);
        } catch (AWTException exception) {
            log.warn("Something went wrong sending a key!", exception);
        }
    }
}
