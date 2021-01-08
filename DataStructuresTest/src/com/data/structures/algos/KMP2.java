package com.data.structures.algos;

public class KMP2 {

	/**
	 * 	@description
	 * 	 	shortened version.
	 *  
	 * 		the expanded version:
	 *  		expands the incremental of variables and uses one major if for each of the parts, then in the else treats the 2 cases
	 *  
	 * @param s
	 * @return
	 */
	public int[] lpsShort(String s) {
		int [] array = new int [s.length()];
		int len = 0, i = 1;

		while (i < s.length())
		{
			if (s.charAt(len) == s.charAt(i)){
				array[i++] = ++len;
			}
			else if (len == 0)
			{
				array[i++] = 0;
			}
			else
			{
				len = array[len - 1];
			}
		}
		return array;
	}


	public int KMPSearch(String s, String pattern)
	{
		int [] lps = lpsShort(pattern);

		int i = 0, len = 0;

		while (i < s.length())
		{
			if (pattern.charAt(len) == s.charAt(i))
			{
				i++;
				len++;
			}
			else if (len == 0) {
				i++;
			}
			else {
				len = lps[len - 1];
			}

			if (len == pattern.length())
				return i - len ;
		}

		return -1;
	}
}