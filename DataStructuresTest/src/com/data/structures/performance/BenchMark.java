package com.data.structures.performance;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BenchMark {

	public void BenchMarkFunctionInputIntArray (List<FunctionInputIntArray> list, int low, int high, int size, int growthRate) {

		long start;
		long end;
		int interval = 1;
		List<BenchMarkOutput> output = new ArrayList<BenchMarkOutput>();
		
		int [] inputArray;
		for (int i = 1; i <= size; i *= growthRate)
		{
			inputArray = new Random().ints(i, low, high).toArray();
			
			for (FunctionInputIntArray f : list) {
				
				start = System.nanoTime();
				
				f.apply(inputArray);
				
				end = System.nanoTime();
				
				output.add(new BenchMarkOutput("Test", i, (double) (end - start) / 1_000_000_000));
				
			}
			
		}			

		for (BenchMarkOutput o : output) {
			System.out.println(o.toString());
		}
	}

}
