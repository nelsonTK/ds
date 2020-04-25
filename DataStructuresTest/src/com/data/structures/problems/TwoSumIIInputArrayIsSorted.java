package com.data.structures.problems;

import java.util.Arrays;
import java.util.HashMap;
/**
 * https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
 * EASY
 * @author Nelson Costa
 *
 */
public class TwoSumIIInputArrayIsSorted {

	public static void main(String[] args) {

		TwoSumIIInputArrayIsSorted t = new TwoSumIIInputArrayIsSorted();
		// int [] a = {2,7,11,15};
		// int x = 10;
		//		 int [] a = {0,2};
//		int [] a = {0,2, 4};
				int [] a = {3,24,50,79,88,150,345};
		//		 int [] a = {0,0,0};
		//		int [] a = {0};
		int x = 200;
//		System.out.println(Arrays.toString(t.twoSum(a, x)));
		System.out.println(Arrays.toString(TwoSumIIInputArrayIsSortedSolution2.twoSum(a, x)));
	}

	

	/*********************************
	 * SOLUTION 1
	 ********************************/	
	private int index2 = -1;

	/**
	 * [WRONG]
	 * 
	 * My goal for this question was to solve in binary search but i didnt find possible
	 * 
	 * INTUITION
	 * 	Binary search first element, Binary Search second element
	 * 	This implementation is a bit messy, specially the second binary search
	 * 	I expect poor runtime performance
	 * 
	 * 
	 * 
	 * 
	 * @time  O(logn*logn) =  logn (search element element) * logn (search second element) 
	 * 		I think this is the time complexity
	 * 		this seams worst than O (N)
	 * @space O(1)
	 * 
	 * @param a
	 * @param x
	 * @return
	 */
	public int[] twoSum0(int[] a, int x) {

		int low = 0;
		int high = a.length - 1;
		int mid;
		int tmpMid;

		while (low < high)
		{
			mid = low + (high - low)/2;

			if (x - a[mid] < a[mid])
			{
				high = mid;
			}
			else if (x - a[mid] >= a[mid])
			{

				//handling duplicates if the previous is equals to the middle we reduce mid by 1
				tmpMid = (mid - 1 > 0 && a[mid - 1] == a[mid])? mid : mid + 1;


				int diffToTarget = searchComplement(a, tmpMid, a.length - 1, x - a[mid]);

				if (diffToTarget == 0) 
				{
					//handling duplicates
					while (mid - 1 >= 0 && a[mid - 1] == a[mid])
					{
						mid--;
					}

					mid = mid < 0 ? 0 : mid;

					while (index2 + 1 <= a.length - 1 && a[index2 + 1] == a[index2])
					{
						index2--;
					}

					index2 = index2 > a.length - 1? a.length - 1 : index2;


					return new int [] {mid + 1, index2 + 1};
				}
				else if (diffToTarget < 0)
				{
					high = mid;
				} 
				else
				{
					low = mid + 1;
				}
			}
		}

		return null;
	}

	private int searchComplement(int[] a, int low, int high, int x) {
		int mid;
		boolean lastRound = false;

		while (low <= high)
		{
			mid = low + (high - low)/2;

			if (x == a[mid])
			{
				index2 = mid;
				return 0;
			}
			else if (x < a[mid])
			{
				high = mid;
			}
			else
			{
				low = mid + 1;
			}

			if (low == high && !lastRound) {
				lastRound = true;
			}
			else if (low == high && lastRound) 
			{
				break;
			}

		}
		low = low >= a.length ? a.length - 1 : low;
		return x - a[low];
	}
	
	

	/*********************************
	 * SOLUTION 1
	 ********************************/		
	public static final int val = 0;
	public static final int index = 1;

	/**
	 * 		Runtime: 6 ms, faster than 13.42% of Java online submissions for Two Sum II - Input array is sorted.
			Memory Usage: 43 MB, less than 5.22% of Java online submissions for Two Sum II - Input array is sorted.
	 * 
	 * nothing fancy here
	 * 
	 * @time 	O(N)
	 * @space 	O(N)
	 * @bcr 	O(N)
	 * 
	 * @param a
	 * @param x
	 * @return
	 */
	public int[] twoSum1(int[] a, int x) {

		if (a == null || a.length == 0)
			return null;

		HashMap<Integer, int[]> map = new HashMap<>();
		boolean [] visited = new boolean[a.length];

		for (int i = 0; i < a.length; i++)
		{
			map.put(a[i], new int[] {x - a[i], i});
		}

		int[] candidate;
		int [] complement;

		for (int i = 0; i < a.length; i++)
		{
			visited[i] = true;
			candidate = map.get(a[i]);
			complement = map.get(candidate[val]);

			if (complement != null && visited[complement[index]] == false)
			{
				return new int [] {
						Math.min(complement[index] + 1, i + 1),
						Math.max(complement[index] + 1, i + 1)
				};
			}
		}

		return null;
	}
}

/**
 * 0ms
 * Best solution with two pointers
 * simple thinking, good job.
 * My goal for this question was to solve in binary search but i didnt find possible
 * @time 	O(N)
 * @space 	O(1)
 * @author Nelson Costa
 *
 */
class TwoSumIIInputArrayIsSortedSolution1 {
    public int[] twoSum(int[] numbers, int target) {
		int n = numbers.length;

		int[] answer = new int[2];
		int L=0,R=n-1;

		while(L<R) {
			int sum = numbers[L]+numbers[R];
			if(sum>target) {
				--R;
			} else if(sum<target) {
				++L;
			} else {
				answer[0]=++L;
				answer[1]=++R;
				return answer;
			}
		}

		answer[0]=-1;
		answer[1]=-1;
		return answer;
    }
}

/**
 * Binary search solution that was in comment section
 * Very cleaver approach, in general we advance the right and left pointer by one
 * but if middle point + right is below target, we advance left to middle and not by one.
 * the same way if middle point + left bigger than target, than right equals to middle
 * I didn't attack the problem in the right way
 * 
 * However this doesn't loo like a normal setup of binary search
 * 
 * @author Nelson Costa
 *
 */
class TwoSumIIInputArrayIsSortedSolution2 {

	   public static int[] twoSum(int [] a, int target) {
	       int left = 0, right = a.length-1; 
	       
	       while (left < right){
	           int mid = left + (right-left)/2; 
	           int sum = a[left] + a[right]; 
	           if (sum == target){
	               return new int [] {left+1, right+1}; 
	           }
	           else if (sum < target){
	               left = (a[mid] + a[right] < target)? mid:left+1;
	           }else{
	               right = (a[mid] + a[left] > target)? mid:right-1;
	           }
	          
	       }
	       return new int [] {-1, -1}; 
	   }
}
