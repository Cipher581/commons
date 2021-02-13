package art.cipher581.commons.gui.components;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;


public class ThreeStateCheckBox extends JCheckBox implements ActionListener {

	/**
	 * SVUID
	 */
	private static final long serialVersionUID = 352929819773682706L;

	public static final String STATE_CHANGED = "STATE_CHANGED";

	private List<ActionListener> actionListeners = new ArrayList<ActionListener>();

	private Boolean status = null;


	public ThreeStateCheckBox() {
		this(null);
	}


	public ThreeStateCheckBox(String text) {
		super(text);

		setModel(new ThreeStateJCheckBoxModel());
		super.addActionListener(this);

		setSelected(null);
	}


	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (getSelected() == null) {
			Graphics2D g2 = (Graphics2D) g;

			g2.setColor(Color.GRAY);
			g2.fillRect(6, 8, 9, 9);
		}
	}


	public Boolean getSelected() {
		return rmmodel().getSelected();
	}


	public void setSelected(Boolean b) {
		super.removeActionListener(this);

		rmmodel().setSelected(b);

		status = b;

		ActionEvent newActionEvent = new ActionEvent(this, 0, STATE_CHANGED, System.currentTimeMillis(), ActionEvent.ACTION_PERFORMED);
		for (ActionListener listener : actionListeners) {
			listener.actionPerformed(newActionEvent);
		}

		changed();

		super.addActionListener(this);
	}


	public ThreeStateJCheckBoxModel rmmodel() {
		return (ThreeStateJCheckBoxModel) model;
	}


	@Override
	public void addActionListener(ActionListener l) {
		actionListeners.add(l);
	}


	@Override
	public ActionListener[] getActionListeners() {
		return actionListeners.toArray(new ActionListener[] {});
	}


	@Override
	public void removeActionListener(ActionListener l) {
		actionListeners.remove(l);
	}


	public final void actionPerformed(ActionEvent e) {
		Boolean newStatus;

		if (status == null) {
			newStatus = Boolean.TRUE;
		} else if (status.equals(Boolean.FALSE)) {
			newStatus = null;
		} else {
			newStatus = Boolean.FALSE;
		}

		setSelected(newStatus);
	}


	protected final void changed() {
		// nothing
	}

	public class ThreeStateJCheckBoxModel extends ToggleButtonModel {

		/**
		 * SVUID
		 */
		private static final long serialVersionUID = -5778205295077937058L;

		private Boolean selected;


		public Boolean getSelected() {
			return selected;
		}


		public void setSelected(Boolean b) {
			selected = b;
			super.setSelected(b == null ? false : b.booleanValue());
		}


		@Override
		public void setSelected(boolean b) {
			selected = Boolean.valueOf(b);
			super.setSelected(b);
		}
	}

}