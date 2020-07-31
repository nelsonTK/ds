package com.data.structures.problems;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/interval-list-intersections
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class IntervalListIntersections {

	public static void main(String[] args) {

	}
    int start = 0;
    int end = 1;
    /** 
     * @intuition
     * 		The intuition is simple, is try to compare the intervals in a greedy way
     * 		formula:
     * 			max(start1,start2), end1 : if end1 in between interval 2
     * 			start, min(end1, end2) : if start1 in between interval 2
     * 
     * 		so the main formula of this solution is:
     * 			test interval 1
     * 			test interval 2
     * 			progress one the interval thatr finishes first
     * 			
     * @score
     * 		Runtime: 	 ms, faster than 99.87% of Java online submissions for Interval List Intersections.
	 *		Memory Usage: 40.5 MB, less than 20.16% of Java online submissions for Interval List Intersections.
     * 
     * @fail
     * 		1) forgot to switch the parameter in the second function
     * 
     * @time
     * 		O(N + M)
     * 
     * @space
     * 		O(N + M) or O(1)
    **/
    public int[][] intervalIntersection(int[][] a, int[][] b) {
        List<int[]> ans = new ArrayList<>();
        
        //guards
        if (a == null || a.length == 0)
            return new int[0][0];
        
        if (b == null || b.length == 0)
            return new int[0][0];
        
        
        int i = 0, j = 0;
        while (i < a.length && j < b.length)
        {
            if (addElement(a, b, i, j,ans)){}
            else if (addElement(b, a, j, i,ans)) {}
            
            //update next
            if (a[i][end] <= b[j][end])
            {
                i++;
            }
            else
            {
                j++;
            }
        }
        
        int [][] num = new int[ans.size()][2];
        for (int k = 0; k < ans.size();k++){
            num[k] = ans.get(k);
        }
        
        return num;
        
    }
    
    private boolean addElement(int[][] a, int[][] b, int i, int j, List<int[]> ans)
    {        
            if (a[i][start] >= b[j][start] && a[i][start] <= b[j][end])
            {
                ans.add(new int[]{a[i][start], Math.min(a[i][end], b[j][end])});
                return true;
            }
            else if (a[i][end] >= b[j][start] && a[i][end] <= b[j][end])
            {
                ans.add(new int[]{Math.max(a[i][start], b[j][start]), a[i][end]});
                return true;
            }
        
        return false;
    }
}


/*********************
* OTHERS SOLUTIONS
*********************/
/**
 * This solution has the same concept than mine but they are much more efficient in merging the intervals
 * 
 * The they go further in the sence that they state that two intervals merge if the max of the start of both if below the min end of both
 * 
 * > intersection
 * 
 * a			____
 * b		_______
 * intersec		___ 
 * 
 * > not intersection
 * 
 * a					____
 * b		_______
 * intersec		
 * 
 * @author Nelson Costa
 *
 */
class IntervalListIntersectionsSolution1 {
	  public int[][] intervalIntersection(int[][] A, int[][] B) {
	    List<int[]> ans = new ArrayList();
	    int i = 0, j = 0;

	    while (i < A.length && j < B.length) {
	      // Let's check if A[i] intersects B[j].
	      // lo - the startpoint of the intersection
	      // hi - the endpoint of the intersection
	      int lo = Math.max(A[i][0], B[j][0]);
	      int hi = Math.min(A[i][1], B[j][1]);
	      if (lo <= hi)
	        ans.add(new int[]{lo, hi});

	      // Remove the interval with the smallest endpoint
	      if (A[i][1] < B[j][1])
	        i++;
	      else
	        j++;
	    }

	    return ans.toArray(new int[ans.size()][]);
	  }
	}