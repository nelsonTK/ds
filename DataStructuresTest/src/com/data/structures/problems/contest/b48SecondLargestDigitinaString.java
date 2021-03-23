package com.data.structures.problems.contest;

import java.util.TreeSet;

/**
 * https://leetcode.com/problems/second-largest-digit-in-a-string/
 * EASY
 * @author Nelson Costa
 *
 */
public class b48SecondLargestDigitinaString {

	/**
	 * not sure if this was the best possible, I could not thing in a better solution
	 * Used tree set to solve this problem.
	 * 
	 * 
	 * @time
	 * 		nlogn
	 * 
	 * @space 
	 * 		n
	 * @param s
	 * @return
	 */
	public int secondHighest(String s) {
		TreeSet<Integer> p = new TreeSet<Integer>((a,b)-> Integer.compare(b,a));

		for (char c : s.toCharArray())
		{
			if (Character.isDigit(c) )
			{
				p.add(c - '0');
			}
		}

		if (p.size() >= 2)
		{
			p.pollFirst();
			return p.first();
		}
		return -1;
	}
}


class b48SecondLargestDigitinaStringUnofficialSolution {

	/**
	 * This solution what they do is to use two variables and pass the first value to the second when a value is greater than the first
	 * and the just update the second element when the value is bigger than second elemenet but bellow first
	 * 
	 * An alternative solution could be using a queu with size two.
	 * 
	 * well done
	 * @param s
	 * @return
	 */
	public int secondHighest(String s) {
		int first = -1, sec = -1;
		for (int i = 0; i < s.length(); ++i) {
			char c = s.charAt(i); 
			if (Character.isDigit(c)) {
				int d = c - '0';
				if (first < d) {
					sec = first;
					first = d;
				}else if (sec < d && d < first) {
					sec = d;
				}
			}
		}
		return sec;
	}
}