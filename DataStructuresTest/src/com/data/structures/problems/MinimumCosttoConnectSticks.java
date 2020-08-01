package com.data.structures.problems;

import java.util.PriorityQueue;
import java.util.Queue;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/minimum-cost-to-connect-sticks/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class MinimumCosttoConnectSticks extends LeetCodeExercise{

	public static void main(String[] args) {

	}
	
	/*********************************
	 * SOLUTION 1
	 ********************************/
	/** 
	 * @intuition
	 * 		Solution with priority queue
	 * 		always sum the minimum elements in the pq
	 * 
	 * @comments
	 * 		I'm tired and this is reflecting in my code
	 * 
	 * 		In this problem is not enough to sort the list and just straigth from the minimum to the maximum suming up the costs.
	 * 		There are cases where it could go wrong and the example is below.
	 * 	
	 * 		Part of the challenge was to understand the question and the other to see this tricky edge case
	 * 
	 * 		this is a great example, the solution is to sum the first two and then the last two, and only then sum both
	 * 		in each sum we add the total cost:
	 * 
	 * 			3354 + 3259 + 4316 + 4904
	 * 			  \     /		\    /
	 *  			6613		9220
	 *  			  \			 /
	 *  				  31666 	<-- Correct Answer	
	 *  
	 * @score
	 * 		Runtime: 113 ms, faster than 20.45% of Java online submissions for Minimum Cost to Connect Sticks.
	 * 		Memory Usage: 52.1 MB, less than 5.05% of Java online submissions for Minimum Cost to Connect Sticks.
	 * 	
	 *  		
	 * @fail 
	 * 		1) I though the problem was simpler didn't saw one possibility where only connect elements by sort is not enough 
	 * 		2) edge case wor odd number of array elements
	 * 
	 * @alternatives
	 * 		I believe there are alternatives to do this exercise with DP
	 * 
	 * @time
	 * 		O(NLogN) N is the number of elements. N at maximum will be 2N
	 * 
	 * @space
	 * 		O(N) 
	 * 
	 **/
	public int connectSticks(int[] sticks) {

		if (sticks.length == 1)
			return 0;

		PriorityQueue<Integer> pq = new PriorityQueue<>();

		for (int s : sticks)
			pq.add(s);

		int firstStick, secondStick, total = 0;
		while (!pq.isEmpty())
		{
			firstStick = pq.poll();

			if (!pq.isEmpty())
			{
				secondStick = pq.poll();

				total += firstStick + secondStick;

				if(!pq.isEmpty())
					pq.add(firstStick + secondStick);
			}
			else 
			{
				total += firstStick;    
			}
		}

		return total;
	}
}

/*********************
* OTHERS SOLUTIONS
*********************/
/**
 * Same concept than my solution but much more efficient implementation, 
 * 
 * it solves some of the troubles I found to keep things consistent at the priority to ensure no elements is added unadvertedly
 * 
 * 
 * 
 * 
 * @author Nelson Costa
 *
 */
class MinimumCosttoConnectSticksUnofficialSolution1{
	public int connectSticks(int[] sticks) {
		Queue<Integer> pq = new PriorityQueue<>();
		for (int stick : sticks) pq.add(stick);

		int cost = 0;
		while (pq.size() > 1) {
			int stick1 = pq.poll();
			int stick2 = pq.poll();
			cost += stick1 + stick2;
			pq.add(stick1 + stick2);
		}

		return cost;
	}
}
