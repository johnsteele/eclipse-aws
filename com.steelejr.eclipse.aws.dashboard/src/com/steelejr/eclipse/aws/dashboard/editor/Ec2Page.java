package com.steelejr.eclipse.aws.dashboard.editor;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.HyperlinkSettings;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

/**
 * Page to display EC2 related material.
 */
public class Ec2Page extends FormPage {

	public Ec2Page(FormEditor editor, String id, String title) {
		super(editor, id, title);
	}

	@Override
	protected void createFormContent(IManagedForm managedForm) {
		
		ScrolledForm form = managedForm.getForm();
		FormToolkit toolkit = managedForm.getToolkit();
		toolkit.getHyperlinkGroup().setHyperlinkUnderlineMode(HyperlinkSettings.UNDERLINE_HOVER);
		
		form.setText("Ec2");
		toolkit.decorateFormHeading(form.getForm());
		TableWrapLayout layout = new TableWrapLayout();
		layout.numColumns = 2;
		form.getBody().setLayout(layout);
		
		/* Master - Instance Section */
		Section section = toolkit.createSection(form.getBody(), Section.EXPANDED | Section.TWISTIE | Section.TITLE_BAR | Section.DESCRIPTION);
		layout = new TableWrapLayout();
		layout.numColumns = 2;
		section.setLayout(layout);
		section.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
		section.setText("Ec2 Instances");
		section.setDescription("Current Elastic Compute Cloud Instances");
		
		Composite client = toolkit.createComposite(section, SWT.WRAP);
		layout = new TableWrapLayout();
		layout.numColumns = 2;
		client.setLayout(layout);
		client.setLayoutData(new TableWrapData(TableWrapData.FILL, TableWrapData.FILL));
		section.setClient(client);
		toolkit.paintBordersFor(client);
		
		// Left column - Tree of instances.
		
		// Right column - Add instance.
		
		
		/* Details - Instance Section */
		
		
	}
}
