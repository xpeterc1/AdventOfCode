package org.xpeterc1.adventofcode;

import java.io.IOException;
import java.util.List;

import AdventUtil.AdventFileReader;

public class Day08 {
	public static void main(String... args) throws IOException{
		List<String> lines = AdventFileReader.getLines("Day08input.txt");
		System.out.println("Part 1: " + countCharPart1(lines));
		System.out.println("Part 2: " + countCharPart2(lines));
	}

	public static int countCharPart1(List<String> lines){
		int totalChar = 0;
		int totalCharinMem = 0;
		for(String line: lines){
			totalChar += line.length() + 2;
			totalCharinMem += line.replaceAll("(\\\\x..)|(\\\\\")|(\\\\{2})|(\")", " ").length();
		}
		return totalChar - totalCharinMem;
	}

	public static int countCharPart2(List<String> lines){
		int totalChar = 0;
		int totalCharinMem = 0;
		for(String line: lines){
			totalChar += line.length();
			totalCharinMem += line.replaceAll("(\")|(\\\\)", "  ").length() + 2;
		}
		return totalCharinMem - totalChar;
	}

}
