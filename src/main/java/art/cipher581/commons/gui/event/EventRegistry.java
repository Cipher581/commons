package art.cipher581.commons.gui.event;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class EventRegistry implements IEventRegistry {

	private static final Logger LOGGER = LogManager.getLogger(EventRegistry.class);
	
	private Map<String, List<IEventListener>> listenersByEvent = new HashMap<String, List<IEventListener>>();
	
	
	public EventRegistry() {
		super();
	}


	@Override
	public void eventOccured(UIEvent event) {
		LOGGER.debug("event " + event);
		
		List<IEventListener> listeners = getListeners(event);

		synchronized (listeners) {
			for (IEventListener listener : listeners) {
				listener.eventOccured(event);
			}
		}
	}
	

	@Override
	public void addListener(IEventListener eventListener, String name) {
		if (name == null) {
			throw new IllegalArgumentException("name is null");
		}
		if (eventListener == null) {
			throw new IllegalArgumentException("eventListener is null");
		}
		
		List<IEventListener> listeners = getListeners(name);
		
		synchronized (listeners) {
			listeners.add(eventListener);
		}
	}
	
	
	@Override
	public void removeListener(IEventListener eventListener, String name) {
		synchronized (listenersByEvent) {
			List<IEventListener> listeners = listenersByEvent.get(name.toLowerCase());
			
			if (listeners != null) {
				listeners.remove(eventListener);
			}
		}
	}
	
	private List<IEventListener> getListeners(UIEvent event) {
		String name = event.getName();
		
		return getListeners(name);
	}


	private List<IEventListener> getListeners(String name) {
		List<IEventListener> listeners;
		
		synchronized (listenersByEvent) {
			listeners = listenersByEvent.get(name.toLowerCase());
	
			if (listeners == null) {
				listeners = new LinkedList<IEventListener>();
				listenersByEvent.put(name.toLowerCase(), listeners);
			}
		}
		
		return listeners;
	}
	
}
