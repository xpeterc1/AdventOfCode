package org.xpeterc1.adventofcode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day10 {
	final static String INPUT = "1113222113";
	public static void main(String... args){
		System.out.println("Part 1: " + myLookSee(INPUT, 40));
		System.out.println("Part 2: " + myLookSee(INPUT, 50));
	}

	public static int myLookSee(String value, int cycles){
		for(int runs = 0; runs < cycles; runs++){
			Matcher mat = Pattern.compile("((?<DIGIT>\\d)\\k<DIGIT>*)").matcher(value);
			StringBuilder ans = new StringBuilder("");
			while(mat.find()){
				ans.append(""+mat.group(1).length()+mat.group(1).charAt(0));
			}
			value = ans.toString();
		}
		return value.length();
	}
}
