package com.data.structures.problems;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/first-unique-number/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class FirstUniqueNumber {

	public static void main(String[] args) {

	}

	 /*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * @intuition
	 * 		The intuition was to use hashmap and reversed hashmap in a treeMap which will have my first index.
	 * 
	 * 		when I 'm going to add a duplicate place I remove it from the hashmap, from the treemap and I add it to the duplicate set.
	 * 
	 * 		So it means that we cannot had that number again.
	 * 	
	 * @score
	 * 		Runtime: 166 ms, faster than 46.00% of Java online submissions for First Unique Number.
	 *		Memory Usage: 86.6 MB, less than 62.20% of Java online submissions for First Unique Number.
	 * 
	 * @time
	 * 		add 
	 * 			O(logn)
	 * 
	 * 		show
	 * 			O(1)
	 * 
	 * 		constructor
	 * 			O(nlogn)
	 * 
	 * @space
	 * 		O(N)
	 * 
	 * @author Nelson Costa
	 *
	 */
	class FirstUnique {

	    TreeMap<Integer, Integer> indexVal;
	    HashMap<Integer, Integer> valIndex;
	    Set<Integer> duplicated;
	    int internalIndex;
	    
	    //O(NlogN)
	    public FirstUnique(int[] nums) {
	        
	        indexVal = new TreeMap<>();
	        valIndex = new HashMap<>();
	        duplicated = new HashSet<>();
	        internalIndex = 0;
	        
	        int cur;
	        for (int i = 0; i < nums.length; i++)
	        {
	            cur = nums[i];
	            addNum(i, cur);
	        }   
	    }
	    
	    //O(1)
	    public int showFirstUnique() {
	        return indexVal.size() > 0? indexVal.get(indexVal.firstKey()) : -1;
	    }
	    
	    //O(logN)
	    public void add(int value) {
	        addNum(internalIndex, value);
	    }
	    
	    //O(logN)
	    private void addNum(int i, int cur){
	        
	        if (!valIndex.containsKey(cur) && !duplicated.contains(cur))
	        {
	            valIndex.put(cur, i);
	            indexVal.put(i, cur);
	        }
	        else if (!duplicated.contains(cur))
	        {
	            indexVal.remove(valIndex.get(cur));
	            valIndex.remove(cur);
	            duplicated.add(cur);
	        }
	            
	        internalIndex++;

	    }
	}
}




/*********************
* OTHERS SOLUTIONS
*********************/