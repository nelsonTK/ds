package com.data.structures.problems;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


    List<List<Integer>> ans = new ArrayList<List<Integer>>();
    /**
     * 	@intuition
     * 		is a backtracking solution to get all the combinationsS
     * 
     * 	@score
     * 
     * 		NON OPTIMIZED - I WAS USING index variable as index and decreasing it each time
					Runtime: 9 ms, faster than 25.48% of Java online submissions for Combination Sum.
					Memory Usage: 42.1 MB, less than 15.77% of Java online submissions for Combination Sum.
			
			OPTIMIZED VERSION	
				Runtime: 3 ms, faster than 85.29% of Java online submissions for Combination Sum.
				Memory Usage: 40 MB, less than 56.31% of Java online submissions for Combination Sum.
     * 
     *  @fail 
     *  	1) forgot about creating a new object list for each array
     *  	2) removing the wrong object
     *  
     *  @time 
     *  	Combinations Formula
     *  
     *  @space
     *  	Combinations Formula
    **/
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        
        solve(candidates, target, 0, 0, new ArrayList<Integer>());
        
        return ans;
    }
    
    public void solve(int [] cand, int target, int index, int sum, List<Integer> cur)
    {
        if (sum == target)
        {
            ans.add(new ArrayList<Integer>(cur));
            return;
        }
        else if (sum > target)
        {
            return;
        }
        
        for (int i = index; i < cand.length; i++)
        {
            cur.add(cand[i]);
            solve(cand, target, i, sum + cand[i], cur);
            cur.remove(cur.size() - 1);
        }
    }
}


/**
 * 
 * @author Nelson Costa
 *
 */
class CombinationSumSolution1 {
    
    List<List<Integer>> result = new ArrayList<>();
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        traverse(candidates, 0, new ArrayList<>(), target);
        return result;
    }
    
    public void traverse(int[] candidates, int index, List<Integer> list, int rem) {
        if(rem == 0) {
            result.add(new ArrayList<>(list));
            return;
        }
        
        for(int i=index;i<candidates.length;i++) {
            int candidate = candidates[i];
            if(candidate <= rem) {
                list.add(candidate);
                traverse(candidates, i, list, rem - candidate);
                list.remove(list.size()-1);
            }
        }
    }
}
