package com.steelejr.eclipse.aws.marketplace.wizard;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.equinox.internal.p2.core.ProvisioningAgent;
import org.eclipse.equinox.internal.p2.ui.ProvisioningOperationRunner;
import org.eclipse.equinox.internal.p2.ui.dialogs.SelectableIUsPage;
import org.eclipse.equinox.p2.core.IProvisioningAgent;
import org.eclipse.equinox.p2.engine.IEngine;
import org.eclipse.equinox.p2.engine.IProfile;
import org.eclipse.equinox.p2.engine.IProfileRegistry;
import org.eclipse.equinox.p2.engine.IProvisioningPlan;
import org.eclipse.equinox.p2.engine.PhaseSetFactory;
import org.eclipse.equinox.p2.engine.ProvisioningContext;
import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.operations.ProvisioningJob;
import org.eclipse.equinox.p2.planner.IPlanner;
import org.eclipse.equinox.p2.planner.IProfileChangeRequest;
import org.eclipse.equinox.p2.ui.AcceptLicensesWizardPage;
import org.eclipse.equinox.p2.ui.ProvisioningUI;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Display;

public class LicenseWizard extends Wizard {
	
	private SelectableIUsPage selectIUpage;
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
		return installInstallableUnit ();
	}	
	
	private boolean installInstallableUnit () {
		
		final boolean[] install = new boolean [1];
		final Display display = Display.getCurrent();
		display.syncExec(new Runnable() {
			
			@Override
			public void run() {
				install[0] = MessageDialog.openConfirm(display.getActiveShell(), "Confirm Installation", "Are you sure you want to install this software?");
			}
		});
		
		// They pressed cancel on the message dialog,
		// so close the license wizard.
		if (!install[0]) {
			return true;
		}
		
		Job job = new Job("Installing New Feature") {
			
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				IProvisioningAgent agent = NewFeatureUtil.getAgent();
				IPlanner planner = (IPlanner) agent.getService(IPlanner.SERVICE_NAME);
				
				IProfileRegistry registry = (IProfileRegistry) agent.getService(IProfileRegistry.SERVICE_NAME);
				IProfile profile = registry.getProfile(IProfileRegistry.SELF);
				
				IProfileChangeRequest profileChangeRequest = planner.createChangeRequest(profile);
				profileChangeRequest.add(unitsToInstall[0]);
				
				ProvisioningContext context = new ProvisioningContext(agent);
				
				IProvisioningPlan plan = planner.getProvisioningPlan(profileChangeRequest, context, monitor);
				
				IEngine engine = (IEngine) agent.getService(IEngine.SERVICE_NAME);
				return engine.perform(plan, PhaseSetFactory.createDefaultPhaseSet(), monitor);
			}
		};
		
		// Ask user for restart once installation is complete.
		ProvisioningOperationRunner por = new ProvisioningOperationRunner(ProvisioningUI.getDefaultUI());
		por.manageJob(job, ProvisioningJob.RESTART_OR_APPLY);
		job.setUser(true);
		job.schedule();
		
		return true;
	}
}
