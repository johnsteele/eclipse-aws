package com.steelejr.eclipse.aws.dashboard.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

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
		
		Button button = toolkit.createButton(form.getBody(), "Progress Bar", SWT.PUSH);
		button.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				IActionBars bars = getEditorSite().getActionBars();
				IStatusLineManager statusLine = bars.getStatusLineManager();
				IProgressMonitor pm = statusLine.getProgressMonitor();
				pm.beginTask("Donig Worlk", IProgressMonitor.UNKNOWN);
				try {
					Thread.currentThread().sleep(10000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				pm.done();
			}
		});
		
//		Section section = toolkit.createSection(form.getBody(), Section.TWISTIE | Section.TITLE_BAR | Section.DESCRIPTION);
//		section.setText("Testing");
		
		/* Bottom - New Features Feed */
	}

}
