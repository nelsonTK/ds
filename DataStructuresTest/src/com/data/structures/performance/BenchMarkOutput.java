package com.data.structures.performance;

public class BenchMarkOutput {

	public String methodName;
	public int inputSize;
	public double time;

	public BenchMarkOutput(String methodName, int inputSize, double time) {
		super();
		this.methodName = methodName;
		this.inputSize = inputSize;
		this.time = time;
	}
	
	/**
	 * 
	 * @param methodName
	 * @param inputSize
	 * @param start time in nano seconds
	 * @param end time in nano seconds
	 */
	public BenchMarkOutput(String methodName, int inputSize, long start, long end) {
		super();
		this.methodName = methodName;
		this.inputSize = inputSize;
		this.time = (double) (end - start) / 1_000_000_000;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public int getInputSize() {
		return inputSize;
	}

	public void setInputSize(int inputSize) {
		this.inputSize = inputSize;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "MethodName: " + methodName + "\t InputSize: " + inputSize + "\t Time: " + time;
	}
}
