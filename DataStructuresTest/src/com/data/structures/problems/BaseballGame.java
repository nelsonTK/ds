package com.data.structures.problems;

import java.util.Stack;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/baseball-game/
 * EASY
 * @author Nelson Costa
 *
 */
public class BaseballGame extends LeetCodeExercise {

	public static void main(String[] args) {
	}

	/**
	 * @intuition
	 * 		I used stack to solve this problem.
	 * 		I always maintained numbers in the stack and after going thorugh all the array I just sum up the values in the stack and return a total
	 * 
	 * 		I use a lot of code for my solution because Im protecting against empty stack.
	 * 
	 * 		It's a Greedy Solution
	 * 	
	 * 	@score
	 * 		Runtime: 2 ms, faster than 95.52% of Java online submissions for Baseball Game.
	 *		Memory Usage: 38.8 MB, less than 65.04% of Java online submissions for Baseball Game.		
	 * 
	 * 	@time
	 * 		O(N)
	 * 
	 * 	@space
	 * 		O(N)
	 * 
	 * @param ops
	 * @return
	 */
	public int calPoints(String[] ops) {

		if (ops == null || ops.length == 0)
			return 0;

		Stack<Integer> stack = new Stack<>();

		//guards empty string

		for (String s : ops)
		{
			if(s.equals("+"))
			{
				int first;
				int sum;
				if (!stack.isEmpty())
				{
					first = stack.pop();
					sum = first;
					if (!stack.isEmpty())
					{
						sum += stack.peek(); 
					}

					stack.push(first);
					stack.push(sum);

				} 
			}
			else if(s.equals("C"))
			{
				if (!stack.isEmpty())
				{
					stack.pop();
				}
			}
			else if(s.equals("D"))
			{

				if (!stack.isEmpty())
				{
					stack.push(stack.peek() * 2);
				}
			}
			else
			{
				int num = getInt(s);
				stack.push(num);
			}
		}


		int total = 0;

		while (!stack.isEmpty())
		{
			total += stack.pop();
		}

		return total;
	}


	private int getInt(String s)
	{
		int signal = s.charAt(0) == '-'? -1: 1;

		int integer = 0;
		int start = signal > 0 ? 0 : 1;
		for (int i = start; i < s.length(); i++)
		{
			integer = integer * 10 + s.charAt(i) - '0';
		}

		return signal * integer;
	}	
}


/*********************
* OTHERS SOLUTIONS
*********************/
/**
 * Same principle than my solution but with no guards and by using valueOf.
 * @author Nelson Costa
 *
 */
class BaseballGameSolution1 {
    public int calPoints(String[] ops) {
        Stack<Integer> stack = new Stack();

        for(String op : ops) {
            if (op.equals("+")) {
                int top = stack.pop();
                int newtop = top + stack.peek();
                stack.push(top);
                stack.push(newtop);
            } else if (op.equals("C")) {
                stack.pop();
            } else if (op.equals("D")) {
                stack.push(2 * stack.peek());
            } else {
                stack.push(Integer.valueOf(op));
            }
        }

        int ans = 0;
        for(int score : stack) ans += score;
        return ans;
    }
}
