package com.data.structures.problems;

import java.util.TreeMap;

/**
 * https://leetcode.com/problems/random-pick-with-weight/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class RandomPickWithWeight {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] w = {1,1};
		RandomPickWithWeight r = new RandomPickWithWeight(w);
		for (int i = 0; i < 10 ; i ++)
			System.out.println(r.pickIndex());
	}

	 /*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * @intuition
	 * 		The intuition in here was to relate each weight with a range of numbers that is equivalent to its weight.
	 * 		Then when we want to pick a number we generate a random numberbetween the max value in the treeNode.
	 * 		and 0. we floor the key which is of type Limit/Interval and the return key is the curresponding weight
	 * 
	 * 		E.g. [1,1] would map for instance to numbers: 1 - 50, and 51 - 100. 
	 * 		then we generate a number between 1 and 100 and depending on where the number falls we pick that index.
	 * 
	 * @score
	 *		Runtime: 36 ms, faster than 42.20% of Java online submissions for Random Pick with Weight.
	 *		Memory Usage: 46.1 MB, less than 34.16% of Java online submissions for Random Pick with Weight.
	 * 
	 * @fail
	 * 		1) I was using floorkey and Longs as key, which cause me to skip keys, and it was not mapping to the current interval of a number
	 * 		2) I though it was possible to geneate longs with NextLong and it was not possible, additional math needed to be done
	 * 		
	 * 
	 * @time
	 * 		O(NLogN)
	 * 	
	 * @space
	 * 		O(1)
	 */
	int bi = 1_000_000_000;
	TreeMap<Limit, Integer> weightMap;

	public RandomPickWithWeight(int[] w) {
		weightMap = new TreeMap<>((a, b) -> Long.compare(a.start, b.start));

		int sum = 0;

		//get sum
		for (int i : w)
			sum += i;

		//calculate weight
		long prevRange = 0;
		long curRange = 0;
		for (int i = 0; i < w.length; i++)
		{
			curRange = ( (long)(((double) w[i]/sum) * bi)) + prevRange;
			weightMap.put(new Limit(prevRange + 1, curRange), i);
			prevRange = curRange;
		}
	}

	public int pickIndex() {
		Limit limits = weightMap.lastKey();
		long random = 0 + (long) (Math.random() * (limits.end - 0));
		Limit findLimit = new Limit(random, random+1);
		Limit Key = weightMap.floorKey(findLimit);

		return Key == null? weightMap.get(weightMap.firstKey()) : weightMap.get(Key);
	}

	class Limit{
		long start;
		long end;

		Limit(long s, long e)
		{
			start = s;
			end = e;
		}
	}
}


/*********************
* OTHERS SOLUTIONS
*********************/


/**
 * Very Cleaver solution instead of adding a range they just added the values comulatively
 * 
 * then when generationg a random number, they generate in range from 1 to max comulative sum.
 * 
 * and search the array to see when the current comulative sum is smaller or equal to the random generated number. if it is then we return the index.
 * 
 * brilliant
 * 
 * This is the binary search approach.
 * 
 * @author Nelson Costa
 *
 */
class RandomPickWithWeightSolution2 {
    private int[] prefixSums;
    private int totalSum;

    public RandomPickWithWeightSolution2(int[] w) {
        this.prefixSums = new int[w.length];

        int prefixSum = 0;
        for (int i = 0; i < w.length; ++i) {
            prefixSum += w[i];
            this.prefixSums[i] = prefixSum;
        }
        this.totalSum = prefixSum;
    }

    public int pickIndex() {
        double target = this.totalSum * Math.random();

        // run a binary search to find the target zone
        int low = 0, high = this.prefixSums.length;
        while (low < high) {
            // better to avoid the overflow
            int mid = low + (high - low) / 2;
            if (target > this.prefixSums[mid])
                low = mid + 1;
            else
                high = mid;
        }
        return low;
    }
}


/**
 * Very Cleaver solution instead of adding a range they just added the values comulatively
 * 
 * then when generationg a random number, they generate in range from 1 to max comulative sum.
 * 
 * and search the array to see when the current comulative sum is smaller or equal to the random generated number. if it is then we return the index.
 * 
 * brilliant
 * 
 * This is the linear approach.
 * 
 * @author Nelson Costa
 *
 */
class RandomPickWithWeightSolution1 {
    private int[] prefixSums;
    private int totalSum;

    public RandomPickWithWeightSolution1(int[] w) {
        this.prefixSums = new int[w.length];

        int prefixSum = 0;
        for (int i = 0; i < w.length; ++i) {
            prefixSum += w[i];
            this.prefixSums[i] = prefixSum;
        }
        this.totalSum = prefixSum;
    }

    public int pickIndex() {
        double target = this.totalSum * Math.random();
        int i = 0;
        // run a linear search to find the target zone
        for (; i < this.prefixSums.length; ++i) {
            if (target < this.prefixSums[i])
                return i;
        }
        // to have a return statement, though this should never happen.
        return i - 1;
  }
}

