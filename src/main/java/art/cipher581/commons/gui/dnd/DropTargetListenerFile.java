package art.cipher581.commons.gui.dnd;

import java.awt.Component;
import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class DropTargetListenerFile implements DropTargetListener {

	private static final Logger LOGGER = LogManager.getLogger(DropTargetListenerFile.class);
	
	private Component parentComponent;
	
	private IDroppedFilesHandler droppedFilesHandler;
	
	
	public DropTargetListenerFile(IDroppedFilesHandler droppedFilesHandler, Component parentComponent) {
		super();

		this.droppedFilesHandler = droppedFilesHandler;
		this.parentComponent = parentComponent;
	}


	@Override
	public void dropActionChanged(DropTargetDragEvent dtde) {
		// do nothing
	}

	@Override
	public void drop(DropTargetDropEvent dtde) {
		dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);

		Point point = dtde.getLocation();

		System.out.println("drop");

		try {
			Transferable transferable = dtde.getTransferable();

			if (transferable != null) {
				System.out.println("transferable: " + transferable);

				for (DataFlavor dataFlavor : transferable.getTransferDataFlavors()) {
					System.out.println("dataFlavor: " + dataFlavor);

					if (dataFlavor.isFlavorJavaFileListType()) {
						@SuppressWarnings("unchecked")
						List<File> droppedFiles = (List<File>) transferable.getTransferData(DataFlavor.javaFileListFlavor);

						droppedFilesHandler.handleFiles(droppedFiles, point);
					}
				}
			}
		} catch (Exception ex) {
			String message = "Error while dropping object(s)";
			LOGGER.error(message, ex);
			JOptionPane.showMessageDialog(parentComponent, message, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	@Override
	public void dragOver(DropTargetDragEvent dtde) {
		// do nothing
	}


	@Override
	public void dragExit(DropTargetEvent dte) {
		// do nothing
	}


	@Override
	public void dragEnter(DropTargetDragEvent dtde) {
		// do nothing
	}
}
