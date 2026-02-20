package com.qa.api.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
    
    
	private static String TEST_DATA_SHEET_PATH = "./src/test/resources/TestData/ApiTestData.xlsx";

           

	public static Object[][] readData(String sheetName) throws IOException {

		Object[][] data;

			try (FileInputStream ip = new FileInputStream(TEST_DATA_SHEET_PATH);
			     Workbook book = WorkbookFactory.create(ip)) {
			
			Sheet sheet = book.getSheet(sheetName);
			
			DataFormatter formatter = new DataFormatter();
			
			int rows = sheet.getLastRowNum();
			
			int cols = sheet.getRow(0).getLastCellNum();
			
			data = new Object[rows][cols];
			
			for (int i = 0; i < rows; i++) {
			    for (int j = 0; j < cols; j++) {
			        data[i][j] = formatter.formatCellValue(sheet.getRow(i + 1).getCell(j));
			    }
			}
			return data;
    }
}
    
}