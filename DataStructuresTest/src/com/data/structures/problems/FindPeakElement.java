package com.data.structures.problems;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/find-peak-element/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class FindPeakElement {

	public static void main(String[] args) {
//		int [] a = {1,2,3,1};
		int [] a = {1,2,1,3,5,6,4};
		FindPeakElement f = new FindPeakElement();
		
		System.out.println(Arrays.toString(a));
		System.out.println(f.findPeakElement(a));
		
	}
 
	/**
	 * @intuition
	 * 		check for the elements at right and left and compare if the middle is the greatest
	 * 		outofbounds returns - infinity
	 * 		 
	 * @score
	 * 		Runtime: 0 ms, faster than 100.00% of Java online submissions for Find Peak Element.
			Memory Usage: 39.2 MB, less than 92.54% of Java online submissions for Find Peak Element.
	 * 
	 * @fail 
	 * 		1) when only one element I should return index and not element. I did it wrong.
	 * 		2) failed again because of the boundaries [-2147483648,-2147483647]
	 * 
	 * @alternatives
	 * 		Linear time and check right and left boundary
	 * 
	 * @time  O(log n )
	 * @space O(1)
	 * @bcr   O(log n)
	 * 
	 * @param a
	 * @return
	 */
    public int findPeakElement(int[] a) {
        
    	if (a == null || a.length == 0)
    		throw new IllegalArgumentException();
    	
    	if (a.length == 1) {
    		return 0;
    	}
    	
    	int low = 0;
    	int high = a.length - 1;
    	int mid;
    	
    	while (low <= high)
    	{
    		mid = low + (high - low)/2;
    		
    		if (getBoundary(a, mid - 1) < a[mid] &&
    			a[mid] < getBoundary(a, mid + 1))
    		{
    			low = mid + 1;
    		}
    		else if (getBoundary(a, mid - 1) < a[mid] && 
    				 getBoundary(a, mid + 1) < a[mid]) 
    		{
    			return mid;
    		}
    		else
    		{
    			high = mid - 1;
    		}
    	}
    	
    	return -1;
    }
    
    
    public long getBoundary(int [] a, long index) {
    	
    	return (index < 0 || index >= a.length) ? Long.MIN_VALUE : a[(int)index];
    }
}

/**
 * Top solution goes around the issue of overflowing
 * and uses much less lines of code than my solution
 * 
 * @author Nelson Costa
 *
 */
class FindPeakElementSolution1 {
    public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] > nums[mid + 1])
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }
}

/**
 * it is a recursive implementation of binary search
 * just for fun
 * 
 * @author Nelson Costa
 *
 */
class FindPeakElementSolution2 {
    public int findPeakElement(int[] nums) {
        return search(nums, 0, nums.length - 1);
    }
    public int search(int[] nums, int l, int r) {
        if (l == r)
            return l;
        int mid = (l + r) / 2;
        if (nums[mid] > nums[mid + 1])
            return search(nums, l, mid);
        return search(nums, mid + 1, r);
    }
}