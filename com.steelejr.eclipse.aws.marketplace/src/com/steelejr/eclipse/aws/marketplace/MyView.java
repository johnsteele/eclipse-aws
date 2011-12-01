package com.steelejr.eclipse.aws.marketplace;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.dialogs.ProgressIndicator;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

public class MyView extends ViewPart {

	public MyView() {
	}

	@Override
	public void createPartControl(Composite parent) {
		final Composite comp = new Composite(parent, SWT.NONE);
		comp.setLayout(new GridLayout());
		
		Button button = new Button(comp, SWT.PUSH);
		button.setText("Status Line");
		
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IActionBars bars = getViewSite().getActionBars();
				IStatusLineManager statusLine = bars.getStatusLineManager();
				IProgressMonitor pm = statusLine.getProgressMonitor();
				pm.beginTask("Doing Work...", 500000);
				pm.worked(1);
				for (int i = 0; i < 500000; i++) {
					for (int j = 0; j < 20000; j++) 
						;
					pm.worked(1);
					pm.subTask("Task - " + i);
				}
				pm.done();				
			}
		});
		
		button = new Button (comp, SWT.PUSH);
		button.setText("Progress Indicator");
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
						
			}
		});
		final Text text = new Text(comp, SWT.BORDER);
		button = new Button (comp, SWT.PUSH);
		button.setText("Progress Dialog");
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ProgressMonitorDialog dialog = new ProgressMonitorDialog(Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getShell());
				try {
					dialog.run(true /* fork */, true /* cancelable */,
							new IRunnableWithProgress() {
								
								@Override
								public void run(IProgressMonitor monitor) throws InvocationTargetException,
										InterruptedException {
									monitor.beginTask("Long running task", 500000);
									for (int i = 0; i < 500000; i++) {
										if (monitor.isCanceled()) return;
										
										monitor.subTask("Working on step: " + i);
//										Display.getDefault().syncExec(new Runnable() {
//											
//											@Override
//											public void run() {
//												text.setText("New work: ");												
//											}
//										});
										for (int j = 0; j < 20000; j++)
											;
										monitor.worked(1);
									}
									monitor.done();
								
								}
							});
				} catch (InvocationTargetException e1) {
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		
		button = new Button (comp, SWT.PUSH);
		button.setText("Progress Service - IProgressService - busyCursorWhile");
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					PlatformUI.getWorkbench().getProgressService().
						busyCursorWhile(new IRunnableWithProgress() {
							
							@Override
							public void run(IProgressMonitor monitor) throws InvocationTargetException,
									InterruptedException {
								monitor.beginTask("Long running task.", 500000);
								for (int i = 0; i < 500000; i++) {
									if (monitor.isCanceled()) return;
									
									monitor.subTask("Working on step: " + i);
//									Display.getDefault().syncExec(new Runnable() {
//										
//										@Override
//										public void run() {
//											text.setText("New work: ");												
//										}
//									});
									for (int j = 0; j < 20000; j++)
										;
									monitor.worked(1);
								}
							}
						});
				} catch (InvocationTargetException e1) {
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		button = new Button (comp, SWT.PUSH);
		button.setText("Progress Service - IProgressService - showInDialog");
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				Job myJob = new Job("my job") {
					
					@Override
					protected IStatus run(IProgressMonitor monitor) {
						
						monitor.beginTask("Long Running Task", 500000);
						for (int i = 0; i < 500000; i++) {
							for (int j = 0; j < 20000; j++) 
								;
							monitor.worked(i);
						}
						
						return Status.OK_STATUS;
					}
				};
				
				
				PlatformUI.getWorkbench().getProgressService().
					showInDialog(Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getShell(), myJob);
			
				//myJob.setUser(true);
				//myJob.schedule();
			}
		});
		
		
	}

	@Override
	public void setFocus() {
	}

}
