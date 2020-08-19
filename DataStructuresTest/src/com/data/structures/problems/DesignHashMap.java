package com.data.structures.problems;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/design-hashmap
 * EASY
 * @author Nelson Costa
 *
 */
public class DesignHashMap {
	
	/**
	 * 	@intuition
	 * 		Super Lazy implementation given the level of difficulty was easy
	 * 
	 * 	@score
			Runtime: 88 ms, faster than 15.67% of Java online submissions for Design HashMap.
			Memory Usage: 91.2 MB, less than 5.05% of Java online submissions for Design HashMap.
		
		@comments
			I could have done better If added more optimizations like increase capacity has the array grows.
		
		@time
			O(1)
			
		@space
			O(1)
			
	 * @author Nelson Costa
	 *
	 */
	class MyHashMap {

	    int [] hash;
	    
	    /** Initialize your data structure here. */
	    public MyHashMap() {
	        hash = new int [1000001];
	        Arrays.fill(hash, -1);
	    }
	    
	    /** value will always be non-negative. */
	    public void put(int key, int value) {
	        hash[key] = value;
	    }
	    
	    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
	    public int get(int key) {
	        return hash[key];
	    }
	    
	    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
	    public void remove(int key) {
	        hash[key] = -1;
	    }
	    
	}
}
