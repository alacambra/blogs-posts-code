package tech.lacambra.poi;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;


public class PdfTest {

  @Test
  void pdfItextSimple() throws FileNotFoundException, DocumentException {

    Document document = new Document();
    PdfWriter.getInstance(document, new FileOutputStream("/Users/albertlacambra/Downloads/pdfItextSimple.pdf"));

    document.open();
    Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);

    Chunk chunk = new Chunk("Hello World", font);

    document.add(chunk);
    document.close();

  }


  @Test
  void pdfITextTable() throws IOException, DocumentException, URISyntaxException {

    Document document = new Document();
    PdfWriter.getInstance(document, new FileOutputStream("/Users/albertlacambra/Downloads/pdfITextTable.pdf"));

    document.open();

    PdfPTable table = new PdfPTable(3);
    addTableHeader(table);
    addRows(table);
    addCustomRows(table);

    document.add(table);
    document.close();

  }

  private void addTableHeader(PdfPTable table) {
    Stream.of("column header 1", "column header 2", "column header 3")
        .forEach(columnTitle -> {
          PdfPCell header = new PdfPCell();
          header.setBackgroundColor(BaseColor.LIGHT_GRAY);
          header.setBorderWidth(2);
          header.setPhrase(new Phrase(columnTitle));
          table.addCell(header);
        });

  }

  private void addRows(PdfPTable table) {

    table.addCell("row 1, col 1");
    table.addCell("row 1, col 2");
    table.addCell("row 1, col 3");
  }

  private void addCustomRows(PdfPTable table)
      throws URISyntaxException, BadElementException, IOException {

    Path path = Paths.get("/Users/albertlacambra/Downloads/stc-50-aerea.jpg");
    Image img = Image.getInstance(path.toAbsolutePath().toString());
    img.scalePercent(10);

    PdfPCell imageCell = new PdfPCell(img);
    table.addCell(imageCell);

    PdfPCell horizontalAlignCell = new PdfPCell(new Phrase("row 2, col 2"));
    horizontalAlignCell.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(horizontalAlignCell);

    PdfPCell verticalAlignCell = new PdfPCell(new Phrase("row 2, col 3"));
    verticalAlignCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
    table.addCell(verticalAlignCell);
  }

  @Test
  void testPdfBox() throws IOException {
    PDDocument document = new PDDocument();
    PDPage page = new PDPage();
    document.addPage(page);

    PDPageContentStream contentStream = new PDPageContentStream(document, page);

    contentStream.setFont(PDType1Font.COURIER, 12);
    contentStream.beginText();
    contentStream.showText("Hello World");
    contentStream.endText();
    contentStream.close();

    document.save("/Users/albertlacambra/Downloads/pdfBoxHelloWorld.pdf");
    document.close();
  }

}


