package Days.Day12;

import java.util.List;
import org.json.*;

import AdventUtil.AdventFileReader;

public class Day12 {
	static final String EXCLUDE = "red";
	public static void main(String[] args) throws Exception {
		List<String> lines = AdventFileReader.getLines("Day12input.txt");
		for(String line: lines){
			System.out.println("Part 1: " + getValue(new JSONArray(line), false));			
			System.out.println("Part 2: " + getValue(new JSONArray(line), true));			
		}
	}
	
	static int getValue(Object input, boolean exclusion) throws Exception {
		int total = 0;
		Object object = new JSONTokener(input.toString()).nextValue();
		if (object instanceof JSONArray) {
			for (int i = 0; i < ((JSONArray)object).length(); ++i) {
				total += getValue(((JSONArray)object).get(i), exclusion);;   
			}
			return total;
		}
		
		if (object instanceof JSONObject) {
			JSONObject jsonObject = (JSONObject) object;
			JSONArray names = jsonObject.names();
			for (int i = 0; i < names.length(); i++) {
				String name = (String) names.get(i);
				if (jsonObject.get(name).equals(EXCLUDE) && exclusion) {
					return 0;
				} else {
					total += getValue(jsonObject.get(name), exclusion);
				}
			}
			return total;
		}
		
		if (isInteger(object.toString())){
			return Integer.parseInt(object.toString());
		}
		return 0;
	}	

	public static boolean isInteger(String str) {
		try { 
			Integer.parseInt(str); 
		} catch(NumberFormatException e) { 
			return false; 
		} catch(NullPointerException e) {
			return false;
		}
		return true;
	}

	
}
