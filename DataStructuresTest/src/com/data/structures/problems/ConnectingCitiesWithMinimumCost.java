package com.data.structures.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class ConnectingCitiesWithMinimumCost {

	public static void main(String[] args) {

	}


	/**
    @intuition
        This was the implementation of Prim's Algorithm


    @score
        Runtime: 48 ms, faster than 26.84% of Java online submissions for Connecting Cities With Minimum Cost.

        Memory Usage: 47.4 MB, less than 35.87% of Java online submissions for Connecting Cities With Minimum Cost.

	@time	O(ELogE)
	@space	O(V)

    @fail
        1) the way I stop the counting was wrong, I was stopping one iteration earlier
        2) I failed because it might not always be an element 1 to start...
        3) I didn't add a undirected graph but rather a directed graph
	 **/
	public int minimumCost(int n, int[][] conn) {

		//guards
		if (n == 1)
			return 0;

		if (n == 0)
			return -1;

		HashMap<Integer, List<Node>> g = new HashMap<>();
		boolean [] visited = new boolean[n + 1];
		int cost, from, to;

		//create Graph
		for (int i = 0; i < conn.length; i++) //O(E)
		{
			from = conn[i][0];
			to = conn[i][1];
			cost = conn[i][2];
			g.computeIfAbsent(from, k -> new ArrayList<Node>()).add(new Node(to, cost));
			g.computeIfAbsent(to, k -> new ArrayList<Node>()).add(new Node(from, cost));
		}

		//create priority queue
		PriorityQueue<Node> pq = new PriorityQueue<>(
				(a, b) -> Integer.compare(a.cost, b.cost));

		//add first node
		pq.add(new Node(conn[0][0], 0));

		Node curr;

		int nodeCount = 0;
		int mstCost = 0;

		//Perform the algorithm
		while (!pq.isEmpty())
		{
			curr = pq.poll();

			if (visited[curr.id])
				continue;

			mstCost += curr.cost;
			nodeCount ++;

			List<Node> edges = g.getOrDefault(curr.id, new ArrayList<Node>());

			for (Node nei : edges) // OE)
			{
				pq.add(nei);	//Log E, in worst case you have a star formation, and all other vertices are connected -> // O(E + E*Log E)
			}

			visited[curr.id] = true;

			if (nodeCount == n)
				return mstCost;
		}

		return -1;

	}

	class Node
	{
		int cost;
		int id;

		public Node(int id, int cost){
			this.id = id;
			this.cost = cost;
		}
	}
}

/*********************
 * OTHERS SOLUTIONS
 *********************/
/*********************
 * UNOFFICIAL SOLUTION
 *********************/
/**
 * Inteligent solution with union join
 * what we do is to use union find to join the least costly edges
 * if two edges are already connected we move to next edge
 * 
 * In a nutshell this was kruskal's algorithm
 * 
 * @author Nelson Costa
 *
 */
class ConnectingCitiesWithMinimumCostSolution1 {
	public int minimumCost(int N, int[][] connections) {

		if(connections.length<N-1)return -1;

		Arrays.sort(connections,(s1,s2)->s1[2]-s2[2]);

		int parent[]= new int[N+1];
		int i=1;
		while(i<=N){
			parent[i]=i;
			i++;
		}
		int edges=0;
		int cost=0;
		for(int []edge:connections){
			int rootA=getParent(edge[0],parent);
			int rootB=getParent(edge[1],parent);
			if(rootA!=rootB){
				parent[rootB]=rootA;
				cost+=edge[2];
				edges++;
				if(edges==N-1)return cost;
			}
		}
		for( i=1;i<=N;i++)
			System.out.println(i+": "+parent[i]);


		return -1;
	}

	int getParent(int node, int parent[]){
		if(node!=parent[node]){
			parent[node]=getParent(parent[node],parent);
		}
		return parent[node];
	}
}