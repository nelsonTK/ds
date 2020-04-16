package com.data.structures.ds.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.data.structures.ds.LRUCache;

/**
 * The tests can still be much improved
 * A lot of redundat code which can be improved to a for loop
 * basic functionalities were tested
 * @author Nelson Costa
 *
 */
class LRUCacheTests {

	@Test
	void test_addNodesBeforeCapacity_Success() {
		System.out.println("test_addNodesBeforeCapacity_Success");

		int capacity = 5;
		LRUCache<Integer, String> cache = new LRUCache<>(capacity);
		String personName;
		String prefix = "PERSON";
		String leastFrequentlyUsed = "";
		
		for (int i = 0; i < capacity; i++) {
			personName = prefix + i;
			
			if(i == 0)
			{
				leastFrequentlyUsed = personName;
			}
			
			cache.put(i, personName);
			assertTrue(cache.getMRU().equals(personName));
			assertTrue(cache.getLRU().equals(leastFrequentlyUsed));
			System.out.println();
		}
	}



	@Test
	void test_addNodesAfterCapacity_Success() {		
		System.out.println("test_addNodesAfterCapacity_Success");

		int capacity = 5;
		LRUCache<Integer, String> cache = new LRUCache<>(capacity);
		String personName;
		String prefix = "PERSON";
		String leastFrequentlyUsed = "";
		
		//beforeCapacity
		for (int i = 0; i < capacity; i++) {
			personName = prefix + i;
			
			if(i == 0)
			{
				leastFrequentlyUsed = personName;
			}
			
			cache.put(i, personName);
			assertTrue(cache.getMRU().equals(personName));
			assertTrue(cache.getLRU().equals(leastFrequentlyUsed));
			System.out.println();
		}
		
		//after capacity
		for (int i = capacity; i < 8; i++) {
			personName = prefix + i;
			
			cache.put(i, personName);
			assertTrue(cache.getMRU().equals(personName));
			assertTrue(cache.getLRU().equals(prefix + (Math.abs(i + 1 - capacity))));
			System.out.println();
		}
	}

	@Test
	void test_addNodesBeforeCapacity_WithChangesDuringTheProcess_Success() {		
		System.out.println("test_addNodesBeforeCapacity_WithChangesDuringTheProcess_Success");

		int capacity = 5;
		LRUCache<Integer, String> cache = new LRUCache<>(capacity);

		String Test = "PERSON1";
		cache.put(1, Test);
		assertTrue(cache.getMRU().equals(Test));
		assertTrue(cache.getLRU().equals("PERSON1"));
		System.out.println();

		Test = "PERSON2";
		cache.put(2, Test);
		assertTrue(cache.getMRU().equals(Test));
		assertTrue(cache.getLRU().equals("PERSON1"));
		System.out.println();


		Test = "PERSON2.1";
		cache.put(2, Test);
		assertTrue(cache.getMRU().equals(Test));
		assertTrue(cache.getLRU().equals("PERSON1"));
		System.out.println();


		Test = "PERSON3";
		cache.put(3, Test);
		assertTrue(cache.getMRU().equals(Test));
		assertTrue(cache.getLRU().equals("PERSON1"));
		System.out.println();



		Test = "PERSON2.1";
		cache.put(1, Test);
		assertTrue(cache.getMRU().equals(Test));
		assertTrue(cache.getLRU().equals("PERSON2.1"));
		System.out.println();

		Test = "PERSON3.1";
		cache.put(3, Test);
		assertTrue(cache.getMRU().equals(Test));
		assertTrue(cache.getLRU().equals("PERSON2.1"));
		System.out.println();

		Test = "PERSON1.2";
		cache.put(1, Test);
		assertTrue(cache.getMRU().equals(Test));
		assertTrue(cache.getLRU().equals("PERSON2.1"));
		System.out.println();


		Test = "PERSON4";
		cache.put(4, Test);
		assertTrue(cache.getMRU().equals(Test));
		assertTrue(cache.getLRU().equals("PERSON2.1"));
		System.out.println();

		Test = "PERSON5";
		cache.put(5, Test);
		assertTrue(cache.getMRU().equals(Test));
		assertTrue(cache.getLRU().equals("PERSON2.1"));
		System.out.println();

		assertTrue(cache.getUsageCapacity() == capacity);
		assertTrue(cache.getUsageSize() == capacity);
	}

	@Test
	void test_addNodesAfterCapacity_WithChangesDuringTheProcess_Success() {		
		System.out.println("test_addNodesAfterCapacity_WithChangesDuringTheProcess_Success");


		int capacity = 5;
		LRUCache<Integer, String> cache = new LRUCache<>(capacity);

		String Test = "PERSON1";
		cache.put(1, Test);
		assertTrue(cache.getMRU().equals(Test));
		assertTrue(cache.getLRU().equals("PERSON1"));
		System.out.println();

		Test = "PERSON2";
		cache.put(2, Test);
		assertTrue(cache.getMRU().equals(Test));
		assertTrue(cache.getLRU().equals("PERSON1"));
		System.out.println();


		Test = "PERSON2.1";
		cache.put(2, Test);
		assertTrue(cache.getMRU().equals(Test));
		assertTrue(cache.getLRU().equals("PERSON1"));
		System.out.println();


		Test = "PERSON3";
		cache.put(3, Test);
		assertTrue(cache.getMRU().equals(Test));
		assertTrue(cache.getLRU().equals("PERSON1"));
		System.out.println();



		Test = "PERSON2.1";
		cache.put(1, Test);
		assertTrue(cache.getMRU().equals(Test));
		assertTrue(cache.getLRU().equals("PERSON2.1"));
		System.out.println();

		Test = "PERSON3.1";
		cache.put(3, Test);
		assertTrue(cache.getMRU().equals(Test));
		assertTrue(cache.getLRU().equals("PERSON2.1"));
		System.out.println();

		Test = "PERSON1.2";
		cache.put(1, Test);
		assertTrue(cache.getMRU().equals(Test));
		assertTrue(cache.getLRU().equals("PERSON2.1"));
		System.out.println();


		Test = "PERSON4";
		cache.put(4, Test);
		assertTrue(cache.getMRU().equals(Test));
		assertTrue(cache.getLRU().equals("PERSON2.1"));
		System.out.println();

		Test = "PERSON5";
		cache.put(5, Test);
		assertTrue(cache.getMRU().equals(Test));
		assertTrue(cache.getLRU().equals("PERSON2.1"));
		System.out.println();

		Test = "PERSON6";
		cache.put(6, Test);
		assertTrue(cache.getMRU().equals(Test));
		assertTrue(cache.getLRU().equals("PERSON3.1"));
		System.out.println();

		Test = "PERSON7";
		cache.put(7, Test);
		assertTrue(cache.getMRU().equals(Test));
		assertTrue(cache.getLRU().equals("PERSON1.2"));
		System.out.println();


		Test = "PERSON2.1";
		cache.put(2, Test);
		assertTrue(cache.getMRU().equals(Test));
		assertTrue(cache.getLRU().equals("PERSON4"));
		System.out.println();

		assertTrue(cache.getUsageCapacity() == capacity);
		assertTrue(cache.getUsageSize() == capacity);
		assertTrue(cache.getMapSize() == 7);
	}


	@Test
	void test_getNodeAfterEviction_Success() {	

		int capacity = 2;
		LRUCache<Integer, String> cache = new LRUCache<>(capacity);
		String personName;
		String prefix = "PERSON";
		String leastFrequentlyUsed = "";
		String testCase = prefix + capacity;
		
		for (int i = 0; i < capacity; i++) {
			personName = prefix + i;
			
			if(i == 0)
			{
				leastFrequentlyUsed = personName;
			}
			
			cache.put(i, personName);
			assertTrue(cache.getMRU().equals(personName));
			assertTrue(cache.getLRU().equals(leastFrequentlyUsed));
			System.out.println();
		}
		
		cache.put(capacity, testCase);
		assertTrue(cache.getMRU().equals(testCase));
		assertTrue(cache.getLRU().equals(prefix + 1));
		
		
		cache.get(0); //first Input
		assertTrue(cache.getMRU().equals(prefix + 0));
		assertTrue(cache.getLRU().equals(prefix + 2)); //1 was evicted
		
	}
}
