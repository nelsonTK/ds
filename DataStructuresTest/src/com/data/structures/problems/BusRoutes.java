package com.data.structures.problems;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/bus-routes/
 * HARD
 * @author Nelson Costa
 *
 */
public class BusRoutes extends LeetCodeExercise{

	static BusRoutes b = new BusRoutes();
	public static void main(String[] args) {
		int [][] routes = stringToMatrix("[[1, 2, 7], [3, 6, 7]]");
		int s = 1;
		int t = 6;
		System.out.println(b.numBusesToDestination(routes, s, t));
	}

	/**
	 * [FAIL]
	 * 
	 * @fail
	 * 		1) passed the object instead of the value in the neighhbor inspection phase
	 * 		2) initialization of stations is wrong at the graph stage
	 * 		3) cost sum is wrong
	 * 		4) forgot to update the cost of the current level, for the parent, instead of left the default of 1
	 * 		5) failed because of differente sizes
	 * 		6) failed becaus I was not returnil -1, I was returning Integer.Max_val
	 * 		7) didn't thought about the edge case where the start equals to end.
	 * @param routes
	 * @param s
	 * @param t
	 * @return
	 */
    public int numBusesToDestination(int[][] routes, int s, int t) {
        //guards
            //if one?
        
        /*
        cache
            when finding the element save in the case the cost,
            when not finding the element save the cost in the cache
            */
        
        //BUILD THE GRAPH
        HashMap<Integer, List<Station>> g = new HashMap<>();
        HashMap<Integer, List<Station>> stations = new HashMap<>();
        Station cur;
        for(int bus = 0; bus < routes.length; bus++) //O(V)
        {
            for (int station = 0; station < routes[bus].length;station++)
            {
                if(station + 1 < routes[bus].length)
                {
                    cur = new Station(routes[bus][station + 1], bus, station + 1);
                    g.computeIfAbsent(routes[bus][station], k -> new ArrayList<Station>())
                    .add(cur);
                    
                }
                else if (routes[bus].length > 1){
                    //link last to first if size not one
                    cur = new Station(routes[bus][0], bus, 0);
                    g.computeIfAbsent(routes[bus][station], k -> new ArrayList<Station>())
                    .add(cur);
                    
                    /*
                    stations.computeIfAbsent(routes[bus][station], k -> new ArrayList<Station>())
                    .add( new Station(routes[bus][station], bus, station));*/
                }
                
                stations.computeIfAbsent(routes[bus][station], k -> new ArrayList<Station>())
                .add( new Station(routes[bus][station], bus, station));
            }
        }
        
        int min = Integer.MAX_VALUE;
        
        if (s == t && stations.get(s) != null)
        {
        	return 0;
        }
        
        //FOR EVERY POSITION STARTING IN s TRY bfs
        for (Station st : stations.getOrDefault(s, new ArrayList<Station>()))
        {
//            boolean seen [][] = new boolean[routes.length][routes[0].length];
            boolean seen [][] = new boolean[routes.length][100000];
            Queue<Station> q = new ArrayDeque<>();
            q.add(st);
            
            //setup first variable
            cur = st;
            cur.cost = 1;
            int curBus = cur == null? -1: cur.r; 
            seen[cur.r][cur.c] = true;
            
            while(!q.isEmpty())
            {
                cur = q.poll();
                if (cur.val == t)
                {
                    //found element
                    //save cache
                    //update min size
                    min = Math.min(min, cur.cost);
                    continue;
                }
                
                
                for(Station nei : g.getOrDefault(cur.val, new ArrayList<Station>()))
                {
                    if(!seen[nei.r][nei.c])
                    {
                        if(nei.r != cur.r)
                        {
                            nei.cost = cur.cost + 1;
                            q.add(nei);
                            seen[nei.r][nei.c] = true;
                        }
                        else
                        {
                        	nei.cost = cur.cost;
                            q.add(nei);
                            seen[nei.r][nei.c] = true;
                        }
                    }
                }
            }
            
            
        }
        
        return min == Integer.MAX_VALUE? -1 : min;
        
        
        //NODE AS vALUE, AND COORDINATES TO FIND THE BUS
        
        //MEMO
    }
    
    
    class Station{
        int val;
        int r; //bus
        int c;
        int cost;
        
        Station(int val, int r, int c)
        {
            this.val = val;
            this.r = r;
            this.c = c;
            cost = 1;
        }
        Station(int val, int r, int c, int cost)
        {
            this.val = val;
            this.r = r;
            this.c = c;
            this.cost = cost;
        }
    }
}
