package com.data.structures.ds;

/**
 * Binary Heap Implementation
 * @author Nelson Costa
 *
 */
public class MaxHeap {

	protected Integer[] array;
	private int capacity;
	protected int size;

	public MaxHeap() {
		capacity = 16;
		array = new Integer[capacity];
		size = 0;
	}

	public void add(int val) {

		if(size < capacity)
		{
			array[size] = val;
			bubbleUp(size, val);
			size++;
		}else {
			array = resize();
			array[size] = val;
			bubbleUp(size, val);
			size++;
		}
	}

	public void delete(int index) {

		if (size <= 0 || index >= size)
			return;

		if (size == 1) {
			array[index] = null;
			size--;
		}
		else
		{
			int last = size - 1;
			swap(array, index, last);
			array[last] = null;
			size--;
			bubbleDown(index);
		}		
	}

	public void updateValAt(int index, int newVal) 
	{
		int oldVal = array[index];
		if (oldVal < newVal) {
			array[index] = newVal;
			bubbleUp(index, newVal);
		}
		else
		{
			array[index] = newVal;
			bubbleDown(index);
		}
	}

	public boolean searchData(int val) {

		for (int i = 0; i < size; i++) {
			Integer integer = array[i];
			if (integer == val)
				return true;
		}
		return false;
	}

	public int countRepeatedValues(int val) {

		int count = 0;
		for (int i = 0, j = size - 1; i <= j; i++, j--) {

			if (i != j)
			{
				if (array[i] == val)
					count++;

				if (array[j] == val)
					count++;
			}
			else
			{
				if(array[i] == val)
				{
					count++;
				}
			}
		}

		return count;
	}
	
	/**
	 * returns element at index position of the MaxHeap internal array
	 * @param index
	 * @return
	 */
	public int getElementAt(int index) {
		if (index < 0 || index >= size || size == 0)
		{
			throw new ArrayIndexOutOfBoundsException("out of bounds");
		}
		else
		{
			return array[index];
		}
	}

	/**
	 * returns the index for the input val
	 * if doesn't exists return -1
	 * @param val
	 * @return
	 */
	public int getIndexForData(int val) {

		for (int i = 0; i < size; i++) {
			int integer = array[i];
			if (integer == val)
				return i;
		}
		return -1;
	}

	public void bubbleUp(int index, int val) {

		int parentIndex = getParentIndex(index);

		if(index == 0 || array[parentIndex] >= val)
			return;

		if( array[parentIndex] < val) {
			swap(array, parentIndex, index);
		}

		bubbleUp(parentIndex, val);
	}
	public void bubbleDown(int index) {

		if (index == size - 1)
			return;

		int lIndex = getLeftChildIndex(index);
		int rIndex = getRightChildIndex(index);
		int rVal;
		int lVal;
		int parentVal = array[index];
		if (lIndex != -1) {
			lVal = array[lIndex];
			if (lVal > parentVal) {
				swap(array,index, lIndex);
				bubbleDown(lIndex);
			}
		}
		else if (rIndex != -1)
		{
			rVal = array[rIndex];
			if(rVal > parentVal) {
				swap(array, index, rIndex);
				bubbleDown(rIndex);
			}
		}
	}


	public Integer[] resize() {
		capacity = capacity << 1;
		Integer [] tmp = new Integer[capacity];
		System.arraycopy(array, 0, tmp, 0, array.length);
		return tmp;
	}
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	private int getLeftChildIndex(int index) {
		int child = index * 2 + 1;
		return child < size ? child : -1 ;
	}

	private int getRightChildIndex(int index) {
		int child = index * 2 + 2;
		return child < size ? child : -1 ;
	}

	private void swap(Integer[] array, int a, int b) {		
		int temp = 0;
		temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}

	private int getParentIndex(int childIndex) {
		return (childIndex - 1)/2;
	}
}
