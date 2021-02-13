package art.cipher581.commons.util;


import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;


public class Group<G, E> implements Comparable<Group<G, ?>> {

	private G group;

	private List<E> elements = new LinkedList<E>();


	public Group(G group) {
		super();

		this.group = group;
	}


	public G getGroup() {
		return group;
	}


	public void setGroup(G group) {
		this.group = group;
	}


	public void add(E element) {
		elements.add(element);
	}


	public List<E> getElements() {
		return elements;
	}


	public void add(List<E> elements) {
		this.elements.addAll(elements);
	}


	public static <G, E> List<Group<G, E>> group(List<? extends E> elements, IValueProvider<? extends G, E> groupValueProvider) {
		if (groupValueProvider == null) {
			throw new IllegalArgumentException("groupValueProvider is null");
		}

		SortedMap<G, Group<G, E>> grouped = new TreeMap<G, Group<G, E>>();

		if (elements == null) {
			elements = new LinkedList<E>();
		}

		for (E element : elements) {
			G group = groupValueProvider.getValue(element);

			Group<G, E> currentGroup = grouped.get(group);

			if (currentGroup == null) {
				currentGroup = new Group<G, E>(group);
				grouped.put(group, currentGroup);
			}

			currentGroup.add(element);
		}

		return new LinkedList<Group<G, E>>(grouped.values());
	}


	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public int compareTo(Group<G, ?> o) {
		if (group instanceof Comparable) {
			return ((Comparable) group).compareTo(o.group);
		} else {
			return 0;
		}
	}

}
