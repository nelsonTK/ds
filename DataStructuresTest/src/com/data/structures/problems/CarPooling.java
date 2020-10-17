package com.data.structures.problems;

/**
 * https://leetcode.com/problems/car-pooling
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class CarPooling {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	 /*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * @intuition
	 * 		Sweep line, mark the start and finish sweep line style and we are done
	 * 
	 * @score
	 *		Runtime: 2 ms, faster than 83.74% of Java online submissions for Car Pooling.
	 *		Memory Usage: 41.1 MB, less than 23.99% of Java online submissions for Car Pooling.
	 *
	 * @time
	 * 		O(NxM)
	 * 
	 * @space
	 * 		O(1)
	 * 
	 * @param trips
	 * @param capacity
	 * @return
	 */
    public boolean carPooling(int[][] trips, int capacity)
    {
        //put the passengers in the travel array
        
        //comulative sum, if the sum at any point is greater then the capacity we return false, else true;
        
        int [] travel = new int[1001];
        for (int i = 0; i < trips.length; i++)
        {
            int start = trips[i][1];
            int end = trips[i][2];
            int value = trips[i][0];
            travel[start] += value;
            
            if(end < travel.length)
            {
                travel[end] -= value; 
            }
        }
        
        if (travel[0] > capacity)
            return false;
        
        for (int i = 1; i < travel.length; i++)
        {
            travel[i]+=travel[i - 1];
            
            if (travel[i] > capacity)
            return false;
        }
        
        return true;
    }
}
