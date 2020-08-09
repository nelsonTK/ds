package com.data.structures.problems;

/**
 * https://leetcode.com/problems/compare-version-numbers
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class CompareVersionNumbers {

	static CompareVersionNumbers s = new CompareVersionNumbers ();
	public static void main(String[] args) {
		String s1 = ".5";
		String s2 = "1.0";
		s.compareVersion(s1, s2);
	}
	
	/*********************************
	 * SOLUTION 1
	 ********************************/
    /** 
     * @intuition
     * 		It is a two pass solution where I first split the string and then I compare each value
     * 		If the one of the versions is too short we "add" a zero.
     * 
     * @score
     * 		Runtime: 1 ms, faster than 91.05% of Java online submissions for Compare Version Numbers.
			Memory Usage: 37.5 MB, less than 34.69% of Java online submissions for Compare Version Numbers.
     * 
     * @comments
     * 		There is one step at the for loop which I could eliminate because it is never possible, and it is the verification of an empty string.
     * 		It never happens unless there are two dots strait and a number following. this is a constrait the is sure to never happen.
     * 		I read through and miss it
     * 
     * @fail   
     * 		1) I failed because I was using a "." to split what it is a regex input, instead of normal string
     * 
     * @time
     * 		O(N + M + MAX(N, M))
     * 			split both strings + for loop
     * 
     * @space
     * 		O(N + M)
     * 
    **/
    public int compareVersion(String v1, String v2) {
        
        String [] split1 = v1 == null || v1.length() == 0? "".split(".") : v1.split("\\.");
        String [] split2 = v2 == null || v2.length() == 0? "".split(".") : v2.split("\\.");

        int i1, i2;
        for (int i = 0; i < Math.max(split1.length, split2.length); i++)
        {
            i1 = i < split1.length ? (split1[i] == "" ? 0: Integer.parseInt(split1[i])) : 0;
            i2 = i < split2.length ? (split2[i] == "" ? 0: Integer.parseInt(split2[i])) : 0;
            
            if (i1 > i2) return 1;
            else if (i1 < i2)return -1;
        }
        
        return 0;        
    }
}


/*********************
* OTHERS SOLUTIONS
*********************/
/**
 * This is a one pass solution that chunks the version number in pieces and returns the value
 * @author Nelson Costa
 *
 */
class CompareVersionNumbersSolution2 {
	  public Pair<Integer, Integer> getNextChunk(String version, int n, int p) {
	    // if pointer is set to the end of string
	    // return 0
	    if (p > n - 1) {
	      return new Pair(0, p);
	    }
	    // find the end of chunk
	    int i, pEnd = p;
	    while (pEnd < n && version.charAt(pEnd) != '.') {
	      ++pEnd;
	    }
	    // retrieve the chunk
	    if (pEnd != n - 1) {
	      i = Integer.parseInt(version.substring(p, pEnd));
	    } else {
	      i = Integer.parseInt(version.substring(p, n));
	    }
	    // find the beginning of next chunk
	    p = pEnd + 1;

	    return new Pair(i, p);
	  }

	  public int compareVersion(String version1, String version2) {
	    int p1 = 0, p2 = 0;
	    int n1 = version1.length(), n2 = version2.length();

	    // compare versions
	    int i1, i2;
	    Pair<Integer, Integer> pair;
	    while (p1 < n1 || p2 < n2) {
	      pair = getNextChunk(version1, n1, p1);
	      i1 = pair.getKey();
	      p1 = pair.getValue();

	      pair = getNextChunk(version2, n2, p2);
	      i2 = pair.getKey();
	      p2 = pair.getValue();
	      if (i1 != i2) {
	        return i1 > i2 ? 1 : -1;
	      }
	    }
	    // the versions are equal
	    return 0;
	  }
	}
