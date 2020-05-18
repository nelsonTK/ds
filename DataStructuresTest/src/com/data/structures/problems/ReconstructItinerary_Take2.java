package com.data.structures.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * 
 * https://leetcode.com/problems/reconstruct-itinerary/
 * MEDIUM
 * Worst question ever, many subjective points there
 * it really sucks I do not recommend for the writing but recommend doing it by the exercise itself
 * @author Nelson Costa
 *
 */
public class ReconstructItinerary_Take2 extends LeetCodeExercise{

	static ReconstructItinerary_Take2 r = new ReconstructItinerary_Take2();
	
	public static void main(String[] args) {
		//TimeOut, need to have a third take

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
	
	private int itinerarySize;
	private HashMap<String, List<String>> graph;
	private HashMap<String, boolean[]> visited;
	
	/**
	 * @intuition
	 * 		To create a itinerary we will perform bfs on a graph where the the edges are sorted by a string
	 * 		We perform a backtracking to test if a itinerary is valid
	 * 
	 * 		Most of the challenges That I faced were cause by misunderstanding the question which is poorly written
	 * 
	 * 
	 * @score
			Runtime: 7 ms, faster than 35.86% of Java online submissions for Reconstruct Itinerary.
			Memory Usage: 40.4 MB, less than 82.09% of Java online submissions for Reconstruct Itinerary.
	 * 
	 * @fail
	 * 		1) null pointer exception because of destinations that had no key
	 * 		2) I was not adding the first node
	 * 
	 * @time 	????
	 * @space	O(V + E)
	 * @bcr
	 * 
	 * @param tickets
	 * @return
	 */
    public List<String> findItinerary(List<List<String>> tickets) {
        
    	//guards
    	
    	List<String> ans = new ArrayList<String>();
    	itinerarySize = tickets.size() + 1;
    	graph = new HashMap<>();
    	visited = new HashMap<>();
    	List<String> edges;
    	String from, dest;
    	
    	for(List<String> t : tickets)
    	{
    		from = t.get(0);
    		dest = t.get(1);
    		edges = graph.getOrDefault(from, new ArrayList<String>());
    		edges.add(dest);
    		graph.put(from, edges);
    	}
    	
    	for(String key : graph.keySet())
    	{
    		edges = graph.get(key);
    		Collections.sort(edges);
    		visited.put(key, new boolean[edges.size()]);
    	}
    	

		ans.add("JFK");
    	createItinerary("JFK", ans);
    	
    	return ans;
    }
    
    
	private boolean createItinerary(String node, List<String> ans) {

		if (ans.size() == itinerarySize)
			return true;
		
		
		List<String> edges = graph.getOrDefault(node, new ArrayList<String>());
		String nei;
		for (int i = 0; i < edges.size(); i++)
		{
			nei = edges.get(i);
			if (!visited.get(node)[i])
			{
				ans.add(nei);
				visited.get(node)[i] = true;
				
				if (!createItinerary(nei, ans))
				{
					ans.remove(ans.size() - 1);
					visited.get(node)[i] = false;
				}
				else
				{
					return true;
				}
			}
		}		
		
		
		return false;
	}
}

/**
 * @intuition
 * 		
 * 
 * @author Nelson Costa
 *
 */
class ReconstructItinerary_Take2Solution1 {
	  // origin -> list of destinations
	  HashMap<String, List<String>> flightMap = new HashMap<>();
	  HashMap<String, boolean[]> visitBitmap = new HashMap<>();
	  int flights = 0;
	  List<String> result = null;


	  public List<String> findItinerary(List<List<String>> tickets) {
	    // Step 1). build the graph first
	    for (List<String> ticket : tickets) {
	      String origin = ticket.get(0);
	      String dest = ticket.get(1);
	      if (this.flightMap.containsKey(origin)) {
	        List<String> destList = this.flightMap.get(origin);
	        destList.add(dest);
	      } else {
	        List<String> destList = new LinkedList<String>();
	        destList.add(dest);
	        this.flightMap.put(origin, destList);
	      }
	    }

	    // Step 2). order the destinations and init the visit bitmap
	    for (Map.Entry<String, List<String>> entry : this.flightMap.entrySet()) {
	      Collections.sort(entry.getValue());
	      this.visitBitmap.put(entry.getKey(), new boolean[entry.getValue().size()]);
	    }

	    this.flights = tickets.size();
	    LinkedList<String> route = new LinkedList<String>();
	    route.add("JFK");

	    // Step 3). backtracking
	    this.backtracking("JFK", route);
	    return this.result;
	  }

	  protected boolean backtracking(String origin, LinkedList<String> route) {
	    if (route.size() == this.flights + 1) {
	      this.result = (List<String>) route.clone();
	      return true;
	    }

	    if (!this.flightMap.containsKey(origin))
	      return false;

	    int i = 0;
	    boolean[] bitmap = this.visitBitmap.get(origin);

	    for (String dest : this.flightMap.get(origin)) {
	      if (!bitmap[i]) {
	        bitmap[i] = true;
	        route.add(dest);
	        boolean ret = this.backtracking(dest, route);
	        route.pollLast();
	        bitmap[i] = false;

	        if (ret)
	          return true;
	      }
	      ++i;
	    }

	    return false;
	  }
	}