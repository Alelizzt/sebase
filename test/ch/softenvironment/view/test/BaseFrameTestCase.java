package ch.softenvironment.view.test;
import java.awt.BorderLayout;

import ch.softenvironment.util.DeveloperException;
import ch.softenvironment.util.Tracer;
import ch.softenvironment.view.BaseFrame;


import javax.swing.JButton;
/**
 * Test class BaseFrame.
 * @author Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.3 $ $Date: 2006-06-29 22:32:21 $
 */
public class BaseFrameTestCase extends BaseFrame {

	private javax.swing.JPanel jContentPane = null;
	private JButton btnShowBusy = null;
	private JButton btnShowBusyTwice = null;
	private JButton btnShowBusySequential = null;
    private JButton btnShowDeveloperException = null;
	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getBtnShowBusy() {
		if (btnShowBusy == null) {
			btnShowBusy = new JButton();
			btnShowBusy.setText("#showBusy() [once]");
			btnShowBusy.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					showBusy(new Runnable() {
					    public void run() {
							doLongAction(1);
					    }
					});
				}
			});
		}
		return btnShowBusy;
	}
	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getBtnShowBusyTwice() {
		if (btnShowBusyTwice == null) {
			btnShowBusyTwice = new JButton();
			btnShowBusyTwice.setText("#showBusy() [recursive]");
			btnShowBusyTwice.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
				    showBusy(new Runnable() {
					    public void run() {
							doLongAction(3);
					    }
					});
				}
			});
		}
		return btnShowBusyTwice;
	}
	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getBtnShowBusySequential() {
		if (btnShowBusySequential == null) {
			btnShowBusySequential = new JButton();
			btnShowBusySequential.setText("#showBusy() [sequential]");
			btnShowBusySequential.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
				    showBusy(new Runnable() {
					    public void run() {
							doLongAction(1);
					    }
					});
				    showBusy(new Runnable() {
					    public void run() {
							doLongAction(1);
					    }
					});
				}
			});
		}
		return btnShowBusySequential;
	}
    private JButton getBtnShowDeveloperException() {
        if (btnShowDeveloperException == null) {
            btnShowDeveloperException = new JButton();
            btnShowDeveloperException.setText("throw new DeveloperException");
            btnShowDeveloperException.addActionListener(new java.awt.event.ActionListener() { 
                public void actionPerformed(java.awt.event.ActionEvent e) {    
                    showBusy(new Runnable() {
                        public void run() {
                            // WaitDialog#showBusy() will handle it
                            throw new DeveloperException("dev-msg", this + "#getBtnShowDeveloperException()", new RuntimeException("other hint"));
                        }
                    });
                }
            });
        }
        return btnShowDeveloperException;
    }
       public static void main(String[] args) {
         Tracer.start(Tracer.ALL);
         
         BaseFrame view = new BaseFrameTestCase();
         view.pack();
         view.setVisible(true);
    }
	/**
	 * This is the default constructor
	 */
	public BaseFrameTestCase() {
		super();
		initialize();
	}
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setTitle("Test: BaseFrame");
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		this.setSize(300,200);
		this.setContentPane(getJContentPane());
	}
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getJContentPane() {
		if(jContentPane == null) {
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getBtnShowBusy(), java.awt.BorderLayout.SOUTH);
			jContentPane.add(getBtnShowBusyTwice(), java.awt.BorderLayout.NORTH);
			jContentPane.add(getBtnShowBusySequential(), java.awt.BorderLayout.CENTER);
            jContentPane.add(getBtnShowDeveloperException(), java.awt.BorderLayout.EAST);
		}
		return jContentPane;
	}
    /* (non-Javadoc)
     * @see ch.softenvironment.view.BaseFrame#initializeView()
     */
    protected void initializeView() throws Throwable {
        // TODO Auto-generated method stub
        
    }
	private void doLongAction(int recursionLevel) {
	    try {
	        if (--recursionLevel > 0) {
	            doLongAction(recursionLevel);
	        }
	        Tracer.getInstance().debug("level=" + recursionLevel + " going to sleep 1s");
	        showProgress(20, "level=" + recursionLevel + " going to sleep 1s");
	    	Thread.sleep(1000);
	    	Tracer.getInstance().debug("level=" + recursionLevel + " going to sleep 3s");
	    	showProgress(50, "level=" + recursionLevel + " going to sleep 3s");
	    	Thread.sleep(3000);
	    	showProgress(95, "level=" + recursionLevel + " done");
	    } catch(Throwable e) {
	        Tracer.getInstance().runtimeError("sleep failed", e);
	    }
	}
}
