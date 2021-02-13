package art.cipher581.commons.gui.chart;


import java.util.List;

import org.jfree.data.general.Dataset;

import art.cipher581.commons.util.Group;
import art.cipher581.commons.util.IValueProvider;


public abstract class AbstractDataSeriesCreator<E, DS extends Dataset> {

	protected IValueProvider<String, E> groupValueProvider;


	public AbstractDataSeriesCreator() {
		super();
	}


	public AbstractDataSeriesCreator(IValueProvider<String, E> groupValueProvider) {
		super();

		this.groupValueProvider = groupValueProvider;
	}


	public IValueProvider<String, E> getGroupValueProvider() {
		return groupValueProvider;
	}


	public void setGroupValueProvider(IValueProvider<String, E> groupValueProvider) {
		this.groupValueProvider = groupValueProvider;
	}


	public DS create(List<? extends E> bills) throws ChartCreationException {
		IValueProvider<String, E> groupValueProviderLocal;

		if (groupValueProvider == null) {
			groupValueProviderLocal = new IValueProvider<String, E>() {

				@Override
				public String getValue(E obj) {
					return "All";
				}

			};
		} else {
			groupValueProviderLocal = groupValueProvider;
		}

		// Daten gruppieren
		List<Group<String, E>> groups = Group.group(bills, groupValueProviderLocal);

		DS dataset = createDataset();

		int g = 0;
		for (Group<String, E> group : groups) {
			handleGroup(group, g, dataset);
		}

		return dataset;
	}


	protected abstract DS createDataset();


	protected abstract void handleGroup(Group<String, E> group, int groupNumber, DS dataset) throws ChartCreationException;

}
