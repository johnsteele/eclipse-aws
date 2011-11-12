package com.steelejr.eclipse.aws.dashboard.editor;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.HyperlinkSettings;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

import com.steelejr.eclipse.aws.dashboard.Activator;

public class OverviewPage extends FormPage {

	public OverviewPage(FormEditor editor, String id, String title) {
		super(editor, id, title);
	}
	
	@Override
	protected void createFormContent(IManagedForm managedForm) {
		ScrolledForm form = managedForm.getForm();
		FormToolkit toolkit = managedForm.getToolkit();
		
		toolkit.getHyperlinkGroup().setHyperlinkUnderlineMode(HyperlinkSettings.UNDERLINE_HOVER);
	
		/* Header */
		form.setText("AWS Tool Suite");
		toolkit.decorateFormHeading(form.getForm());
		
		/* Body Layout */
		GridLayout layout = new GridLayout ();
		layout.numColumns = 2;
		form.getBody().setLayout(layout);
		
		/* Getting Started Section */
		Section section = toolkit.createSection(form.getBody(), Section.TITLE_BAR | Section.TWISTIE | Section.EXPANDED);
		section.setText("Steps to Getting Started");
		Composite client = toolkit.createComposite(section, SWT.WRAP);
		
		
		
		
	}
}
