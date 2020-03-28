package com.data.structures.problems;

import java.util.Arrays;
import java.util.Stack;

/**
 * https://leetcode.com/problems/verify-preorder-sequence-in-binary-search-tree/
 * MEDIUM
 * PREMIUM
 * 2020/03/19
 * @author Nelson Costa
 *
 */
public class VerifyPreorderSequenceInBinarySearchTree {

	public static void main(String[] args) {

		VerifyPreorderSequenceInBinarySearchTree v= new VerifyPreorderSequenceInBinarySearchTree();

		//		int [] array = new int[]{5,2,6,1,3}; //false
		//int [] array = new int[]{5,2,1,3,6}; //true
		int [] array = new int[]{5,2,3,1,6}; 
		//		int [] array = new int[]{5,2,1,0,3,6,7}; //true		
		//		int [] array = new int[]{5,2,1,0,3,7,9,6}; //false
		//int [] array = new int[]{5}; //false
		System.out.println(Arrays.toString(array));

		System.out.println(v.verifyPreorder2ndBest(array));

	}

	/**@explanation
	 * 
	 * 	Runtime: 437 ms, faster than 6.91% of Java online submissions for Verify Preorder Sequence in Binary Search Tree.
		Memory Usage: 41.7 MB, less than 5.55% of Java online submissions for Verify Preorder Sequence in Binary Search Tree.
	 * 
	 * found out this problem has no solution on leetcode ahahah. No wonder there is no explanation on youtube... but it is very dificult to understand
	 * 
	 * 	The pattern is:
	 * 
	 * 	[Root]		[<Root][<Root][<Root][<Root]		[>Root][>Root][>Root][>Root]
	 *  			
	 *  			[node][<node][<node][>node]			[node][<node][<node][>node]
	 *  
	 *  In other word in order for preorder to be fullfield the rule is:
	 *  parent node preceeds both smaller and bigger numbers, then all nodes next are smaller until you find a larger node,
	 *  from there all the nodes are larger.
	 *  And this rule is applied recursively.
	 *  if this rule is not respected then its not preorder
	 *  
	 *  This guy implemented a cool solution with the stack, better than mine, but I believe that its not space constant:
	 *  	https://linlaw0229.github.io/2018/08/01/255-Verify-Preorder-Sequence-in-Binary-Search-Tree/
	 *  
	 *  	According to my analysis, his algorithm time complexity is O(N) + O(N - 1) but I need supervision not 100% sure.
	 *  	And space complexity is O (N) the stack get all stacked up size N, if preorder array is in decending order
	 *  
	 *  	This is the analysis of the for loop and the while loop, it loops the for loop can not for worst than O(N -1)
	 *  	In this process it was fun to think in the worst case scenario for both the for and the while loop
	 * 
	 * @time  O(N^2) -> fit [(1, 0), (2, 1), (3, 3), (4, 6), (5, 10), (6,15)] (bad performancce)
	 * @space O(1) -> uma variável apenas
	 * @param array
	 * @return
	 */
	public boolean verifyPreorder(int[] array) {

		if (array == null)
			return true;

		int j = 0;

		for (int i = 0; i < array.length - 1; i++) {

			j = i + 1;

			while (j < array.length && array[i] > array[j] )
			{
				j++;
			}

			while (j < array.length && array[i] < array[j] )
			{
				j++;
			}

			while (j < array.length && array[i] > array[j])
			{
				return false;
			}
		}

		return true;
	}

	/**
	 * https://www.cnblogs.com/Dylan-Java-NYC/p/5327638.html
	 * @author Dylan-Java-NYC
	 *
	 */

	public boolean verifyPreorderBest(int[] preorder) {
		int index = -1;
		int low = Integer.MIN_VALUE;
		for(int num : preorder){
			if(num < low){
				return false;
			}
			//previous less than current, update low with previous and reduce index
			//with some interpretation the goal here is to find the father
			//once you find the father (preorder[index] > num)
			//means any previous element less than num. we cannot found a smaller number after num. 
			//example 5 2 3 1. 
			//thats why the previous is save in low and you compare with num, is the stop condition
			//so if you find a number smaller than low the preorder is false
			// 5 2 3 1
			// this would mean
			//   5
			
			//2    3
			
			//1
			
			//in pre order you have the node, all its children below, and than all its children bigger.
			//in the above example this was not possible (5 4 3 1) 1 is printed after a right child it's wrong
			// this question required you to really understand the trees
			//very tough question, I was able of solve it alone but the struggle was to understand the pattern, and understand the stop condition
			while(index >= 0 && preorder[index] < num){
				//low is now the current
				low = preorder[index--];
			}
			preorder[++index] = num;
		}
		return true;
	}

	public boolean verifyPreorder2ndBest(int[] preorder) {

		//Method 1
		int low = Integer.MIN_VALUE;
		Stack<Integer> stk = new Stack<Integer>();
		for(int num : preorder){
			if(num < low){
				return false;             }
			while(!stk.isEmpty() && stk.peek() < num){
				low = stk.pop();
			}
			stk.push(num);
		}
		return true;
	}
}
