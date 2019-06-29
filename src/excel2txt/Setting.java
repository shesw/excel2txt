package excel2txt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
		private List<Integer> sheets = new ArrayList<>();
		private boolean isTrim = true;
		
		public void init (String path) {
			String filePath = path + "setting.txt";
			teacherCourseMap = new HashMap<>();
			
			System.out.println(filePath);
			
			try {
				BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
				String line = "";
				while ( ( line = bufferedReader.readLine() ) != null  ) {
					String[] lines = line.split(" ");
					if ("countPerDay".equals(lines[0])) {
						courseTimeCount = Integer.parseInt(lines[1]);
					} else if("sheet".equals(lines[0]) || "sheets".equals(lines[0])) {
						sheets.clear();
						for(int i = 1; i < lines.length; i++) {
							System.out.println(lines[i]);
							sheets.add(new Integer(lines[i]));
						}
					} else if("trim".equals(lines[0])){
						int trim = Integer.parseInt(lines[1]);
						isTrim = !(trim == 0);
					} else {
						if (lines.length == 2) {
							teacherCourseMap.put(lines[0], lines[1]);
							System.out.println(lines[0]+ " " +lines[1]);
						}
					}
				
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public String getTeacher(String course) {
			if (TextUtil.isEmpty(course)) return "";
			String teacher = teacherCourseMap.get(course);
			
			if (TextUtil.isEmpty(teacher)) {
				
				for(Map.Entry<String, String> entry : teacherCourseMap.entrySet()) {
					if (course.contains(entry.getKey())) {
						teacher = entry.getValue();
						break;
					}
				}
			}
			
			return teacher;
		}
		
		public int getCourseTimeCount() {
			return courseTimeCount;
		}
		
		public List<Integer> getSheets() {
			return sheets;
		}
		
		public boolean isTrim() {
			return isTrim;
		}
		
	}

}
