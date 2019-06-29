package excel2txt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
	
	
	public static void main(String args[]) throws FileNotFoundException, IOException {
		
		String[] paths = FileOpenUtil.openFileBrowser();
		String directory = paths[0];
		String fileName = paths[1];

		File file = new File(directory + fileName);

		System.out.println("file path: " + file.getPath());
		
		String[][] result = ExcelUtil.getData(file, 1);
		
		int rowLength = result.length;
		
		for(int i=0;i<rowLength;i++) {
		
			for(int j=0;j<result[i].length;j++) {
				
				System.out.print(result[i][j]+"\t\t");
		
		    }
		
			System.out.println();
		
		}
	}
	
	
	
}
