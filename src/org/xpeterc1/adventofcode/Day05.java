package org.xpeterc1.adventofcode;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import AdventUtil.AdventFileReader;

public class Day05 {
	public static void main(String... args) throws IOException{
		List<String> lines = AdventFileReader.getLines("Day05input.txt");

		System.out.println("Part 1: " + getPart1(lines));
		System.out.println("Part 2: " + getPart2(lines)); 

	}
	public static int getPart1(List<String> lines){
		int total = 0;
		Pattern doublePat = Pattern.compile("([a-z])\\1");
		for(String nice: lines){
			int badWords = nice.length() - nice.replaceAll("ab|cd|pq|xy", "").length();
			int vowels = nice.length() - nice.replaceAll("a|e|i|o|u", "").length();
			if(doublePat.matcher(nice).find() && badWords == 0 && vowels >= 3){
				total++;
			}
		}
		return total;
	}
	
	public static int getPart2(List<String> lines){
		int total = 0;
		Pattern twoLetterPair = Pattern.compile("([a-z]{2})(.*)\\1");
		Pattern palindrome = Pattern.compile("([a-z])(.)\\1");

		for(String nice: lines){
			Matcher mat = twoLetterPair.matcher(nice);
			Matcher mat2 = palindrome.matcher(nice);
			if(mat.find() && mat2.find()){
				total++;
			}
		}
		return total;
	}
}
