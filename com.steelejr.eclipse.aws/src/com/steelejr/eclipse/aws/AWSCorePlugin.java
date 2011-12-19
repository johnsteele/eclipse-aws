package com.steelejr.eclipse.aws;

import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.steelejr.eclipse.aws.auth.AbstractAccountInfo;
import com.steelejr.eclipse.aws.auth.PreferenceStoreAccountInfo;
import com.steelejr.eclipse.aws.auth.PreferenceConstants;

/**
 * The activator class controls the plug-in life cycle
 */
public class AWSCorePlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.steelejr.eclipse.aws"; //$NON-NLS-1$

	// The shared instance
	private static AWSCorePlugin plugin;
	
	private AmazonEC2 ec2_instance;
	private AWSCredentials aws_credentials;
	
	
	/**
	 * The constructor
	 */
	public AWSCorePlugin() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		//AbstractAccountInfo info = getAccountInfo();
		//String accessKey = info.getAccessKey();
		//String secretKey = info.getSecretKey();
		aws_credentials = new BasicAWSCredentials("AKIAICAD37RENDG64DZQ", "8VilybiSyi+2KhauM87FOK9hHFqPdIONN3vQ1sRS");
		ec2_instance = new AmazonEC2Client(aws_credentials);
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
	 * Returns the EC2 client.
	 * 
	 * @return The EC2 client.
	 */
	public AmazonEC2 getEc2Client () {
		return ec2_instance; 
	}
	
	/**
	 * Returns the currently selected Account Info.
	 * 
	 * @return The currently selected account info.
	 */
	public AbstractAccountInfo getAccountInfo () {
		return getAccountInfo(null);
	}
	
	/**
	 * Returns the currently active account information.
	 * 
	 * @return The current account information.
	 */
	public AbstractAccountInfo getAccountInfo(String accountId) {
		if (accountId == null)
			accountId = getCurrentAccountID();
		
		return new PreferenceStoreAccountInfo(getPreferenceStore(), accountId);
	}
	
	/**
	 * Returns the currently active AWS account.
	 * 
	 * @return The currently active AWS account.
	 */
	public String getCurrentAccountID () {
		return getPreferenceStore().getString(PreferenceConstants.P_CURRENT_ACCOUNT);
	}
	
	/**
	 * Sets the currently active AWS account.
	 * 
	 * @param accountId The ID of the AWS account to set as current.
	 */
	public void setCurrentAccountID (String accountId) {
		getPreferenceStore().setValue(PreferenceConstants.P_CURRENT_ACCOUNT, accountId);
	}
	
	/*
	public void addAccountInfoChangeListener (AccountInfoChangeListener) {
		
	}
	
	public void removeAccountInfoChangeListener (AccountInfoChangeListener) {
		
	}
	*/	

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static AWSCorePlugin getDefault() {
		return plugin;
	}
	
	
	@Override
	protected void initializeImageRegistry(ImageRegistry reg) {
		super.initializeImageRegistry(reg);
		
		Image image = AWSCorePlugin.imageDescriptorFromPlugin(PLUGIN_ID, "icons/common/refresh.gif").createImage();
		reg.put("refresh", image);
	}
}
