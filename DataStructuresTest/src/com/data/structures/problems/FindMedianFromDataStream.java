package com.data.structures.problems;

import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/find-median-from-data-stream/
 * HARD
 * @author Nelson Costa
 *
 */
public class FindMedianFromDataStream {

	static FindMedianFromDataStream f = new FindMedianFromDataStream();
	public static void main(String[] args) {

		/**
		 * Your MedianFinder object will be instantiated and called as such:
		 * */
		 MedianFinder obj = f.new MedianFinder();
		 int num = 1;
		 obj.addNum(num);
		 System.out.println(obj.findMedian());
		 
	}

	/*



	*/
	
	/**

	@intuition
		Very simple intuition, You just keep the priority queues balanced, 
		a pq never has more than one item more than another.
			

	@score
		Runtime: 41 ms, faster than 94.17% of Java online submissions for Find Median from Data Stream.
		Memory Usage: 50.9 MB, less than 39.82% of Java online submissions for Find Median from Data Stream.


	@fail
	    1) bad first interpretation
	    2) null pointers unsafe peeks and polls
	    3) error don't know why
	    4) I switched the min heap for the max heap
	    5) failed on guards... I had placeholder value there
	    
	@debug
	    yes contest style
	    
	@time
		findMedian 	O(1)
		addNum( 	O(Log n)
		
	 * @author Nelson Costa
	 *
	 */
	class MedianFinder {
	    PriorityQueue<Integer> min;
	    PriorityQueue<Integer> max;
	    int size;
	    
	    /** initialize your data structure here. */
	    public MedianFinder() {
	        
	        max = new PriorityQueue<>(
	        (a, b) -> Integer.compare(a,b)
	        );
	        
	        min = new PriorityQueue<>(
	        (a, b) -> Integer.compare(b,a)
	        );
	        
	        size = 0;
	    }
	    
	    // O(log n) -> O(2.log n) 
	    public void addNum(int num) {        
	        
	        if (min.size() == max.size())
	        {
	            if(!max.isEmpty() && num >= max.peek())
	            {
	                max.add(num);
	            }
	            else if (!max.isEmpty() && num < max.peek())
	            {
	                min.add(num);                
	            }
	            else {
	                min.add(num);
	            }	            
	        }
	        else if (min.size() < max.size())
	        {
	            if(!max.isEmpty() && num <= max.peek())
	            {
	                min.add(num);
	            }
	            else if (!max.isEmpty() && num > max.peek())
	            {
	                min.add(max.poll());
	                max.add(num);
	            }
	            else {
	                 min.add(num);
	            }
	        }
	        else if (min.size() > max.size())
	        {
	            if (!min.isEmpty() && num <= min.peek())
	            {
	                max.add(min.poll());
	                min.add(num);
	            }
	            else if (!min.isEmpty() && num > min.peek())
	            {
	                max.add(num);
	            }
	            else
	            {
	                max.add(num);
	            }
	        }
	        
	        size++;
	    }
	    
	    //O(1)
	    //Zero size is invalid for this problem
	    public double findMedian() {
	            
	        if (size == 1)
	            return min.size() > max.size() ? min.peek() : max.peek();
	        
	        if (size % 2 == 0)
	        {
	           return (double)(min.peek() + max.peek() )/ 2;
	        }
	        else
	        {
	            if (min.size() > max.size())
	                return min.peek();
	            else
	                return max.peek();
	        }
	    }
	}

	/**
	 * Your MedianFinder object will be instantiated and called as such:
	 * MedianFinder obj = new MedianFinder();
	 * obj.addNum(num);
	 * double param_2 = obj.findMedian();
	 */
}



/*********************
* OTHERS SOLUTIONS
*********************/

	
	/*********************
	 * UNOFFICIAL SOLUTIONS
	 *********************/

/**
 * @intuition	
 * 		This solution has the same gist than mine but is not as efficient as mine, because you always add at least 2 times elements to the pq's and maximum you add 3.
 * 		My solution always adds 1 and can add till 2.
 * 
 * 		however this solution is way more simplistic.
 * 
 * @explanation
 * 
 * 		In simple terms you let the priority queue do the most work.
 * 		You always add the num to the max priorit queue.
 * 		Let the max queue order itself
 * 		You pass the element to the minPriority queue.
 * 		If Min priorityqueue has more elements than maxqueue than you add them back in maxpq.
 * 
 * 		min priority queue has the biggest values.
 * 		max priority queue has the smaller values.
 * 		by doing this we always have the tree balanced in all aspects
 * 		
 * 
 * @source
 * 		https://github.com/JayaramachandranAugustin/Interview/blob/master/Algorithm/Heap/find_median_from_data_stream.md
 * @author Nelson Costa
 *
 */
class FindMedianFromDataStreamSolution1 {
	
	class MedianFinder {

	    PriorityQueue<Integer> maxQ;
	    PriorityQueue<Integer> minQ;

	    public MedianFinder() {
	        maxQ=new PriorityQueue<>((a,b) -> b-a);
	        minQ=new PriorityQueue<>();
	    }

	    public void addNum(int num) {
	        maxQ.offer(num);
	        minQ.offer(maxQ.poll());
	        if(maxQ.size()<minQ.size()) maxQ.offer(minQ.poll());
	    }

	    public double findMedian() {
	        if(maxQ.size()==0) return 0;
	        return maxQ.size()>minQ.size()?maxQ.peek():(double)(maxQ.peek()+minQ.peek())/2;

	    }
	}
}