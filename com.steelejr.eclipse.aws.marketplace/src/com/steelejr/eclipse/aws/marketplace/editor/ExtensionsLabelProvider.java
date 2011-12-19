package com.steelejr.eclipse.aws.marketplace.editor;

import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import com.steelejr.eclipse.aws.marketplace.Activator;

public class ExtensionsLabelProvider implements ITableLabelProvider {

	@Override
	public void addListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
			
//		if (columnIndex == 0) {
//			return Activator.getDefault().getImageRegistry().get("new_feature"); 
//		}
//		
//		else 
			return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		
		return "TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT";
//		
//		// Name
//		if (columnIndex == 1)
//			return ((IInstallableUnit) element).getProperty(IInstallableUnit.PROP_NAME, null);
//		
//		// Version
//		else if (columnIndex == 2) {
//			
//			String version = ((IInstallableUnit) element).getVersion().toString();
//			
//			if (version.length() > 20)
//				return version.substring(0, 19);
//			
//			else 
//				return version;
//		}
//					
//		// Eeek!
//		else 
//			return null;
	}
	
	

}
