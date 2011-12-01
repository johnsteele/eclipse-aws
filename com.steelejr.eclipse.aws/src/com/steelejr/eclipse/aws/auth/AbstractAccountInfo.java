package com.steelejr.eclipse.aws.auth;

import java.io.File;

/**
 * An abstract class describing the interface for accessing configured AWS 
 * account preferences and provides hooks for notification of change events.
 * 
 * The account info object is for accessing the user's credentials.
 */
public abstract class AbstractAccountInfo {

	/**
	 * Returns the currently configured AWS user account ID.
	 * 
	 * @return The currently configured AWS user account ID.
	 */
	//public abstract String getUserId();
	
	/**
	 * Returns the currently configured AWS user access key.
	 * 
	 * @return The currently configured AWS user access key.
	 */
	public abstract String getAccessKey();
	
	/**
	 * Sets the AWS Access Key ID for this account info object.
	 * 
	 * @param accessKey The AWS Access Key ID.
	 */
	public abstract void setAccessKey(String accessKey);
	
	/**
	 * Returns the currently configured AWS secret key.
	 * 
	 * @return The currently configured AWS secret key.
	 */
	public abstract String getSecretKey();
	
	/**
	 * Sets the AWS Secret Access Key for this account info object.
	 * 
	 * @param secretKey The AWS Secret Access Key.
	 */
	public abstract void setSecretKey(String secretKey);
	
	/**
	 * Returns the currently configured EC2 private file key.
	 * 
	 * @return The currently configured EC2 private key file.
	 */
	public abstract String getEc2PrivateKeyFile();

	/**
	 * Returns the currently configured EC2 certificate file.
	 * 
	 * @return The currently configured EC2 certificate file.
	 */
	public abstract String getEc2CertificateFile();
	
	/**
	 * Returns true if the configured account information appears to be valid by
	 * verifying that none of the values are missing.
	 *
	 * @return True if the configured account information appears to be valid,
	 *         otherwise false.
	 */
	public boolean isValid() {
		if (getAccessKey() == null || getAccessKey().length() == 0) return false;
		if (getSecretKey() == null || getSecretKey().length() == 0) return false;
		return true;
	}
	
	
	/**
	 * Returns true if and only if the configured certificate and corresponding
	 * private key are valid. Currently that distinction is made by verifying
	 * that the referenced files exist.
	 *
	 * @return True if and only if the configured certificate and corresponding
	 *         private key are valid.
	 */
	public boolean isCertificateValid() {
		String certificateFile = getEc2CertificateFile();
		String privateKeyFile = getEc2PrivateKeyFile();

		if (certificateFile == null || certificateFile.length() == 0) return false;
		if (privateKeyFile == null || privateKeyFile.length() == 0) return false;

		return (new File(certificateFile).isFile() &&
				new File(privateKeyFile).isFile());
	}
}