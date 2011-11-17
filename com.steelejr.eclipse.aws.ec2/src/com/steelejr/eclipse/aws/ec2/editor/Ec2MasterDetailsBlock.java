package com.steelejr.eclipse.aws.ec2.editor;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.DetailsPart;
import org.eclipse.ui.forms.IFormColors;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.MasterDetailsBlock;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

import com.amazonaws.services.ec2.model.Instance;
import com.steelejr.eclipse.aws.ec2.Activator;
import com.steelejr.eclipse.aws.util.Ec2Images;

public class Ec2MasterDetailsBlock extends MasterDetailsBlock {

	/**
	 * the
	 */
	private FormPage my_page;
	
	/**
	 * The tree of instances.
	 */
	private Ec2Composite my_ec2Comp;
	
	/**
	 * The currently selected ec2 instance in the tree.
	 */
	private Instance my_instance;

	public Ec2MasterDetailsBlock(FormPage the_page) {
		this.my_page = the_page;
	}

	@Override
	protected void createMasterPart(final IManagedForm managedForm, Composite parent) {
		// section initialize
		FormToolkit toolkit = managedForm.getToolkit();
	
		ScrolledForm form = managedForm.getForm();
		Section section = toolkit.createSection(form.getBody(), Section.TWISTIE
				| Section.TITLE_BAR | Section.EXPANDED);
		section.setText("Instances");
		section.setActiveToggleColor(toolkit.getHyperlinkGroup()
				.getActiveForeground());
		section.setToggleColor(toolkit.getColors().getColor(
				IFormColors.SEPARATOR));
		// section layout
		section.setLayout(new GridLayout());
		section.setLayoutData(new GridData(GridData.FILL_BOTH));

		// toolbar
		ToolBar bar = new ToolBar(section, SWT.FLAT | SWT.HORIZONTAL);
		ToolItem item = new ToolItem(bar, SWT.PUSH);
		item.setImage(Activator.getDefault().getImageRegistry()
				.get(Ec2Images.IMG_UPDATE));
		item.setToolTipText("Update Ec2 instance list from server.");
		item.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Selected");
			}
		});

		item = new ToolItem(bar, SWT.SEPARATOR);
		item = new ToolItem(bar, SWT.PUSH);
		item.setImage(Activator.getDefault().getImageRegistry()
				.get(Ec2Images.IMG_NEW_EC2_INSTANCE));
		item.setToolTipText("Create new EC2 instance");

		item = new ToolItem(bar, SWT.PUSH);
		item.setImage(PlatformUI.getWorkbench().getSharedImages()
				.getImage(ISharedImages.IMG_TOOL_DELETE));
		item.setToolTipText("Delete currenlty selected EC2 instance");
		section.setTextClient(bar);

		FormText description = toolkit.createFormText(section, false);
		description
				.setText(
						"<form><p>Manage EC2 instances in the following section.</p></form>",
						true, false);
		section.setDescriptionControl(description);

		// client composite and layout.
		Composite client = toolkit.createComposite(section, SWT.WRAP);
		GridLayout layout = new GridLayout(2, false);
		layout.marginWidth = 0;
		client.setLayout(layout);
		section.setClient(client);

		// tree of instances.
		my_ec2Comp = new Ec2Composite(client);

		// right column.
		Composite colComp = toolkit.createComposite(client, SWT.WRAP);
		layout = new GridLayout();
		layout.numColumns = 1;
		layout.marginHeight = 0;
		colComp.setLayout(layout);
		GridData data = new GridData();
		data.verticalAlignment = SWT.TOP;
		colComp.setLayoutData(data);

		// start
		final Button startButton = toolkit.createButton(colComp, "Start",
				SWT.PUSH);
		startButton.setImage(Activator.getDefault().getImageRegistry()
				.get(Ec2Images.IMG_LAUNCH_RUN));

		// stop
		final Button stopButton = toolkit.createButton(colComp, "Stop",
				SWT.PUSH);
		stopButton.setImage(Activator.getDefault().getImageRegistry()
				.get(Ec2Images.IMG_LAUNCH_STOP));

		my_ec2Comp.getViewer().addSelectionChangedListener(
				new ISelectionChangedListener() {

					@Override
					public void selectionChanged(SelectionChangedEvent event) {
						if (event.getSelection() instanceof IStructuredSelection) {
							IStructuredSelection selection = (IStructuredSelection) event
									.getSelection();
							Object o = selection.getFirstElement();
							if (o instanceof Instance) {
								Instance instance = (Instance) o;
								// If a different instance was selected.
								if (instance != my_instance) {
									my_instance = instance;
									// Update buttons.
									startButton.setEnabled(my_instance
											.getState().getName()
											.equals("stopped"));
									stopButton.setEnabled(my_instance
											.getState().getName()
											.equals("running"));
									// Update details.
									managedForm.fireSelectionChanged(spart, event.getSelection());
									//updateDetails();
								}
							}
						}
					}
				});
	}

	@Override
	protected void registerPages(DetailsPart detailsPart) {

	}

	@Override
	protected void createToolBarActions(IManagedForm managedForm) {

	}

}
