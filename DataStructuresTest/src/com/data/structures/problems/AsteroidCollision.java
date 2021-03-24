package com.data.structures.problems;

import java.util.Stack;

/**
 * https://leetcode.com/problems/asteroid-collision/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class AsteroidCollision {

	
    /**
     * @intuition
     * 		Normal logic, just read the question description this one was easy
     * 
     * @score
     * 		Runtime: 5 ms, faster than 65.57% of Java online submissions for Asteroid Collision.
     * 		Memory Usage: 39.5 MB, less than 83.06% of Java online submissions for Asteroid Collision.
     * 		
     * @fail
     * 		forgeting the case where the peek is negative
     * 
     * @improvements
     * 		I could just negate the negative number
     * 		I could have thought the while loop better
     * 
     * @time
     * 		O(N)
     * 
     * @space
     * 		O(N)
     * 
    **/
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> st = new Stack<>();
        
        for (int asteroid : asteroids )
        {
            if (asteroid > 0)
            {
                st.push(asteroid);
            }
            else
            {
                if (!st.isEmpty())
                {
                    
                    while (!st.isEmpty())
                    {
                        if (st.peek() > 0 && st.peek() > Math.abs(asteroid))
                        {
                            //asteroid is crushed
                            break;
                        }
                        else if (st.peek() > 0 && st.peek() < Math.abs(asteroid))
                        {
                            //asteroid crushes the existing
                            st.pop();
                            if (st.isEmpty())
                            {
                                st.add(asteroid);
                                break;                                
                            }
                        }
                        else if (st.peek() > 0 && st.peek() == Math.abs(asteroid)){
                            //both are blown up
                            st.pop();
                            break;
                        }
                        //if existing peek is negative then no collision
                        else if (st.peek() < 0)
                        {
                            st.add(asteroid);
                            break;
                        }
                            
                    }
                }
                else
                {
                    st.push(asteroid);
                }
            }
            
        }
        
        int [] ans = new int [st.size()];
        
        for (int i = st.size() -1; i >= 0; i--)
        {
            ans[i]=st.pop();
        }
        
        
        return ans;
    }
}
