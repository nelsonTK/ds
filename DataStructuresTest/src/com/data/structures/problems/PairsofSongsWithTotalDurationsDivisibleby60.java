package com.data.structures.problems;

public class PairsofSongsWithTotalDurationsDivisibleby60 {

    /**
    
    Runtime: 2 ms, faster than 99.70% of Java online submissions for Pairs of Songs With Total Durations Divisible by 60.
Memory Usage: 44.4 MB, less than 5.78% of Java online submissions for Pairs of Songs With Total Durations Divisible by 60.
    
    **/
    public int numPairsDivisibleBy60old(int[] time) {
        
        int result = 0;
        for (int i = 0; i < time.length; i++)
        {
            for (int j = i+1; j < time.length; j++)
            {
                if ((time[i] + time[j]) % 60 == 0)
                {
                    result++;
                }
            }
        }
        
        return result;
    }    
    public int numPairsDivisibleBy60(int[] time) {
        if (time == null)
            return 0;
        
        int [] modulo = new int[60];
        int result = 0;
        //O(N)
        for (int i = 0; i < time.length; i++)
        {
            modulo[time[i] % 60]++;            
        }
        
        //O(1)
        //handle the already divisible elements
        result = modulo[0]*(modulo[0]-1)/2;
        
        //HashSet<String> set = new HashSet<>();
        
        boolean [] fakeSet = new boolean[3542]; 
        int max, min;
        for (int i = 1; i < modulo.length; i++)
        {
            int complement = 60 - i;
            
            //String code = Math.min(i, complement) + " " + Math.max(i, complement);
            max =  Math.max(i, complement);
            min =  Math.min(i, complement);
            //if (!set.contains(code))
            if (!fakeSet[60*max+min])
            {
                if(i != 30)
                    result += modulo[i] * modulo[complement];
                else
                    result += modulo[i]*(modulo[i]-1)/2;
                
                //set.add(code);
                fakeSet[60*max+min] = true;
            }
        }
        
        return result;
    }
}
kjhgtfkhgf