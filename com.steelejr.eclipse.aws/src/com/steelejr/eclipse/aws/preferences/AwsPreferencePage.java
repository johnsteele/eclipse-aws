package com.steelejr.eclipse.aws.preferences;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.steelejr.eclipse.aws.AWSCorePlugin;
import com.steelejr.eclipse.aws.auth.PreferenceConstants;

public class AwsPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage {
	
	/**
	 * Access key ID.
	 */
	private Text my_accessKeyText;
	
	/**
	 * Secret access key ID.
	 */
	private Text my_privateAccessKeyIdText;
	
	/**
	 * Show secret access key ID.
	 */
	private Button my_showAccessKeyId;
	
	
	public AwsPreferencePage () {
		super ();
		setPreferenceStore(AWSCorePlugin.getDefault().getPreferenceStore());
	}

	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	@Override
	public void init(IWorkbench workbench) {}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createContents(Composite parent) {
		Composite comp = new Composite (parent, SWT.NONE);
		comp.setLayout(new GridLayout (2, false));
		
		IPreferenceStore store = AWSCorePlugin.getDefault().getPreferenceStore();
		
		// Access Key
		Label label = new Label(comp, SWT.NONE);
		label.setText("Public Access Key:");
		my_accessKeyText = new Text(comp, SWT.BORDER);
		my_accessKeyText.setText(store.getString(PreferenceConstants.ACCESS_KEY_ID));
		my_accessKeyText.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, false));
		
		// Private Access Key
		label = new Label (comp, SWT.NONE);
		label.setText("Private Access Key:");
		my_privateAccessKeyIdText = new Text (comp, SWT.BORDER);
		my_privateAccessKeyIdText.setText(store.getString(PreferenceConstants.PRIVATE_ACCESS_KEY_ID));
		my_privateAccessKeyIdText.setLayoutData(new GridData (GridData.FILL, GridData.FILL, true, false));
		my_privateAccessKeyIdText.setEchoChar(store.getBoolean(PreferenceConstants.SHOW_PRIVATE_ACCESS_KEY) == true ? '\0' : '*');
		
		// Placeholder
		label = new Label (comp, SWT.NONE);
		
		// Show Private Key
		my_showAccessKeyId = new Button(comp, SWT.CHECK);
		my_showAccessKeyId.setText("Show Private Key:");
		my_showAccessKeyId.setSelection(store.getBoolean(PreferenceConstants.SHOW_PRIVATE_ACCESS_KEY));
		my_showAccessKeyId.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (my_showAccessKeyId.getSelection())
					my_privateAccessKeyIdText.setEchoChar('\0');
				else
					my_privateAccessKeyIdText.setEchoChar('*');
			}
		});
		
		return comp;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#performDefaults()
	 */
	@Override
	protected void performDefaults() {
		IPreferenceStore store = AWSCorePlugin.getDefault().getPreferenceStore();
		
		my_accessKeyText.setText(store.getDefaultString(PreferenceConstants.ACCESS_KEY_ID));
		my_privateAccessKeyIdText.setText(store.getDefaultString(PreferenceConstants.PRIVATE_ACCESS_KEY_ID));
		my_showAccessKeyId.setSelection(store.getBoolean(PreferenceConstants.SHOW_PRIVATE_ACCESS_KEY));
		
		super.performDefaults();
	}
	
	@Override
	public boolean performOk() {
		IPreferenceStore store = AWSCorePlugin.getDefault().getPreferenceStore();
		store.setValue(PreferenceConstants.ACCESS_KEY_ID, my_accessKeyText.getText());
		store.setValue(PreferenceConstants.PRIVATE_ACCESS_KEY_ID, my_privateAccessKeyIdText.getText());
		store.setValue(PreferenceConstants.SHOW_PRIVATE_ACCESS_KEY, my_showAccessKeyId.getSelection());
		return super.performOk();
	}
}
