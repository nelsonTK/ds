package com.data.structures.models;

import java.util.EmptyStackException;

public class Queue1 {

	private int [] array;
	private static int CAPACITY;
	private int front;
	private int rear;
	private int size;

	public Queue1() {
		CAPACITY = 5;	
		array = new int[CAPACITY];
		resetPointers();
	}

	public Queue1(int capacity) {
		super();
		CAPACITY = capacity;
		array = new int[CAPACITY];
		resetPointers();
	}

	public int dequeue() throws EmptyStackException {
		int result = 0;

		if (front == -1) 
		{
			//throw new EmptyStackException();
			System.out.println("is Empty");
		}
		else if (rear == front) 
		{
			result = array[front];
			front = -1;
			rear = -1;
			size--;

		}
		else if (front + 1 < CAPACITY) 
		{
			result = array[front];
			front++;
			size--;

		}
		else if (front + 1 == CAPACITY)
		{
			result = array[front];
			front = 0;
			size--;

		}
		
		return result;
	}

	public void enqueue(int element) {

		if (isEmpty()) {
			front = 0;
			rear = 0;
			array[size] = element;
			size++;

		}
		else if (rear + 1 < CAPACITY && rear + 1 != front) 
		{
			rear++;		
			array[rear] = element;
			size++;

		} 
		else if (rear + 1 == CAPACITY && front != (CAPACITY - rear - 1)) 
		{
			rear = CAPACITY - rear - 1; //0
			array[rear] = element;
			size++;


		}else
		{
			//throw new StackOverflowError();
			System.out.println("stackoverflow: " + element);
		}

	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void print() {

		if(size != 0) {
			int i = front;

			do {

				if (i == CAPACITY) 
				{
					i = 0;
				}
				System.out.println(array[i]);

				i++;
			}
			while (i != rear + 1);

		} else {
			System.out.println("stack empty");
		}
	}

	private void resetPointers() {
		front = -1;
		rear = -1;
	}

	public int[] getArray() {
		return array;
	}
	
	public void setArray(int[] array) {
		this.array = array;
	}
	
	public static int getCAPACITY() {
		return CAPACITY;
	}
	
	public static void setCAPACITY(int cAPACITY) {
		CAPACITY = cAPACITY;
	}
	
	public int getFront() {
		return front;
	}
	
	public void setFront(int front) {
		this.front = front;
	}
	
	public int getRear() {
		return rear;
	}
	
	
	public void setRear(int rear) {
		this.rear = rear;
	}


}
