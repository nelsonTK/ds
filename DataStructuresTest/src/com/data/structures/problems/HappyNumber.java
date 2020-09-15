package com.data.structures.problems;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/happy-number/
 * EASY
 * @author Nelson Costa
 *
 */
public class HappyNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	 /*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * @intuition
	 * 		put duplicates in the set, as soon as we find a duplicate we return false;
	 * 		else we return the condition num == 1
	 * 
	 * @score
	 * 		Runtime: 1 ms, faster than 93.43% of Java online submissions for Happy Number.
	 *		Memory Usage: 36.5 MB, less than 79.71% of Java online submissions for Happy Number.
	 * @param num
	 * @return
	 */
    public boolean isHappy(int num) {
        //positive
        Set<Integer> set = new HashSet<>();
        
        while(num > 0 && num != 1)
        {
            num = digitCalc(num);
            if(!set.add(num))
                return false;
        }
        
        
        return num == 1;
    }
    
    private int digitCalc(int n)
    {
        int total = 0;
        int digit = 0;
        while (n > 0)
        {
            digit = (n % 10);
            total += digit * digit;
            n /=10;
        }
        
        return total;
    }
}



/*********************
* OTHERS SOLUTIONS
*********************/
/**
 * Analogous to my solution
 * @author Nelson Costa
 *
 */
class HappyNumberSolution1 {

    private int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            totalSum += d * d;
        }
        return totalSum;
    }

    public boolean isHappy(int n) {
        Set<Integer> seen = new HashSet<>();
        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            n = getNext(n);
        }
        return n == 1;
    }
}

/**
 * Solution using cycle detection with fast and slow pointers
 * @author Nelson Costa
 *
 */
class HappyNumberSolution2 {

    public int getNext(int n) {
       int totalSum = 0;
       while (n > 0) {
           int d = n % 10;
           n = n / 10;
           totalSum += d * d;
       }
       return totalSum;
   }

   public boolean isHappy(int n) {
       int slowRunner = n;
       int fastRunner = getNext(n);
       while (fastRunner != 1 && slowRunner != fastRunner) {
           slowRunner = getNext(slowRunner);
           fastRunner = getNext(getNext(fastRunner));
       }
       return fastRunner == 1;
   }
}
