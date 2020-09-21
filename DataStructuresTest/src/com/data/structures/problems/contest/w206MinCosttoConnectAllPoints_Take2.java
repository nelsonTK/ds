package com.data.structures.problems.contest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * https://leetcode.com/problems/min-cost-to-connect-all-points/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class w206MinCosttoConnectAllPoints_Take2 {

	/** 
	 *  @intuition
	 *  	kinda standard implementation of the prims's algorithm, the kruskal's  algorithm might be better for this ocasion
	 *    
	 *	@score 
	 *      Runtime: 1847 ms, faster than 5.07% of Java online submissions for Min Cost to Connect All Points.
	 *      Memory Usage: 167.8 MB, less than 10.61% of Java online submissions for Min Cost to Connect All Points.
	 *      
	 *  @fail 
	 *  	1) Time limit excedeed, but looks the correct implementation
	 *  
	 *  @time
	 *  	
	 *  @space
	 *  
	 **/
	public int minCostConnectPoints(int[][] pts) {

		HashMap<Point, Integer> minCost = new HashMap<>();
		PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.distance, b.distance));
		Set<Point> visited = new HashSet<>();

		List<Point> points = new ArrayList<>();
		//Create List of Points
		for (int i = 0; i < pts.length; i++)
		{
			points.add(new Point(pts[i][0], pts[i][1]));
		}

		pq.add(new Edge(points.get(0), 0));
		minCost.put(points.get(0), 0);

		Edge cur;

		while(!pq.isEmpty())
		{
			cur = pq.poll();

			if (visited.size() == points.size())
				break;

			for (Point p : points)
			{
				if (p != cur.p && !visited.contains(p))
				{
					int dist = calcDist(cur.p, p);

					if(minCost.getOrDefault(p, Integer.MAX_VALUE) > dist)
					{
						pq.add(new Edge(p, dist));
						minCost.put(p, dist);
					}
				}
			}

			visited.add(cur.p);

		}


		int sum = 0;

		for (Point p : minCost.keySet())
		{
			sum += minCost.get(p);
		}

		return sum;
	}

	private int calcDist(Point a, Point b){
		return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
	}


	class Point{
		int x;
		int y;

		Point(int x, int y)
		{
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object o){
			Point p = (Point) o;
			return p.x == x && p.y == y;
		}

		@Override
		public int hashCode(){
			return (x + ":" + y).hashCode();
		}
	}

	class Edge{
		Point p;
		int distance;

		Edge (Point p, int d)
		{
			this.p = p;
			this.distance = d;
		}
	}
}
