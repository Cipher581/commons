package art.cipher581.commons.gui.chart;


import java.util.List;

import org.jfree.chart.plot.Plot;


public interface IPlotCreator<E> {

	public Plot createPlot(List<? extends E> objects) throws ChartCreationException;

}
