package com.data.structures.problems;

public class PowerOfTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 243;
		PowerOfTree t = new PowerOfTree();
		System.out.println(t.isPowerOfThree2(n));
	}

	/**
	 * Problem applying the formula of logarithms with custom base
	 * 
			Runtime: 11 ms, faster than 80.95% of Java online submissions for Power of Three.
			Memory Usage: 40.8 MB, less than 6.25% of Java online submissions for Power of Three.
	 * 
	 * @time O(1)
	 * @space O(1)
	 * @param num
	 * @return
	 */
	public boolean isPowerOfThree2(int num) {
		
		return num > 0 && (Math.log10(num) / Math.log10(3)) % 1 == 0;
	}
	
	/**
	 * nothing fancy
	 * not accepted (time exceeded)
	 * 
	 * @time log3 n
	 * @space O(1)
	 * @param n
	 * @return
	 */
    public boolean isPowerOfThree1(int n) {
        
    	while ( n % 3 == 0)
    	{
    		n /= 3;
    	}
    	return n == 1;
    }
}
