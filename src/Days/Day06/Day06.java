package Days.Day06;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import AdventUtil.AdventFileReader;

public class Day06 {
	public static void main(String... args) throws IOException{
		List<String> lines = AdventFileReader.getLines("Day06input.txt");
		getPart1(lines);
	}
	public static void getPart1(List<String> lines){
		List<ArrayList<Integer>> lightsPart1 = new ArrayList<ArrayList<Integer>>(1000);
		List<ArrayList<Integer>> lightsPart2 = new ArrayList<ArrayList<Integer>>(1000);
		for(int i = 0; i < 1000; i++){
			ArrayList<Integer> tempPart1 = new ArrayList<Integer>(1000);
			ArrayList<Integer> tempPart2 = new ArrayList<Integer>(1000);
			for(int j = 0; j < 1000; j++){
				tempPart1.add(0);
				tempPart2.add(0);
			}
			lightsPart1.add(tempPart1);
			lightsPart2.add(tempPart2);
		}		
		for(String line: lines){
			Pattern pat = Pattern.compile("(turn off|toggle|turn on) (\\d+),(\\d+) through (\\d+),(\\d+)");
			Matcher mat = pat.matcher(line);
			if(mat.find()){
				String instruction = mat.group(1);
				int startX = Integer.parseInt(mat.group(2));
				int startY = Integer.parseInt(mat.group(3));
				int endX = Integer.parseInt(mat.group(4));
				int endY = Integer.parseInt(mat.group(5));
				for(int x = startX; x <= endX; x++){
					for(int y = startY; y <= endY; y++){
						switch(instruction){
						case"turn off":{
							lightsPart1.get(x).set(y, 0);
							int value = lightsPart2.get(x).get(y); 
							if(value > 0)
								lightsPart2.get(x).set(y, value - 1);
							break;
						}
						case"turn on":{
							lightsPart1.get(x).set(y, 1);
							int value = lightsPart2.get(x).get(y); 
							lightsPart2.get(x).set(y, value + 1);
							break;
						}	
						case"toggle":{
							if(lightsPart1.get(x).get(y) == 0){
								lightsPart1.get(x).set(y, 1);
							}else{
								lightsPart1.get(x).set(y, 0);
							}
							int value = lightsPart2.get(x).get(y); 
							lightsPart2.get(x).set(y, value + 2);
							break;
						}//End of case toggle
						}//End of switch(mat.group(1))
					}//End of for loop y
				}//End of for loop x
			}//End of mat.find()
		}

		int totalPart1 = 0;
		int totalPart2 = 0;
		for(int i = 0; i < 1000; i++){
			for(int j = 0; j < 1000; j++){
				totalPart1 += lightsPart1.get(i).get(j);
				totalPart2 += lightsPart2.get(i).get(j);
			}			
		}
		System.out.println("Part 1: " + totalPart1);
		System.out.println("Part 2: " + totalPart2);
	}

}
