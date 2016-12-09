package main.java;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogToFile {

	// protected static final Logger logger = Logger.getLogger("MYLOG");
	private static LogManager lMgr = LogManager.getLogManager();
	protected static final Logger logger = Logger.getLogger("MYLOG");
	private static String logFile = "test";

	public LogToFile() {

		// TODO Auto-generated constructor stub
	}

	/**
	 * log Method enable to log all exceptions to a file and display user
	 * message on demand
	 * 
	 * @param ex
	 * @param level
	 * @param msg
	 */

	private static void initialize() {
		try {
			//Logger logger = Logger.getLogger(this.getClass().getName());
			SimpleFormatter formatter = new SimpleFormatter();
			//FileHandler fh = new FileHandler("main" + File.separator + "java" + File.separator + "Logs" + File.separator + logFile + "%g%u.LOG", 10000, 2, true);
			FileHandler fh = new FileHandler(logFile + "%g%u.LOG", 100000, 2, true);
			fh.setFormatter(formatter);
			fh.setLevel(Level.ALL);
			logger.setUseParentHandlers(false);
			logger.addHandler(fh);
			lMgr.addLogger(logger);
		} catch (IOException ioe) {
			System.out.println("TestLogger" + "Failed to initialize Logger" + ioe);
		} catch (NullPointerException npe) {
			System.out.println("TestLogger" + npe);
		} catch (Exception e) {
			System.out.println("TestLogger" + e);
		}
	}

	// Creates a Log file for each day and appends a msg.
	public static void log(Exception ex, String level, String msg) {
		Logger logger1;
		if (!checkFileName(logFile)) {
			createFileName();
		   // System.out.println("FileName: " + logFile);
			initialize();
		}

		if ((logger1 = lMgr.getLogger("MYLOG")) != null) {
			switch (level) {
			case "Error":
				logger1.log(Level.SEVERE, msg, ex);
				break;
			case "Debug":
				logger1.log(Level.FINE, msg, ex);
				break;
			case "INFO":
				logger1.log(Level.INFO, msg, ex);
				break;
			}

		} else {
			System.out.println("TestLogger" + "Logger not found");
		}
	}

	// Check if a Log file for the day exists.
	private static boolean checkFileName(String FileName) {
		boolean check = false;
		// System.out.println("Inside checkFileName" + FileName);
		try {
			java.util.Date dt = new java.util.Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			String srvDate = dateFormat.format(dt);
			String yymmdd = "ProcessLog_V2_" + srvDate.substring(6, 10) + srvDate.substring(3, 5)
					+ srvDate.substring(0, 2);
			if (FileName.equals(yymmdd))
				check = true;
			else
				check = false;
			// System.out.println(check);
		} catch (Exception e) {
			System.out.println("TestLogger" + "Check file method" + e);
		}
		// System.out.println("Inside checkFileName" + FileName);
		// System.out.println("Check: " + check);
		return check;
	}

	// Creates a Log file
	private static void createFileName() {
		// System.out.println("Inside createFileName" );
		try {
			java.util.Date dt = new java.util.Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			// System.out.println("Able to create dataFormat object");
			String srvDate = dateFormat.format(dt);
			String yymmdd = srvDate.substring(6, 10) + srvDate.substring(3, 5) + srvDate.substring(0, 2);
			logFile = "ProcessLog_V2_" + yymmdd;
			// System.out.println("FileName in createFileName: " + logFile);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	/*
	 * public void log(Exception ex, String level, String msg) {
	 * 
	 * try { DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd"); String
	 * filePath = "Logs" + File.separator + "log" + "%g%u.LOG"; boolean logFlag
	 * = new File(filePath).isFile(); if (!logFlag) { File logFile = new
	 * File(filePath); // PrintWriter writer = new PrintWriter(filePath,
	 * "UTF-8"); FileHandler fh = null; fh = new FileHandler(filePath, 10000, 2,
	 * true); logger.addHandler(fh); // logger.addHandler(new ConsoleHandler());
	 * logger.setUseParentHandlers(false); } switch (level) { case "Error":
	 * logger.log(Level.SEVERE, msg, ex); break; case "Debug":
	 * logger.log(Level.FINE, msg, ex); break; case "INFO":
	 * logger.log(Level.INFO, msg, ex); break; } } catch (IOException |
	 * SecurityException ex1) { logger.log(Level.SEVERE, null, ex1); } finally {
	 * Handler[] handler = logger.getHandlers(); if ((FileHandler) (handler[0])
	 * != null) ((FileHandler) handler[0]).close(); } }
	 */

}
