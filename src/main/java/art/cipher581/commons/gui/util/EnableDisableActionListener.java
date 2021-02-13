package art.cipher581.commons.gui.util;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EnableDisableActionListener extends AbstractEnableDisableListener implements ActionListener {

	
	public EnableDisableActionListener() {
		super();
	}

	
	public EnableDisableActionListener(IAction action) {
		super(action);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		action();
	}

}
