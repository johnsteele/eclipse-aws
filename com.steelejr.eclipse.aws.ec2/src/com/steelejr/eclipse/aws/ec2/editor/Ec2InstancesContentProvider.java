package com.steelejr.eclipse.aws.ec2.editor;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.Instance;

public class Ec2InstancesContentProvider implements ITreeContentProvider {
	
	List<Instance> instances;

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		instances = (List<Instance>) newInput;
	}

	@Override
	public Object[] getElements(Object inputElement) {
		return instances.toArray();
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		return null;
	}

	@Override
	public Object getParent(Object element) {
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		return false;
	}
	
	@Override
	public void dispose() {
	}

}
