package com.baeldung.poi.excel.insert;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ExcelInsertRowUnitTest {

    private static final String FILE_NAME = "test.xlsx";
    private static final String NEW_FILE_NAME = "new_test.xlsx";
    private String fileLocation;

    @BeforeEach
    public void setup() throws URISyntaxException {
        fileLocation = Paths.get(ClassLoader.getSystemResource(FILE_NAME)
                .toURI())
            .toString();
    }

    @Test
    void givenWorkbook_whenInsertRowBetween_thenRowCreated() throws IOException {
        int startRow = 2;
        int rowNumber = 1;
        Workbook workbook = new XSSFWorkbook(fileLocation);
        Sheet sheet = workbook.getSheetAt(0);
        int lastRow = sheet.getLastRowNum();
        if (lastRow < startRow) {
            sheet.createRow(startRow);
        }
        sheet.shiftRows(startRow, lastRow, rowNumber, true, true);
        sheet.createRow(startRow);
        FileOutputStream outputStream = new FileOutputStream(NEW_FILE_NAME);
        workbook.write(outputStream);
        File file = new File(NEW_FILE_NAME);
        int expectedResult = lastRow + 1;
        Assertions.assertEquals(expectedResult, workbook.getSheetAt(0).getLastRowNum());

        outputStream.close();
        file.delete();
        workbook.close();
    }

}