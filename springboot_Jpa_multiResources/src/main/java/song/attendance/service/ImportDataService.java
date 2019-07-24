package song.attendance.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import song.attendance.model.PunchRecord;
import song.attendance.util.DataPool;
import song.attendance.util.ExcelTool;

@Service
public class ImportDataService {
	
	private static Logger log = org.slf4j.LoggerFactory.getLogger(ImportDataService.class);
	
	/*
	 * 打卡
	 */
	public String analPunchExcel(MultipartFile file) {
		Sheet sheet = ExcelTool.getFirstSheetFromExcel(file);
		if(sheet == null){
			return "";
		}
		int rowSize = sheet.getLastRowNum() + 1;
		Row dateRow = sheet.getRow(2);
		for(int i=3; i<rowSize; ++i){ //从第3行开始解析
			Row row = sheet.getRow(i);
			row.getCell(3);
			String name = row.getCell(0).getStringCellValue().trim();
			for(int j = 5; j<row.getLastCellNum(); ++j){ // 从第5列开始解析打卡时间
				PunchRecord record = new PunchRecord();
				Cell cell = row.getCell(j);
				String cellValue = cell.getStringCellValue();
				String[] timeArray = cellValue.split(" \n");
				log.info(cell.toString());
				record.setStartTime(null != timeArray ? timeArray[0] : null);
				record.setEndTime(null != timeArray ? timeArray[timeArray.length-1] : null);
				record.setStaffName(name);
				record.setRecordDate(dateRow.getCell(j).getStringCellValue());
				DataPool.punchRecords.put(name, record);
			}
		}
		return "success";
	}

	/*
	 * 补卡
	 */
	public String analPatchExcel(MultipartFile file) {
		Sheet sheet = ExcelTool.getFirstSheetFromExcel(file);
		if(sheet == null){
			return "";
		}
		
		
		return "";
	}

	/*
	 * 加班
	 */
	public String analExtraworkExcel(MultipartFile file) {
		Sheet sheet = ExcelTool.getFirstSheetFromExcel(file);
		if(sheet == null){
			return "";
		}
		return "";
	}

	/*
	 * 请假
	 */
	public String analLeaveExcel(MultipartFile file) {
		Sheet sheet = ExcelTool.getFirstSheetFromExcel(file);
		if(sheet == null){
			return "";
		}
		return "";
	}

}
