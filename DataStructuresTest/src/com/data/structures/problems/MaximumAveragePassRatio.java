package com.data.structures.problems;

import java.util.PriorityQueue;

/**
 * MEDIUM
 * https://leetcode.com/problems/maximum-average-pass-ratio/
 * @author Nelson Costa
 *
 */
public class MaximumAveragePassRatio {

	
	 
    /**
     * @intuition
     * 		It is an amazing question.
     * 		to find the best candidate we have to find out which candidate will increase the most by adding one element.
     * 		This is the rational behind it. to find that we have to test that situation for every member.
     * 		And then we will get the element that will increase the most every time. That's basically it.
     *     
     *     
    **/
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        
        //create PriorityQueue
        double ans = 0;
        ClsRatio cur;
        PriorityQueue<ClsRatio> pq = new PriorityQueue<>( (a,b) -> {
            
            return Double.compare(b.getRatio(), a.getRatio());   
        });
        
        
        for (int [] cls : classes)
            pq.add(new ClsRatio(cls));
        
        //get the best ratio and add an extra student
        while (extraStudents > 0)
        {
            cur = pq.poll();
            cur.addExtraStudent();
            extraStudents--;
            pq.add(cur);
        }
        
        //sum all averages
        while (!pq.isEmpty())
        {
            cur = pq.poll();
            ans += cur.getAvg();
        }
        
        return ans/(double)classes.length;
    }
    
    
    class ClsRatio{
        int [] cls;
        double ratio;//rationOfPlusOne;
        
        ClsRatio (int [] c)
        {
            cls = c;
            ratio = getRatio();
        }
        
        public double getRatio()
        {
            return ((cls[0] + 1)/(double)(cls[1] + 1)) - (cls[0]/(double)cls[1]);
        }
        
        public int [] getCls()
        {
            return cls;
        }
        
        public void addExtraStudent()
        {
            cls[0]++;
            cls[1]++;
        }
        
        public double getAvg(){
            return cls[0]/(double)cls[1];
        }
    }
}
