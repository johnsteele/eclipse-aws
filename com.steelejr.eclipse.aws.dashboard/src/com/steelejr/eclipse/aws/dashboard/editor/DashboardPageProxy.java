package com.steelejr.eclipse.aws.dashboard.editor;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;

public class DashboardPageProxy extends FormPage implements IDashboardProxy {

	private IConfigurationElement element;
	
	public DashboardPageProxy() {
		super(null, "default", "default");
	}

	public DashboardPageProxy(FormEditor editor, String id, String title,
			IConfigurationElement element) {
		super(editor, id, title);
		this.element = element;
	}
	

	@Override
	protected void createFormContent(IManagedForm managedForm) {
		createForm (managedForm, getSite());
	}
	
	@Override
	public void createForm(IManagedForm managedForm, IWorkbenchPartSite site) {
		try {
			Object o = element.createExecutableExtension("class");
			if (o instanceof DashboardPageProxy) {
				IDashboardProxy proxy = (IDashboardProxy) o;
				proxy.createForm(managedForm, getSite());
			}
		} catch (CoreException e) {
			System.out.println("Failed to create the Delegate...");
			e.printStackTrace();
		}
	}
}
