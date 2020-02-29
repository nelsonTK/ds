package com.data.structures.models;

import com.data.structures.utils.PrintHelper;

public class ArrayList1 {
	private int capacity;
	private int size;
	private int [] array;

	public ArrayList1() {
		super();
		capacity = 1;
		array = new int[capacity];
		size = 0;
	}

	/**
	 * Add Element to a List
	 * @param element
	 */
	public void add(int element) {

		if (size < capacity)
		{
			array[size] = element;
		}
		else
		{
			increaseCapacity();
			array[size] = element;
		}
		size++;
	}

	/**
	 * Increase Capacity of the array
	 */
	public void increaseCapacity() {

		//increase capacity
		capacity = array.length * 2;			
		int [] newArray = new int[capacity];			
		for (int i = 0, j = array.length - 1; i <= j; i++, j--) {
			newArray[i] = array[i];
			newArray[j] = array[j];
		}
		array = newArray;
		System.out.println("capacity increase");
	}

	/**
	 * get element at position of the index
	 * @param index
	 * @return
	 */
	public int get(int index) {

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
			array[size] = 0;
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
			array[size - 1] = 0;
			size--;
		}
	}

	public void print() {
		PrintHelper.printArray(array, 0, size-1);
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
	public int[] getArray() {
		return array;
	}
	public void setArray(int[] array) {
		this.array = array;
	}
}
