package com.steelejr.eclipse.aws.ec2;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.steelejr.eclipse.aws.util.Ec2Images;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.steelejr.eclipse.aws.ec2"; //$NON-NLS-1$

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
	protected ImageRegistry createImageRegistry() {
		ImageRegistry registry =  super.createImageRegistry();
		
		String[] images = new String [] {
			Ec2Images.IMG_NEW_EC2_INSTANCE, Ec2Images.IMG_NEW_EC2_INSTANCE_LOC,
			Ec2Images.IMG_REFRSH, 			Ec2Images.IMG_REFRESH_LOC,
			Ec2Images.IMG_UPDATE, 			Ec2Images.IMG_UPDATE_LOC,
			Ec2Images.IMG_LAUNCH_RUN, 		Ec2Images.IMG_LAUNCH_RUN_LOC,
			Ec2Images.IMG_LAUNCH_STOP, 		Ec2Images.IMG_LAUNCH_STOP_LOC,
			Ec2Images.IMG_INSTANCE_PENDING, Ec2Images.IMG_INSTSNCE_PENDING_LOC,
			Ec2Images.IMG_INSTANCE_STARTED, Ec2Images.IMG_INSTANCE_STARTED_LOC,
			Ec2Images.IMG_INSTANCE_STOPPED, Ec2Images.IMG_INSTANCE_STOPPED_LOC,
		};
		
		for (int i = 0; i < images.length - 1; i++) {
			registry.put(images[i], ImageDescriptor.createFromFile(getClass(), images[i + 1]));
		}
		
		return registry;
	}

}
