package com.data.structures.problems.contest;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/mean-of-array-after-removing-some-elements/
 * Q1
 * EASY
 * @author Nelson Costa
 *
 */
public class b37MeanofArrayAfterRemovingSomeElements {
    public double trimMean(int[] arr) {
        Arrays.sort(arr);
        int percent = (int) Math.round(arr.length * 0.05);
        int n = 0;
        int sum = 0;
        for (int i = percent; i < arr.length - percent; i++)
        {
            n++;
            sum += arr[i];
        }
        
        return (double)sum/n;
    }
}
