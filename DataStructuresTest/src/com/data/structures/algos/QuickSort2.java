package com.data.structures.algos;

import com.data.structures.utils.PrintHelper;

public class QuickSort2 {

	/**
	 * My second implementation of quicksort
	 * @param array
	 */
	public static void quickSort(int [] array) {

		quickSort(array, 0, array.length - 1);
		System.out.print("Sorted Result > " );
		PrintHelper.printArray(array);
	}
	/**
	 * 
	 * My second implementation of quicksort
	 * 
	 * @param a array
	 * @param l
	 * @param r
	 */
	public static void quickSort(int [] a, int l, int r) {

		//Base Case
		if ( l >= r)
			return;

		//Set Values
		int pivot = l + ((r - l) >> 1);
		int fromLeft = l;
		int fromRight = r - 1;

		//Swap Pivot
		swap(a, pivot, r);
		pivot = r;

		//Swap right and left Val
		while (fromLeft <= fromRight)
		{
			while (a[fromLeft] < a[pivot] && fromLeft < r) {
				fromLeft++;
			}

			while (a[fromRight] > a[pivot] && fromRight > l) {
				fromRight--;
			}

			if (fromLeft < fromRight)
			{
				swap(a, fromLeft, fromRight);
			}
			else
			{
				break;
			}

		}

		//Swap pivot again
		if (fromLeft < r) {
			swap(a, fromLeft, pivot);
			pivot = fromLeft;

		}
		
		//Repeat for left and right site
		quickSort(a, pivot + 1, r);
		quickSort(a, l, pivot - 1);
	}
	

	private static void swap(int [] a, int i, int j)
	{
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

}
