package com.data.structures.problems;

import java.util.PriorityQueue;
import java.util.Stack;

/**
 * https://leetcode.com/problems/reorganize-string/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class ReorganizeString {

	static ReorganizeString r = new ReorganizeString();
	public static void main(String[] args) {

		String s = "baaba";
		r.reorganizeString(s);
		ReorganizeStringSolution1 r1 = new ReorganizeStringSolution1();
		r1.reorganizeString("aab");
	}


	 /*********************************
	 * SOLUTION 1
	 ********************************/
	/** 
	 *  @intuition
	 *  	The intuition for this problem is to have two data structures.
	 *  	One the "unfit" and other the "possible".
	 * 		In the unfit data structure we pick everything that can not be added because previous element in the string in the making, is equals to the peek.
	 * 		In the possible data structure we keep every char that is possible to be inserted.
	 * 
	 * 		when we add an element to the string we pass it to the unfit data structure and pass the previous unfit element to the possible data structure.
	 * 
	 * 		that's about it.
	 * 
	 * 		when the possible data structure is empty we stop the iteration.
	 * 
	 * 		after the iteration is stoped, we check if there is elements unfit, if there is and the possible is empty it means we have not allocated all the characters.
	 * 
	 * 		else we have been successful.
	 * 
	 * 		unfit is a stack but could have been just a variable
	 * 		and possible is a maxheap sorted by element count.
	 * 		
	 * 		
	 * 
	 * 
	 * 	@score
	 * 		Runtime: 3 ms, faster than 62.02% of Java online submissions for Reorganize String.
	 *		Memory Usage: 37.9 MB, less than 53.33% of Java online submissions for Reorganize String.
	 * 
	 * 	@fail   
	 * 		1) I was adding characters that had no count
	 * 		2) I add a variable wrong, had possible instead of unfit
	 * 
	 * 
	 * @time
	 * 		O(NLogN)
	 * 
	 * @space
	 * 		O(N) / O(1) because we never have more than 26 characters.
	 **/
	public String reorganizeString(String s) 
	{

		PriorityQueue<Pair> possible = new PriorityQueue<>((a, b) -> Integer.compare(b.n, a.n));

		Stack<Pair> unfit = new Stack<>();

		int [] charCount = new int[26];

		//count each char O(N)
		for (char c : s.toCharArray())
		{
			charCount[c - 'a']++;
		}

		//add to heap
		//O(NLogN)
		for (int i = 0; i< charCount.length; i++)
		{
			if (charCount[i] > 0)
				possible.add(new Pair((char)('a' + i), charCount[i]));
		}

		//create a candidate string
		StringBuilder sb = new StringBuilder(" ");
		Pair tmp, removeAux;
		while (!possible.isEmpty())
		{
			if (sb.charAt(sb.length() - 1) == possible.peek().c)
			{                    
				//check if stack has something
				//put in possible heap
				//push value to stack
				//else
				//push to stack

				if (!unfit.isEmpty())
				{
					tmp = unfit.pop();
					unfit.push(possible.poll());
				}
				else
				{
					unfit.push(possible.poll());
				}
			}
			else
			{
				//check if stack has something
				//put in possible heap
				//push value to stack
				//update String
				//decrement the num of chars
				//verify if num is zero
				//else
				//push to stack
				//update string
				//decrement the num of chars
				//verify if num is zero

				if (!unfit.isEmpty())
				{
					tmp = unfit.pop();
					if (possible.peek().n > 1)
					{
						unfit.push(possible.poll());
						unfit.peek().n--;
						sb.append(unfit.peek().c);
						possible.add(tmp);
					}
					else
					{
						removeAux = possible.poll();
						sb.append(removeAux.c);
						possible.add(tmp);

					}
				}
				else
				{
					if (possible.peek().n > 1)
					{
						unfit.push(possible.poll());
						unfit.peek().n--;
						sb.append(unfit.peek().c);
					}
					else
					{

						removeAux = possible.poll();
						sb.append(removeAux.c);
					}
				}             
			}
		}

		//if stack is not empty and possible is empty it means there is nothing 
		//possible to ad and we still have a character to add, but that char is unfit
		if (!unfit.isEmpty() && possible.isEmpty())
			return "";

		return sb.toString().trim();
	}


	class Pair{
		char c;
		int n;

		Pair (char ch, int num)
		{
			c = ch;
			n = num;
		}
	}
}

/*********************
* OTHERS SOLUTIONS
*********************/
/**
 * @intuition
 * 		This solution does what I initially tought, but have not implemented
 * 		The thing is build the string in pairs of two highest counts and decrease along the away and reinsert in the queue
 * 	
 * 		The stop condition is verified at the early stage, by checking that there is not no number bigger than half the size.
 * 		beautiful...
 * 
 * 
 * @author Nelson Costa
 *
 */
class ReorganizeStringSolution1 {
	public String reorganizeString(String S) {
		int N = S.length();
		int[] count = new int[26];
		for (char c: S.toCharArray()) count[c-'a']++;
		PriorityQueue<MultiChar> pq = new PriorityQueue<MultiChar>((a, b) ->
		a.count == b.count ? a.letter - b.letter : b.count - a.count);

		for (int i = 0; i < 26; ++i) if (count[i] > 0) {
			if (count[i] > (N + 1) / 2) return "";
			pq.add(new MultiChar(count[i], (char) ('a' + i)));
		}

		StringBuilder ans = new StringBuilder();
		while (pq.size() >= 2) {
			MultiChar mc1 = pq.poll();
			MultiChar mc2 = pq.poll();
			ans.append(mc1.letter);
			ans.append(mc2.letter);
			if (--mc1.count > 0) pq.add(mc1);
			if (--mc2.count > 0) pq.add(mc2);
		}

		if (pq.size() > 0) ans.append(pq.poll().letter);
		return ans.toString();
	}
}
class MultiChar {
	int count;
	char letter;
	MultiChar(int ct, char ch) {
		count = ct;
		letter = ch;
	}
}
