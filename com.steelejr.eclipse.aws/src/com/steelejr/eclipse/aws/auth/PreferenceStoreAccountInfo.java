package com.steelejr.eclipse.aws.auth;

import org.apache.commons.codec.binary.Base64;
import org.eclipse.jface.preference.IPreferenceStore;

public class PreferenceStoreAccountInfo extends AbstractAccountInfo {
	
	/**
	 * The currently active account.
	 */
	private String accountId;
	
	/**
	 * The workbench preference storage.
	 */
	private IPreferenceStore preferenceStore; 
	
	/**
	 * Creates a new object configured to pull account information from the 
	 * specified preference store. The account information is tied to the given
	 * account name, so successive calls to accessors can return different results.
	 * Clients wishing to be notified of such changes should register a change listener
	 * with {@link AWSCorePlugin}.
	 * 
	 * @see AWSCorePlugin#addAccountInfoChangeListener(AccountInfoChangeListener)
	 * @param preferenceStore 
	 * 			The preference store to pull AWS account information.
	 * @param accountId
	 * 			The account name to use when looking up the account information.
	 */
	public PreferenceStoreAccountInfo (IPreferenceStore preferenceStore, String accountId) {
		this.accountId = accountId;
		this.preferenceStore = preferenceStore;
	}
	
	private String getAccountPreferenceName (String key) {
		return accountId + ":" + key;
	}
	
    /* (non-Javadoc)
	 * @see com.steelejr.eclipse.aws.auth.AccountInfo2#getUserId()
	 */
    @Override
	public String getUserId() {
        return decodeString(preferenceStore.getString(getAccountPreferenceName(PreferenceConstants.P_USER_ID)));
    }

    /* (non-Javadoc)
	 * @see com.steelejr.eclipse.aws.auth.AccountInfo2#getAccessKey()
	 */
    @Override
	public String getAccessKey() {
        return decodeString(preferenceStore.getString(getAccountPreferenceName(PreferenceConstants.P_ACCESS_KEY)));
    }

    /* (non-Javadoc)
	 * @see com.steelejr.eclipse.aws.auth.AccountInfo2#getSecretKey()
	 */
    @Override
	public String getSecretKey() {
        return decodeString(preferenceStore.getString(getAccountPreferenceName(PreferenceConstants.P_SECRET_KEY)));
    }

    /* (non-Javadoc)
	 * @see com.steelejr.eclipse.aws.auth.AccountInfo2#getEc2PrivateKeyFile()
	 */
    @Override
	public String getEc2PrivateKeyFile() {
        return preferenceStore.getString(getAccountPreferenceName(PreferenceConstants.P_PRIVATE_KEY_FILE));
    }

    /* (non-Javadoc)
	 * @see com.steelejr.eclipse.aws.auth.AccountInfo2#getEc2CertificateFile()
	 */
    @Override
	public String getEc2CertificateFile() {
        return preferenceStore.getString(getAccountPreferenceName(PreferenceConstants.P_CERTIFICATE_FILE));
    }

    /* (non-Javadoc)
	 * @see com.steelejr.eclipse.aws.auth.AccountInfo2#setAccessKey(java.lang.String)
	 */
    @Override
	public void setAccessKey(String accessKey) {
        throw new RuntimeException(this.getClass().getName() + " is a read-only interface");
    }

    /* (non-Javadoc)
	 * @see com.steelejr.eclipse.aws.auth.AccountInfo2#setSecretKey(java.lang.String)
	 */
    @Override
	public void setSecretKey(String secretKey) {
        throw new RuntimeException(this.getClass().getName() + " is a read-only interface");
    }
    
    private String decodeString (String s) {
    	return new String (Base64.decodeBase64(s.getBytes()));
    }
}
