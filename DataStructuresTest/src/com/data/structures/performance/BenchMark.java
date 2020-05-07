package com.data.structures.performance;

import java.util.List;
import java.util.Random;
import java.util.Stack;

public class BenchMark {
	
	/**
	 * 
	 * @param list list of benchmark inputs to test
	 * @param low  lower limit of possible values for integers in the generated input 
	 * @param high upper limit of possible values for integers in the generated input
	 * @param size maximum size of the desired input array
	 * @param growthRate is the factor by which the number of elements will be multiplied from one iteration to another
	 */
	public void benchMarkFunctionInputIntArray (List<BenchMarkInput<FunctionInputIntArray>> list, int low, int high, int size, int growthRate) {

		long start;
		long end;
		Stack<BenchMarkOutput> output = new Stack<>();
		BenchMarkOutFactory factory = new BenchMarkOutFactory();
		
		int [] inputArray;
		
		for (int i = 1; i <= size; i *= growthRate)
		{
			inputArray = new Random().ints(i, low, high).toArray();
			
			for (BenchMarkInput<FunctionInputIntArray> input : list) {
				
				start = System.nanoTime();
				
				input.getFunction().apply(inputArray);
				
				end = System.nanoTime();
				
				output.add(factory.create(input.getName(), i, start, end));
				
				System.out.println(output.peek());
			}			
		}			
	}

}
