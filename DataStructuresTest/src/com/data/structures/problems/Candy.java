package com.data.structures.problems;

import java.util.Arrays;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/candy/
 * HARD
 * @author Nelson Costa
 *
 */
public class Candy extends LeetCodeExercise{

	static Candy c = new Candy();
	static CandySolution1 c2= new CandySolution1();
	public static void main(String[] args) {
		int [] rates = stringToArray("[3,2,1]");
		System.out.println(c.candy(rates));
		System.out.println(c2.candy(rates));

	}
	
	/*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * @intuition
	 * 		I just count the the elements
	 * 		if previous smaller than current is equals to previous + 1.
	 * 		if next is smaller and current candies is 1 than we need to increase current candy and eventually elements back.
	 * 	
	 * @score
	 *		Runtime: 441 ms, faster than 6.17% of Java online submissions for Candy.
	 *		Memory Usage: 39.9 MB, less than 90.02% of Java online submissions for Candy.
	 *
	 *			
	 * @fail
	 *       1) wrong initialization value. -1 instead of 1;
	 *       2) I was not passing the candy
	 *       3) sign missing it was <= instead of just <
	 *       4) missing situation on items update, which is in a slop and we dont need to update backwards
	 *
	 * @time O(N^2)
	 * @space O(N)	
	 * 
	 * @param rates
	 * @return
	 */
	public int candy(int[] rates) {
		if (rates == null || rates.length == 0)
			return 0;

		if (rates.length == 1)
			return 1;
		
		int [] candy = new int[rates.length];
		Arrays.fill(candy, 1);

		for (int i = 0; i < rates.length; i++)
		{
			if (i - 1 >= 0 && rates[i] > rates[i - 1])
			{
				candy[i] = candy[i - 1] + 1;
			}

			if (i + 1 < rates.length && rates [i] > rates[i + 1] && candy[i] == 1)
			{
				increasePrevious(rates, candy, i);
			}
		}

		int total = 0;

		for (int i : candy)
			total += i;

		return total;
	}


	private void increasePrevious(int[] rates, int [] candy, int curIndex){

		while (curIndex - 1 >= 0 && rates[curIndex - 1] > rates[curIndex] 
				&& candy[curIndex - 1] - candy[curIndex] == 1)
		{
			candy[curIndex]++;
			curIndex = curIndex - 1;
		}

		candy[curIndex]++;
	}
}


/*********************
* OTHERS SOLUTIONS
*********************/

	/*********************
	 * UNOFFICIAL SOLUTIONS
	 *********************/

/**
 * update as you go throught the array, on descending and on ascending
 * equivalent to the best official solution
 * 
 * @intuition
 * 		increment is always at most by one
 * 		whether it is for climbing or going down
 * 
 * @author Nelson Costa
 *
 */
class CandySolutionUnofficial1 {
    public int candy(int[] ratings) {
        if (ratings.length == 0) return 0;
        int ret = 1;
        int up = 0, down = 0, peak = 0;
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i - 1] < ratings[i]) {
                peak = ++up;
                down = 0;
                ret += 1 + up;
            } else if (ratings[i - 1] == ratings[i])  {
                peak = up = down = 0;
                ret += 1;
            } else {
                up = 0;
                down++;
                ret += 1 + down + (peak >= down ? -1 : 0);
            }
        }

        return ret;
    }
}

/*********************
 * OFFICIAL SOLUTIONS
 *********************/

/**
 * Same philosophy than solution 2 but with only one array
 * @author Nelson Costa
 *
 */
class CandySolution3 {
    public int candy(int[] ratings) {
        int[] candies = new int[ratings.length];
        Arrays.fill(candies, 1);
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }
        int sum = candies[ratings.length - 1];
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
            sum += candies[i];
        }
        return sum;
    }
}


/**
 * Solution base in two arrays.
 * 	first you compare current with element from left
 * 	then you compare current with element from right
 * 	the max from both arrays is the correct result for that index
 * 	the answer is the sum.
 * 
 * @author Nelson Costa
 *
 */
class CandySolution2 {
    public int candy(int[] ratings) {
        int sum = 0;
        int[] left2right = new int[ratings.length];
        int[] right2left = new int[ratings.length];
        Arrays.fill(left2right, 1);
        Arrays.fill(right2left, 1);
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left2right[i] = left2right[i - 1] + 1;
            }
        }
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                right2left[i] = right2left[i + 1] + 1;
            }
        }
        for (int i = 0; i < ratings.length; i++) {
            sum += Math.max(left2right[i], right2left[i]);
        }
        return sum;
    }
}


/**
 * @intuition
 * 		Brute Force Solution, worst than mine.
 * 
 * 		what you do in here is to run a while loop untill all the invariantes are fullfilled.
 * 
 * 		the disadvantage to my version is that I dont go though all the array when I need to fix somehting. I go from the current to back and continue from current
 * 
 * @timetook
 * 		1206 ms 
 * 
 * @time n^2
 * 
 * @author Nelson Costa
 *
 */
class CandySolution1 {
    public int candy(int[] ratings) {
        int[] candies = new int[ratings.length];
        Arrays.fill(candies, 1);
        boolean flag = true;
        int sum = 0;
        while (flag) {
            flag = false;
            for (int i = 0; i < ratings.length; i++) {
                if (i != ratings.length - 1 && ratings[i] > ratings[i + 1] && candies[i] <= candies[i + 1]) {
                    candies[i] = candies[i + 1] + 1;
                    flag = true;
                }
                if (i > 0 && ratings[i] > ratings[i - 1] && candies[i] <= candies[i - 1]) {
                    candies[i] = candies[i - 1] + 1;
                    flag = true;
                }
            }
        }
        for (int candy : candies) {
            sum += candy;
        }
        return sum;
    }
}

