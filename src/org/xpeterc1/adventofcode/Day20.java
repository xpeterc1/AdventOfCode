package org.xpeterc1.adventofcode;

import java.io.IOException;

public class Day20 {
	static final int INPUT = 36000000;
	public static void main(String...args) throws IOException{
		System.out.println("Part 1: " + part1(INPUT));
		System.out.println("Part 2: " + part2(INPUT));

	}

	public static int part1(int target){
		int[] houses = new int[1000000];
		for(int i = 1; i < houses.length; i++) {
			for(int j = i; j < houses.length; j+=i) {
				houses[j] += i;
			}
			if(houses[i] * 10 >= target) {
				return i;
			}
		}
		return 0;
	}
	
	public static int part2(int target){
		int[] houses = new int[1000000];
		for(int i = 1; i < houses.length; i++) {
			int fiftyHouses = Math.min(i * 50, houses.length-1);
			for(int j = i; j <= fiftyHouses; j+=i) {
				houses[j] += i;
			}
			if(houses[i] * 11 >= target) {
				return i;
			}
		}
		return 0;
	}
}
