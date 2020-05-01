package com.data.structures.problems;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class FindMinimumInRotatedSortedArray extends LeetCodeExercise{

	public static void main(String[] args) {

		FindMinimumInRotatedSortedArray f = new FindMinimumInRotatedSortedArray();
		int [] a = stringToArray(" [4,5,6,7,0,1,2]");
		a = stringToArray(" [3,4,5,1,2] ");
		System.out.println(f.findMin0(a));
		System.out.println(f.findMin(a));
	}
	
	
	/**
	 * @intuition
	 * 		just a linear search
	 * 		I could not come up with a smarter solution than this one
	 * 		The other solution I came up was a recursive solution with logaritmic time complexity or 2^N
	 * 
	 * @score
	 * 		Runtime: 0 ms, faster than 100.00% of Java online submissions for Find Minimum in Rotated Sorted Array.
			Memory Usage: 39.1 MB, less than 60.23% of Java online submissions for Find Minimum in Rotated Sorted Array.
	 * 		
	 * 		Despite the score I don't like this solution
	 * 
	 * @optimizations
	 * 		I believe I could do binary search to eliminate one of the halves of the array and then
	 * 
	 * @time  O(N)
	 * @space O(1)
	 * @bcr   O(Logn)
	 * 
	 * @param a
	 * @return
	 */
    public int findMin0(int[] a) {
        if(a == null || a.length == 0)
            throw new IllegalArgumentException();
        
        
        int min = Integer.MAX_VALUE;        	
        
        for (int i = 0; i < a.length; i++)
        {
            min = Math.min(a[i], min);
        }
        
        return min;
    }
    
    
    /**
     * [WRONG]
     * 		Because of some possibilities not took into account
     * 
     * @intuition
     * 		second implementation, this time I eliminated the first half with binary search which is 
     * 		faster but does not improves the time complexity nor the space complexity
     * 
     * @param a
     * @return
     */
    public int findMin1(int [] a) {

        if(a == null || a.length == 0)
            throw new IllegalArgumentException();
        
        if (a.length == 1)
        	return a[0];
        
        //find element or Eliminate first half (binary search)
        int low = 0, high = a.length - 1, mid;
        while (low <= high)
        {
        	mid = low + (high - low)/2;
        	
        	if (getPrevious(a, mid - 1) > a[mid])
        		return a[mid];
        	else {
        		high = mid - 1;
        	}
        }        
        
        //find element linearly
        low = 0; 
        high = a.length - 1;
        int min = Integer.MAX_VALUE;
        int secondStart = low + (high - low)/2 + 1;
        
        for (int i = secondStart; i < a.length; i++) {
			min = Math.min(a[i], min);
		}
        
        return min;
        
    }
    
    /**
     * gets previous and includes rotation
     * 
     * @param a
     * @param index
     * @return
     */
    private int getPrevious(int [] a, int index) {    	
    	return (index < 0) ?  a[a.length - 1] : a[index];
    }

    /**
     * 
     * @intuition
     * 		binary search solution goes left or right depending other or not its value is smaller or bigger than the first value
     * 		that's how we deal with rotation
     * 
     * @comments
     * 
     * @param a
     * @return
     */

    public int findMin(int [] a) {
    	
        if(a == null || a.length == 0)
            throw new IllegalArgumentException();
        
        if (a.length == 1)
        	return a[0];
        
    	int low = 0, high = a.length - 1, mid;
    	
        //resolve when first element is the smallest
        if (a[high] > a[0]) {
            return a[0];
        }
        
    	while (low <= high)
    	{
    		mid = low + (high - low ) / 2;
    		
            //order of the conditions matters
            if (a[mid] > a [mid + 1])
                return a[mid + 1];
            
            else if (a[mid] < a[mid - 1])
                return a[mid];
            
    		else if(a[mid] > a[0])
    			low = mid + 1;
            
            else {
                high = mid - 1;
            }
    	}
    	
    	return -1;
    }
    
}
