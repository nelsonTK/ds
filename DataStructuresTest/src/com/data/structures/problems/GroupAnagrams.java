package com.data.structures.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/group-anagrams/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class GroupAnagrams extends LeetCodeExercise {

	static GroupAnagrams g = new GroupAnagrams();
	public static void main(String[] args) {

//		String [] a = {"eat", "tea", "tan", "ate", "nat", "bat"};
		String [] a = {"rag","orr","err","bad","foe","ivy","tho","gem","len","cat","ron","ump","nev","cam","pen","dis","rob","tex","sin","col","buy","say","big","wed","eco","bet","fog","buy","san","kid","lox","sen","ani","mac","eta","wis","pot","sid","dot","ham","gay","oar","sid","had","paw","sod","sop"};
//		printArray(a);
		int count = 0;
		for (List<String> list : g.groupAnagrams(a))
		{
			System.out.println(Arrays.toString(list.toArray()));
			System.out.println(count);
			count ++;
			
		}
	}
	
	/*********************************
	 * SOLUTION 1
	 ********************************/
	/**[WRONG]
	 * @fail
	 * 		1) outofbounds, because I add the length limited by the array and not individual words
	 * 		2) didn't add the rows to the final array
	 * 		3) The might have the same bits but in different order so my method to detect anangrams is not valid
	 * 
	 * @debug 
	 * 		yes
	 * 
	 * @time
	 * @space
	 * 
	 * @param a
	 * @return
	 */
	public List<List<String>> groupAnagrams0(String[] a) {

		if (a == null || a.length == 0)
			return new ArrayList<List<String>>();

		List<List<String>> ans = new ArrayList<List<String>>();
		List<String> row;

		boolean [] isAnagram = new boolean[a.length];
		for (int i = 0; i < a.length; i++)
		{
			if (!isAnagram[i])
			{

				row = new ArrayList<String>();
				row.add(a[i]);
				ans.add(row);

				for (int j = i + 1; j < a.length; j ++)
				{
					if (!isAnagram[j] && check(i, j, a))
					{
						isAnagram[j] = true;
						row.add(a[j]);
					}
				}
			}
		}

		return ans;
	}
	
	private boolean check(int i, int j, String [] a)
	{
		if (a[i].length() != a[j].length())
		{
			return false;
		}
		
		if (a[i].length() == 0 && a[j].length() == 0)
			return true;
		
		
		
		int ans = a[i].charAt(0) ^ a[j].charAt(0);
		
		for (int k = 1; k < a[i].length(); k++) {
			ans^= a[i].charAt(k) ^ a[j].charAt(k);
		}
		
		return ans == 0 ? true : false;
	}
	
	/*********************************
	 * SOLUTION 2
	 ********************************/	
	/**
	 * 
	/**
	 * Better Idea sorting strings 
	 * 
	   @intuition
	   		The goal is to "categorize" each word by its sorted version
	 * 
	 * 
	 * @score
			Runtime: 9 ms, faster than 59.87% of Java online submissions for Group Anagrams.
			Memory Usage: 43.3 MB, less than 71.34% of Java online submissions for Group Anagrams.
	 * 
	 * 
	 * @fail 
	 * 		1) didnt had the appropriat key to the hashmap
	 * 		2) I was sorting the array the wrong way
	 * 
	 * @time  
	 * 			[X] - O(N * Log k) N length of array, k length of string
	 * 			[V] - O(NKLogK)
	 * @space 	[X] - O(N)
	 * 			[V] - O(NK)
	 * @param a
	 * @return
	 */
	public List<List<String>> groupAnagrams1(String[] a) {

		if (a == null || a.length == 0)
			return new ArrayList<List<String>>();
		
		ArrayList<List<String>> ans = new ArrayList<List<String>>();
		
		HashMap<String, List<String>> map = new HashMap<>();
		List<String> list;
		
		for (int i = 0; i < a.length; i++)
		{
			char [] sortChar = a[i].toCharArray();
			Arrays.sort(sortChar);
			String key = new String(sortChar);
			list = map.getOrDefault(key, new ArrayList<String>());
			
			if (list.size() == 0)
			{
				list.add(a[i]);
				map.put(key, list);
			}
			else
			{
				list.add(a[i]);
			}
			sortChar.toString();
		}
		
		
		for (String key : map.keySet())
		{
			ans.add(map.get(key));
		}
		
		return ans;
	}
	
	/*********************************
	 * SOLUTION 3
	 ********************************/
	int [] prims = {2, 3, 5, 7, 11, 13, 17, 19, 23, 
					29, 31, 37, 41, 43, 47, 53, 59, 61,
					67, 71, 73, 79, 83, 89, 97, 101};
	/**
	 * @intuition
	 * 		the idea here is to take advantage of the limitation fo the 26 characteres by creating a unique code for hashing each string.
	 * 
	 * 
	 * @score
			Runtime: 4 ms, faster than 99.78% of Java online submissions for Group Anagrams.
			Memory Usage: 42.2 MB, less than 91.81% of Java online submissions for Group Anagrams.	
	 * 
	 * @fail
	 * 		1) prime multiplication is not well implemented, I was summing and multiplying instead of just multiplying
	 * 		2) I had a prime table starting with 1...
	 * 
	 * @time 	O(NK) 	K is string size
	 * @space	O(NK)	K is string size
	 * 
	 * @param a
	 * @return
	 */
	public List<List<String>> groupAnagrams(String[] a) {
		
		if (a == null || a.length == 0)
			return new ArrayList<List<String>>();
		
		ArrayList<List<String>> ans = new ArrayList<>();
		HashMap<Integer, List<String>> map = new HashMap<>();
		
		List<String> list;
		int code;
		for (String s : a) {
			
			code = getCode(s);
			list = map.getOrDefault(code, new ArrayList<String>());
			list.add(s);
			map.put(code, list);
		}
		
		return ans = new ArrayList<List<String>>(map.values());
	}

	//wrong hashfunction
	private int getCode0(String s) {
		int code = 0;
		
		for (char c : s.toCharArray()) {
			
			code += c * prims[c - 'a'];
			
		}
		
		return code;
	}
	private int getCode(String s) {
		int code = 1;
		
		for (char c : s.toCharArray()) {
			
			code *= prims[c - 'a'];
			
		}
		
		return code;
	}

	
}


/*********************
* OTHERS SOLUTIONS
*********************/
/**
 * This solution is not from leetcode but from the top resolution
 * 
 * It uses prime multiplication to have a unique number for each character
 * 
 * Very very cleaver solution to hash a sequence of strings
 * 
 * @author Nelson Costa
 *
 */
class GroupAnagramsSolution3 {
    static int [] primes = new int[]{2, 3, 5, 7 ,11, 13 ,17 ,19, 23, 29, 31, 37, 41, 43, 47, 53, 59,
                                  61, 67, 71, 73, 79, 83, 89, 97, 101
        
    };
    public List<List<String>> groupAnagrams(String[] strs) {
        
        HashMap<Long, List<String>> map = new HashMap<>();
        
        for(String s : strs){
            Long key = hashKey(s);          
            if(map.containsKey(key)){
                List<String> list = map.get(key);
                list.add(s);
            } else{
                List<String> list = new ArrayList();
                list.add(s);
                map.put(key, list);
            }
        }

        return new ArrayList<>(map.values());
    }
    
    private long hashKey(String s){
        long hash = 1;
        for(char c : s.toCharArray()){
            hash *= primes[c -'a'];
        }
        return hash;
    }
    
}

/**
 * Categorize by Code
 * 
 * This solution states that two strings are anagrams if their character counts are equals
 * and they use hashmap to save the charCounts of the solutions
 * 
 * @time 	O(NK)
 * @space 	O(NK)
 * @author Nelson Costa
 *
 */
class GroupAnagramsSolution2 {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) return new ArrayList();
        Map<String, List> ans = new HashMap<String, List>();
        int[] count = new int[26];
        for (String s : strs) {
            Arrays.fill(count, 0);
            for (char c : s.toCharArray()) count[c - 'a']++;

            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < 26; i++) {
                sb.append('#');
                sb.append(count[i]);
            }
            String key = sb.toString();
            if (!ans.containsKey(key)) ans.put(key, new ArrayList());
            ans.get(key).add(s);
        }
        return new ArrayList(ans.values());
    }
}

/**
 * Categorize by sorted string
 * 
 * Very interesting details like HashMap.values();
 * @author Nelson Costa
 *
 */
class GroupAnagramsSolution1 {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) return new ArrayList();
        Map<String, List> ans = new HashMap<String, List>();
        for (String s : strs) {
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String key = String.valueOf(ca);
            if (!ans.containsKey(key)) ans.put(key, new ArrayList());
            ans.get(key).add(s);
        }
        return new ArrayList(ans.values());
    }
}