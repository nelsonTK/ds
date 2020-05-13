package com.data.structures.problems;

import java.util.Arrays;
import java.util.PriorityQueue;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/reorder-data-in-log-files/
 * EASY
 * @author Nelson Costa
 *
 */
public class ReorderDataInLogFiles extends LeetCodeExercise{

	public static void main(String[] args) {

		String [] logs = {"dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"};
		ReorderDataInLogFiles r = new ReorderDataInLogFiles();
		printArray(logs);
		printArray(r.reorderLogFiles(logs));
	}

	/**
	 * @Intuition
	 * 		Sort only what needs to be sorted, the rest I just place them as needed
	 * 		
	 * 
	 * @score
			Runtime: 3 ms, faster than 87.81% of Java online submissions for Reorder Data in Log Files.
			Memory Usage: 39.7 MB, less than 55.88% of Java online submissions for Reorder Data in Log Files.

	 		AFTER CHANGING DIGITLOG FUNCTION

			Runtime: 2 ms, faster than 99.83% of Java online submissions for Reorder Data in Log Files.
			Memory Usage: 40 MB, less than 26.47% of Java online submissions for Reorder Data in Log Files.

	 * 
	 * 
	 * @debug
	 * 		yes
	 * 
	 * @fail
	 * 		1) forgot to increment ansIndex in the last loop
	 * 		2) the end variable to determine the isDigitLog I was adding one and it was too much
	 * 		3) bad interpretation, the ordering was about  the content of the log and not the logindex
	 * 		4) Failed because there was a change of the numbers where way larger than long or integer the verification must be done differently
	 * 		5) lack of attention on doing digit log check, i forgot to increase i
	 * 		6) I forgot to use hist from the description, like only digits after indentifier or only letters
	 * 
	 * 
	 * @time  NxMLogN, where N is the size of the array and M the size of the prefix which in the worst case is string length - 2;
	 * @space O(N)
	 * @bcr   NxMLogN
	 * 
	 * @param logs
	 * @return
	 */
	public String[] reorderLogFiles(String[] logs) {

		String [] ans = new String[logs.length];
		PriorityQueue<String> pq = new PriorityQueue<String>(
				(a,b) -> a.substring(a.indexOf(" ") + 1).compareTo(b.substring(b.indexOf(" ")+1)));

		int ansIndex = logs.length - 1;
		for(int i = logs.length - 1; i >= 0; i--)
		{
			if (!isDigitLog(logs[i]))
			{
				pq.add(logs[i]);
			}
			else
			{
				ans[ansIndex] = logs[i];
				ansIndex--;
			}
		}

		ansIndex = 0;	
		while (!pq.isEmpty())
		{
			ans[ansIndex] = pq.poll();
			ansIndex++;
		}

		return ans;
	}

	private boolean isDigitLog(String s) {
		int start = s.indexOf(" ") + 1;

		return Character.isDigit( s.charAt(start));
	}

	/**
	 * Less I was distracted reading the description and escaped the int that digit log only had digits
	 * @param s
	 * @return
	 */
	private boolean isDigitLogOld(String s) {
		int start = s.indexOf(" ") + 1;
		int i = start;
		while (i < s.length() && s.charAt(i) != ' ')
		{
			if (s.charAt(i) - '0' < 0 || s.charAt(i) - '0'> 9)
			{
				return false;
			}
			i++;
		}
		return true;
	}
}

/*********************
 * OTHERS SOLUTIONS
 *********************/

/**
 * Cool solution only using comparer
 * The disadvantage for my solution is that it sorts all the array.
 * But I find it clever the way it sorted the array, by using the value of 0, 1 and -1 to manipulate the order.
 * @author Nelson Costa
 *
 */
class ReorderDataInLogFilesSolution1 {
	public String[] reorderLogFiles(String[] logs) {
		Arrays.sort(logs, (log1, log2) -> {
			String[] split1 = log1.split(" ", 2);
			String[] split2 = log2.split(" ", 2);
			boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
			boolean isDigit2 = Character.isDigit(split2[1].charAt(0));
			if (!isDigit1 && !isDigit2) {
				int cmp = split1[1].compareTo(split2[1]);
				if (cmp != 0) return cmp;
				return split1[0].compareTo(split2[0]);
			}
			//returning 1 means that
			//a > b
			//returning 0 means they are equal so order is preserved
			//returning -1 means that a is smaller, if it is smaller than it appears first.
			return isDigit1 ? (isDigit2 ? 0 : 1) : -1;
		});
		return logs;
	}
}

