package com.data.structures.problems;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/solve-the-equation/
 * MEDIUM	
 * @author Nelson Costa
 *
 */
public class SolveTheEquation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "2x+3x-6x=x+2";
		s = "2x=x";
		s = "2=-x";
		s = "3x=33+22+11";
		SolveTheEquation e = new SolveTheEquation();
		System.out.println(e.solveEquation(s));
	}

	

	 /*********************************
	 * SOLUTION 1
	 ********************************/
	/** 
	 * @intuition
	 *     	what i simulate is to pass all the coefficients to the left side
	 *		And then I pass the number elements to the right side.
	 *		I could have broke my equation in half but I didn't made it that way.
	 * 
	 *         
	 * @score
	 *		Runtime: 3 ms, faster than 86.73% of Java online submissions for Solve the Equation.
	 *		Memory Usage: 37.4 MB, less than 84.44% of Java online submissions for Solve the Equation.   
	 *
	 *  	Runtime: 5 ms, faster than 62.50% of Java online submissions for Solve the Equation.
	 *		Memory Usage: 37 MB, less than 99.23% of Java online submissions for Solve the Equation.
	 *         
	 * @fail
	 * 		1) I was not resetting the signal after finding the equal sign (6 min)
     * 		2) change signal was wrong
     * 		3) I had a copy mistake, I exchange the num list for the vars.
     * 		4) forgot about an edge case where the last element is a number, it was not being added to the equation
	 * 		5) I had reverse duplicated
	 * 		6) many mistakes related with the reversing of signal
	 * 		7) I was not reseting the current value after finding equals sign
	 * 		8) fetch function was wrong I was adding the value to the previous...
	 * 
	 *  @time
	 *  	O(N)
	 *  
	 *  @space
	 *  	O(N)
	 * 
	 * @param equation
	 * @return
	 */
	public String solveEquation(String equation) {
		//List of Vars
		//List of constants
		//Sum boths
		//if x is not one divide constants by x.
		//if x is zero return infinite solutions (x0 & 0)
		//if x zero and num > zero retrn no solution
		//this is the simulation of passing all the values to the left side

		List<Integer> vars = new ArrayList<>();
		List<Integer> num  = new ArrayList<>();
		int curNum = 0;
		int signal = 1;
		int reverse = 1;

		int i = 0;
		char cur = ' ';

		while (i < equation.length())
		{
			cur = equation.charAt(i);

			if (cur == '=')
			{
				//Simulate the passage of num numbers to the left side of the equation
				reverse = -1;
				//if previous different from zero add to num list,
				add(num, curNum);
				signal = 1 * reverse;
				curNum = 0;
				i++;
			}
			else if (cur == '+')
			{
				//if previous different from zero add to num list,
				add(num, curNum);
				signal = 1 * reverse;
				i++;
			}
			else if (cur == '-')
			{
				//if previous different from zero add to num list,
				add(num, curNum);
				signal = -1 * reverse;
				i++;
			}            
			else if (Character.isDigit(cur))
			{
				int [] reply = fetchNum(i, equation); //index and num
				i = reply[0];//index
				curNum = signal * reply[1]; //num				
			}
			else if (cur == 'x')
			{
				if (i - 1 < 0 || !Character.isDigit(equation.charAt(i-1)) )
				{
					vars.add(1 * signal);
				}
				else
				{
					vars.add(curNum);
				}
				curNum = 0;

				i++;
			}
		}

		//Get an eventual last value
		if(curNum != 0)
			num.add(curNum);
		
		int x = 0; 
		int nums = 0;

		//Sum variables and constantes
		for (Integer n : vars)
		{
			x +=n;
		}
		for (Integer n : num)
		{
			nums +=n;
		}

		if (x == 0 && nums == 0)
		{
			//infinite solutions x = x we can replace for any value
			return  "Infinite solutions";
		}

		if (x == 0 && nums != 0)
		{
			//no solution   = impossible
			return "No solution";
		}

		//simulate the passage of constants from left to right
		nums = -nums;

		//divide if x is not 1, divide by the constants
		if (x != 1)
		{
			nums /=x;
		}

		return "x=" + nums;

	}

	private int [] fetchNum(int i, String eq){

		int num = 0;

		while (i < eq.length() && Character.isDigit(eq.charAt(i)))
		{
			num = num*10 + (eq.charAt(i) - '0');
			i++;
		}
		
		return new int[]{i, num};
	}

	private void add(List<Integer> num, int curNum)
	{
		if (curNum != 0)
		{
			num.add(curNum);
		}
	}
}
