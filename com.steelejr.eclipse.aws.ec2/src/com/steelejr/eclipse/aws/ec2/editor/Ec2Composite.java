package com.steelejr.eclipse.aws.ec2.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;
import com.steelejr.eclipse.aws.AWSCorePlugin;

public class Ec2Composite {
	
	/**
	 * The tree of instances.
	 */
	private TreeViewer viewer;
	
	/**
	 * Current account information.
	 */
	
	
	public Ec2Composite (Composite parent) {
		viewer = new TreeViewer (parent); //SWT.H_SCROLL | SWT.V_SCROLL | SWT.SINGLE);
		
		
		AmazonEC2 ec2 = AWSCorePlugin.getDefault().getEc2Client();
		DescribeInstancesResult result = new DescribeInstancesResult();
		result = ec2.describeInstances();
		
		List<Reservation> reservations = result.getReservations();
		List<Instance> allInstances = new ArrayList<Instance>();
		
		for (Reservation reservation : reservations) {
			 List<Instance> instances = reservation.getInstances();
			 for (Instance instance : instances) {
				 allInstances.add(instance);
			 }
		}
		
		viewer.setContentProvider(new Ec2InstancesContentProvider());
		viewer.setLabelProvider(new Ec2InstancesLabelProvider());
		viewer.setInput(allInstances);
		viewer.getTree().setLayout(new GridLayout());
		viewer.getTree().setLayoutData(new GridData(GridData.FILL_BOTH));
	}
	
	
	/**
	 * Returns the TreeViewer displaying the instances.
	 * 
	 * @return The TreeViewer.
	 */
	public TreeViewer getViewer () {
		return viewer;
	}
}
