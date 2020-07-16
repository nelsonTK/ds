package com.data.structures.problems;

import java.util.HashMap;
import java.util.Random;

/**
 * https://leetcode.com/problems/encode-and-decode-tinyurl/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class EncodeAndDecodeTinyURL {

	static EncodeAndDecodeTinyURL e = new EncodeAndDecodeTinyURL ();
	public static void main(String[] args) {

		 Codec codec = e.new Codec();
		 String url = "https://www.youtube.com/watch?v=uL0xP57negc";
		 codec.decode(codec.encode(url));
		 
		 System.out.println(codec.encode(url));
	}

	/*********************************
	 * SOLUTION 1
	 ********************************/
	/***
	 * 
	 * @intuition
	 * 		There were several ways I could do this faster, one of them would be to just code or decode an integer for each new id.
	 * 		But I decided to performe something more structured.
	 * 
	 * 		I would love to have something stateless but I've not!
	 * 
	 * 		My Encoding Structure
	 * 			My code is a variable size code.
	 * 			it is composed by a fixed base size code
	 * 			and a variable size code which represents the number of urls that share that base code.
	 * 			
	 * 		Encode
	 * 			I get 2 elements from each domain, from there I get only random 5
	 * 			I had the counter to the front of the basecode
	 * 
	 * 		Decode is just to get the element from the hashmap.
	 * 
	 * 		With this solution I can have a very big number of supported codes.
	 * 		
	 * 		like 2*10^31 per each possible 5 character code.
	 * 
	 * @optimizations
	 * 		1) Forgot to use StringBuilder...
	 * 		1) I could do the basecode with a set of all possible characters
	 * 
	 * 
	 * @score
	  		Runtime: 16 ms, faster than 6.38% of Java online submissions for Encode and Decode TinyURL.
			Memory Usage: 40.2 MB, less than 15.95% of Java online submissions for Encode and Decode TinyURL.
	
	 * @time
	 * @space
	 * 
	 * @author Nelson Costa
	 *
	 */
	public class Codec {

	    HashMap<String, String> hashToFullUrl;
	    HashMap<String, Integer> base; //baseCode
	    String baseUrl = "http://tinyurl.com/";
	    public Codec(){
	        hashToFullUrl = new HashMap<>();
	        base = new HashMap<>();
	    }

	    /**
	     * @time   O(L + N)
	     * @space  O(N)
	     * @param longUrl
	     * @return
	     */
	    public String encode(String longUrl) {
	        	        
	        String [] domains = longUrl.trim().split("/"); //O(L), L string size
	        String candidateLetters = "";
	        Random r = new Random();
	        int randomChar;
	        
	        //get 2 elements from each partition
	        for (String s : domains) //O(N) , N equals to number of domains
	        {            
	            if (!s.equals(""))
	            {
	                for (int i = 0; i < 2; i ++)
	                {
	                    randomChar = r.nextInt(s.length());
	                    candidateLetters += s.charAt(randomChar);
	                }
	            }
	        }
	        
	        //get 5 random elements 
	        String baseCode = "";
	        for (int i = 0; i < 5; i++) //O(5) -> O(1)
	        {
	            randomChar = r.nextInt(candidateLetters.length());
	            baseCode += candidateLetters.charAt(randomChar);
	        }
	        
	        //while base code is not 5 fill with random letters
	        while (baseCode.length() < 5){ //O(5) -> O(1)
	            randomChar = r.nextInt(25);
	            baseCode += (char) (randomChar + 'a');
	        }
	        
	        //link Id
	        int id = base.getOrDefault(baseCode, 0) + 1;
	        base.put(baseCode, id);
	        hashToFullUrl.put(baseCode + id, longUrl);
	        
	        return baseUrl + baseCode + id;
	    }

	    /**
	     * @time   O(L)
	     * @space  O(N)
	     * @param shortUrl
	     * @return
	     */
	    public String decode(String shortUrl) {
	        shortUrl.split("/");
	        String [] domains = shortUrl.split("/"); // O(L), L equals to string Size
	        
	        String decoded = hashToFullUrl.get(domains[domains.length - 1]);
	        
	        return decoded == null ? "" : decoded;
	    }
	}
}




/*********************
* OTHERS SOLUTIONS
*********************/
/**
 * I dont like the fact that they are looping and trying to find a code when encoding. it does not make sense to me.
 * 
 * But it is a very concise way of writing this problem
 * 
 * @score
 		Runtime: 6 ms, faster than 45.99% of Java online submissions for Encode and Decode TinyURL.
		Memory Usage: 39.6 MB, less than 52.89% of Java online submissions for Encode and Decode TinyURL.
 * 
 * @author Nelson Costa
 *
 */
class EncodeAndDecodeTinyURLSolution5 {
    String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    HashMap<String, String> map = new HashMap<>();
    Random rand = new Random();
    String key = getRand();

    public String getRand() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(alphabet.charAt(rand.nextInt(62)));
        }
        return sb.toString();
    }

    public String encode(String longUrl) {
        while (map.containsKey(key)) {
            key = getRand();
        }
        map.put(key, longUrl);
        return "http://tinyurl.com/" + key;
    }

    public String decode(String shortUrl) {
        return map.get(shortUrl.replace("http://tinyurl.com/", ""));
    }
}

