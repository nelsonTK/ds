package com.data.structures.main;

import com.data.structures.algos.AlgoPractice;
import com.data.structures.algos.MergeSort1;
import com.data.structures.algos.QuickSort3;
import com.data.structures.utils.PrintHelper;

public class AlgorithmsTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] array = AlgorithmsTest.createArray(8);
		//bubbleSortTest(array);
		//mergeSortTest(array);
//		AlgoPractice.MergeSortFirst(array);
//		AlgoSolutions.mergersort(array);
		//AlgoPractice.quickSort(array);
		PrintHelper.printArray(array);
		QuickSort3.quickSort(array);
		//Collections.swap(new ArrayList<String>(), 0, 4); 
	}

	public static void mergeSortTest(int [] array) {
		//Algorithm.MergeSort(array);
		MergeSort1.MergeSort(array);
	}

	public static void bubbleSortTest(int array[]) {
		System.out.println("Before Sorting");
		PrintHelper.printArray(array);

		AlgoPractice.bubblesort(array);

		System.out.println("After Sorting");
		PrintHelper.printArray(array);
	}

	private static int[] createArray(int size) {

		int [] array;
		switch (size) {
		case 4:
			int [] tmp4 = {2,1,4,0};//3,1,2,0};
			array = tmp4;
			break;
		case 6:
			int [] tmp6 = {64, 34, 25, 12, 22, 90}; 
			array = tmp6;
			break;
		case 8:			
			int [] tmp8 = {3,1,2,0,6,4,5,7};
			array = tmp8;
			break;
		case 11:
			int [] tmp11 = {64, 34, 25, 52, 22, 22, 11, 11, 90,7, 9}; 
			array = tmp11;
			break;
		default:
			int [] tmpd = {3,1,2,0};
			array = tmpd;
			break;
		}

		return array;
	}
}
