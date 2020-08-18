package com.data.structures.algos;

import com.data.structures.utils.PrintHelper;

public class MergeSort {

	 /*********************************
	 * IMPLEMENTATION 1
	 ********************************/
	public static void mergeSort0(int [] array) {
		mergeArray0(array);
	}

	private static int[] mergeArray0(int [] array) {


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


			left = mergeArray0(left);

			right = mergeArray0(right);

			return mergeSort0(array, left, right);

		}

		return array;

	}

	private static int[] mergeSort0(int [] array, int [] left, int [] right){

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
	
	
	

	 /*********************************
	 * IMPLEMENTATION 2
	 ********************************/
	public static void mergeSort(int [] array) {

		PrintHelper.printArray(array);
		mergeSort(array, new int[array.length], 0, array.length - 1);
		PrintHelper.printArray(array);
	}

	private static void mergeSort(int[] array, int[] tmp, int leftStart, int rightEnd) {
		
		if (leftStart >= rightEnd)
		{
			return;
		}
		
		int mid = leftStart + (rightEnd - leftStart) / 2;

		mergeSort(array, tmp, leftStart, mid);
		mergeSort(array, tmp, mid+1, rightEnd);
		combine(array, tmp, leftStart, rightEnd);
	}

	private static void combine(int[] array, int[] tmp, int leftStart, int rightEnd) {
		int mid = leftStart + (rightEnd - leftStart) / 2;
		int rightStart = mid+1;
		int leftEnd = mid;
		
		int l = leftStart, r = rightStart, t = leftStart;
		
		while (l <= leftEnd && r <= rightEnd) {
			if(array[l]<=array[r])
			{
				tmp[t] = array[l];
				t++;
				l++;
			}
			else
			{
				tmp[t] = array[r];
				t++;
				r++;
			}
		}
		
		while (l <= leftEnd)
		{
			tmp[t] = array[l];
			t++;
			l++;
		}
		
		while (r <= rightEnd)
		{
			tmp[t] = array[r];
			t++;
			r++;
		}
		
		for (int i = leftStart; i <= rightEnd; i++)
		{
			array[i] = tmp[i];
		}
	}
	
}
