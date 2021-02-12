package com.data.structures.problems;

/**
 * /https://leetcode.com/problems/longest-repeating-character-replacement/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class LongestRepeatingCharacterReplacement {

	/*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * @intuition
	 * 		poor solution, N^2 in which for every new position we try considering the first element valid, and then invalid.
	 * 		O(N) Solution is much more attractive but more difficult to come up with
	 * 
	 * 
	 * @score
	 * 		Runtime: 2748 ms, faster than 5.09% of Java online submissions for Longest Repeating Character Replacement.
	 * 		Memory Usage: 39 MB, less than 68.20% of Java online submissions for Longest Repeating Character Replacement.
	 * 
	 * with prunning
	 * 		Runtime: 1678 ms, faster than 5.09% of Java online submissions for Longest Repeating Character Replacement.
	 * 		Memory Usage: 39.1 MB, less than 55.56% of Java online submissions for Longest Repeating Character Replacement.
	 * 
	 * 
	 * @time
	 * 		O(N^2)
	 * 
	 * @space
	 * 		O(N)
	 * 
	 * @param s
	 * @param k
	 * @return
	 */
	public int characterReplacement0(String s, int k) {

		//string size 1 max is 1, string size 0 max is 0;
		//k == 0

		if (s == null || s.length() == 0)
			return 0;

		if (s.length() == 1)
			return 1;

		int max = 1, curCount, curLen, R;
		char c;

		for (int L = 0; L < s.length() - 1; L ++)
		{
			//one iteration counting that counting the first character is the best,
			//another iteration counting that the first character is not the best
			for (int i = 0; i < 2; i++)
			{
				curCount = s.charAt(L + i) == s.charAt(L) ? k : k - i;

				c = s.charAt(L + i);
				curLen = 1;
				R = L + 1; 

				while (curCount >= 0 && R < s.length())
				{
					if (s.charAt(R) == c)
					{
						curLen++;
					}
					else if (curCount == 0)
					{
						break;
					}
					else //if (s.charAt(R) != c)
					{
						curCount--;
						curLen++;
					}

					R++;
				}

				max = Math.max(max, curLen);

				if (max == s.length())
					return max;
			}
		}

		return max;

	}



	/*********************************
	 * SOLUTION 2
	 ********************************/
	/**
	 * @intuition
	 * 	This is a problem that really looks like a binary search code, we have the while loop and then the pointer move according to the condition.
	 * 	in this solution the gist is to increase the Right (R) pointer every time and reduce the Left (L) when the length of the array minus the max value ever found is greater than the number of operations.
	 * 	when it is it means that we need more operations to make the replacements than needed.
	 * 
	 * 	so again the gist is to have a window only with the max number of possible repeated character. never shrink below the max found, and increase whenever a new more frequent element pops up.
	 * 	I had troubles because I wanted to have the max variable up to date, but in the problem it wasn't needed at all.
	 * 
	 * @score
	 * 	Runtime: 4 ms, faster than 93.69% of Java online submissions for Longest Repeating Character Replacement.
	 * 	Memory Usage: 38.9 MB, less than 80.89% of Java online submissions for Longest Repeating Character Replacement.
	 * 
	 * @time
	 * 	O(N)
	 * 
	 * @space
	 * 	O(1)
	 *  
	 * 
	 * @param s
	 * @param k
	 * @return
	 */
	public int characterReplacement(String s, int k) {

		int [] ch = new int[26];

		int L = 0, R = 0, max = 0, res = 0;

		while (R < s.length())
		{
			ch[s.charAt(R) - 'A']++;
			max = Math.max(ch[s.charAt(R) - 'A'], max);

			 //condition that controls how the window moves; put on paper and you will understand, this is not obvious at all. great solution...
			while (R - L + 1 - max > k)
			{
				ch[s.charAt(L) - 'A']--;
				L++;
			}

			res = Math.max(res, R - L + 1);
			R++;

		}

		return res;
	}
}

/*********************
 * UNOFFICIAL SOLUTIONS
 *********************/
/**
 * Cool solution with the same logic than mine but more sucint
 * @author Nelson Costa
 *
 */
class LongestRepeatingCharacterReplacementUnofficialSolutions{
	public int characterReplacement(String s, int k) {
		int l = 0, r = 0, res = 0, dup = 0;
		int[] cnt = new int[26];
		for (r = 0; r < s.length(); r++) {
			dup = Math.max(dup, ++cnt[s.charAt(r) - 'A']);  // see dup +1, or dup same
			while (r - l + 1 - dup > k) {   // only for dup same, shift the same to right;
				cnt[s.charAt(l++) - 'A']--;
			}
			res = Math.max(res, r - l + 1);  // res would +1, if dup +1;
		}
		return res;
	}
}
