package com.steelejr.eclipse.aws.inernal.jobs;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.ui.progress.IProgressConstants;

import com.steelejr.eclipse.aws.ec2.Activator;

public class DescribeInstanceJob extends Job {

	
	/**
	 * Creates a DescribeInstanceJob with specified values.
	 * 
	 * @param name The name of the job to be displayed on the status line.
	 */
	public DescribeInstanceJob(String name) {
		super("Describe Instances Job");
		
		setProperty(IProgressConstants.ICON_PROPERTY, 
					Activator.imageDescriptorFromPlugin(
							Activator.PLUGIN_ID, 
							"icons/jobs/aws-box.gif"));
		
		setProperty(IProgressConstants.NO_IMMEDIATE_ERROR_PROMPT_PROPERTY, true);
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		
		monitor.beginTask("Quering Ec2 instances", IProgressMonitor.UNKNOWN);
		for (int i = 0; i < 500000; i++) {
			for (int j = 0; j < 10000; j++) 
				;
			if (monitor.isCanceled()) return Status.CANCEL_STATUS;
			monitor.worked(1);
		}
		monitor.done();
		
		return Status.OK_STATUS;
	}
}
