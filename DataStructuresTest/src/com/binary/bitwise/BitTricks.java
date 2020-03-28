package com.binary.bitwise;

public class BitTricks {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num = 29;
		int nthBit = 3;
		System.out.println("num: " + num);
		System.out.println("nthBit: " + nthBit);
		System.out.println("clearence after " + nthBit + " bits: " + clearBitsAfterX(num, nthBit));
		System.out.println("keep bits after " + nthBit + ": " + keepBitsAfter(num, nthBit));
	}

	public static int clearBitsAfterX(int number, int nthBit)
	{
		/*
		 * 
		 * 
		 nthBit = 3
		 number = 
		 Creating the Mask
		 
		 	 -Negation
			 0 0 0 0 0 
			 1 1 1 1 1 
			 
			 -Shift left nthBit Times
			 1 1 1 1 0 
			 1 1 1 0 0
			 1 1 0 0 0
			 
			 -Negation
			 0 0 1 1 1 [Done]
		 
		  1 1 1 0 1
		& 0 0 1 1 1 mask (~((~0)<< nthBit)& number)
		-----------
		  0 0 1 0 1
		  
		
		int mask = 0;
		
		while (nthBit > 0)
		{
			mask = (mask << 1) + 1;
			nthBit -- ;
		}
		
		return number & mask;
		*/
		
		return ~( (~0) << nthBit) & number; //still looks expensive
	}
	
	public static int keepBitsAfter(int number, int nthBit) {
		/*
		 * 
		nth = 2
		
		   1 0 1 1 0
		&  1 1 1 0 0 (negação de 0, shifted left 2x)
		____________				   
		   1 0 1 0 0
		
		
		int mask = ~0;
		
		while (nthBit > 0)
		{
			mask = mask << 1;
			nthBit--;
		}
				
		return mask & number;
		*/
		//ou
		return ((~0) << nthBit) & number; //Still looks expensive
	}
}
