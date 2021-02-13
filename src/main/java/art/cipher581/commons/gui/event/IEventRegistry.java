package art.cipher581.commons.gui.event;


public interface IEventRegistry extends IEventListener {

	public void addListener(IEventListener eventListener, String name);

	void removeListener(IEventListener eventListener, String name);

}
