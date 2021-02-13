package art.cipher581.commons.util.init;


import art.cipher581.commons.util.proc.IProcess;


public abstract class InitializationStep implements IProcess {

	public abstract void run() throws InitializationException;

}
