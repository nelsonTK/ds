package com.data.structures.problems.contest;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/widest-vertical-area-between-two-points-containing-no-points/
 * @author Nelson Costa
 *
 */
public class b38WidestVerticalAreaBetweenTwoPointsContainingNoPoints {

    //design completamente errado
	/*
    public int maxWidthOfVerticalAreaold(int[][] points) {
        
        if (points == null)
            return 0;
        
        
        //max width excluding edges
        TreeMap<Integer, Integer> xCount = new TreeMap<>();
        
        //feel the tree
        for (int[] p : points)
        {
            xCount.put(p[0], xCount.getOrDefault(p[0], 0) + 1);
        }
        
        //remove first
        if (xCount.size() > 0)
        {
            xCount.remove(xCount.firstKey());
        }
        
        //remove last
        if (xCount.size() > 0)
        {
            xCount.remove(xCount.lastKey());
        }
        
        int ans = 0;
        
        for(int key : xCount.keySet())
        {
            ans += xCount.get(key);
        }
                
        return ans;
    }*/
    //design completamente errado
    //nlogn
	/**
	 * @intuition
	 * 		the gist was to sort the array and compare the current element with the next.
	 * 		I had a lot of troubles understanding the solution it self and had a lot of confusion, but after all I was able of solve the question in time.
	 * 		
	 * @time
	 * 		O(N)
	 * 
	 * @space
	 * 		O(1)
	 * 
	 * @param points
	 * @return
	 */
    public int maxWidthOfVerticalArea(int[][] points) {
        
        if (points == null)
            return 0;
        
        Arrays.sort(points, (a, b) -> Integer.compare(a[0], b[0]));
                    
        int ans = 0;
                    
        for(int i = 0; i < points.length -1; i++){
            ans = Math.max(ans, points[i + 1][0] - points[i][0]);
        }
        
        return ans;
    }
}
