package com.data.structures.main;

import com.data.structures.ds.ArrayList1;

public class ArrayListMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList1 a = new ArrayList1();
		a.removeLast();
		a.print();
		System.out.println("print size: " + a.getSize());
		a.add(1);
		a.print();
		a.add(2);
		a.add(3);
		a.add(4);
		a.add(5);
		a.add(6);
		a.add(7);
		a.add(8);
		a.print();
		a.add(9);
		a.print();
		System.out.println(a.get(8));
		System.out.println("print size: " + a.getSize());
		a.print();
		System.out.println("-----------REMOVE LAST-----------");
		a.removeLast();
		a.print();		
		a.add(10);
		a.print();
		System.out.println("print size: " + a.getSize());
		System.out.println("-----------REMOVE AT-----------");	
		a.removeAt(5);
		a.print();
		a.removeAt(4);
		a.print();
		a.removeAt(3);
		a.print();
		a.removeAt(2);
		a.print();
		a.removeAt(1);
		a.print();
		a.removeAt(0);
		a.print();
		a.removeAt(0);
		a.removeAt(0);
		a.removeAt(0);
		a.print();
	}

}
