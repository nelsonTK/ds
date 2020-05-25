package com.data.structures.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.data.structures.problems.ds.LeetCodeExercise;

public class Triangle extends LeetCodeExercise{

	static Triangle t = new Triangle();
	
	public static void main(String[] args) {
		List<Integer> l1 = Arrays.stream(stringToArray("[2]")).boxed().collect(Collectors.toList());
		List<Integer> l2 = Arrays.stream(stringToArray("[3,4]")).boxed().collect(Collectors.toList());
		List<Integer> l3 = Arrays.stream(stringToArray("[6,5,7]")).boxed().collect(Collectors.toList());
		List<Integer> l4 = Arrays.stream(stringToArray("[4,1,8,3]")).boxed().collect(Collectors.toList());
		
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		list.add(l1);
		list.add(l2);
		list.add(l3);
		list.add(l4);
		
		System.out.println(t.minimumTotal(list));
	}
	
	
	/**
	 *
	 *
    @intuition
		I start from top and end in the bottom.
		
			dp[r][c] = dp[r][c] + Min (dp[r - 1][c-1], dp[r - 1][c]);
			
			but in this case I use the input as dp matrix.
			
		I didn't try the solution from the bottom because I had a misconception that I only solved later in the problem.
		
    @score
		Runtime: 5 ms, faster than 21.66% of Java online submissions for Triangle.
		Memory Usage: 39.1 MB, less than 8.16% of Java online submissions for Triangle.
		
    @fail
    	1) very tough, the question wasn't clear about what they wanted and therefore I invested energy in something that was not asked. this is my second attempt
        2) bad interpretation, Initially I was creating a triangle using 3 positions. but only two were allowed.
        no many bad interpretations. It's a good question for interviewers
		
    @time   O(MxN)
    	
    @space  O(1)
    	
    @bcr    O(MxN)
		
	 **/
	public int minimumTotal(List<List<Integer>> triangle) {

		//guards
		if (triangle == null || triangle.size() == 0)
			return 0;


		List<Integer> prev, cur;
		int ans = triangle.get(0).get(0), rowMin;

		for (int r = 1; r < triangle.size(); r++)
		{
			ans = Integer.MAX_VALUE;
			prev = triangle.get(r - 1);
			cur = triangle.get(r);

			for (int c = 0; c < cur.size(); c++) {

				rowMin = Integer.MAX_VALUE;

				if (c - 1 >= 0 )
				{
					rowMin = Math.min(rowMin, prev.get(c - 1));
				}


				if (c < prev.size())
				{
					rowMin = Math.min(rowMin, prev.get(c));
				}

				cur.set(c, rowMin + cur.get(c));
				ans = Math.min(cur.get(c), ans);
			}
		}

		return ans;
	}

}



/*********************
* OTHERS SOLUTIONS
*********************/

/**
 * @timeTook 0ms
 * 
 * I Cannot understand the reason why this is the top solution.
 * 
 * @author Nelson Costa
 *
 */
class TriangleUnofficialSolution2 {
	
    public int minimumTotal(List<List<Integer>> triangle) {
        int min = Integer.MAX_VALUE;
        int rows = triangle.size();
        int cols = triangle.get(rows - 1).size();
        
        int[][] cache = new int[rows][cols];
        
        helper(triangle, cache, rows-1);
        
        int[] lastRowSum = cache[rows-1];
        
        for (int i=0; i<cols; i++) {
            min = Math.min(min, lastRowSum[i]);
        }
        
        return min;
    }
    
    public void helper(List<List<Integer>> triangle, int[][] cache, int row) {
        // base case
        if (row == 0) {
            if (cache[row][0] == 0) {
                cache[row][0] = triangle.get(0).get(0);
            }
            return;
        }
        
        // is previous row empty?
        // yes => recurse
        if (cache[row-1][0] == 0) {
            helper(triangle, cache, row-1);
        }
        
        // no => calculate
        int[] lastRowSum = cache[row-1];
        int[] currentRowSum = cache[row];
        List<Integer> curr = triangle.get(row);
        
        currentRowSum[0] = lastRowSum[0] + curr.get(0);
        for (int i=1; i<curr.size() - 1; i++) {
            currentRowSum[i] = Math.min(lastRowSum[i-1], lastRowSum[i]) + curr.get(i);
        }
        currentRowSum[curr.size() - 1] = lastRowSum[curr.size() - 2] + curr.get(curr.size() - 1);
    }
}

/**
 * @timeTook 1ms
 * @author Nelson Costa
 *
 */
class TriangleUnofficialSolution1 {
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle == null || triangle.size() == 0) {
            return 0;
        }
        
        if(triangle.size() == 1) {
            return triangle.get(0).get(0);
        }
        int k = triangle.size();
        int[] sums = new int[k];
        
        //filling last row
        for(int j = 0; j < k; j++) {
            sums[j] = triangle.get(k - 1).get(j);
            //System.out.println(sums[j]);
        }
        
        //Apply formula to each node
        for(int i = k - 2; i >= 0; i--) {
            for(int j = 0; j <= i; j++) {
                sums[j] = Math.min(sums[j], sums[j + 1]) + triangle.get(i).get(j);
                //System.out.println(sums[j] + "j="+j);
            }
        }
        return sums[0];
    }
}