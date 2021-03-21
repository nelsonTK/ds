package com.data.structures.problems;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * https://leetcode.com/problems/keys-and-rooms/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class KeysandRooms {

    
    /**
     * 	@intuition
     *     It was easy
     *     
     *  @fail
     *  	Mistake in Time complexity calculations   
     *     
     *  @time
     *  	O(N + E)
     *     
     *  @space
     *     O(N)    
    **/
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        
        if (rooms == null || rooms.size() == 0)
            return true;
        
        Set<Integer> seen = new HashSet<>();
        Queue<Integer> q = new ArrayDeque<>();
        q.add(0);
        seen.add(0);
        
        Integer cur;
        
        while (!q.isEmpty())
        {
            
            cur = q.poll();
            
            for (Integer room : rooms.get(cur))
            {
                if (!seen.contains(room))
                {
                    q.add(room);
                    seen.add(room);
                }
            }
        }
        
        return seen.size() == rooms.size();
    }
}
