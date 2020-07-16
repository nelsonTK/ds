package com.data.structures.ds.tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.data.structures.ds.HashMap1;

@TestMethodOrder(OrderAnnotation.class)
class HashMapTests {

	@Test
	@Order(1)
	void equality_test_false() {

		// Creating an empty Map 
		Map<Integer, String> map1 = new HashMap<Integer, String>(); 
		HashMap1<Integer, String> map2 = new HashMap1<Integer, String>(); 

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
	@Order(2)
	void equality_test_true() {

		// Creating an empty Map 
		Map<Integer, String> map1 = new HashMap<Integer, String>(); 
		HashMap1<Integer, String> map2 = new HashMap1<Integer, String>(); 

		// Mapping string values to int keys for map1 
		map1.put(10, "Geeks"); 
		map1.put(15, "4"); 
		map1.put(20, "Geeks"); 
		map1.put(25, "Welcomes"); 
		map1.put(30, "You"); 

		map1.put(100, "Geeks0"); 
		map1.put(150, "40"); 
		map1.put(200, "Geeks0"); 
		map1.put(250, "Welcomes0"); 
		map1.put(300, "You0"); 
		
		// Mapping string values to int keys for map2 
		map2.put(10, "Geeks"); 
		map2.put(15, "4"); 
		map2.put(20, "Geeks"); 
		map2.put(25, "Welcomes"); 
		map2.put(30, "You"); 

		map2.put(100, "Geeks0"); 
		map2.put(150, "40"); 
		map2.put(200, "Geeks0"); 
		map2.put(250, "Welcomes0"); 
		map2.put(300, "You0"); 
		// Displaying the map 1 
		System.out.println("First Map: " + map1); 

		// Displaying the map 2 
		System.out.println("Second Map: " + map2); 

		// Displaying the equality 
		for (int i : map1.keySet())
		{
			System.out.println("map1: " + map1.get(i) + "| map2: "  + map2.get(i) );

			assertTrue(map1.get(i).equals(map2.get(i)));	
		}
		
	} 	

}
