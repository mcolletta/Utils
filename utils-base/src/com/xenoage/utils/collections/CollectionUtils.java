package com.xenoage.utils.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Useful methods for working with collections.
 * 
 * @author Andreas Wenger
 */
public final class CollectionUtils {

	/**
	 * Creates a new empty {@link ArrayList} with the inferred type.
	 */
	public static <T> ArrayList<T> alist() {
		return new ArrayList<T>();
	}

	/**
	 * Creates a new empty {@link ArrayList} with the inferred type
	 * using the given capacity.
	 */
	public static <T> ArrayList<T> alist(int initialCapacity) {
		return new ArrayList<T>(initialCapacity);
	}
	
	/**
	 * Creates a new {@link ArrayList} with the inferred type
	 * using the given elements.
	 */
	public static <T> ArrayList<T> alist(Collection<T> vals) {
		ArrayList<T> ret = new ArrayList<T>(vals.size());
		for (T v : vals)
			ret.add(v);
		return ret;
	}

	/**
	 * Creates a new {@link ArrayList} with the inferred type
	 * using the given elements.
	 */
	@SafeVarargs public static <T> ArrayList<T> alist(T... vals) {
		ArrayList<T> ret = new ArrayList<T>(vals.length);
		for (T v : vals)
			ret.add(v);
		return ret;
	}

	/**
	 * Creates a new {@link ArrayList} with the inferred type
	 * and size, using the given value for each element.
	 */
	public static <T> ArrayList<T> alistInit(T value, int size) {
		ArrayList<T> ret = new ArrayList<T>(size);
		for (int i = 0; i < size; i++)
			ret.add(value);
		return ret;
	}

	/**
	 * Creates a new empty {@link HashMap} with the inferred type.
	 */
	public static <T1, T2> HashMap<T1, T2> map() {
		return new HashMap<T1, T2>();
	}

	/**
	 * Creates a new empty {@link LinkedList} with the inferred type.
	 */
	public static <T> LinkedList<T> llist() {
		return new LinkedList<T>();
	}

	/**
	 * Creates a new {@link LinkedList} with the inferred type
	 * using the given elements.
	 */
	@SafeVarargs public static <T> LinkedList<T> llist(T... vals) {
		LinkedList<T> ret = new LinkedList<T>();
		for (T v : vals)
			ret.add(v);
		return ret;
	}

	/**
	 * Creates a new {@link Set} with the inferred type
	 * using the given elements.
	 */
	@SafeVarargs public static <T> Set<T> set(T... vals) {
		HashSet<T> ret = new HashSet<T>();
		for (T v : vals)
			ret.add(v);
		return ret;
	}

	/**
	 * Returns the first maximum value of the given collection.
	 * If the collection is empty, null is returned.
	 */
	public static <T extends Comparable<T>> T getMax(Collection<T> vals) {
		return getExtremum(vals, 1);
	}

	/**
	 * Returns the first minimum value of the given collection.
	 * If the collection is empty, null is returned.
	 */
	public static <T extends Comparable<T>> T getMin(Collection<T> vals) {
		return getExtremum(vals, -1);
	}

	private static <T extends Comparable<T>> T getExtremum(Collection<T> vals, int comparator) {
		if (vals.size() == 0)
			return null;
		Iterator<T> it = vals.iterator();
		T ret = it.next();
		while (it.hasNext()) {
			T v = it.next();
			if (v.compareTo(ret) * comparator > 0)
				ret = v;
		}
		return ret;
	}

	/**
	 * Returns true, if the given collection contains the given object
	 * (same reference, not same contents).
	 */
	public static boolean containsRef(Collection<?> c, Object o) {
		for (Object e : c)
			if (e == o)
				return true;
		return false;
	}

	/**
	 * Returns true, if the given collection contains a <code>null</code> element.
	 */
	public static boolean containsNull(Collection<?> c) {
		return containsRef(c, null);
	}

	/**
	 * Returns the sum of the items of the given collection.
	 */
	public static float sum(Collection<Float> c) {
		float ret = 0;
		for (float i : c)
			ret += i;
		return ret;
	}

	/**
	 * Finds the value that belongs to the given key in the given map.
	 * If the key is null or no value is found, an {@link IllegalStateException} is thrown.
	 */
	public static <T1, T2> T2 find(T1 key, Map<T1, T2> map)
		throws IllegalStateException {
		if (key == null)
			throw new IllegalStateException("Key is null");
		T2 ret = map.get(key);
		if (ret == null)
			throw new IllegalStateException("No value for key " + key);
		return ret;
	}

	/**
	 * In the given list, sets the element at the given index. If the index is out of
	 * the bounds of this list, it is extended up to this index
	 * and the gaps are filled with the given fillElement. If the given list is null,
	 * an {@link ArrayList} is created. The modified list is returned.
	 */
	public static <T> List<T> setExtend(List<T> list, int index, T element, T fillElement) {
		if (list == null)
			list = new ArrayList<T>(index + 1);
		while (index >= list.size())
			list.add(fillElement);
		list.set(index, element);
		return list;
	}
	
	/**
	 * Merges the given two lists in a new list, but without duplicates.
	 */
	public static <T> List<T> mergeNoDuplicates(List<T> sourceList1, List<T> sourceList2) {
		List<T> ret = alist(sourceList1.size() + sourceList2.size());
		ret.addAll(sourceList1);
		for (T e : sourceList2) {
			if (false == ret.contains(e))
				ret.add(e);
		}
		return ret;
	}

}
