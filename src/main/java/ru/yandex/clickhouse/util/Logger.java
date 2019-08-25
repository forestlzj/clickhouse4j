package ru.yandex.clickhouse.util;

import java.sql.DriverManager;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

/**
 * Simple logger.
 */
public final class Logger {

    private static final Level currentLevel = Level.FINE;

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private final String key;

    private Logger(String key) {
        this.key = key;
    }

    public static Logger logger(String key) {
        return new Logger(key);
    }

    public static Logger of(Class clazz) {
        return new Logger(clazz.getSimpleName());
    }

    public static Logger of(Object object) {
        return new Logger(object.getClass().getSimpleName());
    }

    public void error(String message) {
        log(message, Level.SEVERE);
    }

    public synchronized void log(String message, Level level) {
        if (level.intValue() >= currentLevel.intValue()) {
            String str = String.format("%s <%s> %s: %s", DATE_FORMAT.format(new Date()), level.getName(), key, message);
            DriverManager.println(str);
        }
    }

}
