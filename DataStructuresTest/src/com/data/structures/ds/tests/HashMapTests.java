package com.data.structures.ds.tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class HashMapTests {

	@Test
	void equality_test_false() {

		// Creating an empty Map 
		Map<Integer, String> map1 = new HashMap<Integer, String>(); 
		Map<Integer, String> map2 = new HashMap<Integer, String>(); 

		// Mapping string values to int keys for map1 
		map1.put(10, "Geeks"); 
		map1.put(15, "4"); 
		map1.put(20, "Geeks"); 
		map1.put(25, "Welcomes"); 
		map1.put(30, "You"); 

		// Mapping string values to int keys for map2 
		map2.put(10, "Geeks"); 
		map2.put(15, "4"); 
		map2.put(20, "Geek"); 
		map2.put(25, "Welcomes"); 
		map2.put(30, "You"); 

		// Displaying the map 1 
		System.out.println("First Map: " + map1); 

		// Displaying the map 2 
		System.out.println("Second Map: " + map2); 

		// Displaying the equality 
		assertFalse(map1.equals(map2));
	} 	
	@Test
	void equality_test_true() {

		// Creating an empty Map 
		Map<Integer, String> map1 = new HashMap<Integer, String>(); 
		Map<Integer, String> map2 = new HashMap<Integer, String>(); 

		// Mapping string values to int keys for map1 
		map1.put(10, "Geeks"); 
		map1.put(15, "4"); 
		map1.put(20, "Geeks"); 
		map1.put(25, "Welcomes"); 
		map1.put(30, "You"); 

		// Mapping string values to int keys for map2 
		map2.put(10, "Geeks"); 
		map2.put(15, "4"); 
		map2.put(20, "Geeks"); 
		map2.put(25, "Welcomes"); 
		map2.put(30, "You"); 

		// Displaying the map 1 
		System.out.println("First Map: " + map1); 

		// Displaying the map 2 
		System.out.println("Second Map: " + map2); 

		// Displaying the equality 
		assertTrue(map1.equals(map2));
	} 	

}
