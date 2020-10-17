package com.data.structures.problems.contest;

/**
 * https://leetcode.com/problems/coordinate-with-maximum-network-quality/
 * Q2
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class b37CoordinateWithMaximumNetworkQuality {
	/**
	 * Runtime: 43 ms, faster than 100.00% of Java online submissions for Coordinate With Maximum Network Quality.
	 * Memory Usage: 39.2 MB, less than 50.00% of Java online submissions for Coordinate With Maximum Network Quality.
	 * 
	 * @author Nelson Costa
	 *
	 */
	class Solution {
	    public int[] bestCoordinate(int[][] towers, int radius) {
	        
	        int maxX = -1, maxY = -1, minX = 51, minY = 51;
	        int sumForPoint, max = -1;
	        double distance;
	        int [] point = new int[]{51, 51};
	        
	        //find min max x and y to create a box
	        for(int [] tower : towers)
	        {
	            minX = Math.min(minX, tower[0]);
	            maxX = Math.max(maxX, tower[0]);
	            minY = Math.min(minY, tower[1]);
	            maxY = Math.max(maxY, tower[1]);
	        }
	        //test all coordinates inside the box
	        for (int y = minY; y <= maxY; y++){
	            for (int x = minX; x <= maxX; x++){
	                sumForPoint = 0;
	                for (int [] tower : towers){
	                    int dX = Math.abs(tower[0] - x);
	                    int dY = Math.abs(tower[1] - y);
	                    distance = Math.sqrt(dX * dX +  dY * dY);
	                    if (distance <= radius)
	                    {
	                        sumForPoint += (int)(tower[2] / (1 + distance));
	                    }
	                }
	                //update max
	                if (sumForPoint == max){
	                    
	                    if (x < point[0])
	                    {
	                        point[0] = x;
	                        point[1] = y;
	                    } 
	                    else if (x == point[0] && y < point[1])
	                    {
	                        point[0] = x;
	                        point[1] = y;
	                    }
	                }
	                else if (sumForPoint > max)
	                {
	                    point[0]=x;
	                    point[1]=y;
	                    max = sumForPoint;
	                }
	            }
	        }
	        return point;
	    }
	}

	/*

	[[1,2,5],[2,1,7],[3,1,9]]
	2
	[[1,2,13],[2,1,7],[0,1,9]]
	2
	[[23,11,21]]
	9
	[[2,1,9],[0,1,9]]
	1
	[[44,31,4],[47,27,27],[7,13,0],[13,21,20],[50,34,18],[47,44,28]]
	13
	[[31,13,33],[24,45,38],[28,32,23],[7,23,22],[41,50,33],[47,21,3],[3,33,39],[11,38,5],[26,20,28],[48,39,16],[34,29,25]]
	21
	*/
}
