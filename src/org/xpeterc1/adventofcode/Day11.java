package org.xpeterc1.adventofcode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day11 {
	static final String INPUT = "cqjxjnds";
	public static void main(String... args){
		String password = getPassword(INPUT);
		System.out.println("Part 1: " + password);
		String secondPassword = getPassword(password);
		System.out.println("Part 2: " + secondPassword);
	}
	
	public static String getPassword(String input){
		char[] password = new String(input).toCharArray();
		while(true){
			incrementChar(password);
			if(firstCheck(password) && secondCheck(password) && thirdCheck(password)){
				return new String (password);
			}
		}
	}
	
	public static boolean firstCheck(char[] charArray){
		int length = charArray.length-3;
		for(int i = 0; i < length; i++){
			if(charArray[i] + 1 == charArray[i+1]){
				if(charArray[i+1] + 1 == charArray[i+2]){
					return true;
				}
			}
		}
		return false;
	}

	public static boolean secondCheck(char[] charArray){
		String temp = charArray.toString();
		return temp.length() - temp.replaceAll("i|o|l", "").length() == 0;
	}

	public static boolean thirdCheck(char[] charArray){
		Pattern pat = Pattern.compile("(?<PAIR>[a-z])\\k<PAIR>");
		Matcher mat = pat.matcher(new String(charArray));
		int count = 0;
		while(mat.find()){
			count++;
		}
		return count == 2;
	}


	public static void incrementChar(char[] charArray){
		int pos = charArray.length - 1;
		while(charArray[pos] == 'z' && pos > 0){
			charArray[pos] = 'a';
			pos--;
		}
		charArray[pos] = (char)(charArray[pos] == 'z'? 'a': charArray[pos]+1);

	}

}
