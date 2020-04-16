package com.data.structures.problems;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/lru-cache/
 * MEDIUM
 * @author Nelson Costa
 * 
 * 
 * 		Runtime: 11 ms, faster than 99.91% of Java online submissions for LRU Cache.
		Memory Usage: 47.8 MB, less than 100.00% of Java online submissions for LRU Cache.
		
		I obtained times around 11 and 13ms. 10ms is the best. very happy with the result.
		
		Very hard question. a lot of details needed to be took into account
		
		A simpler solution could also be took If I had the pseudo head and tail
 *
 */
public class LRUCache {

	private HashMap<Integer, DLLNode> map;
	private DLL usage;
	private int capacity;

	public static void testcase1(LRUCache cache) {		
		cache.put(1, 1);
		cache.put(2, 2);
		System.out.println(cache.get(1));       // returns 1
		cache.put(3, 3);    // evicts key 2
		System.out.println(cache.get(2));       // returns -1 (not found)
		cache.put(4, 4);    // evicts key 1
		System.out.println(cache.get(1));       // returns -1 (not found)
		System.out.println(cache.get(3));       // returns 3
		System.out.println(cache.get(4));       // returns 4
	}

	public LRUCache(int capacity) {
		if(capacity <= 0)
			throw new IllegalArgumentException("capacity should be bigger than 0.");

		this.capacity = capacity;
		map = new HashMap<>();
		usage = new DLL();
	}

	/**
	 * Get value for key
	 * @param key
	 * @return
	 */
	public int get(int key) {
		DLLNode curr = map.get(key);

		if (curr != null) {
			updateUsage(curr);
			return curr.val;
		}

		return -1;
	}

	/**
	 * @failed 
	 * 1) Was obtaining a null pointer exception because of UpdateUsage which was not considering when the functions is used in put. other than on gets.
	 * 2) forgot to remove items from hashmap on eviction
	 * 3) failed again, era de facto um problema de referencias ao nivel da função de add (solved with assisted debugging)
	 * 4) failed again, could not figured out is a certain kind of null pointer exception. GOT IT....I FORGOT TO UPDATE HEAD ON PUT IN THE MIDDLE 
	 * 
	 * @param k
	 * @param v
	 */
	public void put(int k, int v) {
		DLLNode node = map.get(k);

		if (node != null)
		{			
			node.val = v;
			updateUsage(node);
		}
		else
		{
			ensureCapacity();
			node = new DLLNode(k, v);
			map.put(k, node);
			usage.add(node);
		}
	}

	/**
	 * Ensure Capacity
	 */
	private void ensureCapacity() {
		if(map.size() >= capacity)
		{
			DLLNode evicted =  usage.removeLast();
			map.remove(evicted.key);
		}
	}


	/**
	 * Updates the given note to the most recently used side of the usage list
	 * @param curr
	 */
	private void updateUsage(DLLNode curr) {


		if (curr == null)
			return;

		if (curr == usage.head)
		{
			return;
		}
		else if (curr == usage.tail)
		{
			DLLNode prev = curr.prev;
			curr.prev = null;
			curr.next = usage.head;

			prev.next = null;
			usage.tail = prev;

			usage.head.prev = curr;
			usage.head = curr;				
		}
		//in the middle
		else
		{
			DLLNode prev = curr.prev;
			DLLNode next = curr.next;

			curr.next = usage.head;
			curr.prev = null;
			usage.head.prev = curr;
			usage.head = curr;

			if (prev != null)
				prev.next = next;

			if (next != null)
				next.prev  = prev;
		} 
	}

	/**
	 * Doubly Linked List
	 * @author Nelson Costa
	 *
	 */
	class DLL {
		DLLNode head;
		DLLNode tail;
		int size;

		DLL(){
			head = null;
			tail = null;
		}

		public void add(DLLNode node) {

			if (head != null)
			{
				node.next = head;
				head.prev = node;
				head = node;
			}
			else
			{
				head = node;
				tail = node;
			}

			size++;
		}

		public DLLNode removeLast() {

			if (tail == null)
				return tail;

			DLLNode evicted = tail;

			if(tail.prev == null)
			{
				tail = null;
				head = null;
			}
			else 
			{
				DLLNode prev = tail.prev;
				tail.prev = null;
				tail = prev;
				tail.next = null;
			}
			return evicted;
		}
	}
	/**
	 * Doubly Linked List Node 
	 * @author Nelson Costa
	 *
	 */
	class DLLNode {
		int val;
		int key;
		public DLLNode next;
		public DLLNode prev;

		public DLLNode(int key, int val){
			this.key = key;
			this.val = val;
		}

		@Override
		public boolean equals(Object v) {
			return val == ((DLLNode) v).val && key == ((DLLNode) v).key ;
		}
	}
}

/**
 * Very Very interesting solution using the LinkedHashMap data structure
 * @author Nelson Costa
 *
 */
class LRUCacheSolution1 extends LinkedHashMap<Integer, Integer>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int capacity;

	public LRUCacheSolution1(int capacity) {
		super(capacity, 0.75F, true);
		this.capacity = capacity;
	}

	public int get(int key) {
		return super.getOrDefault(key, -1);
	}

	public void put(int key, int value) {
		super.put(key, value);
	}

	@Override
	protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
		return size() > capacity; 
	}
}


/**
 * Cleaver solution where pseudo head and pseudo tail are created to simplify validation
 * @author Nelson Costa
 *
 */
class LRUCacheSolution2 {

	class DLinkedNode {
		int key;
		int value;
		DLinkedNode prev;
		DLinkedNode next;
	}

	private void addNode(DLinkedNode node) {
		/**
		 * Always add the new node right after head.
		 */
		node.prev = head;
		node.next = head.next;

		head.next.prev = node;
		head.next = node;
	}

	private void removeNode(DLinkedNode node){
		/**
		 * Remove an existing node from the linked list.
		 */
		DLinkedNode prev = node.prev;
		DLinkedNode next = node.next;

		prev.next = next;
		next.prev = prev;
	}

	private void moveToHead(DLinkedNode node){
		/**
		 * Move certain node in between to the head.
		 */
		removeNode(node);
		addNode(node);
	}

	private DLinkedNode popTail() {
		/**
		 * Pop the current tail.
		 */
		DLinkedNode res = tail.prev;
		removeNode(res);
		return res;
	}

	private Map<Integer, DLinkedNode> cache = new HashMap<>();
	private int size;
	private int capacity;
	private DLinkedNode head, tail;

	public LRUCacheSolution2(int capacity) {
		this.size = 0;
		this.capacity = capacity;

		head = new DLinkedNode();
		// head.prev = null;

		tail = new DLinkedNode();
		// tail.next = null;

		head.next = tail;
		tail.prev = head;
	}

	public int get(int key) {
		DLinkedNode node = cache.get(key);
		if (node == null) return -1;

		// move the accessed node to the head;
		moveToHead(node);

		return node.value;
	}

	public void put(int key, int value) {
		DLinkedNode node = cache.get(key);

		if(node == null) {
			DLinkedNode newNode = new DLinkedNode();
			newNode.key = key;
			newNode.value = value;

			cache.put(key, newNode);
			addNode(newNode);

			++size;

			if(size > capacity) {
				// pop the tail
				DLinkedNode tail = popTail();
				cache.remove(tail.key);
				--size;
			}
		} else {
			// update the value.
			node.value = value;
			moveToHead(node);
		}
	}
}