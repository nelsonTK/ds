package com.data.structures.problems.contest;

/**
 * https://leetcode.com/problems/find-nearest-point-that-has-the-same-x-or-y-coordinate/
 * @author Nelson Costa
 *
 */
public class b47FindNearestPointThatHastheSameXorYCoordinate {
    public int nearestValidPoint(int x, int y, int[][] points) {
        int small = -1;
        int min = Integer.MAX_VALUE;
        int [] cur;
        for(int i=0;i< points.length;i++)
        {
            cur = points[i];
            
            if (cur[0] == x || cur[1] == y)
            {
                if (Math.abs(x - cur[0]) + Math.abs(y - cur[1]) < min)
                {
                    min=Math.abs(x - cur[0]) + Math.abs(y - cur[1]);
                    small = i;
                }
            }
        }
        return small;
    }
}
