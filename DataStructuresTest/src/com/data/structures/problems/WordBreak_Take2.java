package com.data.structures.problems;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * https://leetcode.com/problems/word-break/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class WordBreak_Take2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * 
	 * @score
	 		Runtime: 12 ms, faster than 17.30% of Java online submissions for Word Break.
			Memory Usage: 43.9 MB, less than 5.08% of Java online submissions for Word Break.
	 * 
	 * 
	 * @fail
	 * 		1) Failed for the case where string size is equal to one...I was skipping that one element
	 * 
	 * @param s
	 * @param wordDict
	 * @return
	 */
    public boolean wordBreak(String s, List<String> wordDict) {

        int n = s.length();
        boolean [] dp = new boolean[n + 1];
        dp[0] = true;
        Set<String> set = new HashSet<>(wordDict);
        
        for (int i = 1; i <= s.length(); i++)
        {
            for (int j = i; j <= s.length(); j++)
            {
                if (dp[i - 1] && set.contains(s.substring(i - 1, j)))
                {
                    dp[j] = true;
                }
            }
        }
        
        return dp[n];
    }
}



/*********************
 * OTHERS SOLUTIONS
 *********************/

/**
 * Beautiful solution with DP, very very smart
 * 
 * the intuition is like:
 * 
 * a string can be broken into wordDict words if -> the last word belongs to the dictionary and all the others before that word also does.
 * 
 * So they find a way to turn this problem into subproblems
 * 
 * And they find a way to start from the bottom
 * 
 * and the base case is and empty array belongs to the array, so from there, you validade if the first char of the string is a word in the array
 * 
 * if it is and the words before also are (for the first case they are empty) than it is valid.
 * 
 * than for the first two chars we try words in that range, than for the first three until we get to the end of the array.
 * 
 * 
 * Difficult to explain, let's try again:
 * 
 * 
 * 		Finding the Recursive aproach
 * 		
 * 			Identifying the subproblems
 * 				1) if the last word belongs to the set, and all the words behind also belong, than we have something valid.
 * 				2) if we apply the rule 1) to what was behind the last word, and than to was was behind that pre last word
 * 					we start to realize that our global solution is obtain by solving smaller problems.
 * 		
 * 			Identifying the base case
 * 				3) We need a base case to start from the bottom. 
 * 					It is when we are in the first character, everything that is behind must be true.
 * 			
 * 			Figuring out how to fill the DP Table
 * 				4) We need to understand how we are going to fill the dp table with the answer
 * 					In this case the table will have a boolean that indicates from that index backwards you have all words.
 * 					
 * 
 * 			This is pretty much the formula to apply DP.
 * 
 * @author Nelson Costa
 *
 */
class WordBreakSolution4 {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet=new HashSet(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}


/**
 * Very interesting solution with breadth first search.
 * 
 * You think in the string as a tree.
 * 
 * at the root you have position 0 which represents the end of the first cheacter of the string, 
 * from there you add all the words starting in zero to the queue.
 * each of those words will represent a diferent branch of the tree.
 * The one that reaches the end of the string with success is the branch that was able to succeed, thus meaning there is a combination possible.
 * 
 * 
 * O (N^2)
 * O (N)
 * 
 * 
 * @author Nelson Costa
 *
 */
class WordBreakSolution3 {
	public boolean wordBreak(String s, List<String> wordDict) {
		Set<String> wordDictSet=new HashSet(wordDict);
		Queue<Integer> queue = new LinkedList<>();
		int[] visited = new int[s.length()];
		queue.add(0);
		while (!queue.isEmpty()) {
			int start = queue.remove();
			if (visited[start] == 0) {
				for (int end = start + 1; end <= s.length(); end++) {
					if (wordDictSet.contains(s.substring(start, end))) {
						queue.add(end);
						if (end == s.length()) {
							return true;
						}
					}
				}
				visited[start] = 1;
			}
		}
		return false;
	}
}

