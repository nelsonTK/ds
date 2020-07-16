package com.data.structures.problems;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class MinimumRemovetoMakeValidParentheses {

	static MinimumRemovetoMakeValidParentheses m = new MinimumRemovetoMakeValidParentheses();
	static MinimumRemovetoMakeValidParenthesesUnofficialSolution1 m2 = new MinimumRemovetoMakeValidParenthesesUnofficialSolution1();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "lee(t(c)o)de)";
		s = "a)b(c)d";
		s = "))((";
		s = "(a(b(c)d)";
		s = "aaa(()()((()))";
		System.out.println(m.minRemoveToMakeValid(s));
		System.out.println(m2.minRemoveToMakeValid(s));
	}
	
	/*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * [WRONG]
	 * 		
	 * 
	 * @intuition
	 * 		This algorithm is very very messy unfortunately, kinda works though
	 * 		I'm really not proud of this
	 * 		One of the biggest mistakes here was that I tried to do this question in just one pass, I should have tried with two passes
	 * 		
	 * 
	 * @fail
	 * 		1) copy paste erro
	 * 		2) always increasing L and R was wrong
	 * 		3) and missed many other details the implementation was quite tricky to me
	 * @time  	O(N)
	 * @space 	O(N)
	 * 
	 * @param s
	 * @return
	 */
	public String minRemoveToMakeValid0(String s) {

		//guards
		if (s == null || s.length() == 0)
			return "";

		if (!hasParentheses(s))
			return s;

		if(hasParentheses(s) && s.length() == 1)
			return "";

		char[] ans = new char[s.length()];
		Arrays.fill(ans, Character.MIN_VALUE);
		int L = 0, R = s.length() - 1;
		int lCount = 0, rCount = 0;
		boolean [] lVisisted = new boolean[s.length()];
		boolean [] rVisisted = new boolean[s.length()];
		String lastToMove = "";
		while (L <= R)
		{
			while (rCount < lCount && L <= R)
			{
				boolean found = false;

				if (rVisisted[R])
				{
					R--;
					lastToMove = "r";
					continue;
				}

				if (Character.isLetter(s.charAt(R)))
				{
					ans[R] = s.charAt(R);
					rVisisted[R] = true;
				}
				else 
					if (s.charAt(R) == '(')
					{
						rCount--;
						found = true;
						rVisisted[R] = true;
					}
					else if (s.charAt(R) == ')')
					{
						rCount = rCount < 0 ? 1 : rCount + 1;

						found = true;
						rVisisted[R] = true;
					}

				if (rCount > 0)
				{
					ans[R] = s.charAt(R);
				}

				if (found && lCount == rCount)
				{
					continue;
				} 
				else
				{
					R--;
					lastToMove = "r";
				}
			}


			while (lCount <= rCount && L <= R)
			{
				boolean found = false;

				if (lVisisted[L])
				{
					L++;
					lastToMove = "l";
					continue;
				}

				if (Character.isLetter(s.charAt(L)))
				{
					ans[L] = s.charAt(L);
					lVisisted[L] = true;
				}
				else
					if (s.charAt(L) == '(')
					{
						lCount = lCount < 0 ? 1 : lCount + 1;

						ans[L] = s.charAt(L);
						found = true;
						lVisisted[L] = true;

					}
					else  if (s.charAt(L) == ')')
					{
						lCount--;
						found = true;
						lVisisted[L] = true;
					}

				if (lCount > 0)
				{
					ans[L] = s.charAt(L);
				}

				if (found && lCount > rCount)
				{
					continue;
				}
				else
				{
					L++;
					lastToMove = "l";
				}
			}

		}

		if ((lCount - rCount)*(lCount - rCount) == 1)
		{
			if (lastToMove.equals("r"))
				ans[L] = s.charAt(L);
			else
				ans[R] = s.charAt(R);
		}
		else
		{
			if (lastToMove.equals("r"))
				ans[L] = Character.MIN_VALUE;
			else
				ans[R] = Character.MIN_VALUE;
		}

		String str = "";
		for (int i  = 0; i < s.length(); i++)
		{
			str+= ans[i] != Character.MIN_VALUE ? ans[i] : "";
		}

		return str;
	}

	private boolean hasParentheses(String s )
	{
		for (char c : s.toCharArray())
			if (c == ')' || c == '(')
				return true;
		return false;
	}




	/*********************************
	 * SOLUTION 2
	 ********************************/
	/**
	 * 
	 * 
	 *     
     * @score
     *    Runtime: 35 ms, faster than 20.12% of Java online submissions for Minimum Remove to Make Valid Parentheses.
     *    Memory Usage: 52.4 MB, less than 5.05% of Java online submissions for Minimum Remove to Make Valid Parentheses.        
	 *
     * @fail
     *    1) forgot to put a more specific parentheses because its not only parenteses also letter play a role
     *
	 * @param s
	 * @return
	 */
	public String minRemoveToMakeValid(String s) {

		Stack<Integer> st = new Stack<>();
		Set<Integer> toDelete = new HashSet<>();
		StringBuilder ans = new StringBuilder("");
		char c;

		for (int i = 0; i < s.length(); i++)
		{
			c = s.charAt(i);

			if (c == ')')
			{
				if (st.isEmpty())
				{
					toDelete.add(i);
				}      
				else
				{
					st.pop();
				}
			}
			else if (c == '(')
			{
				st.push(i);
			}
		}

		//put stack items in set before building answer
		while (!st.isEmpty())
		{
			toDelete.add(st.pop());
		}

		//int index = 0;
		// for (char ch : s.toCharArray())
		for (int i = 0; i < s.length(); i++)
		{

			if (!toDelete.contains(i))
				ans.append(s.charAt(i));

		}

		return ans.toString();
	}
}


/*********************
 * OTHERS SOLUTIONS
 *********************/

	/*********************
	 * UNOFFICIAL SOLUTION
	 *********************/

/**
 * 
 * TOP SOLUTION
 * 
 * count the number of parentheses +1 for ( and -1 for )
 * if there are some left open we will delete close the first that we find.
 * 
 * 
 * the most important mindset for this solution is that if we are left with open parentheses we should close them starting from the right.
 * 
 * @author Nelson Costa
 *
 */
class MinimumRemovetoMakeValidParenthesesUnofficialSolution1 {
	public String minRemoveToMakeValid(String s) {

		char[] chars = s.toCharArray();
		int cntOpen = 0;

		//first scan from left to right
		for (int i = 0; i < chars.length; ++i) {
			if (chars[i] == '(') cntOpen++;
			else if (chars[i] == ')') {
				if (cntOpen == 0) chars[i] = '0';
				else {
					--cntOpen;
				}
			}
		}

		//second scan from right to left, close unclosed parentheses
		for (int i = chars.length - 1; i >= 0 && cntOpen > 0; --i) {
			if (chars[i] == '(') {
				chars[i] = '0';
				--cntOpen;
			}
		}

		int i = 0;
		for (char c : chars) {
			if (c != '0') chars[i++] = c;
		}

		return String.valueOf(chars, 0, i);
	}
}

/*********************
 * OFFICIAL SOLUTION
 *********************/

/**
 * Same principle as my solution (My favorite for learning purposes, but not performance)
 * 
 * @author Nelson Costa
 *
 */
class MinimumRemovetoMakeValidParenthesesSolution1 {
	public String minRemoveToMakeValid(String s) {
		Set<Integer> indexesToRemove = new HashSet<>();
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				stack.push(i);
			} if (s.charAt(i) == ')') {
				if (stack.isEmpty()) {
					indexesToRemove.add(i);
				} else {
					stack.pop();
				}
			}
		}
		// Put any indexes remaining on stack into the set.
		while (!stack.isEmpty()) indexesToRemove.add(stack.pop());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			if (!indexesToRemove.contains(i)) {
				sb.append(s.charAt(i));
			}
		}
		return sb.toString();
	}
}

class MinimumRemovetoMakeValidParenthesesSolution3 {


	/**
	 * 	counts the number of open parentheses and counts the balance, creates a string with removed ")"
	then they verify how much parentheses we should keep. one you find them. you can remove the others.
	in other words first they remove the closed parentheses, then they remove the open ones
	 * @param s
	 * @return
	 */
	public String minRemoveToMakeValid(String s) {

		// Parse 1: Remove all invalid ")"
		StringBuilder sb = new StringBuilder();
		int openSeen = 0;
		int balance = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '(') {
				openSeen++;
				balance++;
			} if (c == ')') {
				if (balance == 0) continue;
				balance--;
			}
			sb.append(c);
		}

		// Parse 2: Remove the rightmost "("
		StringBuilder result = new StringBuilder();
		int openToKeep = openSeen - balance;
		for (int i = 0; i < sb.length(); i++) {
			char c = sb.charAt(i);
			if (c == '(') {
				openToKeep--;
				if (openToKeep < 0) continue;
			}
			result.append(c);
		}

		return result.toString();
	}
}