package excel2txt;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

public class FileOpenUtil {

	public static String[] openFileBrowser() {
		int result = 0;
		File file = null;
		String path = null;
		String fileName = null;
		JFileChooser fileChooser = new JFileChooser();
		FileSystemView fsv = FileSystemView.getFileSystemView();  //注意了，这里重要的一句
		System.out.println(fsv.getHomeDirectory());                //得到桌面路径
		fileChooser.setCurrentDirectory(fsv.getHomeDirectory());
		fileChooser.setDialogTitle("请选择要上传的文件...");
		fileChooser.setApproveButtonText("确定");
		fileChooser.setMultiSelectionEnabled(false);
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		result = fileChooser.showOpenDialog(null);
		if (JFileChooser.APPROVE_OPTION == result) {
		    	   path=fileChooser.getSelectedFile().getPath();
		    	   fileName = fileChooser.getSelectedFile().getName();
		    	   path = path.substring(0, path.indexOf(fileName));
		    	   System.out.println("path: "+path);
		    	   System.out.println("fileName: " + fileName);
		   }
		
		
		return new String[] {path, fileName};
	}
	
}
