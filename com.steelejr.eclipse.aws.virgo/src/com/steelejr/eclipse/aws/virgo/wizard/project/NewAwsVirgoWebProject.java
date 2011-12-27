package com.steelejr.eclipse.aws.virgo.wizard.project;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import com.steelejr.eclipse.aws.dashboard.editor.IAwsProject;

public class NewAwsVirgoWebProject extends Wizard implements INewWizard, IAwsProject {

	public NewAwsVirgoWebProject() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		return false;
	}

}
