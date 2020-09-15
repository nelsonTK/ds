package com.data.structures.problems;

import java.util.HashMap;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/top-k-frequent-elements/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class TopKFrequentElements_Take2 extends LeetCodeExercise{

	static TopKFrequentElements_Take2 t = new TopKFrequentElements_Take2();
	public static void main(String[] args) {
		int [] nums = stringToArray("[1,1,1,2,2,3]");
		int k = 2;
		nums = stringToArray("[3,0,1,0]");
		k = 1;
		nums = stringToArray("[2,3,4,1,4,0,4,-1,-2,-1]");
		k = 2;

		t.topKFrequent(nums, k);

	}


	HashMap<Integer, Integer> freq;
	int k;
	 /*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * 
	 * @score
	 * 		Runtime: 8 ms, faster than 96.77% of Java online submissions for Top K Frequent Elements.
	 * 		Memory Usage: 41.8 MB, less than 91.75% of Java online submissions for Top K Frequent Elements.
	 * 
	 * 
	 * @fail
	 * 		1) infinite loop on selection, problem in while loop
	 * 		2) I'm returning the array in asc order
	 * 		3) selection comparisons still wrong
	 * 		4) a lot of errors related with adding or subtracting the left and right.
	 * @param nums
	 * @param k
	 * @return
	 */
	public int[] topKFrequent(int[] nums, int k) 
	{

		//create frequencyMap
		this.k = k;
		freq = new HashMap<>();
		for (int i = 0; i < nums.length; i++)
		{
			freq.put(nums[i], freq.getOrDefault(nums[i], 0) + 1);
		}
		//create compressed array with the frequencies
		int [] arr = new int[freq.size()];
		int i = 0;
		for(int key : freq.keySet())
		{
			arr[i] = key;
			i++;
		}

		//perform quickselect on that compressed array
		select(arr, 0, arr.length - 1);

		//create answer
		int [] ans = new int[k];
		for (int j = 0; j < k; j++)
		{
			ans[j] = arr[arr.length - 1 - j];
		}

		return ans;
	}


	private void select(int [] a, int left, int right)
	{
		if (left >= right)
			return;


		int index = partition(a, left, right);

		if (a.length - k == index)
		{
			return;
		}
		else if (a.length - k  < index)
		{
			select(a, left, index - 1);
		}
		else if (a.length - k  > index)
		{
			select(a, index + 1, right);
		}

	}

	private int partition(int [] a, int start , int end)
	{
		int mid = start + (end - start)/2;

		int pivot = freq.get(a[mid]);
		swap(a, mid, end);
		int l = start;
		int r = end - 1;

		while (l <= r)
		{
			while (l < end && freq.get(a[l]) < pivot)
			{
				l++;
			}

			while (r > start && freq.get(a[r]) > pivot)
			{
				r--;
			}

			if (l <=  r)
			{
				swap(a, l, r);
				l++;
				r--;
			}
			else
			{
				break;
			}
		}

		swap(a, end, l);

		return l;
	}

	private void swap(int [] a, int posA, int posB){
		int tmp = a[posB];
		a[posB] = a[posA];
		a[posA] = tmp;
	}
}
