package com.steelejr.eclipse.aws.ec2.editor;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

import com.steelejr.eclipse.aws.dashboard.editor.DashboardPageProxy;

public class Ec2DashboardPage extends DashboardPageProxy {

	@Override
	public void createForm(IManagedForm managedForm) {
		
		/* Form and Toolkit */
		ScrolledForm form = managedForm.getForm();
		FormToolkit toolkit = managedForm.getToolkit();
		form.setText("Ec2");
		TableWrapLayout layout = new TableWrapLayout ();
		layout.numColumns = 2;
		form.getBody().setLayout(layout);
		
		/* Header Composite */
		createHeader (form, toolkit);
		
		/* Instances Section */
		createInstanceSection (form, toolkit);
		
		/* Details Section */
		createDetailsSection (form, toolkit);
	}
	
	
	/**
	 * Creates the header for the EC2 form page.
	 * 
	 * @param form The form to add the header.
	 * @param toolkit The shared toolkit instance.
	 */
	private void createHeader (ScrolledForm form, FormToolkit toolkit) {
		
	}
	
	/**
	 * Creates the Instance section that displays the instances associated with
	 * the active account.
	 * 
	 * @param form The form to add the section to.
	 * @param toolkit The shared form toolkit.
	 */
	private void createInstanceSection (ScrolledForm form, FormToolkit toolkit) {
		Section section = toolkit.createSection(form.getBody(), Section.TWISTIE | Section.TITLE_BAR | Section.DESCRIPTION | Section.EXPANDED);
		section.setText("Instances");
		section.setLayout(new TableWrapLayout ());
		section.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
		
		// client composite and layout.
		Composite client = toolkit.createComposite(section);
		TableWrapLayout layout = new TableWrapLayout();
		layout.numColumns = 2;
		client.setLayout(layout);
		client.setLayoutData(new TableWrapData (TableWrapData.FILL_GRAB));
		section.setClient(client);
		toolkit.paintBordersFor(client);
		
		// tree of instances.
		new Ec2Composite (client);
	}
	
	/**
	 * Creates the details sections which displays the details of the currently selected
	 * instance in the instance section.
	 * 
	 * @param form The form to create the section on.
	 * @param toolkit The shared form toolkit instance.
	 */
	private void createDetailsSection (ScrolledForm form, FormToolkit toolkit) {
		Section section = toolkit.createSection(form.getBody(), Section.TWISTIE | Section.TITLE_BAR | Section.DESCRIPTION | Section.EXPANDED);
		section.setText("Instance Details");
		section.setLayout (new TableWrapLayout ());
		section.setLayoutData(new TableWrapData (TableWrapData.FILL_GRAB));
		
		// client composite and layout.
		Composite client = toolkit.createComposite(section);
		TableWrapLayout layout = new TableWrapLayout ();
		client.setLayout(layout);
		client.setLayoutData(new TableWrapData (TableWrapData.FILL_GRAB));
		section.setClient(client);
		toolkit.paintBordersFor(client);		
	}
}
