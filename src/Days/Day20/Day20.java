package Days.Day20;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import AdventUtil.AdventFileReader;

public class Day20 {
	public static void main(String...args) throws IOException{
		int target = 36000000;
		System.out.println("Part 1: " + part1(target));
		System.out.println("Part 2: " + part2(target));


	}

	public static int part1(int target){
		int house = 831599;
		int value = 0;
		while(value <= target){
			value = 0;
			house++;
			for(int elf = 1; elf <= house; elf++){
				if(house%elf==0){
					value += elf*10;
					if(value == target){
						break;
					}
				}
			}
		}
		return house;
	}

	public static int part2(int target){
		int house = 884519;//862661
		int value = 0;
		int maxH = 0;
		int maxV = 0;
		while(value <= target){
			value = 0;
			house++;
			for(int elf = (house/50)+1; elf <= house; elf++){
				if(house%elf==0){
					value += elf*11;
					if(value == target){
						break;
					}
				}
			}
			if(value > maxV){
				maxV = value;
				maxH = house;
			}
		}
		return house;
	}
}
