package com.data.structures.performance;

public class BenchMarkInput<T> {

	private T function;
	private String name;
	
	public BenchMarkInput(T function, String name) {
		super();
		this.function = function;
		this.name = name;
	}

	public T getFunction() {
		return function;
	}

	public void setFunction(T function) {
		this.function = function;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
