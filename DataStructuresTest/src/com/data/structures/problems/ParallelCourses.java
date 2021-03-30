package com.data.structures.problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/parallel-courses/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class ParallelCourses {

	

	/**
	 * @intuition
	 * 		The gist is to count the longest path from the least dependent to the most dependent.
	 * 
	 * 		I just reversed the dependencies and build that graph with them
	 * 
	 * 		after this I calculated the max dependencies for each course
	 * 
	 * 		The max of all dependencies win be the answer
	 * 
	 * 
	 * @fail
	 * 		forgot to add the set
	 * 
	 * 		forgot about the space complexity
	 * 
	 * @alternatives
	 * 		bfs would also be a good alternative, using indegree approach
	 * 
	 * @time 
	 * 		O(V + E)
	 * 
	 * @space
	 * 		O(V + E)
	 * 
	 * @param n
	 * @param relations
	 * @return
	 */
    public int minimumSemesters(int n, int[][] relations) {
        
        //if during dfs I get the same number it's impossible
        //have cache for courseid and levels 
        HashMap<Integer, Integer> courseMapToSemesters = new HashMap<>();
        HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
        Set<Integer> processing = new HashSet<>();
        
        //create graph reversed
        //for each number in relations set each node dependencies
        for(int r = 0; r < relations.length; r++)
        {
            int independent = relations[r][0];
            int dependent   = relations[r][1];
            graph.computeIfAbsent(dependent, k -> new ArrayList<Integer>()).add(independent);
            graph.put(independent, graph.getOrDefault(independent, new ArrayList<Integer>()));
        }
        
        //for each course in the graphdfs, get the max; if -1 ever we got a loop, abort
        //O (V + E)
        int semesters = 0, max = 0;
        for (int course : graph.keySet())
        {
            semesters = calcSemesters(graph, courseMapToSemesters, course, processing);
            if (semesters == -1)
                return semesters;
            max = Math.max(max, semesters);
        }
        return max;
    }
    
    private int calcSemesters(HashMap<Integer, ArrayList<Integer>> graph, HashMap<Integer, Integer> courseMapToSemesters, int course, Set<Integer> processing)
    {
        if (processing.contains(course))
            return -1;
        if (courseMapToSemesters.containsKey(course))
            return courseMapToSemesters.get(course);
        processing.add(course);
        int semesters = 0, max = 0;
        for (int dependency : graph.get(course))
        {
            semesters = calcSemesters(graph, courseMapToSemesters, dependency, processing);
            if (semesters == -1)
                return semesters;            
            max = Math.max(max, semesters);
        }
        courseMapToSemesters.put(course, max + 1);
        processing.remove(course);
        return courseMapToSemesters.get(course);
    }
}

/**
 * This solution uses the in degree to solve this problem.
 * 
 * Essencially what they do is to start with the nodes that have indegree 0 in a list and then process the others
 * 
 * in each iteration they create a list with the next elements to process which are the ones with indegree equals to 1, and then 2 and then 3.
 * 
 * the indegree of each node is calculated in 
 * 
 * very cool solution
 * 
 * 
 * @author Nelson Costa
 *
 */
class ParallelCoursesSolution1 {
    public int minimumSemesters(int N, int[][] relations) {
        int[] inCount = new int[N + 1]; // or indegree
        List<List<Integer>> graph = new ArrayList<>(N + 1);
        for (int i = 0; i < N + 1; ++i) {
            graph.add(new ArrayList<Integer>());
        }
        for (int[] relation : relations) {
            graph.get(relation[0]).add(relation[1]);
            inCount[relation[1]]++;
        }
        int step = 0;
        int studiedCount = 0;
        List<Integer> bfsQueue = new ArrayList<>();
        for (int node = 1; node < N + 1; node++) {
            if (inCount[node] == 0) {
                bfsQueue.add(node);
            }
        }
        // start learning with BFS
        while (!bfsQueue.isEmpty()) {
            // start new semester
            step++;
            List<Integer> nextQueue = new ArrayList<>();
            for (int node : bfsQueue) {
                studiedCount++;
                for (int endNode : graph.get(node)) {
                    inCount[endNode]--;
                    // if all prerequisite courses learned
                    if (inCount[endNode] == 0) {
                        nextQueue.add(endNode);
                    }
                }
            }
            bfsQueue = nextQueue;
        }

        // check if learn all courses
        return studiedCount == N ? step : -1;
    }
}


/**
 * This is the implementation of graph coloring
 * with  topological sort, great to remember
 * 
 * @author Nelson Costa
 *
 */
class ParallelCoursesUnofficialTopSolutionSolution1 { // approach: Memo DFS
	  
	  // if couse A, B
	  // to finish course A, there are dependencies. but you can finish in 3 semesters
	  // B would have to finish in the 4th semester.
	  // this the whole thought behind.  the longest chain.
	  
	  // You are also given an array relations where relations[i] = [a, b], representing a prerequisite relationship between course a and course b: course a has to be studied before course b.
	  public int minimumSemesters(int n, int[][] relations) {
	    List<List<Integer>> g = new ArrayList<>(); // dependencies
	    for (int i = n; i != -1; i--)
	      g.add(new ArrayList<>());
	    for (int[] rel : relations)
	      g.get(rel[1]).add(rel[0]);
	    int[] color = new int[n + 1];
	    int[] mem = new int[n + 1];
	    int res = 0;
	    for (int i = 1; i <= n; i++) {
	      if (dfs(g, i, color, mem) == false)
	        return -1;
	      res = Math.max(res, mem[i]);
	    }
	    return res;      
	  }
	  
	  private boolean dfs(List<List<Integer>> g, int idx, int[] color, int[] mem) {
	    if (mem[idx] != 0)
	      return true;
	    if (color[idx] != 0) // white
	      return color[idx] == 2; // black
	    color[idx] = 1; // gray
	    int res = 0;
	    for (int next : g.get(idx)) {
	      if (dfs(g, next, color, mem) == false) 
	        return false; // there are cycles
	      res = Math.max(res, mem[next]);
	    }
	    mem[idx] = res + 1; // a course is determined by its worst dependencies
	    color[idx] = 2; // black
	    return true;
	  }
	}
