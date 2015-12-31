package Days.Day25;

public class Day25 {
	static final int row = 3010;
	static final int col = 3019;
	public static void main(String... args){
		long range = getRange();
		long code = 20151125;
		for(long i = 0; i < range; i++){
			code = getNextCode(code);
		}
		System.out.println("Part 1: " + code);
		
	}
	public static long getNextCode(long code){
		return (code * 252533) % 33554393;
 	}
	public static long getRange(){
		long sum = 0;
		int range = row + col - 1;
		for(int i = 0; i < range; i++){
			sum+=i;
		}
		return sum + col -1;
	}
	
}
