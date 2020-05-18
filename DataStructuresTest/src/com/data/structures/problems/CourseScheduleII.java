package com.data.structures.problems;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.IntStream;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/course-schedule-ii/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class CourseScheduleII extends LeetCodeExercise{

	static CourseScheduleII c = new CourseScheduleII();
	static CourseScheduleIISolution2 c2 = new CourseScheduleIISolution2();
	public static void main(String[] args) {

		int numCourses = 2;
		int [][] preReq = stringToMatrix("[[1,0]]");

		numCourses = 4;
		preReq = stringToMatrix("[[1,0],[2,0],[3,1],[3,2]]");

		numCourses = 2;
		preReq = stringToMatrix("[[0,1],[1,0]]");

		numCourses = 3;
		preReq = stringToMatrix("[[1,0]]");

		numCourses = 3;
		preReq = stringToMatrix("[[2,0],[2,1]]");

		numCourses = 3;
		preReq = stringToMatrix("[[1,0],[1,2],[0,1]]");
		//
		//		numCourses = 3;
		//		preReq = stringToMatrix("[[1,0],[0,2],[2,1]]");
		//		
		//		
		//		numCourses = 3;
		//		preReq = stringToMatrix("[[1,0],[2,0]]");

		//		for (int[] i : preReq) {
		//			printArray(i);
		//		}

		printArray(c.findOrder(numCourses, preReq));
		printArray(c2.findOrder(numCourses, preReq));
	}

	/*********************************
	 * SOLUTION 1
	 ********************************/

	public int[] ans;
	int ansIndex = 0;
	boolean [] visited;
	Set<Integer> currentPath; // avoid circular references

	/**
	 * 
	 * @intuition
	 * 		is just a dfs problem, dont even need to think in what is a graph or so..
	 * 		So as I was not very fresh about graphs this was the best implementation that I came up with
	 * 
	 * @score
			Runtime: 23 ms, faster than 11.61% of Java online submissions for Course Schedule II.
			Memory Usage: 40.8 MB, less than 97.56% of Java online submissions for Course Schedule II.
	 * 
	 * @optimization
	 * 		I could have improved my solution by using an hashmap for seeking the nodes
	 * 
	 * 
	 * @fail
	 * 		1) There was a scenary that i didn't took into account and it was when the prerequisites is empty and we have a number of courses
			2) I forgot to think on edgecases
			3) I didn't though circular references were going to play a role, despite thinking in the problem~
			4) misread, when circular references occur I should return -1 
			5) detected that if the number is bigger than the existig couses, the numbers are added at the front
			6) failed again, I forgot that the first element can have multiple dependencies

	 * @time	O(N^2)
	 * @space	O(N)
	 * @bcr		O(???)
	 * 
	 * @param numCourses
	 * @param preReq
	 * @return
	 */
	public int[] findOrder0(int numCourses, int[][] preReq) {

		if (numCourses > 0 && preReq.length == 0)
			return IntStream.range(0, numCourses).toArray();


		ans = new int [numCourses];        
		visited = new boolean [numCourses];
		currentPath = new HashSet<Integer>();

		//find missing numbers
		for (int i = 0; i < preReq.length; i++) {
			for (int j = 0; j < preReq[i].length; j++) {
				visited[preReq[i][j]] = true;
			}
		}

		//put missing numbers first the answer
		for (int i = 0; i < numCourses; i++) {
			if(!visited[i])
			{
				ans[ansIndex++] = i;
			}
		}

		//reset visited array
		visited = new boolean [numCourses];


		try {

			for (int i = 0; i < preReq.length; i++) {

				if (!visited[preReq[i][0]])
				{
					solveDependencies(preReq[i][0], preReq);

				}
			}

		}
		catch (Exception e) {
			return new int [0];
		}

		return ans;
	}

	private void solveDependencies(int course, int[][] preReq) throws Exception {

		if (visited[course])
			return;

		boolean successfulAdd = true;
		for (int i = 0; i < preReq.length; i++)
		{
			if (preReq[i][0] == course && !visited[preReq[i][1]])
			{
				successfulAdd = currentPath.add(preReq[i][1]);
				if (!successfulAdd)
					throw new Exception("Circular Reference");
				solveDependencies(preReq[i][1], preReq);
				currentPath.remove(preReq[i][0]);
			}

		}

		visited[course] = true;
		ans[ansIndex++] = course;

	}


	/*********************************
	 * SOLUTION 2
	 ********************************/

	/**
	 * @intuition
	 * 		use indegree to find what are the nodes that should be displayed first.
	 * 		nodes with 0 have nno dependencies left
	 * 
	 * @score
		Runtime: 8 ms, faster than 38.68% of Java online submissions for Course Schedule II.
		Memory Usage: 41 MB, less than 97.56% of Java online submissions for Course Schedule II.
	 * 
	 * @optimizations
	 * 		I could use a indegree array instead of hashmap
	 * 
	 * @fail
	 * 		1) didn't had into account the possibility of nodes not being sorces
	 * 		2) forgot to mark visited nodes. [bad judgement]
	 * 		2) I forgot to add the isolated nodes, 
	 * 		which is something that I did not think I though that all the nodes were already provided
	 * 		3) I'm not detecting cycles properlly
	 * 		4) my Cyclic resolution was wrong
	 * 		
	 * 
	 * @time	O(V + E) - this is because we process each vertice once, but for each vertice we iterate on theirs edges
	 * 
	 * @space	O(V + E) - V for storing Vetexes in the Indegree map, and E for storing the edges
	 * 
	 * @param numCourses
	 * @param pReq preRequisites
	 * @return
	 */
	public int[] findOrder(int numCourses, int[][] pReq) {

		HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
		HashMap<Integer, Integer> ind = new HashMap<>(); //indegree

		int[] ans = new int [numCourses];
		int ansIdx = 0;
		Queue<Integer> q = new ArrayDeque<Integer>();

		ArrayList<Integer> neighbors;
		//Set up indegree for each node
		for (int [] edge : pReq) {

			neighbors = graph.getOrDefault(edge[1], new ArrayList<Integer>());
			neighbors.add(edge[0]);

			graph.put(edge[1], neighbors);

			ind.put(edge[0], ind.getOrDefault(edge[0], 0) + 1);

		}

		//adding isolated nodes
		for (int i = 0; i < numCourses; i++)
		{
			if (graph.get(i) == null)
			{
				graph.put(i, new ArrayList<Integer>());
			}
		}

		//find courses with inDegree 0, or in other words. no dependencies
		for (Integer course : graph.keySet()) {

			if (ind.getOrDefault(course, 0) == 0)
				q.add(course);
		}


		int node;
		int nInd; //neighbor indegree
		while (!q.isEmpty())
		{

			node = q.poll();

			for (Integer neighbor : graph.getOrDefault(node, new ArrayList<Integer>())) {
				nInd = ind.getOrDefault(neighbor, 0);

				ind.put(neighbor, nInd - 1);

				if (nInd - 1 == 0)
				{
					q.add(neighbor);
				}
			}

			ans[ansIdx++] = node;
		}

		for (Integer indegree : ind.keySet()) {
			if (ind.getOrDefault(indegree, 0) > 0)
				return new int [0];
		}

		return ans;
	}

	public String getcode(int a, int b)
	{
		return Math.min(a,b) + "_" + Math.max(a,b);
	}

}



/*********************
* OTHERS SOLUTIONS
*********************/

/**
 * Solution with in degree is very similar to mine but with subtle differences that make it more efficient
 * 
 * for instance using array instead of using hashmap  for indegree
 * this increases performance but also allows us not not having to iterate trying to find the missing elements of the graph
 * 
 * cycles are identifies by counting iterations performed on nodes (i var) vs the count of nodes
 * 
 * @score
 * 
 		Runtime: 5 ms, faster than 71.39% of Java online submissions for Course Schedule II.
		Memory Usage: 40.3 MB, less than 97.56% of Java online submissions for Course Schedule II.
 * 
 * @author Nelson Costa
 *
 */
class CourseScheduleIISolution2 {
	public int[] findOrder(int numCourses, int[][] prerequisites) {

		boolean isPossible = true;
		Map<Integer, List<Integer>> adjList = new HashMap<Integer, List<Integer>>();
		int[] indegree = new int[numCourses];
		int[] topologicalOrder = new int[numCourses];

		// Create the adjacency list representation of the graph
		for (int i = 0; i < prerequisites.length; i++) {
			int dest = prerequisites[i][0];
			int src = prerequisites[i][1];
			List<Integer> lst = adjList.getOrDefault(src, new ArrayList<Integer>());
			lst.add(dest);
			adjList.put(src, lst);

			// Record in-degree of each vertex
			indegree[dest] += 1;
		}

		// Add all vertices with 0 in-degree to the queue
		Queue<Integer> q = new LinkedList<Integer>();
		for (int i = 0; i < numCourses; i++) {
			if (indegree[i] == 0) {
				q.add(i);
			}
		}

		int i = 0;
		// Process until the Q becomes empty
		while (!q.isEmpty()) {
			int node = q.remove();
			topologicalOrder[i++] = node;

			// Reduce the in-degree of each neighbor by 1
			if (adjList.containsKey(node)) {
				for (Integer neighbor : adjList.get(node)) {
					indegree[neighbor]--;

					// If in-degree of a neighbor becomes 0, add it to the Q
					if (indegree[neighbor] == 0) {
						q.add(neighbor);
					}
				}
			}
		}

		// Check to see if topological sort is possible or not.
		if (i == numCourses) {
			return topologicalOrder;
		}

		return new int[0];
	}
}

/**
 * 
 * solution with top sort and WHITE, GRAY and BLACK APPROACH
 * 
 * @author Nelson Costa
 *
 */
class CourseScheduleIISolution1 {
	  static int WHITE = 1;
	  static int GRAY = 2;
	  static int BLACK = 3;

	  boolean isPossible;
	  Map<Integer, Integer> color;
	  Map<Integer, List<Integer>> adjList;
	  List<Integer> topologicalOrder;

	  private void init(int numCourses) {
	    this.isPossible = true;
	    this.color = new HashMap<Integer, Integer>();
	    this.adjList = new HashMap<Integer, List<Integer>>();
	    this.topologicalOrder = new ArrayList<Integer>();

	    // By default all vertces are WHITE
	    for (int i = 0; i < numCourses; i++) {
	      this.color.put(i, WHITE);
	    }
	  }

	  private void dfs(int node) {

	    // Don't recurse further if we found a cycle already
	    if (!this.isPossible) {
	      return;
	    }

	    // Start the recursion
	    this.color.put(node, GRAY);

	    // Traverse on neighboring vertices
	    for (Integer neighbor : this.adjList.getOrDefault(node, new ArrayList<Integer>())) {
	      if (this.color.get(neighbor) == WHITE) {
	        this.dfs(neighbor);
	      } else if (this.color.get(neighbor) == GRAY) {
	        // An edge to a GRAY vertex represents a cycle
	        this.isPossible = false;
	      }
	    }

	    // Recursion ends. We mark it as black
	    this.color.put(node, BLACK);
	    this.topologicalOrder.add(node);
	  }

	  public int[] findOrder(int numCourses, int[][] prerequisites) {

	    this.init(numCourses);

	    // Create the adjacency list representation of the graph
	    for (int i = 0; i < prerequisites.length; i++) {
	      int dest = prerequisites[i][0];
	      int src = prerequisites[i][1];
	      List<Integer> lst = adjList.getOrDefault(src, new ArrayList<Integer>());
	      lst.add(dest);
	      adjList.put(src, lst);
	    }

	    // If the node is unprocessed, then call dfs on it.
	    for (int i = 0; i < numCourses; i++) {
	      if (this.color.get(i) == WHITE) {
	        this.dfs(i);
	      }
	    }

	    int[] order;
	    if (this.isPossible) {
	      order = new int[numCourses];
	      for (int i = 0; i < numCourses; i++) {
	        order[i] = this.topologicalOrder.get(numCourses - i - 1);
	      }
	    } else {
	      order = new int[0];
	    }

	    return order;
	  }
	}