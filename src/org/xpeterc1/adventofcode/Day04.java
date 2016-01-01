package org.xpeterc1.adventofcode;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Day04 {
	static String INPUT = "ckczppom";
	public static void main(String... args) throws NoSuchAlgorithmException{
		System.out.println("Part 1: " + getAnswer(INPUT, false));
		System.out.println("Part 2: " + getAnswer(INPUT, true));
	}
	public static int getAnswer(String input, boolean part2) throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance("MD5");
		int i = 0;
		byte[] arr;
		while(true) {
			arr = md.digest(input.concat(Integer.toString(i++)).getBytes());
			if(arr[0] == 0 && arr[1] == 0 && (arr[2]>> 4 & 0xf) == 0) {
				if(arr[2] == 0 && part2){
					return i-1;
				}else if(!part2){
					return i-1;
				}
			}
		}
	}
}
