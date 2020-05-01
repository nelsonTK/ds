package com.data.structures.problems;

import java.util.Stack;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/trapping-rain-water/
 * HARD
 * @author Nelson Costa
 *
 */
public class TrappingRainWater extends LeetCodeExercise{

	public static void main(String[] args) {
		int [] h = stringToArray("[0,2,0,1,1,0,1,3,2,1,2,1]");
//		h = stringToArray("[4,2,3]");
		
		printArray(h);
		TrappingRainWater t = new TrappingRainWater();
		System.out.println(t.trap(h));

		TrappingRainWaterSolutions2 t2 = new TrappingRainWaterSolutions2();
		System.out.println(t2.trap(h));

		TrappingRainWaterSolutions3 t3 = new TrappingRainWaterSolutions3();
		System.out.println(t3.trap(h));

		TrappingRainWaterSolutions4 t4 = new TrappingRainWaterSolutions4();
		System.out.println(t4.trap(h));

	}

	/**************************************
	 * SOLUTION 1
	 *************************************/
	/**
	 * 
	 * @intuition
	 * 		I use two pointers and from the start I search the next candidate which will trap water
	 * 		A candidate is a column with the same size or bigger than the start column
	 * 		If I find I calculate how much water is trapped, else we calculate the trap water between start and the second highest column 
	 * 		and move start to that position, and end to that position plus 1. and restart searching
	 * 
	 * @score
			Runtime: 1 ms, faster than 91.86% of Java online submissions for Trapping Rain Water.
			Memory Usage: 39.2 MB, less than 27.40% of Java online submissions for Trapping Rain Water.
	 * 
	 * 
	 * @fail 
	 * 		1) possibly wrong 1st while predicate
	 * 		2) forgot to reset maxInPath
	 * 		3) maxInpath was not being properly compared with the end
	 * 		4) forgot to calculate end in the end of each iteration
	 * 		5) while was not ready for end update
	 * 
	 * @time  O(N^2)
	 * @space O(1)
	 * @bcr   O(n)
	 * 
	 * @param h
	 * @return
	 */
	public int trap0(int[] h) {

		if (h == null || h.length < 3)
			return 0;

		int result = 0;
		int start = 0, end = start + 1, maxInPath;

		while (end < h.length)
		{
			maxInPath = end;

			//search trap water candidate
			while (end < h.length && h[end] < h[start] )
			{
				maxInPath = (h[end] > h[maxInPath]) ? end : maxInPath;
				end++;
			}

			//candidate not found, we will resolve with the second best candidate which is the highest
			if (end >= h.length)
			{
				end = maxInPath;
				result += (end - start - 1) * Math.min(h[end], h[start]) - sumInBetween(start, end, h);
				start = end;
				end = start + 1;
			}
			//candidate found
			else if(h[end] >= h[start])
			{
				result += (end - start - 1) * Math.min(h[end], h[start]) - sumInBetween(start, end, h);
				start = end;
				end = start + 1;
			}

		}


		return result;
	}

	/**
	 * Sums values in between two indexes
	 * @param start
	 * @param end
	 * @param array
	 * @return
	 */
	private int sumInBetween(int start, int end, int [] array) {
		int sum = 0;

		for (int i = start + 1; i < end; i++) {
			sum += array[i];
		}

		return sum;
	}




	/**************************************
	 * SOLUTION 2
	 *************************************/
	
	
	/**
	 * @intuition
	 * 		if left pointer below right pointer, than we know we have a wall if we advance in that direction
	 * 		(the equal could stay in either side)
	 * 		if right pointer below left, than we know we can go in left direction
	 * 		
	 * 		This is the corner stone to solve this exercise.
	 * 		
	 * 
	 * @score
			Runtime: 1 ms, faster than 91.82% of Java online submissions for Trapping Rain Water.
			Memory Usage: 39.1 MB, less than 29.45% of Java online submissions for Trapping Rain Water.
			
			ACHIEVED SAME SCORE BUT WITH BETTER TIME COMPLEXITY
	 * 
	 * @fail
	 * 		1) infinite Loop, caused by signals
	 * 		2) rmax was badly defined
	 * 
	 * @time	O(N)
	 * @space	O(1)
	 * @bcr 	O(N)
	 * 
	 * @param h
	 * @return
	 */
	public int trap(int[] h) {

		if (h == null || h.length < 3)
		{
			return 0;
		}
		
		int ans = 0, r = h.length - 1, l = 0, lmax = 0, rmax = h.length - 1;

		while (l < r) {
			while (l < r && h[l] <= h[r])
			{
				if(h[lmax] < h[l])
				{
					lmax = l;
				}
				else
				{
					ans += h[lmax] - h[l];
				}
				l++;
			}
			
			while (l < r && h[r] < h[l])
			{
				if(h[rmax] < h[r])
				{
					rmax = r;
				}
				else
				{
					ans += h[rmax] - h[r];
				}
				
				r--;
			}

		}
		return ans;
	}


}

class TrappingRainWaterSolutions4 {

	/**
	 * Very Ingenius Solution, not really easy to understand.
	 * The gold tip is that if the height[left] < height[right]	than there is a higher bar in the right for the left pointer
	 * 
	 * if height[left] >= height[righ] than there is a higher bar for the left side, and therefore right advances, when 
	 * 
	 * 
	 * 
	 * What I Like the most here is that they use the right pointer to prevent what I was not able of preventing
	 * which is when you don't find a max bar on the right and have to return, to previous max and advance and start all over
	 * 
	 * @return
	 */
	public int trap(int [] height)
	{
		int left = 0, right = height.length - 1;
		int ans = 0;
		int left_max = 0, right_max = 0;
		while (left < right) {
			// a taller bar exists on left pointer's right side
			if (height[left] < height[right]) {
				//if previous max (left_max) bigger than current(height[left]) add to answer the difference
				//else update max
				if (height[left] >= left_max)
				{
					left_max = height[left];
				}
				else
				{ 
					ans += left_max - height[left];
				}
				++left;
			}
			// a taller bar exists on right pointer's left side
			else 
			{
				if(height[right] >= right_max) 
				{
					right_max = height[right];
				}
				else
				{
					ans += (right_max - height[right]);
				}
				--right;
			}
		}
		return ans;
	}
}
/**
 * Stack Solution
 * its interesting, what we do here is to subtract current with the previous in the stack if the current is larger.
 * 
 * 
 * @score
 * 		Runtime: 3 ms, faster than 15.37% of Java online submissions for Trapping Rain Water.
		Memory Usage: 39.3 MB, less than 26.71% of Java online submissions for Trapping Rain Water.
 * @author Nelson Costa
 *
 */
class TrappingRainWaterSolutions2 {
	int trap(int [] height)
	{
		int ans = 0, current = 0;
		Stack<Integer> st = new Stack<>();

		while (current < height.length) {
			while (!st.empty() && height[current] > height[st.peek()]) {
				int top = st.pop();
				if (st.empty())
					break;
				int distance = current - st.peek() - 1;
				int bounded_height = Math.min(height[current], height[st.peek()]) - height[top];
				ans += distance * bounded_height;
			}
			st.push(current++);
		}
		return ans;
	}
}


/**
 * @score
			Runtime: 1 ms, faster than 91.86% of Java online submissions for Trapping Rain Water.
			Memory Usage: 39.2 MB, less than 27.40% of Java online submissions for Trapping Rain Water.
 * 
 * Dynamic programming solution
 * calc right differences of each column, calc left difference for each column
 * answer is the minimum of each
 * 
 * 
 * @author Nelson Costa
 *
 */
class TrappingRainWaterSolutions3 {
	public int trap(int [] height)
	{
		if(height == null || height.length == 0)
			return 0;

		int ans = 0;
		int size = height.length;
		int [] left_max = new int [size];
		int [] right_max = new int [size];
		left_max[0] = height[0];

		for (int i = 1; i < size; i++) {
			left_max[i] = Math.max(height[i], left_max[i - 1]);
		}

		right_max[size - 1] = height[size - 1];

		for (int i = size - 2; i >= 0; i--) {
			right_max[i] = Math.max(height[i], right_max[i + 1]);
		}
		for (int i = 1; i < size - 1; i++) {
			ans += Math.min(left_max[i], right_max[i]) - height[i];
		}
		return ans;
	}
}