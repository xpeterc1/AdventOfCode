package org.xpeterc1.adventofcode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import AdventUtil.AdventFileReader;

public class Day09 {
	static Map<String, City> cities = new HashMap<String, City>();

	public static void main(String... args) throws IOException{

		List<String> lines = AdventFileReader.getLines("Day09input.txt");
		initCities(lines);
		List<Integer> distances = getDistances();
		System.out.println("Part 1: Shortest Distance is: " + Collections.min(distances));
		System.out.println("Part 2: Longest Distance is: " + Collections.max(distances));

	}
	public static void initCities(List<String> lines){
		for (String line: lines) {
			Pattern pattern = Pattern.compile("([A-Za-z]+) to ([A-Za-z]+) = (\\d+)");
			Matcher matcher = pattern.matcher(line);
			if (matcher.find()) {
				if(!cities.containsKey(matcher.group(1))){
					cities.put(matcher.group(1), new City(matcher.group(1)));
				}
				if(!cities.containsKey(matcher.group(2))){
					cities.put(matcher.group(2), new City(matcher.group(2)));
				}

				cities.get(matcher.group(1)).addCity(matcher.group(2), Integer.parseInt(matcher.group(3)));
				cities.get(matcher.group(2)).addCity(matcher.group(1), Integer.parseInt(matcher.group(3)));

			}else {
				System.out.println("Error, parsing went wrong here: " + line);
			}

		}
	}
	public static List<Integer> getDistances(){
		Set<List<String>> perm = permutation(new ArrayList<String>(cities.keySet()));
		List<Integer> distances = new ArrayList<Integer>();
		for(List<String> tem: perm){
			City nextCity = cities.get(tem.get(0));
			int distance = 0;
			boolean breakOut = false;
			for(int i = 1 ; i < tem.size(); i++){
				if(nextCity.containsCity(tem.get(i))){
					distance += nextCity.getDestinationLength(tem.get(i));
					nextCity = cities.get(tem.get(i));
				}else{
					breakOut = true;
					distance += 20000;
					break;
				}
			}
			if(!breakOut){
				distances.add(distance);
			}
		}
		return distances;
	}

	public static Set<List<String>> permutation(List<String> list){
		Set<List<String>> result = new HashSet<List<String>>();
		if(list == null){
			return null;
		}else if (list.isEmpty()){
			result.add(new ArrayList<String>());
			return result;
		}
		String firstCity = list.get(0);
		Set<List<String>> words = permutation(list.subList(1, list.size()));
		for(List<String> newString : words){
			for(int i = 0; i <= newString.size(); i++){
				result.add(addCityIn(newString, firstCity, i));
			}
		}
		return result;

	}

	private static <E> List<E> addCityIn(List<E> list, E str, int j) {
		List<E> first = list.subList(0, j);
		List<E> last = list.subList(j, list.size());
		List<E> ans = new ArrayList<E>();
		ans.addAll(first);
		ans.add(str);
		ans.addAll(last);
		return ans;
	}

	public static class City{
		private String name;
		private Map<String, Integer> cityList = new HashMap<String, Integer>();

		public City(String name){
			this.name = name;
		}
		public String getName(){
			return this.name;
		}
		public void addCity(String cityName, int length){
			this.cityList.put(cityName, length);
		}

		public boolean containsCity(String cityName){
			return cityList.containsKey(cityName);
		}
		public int getDestinationLength(String cityName){
			if(!cityList.containsKey(cityName)){
				return 200000;
			}
			return cityList.get(cityName);
		}
		public Map<String, Integer> getList(){
			return cityList;
		}

	}
}
