package excel2txt;

import java.io.BufferedInputStream;

import java.io.File;

import java.io.FileInputStream;

import java.io.FileNotFoundException;

import java.io.IOException;

import java.text.DecimalFormat;

import java.text.SimpleDateFormat;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;

import org.apache.poi.hssf.usermodel.HSSFRow;

import org.apache.poi.hssf.usermodel.HSSFSheet;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;

 

public class ExcelUtil {

    /**

     * 璇诲彇Excel鐨勫唴瀹癸紝绗竴缁存暟缁勫瓨鍌ㄧ殑鏄竴琛屼腑鏍煎垪鐨勫�硷紝浜岀淮鏁扮粍瀛樺偍鐨勬槸澶氬皯涓

     * @param file 璇诲彇鏁版嵁鐨勬簮Excel

     * @param ignoreRows 璇诲彇鏁版嵁蹇界暐鐨勮鏁帮紝姣斿柣琛屽ご涓嶉渶瑕佽鍏� 蹇界暐鐨勮鏁颁负1

     * @return 璇诲嚭鐨凟xcel涓暟鎹殑鍐呭

     * @throws FileNotFoundException

     * @throws IOException

     */

	private static Calendar calendar = Calendar.getInstance();
	
    public static String[][] getData(File file, int ignoreRows)

           throws FileNotFoundException, IOException {

       List<String[]> result = new ArrayList<String[]>();

       int rowSize = 0;

       BufferedInputStream in = new BufferedInputStream(new FileInputStream(

              file));

       // 鎵撳紑HSSFWorkbook

       POIFSFileSystem fs = new POIFSFileSystem(in);

       HSSFWorkbook wb = new HSSFWorkbook(fs);

       HSSFCell cell = null;

       List<Integer> sheets = Setting.getInstance().getSheets();
       
       for (int index = 0; index < sheets.size(); index++) {
    	   int sheetIndex = sheets.get(index)-1;
    	   System.out.println("sheetIndex" + sheetIndex);
    	   
           HSSFSheet st = wb.getSheetAt(sheetIndex);

           // 绗竴琛屼负鏍囬锛屼笉鍙�

           for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++) {

              HSSFRow row = st.getRow(rowIndex);

              if (row == null) {

                  continue;

              }

              int tempRowSize = row.getLastCellNum() + 1;

              if (tempRowSize > rowSize) {

                  rowSize = tempRowSize;

              }

              String[] values = new String[rowSize];

              Arrays.fill(values, "");

              boolean hasValue = false;

              for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {

                  String value = "";

                  cell = row.getCell(columnIndex);

                  if (cell != null) {

                     // 娉ㄦ剰锛氫竴瀹氳璁炬垚杩欎釜锛屽惁鍒欏彲鑳戒細鍑虹幇涔辩爜

                     cell.setEncoding(HSSFCell.ENCODING_UTF_16);            

                     switch (cell.getCellType()) {

                     case HSSFCell.CELL_TYPE_STRING:

                         value = cell.getStringCellValue();

                         break;

                     case HSSFCell.CELL_TYPE_NUMERIC:

                         if (HSSFDateUtil.isCellDateFormatted(cell)) {

                            Date date = cell.getDateCellValue();

                            if (date != null) {

                                value = new SimpleDateFormat("yyyy-MM-dd")

                                       .format(date);

                            } else {

                                value = "";

                            }

                         } else {

                            value = new DecimalFormat("0").format(cell

                                   .getNumericCellValue());
                            
                            calendar.set(1900, 0, 0);
                            calendar.add(Calendar.DAY_OF_MONTH, Integer.parseInt(value)-1);                  
                            value = (calendar.get(Calendar.MONTH)+1) + "/" + calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.YEAR);        

                         }

                         break;

                     case HSSFCell.CELL_TYPE_FORMULA:

                         // 瀵煎叆鏃跺鏋滀负鍏紡鐢熸垚鐨勬暟鎹垯鏃犲��

                         if (!cell.getStringCellValue().equals("")) {

                            value = cell.getStringCellValue();

                         } else {

                            value = cell.getNumericCellValue() + "";

                         }

                         break;

                     case HSSFCell.CELL_TYPE_BLANK:

                         break;

                     case HSSFCell.CELL_TYPE_ERROR:

                         value = "";

                         break;

                     case HSSFCell.CELL_TYPE_BOOLEAN:

                         value = (cell.getBooleanCellValue() == true ? "Y"

                                : "N");

                         break;

                     default:

                         value = "";

                     }

                  }

                  if (columnIndex == 0 && value.trim().equals("")) {

                     break;

                  }

                  values[columnIndex] = rightTrim(value);

                  hasValue = true;

              }

 

              if (hasValue) {

                  result.add(values);

              }

           }

       }

       in.close();

       String[][] returnArray = new String[result.size()][rowSize];

       for (int i = 0; i < returnArray.length; i++) {

           returnArray[i] = (String[]) result.get(i);

       }

       return returnArray;

    }

   

    /**

     * 鍘绘帀瀛楃涓插彸杈圭殑绌烘牸

     * @param str 瑕佸鐞嗙殑瀛楃涓�

     * @return 澶勭悊鍚庣殑瀛楃涓�

     */

     public static String rightTrim(String str) {

       if (str == null) {

           return "";

       }

       int length = str.length();

       for (int i = length - 1; i >= 0; i--) {

           if (str.charAt(i) != 0x20) {

              break;

           }

           length--;

       }

       return str.substring(0, length);

    }

}


