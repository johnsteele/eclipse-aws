package com.steelejr.aws.ec2.java.server.ui.view;

import org.eclipse.jface.viewers.LabelProvider;

import com.steelejr.aws.ec2.java.server.core.IServer;
import com.steelejr.aws.ec2.java.server.core.IServerModule;

/**
 * Provides the image and text for servers in the ServersView.
 */
public class ServersViewLabelProvider extends LabelProvider {
	
	@Override
	public String getText(Object element) {
		
		if (element instanceof IServer) {
			return ((IServer)element).getName();
		}
		
		else if (element instanceof IServer) {
			return ((IServerModule)element).getName();
		}
		
		else 
			return "Unknow Element";
	}
	
	

}
