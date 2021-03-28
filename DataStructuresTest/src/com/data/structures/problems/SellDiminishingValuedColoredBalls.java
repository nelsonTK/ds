package com.data.structures.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SellDiminishingValuedColoredBalls {

    /**
     * @intuition
     * 		The intuition is to sum the difference between numbers and then add a multiple and do the math to get the number of elements used.
     * 
     * 		It's simple to understand and hard to implement
     * 
     * 		This question is could be implemented with priority queue but it would need lower requirements
     * 
     * 		for example:
     * 			1st step
     * 			10	8	7
     * 			1	1	1
     * 			
     * 			+(10 + 9)
     * 			2nd
     * 				8	7
     * 				2	1
     * 				
     * 			+(8 * 2)
     * 			3rd
     * 					7
     * 					3
     * 			+((7 -> 1) * 3)
     * 
     * 
     * @score
     * 		Runtime: 122 ms, faster than 7.75% of Java online submissions for Sell Diminishing-Valued Colored Balls.
     * 		Memory Usage: 50.4 MB, less than 96.33% of Java online submissions for Sell Diminishing-Valued Colored Balls.
     * 
     * @alternative
     * 		I think I can do this with treeMap too
     * 
     * 
     * @fail
     *      a lot of problems with casting and overflow, massive overflow problem
     *     
     * @time
     * 		n log n
     * 	    
     * @space
     *     
     *     
    **/
    public int maxProfit(int[] inventory, int orders) {
        
        int [] revinventory = Arrays.stream(inventory).boxed()
            .sorted(Collections.reverseOrder())
            .mapToInt(Integer::intValue)
            .toArray();
        
        int mod = 1_000_000_007;
        
        List<Balls> list = new ArrayList<>();
        
        //set up a list with all the balls and the multiplier
        for (int i : revinventory)
        {
            list.add(new Balls(i, 1));
        }
        
        //calculate the sum
        Balls next, current;
        long sum = 0;
        for (int i = 0; i < list.size(); i++)
        {
            //if next element value is equals to the current element we collapse them and skip iteration
            if (i + 1 < list.size() && list.get(i).val == list.get(i + 1).val)
            {
                list.get(i + 1).increaseMultiple(list.get(i).multiplier); //I was increasing the elements wrongly
                continue;
            }
            current = list.get(i);  
            //if next element is valid
            if (i + 1 < list.size())
            {
                 
                next = list.get(i+1);
                //get difference and calculate gauss
                
                long balls = ((long)current.val - next.val) * current.multiplier;
                
                //if number of balls wanted  is equals or bigger than provided by the difference 
                //of the two color we can get the complete number of balls and value
                if (orders >= balls)
                {
                    long diff = current.val - next.val;
                    sum += ((gauss(current.val) - gauss(next.val ))*current.multiplier);
                    orders -= diff * current.multiplier;                    
                    next.multiplier += current.multiplier;
                }
                //if number of balls wanted is below the number of balls provided we will iterate and do "one by one" with math, it is the end before getting to the last element
                else
                {
                    //dealing with integer rows
                    int rowsToAdd = orders / current.multiplier;
                    int remainder = orders % current.multiplier;
                    sum += (gauss(current.val) - gauss(current.val - rowsToAdd)) * current.multiplier;
                    orders -= current.val - (current.val - rowsToAdd);
                            
                    //dealing with the remainder
                    sum += (long) remainder * (current.val - rowsToAdd);
                    orders -= remainder;
                    break; //my mistake was that I was not ending the calculations here
                }
            }
            else //is last element
            {
                int rowsToAdd = orders / current.multiplier;
                int remainder = orders % current.multiplier;
                sum += ((gauss(current.val) - gauss(current.val - rowsToAdd)) * current.multiplier) % mod;
                orders -= current.val - (current.val - rowsToAdd);
                
                //dealing with the remainder
                sum +=  (long)remainder * (current.val - rowsToAdd);
                orders -= remainder;

            }
        }
        
        return (int) (sum % mod);
    }
    
    private long gauss(int n)
    {
        return ((long) n) * (n + 1)/2;
    }
    
    
    class Balls{
        int val;
        int multiplier;
        
        public Balls(int v, int m){
            val = v;
            multiplier = m;
        }
        
        public void increaseMultiple(int m)
        {
            multiplier+=m;
        }
        
        public String toString()
        {
            return val + "-" + multiplier;
        }
    }
}
