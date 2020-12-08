package com.data.structures.problems;

import java.util.HashMap;

import com.data.structures.problems.ds.TreeNode;

/**
 * https://leetcode.com/problems/path-sum-iii
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class PathSumIII {

    HashMap<Integer, Integer> nCount;
    
    /**
     * @intuition
     * 		When you dominate prefix sum, it turns out to be obvious how to solve this problem.
     * 		It is about understanding about the difference between two comulative points
     * 
     * 		it is about understanding that the answer lies in the sum of all the situations where the current sum is equals to the target
     * 
     * 		and the current - target exists in a hashmap, if it exists it means there is a sum in the middle that sums up to the target, it is indeed very simple.
     * 	
     * 
     * 		REPEATING NOTE:
     * 
     * 			
     *   	If currentSum -  target exists in the hashmap, then it means that we have N number of ways to achieve a target Sum. just write in paper and you will see
     *       
     *   	image explayning how the prefix sum works to solv this exercise 
     *   	https://imgur.com/4obhb3C
     *   
     *   
     *   
     * @score
     * 		Runtime: 2 ms, faster than 99.98% of Java online submissions for Path Sum III.
	 *		Memory Usage: 39.1 MB, less than 22.53% of Java online submissions for Path Sum III.	
     * 
     * @fail
     * 		1) forgot to do backtracking
     * 
     * @media
     * 		https://imgur.com/a/7s97fVh
     * 		
     * 
     * @time
     * 		O(N)
     * 
     * @space
     * 		O(1)
     * 
     * @param root
     * @param k
     * @return
     */
    public int pathSum(TreeNode root, int k) {
        nCount = new HashMap<>();
        
        return find(root, k, 0);
    }
    
    private int find(TreeNode node, int k, int prefix)
    {
        int count = 0;
        
        if (node == null)
            return count;
        
        prefix += node.val;
        
        //search from path from root
        if (prefix == k)
        {
            count++;
        }
        
        //search for middle paths
        count += nCount.getOrDefault( prefix - k, 0 );
        
        //update current counter
        nCount.put(prefix, nCount.getOrDefault(prefix, 0) + 1);
        
        //search left
        count += find(node.left, k, prefix);
        
        
        //search right
        count += find(node.right, k, prefix);
        
        
        //remove element from hashMap
        //I realize that I could have keeped the element in the hashmap but it was okay
        if (nCount.get(prefix) > 1 )
        {
            nCount.put(prefix, nCount.get(prefix) - 1);
        }
        else
        {
            nCount.remove(prefix);
        }
        return count;
    }
}
