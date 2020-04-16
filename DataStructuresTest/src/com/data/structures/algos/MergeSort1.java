package com.data.structures.algos;

import com.data.structures.utils.PrintHelper;

public class MergeSort1 {


	/**
	 * My First Merge Sort from scratch
	 * @return
	 */
	public static void MergeSort(int [] array) {
		MergeArray(array);
	}

	private static int[] MergeArray(int [] array) {


		if (array.length > 1) {

			int middleIndex = (array.length) /2;
			int [] left = new int[middleIndex];
			int [] right = new int[array.length - middleIndex];


			for (int i = 0; i < middleIndex; i++)
			{
				left[i] = array[i];
			}

			//right
			for (int i = middleIndex, j = 0;i < array.length; j++, i++)
			{
				right[j] = array[i];
			}


			left = MergeArray(left);

			right = MergeArray(right);

			return MergeSort(array, left, right);

		}

		return array;

	}

	private static int[] MergeSort(int [] array, int [] left, int [] right){

		int [] merged = new int[array.length];

		//Ordenar Arrays
		System.out.print(">> left  >> ");
		PrintHelper.printArray(left);

		System.out.print(">> right >> ");
		PrintHelper.printArray(right);


		//Merge dos Arrays (Ajuda usar um caderninho para apontar as iterações)
		int i = 0, j = 0;

		while (i + j < array.length && j < right.length && i < left.length)
		{
			if (left[i] > right[j])
			{
				merged[i + j] = right[j];
				j++;
			} 
			else if (left[i] <= right[j]) {
				merged[i + j] = left[i];
				i++;
			}			
		}

		while (j < right.length) //(i >= left.length && [Talvez seja excessivo]
		{
			merged [i + j] = right[j];
			j++;
		}

		while (i < left.length) //j >= right.length && [talvez seja excessivo/desnecessário]
		{
			merged [i + j] = left[i];
			i++;
		}

		System.out.print(">> Merged Array >> ");
		PrintHelper.printArray(merged);
		System.out.println();

		return merged;
	}
}
