package com.data.structures.problems.contest;

import java.util.Stack;

/**
 * https://leetcode.com/problems/maximum-score-from-removing-substrings/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class b43MaximumScoreFromRemovingSubstrings {

	/*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * @intuition
	 * 		This was a very though question, I was able of identifying the gist but my implementation was not optimal at all.
	 * 		I could have identified that with the provided constraints a linear solution may exist and I should have tried it out.
	 * 		
	 * 		This solution is a second attempt where the goal is to process each element in the string and pass to a stack
	 * 		every time the top element and the current element is equals to the max("ab","ba") we sum, after processing the input for the biggest element 
	 * 		we calculate the string for the smaller of the 2.
	 * 
	 * 		between calculating the bigger of x and y, and processing the smaller, we remove leftovers from the stack to a string and reverse the string, in orther we can process it with the same logic but for the other input
	 * 
	 * 		This explanation might be a little bit confusing but It's easier to understand maybe checking the code.
	 * 
	 * 		duplicate code was an issue.
	 * 
	 * @score
	 * 		Runtime: 374 ms, faster than 50.00% of Java online submissions for Maximum Score From Removing Substrings.
	 * 		Memory Usage: 115.3 MB, less than 16.67% of Java online submissions for Maximum Score From Removing Substrings.
	 * 
	 * @time
	 * 		O(N)
	 * 
	 * @space
	 * 		O(N)
	 * 
	 * @param s
	 * @param x
	 * @param y
	 * @return
	 */
	public int maximumGain(String s, int x, int y) {
		Stack<Character> stack = new Stack<>();
		int points = 0;
		char c;

		//traverse the string
		for (int i = 0; i < s.length(); i++)
		{
			c = s.charAt(i);
			if (x > y)
			{
				//if we found "ab" we sum points else we add to stack because maybe next element will make a match
				if (!stack.isEmpty() && stack.peek() == 'a' && c == 'b')
				{
					stack.pop();
					points += x;
				}
				else
				{
					stack.push(c);
				}
			}
			else
			{
				//if we found "ba" we sum points else we add to stack because maybe next element will make a match
				if (!stack.isEmpty() && stack.peek() == 'b' && c == 'a')
				{
					stack.pop();
					points += y;
				}
				else
				{
					stack.push(c);
				}

			}
		}

		//after processing the bigger string we can have leftover and because the stack is on contrary 
		//we will pass the stack content to a string and then we will reverse it to have the original string order of string, and then we can just apply the same logic
		StringBuilder s2 = new StringBuilder(""); 
		while (!stack.isEmpty())
		{
			s2.append(stack.pop());
		}

		s2 = s2.reverse();


		//after getting the string in the right way we can just apply the same logic, with the difference that when x > y we apply the less valuable of the moves, else we apply the smaller of the moves
		for (int i = 0; i < s2.length(); i++)
		{
			c = s2.charAt(i);
			if (x > y)
			{
				if (!stack.isEmpty() && stack.peek() == 'b' && c == 'a')
				{
					stack.pop();
					points += y;
				}
				else
				{
					stack.push(c);
				}
			}
			else
			{
				if (!stack.isEmpty() && stack.peek() == 'a' && c == 'b')
				{
					stack.pop();
					points += x;
				}

				else
				{
					stack.push(c);
				}

			}
		}

		return points;
	}
}

/*********************
 * UNOFFICIAL SOLUTIONS
 *********************/

/**
 * What this guys does is to process the bigger of the elements and insert in stack
 * And he than uses the leftover of the first stack to process the second move
 * 
 * the code is very cleaver ad well written with no duplication of code.
 * 
 * @author Nelson Costa
 *
 */
class b43MaximumScoreFromRemovingSubstringsUnofficialSolution{
	public int maximumGain(String s, int x, int y) {
		Stack<Character> stack = new Stack<>(), stack2 = new Stack<>();
		int result = 0, max=Math.max(x,y), min =Math.min(x,y);
		char first= (x>y)?'a':'b', second=(x>y)?'b':'a';
		
		//process more rewarding move
		for(char c: s.toCharArray()) 
		{
			if(!stack.isEmpty() && stack.peek() == first && c == second) {
				stack.pop(); 
				result +=max;
			} else stack.push(c);
		}

		//process second more rewarding move very cleaver
		while(!stack.isEmpty()) {
			char c = stack.pop();
			if(!stack2.isEmpty() && stack2.peek() == first && c == second) {
				stack2.pop(); 
				result +=min;
			} else stack2.push(c);
		}
		return result;
	}
}
