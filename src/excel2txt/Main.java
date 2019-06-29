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
		
		System.out.println("teacher " + Setting.getInstance().getTeacher("﻿英语"));
		System.out.println("teacher " + Setting.getInstance().getTeacher("英语"));
		
		System.out.println("teacher " + Setting.getInstance().getTeacher("物理"));
		System.out.println("teacher " + Setting.getInstance().getTeacher("数学"));
		System.out.println("teacher " + Setting.getInstance().getTeacher("化学"));
		System.out.println("teacher " + Setting.getInstance().getTeacher("语文"));
		
		String[][] result = ExcelUtil.getData(file, 1);
		
		new DataHandler().print2Txt(result, directory + fileName);

	}
	
}
