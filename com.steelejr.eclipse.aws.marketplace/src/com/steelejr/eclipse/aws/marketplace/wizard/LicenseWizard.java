package com.steelejr.eclipse.aws.marketplace.wizard;

import org.eclipse.equinox.internal.p2.metadata.InstallableUnit;
import org.eclipse.equinox.internal.p2.ui.dialogs.SelectableIUsPage;
import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.ui.AcceptLicensesWizardPage;
import org.eclipse.equinox.p2.ui.ProvisioningUI;
import org.eclipse.jface.wizard.Wizard;

public class LicenseWizard extends Wizard {
	
	private SelectableIUsPage selectIUpage;
	private AcceptLicensesWizardPage licensePage;
	
	public LicenseWizard() {
		super ();
		setWindowTitle("License Agreement");
		setNeedsProgressMonitor(true);
		setForcePreviousAndNextButtons(true);
	}

	@Override
	public boolean performFinish() {
		return true;
	}	
	
	@Override
	public void addPages() {
		super.addPages();
		ProvisioningUI ui = ProvisioningUI.getDefaultUI();
		IInstallableUnit units = new InstallableUnit();
		IInstallableUnit[] intall = new IInstallableUnit[] {units};
		licensePage = new AcceptLicensesWizardPage(ui.getLicenseManager(), intall, null);
		licensePage.setWizard(this);
	}

}
