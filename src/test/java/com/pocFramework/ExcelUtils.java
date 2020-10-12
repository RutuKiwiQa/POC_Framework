package com.pocFramework;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Rutu shah.
 * Date: 9th October 2020
 * Time:
 * Web POC_Framework
 */

public class ExcelUtils {

    static XSSFWorkbook workbook;
    static XSSFSheet sheet;

    public static Properties dataFile = new Properties();

    /**A constructor.*/
    public ExcelUtils(String excelPath, String sheetName) throws IOException {

        workbook = new XSSFWorkbook(excelPath);
        sheet = workbook.getSheet(sheetName);

    }

    public static void main(String[] args) throws IOException {
        getRowCount();
        getColumnCount();
    }

    /** To get row count of current sheet */
    public static int getRowCount() throws IOException {
        int rowCount = 0;
        try {
            rowCount = sheet.getPhysicalNumberOfRows();  //method which gives numbers of rows.
            // System.out.println("No of rows : "  + rowCount);
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            // e.printStackTrace();
        }
        return rowCount;
    }

    /** To get column count of current sheet */
    public static int getColumnCount() throws IOException {
        int colCount = 0;
        try {
            colCount = sheet.getRow(0).getPhysicalNumberOfCells();  //method which gives numbers of rows.
            //   System.out.println("No of columns : "  + colCount);
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            // e.printStackTrace();
        }
        return colCount;
    }

    public static String getCellData(String excelPath, String sheetName, int rowNum, int colNum) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(excelPath);
        XSSFSheet sheet = workbook.getSheet(sheetName);
        DataFormatter formatter = new DataFormatter();
        Object value = formatter.formatCellValue(sheet.getRow(rowNum).getCell(colNum));
        System.out.println("Cell Data : " + value);
        return String.valueOf(value);
    }

    public static Properties loadPropertyFile(String filePath) throws IOException {
        File f = new File(filePath);
        FileInputStream fs = new FileInputStream(f);
        try{
            dataFile.load(fs);
        }catch (IOException e) {
            e.printStackTrace();
        }

        return dataFile;
    }

    public static String getValueFromKey(String Key){
        String getKey = dataFile.getProperty(Key);
        return  getKey;
    }
}
