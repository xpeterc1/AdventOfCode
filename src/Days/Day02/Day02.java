package Days.Day02;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import AdventUtil.AdventFileReader;


public class Day02 {
	public static void main(String... args) throws IOException{
		List<String> lines = AdventFileReader.getLines("Day02input.txt");
		System.out.println("Part 1: " + getPart1(lines));
		System.out.println("Part 1: " + getPart2(lines));
	}

	public static int getPart1(List<String> lines){
		int total = 0;
		for(String i: lines){
			String[] LWH = i.split("x");
			ArrayList<Integer> dim = new ArrayList<Integer>();
			dim.add(Integer.parseInt(LWH[0])); 
			dim.add(Integer.parseInt(LWH[1]));
			dim.add(Integer.parseInt(LWH[2]));
			Collections.sort(dim);
			int min = (dim.get(0)) * (dim.get(1));			
			int ans = (2*dim.get(0)*dim.get(1)) + (2*dim.get(1)*dim.get(2)) + (2*dim.get(2)*dim.get(0)) + min;
			total += ans;
		}
		return total;
	}

	public static int getPart2(List<String> lines){
		int total = 0;
		for(String i: lines){
			String[] LWH = i.split("x");
			ArrayList<Integer> dim = new ArrayList<Integer>();
			dim.add(Integer.parseInt(LWH[0])); 
			dim.add(Integer.parseInt(LWH[1]));
			dim.add(Integer.parseInt(LWH[2]));
			Collections.sort(dim);
			int min = (2*dim.get(0)) + (2*dim.get(1));			
			int ans = ((dim.get(0)) * (dim.get(1)) * (dim.get(2))) + min;
			total += ans;
		}
		return total;
	}
}
