package art.cipher581.commons.gui.util;


import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import art.cipher581.commons.gui.event.IEventListener;
import art.cipher581.commons.gui.event.UIEvent;


public class OpenFileHandler implements IEventListener {

	private static final Logger LOGGER = LogManager.getLogger(OpenFileHandler.class);


	@Override
	public void eventOccured(UIEvent event) {

		if (event.getValue() != null && event.getValue() instanceof File) {
			File file = (File) event.getValue();

			try {
				openFile(file);
			} catch (Exception ex) {
				String message = "File " + file + " could not be opened";

				JOptionPane.showMessageDialog(event.getSource(), message, "Error", JOptionPane.ERROR_MESSAGE);

				LOGGER.warn(message, ex);
			}
		}
	}
	
	
	public void openFile(File file) throws IOException {
		Desktop desktop = Desktop.getDesktop();

		if (desktop != null) {
			desktop.open(file);
		}
	}

}
