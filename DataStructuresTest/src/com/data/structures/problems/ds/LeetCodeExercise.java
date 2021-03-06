package com.data.structures.problems.ds;

import java.util.List;

import com.data.structures.utils.LeetcodeUtils;
import com.data.structures.utils.PrintHelper;

public abstract class LeetCodeExercise {

	/**
	 * Override to benchmark your code and evaluate different solutions, and how different timecomplexities behave
	 */
	public void benchMark() {
	}
	
	public static int [][] stringToMatrix (String s)
	{
		return LeetcodeUtils.stringToMatrix(s);
	}

	public static int [] stringToArray(String s)
	{
		return LeetcodeUtils.stringToArray(s);
	}
	
	public static List<List<Integer>> stringToListOfLists(String s)
	{
		return LeetcodeUtils.stringToListOfLists(s);
	}

	/**
	 * String to TreeNode
	 * @param s
	 * @return
	 */
	public static TreeNode stringToTreeNode(String s) {
		return LeetcodeUtils.Tree.stringToTreeNode(s);
	}
	
	public static void printArray(int [] array) {
		PrintHelper.printArray(array);
	}

	public static void printArray(Object [] array) {
		PrintHelper.printArray(array);
	}

	public static void printMatrix(int [][] m) {
		PrintHelper.printMatrix(m);
	}

	public static void printListNode(ListNode l) {
		while (l != null)
		{
			System.out.print(l.val);

			if (l.next != null)
			{
				System.out.print(" -> ");
			}
			
			l = l.next;
		}
		
		System.out.println();
	}	
}
