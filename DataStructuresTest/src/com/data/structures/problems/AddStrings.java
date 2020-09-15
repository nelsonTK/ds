package com.data.structures.problems;

/**
 * https://leetcode.com/problems/add-strings/
 * EASY
 * @author Nelson Costa
 *
 */
public class AddStrings {

	static AddStrings a = new AddStrings();
	public static void main(String[] args) {
		String num1 = "9";
		String num2 = "99";
		System.out.println(a.addStrings(num1, num2));
		
	}


	 /*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * @comments
	 * 		I could better use math operations like mode to get the value for instance
	 * 
	 * @score
	 * 		(Before optimizations)
	 * 		Runtime: 6 ms, faster than 23.28% of Java online submissions for Add Strings.
	 * 		Memory Usage: 39.2 MB, less than 84.58% of Java online submissions for Add Strings.
	 * 
	 * 		(after optimizations, which was pass int instead of string to sb append())
	 * 		Runtime: 2 ms, faster than 96.38% of Java online submissions for Add Strings.
	 * 		Memory Usage: 39.7 MB, less than 29.86% of Java online submissions for Add Strings.
	 * 
	 * @fail
	 * 		in the var swap I did not return and as a result the function was being executed twice
	 * 
	 * @param num1
	 * @param num2
	 * @return
	 */
	public String addStrings(String num1, String num2) {

		if (num1.length() < num2.length())
			return addStrings(num2, num1);

		if (num1 == null || num1.length() == 0)
			return num2;

		if (num2 == null || num2.length() == 0)
			return num1;


		int carry = 0, size1 = num1.length() - 1, size2 = num2.length() - 1;
		StringBuilder sb = new StringBuilder("");
		for (int i = 0; i < num1.length(); i++)
		{
			int n1 = num1.charAt(size1 - i) - '0';
			int n2 = i <= size2 ? num2.charAt(size2 - i) - '0' : 0;
			int sum = n1 + n2 + carry;


			if (sum >= 10)
			{
				sb.append((sum - 10));
				carry = 1;
			}
			else
			{
				sb.append(sum);
				carry = 0;
			}
		}

		if (carry > 0)
		{
			sb.append(carry);
		}

		return sb.reverse().toString();
	}
}


/*********************
* OTHERS SOLUTIONS
*********************/
/**
 * @intuition
 * 		same approach than mine but with less code and more efficient math and no guards
 * 
 * @author Nelson Costa
 *
 */
class AddStringsSolution1 {
    public String addStrings(String num1, String num2) {
        StringBuilder res = new StringBuilder();

        int carry = 0;
        int p1 = num1.length() - 1;
        int p2 = num2.length() - 1;
        while (p1 >= 0 || p2 >= 0) {
            int x1 = p1 >= 0 ? num1.charAt(p1) - '0' : 0;
            int x2 = p2 >= 0 ? num2.charAt(p2) - '0' : 0;
            int value = (x1 + x2 + carry) % 10;
            carry = (x1 + x2 + carry) / 10;
            res.append(value);
            p1--;
            p2--;    
        }
        
        if (carry != 0)
            res.append(carry);
        
        return res.reverse().toString();
    }
}
