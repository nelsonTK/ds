package com.data.structures.problems;

public class EditDistance {

	static EditDistance e = new EditDistance();
	public static void main(String[] args) {
		String word1 = "horse", word2 = "ros";
		e.minDistance(word1, word2);
	}

	
    /**
     
     	@intuition
     		This is a DP problem with matrix, 
     		where we pre fill the dp array with the distance of each element from "" string to full word
			than the current edit distance is the minimal of the x-1, y-1| x,y-1|x-1,y| plus one.
			
			when a character is equals to the we are comparing we subtract 1 to the previous element (x-1, y-1)
			this is to maintain the minimal distance equals to the previous one. because we then sum one.
			
			dp [i] = 1 + min (dp[x - 1, y - 1],dp[x, y - 1],dp[x - 1, y]]
			or 
			dp [i] = 1 + min (dp[x - 1, y - 1] - 1,dp[x, y - 1],dp[x - 1, y]] (if characters are equals)
     		
     		
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
        
        //fill initial values  (y axis)
        for (int i = 0; i < dp.length; i++)
        {
            dp[i][0] = i;
        }
        
        //fill initial values (x axis)
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


class EditDistanceSolution1 {
	  public int minDistance(String word1, String word2) {
	    int n = word1.length();
	    int m = word2.length();

	    // if one of the strings is empty
	    if (n * m == 0)
	      return n + m;

	    // array to store the convertion history
	    int [][] d = new int[n + 1][m + 1];

	    // init boundaries
	    for (int i = 0; i < n + 1; i++) {
	      d[i][0] = i;
	    }
	    for (int j = 0; j < m + 1; j++) {
	      d[0][j] = j;
	    }

	    // DP compute 
	    for (int i = 1; i < n + 1; i++) {
	      for (int j = 1; j < m + 1; j++) {
	        int left = d[i - 1][j] + 1;
	        int down = d[i][j - 1] + 1;
	        int left_down = d[i - 1][j - 1];
	        if (word1.charAt(i - 1) != word2.charAt(j - 1))
	          left_down += 1;
	        d[i][j] = Math.min(left, Math.min(down, left_down));

	      }
	    }
	    return d[n][m];
	  }
	}
