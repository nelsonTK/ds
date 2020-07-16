package com.data.structures.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/reconstruct-itinerary/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class ReconstructItinerary extends LeetCodeExercise{

	static ReconstructItinerary r = new ReconstructItinerary();
	public static void main(String[] args) {
		List<List<String>> tickets = new ArrayList<List<String>>();
		List<String> s1 = Arrays.asList("MUC", "LHR");
		List<String> s2 = Arrays.asList("JFK", "MUC");
		List<String> s3 = Arrays.asList("SFO", "SJC");
		List<String> s4 = Arrays.asList("LHR", "SFO");

		tickets.add(s1);
		tickets.add(s2);
		tickets.add(s3);
		tickets.add(s4);
		
		printArray(r.findItinerary(tickets).toArray());
		
		
	}
	
	/*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * 
	 *  @fail
	 *  	1) forgot to add the test cases for tickets == null;
	 *  	2) I forgot that not all destinations have a source
	 *  	3) I was removing the last node always without confirming if edges had at least a number
	 *  	4) I didn't thought in the case where the min node hash no connections but there are more nodes to explore. and then the graph gets empty.
	 * 
	 * @time	(O + 2V) 2 is for removal, with a linked list it would be just V.
	 * @space	(O + V)
	 * @bcr		
	 * 
	 * @optimization
	 * 		Use linked List to avoid bad time complexity
	 * 
	 * @param tickets
	 * @return
	 */
	public List<String> findItinerary(List<List<String>> tickets) {
		
		if (tickets == null || tickets.size() == 0)
		{
			return new  ArrayList<>();
		}
		
		List<String> ans = new  ArrayList<>();

		HashMap<String, List<String>> graph = new HashMap<>();
		Stack<String> s = new Stack<>();
		s.push("JFK");

		String from, to;
		List<String> edges;

		//create graph
		for (List<String> t : tickets) {
			from = t.get(0);
			to = t.get(1);
			edges = graph.getOrDefault(from, new ArrayList<String>());
			edges.add(to);
			graph.put(from, edges); 
		}

		String node, min, dest;
		int minIdx = 0;
		while (!s.isEmpty())
		{
			node = s.pop();
			ans.add(node);
			min = null; //min of the edges
			minIdx = 0;
			edges = graph.getOrDefault(node, new ArrayList<String>());

			//find the minimum between its edges
			for (int i = 0; i < edges.size(); i++)
			{
				dest = edges.get(i);

				if (min != null &&  dest.compareTo(min) < 0)
				{
					min = dest; 
					minIdx = i;
				}
				else if (min == null)
				{
					min = dest;
					minIdx = i;
				}
			}


			if (min != null)
			{
				s.push(min);			
				edges.remove(minIdx);

			}
		}


		return ans;
	}
}
