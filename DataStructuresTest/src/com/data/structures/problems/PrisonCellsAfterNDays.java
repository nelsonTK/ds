package com.data.structures.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/prison-cells-after-n-days/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class PrisonCellsAfterNDays extends LeetCodeExercise{

	static PrisonCellsAfterNDays p = new PrisonCellsAfterNDays();
	static PrisonCellsAfterNDaysSolution1 p2 = new PrisonCellsAfterNDaysSolution1();
	static PrisonCellsAfterNDaysSolution2 p3 = new PrisonCellsAfterNDaysSolution2();
	public static void main(String[] args) {
		int [] cells = stringToArray("[0,1,0,1,1,0,0,1]");
		System.out.println(Arrays.toString(p.prisonAfterNDays(cells, 16)));
		System.out.println(Arrays.toString(p2.prisonAfterNDays(cells, 77)));
		System.out.println(Arrays.toString(p3.prisonAfterNDays(cells, 16)));
	}

	/*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 *  [TIME LIMIT]
	 * 
	 * CALCULATING EVERY DAY
	 * 
	 * @intuition
	 * 		Kinda bruteforce solution, iterate all the possible days
	 * 
	 * @alternatives
	 * 		An Alternative could be to create a graph to detect a cycles and after detecting the cycle, I leave the graph
	 * 		And then I would start from the begining and iterate n times.
	 * 
	 * @time O(N)
	 **/
	public int[] prisonAfterNDays0(int[] cells, int n) {

		//occupied  1
		//vagrant   0
		//two adjacent equal => next occuppied
		//else
		//empty


		//if prev is -1 or next is out of bounts 
		//    set to zero

		int prev, cur;
		for (int i = 0; i < n; i++)
		{
			prev = -1;
			//test some inputsizes (1)
			for (int j = 0; j < cells.length ; j++)
			{
				cur = cells[j];
				// we can ignore the zero being wrongly changed 
				// because we change it after the for loop to 0
				if (j + 1 < cells.length && prev == cells[j + 1]) 
				{
					cells[j] = 1;
				}
				else {
					cells[j] = 0;
				}

				//previous advances
				prev = cur;
			}
		}


		return cells;
	}

	/*********************************
	 * SOLUTION 2
	 ********************************/
	/** 
	 * [TIME LIMIT]
	 * 
	 * CALCULATE UNIQUE DAYS AND ITERATE THOUGHT THEM N TIMES
	 * 
	 * @intuition
	 * 		This still was kinda bruteforce solution.
	 * 		I found the cycle and than iterated n times from the cycle to find the correct day setting.
	 * 		The time complexity didn't changed but I was expecting that not calculating every day would buy me 
	 * 		some precious time but I was wrong
	 * 
	 * @alternatives
	 * 		I believe that finding the answer could be made with a matematical formula 
	 * 			 
	 * @fail   
	 * 		1) the code to find the first hashmap key was wrong
	 *
	 **/
	public int[] prisonAfterNDays1(int[] cells, int n) {

		HashMap<List<Integer>, List<Integer>> graph = new HashMap<>();
		List<Integer> curPrison = new ArrayList<>();
		List<Integer> start;
		List<Integer> next;
		int prev, cur;

		//fill the current prison, the starting point of the search
		for (int i : cells)
		{
			curPrison.add(i);
		}
		start = new ArrayList<>(curPrison);

		//Build the graph
		for (int i = 0; i < n; i++)
		{          

			//check if we have this setting in the graph
			//if we have we don't need to calculate it again
			if (graph.containsKey(curPrison))
			{
				//cycle found
				System.out.println("found");
				break;
			}
			next = new ArrayList<Integer> (curPrison);
			prev = -1; 
			//create next step
			for (int j = 0; j < next.size() ; j++)
			{
				cur = next.get(j);
				// we can ignore the zero being wrongly changed 
				// because we change it after the for loop to 0
				if (j + 1 < next.size() && prev == next.get(j + 1)) 
				{
					next.set(j, 1);
				}
				else 
				{
					next.set(j, 0);
				}

				//previous advances
				prev = cur;
			}

			graph.put(curPrison, next);
			curPrison = next;
		}

		//find the n transformation
		//first element of the graph

		for (int i = 0; i < n; i++)
		{
			if(graph.get(start) != null)
				start = graph.get(start);
		}

		//build the answer
		int [] ans = new int[start.size()];

		for (int i = 0; i < start.size(); i++)
		{
			ans[i] = start.get(i);
		}

		return ans;
	}

	/*********************************
	 * SOLUTION 3
	 ********************************/
	/** 
	 * @intuition
	 * 		New version with matematical formula to calculate number of iterations
	 * 		
	 * 		First I create a graph with the unique days where 
	 * 		
	 * 		each day points to the next one, and has a mapping of its order for direct access and ease calculations
	 * 
	 * 		Then I calculate the number of days that I need to simulate the N days.
	 * 
	 * 		The simulated number of days formula is the following
	 * 
	 *  	F(N) : (B) + ((N - B) %  Math.abs(D - B))
	 *    
	 *  		B: days before cycle starts
	 *  		N: days to simulate
	 *  		D: Distinct prison days
	 *  	
	 *  	so our graph could be something like this: -O
	 *	 	  	- : is the non cycle part
	 *	   		o : is the cycle
	 *  
	 *  	[Note] 
	 *  		Day 1 starts at item 2;
	 *  		and when the cycle start position zero is the begining of the cycle and 1 the day after the cycle day.
	 *  
	 *  	So after having the graph with the distinct days and having the number of days that I need to simulate.
	 *  	I just have to iterate the graph F(N) Times.
	 *  
	 *	@score
	 *		Runtime: 8 ms, faster than 17.54% of Java online submissions for Prison Cells After N Days.
	 * 		Memory Usage: 39.3 MB, less than 79.77% of Java online submissions for Prison Cells After N Days.
	 *		
	 *		Runtime: 5 ms, faster than 37.31% of Java online submissions for Prison Cells After N Days.
			Memory Usage: 39.8 MB, less than 9.25% of Java online submissions for Prison Cells After N Days.
	 *	
	 * @fail	
	 * 		1) The first mathematical formula failed because I assumed that the cycles would be perfect, 
	 * 		always from begining to end, but it can be different, the nodes co go to any of the previous
	 *
	 * @time
	 * 		O()
	 * 
	 * @space
	 *		O()
	 **/
	public int[] prisonAfterNDays(int[] cells, int n) {

		HashMap<List<Integer>, Node> graph = new HashMap<>();
		List<Integer> curPrison = new ArrayList<>();
		List<Integer> start;
		List<Integer> cycleStart = null;

		/************************************************************
		 * fill the current prison, the starting point of the search
		 ************************************************************/
		for (int i : cells)
		{
			curPrison.add(i);
		}
		start = new ArrayList<>(curPrison);


		/************************************************************
		 * build the graph
		 ************************************************************/
		cycleStart = createUniquePrisonDays(n, graph, curPrison);

		/************************************************************
		 * Calculate the number of required iterations
		 ************************************************************/
		int iterations = n;
		if (cycleStart != null)
		{
			int cycleStartDay = graph.get(cycleStart).days;
			//explanation in header
			iterations = cycleStartDay + ((n - cycleStartDay) % Math.abs(cycleStartDay - graph.size())); 
		}

		/************************************************************
		 * find the correct day
		 ************************************************************/
		for (int i = 0; i < iterations; i++)
		{
			if(graph.get(start) != null)
				start = graph.get(start).prison;
		}

		/************************************************************
		 * build the answer
		 ************************************************************/
		int [] ans = new int[start.size()];

		for (int i = 0; i < start.size(); i++)
		{
			ans[i] = start.get(i);
		}

		return ans;
	}

	private List<Integer> createUniquePrisonDays(int n, HashMap<List<Integer>, Node> graph, List<Integer> curPrison) {
		List<Integer> next;
		int prev, cur;

		for (int i = 0; i < n; i++)
		{          

			//check if we have this setting in the graph
			//if we have we don't need to calculate it again
			//cycle found
			if (graph.containsKey(curPrison))
			{
				return curPrison;
			}
			next = new ArrayList<Integer> (curPrison);
			prev = -1; 

			//Calculate next Day
			for (int j = 0; j < next.size() ; j++)
			{
				cur = next.get(j);
				if (j + 1 < next.size() && prev == next.get(j + 1)) 
				{
					next.set(j, 1);
				}
				//first and last element fall in here, no need for special treatment
				else 
				{
					next.set(j, 0);
				}
				//previous advances
				prev = cur;
			}

			graph.put(curPrison, new Node(next, i));
			curPrison = next;
		}
		return null;
	}

	class Node{
		List<Integer> prison;
		int days;

		Node(List<Integer> p, int d){
			prison = p;
			days = d;
		}
	}
}



/*********************
 * OTHERS SOLUTIONS
 *********************/

/**
 * This solution has the same approach than the solution 1 but with one significant difference.
 * 
 * The calculation of the next day is done with bitmaping
 * 
 * The detail explanation of how the function works can be seen below in the method description
 * 
 * @author Nelson Costa
 *
 */
class PrisonCellsAfterNDaysSolution2 {
    public int[] prisonAfterNDays(int[] cells, int N) {

        HashMap<Integer, Integer> seen = new HashMap<>();
        boolean isFastForwarded = false;

        // step 1). convert the cells to bitmap
        int stateBitmap = 0x0;
        for (int cell : cells) {
            stateBitmap <<= 1;
            stateBitmap = (stateBitmap | cell);
        }

        // step 2). run the simulation with hashmap
        while (N > 0) {
            if (!isFastForwarded) 	{
                if (seen.containsKey(stateBitmap)) {
                    // the length of the cycle is seen[state_key] - N
                    N %= seen.get(stateBitmap) - N;
                    isFastForwarded = true;
                } else
                    seen.put(stateBitmap, N);
            }
            // check if there is still some steps remained,
            // with or without the fast forwarding.
            if (N > 0) {
                N -= 1;
                stateBitmap = this.nextDay(stateBitmap);
            }
        }

        // step 3). convert the bitmap back to the state cells
        int ret[] = new int[cells.length];
        for (int i = cells.length - 1; i >= 0; i--) {
            ret[i] = (stateBitmap & 0x1);
            stateBitmap = stateBitmap >> 1;
        }
        return ret;
    }

    /**
     * This operation might look cryptic but it is not if looked carefully.
     * 
     * the gist of this is to realize what we are doing. 
     * 
     * And in this case we want to check if both sides of a number are equal
     * 
     * we do it with bit mapping.
     * 
     * this could be as easier with the right the right operator but we dont have it.
     * 
     * 
     * 		 V   V   V   V  
     * 	 -1  1   2   3   4  -1     <-- position in the bitmap (-1 is outofbounds)
     * 		 1  [1]  0  (1)		  <-- focus on 0 and the elements that need to be equal with [] and ()
     * 	  1 [1]  0  (1)	 0		  <-- shift one left (new zero added because of shift)
     * 		 0   1  [1]  0  (1)   <-- shift one right (new zero added at the front because of shift)
     * 
     * [OPERATOR FOR LEFT AND RIGHT SHIFT?]
     * 
     * 
     * if you see carefully, you realize that by shifting left and shifting right
     * we can compare the elements that we want at the right positions. 
     * i-1 and i+1 (represented by [] and ()) overlap. (use colors in each bit if needed to have a better view)
     * however we have no operator to deal with this.
     * We need an operator that only returns true when both elements are true, and both elements are false.
     * in logic, false and false is never true, so we have no such operator.
     * but we can work things around, we have an operator that when one object is false and the other is true it returns true.
     * it is the XOR. so we can fix this situation by negating one of the shifts and apply an XOR (Exclusive OR)
     * 
     * PROBLEM SOLVED
     * 
     * @media
     * 		https://imgur.com/6wDfywc (drafts and experiements on this subject)
     * 
     * @param stateBitmap
     * @return
     */
    protected int nextDay(int stateBitmap) {
        stateBitmap = ~(stateBitmap << 1) ^ (stateBitmap >> 1);
        // set the head and tail to zero
        stateBitmap = stateBitmap & 0x7e;
        return stateBitmap;
    }
}



/**
 * What this solution does is similar to what I do in my solution 3, but with much less code and complexity
 * 
 * In a nutshell they calculate all the unique prison days and from there they fastfoward the next iterations.
 * 
 * so:
 * 		1) they calculated the unique nodes in M times
 * 		2) they just decrement the rest of the N in the N - M remaining iterations
 * 
 * 
 * Main Differences:
 * 
 * 	  MATH
 * 			The calculation of the mod and so forth is made on the fly in a elegant way.
 * 			They dont dedicate a separate time for doing that calculation
 * 			But the principle still applies and is the same I used.
 * 
 * 			get rid of the non cycle part for N and total number of of days, do the mod of whats left with the cycle size.
 * 			I just made this formula happen quite literaly and this author have done it another way.
 *  		
 * 			
 * 
 *	  CALCULATE NEXT DAY ITERATION
 *	  		Contrary to my solution they calculate next day every time.
 *	  		there is no need for that. I don't do it.		
 * 
 *	  DP 
 *	  		Calculates the new Day in DP style
 * 
 *	  USING BITMAP INSTEAD OF LIST
 *	 		A couple of differences, this guys convert the cells into bitmap in order to lookup in the Hashtable.
 *	  
 *	  		Trying to lookup with and array would not work because the equals for different arrays dont match.
 *	  
 *	  		I circunvent this problem by using lists instead of arrays. because collects equals can be compared with equals
 * 
 * @param cells
 * @return
 */
class PrisonCellsAfterNDaysSolution1 {

	/**
	 * Converts Cell to bit map
	 * @param cells
	 * @return
	 */
	protected int cellsToBitmap(int[] cells) {
		int stateBitmap = 0x0;
		for (int cell : cells) {
			stateBitmap <<= 1;
			stateBitmap = (stateBitmap | cell);
		}
		return stateBitmap;
	}

	protected int[] nextDay(int[] cells) {
		int[] newCells = new int[cells.length];
		newCells[0] = 0;
		for (int i = 1; i < cells.length - 1; i++)
			newCells[i] = (cells[i - 1] == cells[i + 1]) ? 1 : 0;
		newCells[cells.length - 1] = 0;
		return newCells;
	}

	public int[] prisonAfterNDays(int[] cells, int N) {

		HashMap<Integer, Integer> seen = new HashMap<>();
		boolean isFastForwarded = false;

		// step 1). run the simulation with hashmap
		while (N > 0) {
			if (!isFastForwarded) {
				int stateBitmap = this.cellsToBitmap(cells);
				if (seen.containsKey(stateBitmap)) {
					// the length of the cycle is seen[state_key] - N 
					//this is the length from the start untill cycle is found. the tail of the cycle: -O
					
					/**
					 * 		if N = 20 & Days (cycle with 3rd day) like:  1 2 [3] 4 5 6 [3]
					 * 		
					 * 		we would get to this point at
					 * 			N = 14
					 * 			[3] was seen at 18. (because they subtract from total and stop at zero)
					 * 			
					 * 		   20  19 18  17 16 15  14  <- this is the evolution of N until finding cycle
					 * 			1  2  [3]  4  5  6  [3]
					 * 			
					 * 		After finding cycle they update N 
					 * 		to the Mod of current N by the size of the cycle which is  given by:
					 * 
					 * 		value stored with 3 (18) - current N (14) which discounts the non cycle part
					 * 
					 */
					
					N %= seen.get(stateBitmap) - N; 
					isFastForwarded = true;
				} else
					seen.put(stateBitmap, N);
			}
			// check if there is still some steps remained,
			// with or without the fast-forwarding.
			if (N > 0) {
				N -= 1;
				cells = this.nextDay(cells);
			}
		}
		return cells;
	}
}
