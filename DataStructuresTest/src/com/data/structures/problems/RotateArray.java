package com.data.structures.problems;

/**
 * https://leetcode.com/problems/rotate-array/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class RotateArray {




	/*********************************
	 * SOLUTION 1 [WRONG - or TLE]
	 ********************************/
	public void rotateold2(int[] a, int k) {

		if (a == null || a.length == 0)
			return;

		int jump = 0, len = a.length, cur = a[0], next, count = 0;

		k %= len; 
		if (k == 0) return;

		if (len % k != 0 || k == 1)
		{ 
			int i = 0;
			while (i < len)
			{
				jump = (jump + k) % len;
				next = a[jump];
				a[jump] = cur;
				cur = next;
				i++;
			}            
		} 
		else
		{
			for (int i = 0; i <= len/k; i ++)
			{
				int start = 0;
				jump = i;
				next = a[jump];
				cur = a[i];
				while (start <= len/k)
				{
					jump = (jump + k) % len;
					next = a[jump];
					a[jump] = cur;
					cur = next;
					start++;
				}
			}
		}
	}


	//O(N) time
	//O(1) space
	//TLE, because I forgot to remove redundant elements
	/**
	 * @intuition
	 * 		weak and implementation with extra space
	 * 		I tried the cyclic implementation but failed the details I don't know why
	 * 
	 * 
	 * @score
	 * 
	 * @fail
	 * 		TLE, because I forgot to remove redundant elements
	 * 
	 * @time
	 * 		O(N)
	 * 
	 * @space
	 * 		O(N)
	 * 
	 * @param a
	 * @param k
	 */
	public void rotate(int [] a, int k)
	{
		if (a == null || k == 0)
			return;

		int [] b = new int[a.length];
		for (int i = 0; i < a.length; i++)
		{
			int jump = (i + k) % a.length;
			b[jump] = a[i];
		}
		for (int i = 0; i <a.length;i++)
		{
			a[i] = b[i];
		}
	}
}
