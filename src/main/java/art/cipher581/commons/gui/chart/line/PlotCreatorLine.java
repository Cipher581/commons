package art.cipher581.commons.gui.chart.line;


import java.util.List;

import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.TimeSeriesCollection;

import art.cipher581.commons.gui.chart.ChartCreationException;
import art.cipher581.commons.gui.chart.IPlotCreator;


public class PlotCreatorLine<E> implements IPlotCreator<E> {

	private ITimeSeriesCreator<E> timeSeriesCreator;

	private XYItemRenderer itemRenderer = new XYLineAndShapeRenderer(true, true);


	public PlotCreatorLine(ITimeSeriesCreator<E> timeSeriesCreator, XYItemRenderer itemRenderer) {
		super();

		this.timeSeriesCreator = timeSeriesCreator;

		if (itemRenderer != null) {
			this.itemRenderer = itemRenderer;
		}
	}


	public ITimeSeriesCreator<E> getTimeSeriesCreator() {
		return timeSeriesCreator;
	}


	public void setTimeSeriesCreator(ITimeSeriesCreator<E> timeSeriesCreator) {
		this.timeSeriesCreator = timeSeriesCreator;
	}


	@Override
	public Plot createPlot(List<? extends E> objects) throws ChartCreationException {
		if (timeSeriesCreator == null) {
			throw new IllegalStateException("timeSeriesCreator is null");
		}

		TimeSeriesCollection dataset = timeSeriesCreator.create(objects);

		ValueAxis valueAxisX = new DateAxis("Zeit");
		ValueAxis valueAxisY = new NumberAxis(timeSeriesCreator.getShortDescription());

		XYPlot xyPlot = new XYPlot(dataset, valueAxisX, valueAxisY, itemRenderer);

		return xyPlot;
	}

}