package com.data.structures.problems;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/divide-two-integers
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class DivideTwoIntegers {

	public static void main(String[] args) {
		int dividend = 0, divisor = 1;
		DivideTwoIntegers d = new DivideTwoIntegers();
		DivideTwoIntegersSolution1 d1 = new DivideTwoIntegersSolution1();
		System.out.print(dividend);
		System.out.println("/" + divisor);
		System.out.println("=" + d.divide(dividend, divisor));		
		//System.out.println("=" + d1.divide(dividend, divisor));		

		DivideTwoIntegersTest test = new DivideTwoIntegersTest();
	}

	/*********************************
	 * SOLUTION 1
	 ********************************/
	
	/**[TIMEOUT]
	 * 
	 * @intuition
	 * 		The gist of this problem was to find how to divide without divide operation mod or multiplication
	 * 		After that war how to not overflow
	 * 		
	 * 		for the first part dividing was solve by summing the divisor and see if the result is equals to the divided,
	 * 		if it is bigger then we need to sum less times, if it is smaller with need to add more times
	 * 
	 * 		for the second part, which was avoiding the overflow i tried to understand when the overflow could happen.
	 * 		it looks like the case when you divide Integer.Min/-1. so I decided to do all the bath using negative numbers
	 * 		and having a variable that tells me if the final result is negative or not
	 * 		
	 * @score
	 * 		
	 * 
	 * @optimizations
	 * 		I can store the values that I've already have calculated
	 * 
	 * @debug
	 * 		Assisted
	 * 
	 * @fail 
	 * 		1) on my refactoring looks like i forgot to switch high by low, on return, becaus in the beginning I had mid as positive number
	 * 		but I swap that order because of overflow but forgot to change or double check the return pointer.
	 * 		2) forgot to switch the sign of the for that adds, for the same reason than 1)
	 * 		3) another fail, it looked an overflow but it was my could limit that was wrong. 
	 * 		I had the count limit to divisor, but it should be the divider I think
	 * 		4) found out the algorithm was too slow, basic error was committed... I had to perform a second binary search
	 * 		5) formula was wrong for the middle
	 * 		6) time complexity was wrong I fixed it
	 * 
	 * @alternatives
	 * 		I could have avoided dealing with only negative cases, 
	 * 		but it clearly simplifies my solution and the time complexity is the same, 
	 * 		so it is more maintainable code that scales equally well
	 * 
	 * @time	O(N*LogN) N equals to dividend size
	 * @space 	O(1)
	 * @bcr 	O(log n)
	 * 
	 * @param dividend
	 * @param divisor
	 * @return
	 */
	public int divide0(int dividend, int divisor) {

		if (dividend == 0)
			return 0;

		boolean isResultNegative = dividend < 0 && divisor > 0 ||  dividend > 0 && divisor < 0 ? true : false;

		dividend = dividend > 0 ? ((~dividend) + 1) : dividend;		
		divisor = divisor > 0 ? (~divisor) + 1 : divisor;

		int low = dividend, high = 0, mid, result = 0; 

		while (low <= high)
		{
			mid = high + ((low - high) >> 1); //low + ((high - low) >> 1);
			result = 0;

			for (int i = 0; i > mid; i--)
			{
				result += divisor;

			}

			if (result == dividend)
			{
				return safeResult(isResultNegative, mid);
			}
			else if (result > dividend)
			{
				high = mid - 1;
			}
			else
			{
				low = mid + 1;
			}
		}
		return safeResult(isResultNegative, low);
	}



	/*********************************
	 * SOLUTION 2
	 ********************************/

	/**[WRONG]
	 * 
	 * @intuition
	 * 		The gist of this problem was to find how to divide without divide operation mod or multiplication
	 * 		After that war how to not overflow
	 * 		
	 * 		for the first part dividing was solve by summing the divisor and see if the result is equals to the divided,
	 * 		if it is bigger then we need to sum less times, if it is smaller with need to add more times
	 * 
	 * 		for the second part, which was avoiding the overflow i tried to understand when the overflow could happen.
	 * 		it looks like the case when you divide Integer.Min/-1. so I decided to do all the bath using negative numbers
	 * 		and having a variable that tells me if the final result is negative or not
	 * 		
	 * @score
	 * 		
	 * 
	 * @optimizations
	 * 		I've stored the values that I've already have calculated and continued from there.
	 * 		I believe I can still do better If I have a Binary Search on the hashmap to find if we have something smaller
	 * 
	 * @debug
	 * 		Assisted
	 * 
	 * @fail 
	 * 		1) on my refactoring looks like i forgot to switch high by low, on return, becaus in the beginning I had mid as positive number
	 * 		but I swap that order because of overflow but forgot to change or double check the return pointer.
	 * 		2) forgot to switch the sign of the for that adds, for the same reason than 1)
	 * 		3) another fail, it looked an overflow but it was my could limit that was wrong. 
	 * 		I had the count limit to divisor, but it should be the divider I think
	 * 		4) found out the algorithm was too slow, basic error was committed... I had to perform a second binary search
	 * 		5) formula was wrong for the middle
	 * 		6) time complexity was wrong I fixed it
	 * 		7) mistake when using TreeMap, for this particular case, the first was actually the last, because they are negative numbers...
	 * 		8) Timeout again for the case with positive numbers
	 * 		9) another fault, the for loop was wrong, I needed to take care of the case where when adding the divisor it do not overflow, nor passed the target
	 * @alternatives
	 * 		I could have avoided dealing with only negative cases, 
	 * 		but it clearly simplifies my solution and the time complexity is the same, 
	 * 		so it is more maintainable code that scales equally well
	 * 
	 * @time	O(N*LogN) N equals to dividend size
	 * @space 	O(1)
	 * @bcr 	O(log n)
	 * 
	 * @param dividend
	 * @param divisor
	 * @return
	 */
	public int divide1(int dividend, int divisor) {

		if (dividend == 0)
			return 0;

		boolean isResultNegative = dividend < 0 && divisor > 0 ||  dividend > 0 && divisor < 0 ? true : false;

		dividend = dividend > 0 ? ((~dividend) + 1) : dividend;		
		divisor = divisor > 0 ? (~divisor) + 1 : divisor;

		TreeMap<Integer, Integer> memo = new TreeMap<>();
		memo.put(-1, 0);

		int low = dividend, high = 0, mid, result = 0, counterStart, cache; 


		while (low <= high)
		{
			mid = high + ((low - high) >> 1); //low + ((high - low) >> 1);
			cache = memo.getOrDefault(memo.firstKey(), 0);
			counterStart = mid < cache ? cache : 0;
			result = (counterStart > 0)? counterStart - divisor : counterStart;


			System.out.println("counterStart: " + counterStart + " cache: " + cache + " mid: " + mid);

			for (int i = counterStart; i > mid; i--)
			{
				result += divisor;

			}

			//			System.out.println("mid" + mid + " result" + result);

			memo.put(mid, result);

			if (result == dividend)
			{
				return safeResult(isResultNegative, mid);
			}
			else if (result > dividend)
			{
				high = mid - 1;
			}
			else
			{
				low = mid + 1;
			}
		}
		return safeResult(isResultNegative, low);
	}




	/*********************************
	 * SOLUTION 3
	 ********************************/

	/**[COMPLETLY MESSED UP]
	 * 
	 * @intuition
	 * 		The gist of this problem was to find how to divide without divide operation mod or multiplication
	 * 		After that war how to not overflow
	 * 		
	 * 		for the first part dividing was solve by summing the divisor and see if the result is equals to the divided,
	 * 		if it is bigger then we need to sum less times, if it is smaller with need to add more times
	 * 
	 * 		for the second part, which was avoiding the overflow i tried to understand when the overflow could happen.
	 * 		it looks like the case when you divide Integer.Min/-1. so I decided to do all the bath using negative numbers
	 * 		and having a variable that tells me if the final result is negative or not
	 * 		
	 * @score
	 * 		
	 * 
	 * @optimizations
	 * 		I've stored the values that I've already have calculated and continued from there.
	 * 		I believe I can still do better If I have a Binary Search on the hashmap to find if we have something smaller
	 * 
	 * @debug
	 * 		Assisted
	 * 
	 * @fail 
	 * 		1) on my refactoring looks like i forgot to switch high by low, on return, becaus in the beginning I had mid as positive number
	 * 		but I swap that order because of overflow but forgot to change or double check the return pointer.
	 * 		2) forgot to switch the sign of the for that adds, for the same reason than 1)
	 * 		3) another fail, it looked an overflow but it was my could limit that was wrong. 
	 * 		I had the count limit to divisor, but it should be the divider I think
	 * 		4) found out the algorithm was too slow, basic error was committed... I had to perform a second binary search
	 * 		5) formula was wrong for the middle
	 * 		6) time complexity was wrong I fixed it
	 * 		7) mistake when using TreeMap, for this particular case, the first was actually the last, because they are negative numbers...
	 * 		8) Timeout again for the case with positive numbers
	 * 		9) another fault, the for loop was wrong, I needed to take care of the case where when adding the divisor it do not overflow, nor passed the target
	 * @alternatives
	 * 		I could have avoided dealing with only negative cases, 
	 * 		but it clearly simplifies my solution and the time complexity is the same, 
	 * 		so it is more maintainable code that scales equally well
	 * 
	 * @time	O(N*LogN) N equals to dividend size
	 * @space 	O(1)
	 * @bcr 	O(log n)
	 * 
	 * @param dividend
	 * @param divisor
	 * @return
	 */
	public int divide2(int dividend, int divisor) {

		if (dividend == 0)
			return 0;

		boolean isResultNegative = dividend < 0 && divisor > 0 ||  dividend > 0 && divisor < 0 ? true : false;

		dividend = dividend > 0 ? ((~dividend) + 1) : dividend;		
		divisor = divisor > 0 ? (~divisor) + 1 : divisor;

		TreeMap<Integer, Integer> memo = new TreeMap<>();

		int low = dividend, high = 0, mid, result = 0, counterStart, cache; 


		while (low <= high)
		{
			mid = high + ((low - high) >> 1); //low + ((high - low) >> 1);
			cache = memo.size() > 0 ? memo.getOrDefault(memo.firstKey(), 0) : 0;

			while (memo.size() > 0 && dividend > memo.firstKey())
			{
				System.out.println("remove");
				memo.remove(memo.firstKey());
			}

			counterStart = mid < cache ? cache : 0;


			result = (counterStart > 0)? counterStart - divisor : counterStart;

			System.out.println("high: " + mid + " low: " + low + " mid: " + mid);
			//			System.out.println("counterStart: " + counterStart + " cache: " + cache + " mid: " + mid + " Result: " + result);
			int i = counterStart;
			while (i > mid && result <= 0)
			{

				if (result + divisor > result)	
				{
					memo.put(i, result);
					System.out.println("before fail" + " i: " + i + " result: " + result);
					result += divisor;
					break;
				}

				result += divisor;
				i--;
			}

			//			System.out.println("mid: " + mid + " result: " + result + " i: " + i);

			if (result == dividend)
			{
				return safeResult(isResultNegative, i);
			}
			else if (i < mid || result > 0 || result > dividend)//(result > dividend )
			{
				low = mid + 1;
			}
			else
			{
				high = mid - 1;
			}
		}
		return safeResult(isResultNegative, low);
	}


	/*********************************
	 * SOLUTION 4
	 ********************************/


	/**[TIMEOUT]
	 * 
	 * @intuition
	 * 		The gist of this problem was to find how to divide without divide operation mod or multiplication
	 * 		After that war how to not overflow
	 * 		
	 * 		for the first part dividing was solve by summing the divisor and see if the result is equals to the divided,
	 * 		if it is bigger then we need to sum less times, if it is smaller with need to add more times
	 * 
	 * 		for the second part, which was avoiding the overflow i tried to understand when the overflow could happen.
	 * 		it looks like the case when you divide Integer.Min/-1. so I decided to do all the bath using negative numbers
	 * 		and having a variable that tells me if the final result is negative or not
	 * 		
	 * @score
	 * 		
	 * 
	 * @optimizations
	 * 		I've stored the values that I've already have calculated and continued from there.
	 * 		I believe I can still do better If I have a Binary Search on the hashmap to find if we have something smaller
	 * 
	 * @debug
	 * 		Assisted
	 * 
	 * @fail 
	 * 		1) on my refactoring looks like i forgot to switch high by low, on return, becaus in the beginning I had mid as positive number
	 * 		but I swap that order because of overflow but forgot to change or double check the return pointer.
	 * 		2) forgot to switch the sign of the for that adds, for the same reason than 1)
	 * 		3) another fail, it looked an overflow but it was my could limit that was wrong. 
	 * 		I had the count limit to divisor, but it should be the divider I think
	 * 		4) found out the algorithm was too slow, basic error was committed... I had to perform a second binary search
	 * 		5) formula was wrong for the middle
	 * 		6) time complexity was wrong I fixed it
	 * 		7) mistake when using TreeMap, for this particular case, the first was actually the last, because they are negative numbers...
	 * 		8) Timeout again for the case with positive numbers
	 * 		9) another fault, the for loop was wrong, I needed to take care of the case where when adding the divisor it do not overflow, nor passed the target
	 * 		10) I wasn't able of turn off binary search idea
	 * @alternatives
	 * 		I could have avoided dealing with only negative cases, 
	 * 		but it clearly simplifies my solution and the time complexity is the same, 
	 * 		so it is more maintainable code that scales equally well
	 * 
	 * @time	O(N*LogN) N equals to dividend size
	 * @space 	O(1)
	 * @bcr 	O(log n)
	 * 
	 * @param dividend
	 * @param divisor
	 * @return
	 */
	public int divide3(int dividend, int divisor) {

		if (dividend == 0)
			return 0;

		boolean isResultNegative = dividend < 0 && divisor > 0 ||  dividend > 0 && divisor < 0 ? true : false;

		dividend = dividend > 0 ? ((~dividend) + 1) : dividend;		
		divisor = divisor > 0 ? (~divisor) + 1 : divisor;

		int result = 0;
		int i = 0;
		while (result + divisor <= result && result + divisor >= dividend)
		{
			result += divisor;
			i--;
		}
		return safeResult(isResultNegative, i);
	}

	/*********************************
	 * SOLUTION 5
	 ********************************/

	/**
	 * @intuition
	 * 		Is similar to the question above but I rise the numbers much faster, always multiplying by two
	 * 		if we overflow or over shoot the result we divide the variables that are adding up to the final result
	 * 		and we go back dividing till we find a multiple of the diviser in the power of two that fits our result
	 * 		the stop condition is either to find a exact result or to have our variable that is multiple of divisor, 
	 * 		becaming below the divisor because even the divisor could not be add up to the final result without getting overflowed
	 * 		
	 * @failed
	 * 		1) Signals are bad. because I was adding and not subtracting.
	 * 		2) I was not taking into account when we don't find an exact result, and returned -1
	 * 		3) forgot to safe the point 2) wrapping in the safe method as a result some results where swapped
	 * 		4) failed again, for the case of 0. because -1 >> 1 is not 0, but -1. is an exeption I was not aware
	 * @score
	 		Runtime: 1 ms, faster than 100.00% of Java online submissions for Divide Two Integers.
			Memory Usage: 36.8 MB, less than 6.06% of Java online submissions for Divide Two Integers.
	 * 
	 * @optimizations
	 * 
	 * 
	 * @time	??? - O(log n + log n + C)  ?? no the answer is [log n + C] we never do more of log2 n just try with some numbers 10/3 - I don't know some derivative o log n. it is similar to log n + log n + C
	 * @space	O(1)
	 * @bcr		
	 * 
	 * @param dividend
	 * @param divisor
	 * @return
	 */
	public int divide(int dividend, int divisor) {

		if (dividend == 0)
			return 0;

		boolean isNegative = (dividend > 0 && divisor < 0 || dividend < 0 && divisor > 0) ? true : false;

		dividend = dividend < 0 ? dividend : ~dividend + 1;
		divisor = divisor < 0 ? divisor : ~divisor + 1;

		int dividerComparer = 0; //current Subtraction, used to compare with the divider
		int subtractionResult = 0; //will be the result
		int curCount = -1;
		int curDivisor = divisor;

		while (curDivisor <= divisor)
		{
			if (dividerComparer + curDivisor > dividend && dividerComparer + curDivisor < 0)
			{
				dividerComparer += curDivisor;
				subtractionResult += curCount;

				curDivisor += curDivisor;
				curCount +=curCount;
			}
			//Overflow or surpass limit, so we divide
			else if (dividerComparer + curDivisor < dividend || dividerComparer + curDivisor > 0)
			{
				curDivisor >>= 1;
			curCount >>= 1;
			}
			else 
			{
				dividerComparer += curDivisor;
				subtractionResult += curCount;

				return safeResult(isNegative, subtractionResult);
			}
		}


		return  safeResult(isNegative, subtractionResult);
	}


	/*********************************
	 * Shared Methods
	 ********************************/

	/**
	 * Returns the result without overflowing
	 * @param isResultNegative
	 * @param result
	 * @return
	 */
	private int safeResult(boolean isResultNegative, int result) {
		//if overflows return max_value
		if (!isResultNegative && result == Integer.MIN_VALUE)
		{
			return Integer.MAX_VALUE;
		}
		//Return negative or positive value
		else
		{
			return !isResultNegative ? ~result + 1 : result;
		}
	}
}



/*********************
 * OTHERS SOLUTIONS
 *********************/

/**
 * It works on top of exponential search and does two search, one up, other down.
 * But with extra memory used	
 * 
 * they prevent overflow by checking if the divisor is below the half of int.min. if it is he breaks.
 * 
 * 
 * @time log n
 * @space log n 
 * @author Nelson Costa
 *
 */
class DivideTwoIntegersSolution3 {

	private static int HALF_INT_MIN = -1073741824;// -2**30;

	public int divide(int dividend, int divisor) {

		// Special case: overflow.
		if (dividend == Integer.MIN_VALUE && divisor == -1) {
			return Integer.MAX_VALUE;
		}

		/* We need to convert both numbers to negatives.
		 * Also, we count the number of negatives signs. */
		int negatives = 2;
		if (dividend > 0) {
			negatives--;
			dividend = -dividend;
		}
		if (divisor > 0) {
			negatives--;
			divisor = -divisor;
		}

		ArrayList<Integer> doubles = new ArrayList<>();
		ArrayList<Integer> powersOfTwo = new ArrayList<>();

		/* Nothing too exciting here, we're just making a list of doubles of 1 and
		 * the divisor. This is pretty much the same as Approach 2, except we're
		 * actually storing the values this time. */
		int powerOfTwo = -1;
		while (divisor >= dividend) {
			doubles.add(divisor);
			powersOfTwo.add(powerOfTwo);
			// Prevent needless overflows from occurring...
			if (divisor < HALF_INT_MIN) {
				break;
			}
			divisor += divisor;
			powerOfTwo += powerOfTwo;
		}

		int quotient = 0;
		/* Go from largest double to smallest, checking if the current double fits.
		 * into the remainder of the dividend. */
		for (int i = doubles.size() - 1; i >= 0; i--) {
			if (doubles.get(i) >= dividend) {
				// If it does fit, add the current powerOfTwo to the quotient.
				quotient += powersOfTwo.get(i);
				// Update dividend to take into account the bit we've now removed.
				dividend -= doubles.get(i);
			}
		}

		/* If there was originally one negative sign, then
		 * the quotient remains negative. Otherwise, switch
		 * it to positive. */
		if (negatives != 1) {
			return -quotient;
		}
		return quotient;
	}

}
/**
 * Exponential Search Approach :
 * Repeated Exponential Searches
 * 
 * The author multiplies the divisor and tries to find the exponential before we overshoot the target value
 * but one exponential search is not enough, we have to do multiple exponential search until nothing of below divisor is left
 * 
 * In Other words we try to find the exponential that fits the number left multiple times
 * 
 * like [divisor] x 2^[exponential N]
 * 
 * @author Nelson Costa
 *
 */
class DivideTwoIntegersSolution2 {
	private static int HALF_INT_MIN = -1073741824;

	public int divide(int dividend, int divisor) {

		// Special case: overflow.
		if (dividend == Integer.MIN_VALUE && divisor == -1) {
			return Integer.MAX_VALUE;
		}

		/* We need to convert both numbers to negatives.
		 * Also, we count the number of negatives signs. */
		int negatives = 2;
		if (dividend > 0) {
			negatives--;
			dividend = -dividend;
		}
		if (divisor > 0) {
			negatives--;
			divisor = -divisor;
		}

		int quotient = 0;
		/* Once the divisor is bigger than the current dividend,
		 * we can't fit any more copies of the divisor into it. */
		while (divisor >= dividend) {
			/* We know it'll fit at least once as divivend >= divisor.
			 * Note: We use a negative powerOfTwo as it's possible we might have
			 * the case divide(INT_MIN, -1). */
			int powerOfTwo = -1;
			int value = divisor;
			/* Check if double the current value is too big. If not, continue doubling.
			 * If it is too big, stop doubling and continue with the next step */
			while (value >= HALF_INT_MIN && value + value >= dividend) {
				value += value;
				powerOfTwo += powerOfTwo;
			}
			// We have been able to subtract divisor another powerOfTwo times.
			quotient += powerOfTwo;
			// Remove value so far so that we can continue the process with remainder.
			dividend -= value;
		}

		/* If there was originally one negative sign, then
		 * the quotient remains negative. Otherwise, switch
		 * it to positive. */
		if (negatives != 1) {
			return -quotient;
		}
		return quotient;
	}
}
/**
 * Same concept than my solution
 * Results in Timeout
 * @author Nelson Costa
 *
 */
class DivideTwoIntegersSolution1 {

	public int divide(int dividend, int divisor) {

		// Special case: overflow.
		if (dividend == Integer.MIN_VALUE && divisor == -1) {
			return Integer.MAX_VALUE;
		}

		/* We need to convert both numbers to negatives
		 * for the reasons explained above.
		 * Also, we count the number of negatives signs. */
		int negatives = 2;
		if (dividend > 0) {
			negatives--;
			dividend = -dividend;
		}
		if (divisor > 0) {
			negatives--;
			divisor = -divisor;
		}

		/* Count how many times the divisor has to be added
		 * to get the dividend. This is the quotient. */
		int quotient = 0;
		while (dividend - divisor <= 0) {
			quotient--;
			dividend -= divisor;
		}

		/* If there was originally one negative sign, then
		 * the quotient remains negative. Otherwise, switch
		 * it to positive. */
		if (negatives != 1) {
			quotient = -quotient;
		}
		return quotient;
	}
}