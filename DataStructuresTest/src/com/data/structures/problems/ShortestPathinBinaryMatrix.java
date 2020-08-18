package com.data.structures.problems;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/shortest-path-in-binary-matrix/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class ShortestPathinBinaryMatrix extends LeetCodeExercise
{
	static ShortestPathinBinaryMatrix s = new ShortestPathinBinaryMatrix();
	public static void main(String[] args) {

		int [][] grid = stringToMatrix("[[0,1],[1,0]]");
		System.out.println(s.shortestPathBinaryMatrix(grid));
		
	}

	   /** 
	    * @intuition
	    * 		to explore all the nodes arround the current node in all 8 directions.
	    * 		when you find the target node you leave
	    * 
	    * @score
	    * 		Runtime: 15 ms, faster than 81.70% of Java online submissions for Shortest Path in Binary Matrix.
				Memory Usage: 41.4 MB, less than 11.92% of Java online submissions for Shortest Path in Binary Matrix.
	    * @debug
	    * 		yes
	    * 
	    * @fail    
	    * 		1) forgot to add the base row and column to the calculations of the indexes 
	    * 		2) not seen was wrong, it should have the neightbor index
	    * 		3) inverted condition in the while
	    **/
	    public int shortestPathBinaryMatrix(int[][] grid) {
	        
	        
	        Integer min = Integer.MAX_VALUE;
	        int n = grid.length;
	        int end = n*n - 1;
	        boolean [] seen = new boolean[n*n];
	        Queue<Pair> q = new ArrayDeque<>();
	        Pair cur;
	        int r;
	        int c;        
	        int [] cR = {-1,-1,-1,0,1,1,1,0}; //combinationsRows
	        int [] cC = {-1,0,1,1,1,0,-1,-1}; //combinationsColumns
	        
	        if(grid[0][0] == 0)
	            q.add(new Pair(0, 1));
	        
	            
	            //equals to 0 or 1?
	        while (!q.isEmpty())
	        {
	            cur = q.poll();
	            
	            //equals to end stop
	            if(cur.index == end)
	            {
	                return Math.min(min, cur.cost);
	            }
	            
	            
	            //visit neighbors
	            c = cur.index % n;
	            r = cur.index / n;
	            
	            for (int i = 0; i < 8; i++)
	            {
	                int childIndex = code(r + cR[i] ,c + cC[i],n);
	                
	                if (cR[i] + r < n && cR[i] + r >= 0 && cC[i] + c < n && cC[i] + c >= 0 && 
	                    grid[cR[i] + r][cC[i] + c] == 0 && !seen[childIndex])
	                {
	                    q.add(new Pair(childIndex, cur.cost+1));
	                    seen[childIndex] = true;
	                    
	                }
	            }
	        }        
	        return min == Integer.MAX_VALUE? -1 : min;        
	    }
	    
	    private int code(int r, int c, int n){
	        return r*n + c;
	    }
	    
	    class Pair {
	        int index;
	        int cost;
	        
	        public Pair(int i, int c){
	            index = i;
	            cost = c;
	        }
	    }
}


/*********************
* OTHERS SOLUTIONS
*********************/
/**
 * This solution uses the same logic than mine, but contrary to what I do it changes the original input
 * 
 * @timetook 9ms
 * 
 * @author Nelson Costa
 *
 */
class ShortestPathinBinaryMatrixUnofficialSolution1 {
    int max=Integer.MAX_VALUE;
    public int shortestPathBinaryMatrix(int[][] grid) {
        if(grid[0][0]==1)
        {
            return -1;
        }
       // boolean worked=false;
        Queue<int[]> pq=new LinkedList<>();
        pq.add(new int[]{0,0});
      //  Set<int []> hashset=new HashSet<>();
        //hashset.add(new int[]{0,0});
        boolean worked=false;
        int dist=1;
        
        while(!pq.isEmpty())
        {
            int size=pq.size();
            for(int i=0;i<size;i++)
            {
                int [] temp=pq.poll();
                if(temp[0]==grid.length-1 && temp[1]==grid[0].length-1)
                {
                    return dist;
                }
               for(int p=temp[0]-1;p<=temp[0]+1;p++)
               {
                   for(int q=temp[1]-1;q<=temp[1]+1;q++)
                   {
                       if(p<grid.length && q<grid[0].length && p>=0 && q>=0 && grid[p][q]==0)
                       {
                           worked=true;
                           grid[p][q]=1;
                           pq.add(new int[]{p,q});
                       }
                   }
               }
                
            }
            dist++;
            if(worked==false)
            {
                break;
            }
        }
        return -1;
    }
}