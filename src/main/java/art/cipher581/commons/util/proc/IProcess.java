package art.cipher581.commons.util.proc;


public interface IProcess {

	public boolean isFinished();
	
	public double getPercent();
	
	public int getItems();
	
	public int getDone();

}
