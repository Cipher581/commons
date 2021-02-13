package art.cipher581.commons.gui.chart.line;


import java.util.List;

import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import art.cipher581.commons.gui.chart.AbstractDataSeriesCreator;
import art.cipher581.commons.util.Group;
import art.cipher581.commons.util.IValueProvider;


public abstract class AbstractTimeSeriesCreator<E> extends AbstractDataSeriesCreator<E, TimeSeriesCollection> implements ITimeSeriesCreator<E> {

	public AbstractTimeSeriesCreator() {
		super();
	}


	public AbstractTimeSeriesCreator(IValueProvider<String, E> groupValueProvider) {
		super(groupValueProvider);
	}


	@Override
	protected TimeSeriesCollection createDataset() {
		TimeSeriesCollection dataset = new TimeSeriesCollection();

		return dataset;
	}


	protected abstract void fillTimeSeries(List<E> groupElements, TimeSeries timeSeries);


	protected void handleGroup(Group<String, E> group, int groupNumber, TimeSeriesCollection dataset) {
		List<E> groupElements = group.getElements();

		TimeSeries timeSeries = new TimeSeries(group.getGroup());

		fillTimeSeries(groupElements, timeSeries);

		dataset.addSeries(timeSeries);
	}

}
