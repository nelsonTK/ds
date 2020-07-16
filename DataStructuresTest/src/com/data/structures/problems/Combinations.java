package com.data.structures.problems;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/combinations/solution/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class Combinations extends LeetCodeExercise{
	static Combinations c = new Combinations();

	public static void main(String[] args) {

		int n = 4;
		int k = 3;

		for(List<Integer> list : c.combine(n, k))
		{
			printArray(list.toArray());
		}
	}

	/*********************************
	 * SOLUTION 1
	 ********************************/
	List<List<Integer>> ans = new ArrayList<List<Integer>>();
	/**
	 * 
	 * @intuition
	 * 		Very though question, despite simple code. I have a lot of troubles explaining this to myself.
	 * 		
	 * 		In combination order don't matters so we only count 1,2,3 once because as the order doesnt matter (123 equals to 321), 
	 * 		1) so we can fix the first digit
	 * 		2) we subtrack 1 to k because we have used one position of the k elements
	 * 		3) we advance one in the start position
	 * 		4) we add another element to the set
	 * 			so we always combine with the following elements
	 * 		5) what determines how far we go in the for loop from 1 to n is the size of k
	 * 			As we don't go backwards, if we dont have room to form a valid set, than we don't need to go there.
	 * 
	 * 		Very challenging question to explain
	 *  	
	 * 		and test as second digit all the upcoming elements until n - k + 1
	 * 		those will be tested by
	 * 		
	 * 
	 * @score
	 		Runtime: 2 ms, faster than 90.20% of Java online submissions for Combinations.
			Memory Usage: 40.8 MB, less than 36.96% of Java online submissions for Combinations.
	 * 
	 * @fail
	 * 		1) I was not adding things properly to the new row of ans List
	 * 		2) I had a trouble with the comparison in the for loop, I cannot figured out why it works with <=
	 * 			The confusion was because I was messying zero based with 1 based counting, and that confused me a lot.
	 * 
	 * @time O(KxCombinations of K and N])
		K is the size of the buckets/sets. we go through k elements before we have a combination
		So we have for each combination we iterate k elements.
		thats why is Kx Combinations of K N.

	 * @space O(Combinations of K N)
	 * 
	 * @param n
	 * @param k
	 * @return
	 */
	public List<List<Integer>> combine(int n, int k) {

		//TestCases
		if (n == k)
		{
			List<Integer> row = IntStream.range(1, n + 1).boxed().collect(Collectors.toList());
			ans.add(row);
			return ans;
		}
		//new ArrayList<List<Integer>>(
		getCombinations(1, n, k, new ArrayList<Integer>());

		return ans;
	}

	private void getCombinations(int start, int n, int k, ArrayList<Integer> row) {

		if (k == 0)
		{
			ans.add(row);
			return;
		}

		ArrayList<Integer> newRow;

		for (int i = start; i <=  n - k + 1; i++)
		{
			newRow = new ArrayList<>(row);
			newRow.add(i);
			getCombinations(i + 1, n, k - 1, newRow);
		}
	}

}

/*********************
 * OTHERS SOLUTIONS
 *********************/
/**
 * Solution binary sorted
 * @author Nelson Costa
 *
 */
class CombinationsSolution2 {
	public List<List<Integer>> combine(int n, int k) {
		// init first combination
		LinkedList<Integer> nums = new LinkedList<Integer>();
		for(int i = 1; i < k + 1; ++i)
			nums.add(i);
		nums.add(n + 1);

		List<List<Integer>> output = new ArrayList<List<Integer>>();
		int j = 0;
		while (j < k) {
			// add current combination
			output.add(new LinkedList(nums.subList(0, k)));
			// increase first nums[j] by one
			// if nums[j] + 1 != nums[j + 1]
			j = 0;
			while ((j < k) && (nums.get(j + 1) == nums.get(j) + 1))
				nums.set(j, j++ + 1);
			nums.set(j, nums.get(j) + 1);
		}
		return output;
	}
}

/**
 * Solution with backtracking
 * concept similar to my solution but more efficient in the sense it does not creates an object at every step of the set creation
 * @author Nelson Costa
 *
 */
class CombinationsSolution1 {
	List<List<Integer>> output = new LinkedList();
	int n;
	int k;

	public void backtrack(int first, LinkedList<Integer> curr) {
		// if the combination is done
		if (curr.size() == k)
			output.add(new LinkedList(curr));

		for (int i = first; i < n + 1; ++i) {
			// add i into the current combination
			curr.add(i);
			// use next integers to complete the combination
			backtrack(i + 1, curr);
			// backtrack
			curr.removeLast();
		}
	}

	public List<List<Integer>> combine(int n, int k) {
		this.n = n;
		this.k = k;
		backtrack(1, new LinkedList<Integer>());
		return output;
	}
}


