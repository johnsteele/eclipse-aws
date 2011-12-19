package com.steelejr.eclipse.aws.internal.provisioning;

import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;

/**
 * The provisioner for the AWS tool suite. 
 * 
 * @author John Steele
 */
public interface IProvisionerService {
	
	/**
	 * Returns the available features that are available to be installed.
	 * 
	 * @return The collection of available features.
	 */
	public abstract Collection getAvailableFeatures ();
	
	/**
	 * Returns the available features that are available to be installed on
	 * the device with the given id.
	 * 
	 * @param id The ID to use for querying features.
	 * @return The available features.
	 */
	public abstract Collection getAvailableFeatures (String id);
	
	/**
	 * Returns the installed features. 
	 * 
	 * @param id The ID of to use for querying installed features.
	 * @return The installed features.
	 */
	public abstract Collection getInstalled (String id);
	
	/**
	 * Returns all the profiles.
	 * 
	 * @return The profiles.
	 */
	public Collection getProfiles ();
	
	/**
	 * Installs the feature. 
	 * 
	 * @param id The ID of the profile to install in.
	 * @param feature The ID of the feature to install.
	 * @param monitor The monitor to report progress.
	 * @return The completion status code.
	 */
	public abstract IStatus install (String id, String feature, 
			IProgressMonitor monitor);
	
	/**
	 * Uninstalls the feature.
	 * 
	 * @param id The ID of the profile to uninstall feature from.
	 * @param feature The ID of the feature to uninstall.
	 * @param monitor The monitor to report progress.
	 * @return The completion status code.
	 */
	public abstract IStatus uninstall (String id, String feature, 
			IProgressMonitor monitor);
	
	/**
	 * Removes the provided profile.
	 * 
	 * @param id The ID of the profile to remove.
	 */
	public void removeProfile (String id);
	
	/**
	 * Adds a profile with the provided ID.
	 * 
	 * @param id The ID of the profile to add.
	 */
	public void addProfile (String id, String environment);

}
