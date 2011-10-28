package com.steelejr.aws.ec2.java.server.ui.view;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import com.steelejr.aws.ec2.java.server.ui.Activator;

/**
 * A view to display the servers. 
 */
public class ServersView extends ViewPart {
	
	/**
	 * The tree of servers. 
	 */
	private TreeViewer server_viewer; 

	public ServersView() {
	}

	@Override
	public void createPartControl(Composite parent) {
		
		/* Parent Layout */
		Composite comp = new Composite (parent, SWT.BORDER);
		GridLayout layout = new GridLayout(1, true);
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.verticalSpacing = 0;
		parent.setLayout(layout);
		comp.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
		
		/* Initialize Tree Viewer */
		init_viewer (comp);
	}
	
	private void init_viewer (Composite parent) {
		
		/* Providers */
		server_viewer = new TreeViewer(parent, SWT.SINGLE);
		server_viewer.setContentProvider(new ServersViewContentProvider());
		server_viewer.setLabelProvider(new ServersViewLabelProvider());
		
		/* Input */
		server_viewer.setInput(ResourcesPlugin.getWorkspace().getRoot().getProjects());
		getSite().setSelectionProvider(server_viewer);
	}

	@Override
	public void setFocus() {
	}
	
	@Override
	public void dispose() {
		super.dispose();
	}
}
