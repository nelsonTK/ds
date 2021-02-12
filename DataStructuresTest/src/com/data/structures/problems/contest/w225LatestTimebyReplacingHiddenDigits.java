package com.data.structures.problems.contest;

public class w225LatestTimebyReplacingHiddenDigits {

	/**
	 * @time
	 * O(N)
	 * @param time
	 * @return
	 */
	public String maximumTime(String time) {
        /*
        hour
        1st :
            if next < 4
                2
            else
                1
            
            if next ?
               2  
                
        
        2nd             
            1 -> 9
            0 -> 9
            2 -> 3
            
        minute
        1st -> 5
        2nd -> 9
       */        
                
        StringBuilder sb = new StringBuilder(time);
        int hFirst = time.charAt(0) == '?' ? -1 : time.charAt(0) - '0';
        int hSecond = time.charAt(1) == '?' ? -1 : time.charAt(1) - '0';
        int mFirst = time.charAt(3) == '?' ? -1 : time.charAt(3) - '0';
        int mSecond = time.charAt(4) == '?' ? -1 : time.charAt(4) - '0';
        
        //hour
        if (hFirst == -1)
        {
            if (hSecond < 4)
            {
                sb.setCharAt(0,'2');
                hFirst = 2;
            }
            else {
                 sb.setCharAt(0,'1');
                hFirst = 1;
            }
        }
        
        if (hSecond == -1)
        {
            if (hFirst == 2)
            {
                sb.setCharAt(1,'3');
                //hSecond = 3;
            }
            else
            {
                sb.setCharAt(1,'9');
                //hSecond = 1;                
            }
        }
        
        
        if (mFirst == -1){
            sb.setCharAt(3,'5');
        }        
        
        if (mSecond == -1)
        {
            sb.setCharAt(4,'9');
        }
        
        return sb.toString();
    }
}
