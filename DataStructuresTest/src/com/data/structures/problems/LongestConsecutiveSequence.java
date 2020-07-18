package com.data.structures.problems;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import com.data.structures.performance.BenchMark;
import com.data.structures.performance.BenchMarkInput;
import com.data.structures.performance.BenchMarkInputFactory;
import com.data.structures.performance.FunctionInputIntArray;
import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/longest-consecutive-sequence/
 * HARD
 * @author Nelson Costa
 *
 */
public class LongestConsecutiveSequence extends LeetCodeExercise{
	static LongestConsecutiveSequence l = new LongestConsecutiveSequence();	
	static LongestConsecutiveSequenceSolution3 l2 = new LongestConsecutiveSequenceSolution3();
	static LongestConsecutiveSequenceUnofficialSolution l3 = new LongestConsecutiveSequenceUnofficialSolution();
	
	public static void main(String[] args) {
		int [] nums = stringToArray("[100, 4, 200, 1, 3, 2]");
		l.longestConsecutive(nums);
		l2.longestConsecutive(nums);
		l.benchMark(); //Comparing my functions performance with others function
		
	}
	
	@Override
	public void benchMark() {
		super.benchMark();

		LongestConsecutiveSequence my = new LongestConsecutiveSequence();	
		LongestConsecutiveSequenceSolution3 oth = new LongestConsecutiveSequenceSolution3();
		LongestConsecutiveSequenceUnofficialSolution topsolution = new LongestConsecutiveSequenceUnofficialSolution();
		BenchMark b = new BenchMark();
		BenchMarkInputFactory<FunctionInputIntArray> factory = new BenchMarkInputFactory<>();
		List<BenchMarkInput<FunctionInputIntArray>> benchmarkList = new ArrayList<>();
		
		benchmarkList.add(factory.create(my::longestConsecutive, "my-longestConsecutive"));
		benchmarkList.add(factory.create(oth::longestConsecutive, "oth-longestConsecutive"));
		benchmarkList.add(factory.create(oth::longestConsecutive, "top-longestConsecutive"));
		b.benchMarkFunctionInputIntArray(benchmarkList, -1000000, 1000000, 1000000000, 2);
		
	}
	/*********************************
	 * SOLUTION 1
	 ********************************/
	/** 
	 * 
	 * @intuition
	 * 		traverse the array once than for each key check 
	 * 		
	 * 		if we have a the element up and the element below and add to a queue, 
	 * 		
	 * 		do that untill all elements are processed. mark the enqueue entries as visited. 
	 * 		
	 * 		as you process each key increase the sequence number. 
	 * 
	 * 		in the end compare that streak of sequence numbers with the maximum sequence to date.
	 * 
	 * @score
	 * 		Runtime: 7 ms, faster than 36.88% of Java online submissions for Longest Consecutive Sequence.
			Memory Usage: 40.1 MB, less than 44.82% of Java online submissions for Longest Consecutive Sequence.
	 * 
	 * 
	 * @fail   
	 * 		1) null pointer exception on runtime, because of the comparison between null and false
	 * 
	 * @time 	O(N)
	 * 
	 * @space 	O(N)
	 * 
	 **/
	public int longestConsecutive(int[] nums) {

		//guards
		//duplicates
		//empty
		//single element

		if (nums == null || nums.length == 0)
			return 0;

		HashMap<Integer, Boolean> visited = new HashMap<>();
		Queue<Integer> q = new ArrayDeque<>();

		//Fill visited map
		for (int i : nums)
		{
			visited.put(i, false);
		}

		int cur;
		int curSeq;
		int ans = 0;
		for (int key : visited.keySet())
		{
			if (!visited.get(key))
			{
				q.add(key);
				curSeq = 0;
				visited.put(key, true);

				while (!q.isEmpty())
				{
					cur = q.poll();
					curSeq++;

					if (visited.get(cur+1) != null && visited.get(cur+1) == false)
					{
						q.add(cur+1);
						visited.put(cur+1, true);
					}


					if (visited.get(cur-1) != null && visited.get(cur-1) == false)
					{
						q.add(cur-1);
						visited.put(cur-1, true);
					}
				}

				ans = Math.max(curSeq, ans);
			}
		}

		return ans;
	}
}



/*********************
* OTHERS SOLUTIONS
*********************/
/**
 * This solution is cleaver and uses less resources than mine, but is the same time and space complexity
 * 
 * @intuition
 * 		What they do is to find always the start of a sequence and going up from there.
 * 
 * 		they way they do it is by checking if there is a number below the current number, 
 * 
 * 		if there isn't than it can only be a beginning of a sequence.
 * 
 * 		
 * @author Nelson Costa
 *
 */
class LongestConsecutiveSequenceSolution3 {
    public int longestConsecutive(int[] nums) {
        Set<Integer> num_set = new HashSet<Integer>();
        for (int num : nums) {
            num_set.add(num);
        }

        int longestStreak = 0;

        for (int num : num_set) {
            if (!num_set.contains(num-1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (num_set.contains(currentNum+1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }
}

/**
 * Simplem Sorting solution
 * Time complexity NLogN
 * However in the benchmark it behaves like a O(N)
 * @author Nelson Costa
 *
 */
class LongestConsecutiveSequenceUnofficialSolution {
    public int longestConsecutive(int[] nums) {
        Arrays.sort(nums);
        
        // System.out.println(Arrays.toString(nums));
        
        int i = 0, j = 0;
        int ans = 0;
        while (j < nums.length) {
            int curr_ans = 1;
            while (j + 1 < nums.length && nums[j+1] <= nums[j]+1) {
                if (nums[j+1] == nums[j]+1) {
                    curr_ans += 1;
                }
                j += 1;
            }
            // System.out.println(i+":"+j+":"+curr_ans);
            ans = Math.max(ans, curr_ans);
            j += 1;
            i = j;
        }
        
        return ans;
    }
}