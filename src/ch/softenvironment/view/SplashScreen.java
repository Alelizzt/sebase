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
 
import java.awt.*;
import javax.swing.*;
/**
 * Splash screen for Application startup.
 * @author: Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.5 $ $Date: 2004-10-26 19:16:42 $
 */
public class SplashScreen extends JWindow {
	IvjEventHandler ivjEventHandler = new IvjEventHandler();
	private JPanel ivjJWindowContentPane = null;
	private JLabel ivjLblImage = null;

class IvjEventHandler implements java.awt.event.WindowListener {
		public void windowActivated(java.awt.event.WindowEvent e) {};
		public void windowClosed(java.awt.event.WindowEvent e) {};
		public void windowClosing(java.awt.event.WindowEvent e) {
			if (e.getSource() == SplashScreen.this) 
				connEtoC1(e);
		};
		public void windowDeactivated(java.awt.event.WindowEvent e) {};
		public void windowDeiconified(java.awt.event.WindowEvent e) {};
		public void windowIconified(java.awt.event.WindowEvent e) {};
		public void windowOpened(java.awt.event.WindowEvent e) {};
	};
/**
 * SplashScreen constructor.
 * Show a certain picture.
 */
public SplashScreen(Dimension windowSize, String image) {
	super();
	initialize();
	setSize(windowSize);
	setImage(image);
}
/**
 * SplashScreen constructor.
 * Show a certain picture.
 */
public SplashScreen(Dimension windowSize, ImageIcon image) {
	super();
	initialize();
	setSize(windowSize);
	setImage(image);
}
/**
 * connEtoC1:  (XSplashScreen.window.windowClosing(java.awt.event.WindowEvent) --> XSplashScreen.dispose()V)
 * @param arg1 java.awt.event.WindowEvent
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void connEtoC1(java.awt.event.WindowEvent arg1) {
	try {
		// user code begin {1}
		// user code end
		this.dispose();
		// user code begin {2}
		// user code end
	} catch (java.lang.Throwable ivjExc) {
		// user code begin {3}
		// user code end
		handleException(ivjExc);
	}
}
/**
 * Return the JWindowContentPane property value.
 * @return javax.swing.JPanel
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private javax.swing.JPanel getJWindowContentPane() {
	if (ivjJWindowContentPane == null) {
		try {
			ivjJWindowContentPane = new javax.swing.JPanel();
			ivjJWindowContentPane.setName("JWindowContentPane");
			ivjJWindowContentPane.setBorder(new javax.swing.border.EtchedBorder());
			ivjJWindowContentPane.setLayout(new java.awt.BorderLayout());
			getJWindowContentPane().add(getLblImage(), "Center");
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjJWindowContentPane;
}
/**
 * Return the JLabel1 property value.
 * @return javax.swing.JLabel
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private javax.swing.JLabel getLblImage() {
	if (ivjLblImage == null) {
		try {
			ivjLblImage = new javax.swing.JLabel();
			ivjLblImage.setName("LblImage");
			ivjLblImage.setIcon(null);
			ivjLblImage.setText("");
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjLblImage;
}
/**
 * Called whenever the part throws an exception.
 * @param exception java.lang.Throwable
 */
private void handleException(java.lang.Throwable exception) {
	ch.softenvironment.util.Tracer.getInstance().uncaughtException(this, "handleException(..)", exception);
}
/**
 * Initializes connections
 * @exception java.lang.Exception The exception description.
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void initConnections() throws java.lang.Exception {
	// user code begin {1}
	// user code end
	this.addWindowListener(ivjEventHandler);
}
/**
 * Initialize the class.
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void initialize() {
	try {
		// user code begin {1}
		// user code end
		setName("XSplashScreen");
		setSize(300, 220);
		setContentPane(getJWindowContentPane());
		initConnections();
	} catch (java.lang.Throwable ivjExc) {
		handleException(ivjExc);
	}
	// user code begin {2}
	getLblImage().setText("");
	// user code end
}
/**
 * Set the wanted image.
 * @param image from IDE with relative FileSystem or compiled within Jar "/images/myPic.gif"
 */
public void setImage(String image) {
	ImageIcon icon = new javax.swing.ImageIcon(SplashScreen.class.getResource(image));
	if (icon == null) {
		ch.softenvironment.util.Tracer.getInstance().runtimeWarning(this, "setImage(String)", "given iconFile not found <" + image + ">!");
	} else {
		setImage(icon);
	}
}
/**
 * Set the wanted image.
 * @parm icon
 */
public void setImage(ImageIcon icon) {
	if (icon == null) {
		ch.softenvironment.util.Tracer.getInstance().runtimeWarning(this, "setImage(ImageIcon)", "given icon is null!");
	}
	getLblImage().setIcon(icon);
}
}
