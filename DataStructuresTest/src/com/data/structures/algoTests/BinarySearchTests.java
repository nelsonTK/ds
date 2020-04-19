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

	@Test
	void Test_get_rightMostDigitForDigit01_success() {
		int [] array = {1,1,1,1,3,5,6,7,8,9,10,11};

		assertTrue(BinarySearch.getRightMostIndexForDuplicates(1, array) == 3);

	}

	@Test
	void Test_get_rightMostDigitForDigit02_success() {
		int [] array = {1,1,1,1,3,5,6,8,8,9,10,11};

		assertTrue(BinarySearch.getRightMostIndexForDuplicates(8, array) == 8);

	}
	
	@Test
	void Test_get_leftMostDigitForDigit01_success() {
		int [] array = {1,1,1,1,3,5,6,8,8,9,10,11};

		assertTrue(BinarySearch.getLeftMostIndexForDuplicates(1, array) == 0);
		
		assertTrue(BinarySearch.getLeftMostIndexForDuplicates(8, array) == 7);

	}
	
	@Test
	void Test_get_leftMostDigitForDigit_OfNonDuplicate_success() {
		int [] array = {1,1,1,1,3,5,6,8,8,9,10,11};

		assertTrue(BinarySearch.getLeftMostIndexForDuplicates(3, array) == 4);
		
		assertTrue(BinarySearch.getLeftMostIndexForDuplicates(11, array) == array.length - 1);

	}

}
