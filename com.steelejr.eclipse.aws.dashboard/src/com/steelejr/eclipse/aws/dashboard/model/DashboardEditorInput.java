package com.steelejr.eclipse.aws.dashboard.model;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

/**
 * Input for the dashboard. 
 */
public class DashboardEditorInput implements IEditorInput {
	
	/**
	 * The input is a singleton.
	 */
	public static final DashboardEditorInput INSTANCE = new DashboardEditorInput();

	
	@Override
	public Object getAdapter(Class adapter) {
		return null;
	}

	@Override
	public boolean exists() {
		return true;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	@Override
	public String getName() {
		return "Dashboard Editor Input";
	}

	@Override
	public IPersistableElement getPersistable() {
		/* Add persitence support later. */
		return null;
	}

	@Override
	public String getToolTipText() {
		return "AWS Dashboard";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		return true;		
	}
}
