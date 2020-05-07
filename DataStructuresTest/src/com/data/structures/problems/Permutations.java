package com.data.structures.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.data.structures.performance.BenchMark;
import com.data.structures.performance.BenchMarkInput;
import com.data.structures.performance.BenchMarkInputFactory;
import com.data.structures.performance.FunctionInputIntArray;
import com.data.structures.problems.ds.LeetCodeExercise;

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
public class Permutations extends LeetCodeExercise{
	private static List<List<Integer>> list = new ArrayList<List<Integer>>();


	public static void main(String[] args) {
		
		Permutations p = new Permutations();
//		p.permute(new int[] {1,2,3});
		
		p.benchMark();
	}
	
	public void benchMark() {
		Permutations p = new Permutations();
		BenchMark b = new BenchMark();
		BenchMarkInputFactory<FunctionInputIntArray> factory = new BenchMarkInputFactory<>();
		List<BenchMarkInput<FunctionInputIntArray>> benchmarkList = new ArrayList<>();
		
		benchmarkList.add(factory.create(p::permute, "permute"));
		benchmarkList.add(factory.create(p::permute0, "permute0"));
		b.benchMarkFunctionInputIntArray(benchmarkList, 0, 10, 10, 2);
		
	}
	
	/*********************************
	 * SOLUTION 1
	 ********************************/
	public List<List<Integer>> permute(int[] nums) {
		permute(nums, 0);
		return list;
	}

	/**
	 * @timecomplexity O(N!)
	 * @param nums 	
	 * @param start
	 */
	private void permute(int [] nums, int start)
	{
		if (nums == null)
			return;

		if (start >= nums.length)
		{
			list.add(copyArrayToList(nums));
			return;
		}

		for (int i = start; i < nums.length; i++) {
			swap(nums, start, i);
			permute(nums, start + 1);
			swap(nums, start, i);
		}
	}


	private List<Integer> copyArrayToList(int [] nums){
		List<Integer> nList = new ArrayList<Integer> ();

		for (int i = 0; i < nums.length; i++)
		{
			nList.add(nums[i]);
		}

		return nList;
	}
	
	/**
	 * 
	 * @param nums
	 * @param i element to swap position index
	 * @param j element to swap position index
	 */
	private void swap(int [] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;		
	}	
	
	/*********************************
	 * SOLUTION 2
	 ********************************/
	
	/**
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> permute0(int[] nums) {

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
	private void permute(List<Integer> nums, int start)
	{
		if (nums == null)
			return;

		if (start >= nums.size())
		{
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
	private void swap(List<Integer> nums, int i, int j) {
		Collections.swap(nums, i, j);
	}	

	private List<Integer> copyList(List<Integer> list) {
		List<Integer> nList = new ArrayList<Integer>();

		for (Integer integer : list) {
			nList.add(integer);
		}

		return nList;
	}
}
