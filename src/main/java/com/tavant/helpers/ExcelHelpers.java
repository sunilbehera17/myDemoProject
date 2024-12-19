package com.tavant.helpers;

import com.tavant.exceptions.InvalidPathForExcelException;
import com.tavant.utils.LogUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.util.Hashtable;
import java.util.HashMap;
import java.util.Map;

public class ExcelHelpers {

    private FileInputStream fis;
    private FileOutputStream fileOut;
    private Workbook workbook;
    private Sheet sheet;
    private Cell cell;
    private Row row;
    private String excelFilePath;
    private Map<String, Integer> columns = new HashMap<>();
    private Map<String, Integer> rows = new HashMap<>();

    public ExcelHelpers() {
    }

    // Set Excel File and Sheet
    public void setExcelFile(String excelPath, String sheetName) {
        LogUtils.info("Set Excel File: " + excelPath);
        LogUtils.info("Sheet Name: " + sheetName);

        try {
            File f = new File(excelPath);

            if (!f.exists()) {
                LogUtils.info("Excel file path not found.");
                throw new InvalidPathForExcelException("Excel file path not found.");
            }

            if (sheetName.isEmpty()) {
                LogUtils.info("The Sheet Name is empty.");
                throw new InvalidPathForExcelException("The Sheet Name is empty.");
            }

            fis = new FileInputStream(excelPath);
            workbook = WorkbookFactory.create(fis);
            sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                LogUtils.info("Sheet name not found.");
                throw new InvalidPathForExcelException("Sheet name not found.");
            }

            excelFilePath = excelPath;

            // Add all the column header names to the map 'columns'
            sheet.getRow(0).forEach(cell -> {
                columns.put(cell.getStringCellValue(), cell.getColumnIndex());
            });

            // Populate the row names map (assuming row names are in the first column)
            populateRowNames();

        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.error(e.getMessage());
        }
    }

    // Populate the rows map with row names (assumed to be in the first column)
    public void populateRowNames() {
        if (sheet != null) {
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {  // Starting from 1 to skip the header row
                row = sheet.getRow(i);
                if (row != null) {
                    // Get the first cell (assuming row name is in the first column)
                    cell = row.getCell(0);
                    if (cell != null) {
                        String rowName = cell.getStringCellValue();
                        rows.put(rowName, i);  // Map row name to the row index
                    } else {
                        LogUtils.error("Cell is null at row " + i + ", column 0.");
                    }
                } else {
                    LogUtils.error("Row is null at row " + i);
                }
            }
        }
    }

    // Get cell data using row name and column name
    public String getCellData(String rowName, String columnName) {
        // Retrieve the row index from the rows map using the rowName
        Integer rowIndex = rows.get(rowName);
        LogUtils.info("Row index for " + rowName + ": " + rowIndex);

        // Retrieve the column index from the columns map using the columnName
        Integer colIndex = columns.get(columnName);
        LogUtils.info("Column index for " + columnName + ": " + colIndex);

        // Validate if both row and column indices are valid (non-null)
        if (rowIndex != null && colIndex != null) {
            // If both row and column indices are valid, retrieve the cell data
            return getCellData(rowIndex, colIndex);
        } else {
            // If row or column doesn't exist, return an empty string
            LogUtils.error("Invalid row or column name: RowName = " + rowName + ", ColumnName = " + columnName);
            return "";
        }
    }

    // Get cell data using row and column indices
    public String getCellData(int rowNum, int colNum) {
        if (rowNum < 0 || colNum < 0 || rowNum > sheet.getLastRowNum()) {
            LogUtils.error("Invalid row or column index: Row: " + rowNum + ", Column: " + colNum);
            return "";
        }

        try {
            row = sheet.getRow(rowNum);
            if (row != null) {
                cell = row.getCell(colNum);
                String cellData = null;
                if (cell != null) {
                    switch (cell.getCellType()) {
                        case STRING:
                            cellData = cell.getStringCellValue();
                            break;
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                cellData = String.valueOf(cell.getDateCellValue());
                            } else {
                                cellData = String.valueOf(cell.getNumericCellValue());
                            }
                            break;
                        case BOOLEAN:
                            cellData = Boolean.toString(cell.getBooleanCellValue());
                            break;
                        case BLANK:
                            cellData = "";
                            break;
                        default:
                            cellData = "";
                            break;
                    }
                }
                return cellData;
            } else {
                LogUtils.error("Row " + rowNum + " is null.");
                return "";
            }
        } catch (Exception e) {
            LogUtils.error("Error reading cell data: " + e.getMessage());
            return "";
        }
    }

    // Get the total number of rows in the sheet
    public int getRows() {
        try {
            return sheet.getPhysicalNumberOfRows();
        } catch (Exception e) {
            e.printStackTrace();
            throw (e);
        }
    }

    // Get the total number of columns in the sheet
    public int getColumns() {
        try {
            row = sheet.getRow(0);
            return row.getLastCellNum();
        } catch (Exception e) {
            e.printStackTrace();
            throw (e);
        }
    }

    // Write data to excel sheet
    public void setCellData(String text, int rowNumber, int colNumber) {
        try {
            row = sheet.getRow(rowNumber);
            if (row == null) {
                row = sheet.createRow(rowNumber);
            }
            cell = row.getCell(colNumber);

            if (cell == null) {
                cell = row.createCell(colNumber);
            }
            cell.setCellValue(text);

            XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();
            text = text.trim().toLowerCase();
            if ("pass".equals(text) || "passed".equals(text) || "success".equals(text)) {
                style.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
            }
            if ("fail".equals(text) || "failed".equals(text) || "failure".equals(text)) {
                style.setFillForegroundColor(IndexedColors.RED.getIndex());
            }

            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            style.setAlignment(HorizontalAlignment.CENTER);
            style.setVerticalAlignment(VerticalAlignment.CENTER);

            cell.setCellStyle(style);

            fileOut = new FileOutputStream(excelFilePath);
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.error(e.getMessage());
        }
    }

    // Get data as Hashtable from the Excel file
    public Object[][] getDataHashTable(String excelPath, String sheetName, int startRow, int endRow) {
        LogUtils.info("Excel File: " + excelPath);
        LogUtils.info("Sheet Name: " + sheetName);

        Object[][] data = null;

        try {
            File f = new File(excelPath);

            if (!f.exists()) {
                LogUtils.info("File Excel path not found.");
                throw new InvalidPathForExcelException("File Excel path not found.");
            }

            fis = new FileInputStream(excelPath);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);

            int rows = getRows();
            int columns = getColumns();

            LogUtils.info("Row: " + rows + " - Column: " + columns);
            LogUtils.info("StartRow: " + startRow + " - EndRow: " + endRow);

            data = new Object[(endRow - startRow) + 1][1];
            Hashtable<String, String> table = null;
            for (int rowNums = startRow; rowNums <= endRow; rowNums++) {
                table = new Hashtable<>();
                for (int colNum = 0; colNum < columns; colNum++) {
                    table.put(getCellData(0, colNum), getCellData(rowNums, colNum));
                }
                data[rowNums - startRow][0] = table;
            }

        } catch (IOException e) {
            e.printStackTrace();
            LogUtils.error(e.getMessage());
        }

        return data;
    }
}






















    
    
    /*
     

package com.tavant.helpers;

import com.tavant.exceptions.InvalidPathForExcelException;
import com.tavant.utils.LogUtils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class ExcelHelpers {

    private FileInputStream fis;
    private FileOutputStream fileOut;
    private Workbook workbook;
    private Sheet sheet;
    private Cell cell;
    private Row row;
    private String excelFilePath;
    private Map<String, Integer> columns = new HashMap<>();
    private Map<String, Integer> rows = new HashMap<>();

    public ExcelHelpers() {
    }

    //Set Excel File
    public void setExcelFile(String excelPath, String sheetName) {
        LogUtils.info("Set Excel File: " + excelPath);
        LogUtils.info("Sheet Name: " + sheetName);

        try {
            File f = new File(excelPath);

            if (!f.exists()) {
                try {
                    LogUtils.info("File Excel path not found.");
                    throw new InvalidPathForExcelException("File Excel path not found.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (sheetName.isEmpty()) {
                try {
                    LogUtils.info("The Sheet Name is empty.");
                    throw new InvalidPathForExcelException("The Sheet Name is empty.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            fis = new FileInputStream(excelPath);
            workbook = WorkbookFactory.create(fis);
            sheet = workbook.getSheet(sheetName);
            //sh = wb.getSheetAt(0); //0 - index of 1st sheet
            if (sheet == null) {
                //sh = wb.createSheet(sheetName);
                try {
                    LogUtils.info("Sheet name not found.");
                    throw new InvalidPathForExcelException("Sheet name not found.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            excelFilePath = excelPath;

            //adding all the column header names to the map 'columns'
            sheet.getRow(0).forEach(cell -> {
                columns.put(cell.getStringCellValue(), cell.getColumnIndex());
            });

        } catch (Exception e) {
            e.getMessage();
            LogUtils.error(e.getMessage());
        }
    }

    //This method takes the row number as a parameter and returns the data for that row.
    public Row getRowData(int rowNum) {
        row = sheet.getRow(rowNum);
        return row;
    }


    public Object[][] getExcelData(String excelPath, String sheetName) {
        Object[][] data = null;
        Workbook workbook = null;

        LogUtils.info("Set Excel file " + excelPath);
        LogUtils.info("Selected Sheet: " + sheetName);

        try {

            File f = new File(excelPath);

            if (!f.exists()) {
                try {
                    LogUtils.info("File Excel path not found.");
                    throw new InvalidPathForExcelException("File Excel path not found.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (sheetName.isEmpty()) {
                try {
                    LogUtils.info("The Sheet Name is empty.");
                    throw new InvalidPathForExcelException("The Sheet Name is empty.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // load the file
            FileInputStream fis = new FileInputStream(excelPath);

            // load the workbook
            workbook = new XSSFWorkbook(fis);
            // load the sheet
            Sheet sheet = workbook.getSheet(sheetName);
            // load the row
            Row row = sheet.getRow(0);

            int noOfRows = sheet.getPhysicalNumberOfRows();
            int noOfCols = row.getLastCellNum();

            System.out.println(noOfRows + " - " + noOfCols);

            Cell cell;
            data = new Object[noOfRows - 1][noOfCols];

            //FOR loop runs from 1 to drop header line (headline is 0)
            for (int i = 1; i < noOfRows; i++) {
                for (int j = 0; j < noOfCols; j++) {
                    row = sheet.getRow(i);
                    cell = row.getCell(j);

                    //This is used to determine the data type from cells in Excel and then convert it to String for ease of reading
                    switch (cell.getCellType()) {
                        case STRING:
                            data[i - 1][j] = cell.getStringCellValue();
                            break;
                        case NUMERIC:
                            data[i - 1][j] = String.valueOf(cell.getNumericCellValue());
                            break;
                        case BLANK:
                            data[i - 1][j] = "";
                            break;
                        default:
                            data[i - 1][j] = null;
                            break;
                    }
                }
            }
        } catch (Exception e) {
            e.getMessage();
            throw new RuntimeException(e);
        }
        return data;
    }

    public Object[][] getDataHashTable(String excelPath, String sheetName, int startRow, int endRow) {
        LogUtils.info("Excel File: " + excelPath);
        LogUtils.info("Sheet Name: " + sheetName);

        Object[][] data = null;

        try {

            File f = new File(excelPath);

            if (!f.exists()) {
                try {
                    LogUtils.info("File Excel path not found.");
                    throw new InvalidPathForExcelException("File Excel path not found.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            fis = new FileInputStream(excelPath);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);

            int rows = getRows();
            int columns = getColumns();

            LogUtils.info("Row: " + rows + " - Column: " + columns);
            LogUtils.info("StartRow: " + startRow + " - EndRow: " + endRow);

            data = new Object[(endRow - startRow) + 1][1];
            Hashtable<String, String> table = null;
            for (int rowNums = startRow; rowNums <= endRow; rowNums++) {
                table = new Hashtable<>();
                for (int colNum = 0; colNum < columns; colNum++) {
                    table.put(getCellData(0, colNum), getCellData(rowNums, colNum));
                }
                data[rowNums - startRow][0] = table;
            }

        } catch (IOException e) {
            e.printStackTrace();
            LogUtils.error(e.getMessage());
        }

        return data;

    }

    public String getTestCaseName(String testCaseName) {
        String value = testCaseName;
        int position = value.indexOf("@");
        value = value.substring(0, position);
        position = value.lastIndexOf(".");

        value = value.substring(position + 1);
        return value;
    }

    public int getRowContains(String sTestCaseName, int colNum) {
        int i;
        int rowCount = getRows();
        for (i = 0; i < rowCount; i++) {
            if (getCellData(i, colNum).equalsIgnoreCase(sTestCaseName)) {
                break;
            }
        }
        return i;
    }

    public int getRows() {
        try {
            return sheet.getLastRowNum();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw (e);
        }
    }

    public int getColumns() {
        try {
            row = sheet.getRow(0);
            return row.getLastCellNum();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw (e);
        }
    }
    public String getCellData(int rowNo, String columnName) {
        return getCellData(rowNo, columns.get(columnName));
    }
    
    

    // Get cell data
    public String getCellData(int rowNum, int colNum) {
        try {
            cell = sheet.getRow(rowNum).getCell(colNum);
            String CellData = null;
            switch (cell.getCellType()) {
                case STRING:
                    CellData = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        CellData = String.valueOf(cell.getDateCellValue());
                    } else {
                        CellData = String.valueOf((long) cell.getNumericCellValue());
                    }
                    break;
                case BOOLEAN:
                    CellData = Boolean.toString(cell.getBooleanCellValue());
                    break;
                case BLANK:
                    CellData = "";
                    break;
            }
            return CellData;
        } catch (Exception e) {
            return "";
        }
    }
    
    public String getCellData(Object rowValue, String columnName) {
        Integer rowIndex = null;
        
        // Check if the rowValue is a row index (Integer)
        if (rowValue instanceof Integer) {
            rowIndex = (Integer) rowValue;
        }
        // Check if the rowValue is a row name (String) and fetch the index from the rows map
        else if (rowValue instanceof String) {
            rowIndex = rows.get((String) rowValue);
        }
        
        // Get the column index from the columns map
        Integer colIndex = columns.get(columnName);
        
        // Validate if row and column indices are found
        if (rowIndex != null && colIndex != null) {
            return getCellData(rowIndex, colIndex); // Call the existing method
        } else {
            // If either row or column index is not found, return an empty string
            return "";
        }
    }
	
    
   
    public String getCellData(String rowName, String columnName) {
        // Retrieve the row index from the rows map using the rowName
        Integer rowIndex = rows.get(rowName);
        System.out.println(rowIndex);
        // Retrieve the column index from the columns map using the columnName
        Integer colIndex = columns.get(columnName);
        System.out.println(rowIndex);

        // Validate if both row and column indices are valid (non-null)
        if (rowIndex != null && colIndex != null) {
            // Call the existing method to retrieve the actual cell data
            return getCellData(rowIndex, colIndex);
        } else {
            // If row or column doesn't exist, return an empty string
            return "";
        }
    }
    
    
    
    
   

    public String getCellData(String columnName, int rowNum) {
        return getCellData(rowNum, columns.get(columnName));
    }

    // Write data to excel sheet
    public void setCellData(String text, int rowNumber, int colNumber) {
        try {
            row = sheet.getRow(rowNumber);
            if (row == null) {
                row = sheet.createRow(rowNumber);
            }
            cell = row.getCell(colNumber);

            if (cell == null) {
                cell = row.createCell(colNumber);
            }
            cell.setCellValue(text);

            XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();
            text = text.trim().toLowerCase();
            if (text == "pass" || text == "passed" || text == "success") {
                style.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
            }
            if (text == "fail" || text == "failed" || text == "failure") {
                style.setFillForegroundColor(IndexedColors.RED.getIndex());
            }
            style.setFillPattern(FillPatternType.NO_FILL);
            style.setAlignment(HorizontalAlignment.CENTER);
            style.setVerticalAlignment(VerticalAlignment.CENTER);

            cell.setCellStyle(style);

            fileOut = new FileOutputStream(excelFilePath);
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (Exception e) {
            e.getMessage();
            LogUtils.error(e.getMessage());
        }
    }

    public void setCellData(String text, int rowNumber, String columnName) {
        try {
            row = sheet.getRow(rowNumber);
            if (row == null) {
                row = sheet.createRow(rowNumber);
            }
            cell = row.getCell(columns.get(columnName));

            if (cell == null) {
                cell = row.createCell(columns.get(columnName));
            }
            cell.setCellValue(text);

            XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();
            text = text.trim().toLowerCase();
            if (text == "pass" || text == "passed" || text == "success") {
                style.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
            }
            if (text == "fail" || text == "failed" || text == "failure") {
                style.setFillForegroundColor(IndexedColors.RED.getIndex());
            }

            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            style.setAlignment(HorizontalAlignment.CENTER);
            style.setVerticalAlignment(VerticalAlignment.CENTER);

            cell.setCellStyle(style);

            fileOut = new FileOutputStream(excelFilePath);
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (Exception e) {
            e.getMessage();
            LogUtils.error(e.getMessage());
        }
    }


}

 
     
     */
    
    

