package art.cipher581.commons.gui.chart;



public class ShowChartRequest<E> {

	private IPlotCreator<E> plotCreator;
	

	public ShowChartRequest(IPlotCreator<E> plotCreator) {
		super();
		
		this.plotCreator = plotCreator;
	}


	public IPlotCreator<E> getPlotCreator() {
		return plotCreator;
	}

	
	public void setPlotCreator(IPlotCreator<E> plotCreator) {
		this.plotCreator = plotCreator;
	}

}
