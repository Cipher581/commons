package art.cipher581.commons.gui.dnd;


import java.awt.Point;
import java.io.File;
import java.util.List;


public interface IDroppedFilesHandler {

	public void handleFiles(List<File> droppedFiles, Point point);

}
