package com.data.structures.problems;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/container-with-most-water/
 * MEDIUM
 * 
 * @author Nelson Costa
 *
 */
public class ContainerWithMostWater extends LeetCodeExercise{

	public static void main(String[] args) {
		
		ContainerWithMostWater c = new ContainerWithMostWater();
		int [] height = stringToArray("[1,8,6,2,5,4,8,3,7]");
		height = stringToArray("[2,3,4,5,18,17,6]");
		printArray(height);
		System.out.println("max capacity: " + c.maxArea(height));
	}

	

	/**************************************
	 * SOLUTION 1
	 *************************************/
	
	
	/**
	 * @intuition
	 * 		Start at the start and firstly comparing with the end in the end of the array, 
	 * 		Then reduce the end and discard calculations if it's useless
	 * 
	 * @comments
	 * 		This so called optimizations ended up having the reverse effect which was worstening performance
	 * 
	 * @score
	 * 		Runtime: 511 ms, faster than 8.33% of Java online submissions for Container With Most Water.
			Memory Usage: 39.8 MB, less than 94.87% of Java online submissions for Container With Most Water.
	 * 
	 * @failed
	 * 		1) forgot to assign Max to the result variable
	 * 
	 * @time  O(n^2)
	 * 		worst case is an ever decreasing array
	 * 		
	 * @space O(1)
	 * 
	 * @bcr O(nlogn) probably this is the best possible
	 * 
	 * @param height
	 * @return
	 */
    public int maxArea0(int[] height) {
        
    	if (height == null || height.length < 2)
    		throw new IllegalArgumentException();
    	
    	int maxCapacity = 0;
    	int last = height.length - 1;
    	for (int i = 0; i < last; i++) {
			for (int j = last; j > i; j--) {
				
				if (j == last)
				{
					maxCapacity = Math.max(getArea(i, j, height), maxCapacity);
				}
				else if (height[j] > height[last] && height[last] < height[i])
				{
					maxCapacity = Math.max(getArea(i, j, height), maxCapacity);
				}
			}
		}
    	
    	
    	return maxCapacity;
    }

	private int getArea(int startPosition, int endPosition, int[] h) {
		return (endPosition - startPosition)*(Math.min(h[startPosition], h[endPosition]));
	}
	
	

	/**************************************
	 * SOLUTION 2
	 *************************************/
	
	
	/**
	 * @intuition
	 * 		The thing here is to use two pointers starting from max width and
	 * 		to jump to the next best candidate given the shorter of them.
	 * 		because the area is limited by the shorted of them
	 * 
	 * @score
	 * 		Runtime: 1 ms, faster than 100.00% of Java online submissions for Container With Most Water.
			Memory Usage: 40.1 MB, less than 93.59% of Java online submissions for Container With Most Water.
	 * 
	 * @fail
	 * 		1) have no guard for aux variable
	 * 		2) signal was wrong in start
	 * 		3) signal was wrong in end
	 * 
	 * @improve
	 * 		1) I could have start done better if I had not only do sketches with bars but also with numbers.
	 * 
	 * @time  O(N)
	 * @space O(1)
	 * 
	 * @param h
	 * @return
	 */
    public int maxArea(int[] h) {
    	
    	int start = 0;
    	int end = h.length - 1;
    	int maxArea = 0;
    	int aux = 0;
    	while (start < end) {
    		
    		maxArea = Math.max((end - start)*(Math.min(h[start], h[end])), maxArea);
    		
    		if (h[start] < h[end])
    		{
    			aux = start;
    			while (aux < end && h[start] >= h[aux])
    			{
    				aux++;
    			}
    			
    			start = aux;
    		}
    		else
    		{

    			aux = end;
    			while (aux > start && h[end] >= h[aux])
    			{
    				aux--;
    			}
    			
    			end = aux;
    		}
    	}
    	
    	return maxArea;
    }

}

/**************************************
 * OTHERS SOLUTIONS
 *************************************/
/**
 * Is the best solution from leetcode
 * my solution is a little bit better, by 1 ms.
 * little code
 * 
 * @score
		Runtime: 2 ms, faster than 94.99% of Java online submissions for Container With Most Water.
		Memory Usage: 39.9 MB, less than 94.87% of Java online submissions for Container With Most Water.
 * 	
 * @author Nelson Costa
 *
 */
class ContainerWithMostWaterSolution2 {
    public int maxArea(int[] height) {
        int maxarea = 0, l = 0, r = height.length - 1;
        while (l < r) {
            maxarea = Math.max(maxarea, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r])
                l++;
            else
                r--;
        }
        return maxarea;
    }
}