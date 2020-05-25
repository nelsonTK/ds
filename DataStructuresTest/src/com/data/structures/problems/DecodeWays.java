package com.data.structures.problems;

import java.util.Arrays;
import java.util.HashMap;

public class DecodeWays {
	static DecodeWays d = new DecodeWays();
	static DecodeWaysSolution2 s = new DecodeWaysSolution2();

	public static void main(String[] args) {
		String str = "12344";
		//str = "126";
		System.out.println(d.numDecodings(str));
		System.out.println(s.numDecodings(str));
	}

	/*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * 	@intuition
	 * 			DP Approach.
	 * 			The current possible combinations for a digit depends on the number of combinations 
	 * 			of the two past digits positions (i - 1, i - 2).

    	@score
    		Runtime: 2 ms, faster than 66.36% of Java online submissions for Decode Ways.
			Memory Usage: 40.1 MB, less than 5.66% of Java online submissions for Decode Ways.


        @fail
            1) Array out of bounds

    	@time	O(N)
    	@space	O(N)
    	@bcr	
	 **/
	public int numDecodings0(String s) {

		if (s == null || s.length() == 0 || s.charAt(0) == '0')
			return 0;

		int n = s.length() - 1, 
				msd = 0, lsd = 0, twoDigits; //most significant digit, least significant digit

		int [] dp = new int[1 + n + 1]; //one at the back other at front
		dp[0] = 1;
		dp[1] = 1;

		for (int i = 1; i <= n; i++)
		{
			msd = Integer.parseInt(s.substring(i - 1, i));
			lsd = Integer.parseInt(s.substring(i, i + 1));
			twoDigits = msd*10 + lsd;

			if (twoDigits <= 26 && twoDigits >= 10)
			{
				dp[i + 1] = dp[i - 1];
			}

			if (lsd >= 1 && lsd <= 9)
			{
				dp[i + 1] += dp[i];
			}

			//optional condition to stop the search when invalid zero is found
			if ((twoDigits > 26 && lsd == 0 )|| twoDigits == 0)
			{
				return 0;
			}
		}

		return  dp[n + 1];
	}

	/*********************************
	 * SOLUTION 2
	 ********************************/   
	
	
	/**
	 * @intuition
	 * 
	 * @score
			Runtime: 2 ms, faster than 66.55% of Java online submissions for Decode Ways.
			Memory Usage: 38.2 MB, less than 5.66% of Java online submissions for Decode Ways.
	 * 
	 * @fail
	 * 		1) forgot to add ways to the memo array
	 * 		2) failed, I was incremensing ways wrongly, I should have increased by 1, instead of by ways+1. it was wrong
	 * 
	 * @time		O(N) - As we only decode each index once, this is O(N)
	 * @space		O(N)
	 * 
	 * @param s
	 * @return
	 */
	public int numDecodings(String s) {

		if (s == null || s.length() == 0)
			return 0;

		int memo[] = new int[s.length()];
		
		Arrays.fill(memo, -1);
		return decode(s, 0, memo, 0);
	}


	private int decode(String s, int start, int []memo, int ways)
	{

		if (start >= s.length())
			return 1;

		if (s.charAt(start) == '0')
			return 0;

		if (memo[start] >= 0)
			return memo[start];

		ways += decode(s, start+1, memo, ways);

		if (start + 1 <= s.length() - 1)
		{
			int twoDigits = Integer.parseInt(s.substring(start, start + 2));
			if (twoDigits >= 10 && twoDigits <= 26)
				ways += decode(s, start+2, memo, ways);
		}
		
		memo[start] = ways;
		
		return ways;
	}
}


/*********************
 * OTHERS SOLUTIONS
 *********************/

/**
 * @INTUITION
 * 		The same principles than my solution
 * 		but probably less messy.
 *		they opted to preserve i of dp array and decrease index in the string by one.
 *		I return the executiong if an invalid zero is found. And they go through the whole process.
 *		But I thought that it was fundamental to have that condition place, but it was optional
 * 
 * @author Nelson Costa
 *
 */
class DecodeWaysSolution2 {

	public int numDecodings(String s) {

		if(s == null || s.length() == 0) {
			return 0;
		}

		// DP array to store the subproblem results
		int[] dp = new int[s.length() + 1];
		dp[0] = 1;
		// Ways to decode a string of size 1 is 1. Unless the string is '0'.
		// '0' doesn't have a single digit decode.
		dp[1] = s.charAt(0) == '0' ? 0 : 1;

		for(int i = 2; i < dp.length; i += 1) {

			// Check if successful single digit decode is possible.
			if(s.charAt(i-1) != '0') {
				dp[i] += dp[i-1];  
			}

			// Check if successful two digit decode is possible.
			int twoDigit = Integer.valueOf(s.substring(i-2, i));
			if(twoDigit >= 10 && twoDigit <= 26) {
				dp[i] += dp[i-2];
			}
		}
		return dp[s.length()];

	}
}

/**
 * Recursion with Memoization
 * 
 * Easy to understand.
 * 
 * could be improved with an array
 * @author Nelson Costa
 *
 */
class DecodeWaysSolution1 {

    HashMap<Integer, Integer> memo = new HashMap<>();

    private int recursiveWithMemo(int index, String str) {

        // If you reach the end of the string
        // Return 1 for success.
        if (index == str.length()) {
            return 1;
        }

        // If the string starts with a zero, it can't be decoded
        if (str.charAt(index) == '0') {
            return 0;
        }

        if (index == str.length()-1) {
            return 1;
        }

        // Memoization is needed since we might encounter the same sub-string.
        if (memo.containsKey(index)) {
            return memo.get(index);
        }

        int ans = recursiveWithMemo(index+1, str);
        if (Integer.parseInt(str.substring(index, index+2)) <= 26) {
             ans += recursiveWithMemo(index+2, str);
         }

        // Save for memoization
        memo.put(index, ans);

        return ans;
    }
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        return recursiveWithMemo(0, s);
    }
}
