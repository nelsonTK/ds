package com.data.structures.problems;

import java.util.Stack;

/**
 * https://leetcode.com/problems/decode-string/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class DecodeString {

	static DecodeString d = new DecodeString();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "3[a2[c]]";
		s = "3[a]2[b4[F]c]";
		System.out.println(d.decodeString(s));

	}

	 /*********************************
	 * SOLUTION 1
	 ********************************/
	/** 
	 * @intuition
	 * 		I used a stack solution to solve this issue but it is messy.
	 * 		The code is not neat
	 * 
	 * 		I should repeat this one
	 * 
	 * @score
	 * 		Runtime: 1 ms, faster than 75.16% of Java online submissions for Decode String.
	 *		Memory Usage: 37.1 MB, less than 94.67% of Java online submissions for Decode String.
	 *
	 * @comments
	 * 		part of the code could be reduce because it is repeated
	 *
	 * @debug
	 * 		yes
	 * 
	 * @fail   
	 * 		1) memory exceeded, forgot to decrement repetitions 
	 * 		2) while stack not empty, erro no while
	 * 		3) missed in putting the string when the stack is empty
	 * 		4) algorithm is wrong because I did not saw the case where before a word there was not letter
	 * 
	 * @time
	 * 		O(N)
	 * 
	 * @space
	 * 		O(N)
	 * 
	 **/
	public String decodeString(String s) {

		Stack<String> stack = new Stack<>();
		StringBuilder word = new StringBuilder("");
		Integer num = 0;
		for (int i = 0; i < s.length(); i++)
		{
			char c = s.charAt(i);
			if(Character.isLetter(c))
			{                
				word.append(c);
			}
			else if (Character.isDigit(c))
			{
				if (word.length() > 0)
				{  
					stack.push(word.toString());
					word = new StringBuilder("");
				}

				num = num * 10 + c - '0';
			}
			else if (c == '[')
			{
				if (num > 0)
				{
					stack.push(num.toString());
					num = 0;
				}

				stack.push(null);
			}
			else if (c == ']')
			{

				if (word.length() > 0)
				{  
					stack.push(word.toString());
					word = new StringBuilder("");
				}

				String cur = stack.pop();
				if (stack.peek() == null) {
					stack.pop(); //remove other parentheses

					int repetitions = Integer.parseInt(stack.pop());
					while (repetitions > 0)
					{
						word.append(cur);
						repetitions--;
					}

					// can only be letter if the string is not empty and its not a opening parentheses
					if (!stack.isEmpty() && stack.peek() != null)
					{
						cur = stack.pop() + word.toString();

						stack.push(cur);
					}
					else
					{
						cur = word.toString();
						stack.push(cur);
					}

					word = new StringBuilder();
				}
				else
				{
					cur = stack.pop() + cur;
					stack.pop(); //remove parentheses

					int repetitions = Integer.parseInt(stack.pop());
					while (repetitions > 0)
					{
						word.append(cur);
						repetitions--;
					}

					// can only be letter if the string is not empty and its not a opening parentheses
					if (!stack.isEmpty() && stack.peek() != null)
					{
						cur = stack.pop() + word.toString();

						stack.push(cur);
					}
					else
					{
						cur = word.toString();
						stack.push(cur);
					}

					word = new StringBuilder();
				}
			}
		}

		if (word.length() != 0)
		{
			stack.push(word.toString());
		}

		String ans = "";
		while (!stack.isEmpty())
		{
			ans = stack.pop() + ans;
		}

		return ans;
	}
}
