package Days.Day24;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import AdventUtil.AdventFileReader;

public class Day24 {
	public static void main(String... args) throws IOException{
		List<String> lists = AdventFileReader.getLines("Day24input.txt");
		List<Integer> intLists = new ArrayList<Integer>();
		for(String list: lists){
			intLists.add(Integer.parseInt(list));
		}
		Collections.sort(intLists, new Comparator<Integer>(){

			@Override
			public int compare(Integer arg0, Integer arg1) {
				return arg1 - arg0;
			}
		});
		System.out.println("Part 1: " + fillGroup(intLists, 3).toString());
		System.out.println("Part 2: " + fillGroup(intLists, 4).toString());

	}

	private static BigInteger fillGroup(List<Integer> intLists, int size) {
		int targetWeight = 0;
		for(int val: intLists){
			targetWeight += val;
		}
		if(targetWeight % size != 0){
			return null;
		}
		targetWeight /= size;
		BigInteger quantam = null;
		int minSize = intLists.size();

		ArrayList<ArrayList<Integer>> answer = getCombinations(intLists, targetWeight);
		if(answer.size() > 0){
			for(ArrayList<Integer> combinations: answer){
				BigInteger temp = BigInteger.ONE;
				if(minSize >= combinations.size()){
					minSize = combinations.size();
					for(int combination: combinations){
						temp = temp.multiply(new BigInteger(String.valueOf(combination)));
					}
					if(quantam == null || temp.compareTo(quantam) == -1){
						quantam = temp;
					}
				}
			}
		}
		return quantam;
	}
	private static ArrayList<ArrayList<Integer>> getCombinations(List<Integer> values, int target){
		return getCombinations(values, target, new ArrayList<Integer>());
	}
	private static ArrayList<ArrayList<Integer>> getCombinations(List<Integer> values, int target, ArrayList<Integer> combination){
		ArrayList<ArrayList<Integer>> combinations = new ArrayList<ArrayList<Integer>>();
		if(values.isEmpty()){
			return combinations;
		}
		int firstValue = values.get(0); 
		ArrayList<Integer> combinationInclude = new ArrayList<Integer>(combination);
		combinationInclude.add(firstValue);
		combinations.addAll(getCombinations(values.subList(1, values.size()), target, combinationInclude));
		combinations.addAll(getCombinations(values.subList(1, values.size()), target, combination));
		for(int value: combination){
			firstValue += value; 
		}
		if(firstValue == target){
			combinations.add(combinationInclude);
		}
		return combinations;
	}

}
