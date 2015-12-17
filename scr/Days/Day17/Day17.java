package Days.Day17;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import AdventUtil.AdventFileReader;

public class Day17 {
	static int LITERS = 150;
	static Map<Integer, Integer> combination_size = new HashMap<Integer, Integer>();
	public static void main(String... args) throws IOException{
		List<String> lines = AdventFileReader.getLines("Day17input.txt");
		List<Integer> containers = new ArrayList<Integer>();
		for(String line: lines){
			containers.add(Integer.parseInt(line));
		}
		System.out.println("Part 1: " + combinations(containers, 0, 0));
		int min_ContainerSize = Collections.min(combination_size.keySet());
		System.out.println("Part 2: " + combination_size.get(min_ContainerSize));
	}
	
	//You can think of this solution as a binary tree, since there are 2^n combinations
	public static int combinations(List<Integer> containers, int depth, int sum){
		if(containers.isEmpty()){
			return 0;
		}
		int value = containers.get(0);
		
		//left branch, include container (value + sum)
		int include = combinations(containers.subList(1, containers.size()), depth + 1, value + sum);		
		
		//right branch, exclude container (0 + sum)
		int exclude = combinations(containers.subList(1, containers.size()), depth, sum);				
		if(sum + value == LITERS){
			combination_size.put(depth, combination_size.containsKey(depth)? combination_size.get(depth) + 1 : 1);
			return include + exclude + 1;
		}				
		return include + exclude;
	}
	
}
