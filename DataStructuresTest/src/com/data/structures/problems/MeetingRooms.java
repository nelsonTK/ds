package com.data.structures.problems;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/meeting-rooms/
 * EASY
 * 252
 * 
 * @author Nelson Costa
 *
 */
public class MeetingRooms extends LeetCodeExercise{

	public static void main(String[] args) {
		MeetingRooms m = new MeetingRooms();

		//false
		int [][] interval = new int [3][2];
		interval = stringToMatrix("[[0,30],[5,10],[15,20]]");
		
		//true
		//		int [][] interval = new int [3][2];
		//		interval[1] = new int []{7,10};
		//		interval[2] = new int []{2,4};

		//true
		//				int [][] interval = new int [2][2];
		//				interval[0] = new int []{13,15};
		//				interval[1] = new int []{1,13};

		//false
		//			int [][] interval = new int [3][2];
		//			interval[0] = new int []{8,11};
		//			interval[1] = new int []{17,20};
		//			interval[2] = new int []{17,20};

		//false
		//		int [][] interval = new int [3][2];
		//		interval[0] = new int []{6,10};
		//		interval[1] = new int []{13,14};
		//		interval[2] = new int []{12,14};

		//		int [][] interval = new int [3][2];
		//		interval[0] = new int []{0,1};
		//		interval[1] = new int []{1,2};
		//		interval[2] = new int []{12,14};


		//		[[6,10],[13,14],[12,14]]

		for (int j = 0; j < interval.length; j++) {
			System.out.println(Arrays.toString(interval[j]));
		}

		System.out.println(m.canAttendMeetingsBruteForce(interval));
		System.out.println(m.canAttendMeetings(interval));

	}

	/*********************************
	 * SOLUTION 1
	 ********************************/
	
	
	/**
	 * INTUITION
	 * 
	 * 	I only need to travel through each meeting once, but I have to map the hours of those meetings
	 * 	If you are about to put an hour of meeting that is already there then you know that you overlapping.
	 * 	
	 * 	This had pros and cons but I decided to go either way because I assumed the intervals where from 0-24.
	 * 	In much larger possibility of intervals I would not go for this solution.
	 * 
	 * 	Cons:
	 * 		Two much space consumption
	 * 		I had to deal with a couple of non obvious cases
	 * 		Not scalable for large intervals
	 * 		It's not the most immediate solution to understand
	 * 
	 * 	Pros:
	 * 		Is better than exponential
	 * 
	 * 
	 * 	Main Concepts
	 * 		start edge == end edges is not overlap
	 * 			this created some challenges and resulted in the following composition of data stuctures
	 * 
	 * 		Edge - is the edge of some interval under the form of a code(start * 7 + end). 
	 * 			if 2 edges have the same code, means they overlap perfectly.
	 * 
	 * 		Content - is all the values between the interval edges (excluding edges)
	 * 			this is an hashset where for all meeting intervals, the values in between are placed
	 * 			If a new edge and its content maches some of this values than we have overlap
	 * 
	 * 		UnitaryEdges - are the edges which have no discrete values in between, and therefore they need to be put in another hashmap.
	 * 			A classic case would be 2,3 inside of 0,5. 2,3 has no discrete values so there is nothing to compare and therefore this case would fail.
	 * 			To solve it unitaryEdges where created, 
	 * 				so you compare your interval edge with edge and content hashsets
	 * 				and compare content with content and unitary hahsset.
	 * 				it would mean you have something inside, weather it is unitary interval or not
	 * 
	 * 
	 * 
	 * 
	 * 		Runtime: 132 ms, faster than 5.00% of Java online submissions for Meeting Rooms.
			Memory Usage: 107.5 MB, less than 5.13% of Java online submissions for Meeting Rooms.
	 * 
	 * This is an improved solution where I came with a time complexity of O(N).
	 * I sacrificed space though.
	 * I'm Assuming the intervals have a limit of 24.
	 * 
	 * @time  O(N) I think. however the time complexity is wasted by the insertions of elements in multiple data structures. it is a very good case of a O(N) that performs worst than NLOGN
	 * @space O(N) where N is the number of rows + 24 in the worst case
	 * @param intervals
	 * @return
	 */
	public boolean canAttendMeetings(int[][] intervals) {
		HashSet<Integer> edges = new HashSet<>();
		HashSet<Integer> content = new HashSet<>();
		HashSet<Integer> unitaryEdges = new HashSet<>();
		int start = 0, end = 1;
		int meetingStart = 0, meetingEnd = 0;

		if (intervals == null || intervals.length == 0)
		{
			return true;
		}

		for (int entry = 0; entry < intervals.length; entry++) {
			meetingStart = intervals[entry][start];
			meetingEnd = intervals[entry][end];

			//when meetings overlap completly
			if(hasFullOvelappingEdges(edges, content, meetingStart, meetingEnd))
			{
				return false;
			}

			//add meeting edges to the hashset
			edges.add(getCode(meetingStart, meetingEnd));


			// one meeting partly contains another, except for cases where meetings takes 1 hour
			if (hasOverlappingContent(meetingStart, meetingEnd, content, unitaryEdges)) {
				return false;
			}

			//when meeting takes only one hour
			addUnitaryEdge(meetingStart, meetingEnd, unitaryEdges);

		}

		return true;

	}

	private void addUnitaryEdge(int meetingStart, int meetingEnd, HashSet<Integer> unitaryEdges) {

		if (meetingEnd - meetingStart == 1) {
			unitaryEdges.add(meetingStart);
			unitaryEdges.add(meetingEnd);
		}
	}

	private boolean hasFullOvelappingEdges(HashSet<Integer> edges, HashSet<Integer> content, int meetingStart, int meetingEnd) {
		return edges.contains(getCode(meetingStart, meetingEnd)) || 
				content.contains(meetingStart)|| 
				content.contains(meetingEnd);
	}

	/**
	 * Adds and verifies if a meeting interval time (excluding edges) has overlapping time 
	 * @param meetingStart
	 * @param meetingEnd
	 * @param content
	 * @return
	 */
	private boolean hasOverlappingContent(int meetingStart, int meetingEnd, HashSet<Integer> content, HashSet<Integer> unitaryEdges) {

		for (int i = meetingStart + 1; i < meetingEnd; i++) {

			if (content.contains(i) || unitaryEdges.contains(i))
			{
				return true;
			}

			content.add(i);
		}

		return false;
	}

	private int getCode(int start, int end) {
		//7 is just a prime number... could be any ano but 1 ou 0 I think
		//calendar 0
		//calendar 1
		//code = 0 * 7 + 1 => 1
		return Math.min(start, end) * 7 + Math.max(start, end);
	}










	/*********************************
	 * SOLUTION 1
	 ********************************/
	
	public boolean canAttendMeetingsBruteForce0(int[][] intervals) {

		if (intervals == null || intervals.length == 0 || intervals[0].length < 2)
			return true;

		HashSet<Integer> visited = new HashSet<>();

		for (int rowOutter = 0; rowOutter < intervals.length; rowOutter++) {

			for (int rowInner = 0; rowInner < intervals.length; rowInner++) {

				//not process the same pair
				if (rowOutter != rowInner && !visited.contains(getHashCode(rowOutter, rowInner))) {

					//validate intervals
					if (!validateIntervals(intervals, rowOutter, rowInner) || !validateIntervals(intervals, rowInner, rowOutter))
					{
						return false;
					}

					visited.add(getHashCode(rowOutter, rowInner));
				}
			}
		}

		return true;
	}


	/**
	 * Has expected this solution was bad a mere brute force solution.
	 * 
	 * 		Runtime: 1499 ms, faster than 5.00% of Java online submissions for Meeting Rooms.
			Memory Usage: 45.6 MB, less than 5.13% of Java online submissions for Meeting Rooms.

			the runtime was drastically improved by removing the hashset from the equation
			 699 ms.
	 * 
	 * @time this looks like a O(N^2)
	 * @space this takes O(N) space because of the hashmap, if I couldn't affort more space I would remove it from the solution
	 * @param intervals
	 * @return
	 */
	public boolean canAttendMeetingsBruteForce(int[][] intervals) {

		if (intervals == null || intervals.length == 0 || intervals[0].length < 2)
			return true;

		for (int rowOutter = 0; rowOutter < intervals.length; rowOutter++) {

			for (int rowInner = 0; rowInner < intervals.length; rowInner++) {

				//not process the same pair
				if (rowOutter != rowInner) {

					//validate intervals
					if (!validateIntervals(intervals, rowOutter, rowInner))
					{
						return false;
					}
				}
			}
		}

		return true;
	}
	
	private int getHashCode(int rowOutter, int rowInner) {
		//7 is just a prime number... could be any ano but 1 ou 0 I think
		//calendar 0
		//calendar 1
		//code = 0 * 7 + 1 => 1
		return Math.min(rowOutter, rowInner) * 7 + Math.max(rowOutter, rowInner);
	}

	private boolean validateIntervals(int [][]intervals, int rowOutter, int rowInner) {

		int start = 0;
		int end = intervals[0].length - 1;

		if (intervals[rowOutter][start] < intervals[rowInner][start] && 
			intervals[rowInner][start] < intervals[rowOutter][end] ||
			
				intervals[rowOutter][start] < intervals[rowInner][end] && 
				intervals[rowInner][end] < intervals[rowOutter][end]	||
				
					intervals[rowOutter][start] == intervals[rowInner][start] && 
					intervals[rowOutter][end] == intervals[rowInner][end]) 
			return false;
		return true;
	}

}

class MeetingRoomsSolution {

	/**
	 * INTUITION
	 * 	What they do in here is sort the matrix before checking for overlapping
	 * 	Then if they find an interval end of a meeting bigger than the interval meeting of another meeting there is overlapping.
	 * 	A very beautiful resolution
	 * 
	 * @time 	O(NLOGN)  + O(N) = NLOGN
	 * @space 	O(1)
	 * @param intervals
	 * @return
	 */
	public boolean canAttendMeetings(int[][] intervals) {
		Arrays.sort(intervals, new Comparator<int[]>() {
			public int compare(int[] i1, int[] i2) {
				return i1[0] - i2[0];
			}
		});

		for (int i = 0; i < intervals.length - 1; i++) {
			if (intervals[i][1] > intervals[i + 1][0])
				return false;
		}
		return true;
	}
}
