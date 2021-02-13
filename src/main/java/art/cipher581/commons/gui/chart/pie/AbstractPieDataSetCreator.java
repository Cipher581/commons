package art.cipher581.commons.gui.chart.pie;


import java.util.List;

import org.jfree.data.general.DefaultPieDataset;

import art.cipher581.commons.gui.chart.AbstractDataSeriesCreator;
import art.cipher581.commons.gui.chart.ChartCreationException;
import art.cipher581.commons.util.Group;
import art.cipher581.commons.util.IValueProvider;


public abstract class AbstractPieDataSetCreator<E> extends AbstractDataSeriesCreator<E, DefaultPieDataset> implements IPieDatasetCreator<E> {

	public AbstractPieDataSetCreator() {
		super();
	}


	public AbstractPieDataSetCreator(IValueProvider<String, E> groupValueProvider) {
		super(groupValueProvider);
	}


	@Override
	protected DefaultPieDataset createDataset() {
		DefaultPieDataset pieDataset = new DefaultPieDataset();

		return pieDataset;
	}


	protected abstract double getValue(List<E> groupObjects) throws ChartCreationException;


	protected void handleGroup(Group<String, E> group, int groupNumber, DefaultPieDataset dataset) throws ChartCreationException {
		List<E> groupObjects = group.getElements();

		double val = getValue(groupObjects);

		dataset.insertValue(groupNumber, group.getGroup(), val);
	}

}
