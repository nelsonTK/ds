package com.data.structures.problems;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.data.structures.problems.ds.LeetCodeExercise;

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
