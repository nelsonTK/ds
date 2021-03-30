package com.data.structures.problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReconstructOriginalDigitsfromEnglish {
	
	/**
	 * @intuition
	 * 		The intuition for this problem is to find something 
	 * 		unique for each  number and used that to find the integer counts in the string.
	 * 
	 * 		1) I used a hashmap to count the numbers, than I reduce the numbers of counts according to the number. 
	 * 		if zero ocurrs once I know I have to decrease one z one r, one e, one o
	 * 
	 * 		I have build layers of uniqueness. but there was a better approach that was even more unique and didn't needed sorting.
	 * 
	 * @media
	 * 		https://imgur.com/lrlMZqn
	 * 
	 * @score
	 * 		
	 *	
	 * @time
	 * 		O(NLogN)
	 * 
	 * @space
	 * 		O(N)
	 * @param s
	 * @return
	 */
    public String originalDigits(String s) {
        
        //Unique words by tiers
        
        //(z)ero, t(w)o, fo(u)r, si(x), ei(g)ht
        Candidate [] tier1 = { 
            new Candidate('z', "zero", '0'),
            new Candidate('w', "two", '2'),
            new Candidate('u', "four", '4'),
            new Candidate('x', "six", '6'),
            new Candidate('g', "eight", '8')
            };
        
        //(o)ne, (t)ree, (s)even
        Candidate [] tier2 = {
            new Candidate('o', "one", '1'),
            new Candidate('t', "tree", '3'),
            new Candidate('s', "seven", '7')
            };
        
        //(f)ive, (n)nine
        Candidate [] tier3 = {
            new Candidate('f', "five", '5'),
            new Candidate('n', "nine", '9')
            };
        
        Candidate [][] tiers = new Candidate[][]{tier1, tier2, tier3}; 
        
        //counting word frequencies O(N)
        int [] letters = new int[26];
        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            
            letters[c - 'a']++;
        }
        
        Candidate cur;
        List<Integer> list = new ArrayList<>();
        //Calculate the exact number of ocurrences for each digit
        for (int tier = 0; tier < tiers.length; tier++) //for each tier
        {
            for (int letter = 0; letter< tiers[tier].length; letter++)//get its unique letters given tier 
            {
                cur = tiers[tier][letter];
                
                if (letters[cur.c - 'a'] > 0)
                {
                    if (cur.n == "nine") //exception
                        letters[cur.c - 'a'] /=2;
                    
                    //add to answer n times
                    int numberRepetitions = letters[cur.c - 'a']; //count the ocurrences
                    while(letters[cur.c - 'a'] > 0) //add to list of answers
                    {
                        list.add(cur.num);
                        letters[cur.c - 'a']--;
                    }
                    
                    //remove all the letters
                    for (char c : cur.n.toCharArray()) //update the counts for each of its letters
                    {
                        letters[c - 'a']-=numberRepetitions;
                    }
                }
            }
        }
        
        Collections.sort(list);
        StringBuilder ans = new StringBuilder("");
        for(int i : list)
        {
            ans.append(i+"");
        }
        
        return ans.toString();
    }
    
    class Candidate{
        Character c;
        String n; //name
        int num;
        public Candidate(Character c, String n, char num){
            this.c=c;
            this.n=n;
            this.num = num - '0';
        }
    }
}

/**
 * This solution was very interesting in the sence that the didn't need to have sorting and used the second unique characters to subtract and have the latest number.
 * very clever.
 * 
 * @author Nelson Costa
 *
 */
class ReconstructOriginalDigitsfromEnglishSolution1 {
	  public String originalDigits(String s) {
	    // building hashmap letter -> its frequency
	    char[] count = new char[26 + (int)'a'];
	    for(char letter: s.toCharArray()) {
	      count[letter]++;
	    }

	    // building hashmap digit -> its frequency
	    int[] out = new int[10];
	    // letter "z" is present only in "zero"
	    out[0] = count['z'];
	    // letter "w" is present only in "two"
	    out[2] = count['w'];
	    // letter "u" is present only in "four"
	    out[4] = count['u'];
	    // letter "x" is present only in "six"
	    out[6] = count['x'];
	    // letter "g" is present only in "eight"
	    out[8] = count['g'];
	    // letter "h" is present only in "three" and "eight"
	    out[3] = count['h'] - out[8];
	    // letter "f" is present only in "five" and "four"
	    out[5] = count['f'] - out[4];
	    // letter "s" is present only in "seven" and "six"
	    out[7] = count['s'] - out[6];
	    // letter "i" is present in "nine", "five", "six", and "eight"
	    out[9] = count['i'] - out[5] - out[6] - out[8];
	    // letter "n" is present in "one", "nine", and "seven"
	    out[1] = count['n'] - out[7] - 2 * out[9];

	    // building output string
	    StringBuilder output = new StringBuilder();
	    for(int i = 0; i < 10; i++)
	      for (int j = 0; j < out[i]; j++)
	        output.append(i);
	    return output.toString();
	  }
	}