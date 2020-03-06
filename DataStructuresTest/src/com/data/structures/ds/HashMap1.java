package com.data.structures.ds;

import java.util.HashSet;

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
	public void put2(K key, V newVal) {

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
			//Posso substituir por add new bucket
			//new
			entry = addNewBucketKey(newEntry, hashIndex, entry);
//			addNewBucketKey(key, newVal, hashIndex, entry);

//			if(!requiresResize()) {
//				newEntry = new Entry<K,V>(key, newVal);
//				entry[hashIndex] = newEntry;
//				debugPrint(newEntry, hashIndex, "[PUT]","[NEW]");
//				size++;
//			}else {
//				resize();
//				newEntry = new Entry<K,V>(key, newVal);
//				entry[hashIndex] = newEntry;
//				debugPrint(newEntry, hashIndex, "[PUT]","[NEW][CAUSED RESIZE]");
//				size++;
//			}
		}
		else if (existingNode != null)
		{
			//Posso substituir por update existingBucket
			//replace
			updateExistingBucketKey(existingNode, newEntry, hashIndex);
//			if (existingNode.key == key) 
//			{
//				existingNode.value = newVal;
//				debugPrint(existingNode, hashIndex, "[PUT]","[REPLACE]");
//			}
//			else {
//				//Append
//				newEntry = new Entry<K,V>(key, newVal);
//				existingNode.setNext(newEntry);
//				debugPrint(newEntry, hashIndex, "[PUT]", "[COLLISION]");
//
//			}
		}
	}

	
	private void updateExistingBucketKey(Entry<K, V> updateNode, Entry<K, V> newEntry, int hashIndex) {
		if (updateNode.key == newEntry.getKey()) 
		{
			updateNode.value = newEntry.getValue();
			debugPrint(updateNode, hashIndex, "[PUT]","[REPLACE]");
		}
		else {
			//Append
			//newEntry = new Entry<K,V>(key, newVal);
			updateNode.setNext(newEntry);
			debugPrint(newEntry, hashIndex, "[PUT]", "[COLLISION]");
		}
	}

	private Entry<K,V>[] addNewBucketKey(Entry<K, V> newEntry, int hashIndex, Entry<K, V> [] entry) {
		if(!requiresResize()) {
			entry[hashIndex] = newEntry;
			debugPrint(newEntry, hashIndex, "[PUT]","[NEW]");
			size++;
		}else {
			entry = resize(entry);
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
			debugPrint(newEntry, hashIndex, "[PUT]","[NEW]");
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
			//new
			
			//addNewBucketKey(node, hashIndex, array);
			addBucketKeyOnResize(node, hashIndex, array);
//			if(!requiresResize()) {
//				//newEntry = new Entry<K,V>(node.key, newVal);
//				array[hashIndex] = node;
//				debugPrint(node, hashIndex, "[PUT]","[REHASHING][NEW]");
//				size++;
//			}else {
//				resize();
//				//newEntry = new Entry<K,V>(key, newVal);
//				array[hashIndex] = node;
//				debugPrint(node, hashIndex, "[PUT]","[REHASHING][NEW][NOT EXPECTED][RESIZED]");
//				size++;
//			}
			
			
		}
		else if (existingNode != null)
		{
			//Posso substituir por //Update existing bucket...
			//replace
			updateExistingBucketKey(existingNode, node, hashIndex);

//			if (existingNode.key == node.key) 
//			{
//				existingNode.value = node.value;
//				debugPrint(existingNode, hashIndex, "[PUT]","[REHASHING][REPLACE]");
//			}
//			else {
//				//Append
//				//newEntry = new Entry<K,V>(key, newVal);
//				existingNode.setNext(node);
//				debugPrint(node, hashIndex, "[PUT]", "[REHASHING][COLLISION]");
//
//			}
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
	 * if not it can return null or the last element of the list, depending if we pass the returnLastIfNotFound with false or true respectively
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

		if (o == null) {
			return 0;
		}

		//Há quem defenda que seria suficiente fazer o módulo do hashcode por capacity/ hashcode % capacity. experimentar
		int code = Math.abs(o.hashCode());
		while(code >= capacity) {
			code /= 7;
		}

		return code;
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


//	/**
//	 * Metodo antigo não tratava das colisões como deve de ser
//	 * @param key
//	 * @return
//	 */
//	private Entry<K,V> getEntry(K key){
//		int index = getHashCode(key);
//
//		Entry<K,V> item = getLastBucketElement(entry[index]);
//
//		return (item != null) ? item : null;
//	}

	
//	/**
//	 * Não se tem revelado necessário
//	 * @param item
//	 * @return
//	 */
//	private Entry<K,V> getLastBucketElement(Entry<K,V> item){
//		if (item == null)
//			return item;
//
//		while (item.getNext() != null)
//		{
//			item = item.getNext();
//		}
//
//		return item;
//	}

	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
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
