package lwjgl_leren.engine.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
	
	private static Logger instance = null;
    public static Logger getInstance() {
        if(instance == null)
            instance = new Logger();
        return instance;
    }

    private final File LOG_FILE;
    private final String LOG_PATH;
    private final String LOG_NAME;
    public boolean write_log = false;
    private Logger() {
        LOG_PATH = "assets/logs/";
        LOG_NAME = "log-" + System.currentTimeMillis() + ".log";
        LOG_FILE = new File(LOG_PATH + LOG_NAME);
    }

    public void init() {
        try {
            if(!write_log)
                return;
            LOG_FILE.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getTimeStamp() {
        SimpleDateFormat simple_date_format = new SimpleDateFormat("[dd/MM/yyyy HH:mm:ss] ");
        Date date = new Date();
        return simple_date_format.format(date);
    }

    public <T> void log(T message, PrintStream type) {
        String prefix = getTimeStamp();
        if(type == System.err)
            prefix += "[ERROR] ";
        else
            prefix += "[INFO] ";

        String out = prefix + message;
        type.println(out);
        if(!write_log)
            return;

        try {
            Files.write(Paths.get(LOG_PATH + LOG_NAME), (out + '\n').getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void clean() {
    	File[] logs = new File(LOG_PATH).listFiles();
    	long oldest_date = Long.MAX_VALUE;
    	File oldest_file = null;
    	
    	if(logs.length > 5) {
    		for(File log : logs) {
    			if(log.lastModified() < oldest_date) {
    				oldest_date = log.lastModified();
    				oldest_file = log;
    			}
    		}
    		
    		
    		log("Deleting oldest log: " + oldest_file.getPath(), System.out);
    		oldest_file.delete();
    	}
    }
    
}
