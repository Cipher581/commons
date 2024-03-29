package art.cipher581.commons.gui.components;


import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import art.cipher581.commons.da.IDataProvider;
import art.cipher581.commons.gui.util.ComponentUtilities;
import art.cipher581.commons.gui.util.IAction;
import art.cipher581.commons.gui.util.INamedAction;
import art.cipher581.commons.gui.util.IRefreshable;


/**
 * 
 */
public class SelectItemsDialog<E> extends JDialog implements IRefreshable {

	/**
	 * SVUID
	 */
	private static final long serialVersionUID = 7561560023991924621L;

	private IDataProvider<List<E>> dataProvider;

	private List<INamedAction> actions = new LinkedList<INamedAction>();

	private boolean okClicked;
	
	private IAction listDoubleClickAction = null;


	public SelectItemsDialog(Frame parent, IDataProvider<List<E>> dataProvider) {
		super(parent, true);

		this.dataProvider = dataProvider;

		initComponents();

		init();
	}


	public SelectItemsDialog(Dialog parent, IDataProvider<List<E>> dataProvider) {
		super(parent, true);

		this.dataProvider = dataProvider;

		initComponents();

		init();
	}


	public static <E> SelectItemsDialog<E> create(Component parent, IDataProvider<List<E>> dataProvider) {
		SelectItemsDialog<E> selectItemsDialog;

		Frame frame = ComponentUtilities.getFrame(parent);
		if (frame == null) {
			Dialog dialog = ComponentUtilities.getDialog(parent);

			selectItemsDialog = new SelectItemsDialog<E>(dialog, dataProvider);
		} else {
			selectItemsDialog = new SelectItemsDialog<E>(frame, dataProvider);
		}

		return selectItemsDialog;
	}


	public static <E> SelectItemsDialog<E> create(Component parent, IDataProvider<List<E>> dataProvider, String title, int width, int height) {
		SelectItemsDialog<E> selectItemsDialog = create(parent, dataProvider);

		selectItemsDialog.setTitle(title);
		selectItemsDialog.setSize(width, height);

		return selectItemsDialog;
	}
	
	
	private void init() {
		String actionKeyEsc = "action-key-esc";
		String actionKeyEnter = "action-key-enter";
		KeyStroke esc = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, true);
		KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true);

		InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap actionMap = getRootPane().getActionMap();
		
		inputMap.put(esc, actionKeyEsc);
		inputMap.put(enter, actionKeyEnter);
		
		actionMap.put(actionKeyEsc, createEscAction());
		actionMap.put(actionKeyEnter, createEnterAction());
		
		fillList();
	}


	private Action createEnterAction() {
		return new AbstractAction() {
			
			/**
			 * SVUID
			 */
			private static final long serialVersionUID = 8293089325466260787L;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (jButtonOK.isVisible() && jButtonOK.isEnabled()) {
					handleOkClicked();
				} else {
					close();
				}
			}
		};
	}


	private Action createEscAction() {
		return new AbstractAction() {
			
			/**
			 * SVUID
			 */
			private static final long serialVersionUID = 8293089325466260787L;

			@Override
			public void actionPerformed(ActionEvent e) {
				close();
			}

		};
	}


	public void addAction(INamedAction namedAction) {
		this.actions.add(namedAction);

		createActionButtons();
	}


	private void createActionButtons() {
		jPanelActions.removeAll();

		if (actions != null) {
			for (final INamedAction action : actions) {
				JButton button = new JButton(action.getName());

				button.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent actionevent) {
						try {
							action.performAction();
						} catch (Exception ex) {
							// TODO Dialog / Log
							ex.printStackTrace();
						}
					}
				});

				jPanelActions.add(button);
			}
		}

		jPanelActions.revalidate();
		jPanelButtons.repaint();
	}


	public boolean isOkClicked() {
		return okClicked;
	}


	private void fillList() {
		System.out.println("fillList");

		List<E> items;
		try {
			items = dataProvider.get();

			ComponentUtilities.fill(jListItems, items);
		} catch (Exception ex) {
			// String message = "Error while filling list";

			ex.printStackTrace();
			// TODO LOG + DIALOG
		}
	}


	/**
	 * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
	 */
	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {
		java.awt.GridBagConstraints gridBagConstraints;

		jPanelContent = new javax.swing.JPanel();
		jPanelButtons = new javax.swing.JPanel();
		jButtonOK = new javax.swing.JButton();
		jButtonCancel = new javax.swing.JButton();
		jScrollPaneItems = new javax.swing.JScrollPane();
		jListItems = new javax.swing.JList<E>();
		jPanelActions = new javax.swing.JPanel();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new java.awt.GridLayout(1, 0));

		jPanelContent.setLayout(new java.awt.GridBagLayout());

		jButtonOK.setText("OK");
		jButtonOK.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonOKActionPerformed(evt);
			}
		});
		jPanelButtons.add(jButtonOK);

		jButtonCancel.setText("Cancel");
		jButtonCancel.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonCancelActionPerformed(evt);
			}
		});
		jPanelButtons.add(jButtonCancel);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		jPanelContent.add(jPanelButtons, gridBagConstraints);

		jListItems.addMouseListener(new java.awt.event.MouseAdapter() {

			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jListItemsMouseClicked(evt);
			}
		});
		jScrollPaneItems.setViewportView(jListItems);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		jPanelContent.add(jScrollPaneItems, gridBagConstraints);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		jPanelContent.add(jPanelActions, gridBagConstraints);

		getContentPane().add(jPanelContent);

		pack();
	}// </editor-fold>
	//GEN-END:initComponents


	private void jListItemsMouseClicked(MouseEvent evt) {
		if (evt.getButton() == MouseEvent.BUTTON1 && evt.getClickCount() == 2) {
			if (listDoubleClickAction != null) {
				try {
					listDoubleClickAction.performAction();
				} catch (Exception ex) {
					// TODO show error
				}
			}
		}
	}


	private void jButtonCancelActionPerformed(ActionEvent evt) {
		close();
	}


	private void close() {
		dispose();
	}


	private void jButtonOKActionPerformed(ActionEvent evt) {
		handleOkClicked();
	}
	

	private void handleOkClicked() {
		this.okClicked = true;

		close();
	}


	/**
	 * return the first (of possibly many) selected item
	 * 
	 * @return null if nothing is selected
	 */
	public E getSelectedItem() {
		return getSelectedItem(false);
	}


	/**
	 * @param singleSelectionOnly if true: a message will be shown if more than one item is selected
	 * 
	 * @return null if nothing is selected or if to much is selected (only if singleSelectionOnly is true)
	 */
	public E getSelectedItem(boolean singleSelectionOnly) {
		List<E> selected = getSelectedItems();

		if (selected == null || selected.isEmpty()) {
			return null;
		} else {
			if (singleSelectionOnly && selected.size() > 1) {

				JOptionPane.showMessageDialog(this, "More than one item is selected. Please select only one item", "Invalid selection", JOptionPane.INFORMATION_MESSAGE);

				return null;
			} else {
				return selected.get(0);
			}
		}
	}


	public List<E> getSelectedItems() {
		List<E> selected = jListItems.getSelectedValuesList();

		return selected;
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton jButtonCancel;

	private javax.swing.JButton jButtonOK;

	private javax.swing.JList<E> jListItems;

	private javax.swing.JPanel jPanelActions;

	private javax.swing.JPanel jPanelButtons;

	private javax.swing.JPanel jPanelContent;

	private javax.swing.JScrollPane jScrollPaneItems;


	// End of variables declaration//GEN-END:variables

	@Override
	public void refresh() {
		fillList();
	}


	public void setCancelText(String text) {
		jButtonCancel.setText(text);
	}


	public void hideOKButton() {
		jButtonOK.setVisible(false);
		jButtonOK.setEnabled(false);
	}
	
	
	
	public IAction getListDoubleClickAction() {
		return listDoubleClickAction;
	}
	

	public void setListDoubleClickAction(IAction listDoubleClickAction) {
		this.listDoubleClickAction = listDoubleClickAction;
	}

}