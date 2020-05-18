package com.data.structures.problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.data.structures.problems.ds.LeetCodeExercise;

public class CheapestFlightsWithinKStops_Take2 extends LeetCodeExercise {

	static CheapestFlightsWithinKStops_Take2 c = new CheapestFlightsWithinKStops_Take2();
	public static void main(String[] args) {
		int n = 3, src = 0, dst = 2, k = 1;
		int [][] flights = stringToMatrix("[[0,1,100],[1,2,100],[0,2,500]]");


		flights = stringToMatrix("[[3,4,4],[2,5,6],[7,4,4],[6,2,10],[6,8,6],[7,9,4],[1,5,4],[1,0,4],[9,7,3],[7,0,5],[6,5,8],[1,7,6],[4,0,9],[5,9,1]]"); //subset to ease debugging
		n = 10;
		src = 6;
		dst = 0;
		k = 7;

		
		flights = stringToMatrix("[[0,12,28],[5,6,39],[8,6,59],[13,15,7],[13,12,38],[10,12,35],[15,3,23],[7,11,26],[9,4,65],[10,2,38],[4,7,7],[14,15,31],[2,12,44],[8,10,34],[13,6,29],[5,14,89],[11,16,13],[7,3,46],[10,15,19],[12,4,58],[13,16,11],[16,4,76],[2,0,12],[15,0,22],[16,12,13],[7,1,29],[7,14,100],[16,1,14],[9,6,74],[11,1,73],[2,11,60],[10,11,85],[2,5,49],[3,4,17],[4,9,77],[16,3,47],[15,6,78],[14,1,90],[10,5,95],[1,11,30],[11,0,37],[10,4,86],[0,8,57],[6,14,68],[16,8,3],[13,0,65],[2,13,6],[5,13,5],[8,11,31],[6,10,20],[6,2,33],[9,1,3],[14,9,58],[12,3,19],[11,2,74],[12,14,48],[16,11,100],[3,12,38],[12,13,77],[10,9,99],[15,13,98],[15,12,71],[1,4,28],[7,0,83],[3,5,100],[8,9,14],[15,11,57],[3,6,65],[1,3,45],[14,7,74],[2,10,39],[4,8,73],[13,5,77],[10,0,43],[12,9,92],[8,2,26],[1,7,7],[9,12,10],[13,11,64],[8,13,80],[6,12,74],[9,7,35],[0,15,48],[3,7,87],[16,9,42],[5,16,64],[4,5,65],[15,14,70],[12,0,13],[16,14,52],[3,10,80],[14,11,85],[15,2,77],[4,11,19],[2,7,49],[10,7,78],[14,6,84],[13,7,50],[11,6,75],[5,10,46],[13,8,43],[9,10,49],[7,12,64],[0,10,76],[5,9,77],[8,3,28],[11,9,28],[12,16,87],[12,6,24],[9,15,94],[5,7,77],[4,10,18],[7,2,11],[9,5,41]]");
		//flights = stringToMatrix("[[3,4,4],[2,5,6],[7,4,4],[6,2,10],[6,8,6],[7,9,4],[1,5,4],[1,0,4],[9,7,3],[7,0,5],[6,5,8],[1,7,6],[4,0,9],[5,9,1]]"); //subset to ease debugging
		flights = stringToMatrix("[[0,12,28],[5,6,39],[8,6,59],[13,15,7],[13,12,38],[10,12,35],[15,3,23],[7,11,26],[9,4,65],[10,2,38],[4,7,7],[14,15,31],[2,12,44],[8,10,34],[13,6,29],[5,14,89],[11,16,13],[7,3,46],[10,15,19],[12,4,58],[13,16,11],[16,4,76],[2,0,12],[15,0,22],[16,12,13],[7,1,29],[7,14,100],[16,1,14],[9,6,74],[11,1,73],[2,11,60],[10,11,85],[2,5,49],[3,4,17],[4,9,77],[16,3,47],[15,6,78],[14,1,90],[10,5,95],[1,11,30],[11,0,37],[10,4,86],[0,8,57],[6,14,68],[16,8,3],[13,0,65],[2,13,6],[5,13,5],[8,11,31],[6,10,20],[6,2,33],[9,1,3],[14,9,58],[12,3,19],[11,2,74],[12,14,48],[16,11,100],[3,12,38],[12,13,77],[10,9,99],[15,13,98],[15,12,71],[1,4,28],[7,0,83],[3,5,100],[8,9,14],[15,11,57],[3,6,65],[1,3,45],[14,7,74],[2,10,39],[4,8,73],[13,5,77],[10,0,43],[12,9,92],[8,2,26],[1,7,7],[9,12,10],[13,11,64],[8,13,80],[6,12,74],[9,7,35],[0,15,48],[3,7,87],[16,9,42],[5,16,64],[4,5,65],[15,14,70],[12,0,13],[16,14,52],[3,10,80],[14,11,85],[15,2,77],[4,11,19],[2,7,49],[10,7,78],[14,6,84],[13,7,50],[11,6,75],[5,10,46],[13,8,43],[9,10,49],[7,12,64],[0,10,76],[5,9,77],[8,3,28],[11,9,28],[12,16,87],[12,6,24],[9,15,94],[5,7,77],[4,10,18],[7,2,11],[9,5,41]]");
		n = 17;
		src = 13;
		dst = 4;
		k = 13;


		System.out.println(c.findCheapestPrice(n, flights, src, dst, k));
	}

	HashMap<Integer, List<Node>> graph;
	boolean [] visited;
	int k;
	Cost [] memo;

	/**
	 * 
	 * 
	 * @fail
	 * 		1) OutOfBounds, visited and memo array was too short... basic, elementary errors
	 * 		2) wrong price, Cost constructor arguments were swapped
	 * 		3) I was overriding references
	 * @debug 
	 * 		yes
	 * 
	 * @param n
	 * @param flights
	 * @param src
	 * @param dst
	 * @param k
	 * @return
	 */
	public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
		this.k = k;
		graph = new HashMap<>();
		visited = new boolean[n];
		memo = new Cost[n];
		List<Node> edges;
		int from, to, price;

		for(int [] f : flights)
		{
			from = f[0];
			to = f[1];
			price = f[2];
			edges = graph.getOrDefault(from, new ArrayList<Node>());
			edges.add(new Node(to, price));
			graph.put(from, edges);
		}

		Cost ans = findMin(src, dst, -1);

		return ans.price == Integer.MAX_VALUE ? -1 : ans.price;
	}

	private Cost findMin(int node, int dst, int curHops) {

		//Node found
		if (node == dst)
		{
			return new Cost(0, -1);
		}

		//Checking cache
		if (memo[node] != null)
		{
			if (curHops - memo[node].inBetweenHops >= 0)
			{
				return memo[node];
			}
			else
			{
				return new Cost(Integer.MAX_VALUE, Integer.MAX_VALUE);
			}
		}

		//Unreachable from this way
		if (curHops + 1 > k)
		{
			return new Cost(Integer.MAX_VALUE, Integer.MAX_VALUE);
		}


		//search
		Cost minCost = new Cost(Integer.MAX_VALUE, Integer.MAX_VALUE);
		Cost neiCost;
		List<Node> edges = graph.getOrDefault(node, new ArrayList<Node>());

		for (Node nei : edges)
		{
			if (!visited[nei.id])
			{
				visited[nei.id] = true;
				neiCost = findMin(nei.id, dst, curHops + 1);


				if (neiCost.price < minCost.price && neiCost.inBetweenHops <= k)
				{
					minCost = neiCost;
					if (memo[nei.id] != null && memo[nei.id].price > neiCost.price)
						memo[nei.id] = new Cost(neiCost.price, neiCost.inBetweenHops);
				}


//				if (neiCost.price != Integer.MAX_VALUE)
//				{
//					neiCost.price+=nei.price;
//					neiCost.inBetweenHops++;
//				}
				visited[nei.id] = false;

			}
		}

		return minCost;
	}

	class Node {
		int id;
		int price;

		public Node(int id, int price) {
			super();
			this.id = id;
			this.price = price;
		}

	}

	class Cost{
		int inBetweenHops;
		int price;
		public Cost(int price, int inBetweenHops) {
			super();
			this.inBetweenHops = inBetweenHops;
			this.price = price;
		}
	}
}
