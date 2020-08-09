package com.data.structures.problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/basic-calculator-ii
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class BasicCalculatorII {

	static BasicCalculatorII b = new BasicCalculatorII();
	static BasicCalculatorIIUnofficialSolution1 b2 = new BasicCalculatorIIUnofficialSolution1();
	static BasicCalculatorIIUnofficialSolution2 b3 = new BasicCalculatorIIUnofficialSolution2();
	public static void main(String[] args) {
		String s = "3+2*2";
//		s = "2+2+555";
//		s = "1+2+3*4*5";
		//		s = "5-10*2";
		//		s = "1233*390";
		//		s = "1233*390*56*68459*0-56*38+324";
		System.out.println(b.calculate(s));
		System.out.println(b2.calculate(s));
		System.out.println(b3.calculate(s));
	}

	/*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * 
	 * @intuition
	 * 		My approch to solve this problem was to break it in 3 stages.
	 * 		
	 * 		1) PRIORITY OPERATOR
	 * 			First solve priority operators like multiplication and division and store them in a list
	 * 
	 * 			If we have a sequence of priority operators we will used the result of one to calculate another.
	 * 			
	 * 			the output is the priorityExpression ArrayList
	 * 
	 * 		2) CREATE AN ORDERED ARRAY OR OPERANDS AND OPERATORS
	 * 			here I'll map every element of the inicial expression to a position in a arraylist
	 * 			if we have something like "3 + 2" the result of this operations is [3,+,2]
	 * 
	 * 			If we have priority operators in the expressions they are replaced by a placeholder that points to the priorityExpression array
	 * 			
	 * 			If the expression is 3 + 2 * 3, we would get [3, +, 0]
	 * 
	 * 			When we have sequence of priority operators, only one placeholder is used, 
	 * 			and it is pointing to the right position in priorityExpression
	 * 
	 * 			if we have 3 * 3 * 3 we would get a [1]. the first iteration would put [0] in the mainExpression List
	 * 			but when in the second iteration it was detected that previous operation was also priority 
	 * 			so the result was overriden by The 1 which points for the second priority calculation result.
	 * 
	 * 			the output is a mainExpression list
	 * 
	 * 		3) SOLVE THE EXPRESSION
	 * 			in this 3rd step we will perform only addictions.
	 * 			when doing addictions you verify if your operand is a number or is a placeholder to a priority expression.
	 * 			if it is not you added a priority operation you add it to total,
	 * 			if it is a placeholder you replace it by the value at the priority expression list and add it to the total.
	 * 
	 *  
	 *  	Main objects
	 *  		Expression
	 *  			is used to represent operands and operators.
	 *  			And the lists I talked about earlier, are lists of this objects.
	 * 			
	 * @comments
	 * 		This solution could be much better. At this point in time I've seen other people solutions
	 * 		and they are much simpler. But I still found pleasure finding my own way through this problem.
	 * 		Let's see I can do better next time.
	 * 
	 * @score
	 * 		Runtime: 35 ms, faster than 13.73% of Java online submissions for Basic Calculator II.
	 *		Memory Usage: 51.3 MB, less than 6.08% of Java online submissions for Basic Calculator II.
	 * 
	 * 		(At the morning)
	 *		Runtime: 14 ms, faster than 50.51% of Java online submissions for Basic Calculator II.
	 *		Memory Usage: 47.6 MB, less than 8.22% of Java online submissions for Basic Calculator II.
	 * 
	 * 
	 * @fail 
	 * 		1) index out of bounds, null pointer exception
	 * 		2) fail for some reason I delete part of the iff condition to treat addicition
	 * 		3) scale calculation was wrong...
	 * 		4) calculating the left operant didn't have parentheses
	 * 		5) out of bounds because of left condition for array was reversed
	 * 		6) forgot to add the last number when we are going to some some operands and we load the last operand
	 * 
	 * @time 
	 * 		O(N)
	 * 
	 * @space
	 * 		O(N)
	 * 
	 * @param s
	 * @return
	 */
	public int calculate(String s) {
		//search for hier priority first
		//then do lower priority


		if (s == null || s.trim().length() == 0)
			return 0;
		Integer isNumber = tryParseInt((s.trim()));
		if (isNumber != null)
			return isNumber;


		/*********************************************************
		 * 1st traversal is for calculating the priority operators
		 *********************************************************/
		List<Expression> priorityExpressions = new ArrayList<>();

		for (int i = 0; i < s.length(); i++)
		{
			char c = s.charAt(i);
			if (c == '*' || c == '/')
			{
				priorityExpressions.add(resolveExpression(s, i, priorityExpressions));
			}
		}


		/*********************************************************
		 * 2nd traversal is for calculating the array with the priority elements indexed to a list
		 *********************************************************/
		Expression pendingNumber = loadDigit(s, 0);
		List<Expression> mainExpression = new ArrayList<>();
		int priorityCalculationLookUp = 0;

		for (int i = pendingNumber.end + 1; i < s.length(); i++)
		{
			//skip to sign
			while(i < s.length() && !isAddiction(s, i) && !isMultiplication(s,i)) 
				i++;

			//if addiction
			if (i < s.length() && isAddiction(s,i))
			{
				Expression prev = 
						mainExpression.size() != 0 ? 
								mainExpression.get(mainExpression.size() - 1) : null;

								//if empty expression we add the numberand the sign
								if (mainExpression.size() == 0 || prev!= null && prev.type != Type.PRIORITY)
								{
									//update main expression
									mainExpression.add(pendingNumber);
									mainExpression.add(getSignCode(s, i));
									//load next number
									pendingNumber = loadDigit(s,++i); 
									i = pendingNumber.end;
								}
								//if expression before is a multiplication or division
								else if (prev.type == Type.PRIORITY) 
								{
									//add plus sign and load next digit
									mainExpression.add(getSignCode(s, i));
									//loadNext number
									pendingNumber = loadDigit(s, ++i);
									i = pendingNumber.end;
								}


								if (pendingNumber != null && pendingNumber.end == s.length() - 1)
								{
									mainExpression.add(pendingNumber);
									//i = pendingNumber.end;
								}
			}
			else if(i < s.length() && isMultiplication(s,i))
			{
				Expression prev = 
						mainExpression.size() != 0 ? 
								mainExpression.get(mainExpression.size() - 1) : null;

								//if expression is empty we add a placeholder
								if (mainExpression.size()== 0)
								{
									//add priority expression with priorityCalculationLookUp
									mainExpression.add(new Expression(priorityCalculationLookUp++));
									pendingNumber = loadDigit(s, ++i);
									i = pendingNumber.end;
								}
								else if (prev.type != Type.PRIORITY)
								{
									//if previous is not multiplication
									//add expression with pointer to the value in priorityExpressions
									//priorityCalculationLookUp                    
									mainExpression.add(new Expression(priorityCalculationLookUp++));
									pendingNumber = loadDigit(s, ++i);
									i = pendingNumber.end;
								}
								else if (prev.type == Type.PRIORITY)
								{
									//if previous is multiplication
									//we increase the number of the index
									prev.val = priorityCalculationLookUp++;
									pendingNumber = loadDigit(s, ++i);
									i = pendingNumber.end;
								}
			}
		}


		/*********************************************************
		 * 3rd traversal is to do the math
		 *********************************************************/
		int total = mainExpression.get(0).type == Type.PRIORITY? priorityExpressions.get(mainExpression.get(0).val).val : mainExpression.get(0).val;
		Expression cur;
		for (int i = 1; i < mainExpression.size() -1; i++)
		{
			cur =  mainExpression.get(i);

			// If next operand is a placeholder to a priority expression 
			// we access the calculated value in the priorityExpressions
			//Else we just add the value as it is
			switch(cur.type)
			{
			case ADDICTION:
				if (mainExpression.get(i+1).type == Type.PRIORITY)
				{
					total += priorityExpressions.get(mainExpression.get(++i).val).val;
				}
				else
				{
					total += mainExpression.get(++i).val;
				}

				break;
			case SUBTRACTION:

				if (mainExpression.get(i+1).type == Type.PRIORITY)
				{
					total -= priorityExpressions.get(mainExpression.get(++i).val).val;
				}
				else
				{
					total -= mainExpression.get(++i).val;
				}
				break;
			default:
				System.out.println("Great Work you got an unexpected situation!");
				break;
			}
		}

		return total;

	}


	private Expression loadDigit(String s, int i)
	{
		//skip non digit numbers        
		while (i < s.length() && !Character.isDigit(s.charAt(i))) 
			i++;

		//process number
		int number = 0;
		boolean end = false;
		Expression exp = new Expression(-1,i,-1);
		while(i < s.length() && Character.isDigit(s.charAt(i)))
		{
			number = number * 10 + s.charAt(i) - '0';
			i++;
			end = true;
		}
		exp.val = number;
		exp.end = i - 1;

		return !end ? null : exp;
	}


	private Expression resolveExpression(String s, int operatorIndex, List<Expression> expressions)
	{        
		//find left operand        
		boolean end = false;
		int left = 0;
		int expressionStart = -1;
		int leftStart = -1;
		int scale = 1;
		for (int l = operatorIndex-1; l >= 0 && !end; l--)
		{
			while(l >= 0 && Character.isDigit(s.charAt(l)))
			{
				left = (s.charAt(l) - '0') * scale + left;
				scale *= 10;
				leftStart = leftStart == -1 ? l : leftStart;
				expressionStart = l;
				l--;
				end = true;
			}
		}

		//find right operand        
		end = false;
		int right = 0;
		int expressionEnd = -1;
		for (int r = operatorIndex + 1; r < s.length()&& !end; r++)
		{
			while(r < s.length() && Character.isDigit(s.charAt(r)))
			{
				right = right * 10 + s.charAt(r) - '0';
				expressionEnd = r;
				r++;
				end = true;
			}
		}

		//Check overlap with previous expression
		// if left operand of current expression overlap right operand of previous priorityExpression 
		// we know that we should get the value of the previous Expression 
		// and used it for our calculation
		Expression previousExpression = 
				expressions.size() > 0 ? expressions.get(expressions.size() -1) : null;

				if (previousExpression != null && 
						leftStart == previousExpression.end)
					left = previousExpression.val;


				//perform calculation
				if (s.charAt(operatorIndex) == '*')
					return new Expression(left*right, expressionStart, expressionEnd);
				else
					return new Expression(left/right, expressionStart, expressionEnd);
	}


	private boolean isMultiplication(String s, int i){
		return s.charAt(i) == '*' || s.charAt(i) == '/';
	}

	private boolean isAddiction(String s, int i){
		return s.charAt(i) == '+' || s.charAt(i) == '-';
	}

	private Expression getSignCode(String s, int index){
		switch(s.charAt(index))
		{
		case '+':
			return new Expression(Type.ADDICTION, index);
		case '-':
			return new Expression(Type.SUBTRACTION, index);
		default:
			return new Expression(Type.PRIORITY, index);
		}
	}

	private Integer tryParseInt(String value) {  
		try {  
			return Integer.parseInt(value); 
		} catch (NumberFormatException e) {  
			return null;  
		}  
	}

	class Expression {
		Type type;
		int val;
		int start;
		int end;

		Expression(int v, int s, int e, Type t){
			val = v;
			start = s;
			end = e;
			type = t;
		}


		Expression(int v, int s, int e){
			val = v;
			start = s;
			end = e;
			type = Type.NUMBER;
		}

		Expression(Type t, int index){

			type = t;
			val = t.ordinal();
			start = index;
			end = index;
		}

		Expression(int index){
			type = Type.PRIORITY;
			val = index;
			start = index;
			end = index;
		}
	}

	enum Type{
		ADDICTION,
		SUBTRACTION,
		NUMBER,
		PRIORITY;
	}
}


/*********************
 * OTHERS SOLUTIONS
 *********************/
/**
 * This solution is one of those fantastic pieces where the problem, 
 * the data structure and the algorithm aligns completly.
 * 
 * what this guy does save the digits in the stack depending on the previously seen signal.
 * 
 * it always starts ith a default + sign so the the first number is added to the stack
 * 
 * 1) load number
 * 2) advance to sign, if previous sign is a sum, add number to stack
 * 3) load next number
 * 4) if previous sign is a sum add previous element to stack
 * 5) if it is a multiplication, multiply previous number with peek of the stack(and pop it), 
 * 		and place it at the top of the stack after
 * 6) repeat
 * 
 * When this processing is done, you just sum all the elements in the stack.
 * 
 * 
 * @score
 *		Runtime: 10 ms, faster than 71.90% of Java online submissions for Basic Calculator II.
 *		Memory Usage: 39.4 MB, less than 88.57% of Java online submissions for Basic Calculator II.
 * 
 * @author Nelson Costa
 *
 */
class BasicCalculatorIIUnofficialSolution1 {
	public int calculate(String s) {
		int len;

		if(s==null || (len = s.length())==0) 
			return 0;

		Stack<Integer> stack = new Stack<Integer>();

		int num = 0;
		char sign = '+';
		for(int i=0;i<len;i++){
			if(Character.isDigit(s.charAt(i))){
				num = num*10+s.charAt(i)-'0';
			}
			if((!Character.isDigit(s.charAt(i)) &&' '!=s.charAt(i)) || i==len-1){
				if(sign=='-'){
					stack.push(-num);
				}
				if(sign=='+'){
					stack.push(num);
				}
				if(sign=='*'){
					stack.push(stack.pop()*num);
				}
				if(sign=='/'){
					stack.push(stack.pop()/num);
				}
				sign = s.charAt(i);
				num = 0;
			}
		}

		int re = 0;
		for(int i:stack){
			re += i;
		}
		return re;
	}
}

/**
 * Stackless Solution
 * 
 * Despite this solution has better performance, I think the previous solution is more didactic.
 * 
 * what the user does here is to use two variables instead of a stack
 * 
 * 
 * tempSum and num generally have the previous and the current operands
 * tempsum can also have the value of multiplications
 * we usually perform operations between elements from the last sign and not the current sign
 * unless we are in the last character of the operation
 * 
 * 1) you get a number
 * 2) you add the number to tempSum, sum still 0
 * 3) you get the next number
 * 4) you get the next sign
 * 5) if last sign is  a sum you add the tempSum var to it (e.g. 3 + 2 * 2, you would add 3 to sum)
 * 6) if it is not a sum you would multiply tmp buy num
 * 7) and that's it
 * 
 * @score
 * 		Runtime: 6 ms, faster than 94.85% of Java online submissions for Basic Calculator II.
 * 		Memory Usage: 40.1 MB, less than 37.86% of Java online submissions for Basic Calculator II.
 * 
 * @author Nelson Costa
 *
 */
class BasicCalculatorIIUnofficialSolution2 {
    public int calculate(String s) {
        int sum = 0;
        int tempSum = 0;
        int num = 0;
        char lastSign = '+';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) num = num * 10 + c - '0';
            if (i == s.length() - 1 || !Character.isDigit(c) && c!=' ') {
                switch(lastSign) {
                    case '+':
                        sum+=tempSum;
                        tempSum = num;
                        break;
                    case '-':
                        sum+=tempSum;
                        tempSum = -num;
                        break;
                    case '*':
                        tempSum *= num;
                        break;
                    case '/':
                        tempSum /= num;
                        break;
                }
                lastSign = c;
                num=0;
            }
        }
        sum+=tempSum;
        return sum;
    }
}