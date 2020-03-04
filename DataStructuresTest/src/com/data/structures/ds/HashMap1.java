package com.data.structures.ds;

public class HashMap1<K, V> {

	Entry<K,V> [] entry;
	int capacity;
	static float LOAD_FACTOR = 0.75f;
	static boolean DEBUG_MODE = true;

	public HashMap1() {
		super();
		capacity = 10;
	}

	@SuppressWarnings("unchecked")
	public void put2(K key, V newVal) {

		if (entry == null)
		{
			entry = new Entry[capacity];
		}

		int hashIndex = getHashCode(key);
		boolean returnLastIfNotFound = true;
		Entry <K, V> result = getEntry2(key, returnLastIfNotFound);
		Entry <K,V> newEntry = null;
		if (result == null) 
		{
			//new
			newEntry = new Entry<K,V>(key, newVal);
			entry[hashIndex] = newEntry;
			debugPrint(newEntry, hashIndex, "[PUT]","[NEW]");

		}
		else if (result != null)
		{
			//replace
			if (result.key == key) 
			{
				result.value = newVal;
				debugPrint(result, hashIndex, "[PUT]","[REPLACE]");
			}
			else {
				//Append
				newEntry = new Entry<K,V>(key, newVal);
				result.setNext(newEntry);
				debugPrint(newEntry, hashIndex, "[PUT]", "[COLLISION]");

			}
		}
	}

	public void remove(K key) {
		//Needs to be implemented
	}
	
	public void resize() {
		//Needs to be implemented
	}
	
	public V get2(K key) {
		Entry<K,V> result = getEntry2(key);
		
		debugPrint(result, getHashCode(key), "[GET]");
		
		return result != null? result.value : null;
	}

	private Entry<K,V> getEntry2(K key) {
		return getEntry2(key, false);
	}

	/**
	 * Criado para evitar ter um método específico para devolver o último elemento
	 * @param key
	 * @param returnLastIfNotFound
	 * @return
	 */
	private Entry<K,V> getEntry2(K key, boolean returnLastIfNotFound) {

		int hashIndex = getHashCode(key);
		Entry<K,V> result = entry[hashIndex];

		if (result == null) {
			return result;
		}

		//if has next and keys do not match keep searching
		while (result.getNext() != null && result.key != key)
		{
			result = entry[hashIndex].getNext();
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
	private int getHashCode(Object o) {

		if (o == null) {
			return 0;
		}

		int code = Math.abs(o.hashCode());
		while(code > capacity) {
			code /= 7;
		}

		return code;
	}

	
	private void debugPrint(Entry<K,V> printEntry, int hashIndex, String prefix) {
		 debugPrint(printEntry, hashIndex, prefix, null);
	}
	
	private void debugPrint(Entry<K,V> printEntry, int hashIndex, String prefix, String sufix) {
		if (printEntry != null && DEBUG_MODE) {
			String message = "key:" + printEntry.key + " 	| value:" + printEntry.value + " | index: " + getHashCode(hashIndex);
			
			message += sufix != null ? " " + sufix : "";
			message = prefix != null ? prefix + "\t" + message : message;
			System.out.println(message);
		}
	}
	
	
	
	
	
	/**
	 * Metodo antigo não tinha em conta as colisões, estava mal implementado.
	 * @param key
	 * @param newValue
	 */
	@SuppressWarnings("unchecked")
	public void put(K key, V newValue) {

		if(entry == null)
		{
			entry = new Entry[capacity];
		}

		int index = getHashCode(key);

		Entry<K,V> oldValue = getEntry(key);

		if (oldValue == null)
		{
			entry[index] = new Entry<K, V>(key, newValue);
			System.out.println("key:" + key + " 	| value:" + newValue + " | index: " + index);
		}
		else 
		{	
			Entry<K , V> newEntry = new Entry<K, V>(key, newValue);
			oldValue.next = newEntry;
			System.out.println("key:" + newEntry.key + " 	| value:" + newEntry.value + " | index: " + index);
		}
	}


	/**
	 * Metodo antigo não tratava das colisões como deve de ser
	 * @param key
	 * @return
	 */
	public V get(K key) {
		return getEntry(key).getValue();
	}

	/**
	 * Metodo antigo não tratava das colisões como deve de ser
	 * @param key
	 * @return
	 */
	private Entry<K,V> getEntry(K key){
		int index = getHashCode(key);

		Entry<K,V> item = getLastBucketElement(entry[index]);

		return (item != null) ? item : null;
	}

	/**
	 * Não se tem revelado necessário
	 * @param item
	 * @return
	 */
	private Entry<K,V> getLastBucketElement(Entry<K,V> item){
		if (item == null)
			return item;

		while (item.getNext() != null)
		{
			item = item.getNext();
		}

		return item;
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
