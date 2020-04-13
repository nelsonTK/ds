package com.data.structures.algoTests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.data.structures.algos.BinarySearch;

class BinarySearchTests {

	@Test
	void Test_search_success() {
		int [] array = {1,2,3,4,5,6,7,8,9,10,11};

		for (int i : array) {
			System.out.println("expected: " + i + " returned: " + BinarySearch.search(i, array));
			assertTrue(BinarySearch.search(i, array) == i);

		}
	}


	@Test
	void Test_search_fail() {
		int [] array = {1,2,3,4,5,6,7,8,9,10,11};

		assertFalse(BinarySearch.search(12, array) == 12);

	}

}
