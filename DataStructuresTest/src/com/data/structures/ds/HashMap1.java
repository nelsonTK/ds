package com.data.structures.ds;

import java.util.HashSet;

/**
 * Current Bugs:
 * 		Rehashing after resize is not happening like it was expecting
 * @author Nelson Costa
 *
 * @param <K>
 * @param <V>
 */
public class HashMap1<K, V> {

	Entry<K,V> [] entry;
	int capacity;
	static final float LOAD_FACTOR = 0.75f;
	static final boolean DEBUG_MODE = true;
	private int size;

	public HashMap1() {
		super();
		capacity = 1;
		size = 0;
	}

	
	/**
	 * Ao fazer resize podem ocorrer resizings dentro de resizings
	 * @param key
	 * @param newVal
	 */
	@SuppressWarnings("unchecked")
	public void put(K key, V newVal) {

		if (entry == null)
		{
			entry = new Entry[capacity];
		}

		int hashIndex = getHashCode(key);
		boolean returnLastIfNotFound = true;
		Entry <K, V> existingNode = getEntry(key, returnLastIfNotFound);
		Entry <K,V> newEntry = new Entry<K,V>(key, newVal);

		if (existingNode == null) 
		{
			entry = addNewBucketKey(newEntry, hashIndex, entry);
			
			//delete after finding the rehashing error...
			/*
			if (get(key) == null)
			{
				System.out.println("[PUT ERROR] put error, cannot find element just added..." + key);
				get(key);
			}*/
		}
		else if (existingNode != null)
		{
			updateExistingBucketKey(existingNode, newEntry, hashIndex);
		}
	}

	
	/**
	 * 
	 * @param updateNode
	 * @param newEntry
	 * @param hashIndex
	 */
	private void updateExistingBucketKey(Entry<K, V> updateNode, Entry<K, V> newEntry, int hashIndex) {
		if (updateNode.key == newEntry.getKey()) 
		{
			//replace
			updateNode.value = newEntry.getValue();
			debugPrint(updateNode, hashIndex, "[PUT]","[REPLACE]");
		}
		else 
		{
			//Append
			updateNode.setNext(newEntry);
			debugPrint(newEntry, hashIndex, "[PUT]", "[COLLISION]");
		}
	}

	
	/**
	 * Adds a new key to the hashmap, or if you prefer fills a new bucket of the array
	 * @param newEntry new hashmap entry
	 * @param hashIndex hashcode
	 * @param entry	array where the buckets will be filled
	 * @return returns the updated or resized array
	 */
	private Entry<K,V>[] addNewBucketKey(Entry<K, V> newEntry, int hashIndex, Entry<K, V> [] entry) {
		if(!requiresResize()) {
			entry[hashIndex] = newEntry;
			debugPrint(newEntry, hashIndex, "[PUT]","[NEW]");
			size++;
		}else {
			entry = resize(entry);
			hashIndex = getHashCode(newEntry.key); //recalculating hash because it can became obsolete on a resize.
			entry[hashIndex] = newEntry;
			debugPrint(newEntry, hashIndex, "[PUT]","[NEW][CAUSED RESIZE]");
			size++;
		}
		
		return entry;
	}
	
	
	/**
	 * This method should be called one resizing is done on entry array
	 * @param newEntry
	 * @param hashIndex
	 * @param entry
	 */
	private void addBucketKeyOnResize(Entry<K, V> newEntry, int hashIndex, Entry<K, V> [] entry) {
			entry[hashIndex] = newEntry;
			debugPrint(newEntry, hashIndex, "[PUT]","[REHASHING][NEW]");
			size++;
		
	}
	
	
	/**
	 * Metodo usado para passar keys de um array para outro
	 * @param node
	 * @param array
	 */
	private void putEntry(Entry<K,V> node, Entry<K,V> [] array) {

		if (node == null)
			return;

		if (array == null)
		{
			throw new NullPointerException();
		}

		int hashIndex = getHashCode(node.key);
		boolean returnLastIfNotFound = true;
		Entry <K, V> existingNode = getEntry(node.key, array, returnLastIfNotFound);
		
		if (existingNode == null) 
		{
			addBucketKeyOnResize(node, hashIndex, array);
		}
		else if (existingNode != null)
		{
			updateExistingBucketKey(existingNode, node, hashIndex);
		}
	}

	public void remove(K key) {
		//Needs to be implemented
	}

	
	/**
	 * Resizes the Hashmap array by multiplying by two until all keys fit a below load factor for the calculated capacity
	 * @param entry
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Entry<K,V>[] resize(Entry<K,V> [] entry) {
		System.out.println(">>>> START resize");
		//Needs to be implemented
		capacity = calculateNewCapacity(capacity << 1);
		System.out.println("new capacity " + capacity);
		Entry<K, V>[] newArray = new Entry[capacity];
		Entry<K, V> current = null;
		Entry<K, V> tmp = null;
		size = 0;
		for (int i = 0; i < entry.length; i++) {
			current = entry[i];

			while (current != null) { //se usar .next o ultimo não é processado
				//rehashing
				tmp = current.next;
				current.next = null;
				putEntry(current, newArray);
				current = tmp;
			}
		}

		
		System.out.println(">>>> END resize");
		
		return newArray;

	}

	
	/**
	 * Calculates new capacity for the existing keys
	 * If the key count is above the LOAD_FACTOR than the capacity is 
	 * increase until the key count is below LOAD_FACTOR.
	 * @param capacity is the value against which the key count will be compared. 
	 * LOAD_FACTOR is 0.75 which means that the key count can not be higher than 75% of the array capacity.
	 * A value will not be returned until this constraint is achieved
	 * @return the returned value is the minimal capacity that fits the key size below load_factor
	 */
	private int calculateNewCapacity(int capacity) {
		
		int keys = calculateKeysForCapacity(capacity);
		while (requiresResize(keys, capacity))
		{
			System.out.println("[Capacity skipped] >> " + capacity + " | keys for capacity : " + keys);
			capacity = capacity << 1;
			keys = calculateKeysForCapacity(capacity);
		}		
		
		return capacity;
	}
	
	
	/**
	 * Calculates keys for given capacity regardless if the load factor is overcame
	 * 
	 * @param capacity
	 * @return
	 */
	private int calculateKeysForCapacity(int capacity) {

		Entry<K, V> current = null;
		HashSet<Integer> map = new HashSet<Integer>();
		
		for (int i = 0; i < entry.length; i++) {
			current = entry[i];

			while (current != null) { //se usar .next o ultimo não é processado
				//rehashing
				map.add(getHashCode(current.key, capacity));
				current = current.next;
			}
		}
		
		return map.size();
	}

	
	/**
	 * Checks if the the hashmap array needs to be resized.
	 * @return
	 */
	private boolean requiresResize() {
		return requiresResize(size, capacity);
	}
	
	
	/**
	 * Checks if the the hashmap array needs to be resized.
	 * @return
	 */
	private boolean requiresResize(int size, int capacity) {
		return ((float)(size + 1) / capacity) >= LOAD_FACTOR;
	}

	
	/**
	 * gets the value for the inputed key
	 * 
	 * @param key
	 * @return
	 */
	public V get(K key) {
		Entry<K,V> result = getEntry(key);

		debugPrint(result, getHashCode(key), "[GET]");

		return result != null? result.value : null;
	}

	
	/**
	 * Returns the value for a given key in the hashmap array
	 * @param key
	 * @return
	 */
	private Entry<K,V> getEntry(K key) {
		return getEntry(key, entry, false);
	}

	
	/**
	 * Returns the value for a given key in the hashmap array
	 * if not it can return null or the last element of the list, 
	 * depending if we pass the returnLastIfNotFound with false or true respectively
	 * @param key
	 * @param returnLastIfNotFound
	 * @return
	 */
	private Entry<K,V> getEntry(K key, boolean returnLastIfNotFound) {
		return getEntry(key, entry, returnLastIfNotFound);
	}

	
	/**
	 * Criado para evitar ter um método específico para devolver o último elemento
	 * @param key
	 * @param returnLastIfNotFound
	 * @return
	 */
	private Entry<K,V> getEntry(K key, Entry<K, V> [] array,  boolean returnLastIfNotFound) {

		int hashIndex = getHashCode(key);
		Entry<K,V> result = array[hashIndex];

		if (result == null) {
			return result;
		}

		//if has next and keys do not match keep searching
		while (result.getNext() != null && result.key != key)
		{
			result = result.getNext();
		}

		if(result.key != key && !returnLastIfNotFound)
		{
			//return null if no entry found & returnLastFlag is off
			return null;
		}
		else 
		{
			return result;
		}
	}

	
	/**
	 * Gera o hashcode adaptado à capacidade do array
	 * @param o
	 * @return
	 */
	private int getHashCode(Object o, int capacity) {
		return hashAlgo2(o, capacity);
	}
	
	/**
	 * More performant algo
	 * @param o
	 * @param capacity
	 * @return
	 */
	private int hashAlgo2(Object o, int capacity) {
		if (o == null) {
			return 0;
		}
		
		return Math.abs((o.hashCode()*7) % capacity);
	}
	
	
	/**
	 * Gera o hashcode adaptado à capacidade do array
	 * @param o
	 * @return
	 */
	private int getHashCode(Object o) {

		return getHashCode(o, capacity);
	}


	/**
	 * debug print pode ser desativado ao colocar a variavel DEBUG_MODE
	 * @param printEntry
	 * @param hashIndex
	 * @param prefix
	 */
	private void debugPrint(Entry<K,V> printEntry, int hashIndex, String prefix) {
		debugPrint(printEntry, hashIndex, prefix, null);
	}

	
	/**
	 * debug print pode ser desativado ao colocar a variavel DEBUG_MODE
	 * @param printEntry
	 * @param hashIndex
	 * @param prefix
	 * @param sufix
	 */
	private void debugPrint(Entry<K,V> printEntry, int hashIndex, String prefix, String sufix) {
		if (printEntry != null && DEBUG_MODE) {
			String message = "key:" + printEntry.key + " 	| value:" + printEntry.value + " | index: " + getHashCode(hashIndex);

			message += sufix != null ? " " + sufix : "";
			message = prefix != null ? prefix + "\t" + message : message;
			System.out.println(message);
		}
	}

	
	/**
	 * Gets the hashmapsize
	 * @return
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Gets Capacity
	 * @return
	 */
	public int getCapacity() {
		return capacity;
	}
}

class Entry<K, V>{
	K key;
	V value;
	Entry<K,V> next;

	public Entry(K key, V value) {
		super();
		this.key = key;
		this.value = value;
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public Entry<K, V> getNext() {
		return next;
	}

	public void setNext(Entry<K, V> next) {
		this.next = next;
	}	
}
