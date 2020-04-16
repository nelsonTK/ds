package com.data.structures.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/find-k-closest-elements/
 * MEDIUM
 * Very interesting and tough problem
 * @author Nelson Costa
 *
 */
public class FindKClosestElements {

	public static void main(String[] args) {
		int[] arr = {1,2,3,4,5};
//		arr = new int []{1,1,1,1,2};
		//		arr = new int []{1,1,1,1};
		int k=2, x=5;
		FindKClosestElements f = new FindKClosestElements();

		System.out.println(Arrays.toString(arr));
		System.out.println(Arrays.toString(f.findClosestElements(arr, k, x).toArray()));
	}

	/**
	 * 
	 * 		Runtime: 7 ms, faster than 41.28% of Java online submissions for Find K Closest Elements.
			Memory Usage: 41.4 MB, less than 5.88% of Java online submissions for Find K Closest Elements.
	 * 
	 * INTUITION
	 * 		It is a sliding window that records the best fitting the closest elements
	 * 		Everytime I get a value closer, I update the window to end in that element
	 * 
	 * @fail forgot to increment i;
	 * -forgot about the absolute value
	 * -failed again the calculus of start position after update was wrong, forgot to add 1
	 * 
	 * 
	 * @comments I Started thinking in a solution with a hashmap, 
	 * then advanced to a stack solution, 
	 * and lastlly a solution with pointers
	 * it was a cool progression
	 * 
	 * @time  O(M^2) worst case is M^2 where M = N - K. I believe it is the case where K is half. 
	 * if k is small this is better. so the best case is k = 1
	 * 
	 * @space O(K)
	 * 
	 * @param arr
	 * @param k k closest parameter
	 * @param x target value
	 * @return
	 */
	public List<Integer> findClosestElements0(int[] arr, int k, int x) {

		int i = k, start = 0, end = k - 1;
		boolean found = false;

		if (k <= 0 || arr == null || arr.length == 0)
		{
			return null;
		}

		if (k == arr.length)
		{
			return getAnswer(arr, start, end);
		}

		while (i < arr.length && !found)
		{
			//compare element with k selection
			for(int j = start; j <= end; j++)
			{
				if(Math.abs(arr[i] - x) < Math.abs(arr[j] - x))
				{
					end = i;
					start= end - k + 1;
					break;
				}
			}

			//check if we can finish search
			if (i + 1 < arr.length &&
					Math.abs(arr[i + 1] - x) >= Math.abs(arr[end] - x) &&
					Math.abs(arr[i + 1] - x) >= Math.abs(arr[start] - x) &&
					arr[i + 1] > arr[end])
			{
				found = true;
			}

			i++;
		}

		return getAnswer(arr, start, end);
	}

	/**
	 * 
	 * 		Runtime: 5 ms, faster than 50.30% of Java online submissions for Find K Closest Elements.
			Memory Usage: 41.3 MB, less than 23.53% of Java online submissions for Find K Closest Elements.
	 * 
	 * 
	 * INTUITION
	 * 	First search the element using binary search, then adjust k window
	 * 
	 * @failed failed on the stop condition (forgot to add the arr)
	 * failed again, forgot to add the abs to the stop conditions
	 * failed again, created an infinite loop in the binary search because of bad parentisis on bitwise operation
	 * failed again a missing condition to validate that no array outOfBounds would happen
	 * failed swapped variables min and mid
	 * 
	 * @time 	O(log n + (k + 1) * (k) => Log n + k^2 => k^2) I believe this is the time complexity
	 * @space	O(k)
	 * 
	 * @param arr
	 * @param k   k closest elements
	 * @param x   target
	 * @return
	 */
	public List<Integer> findClosestElements(int[] arr, int k, int x) {

		if ( arr == null || arr.length == 0 || k <= 0)
		{
			return null;
		}

		if (k == arr.length)
		{
			return getAnswer(arr, 0, arr.length - 1);
		}

		int closestIndex = search(arr, x);
		int ws = Math.max(closestIndex - k , 0);
		int we = Math.min(ws + k - 1 , arr.length - 1);
		int i = we + 1;
		boolean completed = false;
		while (!completed && i < arr.length)
		{
			for(int j = ws; j <= we; j++)
			{
				if(Math.abs(arr[i] - x) < Math.abs(arr[j] - x))
				{
					we = i;
					ws = we - k + 1;
					break;
				}
			}

			if (i+1 <= arr.length - 1 &&
				arr[i+1] >= arr[we] &&
				Math.abs(arr[i+1] - x) >= Math.abs(arr[ws] - x) && 
				Math.abs(arr[i+1] - x) >= Math.abs(arr[we] - x))
			{
				completed = true;
			}
			
			i++;
		}


		return getAnswer(arr, ws, we);
	}

	private int search(int [] a, int x) {
		int min = 0; 
		int max = a.length - 1; 
		int mid = min + ((max - min) >> 1);

		while (min <= max)
		{
			if (a[mid] == x) 
			{
				return mid;
			}
			else if (a[mid] < x)
			{
				min = mid + 1;
				mid = (min <= max) ? min + ((max - min) >> 1) : mid;
			}
			else if (a[mid] > x)
			{
				max = mid - 1;
				mid = (min <= max) ? min + ((max - min) >> 1) : mid;
			}
		}
		
				
		return mid;
	}


	private List<Integer> getAnswer(int[]arr, int start, int end){
		List<Integer> ans = new ArrayList<>();

		//build answer
		for (int j = start; j <= end; j++) {
			ans.add(arr[j]);
		}

		return ans;
	}

}

class FindKClosestElementsSolution {
	
	/**
	 * this is a very subtle and non obvious solution that explores binary search with mastery
	 * it gives a buffer of size k in the end of the array and start 
	 * performing from zero to length - k the binary search
	 * in the end the low will be the start of the answer and low + k -1 the upper limit
	 * 
	 * peculiarities include the absence of a equals comparition
	 * low and high stop before they are equal
	 * and the way this solution gets rid of math.abs is very good too
	 * 
	 * In a nutshell the binary search is used to find the closest element and the k range problem. 
	 * 
	 * BRILLIANT
	 * 
	 * @param arr
	 * @param k
	 * @param x
	 * @return
	 */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        if (arr == null || arr.length < 1) return new ArrayList<Integer>();
        
        int low = 0;
        int high = arr.length - k;
        while (low < high) {
            int mid = low + (high - low)/2;
            //if (x - arr[mid] > arr[mid+k] - x) low = mid + 1;alternativa a escrever math.abs
            if (Math.abs(arr[mid] - x ) > Math.abs(arr[mid+k] - x)) 
            	low = mid + 1;
            else high = mid;
        }
        return Arrays.stream(arr, low, low+k).boxed().collect(Collectors.toList());
    }
    
    /**
     * Another interesting solution where they array is sorted by the difference to the
     * target and they the first k elements are returned.
     * 
     * @param arr
     * @param k
     * @param x
     * @return
     */
    public List<Integer> findClosestElements(List<Integer> arr, int k, int x) {
        Collections.sort(arr, (a,b) -> a == b ? a - b : Math.abs(a-x) - Math.abs(b-x));
        arr = arr.subList(0, k);
        Collections.sort(arr);
        return arr;
   }
}