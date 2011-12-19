package com.steelejr.eclipse.aws.auth;

public class PreferenceConstants {
	
	/**
	 * Preference key for the set of all account IDs stored. 
	 */
	public static final String P_ACCOUNT_IDS = "accountIds";
	
	/**
	 * Preference key for the time account was last modified.
	 */
	public static final String DATE_MODIFIED = "lastModifiedTimeStamp";
	
	/**
	 * Preference key for setting account active on startup.
	 */
	public static final String SET_ACCOUNT_ACTIVE = "accountActive";
	
	/**
	 * Preference key for the currently selected account id. Used to fetch all
	 * other account details.
	 */
	public static final String P_CURRENT_ACCOUNT = "currentAccount";
	
	/**
	 * Preference key for the user-visible name for an account: just a memento.
	 */
	public static final String ACCOUNT_NAME = "accountName";
	public static final String DEFAULT_ACCOUNT_NAME = "default";
	
	/** Preference key for a users' AWS user ID. */
	public static final String ACCESS_KEY_ID = "accessKeyId";
	
	/** Preference key for a user's AWS secret key. */
	public static final String PRIVATE_ACCESS_KEY_ID = "privateAccessKeyId";
	
	/** Show Private key Id. */
	public static final String SHOW_PRIVATE_ACCESS_KEY = "showPrivateAccessKey";
	
	/** Preference key for a user's EC2 private key file. */
	public static final String KEY_PAIR_FILE = "keyPairFile";
	
	/** Preference key for a user's EC2 certificate file. */
	public static final String CERTIFICATE_FILE = "certificateFile";
	
	/** Preference key for the default region. */
	public static final String DEFAULT_REGION = "defaultRegion";
	
	/** Preference key indicating whether the preferences from the EC2
	 *  preference store have been imported yet. 
	 */
	public static final String P_EC2_PREFERENCES_IMPORTED = "ec2PreferencesImported";

}
