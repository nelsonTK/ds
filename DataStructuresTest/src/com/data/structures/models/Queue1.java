package com.data.structures.models;

import java.util.EmptyStackException;

public class Queue1 {

	private int [] array;
	private static int CAPACITY;
	private int front;
	private int rear;
	private int size;
	
	public Queue1() {
		CAPACITY = 2;		
	}
	
	public Queue1(int capacity) {
		super();
		CAPACITY = capacity;
	}
	
	public int dequeue() throws EmptyStackException {
		int result = 0;
		
		if (front == -1) 
		{
			throw new EmptyStackException();			
		}
		else if (rear == front) 
		{
			result = array[front];
			front = -1;
			rear = -1;
		}
		else if (front + 1 < CAPACITY) 
		{
			result = array[front];
			front++;
		}
		else if (front + 1 == CAPACITY)
		{
			result = array[front];
			front = 0;
		}
		size--;
		
		return result;
	}
	
	public void enqueue(int element) {
			
		if (size == 0) {
			front = 1;
			rear = 1;
		}
		else if (rear + 1 < CAPACITY && rear + 1 != front) 
		{
			rear++;			
		} 
		else if (rear + 1 == CAPACITY && front != (CAPACITY - front + 1)) 
		{
			rear = 0;
		}else
		{
			throw new StackOverflowError();
		}
		
		size++;
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
