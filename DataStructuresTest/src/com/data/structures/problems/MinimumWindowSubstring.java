package com.data.structures.problems;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	/**
	 [WRONG]
	 It is not told anywhere that T supports duplicates, so I assumed it was not supported, I made a mistake.
	 but well this is a solution for no duplicates in T.

    @fail
        1) I was putting the wrong content in the queue, was putting index of curated list instead of its content
        2) the for loop is badly writen, it ends up before expected
        3) missing final validation for maximum value
        4) increase index was wrong
		5) Miss interpretation blew up the algorithm, T can have duplicates....
    @time   O(N)
    @space  O(N)

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

	/**
	 * 
	 * [WRONG]
	 I Cannot detect the error... so sad..
	 get almost all cases wright but one
	 
    	fail
	        1)lacking verification on equality
	        2) wrong index being passed to check Equality, 
	        should have been the compresset.get(index) instead of left and right directly
	        3) right side duplicating characters after the equals if condition runs
	        
	        
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


		//3rd iterate compressed array
		//increase right if not complete window
		//decrease left if complete window
		//update matchwindow and complete var
		//update max indexes

		int lettersCompleted = 0;
		int left = 0, right = 0;
		int [] distance = new int[]{-1, -1};
		int max = Integer.MAX_VALUE;
		HashMap<Character, Integer> windowMap = new HashMap<>();
		Set<Integer> visited = new HashSet<>();

		//for (int i = 0; right < compressed.size() && left < ; i++)
		while (right < compressed.size() && left <= right)
		{
			if(lettersCompleted == tMap.size())
			{
				ch = s.charAt(compressed.get(left));

				//update completed
				//we can have letter that overflow the minimal requirement

				//if the letter at left has the exact match with the reference tMap then we know we have a letter less completed
				if (windowMap.getOrDefault(ch, 0) == tMap.get(ch))
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

				//forward left

			}
			else
			{
				//update windowMap
				//we can have letter that overflow the tMap minimal count

				if (visited.contains(right))
					right++;

				if(right < compressed.size())
				{
					ch = s.charAt(compressed.get(right));
					visited.add(right);

					windowMap.put(ch, windowMap.getOrDefault(ch, 0) + 1);


					//update completed
					if (windowMap.getOrDefault(ch, 0) == tMap.get(ch))
					{
						lettersCompleted++;
					}

					//check if equal
					if(lettersCompleted == tMap.size())
					{
						max = checkEqual(compressed.get(right),compressed.get(left), max, distance);
						//                    System.out.printf("max: %d", max);
						//                    System.out.printf(" - coordinates: %d %d | ", compressed.get(left), compressed.get(right));
					}
					else
					{
						right++;
					}

					//forward right
				}
			}


		}

		//4th create response

		if(distance[0] == -1)
			return "";

		//        System.out.printf("distance: %d, %d", distance[0], distance[1]);


		StringBuilder sb = new StringBuilder("");
		for (int i = distance[0]; i <= distance[1]; i++)
		{
			sb.append(s.charAt(i));
			//System.out.printf("charAt - %s", s.charAt(i));
		}


		return sb.toString();
	}


	private int checkEqual(int right, int left, int max, int [] distance){
		if(right - left < max)
		{
			distance[0] = left;
			distance[1] = right;
			max = right - left;

			//                    System.out.printf("update Dist - (%d, %d)", left, right);
		}

		return max;
	}
}
