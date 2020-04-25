package com.data.structures.problems;

import java.util.Arrays;
import java.util.HashMap;

/**
 * https://leetcode.com/problems/find-the-difference/
 * EASY
 * @author Nelson Costa
 *
 */
public class FindTheDifference {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String s = "ac", t = "ach";
		FindTheDifference f  = new FindTheDifference();
		System.out.println("s: " + s);
		System.out.println("t: " + t);
		System.out.println(f.findTheDifference(s, t));

	}
	
	/**************************************
	 * SOLUTION 1
	 *************************************/
	

	/**
	 * Nothing Fancy here
	 * 
	 * 		Runtime: 7 ms, faster than 20.96% of Java online submissions for Find the Difference.
			Memory Usage: 38.2 MB, less than 6.25% of Java online submissions for Find the Difference.
	 * 
	 * @time O(N) => O(N + K) N equals to s + t lenght, K equahs to number of different keys which is contant
	 * @space O(N) = O(N)
	 * @param s
	 * @param t
	 * @return
	 */
	public char findTheDifference0(String s, String t) {
		HashMap<Character, Integer> ht = new HashMap<>();
		HashMap<Character, Integer> hs = new HashMap<>();

		if (s == null || s.length() == 0) {
			return t.charAt(0);
		}

		for (char c : t.toCharArray()) 			
		{	
			if (!ht.containsKey(c)) 
			{
				ht.put(c, 1);
			}
			else
			{
				ht.put(c, ht.get(c) + 1);
			}
		}

		for (char c : s.toCharArray()) 			
		{	
			if (!hs.containsKey(c)) 
			{
				hs.put(c, 1);
			}
			else
			{
				hs.put(c, hs.get(c) + 1);
			}
		}

		for (Character letter : ht.keySet()) {
			if(ht.get(letter) != hs.get(letter))
				return letter;
		}

		return 0;
	}


	/**************************************
	 * SOLUTION 2
	 *************************************/
	
	
	/**
	 * Sligthly improved I reduced the space used, and processing
	 * 
	 * 
	 * 		Runtime: 5 ms, faster than 26.13% of Java online submissions for Find the Difference.
			Memory Usage: 38 MB, less than 6.25% of Java online submissions for Find the Difference.
	 * 
	 * 
	 * @failed didn't got the clause against null
	 * 
	 * @time O(N) => N
	 * @space O(N)
	 * @param s
	 * @param t
	 * @return
	 */
	public char findTheDifference1(String s, String t) {

		if (s == null || s.length() == 0) {
			return t.charAt(0);
		}

		HashMap<Character, Integer> hs = new HashMap<>();

		for (Character c : s.toCharArray()) {

			if (!hs.containsKey(c))
			{
				hs.put(c, 1);
			}
			else
			{
				hs.put(c, hs.get(c) + 1);
			}
		}

		int decrement;
		for(Character c : t.toCharArray())
		{
			if (hs.containsKey(c))
			{
				decrement = hs.get(c) - 1;

				if (decrement >= 0)
				{
					hs.put(c, decrement);
				}
				else
				{
					return c;
				}
			}
			else
			{
				return c;
			}
		}

		return 0;
	}

	/**************************************
	 * SOLUTION 3
	 *************************************/

	/**
	 * @score
	 * 		Runtime: 2 ms, faster than 47.26% of Java online submissions for Find the Difference.
			Memory Usage: 38.1 MB, less than 6.25% of Java online submissions for Find the Difference.
	 * 
	 * It's true despite increased time complexity my algorith performs better...
	 * 
	 * 
	 * @time O (N log N)
	 * @space O(N) N equals to s + t
	 * @param s
	 * @param t
	 * @return
	 */
	public char findTheDifference2(String s, String t) {

		if (s == null || s.length() == 0) 
			return t.charAt(0);

		char [] cs = s.toCharArray();
		Arrays.sort(cs);
		
		char [] ct = t.toCharArray();
		Arrays.sort(ct);

		for (int i = 0; i < cs.length; i++) {
			if (cs[i] != ct[i])
				return ct[i];
		}

		return ct[s.length()];
	}

	/**************************************
	 * SOLUTION 4
	 *************************************/
	
	/**
	 * 	Runtime: 1 ms, faster than 99.36% of Java online submissions for Find the Difference.
		Memory Usage: 37.9 MB, less than 9.38% of Java online submissions for Find the Difference.
	 * 
	 * 
	 * XOR a X a X b X b X c = c
	 * 
	 * @time O(N)
	 * @space O(1) 
	 * @param s
	 * @param t
	 * @return
	 */
	public char findTheDifference(String s, String t) {
	
		char x = 0;

		if (s == null || s.length() == 0) 
			return t.charAt(0);

		
		for (int i = 0; i < s.length(); i++) {
			x ^= t.charAt(i) ^ s.charAt(i);
		}
		
		x ^= t.charAt(s.length());
		
		return x;
	}
}
