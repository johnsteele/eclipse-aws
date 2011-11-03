package com.steelejr.eclipse.aws.dashboard;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.steelejr.eclipse.aws.dashboard"; //$NON-NLS-1$
	public static final String DASHBOARD_EXTENSION = "com.steelejr.eclipse.aws.dashboard.dashboardPage";
	
	// The shared instance
	private static Activator plugin;
	
	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}
	
	@Override
	protected void initializeImageRegistry(ImageRegistry reg) {
		
		Bundle bundle = Platform.getBundle(PLUGIN_ID);
		IPath path = new Path ("icons/logo-header-gradient.png");
		URL url = FileLocator.find(bundle, path, null);
		reg.put("logo_aws", ImageDescriptor.createFromURL(url).createImage());
		
		path = new Path ("icons/header-gradient.png");
		url = FileLocator.find(bundle, path, null);
		reg.put("header-gradient", ImageDescriptor.createFromURL(url).createImage());
	}
}
