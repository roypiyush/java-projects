package com.personal.algo.dynamicprogramming;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

class Activity implements Comparable<Activity> {
    private int color;
    private int startTime;
    private int finishTime;
    private int index;

    public Activity(int color, int startTime, int finishTime, int index) {
	super();
	this.color = color;
	this.startTime = startTime;
	this.finishTime = finishTime;
	this.index = index;
    }

    public int getColor() {
	return color;
    }

    public void setColor(int color) {
	this.color = color;
    }

    public int getStartTime() {
	return startTime;
    }

    public void setStartTime(int startTime) {
	this.startTime = startTime;
    }

    public int getFinishTime() {
	return finishTime;
    }

    public void setFinishTime(int finishTime) {
	this.finishTime = finishTime;
    }

    public int getIndex() {
	return index;
    }

    public void setIndex(int index) {
	this.index = index;
    }

    public int compareTo(Activity i) {
	return this.getStartTime() - i.getStartTime();
    }

}

class StartTime implements Comparator<Activity> {

    public int compare(Activity o1, Activity o2) {
	return o1.getStartTime() - o2.getStartTime();
    }

}

public class ActivitySelectorProblem {

    public Set<Integer> activitySelectionDP(int[] s, int[] f) {

	HashSet<Integer> activities = new HashSet<Integer>();

	for (int k = 0; k < s.length; k++) {

	    HashSet<Integer> temp = new HashSet<Integer>();
	    temp.add(k);

	    int m = k - 1, n = k + 1, l = k;
	    while (m >= 0) {
		if (f[m] <= s[l]) {
		    temp.add(m);
		    l = m;
		}
		m = m - 1;
	    }

	    l = k;
	    while (n < f.length) {
		if (f[l] <= s[n]) {
		    temp.add(n);
		    l = n;
		}
		n = n + 1;
	    }

	    if (temp.size() > activities.size()) {
		activities = new HashSet<Integer>();
		activities.addAll(temp);
	    }

	}

	return activities;
    }

    public Set<Integer> activitySelectionGreedy(int[] s, int[] f) {

	HashSet<Integer> activities = new HashSet<Integer>();

	int m = 0;
	activities.add(m);

	for (int i = 1; i < f.length; i++) {

	    if (f[m] <= s[i]) {
		activities.add(i);
		m = i;
	    }
	}

	return activities;
    }

    int numberOfHalls(Set<Integer> activities, int[] s, int[] f) {

	if (activities != null && activities.size() < 0)
	    return -1;

	int halls = 0;

	while (activities.size() != 0) {
	    Set<Integer> compatibileActivities = activitySelectionGreedyFromSet(
		    activities, s, f);

	    System.out.println("Compatible Activities: "
		    + compatibileActivities);

	    if (activities.removeAll(compatibileActivities)) {
		halls++;
	    }
	}

	return halls;
    }

    private Set<Integer> activitySelectionGreedyFromSet(
	    Set<Integer> activities, int[] s, int[] f) {

	HashSet<Integer> compatible = new HashSet<Integer>();

	int m = new ArrayList<Integer>(activities).get(0);
	compatible.add(m);

	for (int i = m + 1; i < f.length; i++) {

	    if (activities.contains(i) && f[m] <= s[i]) {
		compatible.add(i);
		m = i;
	    }
	}

	return compatible;
    }

    public static void main(String[] args) {
	int[] s = { 1, 3, 0, 5, 3, 5, 6, 8, 8, 2, 12 };
	int[] f = { 4, 5, 6, 7, 9, 9, 10, 11, 12, 14, 16 };

	int[] s1 = { 8, 3, 0, 5, 3, 5, 6, 8, 2, 12, 1 };
	int[] f1 = { 12, 5, 6, 7, 9, 9, 10, 11, 14, 16, 4 };

	System.out.println(new ActivitySelectorProblem()
		.activitySelectionGreedy(s, f));

	System.out.println(new ActivitySelectorProblem().activitySelectionDP(
		s1, f1));

	Set<Integer> activities = new HashSet<Integer>();
	for (int i = 0; i < f.length; i++) {
	    activities.add(i);
	}
	System.out.println(String.format("Number of Halls: %d",
		new ActivitySelectorProblem().numberOfHalls(activities, s, f)));

    }

}
