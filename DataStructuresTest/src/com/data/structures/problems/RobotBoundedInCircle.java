package com.data.structures.problems;

/**
 * https://leetcode.com/problems/robot-bounded-in-circle/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class RobotBoundedInCircle {

    /**
     * @intuition
     * 		The intuition is that after 4 cycles at max you will get back to the initial position
     * 		
     * 
     * @score
     * 		Runtime: 1 ms, faster than 29.24% of Java online submissions for Robot Bounded In Circle.
     * 		Memory Usage: 37.2 MB, less than 39.01% of Java online submissions for Robot Bounded In Circle.
     * 
     * 
     * @time O(N) @space O(1)
    
    
    **/
    public boolean isRobotBounded(String instructions) {

        
        /*
        while laps < 6
        if G consult pointerDirection and add to current position;
        else move pointer direction
            if R move pointer
                if pointer > directions 
                    then pointer = 0;
            if L move pointer
                if pointer < 0
                    then pointer = 3
                    
        if position == start and direction = 'n'
            return true;
        */
    	
    	
        int [][] move = {{-1,0}, {0, 1}, {1, 0}, {0,-1}};
        int directionPointer = 0;
            
        int laps = 4;
        int [] position = {0,0};
        while (laps > 0)
        {
            for (int i = 0; i < instructions.length(); i++)
            {
                char c = instructions.charAt(i);
                if (c == 'G')
                {
                    position[0] += move[directionPointer][0];
                    position[1] += move[directionPointer][1];
                }
                else if (c == 'L')
                {
                    directionPointer = directionPointer == 0 ? move.length - 1 : directionPointer - 1;
                }
                else
                {
                    directionPointer = directionPointer == move.length - 1? 0 : directionPointer + 1;
                }
            }
            
            if (position [0] == 0 && position [1] == 0 && directionPointer == 0)
                return true;
            
            laps --;
        }
        
        return false;
    }
}
