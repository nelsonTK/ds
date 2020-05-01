package com.data.structures.utils;

import java.util.Arrays;
import java.util.List;

public class PrintHelper {

	public static void printHeader() {
		printHeader("");
		System.out.println();
	}
	public static void printHeader(String text) {
		System.out.println("_____________ "+ text +" ______________");
	}
	public static void printsubHeader(String text) {
		System.out.println("_____________ "+ text +" ______________");
	}

	public static String getBreadcrumb(String text) {

		return " > " + text;
	}

	
	public static void printArray(int [] array) {
		if (array != null) {
			System.out.println(Arrays.toString(array));
		}else
		{
			System.out.println("[Empty]");
		}
	}
	
	public static void printArray(Object [] array) {
		if (array != null) {
			System.out.println(Arrays.toString(array));
		}else
		{
			System.out.println("[Empty]");
		}
	}

	public static void printArray(int [] array, int from, int to) {
		String result = "";

		if(array == null || array.length == 0)
		{
			result = "[Empty]";
		}
		else if(from < 0 || to < 0)
		{
			result = "[Empty] (computed)";
		}
		else if (from > to) 
		{
			result = "[ILLEGAL BOUNDARIES] from " + from + " : to " + to;
		}
		else 
		{
			for (int i = from; i<= to; i++)
			{
				result += "["+array[i]+"]";
			}
		}
		System.out.println(result);
	}
	

	public static void print2DArray(int [][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.println(matrix[i][j]);
			}
		}
	}
	
	public static void printMatrix(int [][] m)
	{
		for (int i = 0; i < m.length; i++)
		{
			System.out.println(Arrays.toString(m[i]));
		}
	}
}
