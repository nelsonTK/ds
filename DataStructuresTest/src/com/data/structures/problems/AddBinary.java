package com.data.structures.problems;

/**
 * https://leetcode.com/problems/add-binary/
 * EASY
 * @author Nelson Costa
 *
 */
public class AddBinary {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
    /**
     * 	@intuition
     * 		Just adding the bits and having a carry
     * 	
     * 	@score
     * 		Runtime: 4 ms, faster than 37.30% of Java online submissions for Add Binary.
     * 		Memory Usage: 39.5 MB, less than 58.09% of Java online submissions for Add Binary.
     * 
     * 	@fail 
     * 		1) forgot to subtrack the char 
     * 		2) the limit was for the smaller string instead of the largest 
     * 		3) started the traversal oposite 
     * 
     * 	@time
     * 		O(max(M, N))
     * 
     *  @space
     *  	O(max(M,N))
     * 
    **/
    public String addBinary(String a, String b) {
        
        
        if (a.length() < b.length())
            return addBinary(b, a);
        
        
        StringBuilder result = new StringBuilder("");
        int carry = 0;
        int ai, bi;
        int i = 0;
        //O(Max(N,M))
        for (i = 0; i < a.length(); i++)
        {
            ai = a.charAt(a.length() - 1 - i) - '0';
            bi = b.length() - 1 - i >= 0 ? b.charAt(b.length() - 1 - i) - '0': 0;
            carry =  add(ai, bi, carry, result);
        }
        
        if (carry > 0)
        {
            result.append("1");
        }
        
        
        return result.reverse().toString();
    }
    
    //returns carry O(1) O(Max(N,M))
    private int add(int a, int b, int c, StringBuilder sb)
    {
        if (a + b + c> 1)
        {
            sb.append( (a ^b ^ c ) + "");
            return 1;
        }
        else
        {
            sb.append((a + b + c) + "");
            return 0;
        }
            
    }

}
