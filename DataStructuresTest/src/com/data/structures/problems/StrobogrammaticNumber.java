package com.data.structures.problems;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/strobogrammatic-number/
 * EASY
 * @author Nelson Costa
 *
 */
public class StrobogrammaticNumber {
	
	/**
	 * @intuition
	 * 		Nothing Special
	 * 
	 * 
	 * @time
	 * 		O(N)
	 * 
	 * @space
	 * 		O(N)
	 * 
	 * @param num
	 * @return
	 */
    public boolean isStrobogrammatic(String num) {
        HashMap<Integer, Character> map = new HashMap<>();
        
        map.put(0,'0');
        map.put(1, '1');
        map.put(2, ' ');
        map.put(3, ' ');
        map.put(4, ' ');
        map.put(5, ' ');
        map.put(6, '9');
        map.put(7, ' ');
        map.put(8, '8');
        map.put(9, '6');
        
        StringBuilder sb = new StringBuilder("");
        
        for (int i = num.length() -1; i >= 0; i--)
        {
            sb.append(map.get(num.charAt(i) - '0'));
        }
        
        return sb.toString().equals(num);
    }
}
