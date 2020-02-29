package com.data.structures.algos;

import com.data.structures.utils.PrintHelper;

public class AlgoSolutions {

	/**
	 * Nesta solução partilha todos os pontos com a minha solução mas parece muito mais eficiente
	 * invés de criar um array para cada metade do array que iria consumir memória.
	 * ela simplesmente realiza uma espécie de janela deslizante pelo array aparentemente
	 * @author Gayle Laakmann McDowell
	 * @reference https://www.youtube.com/watch?v=KF2j-9iSf4Q
	 * @param array
	 */
	public static void mergersort(int[] array) {

		PrintHelper.printArray(array);
		System.out.println();
		
		int[] temp = new int[array.length]; 
		mergesort(array, temp, 0 , array.length - 1);
		PrintHelper.printArray(temp);

	}
	
	private static void mergesort(int[] array, int[] temp, int leftStart, int rightEnd) {

		int printmiddle = (leftStart + rightEnd)/2; //só para fazer o print do middle em todas as iterações
		int printsize = rightEnd - leftStart + 1; //só para fazer o print do middle em todas as iterações
		int leftEnd = (rightEnd + leftStart) /2 ;
		int rightStart = leftEnd + 1;
		
		if (leftStart >= rightEnd) {
//			System.out.println("leftstart: " + leftStart + " leftEnd: " + leftEnd + " rightStart: " + rightStart + " rightEnd: " + rightEnd + " middle: " + printmiddle + " size: " + printsize + " basecase");
			return;
		}
//		System.out.println("leftstart: " + leftStart + " leftEnd: " + leftEnd + " rightStart: " + rightStart + " rightEnd: " + rightEnd + " middle: " + printmiddle + " size: " + printsize);

		int middle = (leftStart + rightEnd)/2;
		mergesort(array, temp, leftStart, middle);
		mergesort(array, temp, middle + 1, rightEnd);
		mergeHalves(array, temp, leftStart, rightEnd);
	}
	
	private static void mergeHalves(int [] array, int [] temp, int leftStart, int rightEnd) {
		int leftEnd = (rightEnd + leftStart) /2 ;
		int rightStart = leftEnd + 1;
		int size = rightEnd - leftStart + 1;
		
		int left = leftStart;
		int right = rightStart;
		int index = leftStart;
		
		int printmiddle = (leftStart + rightEnd)/2; //só para fazer o print do middle em todas as iterações
		System.out.println("leftstart: " + leftStart + " leftEnd: " + leftEnd + " rightStart: " + rightStart + " rightEnd: " + rightEnd + " middle: " + printmiddle + " size: " + size);
		PrintHelper.printArray(array, leftStart, leftEnd);
		PrintHelper.printArray(array, rightStart, rightEnd);
		
		
		while (left <= leftEnd && right <= rightEnd) {
			if(array[left] <= array[right]) {
				temp[index] = array[left];
				left++;
			}else
			{
				temp[index] = array[right];
				right++;
			}
			index++;
		}
		System.arraycopy(array, left, temp, index, leftEnd - left + 1); // only one of them (right or left) will be needed to execute because of leftovers
		System.arraycopy(array, right, temp, index, rightEnd - right + 1); // only one of them (right or left) will be needed to execute because of leftovers
		System.arraycopy(temp, leftStart, array, leftStart, size); //copiar de temp para array
		PrintHelper.printArray(array);
		System.out.println();

	}
}
