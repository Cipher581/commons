package art.cipher581.commons.util.img;


import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;


/**
 * Based on
 * https://stackoverflow.com/questions/4552045/copy-bufferedimage-to-clipboard
 */
public class TransferableImage implements Transferable {

    private final BufferedImage image;


    public TransferableImage(BufferedImage image) {
        super();

        this.image = image;
    }


    @Override
    public DataFlavor[] getTransferDataFlavors() {
        DataFlavor[] flavors = {DataFlavor.imageFlavor};

        return flavors;
    }


    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor.equals(DataFlavor.imageFlavor);
    }


    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
        if (flavor.equals(DataFlavor.imageFlavor) && image != null) {
            return image;
        } else {
            throw new UnsupportedFlavorException(flavor);
        }
    }

}
