package com.data.structures.problems;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 136
 * https://leetcode.com/problems/single-number/submissions/
 *  I had a lot of troubles understanding this question because of the space constraint
 *  
 * @author Nelson Costa
 * 
 *
 */
public class SingleNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SingleNumber s = new SingleNumber();
		int [] nums = {2,5,5,3,3};
		System.out.println(s.singleNumber(nums));

	}
	
	/**
	 * a XOR a = 0
	 * 0 XOR b = b
	 * a XOR b XOR a = (a XOR a) XOR b
	 * 
	 * Learned about the associativity and commutativity of bitwise operations
	 * @param nums
	 * @return
	 */
	public int singleNumber(int [] nums)
	{
		int total = 0;
		
		for (int i = 0; i < nums.length; i++) {
			total ^= nums[i];
		}
		
		return total;
	}
	
	/**
	 * 
	 * Runtime: 12 ms, faster than 8.66% of Java online submissions for Single Number.
		Memory Usage: 46.4 MB, less than 5.19% of Java online submissions for Single Number.
	 * Optimal solution, not wuite..
	 * @time O(N)
	 * @space O(N)
	 * @param nums
	 * @return
	 */
	public int singleNumber2(int [] nums) {
				
		HashMap<Integer, Integer> m = new HashMap<Integer, Integer>();
		m.put(null, 0);
		
		int total = 0;
		int sum = 0;
		int times2;
		
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			if(m.get(nums[i]) == null)
			{
				total = m.get(null);
				times2 = nums[i] * 2;
				m.put(nums[i], times2);
				m.put(null, total + times2);
			}
		}
		
		total = m.get(null);
		
		return total - sum;
	}

	/** Runtime: 11 ms, faster than 8.88% of Java online submissions for Single Number.
		Memory Usage: 46.1 MB, less than 5.19% of Java online submissions for Single Number.
	 * 
	 * 
	 * 
	 * @Failed, I've understimated the question, saw very little cases. didn't debug my code before first run (I had a bad sleep though)
	 * I coun't get to the perfect math solution I tried hard, got close but wasn't good enouhg
	 * @time O(N)
	 * @space O(N/2) => O(N)
	 * @param nums
	 * @return
	 */
	public int singleNumber1(int[] nums) {
		
		HashSet<Integer> map = new HashSet<Integer>();
		
		for (int i = 0; i < nums.length; i++) {

			if (map.contains(nums[i]))
			{
				map.remove(nums[i]);
			}
			else
			{
				map.add(nums[i]);
			}
		}
		
		return map.stream().findAny().orElse(0);
	}
}
