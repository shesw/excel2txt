package excel2txt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Setting {
	
	private Map<String, String> teacherCourseMap;
	
	public Setting(String path) {
		// TODO Auto-generated constructor stub	
		init(path);
	}
	
	private void init(String path) {
		String filePath = path + "setting.txt";
		teacherCourseMap = new HashMap<>();
		System.out.println(filePath);
		
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
			String line = "";
			while ( ( line = bufferedReader.readLine() ) != null  ) {
				String[] lines = line.split(" ");
				teacherCourseMap.put(lines[0], lines[1]);
				System.out.println(lines[0]+ " " +lines[1]);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public String getTeacher(String course) {
		return teacherCourseMap.get(course);
	}

}
