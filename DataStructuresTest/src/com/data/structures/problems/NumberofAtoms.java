package com.data.structures.problems;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/number-of-atoms/
 * HARD
 * @author Nelson Costa
 *
 */
public class NumberofAtoms extends LeetCodeExercise{
	static NumberofAtoms n = new NumberofAtoms();

	public static void main(String[] args) {
		String s = "H2O";
		s = "Mg(OH)2";
		s = "K4(ON(SO3)2)2";
		System.out.println(n.countOfAtoms(s));

	}

	/**
	 * @intuition
	 * 		use a stack to solve this problem.
	 * 		
	 * 		[Elaborate more]
	 * 
	 * 
	 * 
	 * @score
	 * 		Runtime: 7 ms, faster than 47.77% of Java online submissions for Number of Atoms.
	 *		Memory Usage: 38.4 MB, less than 50.26% of Java online submissions for Number of Atoms.
	 * 
	 * @fail
	 * 		1) bad number and index match for converting quantity and atoms
	 * 		2) typoed the tostring for creating the big answer
	 * 		3) due o an early design decision of growing i inside the for loop it became complext to manage to deal with all index decrease and increasing
	 * 		an easier solution would be to do only one step per iteration
	 * 		4) hashmap part was using atom full string instead of just the atom name
	 * 
	 * @param s
	 * @return
	 */
	public String countOfAtoms(String s) {

		//return the count of each formula
		//sorted order atom by name
		//Atom starts with Capital
		//count of 1 do not count
		//parentheses...
		//multiply elements inside it my the number next to it
		//should return atoms
		//atoms have elemnent and count

		Atom curAtom;
		Stack<Atom> stack = new Stack<>();
		for (int i = 0; i < s.length(); i++){
			if (Character.isUpperCase(s.charAt(i)))
			{
				curAtom = new Atom(s.charAt(i));
				i = getAtomName(i + 1, s, curAtom);

				if(i < s.length() && Character.isDigit(s.charAt(i)))
					i = getQuantity(i, s, curAtom);
				else
					i--;

				System.out.println(curAtom.toString());
				stack.push(curAtom);
			}
			else if (s.charAt(i) == '(')
					{
				stack.push(null);                
					}
			else if (s.charAt(i) == ')')
			{
				int multiplier = 1;

				//get full number and update multiplier
				if (i < s.length() && Character.isDigit(s.charAt(i+1)))
				{
					curAtom = new Atom('d'); //d form dummy
					i = getQuantity(i + 1, s, curAtom);
					multiplier = curAtom.quantity;
				}

				ArrayDeque<Atom> list = new ArrayDeque<>();
				//multiply between parentheses
				while (!stack.isEmpty() && stack.peek() != null)
				{
					curAtom = stack.pop();
					curAtom.quantity *= multiplier;
					list.addFirst(curAtom);
				}

				stack.pop(); //poping null

				//put elements back in the stack

				while (!list.isEmpty())
				{
					stack.push(list.remove());
				}
			}
		}


		//get count per atom
		HashMap<String, Atom> stringToAtom = new HashMap<>();
		String key;
		while(!stack.isEmpty())
		{
			curAtom = stack.pop();
			key = curAtom.name.toString();
			if (stringToAtom.containsKey(key))
			{
				stringToAtom.get(key).quantity += curAtom.quantity;
			}
			else
			{
				stringToAtom.put(key, curAtom);
			}
		}

		List<Atom> atoms = new ArrayList<>();

		for (String k : stringToAtom.keySet())
		{
			atoms.add(stringToAtom.get(k));
		}

		StringBuilder ans = new StringBuilder("");
		Collections.sort(atoms,
				(
						(a, b) -> 
						{
							int compare = a.name.compareTo(b.name);
							if (compare == 0)
							{
								return Integer.compare(a.quantity, b.quantity);
							}
							else{
								return compare;
							}
						}
						));

		for (Atom a : atoms)
		{
			ans.append(a.toString());
		}

		return ans.toString();

		/*
	        for each char
	            processAtom
	                getletter part
	                getnumber part
	                add to stack

	            if parentheses open put in stack
	                and move forward

	            if parentheses closed check next element, if num, get num and multiply, else not num, so multiply elements by one, and stop in next
		 */
	}


	//atom should be filled with one char
	//index should be in the next position already
	private int getAtomName(int index, String s, Atom a)
	{
		if(index < s.length() && !Character.isLowerCase(s.charAt(index)))
			return index;

		while (index < s.length() && Character.isLowerCase(s.charAt(index)) )
		{
			a.name.append(s.charAt(index));
			index++;
		}

		return index;
	}

	private int getQuantity(int index, String s, Atom a)
	{
		if  (index < s.length() && !Character.isDigit(s.charAt(index)))
		{
			return index;
		}
		int curNum = 0;

		while (index < s.length() && Character.isDigit(s.charAt(index)))
		{
			curNum = curNum * 10 + s.charAt(index) - '0';
			index++;
		}

		a.quantity = curNum;

		return index - 1;
	}

	class Atom
	{
		int quantity;
		StringBuilder name;
		Atom (char name)
		{
			this.name = new StringBuilder("");
			this.name.append(name);
			quantity = 1;
		}

		public String toString(){
			return name.toString() + (quantity == 1 ? "" : "" + quantity);
		}
	}
}
