package com.data.structures.ds;

import java.lang.reflect.Array;

import com.data.structures.utils.PrintHelper;

public class ArrayListGeneric1<T> {
	private int capacity;
	private int size;
	private T [] array;
	boolean initialized;

	public ArrayListGeneric1() {
		capacity = 1;
		size = 0;
	}

	/**
	 * Add Element to a List
	 * @param element
	 */
	@SuppressWarnings("unchecked")
	public void add(T element) {

		if(!initialized)
		{
			array = (T[]) Array.newInstance(element.getClass(), capacity);
			initialized = true;
		}

		if (size < capacity)
		{
			array[size] = element;
		}
		else
		{
			//increase capacity
			increaseCapacity(element);
			array[size] = element;

			System.out.println("capacity increase");
		}
		size++;
	}

	@SuppressWarnings("unchecked")
	public void increaseCapacity(T element) {
		capacity = array.length * 2;			
		//			T [] newArray = new int[capacity];
		T [] newArray = (T[]) Array.newInstance(element.getClass(), capacity);
		for (int i = 0, j = array.length - 1; i <= j; i++, j--) {
			newArray[i] = array[i];
			newArray[j] = array[j];
		}
		array = newArray;		
	}

	/**
	 * get element at position of the index
	 * @param index
	 * @return
	 */
	public T get(int index) {

		if (index >= size)
		{
			throw new ArrayIndexOutOfBoundsException();
		}
		else
		{
			return array[index];
		}
	}

	/**
	 * Remove last element
	 */
	public void removeLast() {
		if (size > 0) {
			array[size] = null;
			size--;
		}
		else
		{
			System.out.println("Nothing to remove.");
		}
	}

	/**
	 * Remove element at position
	 * @param index
	 */
	public void removeAt(int index) {
		if (size > 0 && index >= 0 && index < size) {

			for (int i = index + 1; i < size; i ++ ) {
				array[i - 1] = array[i];
			}
			array[size - 1] = null;
			size--;
		}
	}

	public void print() {
		int from = 0, to = size -1;

		String result = "";

		if(array == null || array.length == 0)
		{
			result = "[Empty]";
		}
		else if(from < 0 || to < 0)
		{
			result = "[Empty] (computed)";
		}
		else if (from > to) 
		{
			result = "[ILLEGAL BOUNDARIES] from " + from + " : to " + to;
		}
		else 
		{
			for (int i = from; i<= to; i++)
			{
				result += "["+array[i]+"]";
			}
		}
		System.out.println(result);
	}	

	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public T[] getArray() {
		return array;
	}
	public void setArray(T[] array) {
		this.array = array;
	}
}
