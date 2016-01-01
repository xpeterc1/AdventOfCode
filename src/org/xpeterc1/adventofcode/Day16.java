package org.xpeterc1.adventofcode;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import AdventUtil.AdventFileReader;

public class Day16 {
	public static int CHILDREN = 3;
	public static int CATS = 7;
	public static int SAMOYEDS = 2;
	public static int POMERANIANS = 3;
	public static int AKITAS = 0;
	public static int VIZSLAS = 0;
	public static int GOLDFISH = 5;
	public static int TREES = 3;
	public static int CARS = 2;
	public static int PERFUMES = 1;

	public static void main(String... args) throws IOException{
		ArrayList<String> lines = AdventFileReader.getLines("Day16input.txt");
		System.out.println("Part 1: " + getAuntSue(lines, false));
		System.out.println("Part 2: " + getAuntSue(lines, true));

	}
	public static int getAuntSue(List<String> lines, boolean part2){
		Pattern pat = Pattern.compile("Sue (\\d+): (\\w+): (\\d+), (\\w+): (\\d+), (\\w+): (\\d+)");

		for(String line: lines){
			Matcher mat = pat.matcher(line);
			if(mat.find()){
				Map<String, Integer> items = new HashMap<String, Integer>();
				for(int i = 0; i < 3; i++){
					items.put(mat.group(2+(i*2)), Integer.parseInt(mat.group(3+(i*2))));
				}
				if(isAuntSue(items, part2)){
					return Integer.parseInt(mat.group(1));
				}
			}
		}
		return 0;
	}

	public static boolean isAuntSue(Map<String, Integer> items, boolean part2){
		int total = 0;
		for(String key: items.keySet()){
			int value = items.get(key);
			switch(key){
			case"children": 
				if(value == CHILDREN) total++; break;
			case"cats":
				if(part2){
					if(value > CATS) total++; break;			
				}
				else if(value == CATS) total++; break;
			case"samoyeds":
				if(value == SAMOYEDS) total++; break;
			case"pomeranians":
				if(part2){ 
					if(value < POMERANIANS) total++; break;
				}
				else if(value == POMERANIANS) total++; break;
			case"akitas":
				if(value == AKITAS) total++; break;
			case"vizslas":
				if(value == VIZSLAS) total++; break;
			case"goldfish":
				if(part2){
					if(value < GOLDFISH) total++; break;
				}
				else if(value == GOLDFISH) total++; break;
			case"trees":
				if(part2){
					if(value > TREES) total++; break;
				}
				else if(value == TREES) total++; break;
			case"cars":
				if(value == CARS) total++; break;
			case"perfumes":
				if(value == PERFUMES) total++; break;
			}
		}
		return total == 3;
	}
}
