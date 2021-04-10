package stelnet.helper;

import com.fs.starfarer.api.Global;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class LogHelper {

    public static void debug(Object logObject) {
        getLogger().debug(logObject);
    }

    public static void info(Object logObject) {
        getLogger().info(logObject);
    }

    private static Logger getLogger() {
        Logger logger = Global.getLogger(getName());
        if (Global.getSettings().isDevMode()) {
            logger.setLevel(Level.DEBUG);
        }
        return logger;
    }

    private static Class<?> getName() {
        try {
            return Class.forName(Thread.currentThread().getStackTrace()[4].getClassName());
        } catch (Exception e) {
            e.printStackTrace();
            return LogHelper.class;
        }
    }
}