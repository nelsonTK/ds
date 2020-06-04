package com.data.structures.problems;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/friend-circles/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class FriendCircles extends LeetCodeExercise{

	static FriendCircles f = new FriendCircles();
	
	public static void main(String[] args) {
		int [][] m = stringToMatrix("[[1,1,0],[1,1,0],[0,0,1]]");
		System.out.println(f.findCircleNum(m));
	}


	/**
	 * @intuition
	 * 		1) I used union find to union direct friends and indirect friends in single group.
	 * 		2) performed an unoptimized find to count the total groups.
	 * 
	 * 
	 * @fail
	 * 		1) failed, forgot to test that the connection must exist. (m[row][col] = 1)
	 * 
	 * @score
	 * 		Runtime: 6 ms, faster than 17.52% of Java online submissions for Friend Circles.
			Memory Usage: 40.4 MB, less than 60.00% of Java online submissions for Friend Circles.
	 * 
	 * @optimizations
	 * 		this is very unefficient solution, this is a lazy version of union find
	 * 
	 * @alternatives
	 * 		1) I could create a graph and detect the components (I think) with dfs
	 * 		2) I could create a graph and use bfs to find the graph components
	 * 
	 * @debug 
	 * 		yes
	 * 
	 * @time	O(M*N*N^2) not 100% sure (traversing the matrix plus union find)
	 * @space	O(M*N)
	 * 
	 * 
	 * @param m
	 * @return
	 */
	public int findCircleNum(int[][] m) {

		if (m == null || m.length == 0)
			return 0;

		if (m.length == 1)
			return 1;

		int [] uni = new int[m.length];
		
		for (int i = 0; i < m.length; i++)
		{
			uni[i] = i;
		}

		for (int row = 0; row < m.length; row++)
		{
			for (int col = 0; col < m[0].length; col++)
			{
				if(row != col && m[row][col] == 1 )
				{
					union(Math.min(row, col), Math.max(row, col), uni);
				}
			}
		}

		return find(uni);
	}

	private void union (int a, int b, int [] union)
	{
		int rootA = getRoot(a, union);
		int rootB = getRoot(b, union);

		union[rootA] = rootB;
	}

	private int getRoot(int a, int [] union)
	{
		int root = union[a];

		while(root != union[root])
		{
			root = union[root];
		}

		return root;
	}

	private int find(int[] union)
	{
		boolean [] visited = new boolean[union.length];
		Set<Integer> ans = new HashSet<>();

		for (int i = 0; i < union.length; i++)
		{

			if (!visited[i])
			{
				int root = union[i];
				visited[i] = true;
				while (root != union[root])
				{
					root = union[root];
					visited[root] = true;
				}

				ans.add(root);
			}
		}

		return ans.size();
	}

}

/*********************
* OTHERS SOLUTIONS
*********************/
	
	/*********************
	* UNOFFICIAL SOLUTIONS
	*********************/

/**
 * Simple and cool implementation of Union find
 * 
 * introduces the concept of rank to union find, which I've not heard yet
 * 
 * uses path compression but in a different way (grand parent style)
 * while processing a element it points that element to its grand parent
 * 
 * https://www.hackerearth.com/practice/notes/disjoint-set-union-union-find/
 * 
 * 
 * And it uses union by rank to add always the smallest tree to the biggest tree
 * 
 * @author Nelson Costa
 *
 */
class FriendCirclesSolution0 {
    class UnionFind {
        private int count = 0;
        private int[] parent;
        private int[] rank;
        
        public UnionFind(int n) {
            count = n;
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        
        public int find(int x) {
            while (x != parent[x]) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }
        
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            
            if (rootX == rootY) return;
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootX] = rootY;
                if (rank[rootX] == rank[rootY]) {
                    rank[rootY]++;
                }
            }
            count--;
        }
        
        public int count() {
            return count;
        }
    }
    
    public int findCircleNum(int[][] M) {
        UnionFind uf = new UnionFind(M.length);
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                if (i == j) continue;
                if (M[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }
        return uf.count();
    }
}


/*********************
* OFFICIAL SOLUTIONS
*********************/

/**
 * Union Find
 * 
 * @score
 * 		Runtime: 10 ms, faster than 8.33% of Java online submissions for Friend Circles.
		Memory Usage: 51.8 MB, less than 8.00% of Java online submissions for Friend Circles.
 * 
 * @time n^3
 * @space n
 * @author Nelson Costa
 *
 */
class FriendCirclesSolution3 {
    int find(int parent[], int i) {
        if (parent[i] == -1)
            return i;
        return find(parent, parent[i]);
    }

    void union(int parent[], int x, int y) {
        int xset = find(parent, x);
        int yset = find(parent, y);
        if (xset != yset)
            parent[xset] = yset;
    }
    public int findCircleNum(int[][] M) {
        int[] parent = new int[M.length];
        Arrays.fill(parent, -1);
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M.length; j++) {
                if (M[i][j] == 1 && i != j) {
                    union(parent, i, j);
                }
            }
        }
        int count = 0;
        for (int i = 0; i < parent.length; i++) {
            if (parent[i] == -1)
                count++;
        }
        return count;
    }
}

/**
 * [BEST]
 * DFS 
 * 
 * @score
		Runtime: 2 ms, faster than 46.64% of Java online submissions for Friend Circles.
		Memory Usage: 52 MB, less than 8.00% of Java online submissions for Friend Circles.
		
		
 * @author Nelson Costa
 *
 */
class FriendCirclesSolution1 {
    public void dfs(int[][] M, int[] visited, int i) {
        for (int j = 0; j < M.length; j++) {
            if (M[i][j] == 1 && visited[j] == 0) {
                visited[j] = 1;
                dfs(M, visited, j);
            }
        }
    }
    public int findCircleNum(int[][] M) {
        int[] visited = new int[M.length];
        int count = 0;
        for (int i = 0; i < M.length; i++) {
            if (visited[i] == 0) {
                dfs(M, visited, i);
                count++;
            }
        }
        return count;
    }
}


/**
 * bfs solution
 * @score
 * 		Runtime: 12 ms, faster than 6.70% of Java online submissions for Friend Circles.
		Memory Usage: 51.9 MB, less than 8.00% of Java online submissions for Friend Circles.
		
		
 * @author Nelson Costa
 *
 */
class FriendCirclesSolution2 {
    public int findCircleNum(int[][] M) {
        int[] visited = new int[M.length];
        int count = 0;
        Queue < Integer > queue = new LinkedList < > ();
        for (int i = 0; i < M.length; i++) {
            if (visited[i] == 0) {
                queue.add(i);
                while (!queue.isEmpty()) {
                    int s = queue.remove();
                    visited[s] = 1;
                    for (int j = 0; j < M.length; j++) {
                        if (M[s][j] == 1 && visited[j] == 0)
                            queue.add(j);
                    }
                }
                count++;
            }
        }
        return count;
    }
}


