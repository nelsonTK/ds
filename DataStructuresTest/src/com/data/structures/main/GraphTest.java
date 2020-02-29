package com.data.structures.main;

import com.data.structures.models.Graph1;
import com.data.structures.models.Graph1.Graph;

public class GraphTest {

	public static void main(String[] args) {
		Graph1 test = new Graph1();
		Graph graph = test.createGraph();
		//Graph graph = test.createGraph2();
		
		//graph.DFSRecursive();
		graph.DFSStack();
	}

}
