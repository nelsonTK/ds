package com.data.structures.problems;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/sort-colors
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class SortColors {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

    /** 
     * 
     * @score
     * 		Runtime: 1 ms, faster than 19.61% of Java online submissions for Sort Colors.
	 *		Memory Usage: 39.8 MB, less than 11.75% of Java online submissions for Sort Colors.
     * 
     * @fail 
     * 		1) Array out of bounds 
     * 		2) forgot to decrease the number of colors
     * 
     * @time
     *  	O(N)
     *  
     * @space
     *  	O(N)
    **/
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0)
            return;
        
        HashMap<Integer, Integer> colorCount = new HashMap<>();
        
        for (int i : nums)
        {
            colorCount.put(i, colorCount.getOrDefault(i, 0) + 1);
        }
        
        int ansIndex = 0;
        for(int i = 0; i < 3; i++)
        {
            while(colorCount.getOrDefault(i, 0) > 0)
            {
                nums[ansIndex++] = i;
                colorCount.put(i, colorCount.get(i) - 1);
            }
        }
    }
}

/*********************
* OTHERS SOLUTIONS
*********************/
/**
 * Tree pointer solution
 * 
 * This problem was created by Dijkstra
 * 
 * @author Nelson Costa
 *
 */
class SortColorsSolution {
	  /*
	  Dutch National Flag problem solution.
	  problem created by Djikstra 
	  */
	  public void sortColors(int[] nums) {
	    // for all idx < i : nums[idx < i] = 0
	    // j is an index of element under consideration
	    int p0 = 0, curr = 0;
	    // for all idx > k : nums[idx > k] = 2
	    int p2 = nums.length - 1;

	    int tmp;
	    while (curr <= p2) {
	      if (nums[curr] == 0) {
	        // swap p0-th and curr-th elements
	        // i++ and j++
	        tmp = nums[p0];
	        nums[p0++] = nums[curr];
	        nums[curr++] = tmp;
	      }
	      else if (nums[curr] == 2) {
	        // swap k-th and curr-th elements
	        // p2--
	        tmp = nums[curr];
	        nums[curr] = nums[p2];
	        nums[p2--] = tmp;
	      }
	      else curr++;
	    }
	  }
	}
