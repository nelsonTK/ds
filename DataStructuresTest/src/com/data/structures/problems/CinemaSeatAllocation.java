package com.data.structures.problems;

import java.util.Arrays;
import java.util.HashMap;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/cinema-seat-allocation/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class CinemaSeatAllocation extends LeetCodeExercise {

	static CinemaSeatAllocation c = new CinemaSeatAllocation();

	public static void main(String[] args) {

		int[][] res = stringToMatrix("[[2,1],[1,8],[2,6]]");
		int n = 2;

		res = stringToMatrix("[[4,3],[1,4],[4,6],[1,7]]");
		n = 4;

		res = stringToMatrix("[[1,2],[1,3],[1,8],[2,6],[3,1],[3,10]]");
		n = 3;
		System.out.println(c.maxNumberOfFamilies(n, res));
		System.out.println(c.maxNumberOfFamilies1(n, res));

	}
	
	/*********************************
	 * SOLUTION 1
	 ********************************/
	int maxFamilySeats = 2;

	/**
	 * @intuition
	 * 		I calculate the max number of seats and from there I subtract by the number of ocuppied seats per row
	 * 		
	 * @score
	 * 
			Runtime: 72 ms, faster than 7.43% of Java online submissions for Cinema Seat Allocation.
			Memory Usage: 71.3 MB, less than 100.00% of Java online submissions for Cinema Seat Allocation.
	 * 
	 * @debug
	 * 		Used debug to confirm that my sort was okay
	 * 
	 * @comment
	 * 		This solution is ugly, I dont Like it. Is a Greedy, Extremly greedy solution with hardcoded values
	 * 
	 * @fail
	 * 		1) Big mistake, I didn't thought that we could have rows not in the reserved tickets
	 * 
	 * 
	 * @time	O(NLog N)
	 * @space	O(1)
	 * 
	 * @param n
	 * @param res
	 * @return
	 */
	public int maxNumberOfFamilies0(int n, int[][] res) {



		int ans = n * 2;
		Arrays.sort(res, 
				(a, b) -> {
					if (a[0] != b[0])
						return Integer.compare(a[0], b[0]);
					else
						return Integer.compare(a[1], b[1]);
				});

		int row;
		int maxFamily = 2;
		int left = 0;
		int right = 0;
		int seat = 0;
		for (int i = 0; i < res.length; i++)
		{

			row = res[i][0];
			seat = res[i][1];

			if (seat >= 2 && seat <= 3) 
			{
				maxFamily = 1;
				left = 1;
			}
			else if (seat >= 4 && seat <= 5)
			{
				if (left < 2)
				{
					left += 2;
				}
				maxFamily = 1;
			}
			else if (seat >= 6 && seat <= 7)
			{
				if (left > 0)
				{
					maxFamily = 0;
				}
				else
				{
					maxFamily = 1;
				}

				right = 1;
			}
			else if (seat >= 8 && seat <= 9)
			{
				if ( left == 0 && right == 0)
				{
					maxFamily = 1;
				}
				else if  ( left >= 2 && right == 0)
				{
					maxFamily = 0;
				}
			}


			if (i + 1 == res.length || i + 1 < res.length && res[i + 1][0] != row)
			{
				ans -=  maxFamilySeats - maxFamily;

				maxFamily = 2;
				left = 0; //1 means 2 or 3 reserved, 2 means 4 or 5 reserved
				right = 0; //means 6 reserved
			}
		}

		return ans;
	}
	
	/*********************************
	 * SOLUTION 2
	 ********************************/
	/**
	 * @intuition
	 * 		We create a bit mapping of the seats, and than we  have some masks to identify if those areas are sonsumed
	 * 		depending on the consumed areas the & between the allocation map and the masks will be differente and will 
	 * 		allow us to improve find out what is the family seats available
	 * 		
			What I don't like is the use of all this masks it looks like I'm doing something wrong.
	 * 
	 * @score
	  		Runtime: 31 ms, faster than 35.56% of Java online submissions for Cinema Seat Allocation.
			Memory Usage: 80.2 MB, less than 100.00% of Java online submissions for Cinema Seat Allocation.
	 * 
	 * @fail
	 * 		1) forgot to add convert cmasks variable bit operation result to 2^n where n belos to 0 - 3
	 * 		2) I was lacking one case
	 * 
	 * @time  O(N)
	 * @space O(N)
	 * 
	 * @param n
	 * @param res
	 * @return
	 */
	public int maxNumberOfFamilies1(int n, int[][] res) {

		HashMap<Integer, Integer> rowMapping = new HashMap<>();
		int row, seat;
		int ans = n * 2;

		for (int i = 0; i < res.length; i ++)
		{
			row = res[i][0];
			seat = res[i][1];
			rowMapping.put(row, rowMapping.getOrDefault(row, 0) | 1 << seat - 1);
		}

		int cMask0 = 0b0110000000;
		int cMask1 = 0b0001100000;
		int cMask2 = 0b0000011000;
		int cMask3 = 0b0000000110;
		int allocationCode = 0;

		int lineup = 0;
		for (int k : rowMapping.keySet())
		{
			lineup = rowMapping.get(k);
			allocationCode = (lineup & cMask0) != 0? 1 : 0;
			allocationCode += (lineup & cMask1) != 0? 2 : 0;
			allocationCode += (lineup & cMask2) != 0? 4 : 0;
			allocationCode += (lineup & cMask3) != 0? 8 : 0;
			ans -= getConsumedFamillySeats(allocationCode);
		}

		return ans;
	}


	private int getConsumedFamillySeats(int allocation) {

		if (allocation == 1 ||allocation == 2 ||allocation == 4 ||
				allocation == 8 || allocation == 12 || allocation == 3 || allocation == 9)
		{
			//one family seat available
			return 1;
		}
		else if (allocation == 0 )
		{
			//all seats are free (no subtraction)
			return 0;
		}
		else
		{
			//no seets available
			return 2;
		}
	}
	
	
	/*********************************
	 * SOLUTION 3
	 ********************************/
	
	/**
	 * @intuition
	 * 		the principle is the same as above
	 * 		but with less number of operations and therefore better use of bitmasking
	 * 		the big difference to the previous approach is that we use Or for the bitwise operation
	 * 
	 * @score
			Runtime: 32 ms, faster than 30.39% of Java online submissions for Cinema Seat Allocation.
			Memory Usage: 78.9 MB, less than 100.00% of Java online submissions for Cinema Seat Allocation.	 
			
	 * @time	o(n)
	 * @space	o(n)
	 * 
	 * @param n
	 * @param res
	 * @return
	 */
	public int maxNumberOfFamilies2(int n, int[][] res) {
		
		int ans = n* 2;
		HashMap<Integer, Integer> rowMap = new HashMap<>();

		int row = 0;
		int seat = 0;
		for (int i = 0; i < res.length; i++) {
			row = res[i][0];
			seat = res[i][1];
			rowMap.put(row, rowMap.getOrDefault(row, 0) | 1 << seat - 1);
		}

		int mask1 = 0b1000000001;//2 families
		int mask2 = 0b1000011111;//1 familly
		int mask3 = 0b1110000111;//1 familly
		int mask4 = 0b1111100001;//1 familly
		
		
		for (Integer k : rowMap.keySet())
		{
			seat = rowMap.get(k);
			if ((seat | mask1) == mask1)
			{
				//ans -= 0;
			} 
			else if ((seat | mask2) == mask2 || (seat | mask3) == mask3 || (seat | mask4) == mask4)
			{
				ans -= 1;
			}
			else
			{
				ans -= 2;
			}
			
		}
		
		return ans;
	}
	
	/*********************************
	 * SOLUTION 4
	 ********************************/

	/**
	 * @intuition
	 * 		the principle is the same as above
	 * 		but with less number of operations and therefore better use of bitmasking
	 * 		I added cache to avoid bitshifting every time
	 * 
	 * @score
			Runtime: 26 ms, faster than 57.56% of Java online submissions for Cinema Seat Allocation.
			Memory Usage: 78.8 MB, less than 100.00% of Java online submissions for Cinema Seat Allocation.

	 * @time	o(n)
	 * @space	o(n)
	 * 
	 * @param n
	 * @param res
	 * @return
	 */
	public int maxNumberOfFamilies(int n, int[][] res) {
		
		int ans = n* 2;
		HashMap<Integer, Integer> rowMap = new HashMap<>();

		//creating cache and avoiding allways shifting bits
		int [] cache = new int [11];
		
		for (int i = 1; i < cache.length; i++)
		{
			cache[i] = 1 << i - 1;
		}
		
		//creating row map/row seat allocation
		int row = 0;
		int seat = 0;
		for (int i = 0; i < res.length; i++) {
			row = res[i][0];
			seat = res[i][1];
			rowMap.put(row, rowMap.getOrDefault(row, 0) | cache[seat]);
		}

		//golden masks
		int mask1 = 0b1000000001;//2 families
		int mask2 = 0b1000011111;//1 familly
		int mask3 = 0b1110000111;//1 familly
		int mask4 = 0b1111100001;//1 familly
		
		//validation
		for (Integer k : rowMap.keySet())
		{
			seat = rowMap.get(k);
			if ((seat | mask1) == mask1)
			{
				//ans -= 0;
				continue;
			} 
			else if ((seat | mask2) == mask2 || (seat | mask3) == mask3 || (seat | mask4) == mask4)
			{
				ans -= 1;
			}
			else
			{
				ans -= 2;
			}
			
		}
		
		return ans;
	}
}
