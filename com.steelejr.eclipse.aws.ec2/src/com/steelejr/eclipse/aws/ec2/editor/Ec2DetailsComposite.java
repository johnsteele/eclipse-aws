package com.steelejr.eclipse.aws.ec2.editor;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IFormPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

import com.amazonaws.services.ec2.model.Instance;
import com.steelejr.eclipse.aws.ec2.Activator;

public class Ec2DetailsComposite implements IDetailsPage {
	
	private IManagedForm my_form;
	
	/**
	 * The instance input. 
	 */
	private Instance my_inputInstance;
	private Composite my_detailsComposite;
	
	private TableViewer my_table;
	
	public Ec2DetailsComposite () {
	}
	
	
	@Override
	public void createContents(Composite parent) {
		
		GridLayout layout = new GridLayout ();
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		parent.setLayout(layout);
		
		FormToolkit toolkit = my_form.getToolkit();
		Section section = toolkit.createSection(parent, Section.EXPANDED | Section.DESCRIPTION | Section.TWISTIE | Section.TITLE_BAR);
		section.setText("Details");
		section.setDescription("Information about the instance.");
		section.setLayout(new GridLayout());
		section.setLayoutData(new GridData (GridData.FILL_BOTH));
		
		my_detailsComposite = toolkit.createComposite(section, SWT.NONE);
		my_detailsComposite.setLayout(new GridLayout());
		my_detailsComposite.setLayoutData(new GridData (GridData.FILL_BOTH));
		my_detailsComposite.setBackground(Activator.getDefault().getWorkbench().getDisplay().getSystemColor(SWT.COLOR_BLUE));		
		
		section.setClient(my_detailsComposite);
		toolkit.paintBordersFor(my_detailsComposite);
	}
	
	
	private void updateDetails () {
		FormToolkit toolkit = my_form.getToolkit();
		Label name = toolkit.createLabel(my_detailsComposite, "Architecture: ");
		Label value = toolkit.createLabel(my_detailsComposite, my_inputInstance.getArchitecture());
	}
	
	
	public TableViewer getTable () {
		return my_table;
	}

	@Override
	public void initialize(IManagedForm form) {
		my_form = form;
	}

	@Override
	public void dispose() {
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public void commit(boolean onSave) {		
	}

	@Override
	public boolean setFormInput(Object input) {
		return false;
	}

	@Override
	public void setFocus() {
	}

	@Override
	public boolean isStale() {
		return false;
	}

	@Override
	public void refresh() {
	}

	@Override
	public void selectionChanged(IFormPart part, ISelection selection) {
		
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection ss = (IStructuredSelection) selection;
			Object o = ss.getFirstElement();
			if (o instanceof Instance) {
				my_inputInstance = (Instance) o;
				updateDetails();
			}
		}
	}
}
