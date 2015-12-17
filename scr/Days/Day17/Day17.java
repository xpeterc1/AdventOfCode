package Days.Day17;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import AdventUtil.AdventFileReader;

public class Day17 {
	static int LITERS = 150;
	public static void main(String... args) throws IOException{
		List<String> lines = AdventFileReader.getLines("Day17input.txt");
		List<Integer> containers = new ArrayList<Integer>();
		for(String line: lines){
			containers.add(Integer.parseInt(line));
		}
		System.out.println(combinations(containers, 0));
	}
	
	//You can think of this solution as a binary tree, since there are 2^n combinations
	public static int combinations(List<Integer> containers, int sum){
		if(containers.isEmpty()){
			return 0;
		}
		int value = containers.get(0);
		int include = combinations(containers.subList(1, containers.size()), value + sum);		//left branch, include container
		int exclude = combinations(containers.subList(1, containers.size()), sum);				//right branch, exclude container
		if(sum + value == LITERS){
			return include + exclude + 1;
		}				
		return include + exclude;
	}
	
}
