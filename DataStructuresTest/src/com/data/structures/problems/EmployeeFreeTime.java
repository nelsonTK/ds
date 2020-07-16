package com.data.structures.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/employee-free-time/
 * HARD
 * @author Nelson Costa
 *
 */
public class EmployeeFreeTime extends LeetCodeExercise{

	private static List<Interval> convertToInterval(int [][] intervals){
		List<Interval> result = new ArrayList<>();
		for (int[] inter : intervals)
		{
			result.add(new Interval(inter[0],inter[1]));
		}

		return result;
	}

	static EmployeeFreeTime e = new EmployeeFreeTime();
	static EmployeeFreeTimeUnofficialSolution1 e2 = new EmployeeFreeTimeUnofficialSolution1();
	static EmployeeFreeTimeSolution2 e3 = new EmployeeFreeTimeSolution2();
	public static void main(String[] args) {

		List<List<Interval>> input = new ArrayList<>();
		input.add(convertToInterval(stringToMatrix("[[7,24],[29,33],[45,57],[66,69],[94,99]]")));
		input.add(convertToInterval(stringToMatrix("[[6,24],[43,49],[56,59],[61,75],[80,81]]")));
		input.add(convertToInterval(stringToMatrix("[[5,16],[18,26],[33,36],[39,57],[65,74]]")));
		input.add(convertToInterval(stringToMatrix("[[9,16],[27,35],[40,55],[68,71],[78,81]]")));
		input.add(convertToInterval(stringToMatrix("[[0,25],[29,31],[40,47],[57,87],[91,94]]")));

		System.out.println(Arrays.toString(e.employeeFreeTime(input).toArray()));
		System.out.println(Arrays.toString(e2.employeeFreeTime(input).toArray()));
		System.out.println(Arrays.toString(e3.employeeFreeTime(input).toArray()));

	}

	/**
	 * @intuition
	 * 		Sort the intervals by start date and work from there
	 * 		we have a current interval that compares with the next intervals
	 * 		
	 * 
	 * @score
	 * 		Runtime: 9 ms, faster than 89.28% of Java online submissions for Employee Free Time.
	 * 		Memory Usage: 41.3 MB, less than 48.24% of Java online submissions for Employee Free Time.
	 * 
	 * @fail 
	 *       1) on the core logic of comparing the end date i switched end per start and was difficult to catch as this is kinda of confusing. 
	 *
	 * @alternatives
	 * 		I believe that I could have used an ArrayList, but I'm not sure if it would be without a doubt quicker
	 *
	 * @debug 
	 * 		yes
	 *
	 * @time O(NLogN) N equals to all intervals (M*N)
	 * @space O(N)
	 **/
	public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {

		if (schedule.size() == 1)
			return new ArrayList<Interval>();

		//Create a minPQ
		PriorityQueue<Interval> pq = new PriorityQueue<>(
				(a, b) -> 
				{
					int compare = Integer.compare(a.start, b.start);

					if (compare == 0)
						return Integer.compare(a.end, b.end);

					return compare;
				}
				);

		//fill the priority queue
		for(List<Interval> empIntervals : schedule) //O(NLogN)
		{
			for(Interval interval : empIntervals)
			{
				pq.add(interval);
			}
		}

		List<Interval> ans = new ArrayList<>();

		Interval cur = pq.poll(), next;

		//fill answer
		while(!pq.isEmpty()) //O(N)
		{
			next = pq.poll();
			System.out.println(next);
			//if next.start after current.end create interval
			if (next.start > cur.end)
			{
				ans.add(new Interval(cur.end, next.start));
				cur = next;
			}
			//if next.end > cur.end update cur
			else if (next.end > cur.end)
			{
				cur = next;
			}
		}

		return ans;
	}
}

class Interval {
	public int start;
	public int end;

	public Interval() {}

	public Interval(int _start, int _end) {
		start = _start;
		end = _end;
	}
	
	public String toString() {
		return "["+start+ ", " + end + "]";
	}
};

/*********************
* OTHERS SOLUTIONS
*********************/

/**
 * @intuition
 * 		Beautiful solution where priority queue is used in the advance way.
 * 		
 * 		Instead of having all the employees intervals, it starts by having all the employee indexes.
 * 	
 * 		From there it starts with the one with the lowest start and puts in the queue the next element from that employee if we have not processed it completly.
 * 
 * 		this continues while the priority queue is not over.
 * 
 * 		This is a classic advance use of priority queue, I haven't done it for a long time :)
 * 
 * 		and it decreases the time complexity
 * 
 * 
 *	@time
 *		O(CLogN) N number of employees, C number of all intervals.
 * 
 * 
 * @author Nelson Costa
 *
 */
class EmployeeFreeTimeSolution2 {
    public List<Interval> employeeFreeTime(List<List<Interval>> avails) {
        List<Interval> ans = new ArrayList();
        PriorityQueue<Job> pq = new PriorityQueue<Job>((a, b) ->
            avails.get(a.eid).get(a.index).start -
            avails.get(b.eid).get(b.index).start);
        int ei = 0, anchor = Integer.MAX_VALUE;

        for (List<Interval> employee: avails) {
            pq.offer(new Job(ei++, 0));
            anchor = Math.min(anchor, employee.get(0).start);
        }

        while (!pq.isEmpty()) {
            Job job = pq.poll();
            Interval iv = avails.get(job.eid).get(job.index);
            if (anchor < iv.start)
                ans.add(new Interval(anchor, iv.start));
            anchor = Math.max(anchor, iv.end);
            // Add element from the employee to the priority queue, 
            // a classic advance use of priority queue
            if (++job.index < avails.get(job.eid).size())
                pq.offer(job);
        }

        return ans;
    }
}

class Job {
    int eid, index;
    Job(int e, int i) {
        eid = e;
        index = i;
    }
}

/**
 * One of the top solutions
 * 
 * The author does the merge of the results with the merge sort and returns the list of intervals already merged
 * 
 * so if the end of previous array different from the beginning of the next we know we go a interval.
 * 
 * This solution solves the toughest part of the problem with the sorting algorithm
 * 
 * @author Nelson Costa
 *
 */
class EmployeeFreeTimeUnofficialSolution1 {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> intervals = mergeIntervals(schedule, 0, schedule.size() - 1);
        List<Interval> result = new ArrayList<>();
        for (int i = 1; i < intervals.size(); i++) {
            int start = intervals.get(i-1).end;
            int end = intervals.get(i).start;
            if (start != end) {
                result.add(new Interval(start, end));
            }
        }
        return result;
    }
    
    private List<Interval> mergeIntervals(List<List<Interval>> schedule, int start, int end) {
        if (start > end) return new ArrayList<>();
        if (start == end) return schedule.get(start);
        int mid = start + (end - start) / 2;
        List<Interval> left = mergeIntervals(schedule, start, mid); 
        List<Interval> right = mergeIntervals(schedule, mid+1, end);
        return merge(left, right);
    }
    
    private List<Interval> merge(List<Interval> list1, List<Interval> list2) {
        List<Interval> result = new ArrayList<>();
        int p = 0;
        int q = 0;
        int start = Integer.MAX_VALUE;
        int end = Integer.MIN_VALUE;
        while(p < list1.size() || q < list2.size()) {
            Interval i1 = p < list1.size() ? list1.get(p) : null;
            Interval i2 = q < list2.size() ? list2.get(q) : null;
            if (i2 == null || (i1 != null && i1.end <= i2.start)) {
                // non overlap
                if (start != Integer.MAX_VALUE) {
                    result.add(new Interval(start, end));
                    start = Integer.MAX_VALUE;
                } else {
                    result.add(i1);
                }
                p++;
            } else if (i1 == null || (i2 != null && i2.end <= i1.start)) {
                if (start != Integer.MAX_VALUE) {
                    result.add(new Interval(start, end));
                    start = Integer.MAX_VALUE;
                } else {
                    result.add(i2);
                }
                q++;
            } else {
                // overlap
                start = Math.min(start, Math.min(i1.start, i2.start));
                end = Math.max(end, Math.max(i1.end, i2.end));
                if (i1.end < i2.end) {
                    p++;
                } else{
                    q++;
                }
            }
        }
        
        return result;
    }
}