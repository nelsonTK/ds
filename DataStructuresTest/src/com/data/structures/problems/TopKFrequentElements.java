package com.data.structures.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 *  [ATTENTION THE ANSWER FORMAT IS WRONG IN LEETCODE IS INT[] INSTEAD OF LIST<INTEGER>]
 *  https://leetcode.com/problems/top-k-frequent-elements
 *  MEDIUM
 *  
 * @author Nelson Costa
 *
 */
public class TopKFrequentElements extends LeetCodeExercise{

	public static void main(String[] args) {

		TopKFrequentElements t = new TopKFrequentElements();
		int [] nums = stringToArray("[1,1,1,2,2,3,5,5,5,5,6,8]");
		int k = 3;

		printArray(nums);

		System.out.println(Arrays.toString(t.topKFrequent(nums, k)));

	}

	/**
	 *  [ATTENTION THE ANSWER FORMAT IS WRONG IN LEETCODE, IT ASKS LIST<INTEGER> BUT EXPECTS IS INT[] 
	 *  
	 *  
	 * @fail	
	 * 		1) the put operation for the frequency map for the first case was wrong, I should have confirmed it
	 * 		2) failed for array equals to one, I didn't though in the first case and it's impact in the start and end position in the answer construction.
	 * 
	 * @assistedDebug yes
	 * 
	 * @time  O(n) worst case O(3n)
	 * @space O(n) worst case O(3n) answer inclusive
	 * @bcr   O(n)
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public List<Integer> topKFrequent0(int[] nums, int k) {
		if (nums == null)
			throw new IllegalArgumentException();

		List<Integer> ans = new ArrayList<Integer>();
		HashMap<Integer, Integer> numsMap = new HashMap<>();
		HashMap<Integer, List<Integer>> frequencyMap = new HashMap<>();
		List<Integer> auxList;


		for (int num : nums) {
			numsMap.put(num, numsMap.getOrDefault(num, 0) + 1);
		}

		//reverse HashMap take care of repeated elements
		for (Integer num : numsMap.keySet()) {
			auxList = frequencyMap.getOrDefault(numsMap.get(num), new ArrayList<Integer>());

			if(auxList.size() == 0)
			{
				auxList.add(num);
				frequencyMap.put(numsMap.get(num), auxList);
			}
			else
			{
				auxList.add(num);
			}
		}


		//work our answer
		int i = nums.length; //maximum possible repeated elements

		while (k > 0 && i > 0)
		{
			auxList = frequencyMap.get(i);

			if (auxList != null && auxList.size() == 1)
			{
				ans.add(auxList.get(0));
				k--;
			}
			else if (auxList != null && auxList.size() > 1)
			{
				for (int j = 0; j < auxList.size() && k > 0; j++, k-- )
				{
					ans.add(auxList.get(j));
				}
			}

			i--;
		}


		return ans;
	}




	/** [CORRECT RETURN TYPE, NEGLICTING LEETCODE'S INDICATION]
	 * 
	 * @fail	
	 * 		1) the put operation for the frequency map for the first case was wrong, I should have confirmed it
	 * 		2) failed for array equals to one, I didn't thought in the first case and it's impact in the start and end position in the answer construction.
	 * 		3) when they asked time complexity better than n log n I imediatly though of n, and not k log k. or n log k.
	 * 
	 * @score
	 * 		Runtime: 8 ms, faster than 93.62% of Java online submissions for Top K Frequent Elements.
			Memory Usage: 42 MB, less than 9.48% of Java online submissions for Top K Frequent Elements.
	 * 
	 * @assistedDebug yes
	 * 
	 * @time  O(n) worst case O(3n)
	 * @space O(n) worst case O(3n) answer inclusive
	 * @bcr   O(n)
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public int[] topKFrequent(int[] nums, int k) {
		if (nums == null)
			throw new IllegalArgumentException();

		int [] ans = new int[k];
		HashMap<Integer, Integer> numsMap = new HashMap<>();
		HashMap<Integer, List<Integer>> frequencyMap = new HashMap<>();
		List<Integer> auxList;   

		for (int num : nums) {
			numsMap.put(num, numsMap.getOrDefault(num, 0) + 1);
		}

		//reverse HashMap take care of repeated elements
		for (Integer num : numsMap.keySet()) {
			auxList = frequencyMap.getOrDefault(numsMap.get(num), new ArrayList<Integer>());

			if(auxList.size() == 0)
			{
				auxList.add(num);
				frequencyMap.put(numsMap.get(num), auxList);
			}
			else
			{
				auxList.add(num);
			}
		}


		//work our answer
		int i = nums.length; //maximum possible repeated elements
		int w = 0;
		while (k > 0 && i > 0)
		{
			auxList = frequencyMap.get(i);

			if (auxList != null && auxList.size() == 1)
			{
				ans[w++] = auxList.get(0);
				k--;
			}
			else if (auxList != null && auxList.size() > 1)
			{
				for (int j = 0; j < auxList.size() && k > 0; j++, k-- )
				{
					ans[w++] = auxList.get(j);
				}
			}
			i--;
		}
		return ans;
	}
}


/**
 * Interesting solution, very interesting solution
 * 
 * They combine Priority Queue and Hashmap to get instant access to hashmap's frequency
 * 
 * Wow, so cool
 * 
 * However I doubt that in the worst case scenario k = n we are going to have k log k = n log n.
 * 
 * But okay.
 * 
 * This solution is
 * 
 * 1) Hash values and frequencies
 * 
 * 2) create a Priority Queue where a array entry is bigger than the other if the value in the hashmap is bigger than the other
 * 
 * 3) poll k elements
 * 
 * 4) nice learning here, never used an hashmap and priority queue in this manner
 * 
 * 
 * @score
 * 		Runtime: 11 ms, faster than 55.36% of Java online submissions for Top K Frequent Elements.
		Memory Usage: 41.9 MB, less than 10.34% of Java online submissions for Top K Frequent Elements.
 * 
 * @author Nelson Costa
 *
 */
class TopKFrequentElementsSolution1 {
	
	/**
	 * up to date return type
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public int[] topKFrequent(int[] nums, int k) {
		// build hash map : character and how often it appears
		HashMap<Integer, Integer> count = new HashMap<>();
		for (int n: nums) {
			count.put(n, count.getOrDefault(n, 0) + 1);
		}

		// init heap 'the less frequent element first'
		PriorityQueue<Integer> heap =
				new PriorityQueue<Integer>((n1, n2) -> count.get(n1) - count.get(n2));

		// keep k top frequent elements in the heap
		for (int n: count.keySet()) {
			heap.add(n);
			if (heap.size() > k)
				heap.poll();
		}

		// build output list
		int [] top_k = new int [k];
		int i = 0;

		while (!heap.isEmpty())
		{
			top_k[i] = heap.poll();
			i++;
		}

		return top_k;
	}
}