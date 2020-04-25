package com.data.structures.problems;

/**
 * https://leetcode.com/problems/sqrtx/
 * EASY
 * @author Nelson Costa
 *
 */
public class Sqrtx {

	public static void main(String[] args) {

		int x = 2100000000;
		Sqrtx s = new Sqrtx();
		System.out.println("number: " + x);
		System.out.println(s.mySqrt(x));
	}

	/**
	 * @intuition
	 * 		I have no idea of how to calculate square root
	 * 		Wait... I can go Binary search
	 * 		
	 * @time  ?
	 * @space ?
	 * @bcr   ?
	 * 
	 * @param x
	 * @return
	 */
	public int mySqrt0(int x) {
		return (int) Math.sqrt(x);
	}

	/**
	 * @intuition
	 * 		Instead of trying out all numbers linearly we search with binary search
	 * 		Straitforward approach nothing fancy
	 * 
	 * @comments reducing my solutions
	 * 
	 * @score
	 * 		Remove ceil and the results improved
	 * 			Runtime: 1 ms, faster than 100.00% of Java online submissions for Sqrt(x).
				Memory Usage: 36.6 MB, less than 5.00% of Java online submissions for Sqrt(x).
				
			first run with ceil	
	 * 			Runtime: 2 ms, faster than 21.27% of Java online submissions for Sqrt(x).
				Memory Usage: 36.7 MB, less than 5.00% of Java online submissions for Sqrt(x).
	 * 
	 * @fail
	 * 		1) I returned number left instead of right. I had things right in the paper...
	 * 
	 * @alternative linear search 
	 * 
	 * @time  O(log n)
	 * @space O(1)
	 * @bcr   O(1) | if there is some kind of formula [there is!]
	 * 
	 * @param x
	 * @return
	 */
	public int mySqrt(int x) {

		if (x == 0 || x == 1)
			return x;

		int low = 1;
		int high = x/2;//(int) Math.ceil(x/2); (worst performance)
		int mid;

		while (low <= high)
		{
			mid = low + (high - low)/2;

			if (pow2(mid) > x)
				high = mid - 1;
			else if (pow2(mid) < x)
			{
				low = mid + 1;
			}else {
				return mid;
			}
		}

		return high;
	}

	private long pow2(int x) {

		return (long) x * x;
	}
}

/**
 * Using math to solve this problem
 * 
 * raiz quadrada de x =  e^(1/2 * log x)
 * @author Nelson Costa
 *
 */
class SqrtxSolution {
	  public int mySqrt(int x) {
	    if (x < 2) return x;

	    int left = (int)Math.pow(Math.E, 0.5 * Math.log(x));
	    int right = left + 1;
	    return (long)right * right > x ? left : right;
	  }
	}
