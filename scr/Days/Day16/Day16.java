package Days.Day16;

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
		ArrayList<Integer> tally = new ArrayList<Integer>();

		for(Aunt sue: aunts){
			if(sue.getChildren() == 3){
				tally.add(sue.getNumber());
			}
			if(sue.getCat() == 7){
				tally.add(sue.getNumber());
			}
			if(sue.getSamoyed() == 2){
				tally.add(sue.getNumber());
			}
			if(sue.getPomeranian() == 3){
				tally.add(sue.getNumber());
			}
			if(sue.getAkita() == 0){
				tally.add(sue.getNumber());
			}
			if(sue.getVizsla() == 0){
				tally.add(sue.getNumber());
			}
			if(sue.getGoldfish() == 5){
				tally.add(sue.getNumber());
			}
			if(sue.getTree() == 3){
				tally.add(sue.getNumber());
			}
			if(sue.getCars() == 2){
				tally.add(sue.getNumber());
			}
			if(sue.getPerfumes() == 1){
				tally.add(sue.getNumber());
			}
		}
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i : tally) {
			Integer count = map.get(i);
			map.put(i, count != null ? count+1 : 0);
		}
		Integer popular = Collections.max(map.entrySet(),
				new Comparator<Map.Entry<Integer, Integer>>() {
			@Override
			public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		}).getKey();
		System.out.println("Part 1: your Aunt Sue's number is " + popular);

	}

	public static int getVal(String input){
		switch(input){
		case "children": return 1;
		case "cats": return 2;
		case "samoyeds": return 3;
		case "pomeranians": return 4;
		case "akitas": return 5;
		case "vizslas": return 6;
		case "goldfish": return 7;
		case "trees": return 8;
		case "cars": return 9;
		case "perfumes": return 10;
		}
		return 11;
	}

	public static class Aunt{
		int number;
		int children;
		int cat;
		int samoyed;
		int pomeranian;
		int akita;
		int vizsla;
		int goldfish;
		int tree;
		int cars;
		int perfumes;
		public Aunt(int number, String word1, int val1, String word2, int val2, String word3, int val3){
			this.number = number;
			incrementVal(getVal(word1), val1);
			incrementVal(getVal(word2), val2);
			incrementVal(getVal(word3), val3);
		}
		private void incrementVal(int type, int value){
			switch(type){
			case 1: children += value; break;
			case 2: cat += value; break;
			case 3: samoyed += value; break;
			case 4: pomeranian += value; break;
			case 5: akita += value; break;
			case 6: vizsla += value; break;
			case 7: goldfish += value; break;
			case 8: tree += value; break;
			case 9: cars += value; break;
			case 10: perfumes += value; break;
			}
		}
		public int getNumber(){
			return number;
		}
		public int getChildren() {
			return children;
		}
		public int getCat() {
			return cat;
		}
		public int getSamoyed() {
			return samoyed;
		}
		public int getPomeranian() {
			return pomeranian;
		}
		public int getAkita() {
			return akita;
		}
		public int getVizsla() {
			return vizsla;
		}
		public int getGoldfish() {
			return goldfish;
		}
		public int getTree() {
			return tree;
		}
		public int getCars() {
			return cars;
		}
		public int getPerfumes() {
			return perfumes;
		}
	}
}
