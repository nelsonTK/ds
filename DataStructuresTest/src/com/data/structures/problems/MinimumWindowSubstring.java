package com.data.structures.problems;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/minimum-window-substring/
 * HARD	
 * @author Nelson Costa
 *
 */
public class MinimumWindowSubstring {

	static MinimumWindowSubstring m = new MinimumWindowSubstring();
	public static void main(String[] args) {

		String s = "ADOBECODEBANC", t = "ABC";
		s = "aasadhkaassdj";
		t = "hja";
		//		s = "jasgascdvbdjhsvdhgv";
		//		t = "jhasg";

		s = "";;
		System.out.println(m.minWindow(s, t));
	}

	/*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * [WRONG]
	 *	 It is not told anywhere that T supports duplicates, so I assumed it was not supported, I made a mistake.
	 *	 but well this is a solution for no duplicates in T.
	 *
 	 * @fail
	 *        1) I was putting the wrong content in the queue, was putting index of curated list instead of its content
	 *        2) the for loop is badly writen, it ends up before expected
	 *        3) missing final validation for maximum value
	 *        4) increase index was wrong
	 *		5) Miss interpretation blew up the algorithm, T can have duplicates....
	 * @time   O(N)
	 *  @space  O(N)
	 *
	 **/
	public String minWindow0(String s, String t) {

		if(t == null || t.length() == 0)
			return s;


		//1st pass
		//save elements in the T string in a set, to have O(1) for lookup
		Set<Character> tSet = new HashSet<>();
		List<Integer> curated = new ArrayList<>();

		for(char c : t.toCharArray())
			tSet.add(c);

		for (int i = 0; i < s.length(); i ++)
		{
			if (tSet.contains(s.charAt(i)))
			{
				curated.add(i);
			}
		}

		//2nd pass
		//Iterate throught the new array marking in a hashmap letters and counts

		//if not all elements:  increase
		//if all elements: decrease window
		HashMap<Character, Integer> letterToCount = new HashMap<>();
		ArrayDeque<Integer> window = new ArrayDeque<>();
		int maxDiff = Integer.MAX_VALUE;
		int [] ans = new int[]{-1, -1};
		//for (int i = 0; i < curated.size(); i ++)
		int index = 0;
		while (index < curated.size())
		{
			if (letterToCount.size() == tSet.size())
			{
				maxDiff = Math.min(reduceWindow(letterToCount, tSet, window, s, ans, maxDiff), maxDiff);
			}
			else
			{   
				window.add(curated.get(index));
				letterToCount.put(s.charAt(curated.get(index)), letterToCount.getOrDefault(s.charAt(curated.get(index)), 0) + 1);

				maxDiff = Math.min(reduceWindow(letterToCount, tSet, window, s, ans, maxDiff), maxDiff);

				index++;

			}

		}

		if (ans[0] == -1)
			return "";

		//3rd pass
		//Get the winner start and end and loop though the string
		StringBuilder min = new StringBuilder("");

		for (int i = ans[0]; i <= ans[1]; i++)
		{
			min.append(s.charAt(i));
		}

		return min.toString();
	}


	private int reduceWindow(HashMap<Character, Integer> letterToCount, Set<Character> tSet, ArrayDeque<Integer>window, String s, int [] ans, int maxDiff) {

		while  (letterToCount.size() == tSet.size())
		{
			//update Maximum 
			if (window.peekLast() - window.peekFirst() < maxDiff)
			{
				ans[0] = window.peekFirst();
				ans[1] = window.peekLast();
				maxDiff = window.peekLast() - window.peekFirst();

				//System.out.printf("= %d %d \n", window.peekLast(), window.peekFirst());
			}

			//reduce letter count
			if (letterToCount.get(s.charAt(window.peek())) == 1)
			{
				letterToCount.remove(s.charAt(window.peek()));
			}
			else
			{
				letterToCount.put(s.charAt(window.peek()), letterToCount.get(s.charAt(window.peek())) - 1);
			}

			//remove from queue  [reduce window]
			window.poll();
		}

		return maxDiff;
	}
	
	/*********************************
	 * SOLUTION 2
	 ********************************/

	/**
	 * 
	 *	 @intuition
	 *	 	I create a map of T's characters and counts
	 *	 	than I create a compressed version of string s with only the characters in t string.
	 *	 	from there I start iterating, where I remove elements when we have a match, and I add elements when we don't have a match
	 *	 
	 *	 @score
	 *		Runtime: 19 ms, faster than 25.29% of Java online submissions for Minimum Window Substring.
	 *		Memory Usage: 49.3 MB, less than 5.00% of Java online submissions for Minimum Window Substring.
	 * 
	 *	        
	 *	 @comments
	 *		One of the annoying things with this solution is that I had to use a visited array 
	 *	    to prevent the right side elements to be processed twice.
	 *	    I believe I can solve it if I do another approach for this problem.
	 *	        
	 *	 @fail
	 *        1)lacking verification on equality
 	 *       2) wrong index being passed to check Equality, 
	 *        should have been the compresset.get(index) instead of left and right directly
	 *        3) right side duplicating characters after the equals if condition runs
	 *        4) unforgivable mistake that was cause by me comparing two character objects with "=="
	 * @param s
	 * @param t
	 * @return
	 */
	public String minWindow(String s, String t) {

		if(t == null || t.length() == 0)
			return s;


		//1st create Map of T
		HashMap<Character, Integer> tMap = new HashMap<>();
		List<Integer> compressed = new ArrayList<>();        
		char ch;

		for (char c : t.toCharArray())
		{
			tMap.put(c, tMap.getOrDefault(c, 0) + 1);
		}
        
		//2nd create compressed list
		for (int i = 0; i < s.length(); i++)
		{
			ch = s.charAt(i);
			if(tMap.containsKey(ch))
			{
				compressed.add(i);
			}
		}

		//3rd find substring
		int lettersCompleted = 0;
		int left = 0, right = 0;
		int [] distance = new int[]{-1, -1};
		int max = Integer.MAX_VALUE;
		HashMap<Character, Integer> windowMap = new HashMap<>();
		Set<Integer> visited = new HashSet<>();

		while (right < compressed.size() && left <= right)
		{
			if(lettersCompleted == tMap.size())
			{
				ch = s.charAt(compressed.get(left));

				//update completed
				if (windowMap.getOrDefault(ch, 0).equals(tMap.get(ch)))
				{
					lettersCompleted--;
				}

				//update windowMap                
				if(windowMap.get(ch) == 1)
				{
					windowMap.remove(ch);
				}
				else //
				{
					windowMap.put(ch, windowMap.get(ch) - 1);
				}


				left++;

				//update distance array
				if(lettersCompleted == tMap.size())
					max = checkEqual(compressed.get(right),compressed.get(left), max, distance);

			}
			else
			{
				//update windowMap
				if (visited.contains(right))
					right++;

				if(right < compressed.size())
				{
					ch = s.charAt(compressed.get(right));
					visited.add(right);

					windowMap.put(ch, windowMap.getOrDefault(ch, 0) + 1);

                    
					if (windowMap.getOrDefault(ch, 0).equals(tMap.get(ch)))
					{
						lettersCompleted++;
					}
                    

					//check if equal
					if(lettersCompleted == tMap.size())
					{
						max = checkEqual(compressed.get(right),compressed.get(left), max, distance);

					}
					else
					{
						right++;
					}
					//forward right
				}
			}
		}

		if(distance[0] == -1)
			return "";



		//4th create response
		StringBuilder sb = new StringBuilder("");
		for (int i = distance[0]; i <= distance[1]; i++)
		{
			sb.append(s.charAt(i));
		}


		return sb.toString();
	}


	private int checkEqual(int right, int left, int max, int [] distance){
		if(right - left < max)
		{
			distance[0] = left;
			distance[1] = right;
			max = right - left;
		}

		return max;
	}
}


/*********************
* OTHERS SOLUTIONS
*********************/

/**
 * The concept is the same as mine but implemented different than mine
 * 
 * @intuition
 * 		Always add the next element and move forward (right++)
 * 		check if we have a formed/completed match
 * 		while that match is on reduce (left --) the substring size (they never remove elements from the array)
 * 		
 * 
 * 
 * @score
		Runtime: 14 ms, faster than 43.87% of Java online submissions for Minimum Window Substring.
		Memory Usage: 45.5 MB, less than 6.27% of Java online submissions for Minimum Window Substring.
		
 * @author Nelson Costa
 *
 */
class MinimumWindowSubstringSolution3 {
    public String minWindow(String s, String t) {

        if (s.length() == 0 || t.length() == 0) {
            return "";
        }

        Map<Character, Integer> dictT = new HashMap<Character, Integer>();

        for (int i = 0; i < t.length(); i++) {
            int count = dictT.getOrDefault(t.charAt(i), 0);
            dictT.put(t.charAt(i), count + 1);
        }

        int required = dictT.size();

        // Filter all the characters from s into a new list along with their index.
        // The filtering criteria is that the character should be present in t.
        List<Pair<Integer, Character>> filteredS = new ArrayList<Pair<Integer, Character>>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (dictT.containsKey(c)) {
                filteredS.add(new Pair<Integer, Character>(i, c));
            }
        }

        int l = 0, r = 0, formed = 0;
        Map<Character, Integer> windowCounts = new HashMap<Character, Integer>();  
        int[] ans = {-1, 0, 0};

        // Look for the characters only in the filtered list instead of entire s.
        // This helps to reduce our search.
        // Hence, we follow the sliding window approach on as small list.
        while (r < filteredS.size()) {
            char c = filteredS.get(r).getValue();
            int count = windowCounts.getOrDefault(c, 0);
            windowCounts.put(c, count + 1);

            if (dictT.containsKey(c) && windowCounts.get(c).intValue() == dictT.get(c).intValue()) {
                formed++;
            }

            // Try and contract the window till the point where it ceases to be 'desirable'.
            while (l <= r && formed == required) {
                c = filteredS.get(l).getValue();

                // Save the smallest window until now.
                int end = filteredS.get(r).getKey();
                int start = filteredS.get(l).getKey();
                if (ans[0] == -1 || end - start + 1 < ans[0]) {
                    ans[0] = end - start + 1;
                    ans[1] = start;
                    ans[2] = end;
                }

                windowCounts.put(c, windowCounts.get(c) - 1);
                if (dictT.containsKey(c) && windowCounts.get(c).intValue() < dictT.get(c).intValue()) {
                    formed--;
                }
                l++;
            }
            r++;   
        }
        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }
}