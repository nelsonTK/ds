package com.data.structures.problems.contest;

/**
 * https://leetcode.com/problems/crawler-log-folder/
 * EASY
 * @author Nelson Costa
 *
 */
public class w208CrawlerLogFolder {

	/**
	 * @intuition
	 * 		if you find a back instruction you reduce the count
	 * 		if equals to current folder you do nothing
	 * 		else it is a instruction to enter inside a folder
	 * 
	 * @score
	 * 		Runtime: 1 ms, faster than 100.00% of Java online submissions for Crawler Log Folder.
	 *		Memory Usage: 38.7 MB, less than 100.00% of Java online submissions for Crawler Log Folder.
	 * 
	 * @time
	 * 		O(M*N)
	 * 		M = LogsLen;
	 * 		N = WordSize
	 * @space
	 * 		O(1)
	 * 
	 * @param logs
	 * @return
	 */
	public int minOperations(String[] logs) 
	{
		int count = 0;
		for (String l : logs)
		{
			if (l.equals("../")){
				count--;
			}
			else if (l.equals("./"))
			{

			}
			else {
				count++;
			}

			if (count < 0) count = 0;

		}

		return count < 0 ? 0 : count;
	}
}
