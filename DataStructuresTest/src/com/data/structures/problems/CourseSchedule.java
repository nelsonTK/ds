package com.data.structures.problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/course-schedule/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class CourseSchedule {
	int white = 0;
	int grey = 1;
	int black = 2; 


	 /*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * @intuition
	 * 		Graph coloring exercise, the challenge of this exercise was to find if the graph had cycle
	 * 		what I did was to use visited graph to count visited elements
	 * 		courses which would have the state of each node
	 * 			White: unvisited
	 * 			Grey:  being visited
	 * 			Black: visited
	 * 		and the hashmap with the dependencies
	 * 
	 * @score
	 * 		Runtime: 4 ms, faster than 78.18% of Java online submissions for Course Schedule.
	 *		Memory Usage: 39.6 MB, less than 91.29% of Java online submissions for Course Schedule.
	 * 
	 * @fail
	 * 		1) forgot to color nodes
	 * 
	 * @time
	 * 		O(V + E)
	 * 
	 * @space
	 * 		O(V + E)
	 * 	
	 * @param numCourses
	 * @param prereq
	 * @return
	 */
	public boolean canFinish(int numCourses, int[][] prereq) {

		if (prereq == null || prereq.length == 0)
			return true;

		Set<Integer> visited = new HashSet<>();
		int [] courses = new int[numCourses];
		HashMap<Integer, List<Integer>> g = new HashMap<>();


		//creae graph with inverted direction  O(N)
		for (int i = 0; i < numCourses; i++)
		{
			g.put(i, new ArrayList<Integer>());
		}

		//add relationships
		for (int i = 0; i < prereq.length; i++) //O(E)
		{            
			g.get(prereq[i][1]).add(prereq[i][0]);
		}


		//start searching in the first one
		for (int i = 0; i < numCourses; i++) // O(N)
		{
			if (courses[i] == white)
			{
				dfs(i, g, visited, courses);
			}
		}

		return visited.size() == numCourses ? true :  false;
	}

	private boolean dfs(int course , HashMap<Integer, List<Integer>> g, Set<Integer>visited, int[] courses)
	{
		if (courses[course] == black)
			return true;
		if (courses[course] == grey)
			return false; //cycle found

		courses[course]++;

		for (Integer dependants : g.get(course))
		{
			if (!dfs(dependants, g, visited, courses))
			{
				return false;
			}
		}

		visited.add(course);
		courses[course]++;
		return true;
	}
}
