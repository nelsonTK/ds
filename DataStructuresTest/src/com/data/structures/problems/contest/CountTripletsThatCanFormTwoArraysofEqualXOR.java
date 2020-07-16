package com.data.structures.problems.contest;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class CountTripletsThatCanFormTwoArraysofEqualXOR extends LeetCodeExercise {
	static CountTripletsThatCanFormTwoArraysofEqualXOR c = new CountTripletsThatCanFormTwoArraysofEqualXOR();

	public static void main(String[] args) {
		int [] a =  stringToArray("[2,3,1,6,7]");
		System.out.println(c.countTriplets(a));
	}
	
	/*********************************
	 * SOLUTION 1
	 ********************************/	
	/**
	 * [WRONG]
	 * 
    	@comments
    		very difficult to debug this question
    		The algorithm that I created was working as expected but was not covering all the cases
    		
    	@time  O(N2)
    	
    	@space O(1)
	 **/
	public int countTriplets(int[] a) {

		if( a == null  || a.length <= 1)
			return 0;


		int triplets = 0, i = 0, j = 1, k = j;

		while (j < a.length)
		{
			k = j;
			i = j - 1;

			//The triplets are (0,1,2), (0,2,2), (2,3,4) and (2,4,4)
			while (i > 0)
			{
				if (calc(i, j - 1, a) == calc(j, k, a))// && !set.contains(code(i,j,k)))
				{
					triplets ++;
					//set.add(code(i,j,k));
				}       
				i--;
			}

			i = 0;

			while (k <= a.length - 1)
			{
				if (calc(i, j - 1, a) == calc(j, k, a))// && !set.contains(code(i,j,k)))
				{
					triplets ++;
					//set.add(code(i,j,k));
				}    

				k++;
			}

			j++;
		}

		return triplets;
	}



	private int calc (int start, int end, int[] a)
	{
		if (start == end)
			return a[start];

		int res = a[start];

		for (int i = start + 1; i <= end; i++)
		{
			res ^= a[i];
		}

		return res;
	}

	public String code (int i, int j, int k)
	{
		return i + "_" + j + "_" + k;
	}

}
