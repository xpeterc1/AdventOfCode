package Days.Day14;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import AdventUtil.AdventFileReader;

public class Day14 {
	//Day 14: Reindeer Olympics
	static int PUZZLE_INPUT = 2503;
	
	public static void main(String... args) throws IOException{
		List<String> lines = AdventFileReader.getLines("Day14input.txt");
		Map<String, Reindeer> reindeers = new HashMap<String, Reindeer>();
		Pattern pat = Pattern.compile("([A-Za-z]+) can fly (\\d+) km/s for (\\d+) seconds, but then must rest for (\\d+) seconds.");
		Matcher mat;

		for(String line: lines){
			mat = pat.matcher(line);
			if(mat.find()){
				String reindeerName = mat.group(1);
				int speed = Integer.parseInt(mat.group(2));
				int seconds = Integer.parseInt(mat.group(3));
				int restTime = Integer.parseInt(mat.group(4));
				reindeers.put(reindeerName, new Reindeer(speed, seconds, restTime));
			}
		}
		List<String> reindeerNameList = new ArrayList<String>(reindeers.keySet());
		for(int i = 0; i < PUZZLE_INPUT; i++){
			for(String name: reindeerNameList){
				reindeers.get(name).increment();
			}
		}
		System.out.println("Part 1: " + reindeers.get(reindeerNameList.get(0)).getMax());
	}
	static class Reindeer{
		private int speed, seconds, restTime;
		static int maxDistanceTraveled = 0;

		private int distanceTraveled = 0;
		private int travelTime = 0;
		private int restCooldown = 0;
		private boolean cooldown = false;

		public Reindeer(int speed, int seconds, int restTime){
			this.speed = speed;
			this.seconds = seconds;
			this.restTime = restTime;			
		}

		public void increment(){
			if(restCooldown == 0){
				distanceTraveled += speed;
				if(distanceTraveled > maxDistanceTraveled) maxDistanceTraveled = distanceTraveled;
				travelTime++;
				if(travelTime == seconds){
					travelTime = 0;
					restCooldown = restTime;
				}
			}else{
				restCooldown--;
			}
		}
		
		public int getMax(){
			return maxDistanceTraveled;
		}
	}
}
