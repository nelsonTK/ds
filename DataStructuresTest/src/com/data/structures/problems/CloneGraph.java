package com.data.structures.problems;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * https://leetcode.com/problems/clone-graph/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class CloneGraph {


	/*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * @intuition
	 * 		The intuition is to just process each node and its neighbors and prevent repeted elements to be enqueued
	 * 		I needed some attempts to make this right, becaus I over complicated by going dfs
	 * 		But well I failed and needed to get back to the bfs solution
	 * 
	 * @optimizations
	 * 		I could have done a better job to reduce the number of data used.
	 * 		instead of using a set I could have just used an hashmap with the original and the clone.
	 * 
	 * @score
	 *		Runtime: 26 ms, faster than 74.55% of Java online submissions for Clone Graph.
	 *		Memory Usage: 39.1 MB, less than 10.59% of Java online submissions for Clone Graph.
	 *
	 * @time
	 * 		O(N) X
	 * 		correct
	 * 		O(N + M) 
	 * 		N nodes
	 * 		M + edges
	 * 
	 * @space
	 * 		O(N)
	 * 
	 * @author Nelson Costa
	 *
	 */
	class Solution {
		public Node cloneGraph(Node node) {

			//I put the original in the q
			//If visited return
			//Clone if the node is not in the map, else return clone
			//if not visited
			//I iterate the original neis
			//if neis are not in the map I clone them
			//and connect the clones

			if (node == null)
				return null;

			Set<Integer> visited = new HashSet<>();
			Queue<Node> q = new ArrayDeque<>();
			HashMap<Integer, Node> clonemap = new HashMap<>();

			//setup answer
			q.add(node);
			Node ans = new Node(node.val);
			clonemap.put(ans.val, ans);

			Node clone, neiClone;
			Node cur;

			while (!q.isEmpty())
			{
				cur = q.poll();

				if (visited.contains(cur.val))
					continue;

				clone = getClone(cur, clonemap);

				for(Node nei: cur.neighbors)
				{
					neiClone = getClone(nei, clonemap);
					clone.neighbors.add(neiClone);

					if (!visited.contains(nei.val))
					{
						q.add(nei);
					}
				}   
				visited.add(cur.val);
			}	        
			return ans;
		}

		private Node getClone(Node node, HashMap<Integer, Node> map){

			if( !map.containsKey(node.val))
			{
				Node clone = new Node(node.val);
				map.put(clone.val, clone);
				return clone;
			}

			return map.get(node.val);
		}
	}


	// Definition for a Node.
	class Node {
		public int val;
		public List<Node> neighbors;

		public Node() {
			val = 0;
			neighbors = new ArrayList<Node>();
		}

		public Node(int _val) {
			val = _val;
			neighbors = new ArrayList<Node>();
		}

		public Node(int _val, ArrayList<Node> _neighbors) {
			val = _val;
			neighbors = _neighbors;
		}
	}
}

/**
 * Solution with bread first search the main difference was the use of the hashmap with the mapping for the old and the new value.
 * 
 * @author Nelson Costa
 *
 */
class CloneGraphSolution2 {
	public Node cloneGraph(Node node) {
		if (node == null) {
			return node;
		}

		// Hash map to save the visited node and it's respective clone
		// as key and value respectively. This helps to avoid cycles.
		HashMap<Node, Node> visited = new HashMap();

		// Put the first node in the queue
		LinkedList<Node> queue = new LinkedList<Node> ();
		queue.add(node);
		// Clone the node and put it in the visited dictionary.
		visited.put(node, new Node(node.val, new ArrayList()));

		// Start BFS traversal
		while (!queue.isEmpty()) {
			// Pop a node say "n" from the from the front of the queue.
			Node n = queue.remove();
			// Iterate through all the neighbors of the node "n"
			for (Node neighbor: n.neighbors) {
				if (!visited.containsKey(neighbor)) {
					// Clone the neighbor and put in the visited, if not present already
					visited.put(neighbor, new Node(neighbor.val, new ArrayList()));
					// Add the newly encountered node to the queue.
					queue.add(neighbor);
				}
				// Add the clone of the neighbor to the neighbors of the clone node "n".
				visited.get(n).neighbors.add(visited.get(neighbor));
			}
		}

		// Return the clone of the node from visited.
		return visited.get(node);
	}

	// Definition for a Node.
	class Node {
		public int val;
		public List<Node> neighbors;

		public Node() {
			val = 0;
			neighbors = new ArrayList<Node>();
		}

		public Node(int _val) {
			val = _val;
			neighbors = new ArrayList<Node>();
		}

		public Node(int _val, ArrayList<Node> _neighbors) {
			val = _val;
			neighbors = _neighbors;
		}
	}
}


/**
 * DFS Solution
 * @author Nelson Costa
 *
 */
class CloneGraphSolution1 {
    private HashMap <Node, Node> visited = new HashMap <> ();
    public Node cloneGraph(Node node) {
        if (node == null) {
            return node;
        }

        // If the node was already visited before.
        // Return the clone from the visited dictionary.
        if (visited.containsKey(node)) {
            return visited.get(node);
        }

        // Create a clone for the given node.
        // Note that we don't have cloned neighbors as of now, hence [].
        Node cloneNode = new Node(node.val, new ArrayList());
        // The key is original node and value being the clone node.
        visited.put(node, cloneNode);

        // Iterate through the neighbors to generate their clones
        // and prepare a list of cloned neighbors to be added to the cloned node.
        for (Node neighbor: node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(neighbor));
        }
        return cloneNode;
    }
	// Definition for a Node.
	class Node {
		public int val;
		public List<Node> neighbors;

		public Node() {
			val = 0;
			neighbors = new ArrayList<Node>();
		}

		public Node(int _val) {
			val = _val;
			neighbors = new ArrayList<Node>();
		}

		public Node(int _val, ArrayList<Node> _neighbors) {
			val = _val;
			neighbors = _neighbors;
		}
	}
}
