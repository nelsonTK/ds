package com.data.structures.problems.contest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * https://leetcode.com/problems/throne-inheritance/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class w208ThroneInheritance {

	/*********************************
	 * SOLUTION 2
	 ********************************/
	/**
	 * @intuition
	 * 		just a backtracking solution
	 * 		we have a hashmap with each candidate and the corresponding children.
	 * 		a new children is always added to the end of the list of the parent
	 * 		the inheritance is returned by starting in the king and traverse all the graph
	 * 		
	 * @optimizations
	 * 		I think it is possible to have a better solution
	 * 		with linkedlist but I was missing some rules
	 * 
	 * @score
	 * 		Runtime: 233 ms, faster than 100.00% of Java online submissions for Throne Inheritance.
	 * 		Memory Usage: 98.6 MB, less than 60.00% of Java online submissions for Throne Inheritance.
	 * 
	 * @time
	 * 		birth
	 * 			O(1)
	 * 
	 *		death
	 *			O(1)
	 *
	 *		getInheritanceOrder
	 *			O(N)
	 * 
	 * @space
	 * 		O(1)
	 * 		getOrder()
	 * 		O(N)
	 * 	
	 **/

	class ThroneInheritance {

		HashMap<String, Candidate> map;
		String king;
		public ThroneInheritance(String kingName)
		{
			map = new HashMap<>();
			map.put(kingName, new Candidate());
			king = kingName;

		}

		public void birth(String parentName, String childName)
		{
			Candidate parent = map.get(parentName);
			parent.children.add(childName);

			map.put(childName, new Candidate());
		}

		public void death(String name) 
		{
			Candidate node = map.get(name);
			node.isAlive = false;
		}

		public List<String> getInheritanceOrder() 
		{
			List<String> ans = new ArrayList<>();
			solve(king, ans);
			return ans;
		}

		public void solve(String str, List<String> ans)
		{
			Candidate node = map.get(str);

			if (node.isAlive)
			{
				ans.add(str);
			}

			for (String child : node.children)
			{
				solve(child, ans);
			}

		}


		class Candidate
		{
			boolean isAlive;
			List<String> children;

			Candidate()
			{
				children = new ArrayList<String>();
				isAlive = true;
			}

			public void add(String child){
				children.add(child);
			}
		}

	}
}


/**
First Implementation Try with Linkedist.


 * mistake on removal? yeah, I forgot to remove the node on delete, for the normal case.
 * forgot to add a previous element to the node that is input in putNext method
 * Problem identified when the last element is removed from a parent, I was not checking if it was the last element of the parent
 * big mistake I think, when I add something, I need to add recursivelly, this solution might have been destroyed, the birth is wrong
 * The last mistake was that I was adding the children to the wrong parent
 * Big mistake in the approach


 L order;
 HashMap<String, Pair> map;//Parentchildren;

 //theoretically solved it in 29 minutes
 public ThroneInheritance(String kingName) {
     //adds king entry to hashmap
     //can we add a birth to a death person? 
     order = new L();
     map = new HashMap<>();

     LN king = new LN(kingName, null);
     map.put(kingName, new Pair(king, king));
     order.head.putNext(king);
 }

 public void birth(String parentName, String childName) {
     Pair legacy = map.get(parentName);

     if (legacy == null) {
         return;
     }

     //update parent legacy
     LN next = new LN(childName, legacy.self);
     Pair target = legacy;
     //System.out.print("|| " + target.self.parent + " > " + target.self + " > " + target.next );
     while ( target.self != target.next )
     {
         target = map.get(target.next.val);

         //System.out.print(target.self.val + " > ");
     }

     target.next.putNext(next);
     legacy.next = next;
     //System.out.println(">" + target.next);
     //add child legacy
     map.put(childName, new Pair(next, next));
 }

 public void death(String name) {

     //die twice? (edgecase)

     Pair curLegacy = map.get(name);


     Pair parent;
     if (curLegacy.self.parent != null)
        parent = map.get(curLegacy.self.parent.val);
     else
         parent = null;

     if (curLegacy == null) 
     {
         System.out.println("fail");
         return;
     }

     //edgecase if the first is equals to head.next, we should update the first element in the order list
     if (curLegacy.self == order.head.next){
         LN tmp = order.head.next.next;  

         //parent = map.get(legacy.parent);

         //if I have a parent and I'm the last children alive of my father and I'm gonna die, then the last children alive needs to be updated
         if (curLegacy.self.parent != null && parent.next == curLegacy.self)
         {
             //if the previous is my father or my brother
             //if(legacy.self.prev == parent || legacy.self.prev.parent == parent.self)
             parent.next = curLegacy.self.prev;
         }

         curLegacy.self.remove();

         order.head.next = tmp;
     }
     else
     {

     //System.out.println("curLegacy.self.parent > " + curLegacy.self.val + " > " + curLegacy.self.parent.val + " > " + parent);
         //if (parent != null && curLegacy.self.parent != null && parent.next == curLegacy.self)
         if (curLegacy.self.parent != null && parent.next == curLegacy.self)
         {
             //if the previous is my father or my brother
             //if(legacy.self.prev == parent || legacy.self.prev.parent == parent.self)
             parent.next = curLegacy.self.prev;
         }

         curLegacy.self.remove();
     }
 }

 //O(N)
 public List<String> getInheritanceOrder() {
     //attention to dead
     List<String> ans = new ArrayList<>();

     LN node = order.head.next;
     while (!node.val.equals(""))
     {
         ans.add(node.val);
         node = node.next;
     }

     //System.out.println("x");

     return ans;
 }


}

class Pair{
 LN self;
 LN next;

 Pair (LN myself, LN myLastNode)
 {
     self = myself;
     next = myLastNode;
 }
}

class L
{
 LN head;
 LN tail;

 L()
 {
     head = new LN("", null);
     tail = new LN("", null);
     head.next = tail;
     tail.prev = head;
 }
}
class LN
{
 String val;
 LN next;
 LN prev;
 LN parent;

 LN(String v, LN p)
 {
     val = v;
     parent = p;
 }

 void putNext(LN node)
 {
     LN temp = this.next;    //save curent.next
     this.next = node;       //set curent.next to arg node
     node.next = temp;       //set input node's next toprevious current's next
     temp.prev = node;
     node.prev = this;
 }

 void remove()
 {
     //System.out.println("Removing " + this.val);
     prev.next = next;
     next.prev = prev;
     //System.out.println("Removed " + this.val);
 }
 @Override
 public String toString(){
     return val;
 }
}

king 1
    logan 2 (3 x)
    hosea 4, 7 X
        leonard 5
            betty 9
        carl 6
            helen 10
                luella 12, 14 X
            herman 17
    ronda 8, 18X
        alfred 11
        lisa 13
            kary 15
            angela 16


 **/