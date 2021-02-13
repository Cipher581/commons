package art.cipher581.commons.gui.util;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TextListener extends AbstractEnableDisableListener implements DocumentListener {


	public TextListener() {
		super();
	}

	public TextListener(IAction action) {
		super(action);
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		action();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		action();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		action();
	}

}
