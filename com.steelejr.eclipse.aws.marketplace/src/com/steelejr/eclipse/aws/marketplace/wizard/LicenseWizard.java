package com.steelejr.eclipse.aws.marketplace.wizard;

import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.ui.AcceptLicensesWizardPage;
import org.eclipse.equinox.p2.ui.ProvisioningUI;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;

public class LicenseWizard extends Wizard {
	
	private AcceptLicensesWizardPage licensePage;
	private IInstallableUnit[] unitsToInstall;
	
	public LicenseWizard(IInstallableUnit[] unitsToInstall) {
		super ();
		this.unitsToInstall = unitsToInstall;
		setWindowTitle("License Agreement");
		setNeedsProgressMonitor(true);
		setForcePreviousAndNextButtons(true);
	}
	
	@Override
	public void addPages() {
		super.addPages();
		ProvisioningUI ui = ProvisioningUI.getDefaultUI();	
		licensePage = new AcceptLicensesWizardPage(ui.getLicenseManager(), unitsToInstall, null);
		licensePage.updateForPlan(unitsToInstall, null);
		licensePage.setWizard(this);
	}
	
	@Override
	public int getPageCount() {
		return 1;
	}
	
	@Override
	public boolean canFinish() {
		return licensePage.isPageComplete();
	}
	
	@Override
	public IWizardPage[] getPages() {
		return new IWizardPage[] { licensePage };
		
	}
	
	@Override
	public IWizardPage getStartingPage() {
		return licensePage;
	}
	
	@Override
	public boolean performFinish() {
		return NewFeatureUtil.installInstallableUnit (unitsToInstall);
	}	
}
