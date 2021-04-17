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

    public static void warn(Object logObject) {
        getLogger().warn(logObject);
    }

    public static void error(Object logObject) {
        getLogger().error(logObject);
    }

    public static void fatal(Object logObject) {
        getLogger().fatal(logObject);
    }

    private static Logger getLogger() {
        Logger logger = Global.getLogger(getName());
        if (GlobalHelper.isDevMode()) {
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
