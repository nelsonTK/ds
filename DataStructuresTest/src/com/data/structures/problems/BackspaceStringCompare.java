package com.data.structures.problems;

import java.util.Stack;

/**
 * https://leetcode.com/problems/backspace-string-compare
 * EASY
 * @author Nelson Costa
 *
 */
public class BackspaceStringCompare {

	/*********************************
	 * SOLUTION 1 (WITH DUPLICATED CODE DONE IN A RUSH, keep scrolling)
	 ********************************/
	/**
	 * @intuition
	 * 		intuition in the second solution
	 * 
	 * @score
	 * 		Runtime: 0 ms, faster than 100.00% of Java online submissions for Backspace String Compare.
	 * 		Memory Usage: 37.3 MB, less than 49.78% of Java online submissions for Backspace String Compare.
	 * 
	 * @param S
	 * @param T
	 * @return
	 */
	public boolean backspaceCompare0(String S, String T) {

		int a = S.length() - 1;
		int b = T.length() - 1;
		while (a >= 0 &&  b >= 0)
		{
			int toDeleteA = 0;
			//find A valid
			while (a >= 0 && (toDeleteA != 0 || S.charAt(a) == '#') )
			{
				if( S.charAt(a) == '#')
				{
					toDeleteA++;
				}
				else if(toDeleteA > 0)
				{
					toDeleteA--;
				}
				a--;
			}

			//find B
			int toDeleteB = 0;
			while (b >= 0 && (toDeleteB != 0 || T.charAt(b) == '#'))
			{

				if( T.charAt(b) == '#')
				{
					toDeleteB++;
				}
				else if(toDeleteB > 0)
				{
					toDeleteB--;
				}                
				b--;
			}

			if (a >= 0 && b >= 0 && T.charAt(b) != S.charAt(a))
			{

				return false;
			}
			else if (a >= 0 && b < 0 || a < 0 && b >= 0) //if one of the indexes is finished then we are done
			{
				return false;
			}

			if(a >= 0)
				a--;
			if(b >= 0)
				b--;
		}

		int toDeleteA = 0;
		//find A valid
		while (a >= 0 && (toDeleteA != 0 || S.charAt(a) == '#') )
		{
			if( S.charAt(a) == '#')
			{
				toDeleteA++;
			}
			else if(toDeleteA > 0)
			{
				toDeleteA--;
			}
			a--;
		}

		//find B
		int toDeleteB = 0;
		while (b >= 0 && (toDeleteB != 0 || T.charAt(b) == '#'))
		{

			if( T.charAt(b) == '#')
			{
				toDeleteB++;
			}
			else if(toDeleteB > 0)
			{
				toDeleteB--;
			}                
			b--;
		}

		if (a >= 0 && b >= 0 && T.charAt(b) != S.charAt(a))
		{

			return false;
		}
		else if (a >= 0 && b < 0 || a < 0 && b >= 0) //if one of the indexes is finished then we are done
		{
			return false;
		}



		return true;

	}


	/*********************************
	 * SOLUTION 2
	 ********************************/
	class Solution2{
		/**
		 * @intuition
		 * 		For me this is a fake easy question. If it is to do it in O(N) time O(1) space.
		 * 		My intuition is to do a Two pointer solution. where the words are invalid when two characters are different or when one word is exhausted before the other.
		 * 		
		 * 		1) we start from right to left
		 * 		2) when we find a backspace we accomulate 1
		 * 		3) while we have accomulated values we keep moving forward and discounting those values, and we stop when there is nothing more to discount
		 * 		4) the returned index is a index with a valid letter or a out of bounds
		 * 		5) we do this process for both words
		 * 		6) compare in the end
		 * 		7) the edge case is when one word is exhausted and the other is not but after processing backspaces it will also be exhausted.
		 * 		8) so after exhausting one of the words we run the process again one last time.
		 * 
		 * 
		 * @alternatives
		 * 		I believe we could also do a O(N) time O(N) space with two stacks one for each word.
		 * 		the stack world contain the final word after applying the backspaces.
		 * 
		 * 		
		 * @score
		 * 		Runtime: 0 ms, faster than 100.00% of Java online submissions for Backspace String Compare.
		 *		Memory Usage: 37.3 MB, less than 49.78% of Java online submissions for Backspace String Compare.
		 *
		 * @time 
		 * 		O(N)
		 * 
		 * @space
		 * 		O(N)
		 * 
		 * @param S
		 * @param T
		 * @return
		 */
		public boolean backspaceCompare(String S, String T) {

			int a = S.length() - 1;
			int b = T.length() - 1;
			while (a >= 0 &&  b >= 0)
			{
				//find next A
				a = calculateNextIndex(a, S);

				//find next B
				b = calculateNextIndex(b, T);

				//check if valid
				if (!valid(S, T, a,  b))
					return false;

				a--;
				b--;
			}

			//find next A 
			a = calculateNextIndex(a, S);

			//find next B
			b = calculateNextIndex(b, T);

			//check if valid
			if (!valid(S, T, a,  b))
				return false;

			return true;
		}

		private int calculateNextIndex(int initIndex, String s) {

			int toDelete = 0;
			while (initIndex >= 0 && (toDelete != 0 || s.charAt(initIndex) == '#'))
			{
				if( s.charAt(initIndex) == '#')
				{
					toDelete++;
				}
				else if(toDelete > 0)
				{
					toDelete--;
				}                
				initIndex--;
			}

			return initIndex;
		}

		private boolean valid(String S, String T, int a, int b)
		{

			if (a >= 0 && b >= 0 && T.charAt(b) != S.charAt(a) ||
					a >= 0 && b <  0 || 
					a <  0 && b >= 0)
			{
				return false;
			}

			return true;
		}
	}


	/*********************************
	 * SOLUTION 3 (SMALL IMPROVEMENT)
	 ********************************/
	/**
	 * 
	 * @intuition
	 * 		This is a small improvement of my solution2 where I remove the calculus of the remaining part, by changing the compasition in the main while
	 * 		it is a very subtle difference but works very well. Inspired by Leetcode solution
	 * 
	 * @param S
	 * @param T
	 * @return
	 */
	public boolean backspaceCompare(String S, String T) {

		int a = S.length() - 1;
		int b = T.length() - 1;
		while (a >= 0 ||  b >= 0)
		{
			//find A
			a = calculateNextIndex(a, S);

			//find B
			b = calculateNextIndex(b, T);

			if (!valid(S, T, a,  b))
				return false;

			a--;
			b--;
		}
		return true;
	}

	private int calculateNextIndex(int initIndex, String s) {
		int toDelete = 0;
		while (initIndex >= 0 && (toDelete != 0 || s.charAt(initIndex) == '#'))
		{

			if( s.charAt(initIndex) == '#')
			{
				toDelete++;
			}
			else if(toDelete > 0)
			{
				toDelete--;
			}                
			initIndex--;
		}
		return initIndex;
	}

	private boolean valid(String S, String T, int a, int b)
	{
		if (a >= 0 && b >= 0 && T.charAt(b) != S.charAt(a) ||
				a >= 0 && b <  0 || 
				a <  0 && b >= 0)
		{
			return false;
		}

		return true;
	}
}


/*********************
* OTHERS SOLUTIONS
*********************/
/**
 * Just Beautiful, Stack solution 
 * 
 * @time 
 * 		O(N) 
 * @space
 * 	 	O(N)
 * 
 * @author Nelson Costa
 *
 */
class BackspaceStringCompareSolution1 {
    public boolean backspaceCompare(String S, String T) {
        return build(S).equals(build(T));
    }

    public String build(String S) {
        Stack<Character> ans = new Stack<>();
        for (char c: S.toCharArray()) {
            if (c != '#')
                ans.push(c);
            else if (!ans.empty())
                ans.pop();
        }
        return String.valueOf(ans);
    }
}

/**
 * Equivalent to my solution
 * 
 * @time 
 * 		O(N)
 * 
 * @space
 * 		O(1)
 * 
 * @author Nelson Costa
 *
 */
class BackspaceStringCompareSolution2 {
    public boolean backspaceCompare(String S, String T) {
        int i = S.length() - 1, j = T.length() - 1;
        int skipS = 0, skipT = 0;

        while (i >= 0 || j >= 0) { // While there may be chars in build(S) or build (T)
            while (i >= 0) { // Find position of next possible char in build(S)
                if (S.charAt(i) == '#') {skipS++; i--;}
                else if (skipS > 0) {skipS--; i--;}
                else break;
            }
            while (j >= 0) { // Find position of next possible char in build(T)
                if (T.charAt(j) == '#') {skipT++; j--;}
                else if (skipT > 0) {skipT--; j--;}
                else break;
            }
            // If two actual characters are different
            if (i >= 0 && j >= 0 && S.charAt(i) != T.charAt(j))
                return false;
            // If expecting to compare char vs nothing
            if ((i >= 0) != (j >= 0))
                return false;
            i--; j--;
        }
        return true;
    }
}
