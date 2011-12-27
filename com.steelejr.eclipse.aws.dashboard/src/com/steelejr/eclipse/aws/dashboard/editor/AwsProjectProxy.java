package com.steelejr.eclipse.aws.dashboard.editor;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.swt.widgets.Composite;

public class AwsProjectProxy {

	IConfigurationElement element;

	public AwsProjectProxy(IConfigurationElement element, Composite parent,
			String hyperlinkText, String description, String icon) {

		this.element = element;
		init(parent, hyperlinkText, description, icon);
	}

	private void init(Composite parent, String linkText, String description,
			String icon) {

	}
}
