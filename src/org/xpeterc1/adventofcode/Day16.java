package org.xpeterc1.adventofcode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import AdventUtil.AdventFileReader;

public class Day16 {
	public static void main(String... args) throws IOException{
		ArrayList<String> lines = AdventFileReader.getLines("Day16input.txt");
		ArrayList<Aunt> aunts = new ArrayList<Aunt>();
		Pattern pat = Pattern.compile("Sue (\\d+): (\\w+): (\\d+), (\\w+): (\\d+), (\\w+): (\\d+)");
		Matcher mat;
		int num = 0;
		for(String line: lines){
			mat = pat.matcher(line);
			if(mat.find()){
				num++;
				aunts.add(new Aunt(num, mat.group(2), Integer.parseInt(mat.group(3)), mat.group(4), Integer.parseInt(mat.group(5)), mat.group(6), Integer.parseInt(mat.group(7))));
			}	
		}
		ArrayList<Integer> tallyPart1 = new ArrayList<Integer>();
		ArrayList<Integer> tallyPart2 = new ArrayList<Integer>();

		for(Aunt sue: aunts){
			if(sue.children == 3){	
				tallyPart1.add(sue.number);
				tallyPart2.add(sue.number); 
			}
			if(sue.cat== 7) 		tallyPart1.add(sue.number);
			if(sue.cat > 7) 		tallyPart2.add(sue.number); 

			if(sue.samoyed == 2){
				tallyPart1.add(sue.number);
				tallyPart2.add(sue.number); 
			}
			if(sue.pomeranian == 3)tallyPart1.add(sue.number);	
			if(sue.pomeranian < 3)	tallyPart2.add(sue.number);			

			if(sue.akita == 0){
				tallyPart1.add(sue.number);
				tallyPart2.add(sue.number);
			}
			if(sue.vizsla == 0){
				tallyPart1.add(sue.number);
				tallyPart2.add(sue.number); 
			}
			if(sue.goldfish == 5)	tallyPart1.add(sue.number);	
			if(sue.goldfish < 5)	tallyPart2.add(sue.number);		

			if(sue.tree == 3)		tallyPart1.add(sue.number);			
			if(sue.tree > 3)		tallyPart2.add(sue.number);

			if(sue.cars == 2){
				tallyPart1.add(sue.number);
				tallyPart2.add(sue.number); 
			}
			if(sue.perfumes == 1){
				tallyPart1.add(sue.number);
				tallyPart2.add(sue.number); 
			}

		}		
		System.out.println("Part 1: your Aunt Sue's number is " + getMode(tallyPart1));
		System.out.println("Part 2: your Aunt Sue's number is " + getMode(tallyPart2));
	}

	public static int getMode(ArrayList<Integer> list){
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i : list) {
			Integer count = map.get(i);
			map.put(i, count != null ? count+1 : 0);
		}
		return Collections.max(map.entrySet(),
				new Comparator<Map.Entry<Integer, Integer>>() {
			@Override
			public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		}).getKey();
	}

	public static class Aunt{
		int number, children, cat, samoyed, pomeranian, akita, vizsla, goldfish, tree, cars, perfumes;

		public Aunt(int number, String word1, int val1, String word2, int val2, String word3, int val3){
			this.number = number;
			incrementVal(word1, val1);
			incrementVal(word2, val2);
			incrementVal(word3, val3);
		}
		private void incrementVal(String word, int value){
			switch(word){
			case "children": children += value; break;
			case "cats": cat += value; break;
			case "samoyeds": samoyed += value; break;
			case "pomeranians": pomeranian += value; break;
			case "akitas": akita += value; break;
			case "vizslas": vizsla += value; break;
			case "goldfish": goldfish += value; break;
			case "trees": tree += value; break;
			case "cars": cars += value; break;
			case "perfumes": perfumes += value; break;

			}
		}

	}
}
