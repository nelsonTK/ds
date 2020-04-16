package com.data.structures.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * https://leetcode.com/problems/find-all-anagrams-in-a-string/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class FindAllAnagramsInAString {

	public static void main(String[] args) {
		String s = "cbaebabacd";
		String p = "abc";
//		s = "";
//		p = "a";
		FindAllAnagramsInAString f = new FindAllAnagramsInAString();

		System.out.println("s: " + s);
		System.out.println("p: " + p);
		System.out.println("indices: " + Arrays.toString(f.findAnagrams(s, p).toArray()));

	}

	/************************************************
	 * ATTEMPT ONE
	 ***********************************************/
	
	/**[Wrong] permutation is badly writen
	 * 
	 * @failed forgot to add the limit of the string search and therefore string overflowed. I had it on the paper though
	 * failed forgot to treat empty strings
	 * failed aa bb (??) this was because the permutation formula was wrong, 
	 * it does not work for cases where s is always same char and p llwas sme char
	 * 
	 * @time N^3 I believe it is derived from the case where p is half and it is  N(traversal) * N(substring) * N (validation) /2
	 * 
	 * I believe I can reduce this time complexity using an array
	 * @param s
	 * @param p
	 * @return
	 */
	public List<Integer> findAnagrams0(String s, String p) {

		if (s == null || p == null || s.equals("") || p.equals("") || p.length() > s.length())
			return new ArrayList<Integer>();

		List<Integer> ans = new ArrayList<Integer>();

		for (int i = 0; i <= s.length() - p.length(); i ++)
		{
			if (isPermutation(s.substring(i, i + p.length()), p))
			{
				ans.add(i);
			}
		}

		return ans;
	}

	/**
	 * [WRONG]
	 * @param substring
	 * @param p
	 * @return
	 */
	private boolean isPermutation(String substring, String p) {

		int result = p.charAt(0) ^ substring.charAt(0);

		for (int i = 1; i < p.length(); i++)
		{
			result ^= p.charAt(i) ^ substring.charAt(i);
		}

		return result == 0 ? true : false;
	}
	


	/************************************************
	 * SOLUTION TWO
	 ***********************************************/

	/**
	 * I Struggled because of permutations
	 * 
	 * My solutions is quite the same as leetcode in terms of concept but behaves much worse
	 * 
	 * 		Runtime: 1897 ms, faster than 5.55% of Java online submissions for Find All Anagrams in a String.
			Memory Usage: 41.1 MB, less than 6.00% of Java online submissions for Find All Anagrams in a String.
	 * 
	 * 
	 * @failed 
	 * 1) I have struggled to find the error it was the for loop comparison that was wrong
	 * realized that I need to go beyond the bondaries I was expecting
	 * forgot to put boundaries
	 * 
	 * @param s
	 * @param p
	 * @return
	 */
	public List<Integer> findAnagrams2(String s, String p) {

		if (s == null || p == null || s.equals("") || p.equals("") || p.length() > s.length())
			return new ArrayList<Integer>();

		List<Integer> ans = new ArrayList<Integer>();

		HashMap<Character, Integer> pMap = new HashMap<>();

		HashMap<Character, Integer> sMap = new HashMap<>();

		for (Character c : p.toCharArray()) {
			increaseMapVal(pMap, c);
		}

		for (int i = 0; i < p.length(); i++) {
			increaseMapVal(sMap, s.charAt(i));
		}

		int anagramStart = 0;

		evalAnagram(pMap, sMap, p, ans, anagramStart);

		for (int i = p.length(); i <= s.length() - 1; i++) {

			sMap.put(s.charAt(anagramStart), sMap.get(s.charAt(anagramStart)) - 1);
			anagramStart++;
			increaseMapVal(sMap, s.charAt(i));
			evalAnagram(pMap, sMap, p, ans, anagramStart);

		}


		return ans;

	}

	private void evalAnagram(HashMap<Character, Integer> pMap, HashMap<Character, Integer> sMap, String p,List<Integer> ans, int anagramStart)
	{
		//understood why this is unders performant comparin with a hashmap.equals
		//if you have aaaaaaaaaaaaaaaaaaaa, you make n comparations. with hashmap you just do 1.
		for (Character c : p.toCharArray()) {
			if (!pMap.get(c).equals(sMap.get(c)))
				return;
		}

		ans.add(anagramStart);
	}
	


	/************************************************
	 * ATTEMPT TREE
	 ***********************************************/

	/**
	 * OPTIMIZATION ATTEMPT
	 * 
	 * 	The concept is the same, sliding window. add and remove key from hashmap
	 * 
	 * Runtime: 38 ms, faster than 28.40% of Java online submissions for Find All Anagrams in a String.
Memory Usage: 40.9 MB, less than 10.00% of Java online submissions for Find All Anagrams in a String.
	 * 
	 * @time O(NxP) P number of unique chars in p. it occurs when we compare both maps.
	 * @space O(1) since both hashmaps cannot go beyond 26.
	 * 
	 * @param s
	 * @param p
	 * @return
	 */
	public List<Integer> findAnagrams(String s, String p) {


		if (s == null || p == null || s.equals("") || p.equals("") || p.length() > s.length())
			return new ArrayList<Integer>();

		List<Integer> ans = new ArrayList<Integer>();

		HashMap<Character, Integer> pMap = new HashMap<>();

		HashMap<Character, Integer> sMap = new HashMap<>();

		for (Character c : p.toCharArray()) {
			increaseMapVal(pMap, c);
		}

		//preload sMap
		for (int i = 0; i < p.length(); i++) {
			increaseMapVal(sMap, s.charAt(i));
		}

		int anagramStart = 0;

		eval(pMap, sMap, ans, anagramStart);

		//i represents the end o the anagram
		
		for (int i = p.length(); i <= s.length() - 1; i++) {

			if (sMap.get(s.charAt(anagramStart)) == 1)
			{
				sMap.remove(s.charAt(anagramStart));
			}else{
				sMap.put(s.charAt(anagramStart), sMap.get(s.charAt(anagramStart)) - 1);
			}
			anagramStart++;
			increaseMapVal(sMap, s.charAt(i));

			eval(pMap, sMap, ans, anagramStart);

		}


		return ans;

	}

	private void eval(Object a, Object b, List<Integer> ans, int anagramStart)
	{
		if(a.equals(b))
		{
			ans.add(anagramStart);
		}
	}
	
	private void increaseMapVal(HashMap<Character, Integer> map, Character c) {
		if (!map.containsKey(c))
		{
			map.put(c, 1);
		}
		else
		{
			map.put(c, map.get(c) + 1);
		}
	}
}

class FindAllAnagramsInAStringSolution {
	
	/**
	 * I Like this solution is much faster than my hashmap optimized solution
	 * But the concept is the same
	 * 
	 * 
	 * @param s
	 * @param p
	 * @return
	 */
	  public List<Integer> findAnagrams(String s, String p) {
	    int ns = s.length(), np = p.length();
	    if (ns < np) return new ArrayList();

	    int [] pCount = new int[26];
	    int [] sCount = new int[26];
	    // build reference array using string p
	    for (char ch : p.toCharArray()) {
	      pCount[(int)(ch - 'a')]++;
	    }

	    List<Integer> output = new ArrayList();
	    // sliding window on the string s
	    for (int i = 0; i < ns; ++i) {
	      // add one more letter 
	      // on the right side of the window
	      sCount[(int)(s.charAt(i) - 'a')]++;
	      // remove one letter 
	      // from the left side of the window
	      if (i >= np) {
	        sCount[(int)(s.charAt(i - np) - 'a')]--;
	      }
	      // compare array in the sliding window
	      // with the reference array
	      if (Arrays.equals(pCount, sCount)) {
	        output.add(i - np + 1);
	      }
	    }
	    return output;
	  }
	}
