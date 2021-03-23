package com.data.structures.problems.contest;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/design-authentication-manager/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class b48DesignAuthenticationManager {
	
	/**
	 * Nothing special
	 * 
	 * @time
	 * 		countUnexpiredTokens O(N)
	 * 		All others O(1)
	 * 
	 * @author Nelson Costa
	 *
	 */
	class AuthenticationManager {

	    HashMap<String, Integer> authExp;    
	    int ttl;
	    
	    public AuthenticationManager(int timeToLive) {
	        authExp = new HashMap<>();
	        ttl = timeToLive;
	    }
	    
	    public void generate(String tokenId, int currentTime) {
	        authExp.put(tokenId, currentTime + ttl);
	    }
	    
	    public void renew(String tokenId, int currentTime) {
	        if (authExp.get(tokenId) != null && authExp.get(tokenId) > currentTime )
	        {
	            authExp.put(tokenId, currentTime + ttl);
	        }
	    }
	    
	    public int countUnexpiredTokens(int currentTime) {
	        int count = 0;
	        
	        for (String k : authExp.keySet())
	        {
	            if (authExp.get(k) > currentTime)
	            {                
	                count++;
	            }
	        }
	        
	        return count;
	    }
	}
}
