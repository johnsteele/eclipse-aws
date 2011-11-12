package com.steelejr.eclipse.aws.dashboard;

import org.eclipse.core.runtime.IStatus;

/**
 * For logging errors.
 */
public class Logger {
	
	public static final Logger INSTANCE = new Logger ();
	
	public void log (IStatus status) {
		Activator.getDefault().getLog().log(status);
	}
}
