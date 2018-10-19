package com.qa.week7assessment;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ExcelUtilities {
    private static XSSFSheet ExcelWorksheet;
    private static XSSFWorkbook ExcelWorkbook;
    private static XSSFCell Cell;
    private static XSSFRow Row;

    //set file path and open the excel file
    public static void setExcelFile(String Path, int sheetIndex) {
        try {
            FileInputStream ExcelFile = new FileInputStream(Path);
            ExcelWorkbook = new XSSFWorkbook(ExcelFile);
            ExcelWorksheet = ExcelWorkbook.getSheetAt(sheetIndex);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static XSSFSheet getExcelWorksheet() {
        return ExcelWorksheet;
    }

    //Read data from excel file
    public static String getCellData (int rowNum, int colNum) {
        try {
            Cell = ExcelWorksheet.getRow(rowNum).getCell(colNum);
            String cellData = Cell.getStringCellValue();
            return cellData;

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    //Write data into excel file
    public static void setCellData (String Result, int rowNum, int colNum) {
        try {
            Row = ExcelWorksheet.getRow(rowNum);
            Cell = Row.getCell(colNum, org.apache.poi.ss.usermodel.Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            if(Cell == null) {
                Cell = Row.createCell(colNum);
                Cell.setCellValue(Result);
            } else {
                Cell.setCellValue(Result);
            }

            FileOutputStream fileOut = new FileOutputStream(Constants.excelLocation + Constants.excelFile);
            ExcelWorkbook.write(fileOut);
            fileOut.flush();
            fileOut.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
