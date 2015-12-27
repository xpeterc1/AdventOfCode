package Days.Day23;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import AdventUtil.AdventFileReader;

public class Day23 {
	public static void main(String... args) throws IOException{
		List<String> input = AdventFileReader.getLines("Day23input.txt");
		System.out.println("Part 1: " + getAnswer(0, input));	
		System.out.println("Part 2: " + getAnswer(1, input));	
	}

	public static int getAnswer(int a, List<String> input){
		int[] register = new int[2];
		register[0] = a;
		int instructionCounter = 0;
		Pattern pat = Pattern.compile("^(\\w{3}) ((\\+|-)?\\w+)(, ((\\+|-)?\\d+))?");
		while( instructionCounter < input.size()){
			Matcher mat = pat.matcher(input.get(instructionCounter));
			String instruction = null;
			int regNum = 0;
			if(mat.find()){
				instruction = mat.group(1);
				regNum = mat.group(2).equals("a")? 0 : 1;
			}
			switch(instruction){
			case "hlf":
				register[regNum] /= 2;
				instructionCounter++;
				break;
			case "tpl":
				register[regNum] *= 3;
				instructionCounter++;
				break;
			case "inc":
				register[regNum]++;
				instructionCounter++;
				break;
			case "jmp":
				instructionCounter += Integer.parseInt(mat.group(2));
				break;
			case "jie":
				instructionCounter += register[regNum]%2==0? Integer.parseInt(mat.group(5)): 1; 
				break;
			case "jio":
				instructionCounter += register[regNum]==1? Integer.parseInt(mat.group(5)): 1; 
				break;
			}	
		}	

		return register[1];
	}
}
