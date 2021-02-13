package art.cipher581.commons.gui.dnd;

import java.awt.Component;
import java.awt.Container;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;


public class DragSourceUtils {
	
	public static void createDefaultDragGestureRecognizer(Component component, final DragSource dragSource, final DragGestureListener dragGestureListener) {
		if (component instanceof Container) {
			Container container = (Container) component;
			
			Component[] children = container.getComponents();
			for (Component child : children) {
				createDefaultDragGestureRecognizer(child, dragSource, dragGestureListener);
			}
			
			container.addContainerListener(new ContainerListener() {
				
				@Override
				public void componentRemoved(ContainerEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				
				@Override
				public void componentAdded(ContainerEvent e) {
					Component added = e.getChild();
					
					createDefaultDragGestureRecognizer(added, dragSource, dragGestureListener);
				}
			});
		}

		dragSource.createDefaultDragGestureRecognizer(component, DnDConstants.ACTION_COPY_OR_MOVE, dragGestureListener);
	}

}
