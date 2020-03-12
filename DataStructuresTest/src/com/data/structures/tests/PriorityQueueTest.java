package com.data.structures.tests;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.data.structures.ds.PriorityQueue;

class PriorityQueueTest {

	@Test
	void test_empty_queue_by_priority() {
		PriorityQueue p = new PriorityQueue();
		p.add(1);
		p.add(11);
		p.add(10);
		p.add(101);
		System.out.println(p.poll());
		System.out.println(p.poll());
		System.out.println(p.poll());
		System.out.println(p.poll());
		assertTrue(p.getSize() == 0);

	}
	@Test
	void test_pollEmptyPriorityQueue_thenSuccess() {
		PriorityQueue p = new PriorityQueue();
		p.add(1);
		p.add(11);
		p.add(10);
		p.add(101);
		p.poll();
		p.poll();
		p.poll();
		p.poll();

		Exception outOfBounds = assertThrows(ArrayIndexOutOfBoundsException.class, () ->
		{
			p.poll();
		});
		
		assertTrue(outOfBounds.getMessage().equals("out of bounds"));

	}

}
