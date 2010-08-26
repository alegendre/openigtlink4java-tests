package org.medcare.igtl.tests.server;

import org.medcare.igtl.util.ErrorManager;

public class MyErrorManager implements ErrorManager {

	@Override
	public void error (String message, Exception exception, int errorCode) {
		String text = "Server ErrorManager: " + errorCode;
		if (message != null) {
		      text = text + ": " + message;
		}
		System.err.println(text);
		if (exception != null) {
			exception.printStackTrace();
		}
	}
}
