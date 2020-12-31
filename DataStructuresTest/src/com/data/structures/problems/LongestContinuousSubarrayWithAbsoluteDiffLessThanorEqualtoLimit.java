package com.data.structures.problems;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/submissions/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class LongestContinuousSubarrayWithAbsoluteDiffLessThanorEqualtoLimit {

    /**
     * @intuition
     * 		to use is to use monotonic queues for this problem
     * 		we use an increasing and decreasing monotonic queue to keep track of the current min and max
     * 		it was a mix between a queue and sliding windows solution
     * 		
     	while R < en
        
        add to min 
            while last > current
                remove
            add to min last
                
        add to max        
            while last > current
                remove last

            add to max last
                    
        if min - max <= limit  
            end++
        else
            start++
            if min < start
                remove
            if max < start remove
               
     * 
     * @score
     *     Runtime: 34 ms, faster than 51.01% of Java online submissions for Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit.
     *     Memory Usage: 49.2 MB, less than 55.08% of Java online submissions for Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit.
     *
     * @fail
     *
     * @time
     * 		O(N) 
     * 
     * @space
     * 
     **/
    public int longestSubarray(int[] nums, int limit) {
        // the fundamentals behind this questions is exactly to know what is the next biggest and the next lowest element
        // that's why you need a monotonic queue
    	
        ArrayDeque<Pair> min = new ArrayDeque<>();        
        ArrayDeque<Pair> max = new ArrayDeque<>();
        
        int L = 0, R = 0;
        
        int current, maxSize = 0;
        while (R < nums.length)
        {
            current = nums[R];
            
            //adding to the end of min
            while (min.size() != 0 && min.getLast().val > current 
                   || min.size() != 0 && min.getLast().index < L)
            {
                min.removeLast();
            }
            
            min.addLast(new Pair(current, R));
            
            //adding to the end of max
            while(max.size() != 0 && max.getLast().val < current 
                  || max.size() != 0 && max.getLast().index < L)
            {
                max.removeLast();
            }
            
            max.addLast(new Pair(current, R));
            
            //moving pointers
            if (max.getFirst().val - min.getFirst().val <= limit)
            {
                maxSize = Math.max(R - L + 1, maxSize);

                R++;
                
            }
            else
            {
                L++;
                
                while (min.size() != 0 && min.getFirst().index < L)
                {
                    min.removeFirst();
                }
                
                while (max.size() != 0 && max.getFirst().index < L)
                {
                    max.removeFirst();
                }
            }
        }
        
        return maxSize;
    }
    
    class Pair{
        int val;
        int index;
        
        Pair (int val, int index){
            this.val = val;
            this.index = index;
        }
    }
}

/**
 * Beautiful solution with the same logic than mine but with less validations and less code
 * he switches the validation of the last while
 * @author Nelson Costa
 *
 */
class LongestContinuousSubarrayWithAbsoluteDiffLessThanorEqualtoLimitSolution {

    
	   public int longestSubarray(int[] A, int limit) {
	       Deque<Integer> maxd = new ArrayDeque<>();
	       Deque<Integer> mind = new ArrayDeque<>();
	       int l=0;
	       int res=1;
	       for (int i=0;i<A.length;i++){
	           while (!maxd.isEmpty() && A[i]>maxd.peekLast()) maxd.pollLast();
	           while (!mind.isEmpty() && A[i]<mind.peekLast()) mind.pollLast();
	           maxd.addLast(A[i]);
	           mind.addLast(A[i]);
	           while (maxd.peek()-mind.peek()>limit){
	               if (A[l]==maxd.peek()) maxd.poll();
	               if (A[l]==mind.peek()) mind.poll();
	               l++;
	           }
	           res = Math.max(res, i-l+1);
	       }
	       return res;
	    }
	}
