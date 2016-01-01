package org.xpeterc1.adventofcode;

import java.io.IOException;
import java.util.List;

import AdventUtil.AdventFileReader;

public class Day18 {
	public static void main(String... args) throws IOException{
		List<String> lines = AdventFileReader.getLines("Day18input.txt");
		System.out.println("Part 1: " + countLights(lines, false));
		System.out.println("Part 2: " + countLights(lines, true));
	}

	public static boolean[][] update(boolean[][] lights, boolean part2){
		boolean[][] updatedLights = new boolean[100][100];
		int val;
		if(part2){
			lights[0][0] = true;
			lights[0][99] = true;
			lights[99][0] = true;
			lights[99][99] = true;
		}
		for(int i = 0; i < 100; i++){
			for(int j = 0; j < 100; j++){
				if(lights[i][j]){
					updatedLights[i][j] = (((val = getNeighbors(i, j, lights)) == 2 || val == 3)? true : false);
				}else{
					updatedLights[i][j] = (getNeighbors(i, j, lights) == 3? true : false);
				}
			}
		}
		if(part2){
			updatedLights[0][0] = true;
			updatedLights[0][99] = true;
			updatedLights[99][0] = true;
			updatedLights[99][99] = true;
		}
		return updatedLights;
	}

	public static int getNeighbors(int x, int y, boolean[][] lights){
		int count = 0;
		for (int i = (x > 0 ? -1 : 0); i < (x < 99 ? 2 : 1); i++) {
			for (int j = (y > 0 ? -1 : 0); j < (y < 99 ? 2 : 1); j++) {
				if (!(i == 0 && j == 0) && lights[x + i][y + j]) 
					count++;
			}
		}
		return count;
	}

	public static int countLights(List<String> lines, boolean part2){
		boolean[][] lights = new boolean[100][100];
		for(int i = 0; i < 100; i++){
			String line = lines.get(i);
			for(int j = 0; j < 100; j++){
				lights[i][j] = (line.charAt(j) == '#');
			}
		}
		for(int cycle = 0; cycle < 100; cycle++){
			lights = update(lights, part2);
		}
		int count = 0;
		for(int i = 0; i < 100; i++){
			for(int j = 0; j < 100; j++){
				if(lights[i][j]){
					count++;
				}
			}
		}
		return count;
	}

}	
