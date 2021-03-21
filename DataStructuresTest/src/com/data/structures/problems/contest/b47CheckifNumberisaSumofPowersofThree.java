package com.data.structures.problems.contest;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/check-if-number-is-a-sum-of-powers-of-three/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class b47CheckifNumberisaSumofPowersofThree {
	public boolean checkPowersOfThree(int n) {



		int limit = (int)( Math.log10(n)/Math.log10(3)) + 1;
		System.out.println( limit);
		int combinations = 1 << limit;

		int total = 0;
		for (int i = 0; i <= combinations; i++)
		{
			total = 0;
			for (int j = 0; j <= limit; j++)
			{
				if ((i & (1 << j)) != 0)
				{
					total += Math.pow(3, j);
				}
			}

			if (total == n)
				return true;
		}

		return false;
	}
}

/**
 * Cool solution too
 * @author Nelson Costa
 *
 */
class b47CheckifNumberisaSumofPowersofThreeUnofficialSolution {
    public boolean checkPowersOfThree(int n) {
        Set<Integer> set = new HashSet<>();
        while(true){
            int a = (int)(Math.log(n)/Math.log(3));
            if(set.contains(a)) return false;
            set.add(a);
            n = n - (int)Math.pow(3,a);
            if(n==0) return true;
        }
    }
}