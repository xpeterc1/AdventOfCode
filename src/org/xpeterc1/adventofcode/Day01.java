package org.xpeterc1.adventofcode;

import java.io.IOException;
import java.util.List;
import AdventUtil.AdventFileReader;

public class Day01 {
	public static void main(String... args) throws IOException{
		List<String> lines = AdventFileReader.getLines("Day01input.txt");
		String floor = lines.get(0);
		System.out.println("Part 1: " + getFloor(floor, false));
		System.out.println("Part 2: " + getFloor(floor, true));

	}
	public static int getFloor(String floor, boolean part2){
		int left = 0;
		int right = 0;
		int position = 0;
		for(int i = 0; i < floor.length(); i++){
			if(floor.charAt(i) == '('){
				left++;
				position++;
			}else if(floor.charAt(i) == ')'){
				right++;
				position--;
			}
			if(position < 0 && part2){
				return i+1;
			}
		}
		return left-right;
	}
}
