package com.steelejr.eclipse.aws.provisioning;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.equinox.p2.core.ProvisionException;
import org.eclipse.equinox.p2.engine.IEngine;
import org.eclipse.equinox.p2.engine.IProfile;
import org.eclipse.equinox.p2.engine.IProfileRegistry;
import org.eclipse.equinox.p2.engine.query.IUProfilePropertyQuery;
import org.eclipse.equinox.p2.planner.IPlanner;
import org.eclipse.equinox.p2.repository.artifact.IArtifactRepositoryManager;
import org.eclipse.equinox.p2.repository.metadata.IMetadataRepository;
import org.eclipse.equinox.p2.repository.metadata.IMetadataRepositoryManager;

import com.steelejr.eclipse.aws.internal.provisioning.IProvisionerService;

/**
 * The AWS provisioning service.
 * 
 * @author John Steele
 *
 */
public class ProvisionerService implements IProvisionerService {
	
	private IProfileRegistry profileRegistry;
	private IPlanner planner;
	private IEngine  engine;
	private IMetadataRepository[] repositories;
	private File dataLocation;
	private IMetadataRepositoryManager metadataRepoManager;
	private IArtifactRepositoryManager artifactRepoManager;
	
	
	
	public ProvisionerService () {}
	
	
	protected void starup () {
		
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.steelejr.eclipse.aws.internal.provisioning.IProvisionerService#addProfile(java.lang.String, java.lang.String)
	 */
	@Override
	public void addProfile(String id, String environment) {
		IProfile profile = profileRegistry.getProfile(id);
		
		if (profile == null || environment == null) 
			return;
		
		String location = new File(dataLocation, environment).getPath();
		
		Map<String, String> props = new HashMap<String, String>();
		props.put(IProfile.PROP_INSTALL_FOLDER, location);
		props.put(IProfile.PROP_CACHE, location);
		props.put(IProfile.PROP_ENVIRONMENTS, environment);
		//props.put(IProfile.PROP_FLAVOR, "tooling");
	
		try {
			profileRegistry.addProfile(id, props);
		} catch (ProvisionException e) {
			//TODO: This needs to be logged as: Error adding profile: id.
			System.out.println("Error adding profile: ProvisionerService");
			e.printStackTrace();
		}
	}
	

	/*
	 * (non-Javadoc)
	 * @see com.steelejr.eclipse.aws.internal.provisioning.IProvisionerService#getAvailableFeatures()
	 */
	@Override
	public Collection getAvailableFeatures() {
		
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.steelejr.eclipse.aws.internal.provisioning.IProvisionerService#getAvailableFeatures(java.lang.String)
	 */
	@Override
	public Collection getAvailableFeatures(String id) {
		
		//IQuery<IInstallableUnit> query = QueryUtil.
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.steelejr.eclipse.aws.internal.provisioning.IProvisionerService#getInstalled(java.lang.String)
	 */
	@Override
	public Collection getInstalled(String id) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.steelejr.eclipse.aws.internal.provisioning.IProvisionerService#getProfiles()
	 */
	@Override
	public Collection getProfiles() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.steelejr.eclipse.aws.internal.provisioning.IProvisionerService#install(java.lang.String, java.lang.String, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public IStatus install(String id, String feature, IProgressMonitor monitor) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.steelejr.eclipse.aws.internal.provisioning.IProvisionerService#uninstall(java.lang.String, java.lang.String, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public IStatus uninstall(String id, String feature, IProgressMonitor monitor) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.steelejr.eclipse.aws.internal.provisioning.IProvisionerService#removeProfile(java.lang.String)
	 */
	@Override
	public void removeProfile(String id) {
	}
}
