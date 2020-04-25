package com.data.structures.utils;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class LeetcodeUtils {

	public static void main(String[] args) {
		int [] a = LeetcodeUtils.stringToArray("[10,2]");
		a = LeetcodeUtils.stringToArray("[3,30,34,5,9]");
		a = LeetcodeUtils.stringToArray("[9,6,4,2,3,5,7,0,1]");
		a = LeetcodeUtils.stringToArray("[3,0,1]");
		a = LeetcodeUtils.stringToArray("[-1,-1]");

		int [][] b = LeetcodeUtils.stringToMatrix("   [\r\n" + 
				"  [1,   3,  5,  7],\r\n" + 
				"  [10, 11, 16, 20],\r\n" + 
				"  [23, 30, 34, 50]\r\n" + 
				"]");

		System.out.println("a");
		System.out.println(Arrays.toString(a));

		System.out.println("b");
		for (int i = 0; i < b.length; i++) {
			System.out.println(Arrays.toString(b[i]));
		}
	}

	/**
	 * given a string returns a Matrix
	 * @param s
	 * @return
	 */
	public static int[][] stringToMatrix(String s)
	{
		int [][] array = (int [][]) parseArray(s);

		return array;
	}

	/**
	 * given a string returns an array
	 * @param s
	 * @return
	 */
	public static int[] stringToArray(String s)
	{
		int [] array = (int []) parseArray(s);

		return array;
	}

	private static Object parseArray(String s)
	{
		if (s == null || s.length() == 0)
			return null;

		return getInnerArray(s.trim(), 1)[0];
	}

	/**
	 * @intuition
	 * 		returns 2d array or 1d array
	 * 		depending on the depth of the array that is evaluated based on 
	 * 		the base case which is zero (the content of a 1d array) and from there the depth is increased
	 * 		
	 * @time  O(N), each char is processed only once
	 * @space O(N)
	 * @param s
	 * @param startIndex
	 * @return
	 */
	private static Object[] getInnerArray(String s, int startIndex) {

		int array = 0;
		int nextIndex = 1;
		int innerDepth = 2;
		int index = startIndex;
		Queue<Object> q = new ArrayDeque<Object>();
		int curDepth = 0;
		Object [] innerAns;
		while (index < s.length() && s.charAt(index) != ']')
		{
			if(s.charAt(index) == '[')
			{
				innerAns =  getInnerArray(s, index + 1);
				index = (int) innerAns[nextIndex];
				curDepth = (int) innerAns[innerDepth];
				q.add(innerAns[array]);
			}
			//positive and negative numbers (base case)
			else if (s.charAt(index) == '-' ||Character.isDigit(s.charAt(index)))
			{
				innerAns = parseArrayContent(s, index);	
				index = (int) innerAns[nextIndex]; //not needed
				curDepth = (int) innerAns[innerDepth];
				q.add(innerAns[array]);
				break; 
				
			} else {
				// spaces, comma
				index++;
			}
		}


		//content of 1D Array
		if (curDepth == 0 && !q.isEmpty())
		{
			return new Object[] {q.poll(), index, curDepth + 1};
		} 

		//content of 2D arrays
		else if  (curDepth == 1 && !q.isEmpty())
		{
			int [][] result = new int [q.size()][((int [])q.peek()).length];

			for (int i = 0; i < result.length; i++) {
				result[i] = (int[]) q.poll();
			}

			return new Object [] {result, index, curDepth + 1};
		}
		//2D array
		else if  (curDepth == 2 && !q.isEmpty())
		{		
			return new Object[] {q.poll(), index, curDepth + 1};
		}
		//if array
		else if (q.isEmpty())
		{
			return new Object[] {new int[] {}, index, curDepth}; 
		}
		//3D+ Arrays not supported
		else
		{
			throw new IllegalArgumentException(" >>>>  Depth not supported");
		}

	}

	/**
	 * parse Array of String to Array of integers
	 * 
	 * @time 	O(N + N + N*C) => O(NC) C is the length of the integers
	 * @space 	O(2N)
	 * 
	 * @param s
	 * @param start
	 * @return
	 */
	private static Object [] parseArrayContent(String s, int start)
	{
		int index = start;

		while (index < s.length() && s.charAt(index) != ']')
		{
			index++;
		}

		//String to int
		String [] sElements = s.substring(start, index).split(",");
		int [] ints = new int[sElements.length];

		for (int i = 0; i < ints.length; i++) {
			ints[i] = Integer.parseInt(sElements[i].trim());
		}

		return new Object[] {ints, index + 1, 0};
	}

}
