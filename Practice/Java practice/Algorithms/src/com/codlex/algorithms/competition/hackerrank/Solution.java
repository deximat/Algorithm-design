package com.codlex.algorithms.competition.hackerrank;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Stream;

public class Solution {
	
	private static class House {
		
		private int money;
		private int index;
		private long forbidenFlag;
		
		public House(int index, int money) {
			this.index = index;
			this.money = money;
		}
		
		public int getMoney() {
			return this.money;
		}
		
		public void forbids(int forbiddenIndex) {
			this.forbidenFlag |= 1l << forbiddenIndex;
		}
		
	
	} 
	
	private static class Solver {		
		private final Map<Integer, House> houses;
		private Set<Long> solutions = new HashSet<>();
		
		public Solver(Map<Integer, House> houses) {
			this.houses = houses;
		}
		
		
		public int calculateMoneyWith(final long forbiden, final House house) {
			return calculateMoney(forbiden | house.forbidenFlag);
		}
		
		
		private Stream<House> getForbiden(long forbiden) {
			return this.houses.values().stream().filter((house) -> !isForbiden(forbiden, house));
		}
		
		private int calculateMoney(long flag) {
			return getForbiden(flag).mapToInt(House::getMoney).sum();
		}
		
		public boolean isDone(long forbiden, int choosen) {
			return this.houses.size() == getForbiden(forbiden).count() + choosen;
		}
		
		private static boolean isForbiden(long forbiden, final House house) {
			return (forbiden & (1l << house.index)) != 0;
		}
		
		public void addNext(long forbiden, int choosen) {
			if (this.solutions.contains(forbiden)) {
				return;
			}
			
			int bestValue = Integer.MIN_VALUE;
			final Map<Long, House> housesToProcess = new HashMap<>();
			
			for (House house : this.houses.values()) {
				if (!isForbiden(forbiden, house)) {
					int thisValue = calculateMoneyWith(forbiden, house);
					long newForbiden = forbiden | house.forbidenFlag;
					if (thisValue > bestValue) {
						housesToProcess.clear();
						bestValue = thisValue;
						housesToProcess.put(newForbiden, house);
					} else if (thisValue == bestValue) {
						housesToProcess.put(newForbiden, house);
					}
				}
			}
			
			for (Entry<Long, House> choosenHouse : housesToProcess.entrySet()) {
				if (!isDone(choosenHouse.getKey(), choosen + 1)) {
					addNext(choosenHouse.getKey(), choosen + 1);
				} else {
					this.solutions.add(choosenHouse.getKey());
				}
			}
		}


		public void solve() {
			addNext(0, 0);
		}


		public int calculateMoney() {
			for (long forbiden : this.solutions) {
				return calculateMoney(forbiden);
			}
			throw new RuntimeException("No solution.");
		}
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Map<Integer, House> houses = new HashMap<>();
		int n = in.nextInt();
		int m = in.nextInt();
		
		for (int i = 1; i <= n; i++) {
			int money = in.nextInt();
			houses.put(i, new House(i, money));
		}
		
		for (int i = 0; i < m; i++) {
			int from = in.nextInt();
			int to = in.nextInt();
			
			houses.get(from).forbids(to);
			houses.get(to).forbids(from);
		}
		
		
		final Solver solver = new Solver(houses);
		solver.solve();
		
		System.out.println(solver.calculateMoney() + " " + solver.solutions.size());
		
	}
}
