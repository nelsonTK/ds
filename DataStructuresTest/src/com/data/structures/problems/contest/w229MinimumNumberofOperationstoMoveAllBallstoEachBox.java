package com.data.structures.problems.contest;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/minimum-number-of-operations-to-move-all-balls-to-each-box/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class w229MinimumNumberofOperationstoMoveAllBallstoEachBox {

	/**
	 * the fundamental thing was that the moves you need to do for each one is equal to the distance to the current position
	 * @param boxes
	 * @return
	 */
    public int[] minOperations(String boxes) {
        
        List<Integer> ones = new ArrayList<>();
        int [] ans = new int[boxes.length()];
        for (int i = 0; i < boxes.length();i++)
        {
            if(boxes.charAt(i) == '1')
            {
                ones.add(i);
            }
        }
        
        int sum;
        for (int i = 0; i < boxes.length();i++ )
        {
            sum = 0;
            for(int idx : ones)
            {
                sum += Math.abs(idx - i);
            }
            ans[i] = sum;
        }
        
        return ans;
    }
}
