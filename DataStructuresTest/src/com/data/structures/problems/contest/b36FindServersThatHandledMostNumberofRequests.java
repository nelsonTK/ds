package com.data.structures.problems.contest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeSet;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/find-servers-that-handled-most-number-of-requests/
 * HARD
 * @author Nelson Costa
 *
 */
public class b36FindServersThatHandledMostNumberofRequests extends LeetCodeExercise
{
	static b36FindServersThatHandledMostNumberofRequests b = new b36FindServersThatHandledMostNumberofRequests();
	public static void main(String[] args) {
		int k = 3;
		int [] arrival = stringToArray("[2,6,7,8]");
		int [] load = stringToArray("[1,3,1,4]");

		k = 2;
		arrival = stringToArray("[1,3,5,6,7,12]");
		load =  stringToArray("[3,4,6,5,5,6]");
		System.out.println(b.busiestServers(k, arrival, load));
	}

	/*********************************
	 * SOLUTION 3
	 ********************************/
	int max = 0;
	/**
	 * 
	 * @intuition
	 * 		The intuition is to have two data structures Treeset and PriorityQueue
	 * 		That allows you to have in the treeset only the available servers, and in the priority queue the busy servers
	 * 		And you have to maintain this property all the time.
	 * 		This approach is very clever and it is common to see this solutions 
	 * 		where you only maintain the available items to avoid traversing every one of them
	 * 		
	 * 		ALGO DESIGN
	 * 
		        //add available servers to the tree

		        //for each arrival
		            //get target server

		            //get available elements from the PQ

		            //check if my target element is available

		                //if is not available check availability of forward elements

		                //if no forward availables
		                    //get first available

		                //if not back or forward we drop

		                //if we find we should add to the server another request count


		        //return the total of requests that have the same value than the max.
	 * @time
	 * 		kLogk + NkLogk
	 * 
	 * @space
	 * 		N + k
	 * 
	 * @param k
	 * @param arrival
	 * @param load
	 * @return
	 */
	public List<Integer> busiestServers(int k, int[] arrival, int[] load) {      

		TreeSet<Integer> available = new TreeSet<>();
		PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.last, b.last));
		int [] reqCount = new int[k];
		int target, time;

		//add available servers
		for (int i = 0; i < k; i++)
		{
			available.add(i);
		}

		for (int i = 0; i < arrival.length; i++)
		{
			target = i % k;
			time = arrival[i];
			//get available elements from the PQ
			while (!pq.isEmpty() && pq.peek().last < time)
			{
				available.add(pq.poll().id);
			}

			//if we have target available we will remove it from availables and add it to busy
			if (available.size() > 0 && available.contains(target))
			{
				updateServerStats(i, target, reqCount, available, pq, load, arrival);
			}
			else if (available.size() > 0)
			{
				//find next next available one                
				boolean found = updateServerStats(i, available.ceiling(target), reqCount, available, pq, load, arrival);

				//if we dont have a next one we will try the first one, if we don't find we just drop it
				if (!found)
				{
					updateServerStats(i, available.first(), reqCount, available, pq, load, arrival);
				}
			}

		}  

		List<Integer> ans = new ArrayList<>();

		for (int i = 0; i < k ; i++)
		{
			if (reqCount[i] == max)
				ans.add(i);
		}

		return ans;      
	}

	private boolean updateServerStats(int i, Integer target, int[] reqCount, TreeSet<Integer> available, PriorityQueue<Pair> pq, int [] load, int [] arrival)
	{
		if (target != null)
		{
			reqCount[target]++;            
			available.remove(target);
			pq.add(new Pair(target, arrival[i] + load[i] - 1) );

			if (reqCount[target] > max)
				max = reqCount[target];

			return true;
		}
		return false;
	}

	class Pair{
		int id;
		int last;

		public Pair( int i, int l)
		{
			id = i;
			last = l;
		}

		@Override
		public String toString()
		{
			return id + ":" + last;
		}
	}


	/*********************************
	 * SOLUTION 1 or 2
	 ********************************/
	/**
	 * [TIME LIMIT EXCEDEED SOLUTION]
	 * @author Nelson Costa
	 */
	class Solution1{
		int max = 0;
		/**
		 * I dont remember if this was my first or second implementation
		 * but the intuition of the failing approaches was to have a priority queue with a available server in the top.
		 * and have a hashmap in which for each server you would have its latest state
		 * 
		 * And then you just follow the question description procedure for searching the next server and the previous server
		 * I committed some cool errors
		 * 
		 * but I also was pretty much confused about the question, and the indexes and so forth
		 * 
		 * @fail
		 * 		1) os arrivals não eram lineares ou eram?
		 * 		2) for got to add all elements to pq?
		 * 		3) Big mistake in the priority queue, I was changing the values inside the priority queue and that was causing my queue to be all messed up
		 * 		4) Time limit exceeded
		 * 
		 * @param k
		 * @param arrival
		 * @param load
		 * @return
		 */
		public List<Integer> busiestServers2(int k, int[] arrival, int[] load) {


			HashMap<Integer, Server> map = new HashMap<>();
			PriorityQueue<Server> pq = new PriorityQueue<>((a,b) -> 
			{
				if (a.last == b.last)
					return 0;
				else if (a.last < b.last)
					return -1;
				else
					return 1;
			});

			Server curServ;
			//for each request arrival
			//get the correct Server and see if it is available
			//get the 
			int targetServer;

			int i;
			for (int j = 0; j < arrival.length; j++)
			{
				i = arrival[j];
				targetServer = (j % k);

				curServ = map.getOrDefault(targetServer, new Server(targetServer, 0, -1));

				if(curServ.last < i)
				{
					curServ.reqCount++;
					curServ.last = i + load[j] - 1;
					map.put(targetServer, curServ);
					addToPq(curServ, pq, map);
					if(curServ.reqCount > max)
					{
						max = curServ.reqCount;
					}
				}
				else
				{
					//if priority queue has a valid number

					if (thereIsAvailable(i, pq, map))
					{

						//search right
						boolean found = search(i, j, targetServer + 1, k - 1, map, pq, load);

						if (!found)
						{
							//search left
							search(i,j, 0, targetServer - 1, map, pq, load);
						}
					}
				}
			}

			List<Integer> ans = new ArrayList<>();
			for (Integer s : map.keySet())
			{
				curServ = map.get(s);

				if (curServ.reqCount == max)
					ans.add(curServ.id);
			}

			return ans;

		}


		private void addToPq(Server s, PriorityQueue<Server> pq,  HashMap<Integer, Server> map)
		{
			removeOutdated(pq, map);

			pq.add(new Server(s.id, s.reqCount,s.last));
		}


		private boolean search(int i, int j, int start, int end, HashMap<Integer, Server> map, PriorityQueue<Server> pq, int [] load)
		{
			if (start > end)
			{
				return false;
			}

			Server curServ;
			for (int server = start; server <= end; server++) 
			{
				curServ = map.get(server);

				if (curServ == null)
				{
					map.put(server, new Server(server, 1, i + load[j] -1));
					addToPq(curServ, pq, map);
					return true;
				}
				else if (curServ.last < i)
				{
					curServ.reqCount++;
					curServ.last = i + load[j] - 1;


					if(curServ.reqCount > max)
					{
						max = curServ.reqCount;
					}

					addToPq(curServ, pq, map);
					return true;
				}
			}

			return false;
		}


		private boolean thereIsAvailable(int i, PriorityQueue<Server> pq,  HashMap<Integer, Server> map)
		{
			removeOutdated(pq, map);

			if (!pq.isEmpty() && pq.peek().last < i)
				return true;

			return false;
		}


		private void removeOutdated(PriorityQueue<Server> pq,  HashMap<Integer, Server> map)
		{
			while (!pq.isEmpty() && pq.peek().last != map.get(pq.peek().id).last)
			{
				pq.remove();
			}
		}


		class Server{
			int id;
			int reqCount;
			int last;

			public Server(int s, int r, int l)
			{
				id = s;
				reqCount = r;
				last = l;
			}

			@Override
			public String toString()
			{
				return id + ":" + reqCount + ":" + last;
			}
		}
	}
}


/*********************
 * OTHERS SOLUTIONS
 *********************/
/**
 * this solution uses the same principle then my solution
 * 
 * @author Nelson Costa
 *
 */
class b36FindServersThatHandledMostNumberofRequestsUnofficialSolution1 {
	public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
		int[] counter = new int[k];
		// use a tree to track available servers
		TreeSet<Integer> available = new TreeSet<Integer>();
		for (int num = 0; num < k; num++) {
			available.add(num);
		}
		// use a PQ to maintain the availability at current arrival time
		Queue<int[]> busyServers = new PriorityQueue<>((a, b)->(a[0] - b[0]));

		for (int idx = 0; idx < arrival.length; idx++) {
			int curTime = arrival[idx];
			int endTime = curTime + load[idx];
			while (!busyServers.isEmpty() && busyServers.peek()[0] <= curTime) {
				int freedServer = busyServers.poll()[1];
				available.add(freedServer);
			}
			if (available.size() == 0) continue; // all busy
			Integer assignNum = available.ceiling(idx % k);
			if (assignNum == null) {
				assignNum = available.first();
			}
			counter[assignNum]++;
			available.remove(assignNum);
			busyServers.offer(new int[] {endTime, assignNum});
		}

		return findMaxesInCounter(counter);
	}


	private List<Integer> findMaxesInCounter(int[] counter) {
		int max = 0;
		for (int i = 0; i < counter.length; i++) {
			max = Math.max(max, counter[i]);
		}
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < counter.length; i++) {
			if (counter[i] == max) {
				result.add(i);
			}
		}
		return result;
	}
}
