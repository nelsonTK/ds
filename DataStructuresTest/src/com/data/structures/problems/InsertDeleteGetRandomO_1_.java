package com.data.structures.problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * https://leetcode.com/problems/insert-delete-getrandom-o1/submissions/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class InsertDeleteGetRandomO_1_ {

	public static void main(String[] args) {
		/**
		 * Your RandomizedSet object will be instantiated and called as such:
		 * RandomizedSet obj = new RandomizedSet();
		 * boolean param_1 = obj.insert(val);
		 * boolean param_2 = obj.remove(val);
		 * int param_3 = obj.getRandom();
		 */

	}

	/*
@fail
    1) didnt took care of the case where the set is 1. (because of Random.nextInt())
    2) forgot to switch the hashmap value of the swaped element
    3) situation on remove when removing was not coverage
    
    
    
	 */
	/**
	 * 
	 
	 
	 @score
	 	Runtime: 8 ms, faster than 84.34% of Java online submissions for Insert Delete GetRandom O(1).
		Memory Usage: 43.6 MB, less than 96.15% of Java online submissions for Insert Delete GetRandom O(1).
	 
	 @fail
	    1) didnt took care of the case where the set is 1. (because of Random.nextInt())
	    2) forgot to switch the hashmap value of the swaped element
	    3) situation on remove when removing was not coverage
	    
	 * @author Nelson Costa
	 *
	 */
	class RandomizedSet {

		List<Integer> set;
		HashMap<Integer, Integer> valToIndex;


		public RandomizedSet() {
			set = new ArrayList<>();
			valToIndex = new HashMap<>();
		}


		public boolean insert(int val) {
			if (!valToIndex.containsKey(val))
			{
				set.add(val);
				valToIndex.put(val, set.size() - 1);
				return true;
			}
			return false;
		}


		public boolean remove(int val) {

			if (valToIndex.containsKey(val))
			{
				if (set.size() == 1 || valToIndex.get(val) == set.size()  - 1)
				{
					set.remove(set.size() - 1);
					valToIndex.remove(val);
					return true;
				}

				int valIndex = valToIndex.get(val);              
				int lastValue = set.get(set.size() - 1);

				set.set(valIndex, lastValue);              
				set.remove(set.size() - 1);

				valToIndex.put(lastValue, valIndex);
				valToIndex.remove(val);
				return true;
			}
			return false;
		}


		public int getRandom() {

			if (set.size() == 0)
				return 1;

			if (set.size() == 1)
				return set.get(0);

			Random random = new Random();
			int index = random.nextInt(set.size());        
			return set.get(index);
		}
	}
}


/*********************
* OTHERS SOLUTIONS
*********************/
/**
 * Same concept than my solution
 * but executed with less code an with some more assumption. like getrandom never to be executed on empty data set
 * 
 * @author Nelson Costa
 *
 */
class RandomizedSetSolution1 {
	Map<Integer, Integer> dict;
	List<Integer> list;
	Random rand = new Random();

	/** Initialize your data structure here. */
	public RandomizedSetSolution1() {
		dict = new HashMap();
		list = new ArrayList();
	}

	/** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
	public boolean insert(int val) {
		if (dict.containsKey(val)) return false;

		dict.put(val, list.size());
		list.add(list.size(), val);
		return true;
	}

	/** Removes a value from the set. Returns true if the set contained the specified element. */
	public boolean remove(int val) {
		if (! dict.containsKey(val)) return false;

		// move the last element to the place idx of the element to delete
		int lastElement = list.get(list.size() - 1);
		int idx = dict.get(val);
		list.set(idx, lastElement);
		dict.put(lastElement, idx);
		// delete the last element
		list.remove(list.size() - 1);
		dict.remove(val);
		return true;
	}

	/** Get a random element from the set. */
	public int getRandom() {
		return list.get(rand.nextInt(list.size()));
	}
}
