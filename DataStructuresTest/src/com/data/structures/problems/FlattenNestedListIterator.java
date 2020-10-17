package com.data.structures.problems;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import com.data.structures.problems.FlattenNestedListIterator.NestedInteger;

/**
 * https://leetcode.com/problems/flatten-nested-list-iterator/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class FlattenNestedListIterator {


	 /*********************************
	 * SOLUTION 1
	 ********************************/

	// This is the interface that allows for creating nested lists.
	// You should not implement it, or speculate about its implementation
	public interface NestedInteger {

		// @return true if this NestedInteger holds a single integer, rather than a nested list.
		public boolean isInteger();

		// @return the single integer that this NestedInteger holds, if it holds a single integer
		// Return null if this NestedInteger holds a nested list
		public Integer getInteger();

		// @return the nested list that this NestedInteger holds, if it holds a nested list
		// Return null if this NestedInteger holds a single integer
		public List<NestedInteger> getList();
	}

	public class NestedIterator implements Iterator<Integer> {

		LN flat;
		N current;

		public NestedIterator(List<NestedInteger> nestedList) {
			flat = new LN();
			for (NestedInteger n : nestedList)
				flat(n, flat);

			current = flat.root;
		} 

		/**
		 * @intuition
		 * 		The intuition is to use a linked list to have all my elements flatten and then I just save the current element
		 * 
		 * 
		 * @score
		 * 		Runtime: 2 ms, faster than 98.53% of Java online submissions for Flatten Nested List Iterator.
		 *		Memory Usage: 41.4 MB, less than 17.86% of Java online submissions for Flatten Nested List Iterator.
		 * 
		 * @time
		 * 		constructor O(N)
		 * 		next O(1)
		 * 		hasnext O(1)
		 * 
		 * @space
		 * 		constructor O(N)
		 * 		next O(1)
		 * 		hasnext O(1)
		 * 
		 * @param nested
		 * @param flat
		 */
		private void flat(NestedInteger nested, LN flat)
		{
			if (nested.isInteger())
			{
				flat.add(nested.getInteger());
			}
			else
			{
				for (NestedInteger child : nested.getList())
				{
					flat(child, flat);
				}
			}
		}

		@Override
		public Integer next() {

			if (current.next != null)
			{
				Integer next = current.next.val;
				current = current.next;
				return next;
			}

			return null;
		}

		@Override
		public boolean hasNext() {
			return current.next != null? true : false;
		}


		class LN
		{
			N root;
			N last;
			int size;

			LN()
			{
				root = new N(-1);
				size = 0;
			}

			public void add(int val)
			{
				N child = new N(val);

				if (size == 0)
				{
					root.next = child;
					last = child;
				}
				else
				{
					last.next = child;
					last = child;
				}

				size++;          
			}

		}

		class N
		{
			Integer val;
			N next;

			N(int val)
			{
				this.val = val;
			}
		}
	}
}

/**
 * This is the next level solution
 * 
 * It uses ListIterator to traverse the stack, is the best design approach.
 * 
 * It is possible because NestedInteger implements Iterator.
 * 
 * The gist is to maintain a stack of list of iterators and do next when the next element is an integer we return it.
 * 
 * When it is not we add the the element to the top of the stack and continue searching.
 * 
 * We should take care about empty lists.
 * 
 * This is a marvelous approach.
 * 
 * @author Nelson Costa
 *
 */
class NestedIteratorSolution4 implements Iterator<Integer> {
    
    // This time, our stack will hold list iterators instead of just lists.
    private Deque<ListIterator<NestedInteger>> stackOfIterators = new ArrayDeque();
    private Integer peeked = null;

    public NestedIteratorSolution4(List<NestedInteger> nestedList) {
        // Make an iterator with the input and put it on the stack. 
        // Note that creating a list iterator is an O(1) operation.
        stackOfIterators.addFirst(nestedList.listIterator());
    }

    private void setPeeked() {
        
        // If peeked is already set, there's nothing to do.
        if (peeked != null) return;
        
        while (!stackOfIterators.isEmpty()) {
            
            // If the iterator at the top of the stack doesn't have a next,
            // remove that iterator and continue on.
            if (!stackOfIterators.peekFirst().hasNext()) {
                stackOfIterators.removeFirst();
                continue;
            }
            
            // Otherwise, we need to check whether that next is a list or 
            // an integer.
            NestedInteger next = stackOfIterators.peekFirst().next();
            
            // If it's an integer, set peeked to it and return as we're done.
            if (next.isInteger()) {
                peeked = next.getInteger();
                return;
            }
            
            // Otherwise, it's a list. Create a new iterator with it, and put
            // the new iterator on the top of the stack.
            stackOfIterators.addFirst(next.getList().listIterator());
        }        
    }
    

    @Override
    public Integer next() {
        
        // As per Java specs, throw an exception if there are no further elements.
        if (!hasNext()) throw new NoSuchElementException();
        
        // hasNext() called setPeeked(), which ensures peeked has the next integer 
        // in it. We need to clear the peeked field so that the element is returned
        // again.
        Integer result = peeked;
        peeked = null;
        return result;
    }

    @Override
    public boolean hasNext() {

        // Try to set the peeked field. If any integers are remaining, it will
        // contain the next one to be returned after this call.
        setPeeked();
        
        // There are elements remaining iff peeked contains a value.
        return peeked != null;
    }
}



/**
 * The Gist of this solution was something I tried to think but thought it was too expensi and move for something simpler
 * this solution uses two stacks, on to maintain the indexes, and other to maintain the lists.
 * 
 * So you start with a nestinteger in one stack, and the index zero in the other stack.
 * 
 * you add the first nested element in the position zero to the nested integer stack. 
 * 
 * this means that you have consumed the first element in the list so you increase the index from zero to 1
 * 
 * you also add a new index for the first element in the list in the second stack. and the index is zero.
 * 
 * if it is a integer you add to the list, and increase the index, if it is not and index we repeat the same process then before.
 * 
 * we increase the current list index, and add the list to the stack, and add a new index in the index stack, 
 * 
 * when we consume all values we pop from both stacks/queues
 * 
 * Great execution
 * 
 * 
 * @author Nelson Costa
 *
 */
class NestedIteratorSolution3 implements Iterator<Integer> {

    private Deque<List<NestedInteger>> listStack = new ArrayDeque<>();
    private Deque<Integer> indexStack = new ArrayDeque<>();
    
    public NestedIteratorSolution3(List<NestedInteger> nestedList) {
        listStack.addFirst(nestedList);
        indexStack.addFirst(0);
    }
        
    @Override
    public Integer next() {
        if (!hasNext()) throw new NoSuchElementException();
        int currentPosition = indexStack.removeFirst();
        indexStack.addFirst(currentPosition + 1);
        return listStack.peekFirst().get(currentPosition).getInteger();
    }

    
    @Override
    public boolean hasNext() {
        makeStackTopAnInteger();
        return !indexStack.isEmpty();
    }


    private void makeStackTopAnInteger() {
    
        while (!indexStack.isEmpty()) {
                        
            // If the top list is used up, pop it and its index.
            if (indexStack.peekFirst() >= listStack.peekFirst().size()) {
                indexStack.removeFirst();
                listStack.removeFirst();
                continue;
            }

            // Otherwise, if it's already an integer, we don't need to do anything.
            if (listStack.peekFirst().get(indexStack.peekFirst()).isInteger()) {
                break;
            }

            // Otherwise, it must be a list. We need to update the previous index
            // and then add the new list with an index of 0.
            listStack.addFirst(listStack.peekFirst().get(indexStack.peekFirst()).getList());
            indexStack.addFirst(indexStack.removeFirst() + 1);
            indexStack.addFirst(0);
        }
    }
}

/**
 * Interesting solution that processes element by element with a queue.
 * and always remove the first.
 * if the first happens to be a list me put all of its elements again in the front of the stack but we need to be carefull to maintain the order.
 * @author Nelson Costa
 *
 */
class NestedIteratorSolution2 implements Iterator<Integer> {

    // In Java, the Stack class is considered deprecated. Best practice is to use
    // a Deque instead. We'll use addFirst() for push, and removeFirst() for pop.
    private Deque<NestedInteger> stack;
    
    public NestedIteratorSolution2(List<NestedInteger> nestedList) {
        // The constructor puts them on in the order we require. No need to reverse.
        stack = new ArrayDeque(nestedList);
    }
        
    
    @Override
    public Integer next() {
        // As per java specs, throw an exception if there's no elements left.
        if (!hasNext()) throw new NoSuchElementException();
        // hasNext ensures the stack top is now an integer. Pop and return
        // this integer.
        return stack.removeFirst().getInteger();
    }

    
    @Override
    public boolean hasNext() {
        // Check if there are integers left by getting one onto the top of stack.
        makeStackTopAnInteger();
        // If there are any integers remaining, one will be on the top of the stack,
        // and therefore the stack can't possibly be empty.
        return !stack.isEmpty();
    }


    private void makeStackTopAnInteger() {
        // While there are items remaining on the stack and the front of 
        // stack is a list (i.e. not integer), keep unpacking.
        while (!stack.isEmpty() && !stack.peekFirst().isInteger()) {
            // Put the NestedIntegers onto the stack in reverse order.
            List<NestedInteger> nestedList = stack.removeFirst().getList();
            for (int i = nestedList.size() - 1; i >= 0; i--) {
                stack.addFirst(nestedList.get(i));
            }
        }
    }
}

/**
 * Same approach than mine but ridiculously more simple because they used a List
 * @author Nelson Costa
 *
 */
class NestedIteratorSolution1 implements Iterator<Integer> {
    
    private List<Integer> integers = new ArrayList<Integer>();
    private int position = 0; // Pointer to next integer to return.
    
    public NestedIteratorSolution1(List<NestedInteger> nestedList) {
        flattenList(nestedList);
    }

    // Recursively unpacks a nested list in DFS order.
    private void flattenList(List<NestedInteger> nestedList) {
        for (NestedInteger nestedInteger : nestedList) {
            if (nestedInteger.isInteger()) {
                integers.add(nestedInteger.getInteger());
            } else {
                flattenList(nestedInteger.getList());
            }
        }
    }
    
    @Override
    public Integer next() {
        // As per Java specs, we should throw an exception if no more ints.
        if (!hasNext()) throw new NoSuchElementException();
        // Return int at current position, and then *after*, increment position.
        return integers.get(position++);
    }

    @Override
    public boolean hasNext() {
        return position < integers.size();
    }
}