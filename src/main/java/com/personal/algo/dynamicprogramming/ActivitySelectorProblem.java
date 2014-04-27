package com.personal.algo.dynamicprogramming;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

class Activity implements Comparable<Activity> {

	private int color;
	private int startTime;
	private int finishTime;
	private int index;
	private int value;

	public Activity(int color, int startTime, int finishTime, int index,
			int value) {
		super();
		this.color = color;
		this.startTime = startTime;
		this.finishTime = finishTime;
		this.index = index;
		this.value = value;
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

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Activity [color=" + color + ", startTime=" + startTime
				+ ", finishTime=" + finishTime + ", index=" + index
				+ ", value=" + value + "]";
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

	private int maxValuedActivities(List<Activity> activityList, int i, int j) {

		List<Activity> maxCompatibleActivities = null;
		int max = -1;

		// for each kth activity check for compatible activities
		for (int k = i; k <= j; k++) {
			List<Activity> compatibleActivities = getCompatibleActivities(
					activityList, k);
			// sum up the values of all activities compatible with k
			int sumOfValues = 0;
			for (Iterator<Activity> iterator = compatibleActivities.iterator(); iterator
					.hasNext();) {
				Activity activity = iterator.next();
				sumOfValues = sumOfValues + activity.getValue();

			}

			if (sumOfValues > max) {
				maxCompatibleActivities = compatibleActivities;
				max = sumOfValues;
			}
			// if sum is maximum then choose activities compatible with k
		}

		System.out.println(maxCompatibleActivities);
		return max;
	}

	private List<Activity> getCompatibleActivities(List<Activity> activityList,
			int k) {

		List<Activity> compatibleActivities = new ArrayList<Activity>();
		compatibleActivities.add(activityList.get(k));

		int m = k - 1, n = k + 1, l = k;
		while (m >= 0) {
			if (activityList.get(m).getFinishTime() <= activityList.get(l)
					.getStartTime()) {
				compatibleActivities.add(activityList.get(m));
				l = m;
			}
			m = m - 1;
		}

		l = k;
		while (n < activityList.size()) {
			if (activityList.get(l).getFinishTime() <= activityList.get(n)
					.getStartTime()) {
				compatibleActivities.add(activityList.get(n));
				l = n;
			}
			n = n + 1;
		}

		return compatibleActivities;
	}
	
	private int maxValued(List<Activity> activityList, int i, int j) {
		
		int max = 0;
		for(int k = i; k <= j; k++) {
			int v1 = valueForChosen(activityList, k - 1, k);
			int v2 = valueForChosen(activityList, k + 1, k);
			max = Math.max(max, v1 + v2);
		}
		return max;
	}
	
	private int valueForChosen(List<Activity> activityList, int j, int chosen) {
		
		int sum = activityList.get(chosen).getValue(), m1 = 0 , m2 = 0;
		
		if (j >= 0 && j < chosen) {
			if (activityList.get(j).getFinishTime() <= activityList.get(chosen)
					.getStartTime()) {
				m1 = valueForChosen(activityList, j - 1, j);
			} else {
				m1 = valueForChosen(activityList, j - 1, chosen);
			}
		}
		
		
		if (j < activityList.size() && j > chosen) {
			if (activityList.get(chosen).getFinishTime() <= activityList.get(j)
					.getStartTime()) {
				m2 = valueForChosen(activityList, j + 1, j);
			} else {
				m2 = valueForChosen(activityList, j + 1, chosen);
			}
		}
		
		
		
		return sum + m1 + m2;
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

		List<Activity> activityList = new ArrayList<Activity>();

		for (int i = 0; i < f.length; i++) {
			Activity activity = new Activity(0, s[i], f[i], i, 
					(int) (Math.random() * 20));
			activityList.add(activity);
			System.out.println(activity);
		}

		System.out.println(String.format("Max Valued Activities: %d",
				new ActivitySelectorProblem().maxValuedActivities(activityList,
						0, activityList.size() - 1)));
		
		System.out.println(String.format("Max Valued Activities Recursive: %d",
				new ActivitySelectorProblem().maxValued(activityList,
						0, activityList.size() - 1)));
	}

}
