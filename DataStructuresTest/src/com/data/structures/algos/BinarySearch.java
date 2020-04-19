package com.data.structures.algos;

/**
 * Binary Search is a Beast Algorithm for searching
 * The big problem is that there are a lot of ways to implement it and the differences are subtle
 * some times you compare low < high, other times low <= high
 * sometimes you have to deal with duplicates, other times dont
 * all of this contributes to changes in the algorithm.
 * 
 * As I Saw earlier
 * 
 * it is the easiest difficult algorithm to master (GeeksForGeeks)
 * 
 * 
 * @author Nelson Costa
 *
 */
public class BinarySearch {

	/**
	 * wrong
	 * @param search
	 * @param array
	 * @return
	 */
	public static int search0(int search, int[] array) {

		int jump = 1;
		int middle = (array.length - 1) >> jump;

		while (((array.length - 1) >> jump )> 0)
		{
			jump++;

			if(array[middle] == search)
			{
				return array[middle];
			}
			else if(array[middle] < search)
			{
				middle += (array.length - 1) >> jump;
			}
			else if (array[middle] > search)
			{
				middle -= (array.length - 1) >> jump;
			}

		}
		return -1;
	}

	/**
	 * Works but is not clean
	 * @param search
	 * @param array
	 * @return
	 */
	public static int search1(int search, int[] array)
	{
		int size = array.length - 1;
		int step = 0; 
		int min = 0; 
		int max =  size >> step;
		int mid = (max - min) >> step + 1;
		while (step < Math.log10(size)/Math.log10(2))
		{
			step++;

			if(array[mid] == search)
			{
				return array[mid];
			}
			else if (array[mid] < search)
			{
				min = mid + 1;
				max = mid + 1 + (size >> step);
				max =(max > size) ? size : max;
				mid += 1 + ((max - min) >> (step)); 
			}
			else if (array[mid] > search)
			{
				max = mid - 1;
				min = mid - 1 - (size >> step);
				min = (min < 0)? 0 : min;
				mid -= 1 + ((max - min) >> (step)) ;
			}			
		}



		return -1;
	}

	/**
	 * much simpler implementation taking advantage of the middle point to calculate min and max
	 * @param x
	 * @param a
	 * @return
	 */
	public static int search2(int x, int a[])
	{
		int min = 0, max = a.length -1, mid = min + (max - min)/2;
		while (min <= max)
		{
			if (a[mid] == x)
			{
				return a[mid];
			}
			else if(a[mid] > x)
			{
				max = mid - 1;
				mid = min + (max - min)/2;
			}
			else if (a[mid] < x)
			{
				min = mid + 1;
				mid = min + (max - min)/2;
			}
		}

		return -1;
	}

	/**
	 * Improved binary search
	 * 
	 * @param x
	 * @param a
	 * @return
	 */
	public static int search (int x, int a [])
	{
		int low = 0;
		int high = a.length - 1;
		int mid;

		while (low < high) {
			mid = low + (high - low)/2;

			if (a[mid] == x)
			{
				return a[mid];
			}
			else if (a[mid] > x)
			{
				high = mid;
			}
			else
			{
				low = mid + 1;
			}
		}
		return a[low];
	}

	/**
	 * 
	 * @param x
	 * @param a
	 * @return
	 */
	public static int getRightMostIndexForDuplicates (int x, int a [])
	{
		if(a == null || a.length == 0 || x > a[a.length-1] || x < a[0]) {
			return -1;
		}
		
		int low = 0;
		int high = a.length - 1;
		int mid;

		while (low <= high) {
			mid = low + (high - low)/2;

			if (a[mid] <= x)
			{
				low = mid + 1;
			}
			else if (a[mid] > x)
			{
				high = mid - 1;
			}
		}
		
		return high;
	}

	/**
	 * Returns the left most duplicate in the array
	 * @param x
	 * @param a
	 * @return
	 */
	public static int getLeftMostIndexForDuplicates (int x, int a []) {
		int low = 0, high = a.length -1;
		int mid;
		
		while (low <= high)
		{
			mid = low + (high - low)/2;
			
			if (a[mid] >= x) {
				high = mid - 1;
			}
			else
			{
				low = mid + 1;
			}
		}
		
		return low;
	}

}
