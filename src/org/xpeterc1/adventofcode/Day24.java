package org.xpeterc1.adventofcode;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
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
		System.out.println("Part 1: " + fillGroup(intLists, 3).toString());
		System.out.println("Part 2: " + fillGroup(intLists, 4).toString());
	}


	private static BigInteger fillGroup(List<Integer> intLists, int size) {
		Collections.sort(intLists);
		int targetWeight = 0;
		for(int val: intLists){
			targetWeight += val;
		}
		if(targetWeight % size != 0){
			return null;
		}
		targetWeight /= size;

		for(int i = 1; i < intLists.size(); i++){
			ArrayList<ArrayList<Integer>> combinationList = getCombinations(intLists, targetWeight, i);
			if(combinationList.size() > 0){
				for(ArrayList<Integer> combinations: combinationList){
					BigInteger quantam = BigInteger.ONE;
					for(int combination: combinations){
						quantam = quantam.multiply(BigInteger.valueOf(combination));
					}
					return quantam;
				}
			}
		}
		return null;
	}

	private static ArrayList<ArrayList<Integer>> getCombinations(List<Integer> values, int size, int target, int start, ArrayList<Integer> item) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		if (item.size() == size) {
			int sum = 0;
			for(int value: item){
				sum += value;
			}
			if(sum == target){
				res.add(new ArrayList<Integer>(item));
			}
			return res;
		}
		for (int i = start; i < values.size(); i++) {
			item.add(values.get(i));
			res.addAll(getCombinations(values, size, target, i + 1, item));
			item.remove(item.size() - 1);
		}
		return res;
	}
	public static ArrayList<ArrayList<Integer>> getCombinations(List<Integer> values, int target, int size){
		return getCombinations(values, size, target, 0, new ArrayList<Integer>());
	}


}
