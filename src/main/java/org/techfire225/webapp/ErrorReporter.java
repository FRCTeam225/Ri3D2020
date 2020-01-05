package org.techfire225.webapp;

import com.google.gson.JsonArray;

public class ErrorReporter {
	static JsonArray errors = new JsonArray();
	
	public static synchronized void report(String error) {
		try {
			errors.add(error);
		} catch (Exception e) {}
	}
	
	public static synchronized String toJSON() {
		return errors.toString();
	}
}
