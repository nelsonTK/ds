package com.data.structures.problems;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 961
 * https://leetcode.com/problems/n-repeated-element-in-size-2n-array/
 * EASY
 * @author Nelson Costa
 *
 */
public class NRepeatedElementInSize2nArray {

	/**
	 * 1) Tenho de compreender que isto demora o seu tempo, ,
	 * e vou demorar tempo a masterizar, 
	 * vou ter de continuar a divertir-me e ir trabalhandoo ao mesmo tempo 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] array = {1,2,3,3};
		int [] array2 = {1,3,2,4,4,4};
		System.out.println(repeatedNTimes0(array));
		System.out.println(repeatedNTimes0(array2));

		System.out.println(repeatedNTimes1(array));
		System.out.println(repeatedNTimes1(array2));

		System.out.println(repeatedNTimes(array));
		System.out.println(repeatedNTimes(array2));

		System.out.println(new NRepeatedElementInSize2nArraySolution1().repeatedNTimes(array));
		System.out.println(new NRepeatedElementInSize2nArraySolution2().repeatedNTimes(array2));

	}

	/*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * Estou a usar o HashMap para fazer lookup dos dados, é mesmo para isto que o hashmap serve. é de caras
	 * Alternativas? todas muito complicadas sem usar hashmap
	 * 
	 * FEEDBACK
	 * 		Runtime: 18 ms, faster than 17.25% of Java online submissions for N-Repeated Element in Size 2N Array.
			Memory Usage: 42.2 MB, less than 8.64% of Java online submissions for N-Repeated Element in Size 2N Array.
	 * 
	 * https://leetcode.com/problems/n-repeated-element-in-size-2n-array/
	 */
	public static int repeatedNTimes0(int [] A) {
		if (A == null || A.length == 0)
			return -1;

		Map<Integer, Integer> CountsPerElement = new HashMap<>();
		int counts = 0;
		int n = (A.length/2);
		int element = -1;

		for (int i : A) {

			if (CountsPerElement.get(i) != null)
			{
				CountsPerElement.put(i, CountsPerElement.get(i) + 1);
			}else
			{
				CountsPerElement.put(i, 1);
			}

			counts = CountsPerElement.get(i);

			if (counts == n)
			{
				element = i;
				break;
			}

			counts = 0;
		}

		return element;
	}

	/*********************************
	 * SOLUTION 2
	 ********************************/
	/**
	 * FEEDBACK
	 * Runtime: 20 ms, faster than 14.61% of Java online submissions for N-Repeated Element in Size 2N Array.
			Memory Usage: 41.6 MB, less than 19.75% of Java online submissions for N-Repeated Element in Size 2N Array.
	 * @param A
	 * @return
	 */
	public static int repeatedNTimes1(int[] A) {

		Map<Integer, Integer> countElement = new HashMap<>();
		Map<Integer, Integer> elementCount = new HashMap<>();
		Integer current = null;
		int n = A.length/2;

		for(int i : A)
		{
			current = elementCount.get(i);
			if (current != null)
			{
				elementCount.put(i, current + 1);
				countElement.put(current + 1, i);
			}else
			{
				elementCount.put(i, 1);
				countElement.put(1, i);
			}
		}

		return countElement.get(n);
	}

	/*********************************
	 * SOLUTION 3
	 ********************************/
	/**
	 * FEEDBACK
			Runtime: 16 ms, faster than 20.70% of Java online submissions for N-Repeated Element in Size 2N Array.
			Memory Usage: 41.8 MB, less than 18.52% of Java online submissions for N-Repeated Element in Size 2N
	 * @param A
	 * @return
	 */
	public static int repeatedNTimes(int [] A) {
		if (A == null || A.length == 0)
			return -1;

		Map<Integer, Integer> CountsPerElement = new HashMap<>();
		int n = (A.length/2);
		int element = -1;
		Integer current = null;

		for (int i : A) {
			current = CountsPerElement.get(i);
			if (current != null)
			{
				CountsPerElement.put(i, current + 1);
			}else
			{
				CountsPerElement.put(i, 1);
			}

			current = CountsPerElement.get(i);

			if (current == n)
			{
				element = i;
				break;
			}
		}

		return element;
	}
}

/**********************
 * OTHERS SOLUTIONS 
 **********************/

/**
 * solução alternativa com sets... melhor que a minha
 * 	 		Ref: https://www.youtube.com/watch?v=otJDbG_os8s
 * 
 * 
 * 		FEEDBACK
		Runtime: 0 ms, faster than 100.00% of Java online submissions for N-Repeated Element in Size 2N Array.
		Memory Usage: 41.9 MB, less than 14.81% of Java online submissions for N-Repeated Element in Size 2N Array.
 * @param A
 * @return
 */
class NRepeatedElementInSize2nArraySolution1{
	public int repeatedNTimes(int[] A) {
		var container = new HashSet<>();
		for (var digit : A) {
			if (container.contains(digit))
			{
				return digit;	
			}
			container.add(digit);
		}
		return 0;
	}
}



class NRepeatedElementInSize2nArraySolution2 {
	/**
	 * Solução com Counters algo que faz uma iteração das pontas para o centro.
	 * A Solução criou um Counter para java. fiquei muito impressionado, deixou de usar o hasmap desta forma.
	 * 		Python Counter. Counter is an unordered collection where elements are stored as Dict keys and their count as dict value. 
 		Ref: https://www.youtube.com/watch?v=otJDbG_os8s

 		FEEDBACK
			Runtime: 1 ms, faster than 53.75% of Java online submissions for N-Repeated Element in Size 2N Array.
			Memory Usage: 41.8 MB, less than 18.52% of Java online submissions for N-Repeated Element in Size 2N Array.
	 * @param A
	 * @return
	 */
	public int repeatedNTimes(int [] A) {
		// 4 <= A.length <= 10000, determina o tamanho do array para 10.001
		// 0 <= A[i] < 10000
		// A.length is even

		var counter = new int [10_001];
		for (int low = 0, high = A.length - 1; low < high; low++, high--) {
			counter[A[low]]++;
			counter[A[high]]++;

			if(counter[A[low]] > 1)
				return A[low];
			if(counter[A[high]] > 1)
				return A[high];
		}
		return 0;
	}
}