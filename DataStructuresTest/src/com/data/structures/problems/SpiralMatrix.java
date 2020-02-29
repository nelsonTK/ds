package com.data.structures.problems;

import java.util.ArrayList;
import java.util.List;

import com.data.structures.utils.PrintHelper;

/**
 * em Java matrix de matrizes significa que primeiro temos o index da row ou y, e depois temos o indice das colunas ou x.
 * é contruitivo para quem é de matemática onde as matrizes são m(x, y) em java são m(y, x) ou m[y][x]
 * é importante perceber isto para não fazer confusão neste tipo de exercicios, ou a preencher uma matriz... 
 * @author nelson
 *
 */
public class SpiralMatrix {

	public static void main(String[] args) {

		int [][] matrix = new int [3][4];
		fill2DMatrix(matrix);
		System.out.println("2d print");
		PrintHelper.print2DArray(matrix);
		System.out.println("spiral print");
		spiralPrint(matrix);
		System.out.println("spiral assimetric print");
		spiralAssimetricArrayPrint(matrix);
	}

	public static void fill2DMatrix(int [][] matrix) {
		int count = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] = ++count;
			}
		}
	}
	
	/**
	 * Leetcode 54
	 * @param matrix
	 * @return
	 */
	  public List<Integer> spiralOrder(int[][] matrix) {
	      //boundaries
	        if (matrix == null || matrix.length == 0) {
	            return new ArrayList<Integer>();
	        }
	        
			int u = 0;
			int r = matrix[0].length;
			int b = matrix.length;
			int l = -1;
			int printcount=0;
			int x = 0, y = 0;
			int items = matrix[0].length * matrix.length;
	        List<Integer> list = new ArrayList<Integer>();
	        
			list.add(matrix[y][x]);
	        printcount++;
			while (printcount < items) {

				if (x < r && printcount < items) {
					while (x + 1 != r) {
						x++;
						printcount++;
	                    list.add(matrix[y][x]);
					}				
					r--;
				}

				if (y < b && printcount < items) {
					while (y + 1 != b) {
						y++;
						printcount++;
	                    list.add(matrix[y][x]);
					}
					b--;
				}

				if (x > l && printcount < items) {
					while (x - 1 != l)
					{
						x--;
						printcount++;
	                    list.add(matrix[y][x]);
					}
					l++;
				}

				if (y > u && printcount < items) {
					while (y - 1 != u)
					{
						y--;
						printcount++;
	                    list.add(matrix[y][x]);
					}
					u++;
				}
	        }
	        return list;
	    }

	public static void spiralAssimetricArrayPrint(int [][] matrix) {
		//boundaries
		int u = 0;
		int r = matrix[0].length;
		int b = matrix.length;
		int l = -1;
		int printcount=0;
		int x = 0, y = 0;
		int items = matrix[0].length * matrix.length;

		System.out.println(matrix[y][x]);
		printcount++;
		while (printcount < items) {

			if (x < r && printcount < items) {
				while (x + 1 != r) {
					x++;
					printcount = printAndCount(printcount, matrix[y][x]);
				}				
				r--;
			}

			if (y < b && printcount < items) {
				while (y + 1 != b) {
					y++;
					printcount = printAndCount(printcount, matrix[y][x]);
				}
				b--;
			}

			if (x > l && printcount < items) {
				while (x - 1 != l)
				{
					x--;
					printcount = printAndCount(printcount, matrix[y][x]);
				}
				l++;
			}

			if (y > u && printcount < items) {
				while (y - 1 != u)
				{
					y--;
					printcount = printAndCount(printcount, matrix[y][x]);
				}
				u++;
			}
		}
	}
	
	public static int printAndCount(int counter, int valueToPrint) {
		System.out.println(valueToPrint);
		return ++counter;
	}
	
	/**
	 * para quadrados simétricos
	 * @param matrix
	 */
	public static void spiralPrint(int [][] matrix) {
		//boundaries
		int u = 0;
		int r = matrix[0].length;
		int b = matrix.length;
		int l = -1;
		int printcount=0;
		int x = 0, y = 0;
		int items = matrix[0].length * matrix.length;

		while (printcount < items) {

			System.out.println(matrix[y][x]);
			printcount++;


			if (x + 1  >= y && x + 1 < r) {
				//x + 1;
				x++;

				if (x + 1 == r)
					r--;

			} else if (x >= y && y + 1 < b) {
				//y + 1					
				y++;

				if (y + 1 == b)
				{
					b--;
				}

			} else if (x -1 <= y && x - 1 > l) {
				// x - 1
				x--;

				if (x - 1 == l) 
				{
					l++;
				}


			} else if (x <= y  && y - 1 > u) {
				// y - 1
				y--;

				if (y - 1 == u)
					u++;
			}
		}
	}
}
