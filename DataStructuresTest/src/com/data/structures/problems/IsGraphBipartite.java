package com.data.structures.problems;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Stack;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * 
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class IsGraphBipartite extends LeetCodeExercise{
	static IsGraphBipartite g = new IsGraphBipartite();
	
	public static void main(String[] args) {
		
		int [][] matrix = stringToMatrix("[[1,3], [0,2], [1,3], [0,2]]");
		
		System.out.println(g.isBipartite(matrix));
	}



	int [] colors;
	boolean [] visited;
	int [][] graph;
	static final int white = 0;
	static final int red = 1;
	static final int blue = 2;

	/**
		@fail
			1) forgot to add the items to the queue

	 * @time	O(V + E)
	 * @space	O(V + E) wost case example all V in colors and visited nod, 
	 * 			plus edges in the queue, if we have n edges more space is gonna be required
	 * @param graph
	 * @return
	 */
	public boolean isBipartite(int[][] graph) {

		visited = new boolean[graph.length];
		colors = new int[graph.length];
		int n = graph.length;
		this.graph = graph;

		for (int i = 0; i < n; i++)
		{
			if(!visited[i])
			{
				if(!isComponentBipartite(i))
				{
					return false;
				}
			}
		}

		return true;
	}

	public boolean isComponentBipartite(int node)
	{
		ArrayDeque<Integer> q = new ArrayDeque<>();
		q.add(node);
		colors[node] = red;
		int parentColor, parent;

		while(!q.isEmpty())
		{
			parent = q.poll();
			parentColor = colors[parent];

			for (int i : graph[parent]){
				if (colors[i] == white)
				{
					q.push(i);
					colors[i] = parentColor == red ? blue : red;

				}
				else if(colors[i] == parentColor)
				{
					return false;
				}
			}

			visited[parent] = true;

		}
		return true;
	}
}

/**
 * They use colors in a way that they dont use visited array
 * @author Nelson Costa
 *
 */
class IsGraphBipartiteSolution1 {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];
        Arrays.fill(color, -1);

        for (int start = 0; start < n; ++start) {
            if (color[start] == -1) {
                Stack<Integer> stack = new Stack();
                stack.push(start);
                color[start] = 0;

                while (!stack.empty()) {
                    Integer node = stack.pop();
                    for (int nei: graph[node]) {
                        if (color[nei] == -1) {
                            stack.push(nei);
                            color[nei] = color[node] ^ 1;
                        } else if (color[nei] == color[node]) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }
}