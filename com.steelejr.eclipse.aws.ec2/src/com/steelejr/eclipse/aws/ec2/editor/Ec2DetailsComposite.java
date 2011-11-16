package com.steelejr.eclipse.aws.ec2.editor;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;

import com.amazonaws.services.ec2.model.Instance;

public class Ec2DetailsComposite {
	
	private TableViewer my_table;
	
	public Ec2DetailsComposite (Composite parent) {
		createTable (parent);
	}
	
	private void createTable (Composite parent) {
		my_table = new TableViewer (parent, SWT.H_SCROLL | SWT.V_SCROLL | SWT.SINGLE | SWT.FULL_SELECTION);
		my_table.getTable().setLinesVisible(true);
		createColumns ();
		my_table.setContentProvider(new ArrayContentProvider());
	}
	
	
	private void createColumns () {
		// Name column
		TableViewerColumn nameViewerColumn = new TableViewerColumn(my_table, SWT.NONE);
		TableColumn tableColumn = nameViewerColumn.getColumn();
		tableColumn.setText("Name");
		tableColumn.setResizable(true);
		tableColumn.setMoveable(true);
		
		nameViewerColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Instance instance = (Instance) element;
			}
		});
		
		// Value column
		TableViewerColumn valueViewerColumn = new TableViewerColumn (my_table, SWT.NONE);
		tableColumn = valueViewerColumn.getColumn();
		tableColumn.setText("Value");
		tableColumn.setResizable(true);
		tableColumn.setMoveable(true);
	}
	
	
	public TableViewer getTable () {
		return my_table;
	}
}
