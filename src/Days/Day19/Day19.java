package Days.Day19;

import java.io.IOException;
import java.util.ArrayList;
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

		//gets the long input value from file
		String molecule = replacements.get(KEYWORD).get(0);

		int part1 = getCombinationsSize(molecule, replacements);
		System.out.println("Part 1: How many distinct molecules can be created?\n" + part1 + "\n");

		int part2 = getMoleculeStepNum(new String(molecule), replacements, 0);
		System.out.println("Part 2: Fewest number of steps to go from e to the medicine molecule??\n" + part2 + "\n");
	}

	//pulls all replacements values from input file and store into a MAP
	public static Map<String, ArrayList<String>> getReplacements(List<String> lines){
		Map<String, ArrayList<String>> replacements = new HashMap<String, ArrayList<String>>();
		Pattern pat = Pattern.compile("(\\w+) => (\\w+)");
		for(String line: lines){
			Matcher mat = pat.matcher(line);
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

	//search for substrings that are equal to keys to MAP, replace substring with value from MAP, save the new string to SET
	public static int getCombinationsSize(String molecule, Map<String, ArrayList<String>> replacements){
		Set<String> combinations = new HashSet<String>();
		StringBuilder candidate = new StringBuilder();

		for(int i = 0; i < molecule.length(); i++){
			char charAt = molecule.charAt(i);
			if(Character.isUpperCase(charAt) || replacements.containsKey(charAt)){
				candidate = new StringBuilder();
			}

			candidate.append(charAt);

			if(replacements.containsKey(candidate.toString())) {
				String beginStr = molecule.substring(0, i+1-candidate.length());
				String endStr = molecule.substring(i+1);
				for(String replacementStr: replacements.get(candidate.toString())){
					combinations.add(beginStr + replacementStr + endStr);
				}
				candidate = new StringBuilder();
			}
		}
		return combinations.size();		
	}

	//starting with long input string, recursively search for a substring we can replace till string equals e, if not backtrack
	public static int getMoleculeStepNum(String molecule, Map<String, ArrayList<String>> replacements, int count){
		if(molecule.equals("e")){
			return count;
		}
		for(String key: replacements.keySet()){
			for(String value: replacements.get(key)){
				int index = -1;
				if((index = molecule.indexOf(value)) != -1){
					String nextMolecule = molecule.substring(0, index) + key + molecule.substring(index + value.length());
					return getMoleculeStepNum(nextMolecule, replacements, count + 1);
				}
			}
		}
		return count;
	}
}