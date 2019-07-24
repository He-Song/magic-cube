package song.attendance.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.springframework.web.multipart.MultipartFile;

public class ExcelTool {
	
	private static Logger log = org.slf4j.LoggerFactory.getLogger(ExcelTool.class);

	/**
     * 判断文件的excel版本
     * @param fileName name of the excel
     * @return Return true if before 03
     */
    public static boolean isExcelOf03(String fileName) {
        if (null != fileName && fileName.endsWith("xls".toLowerCase())) {
            return true;
        }
        return false;
    }
    
    public static Sheet getFirstSheetFromExcel(MultipartFile file){
    	if (file.isEmpty()) {
            return null;
        }
        Workbook book = null;
        Sheet sheet = null;
        try {
            String fileName = file.getOriginalFilename();
            if (isExcelOf03(fileName)) {// 03版以前 的excel
                book = new HSSFWorkbook(file.getInputStream());
            } else {
                book = new XSSFWorkbook(file.getInputStream());
            }
            sheet = book.getSheetAt(0); // 现只考虑一个sheet页的情况
        }catch (Exception e ){
        	log.info("获取首个sheet页异常",e);
        }
        return sheet;
    }

}
