package ch.softenvironment.view;

/* 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 */
 
import ch.ehi.basics.view.*;
import ch.softenvironment.client.ResourceManager;
/**
 * TextField representing a File-Name and Chooser-Button.
 * @author: Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.4 $ $Date: 2004-09-14 16:56:57 $
 */
public class FileNamePanel extends javax.swing.JPanel {
	private javax.swing.JButton ivjBtnChooseFile = null;
	IvjEventHandler ivjEventHandler = new IvjEventHandler();
	private javax.swing.JTextField ivjTxtFileName = null;
	protected transient ch.softenvironment.view.FileNamePanelListener fieldFileNamePanelListenerEventMulticaster = null;

class IvjEventHandler implements java.awt.event.ActionListener, java.awt.event.KeyListener {
		public void actionPerformed(java.awt.event.ActionEvent e) {
			if (e.getSource() == FileNamePanel.this.getBtnChooseFile()) 
				connEtoC1(e);
		};
		public void keyPressed(java.awt.event.KeyEvent e) {};
		public void keyReleased(java.awt.event.KeyEvent e) {
			if (e.getSource() == FileNamePanel.this.getTxtFileName()) 
				connEtoC2(e);
		};
		public void keyTyped(java.awt.event.KeyEvent e) {};
	};
/**
 * FileNamePanel constructor comment.
 */
public FileNamePanel() {
	super();
	initialize();
}
/**
 * FileNamePanel constructor comment.
 * @param layout java.awt.LayoutManager
 */
public FileNamePanel(java.awt.LayoutManager layout) {
	super(layout);
}
/**
 * FileNamePanel constructor comment.
 * @param layout java.awt.LayoutManager
 * @param isDoubleBuffered boolean
 */
public FileNamePanel(java.awt.LayoutManager layout, boolean isDoubleBuffered) {
	super(layout, isDoubleBuffered);
}
/**
 * FileNamePanel constructor comment.
 * @param isDoubleBuffered boolean
 */
public FileNamePanel(boolean isDoubleBuffered) {
	super(isDoubleBuffered);
}
/**
 * 
 * @param newListener ch.softenvironment.view.FileNamePanelListener
 */
public void addFileNamePanelListener(ch.softenvironment.view.FileNamePanelListener newListener) {
	fieldFileNamePanelListenerEventMulticaster = ch.softenvironment.view.FileNamePanelListenerEventMulticaster.add(fieldFileNamePanelListenerEventMulticaster, newListener);
	return;
}
/**
 * Comment
 */
private void chooseFile() {
	try {
		String fileName = null;

		FileChooser dialog =  new FileChooser(/*LauncherView.getSettings().getWorkingDirectory()*/);
		dialog.setDialogTitle(ResourceManager.getInstance().getResource(FileNamePanel.class, "CT_ChooseFile"));//$NON-NLS-1$
//		dialog.setSelectedFile(new File(fileName));
//		dialog.addChoosableFileFilter(HTMLUtility.createHtmlFilter());
		if (dialog.showOpenDialog(this) == FileChooser.APPROVE_OPTION) {
/*		    FileOutputStream out = new FileOutputStream(dialog.getSelectedFile()); //dialog.getSelectedFile().getName());
		   	PrintStream s = new PrintStream(out);
		   	generateHtmlStream(s, metaInfo);
		   	
		    s.flush();
		    out.close();
			ch.ehi.basics.view.BrowserControl.displayURL("file://" + dialog.getSelectedFile().getAbsolutePath());
*/
			getTxtFileName().setText(dialog.getSelectedFile().getAbsolutePath());
			fireTextKeyReleased(new java.util.EventObject(this));
		}
//	} catch(FileNotFoundException fne) {
//		new ch.softenvironment.view.ErrorDialog(this, "Dateifehler", "???", fne);

	} catch(Throwable e) {
		handleException(e);
	}
}
/**
 * connEtoC1:  (BtnChooseFile.action.actionPerformed(java.awt.event.ActionEvent) --> FileNamePanel.chooseFile()V)
 * @param arg1 java.awt.event.ActionEvent
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void connEtoC1(java.awt.event.ActionEvent arg1) {
	try {
		// user code begin {1}
		// user code end
		this.chooseFile();
		// user code begin {2}
		// user code end
	} catch (java.lang.Throwable ivjExc) {
		// user code begin {3}
		// user code end
		handleException(ivjExc);
	}
}
/**
 * connEtoC2:  (TxtFileName.key.keyReleased(java.awt.event.KeyEvent) --> FileNamePanel.fireTextKeyReleased(Ljava.util.EventObject;)V)
 * @param arg1 java.awt.event.KeyEvent
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void connEtoC2(java.awt.event.KeyEvent arg1) {
	try {
		// user code begin {1}
		// user code end
		this.fireTextKeyReleased(new java.util.EventObject(this));
		// user code begin {2}
		// user code end
	} catch (java.lang.Throwable ivjExc) {
		// user code begin {3}
		// user code end
		handleException(ivjExc);
	}
}
/**
 * Method to support listener events.
 * @param newEvent java.util.EventObject
 */
protected void fireTextKeyReleased(java.util.EventObject newEvent) {
	if (fieldFileNamePanelListenerEventMulticaster == null) {
		return;
	};
	fieldFileNamePanelListenerEventMulticaster.textKeyReleased(newEvent);
}
/**
 * Return the BtnChooseFile property value.
 * @return javax.swing.JButton
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private javax.swing.JButton getBtnChooseFile() {
	if (ivjBtnChooseFile == null) {
		try {
			ivjBtnChooseFile = new javax.swing.JButton();
			ivjBtnChooseFile.setName("BtnChooseFile");
			ivjBtnChooseFile.setToolTipText("Datei w�hlen");
			ivjBtnChooseFile.setText("...");
			// user code begin {1}
			ivjBtnChooseFile.setToolTipText(ResourceManager.getInstance().getResource(FileNamePanel.class, "CT_ChooseFile"));
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjBtnChooseFile;
}
/**
 * Method generated to support the promotion of the text attribute.
 * @return java.lang.String
 */
public java.lang.String getText() {
	return getTxtFileName().getText();
}
/**
 * Return the TxtFileName property value.
 * @return javax.swing.JTextField
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private javax.swing.JTextField getTxtFileName() {
	if (ivjTxtFileName == null) {
		try {
			ivjTxtFileName = new javax.swing.JTextField();
			ivjTxtFileName.setName("TxtFileName");
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjTxtFileName;
}
/**
 * Called whenever the part throws an exception.
 * @param exception java.lang.Throwable
 */
private void handleException(java.lang.Throwable exception) {
	BaseFrame.showException(null, exception);
}
/**
 * Initializes connections
 * @exception java.lang.Exception The exception description.
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void initConnections() throws java.lang.Exception {
	// user code begin {1}
	// user code end
	getBtnChooseFile().addActionListener(ivjEventHandler);
	getTxtFileName().addKeyListener(ivjEventHandler);
}
/**
 * Initialize the class.
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void initialize() {
	try {
		// user code begin {1}
		// user code end
		setName("FileNamePanel");
		setLayout(new java.awt.GridBagLayout());
		setSize(178, 28);

		java.awt.GridBagConstraints constraintsTxtFileName = new java.awt.GridBagConstraints();
		constraintsTxtFileName.gridx = 1; constraintsTxtFileName.gridy = 1;
		constraintsTxtFileName.fill = java.awt.GridBagConstraints.HORIZONTAL;
		constraintsTxtFileName.anchor = java.awt.GridBagConstraints.NORTHWEST;
		constraintsTxtFileName.weightx = 1.0;
		constraintsTxtFileName.ipadx = 136;
		constraintsTxtFileName.insets = new java.awt.Insets(1, 0, 7, 2);
		add(getTxtFileName(), constraintsTxtFileName);

		java.awt.GridBagConstraints constraintsBtnChooseFile = new java.awt.GridBagConstraints();
		constraintsBtnChooseFile.gridx = 2; constraintsBtnChooseFile.gridy = 1;
		constraintsBtnChooseFile.anchor = java.awt.GridBagConstraints.NORTHWEST;
		constraintsBtnChooseFile.ipadx = -13;
		constraintsBtnChooseFile.insets = new java.awt.Insets(0, 3, 3, 3);
		add(getBtnChooseFile(), constraintsBtnChooseFile);
		initConnections();
	} catch (java.lang.Throwable ivjExc) {
		handleException(ivjExc);
	}
	// user code begin {2}
	// user code end
}
/**
 * 
 * @param newListener ch.softenvironment.view.FileNamePanelListener
 */
public void removeFileNamePanelListener(ch.softenvironment.view.FileNamePanelListener newListener) {
	fieldFileNamePanelListenerEventMulticaster = ch.softenvironment.view.FileNamePanelListenerEventMulticaster.remove(fieldFileNamePanelListenerEventMulticaster, newListener);
	return;
}
/**
 * Method generated to support the promotion of the text attribute.
 * @param arg1 java.lang.String
 */
public void setText(java.lang.String arg1) {
	getTxtFileName().setText(arg1);
}
}
