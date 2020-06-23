package com.data.structures.problems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class IntegertoEnglishWords {

	static IntegertoEnglishWords i = new IntegertoEnglishWords();
	public static void main(String[] args) {
		int num = 43612;
		num = 150000000;
		System.out.println(i.numberToWords(num));
	}

	//static info
	HashMap<Integer, String> scale = new HashMap<>();
	HashMap<Integer, String> nums = new HashMap<>();
	static final int HUNDRED = 4;

	public IntegertoEnglishWords(){
		scale.put(0, "");
		scale.put(1, " Thousand");
		scale.put(2, " Million");
		scale.put(3, " Billion");
		scale.put(4, " Hundred");

		nums.put(0, "Zero");
		nums.put(1, "One");
		nums.put(2,	"Two");
		nums.put(3,	"Three");
		nums.put(4,	"Four");	
		nums.put(5,	"Five");	
		nums.put(6,	"Six");	
		nums.put(7,	"Seven");	
		nums.put(8,	"Eight");	
		nums.put(9,	"Nine");	
		nums.put(10,	"Ten");	
		nums.put(11,	"Eleven");	
		nums.put(12,	"Twelve");	
		nums.put(13,	"Thirteen");	
		nums.put(14,	"Fourteen");	
		nums.put(15,	"Fifteen");	
		nums.put(16,	"Sixteen");	
		nums.put(17,	"Seventeen");	
		nums.put(18,	"Eighteen");
		nums.put(19,	"Nineteen");

		nums.put(20, "Twenty");
		nums.put(30, "Thirty");
		nums.put(40, "Forty");
		nums.put(50, "Fifty");
		nums.put(60, "Sixty");
		nums.put(70, "Seventy");
		nums.put(80, "Eighty");
		nums.put(90, "Ninety");

	}

	/**
	 * @intuition
	 * 		Break the number in buckets and solve from there.
	 * 		the answer will be built from end of the number to the begining.
	 * 
	 * 		at each iteration I get tree numbers and according with the bucket,
	 * 		in which I'm in I can print thousand, million,billion or even trillion (the code scales)
	 * 	
	 * 		The stronger asset about this solution is indeed that if we want to go to trillions we just need to change from int to long, and add Trillions to the scale
	 * 		I'm wired to think in this scalable way
	 * 
	 * @score
			Runtime: 11 ms, faster than 44.03% of Java online submissions for Integer to English Words.
			Memory Usage: 40 MB, less than 15.73% of Java online submissions for Integer to English Words.
	 * 
	 * @optimizations
	 * 		I could have used switches
	 * 		I could use more math to get the buckets
	 * 
	 * 
	 * 
	 * @fail
	 * 		1) array start position was wrong, it was starting in the end
	 * 		2) bad configuration
	 * 		3) The spaces was not right I needed to add more uniformly
	 * 		4) I didn't sum the two elements
	 * 		5) forgot to treat zero
	 * 
	 * @time 	O(N)
	 * @space 	O(N)
	 * 
	 * 
	 * @param num
	 * @return
	 */
	public String numberToWords(int num) {

		if(num == 0)
			return nums.get(num);

		int size = (int)Math.log10(num) + 1;
		String [] sArr = new String[size];
		int bucketSize = 3;
		int[] bucket = new int[bucketSize];

		int curBucket = 0;
		int ansArrayPosition = 0;
		int bucketValue = 0;

		while(num > 0)
		{
			//fill last 3 digits
			bucketSize = 3;
			Arrays.fill(bucket, -1);
			bucketValue = 0;
			while (bucketSize > 0 && num > 0)
			{
				bucket[bucketSize - 1] = num - (num/10*10); //ISOLATE LAST DIGIT
				bucketValue += bucket[bucketSize - 1] * (int) Math.pow(10, 3 - bucketSize);
				num = num / 10; //remove leftmost digit		
				bucketSize--;
			}

			//Deal with 2 least significative digits
			//if below 20 and not negative
			if (bucket[1] > 0 && bucket[1] * 10 + bucket[2] < 20 && bucket[1] * 10 + bucket[2] >= 10)
			{
				//deal with one and two 
				// join two

				int lastTwo = bucket[1] * 10 + bucket[2];
				sArr[sArr.length - 1 - ansArrayPosition] = nums.get(lastTwo) + scale.get(curBucket) + " ";
				ansArrayPosition +=2;

			}
			else
			{

				//deal with one at a time

				if (bucket[2] > 0 && sArr.length - 1 - ansArrayPosition >= 0)
				{
					sArr[sArr.length - 1 - ansArrayPosition] = nums.get(bucket[2]) + scale.get(curBucket) + " ";
				}
				else
				{
					//if after the first bucket and the value for this bucket is larger than 0, add the scale (Million/Thousand/Billion)
					if (curBucket > 0 && bucketValue > 0)
						sArr[sArr.length - 1 - ansArrayPosition] = scale.get(curBucket).trim() + " ";
				}
				ansArrayPosition++;

				if (bucket[1] > 0 && sArr.length - 1 - ansArrayPosition >= 0)
				{
					sArr[sArr.length - 1 - ansArrayPosition] = nums.get(bucket[1] * 10) + " ";
				}

				ansArrayPosition++;
			}


			//deal with third digit of the bucket (the most significative
			if(bucket[0] > 0)
			{
				sArr[sArr.length - 1 - ansArrayPosition] = nums.get(bucket[0]) + scale.get(HUNDRED) + " ";

			}
			
			ansArrayPosition++;
			curBucket++;
		}


		StringBuilder ans = new StringBuilder("");

		for (int i = 0; i < sArr.length; i++ )
		{
			if(sArr[i] != null)
				ans.append(sArr[i]);
		}

		return ans.toString().trim();
	}
}


/*********************
 * OTHERS SOLUTIONS
 *********************/


/**
 * Intuition is to break the number in blocks of tree just like my solution, but in a more efficient manner
 * 
 * from there we use a method to deal with 3 numbers at a time
 * 
 * the tree blocks are for: Billion, Million, Thousand, and Rest.
 * 
 * Is a rather matematical problem
 * 
 * Important observations which was also made by me.
 * 
 * 		When you have a bucket with only zeros you do nothing, other wise you print the values.
 * 
 * Gist
 * 		In a nutshell the solution is :
 * 
 * 			1) separate the buckets
 * 			2) print each bucket according to english rules
 * 		
 * @author Nelson Costa
 *
 */
class IntegertoEnglishWordsSolution1 {
	//returns numbers from 1 to 9
	public String one(int num) {
		switch(num) {
		case 1: return "One";
		case 2: return "Two";
		case 3: return "Three";
		case 4: return "Four";
		case 5: return "Five";
		case 6: return "Six";
		case 7: return "Seven";
		case 8: return "Eight";
		case 9: return "Nine";
		}
		return "";
	}

	//returns numbers from 10 to 19
	public String twoLessThan20(int num) {
		switch(num) {
		case 10: return "Ten";
		case 11: return "Eleven";
		case 12: return "Twelve";
		case 13: return "Thirteen";
		case 14: return "Fourteen";
		case 15: return "Fifteen";
		case 16: return "Sixteen";
		case 17: return "Seventeen";
		case 18: return "Eighteen";
		case 19: return "Nineteen";
		}
		return "";
	}

	//returns multiples of 10 below 100
	public String ten(int num) {
		switch(num) {
		case 2: return "Twenty";
		case 3: return "Thirty";
		case 4: return "Forty";
		case 5: return "Fifty";
		case 6: return "Sixty";
		case 7: return "Seventy";
		case 8: return "Eighty";
		case 9: return "Ninety";
		}
		return "";
	}

	//prints numbers from 1 to 99
	public String two(int num) {
		if (num == 0)
			return "";
		else if (num < 10)
			return one(num);
		else if (num < 20)
			return twoLessThan20(num);
		else {
			int tenner = num / 10;
			int rest = num - tenner * 10;
			if (rest != 0)
				return ten(tenner) + " " + one(rest);
			else
				return ten(tenner);
		}
	}

	//Deals with hundreds or so called buckets of three
	public String three(int num) {
		int hundred = num / 100;
		int rest = num - hundred * 100;
		String res = "";
		if (hundred*rest != 0)
			res = one(hundred) + " Hundred " + two(rest);
		else if ((hundred == 0) && (rest != 0))
			res = two(rest);
		else if ((hundred != 0) && (rest == 0))
			res = one(hundred) + " Hundred";
		return res;
	}

	public String numberToWords(int num) {
		if (num == 0)
			return "Zero";

		//get billion, million, thousand, and rest
		int billion = num / 1000000000;
		int million = (num - billion * 1000000000) / 1000000;
		int thousand = (num - billion * 1000000000 - million * 1000000) / 1000;
		int rest = num - billion * 1000000000 - million * 1000000 - thousand * 1000;

		String result = "";
		if (billion != 0)
			result = three(billion) + " Billion";
		if (million != 0) {
			if (! result.isEmpty())
				result += " ";
			result += three(million) + " Million";
		}
		if (thousand != 0) {
			if (! result.isEmpty())
				result += " ";
			result += three(thousand) + " Thousand";
		}
		if (rest != 0) {
			if (! result.isEmpty())
				result += " ";
			result += three(rest);
		}
		return result;
	}
}

/**
 * This is the top solution
 * 
 * It is very very elegant, and makes all the sense
 * 
 * loaded hashmap and its content statically
 * 
 * uses the rest intelligently to get the remaining for the numerous buckets, starting with the billion all the way to million and rest.
 * 
 * @author Nelson Costa
 *
 */
class IntegertoEnglishWordsUnoficcialSolution1 {
    // questions
    // 1. can i/p be negative ?
    // 2. range of input ?
    // 3. time or space optimized solution
    public static Map<Integer,String> map = new HashMap<>();
    static {
        map.put(0, "Zero");
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");
        map.put(4, "Four");
        map.put(5, "Five");
        map.put(6, "Six");
        map.put(7, "Seven");
        map.put(8, "Eight");
        map.put(9, "Nine");
        map.put(10, "Ten");
        map.put(11, "Eleven");
        map.put(12, "Twelve");
        map.put(13, "Thirteen");
        map.put(14, "Fourteen");
        map.put(15, "Fifteen");
        map.put(16, "Sixteen");
        map.put(17, "Seventeen");
        map.put(18, "Eighteen");
        map.put(19, "Nineteen");
        map.put(20, "Twenty");
        map.put(30, "Thirty");
        map.put(40, "Forty");
        map.put(50, "Fifty");
        map.put(60, "Sixty");
        map.put(70, "Seventy");
        map.put(80, "Eighty");
        map.put(90, "Ninety");
    }
    
    public String numberToWords(int num) {
        if(num == 0) {
            return map.get(num);
        }
        StringBuilder sb = new StringBuilder();
        if(num >= 1000000000) {
            sb.append(getString(num/1000000000)).append(" Billion");
            num %= 1000000000;
        }
        if(num >= 1000000) {
            sb.append(getString(num/1000000)).append(" Million");
            num %= 1000000;
        }
        if(num >= 1000) {
            sb.append(getString(num/1000)).append(" Thousand");
            num %= 1000;
        }
        if(num > 0) {
            sb.append(getString(num));
        }
        return sb.toString().trim();
    }
    
    private String getString(int num) {
        StringBuilder sb = new StringBuilder();
        if(num >= 100) {
            sb.append(" ").append(map.get(num/100)).append(" Hundred");
            num %= 100;
        }
        if(num > 20) {
            sb.append(" ").append(map.get((num/10)*10));
            num %= 10;
        }
        if(num > 0) {
            sb.append(" ").append(map.get(num));
        }
        return sb.toString();
    }
}