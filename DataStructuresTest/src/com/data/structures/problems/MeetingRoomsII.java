package com.data.structures.problems;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MeetingRoomsII {

	public static void main(String[] args) {
		MeetingRoomsII m = new MeetingRoomsII();
		int [][] a = new int [3][2];
		a[0] = new int []{0, 30};
		a[1] = new int []{5, 10};
		a[2] = new int []{15, 20};
//		
//		int [][] a = new int [2][2];
//		a[0] = new int []{7, 10};
//		a[1] = new int []{1, 4};
		
		
//		int [][] a = new int [5][2];
//		a[0] = new int []{4,18};
//		a[1] = new int []{1,35};
//		a[2] = new int []{12,45};
//		a[3] = new int []{25,46};
//		a[4] = new int []{22,27};
		
		System.out.println("minimumRooms: " + m.minMeetingRooms(a));
	}

	/**
	 * 
	 * 		Runtime: 421 ms, faster than 5.03% of Java online submissions for Meeting Rooms II.
			Memory Usage: 39.7 MB, less than 11.54% of Java online submissions for Meeting Rooms II.
			
			
	 * needed to sort the array before applying my logic, I tried to do it without sorting but failed because of an edge case.
	 * 
	 * @failed 1) I was returning the overlapindex incorrectly
	 * 2) failed again, there was an edcase that I was not havin into account which was 
	 * when too intervals overlap in the same interval but they dont overlap them selves inside that interval
	 * 
	 * @time O(N^2) I believe it is a N(N-1)/2 * nlogn time complexity
	 * @space O(N) because of storing the frequencies
	 * @bestconceivableruntime O(NLogN)
	 * 
	 * @param intervals
	 * @return
	 */
	public int minMeetingRooms0(int[][] intervals) {

		if (intervals == null || intervals.length == 0)
			return 0;
		
		Arrays.sort(intervals, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		
		int maxOcurrence = 0;
		int [] ocurrencesPerInterval = new int[intervals.length];

		for (int i = 0; i < intervals.length - 1; i++) {
			for (int j = i + 1; j < intervals.length; j++) {
				
				//Check if intervals overlap in both ways
				if(isOverlap(intervals, i, j) || isOverlap(intervals, j, i)) {
					maxOcurrence = Math.max(++ocurrencesPerInterval[j], maxOcurrence);
				}


			}
		}

		//need to add 1 to the sum which represents the own room which is out of the previous calculations
		return maxOcurrence + 1;
	}
	

	/**
	 * 
	 * OPTIMIZATION
	 * 
			Runtime: 6 ms, faster than 67.47% of Java online submissions for Meeting Rooms II.
			Memory Usage: 39.6 MB, less than 11.54% of Java online submissions for Meeting Rooms II.
			
			
	 * needed to sort the array before applying my logic, I tried to do it without sorting but failed.
	 * 
	 * 
	 * If I sorted the algorithm I could cut some work for instance for intervals that are beyond the current interval boundaries
	 * there is no point in checking if (1,5) overlaps with (1000, 1300) I could only cut if the array was sorted.
	 * 
	 * @failed 1) I think it was because I was returning the overlapindex incorrectly
	 * 2) failed again, 
	 * 
	 * @time O(N^2) I believe it is a N(N-1)/2 * nlogn time complexity
	 * @space O(N) because of storing the frequencies
	 * 
	 * @param intervals
	 * @return
	 */
	public int minMeetingRooms(int[][] intervals) {

		if (intervals == null || intervals.length == 0)
			return 0;

		int maxOcurrence = 0;
		int [] ocurrencesPerInterval = new int[intervals.length];
		Arrays.sort(intervals, (a,b) -> a[0] - b[0]);

		for (int i = 0; i < intervals.length - 1; i++) {
			for (int j = i + 1; j < intervals.length; j++) {
				
				//if testing interval start is bigger than the end of the current interval, so we can stop iterating 
				if (intervals[i][1] <= intervals[j][0])
					break;
				
				//Check if intervals overlap in both ways
				if(isOverlap(intervals, i, j) || isOverlap(intervals, j, i)) {
					maxOcurrence = Math.max(++ocurrencesPerInterval[j], maxOcurrence);
				}
			}
		}

		//need to add 1 to the sum which represents the own room which is out of the previous calculations
		return maxOcurrence + 1;
	}

	/**
	 * checks if two intervals i and j overlap
	 * @param a
	 * @param i
	 * @param j
	 * @return
	 */
	private boolean isOverlap(int[][] a, int i, int j) {
		int start = 0;
		int end = 1;
		if(a[i][start] >= a[j][start] && a[i][start] < a[j][end] || 
				a[i][end] > a[j][start] && a[i][end] <= a[j][end] ||
				a[i][start] == a[j][start])
			return true;
		return false;
	}
}

/**
 * Solution with priority queue, it's very cleaver, during my exploration of the problem I think in priorityqueue but I though in storing startTime
 * 
 * It was close
 * 
 * The reasoning here is to sort the array, than store the end time in priority queueu of first interval
 * and then compare if the next interval starts after the previous finishes or not 
 * if it finishes before the next start then I should remove it from the priorityqueue
 * 
 * [here is where it's quite different from my though process]
 * 
 * Even if there are other elements in the queue that are smaller, we don't bother to remove them
 * doing this allows us to preserve the maximum room size till the end of the iteration
 * and thus the answer will be the priorityqueue size
 * 
 * @time O(N Log N)
 * @space O (N)
 * 
 * @author Nelson Costa
 *
 */
class MeetingRoomsIISolution1 {
    public int minMeetingRooms(int[][] intervals) {
        
    // Check for the base case. If there are no intervals, return 0
    if (intervals.length == 0) {
      return 0;
    }

    // Min heap
    PriorityQueue<Integer> allocator = new PriorityQueue<>(intervals.length);

    // Sort the intervals by start time
    Arrays.sort(
        intervals,
        new Comparator<int[]>() {
          public int compare(final int[] a, final int[] b) {
            return a[0] - b[0];
          }
        });

    // Add the first meeting
    allocator.add(intervals[0][1]);

    // Iterate over remaining intervals
    for (int i = 1; i < intervals.length; i++) {

      // If the room due to free up the earliest is free, assign that room to this meeting.
      if (intervals[i][0] >= allocator.peek()) {
        allocator.poll();
      }

      // If a new room is to be assigned, then also we add to the heap,
      // If an old room is allocated, then also we have to add to the heap with updated end time.
      allocator.add(intervals[i][1]);
    }

    // The size of the heap tells us the minimum rooms required for all the meetings.
    return allocator.size();
  }
}
/**
 * This solution is also interesting
 * they separate start time and end time in two different arrays
 * and compare how many items starts before one finishes
 * while start dates are less than the end date the counter increases because it means that
 * many meetings start before one finishes, so they need room simultaneously
 * The concept is similar to the previous but more confused to understand/implement
 * as the solution before, the maximum number is in the variable in the end of the for loop
 * @author Nelson Costa
 *
 */
class MeetingRoomsIISolution2 {
    public int minMeetingRooms(int[][] intervals) {
        
    // Check for the base case. If there are no intervals, return 0
    if (intervals.length == 0) {
      return 0;
    }

    Integer[] start = new Integer[intervals.length];
    Integer[] end = new Integer[intervals.length];

    for (int i = 0; i < intervals.length; i++) {
      start[i] = intervals[i][0];
      end[i] = intervals[i][1];
    }

    // Sort the intervals by end time
    Arrays.sort(
        end,
        new Comparator<Integer>() {
          public int compare(Integer a, Integer b) {
            return a - b;
          }
        });

    // Sort the intervals by start time
    Arrays.sort(
        start,
        new Comparator<Integer>() {
          public int compare(Integer a, Integer b) {
            return a - b;
          }
        });

    // The two pointers in the algorithm: e_ptr and s_ptr.
    int startPointer = 0, endPointer = 0;

    // Variables to keep track of maximum number of rooms used.
    int usedRooms = 0;

    // Iterate over intervals.
    while (startPointer < intervals.length) {

      // If there is a meeting that has ended by the time the meeting at `start_pointer` starts
      if (start[startPointer] >= end[endPointer]) {
        usedRooms -= 1;
        endPointer += 1;
      }

      // We do this irrespective of whether a room frees up or not.
      // If a room got free, then this used_rooms += 1 wouldn't have any effect. used_rooms would
      // remain the same in that case. If no room was free, then this would increase used_rooms
      usedRooms += 1;
      startPointer += 1;

    }

    return usedRooms;
  }
}
