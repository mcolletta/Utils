package com.xenoage.utils.collections;

import static com.xenoage.utils.collections.ArrayUtils.max;
import static com.xenoage.utils.collections.ArrayUtils.min;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Test;

/**
 * Tests for {@link ArrayUtils}.
 *
 * @author Andreas Wenger
 */
public class ArrayUtilsTest {

	@Test public void copyTest() {
		float[] a1 = new float[1000];
		for (int i = 0; i < a1.length; i++)
			a1[i] = i;
		float[] a2 = ArrayUtils.copy(a1);
		assertArrayEquals(a1, a2, 0);
	}

	@Test public void convertToArrayTest() {
		ArrayList<Integer> al;
		int[] a;
		//null array
		al = null;
		a = ArrayUtils.toIntArray(al);
		assertNotNull(a);
		assertEquals(0, a.length);
		//empty array
		al = new ArrayList<Integer>();
		a = ArrayUtils.toIntArray(al);
		assertEquals(0, a.length);
		//filled array
		int arraySize = 100;
		for (int i = 0; i < arraySize; i++)
			al.add(i);
		a = ArrayUtils.toIntArray(al);
		for (int i = 0; i < arraySize; i++)
			assertEquals(i, a[i]);
	}
	
	@Test public void minTestInt() {
		assertEquals(5, min(new int[]{6, 8, 5, 6}));
	}
	
	@Test public void minTestGeneric() {
		assertEquals((Integer) 5, min(new Integer[]{6, 8, 5, 6}));
	}
	
	@Test public void maxTestInt() {
		assertEquals(8, max(new int[]{6, 8, 5, 6}));
	}
	
	@Test public void maxTestGeneric() {
		assertEquals((Integer) 8, max(new Integer[]{6, 8, 5, 6}));
	}

}
