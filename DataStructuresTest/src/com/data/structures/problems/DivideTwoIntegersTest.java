package com.data.structures.problems;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DivideTwoIntegersTest {
	DivideTwoIntegers d = new DivideTwoIntegers();

	@Test
	void test1() {
		int dividend = 7, divisor = -3;
		System.out.print(dividend);
		System.out.println("/" + divisor);
		System.out.println("=" + d.divide(dividend, divisor));
		assertTrue(d.divide(dividend, divisor) == -2);
	}
	@Test
	void test2() {
		int dividend = 10, divisor = -3;
		DivideTwoIntegers d = new DivideTwoIntegers();
		System.out.print(dividend);
		System.out.println("/" + divisor);
		System.out.println("=" + d.divide(dividend, divisor));		
		assertTrue(d.divide(dividend, divisor) == -3);

	}

	@Test
	void test3() {
		int dividend =-2147483648, divisor = -1;
		DivideTwoIntegers d = new DivideTwoIntegers();
		System.out.print(dividend);
		System.out.println("/" + divisor);
		System.out.println("=" + d.divide(dividend, divisor));		
		assertTrue(d.divide(dividend, divisor) == 2147483647);

	}
	
	@Test
	void test4() {
		int dividend = -2_000_000_000, divisor = -1;
		DivideTwoIntegers d = new DivideTwoIntegers();
		System.out.print(dividend);
		System.out.println("/" + divisor);
		System.out.println("=" + d.divide(dividend, divisor));		
		assertTrue(d.divide(dividend, divisor) == 2_000_000_000);

	}

	@Test
	void test5() {
		int dividend =2147483647, divisor = 2;
		DivideTwoIntegers d = new DivideTwoIntegers();
		System.out.print(dividend);
		System.out.println("/" + divisor);
		System.out.println("=" + d.divide(dividend, divisor));		
		assertTrue(d.divide(dividend, divisor) == 1073741823);

	}
	
	@Test
	void test6() {
		int dividend = 0, divisor = 1;
		DivideTwoIntegers d = new DivideTwoIntegers();
		System.out.print(dividend);
		System.out.println("/" + divisor);
		System.out.println("=" + d.divide(dividend, divisor));		
		assertTrue(d.divide(dividend, divisor) == 0);

	}
}
