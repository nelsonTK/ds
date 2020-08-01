package com.data.structures.problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.data.structures.problems.ds.LeetCodeExercise;
import com.data.structures.problems.ds.TreeNode;
import com.data.structures.utils.LeetcodeUtils;

/**
 * https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class VerticalOrderTraversalofaBinaryTree extends LeetCodeExercise{

	static VerticalOrderTraversalofaBinaryTree v = new VerticalOrderTraversalofaBinaryTree();
	static VerticalOrderTraversalofaBinaryTreeSolution1 v2 = new VerticalOrderTraversalofaBinaryTreeSolution1();
	public static void main(String[] args) {
		TreeNode root = LeetcodeUtils.Tree.stringToTreeNode("[0,8,1,null,null,3,2,null,4,5,null,null,7,6]");
		v.verticalTraversal(root);
		v2.verticalTraversal(root);
	}


	int min = Integer.MAX_VALUE;
	
	/*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * @intuition
	 * 	This is a great example of a simple question that is turned difficult because of a poor description
	 * 	I wasted a lot of time understanding the order requirement when it was pretty damn simple.
	 * 
	 * 	My solution here was to traverse the tree, and save the elements in a HashMap by their X
	 * 
	 * 	Then after traversing the tree I just need to sort them as the requirement poorly asks
	 * 
	 * 	natural order is determined by the depth in the tree. more depth appears later. or the smaller the level the smaller you naturally are.
	 * and the special case is when all the coordinate is the same for both in that case you should put as first the smaller of both values in the tree
	 * 
	 * 
	 * @comments
	 * 	What makes this question HARD is actually the description that lacks objectiveness.
	 *	because this is indeed a medium question.
	 *	if it was writen in another way you would instantly figured out what this guys want.
	 *	
	 *	The definition of what should happen when the elements have different coordinates is omitted.
	 *	From the description my understanding was that you only sort the triplets,
	 *	if they had the same Coordinate, else you could keep the natural order.
	 *	Where "natural order" would be the order where you find elements in your traversal.
	 *	
	 *	But It turned out to be wrong.
	 *	Looks like what should be the "natural order" was indeed the depth in the tree.
	 *	
	 *	The deeper the coordinate is in the tree the later it appears in the final list.
	 *	This can be consider as the "natural order".
	 *	If the coordinates are the same you then sort by value.
	 * 
	 * @score
	 * 		Runtime: 6 ms, faster than 25.16% of Java online submissions for Vertical Order Traversal of a Binary Tree.
			Memory Usage: 40.6 MB, less than 5.10% of Java online submissions for Vertical Order Traversal of a Binary Tree.
	 * 		
	 * 		Runtime: 4 ms, faster than 52.05% of Java online submissions for Vertical Order Traversal of a Binary Tree.
			Memory Usage: 39.8 MB, less than 19.39% of Java online submissions for Vertical Order Traversal of a Binary Tree.
	 * @fail   
	 * 		1) forgot to change the right element when coping 
	 * 		2) mistakenlly increase the min size. I later increase instead of increase and use 
	 * 		3) order matters... 
	 * 		4) found out the order doesnt matters, they want you to do the traversal in a certain way 
	 * 			other than return the correct result. 
	 * 			Thats a poor question design
	 * 		5) my misinterpretation, I sould read more carefully
	 * 
	 * @optimizations
	 * 		I could use a list, I didn't do it because the requirements were poorly written
	 * 
	 * @debug 
	 * 		yes
	 * 
	 * @time
	 * 		O(NLogN/k) => I sort k chunks => O(K*N/KLogN/K) => this means sorting k chunks k times
	 * 
	 * @space
	 * 		O(N)
	 * 
	 * 
	 * @param root
	 * @return
	 */
	public List<List<Integer>> verticalTraversal(TreeNode root) {

		if (root == null)
			return new ArrayList<List<Integer>>();

		HashMap<Integer, List<Coordinate>> map = new HashMap<>();
		List<List<Integer>> ans = new ArrayList<>();
		verticalize(0, 0, root, map);

		List<Integer> col =  getCoordToInteger(map.get(min));
		while (col.size() != 0)
		{
			ans.add(col);
			col = getCoordToInteger(map.get(++min));
		}

		return ans;
	}


	private List<Integer> getCoordToInteger(List<Coordinate> coor){

		List<Integer> result = new ArrayList<>();

		if (coor == null)
			return result;

		
		Collections.sort(coor, (a, b) -> 
		{
			if(a.x == b.x)
                if(a.y == b.y)
                    return Integer.compare(a.val, b.val);
                else
                    return Integer.compare(a.y, b.y);
            else return Integer.compare(a.x, b.x);
		});

		for (Coordinate c : coor)
		{
			result.add(c.val);
		}
		return result;
	}


	private void verticalize(int x, int y, TreeNode node, HashMap<Integer, List<Coordinate>> map)
	{
		if(node == null)
			return;

		map.computeIfAbsent(x, k -> new ArrayList<Coordinate>()).add(new Coordinate(node.val, x, y));
		min = Math.min(x, min);

		verticalize(x - 1, y + 1, node.left, map);
		verticalize(x + 1, y + 1, node.right, map);

	}

	class Coordinate{
		int val;
		int x;
		int y;
		Coordinate(int v, int x, int y)
		{
			this.x = x;
			this.y = y;
			val = v;

		}
	}
}



/*********************
* OTHERS SOLUTIONS
*********************/

/**
 * 
 * @intuition
 * 		This is the exact same concept than my operation.
 * 
 * 		the reason why it is better than the other solution is because sorting smaller chunks has a smaller time complexity than sorting bigger chunks.
 * 
 * 		Proof is:
 * 			Cost of sorting a chunk
 * 				N/K * Log (N/K) 
 * 					where N/K is a chunk and K is then number of Chunks
 * 
 * 			Cost of sorting K Chunks
 * 
 * 				K*N/K * Log (N/K) => N * Log (N/K)
 * 
 * 		 in the log run it would pay off
 * 
 * @author Nelson Costa
 *
 */
class VerticalOrderTraversalofaBinaryTreeSolution2 {
    Map<Integer, ArrayList<Pair<Integer, Integer>>> columnTable = new HashMap();
    int minColumn = 0, maxColumn = 0;

    private void DFS(TreeNode node, Integer row, Integer column) {
        if (node == null)
            return;

        if (!columnTable.containsKey(column)) {
            this.columnTable.put(column, new ArrayList<Pair<Integer, Integer>>());
        }

        this.columnTable.get(column).add(new Pair<Integer, Integer>(row, node.val));
        this.minColumn = Math.min(minColumn, column);
        this.maxColumn = Math.max(maxColumn, column);
        // preorder DFS traversal
        this.DFS(node.left, row + 1, column - 1);
        this.DFS(node.right, row + 1, column + 1);
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> output = new ArrayList();
        if (root == null) {
            return output;
        }

        // step 1). DFS traversal
        this.DFS(root, 0, 0);

        // step 2). retrieve the value from the columnTable
        for (int i = minColumn; i < maxColumn + 1; ++i) {
            // order by both "row" and "value"
            Collections.sort(columnTable.get(i), new Comparator<Pair<Integer, Integer>>() {
                @Override
                public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
                    if (p1.getKey().equals(p2.getKey()))
                        return p1.getValue() - p2.getValue();
                    else
                        return p1.getKey() - p2.getKey();
                }
            });

            List<Integer> sortedColumn = new ArrayList();
            for (Pair<Integer, Integer> p : columnTable.get(i)) {
                sortedColumn.add(p.getValue());
            }
            output.add(sortedColumn);
        }

        return output;
    }
}



/**
 * What this guys to is to traverse the tree and put the elements in a list, 
 * then sort that list. 
 * and then add the elements in separate partitions.
 * 
 * @score
 * 		Runtime: 2 ms, faster than 99.74% of Java online submissions for Vertical Order Traversal of a Binary Tree.
		Memory Usage: 39.8 MB, less than 14.80% of Java online submissions for Vertical Order Traversal of a Binary Tree.
 * 
 * @author Nelson Costa
 *
 */
class VerticalOrderTraversalofaBinaryTreeSolution1 {
	List<Triplet<Integer, Integer, Integer>> nodeList = new ArrayList<>();

	private void DFS(TreeNode node, Integer row, Integer column) {
		if (node == null)
			return;
		nodeList.add(new Triplet(column, row, node.val));
		// preorder DFS traversal
		this.DFS(node.left, row + 1, column - 1);
		this.DFS(node.right, row + 1, column + 1);
	}

	public List<List<Integer>> verticalTraversal(TreeNode root) {
		List<List<Integer>> output = new ArrayList();
		if (root == null) {
			return output;
		}

		// step 1). DFS traversal
		DFS(root, 0, 0);

		// step 2). sort the list by <column, row, value>
		Collections.sort(this.nodeList, new Comparator<Triplet<Integer, Integer, Integer>>() {
			@Override
			public int compare(Triplet<Integer, Integer, Integer> t1,
					Triplet<Integer, Integer, Integer> t2) {
				if (t1.first.equals(t2.first))
					if (t1.second.equals(t2.second))
						return t1.third - t2.third;
					else
						return t1.second - t2.second;
				else
					return t1.first - t2.first;
			}
		});

		// step 3). extract the values, grouped by the column index.
		List<Integer> currColumn = new ArrayList();
		Integer currColumnIndex = this.nodeList.get(0).first;

		for (Triplet<Integer, Integer, Integer> triplet : this.nodeList) {
			Integer column = triplet.first, value = triplet.third;
			if (column == currColumnIndex) {
				currColumn.add(value);
			} else {
				output.add(currColumn);
				currColumnIndex = column;
				currColumn = new ArrayList();
				currColumn.add(value);
			}
		}
		output.add(currColumn);

		return output;
	}

	class Triplet<F, S, T> {
		public final F first;
		public final S second;
		public final T third;

		public Triplet(F first, S second, T third) {
			this.first = first;
			this.second = second;
			this.third = third;
		}
		
		public String toString() {
			return "val: " + third + " x: " + first + " y: " + second;
		}
	}
}