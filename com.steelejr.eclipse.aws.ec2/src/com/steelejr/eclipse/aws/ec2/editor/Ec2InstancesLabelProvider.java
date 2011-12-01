package com.steelejr.eclipse.aws.ec2.editor;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.amazonaws.services.ec2.model.Instance;
import com.steelejr.eclipse.aws.ec2.Activator;
import com.steelejr.eclipse.aws.ec2.util.Ec2Images;

public class Ec2InstancesLabelProvider extends LabelProvider {

	
	@Override
	public String getText(Object element) {
		Instance instance = (Instance) element;
		StringBuffer buff = new StringBuffer();
		buff.append(instance.getInstanceId());
		buff.append(" [");
		buff.append(instance.getState().getName());
		buff.append("]");
		return buff.toString();
	}
	
	
	@Override
	public Image getImage(Object element) {
		Instance instance = (Instance) element;
		
		if (instance.getState().getName().equals("pending")) 
			return Activator.getDefault().getImageRegistry().get(Ec2Images.IMG_INSTANCE_PENDING);
			
		if (instance.getState().getName().equals("running")) 
			return Activator.getDefault().getImageRegistry().get(Ec2Images.IMG_INSTANCE_STARTED);
			
		// For now, lets say shutting down is stopped.
		if (instance.getState().getName().equals("shutting-down"))
			return Activator.getDefault().getImageRegistry().get(Ec2Images.IMG_INSTANCE_STOPPED);
			
		return null;
	}
}
