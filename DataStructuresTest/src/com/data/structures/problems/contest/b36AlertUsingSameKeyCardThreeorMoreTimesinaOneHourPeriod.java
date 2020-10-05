package com.data.structures.problems.contest;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * https://leetcode.com/problems/alert-using-same-key-card-three-or-more-times-in-a-one-hour-period/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class b36AlertUsingSameKeyCardThreeorMoreTimesinaOneHourPeriod {


	/*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * @intuition
	 * 		Sweepline algorithm applied to this solution
	 * 		Is not the most efficient solution could be done much better is a good interval exercise
	 * 
	 * @comments
	 * 		This solution consumes too much memory, and time.
	 * 		An improvement could be to just initialize one array and reset it's value overtime, while processing
	 * 
	 * @optimizations
	 * 		A different way of doing this exercise would be to do a sliding window, with a queue
	 * 
	 * @time 	
	 * 		O(NLogN)
	 * 
	 * @space
	 * 		O(N)
	 * 
	 * @param keyName
	 * @param keyTime
	 * @return
	 */
	public List <String> alertNames(String[] keyName, String[] keyTime) {
		HashMap <String, List <String>> nameTime = new HashMap <> ();

		//add name and time entries
		for (int i = 0; i < keyName.length; i++) {
			nameTime.computeIfAbsent(keyName[i], k -> new ArrayList < String > ()).add(keyTime[i]);
		}

		int[] hours;
		int[] curTime;
		int timeIndex;
		List <String> ans = new ArrayList <> ();
		for (String name: nameTime.keySet()) {

			hours = new int[60 * 24];//all minutes in a day            
			//for each name mark the the starting point and end point of the interval

			for (String time: nameTime.get(name)) {

				curTime = getTime(time);

				timeIndex = getArrayPosition(curTime[0], curTime[1]);

				hours[timeIndex]++;


				//if the expiring time is past mid night we skip
				if (curTime[0] + 1 < 24) {
					if (curTime[0] + 1 == 23 && curTime[1] == 59) {
						//skip
						continue;
					} 
					//if it ends in 59 we have to increment the hour 2 times
					//09:59 - 10:59 -> 11:00
					else if (curTime[1] == 59) { 
						timeIndex = getArrayPosition(curTime[0] + 1 + 1, 0);
						hours[timeIndex]--;
					} 
					//else we just increase hour
					else {
						timeIndex = getArrayPosition(curTime[0] + 1, curTime[1] + 1);
						hours[timeIndex]--;
					}
				}
			}

			//do comulative sum of intervals and interrupt as soon as we find 3 element
			for (int j = 1; j < hours.length; j++) {
				hours[j] += hours[j - 1];

				if (hours[j] >= 3) {
					ans.add(name);
					break;
				}
			}
		}
		Collections.sort(ans);
		return ans;
	}



	private int[] getTime(String t) {
		String[] time = t.split(":");
		return new int[] {
				Integer.parseInt(time[0]), Integer.parseInt(time[1])
		};
	}

	private int getArrayPosition(int hour, int min) {
		return hour * 60 + min;
	}
}


/*********************
 * OTHERS SOLUTIONS
 *********************/

/***
 * Solution using treeset to sort the entries
 * 
 * Map (name to TreeSet)
 * then see if there is any moment in which the current element is in the same hour than two elements in the past
 * 
 * very cleaver and simple
 * 
 * @author Nelson Costa
 *
 */
class b36AlertUsingSameKeyCardThreeorMoreTimesinaOneHourPeriodUnofficialSolution2 {
	public List<String> alertNames(String[] keyName, String[] keyTime) {
		Map<String, TreeSet<Integer>> nameToTime = new HashMap<>();
		for (int i = 0; i < keyName.length; ++i) {
			int time = Integer.parseInt(keyTime[i].substring(0, 2)) * 60 + Integer.parseInt(keyTime[i].substring(3));
			nameToTime.computeIfAbsent(keyName[i], s -> new TreeSet<>()).add(time);
		}
		TreeSet<String> names = new TreeSet<>();
		for (Map.Entry<String, TreeSet<Integer>> e : nameToTime.entrySet()) {
			List<Integer> list = new ArrayList<>(e.getValue());
			for (int i = 2; i < list.size(); ++i) {
				if (i >= 2 && list.get(i) - list.get(i - 2) <= 60 ) {
					names.add(e.getKey());
					break;
				}
			}
		}
		return new ArrayList<>(names);
	}
}
/**
 * Sliding window with queue, one possible solution.
 * he uses a map of names and dequeues
 * he pairs the events per name and time.
 * then he traverses the events and add them to the user dequeue, if the user queue is 3 or bigger you found a guy that needs to be alerted
 * when you add something to the queue you verify if the first element of it is still valid.
 * 
 * this is not the standard easy approach doesnt look that efficient, but is a good shot
 * 
 * @author Nelson Costa
 *
 */
class b36AlertUsingSameKeyCardThreeorMoreTimesinaOneHourPeriodUnofficialSolution1 {
	public List<String> alertNames(String[] keyName, String[] keyTime) {
		Set<String> set = new HashSet<>();
		Pair[] events = new Pair[keyName.length];
		for (int i = 0; i < keyName.length; i++) {
			int hh = Integer.parseInt(keyTime[i].substring(0, 2));
			int mm = Integer.parseInt(keyTime[i].substring(3));
			events[i] = new Pair(keyName[i], hh*60 + mm);
		}
		Arrays.sort(events, (p1, p2) -> Integer.compare(p1.time, p2.time));
		Map<String, Deque<Integer>> map = new HashMap<>();
		for (Pair e : events) {
			if (!map.containsKey(e.name)) map.put(e.name, new ArrayDeque<>());
			Deque<Integer> q = map.get(e.name);
			q.addLast(e.time);
			while (q.peekFirst() < e.time-60) q.removeFirst();
			if (q.size() >= 3) set.add(e.name);
		}
		List<String> ans = new ArrayList<>(set);
		Collections.sort(ans);
		return ans;
	}
}

class Pair {
	String name;
	int time;
	public Pair(String s, int t) {
		name = s;
		time = t;
	}
}
