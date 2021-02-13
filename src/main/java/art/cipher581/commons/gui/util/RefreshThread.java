package art.cipher581.commons.gui.util;


public class RefreshThread extends Thread {
	
	private long delay = 0;
	
	private IRefreshable refreshable;
	
	private long interval = 5000;
	
	private boolean running = false;

	
	public RefreshThread(IRefreshable refreshable, long interval) {
		this(refreshable, 0, interval);
	}


	public RefreshThread(IRefreshable refreshable, long delay, long interval) {
		super();

		this.delay = delay;
		this.refreshable = refreshable;
		this.interval = interval;
	}


	public long getDelay() {
		return delay;
	}

	
	public void setDelay(long delay) {
		this.delay = delay;
	}

	
	public IRefreshable getRefreshable() {
		return refreshable;
	}

	
	public void setRefreshable(IRefreshable refreshable) {
		this.refreshable = refreshable;
	}

	
	public long getInterval() {
		return interval;
	}

	
	public void setInterval(long interval) {
		this.interval = interval;
	}

	@Override
	public void run() {
		running = true;

		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			// ignore
		}
		
		while (running) {
			if (refreshable != null) {
				refreshable.refresh();
			}
			
			try {
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				// ignore
			}
		}
	}
	
	public synchronized void stopRefreshThread() {
		running = false;
	}

}
