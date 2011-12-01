package com.steelejr.eclipse.aws.marketplace.ui;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;

import com.steelejr.eclipse.aws.dashboard.editor.DashboardPageProxy;


/**
 * The marketplace dash board page. 
 * 
 * @author John Steele
 */
public class MarketplacePage extends DashboardPageProxy {
	
	private IManagedForm my_form;
	private IEditorSite  my_site;

	/**
	 * The extension-point callback.
	 */
	public void createForm(IManagedForm managedForm, IWorkbenchPartSite site) {
	
		my_form = managedForm;
		my_site = (IEditorSite) site;
		
		ScrolledForm form = my_form.getForm();
		FormToolkit  toolkit = my_form.getToolkit();
		
		form.getBody().setLayout(new GridLayout());
	}
}
