package com.data.structures.problems;

import java.util.HashMap;
import java.util.Stack;

public class ValidParentheses_Take2_DC {
	
	/**
	 * @intuition
	 * 		Stack implementation
	 * 
	 * @time
	 * 		O(N)
	 * 
	 * @space
	 * 		O(1)
	 * 	
	 * @author Nelson Costa
	 *
	 */
	class Solution {
	    public boolean isValid(String s) {
	        
	        
	        Stack<Character> stack = new Stack<>();
	        HashMap<Character, Character> map = new HashMap<>();
	        map.put(')', '(');
	        map.put(']', '[');
	        map.put('}', '{');
	        char c;
	        
	        for (int i = 0; i < s.length(); i++)
	        {
	            c = s.charAt(i);
	            
	            if (!stack.isEmpty() && stack.peek() == map.get(c))
	            {
	                stack.pop();
	            }
	            else //if (!stack.isEmpty() && stack.peek() != map.put(c)){
	                stack.push(c);
	            }
	            
	        return stack.isEmpty();
	    }
	        
	}
}
