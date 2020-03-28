package com.data.structures.problems;

/**
 * EASY
 * https://leetcode.com/problems/reverse-integer/
 * Was tough, I needed to learn how binary operations work, &, |, ^, shifts 
 * @author Nelson Costa
 *
 */
public class ReverseBits {

	public static void main(String[] args) {
		ReverseBits r = new ReverseBits();
		int input = 43261596;
		System.out.println("processing:  " + r.reverseBits(input));
		System.out.println("expected for " + 43261596 + " is " + 964176192);
	}
	
	/**
	 * Runtime: 1 ms, faster than 99.57% of Java online submissions for Reverse Bits.
Memory Usage: 38.5 MB, less than 7.32% of Java online submissions for Reverse Bits.

	 * @param input
	 * @return
	 */
    // you need treat n as an unsigned value
	//Errei no resultado
	//estava bem mas esquecime de que isto é complemento de 2 portanto o bit tem de ter 32 bits
    //isto afeta o caso onde estamos a falar de java
	// falhei porque o objetivo era rodar todo o numero e não apenas a parte mais significativa.
	//Algo muito bom para problemas em binário é utilizar tabelas
	public int reverseBits(int input) {
    	//System.out.println(Integer.toBinaryString(input));
    	if (input == 0)
    		return 0;
    	
        int result = 0;
        int mask = 1;
        int count = 0;
        
        /*
        //Most Significative Bit solution
        while (input > 0) {
        	result = (result << 1) | (input & mask);
        	//System.out.println(Integer.toBinaryString(result));

        	input = input >> 1;
        	count++;
        }
        
        //all 31 bits
        while (32 - count > 0)
        {
        	result <<= 1;
        	count ++;
        }
        */
        
        while ( count < 32)
        {
        	result = (result << 1) | (input & mask);
        	input = input >> 1;
        	count++;
        }
        
        
        return result;
    }

}
