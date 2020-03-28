package com.data.structures.problems;

/**
 * https://leetcode.com/problems/hamming-distance/
 * EASY
 * 461
 * 
 * @author Nelson Costa
 *
 */
public class HammingDistance {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		HammingDistance h = new HammingDistance();
		int x = 3;
		int y  = 8;
		System.out.println(h.hammingDistance(x, y));
	}

	/**
	 * 	Runtime: 0 ms, faster than 100.00% of Java online submissions for Hamming Distance.
		Memory Usage: 36.6 MB, less than 5.09% of Java online submissions for Hamming Distance.

		Very easy and strait forward
	 *  
	 *  I could have used 		Integer.bitCount(x ^ y); 
	 *  
	 * @time  O(1) because number of bits are fixed
	 * @space O (1)
	 * @param x
	 * @param y
	 * @return
	 */
	public int hammingDistance(int x, int y) {
		int diff = x ^ y;
		int count = 0;

		if (x < 0 || y < 0)
			throw new IllegalArgumentException();

		while (diff > 0)
		{
			count += diff & 1;
			diff >>= 1;
		}
		return count;
	}
}
