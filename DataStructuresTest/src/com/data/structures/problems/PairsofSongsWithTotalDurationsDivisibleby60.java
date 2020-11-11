package com.data.structures.problems;

public class PairsofSongsWithTotalDurationsDivisibleby60 {

	 /*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * TIME LIMIT EXCEEED
	 * @param time
	 * @return
	 */
    public int numPairsDivisibleBy60old(int[] time) {
        
        int result = 0;
        for (int i = 0; i < time.length; i++)
        {
            for (int j = i+1; j < time.length; j++)
            {
                if ((time[i] + time[j]) % 60 == 0)
                {
                    result++;
                }
            }
        }
        
        return result;
    }    
    

	 /*********************************
	 * SOLUTION 2
	 ********************************/
    /**
     * @intuition
     * 		The intuition is that you can do the module of all the numbers and save it in a arraythat goes from 0 to 59
     * 		and then you can do the combinations of that number with the numbers that have the complementary module. the special cases are zero and 30.
     * 		Because zero needs no complement so we just apply the combination.
     * 		and the complementar number of 30 is also 30 so it is its own complement
     * 		to validate I dont process the same combination twice I use a array and I use math to create a unique key for each combination, but it isn't needed. 
     * 		I could have just reset the values I think
     * 
     * @comments
     * 		This solution could be greatly improved
     * 
     * @score
     * 		Runtime: 2 ms, faster than 99.70% of Java online submissions for Pairs of Songs With Total Durations Divisible by 60.
     * 		Memory Usage: 44.4 MB, less than 5.78% of Java online submissions for Pairs of Songs With Total Durations Divisible by 60.
     * 
     * @time
     * 		O(N)
     * 
     * @space
     * 		O(1)
     * 
    **/
    public int numPairsDivisibleBy60(int[] time) {
        if (time == null)
            return 0;
        
        int [] modulo = new int[60];
        int result = 0;
        //O(N)
        //save modulo
        for (int i = 0; i < time.length; i++)
        {
            modulo[time[i] % 60]++;            
        }
        
        //O(1)
        //handle the already divisible elements
        result = modulo[0]*(modulo[0]-1)/2;
        
        
        boolean [] hashset = new boolean[3542]; 
        int max, min;
        for (int i = 1; i < modulo.length; i++)
        {
            int complement = 60 - i;
            
            max =  Math.max(i, complement);
            min =  Math.min(i, complement);
            if (!hashset[60*max+min])
            {
                if(i != 30)
                	//else we multiply the i * complement (only once
                    result += modulo[i] * modulo[complement];
                else
                	//30 we do combinations of itself
                    result += modulo[i]*(modulo[i]-1)/2;
                
                //update hashset
                hashset[60*max+min] = true;
            }
        }
        
        return result;
    }
}


/*********************
* OTHERS SOLUTIONS
*********************/
/**
 * Beautiful and easy to understand solution.
 * It solves a problem I was not able too, which is not to use a "hash" to avoid counting the same combination twice
 * It is possible because the result is made on the fly
 * also it does not need to use combinations
 * 
 * @author Nelson Costa
 *
 */
class PairsofSongsWithTotalDurationsDivisibleby60UnofficialSolution {
    public int numPairsDivisibleBy60(int[] time) {
        int[] map = new int[60];
        int result = 0;
        
        for(int curr: time){
            curr = curr % 60;
            result += curr == 0 ? map[0] : map[60 - curr];
            // or 
            // result += map[(60 - curr) % 60];
            map[curr]++;
        }
        return result;
    }
}