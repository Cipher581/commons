package art.cipher581.commons.gui.util;


public abstract class AbstractEnableDisableListener {

	private IAction action = null;
	
	private boolean enabled = true;
	
	
	public AbstractEnableDisableListener() {
		super();
	}

	public AbstractEnableDisableListener(IAction action) {
		super();

		this.action = action;
	}
	
	
	protected void action() {
		if (action != null && enabled) {
			try {
				action.performAction();
			} catch (PerformActionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public IAction getAction() {
		return action;
	}

	public void setAction(IAction action) {
		this.action = action;
	}
	
	
	public void enable() {
		this.enabled = true;
	}
	
	
	public void disable() {
		this.enabled = false;
	}
	
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}
