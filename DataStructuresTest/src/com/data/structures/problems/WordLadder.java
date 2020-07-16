package com.data.structures.problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;


/**
 * https://leetcode.com/problems/word-ladder/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class WordLadder {



	/*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * 
	 * @intuition
	 * 		what we do in here is create a graph from the words, and set the connections between them.
	 * 		than performe dijkstra algorithm to find the shortest path.
	 * 
	 * 
	 * @score
	 * 		Runtime: 2493 ms, faster than 5.01% of Java online submissions for Word Ladder.
	 *		Memory Usage: 41.4 MB, less than 59.76% of Java online submissions for Word Ladder.
	 * 
    	@time 	O(W^2*L)
    	@space 	O(V + E)

	 * @param beginWord
	 * @param endWord
	 * @param wordList
	 * @return
	 */
	public int ladderLength(String beginWord, String endWord, List<String> wordList) {

		//aditional guards

		//verify if endword is in the list
		boolean endExists = false;
		for(String s : wordList)    // O(W*L) (W)ord number (L)ength of string
		{
			if (s.equals(endWord))
				endExists = true;
		}

		if (!endExists)
			return 0;


		//create graph based on edit distance
		HashMap<String, List<String>> map = new HashMap<>();
		map.put(beginWord, new ArrayList<String>());

		for (String word : wordList) //O(W)
		{
			map.put(word, new ArrayList<String>());
		}

		String word1;
		String word2;

		//O(W^2 * L)
		for (String key : map.keySet()) // O(W)
		{          
			word1 = key;
			for (String key2 : map.keySet()) // O(W)
			{                
				word2 = key2;

				if (!word1.equals(word2) && validDistance(word1, word2)) //O(L)
				{
					map.computeIfAbsent(word1, k-> new ArrayList<String>()).add(word2);
				}
			}
		}

		// if start has no neigbors end
		if (map.get(beginWord).size() == 0)
			return 0;

		//perform breadth first search starting from first

		PriorityQueue<Pair> pq = new PriorityQueue<>
		((a, b) -> Integer.compare(a.cost, b.cost));

		pq.add(new Pair(beginWord, 1));

		Pair node;
		Set<String> visited = new HashSet<>();

		while (!pq.isEmpty())
		{
			node = pq.poll();

			if (node.name.equals(endWord))
				return node.cost;

			for (String nei : map.get(node.name)) // O(E*Log V)
			{
				if (!visited.contains(nei))
				{
					pq.offer(new Pair(nei, node.cost + 1));
				}
			}


			visited.add(node.name);
		}


		return 0;
	}


	private boolean validDistance(String a, String b)
	{
		int distance = 0;
		for (int i = 0; i < a.length(); i++)
		{
			if(a.charAt(i) != b.charAt(i))
				distance++;
		}

		return distance == 1 ? true : false;
	}

	class Pair {
		String name;
		int cost;

		public Pair (String name, int cost){
			this.name = name;
			this.cost = cost;
		}

	}

}


/*********************
* OTHERS SOLUTIONS
*********************/
/**
 * solution with bidirectional breadth first search
 * 
 * 		interesting solution with bidirectional breadth first search, fist time I see one.
 * 		
 * 		1) reate pattern - words graph
 * 
 * 		2) initiate bi directional breadth first search
 * 			2.1) the stop condition is when you find a node the same node in both searches, it means we found a path.
 * 
 * 
 * @score
 * 		Runtime: 27 ms, faster than 89.96% of Java online submissions for Word Ladder.
		Memory Usage: 45.9 MB, less than 33.67% of Java online submissions for Word Ladder.
 * 
 * @author Nelson Costa
 *
 */
class WordLadderSolution2 {

	private int L;
	private Map<String, List<String>> allComboDict;

	WordLadderSolution2() {
		this.L = 0;

		// Dictionary to hold combination of words that can be formed,
		// from any given word. By changing one letter at a time.
		this.allComboDict = new HashMap<>();
	}

	private int visitWordNode(
			Queue<Pair<String, Integer>> Q,
			Map<String, Integer> visited,
			Map<String, Integer> othersVisited) {

		Pair<String, Integer> node = Q.remove();
		String word = node.getKey();
		int level = node.getValue();

		for (int i = 0; i < this.L; i++) {

			// Intermediate words for current word
			String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);

			// Next states are all the words which share the same intermediate state.
			for (String adjacentWord : this.allComboDict.getOrDefault(newWord, new ArrayList<>())) {
				// If at any point if we find what we are looking for
				// i.e. the end word - we can return with the answer.
				if (othersVisited.containsKey(adjacentWord)) {
					return level + othersVisited.get(adjacentWord);
				}

				if (!visited.containsKey(adjacentWord)) {

					// Save the level as the value of the dictionary, to save number of hops.
					visited.put(adjacentWord, level + 1);
					Q.add(new Pair(adjacentWord, level + 1));
				}
			}
		}
		return -1;
	}

	public int ladderLength(String beginWord, String endWord, List<String> wordList) {

		if (!wordList.contains(endWord)) {
			return 0;
		}

		// Since all words are of same length.
		this.L = beginWord.length();

		wordList.forEach(
				word -> {
					for (int i = 0; i < L; i++) {
						// Key is the generic word
						// Value is a list of words which have the same intermediate generic word.
						String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
						List<String> transformations =
								this.allComboDict.getOrDefault(newWord, new ArrayList<>());
						transformations.add(word);
						this.allComboDict.put(newWord, transformations);
					}
				});

		// Queues for birdirectional BFS
		// BFS starting from beginWord
		Queue<Pair<String, Integer>> Q_begin = new LinkedList<>();
		// BFS starting from endWord
		Queue<Pair<String, Integer>> Q_end = new LinkedList<>();
		Q_begin.add(new Pair(beginWord, 1));
		Q_end.add(new Pair(endWord, 1));

		// Visited to make sure we don't repeat processing same word.
		Map<String, Integer> visitedBegin = new HashMap<>();
		Map<String, Integer> visitedEnd = new HashMap<>();
		visitedBegin.put(beginWord, 1);
		visitedEnd.put(endWord, 1);

		while (!Q_begin.isEmpty() && !Q_end.isEmpty()) {

			// One hop from begin word
			int ans = visitWordNode(Q_begin, visitedBegin, visitedEnd);
			if (ans > -1) {
				return ans;
			}

			// One hop from end word
			ans = visitWordNode(Q_end, visitedEnd, visitedBegin);
			if (ans > -1) {
				return ans;
			}
		}

		return 0;
	}
}


/**
 * Breadth first search approach
 * 
 * supercleaver approach to avoid edit distance comparison
 * 
 * they use word patterns. and each words is connected to it's pattern. 
 * 
 * if two words are connected to the same pattern then they are adjacent
 * 
 * 1) we create a graph with the patterns and words that link to a patter as edges
 * 
 * 2) breadth first search with pairs(word,cost)
 * 
 * 		2.1) when element is found it is added to the queue
 * 
 * 
 * @score
 * 		Runtime: 41 ms, faster than 85.09% of Java online submissions for Word Ladder.
		Memory Usage: 46.6 MB, less than 32.01% of Java online submissions for Word Ladder.
 * 
 * @author Nelson Costa
 *
 */
class WordLadderSolution1 {
	public int ladderLength(String beginWord, String endWord, List<String> wordList) {

		// Since all words are of same length.
		int L = beginWord.length();

		// Dictionary to hold combination of words that can be formed,
		// from any given word. By changing one letter at a time.
		Map<String, List<String>> allComboDict = new HashMap<>();

		wordList.forEach(
				word -> {
					for (int i = 0; i < L; i++) {
						// Key is the generic word
						// Value is a list of words which have the same intermediate generic word.
						String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
						List<String> transformations = allComboDict.getOrDefault(newWord, new ArrayList<>());
						transformations.add(word);
						allComboDict.put(newWord, transformations);
					}
				});

		// Queue for BFS
		Queue<Pair<String, Integer>> Q = new LinkedList<>();
		Q.add(new Pair(beginWord, 1));

		// Visited to make sure we don't repeat processing same word.
		Map<String, Boolean> visited = new HashMap<>();
		visited.put(beginWord, true);

		while (!Q.isEmpty()) {
			Pair<String, Integer> node = Q.remove();
			String word = node.getKey();
			int level = node.getValue();
			for (int i = 0; i < L; i++) {

				// Intermediate words for current word
				String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);

				// Next states are all the words which share the same intermediate state.
				for (String adjacentWord : allComboDict.getOrDefault(newWord, new ArrayList<>())) {
					// If at any point if we find what we are looking for
					// i.e. the end word - we can return with the answer.
					if (adjacentWord.equals(endWord)) {
						return level + 1;
					}
					// Otherwise, add it to the BFS Queue. Also mark it visited
					if (!visited.containsKey(adjacentWord)) {
						visited.put(adjacentWord, true);
						Q.add(new Pair(adjacentWord, level + 1));
					}
				}
			}
		}

		return 0;
	}

}


class Pair<K, V> {

	private final K key;
	private final V element1;

	public static <K, V> Pair<K, V> createPair(K element0, V element1) {
		return new Pair<K, V>(element0, element1);
	}

	public Pair(K element0, V element1) {
		this.key = element0;
		this.element1 = element1;
	}

	public K getKey() {
		return key;
	}

	public V getValue() {
		return element1;
	}

}