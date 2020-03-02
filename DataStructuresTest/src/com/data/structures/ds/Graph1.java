package com.data.structures.ds;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import com.data.structures.utils.PrintHelper;


public class Graph1 {

	class Vertex {
		String label;
		Vertex(String label) {
			this.label = label;
		}
	}

	public class Graph {
		public String breadcrumb;
		public Graph() {
			this.adjVertices = new LinkedHashMap<Vertex, List<Vertex>>(); 
			breadcrumb = "";
		}

		private Map<Vertex, List<Vertex>> adjVertices;

		public List<Vertex> getAdjVertices(String label) {
			return adjVertices.get(new Vertex(label));
		}

		public void addVertex(String label) {
			adjVertices.putIfAbsent(new Vertex(label), new ArrayList<>());
		}

		public void removeVertex(String label) {
			Vertex v = new Vertex(label);
			adjVertices.values().stream().forEach(e -> e.remove(v));
			adjVertices.remove(new Vertex(label));
		}

		public void addEdge(String label1, String label2) {
			//Vertex v1 = new Vertex(label1);
			//Vertex v2 = new Vertex(label2);
			Vertex v1 = (adjVertices.entrySet()
					.stream()
					.filter(x -> x.getKey().label.equals(label1))
					.findFirst().orElse(null))
					.getKey();
			Vertex v2 = (adjVertices.entrySet()
					.stream()
					.filter(x -> x.getKey().label.equals(label2))
					.findFirst().orElse(null))
					.getKey();

			//estamos a adicionar à lista adjacente de cada um dos elementos, a respetiva ligação
			adjVertices.get(v1).add(v2);
			adjVertices.get(v2).add(v1);
		}

		public void removeEdge(String label1, String label2) {
			Vertex v1 = new Vertex(label1);
			Vertex v2 = new Vertex(label2);			
			List<Vertex> l1 = adjVertices.get(v1);
			List<Vertex> l2 = adjVertices.get(v2);
			if (l1 != null)
				l1.remove(v2);

			if (l2 != null)
				l2.remove(v1);
		}

		/**
		 * 
		 * Depth First Search Iteratively
		 * 
		 */
		public void DFSStack() {

			PrintHelper.printHeader("DFSStack");

			//fillVisited
			LinkedHashMap<Vertex,Boolean> visited = new LinkedHashMap<Graph1.Vertex, Boolean>();
			adjVertices.keySet().forEach(x -> visited.put(x, false));

			//Set Variables
			Set<Vertex> keys = adjVertices.keySet();
			Stack<Vertex> stack = new Stack<Vertex>();

			keys.forEach(v -> {	

				stack.push(v);

				PrintHelper.printHeader(v.label);

				while (!stack.isEmpty())
				{
					Vertex stackPeek = stack.peek();

					//Processing
					if (!visited.get(stackPeek))
					{
						breadcrumb += PrintHelper.getBreadcrumb(stackPeek.label);
						visited.put(stackPeek, true);
						System.out.println(breadcrumb);
					}
					

					//Get Adjacent Vertice
					Vertex adjVertice = adjVertices.get(stackPeek)
							.stream()
							.filter(x -> !visited.get(x))
							.findFirst()
							.orElse(null);

					//Update Stack
					if (adjVertice != null)
					{
						stack.push(adjVertice);
					}
					else 
					{						
						Vertex poppedVert = stack.pop();

						breadcrumb += PrintHelper.getBreadcrumb(poppedVert.label + " POP");

						System.out.println(breadcrumb);
					}
				}

				breadcrumb = "";
			});
		}

		/**
		 * Does the traversal of all the Graph in Deep First Search Recursively
		 */
		public void DFSRecursive()
		{
			PrintHelper.printHeader("DFSRecursive");	
			LinkedHashMap<Vertex, Boolean> visited = initVisited();

			visited.keySet()
			.stream()
			.forEach(x -> 
			{
				PrintHelper.printHeader(x.label);
				DFSUtil(x, visited);

				//Mark nodes with no vertices
				if (adjVertices.get(x).size() == 0) //adjVertices.get(x) != null && 
				{
					breadcrumb += " > [No Nodes]";
					System.out.println(breadcrumb);
				}

				//reset breadcrumb
				breadcrumb = "";
			});


		}

		private void DFSUtil(Vertex node, LinkedHashMap<Vertex, Boolean> visited) {

			if(!visited.get(node))
			{
				visited.put(node, true);

				breadcrumb += " > " + node.label;

				System.out.println(breadcrumb);

				adjVertices.get(node).stream().forEach(x -> DFSUtil(x, visited));

			}else
			{
				breadcrumb += " > " + node.label + " X";
				System.out.println(breadcrumb); // already visited
			}
		}

		private LinkedHashMap<Vertex, Boolean> initVisited() {

			LinkedHashMap<Vertex, Boolean> visited = new LinkedHashMap<Vertex, Boolean>();
			adjVertices.keySet()
			.stream()
			.forEach(x -> visited.put(x, false));

			return visited;
		}
	}

	public Graph createGraph() {
		Graph graph = new Graph();
		graph.addVertex("Bob");
		graph.addVertex("Alice");
		graph.addVertex("Mark");
		graph.addVertex("Rob");
		graph.addVertex("Maria");
		graph.addEdge("Bob", "Alice");
		graph.addEdge("Bob", "Rob");
		graph.addEdge("Alice", "Mark");
		graph.addEdge("Rob", "Mark");
		graph.addEdge("Alice", "Maria");
		graph.addEdge("Rob", "Maria");
		return graph;
	}

	public Graph createGraph2() {
		Graph graph = new Graph();
		graph.addVertex("Bob");
		graph.addVertex("Alice");
		graph.addVertex("Mark");
		graph.addVertex("Rob");
		graph.addVertex("Maria");
		graph.addVertex("Monica");
		graph.addVertex("Cesaltina");
		graph.addVertex("Estevão");
		graph.addVertex("Vitor");
		graph.addVertex("Estefânia");
		graph.addEdge("Bob", "Alice");
		graph.addEdge("Bob", "Rob");
		graph.addEdge("Bob", "Vitor");
		graph.addEdge("Alice", "Mark");
		graph.addEdge("Rob", "Mark");
		graph.addEdge("Alice", "Maria");
		graph.addEdge("Rob", "Maria");
		graph.addEdge("Estevão", "Cesaltina");
		return graph;
	}
}
