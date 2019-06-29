package excel2txt;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

public class DataHandler {
	
	public DataHandler() {
		// TODO Auto-generated constructor stub
	}
	
	public void print2Txt(String[][] result, String filePath, Setting setting) {
		int rowLength = result.length;
		String savePath = filePath.substring(0, filePath.lastIndexOf(".")) + "txt";
		
		try {
			BufferedWriter bWriter = new BufferedWriter(new FileWriter(savePath));
			
			for(int i=0;i<rowLength;i++) {
				
				for(int j=0;j<result[i].length;j++) {
					String item = result[i][j];
					System.out.print(item+"\t\t");
					if (Pattern.matches("././.", item)) {
						
					}
			
			    }
			
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
