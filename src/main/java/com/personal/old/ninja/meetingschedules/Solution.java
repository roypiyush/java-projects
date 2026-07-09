/**
 * 
 */
package com.personal.old.ninja.meetingschedules;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * @author piyush
 *
 */

class Time implements Comparable<Time>{
	
	private int hour;
	private int minutes;
		
	public Time(int hour, int minutes) {
		super();
		this.hour = hour;
		this.minutes = minutes;
	}
	
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getMinutes() {
		return minutes;
	}
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
	
	
	public String toString() {
		return String.format("%02d %02d", (hour != 24 ? hour : 0), minutes);
	}

	/**
	 * Next time should be greater than this time
	 * 
	 * @param next
	 * @return
	 */
	public int difference(Time next) {
		int hours = next.getHour() - this.hour;
		int minutes = 0;
		
		if(next.getMinutes() >= this.minutes) {
			minutes = next.getMinutes() - this.minutes;
		}
		else if(next.getMinutes() < this.minutes) {
			
			hours = hours - 1;
			minutes = 60 - this.minutes + next.getMinutes();
		}
		return hours * 60 + minutes;
	}
	

	public int compareTo(Time t) {

		int signum = 0;
		
		if(hour < t.getHour()) {
			signum = -1;
		}
		else if(hour > t.getHour()) {
			signum = 1;
		}
		else {
			if(minutes < t.getMinutes())
			{
				signum = -1;
			}
			else if(minutes < t.getMinutes())
			{
				signum = 1;
			}
			else {
				signum = 0;
			}
		}
		
		return signum;
	}

}

class Slot implements Comparable<Slot>{
	
	private Time startTime;
	private Time endTime;
	
	public Slot(Time startTime, Time endTime) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public Time getStartTime() {
		return startTime;
	}
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	public Time getEndTime() {
		return endTime;
	}
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	
	public String toString() {
		return startTime.toString() + " " + endTime.toString();
	}
	
	public int compareTo(Slot slot) {
		
		int signum = 1;
		
		Time compareTime = startTime;
 		Time slotCompareTime = slot.getStartTime();
		
		if(compareTime.getHour() < slotCompareTime.getHour()) {
			signum = -1;
		}
		else if(compareTime.getHour() > slotCompareTime.getHour()) {
			signum = 1;
		}
		else {
			// If hour is equal then compare minutes
			if(compareTime.getMinutes() < slotCompareTime.getMinutes()) {
				signum = -1;
			}
			else if(compareTime.getMinutes() > slotCompareTime.getMinutes()) {
				signum = 1;
			}
			else {
				signum = 0;
			}
		}
		
		return signum;
	}
	
}



public class Solution {
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Solution solution = new Solution();
		
		int slotCount = 0;
		int bookingMinutes = 0;
		
		ArrayList<Slot> slots = new ArrayList<Slot>();
		
		Scanner sc = null;
		try {
			sc = new Scanner(new BufferedInputStream(System.in));
			String input[] = sc.nextLine().trim().split(" ");
			slotCount = Integer.valueOf(input[0]);
			bookingMinutes = Integer.valueOf(input[1]); 
			
			int count = 0;
			while (count < slotCount && sc.hasNextLine()) {
				
				try {
					
					String time[] = sc.nextLine().trim().split(" ");
					Time startTime = new Time(Integer.parseInt(time[0]), Integer.parseInt(time[1]));
					Time endTime = new Time(Integer.parseInt(time[2]), Integer.parseInt(time[3]));
					Slot slot = new Slot(startTime, endTime);
					
					slots.add(slot);
					
				} catch (Exception e) {
					System.out.println(String.format("Cannot accept input due to %s", e.getMessage()));
					return;
				}
				
				count++;
			}
			
			Collections.sort(slots);
			
//			System.out.println("==========");
//			for (Iterator<Slot> iterator = slots.iterator(); iterator.hasNext();) {
//				Slot slot = iterator.next();
//				System.out.println(slot);
//				
//			}
//			System.out.println("==========");
			
			solution.findFreeTime(slots, bookingMinutes);
			
			
		} catch (Exception e) {
			System.out.println(String.format("Error due to %s", e.getMessage()));
		} finally {
			if(sc != null) {
				sc.close();
			}
		}
		

	}

	private void findFreeTime(ArrayList<Slot> slots, int bookingMinutes) {
		
		Slot start = new Slot(new Time(0, 0), new Time(0, 0));
		
		if(start.getEndTime().compareTo(slots.get(0).getStartTime()) == -1) {
			
			int diff = start.getEndTime().difference(slots.get(0).getStartTime());
			if(diff >= bookingMinutes) {
				System.out.println(start.getEndTime() + " " + slots.get(0).getStartTime());
			}
		}
		
		
		
		
		
		for (int i = 0; i < slots.size() - 1; ) {
			Slot s1 = slots.get(i);
			Slot s2 = slots.get(i + 1);
			
//			// Handle case to merge
			if(s1.getEndTime().compareTo(s2.getEndTime()) >= 0) {
				slots.remove(i + 1); 
				continue;
			}
			
			// Condition is valid iff s1's endTime is less than s2's startTime
			if(s1.getEndTime().compareTo(s2.getStartTime()) == -1) {
				
				int diff = s1.getEndTime().difference(s2.getStartTime());
				if(diff >= bookingMinutes) {
					System.out.println(s1.getEndTime() + " " + s2.getStartTime());
				}
			}
			i = i + 1;
		}
		
		Slot end = new Slot(new Time(24, 0), new Time(24, 0));
		if(slots.get(slots.size() - 1).getEndTime().compareTo(end.getStartTime()) == -1) {
			
			int diff = slots.get(slots.size() - 1).getEndTime().difference(end.getStartTime());
			if(diff >= bookingMinutes) {
				System.out.println(slots.get(slots.size() - 1).getEndTime() + " " + end.getStartTime());
			}
		}
		
	}

}
