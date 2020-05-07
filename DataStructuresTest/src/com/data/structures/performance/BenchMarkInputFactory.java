package com.data.structures.performance;

/**
 * This factory object creates benchmark input objects
 * 
 * @author Nelson Costa
 *
 * @param <T>
 */
public class BenchMarkInputFactory<T> {

	public BenchMarkInput<T> create(T function, String name){
		return new BenchMarkInput<T>(function, name);
	}
}
