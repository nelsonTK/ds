package com.data.structures.problems;

public class EditDistance {

	public static void main(String[] args) {

	}

	
    /**
     
     	@intuition
     		
	    @score
			Runtime: 5 ms, faster than 66.74% of Java online submissions for Edit Distance.
			Memory Usage: 39.8 MB, less than 23.00% of Java online submissions for Edit Distance.
     
	    @fail
	        1) array out of bounds
	        2) wrong limit for column traversal
    **/
    public int minDistance(String word1, String word2) {
        
        //guards
        
        if ((word1 == null || word1.length() == 0 ) && (word2 == null || word2.length() == 0))
            return 0;
        
        if (word1 == null || word1.length() == 0)
            return word2.length();
        
        if (word2 == null || word2.length() == 0)
            return word1.length();
  
        if (word1.equals(word2))
            return 0;
        
        if (word1.length() > word2.length())
        {
            String tmp = word1;            
            word1 = word2;
            word2 = tmp;
        }
        
        int n = word1.length(); 
        int m = word2.length();
        int [][] dp = new int [n + 1][m + 1];
        
        //fill initial values  
        for (int i = 0; i < dp.length; i++)
        {
            dp[i][0] = i;
        }
        
        for (int i = 0; i < dp[0].length; i++)
        {
            dp[0][i] = i;
        }
        
        //Build dp matrix
        for (int row = 1; row < dp.length; row++ )
        {
            for (int col = 1; col < dp[0].length; col++)
            {
                if (word1.charAt(row - 1) != word2.charAt(col - 1))
                {  
                    dp[row][col] = 1 + Math.min(dp[row-1][col-1], Math.min(dp[row][col-1], dp[row-1][col]));
                }
                else 
                {
                    dp[row][col] = 1 +  Math.min(dp[row-1][col-1] - 1, Math.min(dp[row][col-1], dp[row-1][col]));
                }
            }
        }
        
        return dp[n][m];
    }
}
