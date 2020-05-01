package com.data.structures.problems.ds;

import com.data.structures.utils.LeetcodeUtils;
import com.data.structures.utils.PrintHelper;

public abstract class LeetCodeExercise {

	public static int [][] stringToMatrix (String s)
	{
		return LeetcodeUtils.stringToMatrix(s);
	}

	public static int [] stringToArray(String s)
	{
		return LeetcodeUtils.stringToArray(s);
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
}
