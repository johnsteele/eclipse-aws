package com.steelejr.eclipse.aws.ec2.editor;

import org.eclipse.jface.viewers.LabelProvider;

import com.amazonaws.services.ec2.model.Instance;

public class Ec2InstancesLabelProvider extends LabelProvider {

	
	@Override
	public String getText(Object element) {
		Instance instance = (Instance) element;
		return instance.getInstanceId();
	}
}
