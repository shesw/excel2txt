package excel2txt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Setting {
	
	private static Holder _Holder;
	
	public static void init(String path) {
		
		_Holder = new Holder();
		
		_Holder.init(path);
		
		
	}
	
	public static Holder getInstance() {
		return _Holder;
	}
	

	static class Holder {
		
		private Map<String, String> teacherCourseMap;
		private int courseTimeCount = 5;
		
		public void init (String path) {
			String filePath = path + "setting.txt";
			teacherCourseMap = new HashMap<>();
			
			System.out.println(filePath);
			
			try {
				BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
				String line = "";
				while ( ( line = bufferedReader.readLine() ) != null  ) {
					String[] lines = line.split(" ");
					if (lines.length == 2) {
						teacherCourseMap.put(lines[0], lines[1]);
						System.out.println(lines[0]+ " " +lines[1]);
					} else if(lines.length == 1) {
						courseTimeCount = Integer.parseInt(line);
					}

				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public String getTeacher(String course) {
			if (TextUtil.isEmpty(course)) return "";
			return teacherCourseMap.get(course);
		}
		
		public int getCourseTimeCount() {
			return courseTimeCount;
		}
		
	}

}
