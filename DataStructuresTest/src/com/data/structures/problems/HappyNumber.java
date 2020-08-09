package com.data.structures.problems;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
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
            //System.out.println(num);
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
