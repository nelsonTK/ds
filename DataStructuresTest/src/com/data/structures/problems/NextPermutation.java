package com.data.structures.problems;

import com.data.structures.problems.ds.LeetCodeExercise;

public class NextPermutation extends LeetCodeExercise{
	static NextPermutation n = new NextPermutation();
	public static void main(String[] args) {
		int [] nums = stringToArray("[7,5,2,8,1,0]");
		n.nextPermutation(nums);
	}

	/*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * [WRONG]
	 * 
	 * @intuition
	 * 		find firts element on the left compared with the right which is smaller than our right element
	 * 		swap and than perform bubble sort on the reminder from left.
	 * 
	 * @fail
	 * 		1) bad interpretation of the question
	 * 		2) fail didnt stop search for change
	 * 		3) a parentheses was missing in the while loop
	 *		4) wrong inicialization of changed variable 
	 *		5) wrong comparation sign
	 * 
	 * @time
	 * 		O(N^2)
	 * 
	 * @space
	 * 		O(1)
	 * 
	 * @param nums
	 */
	public void nextPermutation0(int[] nums) {

		for (int r = nums.length - 1; r >= 0; r--)
		{
			for(int l = r - 1; l >= 0 ; l--)
			{

				if (nums[l]<nums[r])
				{

					swap(l, r, nums);
					bubble(l+1, nums);
					return;
				}

			}
		}

		bubble(0, nums);

	}


	/*********************************
	 * SOLUTION 2
	 ********************************/

	/** 
	 *
	 * @intuition
	 *    It was also hard to 
	 *    This is a very messy implementation of this problem
	 *    I don't like it. I need to remade it
	 *    The Code is bad, it just works
	 *    
	 *      1) The intuition is to find a descending element in the array and then
	 *      2) switch with the lowest at the right
	 *      3) and sort the portion which is at the right
	 *    
	 *    to know the next permutation I need to know in which state the permutation is     
	 *    bubble sort isn't neat either
	 *    
	 * @score   
	 *    	Runtime: 3 ms, faster than 6.87% of Java online submissions for Next Permutation.
	 *		Memory Usage: 41.1 MB, less than 8.74% of Java online submissions for Next Permutation.
	 *    
	 * @fail 
	 *       1) failed in the fundamental algorithm, despite getting much of the pattern 
	 *       2) failed in many implementation details
	 *
	 *	
	 * @time
	 * 		O(N^2)
	 * 
	 * @space
	 * 		O(N)
	 *
	 **/
	public void nextPermutation1(int[] nums) {

		int min = Integer.MAX_VALUE;
		int minIndex = 0;
		int r = nums.length - 1;

		for(int l = r - 1; l >= 0 ; l--)
		{
			if (nums[l]<nums[r])
			{
				//find minimum bigger than nums[l]
				for (int k = l + 1; k < nums.length; k++)
				{
					if (nums[k] > nums[l] && nums[k] < min)
					{
						min = Math.min(min, nums[k]);
						minIndex = k;
					}
				}

				swap(l, minIndex, nums);
				bubble(l+1, nums);
				return;
			}

			r--;
		}

		bubble(0, nums);
	}



	/** 
	 * @intuition
	 * 		1) find inversion point (which is the first breaking the increasing order counting from the left)
	 * 		2) find find smallest element in the right side
	 * 		3) swap elements at the right 
	 * 
	 * 		Challenges:
	 * 			To find the pattern to find the next permutation
	 * 			And the find the all the cases needed.
	 * 	
	 * 		
	 * @score
	 *		Runtime: 1 ms, faster than 64.20% of Java online submissions for Next Permutation.
	 *		Memory Usage: 40.7 MB, less than 30.97% of Java online submissions for Next Permutation.
	 * 
	 * @fail 
	 * 		1) because of a detail in the finding the minimum, I have to find the last minimum greater element 
	 *
	 * @time
	 * 		O(N)
	 * 
	 * @space
	 * 		O(1)
	 *
	 *
	 **/
	public void nextPermutation(int[] nums) {

		//pattern recognition
		//use a number of cases to see what the next permutation is
		//identify the pattern
		//implement

		//identify element in decreasing order.
		//swap the element with the next greater element
		//Swap all elements
		int min = Integer.MAX_VALUE, minIndex = -2;
		for (int i = nums.length - 2; i >= 0; i--)
		{
			if (nums[i]< nums[i + 1])
			{
				//found the inversion element
				//find next big element  to the right
				for (int j = i + 1; j < nums.length; j ++)
				{
					if (nums[j] > nums[i] && nums[j] <= min)
					{
						min = nums[j];
						minIndex = j;                        
					}
				}

				//swap position                
				swap(minIndex , i, nums);

				//swap or sort the left side
				swapAll(i + 1, nums.length-1, nums);
				
				//return
				return;
			}
		}

		//if we got here than we need to sort the array
		swapAll(0, nums.length-1, nums);
	}


	private void swapAll(int l, int r, int[] nums){
		if (l > r) return;

		for (; l <= r; l++, r--)
		{
			swap(l , r, nums);
		}
	}

	/*********************
	 * SHARED METHODS
	 *********************/
	/**
	 * Used in solution 1 and 2, it's a sloppy implementation of bubble sort
	 * @param left
	 * @param nums
	 */
	private void bubble(int left, int []nums)
	{    
		int i = left;
		boolean changed = true;
		while (i < nums.length)  
		{
			if (!changed)
			{
				i++;
			}
			changed = false;

			//System.out.println(i + " "+ (i + 1));
			for (int j = i + 1; j < nums.length; j++)
			{
				if (nums[i] > nums[j])
				{
					swap(i, j, nums);
					changed = true;
					break;
				}
			}            
		}
	}

	private void swap(int a, int b, int [] nums)
	{
		int tmp = nums[a];
		nums[a] = nums[b];
		nums[b] = tmp;
	}





}
