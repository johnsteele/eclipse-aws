package com.steelejr.eclipse.aws.ec2.editor;

import java.util.List;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.IFormColors;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

import com.amazonaws.services.ec2.model.Instance;
import com.steelejr.eclipse.aws.dashboard.editor.DashboardPageProxy;
import com.steelejr.eclipse.aws.ec2.Activator;
import com.steelejr.eclipse.aws.ec2.util.Ec2Images;
import com.steelejr.eclipse.aws.inernal.jobs.DescribeInstanceJob;

public class Ec2DashboardPage extends DashboardPageProxy {
	
	/**
	 * The EC2Composite maintains the tree of Ec2 instances.
	 */
	private Ec2Composite my_ec2Comp;
	private InstanceDetailsComposite my_ec2Details;
	private IWorkbenchPartSite my_site;
	private IManagedForm my_form;
	
	
	/**
	 * The list of instances.
	 */
	private List<Instance> my_instances;
	
	/**
	 * The currently selected instance.
	 */
	private Instance my_instance;


	@Override
	public void createForm(IManagedForm managedForm, IWorkbenchPartSite site) {		
		
		my_form = managedForm;
		my_site = site;
		
		/* Form and Toolkit */
		ScrolledForm form = my_form.getForm();
		FormToolkit toolkit = my_form.getToolkit();
		form.setText("Ec2");
		GridLayout layout = new GridLayout ();
		layout.numColumns = 2;
		layout.makeColumnsEqualWidth = true;
		form.getBody().setLayout(layout);
		
		/* Header Composite */
		//createHeader (form, toolkit);
		
//		Button button = toolkit.createButton(form.getBody(), "Launch Job", SWT.PUSH);
//		button.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				IActionBars bars = ((IEditorPart)my_site.getPart()).getEditorSite().getActionBars();
//				IStatusLineManager mgr = bars.getStatusLineManager();
//				IProgressMonitor monitor = mgr.getProgressMonitor();
//				DescribeInstanceJob job = new DescribeInstanceJob("Query Instances");
//				job.setProgressGroup(monitor, IProgressMonitor.UNKNOWN);
//				job.setUser(true);
//				job.schedule();
//			}
//		});
		
		/* Instances Section */
		createInstanceSection (form, toolkit);
		//my_block = new Ec2MasterDetailsBlock(this);
		//my_block.createContent(managedForm);
		
		/* Details Section */
		createDetailsSection (form, toolkit);
		setFocus();
	}
	
	
	/**
	 * Creates the header for the EC2 form page.
	 * 
	 * @param form The form to add the header.
	 * @param toolkit The shared toolkit instance.
	 */
	private void createHeader (ScrolledForm form, FormToolkit toolkit) {
		
		// Background color
//		FormColors colors = toolkit.getColors();
//		Color top = colors.getColor(IFormColors.H_GRADIENT_END);
//		Color bot = colors.getColor(IFormColors.H_GRADIENT_START);
//		form.getForm().setTextBackground(new Color [] {top, bot}, new int[] {100}, true); 
//		//toolkit.decorateFormHeading(form.getForm());		
//		
//		// Composite
//		Composite headClient = toolkit.createComposite(form.getForm().getHead(), SWT.NULL);
//		GridLayout layout   = new GridLayout();
//		layout.marginWidth  = 0;
//		layout.marginHeight = 0;
//		layout.numColumns   = 7;
//		headClient.setLayout(layout);
//		
//		// Account: Only if we supported multiple accounts, but we're not now.
//		
//		// Instances
//		toolkit.createLabel(headClient, "Instance:", toolkit.getBorderStyle());
//		Combo c = new Combo (headClient, SWT.DROP_DOWN);
//		//bindInstances (c);
//		
//		
//		
//		form.setHeadClient(headClient);
//		toolkit.paintBordersFor(headClient);
	}
	
	
	/**
	 * Data-binds the provided combo with the Ec2 instances.
	 * 
	 * @param combo The combo to bind with accounts.
	 */
	private void bindInstances (Combo combo) {
		DescribeInstanceJob job = new DescribeInstanceJob("Query Instances");
		job.setUser(true);
		job.schedule();
//		AmazonEC2 client = AWSCorePlugin.getDefault().getEc2Client();
//		DescribeInstancesResult result = new DescribeInstancesResult();
//		result = client.describeInstances();
//		
//		List<Reservation> reservations = result.getReservations();
//		my_instances = new ArrayList<Instance>();
//		
//		for (Reservation reservation : reservations) {
//			List<Instance> instances = reservation.getInstances();
//			for (Instance instance : instances) {
//				my_instances.add(instance);
//				combo.add(instance.getInstanceId());
//			}
//		}
	}
	
	/**
	 * Creates the Instance section that displays the instances associated with
	 * the active account.
	 * 
	 * @param form The form to add the section to.
	 * @param toolkit The shared form toolkit.
	 */
	private void createInstanceSection (ScrolledForm form, FormToolkit toolkit) {
		// section initialize
		Section section = toolkit.createSection(form.getBody(), Section.TITLE_BAR);
		section.setText("Instances");
		section.setActiveToggleColor(toolkit.getHyperlinkGroup().getActiveForeground());
		section.setToggleColor(toolkit.getColors().getColor(IFormColors.SEPARATOR));
		// section layout
		section.setLayout(new GridLayout ());
		section.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		// toolbar
		ToolBar bar = new ToolBar(section, SWT.FLAT | SWT.HORIZONTAL);
		ToolItem item = new ToolItem(bar, SWT.PUSH);
		item.setImage(Activator.getDefault().getImageRegistry().get(Ec2Images.IMG_UPDATE));
		item.setToolTipText("Update Ec2 instance list from server.");
		item.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Selected");
			}
		});
		
		item = new ToolItem (bar, SWT.SEPARATOR);		
		item = new ToolItem (bar, SWT.PUSH);		
		item.setImage(Activator.getDefault().getImageRegistry().get(Ec2Images.IMG_NEW_EC2_INSTANCE));
		item.setToolTipText("Create new EC2 instance");
		
		item = new ToolItem (bar, SWT.PUSH);
		item.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_TOOL_DELETE));
		item.setToolTipText("Delete currenlty selected EC2 instance");
		section.setTextClient(bar);
		
		FormText description = toolkit.createFormText(section, false);
		description.setText("<form><p>Manage EC2 instances in the following section.</p></form>", true, false);
		section.setDescriptionControl(description);
		
		// client composite and layout.
		Composite client = toolkit.createComposite(section, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		layout.marginWidth = 0;
		client.setLayout(layout);
		section.setClient(client);
		
		// tree of instances.
		my_ec2Comp = new Ec2Composite (client);
		
		// right column.
		Composite colComp = toolkit.createComposite(client, SWT.NONE);
		layout = new GridLayout();
		layout.numColumns = 1;
		layout.marginHeight = 0;
		colComp.setLayout(layout);
		GridData data = new GridData ();
		data.verticalAlignment = SWT.TOP;
		colComp.setLayoutData(data);
	
		
		// start 
		final Button startButton = toolkit.createButton(colComp, "Start", SWT.PUSH);
		startButton.setImage(Activator.getDefault().getImageRegistry().get(Ec2Images.IMG_LAUNCH_RUN));
		
		// stop
		final Button stopButton = toolkit.createButton(colComp, "Stop", SWT.PUSH);
		stopButton.setImage(Activator.getDefault().getImageRegistry().get(Ec2Images.IMG_LAUNCH_STOP));
		

		my_ec2Comp.getViewer().addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				if (event.getSelection() instanceof IStructuredSelection) {
					IStructuredSelection selection = (IStructuredSelection) event.getSelection();
					Object o = selection.getFirstElement();
					if (o instanceof Instance) {
						Instance instance = (Instance) o;
						// If a different instance was selected.
						if (instance != my_instance) {
							my_instance = instance;
							// Update buttons.
							startButton.setEnabled(my_instance.getState().getName().equals("stopped"));
							stopButton.setEnabled(my_instance.getState().getName().equals("running"));
							// Update details.
							updateDetails ();
						}
					}
				}
			}
		});
	}
	
	
	/**
	 * Refreshes the details to the currently selected instance.
	 */
	private void updateDetails () {
		
	}
	
	
	/**
	 * Creates the details sections which displays the details of the currently selected
	 * instance in the instance section.
	 * 
	 * @param form The form to create the section on.
	 * @param toolkit The shared form toolkit instance.
	 */
	private void createDetailsSection (ScrolledForm form, FormToolkit toolkit) {
		// section initialize
		Section section = toolkit.createSection(form.getBody(), Section.TITLE_BAR);
		section.setText("Instance Details");
		section.setActiveToggleColor(toolkit.getHyperlinkGroup().getActiveForeground());
		section.setToggleColor(toolkit.getColors().getColor(IFormColors.SEPARATOR));
		// section layout
		section.setLayout (new GridLayout ());
		section.setLayoutData(new GridData (GridData.FILL_BOTH));
		
		FormText description = toolkit.createFormText(section, false);
		description.setText("<form><p>Manage EC2 instances in the following section.</p></form>", true, false);
		section.setDescriptionControl(description);
	
		// client composite and layout.
		Composite client = toolkit.createComposite(section);
		GridLayout layout = new GridLayout ();
		client.setLayout(layout);
		client.setLayoutData(new GridData (GridData.FILL_BOTH));
		section.setClient(client);
		toolkit.paintBordersFor(client);
		
		// Table of details.
		my_ec2Details = new InstanceDetailsComposite(client, toolkit);
		
		my_ec2Comp.getViewer().addSelectionChangedListener(my_ec2Details);
	}
	
	@Override
	public void setFocus() {
		TreeItem item = my_ec2Comp.getViewer().getTree().getItem(0);
		my_ec2Comp.getViewer().getTree().setSelection(item);
		// This is weird, but necessary, I think, b/c setting the selection isn't 
		// firing a selection change event to notify the instance details composite.
		my_ec2Comp.getViewer().setSelection(my_ec2Comp.getViewer().getSelection());
	}
}
