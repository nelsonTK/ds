package com.data.structures.problems;

/**
 * https://leetcode.com/problems/friend-circles/
 * EASY
 * @author Nelson Costa
 *
 */
public class FriendCircles_Take2 {

	/**
	 * [REVISION]
	 * 
	 * @intuition
	 * 		Is just a Union find plain and simple with the number of groups in the data structure itself.
	 * 		I did path compression and link by rank but the solution without link by rank is faster.
	 * 
	 * @score 	
	 * 		//with out link by rank
	 * 		Runtime: 1 ms, faster than 77.40% of Java online submissions for Friend Circles.
	 *		Memory Usage: 39.9 MB, less than 94.77% of Java online submissions for Friend Circles.
	 *		And 2 milliseconds with link by rank
	 * 
	 * @fail
	 * 		forgot to initialize the arr from the data structure
	 * 
	 * @time
	 * 		O(N^2) amortized
	 * 
	 * @space
	 * 		O(N)
	 * 
	 * @param M
	 * @return
	 */
    public int findCircleNum(int[][] M) {
        
        Union u = new Union(M.length);
        
        for (int i = 0; i < M.length; i++)
        {
            for (int j = 0; j < M[i].length; j++)
            {
                
                if(M[i][j]==1)
                {
                    u.union(i, j);
                }
            }
        }
        
        return u.groups;
        
    }
    
    class Union{
        int arr[];
        int rank[];
        int groups;
        
        public Union(int len)
        {
            arr = new int[len];
            for (int i  = 0; i < len; i++)
            {
                arr[i] = i;
            }
            
            rank = new int[len];
            groups = len;
        }
        
        public int find(int node)
        {
            int target = node; //will have the root
            while (target != arr[target])
            {
                target = arr[target];
            }
            
            //path compression
            int next;
            while (node != target)
            {
                next = arr[node];
                arr[node] = target;
                node = next;
            }
            
            return target;
        }
        
        public void union(int a, int b)
        {
            int rA = find(a);
            int rB = find(b);
            
            if(rA != rB)
            {
                groups--;
                //arr[rA] = rB; without link by rank
            }
            
            //link by rank
            if (rank[rA] == rank[rB])
            {
                rank[rA]++;
                arr[rB] = rA;
            }
            else if (rank[rA] > rank[rB])
            {
                arr[rB] = rA;
            }
            else if (rank[rA] < rank[rB])
            {
                arr[rA] = rB;
            }
        }
    }
}
