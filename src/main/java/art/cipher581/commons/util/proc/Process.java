package art.cipher581.commons.util.proc;


public class Process implements IProcess {

	private boolean finished = false;
	
	private int done = 0;
	
	private int items = 0;
	
	
	
	public void setDone(int done) {
		this.done = done;
	}

	
	public void setItems(int items) {
		this.items = items;
	}


	@Override
	public boolean isFinished() {
		return finished;
	}

	
	@Override
	public double getPercent() {
		if (items == done) {
			return 1;
		} else if (items == 0) {
			return 0;
		}
		
		double percent = ((double) done) / ((double) items);
		
		return percent;
	}

	
	public void setFinished(boolean finished) {
		this.finished = finished;
		
		if (finished) {
			done = items;
		}
	}


	@Override
	public int getItems() {
		return items;
	}

	@Override
	public int getDone() {
		return done;
	}
	
	
	public void increaseDone() {
		done++;
	}

}
