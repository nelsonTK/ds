package com.data.structures.problems;

/**
 * https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class MinimumASCIIDeleteSumforTwoStrings {

	/*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * @intuition
	 * 		it was hard to be absolutely sure that my solution was right.
	 * 		but after that stage was cleared I was sure that this would yield the optimal result.
	 * 
	 * 		the gist is to use the logic of longest common subsequence to find the sequence with the biggest sum.
	 * 		and then subtract the total cost of characters by the double of the lcs found.
	 * 		we double because the characters guaranteedtly exists in both strings
	 * 
	 * @score
	 * 		Runtime: 12 ms, faster than 94.81% of Java online submissions for Minimum ASCII Delete Sum for Two Strings.
	 * 		Memory Usage: 39.9 MB, less than 33.91% of Java online submissions for Minimum ASCII Delete Sum for Two Strings.
	 *     
	 * @time
	 * 		O(N*M)
	 * 
	 * @space
	 * 		O(N*M)
	 *     
	 **/
	public int minimumDeleteSumA(String a, String b) {
		//create ascii map
		//get ascii = curchar - a + 97 // if I didnt found out the string.codePointAt(index) method

		int ascii = 0;

		//sum total asciis
		for (int i = 0; i < a.length(); i++)
			ascii += a.codePointAt(i);


		for (int i = 0; i < b.length(); i++)
			ascii += b.codePointAt(i);

		int [][] dp = new int[a.length() + 1][b.length() + 1];

		//find longest common subsequence with minimal cost
		for (int wa = 1; wa < dp.length; wa++)
		{
			for (int wb = 1; wb < dp[wa].length; wb++)
			{
				if (a.charAt(wa-1) == b.charAt(wb-1))
				{
					dp[wa][wb] = dp[wa-1][wb-1] + a.codePointAt(wa - 1);
				}
				else
				{
					dp[wa][wb] = Math.max(dp[wa-1][wb], dp[wa][wb-1]);
				}

			}
		}

		//subtract the total with the maximal found and multiplied by two because it is common
		return ascii - (2 * dp[a.length()][b.length()]);
	}


	/*********************************
	 * SOLUTION 2
	 ********************************/
	/***
	 * 	@intuition
	 * 		Same logic then DP solution
	 * 
	 * 	@score
	 * 		Runtime: 27 ms, faster than 36.36% of Java online submissions for Minimum ASCII Delete Sum for Two Strings.
	 * 		Memory Usage: 42.9 MB, less than 19.48% of Java online submissions for Minimum ASCII Delete Sum for Two Strings.
	 * 
	 * 	@time
	 * 		O(M*N)
	 * 
	 * 	@space
	 * 		O(M*N)
	 * 
	 * 
	 ***/
	Integer [][] memo;
	public int minimumDeleteSumB(String a, String b) {

		memo = new Integer[a.length() + 1][b.length() + 1];

		int ascii = a.chars().sum() + b.chars().sum();

		return ascii - (getMinimum(0,0, a,b) * 2);
	}

	private int getMinimum (int ia, int ib, String a, String b)
	{
		if (ia >= a.length() || ib >= b.length())
			return 0;

		if (memo[ia][ib]!= null)
			return memo[ia][ib];

		if (a.charAt(ia) == b.charAt(ib))
		{
			//could have being b.charAt(ib) too, because they are equal
			return memo[ia][ib] = a.charAt(ia) + getMinimum(ia + 1, ib + 1, a, b);
		}
		else
		{
			return memo[ia][ib] = Math.max(getMinimum(ia + 1, ib, a, b), getMinimum(ia, ib + 1, a, b));
		}
	}
}
