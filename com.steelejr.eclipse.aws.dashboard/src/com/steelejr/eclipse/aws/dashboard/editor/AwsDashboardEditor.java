package com.steelejr.eclipse.aws.dashboard.editor;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.editor.FormEditor;

import com.steelejr.eclipse.aws.dashboard.Activator;
import com.steelejr.eclipse.aws.dashboard.Logger;

public class AwsDashboardEditor extends FormEditor {
	
	public static final String ID = "com.steelejr.eclipse.aws.dashboard.editor.AwsDashboardEditor";
	
	@Override
	protected void addPages() {
		
		try {
			addPage(new WelcomePage(this, "welcome", "Welcome"));
			loadPageContributions();
		} catch (PartInitException e) {
			Logger.INSTANCE.log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, e.getMessage()));
		}
	}
	
	private void loadPageContributions () {
		IConfigurationElement[] configurations = Platform.getExtensionRegistry().getConfigurationElementsFor(Activator.DASHBOARD_EXTENSION);
		for (IConfigurationElement element : configurations) {
			String id = element.getAttribute("id");
			String title = element.getAttribute("title");
			try {
				addPage(new DashboardPageProxy(this, id, title, element));
			} catch (CoreException e) {
				Logger.INSTANCE.log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, e.getMessage()));
			}
		}
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
	}

	@Override
	public void doSaveAs() {
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}
}
