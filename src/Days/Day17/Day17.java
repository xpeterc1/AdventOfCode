package Days.Day17;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import AdventUtil.AdventFileReader;

public class Day17 {
	static Map<Integer, Integer> combination_size = new HashMap<Integer, Integer>();
	public static void main(String... args) throws IOException{
		System.out.println("Day 17: No Such Thing as Too Much");
		List<Integer> containers = new ArrayList<Integer>();
		for(String line: AdventFileReader.getLines("Day17input.txt")){
			containers.add(Integer.parseInt(line));
		}
		System.out.println("Part 1: " + combinations(containers, 0, 0));
		int min_ContainerSize = Collections.min(combination_size.keySet());
		System.out.println("Part 2: " + combination_size.get(min_ContainerSize));
	}

	public static int combinations(List<Integer> containers, int depth, int sum){
		if(containers.isEmpty() || containers == null){
			return 0;
		}
		int value = containers.get(0);
		int include = combinations(containers.subList(1, containers.size()), depth + 1, value + sum);		
		int exclude = combinations(containers.subList(1, containers.size()), depth, sum);				
		if(sum + value == 150){
			combination_size.put(depth, combination_size.containsKey(depth)? combination_size.get(depth) + 1 : 1);
			return include + exclude + 1;
		}				
		return include + exclude;
	}

}
