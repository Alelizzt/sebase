package ch.softenvironment.view;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

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
import ch.softenvironment.util.DateUtils;

/**
 * Define a Date-Range especially for SearchView's.
 * 
 * @author Peter Hirzel, softEnvironment GmbH
 */
@SuppressWarnings("serial")
public class DateRangePanel extends javax.swing.JPanel {
	private javax.swing.JLabel ivjLblBilled32 = null;
	private ch.softenvironment.view.swingext.DateTextField ivjTxtDateFrom = null;
	private ch.softenvironment.view.swingext.DateTextField ivjTxtDateTo = null;
	private javax.swing.JComboBox ivjCbxPeriod = null;
	IvjEventHandler ivjEventHandler = new IvjEventHandler();

	class IvjEventHandler implements java.awt.event.ItemListener {
		public void itemStateChanged(java.awt.event.ItemEvent e) {
			if (e.getSource() == DateRangePanel.this.getCbxPeriod())
				connEtoC1(e);
		};
	};

	/**
	 * DateFromToPanel constructor comment.
	 */
	public DateRangePanel() {
		super();
		initialize();
		addUndoRedo(getTxtDateFrom(), getTxtDateTo());
	}
	
	/**
	  * Handle Ctrl+z and Ctrl+y to Undo/Redo text
	  * @param textcomp
	  */
	 private void addUndoRedo(JTextComponent... textcomp) {
		
		 for(int i=0;i<textcomp.length;i++){
			 final UndoManager undo = new UndoManager();
				 Document doc = textcomp[i].getDocument();
			    
			   // Listen for undo and redo events
			   doc.addUndoableEditListener(new UndoableEditListener() {
			       public void undoableEditHappened(UndoableEditEvent evt) {
			           undo.addEdit(evt.getEdit());
			       }
			   });
			    
			   // Create an undo action and add it to the text component
			   textcomp[i].getActionMap().put("Undo",
			       new AbstractAction("Undo") {
			           public void actionPerformed(ActionEvent evt) {
			               try {
			                   if (undo.canUndo()) {
			                       undo.undo();
			                   }
			               } catch (CannotUndoException e) {
			               }
			           }
			      });
			    
			   // Bind the undo action to ctl-Z
			   textcomp[i].getInputMap().put(KeyStroke.getKeyStroke("control Z"), "Undo");
			    
			   // Create a redo action and add it to the text component
			   textcomp[i].getActionMap().put("Redo",
			       new AbstractAction("Redo") {
			           public void actionPerformed(ActionEvent evt) {
			               try {
			                   if (undo.canRedo()) {
			                       undo.redo();
			                   }
			               } catch (CannotRedoException e) {
			               }
			           }
			       });
			    
			   // Bind the redo action to ctl-Y
			   textcomp[i].getInputMap().put(KeyStroke.getKeyStroke("control Y"), "Redo");
		 }
	 }

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		getTxtDateFrom().setEnabled(enabled);
		getTxtDateTo().setEnabled(enabled);
		getCbxPeriod().setEnabled(enabled);
	}

	/**
	 * Set Date-Period according to ComboBox choice.
	 */
	private void changePeriod() {
		int index = getCbxPeriod().getSelectedIndex();
		switch (index) {
		case 0: {
			reset();
			break;
		}
		case 1: {
			// today
			setFromDate(new java.util.Date());
			setToDate(new java.util.Date());
			break;
		}
		case 2: {
			// this week
			setFromDate(DateUtils.getBeginingOfWeek());
			setToDate(DateUtils.getEndOfWeek());
			break;
		}
		case 3: {
			// this month
			setFromDate(DateUtils.getFirstOfMonth());
			setToDate(DateUtils.getEndOfMonth());
			break;
		}
		case 4: {
			// this year
			setFromDate(DateUtils.getFirstOfYear());
			setToDate(DateUtils.getEndOfYear());
			break;
		}
		}
	}

	/**
	 * connEtoC1: (CbxPeriod.item.itemStateChanged(java.awt.event.ItemEvent) -->
	 * DateFromToPanel.changePeriod()V)
	 * 
	 * @param arg1
	 *            java.awt.event.ItemEvent
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private void connEtoC1(java.awt.event.ItemEvent arg1) {
		try {
			// user code begin {1}
			// user code end
			this.changePeriod();
			// user code begin {2}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {3}
			// user code end
			handleException(ivjExc);
		}
	}

	/**
	 * Return the CbxPeriod property value.
	 * 
	 * @return javax.swing.JComboBox
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JComboBox getCbxPeriod() {
		if (ivjCbxPeriod == null) {
			try {
				ivjCbxPeriod = new javax.swing.JComboBox();
				ivjCbxPeriod.setName("CbxPeriod");
				ivjCbxPeriod.setBounds(195, 1, 104, 23);
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjCbxPeriod;
	}

	/**
	 * Return Date in From-Field.
	 */
	public java.util.Date getFromDate() {
		return getTxtDateFrom().getDate();
	}

	/**
	 * Return Text in From-Field.
	 */
	public String getFromDateText() {
		return getTxtDateFrom().getText();
	}

	/**
	 * Return the LblBilled32 property value.
	 * 
	 * @return javax.swing.JLabel
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JLabel getLblBilled32() {
		if (ivjLblBilled32 == null) {
			try {
				ivjLblBilled32 = new javax.swing.JLabel();
				ivjLblBilled32.setName("LblBilled32");
				ivjLblBilled32.setToolTipText("");
				ivjLblBilled32.setText("...");
				ivjLblBilled32.setBounds(91, 6, 15, 14);
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjLblBilled32;
	}

	/**
	 * Return Date in From-Field.
	 */
	public java.util.Date getToDate() {
		return getTxtDateTo().getDate();
	}

	/**
	 * Return Date in From-Field.
	 */
	public String getToDateText() {
		return getTxtDateTo().getText();
	}

	/**
	 * Return the TxtDateFrom property value.
	 * 
	 * @return ch.softenvironment.view.swingext.DateTextField
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private ch.softenvironment.view.swingext.DateTextField getTxtDateFrom() {
		if (ivjTxtDateFrom == null) {
			try {
				ivjTxtDateFrom = new ch.softenvironment.view.swingext.DateTextField();
				ivjTxtDateFrom.setName("TxtDateFrom");
				ivjTxtDateFrom.setBounds(0, 3, 83, 20);
				ivjTxtDateFrom.setEditable(true);
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjTxtDateFrom;
	}

	/**
	 * Return the TxtDateTo property value.
	 * 
	 * @return ch.softenvironment.view.swingext.DateTextField
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private ch.softenvironment.view.swingext.DateTextField getTxtDateTo() {
		if (ivjTxtDateTo == null) {
			try {
				ivjTxtDateTo = new ch.softenvironment.view.swingext.DateTextField();
				ivjTxtDateTo.setName("TxtDateTo");
				ivjTxtDateTo.setBounds(107, 3, 83, 20);
				ivjTxtDateTo.setEditable(true);
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjTxtDateTo;
	}

	/**
	 * Called whenever the part throws an exception.
	 * 
	 * @param exception
	 *            java.lang.Throwable
	 */
	private void handleException(java.lang.Throwable exception) {
		BaseFrame.showException(null, exception);
	}

	/**
	 * Initializes connections
	 * 
	 * @exception java.lang.Exception
	 *                The exception description.
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private void initConnections() throws java.lang.Exception {
		// user code begin {1}
		// user code end
		getCbxPeriod().addItemListener(ivjEventHandler);
	}

	/**
	 * Initialize the class.
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private void initialize() {
		try {
			// user code begin {1}
			java.util.Vector<String> items = new java.util.Vector<String>(5);
			items.add("");
			items.add("Heute");
			items.add("Woche");
			items.add("Monat");
			items.add("Jahr");
			getCbxPeriod().setModel(new javax.swing.DefaultComboBoxModel(items));
			// user code end
			setName("DateFromToPanel");
			setLayout(null);
			setSize(300, 26);
			add(getTxtDateFrom(), getTxtDateFrom().getName());
			add(getLblBilled32(), getLblBilled32().getName());
			add(getTxtDateTo(), getTxtDateTo().getName());
			add(getCbxPeriod(), getCbxPeriod().getName());
			initConnections();
		} catch (java.lang.Throwable ivjExc) {
			handleException(ivjExc);
		}
		// user code begin {2}
		// user code end
	}

	/**
	 * Enter null-values into From/To-Fields.
	 */
	public void reset() {
		getTxtDateFrom().setDate(null);
		getTxtDateTo().setDate(null);
	}

	/**
	 * Set Period.
	 */
	public void setCurrentMonth() {
		getCbxPeriod().setSelectedIndex(3);
	}

	/**
	 * Set Date in From-Field.
	 */
	public void setFromDate(java.util.Date date) {
		getTxtDateFrom().setDate(date);
	}

	/**
	 * Set Date in To-Field.
	 */
	public void setToDate(java.util.Date date) {
		getTxtDateTo().setDate(date);
	}
}
