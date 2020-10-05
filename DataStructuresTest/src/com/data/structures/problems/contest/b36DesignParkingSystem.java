package com.data.structures.problems.contest;

/**
 * https://leetcode.com/problems/design-parking-system/
 * EASY
 * @author Nelson Costa
 *
 */
public class b36DesignParkingSystem {

	class ParkingSystem {

	    int [] slots;
	    public ParkingSystem(int big, int medium, int small) {
	        slots = new int []{-1, big, medium, small};
	        
	    }
	    
	    public boolean addCar(int carType) {
	        if (slots[carType] - 1 < 0)
	            return false;
	        
	        slots[carType]--;
	        
	        return true;
	    }
	}
}
