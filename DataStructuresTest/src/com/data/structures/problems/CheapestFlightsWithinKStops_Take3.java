package com.data.structures.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/cheapest-flights-within-k-stops/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class CheapestFlightsWithinKStops_Take3 extends LeetCodeExercise {

	static CheapestFlightsWithinKStops_Take3 c = new CheapestFlightsWithinKStops_Take3();
	public static void main(String[] args) {
		int n = 4;
		int [][] matrix = stringToMatrix("[[0,1,1],[0,2,5],[1,2,1],[2,3,1]]");
		int src = 0;
		int dst = 3;
		int k = 1;
		System.out.println(c.findCheapestPrice(n, matrix, src, dst, k));
	}



	/**

    @intuition
    	I just applied a adapted lazy Dijkstra Algorithm approach on this problem.
    	
    	The adjustments included allow the answer to go throgh less optimal cost in general, be optimal for a given k
    
    	
    	
    	
    @score
    		Runtime: 9 ms, faster than 63.17% of Java online submissions for Cheapest Flights Within K Stops.
			Memory Usage: 41.8 MB, less than 5.55% of Java online submissions for Cheapest Flights Within K Stops.

    @fail
        1) nullpointer, because of null edgest.... god damn, never gets old....
        2) I was not returning -1 if the node was not found
        3) there was a case that was not took into account, which is when the answer is through the node that has bigger cost
    
    @time	E + VLogE (I'm not sure)
    @space	
    @bcr


	 **/
	public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

		HashMap<Integer, List<Node>> graph = new HashMap<>();
		HashMap<Integer, Integer> dist = new HashMap<>();

		List<Node> edges;
		int from, to, cost;

		for (int [] f : flights) // E
		{
			edges = graph.getOrDefault(f[0], new ArrayList<Node>());
			from = f[0];
			to = f[1];
			cost = f[2];

			edges.add(new Node (to, cost, 0));
			graph.put(from, edges);
		}

		for (int i = 0; i < n; i++) // V
		{
			dist.put(i, Integer.MAX_VALUE);
		}

		PriorityQueue<Node> pq = new PriorityQueue<> ((a, b) -> Integer.compare(a.cost, b.cost));

		pq.offer(new Node(src, 0, -1));
		Node node;

		while (!pq.isEmpty()) // ELogV
		{
			node = pq.poll();

			//avoid processing if max distance was already reached
			if (node.distance + 1 > k && node.id != dst)
				continue;
			
			dist.put(node.id, node.cost); //most of the times redundant except for the first time

			//break execution id node was found
			if (node.id == dst)
				break;
			
			edges = graph.getOrDefault(node.id, new ArrayList<Node>());
			for (Node nei : edges)
			{
				if (node.distance + 1 <= k)
				{ 
					pq.add(new Node(nei.id, nei.cost + node.cost, node.distance + 1));
				}
			}

			if (node.id == dst)
			{
				break;
			}
		}

		int ans = dist.get(dst);
		return ans == Integer.MAX_VALUE ? -1 : ans;
	} 

	class Node{
		int id;
		int cost;
		int distance;

		Node(int i, int c, int d)
		{
			id = i;
			cost = c;
			distance = d;
		}
	}
}

/*********************
* OTHERS SOLUTIONS
*********************/

/**
 * Solution implemented with adjacency matrix and the corresponding cost
 * 
 * uses magic numbers, very ugly.
 * 
 * 	As I Understood, "(k+1) * 1000 + nei "  && "k * 1000 + place"
	are used to put and get from the hashmap pairs of places and k hops and its cost. 
	So you can have multiple entries for the same place, but with different k distances enconded in the format  (hops * 1000 + place)
 * 
 * 
 * @timetook 6ms
 * 
 * @author Nelson Costa
 *
 */
class CheapestFlightsWithinKStops_Take3Solution2 {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[][] graph = new int[n][n];
        
        //creating adjacency matrix
        for (int[] flight: flights)
            graph[flight[0]][flight[1]] = flight[2];

        Map<Integer, Integer> best = new HashMap();

        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);
        pq.offer(new int[]{0, 0, src});

        while (!pq.isEmpty()) {
            int[] info = pq.poll();
            int cost = info[0], k = info[1], place = info[2];
            if (k > K+1 || cost > best.getOrDefault(k * 1000 + place, Integer.MAX_VALUE))
                continue;
            if (place == dst)
                return cost;

            for (int nei = 0; nei < n; ++nei) if (graph[place][nei] > 0) {
                int newcost = cost + graph[place][nei];
                if (newcost < best.getOrDefault((k+1) * 1000 + nei, Integer.MAX_VALUE)) {
                    pq.offer(new int[]{newcost, k+1, nei});
                    best.put((k+1) * 1000 + nei, newcost);
                }
            }
        }

        return -1;
    }
}

/**
 * I Cannot understand this solution very well
 * @author Nelson Costa
 *
 */
class CheapestFlightsWithinKStops_Take3Solution1 {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[][] dist = new int[2][n];
        int INF = Integer.MAX_VALUE / 2;
        Arrays.fill(dist[0], INF);
        Arrays.fill(dist[1], INF);
        dist[0][src] = dist[1][src] = 0;

        for (int i = 0; i <= K; ++i)
            for (int[] edge: flights)
                dist[i&1][edge[1]] = Math.min(dist[i&1][edge[1]], dist[~i&1][edge[0]] + edge[2]);

        return dist[K&1][dst] < INF ? dist[K&1][dst] : -1;
    }
}