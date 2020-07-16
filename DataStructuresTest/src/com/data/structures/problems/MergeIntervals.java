package com.data.structures.problems;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import com.data.structures.problems.ds.LeetCodeExercise;

public class MergeIntervals extends LeetCodeExercise{
	static MergeIntervals m = new MergeIntervals();
	public static void main(String[] args) {
		int [][] intervals = stringToMatrix("[[1,4],[4,5]]");
		m.merge(intervals);

	}

	/**
	 * 
	 * 
	 *	@intuition
	 		sort the intervals by final date
	 		check if the next element has a final date inside the previous

	 		________________
	 					_
	 				__
	 		______

	 		1 interval
	 				     _
	 				 _
	 		____

	 		3 intervals


	 * @comments
	   		This is a tough question, a lot of details have to be took into account.
	   		there are a lot of possibilities that I have to take into account.
	 *
	 * @improvements
	   		I could have improved how to create the final answer
     *
	 * @score
	 		Runtime: 6 ms, faster than 53.05% of Java online submissions for Merge Intervals.
			Memory Usage: 42.5 MB, less than 47.83% of Java online submissions for Merge Intervals.
	 *
	 * @debug
	  		yes
	 *
	 * @fail
	 * 		1) lack of merge, current was being added always
     *		2) didn't think in the case where the start of the following interval is below the previous, so I only updated the end and forgot about the start
     *		3) didnt thought in the case where there is a umberella interval
     *		4) again I have done very little test cases and ended up with de solution desing
    		more precisely the sorting part
     *
	 * @time  O(NLogN)
	 * @space O(N)
	 * 
	 * @param intervals
	 * @return
	 */
	public int[][] merge(int[][] intervals) {

		if (intervals == null || intervals.length == 0)
		{
			return new int [0][0];
		}

		if (intervals.length == 1)
		{
			return intervals;
		}

		Arrays.sort(intervals, (a,b) -> Integer.compare(b[1], a[1]));
		ArrayDeque<int[]> q = new ArrayDeque<>();

		int [] prev = new int[]{intervals[0][0], intervals[0][1]};
		int [] cur = null;

		for (int i = 1; i <= intervals.length - 1; i++)
		{
			cur = intervals[i];

			if (cur[1] <= prev[1] && cur[1] >= prev[0])
			{
				prev[0] = Math.min(prev[0], cur[0]);
			}
			else
			{
				q.push(prev);
				prev = new int[]{cur[0], cur[1]};
			}
		}

		//add last element
		if(cur[1] < prev[0] || cur[0] > prev[1])
		{
			q.push(prev);
			q.push(cur);
		}
		else 
		{
			q.push(prev);
		}


		int i = 0;
		int[][] ans = new int[q.size()][2];
		while(!q.isEmpty())
		{
			ans[i] = q.poll();
			i++;
		}

		return ans;
	}
}

/*********************
 * OTHERS SOLUTIONS
 *********************/

/**
 * Simple solution 
 * 
 * Sorts the input array by start date.
 * than if the previous element has a end before the current, then we know they are different
 * else we merge
 * 
 * a structure like a linkedlist is used to have the answer
 * pretty neat
 * 
 * 
 * @author Nelson Costa
 *
 */
class MergeIntervalsSolution2 {
	private class IntervalComparator implements Comparator<int[]> {
		@Override
		public int compare(int[] a, int[] b) {
			return a[0] < b[0] ? -1 : a[0] == b[0] ? 0 : 1;
		}
	}

	public int[][] merge(int[][] intervals) {
		Collections.sort(Arrays.asList(intervals), new IntervalComparator());

		LinkedList<int[]> merged = new LinkedList<>();
		for (int[] interval : intervals) {
			// if the list of merged intervals is empty or if the current
			// interval does not overlap with the previous, simply append it.
			if (merged.isEmpty() || merged.getLast()[1] < interval[0]) {
				merged.add(interval);
			}
			// otherwise, there is overlap, so we merge the current and previous
			// intervals.
			else {
				merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
			}
		}

		return merged.toArray(new int[merged.size()][]);
	}
}


/**
 * Graph Based Solution
 * it states that connected intervals are like connected components of a graph
 * 
 * and it is a undirected graph, so finding components its simpler
 * 
 * so we just need to find the components and get the max and min of each one of them
 * 
 * @time 	O(N^2)
 * @space 	O(N^2)
 * 
 * @author Nelson Costa
 *
 */
class MergeIntervalsSolution1 {
	private Map<int[], List<int[]>> graph;
	private Map<Integer, List<int[]>> nodesInComp;
	private Set<int[]> visited;

	// return whether two intervals overlap (inclusive)
	private boolean overlap(int[] a, int[] b) {
		return a[0] <= b[1] && b[0] <= a[1];
	}

	// build a graph where an undirected edge between intervals u and v exists
	// iff u and v overlap.
	private void buildGraph(int[][] intervals) {
		graph = new HashMap<>();
		for (int[] interval : intervals) {
			graph.put(interval, new LinkedList<>());
		}

		for (int[] interval1 : intervals) {
			for (int[] interval2 : intervals) {
				if (overlap(interval1, interval2)) {
					graph.get(interval1).add(interval2);
					graph.get(interval2).add(interval1);
				}
			}
		}
	}

	// merges all of the nodes in this connected component into one interval.
	private int[] mergeNodes(List<int[]> nodes) {
		int minStart = nodes.get(0)[0];
		for (int[] node : nodes) {
			minStart = Math.min(minStart, node[0]);
		}

		int maxEnd = nodes.get(0)[1];
		for (int[] node : nodes) {
			maxEnd = Math.max(maxEnd, node[1]);
		}

		return new int[] {minStart, maxEnd};
	}

	// use depth-first search to mark all nodes in the same connected component
	// with the same integer.
	private void markComponentDFS(int[] start, int compNumber) {
		Stack<int[]> stack = new Stack<>();
		stack.add(start);

		while (!stack.isEmpty()) {
			int[] node = stack.pop();
			if (!visited.contains(node)) {
				visited.add(node);

				if (nodesInComp.get(compNumber) == null) {
					nodesInComp.put(compNumber, new LinkedList<>());
				}
				nodesInComp.get(compNumber).add(node);

				for (int[] child : graph.get(node)) {
					stack.add(child);
				}
			}
		}
	}

	// gets the connected components of the interval overlap graph.
	private void buildComponents(int[][] intervals) {
		nodesInComp = new HashMap<>();
		visited = new HashSet<>();
		int compNumber = 0;

		for (int[] interval : intervals) {
			if (!visited.contains(interval)) {
				markComponentDFS(interval, compNumber);
				compNumber++;
			}
		}
	}

	public int[][] merge(int[][] intervals) {
		buildGraph(intervals);
		buildComponents(intervals);

		// for each component, merge all intervals into one interval.
		List<int[]> merged = new LinkedList<>();
		for (int comp = 0; comp < nodesInComp.size(); comp++) {
			merged.add(mergeNodes(nodesInComp.get(comp)));
		}

		return merged.toArray(new int[merged.size()][]);
	}
}
