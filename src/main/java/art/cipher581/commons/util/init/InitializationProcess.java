package art.cipher581.commons.util.init;


import java.util.List;

import art.cipher581.commons.util.proc.AbstractProcessThread;


public class InitializationProcess extends AbstractProcessThread<InitializationStep> {

	public InitializationProcess(List<InitializationStep> steps) {
		super(steps);
	}


	@Override
	protected void process(InitializationStep step) throws InitializationException {
		System.out.println("run step: " + step.getClass());

		step.run();
		
		System.out.println("step: " + step.getClass() + " finished");
	}

}
