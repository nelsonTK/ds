package com.data.structures.problems;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/3sum/
 * MEDIUM
 * 
 * @author Nelson Costa
 *
 */
public class _3Sum extends LeetCodeExercise {
	static _3Sum sum = new _3Sum();
	public static void main(String[] args) {
		int [] a = stringToArray("[-1, 0, 1, 2, -1, -4]");
		a = stringToArray("[0,0,0]");
		a = stringToArray("[0,0,0,0]");
		a = stringToArray("[-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6]");
		printArray(a);

		//		for (List<Integer> i : sum.threeSum(a)){
		//			System.out.println(Arrays.toString(i.toArray()));
		//		}
		for (List<Integer> i : new _3SumSolutions2().threeSum(a)){
			System.out.println(Arrays.toString(i.toArray()));
		}
	}


	/**
	 * @intuition
	 * 		I commited a mistake in my jugment explored the solution using sort because I though I could keep the time complexity in that level.
	 * 		But I couldn't and that probably added some extra time complexity. I'm not sure and I did not had more time to explore other possibilities
	 * 		I didn't liked this solution
	 * 
	 * @score
	 * 		Runtime: 649 ms, faster than 7.95% of Java online submissions for 3Sum.
			Memory Usage: 47.4 MB, less than 86.92% of Java online submissions for 3Sum.
	 * 
	 * 
	 * @fail
	 * 		1) used candidate instead of candidate to get the index from the 
	 * 		2) for condition was wrong j - i and not i - j
	 * 		3) didn't remember that zero might not exist in the array (first loop) 
	 * 		4) on many ocations I was accessing array[candidate] instead of using candidate
	 * 		5) wrong conjunction || instead of && in the first for 
	 * 		6) wrong condition  for arrays starting with 0, I was assuming it was not possible to return zero but it is
	 * 		7) I was not preventing repetition of entries on scanning the hashmap,
				so [0,0,0,0] was getting score as many times as entries in there between i and j.
	 * 			it was not enough to have a guard at the beginning of the second for
	 * 		8) I added the condition with wrong conjunctions
	 * 		9) first and second set idea was not good enough to control duplicates properly. it was blocking valid combinations
	 * @time	(N^2)
	 * @space   O(N)
	 * @bcr		O(NLog N)
	 * 
	 * @param a
	 * @return
	 */
	public List<List<Integer>> threeSum(int[] a) {

		if (a == null || a.length < 3) {
			return new ArrayList<List<Integer>>(); 
		}

		ArrayList<List<Integer>> ans = new ArrayList<List<Integer>>();
		ArrayList<Integer>  ansRow = new ArrayList<Integer>();
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
		Set<String> code = new HashSet<String>();

		Arrays.sort(a);

		if (a[0] > 0) {
			return new ArrayList<List<Integer>>(); 
		}

		ArrayList<Integer>  list;

		//mounting hahsmap for O(1) access
		for (int i = 0; i < a.length; i++) {
			list = map.getOrDefault(a[i], new ArrayList<Integer>());
			if(list.size() == 0)
			{
				list.add(i);
				map.put(a[i], list);
			}
			else
			{
				list.add(i);
			}
		}


		for (int i = 0; i < a.length &&  a[i] <= 0; i++) {


			//testig the second sum element
			for (int j = a.length - 1; j - i > 1; j--) {

				if (code.contains(getCode(i, j, a)))
					continue;
				int candidate = -(a[i] + a[j]); //third sum element

				//it was already processed, so we can stop
				if (candidate > a[j])
					continue;


				List<Integer> indexes = map.get(candidate);

				if (indexes == null)
					continue;

				if (indexes.size() > 1)
				{
					for (Integer index : indexes) {

						if (index < j && index > i && !code.contains(getCode(i, j, a)))
						{
							ansRow = new ArrayList<Integer>();
							ansRow.add(a[i]);
							ansRow.add(a[j]);
							ansRow.add(candidate);
							ans.add(ansRow);
							code.add(getCode(i, j, a));
						}
					}
				}
				else
				{
					if (indexes.get(0) < j && indexes.get(0) > i && !code.contains(getCode(i, j, a)))
					{
						ansRow = new ArrayList<Integer>();
						ansRow.add(a[i]);
						ansRow.add(a[j]);
						ansRow.add(candidate);
						ans.add(ansRow);
						code.add(getCode(i, j, a));
					}
				}
			}

		}

		return ans;
	}

	private String getCode(int i , int j, int[] a) {
		return a[i] + "_" + a[j];
	}
}

/**
 * 
 * 	Runtime: 16 ms, faster than 98.93% of Java online submissions for 3Sum.
	O(N^2)
 */
class _3SumSolutions1{

	public List<List<Integer>> threeSum(int[] nums) {
		Arrays.sort(nums);
		List<List<Integer>> res = new ArrayList<>();
		for (int i = 0; i < nums.length && nums[i] <= 0; ++i)
			if (i == 0 || nums[i - 1] != nums[i])
				twoSumII(nums, i, res);
		return res;
	}
	
	void twoSumII(int[] nums, int i, List<List<Integer>> res) {
		int lo = i + 1, hi = nums.length - 1;
		while (lo < hi) {
			int sum = nums[i] + nums[lo] + nums[hi];
			if (sum < 0 || (lo > i + 1 && nums[lo] == nums[lo - 1]))
				++lo;
			else if (sum > 0 || (hi < nums.length - 1 && nums[hi] == nums[hi + 1]))
				--hi;
			else
				res.add(Arrays.asList(nums[i], nums[lo++], nums[hi--]));
		}
	}
}
/**
 * (Solution with hashmap)
 * 
	Runtime: 236 ms, faster than 22.54% of Java online submissions for 3Sum.
	Memory Usage: 45.7 MB, less than 95.76% of Java online submissions for 3Sum.


 * @author Nelson Costa
 *
 */
class _3SumSolutions2{

	public List<List<Integer>> threeSum(int[] nums) {
		Set<Pair<Integer, Integer>> found = new HashSet<>();
		Set<Integer> dups = new HashSet<>();
		List<List<Integer>> res = new ArrayList<>();
		Map<Integer, Integer> seen = new HashMap<>();
		for (int i = 0; i < nums.length; ++i)
			if (dups.add(nums[i]))
				for (int j = i + 1; j < nums.length; ++j) {
					int complement = -nums[i] - nums[j];
					if (seen.containsKey(complement) && seen.get(complement) == i) {
						int v1 = Math.min(nums[i], Math.min(complement, nums[j]));
						int v2 = Math.max(nums[i], Math.max(complement, nums[j]));
						if (found.add(new Pair<Integer, Integer>(v1, v2)))
							res.add(Arrays.asList(nums[i], complement, nums[j]));
					}
					seen.put(nums[j], i);
				}
		return res;
	}

	class Pair<T,U>
	{
		public T A;
		public U B;
		// Return a map entry (key-value pair) from the specified values
		Pair(T a, U b){
			A = a;
			B = b;
		}
		public <T, U> Map.Entry<T, U> of(T first, U second)
		{
			return new AbstractMap.SimpleEntry<>(first, second);
		}

		@Override
		public boolean equals(Object p) {
			return ((Pair<T,U>) p).A.equals(this.A) && ((Pair<T,U>) p).B.equals(B);
		}
	}
}
