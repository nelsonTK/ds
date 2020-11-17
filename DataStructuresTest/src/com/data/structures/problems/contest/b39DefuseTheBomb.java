package com.data.structures.problems.contest;

/**
 * Easy
 * @author Nelson Costa
 *
 */
public class b39DefuseTheBomb {
	class Solution {
	    public int[] decrypt(int[] code, int k) {
	        if (k == 0)
	            return new int[code.length];
	        
	        int direction = k > 0? 1 : -1;
	        int n = code.length;
	        int [] ans = new int[code.length];
	        for (int i = 0; i < code.length; i++)
	        {
	            int iterations = k > 0 ? k : -k;
	            int sumElement = 0;
	            int indexElement = i;
	            while (iterations > 0 )
	            {
	            	//could be done with module of code size, I would save this work
	                if (indexElement + direction >= n)
	                {
	                    indexElement = 0;
	                }
	                else if (indexElement + direction < 0)
	                {
	                    indexElement = n - 1;
	                }
	                else
	                {
	                    indexElement +=direction;
	                }
	                
	                sumElement += code[indexElement];
	                iterations--;
	            }
	            ans[i] = sumElement;
	            
	        }
	        return ans;
	    }
	}
}
