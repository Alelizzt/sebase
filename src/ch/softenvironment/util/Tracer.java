package ch.softenvironment.util;

/* 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 */
 
/**
 * Global Tracer.
 * Design Pattern: Singleton
 *
 * This Tracer is useful as a development tool
 * to trace any Information while running an Application.
 * (This Tool is not foreseen for NLS-Support.)
 *
 * @author: Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.6 $ $Date: 2005-01-06 09:49:58 $
 */
public class Tracer {
	// Mode's
	public final static int SILENT = 1;  	// supress all Trace-logs
	public final static int NORMAL = 2;	 	// ~User-Compatible
	public final static int DEBUG = 3;   	// show specific debug-messages
	public final static int TRACE_SQL = 4;	// show specific SQL-logs
	public final static int ALL = 5;		// show all Trace-logs
	
	private static Tracer instance = null;
	private java.io.PrintStream outStream = null;
	private int mode = SILENT;
/**
 * Tracer constructor.
 * @see start(..)
 */
private Tracer() {
	super();
}
/**
 * Log a debug message.
 */
public void debug(Object obj, String methodName, String comment) {
	if ((mode == DEBUG) || (mode == ALL)) {
		log("Debug: ", obj, methodName, comment);
	}
}
/**
 * Log a debug message.
 */
public void debug(String comment) {
	if ((mode == DEBUG) || (mode == ALL)) {
		log("Debug: " + comment);
	}
}
/**
 * Log Developer Errors.
 */
public void developerError(Object obj, String methodName, String comment) {
	if ((mode == DEBUG) || (mode == ALL)) {
		log("Developer Error: ", obj, methodName, comment);
	}
}
/**
 * Log developer Warnings.
 */
public void developerWarning(Object obj, String methodName, String comment) {
	if ((mode == DEBUG) || (mode == ALL)) {
		log("Developer Warning: ", obj, methodName, comment);
	}
}
/**
 * Design Pattern: Singleton
 * @return Tracer.
 */
public static Tracer getInstance() {
	if (instance == null) {
		start(SILENT);
	}
	return instance;
}
/**
 * Trace Nasty codings.
 */
public void hack(Object obj, String methodName, String comment) {
	if (mode == ALL) {
		// for e.g. hack(..., ..., "recode this..");
		log("Hack:", obj, methodName, comment);
	}
}
/**
 * Print Log-Infos with leading Timestamp.
 */
private void log(String logMessage) {
	if (mode != SILENT) {
		outStream.println(NlsUtils.getEuropeanTimestampString() + ">" + logMessage);
	}
}
/**
 * Print Log-Infos.
 */
private void log(String errorType, Object obj, String methodName, String comment) {
	String sender = null;
	if (obj == null) {
		sender = "<???>";
	} else if (obj.getClass().equals(java.lang.Class.class)) {
		// class was the sender
		sender = ((java.lang.Class)obj).getName();
	} else {
		// instance was the sender
		sender = obj.getClass().getName();
	}
	log(errorType + "->" + comment + " in <" + sender + "#" + methodName + ">");
}
/**
 * Keep Not Yet Implemented code references.
 */
public void nyi(Object obj, String methodName) {
	nyi(obj, methodName, "");
}
/**
 * Keep Not Yet Implemented code references.
 */
public void nyi(Object obj, String methodName, String comment) {
	if ((mode == DEBUG) || (mode == ALL)) {
		log("NYI:", obj, methodName, comment);
	}
}
/**
 * Keep temporary fixes references.
 */
public void patch(Object obj, String methodName, String comment) {
	if (mode == ALL) {
		log("Patch:", obj, methodName, comment);
	}
}
/**
 * Log Errors during runtime.
 */
public void runtimeError(Object obj, String methodName, String comment) {
	log("Runtime Error:", obj, methodName, comment);
}
/**
 * Log intormations during runtime.
 */
public void runtimeInfo(String comment) {
	log("Info: " + comment);
}
/**
 * Log Warnings during runtime.
 */
public void runtimeWarning(Object obj, String methodName, String comment) {
	log("Runtime Warning:", obj, methodName, comment);
}
/**
 * Log an SQL-message.
 */
public void sql(Object obj, String methodName, String sqlString) {
	sql(sqlString);
}
/**
 * Log an SQL-message.
 */
public void sql(String sqlString) {
	if ((mode == TRACE_SQL) || (mode == ALL)) {
		log("SQL: " + sqlString);
	}
}
/**
 * Start Tracer and use Console-Error.
 * @param args Command line arguments ("-all, -silent", "-trace", "traceSQL" or "-debug")
 */
public static java.lang.String[] start(java.lang.String[] args) {
	int mode = SILENT;	// default
	java.util.ArrayList ret=new java.util.ArrayList(java.util.Arrays.asList(args));
	if (args != null) {
		java.util.Iterator it=ret.iterator();
		while (it.hasNext()) {
			String option = (String)it.next();
			if (option.equalsIgnoreCase("-debug")) {
				mode = DEBUG;
				it.remove();
				break;
			} else if (option.equalsIgnoreCase("-trace")) {
				mode = NORMAL;
				it.remove();
				break;
			} else if (option.equalsIgnoreCase("-traceSQL")) {
				mode = TRACE_SQL;
				it.remove();
				break;
			} else if (option.equalsIgnoreCase("-all")) {
				mode = ALL;
				it.remove();
				break;
			}
		}
	}
	start((java.io.PrintStream)getConsoleError(), mode);
	return (java.lang.String[])ret.toArray(new String[0]);
}
/**
 * Start Tracer and use Console-Error.
 * @param mode (SILENT, NORMAL, DEBUG, TRACE_SQL, ALL)
 */
public static void start(int mode) {
	start((java.io.PrintStream)getConsoleError(), mode);
}
/**
 * Start Tracer.
 * @param stream Outstream for logging
 * @param traceOn (whether silent or not) 
 */
public static void start(java.io.PrintStream stream, int mode) {
	instance = new Tracer();
	instance.outStream = stream;
	instance.mode = mode;
	instance.debug("START Tracer");
	instance.runtimeInfo("Java Version: " + System.getProperty("java.version"));
	instance.runtimeInfo("Java VM Version: " + System.getProperty("java.vm.version"));
	instance.runtimeInfo("OS Name: " + System.getProperty("os.name"));
	instance.runtimeInfo("OS Architecture: " + System.getProperty("os.arch"));
	instance.runtimeInfo("OS Version: " + System.getProperty("os.version"));
	instance.runtimeInfo("OS Locale: " + java.util.Locale.getDefault().toString());
}
/**
 * Stop Tracer.
 */
public void stop() {
//	try {
		debug("STOP Tracer");
		outStream.close();
		instance = null;
/*	} catch(java.io.IOException e) {
		e.printStackTrace(System.out);
	}
*/
}
/**
 * Keep reference to tuning potential in code.
 */
public void tune(Object obj, String methodName, String comment) {
	if (mode == ALL) {
		// for e.g. tune(..., ..., "slow");
		log("Tune:", obj, methodName, comment);
	}
}
/**
 * Use this message to trace non-visually handled exceptions which
 * might be ignored during application run.
 */
public void uncaughtException(Object obj, String methodName, Throwable exception) {
	exception.printStackTrace(outStream);
	log("Uncaught Exception:", obj, methodName, exception.toString());
}

/**
 * @return console-error Stream
 */
public static java.io.OutputStream getConsoleError() {
	return System.err;
}
}
