package com.steelejr.eclipse.aws.dashboard.editor;

import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.forms.IManagedForm;

public interface IDashboardProxy {
	
	public void createForm(IManagedForm managedForm, IWorkbenchPartSite site);
}
