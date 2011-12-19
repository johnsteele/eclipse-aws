package com.steelejr.eclipse.aws.marketplace.wizard;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;

import com.steelejr.eclipse.aws.dashboard.editor.DashboardPageProxy;
import com.steelejr.eclipse.aws.marketplace.editor.NewEc2FeaturesSection;

/**
 * The marketplace dash board page.
 * 
 * @author John Steele
 */
public class NewFeaturePage extends DashboardPageProxy {

	private IManagedForm my_form;
	private IEditorSite my_site;

	/**
	 * The extension-point callback.
	 */
	public void createForm(IManagedForm managedForm, IWorkbenchPartSite site) {

		my_form = managedForm;
		my_site = (IEditorSite) site;

		ScrolledForm form = my_form.getForm();
		FormToolkit toolkit = my_form.getToolkit();
		form.setText("New AWS Features");
		form.getBody().setLayout(new GridLayout(1, true));

		createHeaderSection(form, toolkit);
		createEc2DownloadSection(form, toolkit);
		createBeanstockDownloadSection(form, toolkit);
		createSimpleDbDownloadSection(form, toolkit);
	}

	private void createHeaderSection(ScrolledForm form, FormToolkit toolkit) {
		toolkit.decorateFormHeading(form.getForm());
	}

	private void createEc2DownloadSection(ScrolledForm form, FormToolkit toolkit) {
		new NewEc2FeaturesSection(form, toolkit);
	}

	private void createBeanstockDownloadSection(ScrolledForm form,
			FormToolkit toolkit) {
	}

	private void createSimpleDbDownloadSection(ScrolledForm form,
			FormToolkit toolkit) {
	}
}