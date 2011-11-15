package com.steelejr.eclipse.aws.ec2.editor;

import org.eclipse.jface.viewers.ISelectionChangedListener;
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
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.IFormColors;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

import com.steelejr.eclipse.aws.dashboard.editor.DashboardPageProxy;
import com.steelejr.eclipse.aws.ec2.Activator;
import com.steelejr.eclipse.aws.util.Ec2Images;

public class Ec2DashboardPage extends DashboardPageProxy implements ISelectionChangedListener {
	
	/**
	 * The EC2Composite maintains the tree of Ec2 instances.
	 */
	private Ec2Composite my_ec2Comp;
	private IWorkbenchPartSite my_site;


	@Override
	public void createForm(IManagedForm managedForm, IWorkbenchPartSite site) {
		
		/* Form and Toolkit */
		ScrolledForm form = managedForm.getForm();
		FormToolkit toolkit = managedForm.getToolkit();
		form.setText("Ec2");
		GridLayout layout = new GridLayout ();
		layout.numColumns = 2;
		layout.makeColumnsEqualWidth = true;
		form.getBody().setLayout(layout);
		
		my_site = site;
		
		/* Header Composite */
		createHeader (form, toolkit);
		
		/* Instances Section */
		createInstanceSection (form, toolkit);
		
		/* Details Section */
		createDetailsSection (form, toolkit);
		
		/* Create Web Server Section */
		createServerSection (form, toolkit);
	}
	
	
	/**
	 * Creates the header for the EC2 form page.
	 * 
	 * @param form The form to add the header.
	 * @param toolkit The shared toolkit instance.
	 */
	private void createHeader (ScrolledForm form, FormToolkit toolkit) {
		//Composite headClient = toolkit.createComposite(form.getForm().getHead(), SWT.NULL);
		
		
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
		Section section = toolkit.createSection(form.getBody(), Section.TWISTIE | Section.TITLE_BAR | Section.EXPANDED);
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
		Composite client = toolkit.createComposite(section, SWT.WRAP);
		GridLayout layout = new GridLayout(2, false);
		layout.marginWidth = 0;
		client.setLayout(layout);
		section.setClient(client);
		
		// tree of instances.
		my_ec2Comp = new Ec2Composite (client);
		
		// Listen for changed instance.
		my_ec2Comp.getViewer().addSelectionChangedListener(this);
		
		// right column.


		
		client.pack();
		section.pack();
		
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
		Section section = toolkit.createSection(form.getBody(), Section.TWISTIE | Section.TITLE_BAR | Section.DESCRIPTION | Section.EXPANDED);
		section.setText("Instance Details");
		section.setActiveToggleColor(toolkit.getHyperlinkGroup().getActiveForeground());
		section.setToggleColor(toolkit.getColors().getColor(IFormColors.SEPARATOR));
		// section layout
		section.setLayout (new GridLayout ());
		section.setLayoutData(new GridData (GridData.FILL_BOTH));
		
		// client composite and layout.
		Composite client = toolkit.createComposite(section);
		GridLayout layout = new GridLayout ();
		client.setLayout(layout);
		client.setLayoutData(new GridData (GridData.FILL_BOTH));
		section.setClient(client);
		toolkit.paintBordersFor(client);	
	}
	
	
	private void createServerSection (ScrolledForm form, FormToolkit toolkit) {
		Section section = toolkit.createSection(form.getBody(), Section.TWISTIE | Section.TITLE_BAR | Section.EXPANDED);
		section.setText("Downloads");
		section.setLayout(new GridLayout ());
		GridData data = new GridData ();
		data.horizontalSpan = 2;
		data.grabExcessHorizontalSpace = true;
		data.grabExcessVerticalSpace = true;
		data.horizontalAlignment = GridData.FILL;
		data.verticalAlignment = GridData.FILL;
		section.setLayoutData(data);
	}


	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		
	}
	
	
	/**
	 * An class to represent the right column composite. 
	 * 
	 * @author John Steele
	 */
	private class ButtonComposite {
		
		public ButtonComposite (FormToolkit tookit, Composite parent) {
			createComposite (tookit, parent);
		}
		
		
		private void createComposite (FormToolkit toolkit, Composite parent) {
			Composite colComp = toolkit.createComposite(parent, SWT.WRAP);
			GridLayout layout = new GridLayout();
			layout.numColumns = 1;
			layout.marginHeight = 0;
			colComp.setLayout(layout);
			GridData data = new GridData ();
			data.verticalAlignment = SWT.TOP;
			colComp.setLayoutData(data);
		
			
			// start 
			Button b = toolkit.createButton(colComp, "Start", SWT.PUSH);
			b.setImage(Activator.getDefault().getImageRegistry().get(Ec2Images.IMG_LAUNCH_RUN));
			
			// stop
			b = toolkit.createButton(colComp, "Stop", SWT.PUSH);
			b.setImage(Activator.getDefault().getImageRegistry().get(Ec2Images.IMG_LAUNCH_STOP));
		}
		
	}
}
