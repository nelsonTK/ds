package com.data.structures.problems.contest;

import java.util.HashMap;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/detect-pattern-of-length-m-repeated-k-or-more-times´
 * EASY
 * 1566
 * @author Nelson Costa
 *
 */
public class DetectPatternofLengthMRepeatedKorMoreTimes extends LeetCodeExercise{
	static DetectPatternofLengthMRepeatedKorMoreTimes d = new DetectPatternofLengthMRepeatedKorMoreTimes();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] arr = stringToArray("[1,2,4,4,4,4]");
		int m = 1;
		int k = 3;
		System.out.println(d.containsPattern(arr, m, k));
	}
	

	 /*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * @intuition
	 * 		To have hashmap saving the codes for each position.
	 * 		then I go though the array and compare the i position with the i + m.
	 * 		So i compare if the next m character are equals tothe current if they are we add one to the count
	 * 
	 * @score
	 * 		Runtime: 13 ms, faster than 10.08% of Java online submissions for Detect Pattern of Length M Repeated K or More Times.
	 *		Memory Usage: 39.5 MB, less than 27.16% of Java online submissions for Detect Pattern of Length M Repeated K or More Times.
	 * 
	 * @fail
	 * 		1) I've failed in question interpretation
	 * 		2) then I fail in many implementation details
	 * 
	 * @param arr
	 * @param m
	 * @param k
	 * @return
	 */
	 public boolean containsPattern(int[] arr, int m, int k) {
	        
	        //index to String
	        HashMap<Integer, String> map = new HashMap<>();
	        //creating code mapping for indexes
	        for (int i = 0; i <= arr.length - m; i++)
	        {
	            String code = "";
	            for (int j = i; j < i + m && j < arr.length; j++)
	            {
	                code += arr[j] + ".";
	            }
	            
	            if (code != "")
	            {
	                System.out.println(code);
	                map.put(i, code);
	            }
	        }
	        
	        
	        int count = 1;
	        for (int i = 0; i <= arr.length - m; i++)
	        {
	            count = 1;
	            boolean stop = false;;
	            for (int j = i; j < arr.length && !stop; j+=m)
	            {
	                System.out.println("count" + count);
	                //System.out.println("map.get(j)" + map.get(j));
	                //System.out.println("j" + j);
	                if (map.get(j).equals(map.get(j + m)))
	                {
	                    count++;
	                    //System.out.println("count" + count);
	                    //System.out.println("j" + j);
	                    if (count >= k)
	                        return true;
	                }
	                else
	                {
	                    stop = true;
	                }
	            }
	            
	        }
	        
	        return false;
	    }
}
