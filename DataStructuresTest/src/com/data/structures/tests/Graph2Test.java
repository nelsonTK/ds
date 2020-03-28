package com.data.structures.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.data.structures.ds.Graph2;

class Graph2Test {

	@Test
	void test() {		
		System.out.println("++++ test ++++");

		Graph2<Integer> g = new Graph2<Integer>();			
		g.add(1);
		g.add(2);
		g.add(3);
		g.addUnidiretionalEdge(1, 2, 1);
		g.addUnidiretionalEdge(1, 3, 1);
//		g.addUnidiretionalEdge(2, 1, 1);
		g.addUnidiretionalEdge(2, 3, 1);
//		g.addUnidiretionalEdge(3, 1, 1);
//		g.addUnidiretionalEdge(3, 2, 1);
//		g.addBidirectionalEdge(1, 3, 1);
//		g.addBidirectionalEdge(1, 2, 1);
		g.print();
	}
	
	@Test
	void test_BFS_Print() {
		System.out.println("++++ test_BFS_Print ++++");

		Graph2<Integer> g = new Graph2<Integer>();			
		g.add(1);		
		g.add(2);		
		g.add(3);		
		g.add(4);		
		g.add(5);
		g.add(6);
		g.add(7);
		g.addDirectedEdge(1, 2);
		g.addDirectedEdge(1, 3);
		g.addDirectedEdge(2, 6);
		g.addDirectedEdge(2, 7);
		g.addDirectedEdge(6, 7);
		g.addDirectedEdge(7, 3);
		g.addDirectedEdge(7, 5);
		g.addDirectedEdge(3, 4);
		g.addDirectedEdge(4, 5);
		
		g.BFSPrint(1);
	}
	
	@Test
	void test_DFS_Print() {
		System.out.println("++++ test_DFS_Print ++++");
		Graph2<Integer> g = new Graph2<Integer>();			
		g.add(1);		
		g.add(2);		
		g.add(3);		
		g.add(4);	
		g.add(5);	
		g.add(6);	
		g.add(7);	
		g.add(8);
		g.add(9);
		g.add(10);
		g.add(11);
		g.addDirectedEdge(1, 2);
		g.addDirectedEdge(1, 3);
		g.addDirectedEdge(2, 4);
		g.addDirectedEdge(3, 5);
		g.addDirectedEdge(5, 6);
		g.addDirectedEdge(6, 7);
		g.addDirectedEdge(7, 8);
		g.addDirectedEdge(7, 9);
		g.addDirectedEdge(7, 10);
		g.addDirectedEdge(4, 11);

		g.DFSPrintIterative(1);
	}

}