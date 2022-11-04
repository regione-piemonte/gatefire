package it.csi.gatefire.common.util;

import org.slf4j.Logger;

public class LogUtils {

	private LogUtils() {
		super();

	}

	private static final int STACK_TRACE_LEVEL_METHOD = 3;
	private static final String METODO = "metodo: ";

	private static final String PLACEHOLDER = " {} ";

	public static void logError(Logger log, String msg) {

		final StackTraceElement[] ste = Thread.currentThread().getStackTrace();

		String metodo = ste[STACK_TRACE_LEVEL_METHOD].getMethodName();
		log.error(METODO + PLACEHOLDER + " - " + PLACEHOLDER, metodo, msg);

	}

	public static void logError(Logger log, Exception e) {

		final StackTraceElement[] ste = Thread.currentThread().getStackTrace();

		String metodo = ste[STACK_TRACE_LEVEL_METHOD].getMethodName();
		log.error(METODO + PLACEHOLDER + " - " + PLACEHOLDER, metodo, getTraceErrorString(e));

	}

	public static void logInfo(Logger log, String msg) {
		final StackTraceElement[] ste = Thread.currentThread().getStackTrace();

		String metodo = ste[STACK_TRACE_LEVEL_METHOD].getMethodName();

		log.info(METODO + PLACEHOLDER + " - " + PLACEHOLDER, metodo, msg);

	}

	public static void logInfo(Logger log, Exception e) {

		final StackTraceElement[] ste = Thread.currentThread().getStackTrace();

		String metodo = ste[STACK_TRACE_LEVEL_METHOD].getMethodName();
		log.info(METODO + PLACEHOLDER + " - " + PLACEHOLDER, metodo, getTraceErrorString(e));
	}

	public static void logDebug(Logger log, String msg) {
		final StackTraceElement[] ste = Thread.currentThread().getStackTrace();

		String metodo = ste[STACK_TRACE_LEVEL_METHOD].getMethodName();

		log.debug(METODO + PLACEHOLDER + " - " + PLACEHOLDER, metodo, msg);

	}

	public static void logDebug(Logger log, Exception e) {

		final StackTraceElement[] ste = Thread.currentThread().getStackTrace();

		String metodo = ste[STACK_TRACE_LEVEL_METHOD].getMethodName();
		log.debug(METODO + PLACEHOLDER + " - " + PLACEHOLDER, metodo, getTraceErrorString(e));
	}

	public static void logWarn(Logger log, String msg) {
		final StackTraceElement[] ste = Thread.currentThread().getStackTrace();

		String metodo = ste[STACK_TRACE_LEVEL_METHOD].getMethodName();

		log.warn(METODO + PLACEHOLDER + " - " + PLACEHOLDER, metodo, msg);

	}

	public static void logWarn(Logger log, Exception e) {

		final StackTraceElement[] ste = Thread.currentThread().getStackTrace();

		String metodo = ste[STACK_TRACE_LEVEL_METHOD].getMethodName();
		log.warn(METODO + PLACEHOLDER + " - " + PLACEHOLDER, metodo, getTraceErrorString(e));
	}

	private static String getTraceErrorString(Exception e) {
		StringBuilder sb = new StringBuilder("");
		if (e.getMessage() != null) {
			sb = new StringBuilder(e.getMessage());
		}
		for (StackTraceElement traceElement : e.getStackTrace()) {
			if (traceElement.getClassName().contains("acqunico")) {

				if (traceElement.getFileName() != null && traceElement.getLineNumber() >= 0) {
					sb.append(" (" + traceElement.getFileName() + ":" + traceElement.getLineNumber() + ")");
				} else if (traceElement.getFileName() != null) {
					sb.append(" (" + traceElement.getFileName() + ":(Unknown Source))");
				}

				break;
			}

		}
		return sb.toString();
	}

}
