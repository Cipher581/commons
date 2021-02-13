package art.cipher581.commons.gui.event;


import java.util.LinkedList;
import java.util.List;

import art.cipher581.commons.gui.util.IRefreshable;


public class EventHandlerRefresh implements IEventListener {

	private final List<IRefreshable> refreshables = new LinkedList<IRefreshable>();


	public EventHandlerRefresh(IRefreshable refreshable) {
		super();
		
		add(refreshable);
	}
	
	
	public EventHandlerRefresh(List<IRefreshable> refreshables) {
		super();
		
		add(refreshables);
	}
	
	
	public final void add(IRefreshable refreshable) {
		refreshables.add(refreshable);
	}
	
	
	public final void add(List<IRefreshable> refreshables) {
		this.refreshables.addAll(refreshables);
	}


	@Override
	public void eventOccured(UIEvent event) {
		for (IRefreshable refreshable : refreshables) {
			try {
				refreshable.refresh();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
}
