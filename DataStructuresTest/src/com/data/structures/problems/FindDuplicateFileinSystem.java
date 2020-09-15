package com.data.structures.problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * https://leetcode.com/problems/find-duplicate-file-in-system/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class FindDuplicateFileinSystem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


	 /*********************************
	 * SOLUTION 1
	 ********************************/
	/** 
	 * 
	 * 	@intuition
	 * 		The intuition was simple, just to group files by content
	 * 		and using the string functions like split and indexOf to parse the content
	 * 
	 * 	@score
	 * 		Runtime: 24 ms, faster than 86.66% of Java online submissions for Find Duplicate File in System.
	 * 		Memory Usage: 49 MB, less than 61.14% of Java online submissions for Find Duplicate File in System.
	 * 	
	 * 	@fail
	 *  	1) failed because of doing bad scaping
	 *  
	 *  @time
	 *  	O(N * S * F * FS) + O(G)
	 *  
	 *  		N paths
	 *  		S string length
	 *  		F number of files
	 *  		FS File size
	 *  		G Number of groups
	 *  
	 *  @space
	 *  	O(FS)
	 **/
	public List<List<String>> findDuplicate(String[] paths) {

		if (paths == null | paths.length == 0)
			return new ArrayList<List<String>>();


		String [] files;
		String directory, file;
		HashMap<String, List<String>> contentFiles = new HashMap<>();

		//file can be empty

		for (String dir : paths) //O(N)
		{
			files = dir.split(" "); //O(S)
			directory = files[0];

			for (int i = 1; i < files.length; i++) //O(F) F is files
			{
				file = files[i];
				int openParenthesesIndex = file.indexOf("("); //O(FS) FS is size of file name
				String content = file.substring(openParenthesesIndex + 1, file.length()); //O(FS)          
				contentFiles.computeIfAbsent(content, k -> new ArrayList<String>()).add(directory+"/"+file.substring(0, openParenthesesIndex));

			}
		}

		List<List<String>> ans = new ArrayList<List<String>>();

		for (List<String> v : contentFiles.values()) //O(G) G is the number of groups which can be greater then O(S)
		{
			if (v.size() > 1)
				ans.add(v);
		}
		return ans;

	}
}


/*********************
* OTHERS SOLUTIONS
*********************/
/**
 * The same concept then my solution however my solution is more efficient
 * 
 * @author Nelson Costa
 *
 */
class FindDuplicateFileinSystemSolution2 {
    public List < List < String >> findDuplicate(String[] paths) {
        HashMap < String, List < String >> map = new HashMap < > ();
        for (String path: paths) {
            String[] values = path.split(" ");
            for (int i = 1; i < values.length; i++) {
                String[] name_cont = values[i].split("\\(");
                name_cont[1] = name_cont[1].replace(")", "");
                List < String > list = map.getOrDefault(name_cont[1], new ArrayList < String > ());
                list.add(values[0] + "/" + name_cont[0]);
                map.put(name_cont[1], list);
            }
        }
        List < List < String >> res = new ArrayList < > ();
        for (String key: map.keySet()) {
            if (map.get(key).size() > 1)
                res.add(map.get(key));
        }
        return res;
    }
}

