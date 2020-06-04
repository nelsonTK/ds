package com.data.structures.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import com.data.structures.problems.ds.LeetCodeExercise;

public class AccountsMerge extends LeetCodeExercise{
	static AccountsMerge a = new AccountsMerge();
	public static void main(String[] args) {

		String [] s1 = {"John", "johnsmith@mail.com", "john00@mail.com"};
		String [] s2 = {"John", "johnnybravo@mail.com"};
		String [] s3 = {"John", "johnsmith@mail.com", "john_newyork@mail.com"};
		String [] s4 = {"Mary", "mary@mail.com"};
		List<List<String>> accounts = new ArrayList<List<String>>();
		accounts.add(Arrays.asList(s1));
		accounts.add(Arrays.asList(s2));
		accounts.add(Arrays.asList(s3));
		accounts.add(Arrays.asList(s4));

		//		[["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"],
		//		 ["Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"],
		//		 ["Ethan","Ethan5@m.co","Ethan4@m.co","Ethan0@m.co"],
		//		 ["Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"],
		//		 ["Fern","Fern5@m.co","Fern1@m.co","Fern0@m.co"]]

		for (List<String> s :a.accountsMerge(accounts))
		{
			printArray(s.toArray());
		}
	}

	/**

	@intuition
		For merging the accounts I used a combination of union find and hashmaps to reduce time complexity.
		the essential knowledge was that if two emails are shared by two different accounts, then they are the same

		so We translate emails and users into Id's.

		we segregate the Id's in a way that emails and usernames dont mix.

		like first n ids are for users, and the rest for emails.

		and then we union user names with Ids

		when building the answer we only add names once to the list

		and then we sort each list.
		
		I Loved this question <3

	@optimizations
		I didn't need to union users and emails. I could have just union emails.
		but I've not done it. next time.

	@score
		Runtime: 27 ms, faster than 93.94% of Java online submissions for Accounts Merge.
		Memory Usage: 45.8 MB, less than 58.82% of Java online submissions for Accounts Merge.

    @fail
        1) null pointer exception at rank array
        2) forgot to eliminate junk, from the list namely user names
        3) first element of the array which was user name was not offset
		4) forgot to add protection against elements that do not belong to the set valid elements. [caused by the heuristic to calculate the emails size, so that I can initialize unionfind datastructure]
		5) didn't noticed that the email acounts should be returned in sourted order

    @time 	O(N*M*(Log n)) - Arguably
    @space 	O(N)

    @degub 
    	yes

	 **/
	public List<List<String>> accountsMerge(List<List<String>> accounts) {
		//guards
		if (accounts == null || accounts.size() <= 1)
			return accounts;


		HashMap<Integer, String> userID = new HashMap<>();
		HashMap<String, Integer> emailID = new HashMap<>();
		HashMap<Integer, String> ReverseEmailID = new HashMap<>();

		int users_count = accounts.size();
		int email_count = 0;
		int currEmailID = users_count + 1;

		//heuristic to count emails and then be able to calculate unionfind
		for (List<String> emails : accounts) //O(N)
			email_count+=emails.size();

		Union uni = new Union(users_count + email_count); //O(N*M)

		for (int i = 0; i < users_count; i++) //O(N)
		{
			//user Id
			userID.put(i, accounts.get(i).get(0));

			for (int j = 1; j < accounts.get(i).size(); j++) //O(M) -> O(N * M)
			{
				String email = accounts.get(i).get(j);
				Integer id = emailID.get(email);

				if (id == null)
				{
					emailID.put(email, currEmailID);
					ReverseEmailID.put(currEmailID, email); 
					uni.union(i, currEmailID); //O(Log n) [arguably] -> O(N*M*(Log n))
					currEmailID++;
				}
				else
				{
					uni.union(i, id);
				}
			}
		}

		//building the answer
		int totalNodes = users_count + email_count;
		HashMap<Integer, List<String>> ans = new HashMap<>();
		int root;
		List<String> list;

		for (int i = totalNodes - 1; i > users_count; i--)
		{
			if (ReverseEmailID.get(i) != null) {
				root = uni.find(i);
				list = ans.getOrDefault(root, new ArrayList<String>());

				if (list.size() == 0)
				{
					list.add(userID.get(root));
					list.add(ReverseEmailID.get(i));
					ans.put(root, list);
				}
				else
				{
					if (i > users_count)
						list.add(ReverseEmailID.get(i));
				}
			}
		}

		//sorting the answer
		for (Integer k : ans.keySet()) //O(N)
		{
			Collections.sort(ans.get(k).subList(1, ans.get(k).size())); //O(MLogK) K is the string size
		}

		return new ArrayList<List<String>>(ans.values());

	}


	class Union
	{
		int [] arr;
		int [] rank;

		public Union(int length)
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

}

/**
 * Solution with union find
 * 
 * @intuition
 * 		instead of joining names and emails like i did, they do unions only between emails. (main difference)
 * 		they maintain a map between emails and ids and also a map for emails and names.
 * 		in the end they build the answer and sorte the lists.
 * 
 * 	    In essence the main difference is that. they just union the emails. not emails and users.
 * 		
 * @score
		Runtime: 32 ms, faster than 83.56% of Java online submissions for Accounts Merge.
		Memory Usage: 43.5 MB, less than 100.00% of Java online submissions for Accounts Merge.
 * 
 * @author Nelson Costa
 *
 */
class AccountsMergeSolution2 {
	public List<List<String>> accountsMerge(List<List<String>> accounts) {
		DSU dsu = new DSU();
		Map<String, String> emailToName = new HashMap();
		Map<String, Integer> emailToID = new HashMap();
		int id = 0;
		for (List<String> account: accounts) {
			String name = "";
			for (String email: account) {
				if (name == "") {
					//set name for the first entry
					name = email;
					continue;
				}
				//Set Email to userName
				emailToName.put(email, name);
				
				//Set email to Id
				if (!emailToID.containsKey(email)) {
					emailToID.put(email, id++);
				}
				
				// Union first Email with others
				dsu.union(emailToID.get(account.get(1)), emailToID.get(email));
			}
		}

		//building answer with emails only
		Map<Integer, List<String>> ans = new HashMap();
		for (String email: emailToName.keySet()) {
			int index = dsu.find(emailToID.get(email));
			ans.computeIfAbsent(index, x-> new ArrayList()).add(email);
		}
		
		//sort emails and add name in first position
		for (List<String> component: ans.values()) {
			Collections.sort(component);
			component.add(0, emailToName.get(component.get(0)));
		}
		return new ArrayList(ans.values());
	}


	class DSU {
		int[] parent;
		public DSU() {
			parent = new int[10001];
			for (int i = 0; i <= 10000; ++i)
				parent[i] = i;
		}
		public int find(int x) {
			if (parent[x] != x) parent[x] = find(parent[x]);
			return parent[x];
		}
		public void union(int x, int y) {
			parent[find(x)] = find(y);
		}
	}
}

/**
 * @intuition
 * 		Depth first search solution
 * 
 * 		the goal is to connect the emails, and from there create lists with all the emails connected.
 * 
 * @score
		Runtime: 33 ms, faster than 77.66% of Java online submissions for Accounts Merge.
		Memory Usage: 44.2 MB, less than 100.00% of Java online submissions for Accounts Merge.
 * 
 * @author Nelson Costa
 *
 */
class AccountsMergeSolution1 {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> emailToName = new HashMap();
        Map<String, ArrayList<String>> graph = new HashMap();
        //creating undirected graph
        for (List<String> account: accounts) {
            String name = "";
            for (String email: account) {
                if (name == "") {
                    name = email;
                    continue;
                }
                graph.computeIfAbsent(email, x-> new ArrayList<String>()).add(account.get(1));
                graph.computeIfAbsent(account.get(1), x-> new ArrayList<String>()).add(email);
                emailToName.put(email, name);
            }
        }

        Set<String> seen = new HashSet();
        List<List<String>> ans = new ArrayList();
        
        //for each email in the graph get its neighbors and add to a component list, which will be added to an answer variable.
        for (String email: graph.keySet()) {
            if (!seen.contains(email)) {
                seen.add(email);
                Stack<String> stack = new Stack();
                stack.push(email);
                List<String> component = new ArrayList();
                //visit neighbors of email
                while (!stack.empty()) {
                    String node = stack.pop();
                    component.add(node);
                    for (String nei: graph.get(node)) {
                        if (!seen.contains(nei)) {
                            seen.add(nei);
                            stack.push(nei);
                        }
                    }
                }
                //sort and add name
                Collections.sort(component);
                component.add(0, emailToName.get(email));
                ans.add(component);
            }
        }
        return ans;
    }
}