package com.luma.qa.util;

import com.luma.qa.logger.Log;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelUtil {
    private static final String TESTDATA_SHEET_PATH = "./src/main/resources/testdata/LumaData.xlsx";

    public Object[][] getTestData(String userdataSheet) {
        FileInputStream inputStream;
        Object[][] userdata = null;
        try {
            inputStream = new FileInputStream(TESTDATA_SHEET_PATH);
            Workbook book = WorkbookFactory.create(inputStream);
            Sheet sheet = book.getSheet(userdataSheet);
            int rowSize = sheet.getLastRowNum();
            int colSize = sheet.getRow(0).getLastCellNum();
            userdata = new Object[rowSize][colSize];
            for(int i=1; i<=rowSize; i++) {
                for(int j=0; j<colSize; j++) {
                    userdata[i][j] =sheet.getRow(i).getCell(j).toString();
                }
            }
        } catch (FileNotFoundException e) {
            Log.error("Excel file Not found..", e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            Log.error("Exception reading excel file", e);
            throw new RuntimeException(e);
        }
        return userdata;
    }
}
