package com.steelejr.eclipse.aws.dashboard.newproject;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.steelejr.eclipse.aws.dashboard.Activator;

public class NewAwsJavaProjectPage extends WizardPage {

	protected NewAwsJavaProjectPage(String pageName) {
		super(pageName);
	}

	@Override
	public void createControl(Composite parent) {
		Composite comp = new Composite (parent, SWT.NONE);
		comp.setLayout(new GridLayout());
		comp.setLayoutData(new GridData (SWT.FILL, SWT.FILL, true, true));
		setImageDescriptor(Activator.getDefault().getImageRegistry().getDescriptor("new_javaprojectwiz"));
		setControl(comp);
	}
}
