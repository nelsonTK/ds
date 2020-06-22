package com.data.structures.problems;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.data.structures.problems.ds.TreeNode;

public class SerializeandDeserializeBinaryTree {

	static SerializeandDeserializeBinaryTree s = new SerializeandDeserializeBinaryTree();
	static SerializeandDeserializeBinaryTreeSolution1 s2 = new SerializeandDeserializeBinaryTreeSolution1();
	public static void main(String[] args) {
		//Your Codec object will be instantiated and called as such:
		Codec codec = s.new Codec();	
		//		TreeNode n = codec.deserialize("[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9,null,10,null,11,null,12,null,13,null,14,null,15,null,16,null,17,null,18,null,19,null,20,null,21]"); 

		//		TreeNode n = codec.deserialize("[5,2,3,null,null,2,4,3,1]"); 
		TreeNode n = codec.deserialize("[5,2,3,4,5,null,null,null,null,6,3,null,null,8]"); 
		String s = codec.serialize(n);
		System.out.println(s);

		//		SerializeandDeserializeBinaryTreeSolution1.Codec codec2 = s2.new Codec();
		//		TreeNode n2 = codec2.deserialize("1,2,3,null,null,4,null,null,5,null,null,");
		//		String s2 = codec2.serialize(n);


	}

	/**
	 * Memory and Time Limit
	 * 
	 * Very Heavy Solution
	 * 
	 * 
	@intuition

	@fail
        1) I didnt split the string
        2) forgot about overflow
        3) serialization is wrong not separating numbers
        4) forgot to add level count
        5) there was a case I was not expecting, which was ending in null, 
        it resulted in a null pointer exception because I was using get instead of getOrDefault.
        6) forgot empty case
        7) didn't though in many edge cases where the tree is unbalanced


	 * @author Nelson Costa
	 *
	 */
	class Codec0 {

		// Encodes a tree to a single string.
		/**
		 * @time complexity O(2^H)
		 * @space complexity O(2^(H + 1)) => 2^H, H height of the tree
		 * @param root
		 * @return
		 */
		public String serialize(TreeNode root) {

			if (root == null)
				return "[null]";

			//TreeNode to List
			HashMap<Integer, Integer> levelToNullCount = new HashMap<>();
			Queue<Triplet> q = new ArrayDeque<>();
			q.add(new Triplet(0, 0, root));
			Triplet cur;

			List<Integer> serial = new ArrayList<Integer>();

			while (!q.isEmpty()) //all nodes nude included will be traversed
			{
				cur = q.poll();

				if (cur.node == null)
				{
					if(levelToNullCount.getOrDefault(cur.level, 0) == (int) Math.pow(2, cur.level))
					{
						break; //all nulls in the current level so we stop
					}
					else
					{
						q.add(new Triplet(cur.level + 1,  getLeftChildrenIndex(cur.index), null));
						q.add(new Triplet(cur.level + 1, getRightChildrenIndex(cur.index), null));
						levelToNullCount.put(cur.level + 1, levelToNullCount.getOrDefault(cur.level + 1, 0) + 2);
					}
				}
				else 
				{
					q.add(new Triplet(cur.level + 1,   getLeftChildrenIndex(cur.index), cur.node.left));
					q.add(new Triplet(cur.level + 1,  getRightChildrenIndex(cur.index), cur.node.right));

					if(cur.node.left == null)
						levelToNullCount.put(cur.level + 1, levelToNullCount.getOrDefault(cur.level + 1, 0) + 1);


					if(cur.node.right == null)
						levelToNullCount.put(cur.level + 1, levelToNullCount.getOrDefault(cur.level + 1, 0) + 1);

				}

				serial.add(cur.node == null? null : cur.node.val);
			}

			//List To String
			StringBuilder ans = new StringBuilder("[");
			ans.append(serial.get(0) == null? "null" : serial.get(0) + "");

			for (int i = 1; i < serial.size(); i++)
			{
				ans.append("," + serial.get(i));
			}

			ans.append("]");

			return ans.toString();        
		}


		/**
		 * Decodes your encoded data to tree.
		 * @time    O(N + L) N number of integers/nulls, and L is the number of characters
		 * @space   O(N) where N is the number of integers
		 * @param s
		 * @return
		 */
		public TreeNode deserialize(String s) {

			if (s.charAt(1) == 'n')
			{
				return null;
			}

			List<Integer> deserial = new ArrayList<>();
			int left = 1; //start
			int right = s.length() - 2; //end

			//String to List - I should have used split.... Duh, too much excitement
			char cur;
			int tokenEnd;
			while (left <= right)       // O(L) L string lentgh
			{
				cur = s.charAt(left);
				if(Character.isDigit(cur) || cur == '-')
				{
					tokenEnd = left;

					do
					{                    
						tokenEnd++;
					}
					while (tokenEnd <= right && s.charAt(tokenEnd) != ',');

					deserial.add(Integer.parseInt(s.substring(left, tokenEnd)));

					left = tokenEnd; //which has to be a comma
				}
				else if (Character.isLetter(cur))
				{

					tokenEnd = left;

					do
					{                    
						tokenEnd++;
					}
					while (tokenEnd <= right && s.charAt(tokenEnd) != ',');

					deserial.add(null);

					left = tokenEnd;
				}

				left++; //skip comma

			}

			//List to Tree
			Triplet tri;
			TreeNode children =  new TreeNode(deserial.get(0)); //root

			TreeNode preAns = new TreeNode(-1); //pre Anser Reference Handling
			preAns.right = children;        

			Queue<Triplet> q = new ArrayDeque<>();
			q.add(new Triplet(0, 0, children));

			while (!q.isEmpty())  //O(N) N number of Elements in the tree
			{
				tri = q.poll();
				int rightIndex = getRightChildrenIndex(tri.index);
				int leftIndex = getLeftChildrenIndex(tri.index);


				if(leftIndex < deserial.size() && deserial.get(leftIndex) != null)
				{
					children = new TreeNode(deserial.get(leftIndex));
					tri.node.left = children;
					q.add(new Triplet(tri.level + 1, leftIndex, children));
				}

				if (rightIndex < deserial.size() && deserial.get(rightIndex) != null)
				{
					children = new TreeNode(deserial.get(rightIndex));
					tri.node.right = children;
					q.add(new Triplet(tri.level + 1, rightIndex, children));
				}

			}

			return preAns.right;

		}

		private int getRightChildrenIndex(int i){
			return (i + 1) * 2;
		}

		private int getLeftChildrenIndex(int i){
			return ((i + 1) * 2) - 1;
		}



		class Triplet{
			int level;
			int index;
			TreeNode node;

			public Triplet(int l, int i, TreeNode n)
			{
				level = l;
				index = i;
				node = n;
			}

		}
	}




	/**
	 * @intuition
	 * 		serialize
	 * 			perform bfs
	 * 
	 * 			using queue with current level and current node
	 * 
	 * 			when processing a node we put the children with parents level plus 1
	 * 
	 * 			we add null children in the queue too, they need to be added to the string
	 * 
	 * 			at each level we calculate the number of non null nodes in the level below, this is important to help us stop iterating
	 * 
	 * 			when we find a null node and we have no non-null nodes in that level, it means we have processed all nodes and can stop
	 * 
	 * 			each iteration deals with a node and that node is added to StringBuilder, whether it is null or non null.
	 * 
	 * 			we finally remove nulls in excess in the string builder
	 * 
	 * 			this nulls appears when we are at the last level and our last not is not that the rightmost possible side.
	 * 
	 * 
	 * 		deserialize
	 * 			Performed bfs
	 * 	
	 * 			the tricky part in here is to get the children of a particular node.
	 * 
	 * 			it might not be easy to see the pattern but if we look carefully we will find how to get the left children.
	 * 
	 * 			Let me explain. for the root, the left children is right in the next position, 0 + 1. the right is left + 1 = 2.
	 * 	
	 * 			for other levels the same rule applies, the last element of each level has its children right next to them.
	 * 
	 * 			so how to find the children of non last element of a level?
	 * 
	 * 			well, we have to jump to the last position of the level, 
	 * 
	 * 			and from there we will multiply by two the number of elements we have already processed in that level which are non-null.
	 * 
	 * 			why skiping non-null elements? because they will not have children in the level below.
	 * 
	 * 			So by doing this we are escaping children of nodes in the same level which already were processed.
	 * 
	 * 			from there it's pretty much doable, assign left and righ children to current node
	 *
	 *				add left and right children to queue
	 * 
	 * 			for each level to perform this calculations we have to count how many nulls we have behind current node, in order to do the right math.
	 * 
	 * 			and again, at each level we count the total elements in the level bellow it.
	 * 
	 * 			and thats it we have used a preAns Node to have at tis right the answer.
	 * 
	 * 	
	 * 		Et Voilá
	 * 
	 * @comments
	 * 		Very Tough question if performed with bfs to work exactly like leetcode
	 * 		way easier if doing dfs with any format
	 * 

		@score
			Runtime: 27 ms, faster than 27.08% of Java online submissions for Serialize and Deserialize Binary Tree.
			Memory Usage: 41.1 MB, less than 82.39% of Java online submissions for Serialize and Deserialize Binary Tree.

	    @fail
	        1) I didnt split the string
	        2) forgot about overflow
	        3) serialization is wrong not separating numbers
	        4) forgot to add level count
	        5) there was a case I was not expecting, which was ending in null, 
	        it resulted in a null pointer exception because I was using get instead of getOrDefault.
	        6) forgot empty case
	        7) didn't though in many edge cases where the tree is unbalanced
			//after changing deserialization
	        8) I was not adding nulls to the hashmap
	        9) I was not updating the curentPosition when dealing with nulls

	 **/
	class Codec {

		/**
		 * @time	O(N*D) + O(T) N number of nodes, D size of Digits, T Diameter of tree.
		 * 		O(T) is when the first element of the last from is not null and all the rest is null. 
		 * 		you will remove T - 1 nulls.
		 * 
		 * @space	O(N)
		 * 
		 * @param root
		 * @return
		 */
		public String serialize(TreeNode root) {
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
		 * @time 	O(S) + O(N*D) + , N each node, S equals to string size , D digit size
		 * @spaced	O(L) + O(N) L is hashmap with levels, N is nodes.
		 * 
		 * @optimizations
		 * 		I believe using String[] would improve performance, but right now i dont want to change the code
		 * 
		 * @param s
		 * @return
		 */
		public TreeNode deserialize(String s) {  

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

		//get left children
		private int getLeft(int parent, int nullsBehind, int positionInCurrentLevel, int totalElementsInLevel){

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

		class Tuple{
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


class SerializeandDeserializeBinaryTreeSolution1 {
	class Codec {
		public String rserialize(TreeNode root, String str) {
			// Recursive serialization.
			if (root == null) {
				str += "null,";
			} else {
				str += str.valueOf(root.val) + ",";
				str = rserialize(root.left, str);
				str = rserialize(root.right, str);
			}
			return str;
		}

		// Encodes a tree to a single string.
		public String serialize(TreeNode root) {
			return rserialize(root, "");
		}


		public TreeNode rdeserialize(List<String> l) {
			// Recursive deserialization.
			if (l.get(0).equals("null")) {
				l.remove(0);
				return null;
			}

			TreeNode root = new TreeNode(Integer.valueOf(l.get(0)));
			l.remove(0);
			root.left = rdeserialize(l);
			root.right = rdeserialize(l);

			return root;
		}

		// Decodes your encoded data to tree.
		public TreeNode deserialize(String data) {
			String[] data_array = data.split(",");
			List<String> data_list = new LinkedList<String>(Arrays.asList(data_array));
			return rdeserialize(data_list);
		}
	}
}
