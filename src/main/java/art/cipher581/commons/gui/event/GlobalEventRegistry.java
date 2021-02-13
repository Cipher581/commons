package art.cipher581.commons.gui.event;



public class GlobalEventRegistry extends EventRegistry {

	private static final GlobalEventRegistry instance = new GlobalEventRegistry();


	private GlobalEventRegistry() {
		super();
	}


	public static GlobalEventRegistry getInstance() {
		return instance;
	}

}
