package com.data.structures.utils;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

import com.data.structures.problems.ds.TreeNode;

public final class LeetcodeUtils {

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


	/**
	 * Class with methods to deal with TreeNodes
	 * @author Nelson Costa
	 *
	 */
	public static class Tree
	{
		/**
		 * @time	O(N*D) + O(T) N number of nodes, D size of Digits, T Diameter of tree.
		 * 			O(T) is when the first element of the last from is not null and all the rest is null. 
		 * 			you will remove T - 1 nulls.	 
		 * @space	O(N)
		 * 
		 * @param root
		 * @return
		 */
		public static  String treeNodeToString(TreeNode root) {
			//guards
			if (root == null)
			{
				return "[]";
			}

			Queue<Tuple> q = new ArrayDeque<>();
			HashMap<Integer, Integer> levelToValueCount = new HashMap<>();
			q.add(new Tuple(0, root));

			StringBuilder sb = new StringBuilder("[");
			Tuple cur;
			while (!q.isEmpty())
			{
				cur = q.poll(); 

				//check if null
				//if null and level has zero values it means we are in the end of the tree, we should break
				//before break, remove last comma
				//if not in the end we just put null value in the string 
				if(cur.node == null)
				{
					if (levelToValueCount.getOrDefault(cur.level, 0) != 0)
					{
						sb.append("null,");
						continue;
					}
					else if (levelToValueCount.getOrDefault(cur.level, 0) == 0)
					{
						sb.setLength(sb.length() - 1);
						sb.append("]"); //always ends up here
						break;
					}

				}

				//if not null add left and right nodes to queue
				//if children not null increase level down + 1;
				//add node value to the string
				if (cur.node.left != null)
				{
					levelToValueCount.put(cur.level + 1, levelToValueCount.getOrDefault(cur.level + 1, 0) + 1);
				}

				if (cur.node.right != null)
				{
					levelToValueCount.put(cur.level + 1, levelToValueCount.getOrDefault(cur.level + 1, 0) + 1);
				}

				q.add(new Tuple(cur.level + 1, cur.node.left));
				q.add(new Tuple(cur.level + 1, cur.node.right));

				sb.append(cur.node.val + ",");
			}

			//remove last null
			//occurs if the last level ends with nulls;
			String nullEnding = "null]";
			int start = sb.length() - 1 - nullEnding.length();
			int end = sb.length();
			while(start > 0 && sb.substring(start, end).equals(nullEnding))
			{
				sb.setLength(sb.length() - 1 - nullEnding.length());
				sb.append("]");
				start = sb.length() - nullEnding.length();
				end = sb.length();
			}

			//remove eventual comma left
			if(sb.length() - 2 > 0 && sb.charAt(sb.length() - 2) == ',')
				sb.setLength(sb.length() - 1);


			return sb.toString();
		}
		

		/**
		 * 
		 * @time 	O(S) + O(N*D), N each node, S equals to string size, D digit size
		 * @spacd	O(L) + O(N) L is hashmap with levels, N is nodes.
		 * 
		 * @optimizations
		 * 		I believe using String[] would improve performance, but right now i dont want to change the code
		 * 
		 * @param s
		 * @return
		 */
		public static TreeNode stringToTreeNode(String s) {  

			if (s.equals("[]"))
				return null;

			List<String> list = Arrays.asList(s.substring(1, s.length() - 1).split(","));

			//prepare answer
			TreeNode preAns = new TreeNode(-1);
			TreeNode root = new TreeNode(Integer.parseInt(list.get(0)));
			preAns.right = root;

			//set level zero with one element
			HashMap<Integer, Integer> levelToNodesCount = new HashMap<>();
			levelToNodesCount.put(0, 1);

			//prepare queue
			Queue<Tuple> q = new ArrayDeque<>();
			q.add(new Tuple(0, 0, root));

			Tuple cur;
			TreeNode children;            
			int loopLevel, nullsBehind, curPos;

			while (!q.isEmpty())
			{
				loopLevel = q.peek().level;
				nullsBehind = 0;
				curPos = 0;

				while (!q.isEmpty() && q.peek().level == loopLevel)
				{
					cur = q.poll();

					if (cur.node == null)
					{
						nullsBehind++;
						curPos++;
						continue;
					}

					int left = getLeft(cur.index, nullsBehind, curPos, levelToNodesCount.get(cur.level));
					int right = left + 1;

					if (left < list.size() ) 
					{
						if (!list.get(left).equals("null"))
						{
							children = new TreeNode(Integer.parseInt(list.get(left)));
							cur.node.left = children;
							q.add(new Tuple(cur.level + 1, left, cur.node.left)); 
						}
						else
						{
							q.add(new Tuple(cur.level + 1, left, null)); 
						}

						levelToNodesCount.put(cur.level + 1, levelToNodesCount.getOrDefault(cur.level + 1, 0) + 1);											
					}

					if (right < list.size()) 
					{
						if (!list.get(right).equals("null"))
						{
							children = new TreeNode(Integer.parseInt(list.get(right)));
							cur.node.right = children;
							q.add(new Tuple(cur.level + 1, right, cur.node.right)); 
						}
						else
						{
							q.add(new Tuple(cur.level + 1, right, null));
						}
						levelToNodesCount.put(cur.level + 1, levelToNodesCount.getOrDefault(cur.level + 1, 0) + 1);											
					}		

					curPos++;

				}
			}

			return preAns.right;
		}

		/**
		 * Get left node children
		 * @param parent
		 * @param nullsBehind
		 * @param positionInCurrentLevel
		 * @param totalElementsInLevel
		 * @return
		 */
		private static int getLeft(int parent, int nullsBehind, int positionInCurrentLevel, int totalElementsInLevel){

			if(parent == 0)
				return 1;

			//elements in front we need to jump to get to the end of the level
			int elementsInFront = totalElementsInLevel - (positionInCurrentLevel + 1); 
			//elements that we need to jump after reaching the end of the level
			int elementsBehind = positionInCurrentLevel - nullsBehind; 

			//to move to the end of the level we need to jump all elements in front
			//after getting to the last element in the level we need to jump for each non-null element behind 2 positions
			//once null elements will not produce more children
			int leftChildrenPosition = parent + (elementsInFront) + (2 * elementsBehind) + 1;

			return leftChildrenPosition;
		}

		static class Tuple{
			int level;
			int index;
			TreeNode node;

			public Tuple(int l, TreeNode n)
			{
				level = l;
				node = n;
			}
			public Tuple(int l, int i,  TreeNode n)
			{
				level = l;
				index = i;
				node = n;
			}
		}
	}
}
