package com.data.structures.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import com.data.structures.problems.ds.LeetCodeExercise;

public class CriticalConnectionsInANetwork extends LeetCodeExercise{

	static CriticalConnectionsInANetwork c = new CriticalConnectionsInANetwork();
	public static void main(String[] args) {
		int n = 5;
		int [][] cons= stringToMatrix("[[0,1],[1,2],[2,3],[3,4],[1,3],[0,4]]");
		List<List<Integer>> connList = new ArrayList<List<Integer>>();
		for (int[] con : cons)
		{
			connList.add(Arrays.asList(con[0], con[1]));
		}
		
		for (List<Integer> rows : c.criticalConnections(n, connList))
		{
			System.out.println(Arrays.toString(rows.toArray()));
		}
	}


	/**
    @fail
        1) indegree solution is not good enough just covers a subset of cases

	 **/
	/*
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> cons) {
        HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
        HashMap<Integer, Integer> indegree = new HashMap<>();
        List<List<Integer>> ans = new ArrayList<List<Integer>>();

        for (List<Integer> con : cons)
        {
            graph.computeIfAbsent(con.get(0), k -> new ArrayList<Integer>()).add(con.get(1));
            graph.computeIfAbsent(con.get(1), k -> new ArrayList<Integer>()).add(con.get(0));
            //indegree.comnputeIfAbsent(con[0], k -> 0)++;
            //indegree.comnputeIfAbsent(con[1], k -> 0)++;
            indegree.put(con.get(0), indegree.getOrDefault(con.get(0), 0) + 1);
            indegree.put(con.get(1), indegree.getOrDefault(con.get(1), 0) + 1);
        }

    	for (int key : indegree.keySet())
        {
            if (indegree.get(key) == 1)
            {
                ArrayList<Integer> row = new ArrayList<Integer>();

                row.add(key);
                row.add(graph.get(key).get(0));
                ans.add(row);
            }
        }


        return ans;
    }

	 */

	int low;
	int [] lowlink, id;
	boolean [] inStack;
	Stack<Integer> stack;

	/**

	@score
		Runtime: 185 ms, faster than 22.28% of Java online submissions for Critical Connections in a Network.
		Memory Usage: 134 MB, less than 22.34% of Java online submissions for Critical Connections in a Network.

    @fail
        1) the recursive call had the wrong parameters
        2) I was lacking the final step which was only add elements to critical path if they were no on inStack

    @time   O(V + E) -> V tightened
    @space  O(V + E)

    @debug
    	yes

	 **/
	public List<List<Integer>> criticalConnections(int n, List<List<Integer>> cons) {

		//guards missing...
		//empty graph
		if (cons == null || cons.size() == 0)
			return new ArrayList<List<Integer>>();


		List<List<Integer>> ans = new ArrayList<>();
		HashMap<Integer, List<Integer>> graph = new HashMap<>();

		this.lowlink = new int[n];
		this.id = new int[n];
		this.inStack = new boolean[n];
		this.stack = new Stack<>();
		this.low = 0;        

		int from, to;

		//Build Graph
		for (List<Integer> con : cons) //O(E)
		{
			from = con.get(0);
			to = con.get(1);

			graph.computeIfAbsent(from, k -> new ArrayList<Integer>()).add(to);
			graph.computeIfAbsent(to, k -> new ArrayList<Integer>()).add(from);
		}

		//dfs
		findCritical(-1, 0, graph, ans);

		return ans;
	}

	private int findCritical(int from, int to, 
			HashMap<Integer, List<Integer>> graph,
			List<List<Integer>> ans)
	{

		if (inStack[to])
			return lowlink[to];

		this.id[to] = low;
		this.lowlink[to] = low++;
		this.inStack[to] = true;
		this.stack.push(to);

		int neiLow;
		List<Integer> ansRow;

		for (int nei : graph.getOrDefault(to, new ArrayList<Integer>())) //V (tightened)
		{
			if (nei != from)
			{
				neiLow = findCritical(to, nei, graph, ans);
				
				if (neiLow > lowlink[to] && !inStack[nei]) //very important to have this inStack, it prevents me to add invalid critical conections and in the context of tarjans it prevents me from destroying the components
				{
					ansRow = new ArrayList<>();
					ansRow.add(to);
					ansRow.add(nei);
					ans.add(ansRow);
					//System.out.printf("critical ... %d %d %d %d \n", to, nei, lowlink[to], neiLow);
				}
				else
				{
					//System.out.printf(" before update: %d %d %d %d \n", to, nei, lowlink[to], neiLow);

					lowlink[to] = Math.min(lowlink[to], neiLow);
				}
			}
		}

		//clean stack
		if (lowlink[to] == id[to])
		{
			int node;
			while (!stack.isEmpty() && lowlink[stack.peek()] >= id[to])
			{
				node = stack.pop();
				inStack[node] = false;
			}
		}

		return lowlink[to];
	}
}





/**
 * 89ms
 * 
 * @author Nelson Costa
 *
 */
class CriticalConnectionsInANetworkSolution1 {
    int time = 0;
    
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> result = new ArrayList<>();
        
        // graph
        List<Integer>[] graph = new List[n];
        buildGraph(connections, graph);
        
        boolean[] visited = new boolean[n];
        int[] disc = new int[n];
        int[] low = new int[n];
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) dfs(i, visited, disc, low, parent, graph, result);
        }
        
        return result;
    }
    
    private void dfs(int u, boolean[] visited, int[] disc, int[] low, int[] parent, List<Integer>[] graph, List<List<Integer>> result) {
        visited[u] = true;
        
        disc[u] = low[u] = time++;
        
        for (int v : graph[u]) {
            if (!visited[v]) {
                parent[v] = u;
                
                dfs(v, visited, disc, low, parent, graph, result);
                
                low[u] = Math.min(low[u], low[v]);
                
                if (disc[u] < low[v]) {
                    result.add(Arrays.asList(u, v));
                }
            } else if (v != parent[u]) { // v is visted
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }
    
    private void buildGraph(List<List<Integer>> connections, List<Integer>[] graph) {
        for (List<Integer> edge : connections) {
            int a = edge.get(0);
            int b = edge.get(1);
            
            if (graph[a] == null) graph[a] = new ArrayList<>();
            if (graph[b] == null) graph[b] = new ArrayList<>();
            
            graph[a].add(b);
            graph[b].add(a);
        }
    }
}