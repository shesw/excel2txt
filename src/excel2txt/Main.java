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
		
		Setting.init(directory);

		System.out.println("file path: " + file.getPath());
		
		String[][] result = ExcelUtil.getData(file, 1);
		
		new DataHandler().print2Txt(result, directory + fileName);

	}
	
}
