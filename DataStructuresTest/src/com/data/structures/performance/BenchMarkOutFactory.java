package com.data.structures.performance;

/**
 * This factory object creates benchmark output objects
 * 
 * @author Nelson Costa
 *
 */
public class BenchMarkOutFactory{

	public BenchMarkOutput create(String methodName, int inputSize, long start, long end){	
		return new BenchMarkOutput(methodName, inputSize, start, end);
	}
}
