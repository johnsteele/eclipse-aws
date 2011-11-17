package com.steelejr.eclipse.aws.dashboard.editor;

import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.forms.IManagedForm;

public interface IDashboardProxy {
	
	/**
	 * A callback to create the UI of the form page.
	 * @param managedForm
	 * @param site
	 */
	public void createForm(IManagedForm managedForm, IWorkbenchPartSite site);
}
