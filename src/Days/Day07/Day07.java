package Days.Day07;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import AdventUtil.AdventFileReader;


public class Day07 {
	static Map<String, Node> wires = new HashMap<String, Node>();
	public static void main(String[] args) throws IOException{
		List<String> lines = AdventFileReader.getLines("Day07input.txt");
		
		setWires(lines);
		int part1Value = wires.get("a").getValue();
		System.out.println("Part 1: " + part1Value);

		setWires(lines);
		wires.get("b").setValue(part1Value);
		int part2Value = wires.get("a").getValue();
		System.out.println("Part 2: " + part2Value);
	}

	public static void setWires(List<String> lines){
		final Pattern pattern1 = Pattern.compile("([a-z]{1,2}|\\d+) (AND|OR|RSHIFT|LSHIFT) ([a-z]{1,2}|\\d+) -> ([a-z]{1,2})");
		final Pattern pattern2 = Pattern.compile("(NOT)?+( |)([a-z]{1,2}|\\d+) -> ([a-z]{1,2})");

		for(String line: lines){
			Matcher matcher = pattern1.matcher(line);
			if (matcher.find()) {
				if(isInteger(matcher.group(1))){
					wires.put(matcher.group(4), new Node(matcher.group(3), Integer.parseInt(matcher.group(1)), matcher.group(2)));
				}else if(isInteger(matcher.group(3))){
					wires.put(matcher.group(4), new Node(matcher.group(1), Integer.parseInt(matcher.group(3)), matcher.group(2)));
				}else{
					wires.put(matcher.group(4), new Node(matcher.group(1), matcher.group(3), matcher.group(2)));	
				}
			}else{
				matcher = pattern2.matcher(line);
				if(matcher.find()){
					if(matcher.group(1) == null){
						if(isInteger(matcher.group(3))){
							wires.put(matcher.group(4), new Node(Integer.valueOf(matcher.group(3).trim())));
						}else{
							wires.put(matcher.group(4), new Node(matcher.group(3)));
						}
					}else{
						wires.put(matcher.group(4), new Node(matcher.group(3), matcher.group(1)));
					}
				}
			}
		}
	}

	public static boolean isInteger(String s) {
		try { 
			Integer.parseInt(s); 
		} catch(NumberFormatException e) { 
			return false; 
		} catch(NullPointerException e) {
			return false;
		}
		return true;
	}

	public static class Node{
		private String gate1;
		private String gate2;
		private int instanceValue;
		private String instruction;
		private int value = 0;
		private boolean doComputeValue = false;

		public Node(int value){
			this.value = value;
			doComputeValue = true;
		}
		
		public Node(String gate1){
			this.gate1 = gate1;
		}
		
		public Node(String gate1, String instruction){
			this.gate1 = gate1;
			this.instruction = instruction;
		}
		
		public Node(String gate1, String gate2, String instruction){
			this.gate1 = gate1;
			this.gate2 = gate2;
			this.instruction = instruction;
		}
		
		public Node(String gate1, int instanceValue, String instruction){
			this.gate1 = gate1;			
			this.instanceValue = instanceValue;
			this.instruction = instruction;
		}
		
		public void setValue(int value){
			this.value = value;
		}
		
		public void computeValue(){
			int firstValue = wires.get(gate1).getValue();
			if(instruction == null){
				value = firstValue;
				return;
			}

			int secondValue = instanceValue;
			if(gate2 == null){
				if(instruction.equals("NOT")){
					value = ~firstValue;
				}else{
					compute(firstValue, secondValue);
				}
			}else{
				secondValue = wires.get(gate2).getValue();
				compute(firstValue, secondValue);
			}
			doComputeValue = true;
		}

		private void compute(int firstValue, int secondValue){
			switch(instruction){
			case "AND":
				value = firstValue & secondValue;
				break;
			case "OR":
				value = firstValue | secondValue;
				break;
			case "LSHIFT": 
				value = firstValue << secondValue;
				break;
			case "RSHIFT":
				value = firstValue >> secondValue;
				break;
			}
		}

		public int getValue(){
			if(!doComputeValue){
				computeValue();
			}
			return value;
		}
		
		public boolean computed(){
			return doComputeValue;
		}
	}
}

