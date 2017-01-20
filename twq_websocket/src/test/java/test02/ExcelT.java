package test02;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelT {

	public static void main(String[] args) throws Exception {
		FileInputStream fis = new FileInputStream(new File("e:\\计划导入格式.xlsx"));
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sheet = wb.getSheetAt(0);
		for (Row row : sheet) {
			for (Cell cell : row) {
				String strCell = "";
		        switch (cell.getCellType()) {
		        case Cell.CELL_TYPE_STRING:
		            strCell = cell.getStringCellValue();
		            break;
		        case Cell.CELL_TYPE_NUMERIC:
		        	if (HSSFDateUtil.isCellDateFormatted(cell)) {
		        		strCell = cell.getDateCellValue().toString();
		        	}else {
		        		strCell = String.valueOf(cell.getNumericCellValue());
					}
		            break;
		        case Cell.CELL_TYPE_BOOLEAN:
		            strCell = String.valueOf(cell.getBooleanCellValue());
		            break;
		        case Cell.CELL_TYPE_BLANK:
		            strCell = "";
		            break;
		        default:
		            strCell = "";
		            break;
		        }
		        System.out.println(strCell);
			}
		}
	}
}
