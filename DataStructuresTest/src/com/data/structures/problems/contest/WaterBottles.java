package com.data.structures.problems.contest;

/**
 * https://leetcode.com/problems/water-bottles/
 * EASY
 * contest 198
 * @author Nelson Costa
 *
 */
public class WaterBottles {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * @intution 
	 * 		nothing special is to divide the numbers of bottles by the number of exchanges.
	 * 
	 * 		Base I start with all bottles consumed.
	 * 
	 * 		then if the empty bottles are equal or bigger than the exchanging bottles we increase the drinking, add one bottle to the total of new bottles
	 * 
	 * 		and add the drinked bottle to the empty bottles and continue this cycle
	 * 		
	 * @fail
	 * 
	 * 
	 * @time
	 * 		O(n) I think so, must analyse the output though to come to this conclusion
	 * 
	 * @space
	 * 		O(1)
	 * 
	 * @param numBottles
	 * @param numExchange
	 * @return
	 */
    public int numWaterBottles(int numBottles, int numExchange) {
        int drink = numBottles, swaped = 0, emptyBottles = numBottles;
        
        while ((emptyBottles) >= numExchange){
            
            while ((emptyBottles) >= numExchange)
            {
                emptyBottles -= numExchange; //swap
                drink++; //drink
                swaped++; //accomulate empty bottle

            }
            emptyBottles += swaped; //add empty bottle
            swaped = 0; //reset swapped bottle
        }
        
        return drink;
        
    }
	
}
