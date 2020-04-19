package com.data.structures.problems;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 
 * @author Nelson Costa
 *
 */
public class KthSmallestElementInASortedMatrix {

	public static void main(String[] args) {
		
		KthSmallestElementInASortedMatrix kk = new KthSmallestElementInASortedMatrix();
		
		int k = 6;
		
		int [][] matrix = new int [3][3];
		matrix[0] = new int[]{ 1,  5,  9};
		matrix[1] = new int[]{ 10, 11, 13};
		matrix[2] = new int[]{ 12, 13, 15};
		
		System.out.println(kk.kthSmallest(matrix, k));
		
		KthSmallestElementInASortedMatrixSolution4 s = new KthSmallestElementInASortedMatrixSolution4();
		s.kthSmallest(matrix, k);
	}

	/**
	 * Runtime: 17 ms, faster than 35.41% of Java online submissions for Kth Smallest Element in a Sorted Matrix.
Memory Usage: 45 MB, less than 35.14% of Java online submissions for Kth Smallest Element in a Sorted Matrix.
	 * 
	 * 
	 * @failed 
	 * 1) the k comparison sign was on contrary in the while loop
	 * 2) putted row instead of min[row], this type of issues are difficutlt to track
	 * 
	 * @time  O (N * Log N) for the initial load, and than we just do K * Log k
	 * @space O (N) Where N is the size of the square matrix
	 * 
	 * @param matrix
	 * @param k
	 * @return
	 */
	public int kthSmallest0(int[][] matrix, int k) {

		if (matrix == null || matrix.length == 0 || k <= 0 )
			throw new IllegalArgumentException();

		int n = matrix.length;

		//only one element because matrix n*n
		if (matrix.length == 1)
		{
			return matrix[0][0];
		}

		if (k > n * n) {
			k = n * n;
		}

		PriorityQueue<int[]> minHeap = new PriorityQueue<int[]>(n, 
				(a, b) -> Integer.compare(a[0], b[0]));

		//Fill heap with the first column of the matrix and its positions
		for (int i = 0; i < matrix.length; i++) {
			minHeap.offer(new int[] {matrix[i][0], i, 0});
		}

		int [] min = null;
		int row = 1;
		int col = 2;
		int nextCol; //next column

		while (k > 0)
		{
			min = minHeap.poll();
			nextCol = min[col] + 1;
			if (nextCol < n) {
				minHeap.add(new int [] {
						matrix[min[row]] [nextCol],
						min[row],
						nextCol
				});
			}
			k--;
		}
		return min[0];
	}
	
	/**
	 *  OPTIMIZATION
	 * 
	 * 		DESCRIPTION
	 * 			What I have done was substitute the array by a tuple with a comparable.
	 * 			I'm not quite sure why this is faster. maybe because the comparison is made at runtime.
	 * 
			Runtime: 13 ms, faster than 59.26% of Java online submissions for Kth Smallest Element in a Sorted Matrix.
			Memory Usage: 45.7 MB, less than 13.51% of Java online submissions for Kth Smallest Element in a Sorted Matrix.
	 *
	 *
	 *	
	 * 
	 * @time  O (N * Log N) for the initial load, and than we just do K * Log k
	 * @space O (N) Where N is the size of the square matrix
	 * 
	 * @param matrix
	 * @param k
	 * @return
	 */
	public int kthSmallest(int[][] matrix, int k) {

		if (matrix == null || matrix.length == 0 || k <= 0 )
			throw new IllegalArgumentException();

		int n = matrix.length;

		//only one element because matrix n*n
		if (matrix.length == 1)
		{
			return matrix[0][0];
		}

		if (k > n * n) {
			k = n * n;
		}

		PriorityQueue<Tuple> minHeap = new PriorityQueue<Tuple>();

		//Fill heap with the first column of the matrix and its positions
		for (int i = 0; i < matrix.length; i++) {
			minHeap.offer(new Tuple(matrix[i][0], i, 0));
		}

		Tuple min = null;
		int row = 1;
		int col = 2;
		int nextCol; //next column

		while (k > 0)
		{
			min = minHeap.poll();
			nextCol = min.col + 1;
			if (nextCol < n) {
				minHeap.add(new Tuple (
						matrix[min.row] [nextCol],
						min.row,
						nextCol
				));
			}
			k--;
		}
		return min.val;
	}
	
	
	class Tuple implements Comparable<Tuple> {
	    int col, row, val;
	    public Tuple (int val, int row, int col) {
	        this.col = col;
	        this.row = row;
	        this.val = val;
	    }
	    
	    @Override
	    public int compareTo (Tuple tup) {
	        return this.val - tup.val;
	    }
	}
}

/***********************
 * OTHERS SOLUTIONS  (8 ms)
 ***********************/

/**
 * LARGEST OR SMALLEST SOLUTION
 * 
 * 		Interesting solution that calculates the largest or the smallest depending on k size
 * 
 * The concept is the same as mine but with the optimization to calculare the largest or smallest
 * @author Nelson Costa
 *
 */
class KthSmallestElementInASortedMatrixSolution {
    class Tuple{
        int i;
        int j;
        int val;
        
        Tuple(int i, int j, int val){
            this.i = i;
            this.j = j;
            this.val = val;
        }
    }
    
    public int kthSmallest(int[][] matrix, int k) {
        if(matrix == null || matrix.length == 0){
            return 0;
        }
        int length = matrix.length*matrix[0].length;
        return k > length /2 ? largest(matrix, length - k + 1) : smallest(matrix, k);
    }
    
    private int smallest(int[][] matrix, int k){
        PriorityQueue<Tuple> pQ = new PriorityQueue<>(new Comparator<Tuple>(){
            public int compare(Tuple t1, Tuple t2){
                return t1.val - t2.val;
            }    
        });
        
        for(int i=0; i<matrix[0].length; i++){
            pQ.offer(new Tuple(0, i, matrix[0][i]));
        }
        
        int result = -1;
        while(k != 0){
            Tuple tuple = pQ.poll();
            result = tuple.val;
            int nextRow = tuple.i + 1;
            if(nextRow <matrix.length){
                pQ.offer(new Tuple(nextRow, tuple.j, matrix[nextRow][tuple.j]));
            }
            k--;
        }
        
        return result;
    }
    
    private int largest(int[][] matrix, int k){
        PriorityQueue<Tuple> pQ = new PriorityQueue<>(new Comparator<Tuple>(){
            public int compare(Tuple t1, Tuple t2){
                return t2.val - t1.val;
            }    
        });
        
        for(int i=matrix.length - 1; i>=0; i--){
            pQ.offer(new Tuple(matrix.length - 1, i, matrix[matrix.length -1][i]));
        }
        
        int result = -1;
        while(k != 0){
            Tuple tuple = pQ.poll();
            result = tuple.val;
            int nextRow = tuple.i - 1;
            if(nextRow >= 0){
                pQ.offer(new Tuple(nextRow, tuple.j, matrix[nextRow][tuple.j]));
            }
            k--;
        }
        
        return result;
    }
}


/**
 * 7ms
 * 
 * 
 * GOLD: from array to matrix
 * 
 * 		array[i * size + j] = matrix[i][j]; 
 * 
 * @author Nelson Costa
 *
 */
class KthSmallestElementInASortedMatrixSolution2 {
    public int kthSmallest(int[][] matrix, int k) {
        int size = Math.min(k, matrix.length);
        int[] array = new int[size * size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                array[i * size + j] = matrix[i][j];
            }
        }
        Arrays.sort(array);
        return array[k - 1];
    }
}

/**
 * 5ms Solutions (there is better 0ms)
 * 
 * With Binary Search
 * 
 * It's Interesting approach, but I think the time complexity is high04532
 * 
 * 
 * @author Nelson Costa
 *
 */
class KthSmallestElementInASortedMatrixSolution3 {
    public int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int lo = matrix[0][0], hi = matrix[m-1][n-1];
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cnt = 0;
            for (int i = 0; i < m; i++) {
            	System.out.println();
                for (int j = 0; j < n && matrix[i][j] <= mid; j++) {
                    cnt++;
                }
            }
            if (cnt < k) lo = mid + 1;
            else hi = mid - 1;
        }
        return lo;
    }
}

/**
 * 0ms
 * top solution
 * Hard to understand
 * It is a twisted binary search.
 * It is not the usual flavor, 
 * but is writen in a way that it looks a lot like binary search
 * 
 * @author Nelson Costa
 *
 */
class KthSmallestElementInASortedMatrixSolution4 {
    public int kthSmallest(int[][] matrix, int k) {
        int low = matrix[0][0], high = matrix[matrix.length - 1][matrix[0].length - 1];
        while (low < high){
            int mid = low + (high - low) / 2;
            int count = 0, j = matrix[0].length - 1;
            for (int i = 0; i < matrix.length; i++){
                while (j >= 0 && matrix[i][j] > mid) 
                	j--;
                count += (j + 1);
            }
            if (count < k) low = mid + 1;
            else high = mid;
        }
        return low;
    }
}