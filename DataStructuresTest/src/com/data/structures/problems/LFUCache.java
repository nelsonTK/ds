package com.data.structures.problems;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/lfu-cache/
 * HARD
 * @author Nelson Costa
 *
 */
public class LFUCache {

	public static void main(String[] args) {
		//Your LFUCache object will be instantiated and called as such:
		LFUCache obj = new LFUCache(2);
		obj.put(1, 1);
		obj.put(2, 2);
		System.out.println(obj.get(1));
		obj.put(3, 3);
		System.out.println(obj.get(2));
		System.out.println(obj.get(3));
	}



	int minFreq;
	int capacity;
	HashMap<Integer, Pair> valToFreq = new HashMap<>();
	HashMap<Integer, LL> freqToList = new HashMap<>();

	/**
	 * @intuition
	 * 		The main line of though is to use hashmaps and linked lists to achieve O(1) for get and put.
	 * 		To ease the work of adding and removing our linked list also uses pseudo head and tail.
	 * 		
	 * 		We have an hashmap to access elements frequency in O(1)
	 * 		We also use an hashmap to access linkedlists with elements of a particular frequency
	 * 		
	 * 		to remove in LFU we should access elements in the lowerfrequency, and from those we should remove the least recently used.
	 * 		to do so we just remove the head, because it was the first to be added.
	 * 		
	 * 
	 * 		putting elements we have to update our hashmap valuesToFrequency, than update FrequencyToList.
	 * 		where we move the element from list frequency N to list frequency N + 1.
	 * 
	 * 		We also have something very important to keep the list working which is the MINFREQ
	 * 		We have to keep track of the current minimum frequency so that we have constant time access to 
	 * 		the min list, else we would need to traverse it and find out.
	 * 		MinFreq could be update on gets and on puts. if we empty the current min list, we can greedy sum + 1 to it.
	 * 		
	 * 
	 * 		so this are the tree major components to create a LFUCache
	 * 
	 * 			- HashMap Key, Pair( Node, Frequency)
	 * 			- HashMap Frequency, LinkedLists
	 * 			- MinFreq Variable
	 * 
	 * 
	 *                
	 * @score
	 * 		Runtime: 18 ms, faster than 82.10% of Java online submissions for LFU Cache.
			Memory Usage: 50.1 MB, less than 51.88% of Java online submissions for LFU Cache.
	 * 
	 * 
	 * @fail
	 * 		1) Forgot to connect the pseudo nodes....duh
	 * 		2) I made a mistake with getOrDefault, I should have used computeIfAbsent. 
	 * 			I changed the reference but did not added it to the hashmap	
	 * 		3) Failed capacity not working as expected
	 * 		4) I was increasing the capacity badly... capacity do not increases
	 * 		5) bad removeMethod, I left one right more than was needed
	 * 		6) forgot to transfer nodes from one list to another
	 * 
	 * 
	 * 
	 * @param c
	 */
	public LFUCache(int c) {

		capacity = c;
		minFreq = Integer.MAX_VALUE;
	}

	public int get(int key) {
        if(capacity == 0)
            return -1;
        
		LLNode node = getNode(key);

		return node == null ? -1 : node.val;
	}

	public void put(int key, int value) {
        if(capacity == 0)
            return;
        
		LLNode node;
        
		//if exists
		if(valToFreq.containsKey(key))
		{         
			node = getNode(key);
			node.val = value;
		}
		else 
        //if not exists
		{

			if (valToFreq.size() + 1 <= capacity)
			{
                //Update HashMap
				node = new LLNode(value, key);
				Pair p = new Pair(node, 1);
				valToFreq.put(key, p);

                //Update LinkedList
				freqToList.computeIfAbsent(p.freq, k -> new LL()).add(node);

                //Update Min size
				if(p.freq < minFreq)
					minFreq = p.freq;

			}
			else
			{
                /***OLD NODE***/
                
                //Remove Old Node from List
				LL list = freqToList.get(minFreq);
				LLNode oldest = list.removeLFU();   
                
                //Remove old node from HashMap
				Pair p = valToFreq.remove(oldest.key);

                //Remove LinkedList from HashMap of LinkedLists if empty
				if (list.size == 0)
				{
					freqToList.remove(p.freq);
				}
                
                /***NEW NODE***/
                
                //Add new node to hashmap
				node = new LLNode(value, key);
				p = new Pair(node, 1);
				valToFreq.put(key, p);   
                
                //Add new node to Linked List
				freqToList.computeIfAbsent(p.freq, k -> new LL()).add(node);
                
                //Update min frequency
				minFreq = 1;
				
			}

		}

	}


	private LLNode getNode(int key){

		if(valToFreq.containsKey(key))
		{
			//update freq
			Pair p = valToFreq.get(key);

			//update List
			LL list = freqToList.get(p.freq);
			list.removeNode(p.node);

			if (list.size == 0)
			{ 
				freqToList.remove(p.freq);

				//update min
				if (p.freq == minFreq)
				{
					minFreq++;
				}
			}            

			//update element frequency
			p.freq++;
			freqToList.computeIfAbsent(p.freq, k -> new LL()).add(p.node);

			return p.node;
		}

		return null;
	}

	class Pair{
		LLNode node;
		int freq;

		public Pair(LLNode n, int f)
		{
			node = n;
			freq = f;
		}
	}
	
	class LL {
		int size;
		private LLNode pseudoHead;
		private LLNode pseudoTail;

		public LL(){
			pseudoHead = new LLNode(-1, -1);
			pseudoTail = new LLNode(-1, -1); 
			pseudoHead.right = pseudoTail;
			pseudoTail.left = pseudoHead;
		}

		//adds element to the tail of the list
		public boolean add(LLNode node)
		{
			node.right = pseudoTail;
			node.left = pseudoTail.left;
			pseudoTail.left.right = node;
			pseudoTail.left = node;
			size++;
			return true;

		}

		//removes the oldest element in the list
		public LLNode removeLFU(){
			if (pseudoHead.right != pseudoTail)
			{
				LLNode oldest = pseudoHead.right;
				pseudoHead.right = pseudoHead.right.right;
				pseudoHead.right.left = pseudoHead;

				oldest.right = null;
				oldest.left  = null;
				size--;
				return oldest;
			}
			return null;
		}

		public LLNode removeNode(LLNode node)
		{
			if(node.right != null && node.left != null)
			{
				node.right.left = node.left;
				node.left.right = node.right;
				node.left = null;
				node.right = null;
				size--;

				return node;
			}
			return null;
		}
	}

	class LLNode{
		int val;
		int key;
		LLNode right;
		LLNode left;

		public LLNode (int v, int k)
		{
			val = v;
			key = k;
		}
	}
}
