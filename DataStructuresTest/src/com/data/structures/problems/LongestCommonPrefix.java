package com.data.structures.problems;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/longest-common-prefix/
 * EASY
 * @author Nelson Costa
 *
 */
public class LongestCommonPrefix {

	public static void main(String[] args) {

	}
	
	
    int size = 0; String ans = "";

	 /*********************************
	 * SOLUTION 1
	 ********************************/
    /**
     * @intuition
     * 		It is just a simple solution with trie, it was made at maximum speed so I had not much time to think about other possibilities
     * 		to improve the performance, namely on how to improve always rewriting ans when you are building the first word of the current answer is just increasing
     * 	
     * 
     * @score
     * 		Runtime: 3 ms, faster than 34.69% of Java online submissions for Longest Common Prefix.
	 *		Memory Usage: 39.2 MB, less than 38.41% of Java online submissions for Longest Common Prefix.
     * 
     * @time
     * 
     * @space
     * 
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        size = strs.length;
        
        //fill the tree
        T trie = new T();
        for (String s : strs)
        {
            trie.addString(s);
        }
        
        //find max value
        findMax(trie.root, new StringBuilder(""));
        
        return ans;
    }
    
    public void findMax(TN node, StringBuilder cur)
    {
        
        if (cur.length() > ans.length())
        {
            ans = cur.toString();
        }
        
        TN children;
        for (Character c : node.children.keySet())
        {
            children = node.get(c);
            if (children.freq == size)
            {
                cur.append(children.val);
                findMax(children, cur);
                cur.setLength(cur.length() - 1);
            }
        }
    }
    
    class T
    {
        TN root;
        
        public T(){
          root = new TN(Character.MIN_VALUE);  
        }
        
        
        public void addString(String s){
            
            //if exists increase count 
            //else just add
            TN node = root;
            TN children;
            for (char c : s.toCharArray())
            {
                children = node.get(c);
                
                if(children == null)
                {
                    node = node.add(new TN(c));
                }
                else
                {
                    children.freq++;
                    node = children;
                }
            }
            
        }
    }
    
    
    class TN{
        char val;
        int freq;
        HashMap<Character, TN> children;
        
        public TN(char c)
        {
            val = c;
            freq = 1;
            children = new HashMap<>();
        }
        
        public TN add(TN c)
        {
            children.put(c.val, c);
            return c;
        }
        
        public TN get(char c)
        {
            return children.get(c);
        }
    }
}
