package Days.Day19;

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

public class Day19 {
	static String KEYWORD = "KEY";
	public static void main(String... args) throws IOException, InterruptedException{
		List<String> lines = AdventFileReader.getLines("Day19input.txt");
		Map<String, ArrayList<String>> replacements = getReplacements(lines);

		String molecule = replacements.get(KEYWORD).get(0);
		Set<String> combinations = getCombinations(molecule, replacements);
		System.out.println("Part 1: How many distinct molecules can be created?\n" + combinations.size() + "\n");
		System.out.println("Part 2: fewest number of steps to go from e to the medicine molecule??\n" + getAnswer(molecule, replacements) + "\n");
	}

	public static int getAnswer(String molecule, Map<String, ArrayList<String>> replacements){
		int count = 0;	
		while(!molecule.equals("e")){
			for(String key: replacements.keySet()){
				for(String value: replacements.get(key)){
					if(molecule.contains(value)){
						int index = molecule.indexOf(value);
						molecule = molecule.substring(0, index) + key + molecule.substring(index + value.length());
						count++;
					}
				}
			}
		}
		return count;
	}

	//put all replacements from input into a map
	public static Map<String, ArrayList<String>> getReplacements(List<String> lines){
		Map<String, ArrayList<String>> replacements = new HashMap<String, ArrayList<String>>();
		Pattern pat = Pattern.compile("(\\w+) => (\\w+)");
		Matcher mat;
		for(String line: lines){
			mat = pat.matcher(line);
			if(mat.find()){
				String key = mat.group(1);
				String value = mat.group(2);
				if(!replacements.containsKey(key)){
					replacements.put(key, new ArrayList<String>());
				}
				replacements.get(key).add(value);
			}else if(!line.isEmpty()){
				replacements.put(KEYWORD, new ArrayList<String>());
				replacements.get(KEYWORD).add(line);
			}
		}
		return replacements;
	}

	//search through the String to find any matching keywords from the MAP and try a replacement, save to a SET and return SET
	public static Set<String> getCombinations(String molecule, Map<String, ArrayList<String>> replacements){
		Set<String> combinations = new HashSet<String>();
		StringBuilder candidate = new StringBuilder();
		StringBuilder stringStart = new StringBuilder();

		for(int i = 0; i < molecule.length(); i++){
			char charAt = molecule.charAt(i);
			if(Character.isUpperCase(charAt) || replacements.containsKey(charAt)){
				candidate = new StringBuilder();
			}

			stringStart.append(charAt);
			candidate.append(charAt);

			if(replacements.containsKey(candidate.toString())) {
				String endStr = molecule.substring(i+1 > molecule.length()? i : i+1);
				String beginStr = stringStart.substring(0, stringStart.length()-candidate.length());
				for(String replacementStr: replacements.get(candidate.toString())){
					combinations.add(beginStr + replacementStr + endStr);
				}
				candidate = new StringBuilder();
			}
		}
		return combinations;		
	}

}
