package tech.lacambra.poi;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

public class PoiXlslTest {

  @Test
  void t() throws IOException, InvalidFormatException {

    String a = "/Users/albertlacambra/Downloads/annex1-exportDemo copy.xlsx";

    XSSFWorkbook oWorkbook = new XSSFWorkbook(new File(a));
    FileOutputStream fileOutputStream = new FileOutputStream("/Users/albertlacambra/Downloads/annex1-export-demo.out.xlsx");
    oWorkbook.write(fileOutputStream);
    oWorkbook.close();

    XSSFWorkbook workbook = new XSSFWorkbook(new File("/Users/albertlacambra/Downloads/annex1-export-demo.out.xlsx"));
    XSSFSheet sheet = workbook.getSheetAt(0);
    XSSFSheet oSheet = workbook.getSheetAt(1);

    Row zusatzentgelteHeader = oSheet.getRow(new CellReference("A17").getRow());

//    System.out.println("scacacascacascasc" + zusatzentgelteHeader.getCell(0).getCellStyle().getFillBackgroundColor());

    Row position = oSheet.getRow(new CellReference("A1").getRow());
    Row zusatzentgelteH = oSheet.getRow(new CellReference("A6").getRow());
    Row zusatzentgelteContentsH = oSheet.getRow(new CellReference("A10").getRow());
    Row zusatzentgelteContentsB = oSheet.getRow(new CellReference("A11").getRow());
//
//    CellReference cr = new CellReference("A20");
//    Row row = sheet.getRow(cr.getRow());
//    Cell cell = row.getCell(cr.getCol());
//    CellStyle cellStyle = cell.getCellStyle();
//    cellStyle.setWrapText(true);
//    cell.setCellStyle(cellStyle);
//    cell.setCellValue("jajaja");
//
//    CellCopyPolicy cellCopyPolicy = new CellCopyPolicy();
//    cellCopyPolicy.setCopyCellFormula(true);

    int totalPositions = 1;
    int totalEntgelds = 1;
    int offset = 0;

//    new Model().renderTitle(workbook, sheet, new CellReference("A40"), oSheet.getRow(16).getCell(0).getCellStyle());


//    CellReference reference1 = new CellReference("A17");
//    CellReference reference2 = new CellReference("A27");
//    sheet.shiftRows(reference1.getRow(), reference2.getRow(), 200);


    for (int i = 0; i < totalPositions; i++) {
      int destRow = new CellReference("A14").getRow() + i + offset;
      sheet.copyRows(Collections.singletonList(position), destRow, new CellCopyPolicy.Builder().mergedRegions(false).build());
      sheet.getRow(destRow).getCell(0).setCellValue(i);

    }

    int startEntgeld = new CellReference("A14").getRow() + totalPositions + offset + 1;
    sheet.copyRows(Collections.singletonList(zusatzentgelteH), startEntgeld, new CellCopyPolicy.Builder().cellStyle(true).cellValue(true).cellFormula(true).build());


    for (int i = 0; i < totalEntgelds * 4; i = i + 4) {

      int pos = startEntgeld + 2 + i;
      sheet.copyRows(Arrays.asList(zusatzentgelteContentsH, zusatzentgelteContentsB), pos, new CellCopyPolicy());
      sheet.getRow(pos).getCell(0).setCellValue("Bemerkung on ZE " + pos);

      Cell c = sheet.getRow(pos + 1).getCell(0);
      c.setCellValue("Value on " + pos);
      CellStyle style = c.getCellStyle();
      style.setFillForegroundColor(IndexedColors.RED.getIndex());

      c.setCellStyle(style);

    }

    // Closing the workbook
    workbook.write(new FileOutputStream("/Users/albertlacambra/Downloads/annex1-export-demo.out1.xlsx"));
    workbook.close();
  }

  private static Row copyRow(Workbook workbook, Sheet worksheet, int sourceRowNum, int destinationRowNum) {
    // Get the source / new row
    Row newRow = worksheet.getRow(destinationRowNum);
    Row sourceRow = worksheet.getRow(sourceRowNum);

    // If the row exist in destination, push down all rows by 1 else create a new row
    if (newRow != null) {
      worksheet.shiftRows(destinationRowNum, worksheet.getLastRowNum(), 1);
    } else {
      newRow = worksheet.createRow(destinationRowNum);
    }

    // Loop through source columns to add to new row
    for (int i = 0; i < sourceRow.getLastCellNum(); i++) {
      // Grab a copy of the old/new cell
      Cell oldCell = sourceRow.getCell(i);
      Cell newCell = newRow.createCell(i);

      // If the old cell is null jump to next cell
      if (oldCell == null) {
        newCell = null;
        continue;
      }

      // Copy style from old cell and apply to new cell
      CellStyle newCellStyle = workbook.createCellStyle();
      newCellStyle.cloneStyleFrom(oldCell.getCellStyle());
      ;
      newCell.setCellStyle(newCellStyle);

      // Set the cell data type
      newCell.setCellType(oldCell.getCellType());

    }
    //..
    //..
    return newRow;
  }

  @Disabled
  @Test
  void t1() throws IOException, InvalidFormatException {

    String a = "/Users/albertlacambra/Downloads/annex1-exportDemo copy.xlsx";

    XSSFWorkbook oWorkbook = new XSSFWorkbook(new File(a));
    FileOutputStream fileOutputStream = new FileOutputStream("/Users/albertlacambra/Downloads/annex1-export-demo.out.xlsx");
    oWorkbook.write(fileOutputStream);

    XSSFWorkbook workbook = new XSSFWorkbook(new File("/Users/albertlacambra/Downloads/annex1-export-demo.out.xlsx"));

    // 1. You can obtain a sheetIterator and iterate over it
    Iterator<Sheet> sheetIterator = workbook.sheetIterator();
    System.out.println("Retrieving Sheets using Iterator");
    while (sheetIterator.hasNext()) {
      Sheet sheet = sheetIterator.next();
      System.out.println("=> " + sheet.getSheetName());
    }

    // 2. Or you can use a for-each loop
    System.out.println("Retrieving Sheets using for-each loop");
    for (Sheet sheet : workbook) {
      System.out.println("=> " + sheet.getSheetName());
    }

    // 3. Or you can use a Java 8 forEach with lambda
    System.out.println("Retrieving Sheets using Java 8 forEach with lambda");
    workbook.forEach(sheet -> {
      System.out.println("=> " + sheet.getSheetName());
    });

        /*
           ==================================================================
           Iterating over all the rows and columns in a Sheet (Multiple ways)
           ==================================================================
        */

    // Getting the Sheet at index zero
    XSSFSheet sheet = workbook.getSheetAt(0);
    XSSFSheet oSheet = oWorkbook.getSheetAt(0);

    // Create a DataFormatter to format and get each cell's value as String
    DataFormatter dataFormatter = new DataFormatter();

    // 1. You can obtain a rowIterator and columnIterator and iterate over them
    System.out.println("\n\nIterating over Rows and Columns using Iterator\n");
    Iterator<Row> rowIterator = sheet.rowIterator();
    while (rowIterator.hasNext()) {
      Row row = rowIterator.next();

      // Now let's iterate over the columns of the current row
      Iterator<Cell> cellIterator = row.cellIterator();

//      Cell cell1 = row.createCell(0);
//      cell1.setCellValue("albert albert albert\n valuevaluevaluevalue");
//      CellStyle cellStyle = workbook.createCellStyle();
//      cellStyle.setWrapText(true);
//      cell1.setCellStyle(cellStyle);

      while (cellIterator.hasNext()) {
        Cell cell = cellIterator.next();
        String cellValue = dataFormatter.formatCellValue(cell);
        System.out.print(cellValue + "\t");
      }
      System.out.println();
    }

    // 2. Or you can use a for-each loop to iterate over the rows and columns
    System.out.println("\n\nIterating over Rows and Columns using for-each loop\n");
    for (Row row : sheet) {
      for (Cell cell : row) {
        String cellValue = dataFormatter.formatCellValue(cell);
        System.out.print(cellValue + "\t");
      }
      System.out.println();
    }

    // 3. Or you can use Java 8 forEach loop with lambda
    System.out.println("\n\nIterating over Rows and Columns using Java 8 forEach with lambda\n");
    sheet.forEach(row -> {
      row.forEach(cell -> {
        String cellValue = dataFormatter.formatCellValue(cell);
        System.out.print(cellValue + "\t");
      });
      System.out.println();
    });

    workbook.write(new FileOutputStream("/Users/albertlacambra/Downloads/annex1-export-demo.out1.xlsx"));
    workbook.close();
    oWorkbook.close();

  }


}