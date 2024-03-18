package me.lunatic.lunaui.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtil {

    private static Logger LOGGER = LogManager.getLogger("Luna-UI");

    public static void info(String s) {
        LOGGER.info(s);
    }

    public static void error(String s) {
        LOGGER.error(s);
    }

    public static void warn(String s) {
        LOGGER.warn(s);
    }
}
