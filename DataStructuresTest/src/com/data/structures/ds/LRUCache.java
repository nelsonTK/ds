package com.data.structures.ds;

import java.util.HashMap;

/**
 * Elements evicted can still be searched in Hashmap 
 * and they are added to the linkedlist with the updated usage (MRU)
 * 
 * This is not the expected behavior of LRU. (I Should reimplement this)
 * 
 * @author Nelson Costa
 *
 * @param <K>
 * @param <V>
 */
public class LRUCache<K, V> {

	private HashMap<K, DoublyLinkedListNode<V>> map = new HashMap<>();
	private DoublyLinkedList<V> usage;

	public LRUCache(int capacity){
		usage = new DoublyLinkedList<V>(capacity);
	}

	/**
	 * gets the value for the given key
	 * @param key
	 * @return
	 */
	public V get(K key) {
		DoublyLinkedListNode<V> node = getNode(key);

		return node == null? null : node.val;
	}

	/**
	 * puts a key and value to the LRUCache and updates its frequency in the usage data structure
	 * 
	 * @param key
	 * @param v
	 */
	public void put(K key, V v) {
		DoublyLinkedListNode<V> node = map.get(key);

		if (node == null)
		{
			node = new DoublyLinkedListNode<V>(v);
			map.put(key, node);
			usage.add(node);
		}
		else
		{
			if (!isCached(node)) {
				usage.add(node);
			}
			else
			{
				node.val = v;
				setMostFrenquetlyUsedNode(node);
			}
		}

		System.out.println("Put: key: " + key + " |  val: " + node);

	}

	/**
	 * gets the least frequently used given the initial capacity
	 * @return
	 */
	public V getLRU() {
		System.out.println("LRU: " + usage.getTail());
		return usage.getTail() == null? null : usage.getTail().val;
	}

	/**
	 * gets the most frequently used given the initial capacity
	 * @return
	 */
	public V getMRU() {
		System.out.println("MRU: " + usage.getHead());
		return usage.getHead() == null? null : usage.getHead().val;
	}

	/**
	 * gets the node for the given key
	 * @param key
	 * @return
	 */
	private DoublyLinkedListNode<V> getNode(K key) {
		DoublyLinkedListNode<V> node = map.get(key);

		if(node.equals(usage.getHead()))
			return usage.getHead();

		if(node != null)
		{
			//if previous node is not null than we need to update it to most recent node
			//if previous node is null, and node is not head, than it is a node out of cache we need to add it back
			if (node.prev == null)
			{
				usage.add(node);
			}
			else if (node.prev != null)
			{
				setMostFrenquetlyUsedNode(node);
				return usage.getHead();
			}
		}

		return null;
	}

	private void setMostFrenquetlyUsedNode(DoublyLinkedListNode<V> node) {

		if (usage.getHead().equals(node))
			return;

		DoublyLinkedListNode<V> prev = node.prev;
		DoublyLinkedListNode<V> next = node.next;

		if(prev != null)
		{
			prev.next = node.next;

			//if no next then we set prev to tail
			if (next != null) {
				node.next.prev = prev;
			}
			else
			{
				usage.setTail(prev);
			}

			//new head
			node.prev = null;
			node.next = usage.getHead();
			usage.getHead().prev = node;
			usage.setHead(node);
		}
	}

	/**
	 * checks if the node is cached
	 * @param node
	 * @return
	 */
	private boolean isCached(DoublyLinkedListNode<V> node) {

		if(node.equals(usage.getHead()) || node.equals(usage.getTail()) || node.prev != null)
			return true;
		return false;
	}

	public int getUsageSize() {
		return usage.getSize();
	}
	
	public int getUsageCapacity() {
		return usage.getCapacity();
	}

	public int getMapSize() {
		return map.size();
	}
}

class DoublyLinkedList<V>{
	private DoublyLinkedListNode<V> head;
	private DoublyLinkedListNode<V> tail;
	private int capacity;
	private int size;

	DoublyLinkedList()
	{
		capacity = 10;
		head = null;
		tail = null;
	}

	DoublyLinkedList(V v){
		capacity = 10;
		head = new DoublyLinkedListNode<V>(v);
		tail = head;
	}

	DoublyLinkedList(int capacity){
		this.capacity = capacity;
	}

	/**
	 * Adds a new element at the head of the list
	 * removes tail if capacity is 
	 * @param v 
	 */
	public void add(V v){
		add(new DoublyLinkedListNode<V>(v));
	}

	/**
	 * 
	 * @param node
	 */
	public void add(DoublyLinkedListNode<V> node) {	

		ensureCapacity();
		
		size++;
		
		node.next = head;
		head = node;

		//update previous Head connection to new head
		if (head.next != null)
			head.next.prev = head;

		//update tail to head if this is a single element list
		if(size == 1) {
			tail = head;
		}


	}

	/**
	 * Removes the last item from the list
	 */
	public void removeLast() {
		if (tail == null)
			//list is empty
			return;

		if (tail.prev != null)
		{
			DoublyLinkedListNode<V> beforeLast = tail.prev;
			beforeLast.next = null;
			tail.prev = null;
			tail = beforeLast;

		}
		//list only has one item
		else
		{
			tail = null;
			head = null;
		}
		size--;
	}

	/**
	 * ensures a valid capacity
	 * 
	 * WARNING: if the capacity was reached it will be decremented
	 */
	private void ensureCapacity() {
		if (size >= capacity && tail != null)
		{
			removeLast();
		}
	}

	public DoublyLinkedListNode<V> getHead(){
		return head;
	}

	public DoublyLinkedListNode<V> getTail(){
		return tail;
	}

	public void setHead(DoublyLinkedListNode<V> h) {
		head = h;
	}

	public void setTail(DoublyLinkedListNode<V> t) {
		tail = t;
	}

	public int getCapacity() {
		return capacity;
	}

	public int getSize() {
		return size;
	}
}

class DoublyLinkedListNode<V>{
	public V val;
	public DoublyLinkedListNode<V> next;
	public DoublyLinkedListNode<V> prev;

	DoublyLinkedListNode(V v) {
		val = v;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object node)
	{
		return this.val.equals(((DoublyLinkedListNode<V>)node).val);
	}

	@Override
	public String toString() {
		return val.toString();
	}
}
