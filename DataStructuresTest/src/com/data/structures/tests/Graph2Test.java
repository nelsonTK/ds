package com.data.structures.tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.data.structures.ds.Graph2;

@TestMethodOrder(Alphanumeric.class)
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

	@Test
	void test_Tarjans_SCC_test01() {

		System.out.println("++++ test_Tarjans_SCC_test01 ++++");

		Graph2<Integer> g = new Graph2<Integer>();
		
		g.add(0);
		g.add(1);
		g.add(2);
		g.add(3);
		g.add(4);
		g.add(5);

		g.addDirectedEdge(0, 1);
		g.addDirectedEdge(1, 0);
		g.addDirectedEdge(1, 2);
		g.addDirectedEdge(2, 3);
		g.addDirectedEdge(3, 2);
		g.addDirectedEdge(3, 4);
		g.addDirectedEdge(3, 5);
		g.addDirectedEdge(5, 3);
		g.addDirectedEdge(5, 2);
		
		g.FindStronglyConnectedComponents();
	}

	@Test
	void test_Tarjans_SCC_test02() {

		System.out.println("++++ test_Tarjans_SCC_test02 ++++");

		Graph2<Integer> g = new Graph2<Integer>();
		
		g.add(0);
		g.add(1);
		g.add(2);
		g.add(3);
		g.add(4);
		g.add(5);
		g.add(6);
		g.add(7);

		g.addDirectedEdge(0, 1);
		g.addDirectedEdge(1, 0);
		g.addDirectedEdge(0, 2);
		g.addDirectedEdge(2, 4);
		g.addDirectedEdge(2, 5);
		g.addDirectedEdge(1, 3);
		g.addDirectedEdge(3, 2);
		g.addDirectedEdge(4, 3);
		g.addDirectedEdge(4, 5);
		g.addDirectedEdge(4, 6);
		g.addDirectedEdge(5, 7);
		g.addDirectedEdge(6, 5);
		g.addDirectedEdge(7, 6);

		
		g.FindStronglyConnectedComponents();
	}
	
	
	@Test
	void test_topological_sort() {
		
		System.out.println("++++ test_topological_sort ++++");

		Graph2<Integer> g = new Graph2<Integer>();
		
		g.add(0);
		g.add(1);
		g.add(2);
		g.add(3);
		g.add(5);
		g.add(6);

		g.addDirectedEdge(0, 1);
		g.addDirectedEdge(6, 1);
		g.addDirectedEdge(1, 2);
		g.addDirectedEdge(1, 3);
		g.addDirectedEdge(2, 5);
		g.addDirectedEdge(3, 5);
		
		g.topologicalSort();
		
	}

	@Test
	void test_is_bipartite_false_001() {

		System.out.println("++++ test_is_bipartite_001 ++++");
		
		Graph2<Integer> g = new Graph2<Integer>();
		g.add(1);
		g.add(2);
		g.add(3);
		g.add(4);

		g.addDirectedEdge(1, 2);
		g.addDirectedEdge(2, 3);
		g.addDirectedEdge(3, 4);
		g.addDirectedEdge(4, 1);
		g.addDirectedEdge(2, 4);
		
		System.out.println(g.isBipartite());
		assertFalse(g.isBipartite());
	}

	@Test
	void test_is_bipartite_true_002() {

		System.out.println("++++ test_is_bipartite_002 ++++");
		
		Graph2<Integer> g = new Graph2<Integer>();
		g.add(1);
		g.add(2);
		g.add(3);
		g.add(4);

		g.addDirectedEdge(1, 2);
		g.addDirectedEdge(2, 3);
		g.addDirectedEdge(3, 4);
		g.addDirectedEdge(4, 1);

		System.out.println(g.isBipartite());
		assertTrue(g.isBipartite());
	}
	
	@Test
	void test_is_bipartite_selfloop_false_003() {

		System.out.println("++++ test_is_bipartite	_003 ++++");
		
		Graph2<Integer> g = new Graph2<Integer>();
		g.add(1);

		g.addDirectedEdge(1, 1);

		System.out.println(g.isBipartite());
		assertFalse(g.isBipartite());
	}
}