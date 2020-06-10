package com.data.structures.problems;

import java.util.HashMap;
import java.util.Stack;

/**
 * https://leetcode.com/problems/valid-parentheses/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class ValidParentheses {

	public static void main(String[] args) {
	}

	/**
	 * 
	 * 
     @intuition
		closed parentisis are the key here.
		we always compare de close parentises with the top of the stack, if it matches then we are good, if not we are not and the string is invalid.
		besides all this comparision in the end if the stack is empty the string is good, else it is invalid.

    @score
        Runtime: 2 ms, faster than 29.20% of Java online submissions for Valid Parentheses.
        Memory Usage: 37.6 MB, less than 49.56% of Java online submissions for Valid Parentheses.

    @optimization
        I could put the hashmap in the class
        I could have used and array of characters

    @alternative
        I think I can do this with pointer instead of stack, reducing space complexity but not sure


        @time O(N)
        @space O(N)
	 **/
	public boolean isValid(String s) {

		if (s == null || s.length() == 0)
			return true;

		HashMap<Character, Character> pairs = new HashMap<>();
		pairs.put(')','(');
		pairs.put(']','[');
		pairs.put('}','{');

		Stack<Character> stack = new Stack<>();

		for (char c : s.toCharArray())
		{
			if (isClose(c))
			{
				if (stack.isEmpty() || stack.peek() != pairs.get(c))
					return false;
				else
					stack.pop();
			}
			else
			{
				stack.push(c);
			}
		}

		return stack.isEmpty();
	}

	public boolean isClose(char c)
	{
		if (c == ')' ||c == '}' ||c == ']')
		{
			return true;
		}
		return false;
	}

}

/*********************
 * OTHERS SOLUTIONS
 *********************/

/**
 * Same Ideia execcuted differently
 * @author Nelson Costa
 *
 */
class ValidParenthesesSolution1 {

	// Hash table that takes care of the mappings.
	private HashMap<Character, Character> mappings;

	// Initialize hash map with mappings. This simply makes the code easier to read.
	public ValidParenthesesSolution1() {
		this.mappings = new HashMap<Character, Character>();
		this.mappings.put(')', '(');
		this.mappings.put('}', '{');
		this.mappings.put(']', '[');
	}

	public boolean isValid(String s) {

		// Initialize a stack to be used in the algorithm.
		Stack<Character> stack = new Stack<Character>();

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			// If the current character is a closing bracket.
			if (this.mappings.containsKey(c)) {

				// Get the top element of the stack. If the stack is empty, set a dummy value of '#'
				char topElement = stack.empty() ? '#' : stack.pop();

				// If the mapping for this bracket doesn't match the stack's top element, return false.
				if (topElement != this.mappings.get(c)) {
					return false;
				}
			} else {
				// If it was an opening bracket, push to the stack.
				stack.push(c);
			}
		}

		// If the stack still contains elements, then it is an invalid expression.
		return stack.isEmpty();
	}
}


/*********************
 * UNOFFICIAL SOLUTION
 *********************/
/**
 * What I liked the most was the using of the array for the mapping stuff
 * very cleaver
 * 
 * And also he was able of using an array instead of a stack
 * 
 * congrats, well done
 * 
 * @author anonymous
 *
 */
class ValidParenthesesUnofficialSolution1 {

	public boolean isValid(String s) {
		char[] stack = new char[s.length() + 1];
		char[] why = new char[256];
		why[')'] = '(';
		why['}'] = '{';
		why[']'] = '[';
		int top = 0;

		for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '(' || c == '{' || c=='[') {
				top++; stack[top] = c;
			} else {
				char a = stack[top];
				top--;
				if (top < 0) {
					return false;
				}
				if (a != why[c]) return false;
			}
		}
		return top == 0;
	}
}