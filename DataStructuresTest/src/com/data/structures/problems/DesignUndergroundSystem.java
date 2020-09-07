package com.data.structures.problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/design-underground-system/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class DesignUndergroundSystem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

 	
	 /*********************************
	 * SOLUTION 1
	 ********************************/
	/**
	 * @intuition
	 * 		The intuition is very simple.
	 * 		To have hashmap to store information grouped by user 
	 * 		and another hashmap where the information is grouped by itinerary
	 * 		All the operation as O(1) Time
	 * 		
	 * @score
	 *		Runtime: 94 ms, faster than 56.15% of Java online submissions for Design Underground System.
	 *		Memory Usage: 53.7 MB, less than 42.78% of Java online submissions for Design Underground System.
	 * 
	 * @fail
	 * 		1) I returned int instead of double
	 * 
	 * 
	 * @time
	 * 		O(1)
	 * 
	 * @space
	 * 		O(N)
	 * 
	 * 
	 * @author Nelson Costa
	 *
	 */
	class UndergroundSystem {

	    HashMap<Integer, List<Ticket>> userTicket;
	    HashMap<String, Avg> itineraryAvg;
	    
	    public UndergroundSystem() {
	        userTicket = new HashMap<>(); 
	        itineraryAvg = new HashMap<>();   
	    }
	    
	    public void checkIn(int id, String stationName, int t) 
	    {
	        userTicket.computeIfAbsent(id, k -> new ArrayList<Ticket>()).add(new Ticket(stationName, t));
	    }
	    
	    public void checkOut(int id, String stationName, int t) {
	        List<Ticket> tickets = userTicket.get(id);
	        Ticket start = tickets.get(tickets.size() - 1);
	        Ticket end = new Ticket(stationName, t);
	        tickets.add(end);	        
	        itineraryAvg.computeIfAbsent(start.station + "." + end.station, k -> new Avg()).add(end.time - start.time);
	        
	    }
	    
	    public double getAverageTime(String startStation, String endStation) {
	        
	        Avg avg = itineraryAvg.get(startStation + "." + endStation);
	        
	        if (avg == null)
	            return 0;
	        
	        return avg.getAvg();
	    }
	    
	    class Avg
	    {
	        int sum;
	        int numTickets;
	        
	        Avg(){}
	        
	        Avg(int s)
	        {
	            sum = s;
	            numTickets++;
	        }
	        
	        public void add(int itineraryTime){
	            sum += itineraryTime;
	            numTickets++;
	        }
	        
	        public double getAvg(){
	            return (double)sum/numTickets; 
	        }
	    }
	    
	    class Ticket
	    {
	        String station;
	        int time;
	        
	        Ticket(String s, int t)
	        {
	            station = s;
	            time = t;
	        }
	    }
	}
}

/*********************
 * UNOFFICIAL SOLUTIONS
 *********************/
/**
 * Same approach than mine but using pair instead of specifica classes
 * 
 * @author Nelson Costa
 *
 */
class UndergroundSystemSolution1 {
    private Map<String, Pair<Double, Double>> journeyData = new HashMap<>();
    private Map<Integer, Pair<String, Integer>> checkInData = new HashMap<>();
    
    public UndergroundSystemSolution1() {
    }
    
    public void checkIn(int id, String stationName, int t) {
        checkInData.put(id, new Pair<>(stationName, t));
    }
    
    public void checkOut(int id, String stationName, int t) {
        // Look up the check in station and check in time for this id.
        // You could combine this "unpacking" into the other lines of code
        // to have less lines of code overall, but we've chosen to be verbose
        // here to make it easy for all learners to follow.
        Pair<String, Integer> checkInDataForId = checkInData.get(id);
        String startStation = checkInDataForId.getKey();
        Integer checkInTime = checkInDataForId.getValue();
        
        // Lookup the current travel time data for this route.
        String routeKey = stationsKey(startStation, stationName);
        Pair<Double, Double> routeStats  = journeyData.getOrDefault(routeKey, new Pair<>(0.0, 0.0));
        Double totalTripTime = routeStats.getKey();
        Double totalTrips = routeStats.getValue();
        
        // Update the travel time data with this trip.
        double tripTime = t - checkInTime;
        journeyData.put(routeKey, new Pair<>(totalTripTime + tripTime, totalTrips + 1));
        
        // Remove check in data for this id.
        // Note that this is optional, we'll talk about it in the space complexity analysis.
        checkInData.remove(id);
    }
    
    public double getAverageTime(String startStation, String endStation) {
        // Lookup how many times this journey has been made, and the total time.
        String routeKey = stationsKey(startStation, endStation);
        Double totalTime = journeyData.get(routeKey).getKey();
        Double totalTrips = journeyData.get(routeKey).getValue();
        // The average is simply the total divided by the number of trips.
        return totalTime / totalTrips;
    }
    
    private String stationsKey(String startStation, String endStation) {
        return startStation + "->" + endStation;
    }
}

/**
 * Same concept than my solution
 * @author Nelson Costa
 *
 */
class UndergroundSystemUnofficialSolution {

    class Box {
        int cnt;
        double totalTime;
    }
    
    Map<Integer, Pair<String, Integer>> in;
    Map<Pair<String, String>, Box> mp;
    
    public UndergroundSystemUnofficialSolution() {
        in = new HashMap<>();
        mp = new HashMap<>();
    }
    
    public void checkIn(int id, String stationName, int t) {
        in.put(id, new Pair<>(stationName, t));
    }
    
    public void checkOut(int id, String stationName, int t) {
        Pair<String, Integer> val = in.get(id);
        
        Pair<String, String> dest = new Pair<>(val.getKey(), stationName);
        Box box = mp.getOrDefault(dest, new Box());
        box.cnt++;
        box.totalTime += t-val.getValue();
        
        mp.put(dest, box);
        in.remove(id);
    }
    
    public double getAverageTime(String startStation, String endStation) {
        Pair<String, String> pr = new Pair<>(startStation, endStation);
        Box val = mp.get(pr);
        return val.totalTime * 1f / val.cnt;
    }
}
