package Days.Day15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import AdventUtil.AdventFileReader;

public class Day15 {
	public static int TEASPOONS = 100;
	public static int CALORIES_TARGET = 500;

	public static void main(String... args) throws Exception{
		List<String> lines = AdventFileReader.getLines("Day15input.txt");
		List<Ingredient> ingredient = new ArrayList<Ingredient>();
		for(String line: lines){
			Pattern pat = Pattern.compile("([A-Za-z]+): capacity (-?\\d+), durability (-?\\d+), flavor (-?\\d+), texture (-?\\d+), calories (-?\\d+)");
			Matcher mat = pat.matcher(line);
			if(mat.find()){
				int capacity = Integer.parseInt(mat.group(2));
				int durability = Integer.parseInt(mat.group(3));
				int flavor = Integer.parseInt(mat.group(4));
				int texture = Integer.parseInt(mat.group(5));
				int calories = Integer.parseInt(mat.group(6));
				ingredient.add(new Ingredient(capacity, durability, flavor, texture, calories));
			}				
		}
		List<ArrayList<Integer>> recpies = multichoose(TEASPOONS, ingredient.size());
		List<Integer> scoresPart1 = new ArrayList<Integer>();
		List<Integer> scoresPart2 = new ArrayList<Integer>();

		for(List<Integer> ingredientAmount: recpies){
			int length = ingredientAmount.size();
			int[] ingredientTotal = new int[5];
			Arrays.fill(ingredientTotal, 0);

			for(int i = 0; i < length; i++){
				int amount = ingredientAmount.get(i);
				Ingredient ingred = ingredient.get(i);
				ingredientTotal[0] += ingred.getCapacity() * amount;
				ingredientTotal[1] += ingred.getDurability() * amount;
				ingredientTotal[2] += ingred.getFlavor() * amount;
				ingredientTotal[3] += ingred.getTexture() * amount;
				ingredientTotal[4] += ingred.getCalories() * amount;
			}

			int total = 1;
			for(int i = 0; i < 4; i++){
				total *= ingredientTotal[i] < 0? 0 : ingredientTotal[i];
			}
			scoresPart1.add(total);
			if(ingredientTotal[4] == CALORIES_TARGET){
				scoresPart2.add(total);
			}
		}
		System.out.println("Part 1: " + Collections.max(scoresPart1));
		System.out.println("Part 2: " + Collections.max(scoresPart2));

	}

	public static ArrayList<ArrayList<Integer>> multichoose(int target, int size){
		ArrayList<ArrayList<Integer>> lists = multichooseUtil(target, size);
		ArrayList<ArrayList<Integer>> answer = new ArrayList<ArrayList<Integer>>();
		for(ArrayList<Integer> list: lists){
			if(getSum(list) == target){
				answer.add(list);
			}
		}
		return answer;
	}

	public static ArrayList<ArrayList<Integer>> multichooseUtil(int target, int size){
		ArrayList<ArrayList<Integer>> answer = new ArrayList<ArrayList<Integer>>();
		if(size <= 1){
			for(int i = 0; i <= target; i++){
				ArrayList<Integer> arrList = new ArrayList<Integer>();
				arrList.add(i);
				answer.add(arrList);
			}
			return answer;
		}
		ArrayList<ArrayList<Integer>> lists = multichooseUtil(target, size - 1);
		for(ArrayList<Integer> list: lists){
			int range = target - getSum(list);
			for(int i = 0; i <= range; i++){
				ArrayList<Integer> al = new ArrayList<Integer>(list);
				al.add(i);
				answer.add(al);
			}
		}
		return answer;
	}

	public static int getSum(ArrayList<Integer> list){
		int sum = 0;
		for(Integer i: list){
			sum += i;
		}
		return sum;
	}

	public static class Ingredient{

		private int capacity;
		private int durability;
		private int flavor;
		private int texture;
		private int calories;

		public Ingredient(int capacity, int durability, int flavor, int texture, int calories){
			this.capacity = capacity;
			this.durability = durability;
			this.flavor = flavor;
			this.texture = texture;
			this.calories = calories;
		}

		public int getCapacity() {
			return capacity;
		}

		public int getDurability() {
			return durability;
		}

		public int getFlavor() {
			return flavor;
		}

		public int getTexture() {
			return texture;
		}

		public int getCalories() {
			return calories;
		}

	}
}
