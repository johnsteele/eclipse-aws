package com.steelejr.aws.ec2.java.server.ui.view;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

/**
 * A view to display the servers. 
 *
 */
public class ServersView extends ViewPart {
	
	/**
	 * The tree of servers. 
	 */
	private TreeViewer my_viewer; 

	public ServersView() {
	}

	@Override
	public void createPartControl(Composite parent) {
		
		/* Parent Layout */
		Composite comp = new Composite (parent, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL);
		comp.setLayout(new GridLayout());
		GridData data = new GridData();
		data.heightHint = 0;
		data.widthHint  = 0;
		data.horizontalAlignment = GridData.FILL;
		data.verticalAlignment   = GridData.FILL;
		comp.setLayoutData(data);
		
		/* Initialize Tree Viewer */
		init_viewer (comp);
	}
	
	private void init_viewer (Composite parent) {
		
	}

	@Override
	public void setFocus() {
	}

}
