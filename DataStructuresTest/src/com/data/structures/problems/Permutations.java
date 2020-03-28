package com.data.structures.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/permutations/
 * MEDIUM
 * @author Nelson Costa
 * @date 2020/03/17


 * Otimização 2:
 * 		Runtime: 1 ms, faster than 92.22% of Java online submissions for Permutations.
			Memory Usage: 41.5 MB, less than 5.68% of Java online submissions for Permutations.
 * 
 * 		Descrição Otimização:
 * 			Removi os constantes casts de integer para int e desta forma ganhei tempo
 * 
 * Otimização 1:
 * 		Runtime: 2 ms, faster than 50.38% of Java online submissions for Permutations.
			Memory Usage: 41.7 MB, less than 5.68% of Java online submissions for Permutations.

			Descrição da otimização:
				Usar o meu próprio metodo de cópia do array

 * Sem otimizações:
 * 		Runtime: 17 ms, faster than 6.97% of Java online submissions for Permutations.
			Memory Usage: 41.6 MB, less than 5.68% of Java online submissions for Permutations.
 *
 *	Faz real diferença quando estamos a fazer casts de int para Integer, neste caso ao passar essas operações para o final ganhei 1 ms
 */
public class Permutations {
	private static List<List<Integer>> list = new ArrayList<List<Integer>>();


	public static void main(String[] args) {
		permute(new int[] {1,2,3});
	}


	public static List<List<Integer>> permute(int[] nums) {
		permute(nums, 0);
		return list;
	}

	/**
	 * @timecomplexity O(N!)
	 * @param nums 	
	 * @param start
	 */
	private static void permute(int [] nums, int start)
	{
		if (nums == null)
			return;

		if (start >= nums.length)
		{
			System.out.println(Arrays.toString(nums));

			list.add(copyArrayToList(nums));
			return;
		}

		for (int i = start; i < nums.length; i++) {
			swap(nums, start, i);
			permute(nums, start + 1);
			swap(nums, start, i);
		}
	}


	private static List<Integer> copyList(List<Integer> list) {
		List<Integer> nList = new ArrayList<Integer>();

		for (Integer integer : list) {
			nList.add(integer);
		}

		return nList;
	}

	private static List<Integer> copyArrayToList(int [] nums){
		List<Integer> nList = new ArrayList<Integer> ();

		for (int i = 0; i < nums.length; i++)
		{
			nList.add(nums[i]);
		}

		return nList;
	}
	

	/**
	 * @param nums
	 * @return
	 */
	public static List<List<Integer>> permuteold(int[] nums) {

		List<Integer> input = Arrays
				.stream(nums)
				.boxed()
				.collect(Collectors.toList());
		permute(input, 0);

		return list;
	}
	
	/**
	 * @timecomplexity O(N!)
	 * @param nums 	
	 * @param start
	 */
	private static void permute(List<Integer> nums, int start)
	{
		if (nums == null)
			return;

		if (start >= nums.size())
		{
			System.out.println(Arrays.toString(nums.toArray()));
			//			ArrayList<Integer> clone = (ArrayList<Integer>) nums
			//					.stream()
			//					.map( x -> Integer.valueOf(x))
			//					.collect(Collectors.toList());	

			list.add(copyList(nums));
			return;
		}

		for (int i = start; i < nums.size(); i++) {
			swap(nums, start, i);
			permute(nums, start + 1);
			swap(nums, start, i);
		}
	}

	/**
	 * 
	 * @param nums
	 * @param i element to swap position index
	 * @param j element to swap position index
	 */
	private static void swap(List<Integer> nums, int i, int j) {
		Collections.swap(nums, i, j);
	}	

	/**
	 * 
	 * @param nums
	 * @param i element to swap position index
	 * @param j element to swap position index
	 */
	private static void swap(int [] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;		
	}	
}
