package com.steelejr.eclipse.aws.marketplace.editor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.equinox.p2.core.IProvisioningAgent;
import org.eclipse.equinox.p2.engine.IProfile;
import org.eclipse.equinox.p2.engine.IProfileRegistry;
import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.query.IQuery;
import org.eclipse.equinox.p2.query.IQueryResult;
import org.eclipse.equinox.p2.query.QueryUtil;
import org.eclipse.jface.preference.IPreferenceStore;
import org.osgi.framework.ServiceReference;

import com.steelejr.eclipse.aws.marketplace.Activator;
import com.steelejr.eclipse.aws.marketplace.wizard.NewFeatureUtil;
import com.steelejr.eclipse.aws.marketplace.wizard.NewFeatureXMLParser;

public class NewFeatureUtility {
	
	private IProgressMonitor progressMonitor; 

	
	/**
	 * Returns a list of the installed features.
	 * 
	 * @return List of installed features.
	 */
	public static List<IInstallableUnit> getInstalledIU() {
		List<IInstallableUnit> installedList = new ArrayList<IInstallableUnit>();

		// Get the Agent Service Reference
		ServiceReference<?> agentSr = Activator.getDefault().getBundle()
				.getBundleContext()
				.getServiceReference(IProvisioningAgent.SERVICE_NAME);
		if (agentSr == null) {
			return null;
		}

		// Get the Agent Service.
		IProvisioningAgent agent = (IProvisioningAgent) Activator.getDefault()
				.getBundle().getBundleContext().getService(agentSr);

		// Get the Profile Registry.
		IProfileRegistry profileRegistry = (IProfileRegistry) agent
				.getService(IProfileRegistry.class.getName());

		// Get the Running System Profile.
		IProfile runningSystemProfile = profileRegistry
				.getProfile(IProfileRegistry.SELF);

		// Query all Installable Units.
		IQuery<IInstallableUnit> query = QueryUtil.createIUAnyQuery();
		IQueryResult<IInstallableUnit> collector = runningSystemProfile.query(
				query, new NullProgressMonitor());

		// Go through installed features, add them to installed list.
		Iterator<IInstallableUnit> iter = collector.iterator();
		while (iter.hasNext()) {
			installedList.add(iter.next());
		}

		// Un-get the Agent Service.
		Activator.getDefault().getBundle().getBundleContext()
				.ungetService(agentSr);
		
		

		return installedList;
		//return installedList.subList(1, 10);
		
	}
	
	
	public static List<IInstallableUnit> getNewFeatures () {
		List<IInstallableUnit> unitsToInstall = new ArrayList<IInstallableUnit>();
		List<IInstallableUnit> installed = NewFeatureUtility.getInstalledIU();
		
		return unitsToInstall;
	}
	
	
	private static List<IInstallableUnit> getUpdateSiteFeatures () {
		return null;
	}
	
	
	private static String[] getUpdateSites () {
		
		// Get the initial update site from plugin.
		//IPreferenceStore store = Activator.getDefault().getBundle().getEntry("updateSites.xml");
		return null;
	}

	
	
	private IInstallableUnit[] getExtensions() {

		List<IInstallableUnit> installedList = new ArrayList<IInstallableUnit>();

		// Get the Agent Service Reference
		ServiceReference<?> agentSr = Activator.getDefault().getBundle()
				.getBundleContext()
				.getServiceReference(IProvisioningAgent.SERVICE_NAME);
		if (agentSr == null) {
			return null;
		}

		// Get the Agent Service.
		IProvisioningAgent agent = (IProvisioningAgent) Activator.getDefault()
				.getBundle().getBundleContext().getService(agentSr);

		// Get the Profile Registry.
		IProfileRegistry profileRegistry = (IProfileRegistry) agent
				.getService(IProfileRegistry.class.getName());

		// Get the Running System Profile.
		IProfile runningSystemProfile = profileRegistry
				.getProfile(IProfileRegistry.SELF);

		// Query all Installable Units.
		IQuery<IInstallableUnit> query = QueryUtil.createIUAnyQuery();
		IQueryResult<IInstallableUnit> collector = runningSystemProfile.query(
				query, new NullProgressMonitor());

		// Go through installed features, add them to installed list.
		Iterator<IInstallableUnit> iter = collector.iterator();
		while (iter.hasNext()) {
			installedList.add(iter.next());
		}

		// Un-get the Agent Service.
		Activator.getDefault().getBundle().getBundleContext()
				.ungetService(agentSr);

		return (IInstallableUnit[]) installedList.toArray();
		// Get the agent.
		// ServiceReference<?> sr =
		// Activator.getDefault().getBundle().getBundleContext().getServiceReference(IProvisioningAgentProvider.SERVICE_NAME);
		// if (sr == null)
		// return null;
		//
		// IProvisioningAgentProvider agentProvider = null;
		// agentProvider = (IProvisioningAgentProvider)
		// Activator.getDefault().getBundle().getBundleContext().getService(sr);
		//
		// // Get agent of currently running system.
		// IProvisioningAgent agent;
		// try {
		// agent = agentProvider.c;
		//
		// if (agent == null) {
		// System.out.println("Agent is null!!!");
		// return null;
		// }
		//
		// // Get the repository managers and define our repository.
		//
		//
		// } catch (ProvisionException e) {
		// System.out.println("Error getting running systems agent!!!");
		// e.printStackTrace();
		// }
		//
		//
		//
		//
		// return null;

	}
}
