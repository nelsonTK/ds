package com.data.structures.problems;

/**
 * https://leetcode.com/problems/rotate-string/
 * EASY
 * @author Nelson Costa
 *
 */
public class RotateString {

	 /*********************************
	 * SOLUTION 1
	 ********************************/

	private int[] lps(String s){

		int len = 0, i = 1;
		int [] array = new int[s.length()];

		while (i < s.length())
		{
			if (s.charAt(len) == s.charAt(i))
			{
				array[i++] = ++len;
			}
			else if (len == 0)
			{
				array[i++] = 0;
			}
			else {
				len = array[len - 1];
			}
		}

		return array;
	}

	/**
	 * @intuition
	 * 		The gist is if we can find a string inside another.
	 * 		and for that I used KMP I just needed to consider B a cyclic array.
	 * 		
	 * 		and if I didn't found a match in A.len*2 iterations Then the strings are not rotated. else it is.
	 * 
	 * 		edge cases includes size disparity.
	 * 	
	 * 
	 * @time
	 * 		O(N)
	 * 
	 * @space
	 * 		O(N)
	 * 		
	 * @param A
	 * @param B
	 * @return
	 */
	public boolean rotateString(String A, String B) {
		/*
        'abcde'
        'cdeab'

        'abac
         acab'

		 */    

		if (A.length() != B.length())
			return false;

		if (A.length() == 0)
			return true;

		int [] lps = lps(A);

		int len = 0, i = 0, iterations = 0;

		while (iterations < A.length() * 2)
		{
			if (A.charAt(len) == B.charAt(i))
			{
				len++;
				i++;
				iterations++;

				if (i >= B.length())
				{
					i = 0;
				}
			}
			else
			{
				if (len == 0)
				{
					i++;
					if (i >= B.length())
					{
						i = 0;
					}
					iterations++;
				}
				else
				{
					len = lps[len - 1];
				}
			}

			if (len == A.length())
				return true;
		}

		return false;
	}
}

/**
 * Simple solution, much worst performance than mine
 * but very quick to implement
 * 
 * @author Nelson Costa
 *
 */
class RotateStringSolution2 {
    public boolean rotateString(String A, String B) {
        return A.length() == B.length() && (A + A).contains(B);
    }
}
