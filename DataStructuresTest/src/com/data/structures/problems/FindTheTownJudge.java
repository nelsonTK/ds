package com.data.structures.problems;

/**
 * https://leetcode.com/problems/find-the-town-judge/
 * EASY
 * @author Nelson Costa
 *
 */
public class FindTheTownJudge {

	public static void main(String[] args) {
		FindTheTownJudge f = new FindTheTownJudge();
		int N = 4;
		int [][] trustMap = new int[3][2];
		trustMap[0] = new int []{1,5};
		trustMap[1] = new int []{3,5};
		trustMap[2] = new int []{2,5};
		System.out.println(f.findJudge(N, trustMap));
	}

	/**
	 * 		Runtime: 3 ms, faster than 78.73% of Java online submissions for Find the Town Judge.
			Memory Usage: 47.2 MB, less than 100.00% of Java online submissions for Find the Town Judge.
	 * 
	 * @comments Despite I tried to cut the time of the for loop in half it really didn't change much in the time took and thosen't change the time complexity at all.
	 * I can stop doing this for in half stuff unless it really makes sence and does not conduct to solution complications, which it does in this scenario
	 *
	 * 
	 * @failed didn't pay attention to the N, understimated this variable
	 * failed again because was not protecting lenght 0
	 * failed again because I was using i instead of trustCount [i]
	 * failed again, not all edge cases where covered (when all people trusts in X but x trusts in someone else)
	 * 
	 * @time  O(1) because the number of people is no more than 1000
	 * @space O(1) because the number of items is no more than 1000 
	 * 
	 * @param N number of people
	 * @param trustMap
	 * @return
	 */
	public int findJudge(int N, int[][] trustMap) {

		if (trustMap == null || trustMap.length == 0 )
			return 1;

		if(trustMap.length == 1)
			return trustMap[0][1];

		int [] trustCount = new int [1001];
		int [] entrustedCount = new int [1001];

		for (int i = 0, j = trustMap.length - 1; i<=j; i++,j--)
		{
			trustCount[trustMap[i][1]]++; 
			trustCount[trustMap[j][1]]++; 


			entrustedCount[trustMap[i][0]]++; 
			entrustedCount[trustMap[j][0]]++; 
		}

		//fix count for when the number of elements is odd
		if ( (trustMap.length & 1) == 1)
		{
			if ( trustCount[trustMap[trustMap.length/2][1]] > 0)
			{
				trustCount[trustMap[trustMap.length/2][1]]--;				
			}

			if (trustCount[trustMap[trustMap.length/2][0]] > 0)
			{
				entrustedCount[trustMap[trustMap.length/2][0]]--;
			}
		}

		for(int i = 1, j = trustCount.length - 1; i <= j; i++,j--)
		{
			if (trustCount[i] == N - 1 && entrustedCount[i] == 0)
				return i;

			if (trustCount[j] == N - 1 && entrustedCount[j] == 0)
				return j;
		}

		return -1;
	}

}

class FindTheTownJudgeSolution {

	public int findJudge(int N, int[][] trust) {

		if (trust.length < N - 1) {
			return -1;
		}

		int[] trustScores = new int[N + 1];

		for (int[] relation : trust) {
			trustScores[relation[0]]--;
			trustScores[relation[1]]++; 
		}

		for (int i = 1; i <= N; i++) {
			if (trustScores[i] == N - 1) {
				return i;
			}
		}
		return -1;
	}
}
