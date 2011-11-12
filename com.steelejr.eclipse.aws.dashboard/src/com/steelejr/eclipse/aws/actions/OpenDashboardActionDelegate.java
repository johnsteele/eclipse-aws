package com.steelejr.eclipse.aws.actions;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.editor.FormEditor;

import com.steelejr.eclipse.aws.dashboard.Activator;
import com.steelejr.eclipse.aws.dashboard.Logger;
import com.steelejr.eclipse.aws.dashboard.editor.AwsDashboardEditor;
import com.steelejr.eclipse.aws.dashboard.model.DashboardEditorInput;

/**
 * Opens the AWS dashboard. 
 */
public class OpenDashboardActionDelegate implements IWorkbenchWindowActionDelegate {
	
	public static final String ID = "com.steelejr.eclipse.aws.dashboard.actions.OpenDashboardActionDelegate";
	
	/**
	 * The active workbench page.
	 */
	private IWorkbenchPage page;
	
	/**
	 * The ID of page within the editor to set as active.
	 */
	private String activePageID;
	
	public OpenDashboardActionDelegate () {
		activePageID = "overview"; // default
	}
	
	/**
	 * Creates a OpenDashboardActionDelegate with the
	 * specified WorkbenchWindow and id. 
	 * 
	 * @param window The active workbench page.
	 */
	public OpenDashboardActionDelegate(IWorkbenchPage page) {
		this.page = page;
	}
	
	/**
	 * Sets the active page id.
	 * 
	 * @param pageId The active page id.
	 */
	public void setActivePageId (String id) {
		this.activePageID = id;
	}
	

	@Override
	public void run(IAction action) {
		try {
			FormEditor editor = (FormEditor) page.openEditor(DashboardEditorInput.INSTANCE, AwsDashboardEditor.ID);
			editor.setActivePage(activePageID);
		} catch (PartInitException e) {
			Logger.INSTANCE.log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, e.getMessage()));
		}
	}

	@Override
	public void init(IWorkbenchWindow window) {
		this.page = window.getActivePage();
	}	
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
	}	
	@Override
	public void dispose() {
	}
}
