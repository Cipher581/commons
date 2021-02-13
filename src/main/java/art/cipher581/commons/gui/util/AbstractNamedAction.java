package art.cipher581.commons.gui.util;


public abstract class AbstractNamedAction implements INamedAction {

	private String name;


	public AbstractNamedAction(String name) {
		super();

		this.name = name;
	}


	@Override
	public String getName() {
		return name;
	}
	

	public void setName(String name) {
		this.name = name;
	}
}
