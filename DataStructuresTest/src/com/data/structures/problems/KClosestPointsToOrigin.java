package com.data.structures.problems;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.ThreadLocalRandom;

public class KClosestPointsToOrigin {

	public static void main(String[] args) {

		KClosestPointsToOrigin kc = new KClosestPointsToOrigin();

		int k = 1;
		//		int p [][] = new int [2][2];
		//		p[0] = new int[] {91,3};
		//		p[1] = new int[] {2,2};

//		int p [][] = new int [3][2];
//		p[0] = new int[] {-5,4};
//		p[1] = new int[] {-6,-5};
//		p[2] = new int[] {4,6};


		int p [][] = new int [2][2];
		p[0] = new int[] {1,3};
		p[1] = new int[] {-2,2};


		for (int[] closest : kc.kClosest(p, k)) {
			System.out.println(Arrays.toString(closest));
		};
	}

	/*********************************
	 * MY SOLUTION 1
	 ********************************/

	/**
	 * 		Runtime: 32 ms, faster than 26.91% of Java online submissions for K Closest Points to Origin.
			Memory Usage: 48 MB, less than 100.00% of Java online submissions for K Closest Points to Origin.
	 * 
	 * 
	 * This is the expensive solution
	 * I believe it is expensive because we sort, and perhaps using priority will be faster because we dont add everytime (theoretically)
	 * its the first problem where I do bcr, I was way more quick than expected
	 * 
	 * @failed I suspect the problem is in the euclidean formula, I was comparing doubles using the "-" instead of using Double.compare. compare with minus is insecure
	 * 
	 * @time  O(N Log N)
	 * @space O(K)
	 * @bcr N Log N, I believe its not possible to do better
	 * 
	 * @param points
	 * @param K
	 * @return
	 */
	public int[][] kClosest0(int[][] p, int k) {
		if (p == null || p.length == 0 || k < 1)
			return null;

		Arrays.sort(p, (a, b) ->  Double.compare(Math.sqrt(a[0]*a[0] + a[1]*a[1]), Math.sqrt(b[0]*b[0] + b[1]*b[1])));

		int [][] dest = new int[k][2];

		System.arraycopy(p, 0, dest, 0, k);

		return dest;
	}

	/*********************************
	 * MY SOLUTION 2
	 ********************************/

	/**
	 * Runtime: 34 ms, faster than 22.79% of Java online submissions for K Closest Points to Origin.
Memory Usage: 48.1 MB, less than 100.00% of Java online submissions for K Closest Points to Origin.
	 * 
	 * I Believe I can still improve this
	 * 
	 * @fail compare descending was wrong
	 * 
	 * @time 	N LOG N
	 * @space 	k
	 * @bcr 	N Log N
	 * @param p
	 * @param k
	 * @return
	 */
	public int[][] kClosest1(int[][] p, int k) {

		if(p == null || p.length == 0)
			return null;

		PriorityQueue<int[]> q = new PriorityQueue<>(k, 
				(a,b) -> Double.compare(euclidean(b), euclidean(a)));

		//preload
		for (int i = 0; i < k; i++) {
			q.add(p[i]);
		}

		//verify k
		int currDistance = 0;
		for (int i = k; i < p.length; i++) {
			currDistance = (int) euclidean(p[i]);

			if(currDistance < euclidean(q.peek()))
			{
				q.poll();
				q.add(p[i]);
			}
		}

		//create answer
		int [][] ans = new int[k][2];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = q.poll();
		}	

		return ans;
	}


	/*********************************
	 * SOLUTION 3
	 ********************************/

	/**
	 * 
			Runtime: 28 ms, faster than 48.65% of Java online submissions for K Closest Points to Origin.
			Memory Usage: 48.8 MB, less than 100.00% of Java online submissions for K Closest Points to Origin.

		My final optimization was to reduce the number of caculations of the euclidean distance

		but the time complexity and space complexity stood still

	 * @param p
	 * @param k
	 * @return
	 */
	public int[][] kClosest2(int[][] p, int k) {

		if(p == null || p.length == 0)
			return null;

		PriorityQueue<Closest> q = new PriorityQueue<Closest>(k, 
				(Comparator<? super Closest>) new Comparator<Closest>() {
			public int compare(Closest n1, Closest n2) {
				return Double.compare(n2.euclideanDistance, n1.euclideanDistance);
			}});

		//preload
		for (int i = 0; i < k; i++) {
			q.add(new Closest(p[i]));
		}

		//verify k
		int currDistance = 0;
		for (int i = k; i < p.length; i++) {
			currDistance = (int) euclidean(p[i]);

			if(currDistance < q.peek().euclideanDistance)
			{
				q.poll();
				q.add(new Closest(p[i]));
			}
		}

		//create answer
		int [][] ans = new int[k][2];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = q.poll().point;
		}	

		return ans;
	}
	/**
	 * calc euclidean distance for point
	 * error handling dismissed
	 * @param a
	 * @return
	 */
	public double euclidean(int[] a) {
		return Math.sqrt(a[0]*a[0] + a[1]*a[1]);
	}

	class Closest {
		public int[] point;
		public double euclideanDistance;

		Closest(int[] p){
			point = p;
			euclideanDistance = euclidean(p);
		}

		/**
		 * calc euclidean distance for point
		 * error handling dismissed
		 * @param a
		 * @return
		 */
		public double euclidean(int[] a) {
			return Math.sqrt(a[0]*a[0] + a[1]*a[1]);
		}
	}



	/*********************************
	 * SOLUTION 4
	 ********************************/
	
	/**
	 * 		Runtime: 3 ms, faster than 98.96% of Java online submissions for K Closest Points to Origin.
			Memory Usage: 48.7 MB, less than 100.00% of Java online submissions for K Closest Points to Origin.
	 * 
	 * 		Glad!!
	 * 		Although I stumbled in two little details that require too much attention to catch
	 * 		I feel like Switching my QuickSelect implementation is a step needed. for something more simple with no swaps.
	 * 		But for now I'll continuo with this one. QuickSelect Swap to last.
	 * 
	 * @fail 
	 *  1) I was increasing right in partition instead of decreasing; 
	 * 
	 * 	2) also I was reducing right variable before the while loop which prevented the output to be correct for a case with two elements (detected with assisted debug)
	 *	I might have not needed debug assist if I was willing to spend more time and I've not spent this much time on this question
	 * 
	 * @time average O(N), worst O(N^2)
	 * @space worst O(n)
	 * @param p
	 * @param k
	 * @return
	 */
	public int[][] kClosest(int[][] p, int k) {

		if (p == null || p.length == 0 || k < 1)
			return null;
			
		return quickSelect(p, k, 0, p.length - 1);
	}
	
	public int[][] quickSelect(int [][] p, int k, int l, int r){
		
		int pivot = l + (r - l)/2;
		pivot = partition(p, l, r, pivot);
		
		if(pivot == k - 1) {
			return Arrays.copyOfRange(p, 0, k);
		} else if (pivot < k - 1)
		{
			return quickSelect(p, k, pivot + 1, r);
		}
		else {
			return quickSelect(p, k, l, pivot - 1);
		}
	}

	private int partition(int[][] points, int l, int r, int pivot) {

		int[] temp = points[pivot];
		points[pivot] = points[r];
		points[r] = temp;
		pivot = r;

		while (l < r)
		{
			while(l < pivot && calcDist(points[l]) <= calcDist(points[pivot]))
			{
				l++;
			}
			
			while(r > 0 && calcDist(points[r]) >= calcDist(points[pivot]))
			{
				r--;
			}
			
			if (r > 0 && l < pivot && l < r) {
				swap(points, l, r);
			}
		}
		swap(points, l, pivot);
		
		return l;
	}

	private void swap(int [][] points, int l, int r) {
		int [] temp = points[l];
		points[l] = points[r];
		points[r] = temp;
	}
	private int calcDist(int [] point) {
		return point[0]*point[0] + point[1]*point[1];
	}
}

/**
 * Runtime: 10 ms, faster than 83.01% of Java online submissions for K Closest Points to Origin.
Memory Usage: 48.7 MB, less than 100.00% of Java online submissions for K Closest Points to Origin.

	Intuition:
		Sort array of distance and get all elements below or equal that value

	Interesting solution we calculate all the distances
	and we sort that array in asc order (no euclidean calculation)
	then we put in all the elements where its distance is equal or below the distance in the kth position of the array.
	very ingenius.

	Optimizations:
		the square root was not calculated, because it was not needed and the values would never overflow

 	@time  N Log N
 	@space N 

 * @author Nelson Costa
 *
 */
class KClosestPointsToOriginSolution1 {
	public int[][] kClosest(int[][] points, int K) {
		int N = points.length;
		int[] dists = new int[N];
		for (int i = 0; i < N; ++i)
			dists[i] = dist(points[i]);

		Arrays.sort(dists);
		int distK = dists[K-1];

		int[][] ans = new int[K][2];
		int t = 0;
		for (int i = 0; i < N; ++i)
			if (dist(points[i]) <= distK)
				ans[t++] = points[i];
		return ans;
	}

	public int dist(int[] point) {
		return point[0] * point[0] + point[1] * point[1];
	}
}

/**
 * [[THIS SOLUTION RETURNS TIME LIMIT EXCEEDED]]
 * For a reason that I don't know and I dont care as it is not my solution, but the concept is good
 * 
 * This is a quickselect implementation to solve this issue.
 * I believe is kinda messy though and difficult to understand.
 * I prefer my solution.
 * 
 * @author Leetcode
 *
 */
class KClosestPointsToOriginSolution2 {
	int[][] points;
	public int[][] kClosest(int[][] points, int K) {
		this.points = points;
		sort(0, points.length - 1, K);
		return Arrays.copyOfRange(points, 0, K);
	}

	public void sort(int i, int j, int K) {
		if (i >= j) return;
		int k = ThreadLocalRandom.current().nextInt(i, j);
		swap(i, k);

		int mid = partition(i, j);
		int leftLength = mid - i + 1;
		if (K < leftLength)
			sort(i, mid - 1, K);
		else if (K > leftLength)
			sort(mid + 1, j, K - leftLength);
	}

	public int partition(int i, int j) {
		int oi = i;
		int pivot = dist(i);
		i++;

		while (true) {
			while (i < j && dist(i) < pivot)
				i++;
			while (i <= j && dist(j) > pivot)
				j--;
			if (i >= j) break;
			swap(i, j);
		}
		swap(oi, j);
		return j;
	}

	public int dist(int i) {
		return points[i][0] * points[i][0] + points[i][1] * points[i][1];
	}

	public void swap(int i, int j) {
		int t0 = points[i][0], t1 = points[i][1];
		points[i][0] = points[j][0];
		points[i][1] = points[j][1];
		points[j][0] = t0;
		points[j][1] = t1;
	}
}
