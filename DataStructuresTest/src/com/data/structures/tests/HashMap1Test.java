package com.data.structures.tests;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.junit.jupiter.api.Test;

import com.data.structures.ds.HashMap1;
import com.data.structures.ds.Trie1;

class HashMap1Test {

	/**
	 * Validate that the output of java hashmap and my hashmap is equal for a number of key-value pairs 
	 */
	@Test
	void test_put_and_get_a_few_keys() {
		
		HashMap<String,String> control = new HashMap<String,String>();
		HashMap1<String,String> target = new HashMap1<String,String>();
		ArrayList<String> keys = new ArrayList<String>();
		generateData(keys, control, target);
		String sControl = new String();
		String sTarget = new String();
		
		for (String x : keys) {
			
			sControl = control.get(x);
			sTarget = target.get(x);
			assertEquals(sControl.equals(sTarget), true);
			//System.out.println("i: " + i + " " + sControl + " | " + sTarget + " | " + x);
		}
		
		System.out.println("size: " + target.getSize());
		System.out.println("capacity: " + target.getCapacity());
	}
	
	/**
	 * Generates data for the test
	 * @param list
	 * @param control
	 * @param target
	 */
	private void generateData(ArrayList<String> list, HashMap<String, String> control, HashMap1<String, String> target) {
		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();
		String generatedString = "";
		for (int j = 0; j < 12000; j++) {
			generatedString = random.ints(leftLimit, rightLimit + 1)
					.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
					.limit(targetStringLength)
					.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
					.toString();
			
			list.add(generatedString);
			control.put(generatedString, generatedString);
			target.put(generatedString, generatedString);
			//debugGenerateData(target, generatedString);
		}
	}

	@Test
	void test_hashmap_hashcode_consistency_after_object_change() {
		
		Trie1 t = new Trie1();

		System.out.println(t.hashCode());
		t.addWord("peterish");
		
//		HashMap<Trie1, String> consistencyCheck = new HashMap<Trie1, String>();
//		consistencyCheck.put(t,"c1");
		System.out.println(t.hashCode());
		
		t = new Trie1();
		System.out.println(t.hashCode());

		
		
	}
}
