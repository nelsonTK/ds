package com.data.structures.main;

import com.data.structures.models.Queue1;
import com.data.structures.utils.PrintHelper;

public class QueueTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Queue1 q = new Queue1();
		System.out.println("-------queue-------");
		q.enqueue(1);
		q.enqueue(2);
		q.enqueue(3);
		q.enqueue(3);
		q.enqueue(3);
		q.print();
		PrintHelper.printArray(q.getArray());
		System.out.println("-------dequeue-------");
		System.out.println("dequeued: " + q.dequeue());
		q.enqueue(3);
		q.enqueue(4);
//		System.out.println("dequeued: " + q.dequeue());
		q.print();
		PrintHelper.printArray(q.getArray());

	}

}
