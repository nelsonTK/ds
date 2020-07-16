package com.data.structures.problems;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/gas-station/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class GasStation extends LeetCodeExercise{

	static GasStation g = new GasStation();
	public static void main(String[] args) {
		int [] gas = stringToArray("[1,2,3,4,5]");
		int [] cost = stringToArray("[3,4,5,1,2]");
		
		gas = stringToArray("[2,3,4]");
		cost = stringToArray("[3,4,3]");
		
		System.out.println(g.canCompleteCircuit(gas, cost));
	}
	
	/*********************************
	 * SOLUTION 1
	 ********************************/	
	/**
	 * @intuition
	 * 		Nothing fancy was applied at all
			Runtime: 25 ms, faster than 21.35% of Java online submissions for Gas Station.
			Memory Usage: 39.5 MB, less than 5.88% of Java online submissions for Gas Station.
	 * 
	 * @fail
	 * 		1) forgot to prevent the infinite loop
	 * 
	 * @optimization 
	 * would be to prevent processing nodes that start negative
	 * 
	 * @time	O(N^2)
	 * @space	O(1)
	 * @bcr		O(N^2)
	 * 
	 * @param gas
	 * @param cost
	 * @return
	 */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        
    	
    	for (int i = 0; i < cost.length; i++) {
			
    		if(run(i, gas, cost) >= 0)
    		{
    			return i;
    		}
		}
    	
    	return -1;
    }

	private int run(int startStation, int[] gas, int[] cost) {
		
		int i = startStation, currentGas = 0, count = 0;
		
		while (currentGas >= 0 && count < cost.length)
		{
			currentGas += gas[i];
			currentGas -= cost[i];
			i = i + 1 >= gas.length ? 0 : i + 1;
			count++;
		}
		
		return currentGas;
	}
}

/*********************
* OTHERS SOLUTIONS
*********************/
/**
 * Very cleaver solution
 * 
 * @intuition
 * 		We do only one traversal and we keep track of the total gas consumed
 * 		If we if we get to the end of the trip with petrol ou with non negative tank, than the node that started the trip is valid.
 * 
 * 		While I was trying to solve this exercise I touch many of this points but failed ultimately because I was lazy to think more
 * 
 * 
 * @time O(N)
 * 
 * 
 * @author Nelson Costa
 *
 */
class GasStationSolution1 {
	  public int canCompleteCircuit(int[] gas, int[] cost) {
	    int n = gas.length;

	    int total_tank = 0;
	    int curr_tank = 0;
	    int starting_station = 0;
	    for (int i = 0; i < n; ++i) {
	      total_tank += gas[i] - cost[i];
	      curr_tank += gas[i] - cost[i];
	      // If one couldn't get here,
	      if (curr_tank < 0) {
	        // Pick up the next station as the starting one.
	        starting_station = i + 1;
	        // Start with an empty tank.
	        curr_tank = 0;
	      }
	    }
	    return total_tank >= 0 ? starting_station : -1;
	  }
	}
