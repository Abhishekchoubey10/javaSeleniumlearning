package com.Qa.OpenCard.Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils {

	// Excel Sheet path
	private static Workbook book;
	private static Sheet sheet;

	public static Object[][] getTestData(String sheetName) {// here sheetName is a sheet in Excel file
		Object data[][] = null;

		try {
			FileInputStream ip = new FileInputStream(Constants.TEST_DATA_SHEET_PATH);
			// WorkBookFactory class is used to create a file in java memory form excel
			// sheet

			book = WorkbookFactory.create(ip);// create method will give us a Workbook reference, we have maintain
												// workbook
			sheet = book.getSheet(sheetName); // reference
			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

			for (int i = 0; i < sheet.getLastRowNum(); i++) {
				for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
					data[i][j] = sheet.getRow(i + 1).getCell(j).toString();// we have use toString method, because
																			// Selenium
																			// always reads and store value in field on
																			// String
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return data;
	}
}
