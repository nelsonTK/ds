package com.data.structures.problems.contest;

import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/sort-array-by-increasing-frequency/
 * EASY
 * @author Nelson Costa
 *
 */
public class b38SortArraybyIncreasingFrequency {

	/**
	 * @intuition
	 * 		the intuition in here was to use a priority queue to sort the elements and then use a custom sort to solve the issue.
	 * 
	 * @time
	 * 		O(NLogN)
	 * 
	 * @param nums
	 * @return
	 */
    public int[] frequencySort(int[] nums) {
        
        HashMap<Integer, Integer> numCount = new HashMap<>();
        
        for (int i : nums)
        {
            numCount.put(i, numCount.getOrDefault(i, 0) + 1);
        }
        
        PriorityQueue<Freq> pq = new PriorityQueue<Freq>((a, b) -> {
            
            if (a.freq > b.freq)
                return 1;
            else if (a.freq < b.freq)
                return -1;
            else
                return Integer.compare(b.val, a.val);
            
        });
        
        for (int key : numCount.keySet())
        {
            pq.add(new Freq(key, numCount.get(key)));
        }
        
        int [] ans = new int [nums.length];
        int index = 0;
        Freq cur;
        while (!pq.isEmpty())
        {
            cur = pq.poll();
            
            for (int i = 0; i < cur.freq; i++)
            {
                ans[index] = cur.val;
                index++;
            }
        }
        
        return ans;
    }
    
    class Freq{
        int val;
        int freq;
        
        Freq(int v, int f)
        {
            val = v; 
            freq = f;
        }
    }
}
