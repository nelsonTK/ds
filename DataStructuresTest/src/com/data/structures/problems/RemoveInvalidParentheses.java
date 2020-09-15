package com.data.structures.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * https://leetcode.com/problems/remove-invalid-parentheses/
 * HARD
 * @author Nelson Costa
 *
 */
public class RemoveInvalidParentheses {

	static RemoveInvalidParentheses r = new RemoveInvalidParentheses();
	public static void main(String[] args) {
		String s = "()())()";
		s = "(()";
		s = ")()(";
		s = "((()";
		s = "()((((()";
		s = ")()))())))";
		s = ")t))()()bo)";
		for (String st : r.removeInvalidParentheses(s))
		{
			System.out.println(st);
		}
	}



	int [] parentheses;
	int [] removed;
	int totalBadClosed;
	int totalBadOpened;
	Set<String> ans;


	 /*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * @intuition
	 * 		in the nutshell I break the string in 3 parts based on parentheses
	 * 		
	 * 		invalid beginning - which starts with ")"
	 * 		
	 * 		invalid end - which ends with "(" and 
	 * 		
	 * 		inner parentheses - which starts valid and ends valid
	 * 
	 * 		then the goal is to find a surplus of open and closed parentheses
	 * 
	 * 		from there we try every combination of the surplus of open and closed parentheses
	 * 
	 * 		it's a backtracking solution.
	 * 
	 * 		
	 * 		Remove invalid parentheses at the start
	 * 
	 * 		Remove invalid parentheses at the end
	 * 		
	 * 		find a surplus of open and closed parentheses
	 * 
	 * 		remove from the original string the bad start and bad end invalid parentheses
	 * 
	 * 		At the same time create a representation of all the inner parentheses in a array so that we are not traversing all the string
	 * 
	 * 		And create an Array with the map of removed parentheses
	 * 
	 * 		and we will use mainly the parentheses array and the removed array to build our solution
	 * 
	 * 		after these we will perform all the combinations with the surplus of bad open and bad closed parentheses inside our inner parentheses section.
	 * 
	 * 		
	 * 
	 * @score
	 * 		Runtime: 4 ms, faster than 68.24% of Java online submissions for Remove Invalid Parentheses.
	 * 		Memory Usage: 39.7 MB, less than 60.92% of Java online submissions for Remove Invalid Parentheses.
	 * 
	 * @fail
	 * 		1) failed in the condition that determines how to explorer possibilities
	 * 		2) failed dealing with duplicates
	 * 		3) forgot to fill the array with -1;
	 * 		4) comparison of isValid is wrong
	 * 		5) build answer was wrong too, I was not increasing the removed array correctly 
	 * 		6) failed, forgot to change the hardcoded code for ")" and "(" that allows me to run the same code for both cases~
	 * 		7) miss match entre parentheses e String position, caused by the removel of bad parentheses
	 * 		8) the switch of variables messed up the solution
	 *		9) I was not using the right index for open and closed	
	 *		10) for loop condition at explorePossibilities was wrong it leacked a equal sign
	 *			10.1) cutting the loop too early
	 * 		11) On optimization, I forgot did not made the super precise calculation and forgot to put equals in the for loop condition of explore possibilities
	 * 
	 * 
	 * @time
	 * 		O(4N + Combinations of badClosed * Combinations of badOpen)	
	 * 
	 * @optimization
	 * 		I could check for the valid parentheses with an integer instead of the stack (implemented alreaydy)
	 * 		Cut the for loop in the exploration(tried but did not seem to have a positive effect)
	 * 
	 * @space
	 * 		O(N + N + N)
	 * 			removed
	 * 			parentheses
	 * 			badParentheses Set
	 * 
	 * @param s
	 * @return
	 */
	public List<String> removeInvalidParentheses(String s) {

		// find the bad number of parentheses
		//negative means too much close
		//positive means too much open
		//badStart
		ans = new HashSet<>();
		totalBadClosed = 0;
		totalBadOpened = 0;

		int curParentheses = 0;
		int curClosed = 0;
		int curOpened = 0;
		HashSet<Integer> badParentheses = new HashSet<>();

		//findbad start O(N)
		//ignore those entries
		for (int i = 0; i < s.length(); i++)
		{
			if (curParentheses != curClosed)
				break;

			if (s.charAt(i) == ')')
			{
				curClosed++;
				curParentheses++;
				badParentheses.add(i);
			}
			else                
				if (s.charAt(i) == '(')
				{
					//curOpened++;
					curParentheses++;
				}
		}

		curParentheses = 0;
		curClosed = 0;
		curOpened = 0;
		//find bad end O(N)
		// ignore those entries
		for(int i = s.length() - 1; i >= 0; i-- )
		{
			if (curParentheses != curOpened)
				break;

			if (s.charAt(i) == ')')
			{
				//curClosed++;
				curParentheses++;
			}
			else
				if (s.charAt(i) == '(')
				{
					curOpened++;
					curParentheses++;
					badParentheses.add(i);
				}
		}


		curClosed = 0;
		curOpened = 0;  
		int parenthesesCount = 0;
		//find surplus of bad open/closed parentheses O(N)
		//put in variables
		int stackControl = 0;
		for(int i = 0; i < s.length(); i++)
		{
			if (!badParentheses.contains(i))
			{
				if(s.charAt(i) == ')')
				{
					if (stackControl == 0)
					{
						curClosed++;
					}
					else
					{
						stackControl--;
					}

					parenthesesCount++;
				}
				else if (s.charAt(i) == '(')
				{
					stackControl++;
					parenthesesCount++;
				}
			}
		}
		curOpened = stackControl;

		//create new string O(N) and parentheses
		int parenthesesIndex = 0;
		parentheses = new int[parenthesesCount];
		removed = new int[parenthesesCount];     
		StringBuilder sb = new StringBuilder();
		Arrays.fill(removed, -1);
		for(int i = 0; i < s.length(); i++)
		{ 
			if  (!badParentheses.contains(i))
			{
				sb.append(s.charAt(i));

				if (s.charAt(i) == ')' || s.charAt(i) == '(' )
				{
					parentheses[parenthesesIndex++] = sb.length() - 1;//i;
				}
			}
		}

		totalBadClosed = curClosed;
		totalBadOpened = curOpened;
		//do the combinations
		doCombinations(sb.toString(), curOpened, curClosed, 0, 0);  
		return new ArrayList<String>(ans);
	}

	private void doCombinations(String s, int curOpened, int curClosed, int curOpenIndex, int curCloseIndex)
	{
		if (curOpened <= 0 && curClosed <= 0)
		{
			if (isValid(s, removed))
			{
				ans.add(buildAnswer(s, removed));
			}
			return;
		}
		//dont forget to backtrack the removed array

		if (curClosed > 0)
		{
			explorePossibilities(curClosed, curOpened, curOpenIndex, curCloseIndex, s, ')', '(', false);
		}
		else
		{
			explorePossibilities(curOpened, curClosed, curCloseIndex, curOpenIndex, s, '(', ')', true);
		}
	}


	/**
	 * This method was an intent to save code but resulted in complicating the things a little bit because of swapping.
	 * 
	 * 
	 * 
	 * @param leadParentheses	
	 * 			by default it will be the close parentheses if the value of curClosed is bigger than 0. 
	 * 			else it is the curOpen parentheses. So the lead parentheses is the one that will not get exausted before the other and therefore drives the combination combinatorics.
	 * 
	 * @param LagParentheses
	 * 			Is the other one.
	 * 
	 * @param LagParenthesesIndex
	 * 			Is the index of the leading parentheses
	 * 
	 * @param LeadParenthesesIndex
	 * 			Is the index of the other parentheses
	 * @param s
	 * 
	 * @param lead
	 * 			is the char of the leading parentheses, if leading is curOpen than '(' else ')'
	 * 
	 * @param lag
	 * 			is the char of the lag parentheses, if lag is curOpen than '(' else ')'
	 * 
	 * @param swapOn
	 * 			This method was design for curClose, so if the swap is off it will act with the configurations for curClose
	 * 			else it will run with the configurations of curOpen
	 */
	private void explorePossibilities(int leadParentheses, int LagParentheses, int LagParenthesesIndex, int LeadParenthesesIndex, String s, char lead, char lag, boolean swapOn){

		//for (int i = curCloseIndex; i <= parentheses.length - curClosed ; i++) //optimization attempt but did not resulted in major improvement at all, cut tree earlier
		for (int i = LeadParenthesesIndex; i < parentheses.length ; i++)
		{
			if (s.charAt(parentheses[i]) == lead)
			{
				removed[i] = parentheses[i];

				//if are open parentheses
				if (LagParentheses != 0)
				{
					//for (int j = curOpenIndex; j <= parentheses.length - curOpened; j++) //optimization attempt but did not resulted in major improvement at all, cut tree earlier
					for (int j = LagParenthesesIndex; j < parentheses.length; j++)
					{
						if (s.charAt(parentheses[j]) == lag)
						{
							removed[j] = parentheses[j];
							if (!swapOn)
								doCombinations(s, LagParentheses - 1, leadParentheses - 1, j + 1, i + 1);       
							else
								doCombinations(s, leadParentheses - 1, LagParentheses - 1, i + 1, j + 1);
							removed[j] = -1;
						}
					}
				}
				//if are not open parentheses we only test combinations for closed parentheses
				else
				{
					if (!swapOn)
						doCombinations(s, LagParentheses, leadParentheses - 1, LagParenthesesIndex + 1, i + 1); 
					else
						doCombinations(s, leadParentheses-1, LagParentheses, i + 1, LagParenthesesIndex); 
				}
				removed[i] = -1;
			}
		}
	}

	private String buildAnswer(String s, int[] removed)
	{
		StringBuilder sb = new StringBuilder("");

		int removedIndex = findNextIndexToRemove(0);

		for (int i = 0; i < s.length(); i++)
		{

			if (removedIndex < removed.length && removed[removedIndex] == i )
			{
				removedIndex = findNextIndexToRemove(++removedIndex);
			}
			else
			{
				sb.append(s.charAt(i));
			}
		}        
		return sb.toString();
	}


	private int findNextIndexToRemove(int removedIndex) {

		while (removedIndex < removed.length && removed[removedIndex] == -1)
			removedIndex++;

		return removedIndex;
	}

	/**
	 * Checks if parentheses is valid given the removed pattern for the given string
	 * @param s
	 * @param removed
	 * @return
	 */
	private boolean isValid(String s, int [] removed){

		Stack<Character> stack = new Stack<>();
		int stackControl = 0;
		for (int i = 0; i < parentheses.length; i++)
		{
			if (removed[i] == -1)
			{
				if (s.charAt(parentheses[i]) == ')')
				{
					if (stackControl == 0)
						return false;
					else 
					{
						stackControl--;
					}
				}
				else
				{
					stackControl++;
				}
			}
		}

		return stack.isEmpty() ? true : false;
	}
}
