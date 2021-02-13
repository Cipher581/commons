package art.cipher581.commons.gui.chart;


import java.util.List;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Plot;


public class ChartCreator<E> implements IChartCreator<E> {
	
	
	@Override
	public ChartPanel createChart(Plot plot) {
		JFreeChart chart = new JFreeChart(plot);
		
		ChartPanel chartPanel = new ChartPanel(chart);
		
		return chartPanel;
	}
	

	@Override
	public ChartPanel createChart(IPlotCreator<E> plotCreator, List<? extends E> objects) throws ChartCreationException {
		Plot plot = plotCreator.createPlot(objects);
		
		return createChart(plot);
	}

}
