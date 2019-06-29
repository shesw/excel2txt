package excel2txt;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

public class DataHandler {
	
	public DataHandler() {
		// TODO Auto-generated constructor stub
	}
	
	public void print2Txt(String[][] result, String filePath) {
		int rowLength = result.length;
		String savePath = filePath.substring(0, filePath.lastIndexOf(".")) + ".txt";

		Setting.Holder setting = Setting.getInstance();
		
		try {
			BufferedWriter bWriter = new BufferedWriter(new FileWriter(savePath));
			
			for(int i=0;i<rowLength;i++) {
				
				for(int j=0;j<result[i].length;j++) {
					String item = result[i][j];
					System.out.println(item+"\t\t");
					System.out.println(Pattern.matches(".*/.*/.*", item));
					if (Pattern.matches(".*/.*/.*", item)) {
						int firstIndex = item.indexOf("/");
						int month = Integer.parseInt(item.substring(0, firstIndex));
						int secondIndex = item.indexOf("/", firstIndex+1);
						int day = Integer.parseInt(item.substring(firstIndex+1, secondIndex));
						
						for (int k = 1; k <= setting.getCourseTimeCount(); k++) {
							String date = month + "." + day;
							String time = result[i+k][0];
							String course = result[i+k][j];
							String teacherName = setting.getTeacher(result[i+k][j]);
							
							if (TextUtil.isEmpty(course) && setting.isTrim()) continue;
							
							bWriter.newLine();
							bWriter.write(date + "\t\t"); //写日期
							bWriter.write(time + "\t\t");	//写时刻
							bWriter.write(course + "\t\t");	//写课程
							bWriter.write(TextUtil.isEmpty(teacherName) ? "" : teacherName); //写老师名字
						}
					}
			    }
			}
			
			try {
				bWriter.flush();
				bWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
