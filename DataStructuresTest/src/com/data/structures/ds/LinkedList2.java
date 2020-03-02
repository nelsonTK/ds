package com.data.structures.ds;

import java.util.Stack;

/**
 * Segunda implementação da LinkedList, com Head e Tail
 * 
 * 1) acho dificil acertar uma solução à primeira tendo os edge cases todos em mente
 * 2) acho dificil criar uma solução otimizada à primeira
 * 3) após ver uma soluçao acho extremamente dificil pensar noutras soluções, fico sempre preso na primeira solução que me vêm à cabeça.
 * Um pouco como aconteceu na produção de musica e outros.
 * 4) compreendi que as perguntas que se fazem consideram a lista como tendo apenas o head e não o tail, parece que é este o standard
 * 5) muita confusão também a usar a recursividade como deve de ser...
 * @author Nelson Costa
 */
public class LinkedList2 {

	private Node head;
	private Node tail;
	private int size;

	public LinkedList2  (int value) {
		addOnEmpty(value);
	}

	public void add(int value) {
		Node newNode = new Node(value);
		Node tmp = tail;
		tail = newNode;
		tmp.next = tail;
		size++;
	}

	public void add(int index, int value) {

		Node curr = head;

		Node prev = null;
		if(index < size && index >=0) {

			for (int i = 0; i < index; i++) {
				prev = curr;
				curr = curr.next;
			}

			Node newValue = new Node(value);

			if (prev != null) {
				prev.next = newValue;
				newValue.next = curr;
			}
			else
			{
				newValue.next = curr;
				head = newValue;
			}
			size++;
		}
	}

	public void addFirst(int value) {
		if(head != null) {
			Node newNode = new Node(value);
			Node tmp = head;
			head = newNode;
			head.next = tmp;
			size ++;
		}else {
			addOnEmpty(value);

		}
	}

	public void removeLast() {
		Node curr = head;

		if (curr != null) {

			if(curr.next != null) {

				while (curr.next != tail)
				{
					curr = curr.next;
				}

				curr.next = null;
				tail = curr;
				size --;

			}
			else
			{
				head = null;
				tail = null;
				size --;
			}
		}
	}

	public void removeFirst() {
		if (head != null)
		{
			if (head.next != null)
			{
				head = head.next;
				size--;
			}else
			{
				//means it is only one element
				head = null;
				tail = null;
				size--;
			}
		}
	}

	public void reverse() {
		reverse(null, head, head.next);
	}

	private void reverse(Node prev, Node curr, Node next) {
//		System.out.println("p: " + ((prev != null) ? prev.value : "null"));
//		System.out.println("c: " + ((curr != null) ? curr.value : "null"));
//		System.out.println("n: " + ((next != null) ? next.value : "null"));
//		System.out.println();

		if (size <= 1)
		{
			//single element
			return;
		}

		if (curr == null && prev != null)
		{
			//edge case
			head = prev;
			return;
		}

		if (prev == null && curr == null && next == null)
		{
			//edge case
			return;
		}

		Node tmp = null;

		if (prev != null && curr != null && next == null) {
			//edge case
			curr.next = prev;
			head = curr;
		}else {
			tmp = next.next;
			curr.next = prev;
			next.next = curr;
		}


		if (prev == null)
		{
			tail = curr;
		}

		if (tmp != null) {
			reverse(next, tmp, tmp.next);
		}
		else
			reverse(next, null, null);

	}

	public void print() {
		String result = "";
		Node curr = null;
		curr = head;
		if (curr != null) {

			result = "[" + curr.value + "]";
			curr = curr.next;

			while(curr != null) {
				result += " -> [" + curr.value + "]";
				curr = curr.next;
			}

			System.out.println(result);
			System.out.println("size:  " + size);
			System.out.println("first: " + head.value);
			System.out.println("last:  " + tail.value);
		}
		else {
			System.out.println("[Empty List]");			
			System.out.println("size:  " + size);

		}


	}

	private void addOnEmpty(int value) {
		head = new Node(value);
		tail = head;
		size ++;
	}

	public Node getHead() {
		return head;
	}

	public void setHead(Node head) {
		this.head = head;
	}

	public Node getTail() {
		return tail;
	}


	public void setTail(Node tail) {
		this.tail = tail;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}




	private class Node{
		private int value;
		private Node next;		

		public Node(int value) {
			super();
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
