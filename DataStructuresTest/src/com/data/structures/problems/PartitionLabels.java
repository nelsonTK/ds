package com.data.structures.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/partition-labels/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class PartitionLabels extends LeetCodeExercise {

	public static void main(String[] args) {
		String s = "ababcbacadefegdehijhklij";
		s = "caedbdedda";
		PartitionLabels p = new PartitionLabels();
		Solution sol = p.new Solution();
		for(int i : sol.partitionLabels(s))
		{
			System.out.println(i);
		}

	}


	/**
	 * 

		@intuition
			Union Find.
			Create HashMap
			Create UnionFind Data structure
			
		
		@score
			Runtime: 13 ms, faster than 8.42% of Java online submissions for Partition Labels.
			Memory Usage: 39.3 MB, less than 39.85% of Java online submissions for Partition Labels.
		
		@fail
		    1) didn't updated the visited element when counting the elements
		    2) forgot to add clause for stoping the chainUnion
		    	2.1) mistake a second time becau of nothing much about the comparision
		    	2.2) again I miss the correct comparision
		    3) null pointer exception because I was trying to access the map hashmap with a int instead of a char
	    	4) accidentally removed the first element of the array
	 */
	class Solution {
		HashMap<Character, Integer> map;

		public Solution(){

			map = new HashMap<>(); //charToMinIndex
			map.put('a', -1);
			for (int i = 1; i < 26; i++)
			{
				map.put((char)('a' + i), -1);
			}
		}

		public List<Integer> partitionLabels(String s) {

			if (s.length() == 1)
				return Arrays.asList(1);

			Union uni = new Union(s.length());        
			map.put(s.charAt(0), 0);

			for (int i = 1; i < s.length(); i++)
			{        
				if(map.get(s.charAt(i)) != -1)
				{
					//chain Union
					chainUnion(i, uni, s);
					map.put(s.charAt(i), i);
				}
				else
				{
					map.put(s.charAt(i), i);
				}
			}

			return countComponents(uni, s);
		}

		//O(N)
		private void chainUnion(int start, Union uni, String s)
		{
			//while the start element is not the same as the first element continue
			int current = start - 1;
			boolean equalFound = false;
			while(!equalFound)	
			{
				uni.union(current, start);

				if(s.charAt(current) == s.charAt(start))
					equalFound = true;
				current--;           
			}
		}

		//O(N)
		private List<Integer> countComponents(Union uni, String s)
		{
			TreeMap<Integer, Integer> rootToSize = new TreeMap<>();
			List<Integer> ans = new ArrayList<>();

			for (int element : uni.parent)
			{
				int root = uni.find(element);
				rootToSize.put(root, rootToSize.getOrDefault(root, 0) + 1);
			}

			for (Integer i : rootToSize.keySet())
			{
				ans.add(rootToSize.get(i));
			}

			return ans;
		}

		class Union{
			int [] parent;
			int [] rank;
			int [] size;

			public Union(int s){
				parent = new int[s];
				rank = new int[s];
				size = new int[s];
				Arrays.fill(size, 1);

				for (int i = 0; i < s; i++)
				{
					parent[i] = i;
				}
			}

			//O(1)
			public void union(int a, int b){
				int ra = find(a);
				int rb = find(b);



				if (ra > rb)
				{
					parent[rb] = ra;
				}
				else{
					parent[ra] = rb;
				}
			}

			//path compression
			//O(1) amortized
			public int find(int a){
				int rootA = a;

				while (rootA != parent[rootA])
				{
					rootA = parent[rootA];
				}

				int next;
				while (a != parent[a])
				{
					next = parent[a];
					parent[a] = rootA;
					a = next;
				}

				return rootA;
			}

		}
	}
}


/**

	@intuition
		Greedy Solution

		the goal here is to mark the last position of each character

		and traverse the string keeping track of the furthest element of that group 

		until you find a element which its last index is equals to the current iteration

		that marks the end of the group and the start of another

		is a smart greedy solution

	@score
		Runtime: 3 ms, faster than 85.75% of Java online submissions for Partition Labels.
		Memory Usage: 37.9 MB, less than 81.72% of Java online submissions for Partition Labels.


 * @author Nelson Costa
 *
 */
class PartitionLabelsSolution1 {
	public List<Integer> partitionLabels(String S) {
		int[] last = new int[26];
		for (int i = 0; i < S.length(); ++i)
			last[S.charAt(i) - 'a'] = i;

		int j = 0, anchor = 0;
		List<Integer> ans = new ArrayList();
		for (int i = 0; i < S.length(); ++i) {
			j = Math.max(j, last[S.charAt(i) - 'a']);
			if (i == j) {
				ans.add(i - anchor + 1);
				anchor = i + 1;
			}
		}
		return ans;
	}
}


/**
 * Intervals/Meeting Rooms like solution, similar to meeting rooms
 * 
 * @author Nelson Costa
 *
 */
class PartitionLabelsUnofficialSolution1 {

	public List partitionLabels(String S) {
		Map<Character , Integer> startMap = new HashMap<>();
		Map<Character , Integer> endMap = new HashMap<>();

		for(int i = 0 ; i < S.length() ; i++)
		{
			if(startMap.get(S.charAt(i)) == null)
				startMap.put(S.charAt(i) , i);
			endMap.put(S.charAt(i) , i);
		}
		int[] start = new int[startMap.size()];
		int[] end = new int[endMap.size()];
		int ind = 0;

		for(Map.Entry<Character , Integer> mp : startMap.entrySet())
			start[ind++] = mp.getValue();

		ind = 0;
		for(Map.Entry<Character , Integer> mp : endMap.entrySet())
			end[ind++] = mp.getValue();

		Arrays.sort(start);
		Arrays.sort(end);
		int n = start.length;
		int i = 0;
		int j = 0;

		List<Integer> ans = new ArrayList<>();
		int c = 0;
		int prev = -1;
		while(j < n)
		{
			if(i < n && start[i] <= end[j])
			{
				if(prev == -1)
					prev = start[i];
				i++;
				c++;
			}
			else
			{
				c--;
				if(c == 0)
				{
					ans.add(end[j] - prev + 1);
					prev = -1;
				}
				j++;
			}
		}

		return ans;
	}
}