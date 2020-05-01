package com.data.structures.problems;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/divide-chocolate
 * HARD (VERY)
 * @author Nelson Costa
 *
 */
public class DivideChocolate extends LeetCodeExercise {

	public static void main(String[] args) {
		int [] sweetness = stringToArray("[1,2,3,4,5,6,7,8,9]");
		int K = 5;
//		sweetness = stringToArray("[5,6,7,8,9,1,2,3,4]");
		K = 8;
//		sweetness = stringToArray("[1,1,1,2,2,2,2,2,2]");
//		sweetness = stringToArray("[1,2,2,1,2,2,1,2,2]");
		sweetness = stringToArray("[1, 2, 3, 4, 5, 3]");
		K = 2;

		DivideChocolate d = new DivideChocolate();

		printArray(sweetness);

		System.out.println("K: " + K);
		System.out.println(d.maximizeSweetness(sweetness, K));

	}

	/**
	 * 
	 *  I'TS NOT ABSOLUTE MAXIMUM SWEETNESS BUT RELATIVE MAXIMUM SWEETNESS
	 *  
	 *  
	 *  I though you always had to return the highest possible result and I tried to make that happen.

		But found out it was not the case:
		
		this input array for K = 2 returns 5 as maximum sweetness.
		[1,2,2,1,2,2,1,2,2]
		
		And this array, which is the same but with some elements swapped, returns  4.
		[1,1,1,2,2,2,2,2,2]
		
		I believe you should always return 5, but is not the case, the maximum sweetness is relative to the array disposition.
		
		Once you can't ask questions, if the description is not clear you end up spending an 1h on something that was not intended.
		
		Hope I Have saved you some time.
		
		Now you know....
		
		It is not the return the absolute best sweetness but the maximum relative sweetness given the array disposition
	 * 
	 * 
	 * 
	 * 
	 */
	
	/**
	 * @intuition
	 * 
	 * 		LINEAR APPROACH
	 * 		to find the number that fits our array best And we do trial and error.
	 * 		Like if we do buckets of size 1 how it goes?
	 * 		if we do buckets of size 2 how it goes?
	 * 		if we do buckets of size 3 how it goes?
	 * 		if we do buckets of size 4 how it goes?
	 * 		and so on and so on.. but this is linear..
	 * 		can we do better?
	 * 		of course, we can do with binary search
	 * 
	 * 		So with binary search the middle is our bucket size and we compare the sum of elements with the bucket size.
	 * 		if it is equals we slice
	 * 		Then after going through all the elements in the array you compare the number of slices with the target.
	 * 		if it is above the target value, we know the bucket size is small so we need to increase the bucket size.
	 * 		else if it is smaller we reduce the bucket size (mid) by reducing high boundary
	 * 
	 * 
	 * (delete this line) we return low because low is the minimum value for the bucket size mid.
	 * if we reach the desired number of slices (e.g. 1), 
	 * we continue reducing the bucket size because we want to earn the highest possible value. 
	 * in other words we want the bucket value to be the smallest possible
	 * so the goal is to find the smallest bucket size that fits the specified number of slices.
	 * 
	 * THIS IS THE ACTUAL QUESTION
	 * 
	 * FIND THE SMALLEST BUCKET SIZE THAT FITS K SLICES
	 * 
	 * Other alternatives would include going after the smaller, but when you go after the smaller you get the smallest piece.
	 * 
	 * 		[1, 2, 3, 4, 5, 3] K = 2
	 * 
	 * 		how can we cut this in two slices and still get the highest minimum value possible?
	 * 
	 * 		a) [1]. [2]. [3]. [4]. [5]. [3].  size 1, 6 slices
	 *
	 * 		b) [1, 2]. [3]. [4]. [5]. [3]. bucket size 2,  5 slices
	 * 			
	 * 		c) [1, 2]. [3]. [4]. [5]. [3]. bucket size 3, 5 slices
	 * 			
	 * 		d) [1,2,3]. [4]. [5]. [3] bucket size 4, 3 slices
	 * 
	 *  	e) [1, 2, 3]. [4, 5]. [3] bucket size 5, 2 slice
	 *  	
	 *  	f) [1, 2, 3]. [4, 5]. [3] bucket size 6, 2 slice
	 *  
	 *  	g) [1, 2, 3, 4]. [5, 3]. bucket size 7, 2 slice
	 *  
	 *  	h) [1, 2, 3, 4]. [5, 3]. bucket size 8, 2 slice   [THIS IS THE ANSWER]
	 *  
	 *  	i) [1, 2, 3, 4]. [5, 3] bucket size 9, 1 slice
	 * 
	 * 		SO THIS IS OUR ANSWER
	 * 		8 
	 * 		
	 * 
	 * @param sweetness
	 * @param K
	 * @return
	 */

	public int maximizeSweetness(int[] sweetness, int K) {
		int low = 1;
		int high = Integer.MAX_VALUE;
		while(low < high) {
			int mid = low + (high - low + 1) / 2;
			int slice = 0;
			int sum = 0;
			for(int s : sweetness) {
				sum += s;
				if(sum >= mid) {
					sum = 0;
					slice++;
					if(slice > K) break;
				}
			}
			if(slice > K) {
				low = mid;
			} else {
				high = mid - 1;
			}
		}
		return low;
	}
}

/*********************
 * OTHERS SOLUTIONS
 *********************/

/***
 * 
 * @author Nelson Costa
 *
 */
class DivideChocolateSolution1{
	/**
	 * 
	 * @param sweetness
	 * @param K
	 * @return
	 */
	public 	int maximizeSweetness(int [] sweetness, int K) {
		int  l = 1, r = Integer.MAX_VALUE;
		while(l < r){
			int mid = l+(r-l + 1)/2;
			if (canCut(sweetness, K, mid)) 
			{ 
				l = mid;
			}
			else {
				r = mid - 1;
			}
		}
		return l;
	}

	public  boolean canCut(int [] sweetness, int K, int minVal){
		int cnt = 0, sum = 0;        
		for(int  x : sweetness){
			sum += x;
			if(sum >= minVal){
				sum = 0;
				cnt++;
			}
			if(cnt >= K+1) return true;
		}
		return false;
	}
}