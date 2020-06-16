package com.data.structures.problems;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class SearchInRotatedSortedArray extends LeetCodeExercise{

	static SearchInRotatedSortedArray s = new SearchInRotatedSortedArray();
	
	public static void main(String[] args) {
		int [] nums = stringToArray("[4,5,6,7,0,1,2]");
		int target = 3;
		System.out.println(s.search(nums, target));
	}

    /**
     * 
     * 
    @intuition
     	It is a binary search heavily base in invariants
     	
     	using the first element as reference to understand if I need to move right or left
     	
     	the trickiest invariant was the last one where I specifically target an edge case where the array is size two [1,3] where the answer can only be at right.
     	if it was in the first position it would be caught by the first invariant.
     
     
    @score
     	Runtime: 1 ms, faster than 24.22% of Java online submissions for Search in Rotated Sorted Array.
		Memory Usage: 39.5 MB, less than 30.42% of Java online submissions for Search in Rotated Sorted Array.
     
    @comments
    	Very tough question
    	insane to find the pattern of this
    	used the usual notebook to find the pattern but was not easy at all
    
    @fail
        1) when first and mid are equals to one
    
    @time	O(log N)
    @space	O(1)
    
    */
    public int search(int[] nums, int target) {
        
        if (nums == null || nums.length == 0)
            return -1;
        
        if (nums.length == 1)
            return nums[0] == target ? 0 : -1;
        
        
        int left = 0, first = nums[0], right = nums.length - 1, mid, curMid;
        
        while (left < right)
        {
            mid = left + (right - left )/2;
            curMid = nums[mid];
            
            if (curMid == target)
                return mid;
            else if (first - curMid > 0)
            {
                
                if (curMid < target && first <= target)
                {
                    right = mid;
                }
                else if (curMid < target && first > target)
                {
                    left = mid + 1;
                }
                else if (curMid > target && first > target)
                {                    
                    right = mid;
                }
            } 
            else if (first - curMid < 0)
            {
                
                if (curMid > target && first <= target)
                {
                    right = mid;    
                }
                else if (curMid > target && first > target)
                {
                    left = mid + 1;
                }
                else if (curMid < target && first < target)
                {
                    left = mid + 1;
                }
                
            }
            else if (first - curMid == 0) // the last and unique possibility, it's only reached if the size of array is two
            {
                left = mid + 1;
            }
            
        }           
        return nums[left] == target ? left : -1;
    }
}

/**
 * Binary Search, in very simple terms
 * 
 * uses the parts of the binary search that are sorted to solve the problem and ease the coding.
 * 
 * very well done
 * 
 * 
 * @author Nelson Costa
 *
 */
class SearchInRotatedSortedArraySolution2 {
	  public int search(int[] nums, int target) {
	    int start = 0, end = nums.length - 1;
	    while (start <= end) {
	      int mid = start + (end - start) / 2;
	      
	      //if equals job done
	      if (nums[mid] == target) 
	    	  return mid;
	      
	      // if middle bigger than start means all left side is sorted unrotated, and we use it as reference
	      else if (nums[mid] >= nums[start]) {
	        if (target >= nums[start] && target < nums[mid]) 
	        	end = mid - 1;
	        else start = mid + 1;
	        
	      }
	      //if middle is smaller than start it means that right is sorted and unrotated, and we use it as reference
	      else {
	        if (target <= nums[end] && target > nums[mid]) 
	        	start = mid + 1;	        
	        else end = mid - 1;
	      }
	    }
	    return -1;
	  }
	}
