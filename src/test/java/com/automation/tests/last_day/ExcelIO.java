package com.automation.tests.last_day;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.*;
import java.util.Date;

public class ExcelIO {
    @Test
    public void readData() throws IOException {
        try(FileInputStream fileInputStream = new FileInputStream("VytrackTestUsers.xlsx");
            Workbook workbook = WorkbookFactory.create(fileInputStream);
            FileOutputStream fileOutputStream = new FileOutputStream("VytrackTestUsers.xlsx");
        ) // public interface AutoClosable

        {
            Sheet workSheet = workbook.getSheetAt(0);
            Row row1 = workSheet.getRow(0); // to get first row

            for (int rowIndex = 0; rowIndex < workSheet.getPhysicalNumberOfRows(); rowIndex++) {
                Row row = workSheet.getRow(rowIndex);
                for (int cellIndex = 0; cellIndex < row.getPhysicalNumberOfCells(); cellIndex++) {
                    Cell cell = row.getCell(cellIndex);
                    System.out.println(cell + " ");
                }
                System.out.println();
            }
            // System.gc(); redundant
            row1.createCell(4);// we create a cell in the first row
            row1.getCell(4).setCellValue("Test Results"); // then set a value
            workbook.write(fileOutputStream); // to apply changes to the file, you need this line
            // do not open file manually, otherwise file will be corrupted
        }
    }

    @Test
    public void createAExcelFile() throws Exception{
        Date date = new Date();
        String fileName = date.toString().replace(" ", "_").
                replace(":", "_")+"_test_results.xlsx";
        System.out.println("fileName = " + fileName);
        File file = new File(fileName);
        file.createNewFile();
        try(
                FileOutputStream fileOutputStream = new FileOutputStream(fileName)){
                XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
                xssfWorkbook.createSheet("test_results");
                xssfWorkbook.write(fileOutputStream);
        }

    }

}
