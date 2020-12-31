package com.data.structures.problems;

/**
 * https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class NumberofDiceRollsWithTargetSum {

    /**
     * @intuition
     * 		dice 1 a 6
     * 		target 11
     * 		x equals to the desired roll we need for the current dice to achieve a target value.
     * 		how many ways?
     * 		11 - 6 = x => 5 the current dice needs to roll 5, the previous roll needs to be 6
     * 		11 - 5 = x => 6 the current dice needs to roll 6, the previoues roll needs to be 5
     * 		so the number of ways of getting 11 is equals to the number of ways of getting 5 plus 6. 
     * 		the base case of getting 5 and 6 is 1. because the 1 dice goes from 1 to 6. 
     * 		And we build from there.
     * 
     * 		So to have the answer for the target value T, we need to sum the target value T-1, T-2, T-3 for all dices.
     * 		And we have has a base case the first dice.
     * 		this is beautiful.
     * 
     * @optimization	
     * 		Is possible to cut the iteration, but I have not done it.
     * 
     * @score
     * 		Runtime: 31 ms, faster than 53.87% of Java online submissions for Number of Dice Rolls With Target Sum.
     * 		Memory Usage: 38.2 MB, less than 58.71% of Java online submissions for Number of Dice Rolls With Target Sum.
     * 
     * @fail 
     * 		1) because of modulo, wrong calculus 
     * 		2) failed because of limits in foreach
     * 
     **/
    public int numRollsToTarget(int d, int f, int target) {
        
        int mod = 1_000_000_007;
        int [][] dp = new int[d][target + 1];
        
        //set the base case
        for (int i = 1; i <= Math.min(f, target); i++)
            dp[0][i] = 1;
        
        //System.out.println("test");
        //iterate dices, for each dice
        for(int dice = 1; dice < dp.length; dice++)
        {
            //iterate targets
            //for each target
            for(int t = 1; t < dp[dice].length; t++)
            {
                //iterate faces
                //for each face a dice can have
                for (int face = 1; face <= f; face++)
                {
                    //if we have a way give the number of faces of achieving the target current value, then we increase the possibilities
                    // like number os ways of getting to 3 with one dice is 1. with two dices it is 1+2, 2 + 1, it is two.
                    // what we are trying here is to test if the second value for the second roll is possible
                    // like we can roll values from 1 to 6. so if the target is 7, we have to test the number of ways of getting to 7 with two dices which is the number of ways of getting to 7 - 1, 7 - 2, 7 - 3, 7 - 6. and it is like this for any case.
                    
                    if (t - face > 0)
                    {
                        dp[dice][t] =  (dp[dice][t] + dp[dice - 1][t - face]) % mod;
                    }
                }
            }
        }
        
        return dp[d - 1][target];
    }
}
