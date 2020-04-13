package com.data.structures.algos;

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
	public static int search(int x, int a[])
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
}
