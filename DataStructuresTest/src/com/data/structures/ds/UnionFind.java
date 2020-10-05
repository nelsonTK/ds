package com.data.structures.ds;

/**
 * Simple Union find implementation
 * with path compression and rank link
 * @author Nelson Costa
 *
 */
public class UnionFind {
	int [] arr;
	int [] rank;

	public UnionFind(int length)
	{
		arr = new int [length];
		rank = new int [length];

		for (int i = 0; i < length; i++)
			arr[i] = i;
	}

	public void union(int a, int b)
	{
		int rA = find(a);
		int rB = find(b);

		if (rank[rA] == rank[rB])
		{
			rank[rA]++;
			//join b to a
			arr[rB] = rA;
		}
		else if (rank[rA] > rank[rB])
		{
			arr[rB] = rA;
		}
		else
		{
			arr[rA] = rB;
		}
	}

	public int find(int a){
		int root = a;

		while (root != arr[root])
		{
			root = arr[root];
		}

		int next;
		while (a != arr[a])
		{
			next = arr[a];
			arr[a] = root;
			a = next;
		}

		return root;
	}
}
