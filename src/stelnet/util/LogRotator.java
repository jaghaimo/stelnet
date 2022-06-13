package stelnet.util;

import java.util.Enumeration;
import org.apache.log4j.Appender;
import org.apache.log4j.Logger;
import org.apache.log4j.RollingFileAppender;

public class LogRotator {

    public static void rotate() {
        @SuppressWarnings("rawtypes")
        Enumeration e2 = Logger.getRootLogger().getAllAppenders();
        while (e2.hasMoreElements()) {
            Appender appender = (Appender) e2.nextElement();
            if (appender instanceof RollingFileAppender) {
                RollingFileAppender rfa = (RollingFileAppender) appender;
                rfa.rollOver();
            }
        }
    }
}
