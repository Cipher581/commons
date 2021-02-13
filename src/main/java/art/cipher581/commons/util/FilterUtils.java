package art.cipher581.commons.util;


import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class FilterUtils {

    private static final Logger LOGGER = LogManager.getLogger(FilterUtils.class);


    public static <E> List<E> filter(IFilter<E> filter, E[] objects) {
        List<E> list = objects == null ? new LinkedList<E>() : Arrays.asList(objects);

        return filter(filter, list);
    }


    public static <E> List<E> filter(IFilter<E> filter, Collection<? extends E> objects) {
        List<E> filtered = new LinkedList<>();

        if (objects != null) {
            if (filter != null) {
                for (E object : objects) {
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("evaluating " + object);
                    }
                    filter.evaluate(object);
                }
            }

            for (E object : objects) {
                if (filter == null || filter.accept(object)) {
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("accepting " + object);
                    }

                    filtered.add(object);
                } else if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("ignoring " + object);
                }
            }
        }

        return filtered;
    }


    public static <E> boolean acceptedByAll(List<? extends IFilter<E>> filters, E object) {
        if (filters == null || filters.isEmpty()) {
            throw new IllegalArgumentException("filters is null or filters is empty");
        }

        for (IFilter<E> filter : filters) {
            if (!filter.accept(object)) {
                return false;
            }
        }

        return true;
    }
}
