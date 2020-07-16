package com.data.structures.problems;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * MEDIUM	  
 * @author Nelson Costa
 *
 */
public class FindFirstAndLastPositionOfElementInSortedArray extends LeetCodeExercise{

	static FindFirstAndLastPositionOfElementInSortedArray f = new FindFirstAndLastPositionOfElementInSortedArray();
	
	public static void main(String[] args) {
		 int [] nums = stringToArray("[5,7,7,8,8,10]");
		 int target = 8;
		 
		 printArray(f.searchRange(nums, target));
	}
	
	/*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * 
	 * 
	 * @intuition
	 * 		No particularlay intrincated intuition. just two binary search performed
	 * 	 
	 * @score
	 * 		Runtime: 0 ms, faster than 100.00% of Java online submissions for Find First and Last Position of Element in Sorted Array.
	 * 		Memory Usage: 43 MB, less than 27.76% of Java online submissions for Find First and Last Position of Element in Sorted Array.
	 * 	 
	 * @fail
	 * 	       1) forgot to put the binary search exactly as I wanted with length -1 var
	 * 	       2) didn't pay attenction that I should put the right element at right and the left element at left
	 * 	       3) lack of attention and the cost of copy and paste, I didnt configured right and left move correctly for the getLeftMost method.
 	 *        
	 * @time 	O(2.Log n)
	 * @space 	O(1)
	 *         
	 * @param nums
	 * @param target
	 * @return
	 */
    public int[] searchRange(int[] nums, int target) {
        //guards
        if (nums == null || nums.length == 0)
            return new int[]{-1, -1};
        
        
        //rightmost
        
        int right = getRightMost(nums, target);
        
        //leftmost
        
        int left = getLeftMost(nums, target);
        
        //answer
        int [] ans = new int[]{left, right}; 
        
        return ans;
    }
    
     private int getRightMost(int[] nums, int target) {
         
         int low = 0, high = nums.length - 1, mid;
         
         while (low <= high)
         {
             mid = low + (high - low)/2;
             
             if (nums[mid]<= target)
             {
                 low = mid + 1;
             }
             else
             {
                 high = mid - 1;
             }
         }         
         
        return high >= 0 && nums[high] == target ? high : -1;
     }
    
    private int getLeftMost(int[] nums, int target) {
    
         int low = 0, high = nums.length - 1, mid;
         
         while (low <= high)
         {
             mid = low + (high - low)/2;
             
             if (nums[mid] >= target)
             {
                 high = mid - 1;
             }
             else
             {
                 low = mid + 1;
             }
         }
                  
        return low <= nums.length - 1 && nums[low] == target ? low : -1;
    }
}


/**
 * A little bit more "optimized" solution where the search left or right binary code is controlled with a variable
 * 
 * and the other optimization is that we dont search a second time if we dont find the element in the first place
 * 
 * @author Nelson Costa
 *
 */
class FindFirstAndLastPositionOfElementInSortedArraySolution1 {
    // returns leftmost (or rightmost) index at which `target` should be
    // inserted in sorted array `nums` via binary search.
    private int extremeInsertionIndex(int[] nums, int target, boolean left) {
        int lo = 0;
        int hi = nums.length;

        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (nums[mid] > target || (left && target == nums[mid])) {
                hi = mid;
            }
            else {
                lo = mid+1;
            }
        }

        return lo;
    }

    public int[] searchRange(int[] nums, int target) {
        int[] targetRange = {-1, -1};

        int leftIdx = extremeInsertionIndex(nums, target, true);

        // assert that `leftIdx` is within the array bounds and that `target`
        // is actually in `nums`.
        if (leftIdx == nums.length || nums[leftIdx] != target) {
            return targetRange;
        }

        targetRange[0] = leftIdx;
        targetRange[1] = extremeInsertionIndex(nums, target, false)-1;

        return targetRange;
    }
}
