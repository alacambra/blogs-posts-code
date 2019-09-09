package tech.lacambra.poi;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;

public class Model {

  public void renderTitle(Workbook workbook, Sheet sheet, CellReference posReference, CellStyle cs) {


    CellRangeAddress cellAddresses = new CellRangeAddress(posReference.getRow(), posReference.getRow(), 0, 24);
    sheet.addMergedRegion(cellAddresses);

    Row row = sheet.createRow(posReference.getRow());
    Cell cell = row.createCell(0);

    CellStyle cellStyle = workbook.createCellStyle();
    cellStyle.setFillPattern(cs.getFillPattern());
//    cellStyle.setFillBackgroundColor(cs.getFillBackgroundColor());
    cellStyle.setFillForegroundColor(cs.getFillForegroundColor());

    cell.setCellStyle(cellStyle);
//    row.setRowStyle(cellStyle);
    cell.setCellValue("4)  Zusatzentgelte");


  }

}
