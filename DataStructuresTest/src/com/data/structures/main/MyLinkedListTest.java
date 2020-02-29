package com.data.structures.main;

import com.data.structures.models.LinkedList2;
import com.data.structures.models.MyLinkedList;

public class MyLinkedListTest {

	public static void main(String args[]) {
		LinkedList2Test();
	}
	
	public static void LinkedList2Test() {
		

		LinkedList2 l2 = new LinkedList2(1);
		l2.print();
		

		System.out.println("---------Reverse List----------");
		l2.reverse();
		l2.print();
	}
	
	public static void MyLinkedListTest03() {

		LinkedList2 l2 = new LinkedList2(1);
		l2.add(2);
		l2.print();
		

		System.out.println("---------Reverse List----------");
		l2.reverse();
		l2.print();
	}
	
	public static void MyLinkedListTest02() {

		LinkedList2 l = new LinkedList2(1);
		l.add(2);
		l.add(3);
		l.add(4);
		l.add(5);
		l.print();
		
		System.out.println("---------Remove First----------");

		l.removeFirst();
		l.print();
		System.out.println("---------Remove Last----------");
		
		l.removeLast();
		l.print();
		
		System.out.println("---------Add at index----------");
		l.add(2, 5);
		l.print();

		l.add(2, 6);
		l.print();

		l.add(0, 7);
		l.print();

		
		System.out.println("---------Reverse List----------");
		l.reverse();
		l.print();
		
		System.out.println("---------Remove Last----------");
		l.removeLast();
		l.removeLast();
		l.removeLast();
		l.print();
		

		System.out.println("---------Reverse List----------");
		l.reverse();
		l.print();

	}
	
	public static void MyLinkedListTest01() {

		MyLinkedList list = new MyLinkedList(1);
		list.addNode(2);
		list.addNode(3);
		list.addNode(4);
		list.addNode(5);
		System.out.println(list.toString());

		System.out.println("-------------------");
		list.removeFirst();
		System.out.println(list.toString());

		System.out.println("-------------------");
		list.removeLast();
		System.out.println(list.toString());

		System.out.println("-------------------");
		list.removeLast();
		list.removeLast();
		list.removeLast();
		list.removeLast();	
		list.removeLast();		
		System.out.println(list.toString());
	}
}
