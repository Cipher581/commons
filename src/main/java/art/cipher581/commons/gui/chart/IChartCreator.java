package art.cipher581.commons.gui.chart;


import java.util.List;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.Plot;


public interface IChartCreator<E> {
	
	public ChartPanel createChart(IPlotCreator<E> plotCreator, List<? extends E> objects) throws ChartCreationException;
	
	public ChartPanel createChart(Plot plot);

}
