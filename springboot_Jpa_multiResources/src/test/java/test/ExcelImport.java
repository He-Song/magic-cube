package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

public class ExcelImport {

	@Test
	public void test() throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		System.out.println(123);
		File excel = new File("C:\\Users\\76797\\Desktop\\testExcel.xlsx");
		Workbook book = new XSSFWorkbook(new FileInputStream(excel));
		Sheet sheet = book.getSheetAt(0);
		System.out.println(sheet.getLastRowNum());
		for (int i = 0; i <= sheet.getLastRowNum(); ++i) {
			Row row = sheet.getRow(i);
			for (int index = 0; index < row.getLastCellNum(); ++index) {
				Cell cell = row.getCell(index);
				System.out.println("row:" + i + "; col:" + index + cell);
				System.out.println(cell.getAddress());
				
				
			}
		}
		int mergedCellNum = sheet.getNumMergedRegions();
		List<CellRangeAddress> address = new LinkedList<>();
		for(int i = 0 ; i< mergedCellNum; ++i){
			CellRangeAddress adr = sheet.getMergedRegion(i);
			System.out.println(adr);
			address.add(adr);
		}
//		sheet.addMergedRegion(new CellRangeAddress(0,5,0,0));
	}

}
