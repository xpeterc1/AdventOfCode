package AdventUtil;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdventFileReader {
	public static List<String> getLines(String fileName) throws IOException{
		FileReader fileReader = new FileReader(System.getProperty("user.dir") + "/input/" + fileName);
		BufferedReader br = new BufferedReader(fileReader);
		List<String> list = new ArrayList<String>();
		String line = null;
		while((line = br.readLine()) != null){
			list.add(line);
		}
		return list;		
	}
}
