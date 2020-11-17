package com.data.structures.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.data.structures.problems.ds.LeetCodeExercise;
import com.data.structures.problems.ds.TreeNode;

/**
 * https://leetcode.com/problems/analyze-user-website-visit-pattern/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class AnalyzeUserWebsiteVisitPattern extends LeetCodeExercise{

	static AnalyzeUserWebsiteVisitPattern a = new AnalyzeUserWebsiteVisitPattern();
	static AnalyzeUserWebsiteVisitPatternUnofficialSolution2 a2 = new AnalyzeUserWebsiteVisitPatternUnofficialSolution2();
	public static void main(String[] args) {

		//		String[] username = {"joe","joe","joe","james","james","james","james","mary","mary","mary"};
		//		String[] website = {"home","about","career","home","cart","maps","home","home","about","career"};
		//		int [] timestamp = stringToArray("[1,2,3,4,5,6,7,8,9,10]");


		//		String[] username = {"zkiikgv","zkiikgv","zkiikgv","zkiikgv"};
		//		int [] timestamp = stringToArray("[436363475,710406388,386655081,797150921]");
		//		String[] website = {"wnaaxbfhxp","mryxsjc","oz","wlarkzzqht"};



		String[] username = {"h","eiy","cq","h","cq","txldsscx","cq","txldsscx","h","cq","cq"};
		int [] timestamp = stringToArray("[527896567,334462937,517687281,134127993,859112386,159548699,51100299,444082139,926837079,317455832,411747930]");
		String[] website = {"hibympufi","hibympufi","hibympufi","hibympufi","hibympufi","hibympufi","hibympufi","hibympufi","yljmntrclw","hibympufi","yljmntrclw"};

		//Testing equals for "primitive types"
		List<Integer> l1 = Arrays.asList(1,2,3);
		List<Integer> l2 = Arrays.asList(1,2,3);
		System.out.println("lists equals: " + l1.equals(l2));

		//Testing equals for objects
		List<TreeNode> l3 = Arrays.asList(new TreeNode[]{new TreeNode(1)});
		List<TreeNode> l4 = Arrays.asList(new TreeNode[]{new TreeNode(1)});
		System.out.println("lists equals: " + l3.equals(l4));
		
		//Testing equals for arrays
		int [] int1 = {1,2,3};
		int [] int2 = {1,2,3};
		System.out.println("arrays equals 01: " + int1.equals(int2));
		
		System.out.println("arrays equals 02: " + Arrays.equals(int1, int2));
		

		
		a.mostVisitedPattern(username, timestamp, website);
		a2.mostVisitedPattern(username, timestamp, website);
		

		
	}


	//we want to find out the 3 most visited 3 sequence websites by all the users
	//however it only counts if 3 sequence was met, and it is garanteed that exists a user with a tree sequence
	//sliding windows do not count because we care about all possible combinations going forward
	/**
	 *		@intuition
	 *			the intuition for this question was to:
	 *				1) create tuples with user, website and timestamp.
	 *				2) sort by user and sequence to ease the sequence check 
	 *				3) check all possible combinations for each user and store the 3-sequences in an TreeMap
	 *				4) go through the treemap and get the lexicographically smalles combination with the maximum count
	 *
	 *			I found it a tough question.
	 *
	 *		@problemaClarification
	 *			1) No repeated sequences from users counts. e.g. A, A, A, B, A, C. there is only one sequence count for A,A,A, one ABA, AAB, ABC, etc...
	 *			2) Is a Sequence but not contiguous sequence only, values may be spread. e.g. A, B, C
	 *			3) we want the smallest lexicographically answer in case of multiple candidates
	 *			4) we can have sequences with the same website
	 *
	 *
	 *		@optimizations one think to notice compating to other solutions is that I sort by time and user, it is part of my algorithm.
	 *			Other users just sort by time. and create hashmaps with user journeys
	 *
	 *		@score
	 *			Runtime: 54 ms, faster than 11.60% of Java online submissions for Analyze User Website Visit Pattern.
	 *			Memory Usage: 46.3 MB, less than 5.78% of Java online submissions for Analyze User Website Visit Pattern.
	 *
 	 *       @debug
 	 *           1) contest style
	 *
 	 *       @fail
	 *            1) array out of bounds, I was using i instead of j, I was using j instead of count
	 *           2) class cast exception, I was casting TreeMap for Triplet
	 *           3) nullPointerException, I mistakenly took default of string for "", what a shame
	 *           4) I was comparing the wrong tupple guy, and I was using equals to compare strings
	 *           5) my compare was wrong, and it was causing a very strange behavior
	 *           6) the transition for the next element was wrong, because in the end of the for i gets incremented
	 *			   the fix was so reduce j before assign to i.
	 *		  	 7) max variable was not being assigned in the answer construction
	 *			 8) I didnt too into account that the solution might not be contiguous, which might invalidate sliding windows solution
	 *			 9) Heavy problems with references, I was chinging references without noticing and messing up all the results
	 *			 10) I think for the same user, each sequence only counts once, what a communication shame
	 *			 11) failed because of not having hashcode in the object definition, only equals and I was missing the bucket in the set.
	 *
	 *		@time
	 *			O(N) + O(NLogN) + O(N^3LogN) + O(N)
	 *
	 *		@space
	 *			O(N)
	 *
	 **/
	public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {


		Set<Triplet> userSequence = new HashSet<>();
		int n = username.length;

		//define sort for sequence count
		TreeMap<Triplet, Integer> sequenceCount = new TreeMap<Triplet, Integer>(
				(a, b) -> {
					for (int i = 0; i < 3; i++){

						int compare = a.triplet[i].compareTo(b.triplet[i]);

						if (compare < 0)
							return compare;
						if (compare == 0)
							continue;
						if (compare > 0)
							return compare;
					}

					return 0;
					});


		Tuple [] tuples = new Tuple[n];

		/**
		 * creating tupples O(N)
		 */
		for (int i = 0; i < n; i++)
		{
			tuples[i] = new Tuple(username[i], website[i], timestamp[i]);
		}

		/**
		 * sort tuple by user and time O(NLogN)
		 */
		Arrays.sort(tuples, (a,b) -> {

			if(a.user.compareTo(b.user) > 0)
				return 1;
			else if (a.user.compareTo(b.user) < 0)
				return -1;
			else {
				return Integer.compare(a.tstamp, b.tstamp);
			}

		});

		/**
		 * Finding sequences for users. only unique sequences per user count
		 */
		String curUser = "";
		int j,w;
		Triplet curTriplet;

		//This code is a bit messy
		for (int i = 0; i < n; i++) //O(N)
		{
			if (!curUser.equals(tuples[i].user))
			{
				userSequence = new HashSet<>();
			}

			curUser = tuples[i].user;
			j = i + 1;

			while (j < n && tuples[j].user.equals(curUser)) //O(N)
			{
				w = j + 1;

				while (w < n && tuples[w].user.equals(curUser)) //O(N)
				{
					curTriplet = new Triplet();
					curTriplet.triplet[0] =  tuples[i].website;
					curTriplet.triplet[1] =  tuples[j].website;
					curTriplet.triplet[2] =  tuples[w].website;

					if (!userSequence.contains(curTriplet))
					{
						sequenceCount.put(curTriplet, sequenceCount.getOrDefault(curTriplet, 0) + 1); //O(logn)
						userSequence.add(curTriplet);
					}

					w++;
				}
				j++;
			}
		}

		/**
		 * Building the answer O(N)
		 */
		int maxCount = 0;
		Triplet ans = new Triplet();

		for (Triplet t : sequenceCount.keySet())
		{
			if (sequenceCount.get(t) > maxCount)
			{
				ans = t;
				maxCount = sequenceCount.get(t);
			}
		}

		return Arrays.asList(ans.triplet);
	}
}
class Tuple{
	String user;
	String website;
	int tstamp;

	public Tuple(String u, String w, int t)
	{
		user = u;
		website = w;
		tstamp = t;
	}

	/*
	public String toString() {
		return user + " " + website + " " + tstamp;
	}
	 */
}



class Triplet {
	String [] triplet;
	public Triplet(){
		triplet = new String[3];
	}

	public boolean isFull(){
		return triplet[2] != null;
	}

	@Override
	public boolean equals(Object t)
	{
		Triplet other = (Triplet) t;
		return  triplet[0].equals(other.triplet[0]) && 
				triplet[1].equals(other.triplet[1]) && 
				triplet[2].equals(other.triplet[2]);
	}

	//Needed becaus I needed this different objects to go to the same bucket
	@Override
	public int hashCode() {
		int hash = 0;

		for (int i = 0; i < 3; i++)
		{
			for (char c : triplet[i].toCharArray())
			{
				hash += c + 7;
			}
		}

		return hash;
	}
	/*
	public String toString(){
		String ans = "";
		for (String s: triplet)
			ans += s + "|";

		return ans;
	}
	 */
}


/**
 * Top solution
 * @author Nelson Costa
 *
 */
class AnalyzeUserWebsiteVisitPatternUnofficialSolution2{
    class Node{
        int time;
        String name;
        String web;
        
        Node(int t, String n, String w){
            time = t;
            name = n;
            web = w;
        }
    }

    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        List<Node> list = new ArrayList<>();
        
        // add each web access to a list
        for(int i = 0; i < username.length; i++){
            list.add(new Node(timestamp[i], username[i], website[i]));
        }
        
        //sort by the time they entered the website
        Collections.sort(list, (x,y)->x.time-y.time);
        
        //get a list of websites entered by the user
        Map<String, List<String>> userWebMap = new HashMap<>();
        for(int i = 0; i < list.size(); i++){
            userWebMap.putIfAbsent(list.get(i).name, new ArrayList<>());
            userWebMap.get(list.get(i).name).add(list.get(i).web);
        }
        
        //for each user generate each 3 website pair in order
        Map<List<String>, Integer> webUserFreqMap = new HashMap<>();
        for(String user: userWebMap.keySet()){
            Set<List<String>> seqs = generateWebsite(userWebMap.get(user));
            
            for(List<String> seq: seqs){//count the number of times thouse pairs show up
                webUserFreqMap.put(seq, webUserFreqMap.getOrDefault(seq, 0)+1);
            }
        }
        
        // go through each set of 3 and grab the one that occours the most
        int max = 0;
        List<String> res = new ArrayList<>();
        
        for(List<String> l: webUserFreqMap.keySet()){
            if(webUserFreqMap.get(l) > max){
                res = l;
                max = webUserFreqMap.get(l);
            }else if(webUserFreqMap.get(l) == max){ //if they occur the sam out of times we have to figure which one occours alphabetized
                for(int i = 0; i < 3; i++){
                    if(l.get(i).compareTo(res.get(i)) < 0){
                        res = l;
                        break;
                    }else if(l.get(i).compareTo(res.get(i)) > 0){
                        break;
                    }
                }
            }
        }
        
        return res;
    }
    
    // generates each 3 website combination the list is per user, inorder by time visited
    //uses collection as key
    Set<List<String>> generateWebsite(List<String> list){
        Set<List<String>> set = new HashSet<>();
        
        for(int i = 0; i < list.size(); i++){
            for(int j = i+1; j < list.size(); j++){
                for(int k = j+1; k < list.size(); k++){
                    List<String> l = new ArrayList<>();
                    l.add(list.get(i));
                    l.add(list.get(j));
                    l.add(list.get(k));
                    set.add(l);
                }
            }
        }
        return set;
    }
}

/**
 * @intuition
 * 		the intuition is create visites with user, timestamp, and website
 * 		than for each user create a journey
 * 		than for each journey find all possible combinations
 * 		from those combinations find all maximum elements
 * 		from all those maximum elements find the minimum lexicographically maximum.
 * 		is simple it's okay
 * 
 * 
 * 		One thing that he does different is that he just sorts by time, and I sort by time and user. that takes me time.
 * 
 * https://www.youtube.com/watch?v=wA-M2fvJOvY&t=327s
 * 
 * @score
 * 		Runtime: 41 ms, faster than 19.96% of Java online submissions for Analyze User Website Visit Pattern.
		Memory Usage: 46 MB, less than 6.52% of Java online submissions for Analyze User Website Visit Pattern.
 * 
 * @author Nelson Costa
 *
 */
class AnalyzeUserWebsiteVisitPatternUnofficialSolution1 {
	class Visit {
		String username;
		int timestamp;
		String website;
		public Visit(String username, int timestamp, String website) {
			this.username = username;
			this.timestamp = timestamp;
			this.website = website;
		}

		public String toString() {
			return username+" "+timestamp+" "+website;
		}

	}

	Set<List<String>> generate3(List<String> history) {
		Set<List<String>> output = new HashSet<>();
		for(int i = 0; i < history.size(); i++) {
			for(int j = i + 1; j < history.size(); j++) {
				for(int k = j + 1; k < history.size(); k++) {
					List<String> l = new ArrayList<>();
					l.add(history.get(i));
					l.add(history.get(j));
					l.add(history.get(k));
					output.add(l);
				}
			}
		}
		return output;
	}

	public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
		Visit[] visits = new Visit[username.length];
		for(int i = 0; i < username.length; i++) {
			visits[i] = new Visit(username[i], timestamp[i], website[i]);
		}

		Arrays.sort(visits, (v1, v2) -> {
			return v1.timestamp - v2.timestamp;
		});

		//creates a map for each user with its journey
		HashMap<String, List<String>> userJourney = new HashMap<>();
		for(Visit v : visits) {
			userJourney.putIfAbsent(v.username, new ArrayList<>());
			userJourney.get(v.username).add(v.website);
		}

		//create and fill possibilites for 3 sequences
		HashMap<List<String>, Integer> candidateOutput = new HashMap<>();
		for(List<String> history : userJourney.values()) {
			Set<List<String>> history3Comb = generate3(history);
			for(List<String> h : history3Comb) {
				candidateOutput.putIfAbsent(h, 0);
				candidateOutput.put(h, candidateOutput.get(h) + 1);
			}
		}

		//Find max candidates
		List<List<String>> maxCandidate = new ArrayList<>();
		int max = 0;
		for(Map.Entry<List<String>, Integer> entry : candidateOutput.entrySet()) {
			if(max < entry.getValue()) {
				maxCandidate.clear();
				max = entry.getValue();
				maxCandidate.add(entry.getKey());
			} else if(max == entry.getValue()) {
				maxCandidate.add(entry.getKey());
			}
		}
		//System.out.println(maxCandidate);
		List<String> output = null;
		String o = null;

		//Find less lexicographically element
		for(List<String> c : maxCandidate) {
			StringBuilder sb = new StringBuilder();
			for(String s : c) sb.append(s+" ");
			if(o == null || o.compareTo(sb.toString()) > 0) {
				o = sb.toString();
				output = c;
			}
		}

		return output;
	}

}
