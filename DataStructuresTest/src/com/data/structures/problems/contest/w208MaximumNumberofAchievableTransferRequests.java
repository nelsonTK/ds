package com.data.structures.problems.contest;

/**
 * https://leetcode.com/problems/maximum-number-of-achievable-transfer-requests/
 * HARD
 * @author Nelson Costa
 *
 */
public class w208MaximumNumberofAchievableTransferRequests {

	
	/**
	 * @intuition
	 * 		The intuition here is to try out all possible requests because of the range allow us to do that
	 * 		I initially thought to do things recursively, like trying all combinations that are related, but then I saw that was not a good way to approach it,
	 * 		Then I realized that I needed to performed all combinations, and that was when I though in using bitmap, 
	 * 		I initially thought in permutations but permutations would be O(16!) and in this case order doesnt matter to I went to bitmap solution
	 * 		
	 * 		So I do all the combinations by counting from one to 2^N and then I try all the requests in the end.
	 * 
	 * @score
     *      Runtime: 160 ms, faster than 57.14% of Java online submissions for Maximum Number of Achievable Transfer Requests.
	 * 		Memory Usage: 36.6 MB, less than 100.00% of Java online submissions for Maximum Number of Achievable Transfer Requests.
	 *
     * @fail
	 *   missmatch between index and bitmap, the 0 was being compared with 1 when doing & which lead to unexpected results
	 *   
	 * @time
	 * 		O(2^N*N)
	 * 
	 * @space
	 * 		O(N)
	 *   
	 **/ 
	public int maximumRequests(int n, int[][] requests) {
		//Overview
		//	bitmap from 1 to 2^n
		//	for each bitmap combination process each request at a position i
		//	if at the end of this processing the result is zero (second bitmap to control if al elements are zero)

		int rLen = requests.length;
		int maxCombinations =  (int) Math.pow(2, rLen);//Math.pow(2, requests.length + 1) - 1;
		int [] buildings = new int[n];
		int maxNumberOfRequests = 0;

		//bitmap from 1 to 2^n
		for (int combination = 1; combination <= maxCombinations; combination++)
		{
			maxNumberOfRequests = processCombinations (combination, buildings, requests, maxNumberOfRequests);
		}

		//try all requests and see if the buildings are balanced
		return processCombinations((int)(Math.pow(2, requests.length + 1) - 1), buildings, requests, maxNumberOfRequests);

	}

	private int processCombinations (int combination, int [] buildings, int [][] requests, int max)
	{
		int numberOfRequests = 0;
		int rLen = requests.length;

		//for each bitmap combination process each request at a position i
		for (int i = 0; i < rLen; i++)
		{
			//if bit at position i is active we will process the request at position i
			if (((1 << i) & combination) > 0)
			{
				buildings[requests[i][0]]--;
				buildings[requests[i][1]]++;
				numberOfRequests++;
			}
		}

		//check balancedBuilding and reset array
		boolean balancedBuildings = true;
		for (int i = 0; i < buildings.length; i++)
		{
			if (buildings[i] != 0)
				balancedBuildings = false;

			buildings[i] = 0;
		}

		//update maxNumberOfRequests
		if (balancedBuildings && numberOfRequests > max)
		{
			max = numberOfRequests;
		}

		return max;
	}
}
