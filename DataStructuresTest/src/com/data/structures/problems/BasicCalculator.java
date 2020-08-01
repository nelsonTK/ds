package com.data.structures.problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/basic-calculator/
 * HARD
 * @author Nelson Costa
 *
 */
public class BasicCalculator {

	public static void main(String[] args) {

	}



	static final int SUM = -2;
	static final int SUBTRACTION = -1; 
	static final int INVALID_OPERATOR = -3; 

	/*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * @intuition
	 * 		Just a recursive problem where I parse the string into single elements and sum them in the end.
	 * 
	 * 		every time I find parentheses I do dfs and return its value, 
	 * 		which is than added to the math expression, 
	 * 		and also move the index in the string forward
	 * 		
	 * 		we than execute the math expression.
	 * 		nothing fancy
	 * 		
	 * 
	 * @optimizations
	 * 		I believe that I could have avoided extra space, with the list of math expressions
	 * 		I could have avoided Integer.parseInt by using the char trick
	 * @score
	 * 		Runtime: 28 ms, faster than 17.12% of Java online submissions for Basic Calculator.
	 * 		Memory Usage: 53.5 MB, less than 8.01% of Java online submissions for Basic Calculator.
	 * 
	 * 
	 * 	 		WITH CHAR TRICK OPTIMIZATION
	 * 				Runtime: 19 ms, faster than 26.32% of Java online submissions for Basic Calculator.
	 * 				Memory Usage: 45.4 MB, less than 13.53% of Java online submissions for Basic Calculator.
	 * 
	 * 	 			Runtime: 6 ms, faster than 71.98% of Java online submissions for Basic Calculator.
	 * 				Memory Usage: 40.9 MB, less than 38.14% of Java online submissions for Basic Calculator.
	 * 
	 * @fail)
	 *             1) Index out of bounds in first while for parsing numbers
	 *             2) Index out of bounds in second while for spaces
	 *             3) NumberFormat exception when there are leading and trailling spaces, resolve with triming the string
	 * 
	 * 
	 * 	@time	O(N + M) 
	 * 			where N is the number characters. and M is the number of final elements after reducing the parentheses
	 * 
	 * @space	O(N) => O(N) + O(N)
	 * 			Case where no parentheses exists
	 * @space	
	 * @param s
	 * @return
	 */
	public int calculate(String s) {

		if(s == null || s.length() == 0)
			return 0;

		s = s.trim();
		if(s.length() == 1)
			return Integer.parseInt(s);

		return dfsCalc(s, 0).num;
	}


	private Pair dfsCalc(String s, int start)
	{   
		int i = start;
		//StringBuilder curNum;
		int curNum;
		List<Integer> mathExpression = new ArrayList<>();     

		//processing string and tranforming in math expression
		while (i < s.length() && s.charAt(i) != ')')
		{
			//if digit add all the number to math expression
			if (Character.isDigit(s.charAt(i)))
			{
				//curNum = new StringBuilder("");
				//curNum.append(s.charAt(i));
				curNum = 0;
				curNum = 10 * curNum + (s.charAt(i) - '0');
				i++;

				while(i < s.length() && Character.isDigit(s.charAt(i)))
				{            
					//curNum.append(s.charAt(i));    
					curNum = 10 * curNum + (s.charAt(i) - '0');
					i++;
				}

				//mathExpression.add(Integer.parseInt(curNum.toString()));    
				mathExpression.add(curNum);     
			}
			// if open parentheses we open a new dfs and update our index
			else if (s.charAt(i) == '(')
			{
				Pair p = dfsCalc(s, i + 1);
				mathExpression.add(p.num);
				i = p.index; //i + 1

			}
			//if sign we add it to the mathExpression
			else if (isSign(s.charAt(i)))
			{
				mathExpression.add(encodeSign(s.charAt(i)));
				i++;
			}
			// else, which is space, forward.
			else
			{
				while (i < s.length() && s.charAt(i) == ' ')
				{
					i++;
				}
			}

		}

		//resolve math expression into a number
		int sum = mathExpression.get(0);
		for (int e = 1; e < mathExpression.size(); e++)
		{
			switch(mathExpression.get(e))
			{
			case -2:
				sum+=mathExpression.get(++e);
				break;
			case -1:
				sum-=mathExpression.get(++e);
				break;
			}
		}

		return new Pair(sum, i + 1);
	}

	private boolean isSign(char c){
		if (c == '+' || c == '-')
			return true;
		return false;
	}

	private int encodeSign(char c){
		if (c == '+' || c == '-')
		{
			switch(c)
			{
			case '+':
				return SUM;
			case '-':
				return SUBTRACTION;
			}
		}
		return INVALID_OPERATOR;
	}

	class Pair{
		int num;
		int index;
		public Pair (int n, int i){
			num = n;
			index = i;
		}
	}
}


/*********************
* OTHERS SOLUTIONS
*********************/
/**
 * 
 * 	@intuition
 * 		In this solution we always put the sign and the number together
 * 
 * 		before a parenthesis we always put the sign in the stack
 * 
 * 		we have a result variable that has the current result inside a parentheses (or in the operand where we are at if no parentheses)
 * 
 * 		and we have a operand value that has the current number formed
 * 
 * 		when we find a sign, we join the previous sign and operand together and add it to the result
 * 
 * 		when we find a open parentheses we push the result before and sign to the stack (sign on top).
 * 		we also reset result and sign.
 * 
 * 		when we find a closing parentheses we sum to the result the last operand (and its sign)
 * 		
 * 		and we multiply the result with the sign before the parentheses that were before the opening parentheses
 * 
	 @score
	 	Runtime: 9 ms, faster than 51.61% of Java online submissions for Basic Calculator.
		Memory Usage: 41.3 MB, less than 32.36% of Java online submissions for Basic Calculator.
		
		
 * @author Nelson Costa
 *
 */
class BasicCalculatorSolution2 {
	public int calculate(String s) {

		Stack<Integer> stack = new Stack<Integer>();
		int operand = 0;
		int result = 0; // For the on-going result
		int sign = 1;  // 1 means positive, -1 means negative

		for (int i = 0; i < s.length(); i++) {

			char ch = s.charAt(i);
			if (Character.isDigit(ch)) {

				// Forming operand, since it could be more than one digit
				operand = 10 * operand + (int) (ch - '0');

			} else if (ch == '+') {

				// Evaluate the expression to the left,
				// with result, sign, operand
				result += sign * operand;

				// Save the recently encountered '+' sign
				sign = 1;

				// Reset operand
				operand = 0;

			} else if (ch == '-') {

				result += sign * operand;
				sign = -1;
				operand = 0;

			} else if (ch == '(') {

				// Push the result and sign on to the stack, for later
				// We push the result first, then sign
				stack.push(result);
				stack.push(sign);

				// Reset operand and result, as if new evaluation begins for the new sub-expression
				sign = 1;
				result = 0;

			} else if (ch == ')') {

				// Evaluate the expression to the left
				// with result, sign and operand
				result += sign * operand;

				// ')' marks end of expression within a set of parenthesis
				// Its result is multiplied with sign on top of stack
				// as stack.pop() is the sign before the parenthesis
				result *= stack.pop();

				// Then add to the next operand on the top.
				// as stack.pop() is the result calculated before this parenthesis
				// (operand on stack) + (sign on stack * (result from parenthesis))
				result += stack.pop();

				// Reset the operand
				operand = 0;
			}
		}
		return result + (sign * operand);
	}
}

/**
 * @intuition
 * 		the process the string in reverse order
 * 
 * @score
	 	Runtime: 22 ms, faster than 22.28% of Java online submissions for Basic Calculator.
		Memory Usage: 42.2 MB, less than 21.38% of Java online submissions for Basic Calculator.
 * @author Nelson Costa
 *
 */
class BasicCalculatorSolution1 {

	public int evaluateExpr(Stack<Object> stack) {

		int res = 0;

		if (!stack.empty()) {
			res = (int) stack.pop();
		}

		// Evaluate the expression till we get corresponding ')'
		while (!stack.empty() && !((char) stack.peek() == ')')) {

			char sign = (char) stack.pop();

			if (sign == '+') {
				res += (int) stack.pop();
			} else {
				res -= (int) stack.pop();
			}
		}
		return res;
	}

	public int calculate(String s) {

		int operand = 0;
		int n = 0;
		Stack<Object> stack = new Stack<Object>();

		for (int i = s.length() - 1; i >= 0; i--) {

			char ch = s.charAt(i);

			if (Character.isDigit(ch)) {

				// Forming the operand - in reverse order.
				operand = (int) Math.pow(10, n) * (int) (ch - '0') + operand;
				n += 1;

			} else if (ch != ' ') {
				if (n != 0) {

					// Save the operand on the stack
					// As we encounter some non-digit.
					stack.push(operand);
					n = 0;
					operand = 0;

				}
				if (ch == '(') {

					int res = evaluateExpr(stack);
					stack.pop();

					// Append the evaluated result to the stack.
					// This result could be of a sub-expression within the parenthesis.
					stack.push(res);

				} else {
					// For other non-digits just push onto the stack.
					stack.push(ch);
				}
			}
		}

		//Push the last operand to stack, if any.
		if (n != 0) {
			stack.push(operand);
		}

		// Evaluate any left overs in the stack.
		return evaluateExpr(stack);
	}
}

