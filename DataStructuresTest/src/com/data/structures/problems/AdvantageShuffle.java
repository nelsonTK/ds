package com.data.structures.problems;

import java.util.TreeMap;

public class AdvantageShuffle {
	/**
	 * @intuition
	 * 		The gist of this solution is to find the element imediatly bigger thant the b element
	 * 		And for that I used a TreeMap I add all element to a tree map and then I search for the elements
	 * 		Immediatly bigger than the one existing in b if not found I mark the position to visit later and fill in with any number.
	 * 
	 * @score
	 * 		Runtime: 72 ms, faster than 30.67% of Java online submissions for Advantage Shuffle.
	 * 		Memory Usage: 40.5 MB, less than 95.51% of Java online submissions for Advantage Shuffle.
	 * 
	 * @fail
	 * 		Failed because of the ceilling functions is greater or equal and not only greater
	 * 
	 * @time
	 * 		O(NLogN)
	 * 
	 * @space
	 * 		O(N)
	 * 
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	 public int[] advantageCount(int[] a, int[] b) {
	        
	        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
	        int [] ans = new int [b.length];
	        
	        //map each number to its count in the a array
	        for (int n : a)
	        {
	            map.put(n , map.getOrDefault(n, 0) +1);
	        }
	        
	        //for each element in the array add the first bigger
	        int i = 0;
	        for (int n : b)
	        {
	            Integer bigger = map.ceilingKey(n + 1);
	            
	            if(bigger == null)
	            {
	                ans[i] = -1;
	            }
	            else
	            {
	                ans[i] = bigger;
	                decreaseKey(bigger, map);
	            }
	            i++;
	        }
	        
	        //solve negative
	        for (int j = 0; j < ans.length; j++)
	        {
	            if(ans[j] == -1)
	            {
	                ans[j] = map.firstKey();
	                int key = map.firstKey();
	                decreaseKey(key, map);
	            }
	        }
	        
	        return ans;
	    }
	    
	    private void decreaseKey(Integer key, TreeMap<Integer, Integer> map){

	        if (map.get(key) == 1)
	            map.remove(key);
	        else
	            map.put(key, map.get(key) - 1);
	    }
}
