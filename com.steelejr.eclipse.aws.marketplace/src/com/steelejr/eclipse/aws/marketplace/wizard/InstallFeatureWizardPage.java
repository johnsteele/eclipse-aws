package com.steelejr.eclipse.aws.marketplace.wizard;

import org.eclipse.equinox.p2.ui.AcceptLicensesWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class InstallFeatureWizardPage extends WizardPage {
	
	private AcceptLicensesWizardPage licensePage;

	public InstallFeatureWizardPage(AcceptLicensesWizardPage licensePage) {
		super("feature");
		this.licensePage = licensePage;
		setTitle("Feature Title");
		setDescription("Select features to install.");
		setPageComplete(false);
	}

	@Override
	public void createControl(Composite parent) {
		Composite client  = new Composite (parent, SWT.NULL);
		
		setControl(client);
	}
}
