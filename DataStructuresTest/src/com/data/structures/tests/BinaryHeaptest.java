package com.data.structures.tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Random;

import org.junit.jupiter.api.Test;

import com.data.structures.ds.MaxHeap;

class BinaryHeaptest {

	@Test
	void test_add_elements_all_together() {

		MaxHeap h = new MaxHeap();

		ArrayList<Integer> main = generateData(100);	

		fillBinaryHeap(h, main);

		for (Integer val: main) {
			assertTrue(h.searchData(val));
		}
	}

	@Test
	void test_add_elements_in_two_times() {

		MaxHeap h = new MaxHeap();

		ArrayList<Integer> main = new ArrayList<Integer>();

		ArrayList<Integer> tmp = generateData(40);
		main.addAll(tmp);

		fillBinaryHeap(h, tmp);

		tmp = generateData(40);
		main.addAll(tmp);

		fillBinaryHeap(h, tmp);		

		for (Integer val: main) {
			assertTrue(h.searchData(val));
		}
	}

	@Test
	void test_delete_element() {
		MaxHeap h = new MaxHeap();

		ArrayList<Integer> main = generateData(1000);

		fillBinaryHeap(h, main);

		int elementVal = main.get(main.size()/2);
		int elementIndex = h.getIndexForData(elementVal);
		int countBeforeDelete = h.countRepeatedValues(elementVal);
		
		h.delete(elementIndex);
		
		int countAfterDelete = h.countRepeatedValues(elementVal);
		assertTrue(countBeforeDelete == countAfterDelete + 1);
	}

	@Test
	void test_delete_and_add_same_element() {
		MaxHeap h = new MaxHeap();

		ArrayList<Integer> main = generateData(1000);

		fillBinaryHeap(h, main);

		int elementVal = main.get(main.size()/2);
		int elementIndex = h.getIndexForData(elementVal);


		int countBeforeDelete = h.countRepeatedValues(elementVal);
		
		h.delete(elementIndex);
		
		int countAfterDelete = h.countRepeatedValues(elementVal);
		
		assertTrue(countBeforeDelete == countAfterDelete + 1); //It may have repeated values
		
		h.add(elementVal);
		
		assertTrue(h.searchData(elementVal));

	}

	/**
	 * Test update index
	 */
	@Test
	public void test_update_index_and_heapify() {
		MaxHeap m = new MaxHeap();
		m.add(1);
		m.add(2);
		m.add(3);
		m.updateValAt(2, 4);
		assertTrue(m.searchData(4));
	}
	
	
	/**
	 * Fill the binary heap with given list
	 * @param h
	 * @param data
	 */
	private void fillBinaryHeap(MaxHeap h, ArrayList<Integer> data) {
		for (Integer val : data) {
			h.add(val);
		}
	}

	/**
	 * Generate List of integers
	 * @param size
	 * @return
	 */
	private ArrayList<Integer> generateData(int size){
		ArrayList<Integer> list = new ArrayList<Integer>();
		Random r = new Random();

		for (int i = 0; i < size; i++) {
			list.add(Math.abs(i * Math.abs(r.nextInt(15)))); //because it overflows
		}

		return list;
	}

}
