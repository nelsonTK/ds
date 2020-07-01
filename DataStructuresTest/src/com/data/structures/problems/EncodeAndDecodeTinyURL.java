package com.data.structures.problems;

import java.util.HashMap;
import java.util.Random;

public class EncodeAndDecodeTinyURL {

	static EncodeAndDecodeTinyURL e = new EncodeAndDecodeTinyURL ();
	public static void main(String[] args) {

		 Codec codec = e.new Codec();
		 String url = "https://www.youtube.com/watch?v=uL0xP57negc";
		 codec.decode(codec.encode(url));
		 
		 System.out.println(codec.encode(url));
	}


	/***
	 * 
	 * @intuition
	 * 		There were several ways I could do this faster, one of them would be to just code or decode and integer for each new id.
	 * 		But I decided to performe something more structured.
	 * 
	 * 		I would love to have something stateless but I've not!
	 * 
	 * @optimizations
	 * 		Forgot to use StringBuilder...
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
	    
	    /*
	    
	    @time   O(L + N)
	    @space  O()
	    
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

	    /*
	    
	    @time   O(L)
	    @space  O(N)
	    */
	    public String decode(String shortUrl) {
	        shortUrl.split("/");
	        String [] domains = shortUrl.split("/"); // O(L), L equals to string Size
	        
	        String decoded = hashToFullUrl.get(domains[domains.length - 1]);
	        
	        return decoded == null ? "" : decoded;
	    }
	}
}
