package com.data.structures.problems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/task-scheduler
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class TaskScheduler {

	public static void main(String[] args) {
		//char [] tasks = {'A','A','A','B','B','B'};
		char [] tasks = {'A','B'};
		int n = 2;
		TaskScheduler t = new TaskScheduler();
		System.out.println(t.leastInterval(tasks, n));
	}


	 /*********************************
	 * SOLUTION 1
	 ********************************/
	/** 
	 * @intuition
	 * 		The goal was to create a structure with the counts and the tasks with those counts 
	 * 		and traverse the list in search for the first element that is elligible for the position
	 * 
	 * @score
	 *		Runtime: 724 ms, faster than 5.08% of Java online submissions for Task Scheduler.
	 *		Memory Usage: 39.8 MB, less than 99.37% of Java online submissions for Task Scheduler.
	 * 
	 * @fail 
	 * 		1) forgot to move the iteration forward 
	 * 		2) I was skiping the first element in the while loop 
	 * 		3) I was adding elements on decreasing when the key is zero
	 * 		4) floorkey did below or equal to
	 * 
	 * @time
	 * 		O(N * S) N is number of elements which are equal, and S equals to space/cooldown
	 * 
	 * @space
	 * 		O()
	 * 
	 **/
	private static TreeMap<Integer, LN> countChar;
	public int leastInterval(char[] tasks, int n) {

		HashMap<Character, Integer> charCount = new HashMap<>();
		//count chars
		for (char c : tasks) //O(N)
		{
			charCount.put(c, charCount.getOrDefault(c, 0) + 1);
		}

		countChar = new TreeMap<>();

		//group chars by count
		for (char c: charCount.keySet()) //O(N) at worst
		{
			countChar.computeIfAbsent(charCount.get(c), k -> new LN()).add(new Task(c, charCount.get(c)));
		}

		//Set first element to index zero 
		int curIndex = 0;
		LN curList;
		N curNode;
		Integer key;

		while (!countChar.isEmpty()) //O(1) 
		{
			//get bigger
			key = countChar.lastKey();
			curList = countChar.get(key);
			curNode = curList.head.next;//bigger

			while(!executed(curList, curNode, curIndex, n)) //O(N), for each unavailable processing time all elements are traversed
			{
				key = countChar.floorKey(key - 1);

				if (key != null)
				{
					curList = countChar.get(key);
					curNode = countChar.get(key).head.next;
				}
				else
					break;
			}
			curIndex++;
		}
		return curIndex;
	}

	public boolean executed(LN curList, N node, int curIndex, int cooldown){

		if (node.val.ch == Character.MIN_VALUE)
			return false;

		if (node.val.lastIndex == Integer.MIN_VALUE)
		{
			//System.out.println(node.val.ch);
			updateCounts(curIndex, node, curList);
			return true;
		}

		//node = node.next;
		while (node.val.ch != Character.MIN_VALUE)
		{
			if (node.val.lastIndex == Integer.MIN_VALUE)
			{
				//System.out.println(node.val.ch);
				updateCounts(curIndex, node, curList);
				return true;
			}

			if (curIndex - node.val.lastIndex > cooldown)
			{

				//System.out.println(node.val.ch);
				updateCounts(curIndex, node, curList);
				return true;
			}
			node = node.next;
		}

		return false;

	}

	private void updateCounts(int curIndex, N node, LN curList)
	{
		//remover da task atual
		//baixar 1 nivel
		//update do last index da task

		curList.remove(node);
		if (curList.size <= 0) 
		{
			//System.out.println("remove");
			countChar.remove(node.val.count);
		}

		node.val.count--;
		node.val.lastIndex = curIndex;
		if (node.val.count > 0)
		countChar.computeIfAbsent(node.val.count, k -> new LN()).add(node.val); 

	}


	class Task{
		char ch;
		int count;
		int lastIndex;

		Task (char c, Integer count){
			ch = c;
			lastIndex = Integer.MIN_VALUE;
			this.count = count;
		}
		Task (char c){
			ch = c;
			lastIndex = Integer.MIN_VALUE;
		}
	}


	class LN {
		N head;
		N tail;
		int size;

		LN (){
			head = new N();
			tail = new N();
			head.next = tail;
			tail.prev = head;
		}

		public void add(Task t)
		{

			N node = new N(t);
			N prev = tail.prev;
			prev.next = node;
			node.prev = prev;
			node.next = tail;
			tail.prev = node;        
			size++;
		}

		public void remove(N node)
		{
			N prev = node.prev;
			N next = node.next;
			prev.next = next;
			next.prev = prev;
			if (size > 0)
				size--;
		}
	}

	class N
	{
		Task val;
		N next;
		N prev;

		N()
		{
			val = new Task(Character.MIN_VALUE);
		}

		N (Task t)
		{
			val = t; 
		}
	}
}

/**
 * GREEDY solution very beautiful
 * 		The gist of this solution was to find the iddle time.
 * 		Basically we find the max possible slots, 
 * 		and subtrack all used slots per task and then whats left is the iddle slots
 * 
 * 	1) find frequencies
 * 
 *  2) sort array
 *  
 *  3) find max and find out the max possible value
 *  
 *  4) subtrack from the max possible value the max possible value for each element
 *  
 * @author Nelson Costa
 *
 */
class TaskSchedulerSolution1 {
    public int leastInterval(char[] tasks, int n) {
        // frequencies of the tasks
        int[] frequencies = new int[26];
        for (int t : tasks) {
            frequencies[t - 'A']++;
        }

        Arrays.sort(frequencies);

        // max frequency
        int f_max = frequencies[25];
        int idle_time = (f_max - 1) * n;
        
        for (int i = frequencies.length - 2; i >= 0 && idle_time > 0; --i) {
            idle_time -= Math.min(f_max - 1, frequencies[i]); 
        }
        idle_time = Math.max(0, idle_time);

        return idle_time + tasks.length;
    }
}
