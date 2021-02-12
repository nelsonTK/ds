package com.data.structures.problems;

import java.util.HashSet;
import java.util.Set;
import java.util.List;

/**
 * https://leetcode.com/problems/word-break/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class WordBreak_Take3 {


	 /*********************************
	 * SOLUTION 1
	 ********************************/	
	/** 
	 * 	@intuition
	 * 		The false values are cached, as far as I know the true values are never cached, but at the end to buble up. it is cleaver way of solving this and avoiding a backtracking hell.
	 * 		So that's where the cache helps, in preventing to calculate values that we already calculated.
	 * 		We cache the start value so we know that whenever we start from a value that has value we don't need to calculate everything again.
	 * 		tough problem afterall		
	 * 
	 * 
	 *  @score
	 *  	Runtime: 5 ms, faster than 76.95% of Java online submissions for Word Break.
	 *  	Memory Usage: 39 MB, less than 82.42% of Java online submissions for Word Break.
	 *  
	 *  @media
	 *  	https://imgur.com/9G5yvQy (x means false outcome starting from that position)
	 *  
	 *  @time
	 *  	O(N^3) que 
	 *  	
	 *  @space
	 *  	O(N)
	 **/
	Boolean [] memo;
	public boolean wordBreak0(String s, List<String> wordDict) {

		memo = new Boolean[s.length()];
		return canBreak(0, new HashSet<>(wordDict),s);

	}

	private boolean canBreak(int idx, Set<String> set, String s)
	{

		if (idx == s.length())
			return true;

		if (memo[idx] == null)
		{

			for (int end = idx + 1; end <= s.length(); end++)
			{

				if (set.contains(s.substring(idx, end)) && canBreak(end, set, s))
				{
					memo[idx] = true;
					return true;
				}
			}

			memo[idx] = false;
		}

		return memo[idx];
	}
	
	
	

	 /*********************************
	 * SOLUTION 2
	 ********************************/
	/**
	 * @intuition
	 * 		Solution with DP, not obvious at all.
	 * 		we start from the begining of the string and start building our way up.
	 * 		we will check all possible possibilities.
	 * 		
	 * 		Like: check for size one if all words exists, then size 2, than 3.
	 * 		and for each size we try to see from zero to size if the the varios words that can be formed.
	 * 		when the previous word exists and the current word exists, than we mark the end of the word.
	 * 
	 * 		This is one of those solutions where memoization and DP differe in implementation and in logic.
	 * 		they mostly share the solution. Tough problem.
	 * 
	 * @param s
	 * @param wordDict
	 * @return
	 */
    public boolean wordBreak(String s, List<String> wordDict) {
        
        
        boolean dp[] = new boolean [s.length()+1];
        HashSet<String> set = new HashSet<>(wordDict);
        dp[0] = true;
        
        for (int i = 1; i <= s.length(); i++)
        {
            for (int j = 0; j < i; j++)
            {
                if (dp[j] && set.contains(s.substring(j,i)))
                {
                    dp[i] = true;
                }
            }
        }
        
        return dp[s.length()];
    }
}
