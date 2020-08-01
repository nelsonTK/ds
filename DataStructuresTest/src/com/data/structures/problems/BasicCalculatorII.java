package com.data.structures.problems;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/basic-calculator-ii
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class BasicCalculatorII {

	static BasicCalculatorII b = new BasicCalculatorII();
	public static void main(String[] args) {
		String s = "3+2*2";
		s = "2+2+555";
		s = "5-10*2";
		s = "1233*390";
		s = "1233*390*56*68459*0-56*38+324";
		System.out.println(b.calculate(s));
	}

	/**
	 * 
	 * 
	 * @score
	 * 		Runtime: 35 ms, faster than 13.73% of Java online submissions for Basic Calculator II.
			Memory Usage: 51.3 MB, less than 6.08% of Java online submissions for Basic Calculator II.
	 * 
	 * 
	 * @fail 
	 * 		1) index out of bounds, null pointer exception
	 * 		2) fail for some reason I delete part of the iff condition to treat addicition
	 * 		3) scale calculation was wrong...
	 * 		4) calculating the left operant didn't have parentheses
	 * 		5) out of bounds because of left condition for array was reversed
	 * 		6) forgot to add the last number when we are going to some some operands and we load the last operand
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
    	
    	
        //solution with 3 traversals
            //1st traversal is for calculating the priority elements * and /
            //2nd traversal is for calculating the array with the priority elements indexed to a list
            //3rd traversal is to do the math
        
        List<Expression> priorityExpressions = new ArrayList<>();
        
        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            if (c == '*' || c == '/')
            {
                priorityExpressions.add(resolveExpression(s, i, priorityExpressions));
            }
        }
        
        //2nd traversal is for calculating the array with the priority elements indexed to a list
        
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
                    //index --;
                    prev.val = priorityCalculationLookUp++;
                    pendingNumber = loadDigit(s, ++i);
                    i = pendingNumber.end;
                }
            }
        }
        
        //3rd traversal is to do the math
        int total = mainExpression.get(0).type == Type.PRIORITY? 
        		priorityExpressions.get(mainExpression.get(0).val).val : mainExpression.get(0).val;
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
                    System.out.println("Great Work you got an error! NAC");
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
        int scale = 1;
        int number = 0;
        boolean end = false;
        Expression exp = new Expression(-1,i,-1);
        while(i < s.length() && Character.isDigit(s.charAt(i)))
        {
            number = number * 10 + s.charAt(i) - '0';
            //scale *=10;
            //expressionEnd = i;
            i++;
            end = true;
        }
        exp.val = number;
        exp.end = i - 1;
        
        return !end ? null : exp;
    }
    
    //Incomplete need to recursively resolve multiplications
    private Expression resolveExpression(String s, int i, List<Expression> expressions)
    {
        //i is the operator index
        
        //find left operand        
        boolean end = false;
        int left = 0;
        int expressionStart = -1;
        int leftStart = -1;
        int scale = 1;
        for (int l = i-1; l >= 0 && !end; l--)
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
        scale = 1;
        for (int r = i + 1; r < s.length()&& !end; r++)
        {
             while(r < s.length() && Character.isDigit(s.charAt(r)))
             {
                 right = right * 10 + s.charAt(r) - '0';
                 //scale *=10;
                 expressionEnd = r;
                 r++;
                 end = true;
             }
        }
        
        //Check overlap with previous expression
        // if left operand overlap right operand of previous priorityExpression 
        // we know that we should get the value of the previous Expression 
        // and used it for our calculation
        Expression previousExpression = 
            expressions.size() > 0 ? expressions.get(expressions.size() -1) : null;
        
        if (previousExpression != null && 
            leftStart == previousExpression.end)
            left = previousExpression.val;
        
        
        //perform calculation
        //if sign is multiplication, multiply, else divide
        switch(s.charAt(i))
        {
            case '*':
                return new Expression(left*right, expressionStart, expressionEnd);
            default:
                return new Expression(left/right, expressionStart, expressionEnd);
        }
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
