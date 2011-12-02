package com.steelejr.eclipse.aws.dashboard.editor;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;

public class WelcomePage extends FormPage {

	public WelcomePage(FormEditor editor, String id, String title) {
		super(editor, id, title);
	}
	
	@Override
	protected void createFormContent(IManagedForm managedForm) {
		ScrolledForm form = managedForm.getForm();
		FormToolkit toolkit = managedForm.getToolkit();
		form.setText("Overview");
		
		form.getBody().setLayout(new GridLayout());
		
		createHeadSection ();
		
//		Section section = toolkit.createSection(form.getBody(), Section.TWISTIE | Section.TITLE_BAR | Section.DESCRIPTION);
//		section.setText("Testing");
		
		/* Bottom - New Features Feed */
	}
	
	
	private void createHeadSection () {
		
	}
	
	
	private void createGeneralInfoSection () {
		
	}
	
	
	private void createProjectSection () {
		
	}
	
	
	private void createEc2DownloadSection () {
		
	}
}
