package com.data.structures.problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/alien-dictionary
 * HARD
 * @author Nelson Costa
 *
 */
public class AlienDictionary extends LeetCodeExercise{

	static AlienDictionary a = new AlienDictionary();

	public static void main(String[] args) {

		String [] words = {
				"wrt",
				"wrf",
				"er",
				"ett",
				"rftt"
		};

		System.out.println(a.alienOrder(words));
	}

	int white = 0;
	int grey = 1;
	int black = 2;
	char infinity = 'a' + 26;
	/**
	 * @commets
	 * 		Very though question....
	 * 		Very Very tough a lot of details to have into account or a lot of details omitted...
	 * 
	 * @score
	 * 		Runtime: 6 ms, faster than 28.20% of Java online submissions for Alien Dictionary.
			Memory Usage: 39.7 MB, less than 33.54% of Java online submissions for Alien Dictionary.
	 * 
	 * @fail
	 * 		1) Arrays.fill copies the object not the value;
	 * 		2) I was not processing the last element
	 * 		3) using == instead of equals
	 * 		4) when using set to control duplicates, I forgot to add a for loop
	 * 		5) forgot to add nodes as visited... 
	 * 		6) I failed an edge case where single letter only and all the same "z", "z"
            7) I was lacking another edge case which is when Is when one words still have elements but the one which we compare don't on this cases the letter was not added, the solution was on
            8) another edge case when prefix is equal but a letter comes before a space
			9) I was not covering another edge case which was when we only had one word which they wanted to return all letters...
			
			
	 *  @time	O(n*m) n = words and m = word size (all the same size)
	 *  @space	O(E + V)
	 * 
	 * @param words
	 * @return
	 */
	public String alienOrder(String[] words) {

		//guards
		if (words == null || words.length == 0)
			return "";

        if (words.length == 1)
        {
            Set<Character> set = new HashSet<>();
            for (char c : words[0].toCharArray())
                set.add(c);
            
            StringBuilder s = new StringBuilder("");
            
            for (Character c : set)
            {
                s.append(c);
            }
            return s.toString();
        }
        

		HashMap<Character, List<Character>> graph = new HashMap<>();
		Set<Pair> pairs = new HashSet<Pair>();

		StringBuilder [] prefix = new StringBuilder[words.length];
		for (int i = 0; i < prefix.length; i++)
			prefix[i] = new StringBuilder("");

		int [] charIdx = new int[words.length];
		boolean somethingChanged = true;

		int curCharIndex = 0;
		String curWord = "";
		int nextCharIndex = 0;
		String nxtWord = "";
		
		infinity = 'a' + 26;
		
		while (somethingChanged) // O(wordsX wordlength)
		{
			somethingChanged = false;

			for (int i = 0; i <= words.length-1; i++)
			{
				curCharIndex = charIdx[i];
				curWord = words[i];

				if (i + 1 < words.length)
				{
					nextCharIndex = charIdx[i + 1];
					nxtWord = words[i + 1];
				}

				if (i + 1 < words.length &&
						curCharIndex  < curWord.length() &&
						nextCharIndex < nxtWord.length() &&
						prefix[i].toString().equals(prefix[i + 1].toString()) //&&
						)
				{
					if (curWord.charAt(curCharIndex) != nxtWord.charAt(nextCharIndex))
						pairs.add(new Pair(curWord.charAt(curCharIndex), nxtWord.charAt(nextCharIndex)));
					else
						pairs.add(new Pair(curWord.charAt(curCharIndex), infinity));
					
					somethingChanged = true; //will terminate when all words are consumed...
				}
				else if (
                        i + 1 < words.length &&
						curCharIndex  < curWord.length() &&
						prefix[i].toString().equals(prefix[i + 1].toString()) &&
                        nextCharIndex >= nxtWord.length()
						)
				{
					return "";
				}
				else if (curCharIndex  < curWord.length())
                {
                    pairs.add(new Pair(curWord.charAt(curCharIndex), infinity));
                    somethingChanged = true; //will terminate when all words are consumed...

                }
				//when you have two words with the same prefix but the letter comes before the empty space we finish.

				if(curCharIndex < curWord.length())
				{
					prefix[i].append(curWord.charAt(curCharIndex));
				}

				charIdx[i]++;

			}
		}

		//build graph
		for (Pair p : pairs) // O(E)
		{
			graph.computeIfAbsent(p.from, k-> new ArrayList<Character>()).add(p.to);
		}

		int [] visited = new int[26 + 1]; // plus one is for infinity
		StringBuilder ans = new StringBuilder("");

		for (char letter : graph.keySet())
		{
			if (visited[letter - 'a'] == white)
				if (getRules(letter, ans, visited, graph)== -1) return "";
		}
		return ans.reverse().toString();

	}

	private int getRules(char node, StringBuilder ans, int[] visited,  HashMap<Character, List<Character>> graph)
	{
		if (visited[node - 'a'] == grey) //cycle
			return -1;

		if (visited[node - 'a'] == black)
			return 1;

		visited[node - 'a'] = grey;
				
		for (char nei : graph.getOrDefault(node, new ArrayList<Character>()))
		{
			if (visited[nei - 'a'] != grey)
			{

				if (getRules(nei, ans, visited, graph) == -1)
				{
					return -1; 
				}
			}
			else 
				return -1;
		}

		ans.append(node == infinity? "" : node);

		visited[node - 'a'] = black;
		
		return 1;
	}


	class Pair{
		char from;
		char to;

		public Pair(char from, char to){
			this.from = from;
			this.to = to;
		}


		@Override
		public boolean equals(Object a) {        	
			return ((Pair) a).from ==  this.from && ((Pair) a).to == this.to;
		}
	}
}


/*********************
* OTHERS SOLUTIONS
*********************/
/**
 * Depth-First Search
 * 
 * @author Nelson Costa
 *
 */
class AlienDictionarySolution2 {
    
    private Map<Character, List<Character>> reverseAdjList = new HashMap<>();
    private Map<Character, Boolean> seen = new HashMap<>();
    private StringBuilder output = new StringBuilder();
    
    public String alienOrder(String[] words) {
        
        // Step 0: Put all unique letters into reverseAdjList as keys.
        for (String word : words) {
            for (char c : word.toCharArray()) {
                reverseAdjList.putIfAbsent(c, new ArrayList<>());
            }
        }
        
        // Step 1: Find all edges and add reverse edges to reverseAdjList.
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            // Check that word2 is not a prefix of word1.
            if (word1.length() > word2.length() && word1.startsWith(word2)) {
                return "";
            }
            // Find the first non match and insert the corresponding relation.
            for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
                if (word1.charAt(j) != word2.charAt(j)) {
                    reverseAdjList.get(word2.charAt(j)).add(word1.charAt(j));
                    break;
                }
            }
        }
        
        // Step 2: DFS to build up the output list.
        for (Character c : reverseAdjList.keySet()) {
            boolean result = dfs(c);
            if (!result) return "";
        }
        
        
        if (output.length() < reverseAdjList.size()) {
            return "";
        }
        return output.toString();
    }
    
    // Return true iff no cycles detected.
    private boolean dfs(Character c) {
        if (seen.containsKey(c)) {
            return seen.get(c); // If this node was grey (false), a cycle was detected.
        }
        seen.put(c, false);
        for (Character next : reverseAdjList.get(c)) {
            boolean result = dfs(next);
            if (!result) return false;
        }
        seen.put(c, true);
        output.append(c);
        return true;
    }    
}



/**
 * Breadth-First Search
 * 
 * so effortless...
 * 
 * 
 * 
 * @author Nelson Costa
 *
 */
class AlienDictionarySolution1{
    public String alienOrder(String[] words) {
    
    // Step 0: Create data structures and find all unique letters.
    Map<Character, List<Character>> adjList = new HashMap<>();
    Map<Character, Integer> counts = new HashMap<>();
    for (String word : words) {
        for (char c : word.toCharArray()) {
            counts.put(c, 0);
            adjList.put(c, new ArrayList<>());
        }
    }
    
    // Step 1: Find all edges.
    for (int i = 0; i < words.length - 1; i++) {
        String word1 = words[i];
        String word2 = words[i + 1];
        // Check that word2 is not a prefix of word1.
        if (word1.length() > word2.length() && word1.startsWith(word2)) {
            return "";
        }
        // Find the first non match and insert the corresponding relation.
        for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
            if (word1.charAt(j) != word2.charAt(j)) {
                adjList.get(word1.charAt(j)).add(word2.charAt(j));
                counts.put(word2.charAt(j), counts.get(word2.charAt(j)) + 1);
                break;
            }
        }
    }
    
    // Step 2: Breadth-first search.
    StringBuilder sb = new StringBuilder();
    Queue<Character> queue = new LinkedList<>();
    for (Character c : counts.keySet()) {
        if (counts.get(c).equals(0)) {
            queue.add(c);
        }
    }
    while (!queue.isEmpty()) {
        Character c = queue.remove();
        sb.append(c);
        for (Character next : adjList.get(c)) {
            counts.put(next, counts.get(next) - 1);
            if (counts.get(next).equals(0)) {
                queue.add(next);
            }
        }
    }
    
    if (sb.length() < counts.size()) {
        return "";
    }
    return sb.toString();
}
}
