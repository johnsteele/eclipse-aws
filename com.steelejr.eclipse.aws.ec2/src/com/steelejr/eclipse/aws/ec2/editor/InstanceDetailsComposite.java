package com.steelejr.eclipse.aws.ec2.editor;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.amazonaws.services.ec2.model.Instance;

public class InstanceDetailsComposite implements ISelectionChangedListener {


	/**
	 * The currently displayed instance.
	 */
	private Instance my_instance;
	
	/**
	 * The table of instance details.
	 */
	private Table my_table;
	
	private TableItem platform;
	private TableItem archItem;
	private TableItem publicDnsName;
	private TableItem privateDnsName;
	private TableItem publicIpAddress;
	private TableItem instanceType;
	private TableItem launchTime;
	

	public InstanceDetailsComposite (Composite parent, FormToolkit toolkit) {
		createContents (parent, toolkit);
	}
	
	
	/**
	 * Sets up the table.
	 */
	private void createContents (Composite parent, FormToolkit toolkit) {
		my_table = toolkit.createTable(parent, SWT.SINGLE | SWT.V_SCROLL | SWT.H_SCROLL | SWT.FULL_SELECTION);
		my_table.setLayout(new GridLayout (2, true));
		my_table.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
		my_table.setLinesVisible(true);
		//my_table.setHeaderVisible(true);
		
		// Property and Value columns.
		TableColumn column = new TableColumn(my_table, SWT.LEFT);
		//column.setWidth(100);
		column.setResizable(true);
		column = new TableColumn (my_table, SWT.LEFT);
		//column.setWidth(100);
		column.setResizable(true);
		
		platform = new TableItem(my_table, SWT.NONE);
		platform.setText(0, "Platform:");
		archItem = new TableItem(my_table, SWT.NONE);
		archItem.setText(0, "Architecture:");
		publicDnsName = new TableItem(my_table, SWT.NONE);
		publicDnsName.setText(0, "Public DNS Name:");
		privateDnsName = new TableItem(my_table, SWT.NONE);
		privateDnsName.setText(0, "Private DNS Name:");
		publicIpAddress = new TableItem(my_table, SWT.NONE);
		publicIpAddress.setText(0, "Public IP Address:");
		instanceType = new TableItem(my_table, SWT.NONE);
		instanceType.setText(0, "Instance Type:");
		launchTime = new TableItem(my_table, SWT.NONE);
		launchTime.setText(0, "Launch Time:");
		
		
		column = my_table.getColumn(0);
		column.pack();
		column = my_table.getColumn(1);
		column.pack();
	}
	
	
	
	private void updateTable () {
		if (my_instance != null) {
			//platform.setText(1, my_instance.getPlatform());
			archItem.setText(1, my_instance.getArchitecture());
			publicDnsName.setText(1, my_instance.getPublicDnsName());
			privateDnsName.setText(1, my_instance.getPrivateDnsName());
			publicIpAddress.setText(1, my_instance.getPublicIpAddress());
			instanceType.setText(1, my_instance.getInstanceType());
			launchTime.setText(1, my_instance.getLaunchTime().toString());
		}
	}


	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		ISelection selection = event.getSelection();
		if (selection instanceof IStructuredSelection) {
			StructuredSelection ss = (StructuredSelection) selection;
			Object o = ss.getFirstElement();
			if (o instanceof Instance) {
				Instance instance = (Instance) o;
				if (instance != my_instance) {
					my_instance = instance;
					updateTable();
				}
			}
		}
	}
}
