package com.codlex.algorithms.topcoder.practice.srm144.div2;

public class Time {
	public String whatTime(int seconds) {
		int finalSeconds = seconds % 60;
		seconds = seconds / 60;
		int finalMinutes = seconds % 60;
		seconds = seconds / 60;
		int finalHours = seconds;
		
		return finalHours + ":" + finalMinutes + ":" + finalSeconds;
	}
}
