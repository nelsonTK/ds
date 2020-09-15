package com.data.structures.problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/top-k-frequent-words/
 * MEDIUM
 * 
 * @author Nelson Costa
 *
 */
public class TopKFrequentWords extends LeetCodeExercise{

	public static void main(String[] args) {
		String [] words = {"i", "love", "leetcode", "i", "love", "coding"};
		int k = 3;
		TopKFrequentWords t = new TopKFrequentWords();
		printArray(words);
		printArray(t.topKFrequent(words, k).toArray());
		
		 
	}

	 /*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * 
	 * @score
	 * 		Runtime: 5 ms, faster than 87.85% of Java online submissions for Top K Frequent Words.
			Memory Usage: 39.9 MB, less than 30.36% of Java online submissions for Top K Frequent Words.
	 * 
	 * @intuition
	 * 		Hashmap to O(1) and counting frequencies
	 * 		Priority queue to sort
	 * 
	 * @optimizations
	 * 		I could make this O (log k) bye having a heap size of k. 
	 * 		in that case we would have the add operation to the max of log of k
	 * 
	 * 
	 * @time 	O (n log n)
	 * @space   O (n)
	 * @bcr		O (n)
	 * 
	 * @param words
	 * @param k
	 * @return
	 */
	public List<String> topKFrequent(String[] words, int k) {

		if (words == null)
			return new ArrayList<String>();
		
		HashMap<String, Integer> wordFrequency = new HashMap<>();

		for (String s : words) {
			wordFrequency.put(s, wordFrequency.getOrDefault(s, 0) + 1);
		}

		PriorityQueue<String> max = new PriorityQueue<String>(
				(a, b) -> wordFrequency.get(a).equals(wordFrequency.get(b)) ? 
						a.compareTo(b) : 
						Integer.compare(wordFrequency.get(b),  wordFrequency.get(a))
				);
		
		
		for (String s : wordFrequency.keySet())
		{
			max.offer(s);
		}
		
		List<String> ans = new ArrayList<String>();
		
		while (k > 0 && !max.isEmpty())
		{
			ans.add(max.poll());
			k--;
		}
		
		return ans;	
	}

}

/*********************
* OTHERS SOLUTIONS
*********************/
/**
 * This is n log k solution
 * because they never let heap get bigger than k
 * @author Nelson Costa
 *
 */
class TopKFrequentWordsSolution1 {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> count = new HashMap();
        for (String word: words) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }
        PriorityQueue<String> heap = new PriorityQueue<String>(
                (w1, w2) -> count.get(w1).equals(count.get(w2)) ?
                w2.compareTo(w1) : count.get(w1) - count.get(w2) );

        for (String word: count.keySet()) {
            heap.offer(word);
            if (heap.size() > k) heap.poll();
        }

        List<String> ans = new ArrayList();
        while (!heap.isEmpty()) ans.add(heap.poll());
        Collections.reverse(ans);
        return ans;
    }
}

/**
 * Number one solution from the community
 * With tries
 * Interesting
 * 
 * @author Nelson Costa
 *
 */
class TopKFrequentWordsSolution2 {
    class Trie {
        int cnt;
        int low;
        int high;
        String str;
        Trie[] children;
        Trie() {
        }
        
        Trie insert(String str, int lvl) {
            if (lvl >= str.length()) {
                if (cnt == 0) {
                    this.str = str;
                }
                cnt++;
                return this;
            }
            
            int idx = str.charAt(lvl) - 'a';
            if (children == null) {
                children = new Trie[26];
                low = high = idx;
            }
            
            if (children[idx] == null) {
                children[idx] = new Trie();
                if (idx < low) {
                    low = idx;
                } else if (idx > high) { 
                    high = idx;
                }
            }
            return children[idx].insert(str, lvl+1);
        }
        
        void traverse() {
            if (children != null) {
                for (int i = high; i >= low; i--) {
                    if (children[i] == null) {
                        continue;
                    }
                    children[i].traverse();
                }
            }

            if (cnt > 0) {
                ListTrie listTrie = new ListTrie(this);
                listTrie.next = listTries[cnt];
                listTries[cnt] = listTrie;
            }
        }
    }
    
    class ListTrie {
        Trie trie;
        ListTrie next;
        ListTrie(Trie trie) {
            this.trie = trie;
        }
    }
    
    ListTrie[] listTries;
    
    public List<String> topKFrequent(String[] words, int k) {
        
        Trie root = new Trie();
        int maxCnt = 0;
        
        for (int i = 0; i < words.length; i++) {        
            Trie trie = root.insert(words[i], 0);
            if (trie.cnt > maxCnt) {
                maxCnt = trie.cnt;
            }
        }
        
        listTries = new ListTrie[maxCnt+1];
        root.traverse();
        
        LinkedList<String> rslts = new LinkedList<>();
        int rest = k;
        for (int i = maxCnt; i >= 0 && rest > 0; i--) {
            ListTrie listTrie = listTries[i];
            while(rest > 0 && listTrie != null) {
                rslts.add(listTrie.trie.str);
                rest--;
                listTrie = listTrie.next;
            }
            
        }
        return rslts;
    }
}