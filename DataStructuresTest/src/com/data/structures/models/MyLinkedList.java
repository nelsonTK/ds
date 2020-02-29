package com.data.structures.models;

/**
 * Implementação errada, eu coloquei o como last o que era na verdade o first, e vice versa...
 * @author Nelson Costa
 *
 */
public class MyLinkedList {
	private Node first;
	private Node last;
	private int size;

	public MyLinkedList(int value) {
		addOnEmpty(value);
	}

	public void addNode(int value) {

		if(first != null) {
			Node newNode = new Node(value);
			Node previousLast = last;
			last = newNode;
			last.setNext(previousLast);
			size++;
		}else {
			addOnEmpty(value);
		}
	}

	private void addOnEmpty(int value) {
		first = new Node(value);
		last = first;
		size++;

	}

	public void removeLast() {
		if(last != null)
		{
			last = last.next;
			size--;
		}
	}
	

	public void removeFirst() {

		if(last.next != first) {
			Node currentNode;
			currentNode = last;

			while (currentNode.next != null)
			{
				if(currentNode.next != first) {
					currentNode = currentNode.next;
				}else {
					currentNode.next = null;
					first = currentNode;
					size--;
				}
			}

		}else
		{
			first = null;
			last = null;
			size--;
		}
	}

	public void addFirst(int value) {
		Node newFirst = new Node(value);
		Node oldFirst = first;
		first = newFirst;
		oldFirst.next = first;
	}

	public Node getFirst() {
		return first;
	}

	public void setFirst(Node first) {
		this.first = first;
	}

	public Node getLast() {
		return last;
	}

	public void setLast(Node last) {
		this.last = last;
	}

	public int getSize() {
		return size;
	}

	@Override
	public String toString() {
		String result = "";

		if(last != null) {

			Node currentNode = last;
			result = "[" + currentNode.value + "]";
			currentNode = currentNode.next;

			while (currentNode != null)
			{
				result += " -> [" + currentNode.value + "]";
				currentNode = currentNode.next;

			}

			if (first != null) {
				result += System.lineSeparator() + "First : [" + this.first.getValue() + "]";
				result += System.lineSeparator() + "Last  : [" + this.last.getValue()  + "]";
				result += System.lineSeparator() + "Size  : [" + this.getSize() + "]";
			}	
		}else
		{
			result += System.lineSeparator() + "Empty Array";
			result += System.lineSeparator() + "Size  : [" + this.getSize() + "]";
		}
		return result;	
	}


	class Node {
		private int value;
		private Node next;

		public Node(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

	}
}
