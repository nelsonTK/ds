package com.data.structures.problems;

import java.util.TreeMap;

public class AdvantageShuffle {
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
