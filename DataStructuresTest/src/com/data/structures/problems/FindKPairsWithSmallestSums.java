package com.data.structures.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/find-k-pairs-with-smallest-sums/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class FindKPairsWithSmallestSums {

	public static void main(String[] args) {

		FindKPairsWithSmallestSums f = new FindKPairsWithSmallestSums();
		//		int[] a = new int[]{1,7,11};
		//		int[] b = new int[]{2,4,6};
		//		int k = 2;

		//		int[] a = new int[]{1,1,2};
		//		int[] b = new int[]{1,2,3};
		//		int k = 2;


		int[] a = new int[]	{-10, -4, 0, 0, 6};
		int[] b = new int[]{3, 5, 6, 7, 8, 100};
		int k = 15;

		//		int[] a = new int[]{1};
		//		int[] b = new int[]{2,4,6};
		for (List<Integer> entry : f.kSmallestPairs(a, b, k)) {
			System.out.println(Arrays.toString(entry.toArray()));
		}

		//		FindKPairsWithSmallestSumsSolution fs = new FindKPairsWithSmallestSumsSolution();
		//		fs.kSmallestPairs(a, b, k);
	}

	/**
	 *  [WRONG ANSWER]
	 * 
	 * INTUITION
	 * 		the implementation is based in the concept of 4 pointers, two for each array.
	 * 		each array has the head and tail
	 * 		the head is in its own array(a). 
	 * 		the tail is in the other array(b).
	 * 		head and tail advance according to whether or not its sume is smaller that the head and tail of the other array.
	 * 		this was the bes concept I could cameup with to solve this issue.
	 * 
	 * 		I start up adding the first node, and then the processing starts.
	 * 
	 * 
	 * 		HA
	 * 		1  2  3  4  4
	 * 		   TB
	 * 		
	 * 		   TA
	 * 		1  2  2  3  4
	 * 		HB
	 * ______________________________
	 * 		HA + TA < HB + TB ?
	 * 			Combination A is smaller, so Tail advances
	 * ______________________________
	 * 
	 * 		HA
	 * 		1  3  3  4  4
	 * 		   TB
	 * 		
	 * 		   	  TA
	 * 		1  2  2  3  4
	 * 		HB
	 * _____________________________
	 * 
	 * 		HA + TA < HB + TB ?
	 * 
	 * Etc..
	 * 
	 * 
	 * 
	 * 
	 * @failed 
	 * 1) I've forgot to add the entries from processing A
	 * 2) Forgot to test the case where I have only one element and I add one to tail blindly
	 * 3) duplicate case on first, it looks like there is a conceptual fault in here, we 
	 * cannot count the same connection from one side to other, and also there are negative numbers
	 * 
	 * @space K
	 * @time  N
	 * @bcr   N
	 * 
	 * @param a
	 * @param b
	 * @param k
	 * @return
	 */
	public List<List<Integer>> kSmallestPairs0(int[] a, int[] b, int k) {

		if(a == null || a.length == 0 || b == null || b.length == 0 || k <= 0 )
			return new ArrayList<List<Integer>>();

		Candidate ca = new Candidate(0, Math.min(1, b.length));
		Candidate cb = new Candidate(0, Math.min(1, a.length));
		List<List<Integer>> ans = new ArrayList<List<Integer>>(); //Why it cant be ArrayList of ArrayList the same as List of List?
		ArrayList<Integer> entry = new ArrayList<>();
		entry.add(a[0]);
		entry.add(b[0]);
		ans.add(entry);
		k--;
		while (k > 0 && ca.head < a.length && cb.head < b.length) {


			//a head and tail has smaller sum than b
			if(a[ca.head] + b[ca.tail] <= b[cb.head] + a[cb.tail]) {	

				processA(a, b, ans, ca, cb);
			}
			//b head and tail has smaller sum than a
			else
			{
				processB(a, b, ans, ca, cb);
			}
			k--;
		}

		//process remaining if any, and k < 0;
		if(k > 0 && cb.head >= b.length) {

			while (k > 0 && ca.head < a.length)
			{
				processA(a, b, ans, ca, cb);
			}
		}
		else if ( k > 0 && ca.head >= a.length)
		{
			while (k > 0 && cb.head < b.length)
			{				
				processB(a, b, ans, ca, cb);
			}
		}

		return ans;
	}

	private void processA(int[] a, int[] b, List<List<Integer>> ans, Candidate ca, Candidate cb) {
		ArrayList<Integer> entry = new ArrayList<>();

		entry.add(a[ca.head]);
		entry.add(b[ca.tail]);
		ans.add(entry);

		if (ca.tail + 1 < b.length) {
			ca.tail++;
		}
		else
		{		
			ca.head++;
			ca.tail = 0;
		}

	}

	private void processB(int[] a, int[] b, List<List<Integer>>  ans, Candidate ca, Candidate cb) {
		ArrayList<Integer> entry = new ArrayList<>();

		entry.add(a[cb.tail]);
		entry.add(b[cb.head]);
		ans.add(entry);		

		if (cb.tail + 1 < a.length) {
			cb.tail++;
		}
		else
		{
			cb.head++;
			cb.tail=0;
		}
	}

	class Candidate{
		int head;
		int tail;

		Candidate(int h, int t){
			head = h;
			tail = t;
		}
	}

	/*******************
	 * 
	 * SOLUTION 2
	 *
	 *******************/


	/**
	 * 
	 * 		Runtime: 509 ms, faster than 5.00% of Java online submissions for Find K Pairs with Smallest Sums.
			Memory Usage: 114.3 MB, less than 7.41% of Java online submissions for Find K Pairs with Smallest Sums.
	 * 
	 * 
	 * Ugly Brute Force Solution
	 * 
	 * There are other brute force solutions, probably better than this:
	 * 		https://www.geeksforgeeks.org/find-k-pairs-smallest-sums-two-arrays/ (Method 2)
	 * 
	 * 
	 * @time  axb * log (axb)
	 * @space axb
	 * BCR N
	 * @param a
	 * @param b
	 * @param k
	 * @return
	 */
	public List<List<Integer>> kSmallestPairs1(int[] a, int[] b, int k) {

		HashSet<String> set = new HashSet<String>();
		List<List<Integer>> ans = new ArrayList<List<Integer>>();
		ArrayList<Integer> entry = new ArrayList<Integer>();
		for (int i = 0; i < a.length; i++) {
			for(int j = 0; j < b.length; j++)
			{
				if(!set.contains(i + "." + j))
				{
					entry = new ArrayList<Integer>();
					entry.add(a[i]);
					entry.add(b[j]);
					ans.add(entry);
					set.add(i + "." + j);
				}
			}
		}

		ans = ans.stream().sorted((x,y) -> {
			int sumX = 0;
			int sumY = 0;

			for (Integer i : x) {
				sumX+=i;
			}

			for (Integer i : y) {
				sumY+=i;
			}

			return Integer.compare(sumX,sumY);
		}).limit(k).collect(Collectors.toList());

		return ans;
	}

	


	/*******************
	 * 
	 * SOLUTION 3
	 *
	 *******************/	
	
	
	
	/**
	 * @comments
	 * 		One of the Hardest Execises to date, I couldn't go from a solution better than bruteforce
	 * 		really violent one, I loved it.
	 * 
	 * 
	 * @score
	 * 		Runtime: 3 ms, faster than 100.00% of Java online submissions for Find K Pairs with Smallest Sums.
			Memory Usage: 39.9 MB, less than 66.67% of Java online submissions for Find K Pairs with Smallest Sums.
	 * 
	 * INTUITION
	 * 		We will calculate the smallest sum, using one of the tables as reference.
	 * 		So we'll fix it and compare it with the other table. 
	 * 		in this case we fix (a)
	 * 		Which you can consider here our left array.
	 * 		So conceptually you dont compare from right to left, is always left to right.
	 * 		
	 * 
	 * 		So we save in the priority queue all combinations of the left array and the first value of the right array (b, index 0)
	 * 		We know the smallest element is there.
	 * 		For each line, which we can consider a tuple, we also save the index of the right table, 
	 * 		which is zero, because it is the first element 
	 * 		Saving the index of right array is important to help us progress in that array. (More later)
	 * 		
	 * 
	 * 		Then while we dont reach k elements
	 * 		We add the smallest of the elements in the queue to the results
	 * 		And we add to the priority queue the next element following that recently added element
	 * 		(In case this is not the next smallest element we are garanteed it is already in the queue.)
	 * 		
	 * 		This will garantee we add the elements in the right order
	 * 		
	 * 		left	right
	 * 		3		-10
	 * 		5		-4
	 * 		6		0
	 * 		7 		0
	 * 		8		6
	 * 		100	
	 * 
	 * 		Priority Queue at the start
	 * 		3,  -10, 0
	 * 		5,  -10, 0 		
	 * 		6,  -10, 0 
	 * 		7,  -10, 0 
	 * 		8,  -10, 0 
	 *		100,-10, 0 
	 *
	 *		After first iteration the smallest element was remove, 
	 *		and the next bigger for that element in the left array was added
	 *		and as you can see the index was incremented
	 *
	 * 		5,  -10, 0 		
	 * 		6,  -10, 0 
	 * 		7,  -10, 0 
	 * 		8,  -10, 0 
	 * 		3,	-4,  1 [New Element]
	 *		100,-10, 0 
	 * 
	 * 
	 * 		
	 * @failed 
	 * 1) forgot to use comparable, because arrays do not have comparable
	 * 2) What I was adding in the priorityqueue was not the b but only the correct index
	 * 
	 * @time 	O(log mk ==> MLogM + kLogk) like MLogM + kLogk, MLogM is traversing left array and add to queue, klogk is adding k nodes which takes logk per item
	 * @space 	O(MAX(M, k))
	 * @bcr		O(K) (Not reachable)
	 * 
	 * @param a
	 * @param b
	 * @param k
	 * @return
	 */
	public List<List<Integer>> kSmallestPairs(int[] a, int[] b, int k) {

		if (a == null || a.length == 0 || b == null || b.length == 0 || k < 1 )
			return new ArrayList<List<Integer>>();


		PriorityQueue<int[]> minHeap = new PriorityQueue<>(a.length,
				(x,y) -> Integer.compare(x[0] + x[1], y[0] + y[1]));

		//load priority queue with a array min values
		for (int i = 0; i < a.length; i++)
		{
			minHeap.add(new int [] {a[i], b[0], 0});
		}

		List<List<Integer>> ans = new ArrayList<List<Integer>>();
		List<Integer> entry = new ArrayList<Integer>();
		int [] min;


		while (k > 0 && !minHeap.isEmpty())
		{
			min = minHeap.poll();
			entry = new ArrayList<Integer>();
			entry.add(min[0]);
			entry.add(min[1]);
			ans.add(entry);

			//Add the next value for the current entry of the array
			//And let the priority queue do its magic (sorting)
			if (min[2] + 1 < b.length)
			{
				minHeap.add(new int[] {min[0], b[min[2] + 1], min[2] + 1});
			}

			k--;

		}	
		return ans;
	}
}


/*******************
 * 
 * SOLUTIONS BY OTHERS
 *
 *******************/
/**
 * 
 * 
 * 
 * Super Cleaver solution to solve this complex problem.
 * Priority queue is used to control the smallest element but also to control the next element to add
 * in the queue by adding a index to the element in the priority queue
 * learned a lot from this solution
 * 
 * @time although they defend time complexity is klogk i dont agree because you load the priority k with m and m can be bigger tha k.
 * I believe time complexxity is more complex.
 * like MLogM + kLogk
 * 
 * Source: https://github.com/cherryljr/LeetCode/blob/master/Find%20K%20Pairs%20with%20Smallest%20Sums.java
 * Youtube: https://www.youtube.com/watch?v=u565nLpQx98 (this was the first video I saw but the eplanation was bad, very bad)
 * 
 * @author Nelson Costa
 *
 */
class FindKPairsWithSmallestSumsSolution {
	public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
		List<List<Integer>> rst = new ArrayList<>();
		if (nums1.length == 0 || nums2.length == 0 || k == 0) {
			return rst;
		}

		PriorityQueue<int[]> pq = new PriorityQueue<>(k, new Comparator<int[]>(){
			// Overwrite the compare method
			public int compare(int[] a, int[] b) {
				return (a[0] + a[1]) - (b[0] + b[1]);
			}
		});
		// Initialize the minHeap
		// when offer the next element, we need to know the curr index of nums2.
		// So the array is consist of: the element in nums1, the element in nums2, the curr element's index in nums2.
		for (int i = 0; i < k && i < nums1.length; i++) {
			pq.offer(new int[]{nums1[i], nums2[0], 0});
		}
		// poll the peak element (minElement currently), and offer the next element
		while (k-- > 0 && !pq.isEmpty()) {
			int[] cur = pq.poll();
			rst.add(Arrays.asList(cur[0], cur[1]));
			// check the index of nums2 is out of bound or not
			if (cur[2] == nums2.length - 1) {
				continue;
			}
			pq.offer(new int[]{cur[0], nums2[cur[2] + 1], cur[2] + 1});
		}

		return rst;
	}
}
