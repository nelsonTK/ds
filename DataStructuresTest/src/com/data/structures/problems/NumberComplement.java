package com.data.structures.problems;

/**
 * https://leetcode.com/problems/number-complement/
 * EASY
 * I've no idea if this answer is correct I believe it's not despite I've passed 
 */
public class NumberComplement {

	public static void main(String[] args) {
		int num = -17;
		System.out.println(num);
		NumberComplement n = new NumberComplement();
		System.out.println(n.findComplement(num));
		
		NumberComplementSolution s = new NumberComplementSolution();
		System.out.println(s.findComplement(num));
		System.out.println(s.findComplement2Best(num));
		System.out.println(s.findComplement3Best(num));
	}
	
	/*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * Correct implementation with logarithm
	 * @param num
	 * @return
	 */
	public int findComplement0(int num) {
		int exponent = ((int)(Math.log(num)/Math.log(2)) + 1);
		int mask = (1 << exponent) - 1;

		return (num ^ mask);
	}


	/*********************************
	 * SOLUTION 2
	 ********************************/
	/**
	 * [MESSY SOLUTION BECAUSE OF CASTING AND THE LOG FORMULA]
	 * 
	 * 
	 * I would like to have a negative number example to understand if only the number i required
	 * very tricky because of integer limits and negative numbers
	 * 
	 * 
	 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Number Complement.
Memory Usage: 36.2 MB, less than 8.70% of Java online submissions for Number Complement.
	 * 
	 * @time  O(1)
	 * @space O(1)
	 * @param num
	 * @return
	 */
	public int findComplement(int num) {
		if (num == Integer.MAX_VALUE) //2147483647
			return 0;

		if (num == 0)
			return 1;

		int exponent = (int) Math.ceil(Math.log(num + 1)/Math.log(2));
		long mask = ((long) Math.pow(2, exponent)) - 1;

		return (int)(num ^ mask);
	}
}

/*********************************
 * OTHERS SOLUTIONS
 ********************************/
/**
 * most logical solution, but very poor performance
 * and has loops
 * 1011
 * we fill the numbers by using shifts and the power to put the next number in front of numbers previously placed.
 * 
 * 2^2 2^1 2^0
 * 			1  1º
 * 		1	1  2º
 * 0	1	1  3º
 * 2	1	0  <- power
 * 0001
 * 0011
 * 0011
 * 1011
 * 
 * @author Nelson Costa
 *
 */
class NumberComplementSolution {



	/**
	 * 		1000101
	 * 		0100010 >> 1
	 * 	(|)	1100101 = resultado
	 * 
	 * 		1100101
	 * 		0010010 >> 2
	 * 	(|)	1111000 =
	 * 
	 * 		1111000
	 * 		0000111 >> 4
	 * 	(|)	1111111 = resultado final
	 * 
	 * Very easy to understand by doing it by hand
	 * @param num
	 * @return
	 */
	public int findComplement(int num) {
		// bitmask has the same length as num and contains only ones 1...1
		int bitmask = num;
		bitmask |= (bitmask >> 1);
		bitmask |= (bitmask >> 2);
		bitmask |= (bitmask >> 4);
		bitmask |= (bitmask >> 8);
		bitmask |= (bitmask >> 16);
		// flip all bits 
		return bitmask ^ num;
	}

	public int findComplement2Best(int num) {
		return (Integer.highestOneBit(num) << 1) - num - 1;
	}

	public int findComplement3Best(int num) {
		int exponent = ((int)(Math.log(num)/Math.log(2)) + 1);
		int mask = (1 << exponent) - 1;

		return (num ^ mask);
	}

	/**
	 * 
	 * @param num
	 * @return
	 */
	public int findComplement4Best(int num) {
		int result = 0;
		int power = 1;
		while (num > 0 ) {
			result += (num % 2 ^1) * power; 
			power <<= 1;
			num >>= 1;
		}

		return result;
	}
}