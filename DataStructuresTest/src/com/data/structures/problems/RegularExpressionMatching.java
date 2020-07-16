package com.data.structures.problems;

/**
 * https://leetcode.com/problems/regular-expression-matching
 * HARD
 * @author Nelson Costa
 *
 */
public class RegularExpressionMatching {

	static RegularExpressionMatching r = new RegularExpressionMatching();

	public static void main(String[] args) {
		//		String s = "aaa", p = ".*.";
		//		String s = "aaaaaaaaaaaaaaaab", p = ".*.*";
		//		String s = "ab", p = ".*c";
		//String s = "a", p = "ab*";
		//String s = "aaa", p = "aaaa";
		//String s = "a", p = ".*";

		//		String s = "aaaaaaaaaaaaab", p=
		//				"a*a*a*a*a*a*a*a*a*a*c";
		String s = "abbabaaaaaaacaa", p="a*.*b.a.*c*b*a*c*";



		System.out.println(r.isMatch(s, p));
	}

	/*********************************
	 * SOLUTION 1
	 ********************************/
	/**

	@intuition
		the intuition here was to greedly solve the string until process all string characters and mark them as true.
		at the same time I should mark as true elements in the array of the expression.

		if I process the string array before all elements in the expression being mark 
		true I have to clean the leftover of the expression against an empty string.

		this was greedy and wrong and caused by a miss interpretation of the expression symbols



	@comments
		Works for many cases but, I think the design was fundamentally wrong.
		I designed a Greedy solution, should have think in a gready solution.
		But this mistake was created because of a miss interpretation of how star should behave.
		But still I can see many good things in this solution.

    @fail
        1) 
        2) did not treat the case where the last element is dot, after ".*"
		3) arrayoutofbounds, wrong guard I hadd <= length
        4) fundamental erro in interpretation.. * works alone.. I think
    @time
        O(M + N)
    @space
        O()
        aaabaa
        .*.*.*b*.*.*
	 **/
	boolean [] dpe;
	boolean [] dp;
	public boolean isMatch0(String s, String p) {

		//no expression and inputstring filled is never match
		if(!(s == null || s.length() == 0) && (p == null || p.length() == 0)) 
			return false;

		// empty empty is always match  
		if((s == null || s.length() == 0) && (p == null || p.length() == 0)) 
			return true;


		int in = 0;
		int ex = 0;  
		dp = new boolean[s.length() == 0? 1 : s.length()]; //dp of string
		dpe = new boolean[p.length() == 0? 1 : p.length()]; //dp of pattern expression
		char ci;
		char ce;
		while (in < s.length() && ex < p.length())
		{
			ci = s.charAt(in);
			ce = p.charAt(ex);
			//same letter or we have a dot
			if (ci == ce || ce == '.')
			{

				dp[in] = true;
				dpe[ex] = true;
				in++;
				ex++;

			}
			//if different and char in expression is *, check previous element, if equals or point
			else if (ci != ce && ce == '*')
			{
				// if previous is same char or is a dot & prev from string also is equals to s char we progress
				if(p.charAt(ex - 1) == ci || p.charAt(ex - 1) == '.' )
				{
					dp[in] = true;
					in++; 
				}
				else
				{
					dpe[ex] = true;
					ex++;
				}
			}
			//if letters are different but next element is *
			else if (ci != ce)
			{
				if (ex + 1 < p.length() && p.charAt(ex + 1) == '*')
				{
					dpe[ex] = true;
					dpe[ex + 1] = true;
					ex+=2;
				}
				else
				{
					return false;
				}
			}
		}

		//deal with leftover
		//if exausted expression we can retrieve value
		//if expression not exausted we need to exausted it


		while(ex < p.length() && in >= s.length())
		{
			if(ex + 1 < p.length() && isLetterOrDot(p,ex + 1) && p.charAt(ex) == '*' && ex + 1== p.length() - 1)
			{
				if(Character.isLetter(p.charAt(ex + 1)) && p.charAt(ex + 1) == s.charAt(s.length() - 1))
				{

					dpe[ex+1] = true;
					dpe[ex] = true;
					ex += 2; 
				}
				else
					if(p.charAt(ex + 1) == '.')
					{
						dpe[ex+1] = true;
						dpe[ex] = true;
						ex += 2; 
					}
					else
						return false;
			}
			else
				if(ex + 1 < p.length() && Character.isLetter(p.charAt(ex)) && p.charAt(ex + 1) == '*')
				{
					dpe[ex+1] = true;
					dpe[ex] = true;
					ex += 2; 
				}

				else if(p != null && isLetterOrDot(p,ex - 1)
						&&  p.charAt(ex) == '*')
				{
					dpe[ex] = true;
					dpe[ex - 1] = true;
					ex += 2;
				}
				else
				{
					return false;
				}
		}

		return dp[dp.length - 1] && dpe[dpe.length - 1];
	}

	private boolean isLetterOrDot(String s, int index){
		return Character.isLetter(s.charAt(index)) || s.charAt(index) == '.';
	}

	/*********************************
	 * SOLUTION 2
	 ********************************/
	/**
	 * [FAILED]
	 * 
	 * @intuition
	 * 
	 * 		DP Solution
	 * 			If elements are equal the result is what comes from behind. 
	 * 
	 * 				(if it is true it continue to be true, if false it will be false)
	 * 			
	 * 			if we find a '*' in the expression either we ignore it or we repeat the character, 
	 * 			repeating the charater means to get the previous value stored in '*', in other words get the value up in the dp matrix
	 * 
	 * 			Each cell means the value of the expression until there.
	 * 
	 * 
	 * reminds me edit distance problem
	 * @param s
	 * @param p
	 * @return
	 *//**
@fail
    array out of bounds
    bad initial dp value
    issue with for loop condition

	  **/
	public boolean isMatch1(String s, String p) {

		//guards

		boolean [][] dp = new boolean[s.length() + 1][p.length() + 1];
		dp[0][0] = true;

		//edge case when [a-z.]* we need to put the string value to true also, I believe
		for (int i = 0; i < s.length(); i ++)
		{
			if(1 < p.length() && p.charAt(1) == '*' && (s.charAt(i) == p.charAt(0) || p.charAt(0) == '.'))
			{ 
				dp[i + 1][0] = true;
				System.out.println("hey");
			}
		}

		for (int row = 1; row < dp.length; row++) 
		{
			for(int col = 1; col < dp[0].length; col++) 
			{

				if (s.charAt(row - 1) == p.charAt(col- 1) || p.charAt(col- 1) == '.')
				{
					dp[row][col] = dp[row-1][col-1];
				}
				else if (p.charAt(col - 1) == '*')
				{
					dp[row][col] = dp[row][col-2] || dp[row - 1][col];
				}
				else
				{
					dp[row][col] = false;
				}
			}
		}
		return dp[s.length()][p.length()];
	}


	/*********************************
	 * SOLUTION 3
	 ********************************/
	/**
	 * 
	 * [TIME EXCEEDED]
	 * 
	 * Not completly corrent
	 * 
	 * @intuition
	 * 	Recursive solution with backtracking
	 * 	for each * skip or try.
	 *             base case -> 
     *               if wordIndex overflow and expression at the end or overflow (true)
     *   	       	 if one is out of the array and the other not in the end false
     *               if wordIndex out and expression below end false
     *               if expression overflow and wordIndex not false
     *           
     *               if next element is star skip(2) or check for letter by letter match
     *               
     *               if current element is * advance one letter if true or advance one letter of the expression
	 * 	I believe it works
	 * 
	 * @fail
	 *	1) I think it was a change caused by a last minute change in the algo where I too some of the recursive guards
	 *	2) base case incomplete for my fault

	 **/
	public boolean isMatch(String s, String p) {        

		if (s == null && p == null)
			return true;

		if (p == null)
			return false;

		return match(s, p, 0, 0);

	}

	private boolean match(String s, String p, int sIndex, int pIndex){

		//if we exaust both expression and word
		if (sIndex >= s.length() && pIndex >= p.length())
		{
			return true;
		}


		if (sIndex >= s.length() && pIndex == p.length() - 1 && p.charAt(pIndex) == '*')
		{
			return true;
		}

		//if we exaust word before pattern
		if (s.length() > 0 && sIndex >= s.length() && pIndex <= p.length() - 1)
		{
			if (pIndex + 1 < p.length() && p.charAt(pIndex + 1) != '*')
				return false;

			if (pIndex ==  p.length() - 1)
				return false;
		}

		//if we exaust expression before word
		if (sIndex < s.length() && pIndex >=p.length())
		{
			return false;
		}

		boolean preMatch = sIndex < s.length() && s.length() > 0 && charMatch(s.charAt(sIndex),  p.charAt(pIndex));

		if (pIndex + 1 < p.length() && p.charAt(pIndex + 1) == '*' &&                                                   (match(s, p, sIndex, pIndex+2) || preMatch && match(s,p,sIndex+1,pIndex+1) ))
		{
			return true;
		}
		// If we are at a '*' we test move forward the expression, 
		// and move forward the word, if possible
		else if (p.charAt(pIndex)=='*' && (match(s,p,sIndex,pIndex +1) || 
				charMatch(s.charAt(sIndex),  p.charAt(pIndex - 1)) && 
				match (s,p,sIndex+1,pIndex)))
		{
			return true;
		}
		else if(preMatch && match(s,p,sIndex + 1,pIndex +1) )
		{
			return true;
		}

		return false;
	}


	private boolean charMatch(char wordChar, char patternChar){
		return (wordChar == patternChar || patternChar == '.');
	}
}

/*********************
 * OTHERS SOLUTIONS
 *********************/

/**
 * How the author solve this was, basically to try a backtracking solution, Which was what I've missed.
 * I decided to go greedy and failed at the first attempt.
 * 
 * 
 * So 1) is a backtracking solution where the author tries both ignoring ".*" and counting with ".*"
 * Very elegant.
 * 
 * To have this job more efficient he could have used two pointers
 * 
 * @author Nelson Costa
 *
 */
class RegularExpressionMatchingSolution1 {
	public boolean isMatch(String text, String pattern) {
		if (pattern.isEmpty()) 
			return text.isEmpty();

		boolean first_match = (!text.isEmpty() &&
				(pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

		if (pattern.length() >= 2 && pattern.charAt(1) == '*')
		{
			return (isMatch(text, pattern.substring(2)) ||
					(first_match && isMatch(text.substring(1), pattern)));
		} 
		else 
		{
			return first_match && isMatch(text.substring(1), pattern.substring(1));
		}
	}
}


/**
 * 
 * @intuition
 * 		DP Solution
 * 
 * 		EXPLAINING .*
	  		When finding * we move up or 2 places left because we are trying:
	 		1) remove the expression .* or b* and test if it works
	  		2) test if the previous result of * was true.

	 		because it can either be from xa* -> x or xa(a)

	  		so we test [i - 1; j] or [i, j-2]
 * 
 * 
 * 	@resource
 * 		https://www.youtube.com/watch?v=l3hda49XcDE (Good Explanation	)
 **/