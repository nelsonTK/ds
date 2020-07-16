package com.data.structures.problems;

/**
 * https://leetcode.com/problems/first-missing-positive/
 * HARD
 * @author Nelson Costa
 *
 */
public class FirstMissingPositive_Take2 {

	public static void main(String[] args) {

	}

    
    /**
     *
     * @intuition
     *    	The intuition here was based on some principles that require a good analysis to the problem.
     *    	
     *    	This are the premises:
     *    	
     *    	if all numbers are present, than you return n + 1
     *    	
     *    	if not all numbers are present you to traverse the collection only once you'll need to use each number as index.
     *    	
     *   	and mark that index as filled. 
     *    	
     *    	so I broke this problem in tree steps.
     *    	
     *   	1) remove from equation elements that are out of range
     *    	
     *    	2) mark elements that are inside range
     *    	
     *    	3) traverse the array searching for the first unmarked position, which will be -2 or positive number.
     *    	
     *    	so I mark with -1 elements that are filled
     *    	I mark with -2 elements that are out of range
     *    	if an index is not filled it will stay with the original array's value or the out of range placeholder (-2)
     *    	
     *    	[1,5,2,3]
     *    	[1,-2, 2,3]
     *    	[-1,-1,-1,3]
     *    			  ^
     *    			  3 + 1 is our answer.
     *    	
     *    
     * @score
     *    	Runtime: 1 ms, faster than 45.26% of Java online submissions for First Missing Positive.
     *		Memory Usage: 38.8 MB, less than 19.58% of Java online submissions for First Missing Positive.
     *		
     * @fail
     *        1) the logic to return the index was wrong, I should return at -2 or above zero, that would be the first, but looks like there is another problems.
     *        2) I had a bad condition in the if statement
     *    
     *    
     * @time   O(N) => 2N
     * @space  O(1)
     *    
    **/
    public int firstMissingPositive(int[] nums) {
    	
        //edge cases        
        if(nums == null || nums.length == 0)
            return 1;
        
        int n = nums.length;
        
        //Invalidate bad elements
            //bad elements will be -2
        
        for (int i = 0; i < n; i++)
        {
            if (nums[i] < 1 || nums[i] > n)
                nums[i] = -2;
        }
        
        //Invalidate Good Elements
            //good elements will be -1
        
        int jumpIndex;
        int jumpIndexVal;
        for (int i = 0; i < n; i++)
        {
            if (nums[i] > 0){
                jumpIndex = nums[i] - 1;
                while (jumpIndex >= 0 && jumpIndex < n && nums[jumpIndex] != -1)
                {
                    //mark valid value
                    jumpIndexVal = nums[jumpIndex];
                    nums[jumpIndex] = -1;
                    jumpIndex = jumpIndexVal - 1;
                }
            }
        }
        
        
        //Check for missing element (<> -1)
            //if no missing element it might be 1 missing element (everything -1) first missing is the next
        //System.out.println("new");
        for (int i = 0; i < n; i++){
            if (nums[i] == -2 || nums[i] > 0)
            {       
                return i + 1;
            }
        }
        
        return n + 1;
    }
}



/*********************
* OTHERS SOLUTIONS
*********************/

/**
 * 
 * @author Nelson Costa
 *
 */
class FirstMissingPositiveSolution1 {
	  public int firstMissingPositive(int[] nums) {
	    int n = nums.length;

	    // Base case.
	    int contains = 0;
	    for (int i = 0; i < n; i++)
	      if (nums[i] == 1) {
	        contains++;
	        break;
	      }

	    if (contains == 0)
	      return 1;

	    // nums = [1]
	    if (n == 1)
	      return 2;

	    // Replace negative numbers, zeros,
	    // and numbers larger than n by 1s.
	    // After this convertion nums will contain 
	    // only positive numbers.
	    for (int i = 0; i < n; i++)
	      if ((nums[i] <= 0) || (nums[i] > n))
	        nums[i] = 1;

	    // Use index as a hash key and number sign as a presence detector.
	    // For example, if nums[1] is negative that means that number `1`
	    // is present in the array. 
	    // If nums[2] is positive - number 2 is missing.
	    for (int i = 0; i < n; i++) {
	      int a = Math.abs(nums[i]);
	      // If you meet number a in the array - change the sign of a-th element.
	      // Be careful with duplicates : do it only once.
	      if (a == n)
	        nums[0] = - Math.abs(nums[0]);
	      else
	        nums[a] = - Math.abs(nums[a]);
	    }

	    // Now the index of the first positive number 
	    // is equal to first missing positive.
	    for (int i = 1; i < n; i++) {
	      if (nums[i] > 0)
	        return i;
	    }

	    if (nums[0] > 0)
	      return n;

	    return n + 1;
	  }
	}
