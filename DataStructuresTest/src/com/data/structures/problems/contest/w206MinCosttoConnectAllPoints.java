package com.data.structures.problems.contest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import com.data.structures.problems.ds.LeetCodeExercise;
/**
 * contest 206
 * https://leetcode.com/problems/min-cost-to-connect-all-points/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class w206MinCosttoConnectAllPoints extends LeetCodeExercise {

	public static void main(String[] args) {
		int [][] points = stringToMatrix("[[0,0],[2,2],[3,10],[5,2]");
		w206MinCosttoConnectAllPoints m = new w206MinCosttoConnectAllPoints();
		System.out.println(m.minCostConnectPoints(points));
	}

	 /*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 *  [WRONG IMPLEMENTATION OF MST WITH PRIM'S]
	 *  @Timetook
	 *  	45min
	 * @param points
	 * @return
	 */   public int minCostConnectPoints(int[][] points) {

		 PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.cost, b.cost));
		 Set<Point> visited = new HashSet<>();
		 int totalDistance = 0;
		 List<Point> list = new ArrayList<>();
		 HashMap<Point, Integer> cost = new HashMap<>();


		 for (int[] point : points)
		 {
			 list.add(new Point(point[0], point[1]));
		 }

		 Pair cur = new Pair(list.get(0), 0, 1);
		 pq.add(cur);
		 cost.put(list.get(0), 0);

		 int minCost = Integer.MAX_VALUE;


		 while (!pq.isEmpty())
		 {
			 cur = pq.poll();
			 //remove cur from the list
			 //totalDistance += cur.cost;

			 //list.remove(cur.point);
			 if (cur.count >= points.length)
				 System.out.println(cur.count + " " + points.length);


			 if (cur.cost > cost.getOrDefault(cur.point, Integer.MAX_VALUE))
				 continue;

			 //System.out.println(cur.count + " " + points.length);

			 if (cur.count >= points.length)
			 {
				 minCost = Math.min(cur.cost, minCost);
				 continue;
			 }
			 //System.out.println("cur count: " + cur.count);
			 cost.put(cur.point, cur.cost);
			 //System.out.println(cur.point + " min cost: " + cur.cost);
			 //int minDistance = Integer.MAX_VALUE;
			 //Point next = null;
			 for(Point point : list)
			 {
				 if (!point.equals(cur.point))
				 {
					 int distanceToPoint = calculateDistance(cur.point, point);
					 //System.out.println("parent : " + cur.point + " -> " + point + " cost -> " 
					 //                   + distanceToPoint + " -> totaldistance : " + (cur.cost + distanceToPoint));
					 /*
	                    if (distanceToPoint < minDistance)
	                    {
	                        next = point;
	                        minDistance = distanceToPoint;
	                    }
					  */

					 pq.add(new Pair(point, cur.cost + distanceToPoint, cur.count + 1));
				 }
				 //calculate the distance and put in pq.
			 }
			 //System.out.println("__");
			 /*
	            if (minDistance != Integer.MAX_VALUE)
	            {
	                //System.out.println(next + " cost -> " + minDistance);

	                pq.add(new Pair(next, minDistance));
	            }
			  */

			 //put in set list
			 visited.add(cur.point);
		 }


		 //finding max;
		 /*
	        int maxCost = 0;
	        System.out.println("size " + cost.size());
	        for (Point k : cost.keySet())
	        {
	            //maxCost = Math.max(maxCost, cost.get(k));
	            //System.out.println(cost.get(k));
	            maxCost += cost.get(k);
	        }
		  */


		 return minCost;

	 }

	 private int calculateDistance(Point a, Point b)
	 {
		 return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
	 }

	 class Point {
		 int x;
		 int y;

		 Point(int x, int y)
		 {
			 this.x = x;
			 this.y = y;
		 }

		 @Override
		 public boolean equals(Object o)
		 {
			 Point p = (Point) o;
			 return x == p.x && y == p.y;
		 }

		 @Override
		 public int hashCode(){
			 return (x + ":" + y).hashCode();
		 }

		 public String toString(){
			 return x + ":" + y;
		 }
	 }

	 class Pair {
		 Point point;
		 int cost;
		 int count;

		 Pair(Point p, int c, int count)
		 {
			 point = p;
			 cost = c;
			 this.count = count;
		 }
	 }
}

