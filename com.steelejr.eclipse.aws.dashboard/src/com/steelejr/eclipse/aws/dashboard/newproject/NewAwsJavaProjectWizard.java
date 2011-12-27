package com.steelejr.eclipse.aws.dashboard.newproject;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

public class NewAwsJavaProjectWizard extends Wizard implements INewWizard {

	public NewAwsJavaProjectWizard() {
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		addPage(new NewAwsJavaProjectPage("New AWS Java Project"));
	}

	@Override
	public boolean performFinish() {
		return false;
	}

}
