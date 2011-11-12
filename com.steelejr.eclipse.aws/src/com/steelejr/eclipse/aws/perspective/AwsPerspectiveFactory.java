package com.steelejr.eclipse.aws.perspective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
 * The Amazon Web Services perspective.
 */
public class AwsPerspectiveFactory implements IPerspectiveFactory {

	@Override
	public void createInitialLayout(IPageLayout layout) {
		
		String editorArea = layout.getEditorArea();
		
		/* Dashboard Action Sets. */
		layout.addActionSet("com.steelejr.eclipse.aws.dashboard.actionSet");
		
		/* Left - Navigation Area */
		IFolderLayout left = layout.createFolder("left", IPageLayout.LEFT, 0.25F, editorArea);
		left.addPlaceholder("org.eclipse.jdt.ui.PackageExplorer");
		left.addPlaceholder("org.eclipse.ui.views.ResourceNavigator");
		left.addPlaceholder("org.eclipse.ui.navigator.ProjectExplorer");
		left.addView("com.steelejr.eclipse.aws.server.view.ServersView");
		
		/* Bottom - Output Area */
		IFolderLayout bottomOutputFolder = layout.createFolder("bottom", IPageLayout.BOTTOM, 0.8F, editorArea);
		bottomOutputFolder.addView("org.eclipse.ui.console.ConsoleView");
		bottomOutputFolder.addPlaceholder("org.eclipse.ui.views.ProgressView");
		
		/* Show-View Shortcuts. */
		layout.addShowViewShortcut("org.eclipse.ui.navigator.ProjectExplorer");
		layout.addShowViewShortcut("org.eclipse.ui.views.ResourceNavigator");
		layout.addShowViewShortcut("org.eclipse.jdt.ui.PackageExplorer");	
		layout.addShowViewShortcut("org.eclipse.ui.console.ConsoleView");
		layout.addShowViewShortcut("com.steelejr.eclipse.aws.server.view.ServersView");
				
		/* New Wizard Shortcuts */
		layout.addNewWizardShortcut("org.eclipse.ui.wizards.file");
		layout.addNewWizardShortcut("org.eclipse.ui.wizards.folder");
		
		layout.addNewWizardShortcut("org.eclipse.jdt.ui.wizards.NewClassCreationWizard");
		layout.addNewWizardShortcut("org.eclipse.jdt.ui.wizards.NewPackageCreationWizard");
	}
}
