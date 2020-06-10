package com.data.structures.problems;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/generate-parentheses/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class GenerateParentheses {

	static GenerateParentheses g = new GenerateParentheses();

	public static void main(String[] args) {

		int n = 3;
		for (String s : g.generateParenthesis(n))
		{
			System.out.println(s);
		}
	}


	List<String> ans;
	int expectedSize;
	public GenerateParentheses (){
		ans = new ArrayList<String>();
	}

	public List<String> generateParenthesis(int n) {

		if (n == 0)
			return new ArrayList<String>();

		expectedSize = n * 2;

		generate(n, 1, 1, "(");

		return ans;
	}


	/**
	 * 
	 * 
	@intuition
		Initially I thought this could be something like a permutation problem, and I though to solve this using a permutation algo.
		combined with a way of validating if the parentesis count.

		As I progress in the problem I try to see what could be a valid parentesis string and a invalid parentisis.
		and how to come up with all combinations.

		And I finally came up with a solution.

		Permutation
			1) I construct the answer as I go, rather than trying out all combinations

		Validation
			2) I validate parentheses not in the and but also as I go

		Stop conditions
			3) I defined when to stop the generation of parentheses, which is when valid and the size is the expected

		This was a backtracking problem

    @score
        Runtime: 1 ms, faster than 83.78% of Java online submissions for Generate Parentheses.
		Memory Usage: 39.3 MB, less than 90.58% of Java online submissions for Generate Parentheses.

    @fail
        NO FAIL AH AH, NO MISTAKES

	@time	??
    @space 	O(N) N = string size
	 **/
	private void generate(int n, int curCount, int valid, String s)
	{

		if (s.length() == expectedSize && valid == 0)
		{
			ans.add(s);
			return;
		}

		if (valid < 0 || s.length() > expectedSize || curCount > n)
			return;

		generate(n, curCount + 1, valid + 1, s + "(");

		generate(n, curCount, valid - 1, s + ")");
	}
}


/*********************
 * OTHERS SOLUTIONS
 *********************/

/**
 * Same approach than mine be executed  a bit differently
 * 
 * the stop is not made early
 * 
 * @author Nelson Costa
 *
 */
class GenerateParenthesesSolution2 {
	public List<String> generateParenthesis(int n) {
		List<String> ans = new ArrayList();
		backtrack(ans, "", 0, 0, n);
		return ans;
	}

	public void backtrack(List<String> ans, String cur, int open, int close, int max){
		if (cur.length() == max * 2) {
			ans.add(cur);
			return;
		}

		if (open < max)
			backtrack(ans, cur+"(", open+1, close, max);
		if (close < open)
			backtrack(ans, cur+")", open, close+1, max);
	}
}


/**
 * Brute force solution
 * 
 * they perform all possible combinations and validate in the end.
 * this is some how my first thought. but thankfully I was able of think in a abetter solution
 * 
 * @time  O(2^2n)
 * @space O(2^2n)
 * @author Nelson Costa
 *
 */
class GenerateParenthesesSolution1 {
    public List<String> generateParenthesis(int n) {
        List<String> combinations = new ArrayList();
        generateAll(new char[2 * n], 0, combinations);
        return combinations;
    }

    public void generateAll(char[] current, int pos, List<String> result) {
        if (pos == current.length) {
            if (valid(current))
                result.add(new String(current));
        } else {
            current[pos] = '(';
            generateAll(current, pos+1, result);
            current[pos] = ')';
            generateAll(current, pos+1, result);
        }
    }

    public boolean valid(char[] current) {
        int balance = 0;
        for (char c: current) {
            if (c == '(') balance++;
            else balance--;
            if (balance < 0) return false;
        }
        return (balance == 0);
    }
}


/*********************
 * UNOFFICIAL SOLUTIONS
 *********************/
/**
 * Solution with string builder
 * @author Nelson Costa
 *
 */
class GenerateParenthesesUnofficialSolution1 {
	public List<String> generateParenthesis(int n) {
		List<String> res = new ArrayList<>();
		if (n <= 0) {
			return res;
		}
		backtracking(res, new StringBuilder(), n, n);
		return res;
	}
    
	private void backtracking(List<String> res, StringBuilder sb, int left, int right) {
		if (left < 0 || right < 0 || right < left) {
			return;
		}
		if (left == 0 && right == 0) {
			res.add(sb.toString());
			return;
		}
        
        sb.append("(");
		backtracking(res, sb, left - 1, right);
		sb.setLength(sb.length() - 1);
        
        sb.append(")");
        backtracking(res, sb, left, right - 1);
        sb.setLength(sb.length() - 1);
	}
}