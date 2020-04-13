package com.data.structures.problems;

/**
 * https://leetcode.com/problems/count-primes/
 * EASY
 * 
 * This was a though one.
 * 
 * A number is factorial if it is not a result a multiplication of some of the numbers between 2 and square root of that number..
 * there are non prime numbers that are not divisible by 2, 3, 5, and 7. 
 * the first one is 121 which is a perfect square of 11. 
 * factors of 121: 121 * 11 * 1
 * 
 * @author Nelson Costa
 *
 */
public class CountPrimes {

	public static void main(String[] args) {

		CountPrimes c = new CountPrimes();

		int n = 1500000;

		System.out.println(c.countPrimes(n));
	}

	/** [WRONG]
	 * primes calculus was wrong
	 * nothing fancy
	 * @failed the calculus of divisible was wrong, I wasn't having the other numbers into account
	 * And the boolean operation was also wrong Or instead of and
	 * @time  O(n)
	 * @space O(1)
	 * @param n
	 * @return
	 */
	public int countPrimes0(int n) {

		if (n == 1 || n == 0)
			return 0;

		int count = 0;

		for (int i = 2; i <= n; i++) {

			if (i % 2 != 0 && i != 2 && 
					i % 3 != 0 && i != 3 && 
					i % 5 != 0 && i != 5 && 
					i % 7 != 0 && i != 7)
			{
				count++;
			}
			else if (i == 2 || i == 3 || i == 5 || i == 7)
			{
				count++;	
			}
		}

		return count;
	}

	/**
	 * Timeout [because of math.sqrt in isPrime]
	 * @time O(N*sqrt(N))
	 * @param n
	 * @return
	 */
	public int countPrimes1(int n) {

		if (n == 1 || n == 0)
			return 0;

		int count = 0;

		for (int i = 2; i < n; i++)
		{
			if (isPrime(i))
			{
				count++;
			}			
		}

		return count;
	}


	/**
	 * only possible after improving the isPrime Function 
	 * 
	 * 		Runtime: 554 ms, faster than 8.06% of Java online submissions for Count Primes.
			Memory Usage: 36 MB, less than 9.43% of Java online submissions for Count Primes.

			isPrime was the bottleneck
	 * @time O(N*sqrt(N))
	 * @param n
	 * @return
	 */
	public int countPrimes(int n) {

		if (n == 1 || n == 0)
			return 0;

		int count = 0;

		for (int i = 2; i < n; i++)
		{
			if (isPrime(i))
			{
				count++;
			}
		}
		return count;
	}
	

	private boolean isPrime(int n) {
		//for (int i = 2; i * i <= Math.sqrt(n); i++) {
		for (int i = 2; i * i <= n; i++) {

			if (n % i == 0)
				return false;
		}
		return true;
	}
}

class CountPrimesSolution{
	
	/**
	 * leetcode solution, not easy to understand
	 * @param n
	 * @return
	 */
	public int countPrimes0(int n) {
		boolean[] isPrime = new boolean[n];
		for (int i = 2; i < n; i++) {
			isPrime[i] = true;
		}
		// Loop's ending condition is i * i < n instead of i < sqrt(n)
		// to avoid repeatedly calling an expensive function sqrt().
		for (int i = 2; i * i < n; i++) {
			if (!isPrime[i]) continue;
			for (int j = i * i; j < n; j += i) {
				isPrime[j] = false;
			}
		}
		
		int count = 0;
		for (int i = 2; i < n; i++) {
			if (isPrime[i]) count++;
		}
		return count;
	}
	
	
	/**
	 * This is no easy solution at all
	 * 
	 * Composite Number:
	 * 	a composite number is a number that is a result of a multiplication of two other numbers.
	 * 
	 * if a number is a composite Number it is not a prime number.
	 * 
	 * To check if a numbers is prime we just need to check if there is a multiplication 
	 * of its smallest numbers that returns the given value.
	 * 
	 * we only need to check maximum till the square root of this number.
	 * 
	 * So for 100. we only need to check the square root of 100
	   so 10. from 2 to 10 and we multiply all this values and mark this values as composite
	 * 
	 * 
	 * INTUITION
	 * 		So this solution in a nutshell is the process of marking all composite numbers till the input and counting the num composite numbers.
	 * 
	 * 		beautiful...
	 	
	 * 		I though the same but in a wrong maner, I thought I could this by 
			skiping the multiples of 2, 3, 5 and 7.
	 *  
	 * 
	 * 
	 * @param n
	 * @return
	 */
	public int countPrimes(int n) {
		boolean[] isComposite = new boolean[n];

		//we want the count of primes below n, so if n is prime it does not add up
		for (int i = 2; i * i < n; i++) {
			if (!isComposite[i]) {
				for (int j = i; j * i < n; j++) {
					isComposite[j * i] = true;
				}
			}
		}
		
		int count = 0;
		for (int i = 2; i < n; i++) {
			if (!isComposite[i]) count++;
		}
		
		return count;
	}
}