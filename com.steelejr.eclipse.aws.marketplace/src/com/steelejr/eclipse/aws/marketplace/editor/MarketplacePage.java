package com.steelejr.eclipse.aws.marketplace.editor;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

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
		
		createHeaderSection (form, toolkit);
		createDownloadSection (form, toolkit);
	}
	
	private void createHeaderSection (ScrolledForm form, FormToolkit toolkit) {
		
	}
	
	
	private void createDownloadSection (ScrolledForm form, FormToolkit toolkit) {
		Section section = toolkit.createSection(form.getBody(), Section.TITLE_BAR);
		section.setLayout(new GridLayout ());
		GridData data = new GridData ();
		data.horizontalSpan = 2;
		data.horizontalAlignment = SWT.FILL;
		data.verticalAlignment   = SWT.FILL;
		data.grabExcessHorizontalSpace = true;
		data.grabExcessVerticalSpace   = true;
		section.setLayoutData(data);
		section.setText("Promoted Plug-in Downloads");
		
		FormText description = toolkit.createFormText(section, false);
		description.setText("<form><p>Download additional Eclipse plug-ins and AWS support in this section.</p></form>", true, false);
		section.setDescriptionControl(description);
		
		Composite client = toolkit.createComposite(section);
		client.setLayout(new GridLayout ());
		client.setLayoutData(new GridData (GridData.FILL_BOTH));
		section.setClient(client);
	}
}
