package art.cipher581.commons.gui.util;


public class NamedAction extends AbstractNamedAction {

	private IAction action;


	public NamedAction(String name, IAction action) {
		super(name);
		
		this.action = action;
	}
	
	
	public IAction getAction() {
		return action;
	}
	
	
	
	public void setAction(IAction action) {
		this.action = action;
	}
	
	
	@Override
	public void performAction() throws PerformActionException {
		action.performAction();
	}

}
