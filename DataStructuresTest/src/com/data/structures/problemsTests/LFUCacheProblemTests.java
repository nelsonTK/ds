package com.data.structures.problemsTests;

import org.junit.jupiter.api.Test;

import com.data.structures.problems.LRUCache;

class LFUCacheProblemTests {

	/**
	 * LeetCode Failed Scenario
	 */
	@Test
	void test_NoExceptions_Success() {
		int cap = 10;

		LRUCache cache = new LRUCache(cap);

		cache.put(10,13);
		cache.put(3,17);
		cache.put(6,11);
		cache.put(10,5);
		cache.put(9,10);
		cache.get(13);
		cache.put(2,19);
		cache.get(2);
		cache.get(3); 
		cache.put(5,25);
		cache.get(8);
		cache.put(9,22);
		cache.put(5,5);
		cache.put(1,30);
		cache.get(11);
		cache.put(9,12);
		cache.get(7);
		cache.get(5);
		cache.get(8);
		cache.get(9);
		cache.put(4,30);
		cache.put(9,3);
		cache.get(9);
		cache.get(10);
		cache.get(10);
		cache.put(6,14);
		cache.put(3,1);
		cache.get(3);
		cache.put(10,11);
		cache.get(8);
		cache.put(2,14);	

	}

}
