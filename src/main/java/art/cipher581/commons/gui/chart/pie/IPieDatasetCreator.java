package art.cipher581.commons.gui.chart.pie;


import java.util.List;

import org.jfree.data.general.PieDataset;

import art.cipher581.commons.gui.chart.ChartCreationException;


public interface IPieDatasetCreator<E> {

	public PieDataset create(List<? extends E> objects) throws ChartCreationException;

}
