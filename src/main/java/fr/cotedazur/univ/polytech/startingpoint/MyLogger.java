package fr.cotedazur.univ.polytech.startingpoint;

import java.io.IOException;
import java.util.logging.*;

public class MyLogger  {

    private static Logger logger;

    private MyLogger()  {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$s] %5$s%6$s%n");
        //instance the logger
        logger = Logger.getLogger(MyLogger.class.getName());
        logger.setLevel(Level.INFO);
    }

    private static Logger getLogger(){
        if(logger == null){
            new MyLogger();
        }
        return logger;
    }
    public static void log(Level level, String msg){
        getLogger().log(level, msg);
    }

    public static void setLogLevel(Level level){
        getLogger().setLevel(level);
    }


}
