package com.data.structures.problems.contest;

import java.util.Arrays;
import java.util.List;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/minimum-cost-to-connect-two-groups-of-points/
 * HARD
 * @author Nelson Costa
 */
public class w207MinimumCosttoConnectTwoGroupsofPoints extends LeetCodeExercise{

	public static void main(String[] args) 
	{
		List<List<Integer>> cost = stringToListOfLists("[[ 5, 1], [ 4, 7], [1, 2]]");
		w207MinimumCosttoConnectTwoGroupsofPoints c = new w207MinimumCosttoConnectTwoGroupsofPoints();
		c.connectTwoGroups(cost);
	}


	/*********************************
	 * SOLUTION 1
	 ********************************/
	/** [TIME LIMIT EXCEEDED]
	 * failed because of TLE, no cache used, no bitmap used
	 **/
	class Solution1{
		int[]dp;
		int [] minCost2;
		int ans;
		public int connectTwoGroups(List<List<Integer>> cost) {

			//calculate assignments
			int size1 = cost.size();
			int size2 = cost.get(0).size();
			ans = Integer.MAX_VALUE;
			dp = new int [size2];
			minCost2 = new int[size2];
			Arrays.fill(minCost2, Integer.MAX_VALUE);


			//calculate minimum size for each size2 array
			for (int r = 0; r < size1; r++)
			{
				for (int c = 0; c < size2; c++)
				{
					//bitmap used to mark sizepoints, 
					minCost2[c] = Math.min(cost.get(r).get(c), minCost2[c]);    
				}
			}

			//calculate all possible single connections
			minCost(0, 0, cost);

			return ans;
		}


		private void minCost(int intGroup, int curCost, List<List<Integer>> cost)
		{
			//when all indexes processed we will calculate the minimum of the remaining elements
			if (intGroup == cost.size())
			{
				for (int i = 0; i < dp.length; i++)
				{
					if (dp[i] == 0)
					{
						curCost += minCost2[i];
					}
				}
				ans = Math.min(curCost, ans);
				return;
			}

			//sum cost of conection size1 group to all others
			for (int j = 0; j < cost.get(0).size(); j++)
			{
				dp[j]+=1;
				curCost += cost.get(intGroup).get(j);

				minCost(intGroup + 1, curCost, cost);

				dp[j]-=1;
				curCost -= cost.get(intGroup).get(j);
			}
		}
	}

	/*********************************
	 * SOLUTION 2
	 ********************************/
	/**
	 * 	@intuition
	 * 		This was a very thought problem
	 * 
	 * 		My implementation was DP top down. with a matrix that has row as group one, and a bitmap that tells which elements where connected in the second group
	 * 		
	 * 		each element returns it's sum each time it is call with a bitmap (which represents the second group nodes connected)
	 * 
	 * 	
	 * 
	 *   @score
	 *       Runtime: 32 ms, faster than 69.63% of Java online submissions for Minimum Cost to Connect Two Groups of Points.
	 *       Memory Usage: 38.9 MB, less than 67.62% of Java online submissions for Minimum Cost to Connect Two Groups of Points.
	 *
	 *
	 *
	 *   @fail
	 *        1) Failing because of a bad backtracking, I was not returning the correct value 
	 *        
	 *        
	 *   @time
	 *   	O(N * M * 2^N)
	 *   
	 *   @space
	 *      O(N*2^N)
	 **/
	int ans;
	int [] minCost2;
	public int connectTwoGroups(List<List<Integer>> cost) 
	{
		//find the minimum for each point in size 2

		//create memo with index size and bitmap size for columns

		//find the minimum cost starting with index 0, and bitmap 0

		int size1 = cost.size();
		int size2 = cost.get(0).size();
		ans = Integer.MAX_VALUE;
		minCost2 = new int[size2];
		Arrays.fill(minCost2, Integer.MAX_VALUE);

		//size1 + 1 is to avoid validations about size in the recursion (getMin())
		Integer [][] memo = new Integer[size1 + 1][1 << size2]; 

		for (int i = 0; i < size1; i++)
		{
			for (int j = 0; j < size2; j++)
			{
				minCost2[j] = Math.min(minCost2[j], cost.get(i).get(j));
			}
		}
		System.out.println(Arrays.toString(minCost2));
		return getMin(0,0,memo,cost);
	}

	//dp[size1Index][bitmap] = minimum cost of point X of size1 
	//for a set of paired size2 points
	private int getMin(int group1, int bitmap, Integer [][] memo, List<List<Integer>> cost)
	{

		//if memo already exists update mincost suming and return
		if (memo[group1][bitmap] != null)
		{
			//ans = Math.min(ans, curCost + memo[group1][bitmap]);
			return memo[group1][bitmap];//Math.min(ans, curCost + memo[group1][bitmap]);
		}

		//if all items processed calculate minimum cost for a element
		//requires see the nodes that are not connected and consult the mincost array for them
		if(group1 == cost.size())
		{
			int costRemaining = 0;
			for (int i = 0; i < cost.get(0).size(); i++)
			{
				if (((bitmap >> i) & 1) == 0 )
				{
					//(bitmap >> i) - shit right to have acces to the nth bit in the first place
					//AND with 1 to see if it is a zero, if it is then we found a element that need to be paired

					costRemaining += minCost2[i];
				}
			}

			return costRemaining;
		}

		//for each point in size1 try each connection to the point in size2 group
		int connCost = 0;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < cost.get(0).size(); i++)
		{
			connCost = cost.get(group1).get(i);
			min = Math.min(getMin(group1 + 1, bitmap | 1 << i, memo, cost) + connCost, min);
		}

		//memoize
		memo[group1][bitmap] = min;

		return min;
	}
}


/*********************
* OTHERS SOLUTIONS
*********************/
/**
 * Bottom Up Solution
 * @author Nelson Costa
 *
 */
class w207MinimumCosttoConnectTwoGroupsofPointsUnofficialSolution{
	public int connectTwoGroups(List<List<Integer>> cs) {
		int m = cs.size(), n = cs.get(0).size(), msk = 1 << m, dp[] = new int[msk];
		for (int i = 0; i < msk; i++) dp[i] = Integer.MAX_VALUE;
		dp[0] = 0;
		for (int i = 0; i < n; i++) {
			int[] tmp = new int[msk];
			for (int l = 0; l < msk; l++) tmp[l] = Integer.MAX_VALUE;
			// tmp[0] = 0; // you can not leave the previous lines without connections.
			for (int k = 0; k < msk; k++) {
				for (int j = 0; j < m; j++) {
					int mask = k | (1 << j);
					if (dp[k] != Integer.MAX_VALUE) tmp[mask] = Math.min(tmp[mask], dp[k] + cs.get(j).get(i));
					if ((k & (1 << j)) == 0) {
						if (tmp[k] != Integer.MAX_VALUE)  tmp[mask] = Math.min(tmp[mask], tmp[k] + cs.get(j).get(i));
					}
				}
			}
			dp = tmp;
		}
		return dp[msk - 1];
	}
}