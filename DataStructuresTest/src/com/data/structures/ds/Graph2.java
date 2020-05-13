package com.data.structures.ds;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Stack;

/**
 * 
 * @author Nelson Costa
 *
 * @param <T> WARNING: <p>
 * 
 * IMPLEMENT TO STRING FOR T <p>
 * 	in order for different objects to be compared the same we need to override the classes' toString() 
 * 	so that we can get the same string for different objects with same property values. 
 * 	this string will later be hashed and that's what makes possible different objects be hashed the same.
 * 	Ideally we would need to Override hashcode, but some objects like 'Integer' would require a special treatment because we cannot Override it's hashcode function.
 * 
 * IMPLEMENT EQUALS
 * 	If you are using Objects, and don't want to save references of objects you insert in the graph 
 * 	you can create a new object with the same properties and used it as input to get its adjacent nodes.
 * 	But this requires you to override equals method, because by default diferent objects have different references. 
 * 	if you don't want this to be the equals comparison you have to override it and define your own rules for equality. 
 * 	You need to implement equals so that when create a new object with the same properties that
 */
public class Graph2<T> {

	private HashMap<Node<T>, ArrayList<Edge<T>>> adj;	

	public Graph2(){	
		adj = new HashMap<Node<T>, ArrayList<Edge<T>>>();
	}

	public Graph2(boolean isDirected){	
		adj = new HashMap<Node<T>, ArrayList<Edge<T>>>();
	}

	public void add(T value) {
		Node<T> node = new Node<T>(value);
		if (adj.get(node) == null)
			adj.put(node, new ArrayList<Edge<T>>());
	}
	public void addDirectedEdge(T origin, T destination) {
		addUnidiretionalEdge(origin, destination, 1);
	}
	public void addUnidiretionalEdge(T origin, T destination, int weight)
	{

		Node<T> oNode = new Node<T>(origin);
		Node<T> dNode = new Node<T>(destination);
		Edge<T> edge = new Edge<T>(oNode, dNode, weight);

		if (adj.get(oNode) == null )
		{
			System.out.println( origin.toString() + " don't exists.");
			return;
		}

		if(adj.get(dNode) == null)
		{
			System.out.println( destination.toString() + " don't exists.");
			return;
		}

		ArrayList<Edge<T>> edges = adj.get(oNode);

		if (edges.size() == 0) {
			edges.add(edge);
			//			System.out.println("New Edge Created");
		}
		else
		{
			Edge<T> oldEdge = getEdgeFromList(oNode, edge);

			if (oldEdge != null && oldEdge.getWeight()!= edge.getWeight())
			{
				oldEdge.setWeight(edge.getWeight());
				System.out.println("Update Edge Weight");
			}
			else if(oldEdge != null && oldEdge.getWeight() == edge.getWeight())
			{
				System.out.println("Edge already Exists" );
			}
			else
			{
				edges.add(edge);
				//				System.out.println("New Edge Created");
			}

		}
	}

	public void addBidirectionalEdge(T value1, T value2, int weight) {
		addUnidiretionalEdge(value1, value2, weight);
		addUnidiretionalEdge(value2, value1, weight);
	}


	public ArrayList<Edge<T>> getEdges(T value){
		if (value == null)
			return null;

		Node<T> node = new Node<T>(value);
		ArrayList<Edge<T>> edges = adj.get(node);
		return edges;
	}

	public void DFSPrintIterative(T value) {

		Node<T> curr = new Node<T>(value);

		Node<T> child;
		HashSet<Node<T>> visited = new HashSet<Node<T>>();
		boolean hasChildrenToProcess = false;

		ArrayList<Edge<T>> edges = new ArrayList<Edge<T>>();

		Stack<Node<T>> s = new Stack<Node<T>>();

		if (adj.get(curr) == null)
			return;		

		s.push(curr);
		visited.add(curr);
		process(curr);

		while (!s.isEmpty()) {

			curr = s.peek();

			//Process Current
			if(!visited.contains(curr))
			{
				process(curr);
				visited.add(curr);
			}

			edges = adj.get(curr);

			//get child
			hasChildrenToProcess = false;

			for (Edge<T> e : edges) {

				child = e.getDest();
				if (!visited.contains(child))
				{
					s.push(child);
					hasChildrenToProcess = true;
					break;
				}
			}

			//baseCase
			if (edges == null || !hasChildrenToProcess)
			{
				s.pop();
			}

		}
	}		


	public void BFSPrint(T value) {
		Node<T> curr = new Node<T>(value);

		if (adj.get(curr) == null)
		{
			System.out.println("node not found");
			return;
		}

		Node<T> dest ;
		HashSet<Node<T>> visited = new HashSet<Node<T>>();
		Queue<Node<T>> queue = new ArrayDeque<Node<T>>();
		ArrayList<Edge<T>> edges;
		queue.add(curr);
		visited.add(curr);

		while(!queue.isEmpty()) {
			curr = queue.remove();

			process(curr);

			edges = adj.get(curr);

			for (Edge<T> e : edges) {
				dest = e.getDest();

				if (dest != null && !visited.contains(e.getDest()))
				{
					queue.add(dest);
					visited.add(dest);
				}
			}
		}
	}

	/**
	 * Trial of Tarjan's Strongly Connected Components Algorithm
	 * @param start
	 */
	public void FindStronglyConnectedComponents() {

		HashSet<Node<T>> visited = new HashSet<>();
		Stack<Node<T>> stack = new Stack<Node<T>>();
		HashMap<Node<T>, Integer> lowLinkmap = new HashMap<Node<T>, Integer>(); 
		HashMap<Integer, ArrayList<Node<T>>> components = new HashMap<Integer, ArrayList<Node<T>>>();

		for (Node<T> curr : adj.keySet()) {

			if (!lowLinkmap.containsKey(curr))
			{
				findSCC(curr, lowLinkmap.size() , null, visited, stack, lowLinkmap, components);
			}

		}

		Integer component;
		ArrayList<Node<T>> nodes;

		//Print only
		for (Entry<Integer, ArrayList<Node<T>>>  entry: components.entrySet()) {
			component = entry.getKey();
			nodes = entry.getValue();

			System.out.println("Compoent: " + component);
			for (Node<T> node : nodes) {
				System.out.println("--> " + node.getValue());
			}
		}
	}

	private int findSCC(Node<T> curr, int currLowLink, Node<T> prev, HashSet<Node<T>> visited, Stack<Node<T>> stack, HashMap<Node<T>, Integer> lowLinkmap, HashMap<Integer, ArrayList<Node<T>>> components) {

		//visited can be replaced by lowLinkMap
		if (visited.contains(curr) && stack.contains(curr))
		{
			return Math.min(lowLinkmap.get(prev), lowLinkmap.get(curr));
		}

		//must ignore entry
		if (visited.contains(curr) && !stack.contains(curr))
		{
			return Integer.MAX_VALUE;
		}

		if (!visited.contains(curr))
		{
			visited.add(curr);
			lowLinkmap.put(curr, currLowLink);
			stack.add(curr);
		}

		Node<T> neighbor;

		int nextLowLink = currLowLink;
		for (Edge<T> edge : adj.get(curr)) {

			neighbor = edge.getDest();

			if (!visited.contains(neighbor))
			{
				nextLowLink++;
			}

			currLowLink = Math.min(currLowLink, findSCC(neighbor, nextLowLink, curr, visited, stack, lowLinkmap, components));

			lowLinkmap.put(curr, currLowLink); //no verification... can imporve

		}

		stack.pop();
		ArrayList<Node<T>> a;

		if(!components.containsKey(currLowLink))
		{
			a = new ArrayList<Node<T>>();
			a.add(curr);
			components.put(currLowLink, a);
		}
		else {
			a = components.get(currLowLink);
			a.add(curr);
			components.put(currLowLink, a);
		}

		return currLowLink;
	}

	/**
	 * this is not thoroughly tested
	 */
	public void topologicalSort() {
		Stack<Node<T>> topOrder = new Stack<Node<T>>();
		HashSet<Node<T>> visited = new HashSet<>();
		for (Node<T> n : adj.keySet())
		{
			if (!visited.contains(n))
			{
				topSort(n, visited, topOrder);
			}
		}

		while (!topOrder.isEmpty())
		{
			System.out.print(" -> " + topOrder.pop().getValue());

		}
	}


	private void topSort(Node<T> n, HashSet<Node<T>> visited, Stack<Node<T>> topOrder) {

		if (visited.contains(n))
		{
			return;
		}

		visited.add(n);

		for(Edge<T> edge : adj.get(n))
		{
			topSort(edge.getDest(), visited, topOrder);
		}

		topOrder.add(n);		
	}

	public boolean isBipartite() {
		if (adj.size() == 0)
		{
			return true;
		}

		HashSet<Node<T>> red = new HashSet<>();
		HashSet<Node<T>> blue = new HashSet<>();
		Queue<Node<T>> q = new ArrayDeque<>();
		Node<T> curr = adj.keySet().iterator().next();
		q.add(curr);
		red.add(curr);
		while (!q.isEmpty())
		{
			curr = q.poll();
			if (red.contains(curr))
			{
				if(!colorNeighbors(curr, blue, red, q))
				{
					return false;
				}
			}
			else
			{
				if(!colorNeighbors(curr,red, blue, q))
				{
					return false;
				}
			}
		}

		return true;
	}

	public boolean colorNeighbors(Node<T> node, HashSet<Node<T>> correctColor, HashSet<Node<T>> wrong, Queue<Node<T>> q) {

		for (Edge<T> edge : adj.get(node)) {

			if (wrong.contains(edge.getDest()))
			{
				return false;
			}
			else if(!correctColor.contains(edge.getDest())) {
				correctColor.add(edge.getDest());
				q.add(edge.getDest());
			}			
		}
		return true;
	}

	public void process(Node<T> node) {
		System.out.println(node.getValue());
	}


	/**
	 * print graph
	 */
	public void print() {				
		for (Node<T> key : adj.keySet()) {
			System.out.println(key);			
			for (Edge<T> edge : adj.get(key)) {
				System.out.println(edge.getOri() + " -> " + edge.getDest());
			}
		}
	}


	private Edge<T> getEdgeFromList(Node<T> node,Edge<T> edge){

		ArrayList<Edge<T>> edges = adj.get(node);

		if (edges != null) {
			for (Edge<T> e : edges) {
				if (e.equals(edge))
				{
					return e;
				}
			}
		}

		return null;
	}
}

class Node <T>{
	private T value;

	public Node(T value) {
		this.value = value;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object val) {
		return this.value.equals(((Node<T>)val).value);
	}

	@Override
	public String toString() {
		return value.toString();
	}

	/**
	 * to be able of using this structure with 'Integer' which I cannot override its hashcode method
	 * To garantee that different object with same attribute values are hashed the same, we have to implement toString
	 */
	@Override 
	public int hashCode() {
		return  value.toString().hashCode();
	}
}

class Edge <T> {
	private Node<T> origin;
	private Node<T> destination;
	private int weight;

	public Edge(Node<T> origin, Node<T> destination) {
		super();
		this.origin = origin;
		this.destination = destination;
	}

	public Edge(Node<T> origin, Node<T> destination, int weight) {
		super();
		this.origin = origin;
		this.destination = destination;
		this.weight = weight;
	}

	/**
	 * Returns true if both origin and destination are equal
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
		Edge<T> edge = (Edge<T>) o;
		return edge.origin.equals(this.origin) & edge.destination.equals(this.destination);
	}

	public Node<T> getOri() {
		return origin;
	}

	public void setOri(Node<T> ori) {
		this.origin = ori;
	}

	public Node<T> getDest() {
		return destination;
	}

	public void setDest(Node<T> dest) {
		this.destination = dest;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
}