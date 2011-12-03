package com.steelejr.eclipse.aws.dashboard.editor;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

import com.steelejr.eclipse.aws.dashboard.Activator;

public class WelcomePage extends FormPage {

	public WelcomePage(FormEditor editor, String id, String title) {
		super(editor, id, title);
	}
	
	@Override
	protected void createFormContent(IManagedForm managedForm) {
		ScrolledForm form = managedForm.getForm();
		FormToolkit toolkit = managedForm.getToolkit();
		form.setText("Overview");
		toolkit.decorateFormHeading(form.getForm());
		
		form.getBody().setLayout(new GridLayout(2, true));
		
		createHeadSection (form, toolkit);
		createGeneralInfoSection(form, toolkit);
		createProjectSection(form, toolkit);
	}
	
	
	private void createHeadSection (ScrolledForm form, FormToolkit toolkit) {
		
	}
	
	
	private void createGeneralInfoSection (ScrolledForm form, FormToolkit toolkit) {
		Section section = toolkit.createSection(form.getBody(), Section.TITLE_BAR);
		section.setText("Account Information");
		section.setLayout(new GridLayout ());
		section.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		FormText description = toolkit.createFormText(section, false);
		description.setText("<form><p>This section describes general information about the active AWS account.</p></form>", true, false);
		section.setDescriptionControl(description);
		
		Composite client = toolkit.createComposite(section, SWT.NONE);
		client.setLayout(new GridLayout (3, false));
		client.setLayoutData(new GridData (GridData.FILL_BOTH));
		section.setClient(client);
		toolkit.paintBordersFor(client);
		
		toolkit.createLabel(client, "ID:", SWT.NONE);
		Text accountId = toolkit.createText(client, "");
		GridData data = new GridData ();
		data.horizontalAlignment = SWT.FILL;
		data.verticalAlignment   = SWT.FILL;
		data.horizontalSpan      = 2;
		data.grabExcessHorizontalSpace = true;
		accountId.setLayoutData(data);
		
		toolkit.createLabel(client, "Since:", SWT.NONE);
		Text since = toolkit.createText(client, "");
		data = new GridData ();
		data.horizontalAlignment = SWT.FILL;
		data.verticalAlignment   = SWT.FILL;
		data.horizontalSpan      = 2;
		data.grabExcessHorizontalSpace = true;
		since.setLayoutData(data);
		
		
		Hyperlink link = toolkit.createHyperlink(client, "Account:", SWT.WRAP);
		Text location = toolkit.createText(client, "");
		data = new GridData ();
		data.horizontalAlignment = SWT.FILL;
		data.grabExcessHorizontalSpace = true;
		location.setLayoutData(data);		
		Button browse = toolkit.createButton(client, "Browse...", SWT.PUSH);		

		Composite checkComp = toolkit.createComposite(client);
		checkComp.setLayout(new GridLayout ());
		data = new GridData ();
		data.grabExcessHorizontalSpace = true;
		data.horizontalSpan = 3;
		data.horizontalAlignment = SWT.FILL;
		data.verticalAlignment   = SWT.FILL;
		checkComp.setLayoutData(data);
				
		Button check = toolkit.createButton(checkComp, "Activate this account on start-up.", SWT.CHECK);
		check.setSelection(true);
		
		browse.pack();
		client.pack();
		form.reflow(true);
	}
	
	
	private void createProjectSection (ScrolledForm form, FormToolkit toolkit) {
		Section section = toolkit.createSection(form.getBody(), Section.TITLE_BAR);
		section.setText("Create Project");
		section.setLayout (new GridLayout ());
		section.setLayoutData(new GridData (GridData.FILL_BOTH));
		
		FormText description = toolkit.createFormText(section, false);
		description.setText("<form><p>Create an AWS project in the following section:</p></form>", true, false);
		section.setDescriptionControl(description);
		
		Composite client = toolkit.createComposite(section, SWT.NONE);
		client.setLayout(new GridLayout (3, false));
		client.setLayoutData(new GridData (GridData.FILL_BOTH));		
		section.setClient(client);
		
		Label projImage = toolkit.createLabel(client, "");
		projImage.setImage(Activator.getDefault().getImageRegistry().get("new_server_project"));
		Hyperlink link = toolkit.createHyperlink(client, "AWS Java Project:", SWT.WRAP);
		Text text = toolkit.createText(client, "Create a new AWS Java-based project.");
		
		form.reflow(true);
	}
	
	
	private void createEc2DownloadSection (ScrolledForm form, FormToolkit toolkit) {
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
	
	@Override
	public void setFocus() {
		
	}
}
