package com.steelejr.eclipse.aws.marketplace.wizard;

import org.eclipse.equinox.internal.p2.ui.dialogs.ProvisioningOperationWizard;
import org.eclipse.equinox.internal.p2.ui.dialogs.SelectableIUsPage;
import org.eclipse.equinox.internal.p2.ui.model.IUElementListRoot;
import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.ui.AcceptLicensesWizardPage;
import org.eclipse.equinox.p2.ui.ProvisioningUI;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;

import com.steelejr.eclipse.aws.marketplace.Activator;

public class InstallFeatureWizard extends Wizard {
	
	private InstallFeatureWizardPage installFeaturePage;
	private AcceptLicensesWizardPage licensePage;
	private SelectableIUsPage selectFeaturesPage;
	
	public InstallFeatureWizard() {
		super ();
		setWindowTitle("Install New Feature Wizard");
		setDefaultPageImageDescriptor(Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/wizban/install_wiz.gif"));
		setNeedsProgressMonitor(true);
		setForcePreviousAndNextButtons(true);
	}
	
	
	@Override
	public void addPages() {
		super.addPages();
		ProvisioningUI ui = ProvisioningUI.getDefaultUI();
		
		licensePage = new AcceptLicensesWizardPage(ui.getLicenseManager(), new IInstallableUnit[0], null);
		licensePage.setWizard(this);
	}

	@Override
	public int getPageCount() {
		return 1;
	}
	
	
	@Override
	public IWizardPage getStartingPage() {
		return licensePage;
	}
	
	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		return licensePage;
	}

	
	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		return false;
	}
}
