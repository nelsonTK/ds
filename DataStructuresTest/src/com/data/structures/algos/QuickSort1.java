package com.data.structures.algos;

import com.data.structures.utils.PrintHelper;

public class QuickSort1 {


	public static void quickSort(int [] array) {

		quickSortDraft(array, 0, array.length - 1);
		System.out.print("Sorted Result > " );
		PrintHelper.printArray(array);
	}

	public static void quickSortDraft(int [] array, int leftStart, int rightEnd)
	{		
		System.out.print("Array > ");
		PrintHelper.printArray(array, leftStart, rightEnd);


		//se o tamanho é 1, pára
		if (rightEnd - leftStart + 1 <= 1)
		{
			int printpivot = (rightEnd - leftStart)/2 + leftStart;
			System.out.println("Basecase > " + "[" + printpivot + "]");
			PrintHelper.printHeader();
			return;
		}

		int leftIndex = leftStart, rightIndex = rightEnd;
		boolean leftFound = false, rightFound = false;
		int pivot = (rightEnd - leftStart)/2 + leftStart;
		int tmp = 0;

		System.out.println("Pivot > " + "[" + array[pivot] + "]");


		//initial swap
		tmp = array[pivot];
		array[pivot] = array[rightEnd];
		array[rightEnd] = tmp;
		pivot = rightEnd;

		PrintHelper.printArray(array, leftStart, rightEnd);

		while (leftIndex <= rightIndex) {

			leftIndex = leftStart;
			rightIndex = rightEnd;
			leftFound = false;
			rightFound = false;

			//find left
			while (leftIndex < array.length  && !leftFound)
			{
				if(array[leftIndex] <= array[pivot])
				{
					leftIndex++;
				}
				else
				{
					leftFound = true;
				}
			}

			//find right
			while (rightIndex >= 0 && !rightFound) {

				if(array[rightIndex] >= array[pivot])
				{
					rightIndex--;
				}
				else
				{
					rightFound = true;
				}
			}

			//swap values
			if (leftIndex <= rightIndex && leftIndex <= rightEnd) {
				//swap left for right
				tmp = array[leftIndex];
				array[leftIndex] = array[rightIndex];
				array[rightIndex] = tmp;
			}
			else if ( leftIndex <= rightEnd && rightIndex >= 0)
			{
				//swap pivot for left
				tmp = array[leftIndex];
				array[leftIndex] = array[pivot];
				array[pivot] = tmp;

				pivot = leftIndex;
			}
			else if (rightIndex < 0 && leftIndex <= rightEnd){
				System.out.println("este caso talvez nunca aconteça porque acho que se baseia num cenário impossível, caso no caderno. que é quando temos por exemplo [4][2] -> 4 Lindex, 2 é Pivot, RIndex não tem elemento e portanto temos de trocar 4 com o 2");
				//swap data
				tmp = array[pivot];
				array[pivot] =  array[leftIndex];
				array[leftIndex] = tmp;

				//swap indices
				tmp = pivot;
				pivot = leftIndex;
				leftIndex = tmp;
				System.out.println("touch");
			}

			PrintHelper.printArray(array, leftStart, rightEnd);

		}

		PrintHelper.printHeader();

		//quick sort left
		quickSortDraft(array, leftStart, pivot - 1);

		//quick sort right
		quickSortDraft(array, pivot + 1 ,rightEnd);

	}

}
