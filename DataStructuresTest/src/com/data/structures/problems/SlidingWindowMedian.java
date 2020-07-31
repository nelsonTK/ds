package com.data.structures.problems;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import com.data.structures.problems.ds.LeetCodeExercise;

public class SlidingWindowMedian extends LeetCodeExercise{
	static SlidingWindowMedian s = new SlidingWindowMedian();
	static SlidingWindowMedianUnofficialSolution1 s1 = new SlidingWindowMedianUnofficialSolution1();
	public static void main(String[] args) {
		//		int [] nums = stringToArray("[1,3,-1,-3,5,3,6,7]");
		//		int k = 4;
		int [] nums = stringToArray("[4,1,7,1,8,7,8,7,7,4]");
		int k = 4;

		//System.out.println(Arrays.toString( s.medianSlidingWindow(nums, k)));
		System.out.println(Arrays.toString( s1.medianSlidingWindow(nums, k)));
	}

	/*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * @intuition
	 * 		Used queue to have fast access to the element to remove.
	 * 
	 * 		What I do is to have a maxpq which have the lower half of the numbers 
	 * 		
	 * 		And a minPq that has the higher/greater half of the numbers
	 * 		
	 * 		and I use a queue to mantain the number that I want to take away
	 * 
	 * 		in the minPq I have always a number higher or equal to the maxPq.
	 * 
	 * 		So that I always know how to find the median
	 * 
	 * 		whether it is in the peek of the minPq if k is odd
	 * 
	 * 		Or it is the average of both peeks if k is even
	 * 		
	 * 		the values that are in the priority queues or queues are the indexes
	 * 		
	 * 		
	 * @score
	 * 		Runtime: 316 ms, faster than 9.17% of Java online submissions for Sliding Window Median.
	 *		Memory Usage: 54.1 MB, less than 5.01% of Java online submissions for Sliding Window Median.
	 * 
	 * 		Runtime: 242 ms, faster than 13.21% of Java online submissions for Sliding Window Median.
			Memory Usage: 41.9 MB, less than 44.22% of Java online submissions for Sliding Window Median.
	 * 
	 * @fail
	 * 		1) when adding numbers to the priority queues the equal case was not well treated
	 * 		2) still in priority queue. in the first case I had max instead of max.
	 * 		3) get Median was calculating with indexes and not nums
	 * 		4) queue had values instead of indexes
	 * 		5) I think I had a case missing
	 * 		6) failed because of overflow
	 * @param nums
	 * @param k
	 * @return
	 */
	public double[] medianSlidingWindow(int[] nums, int k) {

		//guards
		//k = 1
		//fill the pq's
		/*
            minpq
            maxpq
            indexpq    
            if k is odd minpq will have 1+
            if k is even minpq equals to maxpq
            before adding need to check if I have to pop somethiong
		 */
		if(nums == null || nums.length == 0)
			return new double[0];

		if (k == 1)
		{
			double [] guardAnswer = new double[nums.length];
			for (int i = 0; i < nums.length; i++)
			{
				guardAnswer[i] = (double) nums[i];
			}
			return guardAnswer;
		}

		ArrayDeque<Integer> q = new ArrayDeque<>();

		PriorityQueue<Integer> min = new PriorityQueue<>(
				(a,b) -> Integer.compare(nums[a], nums[b])
				);

		PriorityQueue<Integer> max = new PriorityQueue<>(
				(a,b) -> Integer.compare(nums[b], nums[a])
				);

		for (int i = 0; i < k; i++)
		{

			q.add(i);
			addToDualPQ(nums, min, max, i);
		}

		double [] ans = new double[nums.length - k + 1];
		int ansIndex = 0;
		ans[ansIndex++] = getMedian(nums,min, max, k);

		for (int i = k; i < nums.length; i++)
		{

			q.add(i);

			
			if (q.size() > k)
			{
				int indexToRemove = q.removeFirst();
				
				//requires optimization
				min.remove(indexToRemove);
				max.remove(indexToRemove);
			}

			addToDualPQ(nums, min, max, i);

			ans[ansIndex++] = getMedian(nums, min, max, k);
		}

		return ans;
	}

	private double getMedian(int[] nums, PriorityQueue<Integer> min, PriorityQueue<Integer> max, int k){

		return k % 2 == 0 ? (double)((long)nums[min.peek()] + nums[max.peek()])/2 : nums[min.peek()];    
	}

	/**
	 * add index and balance queues
	 * @param nums
	 * @param min
	 * @param max
	 * @param index
	 */
	private void addToDualPQ(int [] nums, PriorityQueue<Integer> min, PriorityQueue<Integer> max, int index){


		if(min.size() > max.size())
		{
			//if number bigger or equal than min of biggest we rebalance
			if(nums[index] >= nums[min.peek()])
			{
				max.add(min.poll());
				min.add(index);
			}
			else
			{
				max.add(index);
			}

			//rebalance
			//add element
		}
		else if(min.size() < max.size())
		{ 
			if(min.isEmpty())
			{
				if (nums[index] >= nums[max.peek()])
				{
					min.add(index);
				}
				else
				{
					min.add(max.poll());
					max.add(index);
				}
			}
			else if (nums[index] >= nums[min.peek()])
			{
				min.add(index);
			}
			else
			{
				max.add(index);
				min.add(max.poll());
			}
		}
		else //if equals
		{
			if (min.isEmpty())
				min.add(index);
			else if (nums[index] >= nums[min.peek()])
			{
				min.add(index);
			}
			else
			{
				max.add(index);
				min.add(max.poll());
			}
		}
	}
}




/*********************
 * OTHERS SOLUTIONS
 *********************/
/**
 * 
 * @intuition
 * 		The solution of this guy is clean.
 * 		
 * 		What he does is to use two priority queues (min and max).
 * 		
 * 		and then he adds elements to them while the window is not filled.
 * 
 * 		after the window is filled in each iteration forward he calculates the median, 
 * 
 * 		then removes the last element and then rebalances the tree
 * 
 * 
 * getMedian return the average if both elements have the same size.
 * 
 * 		returns the peek of the min if the min is bigger
 * 
 * 		returns the peek of the max if the max is bigger
 * 
 * 		so unlike me this guy do not force the min heap to be always equal or bigger than the other, 
 * 
 * 		I knew that caused some performance lag in my solution
 * 
 * 
 * balance trees 
 * 		is to send the value of the bigger queue to the other queue.
 * 
 * @score
 *	Runtime: 70 ms, faster than 64.81% of Java online submissions for Sliding Window Median.
 *	Memory Usage: 42.4 MB, less than 13.94% of Java online submissions for Sliding Window Median.
 *
 * @source
 *		https://leetcode.com/problems/sliding-window-median/discuss/741357/Java-solution-using-2-heaps-easy-to-understand.
 * @author Nelson Costa
 *
 */
class SlidingWindowMedianUnofficialSolution1 {

	public double[] medianSlidingWindow(int[] nums, int k) {

		int n = nums.length;
		if(n == 0 || k == 0)
			return new double[0];

		double[] result = new double[n - k + 1];
		//for upper half of the ordered list
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();

		//for lower half of the ordered list
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder()); 

		for(int i = 0; i < n; i++) {

			if(minHeap.size() > 0 && nums[i] > minHeap.peek())
				minHeap.add(nums[i]);    
			else 
				maxHeap.add(nums[i]);

			balanceHeaps(minHeap, maxHeap);

			if(i >= k - 1){

				result[i - k + 1] = getMedian(minHeap, maxHeap);

				//heaps should have elements only belonging to the sliding window 
				if(nums[i - k + 1] <= maxHeap.peek())
					maxHeap.remove(nums[i - k + 1]);
				else
					minHeap.remove(nums[i - k + 1]);

				balanceHeaps(minHeap, maxHeap);
			} 
		}
		return result;
	}

	private void balanceHeaps(PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap) {
		if(minHeap.size() - maxHeap.size() > 1) {
			int value = minHeap.poll();
			maxHeap.add(value);
		} else if(maxHeap.size() - minHeap.size() > 1) {
			int value = maxHeap.poll();
			minHeap.add(value);
		}
	}

	private double getMedian(PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap) {

		double median = 0.0;
		if(minHeap.size() == maxHeap.size())
			median = minHeap.peek() / 2.0 + maxHeap.peek() / 2.0;
		else if (minHeap.size() > maxHeap.size())
			median = minHeap.peek();
		else 
			median = maxHeap.peek(); 

		return median;
	}
}