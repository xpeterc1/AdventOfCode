package Days.Day13;

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


public class Day13 {
	public static void main(String... args) throws Exception{
		Map<String, Person> tableGuest = new HashMap<String, Person>();
		ArrayList<String> lines = AdventFileReader.getLines("Day13input.txt");
		Pattern pat = Pattern.compile("([a-zA-z]+) would (gain|lose) (\\d+) happiness units by sitting next to ([a-zA-Z]+)\\.");
		Matcher mat = null;
		
		for(String line: lines){
			mat = pat.matcher(line);
			if(mat.find() && !tableGuest.containsKey(mat.group(1))){
				tableGuest.put(mat.group(1), new Person());
			}			
			int value = Integer.parseInt((mat.group(2).equals("lose")? "-":"") + mat.group(3));
			
			tableGuest.get(mat.group(1)).addSeatedNextTo(mat.group(4), value);
		}
		
		System.out.println("Part 1: " + computeHappiness(tableGuest));

		tableGuest.put("Peter", new Person()); //Add myself to the table
		System.out.println("Part 2: " + computeHappiness(tableGuest));
	}
	
	public static int computeHappiness(Map<String, Person> tableGuest){
		List<String> list = new ArrayList<String>(tableGuest.keySet());
		Set<List<String>> perm = permutation(list);
		Set<Integer> happinessList = new HashSet<Integer>(perm.size());
		int size = tableGuest.keySet().size();

		for(List<String> l: perm){
			int happiness = 0;
			for(int i = 0; i < l.size(); i++){
				happiness += tableGuest.get(l.get(i)).getValue(l.get((i+1)%size)); //Right person of circular buffer
				happiness += tableGuest.get(l.get(i)).getValue(l.get(((i-1) == -1? size-1:i-1)%size));  //Left person of circular buffer
			}
			happinessList.add(happiness);
		}
		return Collections.max(happinessList);
	}

	public static Set<List<String>> permutation(List<String> list){
		Set<List<String>> result = new HashSet<List<String>>();
		if(list == null){
			return null;
		}else if (list.isEmpty()){
			result.add(new ArrayList<String>());
			return result;
		}
		String first = list.get(0);
		Set<List<String>> words = permutation(list.subList(1, list.size()));
		for(List<String> newString : words){
			for(int i = 0; i <= newString.size(); i++){
				result.add(addFirstBackIn(newString, first, i));
			}
		}
		return result;
	}
	private static List<String> addFirstBackIn(List<String> list, String str, int j) {
		List<String> first = list.subList(0, j);
		List<String> last = list.subList(j, list.size());
		List<String> ans = new ArrayList<String>();
		ans.addAll(first);
		ans.add(str);
		ans.addAll(last);
		return ans;
	}

	static class Person{
		Map<String, Integer> personList = new HashMap<String, Integer>();
		public void addSeatedNextTo(String name, int value){
			personList.put(name, value);
		}
		public int getValue(String name){
			if(personList.containsKey(name)){
				return personList.get(name).intValue();
			}
			return 0;
		}
	}
}
