package com.data.structures.problems;

import java.util.Arrays;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/product-of-array-except-self/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class ProductofArrayExceptSelf extends LeetCodeExercise {

	static ProductofArrayExceptSelf p = new ProductofArrayExceptSelf();
	public static void main(String[] args) {

		int [] nums = stringToArray("[1,2,3,4]");
		System.out.println(Arrays.toString(nums));
		System.out.println(Arrays.toString(p.productExceptSelf(nums)));
	}

	 /**
	  * 
	  * 
    	@intuition 
    		if zero is not took into account, a trivial solution would be multiply all elements and save in a variable.
    		and then for each element divide the total by the element.
    		
    		but as we cannot use division this is not quite the answer, but we can do something similar with some adjestments.
    		
    		what I did was to use exponential to get the division done. so I still divide but in a different manner.
    		3^-1 equals to 1/3. so this is the basis for me to solve this question.
    		
    		what's left is the zeros. which are exceptions. and what's worthy here is that if we have one zero, than all the elements have zeros as multiplication result but the zero element.
    		if we have more than one zero, than all elements will have zero.
    		
    		That's it
    		
    		
    	
    	@score
			Runtime: 3 ms, faster than 12.47% of Java online submissions for Product of Array Except Self.
			Memory Usage: 48.5 MB, less than 12.44% of Java online submissions for Product of Array Except Self.    	
			
    	@fail
        	1)	
        
        @time  O(N)
        @space O(1) except answer
    **/
    public int[] productExceptSelf(int[] nums) {
        
        
        int totalWithoutZeros = 1;
        int zeros=0;
        int [] ans = new int[nums.length];
        
        //count zeros and get total without zeros
        for (int i : nums)
        {
            if (i != 0)
                totalWithoutZeros*=i;
            
            if (i == 0)
                zeros++;
        }
        
        //if more than one zero the result is all zeros
        if (zeros > 1)
            return ans;
        //if no zeros qe can just do basic math
        else if (zeros == 0)
        {
            for (int i = 0; i < nums.length; i++)
            {
                ans[i] = (int)((double)totalWithoutZeros * Math.pow(nums[i], -1));
            }
        }
        //if one zero than only zero element position will be different from zeroqq
        else
        {
             for (int i = 0; i < nums.length; i++)
            {
                ans[i] = nums[i] == 0 ? totalWithoutZeros : 0;
            }
        }
        return ans;
    }
}



/*********************
* OTHERS SOLUTIONS
*********************/

/**
 * 
 * they use the answer algorithm to be the left product.
 * and use a variable to hold the values on the fly of a "right" array.
 * 
 * is the same algo than solution 1 but with space complexity improved
 * 
 * @time	O(N)
 * @space	O(1)
 * @author Nelson Costa
 *
 */
class ProductofArrayExceptSelfSolution2 {
    public int[] productExceptSelf(int[] nums) {

        // The length of the input array 
        int length = nums.length;

        // Final answer array to be returned
        int[] answer = new int[length];

        // answer[i] contains the product of all the elements to the left
        // Note: for the element at index '0', there are no elements to the left,
        // so the answer[0] would be 1
        answer[0] = 1;
        for (int i = 1; i < length; i++) {

            // answer[i - 1] already contains the product of elements to the left of 'i - 1'
            // Simply multiplying it with nums[i - 1] would give the product of all 
            // elements to the left of index 'i'
            answer[i] = nums[i - 1] * answer[i - 1];
        }

        // R contains the product of all the elements to the right
        // Note: for the element at index 'length - 1', there are no elements to the right,
        // so the R would be 1
        int R = 1;
        for (int i = length - 1; i >= 0; i--) {

            // For the index 'i', R would contain the 
            // product of all elements to the right. We update R accordingly
            answer[i] = answer[i] * R;
            R *= nums[i];
        }

        return answer;
    }
}

/**
 * Solution using two arrays
 * one with the multiplication of all the elements on the left
 * and other with the multiplication of all the elements on the right
 * for a given position i.
 * 
 * so the answer for i is the multiplication of L[i] - 1 * R[i] - 1;
 * 
 * @time  O(N)
 * @space O(N)
 * 
 * 
 * @author Nelson Costa
 *
 */
class ProductofArrayExceptSelfSolution1 {
    public int[] productExceptSelf(int[] nums) {

        // The length of the input array
        int length = nums.length;

        // The left and right arrays as described in the algorithm
        int[] L = new int[length];
        int[] R = new int[length];

        // Final answer array to be returned
        int[] answer = new int[length];

        // L[i] contains the product of all the elements to the left
        // Note: for the element at index '0', there are no elements to the left,
        // so L[0] would be 1
        L[0] = 1;
        for (int i = 1; i < length; i++) {

            // L[i - 1] already contains the product of elements to the left of 'i - 1'
            // Simply multiplying it with nums[i - 1] would give the product of all
            // elements to the left of index 'i'
            L[i] = nums[i - 1] * L[i - 1];
        }

        // R[i] contains the product of all the elements to the right
        // Note: for the element at index 'length - 1', there are no elements to the right,
        // so the R[length - 1] would be 1
        R[length - 1] = 1;
        for (int i = length - 2; i >= 0; i--) {

            // R[i + 1] already contains the product of elements to the right of 'i + 1'
            // Simply multiplying it with nums[i + 1] would give the product of all
            // elements to the right of index 'i'
            R[i] = nums[i + 1] * R[i + 1];
        }

        // Constructing the answer array
        for (int i = 0; i < length; i++) {
            // For the first element, R[i] would be product except self
            // For the last element of the array, product except self would be L[i]
            // Else, multiple product of all elements to the left and to the right
            answer[i] = L[i] * R[i];
        }

        return answer;
    }
}