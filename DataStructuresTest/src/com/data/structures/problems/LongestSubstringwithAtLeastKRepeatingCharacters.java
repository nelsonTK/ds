package com.data.structures.problems;

public class LongestSubstringwithAtLeastKRepeatingCharacters {

	static LongestSubstringwithAtLeastKRepeatingCharacters l = new LongestSubstringwithAtLeastKRepeatingCharacters();

	public static void main(String[] args) {
		String s = "shjahhjgahydasdassssssdddljgjhgjhlg";
		int k = 4;
		s = "kjkhhhhhkkssslllsdlkc";
		k = 5;
		
		s = "jdhashaskjkhkjkjkkjjjjjjddlllaxxxlamdkjshksjhddiiwbbd";
		k = 4;
		System.out.println(l.longestSubstring(s, k));
	}
	/**
	 * @intuition
	 * 		Ugly Solution, bruteforce.
	 * 		the goal is to try out all combination and see if there is any number that is greater than specified, if we don't have than we found a fit substring.
	 * 		We just have to sum it's values and we are done.
	 * 
	 * 
	 * @score
	 * 		Runtime: 497 ms, faster than 5.09% of Java online submissions for Longest Substring with At Least K Repeating Characters.
	 * 		Memory Usage: 38.8 MB, less than 26.70% of Java online submissions for Longest Substring with At Least K Repeating Characters.
	 * 
	 * @time
	 * 		O(N^2)
	 * 
	 * @space
	 * 		O(1)
	 * 
	 * @param s
	 * @param k
	 * @return
	 */
	public int longestSubstring0(String s, int k) {
		int longest = 0;
		for (int i = 0; i < s.length(); i++)
		{
			int [] c = new int [26];
			for (int j = i; j < s.length(); j++)
			{
				c[s.charAt(j) - 'a']++;

				int count = 0;
				for (int validator = 0; validator < c.length; validator++)
				{
					if(c[validator] >= k)
					{
						count+=c[validator];
					}
					else if (c[validator] < k && c[validator]> 0)
					{
						count = 0;
						break;
					}
				}

				longest = Math.max(longest, count);
			}
		}

		return longest;
	}



	//failed  1) I was increasing the end too early
	//2) I was missing reducing the window
	//3) I was not removing the count of elements that were past in the past
	//4) fail because I dont update the numbers while running
	public int longestSubstring(String s, int k) {

		//k == 1 -> all word;
		//k == 0 -> all word;
		if (k <= 1)
			return s.length();


		int [] charCount = new int [26]; 
		int result = 0;

		//count each character word
		for (int i = 0; i < s.length(); i++)
		{
			charCount[s.charAt(i) - 'a']++;
		}

		for (int i = 0; i < 26; i++)
		{
			System.out.println((char)(i + 'a')  + " : " + charCount[i]);
		}
		
		int start = 0, end = 0;
		int [] temp;
		boolean valid = true;
		while (end < s.length())
		{
			start = end;
			//if current char has a count  equal or greater than  k
			//we will grow the window boundary
			//char curC = s.charAt(end);

			//validate if currentChar is valid
			if (charCount[s.charAt(end) - 'a'] >= k)
			{
				temp = new int [26];
				//temp[curC - 'a']++;
				//end++;
				//curC = s.charAt(end); //current sliding window end
				//temp[curC - 'a']++; //update sliding window counts, first element

				while (end < s.length() && charCount[s.charAt(end) - 'a'] + temp [s.charAt(end) - 'a']>= k)
				{
					temp[s.charAt(end) - 'a']++; //update sliding window counts

					end++;
				}


				//validate counts
				while (start < end)
				{
					valid = true;
					for (int counts : temp)
					{
						if (counts > 0 && counts < k)
						{
							valid = false;
							break;
						}
					}

					//validade if all elements have the expected time
					if (valid)
					{
						result = Math.max(result, end - start);
					}

					temp[s.charAt(start) - 'a']--;
					charCount[s.charAt(start) - 'a']--;
					start++;
				}

				start = end;
			}
			//if current char has not a count  equal or greater than  k
			//we will grow both window init and end coundaries
			else //advance bad elements
			{
				charCount[s.charAt(end) - 'a']--;
				end++;
				while (end < s.length() && charCount[s.charAt(end) - 'a'] < k)
				{
					charCount[s.charAt(end) - 'a']--;
					end++;
				}
			}
		}

		return result;
	}
}
/*
"aaabaadeaaaa"
4
"ababbc"
2
"aaabb"
3
"shjahhjgahydasdassssssdddljgjhgjhlg"
4
 */