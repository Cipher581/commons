package art.cipher581.commons.gui.chart.line;


import java.util.List;

import org.jfree.data.time.TimeSeriesCollection;

import art.cipher581.commons.gui.chart.ChartCreationException;


public interface ITimeSeriesCreator<E> {

	public TimeSeriesCollection create(List<? extends E> objects) throws ChartCreationException;


	public String getShortDescription();

}
