package com.data.structures.problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/online-stock-span/submissions/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class OnlineStockSpan {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		StockSpanner S = new StockSpanner();
		S.next(100);
		S.next(80);
		S.next(60);
		S.next(70);
		S.next(60);
		S.next(75);
		S.next(85);
	}

	/**
	 * [WRONG]
	 * 
	 * @intuition
	 * 		Tried to do a O(1) Solution, by adding elements only when they where bigger than previous elements
	 * 		But it had a lot of cases that were not covered.
	 * 		Used more data structures than needed.
	 * 
	 * @author Nelson Costa
	 *
	 */
	static class StockSpanner0 {

	    List<Integer> prev;
	    List<Integer> span;
	    Stack<Triplet> stack;
	    
	    public StockSpanner0() {
	        stack = new Stack<>();
	        span = new ArrayList<>();
	        prev = new ArrayList<>();
	    }
	    
	    /** @fail 1) 
	    **/
	    public int next(int price) {
	        
	        if (span.size() == 0)
	        {
	            span.add(1);
	            stack.push(new Triplet(price, 0, 1));
	            prev.add(price);
	            return 1;
	        }
	        
	        if (price >= prev.get(prev.size()-1))
	        {
	            //compare with the top of the stack
	            //if bigger than top of the stack add to current
	            //    else add the different between
	            if (price >= stack.peek().val){
	                
	                Triplet peek = stack.peek();
	                Triplet t = new Triplet(price, prev.size(), peek.span + (prev.size() - peek.index - 1)+1);
	                stack.push(t);
	                span.add(t.span);
	                prev.add(price); 
	            }
	            else
	            {
	                Triplet t = new Triplet(price, prev.size(), (prev.size() - stack.peek().index - 1) + 1);
	                span.add(t.span);
	                prev.add(price); 
	            }
	                    
	        }
	        else
	        {
	            span.add(1);
	            prev.add(price);
	        }
	        
	        return span.get(span.size() - 1);
	    }
	    
	    class Triplet{
	        int val;
	        int index;
	        int span;
	        
	        public Triplet(int v, int i, int s){
	            val = v;
	            index = i;
	            span = s;
	        }
	        
	    }
	}

	 /** 
     *  @intuition
     *  	The intuition applied in this solution is to use a stack to hold the price and index of that element.
     *  	when we ave a value which is biggert than the top of the stack then we we move all the elements until we have a value bigger.
     *  	Then we stop
     *  
     * 
     * 
     *  @score
     *  	Runtime: 28 ms, faster than 69.25% of Java online submissions for Online Stock Span.
	 *		Memory Usage: 48.1 MB, less than 81.74% of Java online submissions for Online Stock Span.
     * 
     * 	@optimizations
     * 		I could have done better if just accomulated the values of the previous..
     * 
     * 	@fail   
     * 		1) failed for the case where the input is always increasing
     * 
     * 	@time
     * 		O(N)
     * 
     * 	@space
     * 		O(N)
    **/
	static class StockSpanner {

	    Stack<int[]> stack;
	    int days;
	    public StockSpanner() {
	        stack  = new Stack<>();
	        days = -1;
	    }
	    
	   
	    public int next(int price) {
	        
	        days++;
	        
	        if (stack.isEmpty())
	        {
	            stack.push(new int []{price, days});
	            return 1;
	        }
	        
	        while(!stack.isEmpty() && stack.peek()[0] <= price)
	        {
	            stack.pop();            
	        }
	        
	        if (stack.isEmpty())
	        {
	            stack.push(new int [] {price, days});
	            return days + 1;
	        }
	        else
	        {
	            int result = days - stack.peek()[1];
	            stack.push(new int [] {price, days});
	            return result;
	        }
	    }
	}

}



/*********************
* OTHERS SOLUTIONS
*********************/

/**
 * 
 * @author Nelson Costa
 *
 */
class StockSpannerSolution1 {
    Stack<Integer> prices, weights;

    public StockSpannerSolution1() {
        prices = new Stack();
        weights = new Stack();
    }

    public int next(int price) {
        int w = 1;
        while (!prices.isEmpty() && prices.peek() <= price) {
            prices.pop();
            w += weights.pop();
        }

        prices.push(price);
        weights.push(w);
        return w;
    }
}

/**
 * @timetook
 * 		17ms
 * 
 * @intuition
 * 		Top solution for this problem
 * 
 * 		very cleaver, it uses the limits to resolve the issue.
 * 
 * 		the gist is:
 * 
 * 			a) If current value bigger than previous, we absolve its count and subtrack its index to current index to explore the next element.
 * 
 * 			b) if the previous element is also smaller we repeat step a) else we stop.
 * 
 * 
 * 			- stock has all the values the were inserted.
 * 			- memoize has all the counts for each element inserted
 * 			- and size has the size of the elements inseted.
 * 
 * 			for indexing we use the size.
 * 
 * 		This approach is indeed what I tried to achieve with the first solution.
 * 		But a missed few things with using stack, which oblied me to remove and add elements again.
 * 		I needed to use an array as this man did.
 * 
 * 		Well done!
 * 
 * 
 * 
 * @author Nelson Costa
 *
 */
class StockSpannerUnofficialSolution1 {
    
    int[] stocks;
    int[] memoize;
    int size;
    public StockSpannerUnofficialSolution1() {
        this.stocks = new int[10000];
        this.memoize = new int[10000];
        this.size=-1;
    }
    
    public int next(int price) {
        int i = this.size;
        int count = 1;
        while (i >=0) {
            if(this.stocks[i] <= price) {
                count += this.memoize[i];
                i -= this.memoize[i];
            } else {
                break;
            }
        }
        this.size++;
        this.stocks[size] = price;
        this.memoize[size] = count;
        return count;
    }
}

