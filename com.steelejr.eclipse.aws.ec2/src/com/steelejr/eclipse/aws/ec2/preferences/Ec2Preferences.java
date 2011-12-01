package com.steelejr.eclipse.aws.ec2.preferences;

import java.io.File;

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
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.steelejr.eclipse.aws.auth.PreferenceConstants;
import com.steelejr.eclipse.aws.ec2.Activator;

public class Ec2Preferences extends PreferencePage implements
		IWorkbenchPreferencePage {
	
	/**
	 * File location text.
	 */
	private Text my_fileLocation;
	
	/**
	 * Key-pair File.
	 */
	private File my_keyPairFile;
	
	
	public Ec2Preferences() {
		super ();
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createContents(Composite parent) {
		Composite comp = new Composite (parent, SWT.NONE);
		comp.setLayout(new GridLayout ());
		
		final IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		
		Group keyPairGroup = new Group(comp, SWT.SHADOW_NONE);
		keyPairGroup.setText("Key Pair");
		keyPairGroup.setLayout(new GridLayout(3, false));
		keyPairGroup.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, false));
		new Label(keyPairGroup, SWT.NONE).setText("File location:");

		my_fileLocation = new Text(keyPairGroup, SWT.BORDER);
		my_fileLocation.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, false));
		my_fileLocation.setText(store.getString(PreferenceConstants.KEY_PAIR_FILE));
		
		Button openDialog = new Button(keyPairGroup, SWT.PUSH);
		openDialog.setText("Browse...");
		openDialog.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog dialog = new FileDialog(getShell(), SWT.OPEN | SWT.SINGLE);
				dialog.setFilterExtensions(new String [] {"*.pem"});
				dialog.setText("Select Key-Pair File");
				dialog.setFilterPath(store.getString(PreferenceConstants.KEY_PAIR_FILE));
				String dir = dialog.open();
				my_fileLocation.setText(dir.trim());
			}			
		});
		
		my_fileLocation.pack();
		keyPairGroup.pack();
		return comp;
	}
	
	@Override
	protected void performDefaults() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		store.setValue(PreferenceConstants.KEY_PAIR_FILE, my_fileLocation.getText().trim());
		super.performDefaults();
	}
	
	
	@Override
	public boolean performOk() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		store.setValue(PreferenceConstants.KEY_PAIR_FILE, my_fileLocation.getText().trim());
		return super.performOk();
	}

	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	@Override
	public void init(IWorkbench workbench) {}
}
