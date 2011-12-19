package com.steelejr.eclipse.aws.marketplace.editor;

import java.util.List;

import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class ExtensionsContentProvider implements ITreeContentProvider {
	
	private List<IInstallableUnit> list;
	
	public ExtensionsContentProvider (List<IInstallableUnit> list) {
		this.list = list;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object[] getElements(Object inputElement) {
		return list.toArray();
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		// TODO Auto-generated method stub
		return false;
	} // IStructuredContentProvider {
	
//	private List<IInstallableUnit> list;
//	
//	public ExtensionsContentProvider (List<IInstallableUnit> list) {
//		super ();
//		this.list = list;
//	}
//
//	@Override
//	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
//		
//		
//	}
//
//	@Override
//	public Object[] getElements(Object inputElement) {
//		return list.toArray();
//	}
//	
//	
//	@Override
//	public void dispose() {
//		
//	}

}
