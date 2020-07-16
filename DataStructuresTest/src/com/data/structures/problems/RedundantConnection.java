package com.data.structures.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.data.structures.problems.ds.LeetCodeExercise;

public class RedundantConnection extends LeetCodeExercise{

	static RedundantConnection r = new RedundantConnection();
	public static void main(String[] args) {
		int [][] edges = stringToMatrix("[[1,2], [2,3], [3,4], [1,4], [1,5]]");
		System.out.println(Arrays.toString(r.findRedundantConnection(edges)));

	}

    
    /**
     * @intuition
     *	the order is given by the input, not by the matrix, we will not traverse a matrix.
     *	so the last redundant connection is the last connection where both elements have the same root
     *	ultimately this is a exercise to find a cycle which you can perform with union find for undirected graphs...
     *	
     *	A detail I've missed is that, eventually the cyclic edge is only the first one we find in the 2D array.
     *	But it's okay.
     *	
     * @score
     *    Runtime: 1 ms, faster than 68.44% of Java online submissions for Redundant Connection.
     *    Memory Usage: 39.9 MB, less than 63.64% of Java online submissions for Redundant Connection.
     *
     * @time   O(N) amortized O(N)
     * @space  O(N)
     * 
     * @fail
     *    1) the calculation of union array size was wrong
     *    2) forgot to add the root to itself...
     *    
    **/
    public int[] findRedundantConnection(int[][] edges) {
        
        Union uni = new Union(edges.length + 1);
        int [] ans = new int[2];
        
        for (int[] link : edges)
        {
            uni.union(link[0], link[1], ans);
        }
        
        return ans;
    }
    
    class Union
    {
        int [] parent;
        
        public Union(int lenght)
        {
            parent = new int [lenght];
            
            for (int i = 0; i < lenght; i++)
                parent[i] = i;
        }
        
        
        public void union(int a, int b, int [] ans)
        {
            
            
            int rootA = find(a);
            int rootB = find(b);
                        
            if (rootA == rootB)
            {
                ans[0] = a;
                ans[1] = b;
            }
            
            parent[rootB] = rootA;
        }
        
        public int find(int a)
        {
            int root = a;
            
            while (root != parent[root])
            {
                root = parent[root];
            }
            
            int next;
            while (a != parent[a])
            {
                next = parent[parent[a]];
                parent[a] = root;
                a = next;
            }
            
            return root;
        }
    }
}


/*********************
* OTHERS SOLUTIONS
*********************/

/**
 * Union find solution with rank and path compression implemented recursively
 * 
 * @time ~O(N)
 * @author Nelson Costa
 *
 */
class RedundantConnectionSolution2 {
    int MAX_EDGE_VAL = 1000;

    public int[] findRedundantConnection(int[][] edges) {
        DSU dsu = new DSU(MAX_EDGE_VAL + 1);
        for (int[] edge: edges) {
            if (!dsu.union(edge[0], edge[1])) return edge;
        }
        throw new AssertionError();
    }
}

class DSU {
    int[] parent;
    int[] rank;

    public DSU(int size) {
        parent = new int[size];
        for (int i = 0; i < size; i++) parent[i] = i;
        rank = new int[size];
    }

    public int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    public boolean union(int x, int y) {
        int xr = find(x), yr = find(y);
        if (xr == yr) {
            return false;
        } else if (rank[xr] < rank[yr]) {
            parent[xr] = yr;
        } else if (rank[xr] > rank[yr]) {
            parent[yr] = xr;
        } else {
            parent[yr] = xr;
            rank[xr]++;
        }
        return true;
    }
}


/**
 * DFS Solution
 * 
 * @time O(N^2)
 * @author Nelson Costa
 *
 */
class RedundantConnectionSolution1 {
    Set<Integer> seen = new HashSet();
    int MAX_EDGE_VAL = 1000;

    public int[] findRedundantConnection(int[][] edges) {
        ArrayList<Integer>[] graph = new ArrayList[MAX_EDGE_VAL + 1];
        for (int i = 0; i <= MAX_EDGE_VAL; i++) {
            graph[i] = new ArrayList();
        }

        for (int[] edge: edges) {
            seen.clear();
            if (!graph[edge[0]].isEmpty() && !graph[edge[1]].isEmpty() &&
                    dfs(graph, edge[0], edge[1])) {
                return edge;
            }
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        throw new AssertionError();
    }
    public boolean dfs(ArrayList<Integer>[] graph, int source, int target) {
        if (!seen.contains(source)) {
            seen.add(source);
            if (source == target) return true;
            for (int nei: graph[source]) {
                if (dfs(graph, nei, target)) return true;
            }
        }
        return false;
    }
}