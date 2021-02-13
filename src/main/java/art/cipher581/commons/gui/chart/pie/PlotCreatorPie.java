package art.cipher581.commons.gui.chart.pie;


import java.util.List;

import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.Plot;
import org.jfree.data.general.PieDataset;

import art.cipher581.commons.gui.chart.ChartCreationException;
import art.cipher581.commons.gui.chart.IPlotCreator;


public class PlotCreatorPie<E> implements IPlotCreator<E> {

	private IPieDatasetCreator<E> datasetCreator;


	public PlotCreatorPie(IPieDatasetCreator<E> datasetCreator) {
		super();

		this.datasetCreator = datasetCreator;
	}


	public IPieDatasetCreator<E> getDatasetCreator() {
		return datasetCreator;
	}


	public void setDatasetCreator(IPieDatasetCreator<E> datasetCreator) {
		this.datasetCreator = datasetCreator;
	}


	@Override
	public Plot createPlot(List<? extends E> bills) throws ChartCreationException {
		PieDataset pieDataset = datasetCreator.create(bills);

		PiePlot3D piePlot = new PiePlot3D(pieDataset);

		return piePlot;
	}

}
