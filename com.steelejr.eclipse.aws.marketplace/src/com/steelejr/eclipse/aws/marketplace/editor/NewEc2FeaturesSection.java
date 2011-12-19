package com.steelejr.eclipse.aws.marketplace.editor;

import java.util.Iterator;
import java.util.List;

import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

import com.steelejr.eclipse.aws.marketplace.Activator;
import com.steelejr.eclipse.aws.marketplace.wizard.LicenseWizard;
import com.steelejr.eclipse.aws.marketplace.wizard.NewFeatureUtil;

public class NewEc2FeaturesSection {

	private static final Font NAME_FONT = new Font(Activator.getDefault()
			.getWorkbench().getDisplay(), "Arial", 11, SWT.BOLD);

	/**
	 * The table of available download-able extensions.
	 */
	private Table table;

	/**
	 * Creates a DownloadExtensionComposite with the provided parent and style.
	 * 
	 * @param parent
	 *            The parent of this composite.
	 * @param style
	 *            The composite style.
	 */
	public NewEc2FeaturesSection(ScrolledForm form, FormToolkit toolkit) {
		init(form, toolkit);
	}

	private void init(ScrolledForm form, FormToolkit toolkit) {
		createEc2Downloads(form, toolkit);
	}

	private void createEc2Downloads(ScrolledForm form, FormToolkit toolkit) {

		/* Section */
		Section section = toolkit.createSection(form.getBody(),
				Section.TITLE_BAR);
		section.setText("Ec2 Tool Downloads");
		section.setLayout(new GridLayout());
		section.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		/* Toolbar */
		ToolBar bar = new ToolBar(section, SWT.HORIZONTAL | SWT.FLAT);
		
		ToolItem item = new ToolItem(bar, SWT.PUSH);
		item.setImage(Activator.getDefault().getImageRegistry().get("refresh"));
		item.setToolTipText("Refresh");
		
		item = new ToolItem (bar, SWT.SEPARATOR);
		
		item = new ToolItem (bar, SWT.PUSH);
		item.setImage(Activator.getDefault().getImageRegistry().get("new_updateSite"));
		item.setToolTipText("Add new update site URL");
		
		section.setTextClient(bar);

		/* Description */
		FormText description = toolkit.createFormText(section, false);
		description
				.setText(
						"<form><p>Download additional Eclipse plug-ins and AWS support in this section.</p></form>",
						true, false);
		section.setDescriptionControl(description);

		/* Client */
		Composite client = toolkit.createComposite(section);
		client.setLayout(new GridLayout());
		client.setLayoutData(new GridData(GridData.FILL_BOTH));
		section.setClient(client);
		toolkit.paintBordersFor(client);

		/* Table */
		table = toolkit.createTable(client, SWT.V_SCROLL | SWT.H_SCROLL
				| SWT.SINGLE | SWT.FULL_SELECTION);

		// Layout
		table.setLayout(new GridLayout(2, true));
		table.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true,
				true));

		// Image Column
		TableColumn column = new TableColumn(table, SWT.LEFT);

		// Name Column
		column = new TableColumn(table, SWT.LEFT);

		// Version Column
		column = new TableColumn(table, SWT.LEFT);

		refreshTable();

		addHoverListener();
		addSelectionListener ();

		column = table.getColumn(0);
		column.pack();
		column = table.getColumn(1);
		column.pack();
		column = table.getColumn(2);
		column.pack();
		table.pack();
	}
	
	
	private void addSelectionListener () {
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				TableItem item = table.getItem(new Point (e.x, e.y));
				if (item == null) 
					return;
				
				// Get selected Installable Unit.
				IInstallableUnit unitToInstall = (IInstallableUnit) item.getData();
				
				installInstallableUnit (unitToInstall);
			}
		});
	}
	
	
	private void installInstallableUnit (IInstallableUnit unitToInstall) {
		
	}
	

	private void addHoverListener() {
		table.addListener(SWT.MouseMove, new Listener() {

			@Override
			public void handleEvent(Event event) {
				TableItem item = table.getItem(new Point(event.x, event.y));
				if (item == null)
					return;

				Color hover = Activator.getDefault().getWorkbench()
						.getDisplay()
						.getSystemColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT);
				Color normal = Activator.getDefault().getWorkbench()
						.getDisplay().getSystemColor(SWT.COLOR_LIST_BACKGROUND);

				TableItem[] items = table.getItems();
				for (int i = 0; i < items.length; i++) {

					items[i].setFont(1, NAME_FONT);

					if (items[i] != item && items[i].getBackground() != normal) {
						items[i].setBackground(normal);
					} else {
						if (items[i].getBackground() != hover)
							items[i].setBackground(hover);
					}
				}
			}
		});
	}

	
	private void refreshTable() {
//		List<IInstallableUnit> installed = NewFeatureUtility.getNewFeatures();
//
//		for (IInstallableUnit installableUnit : installed) {
//
//			String name = installableUnit.getProperty(IInstallableUnit.PROP_NAME, null);
//			String version = installableUnit.getVersion().toString();
//			if (version.length() > 20)
//				version = version.substring(0, 19);
//
//			TableItem item = new TableItem(table, SWT.NULL);
//			item.setData(installableUnit);
//			item.setImage(0,
//					Activator.getDefault().getImageRegistry()
//							.get("new_feature"));
//
//			item.setFont(1, NAME_FONT);
//			item.setText(1, name);
//			item.setText(2, version);
//		}

		List<IInstallableUnit> installed = NewFeatureUtil.getNewFeatures();

		for (IInstallableUnit installableUnit : installed) {

			String name = installableUnit.getProperty(IInstallableUnit.PROP_NAME, null);
			String version = installableUnit.getVersion().toString();
			if (version.length() > 20)
				version = version.substring(0, 19);

			TableItem item = new TableItem(table, SWT.NULL);
			item.setData(installableUnit);
			item.setImage(0,
					Activator.getDefault().getImageRegistry()
							.get("new_feature"));

			item.setFont(1, NAME_FONT);
			item.setText(1, name);
			item.setText(2, version);
		}
		
	}
	
	
	/**
	 * A helper method to show the license page.
	 */
	private void showLicense () {
		LicenseWizard wizard = new LicenseWizard ();
		WizardDialog dialog = new WizardDialog(Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getShell(), wizard);
		if (dialog.open() !=  Window.CANCEL) {
			
		}
	}	
}
