package main.java;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.ConsoleHandler;

public class LogToFile {

	protected static final Logger logger = Logger.getLogger("MYLOG");

	/**
	 * log Method enable to log all exceptions to a file and display user
	 * message on demand
	 * 
	 * @param ex
	 * @param level
	 * @param msg
	 */
	public static void log(Exception ex, String level, String msg) {

		FileHandler fh = null;
		try {
			fh = new FileHandler("log.xml", true);
			logger.addHandler(fh);
			logger.addHandler(new ConsoleHandler());
		    logger.setUseParentHandlers(false);
			switch (level) {
			case "Error":
				logger.log(Level.SEVERE, msg, ex);
				break;
			case "Debug":
				logger.log(Level.FINE , msg, ex);
				break;
			case "INFO":
				logger.log(Level.INFO, msg, ex);
				break;
			}
		} catch (IOException | SecurityException ex1) {
			logger.log(Level.SEVERE, null, ex1);
		} finally {
			if (fh != null)
				fh.close();
		}
	}
	
	

}
