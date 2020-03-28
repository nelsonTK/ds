package com.binary.bitwise;

public class BitOperations {

	public static void main(String[] args) {
		int x = 3;
		int y = 3;
		System.out.println(Add(x, y));
		System.out.println(myAdd(x,y));
		System.out.println(addRecursively(x,y));
	}
	

	/**
	 * add operation can be break down in 
	 * 		calculating the carry (& AND <<)
	 * 		adding the carry to the sum of the two binary numbers (^)
	 * 		if this sum carry IS ZERO then a ^ b is the final result
	 * 	
	 * @param a
	 * @param b
	 * @return
	 */
	public static int myAdd(int a, int b) {
		
		int carry = -1;
		
		while (carry != 0) {
			
			carry = (a & b) << 1; //identifying the places that need carries, and shift left			
			
			b = (a ^ b); //result
			
			a = carry; // in order to sum carry with result
		}
		
		return b;
	}

	public static int addRecursively(int a, int b) {
		
		if (b == 0)
			return a;
		
		// a) will be the sum and b) the carry
		// if b) is 0, or we have no carry return a...
		return addRecursively(a ^ b, (a & b) << 1);
	}
	
	
	/**
	 * 
	 * Solução geek for geeks
	 * @param x
	 * @param y
	 * @return
	 */
	public static int Add(int x, int y) 
	{ 
		// Iterate till there is no carry 
		while (y != 0)  
		{ 
			// carry now contains common 
			// set bits of x and y 
			int carry = x & y; 
  
			// Sum of bits of x and  
			// y where at least one  
			// of the bits is not set 
			x = x ^ y; 
  
			// Carry is shifted by  
			// one so that adding it  
			// to x gives the required sum 
			y = carry << 1; 
		} 
		return x; 
	} 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
