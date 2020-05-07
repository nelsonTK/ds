package com.data.structures.problems;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 * HARD
 * @author Nelson Costa
 *
 */
public class MedianOfTwoSortedArrays extends LeetCodeExercise{


	public static void main(String[] args) {
		int [] a = stringToArray("[1, 3]");
		int [] b = stringToArray("[2]");
		
		a = stringToArray("[1, 2]");
		b = stringToArray("[3, 4]");
		
		b = stringToArray("[1,1,1]");
		a = stringToArray("[1,1,1]");
		MedianOfTwoSortedArrays m = new MedianOfTwoSortedArrays();
		System.out.println(m.findMedianSortedArrays(a, b));
	}


	/**
	 * @intuition
	 * 		Thoughest question to date, needed help
	 * 		In the design stage I was not even able of finding a solution
	 * 		It was very important question to improve my overall process
	 * 
	 * 
	 * @comments
	 * 		My code could still be much more efficient I could calculate Left and Right of X and Y...
	 * 
	 * @score
	 * 		Runtime: 2 ms, faster than 99.80% of Java online submissions for Median of Two Sorted Arrays.
			Memory Usage: 40.4 MB, less than 100.00% of Java online submissions for Median of Two Sorted Arrays.
			
			
	 * @fail
	 * 		1) I was not dealing with infinity in median calculations
	 * 		2) I was not converting int to double
	 * 		3) The while loop was too much restrictive
	 * 
	 * @time log(min a, b)
	 * @space O(1)
	 * @bcr log(min a, b)
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public double findMedianSortedArrays(int[] a, int[] b) {
		double median = -1;

		int [] smallest = a.length <= b.length ? a : b;
		int [] largest = b != smallest ? b : a;

		int low = 0;
		int high = smallest.length - 1;
		int partitionX; 
		int partitionY;

		while (Math.abs((low - high)) <= largest.length)
		{
			partitionX = low + (high - low)/2;
			partitionY = (largest.length + smallest.length + 1)/2;//at worst points to the last of the largest array + 1 (adding one goes good with odd and even numbers)
			partitionY -= partitionX; //deviate from end according to the position of partition x
			partitionY = partitionY < 0 ? 0: partitionY; //if it is below zero, we can set it to zero as the element zero is the beggining of the largest

			if (safeBounds(smallest, partitionX) >= safeBounds(largest, partitionY - 1) &&
				safeBounds(largest, partitionY)  >= safeBounds(smallest, partitionX - 1))
			{
				//calc Median
				return calcMedian(smallest, largest, 
						safeBounds(smallest, partitionX - 1), 
						safeBounds(smallest, partitionX), 
						safeBounds(largest, partitionY - 1), 
						safeBounds(largest, partitionY));
			}
			else if (safeBounds(smallest, partitionX - 1) > safeBounds(largest, partitionY))
			{
				high = partitionX - 1;
			}
			else if (safeBounds(smallest, partitionX) < safeBounds(largest, partitionY - 1))
			{
				low = partitionX + 1;
			}

		}
		
		return median;
	}
	
	private double calcMedian(int [] x, int [] y, int xLeft, int xRight, int yLeft, int yRight) {

		//odd
		if ((x.length + y.length) % 2 != 0)
		{
			return Math.max(xLeft, yLeft);
		}
		else
		{
			return (double)(Math.max(xLeft, yLeft) + Math.min(yRight, xRight)) / 2;
		}
	}

	private int safeBounds(int [] array, int index) {

		if (index < 0)
			return Integer.MIN_VALUE;
		if (index >= array.length)
			return Integer.MAX_VALUE;
		else
			return array[index];
	}
	
}

/**
 *
 * Median of Two Sorted Arrays
 * Very Very hard problem
 * 
 * Solution:
 * 	https://www.youtube.com/watch?v=LPFhl65R7ww&t=595s
 * 
 * When they partition at index N, it means there are N elements smaller at the left of that number
 * The goal is to find the point in the smaller array where the combined halves are of equal size, 
 * and all elements in the small side are below the bigger elements in the right side of both partitions
 *
 *
 *	MY ATTEMPY
 *		The solution I was working on paper had some similarities but was still very far from this.
 *		I noticed that the solution contained always one of the elements in between the two initial middle points
 *		I Worked very hard to get that solution, but realized that even if I get that solution working I would not get log(m+n) solution
 *		but rather log(n) + Log (m), But still I Insisted on that path, I should have stopped and try something different.
 *		Something that I could've tried though was be to find the median on the arrays just by watching and see the pattern going on.
 *		I've not done that, I think it was a big mistake in my approach.
 *		
 *
 */