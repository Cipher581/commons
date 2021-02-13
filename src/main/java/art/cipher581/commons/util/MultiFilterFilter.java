package art.cipher581.commons.util;


import java.util.LinkedList;
import java.util.List;


public class MultiFilterFilter<E> implements IFilter<E> {

    public enum LogicalOperator {

        AND, OR

    }

    private final List<IFilter<E>> filters = new LinkedList<IFilter<E>>();

    private LogicalOperator logicalOperator = LogicalOperator.OR;


    public MultiFilterFilter() {
        super();
    }


    public MultiFilterFilter(LogicalOperator logicalOperator) {
        super();

        this.logicalOperator = logicalOperator;
    }


    public void addFilter(IFilter<E> filter) {
        this.filters.add(filter);
    }


    public void removeFilter(IFilter<E> filter) {
        this.filters.remove(filter);
    }


    public LogicalOperator getLogicalOperator() {
        return logicalOperator;
    }


    public void setLogicalOperator(LogicalOperator logicalOperator) {
        this.logicalOperator = logicalOperator;
    }


    @Override
    public boolean accept(E obj) {
        if (filters.isEmpty()) {
            throw new IllegalArgumentException("no filters defined");
        }

        for (IFilter<E> filter : filters) {
            boolean accept = filter.accept(obj);

            if (accept == logicalOperator.equals(LogicalOperator.OR)) {
                return accept;
            }
        }

        return logicalOperator.equals(LogicalOperator.AND);
    }


    public void addFilters(List<? extends IFilter<E>> filters) {
        this.filters.addAll(filters);
    }


    @Override
    public void evaluate(E obj) {
        for (IFilter<E> filter : filters) {
            filter.evaluate(obj);
        }
    }

}
