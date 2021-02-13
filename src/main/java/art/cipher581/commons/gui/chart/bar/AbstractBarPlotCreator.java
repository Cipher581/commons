package art.cipher581.commons.gui.chart.bar;


import java.awt.Color;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import art.cipher581.commons.gui.chart.ChartColors;
import art.cipher581.commons.gui.chart.ChartCreationException;
import art.cipher581.commons.gui.chart.IPlotCreator;
import art.cipher581.commons.util.Group;
import art.cipher581.commons.util.IValueProvider;


public abstract class AbstractBarPlotCreator<E> implements IPlotCreator<E> {

	/**
	 * grouping
	 */
	private IValueProvider<? extends Comparable<?>, E> groupValueProvider;

	/**
	 * subgrouping
	 */
	private IValueProvider<? extends Comparable<?>, E> subGroupValueProvider;

	private String xAxisName;

	private String yAxisName;

	/**
	 * Name of group (if no subgrouping)
	 */
	private String groupName;

	private CategoryItemRenderer itemRenderer;


	public AbstractBarPlotCreator(IValueProvider<? extends Comparable<?>, E> groupValueProvider, IValueProvider<? extends Comparable<?>, E> subGroupValueProvider, String xAxisName, String yAxisName, String groupName) {
		super();

		this.groupValueProvider = groupValueProvider;
		this.subGroupValueProvider = subGroupValueProvider;
		this.xAxisName = xAxisName;
		this.yAxisName = yAxisName;
		this.groupName = groupName;
		this.itemRenderer = getDefaultRenderer();
	}


	public final CategoryItemRenderer getDefaultRenderer() {
		CategoryItemRenderer itemRenderer = new BarRenderer();

		// Apply default colors
		List<Color> colors = ChartColors.getDefaultColorsWaterMelon();

		int i = 0;
		for (Color color : colors) {
			itemRenderer.setSeriesPaint(i++, color);
		}

		return itemRenderer;
	}


	// protected abstract void sort(List<? extends E> objects);

	private void sort(List<? extends E> objects) {
		Comparator<E> comparator = new Comparator<E>() {

			@Override
			@SuppressWarnings({"rawtypes", "unchecked"})
			public int compare(E o1, E o2) {
				Comparable c1 = groupValueProvider.getValue(o1);
				Comparable c2 = groupValueProvider.getValue(o2);

				return c1.compareTo(c2);
			}
		};

		Collections.sort(objects, comparator);
	}


	@Override
	public Plot createPlot(List<? extends E> objects) throws ChartCreationException {
		sort(objects);

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		List<Group<Comparable<?>, E>> groups = Group.group(objects, groupValueProvider);

		for (Group<? extends Comparable<?>, E> group : groups) {
			List<E> groupObjects = group.getElements();

			if (subGroupValueProvider == null) {
				// no subgrouping
				double val = getValue(groupObjects);

				dataset.addValue(val, groupName, group.getGroup());
			} else {
				List<Group<Comparable<?>, E>> subGroups = Group.group(groupObjects, subGroupValueProvider);

				for (Group<? extends Comparable<?>, E> subGroup : subGroups) {
					List<E> subGroupObjects = subGroup.getElements();

					double val = getValue(subGroupObjects);

					dataset.addValue(val, subGroup.getGroup(), group.getGroup());
				}
			}
		}

		ValueAxis valueAxis = new NumberAxis(yAxisName);

		CategoryAxis categoryAxis = new CategoryAxis(xAxisName);

		CategoryPlot categoryPlot = new CategoryPlot(dataset, categoryAxis, valueAxis, itemRenderer);

		return categoryPlot;
	}


	protected abstract double getValue(List<? extends E> objects);

}
