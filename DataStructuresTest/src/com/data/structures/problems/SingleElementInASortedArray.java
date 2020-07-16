package com.data.structures.problems;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/single-element-in-a-sorted-array/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class SingleElementInASortedArray extends LeetCodeExercise{

	static SingleElementInASortedArray s = new SingleElementInASortedArray();

	public static void main(String[] args) {

		int [] a = stringToArray("[1,1,2]");
		a = stringToArray("[1,1,2,3,3,4,4,8,8]");
		System.out.println(s.singleNonDuplicate(a));
	}

	/*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 *
	 * @intuition
	   		I found out a pattern that indicates where to search the single element
	   		and it was related with the size of the array(or subarray).
	   		
	   		if the size of the search space divided by 2 was odd
	   		
	   		we find in the middle
	   		 	abb
	   		 	move left
	   		 	aab
	   		 	move right
	   		 	
	   		 if it is even
	   		 	abb
	   		 	move right
	   		 	aab
	   		 	move left
	   		 	
	   		 The tricky thing is that we have to "Eliminate" the repeated elements, by jumping them
	   		 that's why on even size we jump 2 elements instead of one.
	   		 
	   		 Very very tricky question.
	 *
     * @fail
        1) the invariant consequences were wrong, they were switched
        2) the same error but I had a bad signal - 1 instead of + 1.
        3) forgot the protections
     * 
	 * @score
	  		Runtime: 0 ms, faster than 100.00% of Java online submissions for Single Element in a Sorted Array.
			Memory Usage: 40 MB, less than 8.00% of Java online submissions for Single Element in a Sorted Array.
	 *
	 * @time 	O(log N)
	 * @space	O(1)
	 * 
	 * @param a
	 * @return
	 */
	public int singleNonDuplicate(int[] a) {

		if (a == null || a.length == 0)
			return -1;

		if (a.length == 1)
			return a[0];


		int left = 0, right = a.length - 1, mid, hint, size;

		while (left <= right)
		{
			mid = left + (right - left)/2;
			size = (right - left + 1);
			hint = (size) / 2; //It's a pattern I found out
			if (hint % 2 == 0)
			{
				if (size == 1 || a[mid] != a[mid + 1] && a[mid] != a[mid - 1])
					return a[mid];

				else if (a[mid] == a[mid + 1])
				{
					left = mid + 2;
				}
				else if  (a[mid] != a[mid + 1])
				{
					right = mid - 2;
				}
			}
			else
			{
				if (size == 1 || a[mid] != a[mid + 1] && a[mid] != a[mid - 1])
					return a[mid];
				else if (a[mid] == a[mid + 1])
				{       
					right = mid - 1;
				}
				else if (a[mid] != a[mid + 1])
				{
					left = mid + 1;
				}
			}            
		}
		
		return -1;

	}
}


/*********************
* OTHERS SOLUTIONS
*********************/
/**
 * Very elegant solution
 * 	Only searches on even indexes, and relies on the pattern of what appens to the even indexes before and after the single element
 * 	Mid must always be positive index
 * 
 * 
 * 
 * @time 	O(Log N/2)
 * @space 	O(1)
 * @author Nelson Costa
 *
 */
class SingleElementInASortedArraySolution2 {
    public int singleNonDuplicate(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (mid % 2 == 1) mid--;
            if (nums[mid] == nums[mid + 1]) {
                lo = mid + 2;
            } else {
                hi = mid;
            }
        }
        return nums[lo];
    }
}

/**
 * This solution uses the same principle than mine.
 * but is executed slightly different, they check for the halves sizes, and they know that the single element is in the odd size part
 * 
 * The beauty of this solution is that it would work even on not so sorted arrays (just like mine, just found out)
 * 
 * 
 * @time O(log n)
 * @space O(1)
 * 
 * @author Nelson Costa
 *
 */
class SingleElementInASortedArraySolution1 {
    public int singleNonDuplicate(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            boolean halvesAreEven = (hi - mid) % 2 == 0;
            if (nums[mid + 1] == nums[mid]) {
                if (halvesAreEven) {
                    lo = mid + 2;
                } else {
                    hi = mid - 1;
                }
            } else if (nums[mid - 1] == nums[mid]) {
                if (halvesAreEven) {
                    hi = mid - 2;
                } else {
                    lo = mid + 1;
                }
            } else {
                return nums[mid];
            }
        }
        return nums[lo];
    }
}
