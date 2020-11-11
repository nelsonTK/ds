package com.data.structures.problems;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class WebCrawler {


	 /*********************************
	 * SOLUTION 1
	 ********************************/
	// This is the HtmlParser's API interface.
	// You should not implement it, or speculate about its implementation
	interface HtmlParser {
		public List<String> getUrls(String url);
	}
	/** 
	 * @intuition	
	 * 	This problem is to be solved with a hashset and optionally a hashmap.
	 *  we used a bfs to solve this problem, I turned the problem a little bit more complicated because I didn't wanted to be comparing strings.
	 *  I prefered to be comparing Id's, so my comparison is with the hostname ids, thats why I use a hashmap.
	 *  
	 *  BLUEPRINT
	 *  
	 *  //add start url to queue
	 *  //while queue not empty
	 *  //get current
	 *  //add to list of answers
	 *  //mark visited/seen
	 *  //if neighbor not seen
	 *  //if has the same host add to queue / compare ids
	 *  //update hostid with not present
	 * 	
	 * @score
	 * 		Runtime: 4 ms, faster than 86.67% of Java online submissions for Web Crawler.
	 * 		Memory Usage: 46.6 MB, less than 5.03% of Java online submissions for Web Crawler.
	 *  
	 * @fail 
	 * 		1) I was not adding to the queue 
	 * 		2) forgot to add the current element to the answer
	 **/
	public List<String> crawl(String startUrl, HtmlParser htmlParser) {
		//mapping objects
		HashMap<String, Integer> hostId = new HashMap<>();
		Set<String> seen = new HashSet<>();

		//queue
		Queue<String> q = new ArrayDeque<>();
		q.add(startUrl);

		//ids
		String mainHost = getHost(startUrl);
		seen.add(startUrl);
		hostId.put(mainHost, 0);

		String cur;
		List<String> children;
		List<String> ans = new ArrayList<>();
		while(!q.isEmpty())
		{
			cur = q.poll();

			ans.add(cur);

			children = htmlParser.getUrls(cur);

			for(String child: children)
			{
				//does not contain child we need to check if the urls are equal
				if(!seen.contains(child))
				{
					String childHost = getHost(child);

					//if host is already mapped we add this new value
					if (hostId.containsKey(childHost))
					{
						if (hostId.get(mainHost).equals(hostId.get(childHost))) //comparing Ids
						{
							q.add(child);
						}
					}
					else //does not contain childHost means its not a match
					{
						hostId.put(childHost, hostId.size());
					}

					seen.add(child);
				}
			}
		}

		return ans;
	}

	private String getHost(String url){
		int start = "http://".length();
		int i = start;

		StringBuilder host = new StringBuilder("");
		while (i < url.length() && url.charAt(i) != '/' )
		{
			host.append(url.charAt(i));
			i++;
		}

		return host.toString();
	}
}