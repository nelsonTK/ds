package com.data.structures.problems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class NumberofConnectedComponentsinanUndirectedGraph extends LeetCodeExercise{
	static NumberofConnectedComponentsinanUndirectedGraph num = new NumberofConnectedComponentsinanUndirectedGraph();
	
	public static void main(String[] args) {

		int n = 5;
		int [][] edges = stringToMatrix("[[0, 1], [1, 2], [3, 4]]");
		
		System.out.println(num.countComponents(n, edges));
	}

	/**
	 * @score
	 *		Path Compression optimization With constant space(Best)
	 *			Runtime: 3 ms, faster than 70.66% of Java online submissions for Number of Connected Components in an Undirected Graph.
	 *			Memory Usage: 39.9 MB, less than 100.00% of Java online submissions for Number of Connected Components in an Undirected Graph.
	 *
	 *
	 * 		Path Compression optimization With Set<Integer>
	 *			Runtime: 6 ms, faster than 42.38% of Java online submissions for Number of Connected Components in an Undirected Graph.
	 *			Memory Usage: 40.3 MB, less than 100.00% of Java online submissions for Number of Connected Components in an Undirected Graph.
	 *
	 *
	 * 		Path Compression Otimization with List<Integer>
	 * 			Runtime: 5 ms, faster than 50.53% of Java online submissions for Number of Connected Components in an Undirected Graph.
	 *			Memory Usage: 39.5 MB, less than 100.00% of Java online submissions for Number of Connected Components in an Undirected Graph.
	 *
	 *			Runtime: 4 ms, faster than 57.39% of Java online submissions for Number of Connected Components in an Undirected Graph.
	 *			Memory Usage: 39.6 MB, less than 100.00% of Java online submissions for Number of Connected Components in an Undirected Graph.
	 *
	 * 		Without Path Compression
	 * 			Runtime: 8 ms, faster than 26.60% of Java online submissions for Number of Connected Components in an Undirected Graph.
	 *			Memory Usage: 39.7 MB, less than 100.00% of Java online submissions for Number of Connected Components in an Undirected Graph.
	 *
	 * @time	
	 * @space	
	 * 
	 * @param n
	 * @param edges
	 * @return
	 */
	public int countComponents(int n, int[][] edges) {
		Union uni = new Union(n);

		for (int [] edge : edges)
		{
			uni.union(edge[0], edge[1]);
		}

		return uni.getComponents();

	}


	class Union{
		int[] arr;

		public Union (int length)
		{
			arr = new int[length];

			for(int i = 0; i < length; i++)
			{
				arr[i] = i;
			}
		}

		public void union(int a, int b)
		{
			if( a > b)
				union(b, a);

			int rootA = findPathCompression2(a);
			int rootB = findPathCompression2(b);

			arr[rootA] = rootB;
		}

		public int find(int a){

			int root = arr[a];

			while (root != arr[root])
			{
				root = arr[root];
			}

			return root;
		}


		/**
		 * Works faster with list but repeated elements will be added
		 * @param a
		 * @return
		 */
		public int findPathCompression1(int a){

			int root = arr[a];
			//Set<Integer> path = new HashSet<>();
			List<Integer> path = new ArrayList<Integer>();
			while (root != arr[root])
			{
				path.add(root);
				root = arr[root];
			}

			for (Integer node : path)
			{
				arr[node] = root;
			}

			return root;
		}
		

		/**
		 * Optimized path compression without aditional space
		 * @param a
		 * @return
		 */
		public int findPathCompression2(int a){

			int root = arr[a];
			while (root != arr[root])
			{
				root = arr[root];
			}

			int next;
			while (a != root)
			{
				next = arr[a];
				arr[a] = root;
				a = next;
			}

			return root;
		}

		public int find(int a, boolean[] visited){

			int root = arr[a];

			while (root != arr[root])
			{
				visited[root] = true;
				root = arr[root];

			}

			return root;
		}

		public int getComponents(){
			Set<Integer> set = new HashSet<>();
			boolean [] visited = new boolean[arr.length];

			for (int i = 0; i < arr.length; i++)
			{
				if (!visited[i])
				{
					set.add(find(i, visited));
				}
			}            
			return set.size();
		}
	}
}
