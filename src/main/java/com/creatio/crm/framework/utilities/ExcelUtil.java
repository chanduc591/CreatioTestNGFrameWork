package com.creatio.crm.framework.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	public static List<Map<String, String>> readData(String fileName, String sheetName) {

		List<Map<String, String>> data = new ArrayList<Map<String, String>>();

		try {

			// Read the Excel file using Java
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\TestData\\" + fileName);

			// Load the file into Excel related Class
			XSSFWorkbook wb = new XSSFWorkbook(fis);

			// Read the data from specific sheet
			XSSFSheet sh = wb.getSheet(sheetName);

			// Get the total rows & columns having data
			int totalRows = sh.getPhysicalNumberOfRows();
			int totalColumns = sh.getRow(0).getPhysicalNumberOfCells();

			// Create Loops and load row wise data (column name=column value) into maps and
			// then store them in List

			// Loop to iterate over rows
			for (int r = 1; r < totalRows; r++) {

				Map<String, String> rowData = new HashMap<String, String>();

				// Loop to iterate over columns
				for (int c = 0; c < totalColumns; c++) {
					String columnName = sh.getRow(0).getCell(c).getStringCellValue();
					String columnValue = sh.getRow(r).getCell(c).getStringCellValue();
					rowData.put(columnName, columnValue);
				}

				data.add(rowData);

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return data;
	}

	public static String[][] readExcelData(String fileName, String sheetName) {

		String[][] data = null;

		try {

			// Read the Excel file using Java
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\TestData\\" + fileName);

			// Load the file into Excel related Class
			XSSFWorkbook wb = new XSSFWorkbook(fis);

			// Read the data from specific sheet
			XSSFSheet sh = wb.getSheet(sheetName);

			// Get the total rows & columns having data
			int totalRows = sh.getPhysicalNumberOfRows();
			int totalColumns = sh.getRow(0).getPhysicalNumberOfCells();
			
			// Create a 2D array to store the data
			data = new String[totalRows][totalColumns];
			
			// Loop to iterate over rows
			for(int r=0; r<totalRows; r++) {
				
				// Loop to iterate over columns
				for(int c=0; c<totalColumns; c++) {
					data[r][c] = sh.getRow(r).getCell(c).getStringCellValue();
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return data;
	}
}
