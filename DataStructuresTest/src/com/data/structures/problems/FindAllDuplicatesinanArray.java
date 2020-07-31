package com.data.structures.problems;

import java.util.ArrayList;
import java.util.List;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/find-all-duplicates-in-an-array/
 * @author Nelson Costa
 *
 */
public class FindAllDuplicatesinanArray extends LeetCodeExercise{

	static FindAllDuplicatesinanArray f = new FindAllDuplicatesinanArray();
	static FindAllDuplicatesinanArraySolution4 f2 = new FindAllDuplicatesinanArraySolution4();
	public static void main(String[] args) {
		int [] n = stringToArray("[4,3,2,7,8,2,3,1]");
		for(int i : f.findDuplicates(n))
		{
			System.out.println(i);
		}
		for(int i : f2.findDuplicates(n))
		{
			System.out.println(i);
		}
	}

	/*********************************
	 * SOLUTION 1
	 ********************************/
	/** 
	 * 
	 * 
	 * @intuition
	 * 		jump to the right index, if it is already occupied we mark it  as used.
	 * 		if not we place it in the correct place.
	 * 
	 * @score
	 * 		Runtime: 8 ms, faster than 56.36% of Java online submissions for Find All Duplicates in an Array.
	 *		Memory Usage: 63.7 MB, less than 5.24% of Java online submissions for Find All Duplicates in an Array.
	 * 
	 * 
	 * @fail 
	 * 		1) infinite loop caused by not updating the index
	 * 		2) infinit loop caused by comparing the cur - 1 with num[index] instead of cur...
	 * 
	 * @time 
	 * 		O(N)
	 * 
	 * @space
	 * 		O(1)
	 * 
	 **/
	public List<Integer> findDuplicates(int[] nums) {

		List<Integer> ans = new ArrayList<Integer>();

		int cur, tmp;   
		for (int i = 0; i < nums.length; i++)
		{
			cur = nums[i];

			if ((cur-1) != i && cur != -1)
			{
				tmp = nums[i];
				nums[i] = -1;
				cur = tmp;
				int newIndex = cur-1;
				//if next target is not done
				while (cur > -1)
				{
					if (cur != nums[newIndex])
					{
						tmp = nums[newIndex];
						nums[newIndex] = cur;
						cur = tmp; 
						newIndex = cur-1;
					}
					else
					{
						ans.add(nums[newIndex]);
						cur = -1;
					}   
				}

			}
		}

		return ans;

	}
}



/*********************
* OTHERS SOLUTIONS
*********************/
/**
 * Very cleaver solution, basically what this guys do it to make the right position with -1, 
 * and if they ever find that position with -1 it means that it is a duplicate
 * 
 * the absolute is used because you can be at the position 0, and have an element pointing to the the second position.
 * That will cause position 2 to have -1. the math says that if you subtrack -1 to -1, you get -2.
 * which is out of boundaries. with absolute you always go to the first position in the array.
 * 
 * Very simple 
 * @author Nelson Costa
 *
 */
class FindAllDuplicatesinanArraySolution4 {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> ans = new ArrayList<>();

        for (int num : nums) {
            if (nums[Math.abs(num) - 1] < 0) { // seen before
                ans.add(Math.abs(num));
            }
            nums[Math.abs(num) - 1] *= -1;
        }

        return ans;
    }
}
