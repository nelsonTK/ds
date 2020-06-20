package com.data.structures.problems;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/sliding-window-maximum/
 * HARD
 * @author Nelson Costa
 *
 */
public class SlidingWindowMaximum extends LeetCodeExercise{

	static SlidingWindowMaximumSolution1 s1 = new SlidingWindowMaximumSolution1();
	static SlidingWindowMaximum s = new SlidingWindowMaximum();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] nums = stringToArray("[1,3,1,2,0,5]");
		int k = 3;
		s.maxSlidingWindow(nums, k);
	}
	
    /**
    
    [WRONG]
    @intuition
    	This was my first trial to solve the problem with dp
    	The logic was almost there but I there where bits I was not able to solve.
    	it fails to address some cases.

    	the logic here is to greedly put the biggest element if it is in the range of k in the first pass
    	than on the second pass this time right to leftcompare the first element of the window with the last element of the window and assign the maximal
    	
    @alternatives
    	I also have thought in a Priority Solution
    
    @fail
        1) array out of bounds
        2) forgot about negative numbers
        3) I was not updating dp in the first pass
        4) when k equals to arraysize, I had set max variable with MAX_VALUE instead of MIN_VALUE
        5) wrong algorithm
        
    @time   O(3N)
    @space  O(N)
    
    **/
    public int[] maxSlidingWindow0(int[] nums, int k) {
        
        //ans size() equals to => nums - k + 1
        //guards
            //size 0, size 1;
            //k = nums.size
            //k = 1;
        if (nums.length == 0)
            return new int[0];
        
        if (nums.length == 1 || k == 1)
            return nums;
        
        if (k == nums.length)
            return getMax(nums);
        
        int [] ans = new int[nums.length - k + 1];
        
        //pass one (set the pseudo maximum)
        
        //O(N)
        int [] dp = new int[nums.length];
        int max = Integer.MIN_VALUE;
        int curK = k;
        for (int i = 0; i < nums.length; i++)
        {
            if (curK > 0)
            {
                //found max before end window
                if (nums[i] > max)
                {
                    max = nums[i];
                    curK = k;
                }
            }
            else //if (curK == 0)
            {
                max = nums[i];
                curK = k;
            }
            
            dp[i] = max;
            curK--;
        }
        
        //pass 2 (if first element of a window (left to right) in the original array is bigger than the dp array, we update it )
        
        for(int i = nums.length - 1; i >= k - 1; i--)
        {
            dp[i] = Math.max(nums[i - (k - 1)], dp[i]);
        }
        
        //create return array
        int ansIndex = 0;
        for (int i = k - 1; i < dp.length; i++)
        {
            ans[ansIndex++] = dp[i];
        }
        
        return ans;
    }
    
    private int[] getMax(int [] nums)
    {
        int max = Integer.MIN_VALUE;
        
        for (int i = 0; i < nums.length; i++){
            max = Math.max(max, nums[i]);
        }
        
        return new int[]{max};
    }
    
    
    
    
    

    /**
    
    @score
		Runtime: 3 ms, faster than 95.70% of Java online submissions for Sliding Window Maximum.
		Memory Usage: 52.1 MB, less than 49.64% of Java online submissions for Sliding Window Maximum.
    
    @fail
        1) increment was wrong
        2) index of ans was wrong
        3) error found, it was in the implementation of the right side, the k containers were not sinchronous
    	4) parentheses missing in modulo operation
    **/
    public int[] maxSlidingWindow(int[] nums, int k) {
        
        //guards
        //size == 1,
        //k == 1
        //size== 0
        
        if (nums.length == 1 || k == 1)
            return nums;
        
        int n = nums.length;
        int [] ans = new int[n - k + 1];
        int [] left = new int[n];
        int [] right = new int[n];
        
        //set left
        left[0] = nums[0];
        for(int i = 1; i < n; i++)
        {
            if(i % k != 0)
            {
                left[i] = Math.max(left[i - 1], nums[i]);
            }
            else
            {
                left[i] = nums[i];
            }
        }
        
        //set right
        right[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--)
        {
        	if((i + 1) % k == 0)
            {
                right[i] = nums[i];
            }
            else
            {
                right[i] = Math.max(nums[i], right[i + 1]);

            }
        }
        
        //set output
        for (int i = 0; i + k - 1 < n; i++)
        {
            ans[i] = Math.max(left[i + k - 1], right[i]);
        }
        
        return ans;
    }

}

/**
 * @intuition
 * 		Separate array in k blocks
 * 		
 * 		create left array where the leftmost side of the block is always the biggest of the block
 * 		create right array where the rightmost side of the block is always the biggest.
 * 
 * 		this means that I can do:
 * 
 * 		Creating the left array
 * 			left [i] = Max(left [i - 1], current[i]);
 * 		
 * 		Creating the right array
 * 			right[i] = Max(right[i + 1], current[i]);
 * 
 * 		Final Output
 * 			Output[i - k + 1] = Max(left[i + k - 1], right[i]);
 * 
 *  		What this means is that the output[0] (we skip the previous k - 1 positions of the left and right)
 *  		is equals to the maxim of the end of the bucket which we find in left side 
 *  		and the maximum of the beginning of the bucket (at the right side).
 *  		
 *  		Other order would not work, because we would be checking the start side of the max verification of both arrays.
 * 		
 * 		In summary:
 * 
 * 			1) Separate the initial input in blocks
 *			
 *			2) calculate the maximum value inside each block from left to right, and then from right to left
 *				2) the right and left separations should be equal
 *
 * 			3) calculate output with the starting point at rightmost and the end at the left most. 
 * 				in each iteration we are dealing with both the largest values possible from both left and right of the block
 * @author Nelson Costa
 *
 */
class SlidingWindowMaximumSolution1 {
	  public int[] maxSlidingWindow(int[] nums, int k) {
	    int n = nums.length;
	    if (n * k == 0) return new int[0];
	    if (k == 1) return nums;

	    int [] left = new int[n];
	    left[0] = nums[0];
	    int [] right = new int[n];
	    right[n - 1] = nums[n - 1];
	    for (int i = 1; i < n; i++) {
	      // from left to right
	      if (i % k == 0) left[i] = nums[i];  // block_start
	      else left[i] = Math.max(left[i - 1], nums[i]);

	      // from right to left
	      int j = n - i - 1;
	      if ((j + 1) % k == 0) right[j] = nums[j];  // block_end
	      else right[j] = Math.max(right[j + 1], nums[j]);
	    }

	    int [] output = new int[n - k + 1];
	    for (int i = 0; i < n - k + 1; i++)
	      output[i] = Math.max(left[i + k - 1], right[i]);

	    return output;
	  }
	}