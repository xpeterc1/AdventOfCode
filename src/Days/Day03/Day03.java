package Days.Day03;

import java.awt.Point;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import AdventUtil.AdventFileReader;

public class Day03 {
	public static void main(String... args) throws IOException{
		List<String> lines = AdventFileReader.getLines("Day03input.txt");
		System.out.println("Part 1: " + getTotal(lines, false));
		System.out.println("Part 2: " + getTotal(lines, true));
	}

	public static int getTotal(List<String> lines, boolean part2){
		boolean toggle = true;
		StringBuilder directions = new StringBuilder();
		int x = 0; //Santa
		int y = 0;
		int x1 = 0;	//Robo-Santa
		int y1 = 0;

		int total = 1;		
		for(String i: lines){
			directions.append(i.trim());
		}
		HashSet<Point> hashSet = new HashSet<Point>();
		hashSet.add(new Point(x,y));

		for(char c: directions.toString().toCharArray()){
			if(toggle = !toggle && part2){
				switch(c){
				case('<'): x--; break;
				case('>'): x++; break;
				case('v'): y--; break;
				case('^'): y++; break;
				}
				Point point = new Point(x,y);
				if(!hashSet.contains(point)){
					total++;
					hashSet.add(point);
				}
			}else{
				switch(c){
				case('<'): x1--; break;
				case('>'): x1++; break;
				case('v'): y1--; break;
				case('^'): y1++; break;
				}
				Point point = new Point(x1,y1);
				if(!hashSet.contains(point)){
					total++;
					hashSet.add(point);
				}
			}
		}
		return total;
	}
}

