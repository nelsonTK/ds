package com.data.structures.problems.contest;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/number-of-students-unable-to-eat-lunch/
 * EASY
 * @author Nelson Costa
 *
 */
public class b42NumberofStudentsUnabletoEatLunch {
    public int countStudents(int[] students, int[] sandwiches) {
        //0 circular
        //1 square
        //students has the prefered sandwich
        //sandwish has the stack of sanduiches
        
        Deque<Integer> studs = new ArrayDeque<>();
        for (int s : students)
            studs.addLast(s);
        
        boolean used = false;
        for (int sw : sandwiches)
        {
            used = false;
            for (int i = 0; i < studs.size(); i++)
            {
                if (studs.peek() == sw)
                {
                    used = true;
                    studs.poll();
                    break;
                }
                else
                {
                    studs.addLast(studs.poll());
                }
            }
            
            if (!used)
                return studs.size();
        }
        
        return studs.size();
    }
}
